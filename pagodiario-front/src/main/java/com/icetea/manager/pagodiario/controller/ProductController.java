package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
	public @ResponseBody ListOutputDto<ProductDto> get(@RequestParam(required = false) Long id,
			@RequestParam(required = false) String code, HttpServletRequest request){
		ListOutputDto<ProductDto> r = new ListOutputDto<ProductDto>();

		List<ProductDto> list = Lists.newArrayList();
		
		if(id != null){
			ProductDto p = this.productService.searchById(id);
			if(p != null){
				list.add(p);
			}
		} else if(StringUtils.isNotBlank(code)){
			ProductDto d = this.productService.searchByCode(code);
			if(d != null){
				list.add(d);
			}
		} else {
			list = this.productService.searchAll();			
		}
		
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<ProductDto> add(@RequestBody ProductDto input){
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
	public @ResponseBody BasicOutputDto delete(@PathVariable Long id){
		BasicOutputDto r = new BasicOutputDto();

		this.productService.remove(id);
		
		return r;
	}

}
