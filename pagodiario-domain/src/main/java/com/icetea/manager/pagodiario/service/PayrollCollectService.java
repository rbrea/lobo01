package com.icetea.manager.pagodiario.service;

import com.icetea.manager.pagodiario.api.dto.PayrollCollectDto;
import com.icetea.manager.pagodiario.model.PayrollCollect;

public interface PayrollCollectService
		extends
		BasicIdentifiableService<PayrollCollect, PayrollCollectDto> {

	PayrollCollectDto processPayroll(String inputDate);

}
