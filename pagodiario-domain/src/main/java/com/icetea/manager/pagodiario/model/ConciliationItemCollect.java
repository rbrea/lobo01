package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "CONCILIATION_ITEM_COLLECT")
@Audited
public class ConciliationItemCollect extends Identifiable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "BILL_ID")
	private Bill bill;
	@ManyToOne
	@JoinColumn(name = "PAYROLL_ITEM_COLLECT_ID")
	private PayrollItemCollect payrollItemCollect;
	@Column(name = "AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE)	
	private BigDecimal amount = BigDecimal.ZERO;
	@Column(name = "DESCRIPTION")
	private String description;

	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	public PayrollItemCollect getPayrollItemCollect() {
		return payrollItemCollect;
	}
	public void setPayrollItemCollect(PayrollItemCollect payrollItemCollect) {
		this.payrollItemCollect = payrollItemCollect;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
