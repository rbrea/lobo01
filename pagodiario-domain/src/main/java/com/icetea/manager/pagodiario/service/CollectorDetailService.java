package com.icetea.manager.pagodiario.service;

import java.util.List;

import com.icetea.manager.pagodiario.api.dto.CollectorDetailDto;

public interface CollectorDetailService extends BasicService {

	List<CollectorDetailDto> search(String fromArg, String toArg);

}
