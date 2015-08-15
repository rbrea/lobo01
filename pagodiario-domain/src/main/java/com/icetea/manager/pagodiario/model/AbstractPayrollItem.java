package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class AbstractPayrollItem extends Identifiable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "PAYROLL_ID")
	private Payroll payroll;
	
	@OneToOne(cascade = CascadeType.ALL)
	private BonusConciliationItem bonusItem;
	@Column(name = "SUBTOTAL_COLLECT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal subtotalCollect = BigDecimal.ZERO;
	@Column(name = "SUBTOTAL_DISCOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal subtotalDiscount = BigDecimal.ZERO;
	@Column(name = "TOTAL_AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal totalAmount = BigDecimal.ZERO;
	@Column(name = "ITEM_DATE", columnDefinition = "DATETIME", nullable = false)
	private Date itemDate;
	
	public AbstractPayrollItem() {
		super();
	}
	
	public AbstractPayrollItem(String createdBy) {
		super(createdBy);
	}

	public Payroll getPayroll() {
		return payroll;
	}

	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}

	public Date getItemDate() {
		return itemDate;
	}

	public void setItemDate(Date itemDate) {
		this.itemDate = itemDate;
	}

	public BonusConciliationItem getBonusItem() {
		return bonusItem;
	}

	public void setBonusItem(BonusConciliationItem bonusItem) {
		this.bonusItem = bonusItem;
	}

	public BigDecimal getSubtotalCollect() {
		return subtotalCollect;
	}

	public void setSubtotalCollect(BigDecimal subtotalCollect) {
		this.subtotalCollect = subtotalCollect;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getSubtotalDiscount() {
		return subtotalDiscount;
	}

	public void setSubtotalDiscount(BigDecimal subtotalDiscount) {
		this.subtotalDiscount = subtotalDiscount;
	}

}
