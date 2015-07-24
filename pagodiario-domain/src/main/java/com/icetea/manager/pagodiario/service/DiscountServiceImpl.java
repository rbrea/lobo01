package com.icetea.manager.pagodiario.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

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
		
		Discount e = new Discount();
		e.setAmount(NumberUtils.toBigDecimal(o.getAmount()));
		e.setDate(DateUtils.parseDate(o.getDate(), "dd/MM/yyyy"));
		e.setObservations(o.getObservations());
		e.setBill(bill);
		
		this.getDao().saveOrUpdate(e);
		
		bill.getDiscounts().add(e);
		
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
