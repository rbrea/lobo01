package com.icetea.manager.pagodiario.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import com.google.common.collect.Lists;

@Entity
@Table(name = "trader")
public class Trader extends Person {

	private static final long serialVersionUID = 1L;

	@Column(name = "SUPERVISOR")
	private boolean supervisor = false;
	@ManyToOne
	private Trader parent;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Trader> traders = Lists.newArrayList();

	public Trader() {
		super();
	}

	public Trader(String createdBy) {
		super(createdBy);
	}
	
	public List<Trader> getTraders() {
		return traders;
	}
	public void setTraders(List<Trader> traders) {
		this.traders = traders;
	}

	public boolean isSupervisor() {
		return supervisor;
	}

	public void setSupervisor(boolean supervisor) {
		this.supervisor = supervisor;
	}

	public Trader getParent() {
		return parent;
	}

	public void setParent(Trader parent) {
		this.parent = parent;
	}

	public boolean removeTrader(final Long id){
		Trader trader = CollectionUtils.find(this.getTraders(), new Predicate<Trader>() {
			@Override
			public boolean evaluate(Trader t) {
				return t.getId().equals(id);
			}
		});
		if(trader != null){
			return this.getTraders().remove(trader);
		}
		
		return false;
	}
	
	public boolean addTrader(final Trader trader){
		if(trader == null){
			return false;
		}
		Trader found = CollectionUtils.find(this.getTraders(), new Predicate<Trader>() {
			@Override
			public boolean evaluate(Trader t) {
				return t.getId().equals(trader.getId());
			}
		});
		if(found == null){
			return this.getTraders().add(trader);
		}
		
		return false;
	}
	
}
