package com.icetea.manager.pagodiario.api.dto;

public class PaymentDto extends BillInfoDto {

	private static final long serialVersionUID = 1L;
	
	private Integer idx;
	private String amount;
	private String date;
	private Long creditNumber;
	private boolean traderPayment;
	private String clientName;
	private String clientCompanyType;
	private String collectorDescription;
	private Long collectorZone;
	
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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientCompanyType() {
		return clientCompanyType;
	}

	public void setClientCompanyType(String clientCompanyType) {
		this.clientCompanyType = clientCompanyType;
	}

	public String getCollectorDescription() {
		return collectorDescription;
	}

	public void setCollectorDescription(String collectorDescription) {
		this.collectorDescription = collectorDescription;
	}

	public Long getCollectorZone() {
		return collectorZone;
	}

	public void setCollectorZone(Long collectorZone) {
		this.collectorZone = collectorZone;
	}

}
