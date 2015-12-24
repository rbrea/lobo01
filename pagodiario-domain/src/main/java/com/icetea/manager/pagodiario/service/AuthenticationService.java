package com.icetea.manager.pagodiario.service;

import java.util.List;

import com.icetea.manager.pagodiario.api.dto.UserDto;
import com.icetea.manager.pagodiario.api.dto.UserRegistrationDto;

public interface AuthenticationService extends BasicService {

	UserDto validateAccessToken(String accessToken);

	UserDto login(String username, String password);

	UserDto register(UserRegistrationDto userRegistration);

	List<UserRegistrationDto> getUserRegistration(Long id, String username);
	
	UserDto editUser(UserRegistrationDto userRegistration);
	
	boolean removeUser(Long id);

	UserDto search(String username);

	UserRegistrationDto resetPassword(Long id);

	UserRegistrationDto resetPassword(String username);

	UserRegistrationDto checkVerificationCode(String verificationCode, String newPassword);
	
}
