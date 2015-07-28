package com.icetea.manager.pagodiario.dao;

import java.util.List;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.ConciliationItem;

@Named
public class ConciliationDaoImpl extends BasicIdentificableDaoImpl<ConciliationItem> implements
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
	
}
