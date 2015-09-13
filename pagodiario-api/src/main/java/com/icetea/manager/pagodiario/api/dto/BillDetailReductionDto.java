package com.icetea.manager.pagodiario.api.dto;

public class BillDetailReductionDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private String date;
	private String amount;

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
	
}
