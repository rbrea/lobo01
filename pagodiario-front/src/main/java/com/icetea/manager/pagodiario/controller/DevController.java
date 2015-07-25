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
import com.icetea.manager.pagodiario.api.dto.DevDto;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.service.DevService;

@Controller
@RequestMapping(value = "/html/dev")
public class DevController extends ExceptionHandlingController {
	
	private static final Logger LOGGER = getLogger(DevController.class);
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@Inject
	private DevService devService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm(){
		
		return "dev";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<DevDto> get(@RequestParam(required = false) Long id,
			@RequestParam(required = false) Long billId){
		ListOutputDto<DevDto> r = new ListOutputDto<DevDto>();

		List<DevDto> list = Lists.newArrayList();
		
		if(billId != null){
			list = this.devService.search(billId);
		} else if(id != null){
			list.add(this.devService.searchById(id));
		} else {
			list = this.devService.searchAll();
		}
		
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<DevDto> add(@RequestBody DevDto input){
		ListOutputDto<DevDto> r = new ListOutputDto<DevDto>();

		List<DevDto> list = Lists.newArrayList();
		DevDto client = null;
		if(input.getId() != null){
			client = this.devService.update(input);
			list.add(client);
		} else {
			client = this.devService.insert(input);
			list.add(client);
		}
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody BasicOutputDto delete(@PathVariable Long id){
		BasicOutputDto r = new BasicOutputDto();

		this.devService.remove(id);
		
		return r;
	}
	
}
