package com.icetea.manager.pagodiario.api.dto;

public class PayrollItemCollectDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private Long collectorZone;
	private String collectorDescription;
	private String totalAmount;
	private String commission;
	private int cardsCount;
	private String totalPayment;
	private String commissionPercentage;
	private String amountToPay;

	public Long getCollectorZone() {
		return collectorZone;
	}
	public void setCollectorZone(Long collectorZone) {
		this.collectorZone = collectorZone;
	}
	public String getCollectorDescription() {
		return collectorDescription;
	}
	public void setCollectorDescription(String collectorDescription) {
		this.collectorDescription = collectorDescription;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public int getCardsCount() {
		return cardsCount;
	}
	public void setCardsCount(int cardsCount) {
		this.cardsCount = cardsCount;
	}
	public String getTotalPayment() {
		return totalPayment;
	}
	public void setTotalPayment(String totalPayment) {
		this.totalPayment = totalPayment;
	}
	public String getCommissionPercentage() {
		return commissionPercentage;
	}
	public void setCommissionPercentage(String commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}
	public String getAmountToPay() {
		return amountToPay;
	}
	public void setAmountToPay(String amountToPay) {
		this.amountToPay = amountToPay;
	}
	
}
