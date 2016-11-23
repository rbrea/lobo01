package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BasicOutputDto;
import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.api.dto.PaymentDto;
import com.icetea.manager.pagodiario.api.dto.PaymentResponseDto;
import com.icetea.manager.pagodiario.exception.ErrorTypedException;
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
			@RequestParam(required = false) Long collectorId,
			@RequestParam(required = false) String paymentDate){
		ListOutputDto<PaymentDto> r = new ListOutputDto<PaymentDto>();

		List<PaymentDto> payments = Lists.newArrayList();
		
		if(collectorId != null){
			List<BillDto> bills = this.billService.searchByCollectorId(collectorId);

			payments = this.paymentService.transform(bills);
			
		} else if(billId != null){
			if(StringUtils.isNotBlank(paymentDate)){
				payments = this.paymentService.search(billId, paymentDate);
			} else {
				payments = this.paymentService.search(billId);
			}
		} else if(id != null){
			payments.add(this.paymentService.searchById(id));
		} else {
			payments = this.paymentService.searchAll();
		}
		
		r.setData(payments);
		
		return r;
	}
	
	@RequestMapping(value = "/creditNumber", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<PaymentDto> getPaymentsByCreditNumber(
			@RequestParam(required = false) Long creditNumber, 
			@RequestParam(required = false) String paymentDate){
		ListOutputDto<PaymentDto> r = new ListOutputDto<PaymentDto>();

		List<PaymentDto> payments = this.paymentService.searchByCreditNumber(creditNumber, paymentDate);
		if(payments == null){
			payments = Lists.newArrayList();
		}
		
		r.setData(payments);
		
		return r;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<PaymentDto> addPayment(@RequestBody PaymentDto input){
		ListOutputDto<PaymentDto> r = new ListOutputDto<PaymentDto>();

		List<PaymentDto> payments = Lists.newArrayList();
		PaymentDto payment = this.paymentService.insert(input, false);
		if(payment != null){
			payments.add(payment);
		}
		r.setData(payments);
		
		return r;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody BillDto deletePayment(@PathVariable Long id){
		BasicOutputDto r = new BasicOutputDto();

		Long billId = null; 
		PaymentDto payment = this.paymentService.searchById(id);
		if(payment != null){
			billId = payment.getBillId();
		}
		this.paymentService.remove(id);

		BillDto bill = this.billService.searchById(billId);
			
		return bill;
	}
	
	@RequestMapping(value = "/payments", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<PaymentDto> getPayments(@RequestParam(required = false) Long billId){
		ListOutputDto<PaymentDto> r = new ListOutputDto<PaymentDto>();

		List<PaymentDto> list = Lists.newArrayList();
		
		list = this.paymentService.search(billId);
		
		r.setData(list);
		
		return r;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<PaymentResponseDto> addListOfPayment(@RequestBody List<PaymentDto> input){
		ListOutputDto<PaymentResponseDto> r = new ListOutputDto<PaymentResponseDto>();

		List<PaymentResponseDto> list = Lists.newArrayList();
		for(PaymentDto p : input){
			PaymentResponseDto d = new PaymentResponseDto();
			d.setIdx(p.getIdx());
			try {
				PaymentDto payment = this.paymentService.insert(p, true);
				if(payment != null){
					d.setPayment(payment);
				}
			} catch (ErrorTypedException e) {
				String errorMessage = StringUtils.EMPTY;
				if(StringUtils.isNotBlank(e.getMessage())){
					errorMessage = e.getMessage();
				} else {
					errorMessage = e.getErrorType().getText();
				}
				d.setErrorMessage(errorMessage);
			}
			list.add(d);
		}
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "/filter/index", method = RequestMethod.GET)
	public String showPage(){
		
		return "payment-filter";
	}
	
	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<PaymentDto> getPaymentsQuery(
			@RequestParam(required = false) String dateFrom,
			@RequestParam(required = false) String dateTo,
			@RequestParam(required = false) Long collectorId,
			@RequestParam(required = false) Long clientId){
		ListOutputDto<PaymentDto> r = new ListOutputDto<PaymentDto>();

		List<PaymentDto> list = Lists.newArrayList();
		
		list = this.paymentService.search(dateFrom, dateTo, collectorId, clientId);
		if(list == null){
			list = Lists.newArrayList();
		}
		r.setData(list);
		
		return r;
	}

	@RequestMapping(value = "/export.xls", method = RequestMethod.POST)
	public ModelAndView exportBillsXls(HttpServletResponse response,
			@RequestParam(required = false, value="cfCollectorId") Long collectorId,
			@RequestParam(required = false, value="cfDateFrom") String dateFrom,
			@RequestParam(required = false, value="cfDateTo") String dateTo,
			@RequestParam(required = false, value="cfClientId") Long clientId){
		
		List<PaymentDto> list = this.paymentService.search(dateFrom, dateTo, collectorId, clientId);
		if(list == null){
			list = Lists.newArrayList();
		}
		
		String filename = "pagos_" + System.currentTimeMillis() + ".xls";
		response.setHeader("Content-Disposition", "inline; filename=" + filename);
		response.setContentType("application/msexcel");
		
		return new ModelAndView("paymentExcelView", "list", list);
	}
	
}
