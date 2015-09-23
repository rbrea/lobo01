package com.icetea.manager.pagodiario.service.chart;

import com.icetea.manager.pagodiario.api.dto.chart.ChartDto;
import com.icetea.manager.pagodiario.service.BasicService;

public interface ChartService extends BasicService {

	ChartDto search();

}
