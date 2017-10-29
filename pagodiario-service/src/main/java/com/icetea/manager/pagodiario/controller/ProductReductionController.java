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
import com.icetea.manager.pagodiario.api.dto.ProductReductionDto;
import com.icetea.manager.pagodiario.service.ProductReductionService;

@Controller
@RequestMapping(value = "/html/productReduction")
public class ProductReductionController extends ExceptionHandlingController {

private static final Logger LOGGER = getLogger(ProductReductionController.class);
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@Inject
	private ProductReductionService productReductionService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm(){
		
		return "productReduction";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<ProductReductionDto> get(@RequestParam(required = false) Long id,
			@RequestParam(required = false) Long billId){
		ListOutputDto<ProductReductionDto> r = new ListOutputDto<ProductReductionDto>();

		List<ProductReductionDto> list = Lists.newArrayList();
		
		if(billId != null){
			list = this.productReductionService.search(billId);
		} else if(id != null){
			list.add(this.productReductionService.searchById(id));
		} else {
			list = this.productReductionService.searchAll();
		}
		
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<ProductReductionDto> add(@RequestBody ProductReductionDto input){
		ListOutputDto<ProductReductionDto> r = new ListOutputDto<ProductReductionDto>();

		List<ProductReductionDto> list = Lists.newArrayList();
		ProductReductionDto d = null;
		if(input.getId() != null){
			d = this.productReductionService.update(input);
			list.add(d);
		} else {
			d = this.productReductionService.insert(input);
			list.add(d);
		}
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody BasicOutputDto delete(@PathVariable Long id){
		BasicOutputDto r = new BasicOutputDto();

		this.productReductionService.remove(id);
		
		return r;
	}

}
