package com.icetea.manager.pagodiario.dao;

import javax.inject.Named;

import com.icetea.manager.pagodiario.model.Trader;

@Named
public class TraderDaoImpl extends BasicIdentificableDaoImpl<Trader> implements
		TraderDao {

	@Override
	protected Class<Trader> getEntityClass() {
		return Trader.class;
	}

}
