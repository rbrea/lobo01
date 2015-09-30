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
import com.icetea.manager.pagodiario.api.dto.ClientDto;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.exception.ErrorTypedException;
import com.icetea.manager.pagodiario.service.ClientService;

@Controller
@RequestMapping(value = "/html/client")
public class ClientController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(ClientController.class);
	
	@Inject
	private ClientService clientService;
	@Inject
	private ServletContext servletContext;
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showClientForm(){
		
		return "client";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<ClientDto> getClients(@RequestParam(required = false) Long id, 
			@RequestParam(required = false) String q){
		ListOutputDto<ClientDto> r = new ListOutputDto<ClientDto>();

		List<ClientDto> clients = Lists.newArrayList();
		
		if(StringUtils.isNotBlank(q)){
			clients = this.clientService.searchByName(q);
		} else if(id == null){
			clients = this.clientService.searchAll();
		} else {
			ClientDto client = this.clientService.searchById(id);
			if(client != null){
				clients.add(client);
			}
		}
		
		r.setData(clients);
		
		return r;
	}
	
	@RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
	public @ResponseBody List<ClientDto> getClientsAutocomplete(@RequestParam(required = false) String q){

		List<ClientDto> clients = Lists.newArrayList();
		
		if(StringUtils.isNotBlank(q)){
			clients = this.clientService.searchByName(q);
		} else {
			clients = this.clientService.searchAll();
		}
		if(clients == null){
			return Lists.newArrayList();
		}
		
		return clients;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<ClientDto> addClient(@RequestBody ClientDto input){
		ListOutputDto<ClientDto> r = new ListOutputDto<ClientDto>();

		List<ClientDto> clients = Lists.newArrayList();
		ClientDto client = null;
		if(input.getId() != null){
			client = this.clientService.update(input);
			clients.add(client);
		} else {
			client = this.clientService.insert(input);
			clients.add(client);
		}
		r.setData(clients);
		
		return r;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody BasicOutputDto deleteClient(@PathVariable Long id){
		BasicOutputDto r = new BasicOutputDto();

		this.clientService.remove(id);
		
		return r;
	}

	@RequestMapping(value = "/export/pdf", method = RequestMethod.POST)
	public void exportClients(HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> params = Maps.newHashMap();
		
		List<ClientDto> list = this.clientService.searchAll();
		
		try {
			String fullpath = this.servletContext.getRealPath("/WEB-INF/jasper/clients.jasper");
			// [roher] otra forma de hacerlo ... x ahora uso directamente el jasper, parece que es mas rapido ...
//			JasperDesign jasperDesign = JRXmlLoader.load(fullpath);
//			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			InputStream is = new FileInputStream(fullpath);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, params, 
					new JRBeanCollectionDataSource(list));
			response.setContentType("application/pdf");
			String filename = "clientes-" + System.currentTimeMillis() + ".pdf";
			response.addHeader("Content-disposition", "attachment; filename=" + filename); 
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		} catch (Exception e) {
			LOGGER.error("Error inesperado al exportar los clientes", e);
			throw new ErrorTypedException("Ha ocurrido un error al tratar generar el export de pdf de clientes", 
					ErrorType.UNKNOWN_ERROR);
		}
		
	}
	
}
