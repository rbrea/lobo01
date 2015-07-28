package com.icetea.manager.pagodiario.service;

import java.util.List;

import com.icetea.manager.pagodiario.api.dto.ConciliationItemDto;
import com.icetea.manager.pagodiario.model.ConciliationItem;

public interface ConciliationItemService extends
		BasicIdentifiableService<ConciliationItem, ConciliationItemDto> {

	List<ConciliationItemDto> searchByPayrollItemId(Long id);

}
