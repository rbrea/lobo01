package com.icetea.manager.pagodiario.service;

import com.icetea.manager.pagodiario.api.dto.SupervisorPayrollItemDto;
import com.icetea.manager.pagodiario.model.SupervisorPayrollItem;

public interface SupervisorPayrollItemService
		extends
		BasicIdentifiableService<SupervisorPayrollItem, SupervisorPayrollItemDto> {

	SupervisorPayrollItemDto searchDetail(Long id);

}
