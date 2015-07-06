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
import com.icetea.manager.pagodiario.api.dto.ProductDto;
import com.icetea.manager.pagodiario.service.ProductService;

@Controller
@RequestMapping(value = "/html/product")
public class ProductController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(ProductController.class);
	
	@Inject
	private ProductService productService;
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showClientForm(){
		
		return "product";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<ProductDto> getClients(@RequestParam(required = false) Long id){
		ListOutputDto<ProductDto> r = new ListOutputDto<ProductDto>();

		List<ProductDto> list = Lists.newArrayList();
		
		if(id == null){
			list = this.productService.searchAll();
		} else {
			list.add(this.productService.searchById(id));
		}
		
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<ProductDto> addClient(@RequestBody ProductDto input){
		ListOutputDto<ProductDto> r = new ListOutputDto<ProductDto>();

		List<ProductDto> list = Lists.newArrayList();
		ProductDto client = null;
		if(input.getId() != null){
			client = this.productService.update(input);
			list.add(client);
		} else {
			client = this.productService.insert(input);
			list.add(client);
		}
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody BasicOutputDto deleteClient(@PathVariable Long id){
		BasicOutputDto r = new BasicOutputDto();

		this.productService.remove(id);
		
		return r;
	}

}
