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
import com.icetea.manager.pagodiario.api.dto.ClientDto;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.service.ClientService;

@Controller
@RequestMapping(value = "/html/client")
public class ClientController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(ClientController.class);
	
	@Inject
	private ClientService clientService;
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showClientForm(){
		
		return "client";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<ClientDto> getClients(@RequestParam(required = false) Long id){
		ListOutputDto<ClientDto> r = new ListOutputDto<ClientDto>();

		List<ClientDto> clients = Lists.newArrayList();
		
		if(id == null){
			clients = this.clientService.searchAll();
		} else {
			clients.add(this.clientService.searchById(id));
		}
		
		r.setData(clients);
		
		return r;
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
	
}
