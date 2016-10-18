package com.icetea.manager.pagodiario.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class BillDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private Long traderId;
	private Long clientId;
	private List<BillProductDto> billProducts = Lists.newArrayList();
	private String totalAmount;
	private String totalDailyInstallment;
	private int overdueDays = 0;
	private String startDate;
	private String endDate;
	private String status;
	private Long creditNumber;
	private Long collectorId;
	private Long collectorZone;
	private String collectorDescription;
	private String remainingAmount;
	private Integer weekOfYear;
	private Integer month;
	private Integer year;
	private String traderName;
	private String traderPhone;
	private String clientName;
	private String clientAddress;
	private String clientCompanyType;
	private String clientDocumentNumber;
	private boolean weekSunday;
	private boolean weekMonday;
	private boolean weekTuesday;
	private boolean weekWednesday;
	private boolean weekThursday;
	private boolean weekFriday;
	private boolean weekSaturday;
	private String payrollDate;
	private Boolean devTotalMark;
	
	public BillDto() {
		super();
	}

	public Long getTraderId() {
		return traderId;
	}
	public void setTraderId(Long traderId) {
		this.traderId = traderId;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public List<BillProductDto> getBillProducts() {
		return billProducts;
	}
	public void setBillProducts(List<BillProductDto> billProducts) {
		this.billProducts = billProducts;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public int getOverdueDays() {
		return overdueDays;
	}
	public void setOverdueDays(int overdueDays) {
		this.overdueDays = overdueDays;
	}
	public Long getCreditNumber() {
		return creditNumber;
	}
	public void setCreditNumber(Long creditNumber) {
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
	public Integer getWeekOfYear() {
		return weekOfYear;
	}
	public void setWeekOfYear(Integer weekOfYear) {
		this.weekOfYear = weekOfYear;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
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
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public String getClientCompanyType() {
		return clientCompanyType;
	}
	public void setClientCompanyType(String clientCompanyType) {
		this.clientCompanyType = clientCompanyType;
	}
	public String getClientDocumentNumber() {
		return clientDocumentNumber;
	}
	public void setClientDocumentNumber(String clientDocumentNumber) {
		this.clientDocumentNumber = clientDocumentNumber;
	}
	public String getCollectorDescription() {
		return collectorDescription;
	}
	public void setCollectorDescription(String collectorDescription) {
		this.collectorDescription = collectorDescription;
	}

	public Long getCollectorZone() {
		return collectorZone;
	}

	public void setCollectorZone(Long collectorZone) {
		this.collectorZone = collectorZone;
	}

	public boolean isWeekSunday() {
		return weekSunday;
	}

	public void setWeekSunday(boolean weekSunday) {
		this.weekSunday = weekSunday;
	}

	public boolean isWeekMonday() {
		return weekMonday;
	}

	public void setWeekMonday(boolean weekMonday) {
		this.weekMonday = weekMonday;
	}

	public boolean isWeekTuesday() {
		return weekTuesday;
	}

	public void setWeekTuesday(boolean weekTuesday) {
		this.weekTuesday = weekTuesday;
	}

	public boolean isWeekWednesday() {
		return weekWednesday;
	}

	public void setWeekWednesday(boolean weekWednesday) {
		this.weekWednesday = weekWednesday;
	}

	public boolean isWeekThursday() {
		return weekThursday;
	}

	public void setWeekThursday(boolean weekThursday) {
		this.weekThursday = weekThursday;
	}

	public boolean isWeekFriday() {
		return weekFriday;
	}

	public void setWeekFriday(boolean weekFriday) {
		this.weekFriday = weekFriday;
	}

	public boolean isWeekSaturday() {
		return weekSaturday;
	}

	public void setWeekSaturday(boolean weekSaturday) {
		this.weekSaturday = weekSaturday;
	}

	public String getPayrollDate() {
		return payrollDate;
	}

	public void setPayrollDate(String payrollDate) {
		this.payrollDate = payrollDate;
	}

	public Boolean getDevTotalMark() {
		return devTotalMark;
	}

	public void setDevTotalMark(Boolean devTotalMark) {
		this.devTotalMark = devTotalMark;
	}
	
}
