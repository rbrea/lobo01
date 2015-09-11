package com.icetea.manager.pagodiario.dao;

import javax.inject.Named;

import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.Collector;

@Named
public class CollectorDaoImpl extends BasicIdentificableDaoImpl<Collector>
		implements CollectorDao {

	@Override
	protected Class<Collector> getEntityClass() {
		return Collector.class;
	}

	@Override
	public Collector findByZone(Long zone){
		return (Collector) super.createCriteria().add(Restrictions.eq("zone", zone)).uniqueResult();
	}
	
}
