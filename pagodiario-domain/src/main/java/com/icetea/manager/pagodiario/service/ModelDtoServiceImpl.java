package com.icetea.manager.pagodiario.service;

import java.util.List;

import javax.transaction.Transactional;

import com.icetea.manager.pagodiario.api.dto.BasicDto;
import com.icetea.manager.pagodiario.dao.BasicDao;
import com.icetea.manager.pagodiario.model.Model;
import com.icetea.manager.pagodiario.transformer.DtoModelTransformer;

@Transactional
public abstract class ModelDtoServiceImpl<E extends Model, 
		D extends BasicDao<E>, O extends BasicDto, 
		T extends DtoModelTransformer<O, E>> 
		extends BasicServiceImpl 
	implements ModelDtoService<E, O> {
	
	private final D dao;
	private final T transformer;

	public ModelDtoServiceImpl(D dao, T transformer) {
		super();
		this.dao = dao;
		this.transformer = transformer;
	}

	public List<O> searchAll(){
		return this.transformer.transformAllTo(
				this.dao.findAll());
	}
	
	protected D getDao() {
		return dao;
	}
	
	protected T getTransformer() {
		return transformer;
	}	
	
}
