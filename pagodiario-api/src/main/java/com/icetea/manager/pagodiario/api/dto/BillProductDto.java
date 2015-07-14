package com.icetea.manager.pagodiario.api.dto;

public class BillProductDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private int count;
	private Long productId;
	private String amount;
	private String dailyInstallment;

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
	
}
