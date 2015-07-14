package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "BILL_PRODUCT")
@Audited
public class BillProduct extends Identifiable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Product product;
	@ManyToOne
	private Bill bill;
	@Column(name = "COUNT", nullable = false)
	private Integer count = 0;
	@Column(name = "AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal amount; // precio total = cant * precio unitario
	@Column(name = "DAILY_INSTALLMENT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal dailyInstallment; // couta_diaria

	public BillProduct() {
		super();
	}
	
	public BillProduct(String createdBy) {
		super(createdBy);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public BigDecimal getDailyInstallment() {
		return dailyInstallment;
	}

	public void setDailyInstallment(BigDecimal dailyInstallment) {
		this.dailyInstallment = dailyInstallment;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
