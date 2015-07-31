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
import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.api.dto.PaymentDto;
import com.icetea.manager.pagodiario.service.BillService;
import com.icetea.manager.pagodiario.service.PaymentService;

@Controller
@RequestMapping(value = "/html/payment")
public class PaymentController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(PaymentController.class);
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@Inject
	private PaymentService paymentService;
	@Inject
	private BillService billService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm(){
		
		return "payment";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<PaymentDto> getPayments(
			@RequestParam(required = false) Long id, 
			@RequestParam(required = false) Long billId,
			@RequestParam(required = false) Long collectorId){
		ListOutputDto<PaymentDto> r = new ListOutputDto<PaymentDto>();

		List<PaymentDto> payments = Lists.newArrayList();
		
		if(collectorId != null){
			List<BillDto> bills = this.billService.searchByCollectorId(collectorId);

			payments = this.paymentService.transform(bills);
			
		} else if(billId != null){
			payments = this.paymentService.search(billId);
		} else if(id != null){
			payments.add(this.paymentService.searchById(id));
		} else {
			payments = this.paymentService.searchAll();
		}
		
		r.setData(payments);
		
		return r;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<PaymentDto> addPayment(@RequestBody PaymentDto input){
		ListOutputDto<PaymentDto> r = new ListOutputDto<PaymentDto>();

		List<PaymentDto> payments = Lists.newArrayList();
		PaymentDto payment = this.paymentService.insert(input);
		if(payment != null){
			payments.add(payment);
		}
		r.setData(payments);
		
		return r;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody BasicOutputDto deletePayment(@PathVariable Long id){
		BasicOutputDto r = new BasicOutputDto();

		this.paymentService.remove(id);
		
		return r;
	}
	
	@RequestMapping(value = "/payments", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<PaymentDto> getPayments(@RequestParam(required = false) Long billId){
		ListOutputDto<PaymentDto> r = new ListOutputDto<PaymentDto>();

		List<PaymentDto> list = Lists.newArrayList();
		
		list = this.paymentService.search(billId);
		
		r.setData(list);
		
		return r;
	}
	
}
