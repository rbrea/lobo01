package com.icetea.manager.pagodiario.api.dto.chart;

import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class ChartTraderSalesWeekDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private String label;
	private List<List<Object>> data = Lists.newArrayList();

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<List<Object>> getData() {
		return data;
	}
	public void setData(List<List<Object>> data) {
		this.data = data;
	}

	public void addData(List<Object> data){
		if(data != null){
			this.data.add(data);
		}
	}
	
}
