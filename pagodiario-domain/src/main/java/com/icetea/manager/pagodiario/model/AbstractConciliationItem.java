package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractConciliationItem extends Identifiable {

	private static final long serialVersionUID = 1L;
	
	public static enum Type {
		CREDIT, BONUS, DEVOLUTION, REDUCTION
	}

	@Column(name = "CONCILIATION_ITEM_DATE", columnDefinition = "DATETIME", nullable = false)
	private Date date;
	@Column(name = "COLLECT_AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE)
	private BigDecimal collectAmount = BigDecimal.ZERO;
	@Column(name = "DISCOUNT_AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE)
	private BigDecimal discountAmount = BigDecimal.ZERO;
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	@ManyToOne
	@JoinColumn(name = "PAYROLL_ITEM_ID")
	private PayrollItem payrollItem;
	@Column(name = "TYPE", length = 30, nullable = false)
	@Enumerated(EnumType.STRING)
	private Type type;
	
	public AbstractConciliationItem(Type type) {
		super();
		this.type = type;
	}

	public AbstractConciliationItem(String createdBy, Type type) {
		super(createdBy);
		this.type = type;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getCollectAmount() {
		return collectAmount;
	}
	public void setCollectAmount(BigDecimal collectAmount) {
		this.collectAmount = collectAmount;
	}
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public PayrollItem getPayrollItem() {
		return payrollItem;
	}
	public void setPayrollItem(PayrollItem payrollItem) {
		this.payrollItem = payrollItem;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
}
