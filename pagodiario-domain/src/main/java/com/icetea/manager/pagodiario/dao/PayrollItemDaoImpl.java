package com.icetea.manager.pagodiario.dao;

import javax.inject.Named;

import com.icetea.manager.pagodiario.model.PayrollItem;

@Named
public class PayrollItemDaoImpl extends
		BasicIdentificableDaoImpl<PayrollItem> implements PayrollItemDao {

	@Override
	protected Class<PayrollItem> getEntityClass() {
		return PayrollItem.class;
	}

}
