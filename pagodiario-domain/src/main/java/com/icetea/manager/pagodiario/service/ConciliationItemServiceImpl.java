package com.icetea.manager.pagodiario.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.NotImplementedException;

import com.icetea.manager.pagodiario.api.dto.ConciliationItemDto;
import com.icetea.manager.pagodiario.dao.ConciliationItemDao;
import com.icetea.manager.pagodiario.model.ConciliationItem;
import com.icetea.manager.pagodiario.model.Payroll;
import com.icetea.manager.pagodiario.model.PayrollItem;
import com.icetea.manager.pagodiario.transformer.ConciliationItemDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.ListUtils;

@Named
public class ConciliationItemServiceImpl extends
		BasicIdentifiableServiceImpl<ConciliationItem, ConciliationItemDao, ConciliationItemDto, ConciliationItemDtoModelTransformer> 
			implements ConciliationItemService {

	@Inject
	public ConciliationItemServiceImpl(ConciliationItemDao dao,
			ConciliationItemDtoModelTransformer transformer) {
		super(dao, transformer);
	}

	@Override
	public ConciliationItemDto insert(ConciliationItemDto o) {
		throw new NotImplementedException();
	}

	@Override
	public ConciliationItemDto update(ConciliationItemDto o) {
		throw new NotImplementedException();
	}
	
	@Override
	public List<ConciliationItemDto> searchByPayrollItemId(Long id){
		
		return this.getTransformer().transformAllTo(this.getDao().findByPayrollItemId(id));
	}

	@Override
	public List<ConciliationItemDto> searchByBillId(Long billId){
		
		return this.getTransformer().transformAllTo(this.getDao().findByBillId(billId));
	}
	
	@Override
	public Date searchPayrollDateFromBillId(Long billId){
		List<ConciliationItem> list = this.getDao().findByBillId(billId);
		if(ListUtils.isNotEmpty(list)){
			PayrollItem payrollItem = list.get(0).getPayrollItem();
			if(payrollItem != null){
				Payroll payroll = payrollItem.getPayroll();
				if(payroll != null){
					return payroll.getCreatedDate();
				}
			}
		}
		
		return null;
	}
	
}
