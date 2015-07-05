package com.icetea.manager.pagodiario.service;

import com.icetea.manager.pagodiario.api.dto.BasicDto;
import com.icetea.manager.pagodiario.dao.BasicIdentificableDao;
import com.icetea.manager.pagodiario.model.Identifiable;
import com.icetea.manager.pagodiario.transformer.DtoModelTransformer;

public abstract class BasicIdentifiableServiceImpl<E extends Identifiable, 
	D extends BasicIdentificableDao<E>, O extends BasicDto, 
	T extends DtoModelTransformer<O, E>> extends ModelDtoServiceImpl<E, D, O, T> 
		implements BasicIdentifiableService<E, O> {

	public BasicIdentifiableServiceImpl(D dao, T transformer) {
		super(dao, transformer);
	}

	@Override
	public boolean remove(Long id) {
		E e = this.getDao().findById(id);
		if(e == null){
			throw new RuntimeException(String.format("La entidad con id %s no existe en la bd", id));
		}
		return this.getDao().delete(e);
	}
	
	@Override
	public O searchById(Long id) {
		return this.getTransformer().transform(this.getDao().findById(id));
	}
	
}
