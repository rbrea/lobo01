package com.icetea.manager.pagodiario.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person extends Identifiable {

	private static final long serialVersionUID = 1L;

	@Column(name = "NAME", length = 100, nullable = false)
	private String name;
	@Column(name = "DOCUMENT_TYPE", length = 100, nullable = false)
	private String documentType;
	@Column(name = "DOCUMENT_NUMBER", unique = true, nullable = false)
	private Long documentNumber;
	@Column(name = "PHONE", length = 100)
	private String phone;
	@Column(name = "ADDRESS", length = 200)
	private String address;
	@Column(name = "NEAR_STREETS", length = 200)
	private String nearStreets;
	@Column(name = "CITY", length = 100)
	private String city;
	@Column(name = "EMAIL", length = 100)
	private String email;
	
	public Person() {
		super();
	}

	public Person(String createdBy) {
		super(createdBy);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public Long getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(Long documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNearStreets() {
		return nearStreets;
	}

	public void setNearStreets(String nearStreets) {
		this.nearStreets = nearStreets;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
