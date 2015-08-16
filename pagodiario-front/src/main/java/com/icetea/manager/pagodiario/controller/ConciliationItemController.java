package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
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
import com.icetea.manager.pagodiario.api.dto.SupervisorConciliationItemDto;
import com.icetea.manager.pagodiario.api.dto.SupervisorPayrollItemDto;
import com.icetea.manager.pagodiario.service.ConciliationItemService;
import com.icetea.manager.pagodiario.service.PayrollService;
import com.icetea.manager.pagodiario.service.SupervisorPayrollItemService;
import com.icetea.manager.pagodiario.utils.NumberUtils;

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
	@Inject
	private SupervisorPayrollItemService supervisorPayrollItemService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm(@RequestParam(required = false) Long payrollId, 
			@RequestParam(required = false) Long payrollItemId,
			@RequestParam(required = false) String traderName,
			@RequestParam(required = false) String totalCollect,
			@RequestParam(required = false) String totalDiscount,
			@RequestParam(required = false) String totalTrader,
			ModelMap modelMap){
		modelMap.addAttribute("payrollId", payrollId);
		modelMap.addAttribute("payrollItemId", payrollItemId);
		
		PayrollDto payroll = this.payrollService.searchById(payrollId);
		
		modelMap.addAttribute("fromDate", payroll.getFromDate());
		modelMap.addAttribute("toDate", payroll.getToDate());
		modelMap.addAttribute("traderName", traderName);
		
		modelMap.addAttribute("totalCollect", totalCollect);
		modelMap.addAttribute("totalDiscount", totalDiscount);
		modelMap.addAttribute("totalTrader", totalTrader);
		
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
	
	@RequestMapping(value = "/supervisor/index", method = RequestMethod.GET)
	public String showSupervisorForm(@RequestParam(required = false) Long payrollId, 
			@RequestParam(required = false) Long payrollItemId,
			@RequestParam(required = false) String traderName,
			@RequestParam(required = false) String totalCollect,
			@RequestParam(required = false) String totalDiscount,
			@RequestParam(required = false) String totalTrader,
			ModelMap modelMap){
		modelMap.addAttribute("payrollId", payrollId);
		modelMap.addAttribute("payrollItemId", payrollItemId);
		
		PayrollDto payroll = this.payrollService.searchById(payrollId);
		
		SupervisorPayrollItemDto supervisorPayrollItemDto = this.supervisorPayrollItemService.searchDetail(payrollItemId);
		
		modelMap.addAttribute("fromDate", payroll.getFromDate());
		modelMap.addAttribute("toDate", payroll.getToDate());
		modelMap.addAttribute("supervisorName", supervisorPayrollItemDto.getSupervisorName());
		
		modelMap.addAttribute("totalCredit", (StringUtils.isNotBlank(supervisorPayrollItemDto.getTotalCreditAmount())) 
				? supervisorPayrollItemDto.getTotalCreditAmount() : NumberUtils._ZERO);
		modelMap.addAttribute("totalDev", (StringUtils.isNotBlank(supervisorPayrollItemDto.getTotalDevAmount())) 
				? supervisorPayrollItemDto.getTotalDevAmount() : NumberUtils._ZERO);
		modelMap.addAttribute("totalBonus", (StringUtils.isNotBlank(supervisorPayrollItemDto.getTotalBonusAmount())) 
				? supervisorPayrollItemDto.getTotalBonusAmount() : NumberUtils._ZERO);
		modelMap.addAttribute("totalReduction", (StringUtils.isNotBlank(supervisorPayrollItemDto.getTotalReductionAmount())) 
				? supervisorPayrollItemDto.getTotalReductionAmount() : NumberUtils._ZERO);
		modelMap.addAttribute("totalAmount", (StringUtils.isNotBlank(supervisorPayrollItemDto.getTotalAmount())) 
				? supervisorPayrollItemDto.getTotalAmount() : NumberUtils._ZERO);
		
		return "supervisorConciliationItem";
	}
	
	@RequestMapping(value = "/supervisor", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<SupervisorConciliationItemDto> getSupervisor(@RequestParam(required = false) Long payrollItemId){
		ListOutputDto<SupervisorConciliationItemDto> r = new ListOutputDto<SupervisorConciliationItemDto>();

		List<SupervisorConciliationItemDto> list = Lists.newArrayList();

		SupervisorPayrollItemDto supervisorPayrollItemDto = this.supervisorPayrollItemService.searchDetail(payrollItemId);		
		
		list = supervisorPayrollItemDto.getSupervisorConciliationItemList();
		
		r.setData(list);
		
		return r;
	}

}
