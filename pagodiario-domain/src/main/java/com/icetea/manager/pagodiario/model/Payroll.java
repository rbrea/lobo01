package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.google.common.collect.Lists;

@Entity
@Table(name = "PAYROLL")
@Audited
public class Payroll extends Identifiable {

	private static final long serialVersionUID = 1L;
	
	public static enum Status {
		INITIALIZED, FINISHED
	}
	
	@Column(name = "DATE_FROM", columnDefinition = "DATETIME", nullable = false)
	private Date dateFrom;
	@Column(name = "DATE_TO", columnDefinition = "DATETIME", nullable = false)
	private Date dateTo;
	@Column(name = "TOTAL_AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal totalAmount = BigDecimal.ZERO;
	@Column(name = "TOTAL_DISCOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal totalDiscount = BigDecimal.ZERO;
	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status = Status.INITIALIZED;
	@OneToMany(cascade = CascadeType.ALL)
	private List<PayrollItem> payrollItemList = Lists.newArrayList();
	@Column(name = "TOTAL", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal total = BigDecimal.ZERO;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<SupervisorPayrollItem> supervisorPayrollItemList = Lists.newArrayList();
	@Column(name = "TOTAL_SUPERVISOR", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal totalSupervisor = BigDecimal.ZERO;

	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(BigDecimal totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public List<PayrollItem> getPayrollItemList() {
		return payrollItemList;
	}
	public void setPayrollItemList(List<PayrollItem> payrollItemList) {
		this.payrollItemList = payrollItemList;
	}

	public boolean addPayrollItem(PayrollItem payrollItem){
		if(payrollItem == null){
			return false;
		}
		return this.payrollItemList.add(payrollItem);
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public List<SupervisorPayrollItem> getSupervisorPayrollItemList() {
		return supervisorPayrollItemList;
	}
	public void setSupervisorPayrollItemList(
			List<SupervisorPayrollItem> supervisorPayrollItemList) {
		this.supervisorPayrollItemList = supervisorPayrollItemList;
	}

	public void addSupervisorPayrollItem(SupervisorPayrollItem supervisorPayrollItem){
		if(supervisorPayrollItem == null){
			this.supervisorPayrollItemList.add(supervisorPayrollItem);
		}
	}
	public BigDecimal getTotalSupervisor() {
		return totalSupervisor;
	}
	public void setTotalSupervisor(BigDecimal totalSupervisor) {
		this.totalSupervisor = totalSupervisor;
	}
	
}
