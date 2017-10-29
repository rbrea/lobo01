package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/xls")
public class ExcelController {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = getLogger(ExcelController.class);
	
}
