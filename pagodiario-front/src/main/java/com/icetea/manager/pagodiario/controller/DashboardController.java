package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.api.dto.chart.CollectorChartDto;
import com.icetea.manager.pagodiario.api.dto.chart.TopTraderDto;
import com.icetea.manager.pagodiario.service.chart.ChartService;

@Controller
@RequestMapping(value = "/html/dashboard")
public class DashboardController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(DashboardController.class);
	
	@Inject
	private ChartService chartService;
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showDashboardForm(){
		
		return "dashboard";
	}

	@RequestMapping(value = "/toptraders", method = RequestMethod.GET)
	public ListOutputDto<TopTraderDto> getTopTraders(){
		ListOutputDto<TopTraderDto> r = new ListOutputDto<TopTraderDto>();
		
		List<TopTraderDto> list = this.chartService.searchTopTraders();
		
		r.addAll(list);
		
		return r;
	}
	
	@RequestMapping(value = "/topcollectors", method = RequestMethod.GET)
	public ListOutputDto<CollectorChartDto> getTopCollectors(){
		ListOutputDto<CollectorChartDto> r = new ListOutputDto<CollectorChartDto>();
		
		List<CollectorChartDto> list = this.chartService.searchTopCollectors();
		
		r.addAll(list);
		
		return r;
	}
	
}
