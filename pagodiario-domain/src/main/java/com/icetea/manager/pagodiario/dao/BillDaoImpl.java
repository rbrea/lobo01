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

	@Override
	public Bill findByCreditNumber(Long creditNumber){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.eq("creditNumber", creditNumber));
		
		return (Bill) criteria.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bill> findByCollectorId(Long collectorId){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.eq("collectorId", collectorId));
		criteria.add(Restrictions.eq("status", Bill.Status.ACTIVE));
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bill> find(Integer collectorId, Date dateFrom, Date dateTo){
		Criteria criteria = super.createCriteria();
		if(dateFrom != null && dateTo != null){
			criteria.add(Restrictions.between("startDate", dateFrom, dateTo));
		}
		criteria.add(Restrictions.eq("collectorId", collectorId));
		criteria.add(Restrictions.eq("status", Bill.Status.ACTIVE));
		
		return criteria.list();
	}
	
}
