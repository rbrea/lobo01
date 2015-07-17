package com.icetea.manager.pagodiario.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.PaymentDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.PaymentDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Payment;
import com.icetea.manager.pagodiario.transformer.PaymentDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class PaymentServiceImpl 
	extends BasicIdentifiableServiceImpl<Payment, PaymentDao, PaymentDto, PaymentDtoModelTransformer> 
		implements PaymentService {

	private final BillDao billDao;
	
	@Inject
	public PaymentServiceImpl(PaymentDao dao,
			PaymentDtoModelTransformer transformer,
			BillDao billDao) {
		super(dao, transformer);
		this.billDao = billDao;
	}
	
	@Override
	public PaymentDto insert(PaymentDto d) {
		Payment e = new Payment();
		e.setAmount(NumberUtils.toBigDecimal(d.getAmount()));
		
		ErrorTypedConditions.checkArgument(d.getId() != null, ErrorType.BILL_REQUIRED);
		
		Bill bill = this.billDao.findById(d.getId());
		
		ErrorTypedConditions.checkArgument(bill != null, ErrorType.BILL_NOT_FOUND);
		
		e.setBill(bill);
		e.setCollectorId(d.getCollectorId());
		e.setDate(DateUtils.parseDate(d.getDate()));
		this.getDao().saveOrUpdate(e);
		
		bill.addPayment(e);
		this.billDao.saveOrUpdate(bill);
		
		return d;
	}
	
	@Override
	public PaymentDto update(PaymentDto d) {
		Payment e = new Payment();
		
		return d;
	}

	@Override
	public List<PaymentDto> search(Long billId){
		
		ErrorTypedConditions.checkArgument(billId != null, ErrorType.BILL_REQUIRED);
		
		return this.getTransformer().transformAllTo(this.getDao().findByBillId(billId));
	}
	
}
