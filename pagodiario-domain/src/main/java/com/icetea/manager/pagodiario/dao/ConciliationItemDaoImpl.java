package com.icetea.manager.pagodiario.dao;

import java.util.List;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.ConciliationItem;

@Named
public class ConciliationItemDaoImpl extends BasicIdentificableDaoImpl<ConciliationItem> implements
		ConciliationItemDao {

	@Override
	protected Class<ConciliationItem> getEntityClass() {
		return ConciliationItem.class;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ConciliationItem> findByPayrollItemId(Long payrollItemId){
		Criteria criteria = super.createCriteria();
		criteria.createAlias("payrollItem", "payrollItem");
		criteria.add(Restrictions.eq("payrollItem.id", payrollItemId));
		
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ConciliationItem> findByBillId(Long billId){
		Criteria criteria = super.createCriteria();
		criteria.createAlias("bill", "bill");
		criteria.add(Restrictions.eq("bill.id", billId));
		
		return criteria.list();
	}
	
}
