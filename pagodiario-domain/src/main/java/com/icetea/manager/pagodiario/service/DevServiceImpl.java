package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.icetea.manager.pagodiario.api.dto.DevDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.DevDao;
import com.icetea.manager.pagodiario.dao.ProductDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Dev;
import com.icetea.manager.pagodiario.model.Product;
import com.icetea.manager.pagodiario.transformer.DevDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class DevServiceImpl extends BasicIdentifiableServiceImpl<Dev, DevDao, DevDto, DevDtoModelTransformer> implements
		DevService {
	
	private final BillDao billDao;
	private final ProductDao productDao;

	@Inject
	public DevServiceImpl(DevDao dao, DevDtoModelTransformer transformer,
			BillDao billDao, ProductDao productDao) {
		super(dao, transformer);
		this.billDao = billDao;
		this.productDao = productDao;
	}

	@Override
	public DevDto insert(DevDto o) {
		Bill bill = this.billDao.findById(o.getBillId());
		
		ErrorTypedConditions.checkArgument(bill != null, ErrorType.BILL_NOT_FOUND);
		
		BigDecimal amount = NumberUtils.toBigDecimal(o.getAmount());

		ErrorTypedConditions.checkArgument(amount.compareTo(bill.getTotalAmount()) <= 0, 
				String.format("El monto ingresado %s no puede ser mayor al total facturado %s", 
						o.getAmount(), NumberUtils.toString(bill.getTotalAmount())), ErrorType.VALIDATION_ERRORS);
		
		ErrorTypedConditions.checkArgument(o.getProductCount() != null, 
				"La cantidad de productos es requerido", ErrorType.VALIDATION_ERRORS);
		ErrorTypedConditions.checkArgument(o.getProductId() != null, 
				"El producto es requerido", ErrorType.VALIDATION_ERRORS);
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(o.getAmount()), 
				"El monto es requerido", ErrorType.VALIDATION_ERRORS);
		
		Product product = this.productDao.findById(o.getProductId());
		
		ErrorTypedConditions.checkArgument(product != null, 
				"El producto seleccionado no fue encontrado en el sistema", ErrorType.VALIDATION_ERRORS);
		
		// TODO: asociar el producto a la devolucion
		// TODO: recalcular el monto de la cuota diaria
		
		
		Dev e = new Dev();
		e.setAmount(amount);
		e.setDate(DateUtils.parseDate(o.getDate(), "dd/MM/yyyy"));
		e.setObservations(StringUtils.trim(o.getObservations()));
		e.setBill(bill);
		
		this.getDao().saveOrUpdate(e);
		
		bill.getDevList().add(e);
		
		bill.setTotalAmount(NumberUtils.subtract(bill.getTotalAmount(), e.getAmount()));
		bill.setRemainingAmount(NumberUtils.subtract(bill.getRemainingAmount(), e.getAmount()));
		
//		BigDecimal installmentAmount = null;
//		
//		bill.setTotalDailyInstallment(installmentAmount);

		// FIXME: [roher] tal vez tenga q actualizar los dias de atraso ...
		
		this.billDao.saveOrUpdate(bill);
		
		return this.getTransformer().transform(e);
	}
	
	protected BigDecimal doNewInstallmentAmountCalculation(BigDecimal totalAmount, BigDecimal totalRemaining){
		return null;
	}

	@Override
	public DevDto update(DevDto o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DevDto> search(Long billId){
		
		ErrorTypedConditions.checkArgument(billId != null, ErrorType.BILL_REQUIRED);
		
		return this.getTransformer().transformAllTo(this.getDao().findByBillId(billId));
	}
	
}
