package com.icetea.manager.pagodiario.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.Discount;

@Named
public class DiscountDaoImpl extends BasicIdentificableDaoImpl<Discount>
		implements DiscountDao {

	@Override
	protected Class<Discount> getEntityClass() {
		return Discount.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Discount> findByBillId(Long billId){
		
		Criteria criteria = super.createCriteria();
		criteria.createAlias("bill", "bill");
		criteria.add(Restrictions.eq("bill.id", billId));
		
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Discount> find(Date dateFrom, Date dateTo){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.between("date", dateFrom, dateTo));
		
		return criteria.list();
	}
	
}
