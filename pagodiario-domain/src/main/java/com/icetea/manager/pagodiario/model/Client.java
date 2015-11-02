package com.icetea.manager.pagodiario.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "CLIENT")
@Audited
public class Client extends Person {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "COMPANY_TYPE", length = 50)
	private String companyType;
	@Column(name = "COMPANY_ADDRESS", length = 200)
	private String companyAddress;
	@Column(name = "COMPANY_CITY", length = 200)
	private String companyCity;
	@Column(name = "COMPANY_PHONE", length = 100)
	private String companyPhone;
	@Column(name = "REDUCTION_MARK", columnDefinition = "DATETIME")
	private Date reductionMark;

	public Client() {
		super();
	}

	public Client(String createdBy) {
		super(createdBy);
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
	
	public String getCompanyType() {
		return companyType;
	}
	
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	
	public String getCompanyPhone() {
		return companyPhone;
	}
	
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public Date getReductionMark() {
		return reductionMark;
	}

	public void setReductionMark(Date reductionMark) {
		this.reductionMark = reductionMark;
	}

}
