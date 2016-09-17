package com.icetea.manager.pagodiario.api.dto;

public class PaymentDto extends BillInfoDto {

	private static final long serialVersionUID = 1L;
	
	private Integer idx;
	private String amount;
	private String date;
	private Long collectorId;
	private Long creditNumber;
	private boolean traderPayment;
	
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
	
	public Long getCollectorId() {
		return collectorId;
	}
	
	public void setCollectorId(Long collectorId) {
		this.collectorId = collectorId;
	}
	
	public Long getCreditNumber() {
		return creditNumber;
	}

	public void setCreditNumber(Long creditNumber) {
		this.creditNumber = creditNumber;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public boolean isTraderPayment() {
		return traderPayment;
	}

	public void setTraderPayment(boolean traderPayment) {
		this.traderPayment = traderPayment;
	}

}
