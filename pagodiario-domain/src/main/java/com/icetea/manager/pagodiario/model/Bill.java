package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.envers.Audited;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Entity
@Table(name = "BILL")
@Audited
public class Bill extends Identifiable {

	private static final long serialVersionUID = 1L;

	public static enum Status {
		INITIALIZED, ACTIVE, COMPLETED, FINALIZED, CANCELED;
		
		public static Status getValueOf(String name){
			for(Status s : Status.values()){
				if(StringUtils.equalsIgnoreCase(s.name(), name)){
					return s;
				}
			}
			
			return null;
		}
		
	}
	
	@ManyToOne
	private Trader trader;
	@ManyToOne
	private Client client;
	@OneToMany(cascade = CascadeType.ALL)
	private List<BillProduct> billProducts = Lists.newArrayList();
	@Column(name = "TOTAL_DAILY_INSTALLMENT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal totalDailyInstallment = BigDecimal.ZERO;
	@Column(name = "TOTAL_AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE, nullable = false)
	private BigDecimal totalAmount = BigDecimal.ZERO;
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
	@ManyToOne
	@JoinColumn(name = "COLLECTOR_ID")
	private Collector collector;
	@Column(name = "CREDIT_NUMBER", nullable = false, unique = true)
	private Long creditNumber;
	@Column(name = "REMAINING_AMOUNT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE)
	private BigDecimal remainingAmount = BigDecimal.ZERO; // DATO CALCULADO
	@OneToMany(cascade = CascadeType.ALL)
	private List<Discount> discounts = Lists.newArrayList();
	@OneToMany(cascade = CascadeType.ALL)
	private List<Bonus> bonusList = Lists.newArrayList();
	@OneToMany(cascade = CascadeType.ALL)
	private List<ProductReduction> productReductionList = Lists.newArrayList();
	@OneToMany(cascade = CascadeType.ALL)
	private List<Dev> devList = Lists.newArrayList();
	@Column(name = "OVERDUE_DAYS_FLAG", columnDefinition = "DATETIME")
	private Date overdueDaysFlag = new Date();
	@Column(name = "COMPLETED_DATE", columnDefinition = "DATETIME")
	private Date completedDate;
	@Column(name = "WEEK_OF_YEAR")
	private Integer weekOfYear;
	@Column(name = "MONTH")
	private Integer month;
	@Column(name = "YEAR")
	private Integer year;
	
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
	
	public BigDecimal calculateTotalAmount(){
		
		BigDecimal sum = BigDecimal.ZERO;
		
		if(this.billProducts == null){
			return BigDecimal.ZERO;
		}
		for(BillProduct b : this.billProducts){
			sum = NumberUtils.add(sum, b.getAmount());
		}
		
		return sum;
	}

	public BigDecimal calculateTotalDailyInstallment(){
		
		BigDecimal sum = BigDecimal.ZERO;
		
		if(this.billProducts == null){
			return BigDecimal.ZERO;
		}
		for(BillProduct b : this.billProducts){
			sum = NumberUtils.add(sum, b.getDailyInstallment());
		}
		
		return sum;
	}
	
	public Date calculateEndDate(){
		
		BigDecimal result = this.totalAmount.divide(this.totalDailyInstallment, RoundingMode.UP);
		int days = result.intValue();
		
		Calendar c = Calendar.getInstance();
		c.setTime(this.startDate);
		c.add(Calendar.DATE, days);
		
		return c.getTime();
	}

	public Long getCreditNumber() {
		return creditNumber;
	}

	public void setCreditNumber(Long creditNumber) {
		this.creditNumber = creditNumber;
	}

	public BigDecimal getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(BigDecimal remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public void addPayment(Payment payment){
		if(this.payments == null){
			this.payments = Lists.newArrayList();
		}
		this.payments.add(payment);
	}

	public void incrementOverdueDays(){
		this.overdueDays++;
		this.overdueDaysFlag = new Date();
	}
	
	public void decrementOverdueDays(){
		this.decrementOverdueDays(1);
	}
	
	public void decrementOverdueDays(int days){
		this.overdueDays = this.overdueDays - days;
	}

	public List<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}
	
	public void addDiscount(Discount discount){
		if(discount != null){
			this.discounts.add(discount);
		}
	}

	public List<Bonus> getBonusList() {
		return bonusList;
	}

	public void setBonusList(List<Bonus> bonusList) {
		this.bonusList = bonusList;
	}

	public List<ProductReduction> getProductReductionList() {
		return productReductionList;
	}

	public void setProductReductionList(List<ProductReduction> productReductionList) {
		this.productReductionList = productReductionList;
	}

	public List<Dev> getDevList() {
		return devList;
	}

	public void setDevList(List<Dev> devList) {
		this.devList = devList;
	}

	public int remainingDays(){
		if(this.remainingAmount == null || this.totalDailyInstallment == null){
			return 0;
		}
		
		return this.remainingAmount.divide(this.totalDailyInstallment, RoundingMode.CEILING).intValue();
	}

	public Date getOverdueDaysFlag() {
		return overdueDaysFlag;
	}

	public void setOverdueDaysFlag(Date overdueDaysFlag) {
		this.overdueDaysFlag = overdueDaysFlag;
	}

	public void addDev(Dev dev){
		if(dev != null){
			this.devList.add(dev);
		}
	}
	
	public void addProductReduction(ProductReduction productReduction){
		if(productReduction != null){
			this.productReductionList.add(productReduction);
		}
	}

	public void addBonus(Bonus bonus){
		if(bonus != null){
			this.bonusList.add(bonus);
		}
	}

	public Collector getCollector() {
		return collector;
	}

	public void setCollector(Collector collector) {
		this.collector = collector;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public Integer getWeekOfYear() {
		return weekOfYear;
	}

	public void setWeekOfYear(Integer weekOfYear) {
		this.weekOfYear = weekOfYear;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}
