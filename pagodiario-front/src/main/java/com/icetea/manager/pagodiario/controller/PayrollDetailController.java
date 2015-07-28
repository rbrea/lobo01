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
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.api.dto.PayrollDetailDto;
import com.icetea.manager.pagodiario.service.PayrollService;

@Controller
@RequestMapping(value = "/html/payrollDetail")
public class PayrollDetailController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(PayrollDetailController.class);
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@Inject
	private PayrollService payrollService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm(@RequestParam(required = false) Long payrollId, ModelMap modelMap){
		modelMap.addAttribute("payrollId", payrollId);
		
		return "payrollDetail";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<PayrollDetailDto> get(@RequestParam(required = false) Long payrollId){
		ListOutputDto<PayrollDetailDto> r = new ListOutputDto<PayrollDetailDto>();

		List<PayrollDetailDto> list = Lists.newArrayList();

		list = this.payrollService.searchDetail(payrollId);
		
		r.setData(list);
		
		return r;
	}

}
