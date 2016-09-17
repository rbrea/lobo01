package com.icetea.manager.pagodiario.api.pojo.jasper;

import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class PaymentDetailPojo extends BasicDto {

	private static final long serialVersionUID = 1L;

	private String paymentDate = "";
	private String collector = "";
	private String amount = "";
	private String traderPayment;

	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
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
