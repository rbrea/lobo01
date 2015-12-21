package com.icetea.manager.pagodiario.dao;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.User;

@Named
public class UserDaoImpl extends BasicIdentificableDaoImpl<User> implements
		UserDao {

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	public User find(String username, String password){
		Criteria criteria = super.createCriteria();
		
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("password", password));
		
		return (User) criteria.uniqueResult();
	}
	
	@Override
	public User findByAccessToken(String accessToken){
		Criteria criteria = super.createCriteria();
		
		criteria.add(Restrictions.eq("token", accessToken));
		
		return (User) criteria.uniqueResult();
	}

	@Override
	public User find(String username) {
		Criteria criteria = super.createCriteria();
		
		criteria.add(Restrictions.eq("username", username));
		
		return (User) criteria.uniqueResult();
	}
	
	@Override
	public User findByVerificationCode(String verificationCode) {
		Criteria criteria = super.createCriteria();
		
		criteria.add(Restrictions.eq("resetPasswordVerificationCode", verificationCode));
		
		return (User) criteria.uniqueResult();
	}
	
}
