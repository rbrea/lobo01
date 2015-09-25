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
import com.icetea.manager.pagodiario.api.dto.PayrollCollectDto;
import com.icetea.manager.pagodiario.api.dto.PayrollItemCollectDto;
import com.icetea.manager.pagodiario.service.PayrollCollectService;

@Controller
@RequestMapping(value = "/html/payrollcollectitem")
public class PayrollItemCollectController extends ExceptionHandlingController {

private static final Logger LOGGER = getLogger(PayrollItemCollectController.class);
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@Inject
	private PayrollCollectService payrollCollectService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm(@RequestParam(required = false) Long payrollId, ModelMap modelMap){
		modelMap.addAttribute("payrollId", payrollId);
		PayrollCollectDto p = this.payrollCollectService.searchById(payrollId);
		if(p != null){
			modelMap.addAttribute("totalCards", p.getTotalCards());
			modelMap.addAttribute("totalToCollect", p.getTotalAmount());
			modelMap.addAttribute("totalCollectedGross", p.getTotalPayment());
			modelMap.addAttribute("totalCommission", p.getTotalAmountToPay());
			modelMap.addAttribute("totalCollectedNet", p.getTotalToCollect());
		}
		
		return "payrollCollectItem";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<PayrollItemCollectDto> get(@RequestParam(required = false) Long id){
		ListOutputDto<PayrollItemCollectDto> r = new ListOutputDto<PayrollItemCollectDto>();

		List<PayrollItemCollectDto> list = Lists.newArrayList();

		PayrollCollectDto p = this.payrollCollectService.searchById(id);
		if(p != null){
			list.addAll(p.getItems());
		}
		
		r.setData(list);
		
		return r;
	}

}
