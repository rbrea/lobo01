package com.icetea.manager.pagodiario.api.dto.chart;

import java.math.BigDecimal;

import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class CollectorChartDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private String label;
	private BigDecimal value = BigDecimal.ZERO;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
