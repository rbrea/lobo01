package com.icetea.manager.pagodiario.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.BonusDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.BonusDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bonus;
import com.icetea.manager.pagodiario.transformer.BonusDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class BonusServiceImpl 
		extends BasicIdentifiableServiceImpl<Bonus, BonusDao, BonusDto, BonusDtoModelTransformer> 
	implements BonusService {

	private BillDao billDao;
	
	@Inject
	public BonusServiceImpl(BonusDao dao, BonusDtoModelTransformer transformer,
			BillDao billDao) {
		super(dao, transformer);
		this.billDao = billDao;
	}

	@Override
	public BonusDto insert(BonusDto o) {
		
		Bill bill = this.billDao.findById(o.getBillId());
		
		ErrorTypedConditions.checkArgument(bill != null, ErrorType.BILL_NOT_FOUND);
		
		Bonus e = new Bonus();
		e.setAmount(NumberUtils.toBigDecimal(o.getAmount()));
		e.setDate(DateUtils.parseDate(o.getDate(), "dd/MM/yyyy"));
		e.setObservations(o.getObservations());
		e.setBill(bill);
		
		this.getDao().saveOrUpdate(e);
		
		bill.getBonusList().add(e);
		
		this.billDao.saveOrUpdate(bill);
		
		return this.getTransformer().transform(e);
	}

	@Override
	public BonusDto update(BonusDto o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BonusDto> search(Long billId){
		
		ErrorTypedConditions.checkArgument(billId != null, ErrorType.BILL_REQUIRED);
		
		return this.getTransformer().transformAllTo(this.getDao().findByBillId(billId));
	}
	
}
