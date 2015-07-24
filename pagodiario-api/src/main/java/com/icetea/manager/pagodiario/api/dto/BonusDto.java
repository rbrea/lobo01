package com.icetea.manager.pagodiario.api.dto;

public class BonusDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private Long billId;
	private String observations;
	private String amount;
	private String date;

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
	
}
