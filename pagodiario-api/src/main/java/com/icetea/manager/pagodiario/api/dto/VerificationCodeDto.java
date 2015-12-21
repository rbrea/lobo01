package com.icetea.manager.pagodiario.api.dto;

public class VerificationCodeDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private String verificationCode;
	private String newPassword;
	private String username;

	public VerificationCodeDto() {
		super();
	}
	
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
