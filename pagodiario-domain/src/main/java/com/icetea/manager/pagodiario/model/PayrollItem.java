package com.icetea.manager.pagodiario.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.envers.Audited;

import com.google.common.collect.Lists;

@Entity
@Table(name = "PAYROLL_ITEM", 
	uniqueConstraints = {@UniqueConstraint(name = "IDX_LIQ_VEND", 
		columnNames = {"PAYROLL_ID", "TRADER_ID"})})
@Audited
public class PayrollItem extends Identifiable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "PAYROLL_ID")
	private Payroll payroll;
	@ManyToOne(optional = false)
	@JoinColumn(name = "TRADER_ID")
	private Trader trader;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ConciliationItem> items = Lists.newArrayList();
	@OneToOne(cascade = CascadeType.ALL)
	private BonusConciliationItem bonusItem;
	
//	@Column(name = "COMMISSION", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
//	private BigDecimal commission = BigDecimal.ZERO; // 10%
//	@Column(name = "DISCOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
//	private BigDecimal discountAmount = BigDecimal.ZERO;
//	@Column(name = "BONUS", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
//	private BigDecimal bonusAmount = BigDecimal.ZERO;
//	@Column(name = "PRODUCT_REDUCTION", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
//	private BigDecimal productReductionAmount = BigDecimal.ZERO;
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

	public List<ConciliationItem> getItems() {
		return items;
	}

	public void setItems(List<ConciliationItem> items) {
		this.items = items;
	}

	public Date getItemDate() {
		return itemDate;
	}

	public void setItemDate(Date itemDate) {
		this.itemDate = itemDate;
	}

	public void addItem(ConciliationItem item){
		if(item != null){
			this.items.add(item);
		}
	}

	public BonusConciliationItem getBonusItem() {
		return bonusItem;
	}

	public void setBonusItem(BonusConciliationItem bonusItem) {
		this.bonusItem = bonusItem;
	}

}
