package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.UserRegistrationDto;
import com.icetea.manager.pagodiario.model.User;

@Named
public class UserRegistrationDtoModelTransformer extends
		AbstractDtoModelTransformer<UserRegistrationDto, User> {

	@Override
	protected UserRegistrationDto doTransform(User e, int depth) {
		UserRegistrationDto dto = new UserRegistrationDto();
		dto.setAdmin(e.isAdmin());
		dto.setDocumentNumber(e.getDocumentNumber());
		dto.setDocumentType(e.getDocumentType());
		dto.setEmail(e.getEmail());
		dto.setId(e.getId());
		dto.setName(e.getName());
		dto.setUsername(e.getUsername());
		
		return dto;
	}

}
