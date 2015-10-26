package com.icetea.manager.pagodiario.api.dto.chart;

import java.math.BigDecimal;

import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class TopTraderDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private Long traderId;
	private String nombre;
	private BigDecimal value = BigDecimal.ZERO;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Long getTraderId() {
		return traderId;
	}

	public void setTraderId(Long traderId) {
		this.traderId = traderId;
	}

}
