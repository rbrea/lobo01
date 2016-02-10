package com.icetea.manager.pagodiario.api.pojo.jasper;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class BillTicketPojo extends BasicDto {

	private static final long serialVersionUID = 1L;
	
	private String ticketNumber = "";
	private String traderName = "";
	private String traderPhone = "";
	private String clientName = "";
	private String clientCompanyAddress = "";
	private String companyType = "";
	private String clientAddress = "";
	private String clientCity = "";
	private String clientPhone = "";
	private String overdueDays = "";
	private List<ProductPojo> products = Lists.newArrayList();
	private String purchaseDate = "";
	private String totalAmount = "";
	private String installmentAmount = "";
	private String remainingAmount = "";
	private String creditNumber = "";
	private String currentDate = "";
	private String remainingDays = "";
	private String zone = "";
	private String weekAmount;
	private String currentAmount;
	
	private String ticketNumber2 = "";
	private String traderName2 = "";
	private String traderPhone2 = "";
	private String clientName2 = "";
	private String clientCompanyAddress2 = "";
	private String companyType2 = "";
	private String clientAddress2 = "";
	private String clientCity2 = "";
	private String clientPhone2 = "";
	private String overdueDays2 = "";
	private List<ProductPojo> products2 = Lists.newArrayList();
	private String purchaseDate2 = "";
	private String totalAmount2 = "";
	private String installmentAmount2 = "";
	private String remainingAmount2 = "";
	private String creditNumber2 = "";
	private String remainingDays2 = "";
	private String zone2 = "";
	private String weekAmount2;
	private String currentAmount2;
	
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getTraderName() {
		return traderName;
	}
	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}
	public String getTraderPhone() {
		return traderPhone;
	}
	public void setTraderPhone(String traderPhone) {
		this.traderPhone = traderPhone;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientCompanyAddress() {
		return clientCompanyAddress;
	}
	public void setClientCompanyAddress(String clientCompanyAddress) {
		this.clientCompanyAddress = clientCompanyAddress;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public String getClientCity() {
		return clientCity;
	}
	public void setClientCity(String clientCity) {
		this.clientCity = clientCity;
	}
	public String getClientPhone() {
		return clientPhone;
	}
	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}
	public String getOverdueDays() {
		return overdueDays;
	}
	public void setOverdueDays(String overdueDays) {
		this.overdueDays = overdueDays;
	}
	public List<ProductPojo> getProducts() {
		return products;
	}
	public void setProducts(List<ProductPojo> products) {
		this.products = products;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getInstallmentAmount() {
		return installmentAmount;
	}
	public void setInstallmentAmount(String installmentAmount) {
		this.installmentAmount = installmentAmount;
	}
	public String getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(String remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	public String getCreditNumber() {
		return creditNumber;
	}
	public void setCreditNumber(String creditNumber) {
		this.creditNumber = creditNumber;
	}
	public String getTicketNumber2() {
		return ticketNumber2;
	}
	public void setTicketNumber2(String ticketNumber2) {
		this.ticketNumber2 = ticketNumber2;
	}
	public String getTraderName2() {
		return traderName2;
	}
	public void setTraderName2(String traderName2) {
		this.traderName2 = traderName2;
	}
	public String getTraderPhone2() {
		return traderPhone2;
	}
	public void setTraderPhone2(String traderPhone2) {
		this.traderPhone2 = traderPhone2;
	}
	public String getClientName2() {
		return clientName2;
	}
	public void setClientName2(String clientName2) {
		this.clientName2 = clientName2;
	}
	public String getClientCompanyAddress2() {
		return clientCompanyAddress2;
	}
	public void setClientCompanyAddress2(String clientCompanyAddress2) {
		this.clientCompanyAddress2 = clientCompanyAddress2;
	}
	public String getCompanyType2() {
		return companyType2;
	}
	public void setCompanyType2(String companyType2) {
		this.companyType2 = companyType2;
	}
	public String getClientAddress2() {
		return clientAddress2;
	}
	public void setClientAddress2(String clientAddress2) {
		this.clientAddress2 = clientAddress2;
	}
	public String getClientCity2() {
		return clientCity2;
	}
	public void setClientCity2(String clientCity2) {
		this.clientCity2 = clientCity2;
	}
	public String getClientPhone2() {
		return clientPhone2;
	}
	public void setClientPhone2(String clientPhone2) {
		this.clientPhone2 = clientPhone2;
	}
	public String getOverdueDays2() {
		return overdueDays2;
	}
	public void setOverdueDays2(String overdueDays2) {
		this.overdueDays2 = overdueDays2;
	}
	public List<ProductPojo> getProducts2() {
		return products2;
	}
	public void setProducts2(List<ProductPojo> products2) {
		this.products2 = products2;
	}
	public String getPurchaseDate2() {
		return purchaseDate2;
	}
	public void setPurchaseDate2(String purchaseDate2) {
		this.purchaseDate2 = purchaseDate2;
	}
	public String getTotalAmount2() {
		return totalAmount2;
	}
	public void setTotalAmount2(String totalAmount2) {
		this.totalAmount2 = totalAmount2;
	}
	public String getInstallmentAmount2() {
		return installmentAmount2;
	}
	public void setInstallmentAmount2(String installmentAmount2) {
		this.installmentAmount2 = installmentAmount2;
	}
	public String getRemainingAmount2() {
		return remainingAmount2;
	}
	public void setRemainingAmount2(String remainingAmount2) {
		this.remainingAmount2 = remainingAmount2;
	}
	public String getCreditNumber2() {
		return creditNumber2;
	}
	public void setCreditNumber2(String creditNumber2) {
		this.creditNumber2 = creditNumber2;
	}

	public String getProductList(){
		String r = "";
		int i=0;
		for (ProductPojo productPojo : this.products) {
			if(i==4){
				break;
			}
			r += "-" + StringUtils.abbreviate(productPojo.getName(), 30) + " x " + productPojo.getCount() + ((i==3)?" ... " : "") + "\n";
			i++;
		}
		
		return r;
	}
	
	public String getProductList2(){
		String r = "";
		int i=0;
		for (ProductPojo productPojo : this.products2) {
			if(i==4){
				break;
			}
			r += "-" + StringUtils.abbreviate(productPojo.getName(), 30) + " x " + productPojo.getCount() +  ((i==3)?" ... " : "") + "\n";
			i++;
		}
		
		return r;
	}
	
	public String getCurrentDate() {
		return currentDate;
	}
	
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public String getRemainingDays() {
		return remainingDays;
	}
	public void setRemainingDays(String remainingDays) {
		this.remainingDays = remainingDays;
	}
	public String getRemainingDays2() {
		return remainingDays2;
	}
	public void setRemainingDays2(String remainingDays2) {
		this.remainingDays2 = remainingDays2;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getZone2() {
		return zone2;
	}
	public void setZone2(String zone2) {
		this.zone2 = zone2;
	}
	public String getWeekAmount() {
		return weekAmount;
	}
	public void setWeekAmount(String weekAmount) {
		this.weekAmount = weekAmount;
	}
	public String getWeekAmount2() {
		return weekAmount2;
	}
	public void setWeekAmount2(String weekAmount2) {
		this.weekAmount2 = weekAmount2;
	}
	public String getCurrentAmount() {
		return currentAmount;
	}
	public void setCurrentAmount(String currentAmount) {
		this.currentAmount = currentAmount;
	}
	public String getCurrentAmount2() {
		return currentAmount2;
	}
	public void setCurrentAmount2(String currentAmount2) {
		this.currentAmount2 = currentAmount2;
	}
	
}
