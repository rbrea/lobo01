package com.icetea.manager.pagodiario.service;

import com.icetea.manager.pagodiario.api.dto.UserDto;
import com.icetea.manager.pagodiario.api.dto.UserRegistrationDto;

public interface AuthenticationService extends BasicService {

	UserDto validateAccessToken(String accessToken);

	UserDto login(String username, String password);

	UserDto register(UserRegistrationDto userRegistration);
	
}
