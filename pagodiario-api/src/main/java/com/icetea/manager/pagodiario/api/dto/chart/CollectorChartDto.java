package com.icetea.manager.pagodiario.api.dto.chart;

import java.math.BigDecimal;

import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class CollectorChartDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private Long collectorId;
	private String label;
	private BigDecimal data = BigDecimal.ZERO;
	private String color;
	private BigDecimal totalAmount = BigDecimal.ZERO;
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public BigDecimal getData() {
		return data;
	}

	public void setData(BigDecimal data) {
		this.data = data;
	}

	public Long getCollectorId() {
		return collectorId;
	}

	public void setCollectorId(Long collectorId) {
		this.collectorId = collectorId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

}
