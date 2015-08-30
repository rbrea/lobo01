package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
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
@RequestMapping(value = "/html/trader")
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
	public @ResponseBody ListOutputDto<TraderDto> getList(@RequestParam(required = false) final Long id,
			@RequestParam(required = false) final Long parentId){
		ListOutputDto<TraderDto> r = new ListOutputDto<TraderDto>();

		List<TraderDto> list = Lists.newArrayList();
		
		if(id == null){
			list = this.traderService.searchAll();
			if(parentId != null && list != null && !list.isEmpty()){
				TraderDto parent = CollectionUtils.find(list, new Predicate<TraderDto>() {
					@Override
					public boolean evaluate(TraderDto object) {
						
						return object.getId().equals(parentId);
					}
				}); 
				if(parent != null){
					list.remove(parent);
				}
			}
		} else {
			TraderDto trader = this.traderService.searchById(id);
			if(trader != null){
				list.add(trader);
			}
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

	@RequestMapping(value = "/lov", method = RequestMethod.GET)
	public String showLov(){
		
		return "lovTrader";
	}

	@RequestMapping(value = "/children", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<TraderDto> getChildren(@RequestParam(required = false) final Long parentId){
		ListOutputDto<TraderDto> r = new ListOutputDto<TraderDto>();

		List<TraderDto> list = Lists.newArrayList();
		
		TraderDto dto = this.traderService.searchById(parentId);
		if(dto != null){
			List<Long> ids = dto.getListOfTraderIds();
			if(ids != null && !ids.isEmpty()){
				for(Long id : ids){
					TraderDto child = this.traderService.searchById(id);
					if(child != null){
						list.add(child);
					}
				}
			}
		}
		r.setData(list);
		
		return r;
	}

	@RequestMapping(value = "/children/{parentId}/{childId}", method = RequestMethod.DELETE)
	public @ResponseBody BasicOutputDto deleteChild(@PathVariable Long parentId,
			@PathVariable Long childId){
		BasicOutputDto r = new BasicOutputDto();

		this.traderService.deleteChild(parentId, childId);
		
		return r;
	}
	
	@RequestMapping(value = "/children/{parentId}/{childId}", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<TraderDto> addChildToParentTrader(@PathVariable Long parentId,
			@PathVariable Long childId){
		ListOutputDto<TraderDto> r = new ListOutputDto<TraderDto>();
		
		this.traderService.addChild(parentId, childId);
		
		return r;
	}

	@RequestMapping(value = "/supervisor", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<TraderDto> getSupervisors(){
		ListOutputDto<TraderDto> r = new ListOutputDto<TraderDto>();

		List<TraderDto> list = Lists.newArrayList();
		
		List<TraderDto> sups = this.traderService.searchSupervisors();
		if(sups != null){
			list.addAll(sups);
		}
		
		r.setData(list);
		
		return r;
	}
	
}
