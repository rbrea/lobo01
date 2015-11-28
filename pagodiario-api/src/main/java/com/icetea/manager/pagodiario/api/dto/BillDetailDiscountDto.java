package com.icetea.manager.pagodiario.api.dto;

public class BillDetailDiscountDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private String date;
	private String amount;
	private String observations;

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	
}
