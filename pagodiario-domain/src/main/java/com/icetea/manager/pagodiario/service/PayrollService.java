package com.icetea.manager.pagodiario.service;

import java.util.Date;

import com.icetea.manager.pagodiario.api.dto.PayrollDto;
import com.icetea.manager.pagodiario.model.Payroll;

public interface PayrollService extends BasicIdentifiableService<Payroll, PayrollDto> {

	PayrollDto processPeriod(Date dateFrom, Date dateTo);

}
