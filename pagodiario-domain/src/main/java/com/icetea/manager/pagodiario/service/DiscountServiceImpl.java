package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.icetea.manager.pagodiario.api.dto.DiscountDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.DiscountDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Discount;
import com.icetea.manager.pagodiario.transformer.DiscountDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class DiscountServiceImpl extends
		BasicIdentifiableServiceImpl<Discount, DiscountDao, DiscountDto, DiscountDtoModelTransformer> 
			implements DiscountService {

	private BillDao billDao;
	
	@Inject
	public DiscountServiceImpl(DiscountDao dao,
			DiscountDtoModelTransformer transformer, BillDao billDao) {
		super(dao, transformer);
		this.billDao = billDao;
	}

	@Override
	public DiscountDto insert(DiscountDto o) {
		
		Bill bill = this.billDao.findById(o.getBillId());
		
		ErrorTypedConditions.checkArgument(bill != null, ErrorType.BILL_NOT_FOUND);
		
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(o.getAmount()), 
				"El monto de descuento es requerido", ErrorType.VALIDATION_ERRORS);
		
		BigDecimal amount = NumberUtils.toBigDecimal(o.getAmount());
		
		ErrorTypedConditions.checkArgument(!NumberUtils.isNegative(amount), 
				"El monto de descuento no puede ser menor a 0.", ErrorType.VALIDATION_ERRORS);

		ErrorTypedConditions.checkArgument(amount.compareTo(bill.getTotalAmount()) <= 0, 
				String.format("El monto ingresado %s no puede ser mayor al total facturado %s", 
						o.getAmount(), NumberUtils.toString(bill.getTotalAmount())), ErrorType.VALIDATION_ERRORS);
		
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(o.getInstallmentAmount()), 
				"El nuevo monto de la cuota diaria es requerido", ErrorType.VALIDATION_ERRORS);
		
		BigDecimal newInstallmentAmount = NumberUtils.toBigDecimal(o.getInstallmentAmount());
		
		ErrorTypedConditions.checkArgument(!NumberUtils.isNegative(newInstallmentAmount), 
				"El nuevo monto de la cuota diaria no puede ser menor a 0.", 
				ErrorType.VALIDATION_ERRORS);
		
		ErrorTypedConditions.checkArgument(newInstallmentAmount.compareTo(bill.getRemainingAmount()) <= 0, 
				String.format("El nuevo monto de la cuota diaria no puede ser mayor al monto restante: %s", 
						NumberUtils.toString(bill.getRemainingAmount())), 
				ErrorType.VALIDATION_ERRORS);
		
		Discount e = new Discount();
		e.setAmount(amount);
		e.setDate(DateUtils.parseDate(o.getDate(), "dd/MM/yyyy"));
		e.setObservations(o.getObservations());
		e.setBill(bill);
		e.setInstallmentAmount(newInstallmentAmount);
		
		this.getDao().saveOrUpdate(e);
		
		bill.getDiscounts().add(e);
		bill.setTotalAmount(NumberUtils.subtract(bill.getTotalAmount(), e.getAmount()));
		bill.setRemainingAmount(NumberUtils.subtract(bill.getRemainingAmount(), e.getAmount()));
		bill.setTotalDailyInstallment(newInstallmentAmount);

		// FIXME: [roher] tal vez tenga q actualizar los dias de atraso ...
		
		this.billDao.saveOrUpdate(bill);
		
		return this.getTransformer().transform(e);
	}

	@Override
	public DiscountDto update(DiscountDto o) {
		return null;
	}

	@Override
	public List<DiscountDto> search(Long billId){
		
		ErrorTypedConditions.checkArgument(billId != null, ErrorType.BILL_REQUIRED);
		
		return this.getTransformer().transformAllTo(this.getDao().findByBillId(billId));
	}
	
}
