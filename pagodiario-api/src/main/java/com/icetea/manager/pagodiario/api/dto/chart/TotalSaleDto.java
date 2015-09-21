package com.icetea.manager.pagodiario.api.dto.chart;

import java.math.BigDecimal;

import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class TotalSaleDto extends BasicDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String periodo;
	private BigDecimal value = BigDecimal.ZERO;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
