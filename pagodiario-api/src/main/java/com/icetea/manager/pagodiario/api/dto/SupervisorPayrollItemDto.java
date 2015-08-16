package com.icetea.manager.pagodiario.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class SupervisorPayrollItemDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;
	
	private List<SupervisorConciliationItemDto> supervisorConciliationItemList = Lists.newArrayList();
	
	private String supervisorName;
	private String totalCreditAmount;
	private String totalDevAmount;
	private String totalBonusAmount;
	private String totalReductionAmount;
	private String totalAmount;

	public List<SupervisorConciliationItemDto> getSupervisorConciliationItemList() {
		return supervisorConciliationItemList;
	}

	public void setSupervisorConciliationItemList(
			List<SupervisorConciliationItemDto> supervisorConciliationItemList) {
		this.supervisorConciliationItemList = supervisorConciliationItemList;
	}

	public void addSupervisorConciliationItem(SupervisorConciliationItemDto d){
		if(d != null){
			this.supervisorConciliationItemList.add(d);
		}
	}

	public String getTotalCreditAmount() {
		return totalCreditAmount;
	}

	public void setTotalCreditAmount(String totalCreditAmount) {
		this.totalCreditAmount = totalCreditAmount;
	}

	public String getTotalDevAmount() {
		return totalDevAmount;
	}

	public void setTotalDevAmount(String totalDevAmount) {
		this.totalDevAmount = totalDevAmount;
	}

	public String getTotalBonusAmount() {
		return totalBonusAmount;
	}

	public void setTotalBonusAmount(String totalBonusAmount) {
		this.totalBonusAmount = totalBonusAmount;
	}

	public String getTotalReductionAmount() {
		return totalReductionAmount;
	}

	public void setTotalReductionAmount(String totalReductionAmount) {
		this.totalReductionAmount = totalReductionAmount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
	
}
