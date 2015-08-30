package com.icetea.manager.pagodiario.jasper.factory.sample;

import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class TestPojo extends BasicDto {

	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
