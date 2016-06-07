package com.icetea.manager.pagodiario.service;

import com.icetea.manager.pagodiario.api.dto.PayrollCollectDto;
import com.icetea.manager.pagodiario.model.PayrollCollect;

public interface PayrollCollectService
		extends
		BasicIdentifiableService<PayrollCollect, PayrollCollectDto> {

	@Deprecated
	PayrollCollectDto processPayroll(String inputDate);

	PayrollCollectDto commitPayroll(Long id);

	PayrollCollectDto processPayrollAlternative(String inputDate);

	boolean existsByPaymentId(Long paymentId);

}
