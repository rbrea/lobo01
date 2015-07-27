package com.icetea.manager.pagodiario.dao;

import java.util.Date;

import com.icetea.manager.pagodiario.model.Payroll;

public interface PayrollDao extends BasicIdentificableDao<Payroll> {

	Payroll find(Date from, Date to);

}
