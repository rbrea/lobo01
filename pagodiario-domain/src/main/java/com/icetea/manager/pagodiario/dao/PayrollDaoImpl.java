package com.icetea.manager.pagodiario.dao;

import javax.inject.Named;

import com.icetea.manager.pagodiario.model.Payroll;

@Named
public class PayrollDaoImpl extends BasicIdentificableDaoImpl<Payroll>
		implements PayrollDao {

	@Override
	protected Class<Payroll> getEntityClass() {
		return Payroll.class;
	}

}
