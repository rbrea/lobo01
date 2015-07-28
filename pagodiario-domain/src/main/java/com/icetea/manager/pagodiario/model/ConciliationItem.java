package com.icetea.manager.pagodiario.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "CONCILIATION_ITEM", 
	uniqueConstraints = {@UniqueConstraint(name = "IDX_CONCILIATION_ITEM", 
		columnNames = {"PAYROLL_ITEM_ID", "BILL_ID"})})
@Audited
public class ConciliationItem extends AbstractConciliationItem {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "BILL_ID")
	private Bill bill;
	
	@SuppressWarnings("unused")
	private ConciliationItem(){
		this(Type.CREDIT);
	}

	public ConciliationItem(Type type) {
		super(type);
	}

	public ConciliationItem(String createdBy, Type type) {
		super(createdBy, type);
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
}
