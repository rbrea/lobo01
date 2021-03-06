package com.icetea.manager.pagodiario.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

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
	private String totalToCollect;
	private int cardsCountReal;
	private List<ConciliationItemCollectDto> items = Lists.newArrayList();
	
	public PayrollItemCollectDto() {
		super();
	}

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
	public String getTotalToCollect() {
		return totalToCollect;
	}
	public void setTotalToCollect(String totalToCollect) {
		this.totalToCollect = totalToCollect;
	}
	public int getCardsCountReal() {
		return cardsCountReal;
	}
	public void setCardsCountReal(int cardsCountReal) {
		this.cardsCountReal = cardsCountReal;
	}

	public List<ConciliationItemCollectDto> getItems() {
		return items;
	}

	public void setItems(List<ConciliationItemCollectDto> items) {
		this.items = items;
	}

}
