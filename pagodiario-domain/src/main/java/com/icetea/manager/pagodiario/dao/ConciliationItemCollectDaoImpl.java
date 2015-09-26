package com.icetea.manager.pagodiario.dao;

import java.util.List;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.ConciliationItemCollect;

@Named
public class ConciliationItemCollectDaoImpl extends
		BasicIdentificableDaoImpl<ConciliationItemCollect> implements
		ConciliationItemCollectDao {

	@Override
	protected Class<ConciliationItemCollect> getEntityClass() {
		return ConciliationItemCollect.class;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ConciliationItemCollect> findByBillId(Long billId){
		Criteria criteria = super.createCriteria();
		criteria.createAlias("bill", "bill");
		criteria.add(Restrictions.eq("bill.id", billId));
		
		return criteria.list();
	}
	
}
