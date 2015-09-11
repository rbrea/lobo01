package com.icetea.manager.pagodiario.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.icetea.manager.pagodiario.api.dto.CollectorDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.CollectorDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
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
		ErrorTypedConditions.checkArgument(o.getZone() != null, "zona es requerida",
				ErrorType.VALIDATION_ERRORS);
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(o.getDescription()), "descripcion de cobrador es requerido",
				ErrorType.VALIDATION_ERRORS);
		
		Collector e = this.getDao().findByZone(o.getZone());
		
		ErrorTypedConditions.checkArgument(e == null, "Cobrador ya existe con nro de zona: " + o.getZone(),
				ErrorType.VALIDATION_ERRORS);
		
		e = new Collector();
		e.setZone(o.getZone());
		e.setDescription(o.getDescription());
		
		this.getDao().saveOrUpdate(e);
		
		return this.getTransformer().transform(e);
	}

	@Override
	public CollectorDto update(CollectorDto o) {
		ErrorTypedConditions.checkArgument(o.getId() != null, "Id de cobrador requerido", ErrorType.VALIDATION_ERRORS);
		
		Collector e = this.getDao().findById(o.getId());
		
		ErrorTypedConditions.checkArgument(e != null, "cobrador inexistente", ErrorType.VALIDATION_ERRORS);
		
		e.setZone(o.getZone());
		e.setDescription(o.getDescription());
		
		this.getDao().saveOrUpdate(e);
		
		return this.getTransformer().transform(e);
	}

	@Override
	public boolean remove(Long id) {
		Collector e = this.getDao().findById(id);
		
		ErrorTypedConditions.checkArgument(e != null, String.format("El cobrador con id %s no se encuentra en el sistema", id), 
				ErrorType.VALIDATION_ERRORS);
		
		return this.getDao().delete(e);
	}
	
}
