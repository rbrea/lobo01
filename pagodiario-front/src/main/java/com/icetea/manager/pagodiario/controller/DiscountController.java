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
import com.icetea.manager.pagodiario.api.dto.DiscountDto;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.service.DiscountService;

@Controller
@RequestMapping(value = "/html/discount")
public class DiscountController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(DiscountController.class);
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@Inject
	private DiscountService discountService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm(){
		
		return "discount";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<DiscountDto> get(@RequestParam(required = false) Long id,
			@RequestParam(required = false) Long billId){
		ListOutputDto<DiscountDto> r = new ListOutputDto<DiscountDto>();

		List<DiscountDto> list = Lists.newArrayList();
		
		if(billId != null){
			list = this.discountService.search(billId);
		} else if(id != null){
			list.add(this.discountService.searchById(id));
		} else {
			list = this.discountService.searchAll();
		}
		
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<DiscountDto> add(@RequestBody DiscountDto input){
		ListOutputDto<DiscountDto> r = new ListOutputDto<DiscountDto>();

		List<DiscountDto> list = Lists.newArrayList();
		DiscountDto client = null;
		if(input.getId() != null){
			client = this.discountService.update(input);
			list.add(client);
		} else {
			client = this.discountService.insert(input);
			list.add(client);
		}
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody BasicOutputDto delete(@PathVariable Long id){
		BasicOutputDto r = new BasicOutputDto();

		this.discountService.remove(id);
		
		return r;
	}
	
}
