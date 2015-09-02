package com.icetea.manager.pagodiario.service;

import java.util.Date;
import java.util.List;

import com.icetea.manager.pagodiario.api.dto.PayrollDetailDto;
import com.icetea.manager.pagodiario.api.dto.PayrollDto;
import com.icetea.manager.pagodiario.model.Payroll;

public interface PayrollService extends BasicIdentifiableService<Payroll, PayrollDto> {

	PayrollDto processPeriod(Date dateFrom, Date dateTo);

	List<PayrollDetailDto> searchDetail(Long payrollId);

	List<PayrollDetailDto> searchSupervisorDetail(Long payrollId);

	PayrollDto commitPayroll(Long id);

}
