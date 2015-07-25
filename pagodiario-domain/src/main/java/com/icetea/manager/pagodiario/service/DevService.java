package com.icetea.manager.pagodiario.service;

import java.util.List;

import com.icetea.manager.pagodiario.api.dto.DevDto;
import com.icetea.manager.pagodiario.model.Dev;

public interface DevService extends BasicIdentifiableService<Dev, DevDto> {

	List<DevDto> search(Long billId);

}
