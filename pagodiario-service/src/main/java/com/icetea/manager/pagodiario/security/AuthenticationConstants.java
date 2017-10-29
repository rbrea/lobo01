package com.icetea.manager.pagodiario.security;

public interface AuthenticationConstants {
    
	String COOKIE_TOKEN = "COOKIE_TOKEN";
	
	String COOKIE_MAX_AGE_MINUTES = "COOKIE_MAX_AGE_MINUTES";
	
	Integer COOKIE_MAX_AGE_MINUTES_VALUE = 5;
	
	String APPLICATION_NAME = "sgpd";
	
	String SESSION_AUTHENTICATION = "authentication.dto.";
	
	String SESSION_USERNAME_KEY = "authentication.session.username";
	
	String AUTHENTICATION_ACCESS_KEY = "authentication.access.key.";
	
	String AUTHENTICATION_EXCHANGE_KEY = "authentication.exchange.key.";
	
	String ROLE_KEY_ACCESS = "ACCESS"; 
	
	String ROLE_KEY_LOGIN = "LOGIN";
	
	String ROLE_KEY_APPLICATION = "APPLICATION";
	
}
