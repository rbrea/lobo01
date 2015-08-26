package com.icetea.manager.pagodiario.api.pojo.jasper;

import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class DevDetailPojo extends BasicDto {

	private static final long serialVersionUID = 1L;

	private String devDate = "";
	private String product = "";
	private String amount = "";
	private String installmentAmount = "";

	public String getDevDate() {
		return devDate;
	}
	public void setDevDate(String devDate) {
		this.devDate = devDate;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
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
