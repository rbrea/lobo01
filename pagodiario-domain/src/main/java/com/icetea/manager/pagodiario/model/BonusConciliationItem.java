package com.icetea.manager.pagodiario.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "BONUS_CONCILIATION_ITEM", 
	uniqueConstraints = {@UniqueConstraint(name = "IDX_BONUS_CONCILIATION_ITEM", 
	columnNames = {"PAYROLL_ITEM_ID"})})
@Audited
public class BonusConciliationItem extends AbstractConciliationItem {

	private static final long serialVersionUID = 1L;

	public BonusConciliationItem() {
		super(Type.BONUS);
	}

	public BonusConciliationItem(String createdBy) {
		super(createdBy, Type.BONUS);
	}

}
