package com.icetea.manager.pagodiario.service;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;

import com.google.common.base.Preconditions;
import com.icetea.manager.pagodiario.api.dto.UserDto;
import com.icetea.manager.pagodiario.api.dto.UserRegistrationDto;
import com.icetea.manager.pagodiario.dao.UserDao;
import com.icetea.manager.pagodiario.exception.IncorrectUserLoginException;
import com.icetea.manager.pagodiario.exception.PasswordIncorrectException;
import com.icetea.manager.pagodiario.model.User;
import com.icetea.manager.pagodiario.transformer.UserDtoModelTransformer;

@Named
public class AuthenticationServiceImpl extends BasicServiceImpl implements
		AuthenticationService {

	private final UserDao userDao;
	
	private final UserDtoModelTransformer userDtoModelTransformer;
	
	@Inject
	public AuthenticationServiceImpl(UserDao userDao,
			UserDtoModelTransformer userDtoModelTransformer) {
		super();
		this.userDao = userDao;
		this.userDtoModelTransformer = userDtoModelTransformer;
	}

	@Override
	public UserDto register(UserRegistrationDto userRegistration){
		
		Preconditions.checkArgument(
				StringUtils.isNotBlank(userRegistration.getPassword()), 
				"El password es requerido");
		
		User found = this.userDao.find(userRegistration.getUsername());
		
		if(found != null){
			throw new RuntimeException("El nombre de usuario ya existe. Por favor elija otro.");
		}
		
		PasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(userRegistration.getPassword());
		
		User user = this.createUser(userRegistration, encryptedPassword);
		
		this.userDao.saveOrUpdate(user);
		
		return this.userDtoModelTransformer.transform(user);
	}

	protected User createUser(UserRegistrationDto userRegistration,
			String encryptedPassword) {
		User user = new User();
		user.setDocumentNumber(userRegistration.getDocumentNumber());
		user.setDocumentType(userRegistration.getDocumentType());
		user.setName(userRegistration.getName());
		user.setEmail(userRegistration.getEmail());
		user.setUsername(userRegistration.getUsername());
		user.setPassword(encryptedPassword);
		user.setToken(UUID.randomUUID().toString());
		user.setAdmin(userRegistration.isAdmin());
		
		return user;
	}
	
	@Override
	public UserDto login(String username, String password){
		
		User user = this.userDao.find(username);
		if(user == null){
			throw new IncorrectUserLoginException("No existe usuario con el username indicado");
		}
		PasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		if(!passwordEncryptor.checkPassword(password, user.getPassword())){
			throw new PasswordIncorrectException("password invalido. Por favor intente nuevamente.");
		}
		
		return this.userDtoModelTransformer.transform(user);
	}
	
	@Override
	public UserDto validateAccessToken(String accessToken) {
		return this.userDtoModelTransformer.transform(this.userDao.findByAccessToken(accessToken));
	}
	
}
