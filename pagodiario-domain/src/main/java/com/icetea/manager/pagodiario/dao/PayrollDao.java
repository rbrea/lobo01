package com.icetea.manager.pagodiario.dao;

import java.util.Date;
import java.util.List;

import com.icetea.manager.pagodiario.model.Payroll;

public interface PayrollDao extends BasicIdentificableDao<Payroll> {

	Payroll find(Date from, Date to);

	Payroll findLast();

	List<Payroll> findLast(Integer maxResults);

}
