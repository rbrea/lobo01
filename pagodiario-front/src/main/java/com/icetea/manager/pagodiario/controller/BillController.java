package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.service.BillService;

@Controller
@RequestMapping(value = "/html/bill")
public class BillController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(BillController.class);
	
	@Inject
	private BillService billService;
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showClientForm(){
		
		return "bill";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<BillDto> getBills(@RequestParam(required = false) Long id){
		ListOutputDto<BillDto> r = new ListOutputDto<BillDto>();

		List<BillDto> list = Lists.newArrayList();
		
		if(id == null){
			list = this.billService.searchAll();
		} else {
			list.add(this.billService.searchById(id));
		}
		
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<BillDto> addBill(@RequestBody BillDto input){
		ListOutputDto<BillDto> r = new ListOutputDto<BillDto>();

		List<BillDto> list = Lists.newArrayList();
		BillDto client = null;
		if(input.getId() != null){
			client = this.billService.update(input);
			list.add(client);
		} else {
			client = this.billService.insert(input);
			list.add(client);
		}
		r.setData(list);
		
		return r;
	}
	
}
