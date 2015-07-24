package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "BONUS")
@Audited
public class Bonus extends Identifiable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "OBSERVATIONS", length = 250)
	private String observations;
	@Column(name = "AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal amount;
	@Column(name = "BONUS_DATE", columnDefinition = "DATETIME", nullable = false)
	private Date date;
	@ManyToOne
	private Bill bill;

	public Bonus() {
		super();
	}

	public Bonus(String createdBy) {
		super(createdBy);
	}

	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
}
