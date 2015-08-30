package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "SUPERVISOR_CONCILIATION_ITEM")
@Audited
public class SupervisorConciliationItem extends Identifiable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "TRADER_ID")
	private Trader trader;
	@Column(name = "CREDIT_AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal creditAmount = BigDecimal.ZERO;
	@Column(name = "DEV_AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal devAmount = BigDecimal.ZERO;
	@Column(name = "BONUS_AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal bonusAmount = BigDecimal.ZERO;
	@Column(name = "REDUCTION_AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal reductionAmount = BigDecimal.ZERO;
	@Column(name = "TOTAL_TRADER", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal totalTrader = BigDecimal.ZERO;
	@ManyToOne
	@JoinColumn(name = "SUPERVISOR_PAYROLL_ITEM_ID")
	private SupervisorPayrollItem supervisorPayrollItem;
	
	@SuppressWarnings("unused")
	private SupervisorConciliationItem() {
		super();
	}

	public SupervisorConciliationItem(Trader trader) {
		super();
		this.trader = trader;
	}
	
	public SupervisorConciliationItem(String createdBy, Trader trader) {
		super(createdBy);
		this.trader = trader;
	}

	public Trader getTrader() {
		return trader;
	}
	public void setTrader(Trader trader) {
		this.trader = trader;
	}
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}
	public BigDecimal getDevAmount() {
		return devAmount;
	}
	public void setDevAmount(BigDecimal devAmount) {
		this.devAmount = devAmount;
	}
	public BigDecimal getBonusAmount() {
		return bonusAmount;
	}
	public void setBonusAmount(BigDecimal bonusAmount) {
		this.bonusAmount = bonusAmount;
	}
	public BigDecimal getReductionAmount() {
		return reductionAmount;
	}
	public void setReductionAmount(BigDecimal reductionAmount) {
		this.reductionAmount = reductionAmount;
	}
	public BigDecimal getTotalTrader() {
		return totalTrader;
	}
	public void setTotalTrader(BigDecimal totalTrader) {
		this.totalTrader = totalTrader;
	}

	public SupervisorPayrollItem getSupervisorPayrollItem() {
		return supervisorPayrollItem;
	}

	public void setSupervisorPayrollItem(SupervisorPayrollItem supervisorPayrollItem) {
		this.supervisorPayrollItem = supervisorPayrollItem;
	}
	
}
