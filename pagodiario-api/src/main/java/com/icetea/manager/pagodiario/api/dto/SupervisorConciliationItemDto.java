package com.icetea.manager.pagodiario.api.dto;

public class SupervisorConciliationItemDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private String traderName;
	private String creditAmount;
	private String devAmount;
	private String bonusAmount;
	private String reductionAmount;
	private String totalTrader;

	public String getTraderName() {
		return traderName;
	}
	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getDevAmount() {
		return devAmount;
	}
	public void setDevAmount(String devAmount) {
		this.devAmount = devAmount;
	}
	public String getBonusAmount() {
		return bonusAmount;
	}
	public void setBonusAmount(String bonusAmount) {
		this.bonusAmount = bonusAmount;
	}
	public String getReductionAmount() {
		return reductionAmount;
	}
	public void setReductionAmount(String reductionAmount) {
		this.reductionAmount = reductionAmount;
	}
	public String getTotalTrader() {
		return totalTrader;
	}
	public void setTotalTrader(String totalTrader) {
		this.totalTrader = totalTrader;
	}
	
}
