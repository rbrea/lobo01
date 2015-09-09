package com.icetea.manager.pagodiario.dao;

import javax.inject.Named;

import com.icetea.manager.pagodiario.model.Collector;

@Named
public class CollectorDaoImpl extends BasicIdentificableDaoImpl<Collector>
		implements CollectorDao {

	@Override
	protected Class<Collector> getEntityClass() {
		return Collector.class;
	}

}
