package com.icetea.manager.pagodiario.api.dto;

public class DevDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private Long billId;
	private String observations;
	private String amount;
	private String date;
	private Integer productCount;
	private Long productId;
	private String productCode;
	private String productInstallment;

	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId = billId;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductInstallment() {
		return productInstallment;
	}
	public void setProductInstallment(String productInstallment) {
		this.productInstallment = productInstallment;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
}
