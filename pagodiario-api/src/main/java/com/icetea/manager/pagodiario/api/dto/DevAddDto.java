package com.icetea.manager.pagodiario.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class DevAddDto extends BillInfoDto {

	private static final long serialVersionUID = 1L;

	private Long billId;
	private String selectedDate;
	private List<BillProductDto> billProducts = Lists.newArrayList();
	private String observations;
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
	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId = billId;
	}
	public String getTotalInstallment() {
		return totalInstallment;
	}
	public void setTotalInstallment(String totalInstallment) {
		this.totalInstallment = totalInstallment;
	}
	
}
