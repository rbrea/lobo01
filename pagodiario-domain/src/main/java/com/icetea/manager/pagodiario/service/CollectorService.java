package com.icetea.manager.pagodiario.service;

import com.icetea.manager.pagodiario.api.dto.CollectorDto;
import com.icetea.manager.pagodiario.model.Collector;

public interface CollectorService extends
		BasicIdentifiableService<Collector, CollectorDto> {

	CollectorDto searchByZone(Long zone);

}
