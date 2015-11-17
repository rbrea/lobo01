package com.icetea.manager.pagodiario.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class DevAddDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private Long billId;
	private String selectedDate;
	private List<BillProductDto> billProducts = Lists.newArrayList();
	private String observations;
	private String totalAmount;
	private String totalInstallment;

	public String getSelectedDate() {
		return selectedDate;
	}
	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}
	public List<BillProductDto> getBillProducts() {
		return billProducts;
	}
	public void setBillProducts(List<BillProductDto> billProducts) {
		this.billProducts = billProducts;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTotalInstallment() {
		return totalInstallment;
	}
	public void setTotalInstallment(String totalInstallment) {
		this.totalInstallment = totalInstallment;
	}
	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId = billId;
	}
	
}
