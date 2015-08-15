package com.icetea.manager.pagodiario.api.dto;

public class BillDetailDevolutionDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private String date;
	private String productDescription;
	private String amount;
	private String installmentAmount;

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getInstallmentAmount() {
		return installmentAmount;
	}
	public void setInstallmentAmount(String installmentAmount) {
		this.installmentAmount = installmentAmount;
	}
	
}
