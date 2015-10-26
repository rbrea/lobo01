package com.icetea.manager.pagodiario.service.chart;

import java.util.List;

import com.icetea.manager.pagodiario.api.dto.chart.ChartDto;
import com.icetea.manager.pagodiario.api.dto.chart.ChartTraderSalesWeekDto;
import com.icetea.manager.pagodiario.api.dto.chart.CollectorChartDto;
import com.icetea.manager.pagodiario.api.dto.chart.TopTraderDto;
import com.icetea.manager.pagodiario.service.BasicService;

public interface ChartService extends BasicService {

	ChartDto search();

	List<ChartTraderSalesWeekDto> searchTraderBillsByWeek();

	List<TopTraderDto> searchTopTraders();

	List<CollectorChartDto> searchTopCollectors();

}
