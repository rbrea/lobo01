package com.icetea.manager.pagodiario.api.dto;

public class PayrollDetailDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private Long traderId;
	private String date;
	private String name;
	private String phone;
	private String totalAmount = "0";
	private String totalDiscount = "0";
	private String total = "0";

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(String totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public Long getTraderId() {
		return traderId;
	}
	public void setTraderId(Long traderId) {
		this.traderId = traderId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
