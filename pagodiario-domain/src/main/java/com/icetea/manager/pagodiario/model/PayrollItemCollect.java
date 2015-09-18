package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Entity
@Table(name = "PAYROLL_ITEM_COLLECT")
public class PayrollItemCollect extends Identifiable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "COLLECTOR_ID")
	private Collector collector;
	@Column(name = "TOTAL_AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE)	
	private BigDecimal totalAmount = BigDecimal.ZERO;
	@Column(name = "CARDS_COUNT", nullable = false)
	private int cardsCount = 0;
	@Column(name = "COMMISSION", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE)	
	private BigDecimal commission = BigDecimal.ZERO;
	@Column(name = "TOTAL_PAYMENT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE)	
	private BigDecimal totalPayment = BigDecimal.ZERO;
	@Column(name = "COMMISSION_PERCENTAGE", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE)	
	private BigDecimal commissionPercentage = BigDecimal.ZERO;
	@Column(name = "AMOUNT_TO_PAY", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE)	
	private BigDecimal amountToPay = BigDecimal.ZERO;
	@ManyToOne
	@JoinColumn(name = "PAYROLL_COLLECT_ID")
	private PayrollCollect payrollCollect;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ConciliationItemCollect> conciliationItemCollectList = Lists.newArrayList();

	public Collector getCollector() {
		return collector;
	}

	public void setCollector(Collector collector) {
		this.collector = collector;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public PayrollCollect getPayrollCollect() {
		return payrollCollect;
	}

	public void setPayrollCollect(PayrollCollect payrollCollect) {
		this.payrollCollect = payrollCollect;
	}

	public List<ConciliationItemCollect> getConciliationItemCollectList() {
		return conciliationItemCollectList;
	}

	public void setConciliationItemCollectList(
			List<ConciliationItemCollect> conciliationItemCollectList) {
		this.conciliationItemCollectList = conciliationItemCollectList;
	}

	public BigDecimal acumTotalAmount(BigDecimal amount){
		
		this.totalAmount = NumberUtils.add(this.totalAmount, amount);
		
		return this.totalAmount;
	}
	
	public BigDecimal acumTotalPayment(BigDecimal amount){
		
		this.totalPayment = NumberUtils.add(this.totalPayment, amount);
		
		return this.totalPayment;
	}
	
	public void addConciliationItemCollect(ConciliationItemCollect conciliationItemCollect){
		if(conciliationItemCollect != null){
			this.conciliationItemCollectList.add(conciliationItemCollect);
		}
	}

	public int getCardsCount() {
		return cardsCount;
	}

	public void setCardsCount(int cardsCount) {
		this.cardsCount = cardsCount;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	
	public int incrementCards(){
		return ++this.cardsCount;
	}

	public BigDecimal getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(BigDecimal totalPayment) {
		this.totalPayment = totalPayment;
	}

	public BigDecimal commissionPercentage(){
		
		BigDecimal commissionPercentage = BigDecimal.ZERO;
		
		final BigDecimal totalAmount = this.getTotalAmount();
		final BigDecimal totalPayment = this.getTotalPayment();
		
		if(NumberUtils.isZero(totalPayment)){
			return BigDecimal.ZERO;
		}
		
		BigDecimal percetage = NumberUtils.multiply(
				NumberUtils.divide(totalPayment, totalAmount), 
					NumberUtils._100);
		
		if(percetage.compareTo(new BigDecimal(80)) <= 0){
			commissionPercentage = new BigDecimal(18); 
		} else if(percetage.compareTo(new BigDecimal(90)) <= 0){
			commissionPercentage = new BigDecimal(19);
		} else {
			commissionPercentage = new BigDecimal(20);
		}
		
		return commissionPercentage;
	}

	public BigDecimal getCommissionPercentage() {
		return commissionPercentage;
	}

	public void setCommissionPercentage(BigDecimal commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}

	public BigDecimal getAmountToPay() {
		return amountToPay;
	}

	public void setAmountToPay(BigDecimal amountToPay) {
		this.amountToPay = amountToPay;
	}

}
