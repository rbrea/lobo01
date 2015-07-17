package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "PAYMENT")
@Audited
public class Payment extends Identifiable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Bill bill;
	@Column(name = "AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal amount = BigDecimal.ZERO;
	@Column(name = "PAYMENT_DATE", columnDefinition = "DATETIME", nullable = false)
	private Date date;
	@Column(name = "COLLECTOR_ID")
	private Integer collectorId; // FIXME: [roher] esto deberia ser una entidad "COBRADOR" ???
	
	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
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

	public Integer getCollectorId() {
		return collectorId;
	}

	public void setCollectorId(Integer collectorId) {
		this.collectorId = collectorId;
	}
	
}
