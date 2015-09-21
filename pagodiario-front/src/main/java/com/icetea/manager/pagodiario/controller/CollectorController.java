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
import com.icetea.manager.pagodiario.api.dto.CollectorDto;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.service.CollectorService;

@Controller
@RequestMapping(value = "/html/collector")
public class CollectorController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(CollectorController.class);
	
	@Inject
	private CollectorService collectorService;
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm(){
		
		return "collector";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<CollectorDto> getList(@RequestParam(required = false) Long id,
			@RequestParam(required = false) Long zone){
		ListOutputDto<CollectorDto> r = new ListOutputDto<CollectorDto>();

		List<CollectorDto> list = Lists.newArrayList();
		
		if(zone != null){
			CollectorDto p = this.collectorService.searchByZone(zone);
			if(p != null){
				list.add(p);
			}
		} else if(id != null){
			CollectorDto p = this.collectorService.searchById(id);
			if(p != null){
				list.add(p);
			}
		} else {
			list = this.collectorService.searchAll();			
		}
		
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<CollectorDto> add(@RequestBody CollectorDto input){
		ListOutputDto<CollectorDto> r = new ListOutputDto<CollectorDto>();

		List<CollectorDto> list = Lists.newArrayList();
		CollectorDto c = null;
		if(input.getId() != null){
			c = this.collectorService.update(input);
			list.add(c);
		} else {
			c = this.collectorService.insert(input);
			list.add(c);
		}
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody BasicOutputDto delete(@PathVariable Long id){
		BasicOutputDto r = new BasicOutputDto();

		this.collectorService.remove(id);
		
		return r;
	}
	
}
