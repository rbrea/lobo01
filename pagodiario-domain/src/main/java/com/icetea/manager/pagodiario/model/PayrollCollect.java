package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Entity
@Table(name = "payroll_collect")
public class PayrollCollect extends Identifiable {

	private static final long serialVersionUID = 1L;
	
	public static enum Status {
		INITIALIZED, FINISHED, COMMITED
	}

	@Column(name = "PAYROLL_DATE", columnDefinition = "DATETIME")
	private Date payrollDate;
	@Column(name = "TOTAL_AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE)
	private BigDecimal totalAmount = BigDecimal.ZERO;
	@Column(name = "TOTAL_PAYMENT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE)	
	private BigDecimal totalPayment = BigDecimal.ZERO;
	@Column(name = "TOTAL_CARDS_COUNT", nullable = false)
	private int totalCardsCount = 0;
	@Column(name = "TOTAL_AMOUNT_TO_PAY", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE)	
	private BigDecimal totalAmountToPay = BigDecimal.ZERO;
	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status = Status.INITIALIZED;
	@OneToMany(cascade = CascadeType.ALL)
	private List<PayrollItemCollect> payrollItemCollectList = Lists.newArrayList();
	@Column(name = "TOTAL_CARDS_COUNT_REAL", nullable = false)
	private int cardsCountReal = 0;

	public Date getPayrollDate() {
		return payrollDate;
	}
	public void setPayrollDate(Date payrollDate) {
		this.payrollDate = payrollDate;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<PayrollItemCollect> getPayrollItemCollectList() {
		return payrollItemCollectList;
	}
	public void setPayrollItemCollectList(
			List<PayrollItemCollect> payrollItemCollectList) {
		this.payrollItemCollectList = payrollItemCollectList;
	}
	
	public BigDecimal acumTotalAmount(BigDecimal amount){
		
		this.totalAmount = NumberUtils.add(this.totalAmount, amount);
		
		return this.totalAmount;
	}

	public BigDecimal acumTotalPayment(BigDecimal amount){
		
		this.totalPayment = NumberUtils.add(this.totalPayment, amount);
		
		return this.totalPayment;
	}
	
	public BigDecimal getTotalPayment() {
		return totalPayment;
	}
	public void setTotalPayment(BigDecimal totalPayment) {
		this.totalPayment = totalPayment;
	}

	public int incrementCards(){
		return ++this.totalCardsCount;
	}
	public int getTotalCardsCount() {
		return totalCardsCount;
	}
	public void setTotalCardsCount(int totalCardsCount) {
		this.totalCardsCount = totalCardsCount;
	}
	public BigDecimal getTotalAmountToPay() {
		return totalAmountToPay;
	}
	public void setTotalAmountToPay(BigDecimal totalAmountToPay) {
		this.totalAmountToPay = totalAmountToPay;
	}
	
	public BigDecimal acumTotalAmountToPay(BigDecimal amount){
		
		this.totalAmountToPay = NumberUtils.add(this.totalAmountToPay, amount);
		
		return this.totalAmountToPay;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public int getCardsCountReal() {
		return cardsCountReal;
	}
	
	public void setCardsCountReal(int cardsCountReal) {
		this.cardsCountReal = cardsCountReal;
	}
	
	public int incrementCardsReal(){
		return ++this.cardsCountReal;
	}
}
