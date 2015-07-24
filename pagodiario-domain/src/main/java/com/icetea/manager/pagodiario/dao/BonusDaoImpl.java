package com.icetea.manager.pagodiario.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.Bonus;

@Named
public class BonusDaoImpl extends BasicIdentificableDaoImpl<Bonus> implements
		BonusDao {

	@Override
	protected Class<Bonus> getEntityClass() {
		return Bonus.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bonus> findByBillId(Long billId){
		
		Criteria criteria = super.createCriteria();
		criteria.createAlias("bill", "bill");
		criteria.add(Restrictions.eq("bill.id", billId));
		
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bonus> find(Date dateFrom, Date dateTo){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.between("date", dateFrom, dateTo));
		
		return criteria.list();
	}
	
}
