package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BasicOutputDto;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.api.dto.PayrollCollectDto;
import com.icetea.manager.pagodiario.service.PayrollCollectService;

@Controller
@RequestMapping(value = "/html/payrollcollect")
public class PayrollCollectController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(PayrollCollectController.class);
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}

	@Inject
	private PayrollCollectService payrollCollectService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm(){
		
		return "payrollCollect";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<PayrollCollectDto> get(@RequestParam(required = false) Long id){
		ListOutputDto<PayrollCollectDto> r = new ListOutputDto<PayrollCollectDto>();

		List<PayrollCollectDto> list = Lists.newArrayList();
		
		if(id != null){
			list.add(this.payrollCollectService.searchById(id));
		} else {
			list = this.payrollCollectService.searchAll();
		}
		
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<PayrollCollectDto> add(@RequestBody PayrollCollectDto input){
		ListOutputDto<PayrollCollectDto> r = new ListOutputDto<PayrollCollectDto>();

		List<PayrollCollectDto> list = Lists.newArrayList();
		PayrollCollectDto d = null;
		if(input.getId() != null){
			d = this.payrollCollectService.update(input);
			list.add(d);
		} else {
			d = this.payrollCollectService.insert(input);
			list.add(d);
		}
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "/processperiod", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<PayrollCollectDto> processPeriod(@RequestBody PayrollCollectDto input){
		ListOutputDto<PayrollCollectDto> r = new ListOutputDto<PayrollCollectDto>();

		List<PayrollCollectDto> list = Lists.newArrayList();
		
		PayrollCollectDto p = this.payrollCollectService.processPayroll(input.getPayrollDate());
		
		list.add(p);
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody BasicOutputDto delete(@PathVariable Long id){
		BasicOutputDto r = new BasicOutputDto();

		this.payrollCollectService.remove(id);
		
		return r;
	}

	@RequestMapping(value = "/undoliq/{id}", method = RequestMethod.POST)
	public @ResponseBody BasicOutputDto undoPayroll(@PathVariable Long id){
		BasicOutputDto r = new BasicOutputDto();
		
		this.payrollCollectService.remove(id);
		
		return r;
	}
	
	@RequestMapping(value = "/commitliq/{id}", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<PayrollCollectDto> commitPayroll(@PathVariable Long id){
		ListOutputDto<PayrollCollectDto> r = new ListOutputDto<PayrollCollectDto>();
		
		PayrollCollectDto p = this.payrollCollectService.commitPayroll(id);
		if(p != null){
			r.add(p);
		}
		
		return r;
	}
	
}
