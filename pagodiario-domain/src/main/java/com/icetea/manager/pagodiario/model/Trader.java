package com.icetea.manager.pagodiario.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.google.common.collect.Lists;

@Entity
@Table(name = "TRADER")
@Audited
public class Trader extends Person {

	private static final long serialVersionUID = 1L;

	@Column(name = "IS_SUPERVISOR")
	private boolean isSupervisor = false;
	@ManyToOne
	private Trader supervisor;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Trader> traders = Lists.newArrayList();

	public Trader() {
		super();
	}

	public Trader(String createdBy) {
		super(createdBy);
	}
	
	public boolean isSupervisor() {
		return isSupervisor;
	}
	public void setSupervisor(boolean isSupervisor) {
		this.isSupervisor = isSupervisor;
	}
	public Trader getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(Trader supervisor) {
		this.supervisor = supervisor;
	}
	public List<Trader> getTraders() {
		return traders;
	}
	public void setTraders(List<Trader> traders) {
		this.traders = traders;
	}
	
}
