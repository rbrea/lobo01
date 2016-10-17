package com.icetea.manager.pagodiario.api.dto;

public class CollectorDetailDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String zone;
	private String name;
	private String amountToCollect;
	private String amountCollected;
	private String remainingAmount;
	private String fromDate;
	private String toDate;
	private Long billId;

	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAmountToCollect() {
		return amountToCollect;
	}
	public void setAmountToCollect(String amountToCollect) {
		this.amountToCollect = amountToCollect;
	}
	public String getAmountCollected() {
		return amountCollected;
	}
	public void setAmountCollected(String amountCollected) {
		this.amountCollected = amountCollected;
	}
	public String getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(String remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId = billId;
	}
	
}
