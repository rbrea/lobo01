package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/html/payment")
public class PaymentController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(PaymentController.class);
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}

}
