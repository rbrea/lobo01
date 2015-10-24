package com.icetea.manager.pagodiario.api.pojo.jasper;

import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class VoucherPojo extends BasicDto {

	private static final long serialVersionUID = 1L;
	
	private String voucherId;
	private String traderData;
	private String zone;
	private String clientName;
	private String clientData;
	private String clientDocumentNumber;
	private String discountAmount;
	private String installmentAmount;
	private String expirationDate;

	public String getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}
	public String getTraderData() {
		return traderData;
	}
	public void setTraderData(String traderData) {
		this.traderData = traderData;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientData() {
		return clientData;
	}
	public void setClientData(String clientData) {
		this.clientData = clientData;
	}
	public String getClientDocumentNumber() {
		return clientDocumentNumber;
	}
	public void setClientDocumentNumber(String clientDocumentNumber) {
		this.clientDocumentNumber = clientDocumentNumber;
	}
	public String getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getInstallmentAmount() {
		return installmentAmount;
	}
	public void setInstallmentAmount(String installmentAmount) {
		this.installmentAmount = installmentAmount;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
}
