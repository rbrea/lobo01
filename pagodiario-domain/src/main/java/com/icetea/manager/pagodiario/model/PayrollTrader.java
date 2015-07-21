package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "PAYROLL_TRADER", 
	uniqueConstraints = {@UniqueConstraint(name = "IDX_LIQ_VEND", 
		columnNames = {"PAYROLL_ID", "TRADER_ID", "BILL_ID"})})
@Audited
public class PayrollTrader extends Identifiable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "PAYROLL_ID")
	private Payroll payroll;
	@ManyToOne(optional = false)
	@JoinColumn(name = "TRADER_ID")
	private Trader trader;
	@ManyToOne(optional = false)
	@JoinColumn(name = "BILL_ID")
	private Bill bill;
	@Column(name = "COMMISSION", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal commission = BigDecimal.ZERO; // 10%
	@Column(name = "DISCOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal discount = BigDecimal.ZERO;
	@Column(name = "BONUS", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal bonus = BigDecimal.ZERO;
	
	public PayrollTrader() {
		super();
	}

	public PayrollTrader(String createdBy) {
		super(createdBy);
	}

	public Payroll getPayroll() {
		return payroll;
	}

	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}

	public Trader getTrader() {
		return trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}
	
}
