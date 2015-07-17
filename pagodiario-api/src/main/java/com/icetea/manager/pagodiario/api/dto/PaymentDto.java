package com.icetea.manager.pagodiario.api.dto;

public class PaymentDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;
	
	private String amount;
	private String date;
	private Integer collectorId;
	private Long billId;
	
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
	
	public Integer getCollectorId() {
		return collectorId;
	}
	
	public void setCollectorId(Integer collectorId) {
		this.collectorId = collectorId;
	}
	
	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}
	
}
