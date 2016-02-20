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
	private String customerAddress;
	
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
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getWeekAmount() {
		return weekAmount;
	}
	public void setWeekAmount(String weekAmount) {
		this.weekAmount = weekAmount;
	}
	public String getCurrentAmount() {
		return currentAmount;
	}
	public void setCurrentAmount(String currentAmount) {
		this.currentAmount = currentAmount;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
}
