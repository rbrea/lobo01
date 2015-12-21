package com.icetea.manager.pagodiario.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.UserDto;
import com.icetea.manager.pagodiario.api.dto.UserRegistrationDto;
import com.icetea.manager.pagodiario.dao.UserDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.exception.IncorrectUserLoginException;
import com.icetea.manager.pagodiario.exception.PasswordIncorrectException;
import com.icetea.manager.pagodiario.model.User;
import com.icetea.manager.pagodiario.transformer.UserDtoModelTransformer;
import com.icetea.manager.pagodiario.transformer.UserRegistrationDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.StringUtils;
import com.icetea.manager.pagodiario.utils.mail.MailHelper;

@Named
public class AuthenticationServiceImpl extends BasicServiceImpl implements
		AuthenticationService {

	private final UserDao userDao;
	
	private final UserDtoModelTransformer userDtoModelTransformer;
	private final UserRegistrationDtoModelTransformer userRegistrationDtoModelTransformer;

	private final MailHelper mailHelper;
	
	@Inject
	public AuthenticationServiceImpl(UserDao userDao,
			UserDtoModelTransformer userDtoModelTransformer,
			UserRegistrationDtoModelTransformer userRegistrationDtoModelTransformer,
			MailHelper mailHelper) {
		super();
		this.userDao = userDao;
		this.userDtoModelTransformer = userDtoModelTransformer;
		this.userRegistrationDtoModelTransformer = userRegistrationDtoModelTransformer;
		this.mailHelper = mailHelper;
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

	@Override
	public List<UserRegistrationDto> getUserRegistration(Long id){
		if(id == null){
			return this.userRegistrationDtoModelTransformer.transformAllTo(this.userDao.findAll());
		}
		return Lists.newArrayList(this.userRegistrationDtoModelTransformer.transform(this.userDao.findById(id)));
	}

	@Override
	public UserDto editUser(UserRegistrationDto userRegistration) {
		User user = this.userDao.findById(userRegistration.getId());
		
		if(user == null){
			throw new RuntimeException("error usuario no existe");
		}
		
		user.setName(userRegistration.getName());
		user.setDocumentNumber(userRegistration.getDocumentNumber());
		user.setEmail(userRegistration.getEmail());
		user.setAdmin(userRegistration.isAdmin());
		
		return this.userDtoModelTransformer.transform(user);
	}
	
	@Override
	public boolean removeUser(Long id) {
		User user = this.userDao.findById(id);
		
		if(user == null){
			throw new RuntimeException("error usuario no existe");
		}
	
		return this.userDao.delete(user);
	}
	
	@Override
	public UserDto search(String username){
		
		return this.userDtoModelTransformer.transform(this.userDao.find(username));
	}
	
	@Override
	public UserRegistrationDto resetPassword(String username){
		
		final User user = this.userDao.find(username);
		
		return this.resetPassword(user);
	}
	
	@Override
	public UserRegistrationDto resetPassword(Long id){
		
		final User user = this.userDao.findById(id);
		
		return this.resetPassword(user);
	}

	public UserRegistrationDto resetPassword(User user){
		
		ErrorTypedConditions.checkArgument(user != null, "No se ha encontrado el usuario solicitado para resetear el password");
		
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(user.getEmail()), "email del usuario requerido");
		
		final String verificationCode = UUID.randomUUID().toString();
		final Date expirationDate = DateUtils.addDays(DateUtils.now(), 1);
		
		user.setResetPasswordVerificationCode(verificationCode);
		user.setResetPasswordExpiration(expirationDate);
		
		StringBuffer bf = new StringBuffer();
		
		String name = user.getName();
		if(StringUtils.isBlank(name)){
			name = "No Especificado";
		}
		
		bf.append("Estimado/a ").append(StringUtils.upperCase(name)).append("\n");
		bf.append("Usted ha solicitado el reseteo de su contraseña para acceder al sistema de Gestión de Pago Diario.\n");
		bf.append("A continuación se le otorga un código de Verificaci&oacute;n; "
				+ "sea tan amable de ingresar el mismo en el lugar indicado.\n");
		bf.append("\n\nCÓDIGO DE VERIFICACIÓN: ").append(verificationCode).append("\n\n");
		bf.append("\nTenga en cuenta que el mismo expirará luego de las 24hs de generado. Por lo que tendrá que volver a generarlo.\n");
		bf.append("\nMuchas gracias!");
		
		final String text = bf.toString();
		
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(user.getEmail()), "El email del usuario no esta ingresado.");
		
		this.mailHelper.send(user.getEmail(), "Reseteo de contraseña", text);
		
		this.userDao.saveOrUpdate(user);
		
		return this.userRegistrationDtoModelTransformer.transform(user);
	}
	
	@Override
	public UserRegistrationDto checkVerificationCode(String verificationCode, String newPassword){
		
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(verificationCode), 
				"Código de verificación requerido");
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(newPassword),
				"Nueva contraseña requerida");
		
		User user = this.userDao.findByVerificationCode(verificationCode);
		
		ErrorTypedConditions.checkArgument(user != null, 
				"Usuario no encontrado con código de verificación: " + verificationCode);
		
		ErrorTypedConditions.checkArgument(user.getResetPasswordExpiration().after(DateUtils.now()), 
				"El código de verificación ha expirado. Por favor generar uno nuevo.");
		
		PasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(newPassword);
		user.setPassword(encryptedPassword);
		user.setResetPasswordExpiration(null);
		user.setResetPasswordVerificationCode(null);
		
		this.userDao.saveOrUpdate(user);
		
		return this.userRegistrationDtoModelTransformer.transform(user);
	}
	
}
