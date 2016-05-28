package com.icetea.manager.pagodiario.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "usuario")
public class User extends Identifiable {

	private static final long serialVersionUID = 1L;

	@Column(name = "USERNAME", length = 40, nullable = false, unique = true)
	private String username;
	@Column(name = "PASSWORD", length = 100, nullable = false)
	private String password;
	@Column(name = "TOKEN", length = 50, nullable = false, unique = true)
	private String token;
	@Column(name = "NAME", length = 100)
	private String name;
	@Column(name = "EMAIL", length = 150)
	private String email;
	@Column(name = "DOCUMENT_NUMBER", length = 50)
	private String documentNumber;
	@Column(name = "DOCUMENT_TYPE", length = 50)
	private String documentType;
	@Column(name = "IS_ADMIN")
	private boolean admin = false;
	@Column(name = "RESET_PASSWORD_VERIFICATION_CODE", length = 50)
	private String resetPasswordVerificationCode;
	@Column(name = "RESET_PASSWORD_EXPIRATION", columnDefinition = "DATETIME")
	private Date resetPasswordExpiration;
	
	public User() {
		super();
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getResetPasswordVerificationCode() {
		return resetPasswordVerificationCode;
	}

	public void setResetPasswordVerificationCode(
			String resetPasswordVerificationCode) {
		this.resetPasswordVerificationCode = resetPasswordVerificationCode;
	}

	public Date getResetPasswordExpiration() {
		return resetPasswordExpiration;
	}

	public void setResetPasswordExpiration(Date resetPasswordExpiration) {
		this.resetPasswordExpiration = resetPasswordExpiration;
	}

}
