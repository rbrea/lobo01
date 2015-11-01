package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
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
import com.icetea.manager.pagodiario.api.dto.CollectorDetailDto;
import com.icetea.manager.pagodiario.api.dto.CollectorDto;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.exception.ErrorTypedException;
import com.icetea.manager.pagodiario.service.CollectorDetailService;
import com.icetea.manager.pagodiario.service.CollectorService;

@Controller
@RequestMapping(value = "/html/collector")
public class CollectorController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(CollectorController.class);
	
	@Inject
	private CollectorService collectorService;
	@Inject
	private CollectorDetailService collectorDetailService;
	@Inject
	private ServletContext servletContext;
	
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

	@RequestMapping(value = "/index/detail", method = RequestMethod.GET)
	public String showDetail(){
		
		return "collector-detail";
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<CollectorDetailDto> getCollectorDetails(@RequestParam(required = false) String from,
			@RequestParam(required = false) String to){
		ListOutputDto<CollectorDetailDto> r = new ListOutputDto<CollectorDetailDto>();

		List<CollectorDetailDto> list = this.collectorDetailService.search(from, to);
		if(list == null){
			list = Lists.newArrayList();
		}
		
		r.setData(list);
		
		return r;
	}

	@RequestMapping(value = "/detail/export/pdf", method = RequestMethod.POST)
	public void exportClients(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate){
		Map<String, Object> params = Maps.newHashMap();
		
		List<CollectorDetailDto> list = this.collectorDetailService.search(fromDate, toDate);
		
		Collections.sort(list, new Comparator<CollectorDetailDto>() {
			@Override
			public int compare(CollectorDetailDto o1, CollectorDetailDto o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		
		try {
			String fullpath = this.servletContext.getRealPath("/WEB-INF/jasper/collectorDetail.jasper");

			InputStream is = new FileInputStream(fullpath);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, params, 
					new JRBeanCollectionDataSource(list));
			response.setContentType("application/pdf");
			String filename = "detalle-de-cobradores-" + System.currentTimeMillis() + ".pdf";
			response.addHeader("Content-disposition", "attachment; filename=" + filename); 
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		} catch (Exception e) {
			LOGGER.error("Error inesperado al exportar el detalle de cobradores", e);
			throw new ErrorTypedException("Ha ocurrido un error al tratar generar el export de pdf del detalle de cobradores", 
					ErrorType.UNKNOWN_ERROR);
		}
		
	}
	
}
