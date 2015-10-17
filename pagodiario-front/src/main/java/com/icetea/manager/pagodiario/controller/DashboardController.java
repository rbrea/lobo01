package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/html/dashboard")
public class DashboardController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(DashboardController.class);
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showDashboardForm(){
		
		return "dashboard";
	}
	
}
