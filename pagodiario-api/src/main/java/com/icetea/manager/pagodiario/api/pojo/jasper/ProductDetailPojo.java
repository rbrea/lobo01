package com.icetea.manager.pagodiario.api.pojo.jasper;

import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class ProductDetailPojo extends BasicDto {

	private static final long serialVersionUID = 1L;

	private String count;
	private String code;
	private String description;
	private String installmentAmount;
	private String totalAmount;

	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
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
	public String getInstallmentAmount() {
		return installmentAmount;
	}
	public void setInstallmentAmount(String installmentAmount) {
		this.installmentAmount = installmentAmount;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
