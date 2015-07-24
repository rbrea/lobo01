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
import com.icetea.manager.pagodiario.api.dto.BonusDto;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.service.BonusService;

@Controller
@RequestMapping(value = "/html/bonus")
public class BonusController extends ExceptionHandlingController {

private static final Logger LOGGER = getLogger(BonusController.class);
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@Inject
	private BonusService bonusService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm(){
		
		return "bonus";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<BonusDto> get(@RequestParam(required = false) Long id,
			@RequestParam(required = false) Long billId){
		ListOutputDto<BonusDto> r = new ListOutputDto<BonusDto>();

		List<BonusDto> list = Lists.newArrayList();
		
		if(billId != null){
			list = this.bonusService.search(billId);
		} else if(id != null){
			list.add(this.bonusService.searchById(id));
		} else {
			list = this.bonusService.searchAll();
		}
		
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<BonusDto> add(@RequestBody BonusDto input){
		ListOutputDto<BonusDto> r = new ListOutputDto<BonusDto>();

		List<BonusDto> list = Lists.newArrayList();
		BonusDto client = null;
		if(input.getId() != null){
			client = this.bonusService.update(input);
			list.add(client);
		} else {
			client = this.bonusService.insert(input);
			list.add(client);
		}
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody BasicOutputDto delete(@PathVariable Long id){
		BasicOutputDto r = new BasicOutputDto();

		this.bonusService.remove(id);
		
		return r;
	}

}
