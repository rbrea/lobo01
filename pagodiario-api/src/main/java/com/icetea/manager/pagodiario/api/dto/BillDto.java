package com.icetea.manager.pagodiario.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class BillDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private Long traderId;
	private Long clientId;
	private List<BillProductDto> billProducts = Lists.newArrayList();
	private String totalAmount;
	private String totalDailyInstallment;
	private int overdueDays = 0;
	private String startDate;
	private String endDate;
	private String status;
	private Long creditNumber;
	private Integer collectorId;
	private String remainingAmount;

	public Long getTraderId() {
		return traderId;
	}
	public void setTraderId(Long traderId) {
		this.traderId = traderId;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public List<BillProductDto> getBillProducts() {
		return billProducts;
	}
	public void setBillProducts(List<BillProductDto> billProducts) {
		this.billProducts = billProducts;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTotalDailyInstallment() {
		return totalDailyInstallment;
	}
	public void setTotalDailyInstallment(String totalDailyInstallment) {
		this.totalDailyInstallment = totalDailyInstallment;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public int getOverdueDays() {
		return overdueDays;
	}
	public void setOverdueDays(int overdueDays) {
		this.overdueDays = overdueDays;
	}
	public Long getCreditNumber() {
		return creditNumber;
	}
	public void setCreditNumber(Long creditNumber) {
		this.creditNumber = creditNumber;
	}
	public Integer getCollectorId() {
		return collectorId;
	}
	public void setCollectorId(Integer collectorId) {
		this.collectorId = collectorId;
	}
	public String getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(String remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	
}
