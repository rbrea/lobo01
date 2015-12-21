package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
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
import com.icetea.manager.pagodiario.utils.BillUtils;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class DiscountServiceImpl extends
		BasicIdentifiableServiceImpl<Discount, DiscountDao, DiscountDto, DiscountDtoModelTransformer> 
			implements DiscountService {

	private final BillDao billDao;
	private final BillUtils billUtils;
	
	@Inject
	public DiscountServiceImpl(DiscountDao dao,
			DiscountDtoModelTransformer transformer, 
			BillDao billDao,
			BillUtils billUtils) {
		super(dao, transformer);
		this.billDao = billDao;
		this.billUtils = billUtils;
	}

	@Override
	public DiscountDto insert(DiscountDto o) {
		
		Bill bill = this.billDao.findById(o.getBillId());
		
		ErrorTypedConditions.checkArgument(bill != null, ErrorType.BILL_NOT_FOUND);
		
		Date selectedDate = DateUtils.parseDate(o.getDate(), "dd/MM/yyyy");
		
		ErrorTypedConditions.checkArgument(!selectedDate.before(bill.getStartDate()), 
				"La fecha elegida no puede ser menor a la fecha de inicio de Factura");
		
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
		
		Discount e = new Discount();
		e.setAmount(amount);
		e.setDate(selectedDate);
		e.setObservations(o.getObservations());
		e.setBill(bill);
		e.setInstallmentAmount(newInstallmentAmount);
		
		this.getDao().saveOrUpdate(e);
		
		bill.getDiscounts().add(e);
		bill.setTotalAmount(NumberUtils.subtract(bill.getTotalAmount(), e.getAmount()));
		bill.setRemainingAmount(NumberUtils.subtract(bill.getRemainingAmount(), e.getAmount()));
		bill.setTotalDailyInstallment(newInstallmentAmount);

		if(!NumberUtils.isNullOrZero(bill.getTotalDailyInstallment())){
			// [roher] actualizo los dias de atraso segun el nuevo monto de cuota diaria y el nuevo remanente
			int overdueDays = e.getAmount().divide(
					bill.getTotalDailyInstallment(), RoundingMode.DOWN).intValue();
			bill.decrementOverdueDays(overdueDays);
		}

		this.billUtils.doBillCancelation(bill);
		
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
