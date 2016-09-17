package com.icetea.manager.pagodiario.api.dto;

public class ClientDto extends PersonDto {

	private static final long serialVersionUID = 1L;

	private String companyType;
	private String companyAddress;
	private String companyCity;
	private String companyPhone;
	private String reductionMark;
	private String cancelationMark;

	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyCity() {
		return companyCity;
	}
	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getReductionMark() {
		return reductionMark;
	}
	public void setReductionMark(String reductionMark) {
		this.reductionMark = reductionMark;
	}
	public String getCancelationMark() {
		return cancelationMark;
	}
	public void setCancelationMark(String cancelationMark) {
		this.cancelationMark = cancelationMark;
	}

}
