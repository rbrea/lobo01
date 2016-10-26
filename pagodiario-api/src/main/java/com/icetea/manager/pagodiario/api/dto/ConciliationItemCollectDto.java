package com.icetea.manager.pagodiario.api.dto;

public class ConciliationItemCollectDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private Long creditNumber;
	private String amount;
	private String description;
	private String clientName;
	private String clientCompanyType;
	
	public ConciliationItemCollectDto() {
		super();
	}

	public Long getCreditNumber() {
		return creditNumber;
	}

	public void setCreditNumber(Long creditNumber) {
		this.creditNumber = creditNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientCompanyType() {
		return clientCompanyType;
	}

	public void setClientCompanyType(String clientCompanyType) {
		this.clientCompanyType = clientCompanyType;
	}

}
