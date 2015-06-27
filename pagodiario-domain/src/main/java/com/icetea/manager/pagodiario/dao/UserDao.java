package com.icetea.manager.pagodiario.dao;

import com.icetea.manager.pagodiario.model.User;

public interface UserDao extends BasicIdentificableDao<User> {

	User find(String username);
	
	User find(String username, String password);

	User findByAccessToken(String accessToken);

}
