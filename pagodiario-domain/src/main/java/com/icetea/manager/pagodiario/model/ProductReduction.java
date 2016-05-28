package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_reduction")
public class ProductReduction extends Identifiable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "OBSERVATIONS", length = 250)
	private String observations;
	@Column(name = "AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal amount;
	@Column(name = "REDUCTION_DATE", columnDefinition = "DATETIME", nullable = false)
	private Date date;
	@OneToOne
	private Bill bill;

	public ProductReduction() {
		super();
	}
	
	public ProductReduction(String createdBy) {
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
