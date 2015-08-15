package com.icetea.manager.pagodiario.model;

import java.util.List;

import javax.persistence.CascadeType;
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

}
