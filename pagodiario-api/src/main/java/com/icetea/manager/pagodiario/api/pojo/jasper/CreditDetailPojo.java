package com.icetea.manager.pagodiario.api.pojo.jasper;

import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class CreditDetailPojo extends BasicDto {

	private static final long serialVersionUID = 1L;
	
	private String detailNumber = "";
	private String clientName = "";
	private String clientAddress = "";
	private String traderName = "";
	private String creditDate = "";
	private String creditAmount = "";
	private String installmentAmount = "";
	private String firstInstallmentAmount = "";
	private List<DevDetailPojo> devs = Lists.newArrayList();
	private List<PaymentDetailPojo> payments = Lists.newArrayList();
	private String totalAmount = "";
	
	public String getDetailNumber() {
		return detailNumber;
	}
	public void setDetailNumber(String detailNumber) {
		this.detailNumber = detailNumber;
	}
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
	public List<DevDetailPojo> getDevs() {
		return devs;
	}
	public void setDevs(List<DevDetailPojo> devs) {
		this.devs = devs;
	}
	public List<PaymentDetailPojo> getPayments() {
		return payments;
	}
	public void setPayments(List<PaymentDetailPojo> payments) {
		this.payments = payments;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
