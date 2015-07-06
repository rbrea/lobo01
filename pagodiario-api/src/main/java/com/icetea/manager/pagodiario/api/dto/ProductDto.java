package com.icetea.manager.pagodiario.api.dto;

public class ProductDto extends BasicDto {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String code;
	private String description;
	private String price;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
