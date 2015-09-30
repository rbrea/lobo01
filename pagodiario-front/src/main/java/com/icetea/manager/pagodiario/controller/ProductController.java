package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
import com.google.common.collect.Maps;
import com.icetea.manager.pagodiario.api.dto.BasicOutputDto;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.api.dto.ProductDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.exception.ErrorTypedException;
import com.icetea.manager.pagodiario.service.ProductService;

@Controller
@RequestMapping(value = "/html/product")
public class ProductController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(ProductController.class);
	
	@Inject
	private ProductService productService;
	@Inject
	private ServletContext servletContext;
	
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

	@RequestMapping(value = "/export/pdf", method = RequestMethod.POST)
	public void exportProducts(HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> params = Maps.newHashMap();
		
		List<ProductDto> list = this.productService.searchAll();
		
		try {
			String fullpath = this.servletContext.getRealPath("/WEB-INF/jasper/product.jasper");
			// [roher] otra forma de hacerlo ... x ahora uso directamente el jasper, parece que es mas rapido ...
//			JasperDesign jasperDesign = JRXmlLoader.load(fullpath);
//			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			InputStream is = new FileInputStream(fullpath);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, params, 
					new JRBeanCollectionDataSource(list));
			response.setContentType("application/pdf");
			String filename = "productos-" + System.currentTimeMillis() + ".pdf";
			response.addHeader("Content-disposition", "attachment; filename=" + filename); 
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		} catch (Exception e) {
			LOGGER.error("Error inesperado al exportar los productos", e);
			throw new ErrorTypedException("Ha ocurrido un error al tratar generar el export de pdf de productos", 
					ErrorType.UNKNOWN_ERROR);
		}
		
	}
	
}
