package com.icetea.manager.pagodiario.api.dto;

public class BillDetailPaymentDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private String date;
	private String collector;
	private String amount;
	private String traderPayment;

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCollector() {
		return collector;
	}
	public void setCollector(String collector) {
		this.collector = collector;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTraderPayment() {
		return traderPayment;
	}
	public void setTraderPayment(String traderPayment) {
		this.traderPayment = traderPayment;
	}
	
}
