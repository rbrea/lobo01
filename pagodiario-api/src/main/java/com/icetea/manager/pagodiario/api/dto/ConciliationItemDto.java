package com.icetea.manager.pagodiario.api.dto;

public class ConciliationItemDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private String date;
	private String collectAmount;
	private String discountAmount;
	private String description;
	private String type;

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCollectAmount() {
		return collectAmount;
	}
	public void setCollectAmount(String collectAmount) {
		this.collectAmount = collectAmount;
	}
	public String getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
