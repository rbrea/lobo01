package com.icetea.manager.pagodiario.api.dto;

public class PaydayDto extends BasicOutputDto {

	private static final long serialVersionUID = 1L;

	private Long billId;
	private boolean weekSunday;
	private boolean weekMonday;
	private boolean weekTuesday;
	private boolean weekWednesday;
	private boolean weekThursday;
	private boolean weekFriday;
	private boolean weekSaturday;

	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId = billId;
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
	
}
