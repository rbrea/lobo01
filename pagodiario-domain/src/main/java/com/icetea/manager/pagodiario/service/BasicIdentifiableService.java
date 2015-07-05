package com.icetea.manager.pagodiario.service;

import com.icetea.manager.pagodiario.api.dto.BasicDto;
import com.icetea.manager.pagodiario.model.Model;

public interface BasicIdentifiableService<E extends Model, O extends BasicDto> 
	extends ModelDtoService<E, O> {

	O searchById(Long id);
	
	boolean remove(Long id);
	
}
