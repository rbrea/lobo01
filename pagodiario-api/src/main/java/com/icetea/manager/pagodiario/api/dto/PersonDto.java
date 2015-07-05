package com.icetea.manager.pagodiario.api.dto;

public abstract class PersonDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String documentType = "DNI";
	private Long documentNumber;
	private String phone;
	private String address;
	private String nearStreets;
	private String city;
	private String email;
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
