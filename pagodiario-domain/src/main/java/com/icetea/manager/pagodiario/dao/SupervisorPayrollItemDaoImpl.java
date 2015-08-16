package com.icetea.manager.pagodiario.dao;

import javax.inject.Named;

import com.icetea.manager.pagodiario.model.SupervisorPayrollItem;

@Named
public class SupervisorPayrollItemDaoImpl extends
		BasicIdentificableDaoImpl<SupervisorPayrollItem> 
	implements SupervisorPayrollItemDao {

	@Override
	protected Class<SupervisorPayrollItem> getEntityClass() {
		return SupervisorPayrollItem.class;
	}

}
