package com.icetea.manager.pagodiario.dao;

import java.util.List;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.Trader;

@Named
public class TraderDaoImpl extends BasicIdentificableDaoImpl<Trader> implements
		TraderDao {

	@Override
	protected Class<Trader> getEntityClass() {
		return Trader.class;
	}

	@Override
	public Trader find(Long documentNumber){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.eq("documentNumber", documentNumber));
		
		return (Trader) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Trader> findSupervisors(){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.eq("supervisor", true));
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trader> find(Trader.Status status){
		Criteria criteria = super.createCriteria();
		if(status != null){
			criteria.add(Restrictions.eq("status", status));
		}
		
		return criteria.list();
	}
	
}
