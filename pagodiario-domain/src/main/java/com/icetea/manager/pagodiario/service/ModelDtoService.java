package com.icetea.manager.pagodiario.service;

import java.util.List;

import com.icetea.manager.pagodiario.api.dto.BasicDto;
import com.icetea.manager.pagodiario.model.Model;

public interface ModelDtoService<E extends Model, O extends BasicDto> 
	extends BasicService {

	List<O> searchAll();
	
}
