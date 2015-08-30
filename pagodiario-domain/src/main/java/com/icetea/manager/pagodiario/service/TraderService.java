package com.icetea.manager.pagodiario.service;

import java.util.List;

import com.icetea.manager.pagodiario.api.dto.TraderDto;
import com.icetea.manager.pagodiario.model.Trader;

public interface TraderService extends
		BasicIdentifiableService<Trader, TraderDto> {

	TraderDto insert(TraderDto input);

	TraderDto update(TraderDto d);

	boolean deleteChild(Long parentId, Long childId);

	boolean addChild(Long parentId, Long childId);

	List<TraderDto> searchSupervisors();
	
}
