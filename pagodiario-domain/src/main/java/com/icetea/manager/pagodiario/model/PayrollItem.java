package com.icetea.manager.pagodiario.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.google.common.collect.Lists;

@Entity
@Table(name = "payroll_item", 
	uniqueConstraints = {@UniqueConstraint(name = "IDX_LIQ_VEND", 
		columnNames = {"PAYROLL_ID", "TRADER_ID"})})
public class PayrollItem extends AbstractPayrollItem {

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false)
	@JoinColumn(name = "TRADER_ID")
	private Trader trader;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ConciliationItem> items = Lists.newArrayList();
	
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
	
	public void addItem(ConciliationItem item){
		if(item != null){
			this.items.add(item);
		}
	}

}
