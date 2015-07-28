package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.ConciliationItemDto;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.api.dto.PayrollDto;
import com.icetea.manager.pagodiario.service.ConciliationItemService;
import com.icetea.manager.pagodiario.service.PayrollService;

@Controller
@RequestMapping(value = "/html/conciliationItem")
public class ConciliationItemController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(ConciliationItemController.class);
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@Inject
	private ConciliationItemService conciliationItemService;
	@Inject
	private PayrollService payrollService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm(@RequestParam(required = false) Long payrollId, 
			@RequestParam(required = false) Long payrollItemId,
			@RequestParam(required = false) String traderName,
			ModelMap modelMap){
		modelMap.addAttribute("payrollId", payrollId);
		modelMap.addAttribute("payrollItemId", payrollItemId);
		
		PayrollDto payroll = this.payrollService.searchById(payrollId);
		
		modelMap.addAttribute("fromDate", payroll.getFromDate());
		modelMap.addAttribute("toDate", payroll.getToDate());
		modelMap.addAttribute("traderName", traderName);
		
		return "conciliationItem";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<ConciliationItemDto> get(@RequestParam(required = false) Long payrollItemId){
		ListOutputDto<ConciliationItemDto> r = new ListOutputDto<ConciliationItemDto>();

		List<ConciliationItemDto> list = Lists.newArrayList();

		list = this.conciliationItemService.searchByPayrollItemId(payrollItemId);
		
		r.setData(list);
		
		return r;
	}

}
