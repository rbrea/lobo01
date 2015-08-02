package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.DevDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.DevDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Dev;
import com.icetea.manager.pagodiario.transformer.DevDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class DevServiceImpl extends BasicIdentifiableServiceImpl<Dev, DevDao, DevDto, DevDtoModelTransformer> implements
		DevService {
	
	private BillDao billDao;

	@Inject
	public DevServiceImpl(DevDao dao, DevDtoModelTransformer transformer,
			BillDao billDao) {
		super(dao, transformer);
		this.billDao = billDao;
	}

	@Override
	public DevDto insert(DevDto o) {
		Bill bill = this.billDao.findById(o.getBillId());
		
		ErrorTypedConditions.checkArgument(bill != null, ErrorType.BILL_NOT_FOUND);
		
		BigDecimal amount = NumberUtils.toBigDecimal(o.getAmount());

		ErrorTypedConditions.checkArgument(amount.compareTo(bill.getTotalAmount()) <= 0, 
				String.format("El monto ingresado %s no puede ser mayor al total facturado %s", 
						o.getAmount(), NumberUtils.toString(bill.getTotalAmount())), ErrorType.VALIDATION_ERRORS);
		
		Dev e = new Dev();
		e.setAmount(amount);
		e.setDate(DateUtils.parseDate(o.getDate(), "dd/MM/yyyy"));
		e.setObservations(o.getObservations());
		e.setBill(bill);
		
		this.getDao().saveOrUpdate(e);
		
		bill.getDevList().add(e);
		
		bill.setTotalAmount(NumberUtils.subtract(bill.getTotalAmount(), e.getAmount()));
		bill.setRemainingAmount(NumberUtils.subtract(bill.getRemainingAmount(), e.getAmount()));

		// FIXME: [roher] tal vez tenga q actualizar los dias de atraso ...
		
		this.billDao.saveOrUpdate(bill);
		
		return this.getTransformer().transform(e);
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
