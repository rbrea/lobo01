package com.icetea.manager.pagodiario.api.pojo.jasper;

import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BasicDto;
import com.icetea.manager.pagodiario.api.dto.ClientDto;
import com.icetea.manager.pagodiario.api.dto.TraderDto;

public class BillPojo extends BasicDto {

	private static final long serialVersionUID = 1L;

	private Long ticketNumber;
	private TraderDto trader;
	private ClientDto client;
	private List<ProductPojo> products = Lists.newArrayList();
	private String totalAmount;
	private String totalDailyInstallment;
	private int overdueDays = 0;
	private String startDate;
	private String endDate;
	private String status;
	private String creditNumber;
	private Long collectorId;
	private String remainingAmount;
	
	public BillPojo() {
		super();
	}
	
	public TraderDto getTrader() {
		return trader;
	}
	public void setTrader(TraderDto trader) {
		this.trader = trader;
	}
	public ClientDto getClient() {
		return client;
	}
	public void setClient(ClientDto client) {
		this.client = client;
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
	public int getOverdueDays() {
		return overdueDays;
	}
	public void setOverdueDays(int overdueDays) {
		this.overdueDays = overdueDays;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
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
	public String getCreditNumber() {
		return creditNumber;
	}
	public void setCreditNumber(String creditNumber) {
		this.creditNumber = creditNumber;
	}
	public Long getCollectorId() {
		return collectorId;
	}
	public void setCollectorId(Long collectorId) {
		this.collectorId = collectorId;
	}
	public String getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(String remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public List<ProductPojo> getProducts() {
		return products;
	}

	public void setProducts(List<ProductPojo> products) {
		this.products = products;
	}

	public Long getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(Long ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
}
