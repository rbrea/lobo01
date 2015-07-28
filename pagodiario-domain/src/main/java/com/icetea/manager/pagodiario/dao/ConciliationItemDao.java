package com.icetea.manager.pagodiario.dao;

import java.util.List;

import com.icetea.manager.pagodiario.model.ConciliationItem;

public interface ConciliationItemDao extends
		BasicIdentificableDao<ConciliationItem> {

	List<ConciliationItem> findByPayrollItemId(Long payrollItemId);

}
