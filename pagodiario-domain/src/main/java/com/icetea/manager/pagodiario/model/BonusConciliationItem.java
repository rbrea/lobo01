package com.icetea.manager.pagodiario.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "bonus_conciliation_item", 
	uniqueConstraints = {@UniqueConstraint(name = "IDX_BONUS_CONCILIATION_ITEM", 
	columnNames = {"PAYROLL_ITEM_ID"})})
public class BonusConciliationItem extends AbstractConciliationItem {

	private static final long serialVersionUID = 1L;
	
	public static final int BONUS_LIMIT = 240;

	public BonusConciliationItem() {
		super(Type.BONUS);
	}

	public BonusConciliationItem(String createdBy) {
		super(createdBy, Type.BONUS);
	}

}
