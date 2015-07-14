package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.google.common.collect.Lists;

@Entity
@Table(name = "BILL")
@Audited
public class Bill extends Identifiable {

	private static final long serialVersionUID = 1L;

	public static enum Status {
		INITIALIZED, ACTIVE, FINALIZED, CANCELED
	}
	
	@ManyToOne
	private Trader trader;
	@ManyToOne
	private Client client;
	@OneToMany(cascade = CascadeType.ALL)
	private List<BillProduct> billProducts = Lists.newArrayList();
	@Column(name = "TOTAL_DAILY_INSTALLMENT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal totalDailyInstallment;
	@Column(name = "TOTAL_AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal totalAmount;
	@Column(name = "OVERDUE_DAYS", nullable = false)
	private Integer overdueDays = 0; // DATO CALCULADO
	@Column(name = "START_DATE", columnDefinition = "DATETIME")
	private Date startDate;
	@Column(name = "END_DATE", columnDefinition = "DATETIME")
	private Date endDate; // DATO CALCULADO
	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status = Status.INITIALIZED;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Payment> payments = Lists.newArrayList();

	public Bill() {
		super();
	}

	public Trader getTrader() {
		return trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<BillProduct> getBillProducts() {
		return billProducts;
	}

	public void setBillProducts(List<BillProduct> billProducts) {
		this.billProducts = billProducts;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BigDecimal getTotalDailyInstallment() {
		return totalDailyInstallment;
	}

	public void setTotalDailyInstallment(BigDecimal totalDailyInstallment) {
		this.totalDailyInstallment = totalDailyInstallment;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public boolean addBillProduct(BillProduct billProduct){
		return this.billProducts.add(billProduct);
	}

	public Integer getOverdueDays() {
		return overdueDays;
	}

	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
}
