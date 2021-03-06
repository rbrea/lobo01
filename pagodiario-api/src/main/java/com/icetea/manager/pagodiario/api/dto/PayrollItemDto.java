package com.icetea.manager.pagodiario.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class PayrollItemDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;
	
	private String traderName;
	private String subtotalCollect;
	private String subtotalDiscount;
	private String payrollDateFrom;
	private String payrollDateTo;
	private String totalAmount;
	
	private List<ConciliationItemDto> conciliationItems = Lists.newArrayList();

	public String getTraderName() {
		return traderName;
	}
	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}
	public String getSubtotalCollect() {
		return subtotalCollect;
	}
	public void setSubtotalCollect(String subtotalCollect) {
		this.subtotalCollect = subtotalCollect;
	}
	public String getSubtotalDiscount() {
		return subtotalDiscount;
	}
	public void setSubtotalDiscount(String subtotalDiscount) {
		this.subtotalDiscount = subtotalDiscount;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPayrollDateFrom() {
		return payrollDateFrom;
	}
	public void setPayrollDateFrom(String payrollDateFrom) {
		this.payrollDateFrom = payrollDateFrom;
	}
	public String getPayrollDateTo() {
		return payrollDateTo;
	}
	public void setPayrollDateTo(String payrollDateTo) {
		this.payrollDateTo = payrollDateTo;
	}
	public List<ConciliationItemDto> getConciliationItems() {
		return conciliationItems;
	}
	public void setConciliationItems(List<ConciliationItemDto> conciliationItems) {
		this.conciliationItems = conciliationItems;
	}

	public void addConciliationItem(ConciliationItemDto d){
		if(d != null){
			this.conciliationItems.add(d);
		}
	}
	
}
