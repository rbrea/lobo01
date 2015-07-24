package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "PAYROLL_ITEM", 
	uniqueConstraints = {@UniqueConstraint(name = "IDX_LIQ_VEND", 
		columnNames = {"PAYROLL_ID", "TRADER_ID", "BILL_ID"})})
@Audited
public class PayrollItem extends Identifiable {

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
	private BigDecimal discountAmount = BigDecimal.ZERO;
	@Column(name = "BONUS", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal bonusAmount = BigDecimal.ZERO;
	@Column(name = "PRODUCT_REDUCTION", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal productReductionAmount = BigDecimal.ZERO;
	@Column(name = "ITEM_DATE", columnDefinition = "DATETIME", nullable = false)
	private Date itemDate;
	
	public PayrollItem() {
		super();
	}

	public PayrollItem(String createdBy) {
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

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(BigDecimal bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public BigDecimal getProductReductionAmount() {
		return productReductionAmount;
	}

	public void setProductReductionAmount(BigDecimal productReductionAmount) {
		this.productReductionAmount = productReductionAmount;
	}

	public Date getItemDate() {
		return itemDate;
	}

	public void setItemDate(Date itemDate) {
		this.itemDate = itemDate;
	}

}
