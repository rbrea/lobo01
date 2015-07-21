package com.icetea.manager.pagodiario.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bill.Status;

@Named
public class BillDaoImpl extends BasicIdentificableDaoImpl<Bill> 
	implements BillDao {

	@Override
	protected Class<Bill> getEntityClass() {
		return Bill.class;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bill> find(Status status){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.eq("status", status));
		
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Bill> find(Date dateFrom, Date dateTo){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.between("startDate", dateFrom, dateTo));
		criteria.add(
				Restrictions.or(
						Restrictions.eq("status", Bill.Status.ACTIVE),
						Restrictions.eq("status", Bill.Status.FINALIZED)
				)
		);
		
		return criteria.list();
	}
	
}
