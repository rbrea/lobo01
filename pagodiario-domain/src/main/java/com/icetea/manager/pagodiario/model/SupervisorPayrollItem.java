package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.envers.Audited;

import com.google.common.collect.Lists;

@Entity
@Table(name = "SUPERVISOR_PAYROLL_ITEM", 
	uniqueConstraints = {@UniqueConstraint(name = "IDX_LIQ_SUPERVISOR", 
		columnNames = {"PAYROLL_ID", "SUPERVISOR_ID"})})
@Audited
public class SupervisorPayrollItem extends AbstractPayrollItem {

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false)
	@JoinColumn(name = "SUPERVISOR_ID")
	private Trader supervisor;
	@OneToMany(cascade = CascadeType.ALL)
	private List<SupervisorConciliationItem> supervisorConciliationItems = Lists.newArrayList();
	@Column(name = "SUBTOTAL_BONUS", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal subtotalBonus = BigDecimal.ZERO;
	@Column(name = "SUBTOTAL_DEV", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal subtotalDev = BigDecimal.ZERO;
	@Column(name = "SUBTOTAL_REDUCTION", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal subtotalReduction = BigDecimal.ZERO;

	public Trader getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Trader supervisor) {
		this.supervisor = supervisor;
	}

	public List<SupervisorConciliationItem> getSupervisorConciliationItems() {
		return supervisorConciliationItems;
	}

	public void setSupervisorConciliationItems(
			List<SupervisorConciliationItem> supervisorConciliationItems) {
		this.supervisorConciliationItems = supervisorConciliationItems;
	}

	public void addItem(SupervisorConciliationItem supervisorConciliationItem){
		if(supervisorConciliationItem != null){
			this.supervisorConciliationItems.add(supervisorConciliationItem);
		}
	}

	public BigDecimal getSubtotalBonus() {
		return subtotalBonus;
	}

	public void setSubtotalBonus(BigDecimal subtotalBonus) {
		this.subtotalBonus = subtotalBonus;
	}

	public BigDecimal getSubtotalDev() {
		return subtotalDev;
	}

	public void setSubtotalDev(BigDecimal subtotalDev) {
		this.subtotalDev = subtotalDev;
	}

	public BigDecimal getSubtotalReduction() {
		return subtotalReduction;
	}

	public void setSubtotalReduction(BigDecimal subtotalReduction) {
		this.subtotalReduction = subtotalReduction;
	}

}
