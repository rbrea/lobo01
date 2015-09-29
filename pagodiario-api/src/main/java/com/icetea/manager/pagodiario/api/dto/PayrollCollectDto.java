package com.icetea.manager.pagodiario.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class PayrollCollectDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private String payrollDate;
	private String totalAmount;
	private String totalPayment;
	private int totalCards;
	private String totalAmountToPay;
	private String totalToCollect;
	private String status;
	
	private List<PayrollItemCollectDto> items = Lists.newArrayList();

	public String getPayrollDate() {
		return payrollDate;
	}
	public void setPayrollDate(String payrollDate) {
		this.payrollDate = payrollDate;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTotalPayment() {
		return totalPayment;
	}
	public void setTotalPayment(String totalPayment) {
		this.totalPayment = totalPayment;
	}
	public int getTotalCards() {
		return totalCards;
	}
	public void setTotalCards(int totalCards) {
		this.totalCards = totalCards;
	}
	public String getTotalAmountToPay() {
		return totalAmountToPay;
	}
	public void setTotalAmountToPay(String totalAmountToPay) {
		this.totalAmountToPay = totalAmountToPay;
	}
	public List<PayrollItemCollectDto> getItems() {
		return items;
	}
	public void setItems(List<PayrollItemCollectDto> items) {
		this.items = items;
	}
	public String getTotalToCollect() {
		return totalToCollect;
	}
	public void setTotalToCollect(String totalToCollect) {
		this.totalToCollect = totalToCollect;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
