package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.ClientDto;
import com.icetea.manager.pagodiario.model.Client;
import com.icetea.manager.pagodiario.utils.DateUtils;

@Named
public class ClientDtoModelTransformer extends AbstractDtoModelTransformer<ClientDto, Client> {

	@Override
	protected ClientDto doTransform(Client e, int depth) {
		ClientDto dto = new ClientDto();
		dto.setId(e.getId());
		dto.setAddress(e.getAddress());
		dto.setCity(e.getCity());
		dto.setCompanyAddress(e.getCompanyAddress());
		dto.setCompanyCity(e.getCompanyCity());
		dto.setCompanyPhone(e.getCompanyPhone());
		dto.setCompanyType(e.getCompanyType());
		dto.setDocumentNumber(e.getDocumentNumber());
		dto.setDocumentType(e.getDocumentType());
		dto.setName(e.getName());
		dto.setNearStreets(e.getNearStreets());
		dto.setPhone(e.getPhone());
		dto.setEmail(e.getEmail());
		dto.setReductionMark(DateUtils.toDate(e.getReductionMark()));
		dto.setCancelationMark(DateUtils.toDate(e.getCancelationMark()));
		
		return dto;
	}

}
