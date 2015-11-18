package com.icetea.manager.pagodiario.api.dto;

public class BillProductDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private int count;
	private Long productId;
	private String amount;
	private String dailyInstallment;
	private String price;
	private String productCode;
	private String productDescription;

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDailyInstallment() {
		return dailyInstallment;
	}
	public void setDailyInstallment(String dailyInstallment) {
		this.dailyInstallment = dailyInstallment;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
}
