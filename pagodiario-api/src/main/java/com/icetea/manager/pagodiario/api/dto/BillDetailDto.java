package com.icetea.manager.pagodiario.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class BillDetailDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private String clientName;
	private String clientAddress;
	private String traderName;
	private String creditDate;
	private String creditAmount;
	private String installmentAmount;
	private String firstInstallmentAmount;
	private String remainingAmount;
	private List<BillDetailDevolutionDto> devolutions = Lists.newArrayList();
	private List<BillDetailPaymentDto> payments = Lists.newArrayList();
	private List<BillProductDetailDto> products = Lists.newArrayList();
	private List<BillDetailDiscountDto> discounts = Lists.newArrayList();
	private List<BillDetailReductionDto> reductions = Lists.newArrayList();
	
	private String creditNumber;
	
	private String completedDate;
	private String status;
	private String collectorDescription;
	private String customerCompanyType;

	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public String getTraderName() {
		return traderName;
	}
	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}
	public String getCreditDate() {
		return creditDate;
	}
	public void setCreditDate(String creditDate) {
		this.creditDate = creditDate;
	}
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getInstallmentAmount() {
		return installmentAmount;
	}
	public void setInstallmentAmount(String installmentAmount) {
		this.installmentAmount = installmentAmount;
	}
	public String getFirstInstallmentAmount() {
		return firstInstallmentAmount;
	}
	public void setFirstInstallmentAmount(String firstInstallmentAmount) {
		this.firstInstallmentAmount = firstInstallmentAmount;
	}
	public String getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(String remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	public List<BillDetailDevolutionDto> getDevolutions() {
		return devolutions;
	}
	public void setDevolutions(List<BillDetailDevolutionDto> devolutions) {
		this.devolutions = devolutions;
	}
	public List<BillDetailPaymentDto> getPayments() {
		return payments;
	}
	public void setPayments(List<BillDetailPaymentDto> payments) {
		this.payments = payments;
	}
	public List<BillProductDetailDto> getProducts() {
		return products;
	}
	public void setProducts(List<BillProductDetailDto> products) {
		this.products = products;
	}
	public List<BillDetailDiscountDto> getDiscounts() {
		return discounts;
	}
	public void setDiscounts(List<BillDetailDiscountDto> discounts) {
		this.discounts = discounts;
	}
	public List<BillDetailReductionDto> getReductions() {
		return reductions;
	}
	public void setReductions(List<BillDetailReductionDto> reductions) {
		this.reductions = reductions;
	}
	public String getCreditNumber() {
		return creditNumber;
	}
	public void setCreditNumber(String creditNumber) {
		this.creditNumber = creditNumber;
	}
	public String getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCollectorDescription() {
		return collectorDescription;
	}
	public void setCollectorDescription(String collectorDescription) {
		this.collectorDescription = collectorDescription;
	}
	public String getCustomerCompanyType() {
		return customerCompanyType;
	}
	public void setCustomerCompanyType(String customerCompanyType) {
		this.customerCompanyType = customerCompanyType;
	}
	
}
