package com.icetea.manager.pagodiario.api.dto;

public class BillInfoDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private Long billId;
	private String billStatus;
	private String remainingAmount;
	private String installmentAmount;
	private int overdueDays;
	private String totalAmount;
	private boolean weekSunday;
	private boolean weekMonday;
	private boolean weekTuesday;
	private boolean weekWednesday;
	private boolean weekThursday;
	private boolean weekFriday;
	private boolean weekSaturday;
	private Long collectorId;

	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	public String getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(String remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	public String getInstallmentAmount() {
		return installmentAmount;
	}
	public void setInstallmentAmount(String installmentAmount) {
		this.installmentAmount = installmentAmount;
	}
	public int getOverdueDays() {
		return overdueDays;
	}
	public void setOverdueDays(int overdueDays) {
		this.overdueDays = overdueDays;
	}
	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId = billId;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
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
	public Long getCollectorId() {
		return collectorId;
	}
	public void setCollectorId(Long collectorId) {
		this.collectorId = collectorId;
	}
	
}
