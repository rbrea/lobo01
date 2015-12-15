package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.NotImplementedException;

import com.icetea.manager.pagodiario.api.dto.ProductReductionDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.PayrollDao;
import com.icetea.manager.pagodiario.dao.ProductReductionDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bill.Status;
import com.icetea.manager.pagodiario.model.ProductReduction;
import com.icetea.manager.pagodiario.transformer.ProductReductionDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class ProductReductionServiceImpl
		extends BasicIdentifiableServiceImpl<ProductReduction, ProductReductionDao, 
			ProductReductionDto, ProductReductionDtoModelTransformer> implements ProductReductionService {
	
	private final BillDao billDao;
	private final PayrollDao payrollDao;
	
	@Inject
	public ProductReductionServiceImpl(ProductReductionDao dao,
			ProductReductionDtoModelTransformer transformer,
			BillDao billDao,
			PayrollDao payrollDao) {
		super(dao, transformer);
		this.billDao = billDao;
		this.payrollDao = payrollDao;
	}

	@Override
	public ProductReductionDto insert(ProductReductionDto o) {
		Bill bill = this.billDao.findById(o.getBillId());
		
		ErrorTypedConditions.checkArgument(bill != null, ErrorType.BILL_NOT_FOUND);
		
		Date selectedDate = DateUtils.parseDate(o.getDate(), "dd/MM/yyyy");
		
		ErrorTypedConditions.checkArgument(!selectedDate.before(bill.getCreatedDate()), 
				"La fecha elegida no puede ser menor a la fecha de inicio de Factura");
		
		BigDecimal amount = NumberUtils.toBigDecimal(o.getAmount());
		
		ErrorTypedConditions.checkArgument(!NumberUtils.isNegative(amount), 
				String.format("El monto ingresado %s no puede ser menor a 0 (cero)", o.getAmount()));

		ErrorTypedConditions.checkArgument(amount.compareTo(bill.getRemainingAmount()) <= 0, 
				String.format("El monto ingresado %s no puede ser mayor al saldo restante %s", 
						o.getAmount(), NumberUtils.toString(bill.getRemainingAmount())), ErrorType.VALIDATION_ERRORS);
		
		ProductReduction e = new ProductReduction();
		e.setAmount(amount);
		e.setDate(selectedDate);
		e.setObservations(o.getObservations());
		e.setBill(bill);
		
		this.getDao().saveOrUpdate(e);
		
		bill.getProductReductionList().add(e);
		bill.setStatus(Status.REDUCED);
		Date now = DateUtils.now();
		bill.setUpdatedDate(now);
		bill.setCompletedDate(now);
		// [roher] le actualizo al cliente la marca de que alguna vez tuvo un pago incompleto ...
		bill.getClient().setReductionMark(now);
		
		this.billDao.saveOrUpdate(bill);
		
		return this.getTransformer().transform(e);
	}

	@Override
	public ProductReductionDto update(ProductReductionDto o) {
		throw new NotImplementedException("not implemented");
	}

	@Override
	public List<ProductReductionDto> search(Long billId){
		
		ErrorTypedConditions.checkArgument(billId != null, ErrorType.BILL_REQUIRED);
		
		return this.getTransformer().transformAllTo(this.getDao().findByBillId(billId));
	}

	@Override
	public boolean remove(Long id) {
		
		ErrorTypedConditions.checkArgument(id != null, "El identificador de la BAJA es requerido");
		
		ProductReduction e = this.getDao().findById(id);
		
		ErrorTypedConditions.checkArgument(e != null, "No se ha encontrado la BAJA solicitada");
		
		ErrorTypedConditions.checkArgument(e.getDate() != null, "La fecha de BAJA no puede estar vacía");

		// FIXME: [roher] hay que evaluar si existe una liq hecha en donde este la baja q se quiere remove?
		
		Bill bill = e.getBill();
		
		bill.setProductReductionList(new ArrayList<ProductReduction>());
		bill.setStatus(Status.ACTIVE);
		
		this.billDao.saveOrUpdate(bill);
		
		return super.remove(id);
	}
	
}
