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
import com.icetea.manager.pagodiario.api.dto.TraderDto;
import com.icetea.manager.pagodiario.service.TraderService;

@Controller
public class TraderController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(TraderController.class);
	
	@Inject
	private TraderService traderService;
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm(){
		
		return "trader";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<TraderDto> getList(@RequestParam(required = false) Long id){
		ListOutputDto<TraderDto> r = new ListOutputDto<TraderDto>();

		List<TraderDto> list = Lists.newArrayList();
		
		if(id == null){
			list = this.traderService.searchAll();
		} else {
			list.add(this.traderService.searchById(id));
		}
		
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<TraderDto> add(@RequestBody TraderDto input){
		ListOutputDto<TraderDto> r = new ListOutputDto<TraderDto>();

		List<TraderDto> list = Lists.newArrayList();
		TraderDto dto = null;
		if(input.getId() != null){
			dto = this.traderService.update(input);
			list.add(dto);
		} else {
			dto = this.traderService.insert(input);
			list.add(dto);
		}
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody BasicOutputDto delete(@PathVariable Long id){
		BasicOutputDto r = new BasicOutputDto();

		this.traderService.remove(id);
		
		return r;
	}

}
