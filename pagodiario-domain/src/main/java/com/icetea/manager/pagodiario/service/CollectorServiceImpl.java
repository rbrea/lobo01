package com.icetea.manager.pagodiario.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.CollectorDto;
import com.icetea.manager.pagodiario.dao.CollectorDao;
import com.icetea.manager.pagodiario.model.Collector;
import com.icetea.manager.pagodiario.transformer.CollectorDtoModelTransformer;

@Named
public class CollectorServiceImpl 
	extends BasicIdentifiableServiceImpl<Collector, CollectorDao, CollectorDto, CollectorDtoModelTransformer> implements
		CollectorService {

	@Inject
	public CollectorServiceImpl(CollectorDao dao,
			CollectorDtoModelTransformer transformer) {
		super(dao, transformer);
	}

	@Override
	public CollectorDto insert(CollectorDto o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CollectorDto update(CollectorDto o) {
		// TODO Auto-generated method stub
		return null;
	}

}
