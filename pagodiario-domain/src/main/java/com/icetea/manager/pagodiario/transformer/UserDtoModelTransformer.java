package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.UserDto;
import com.icetea.manager.pagodiario.model.User;

@Named
public class UserDtoModelTransformer extends AbstractDtoModelTransformer<UserDto, User> {

	@Override
	protected UserDto doTransform(User e, int depth) {
		UserDto d = new UserDto();
		d.setToken(e.getToken());
		d.setUsername(e.getUsername());
		d.setAdmin(e.isAdmin());
		if(e.isAdmin()){
			d.getRoles().add("ROLE_ADMIN");
		}
		
		return d;
	}

}
