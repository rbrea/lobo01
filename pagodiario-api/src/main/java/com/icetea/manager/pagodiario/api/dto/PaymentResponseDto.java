package com.icetea.manager.pagodiario.api.dto;

public class PaymentResponseDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private Integer idx;
	private String errorMessage;
	private PaymentDto payment;
	
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public PaymentDto getPayment() {
		return payment;
	}
	public void setPayment(PaymentDto payment) {
		this.payment = payment;
	}
	
}
