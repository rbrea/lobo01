package com.icetea.manager.pagodiario.api.dto.chart;

import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class ChartDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private List<TotalSaleDto> totalSales = Lists.newArrayList();

	private List<TopTraderDto> topTraders = Lists.newArrayList();

	private List<CollectorChartDto> topCollectors = Lists.newArrayList();

	public List<TotalSaleDto> getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(List<TotalSaleDto> totalSales) {
		this.totalSales = totalSales;
	}

	public List<TopTraderDto> getTopTraders() {
		return topTraders;
	}

	public void setTopTraders(List<TopTraderDto> topTraders) {
		this.topTraders = topTraders;
	}

	public List<CollectorChartDto> getTopCollectors() {
		return topCollectors;
	}

	public void setTopCollectors(List<CollectorChartDto> topCollectors) {
		this.topCollectors = topCollectors;
	}

}
