package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.TraderDto;
import com.icetea.manager.pagodiario.model.Trader;

@Named
public class TraderDtoModelTransformer extends AbstractDtoModelTransformer<TraderDto, Trader> {

	@Override
	protected TraderDto doTransform(Trader e, int depth) {
		TraderDto d = new TraderDto();
		d.setAddress(e.getAddress());
		d.setCity(e.getCity());
		d.setDocumentNumber(e.getDocumentNumber());
		d.setDocumentType(e.getDocumentType());
		d.setEmail(e.getEmail());
		d.setId(e.getId());
		d.setName(e.getName());
		d.setNearStreets(e.getNearStreets());
		d.setPhone(e.getPhone());
		d.setSupervisor(e.isSupervisor());
		d.setParentId((e.getParent() != null) ? e.getParent().getId() : null);
		d.setParentDescription((e.getParent() != null) ? e.getParent().getName() : null);
		if(e.getTraders() != null && !e.getTraders().isEmpty()){
			for(Trader t : e.getTraders()){
				d.getListOfTraderIds().add(t.getId());
			}
		}
		d.setStatus(e.getStatus().name());
		
		return d;
	}

}
