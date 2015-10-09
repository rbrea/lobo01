package com.icetea.manager.pagodiario.api.dto.chart;

import com.icetea.manager.pagodiario.api.dto.BasicOutputDto;

public class DashBoardDto extends BasicOutputDto {

	private static final long serialVersionUID = 1L;

	private String totalAmount = "0.00";
	private String totalCollected = "0.00";
	private String totalCommission = "0.00";
	private int countActivesBills = 0;
	
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTotalCollected() {
		return totalCollected;
	}
	public void setTotalCollected(String totalCollected) {
		this.totalCollected = totalCollected;
	}
	public String getTotalCommission() {
		return totalCommission;
	}
	public void setTotalCommission(String totalCommission) {
		this.totalCommission = totalCommission;
	}
	public int getCountActivesBills() {
		return countActivesBills;
	}
	public void setCountActivesBills(int countActivesBills) {
		this.countActivesBills = countActivesBills;
	}
	
}
