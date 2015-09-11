package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.CollectorDto;
import com.icetea.manager.pagodiario.model.Collector;

@Named
public class CollectorDtoModelTransformer extends AbstractDtoModelTransformer<CollectorDto, Collector>{

	@Override
	protected CollectorDto doTransform(Collector e, int depth) {
		CollectorDto d = new CollectorDto();
		d.setId(e.getId());
		d.setZone(e.getZone());
		d.setDescription(e.getDescription());
		
		return d;
	}

}
