package com.icetea.manager.pagodiario.dao;

import com.icetea.manager.pagodiario.model.Identifiable;

public interface BasicIdentificableDao<E extends Identifiable> extends BasicDao<E>{
	
	E findById(Long id);
	
	boolean deleteById(Long id);
	
}
