package com.icetea.manager.pagodiario.api.dto;


public class ProductDto extends BasicDto {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String code;
	private String description;
	private String price;
	private String dailyInstallment;
	private String weekInstallment;
	private String twoWeeksInstallment;

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
	public String getDailyInstallment() {
		return dailyInstallment;
	}
	public void setDailyInstallment(String dailyInstallment) {
		this.dailyInstallment = dailyInstallment;
	}
	public String getWeekInstallment() {
		return weekInstallment;
	}
	public void setWeekInstallment(String weekInstallment) {
		this.weekInstallment = weekInstallment;
	}
	public String getTwoWeeksInstallment() {
		return twoWeeksInstallment;
	}
	public void setTwoWeeksInstallment(String twoWeeksInstallment) {
		this.twoWeeksInstallment = twoWeeksInstallment;
	}
	
}
