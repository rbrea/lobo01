package com.icetea.manager.pagodiario.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.icetea.manager.pagodiario.utils.NumberUtils;

@Entity
@Table(name = "PRODUCT", 
	indexes = {@Index(name = "IDX_PRODUCT", columnList = "CODE", unique = true)})
@Audited
public class Product extends Identifiable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "CODE", length = 40, unique = true)
	private String code;
	@Column(name = "DESCRIPTION", length = 140)
	private String description;
	@Column(name = "PRICE", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE)
	private BigDecimal price;
	@Column(name = "DAILY_INSTALLMENT", precision = BIG_DECIMAL_PRECISION, scale = BIG_DECIMAL_SCALE)
	private BigDecimal dailyInstallment;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getDailyInstallment() {
		return dailyInstallment;
	}
	public void setDailyInstallment(BigDecimal dailyInstallment) {
		this.dailyInstallment = dailyInstallment;
	}

	public BigDecimal weekInstallment(){
		return NumberUtils.multiply(this.dailyInstallment, new BigDecimal(7));
	}
	
	public BigDecimal twoWeeksInstallment(){
		return NumberUtils.multiply(this.dailyInstallment, new BigDecimal(14));
	}
	
	public BigDecimal priceWithDiscount(){
		BigDecimal perc = NumberUtils.calculatePercentage(this.price, new BigDecimal(20));
		
		return NumberUtils.subtract(this.price, perc);
	}
	
}
