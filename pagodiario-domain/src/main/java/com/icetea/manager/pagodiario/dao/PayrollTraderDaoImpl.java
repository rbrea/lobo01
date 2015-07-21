package com.icetea.manager.pagodiario.dao;

import javax.inject.Named;

import com.icetea.manager.pagodiario.model.PayrollTrader;

@Named
public class PayrollTraderDaoImpl extends
		BasicIdentificableDaoImpl<PayrollTrader> implements PayrollTraderDao {

	@Override
	protected Class<PayrollTrader> getEntityClass() {
		return PayrollTrader.class;
	}

}
