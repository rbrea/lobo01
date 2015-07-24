package com.icetea.manager.pagodiario.service;

import java.util.List;

import com.icetea.manager.pagodiario.api.dto.BonusDto;
import com.icetea.manager.pagodiario.model.Bonus;

public interface BonusService extends BasicIdentifiableService<Bonus, BonusDto> {

	List<BonusDto> search(Long billId);

}
