package com.icetea.manager.pagodiario.model;

import javax.persistence.ManyToOne;

//@Entity
//@Table(name = "ROLE")
//@Audited
public class Role extends Identifiable {

	private static final long serialVersionUID = 1L;
	
	public static final String ROLE_NAME_BASIC = "ROLE_BASIC";
	public static final String ROLE_NAME_ADMIN = "ROLE_ADMIN";

	private String name;
	
	@ManyToOne
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
