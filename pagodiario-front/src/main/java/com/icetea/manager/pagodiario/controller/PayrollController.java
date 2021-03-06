package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

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
import com.icetea.manager.pagodiario.api.dto.PayrollDto;
import com.icetea.manager.pagodiario.service.PayrollService;
import com.icetea.manager.pagodiario.utils.DateUtils;

@Controller
@RequestMapping(value = "/html/payroll")
public class PayrollController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(PayrollController.class);
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@Inject
	private PayrollService payrollService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm(){
		
		return "payroll";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<PayrollDto> get(@RequestParam(required = false) Long id){
		ListOutputDto<PayrollDto> r = new ListOutputDto<PayrollDto>();

		List<PayrollDto> list = Lists.newArrayList();
		
		if(id != null){
			list.add(this.payrollService.searchById(id));
		} else {
			list = this.payrollService.searchAll();
		}
		
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<PayrollDto> add(@RequestBody PayrollDto input){
		ListOutputDto<PayrollDto> r = new ListOutputDto<PayrollDto>();

		List<PayrollDto> list = Lists.newArrayList();
		PayrollDto d = null;
		if(input.getId() != null){
			d = this.payrollService.update(input);
			list.add(d);
		} else {
			d = this.payrollService.insert(input);
			list.add(d);
		}
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "/processperiod", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<PayrollDto> processPeriod(@RequestBody PayrollDto input){
		ListOutputDto<PayrollDto> r = new ListOutputDto<PayrollDto>();

		List<PayrollDto> list = Lists.newArrayList();
		
		String[] first = input.getPeriod().split(" -> ");
		
		String[] periods = StringUtils.split(first[0], " a ");
		
		Date from = DateUtils.parseDate(periods[0]);
		Date to = DateUtils.parseDate(periods[1]);
		
		PayrollDto p = this.payrollService.processPeriod(from, to);
		
		list.add(p);
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody BasicOutputDto delete(@PathVariable Long id){
		BasicOutputDto r = new BasicOutputDto();

		this.payrollService.remove(id);
		
		return r;
	}

	@RequestMapping(value = "/periods", method = RequestMethod.GET)
	public @ResponseBody List<String> getPeriods(){
		List<String> list = Lists.newArrayList();
		
		Date now = new Date();
		
		Calendar c = Calendar.getInstance();   // this takes current date
	    c.set(Calendar.DAY_OF_MONTH, 1);
	    Date start = c.getTime();
	    
	    // [roher] aca es donde esta la papa!! depende el nro que le ponga aca es d donde empieza ...
	    Date before = DateUtils.addMonths(now, -28);
	    c.setTime(before);
	    
	    c.set(Calendar.DAY_OF_MONTH, 15);
	    Date middle = c.getTime();
	    
	    boolean first = true;
	    if(now.after(middle)){
	    	first = false;
	    }

	    Date end = middle;
	    start = middle;
	    for(int i=0;i<62;i++){
	    	if(!first){
	    		end = DateUtils.addMonths(start, 1);
	    		c.setTime(end);
	    		c.set(Calendar.DAY_OF_MONTH, 1);
	    		end = DateUtils.addDays(c.getTime(), -1);
	    	} else {
	    		start = DateUtils.addDays(end, 1);
	    		end = DateUtils.addDays(start, 14);
	    	}
	    	
	    	Calendar calendar = Calendar.getInstance();
	    	calendar.setTime(end);
	    	int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
	    	if(dayOfMonth > 15){
	    		calendar.set(Calendar.DAY_OF_MONTH, 15);
	    		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
	    	} else {
	    		calendar.set(Calendar.DAY_OF_MONTH, 1);
	    		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
	    	}
	    	
	    	Date periodDate = calendar.getTime();
	    	
	    	String period = DateUtils.toDate(start) 
	    			+ " a " + DateUtils.toDate(end) + " -> " + DateUtils.toDate(periodDate);

	    	if(now.before(end) && now.after(start)){
	    		period += "--CURRENT";
	    	}
	    	
	    	list.add(period);
	    	
	    	start = DateUtils.addDays(end, 1);
	    	first = !first;
	    }
		
		return list;
	}
	
	@RequestMapping(value = "/undoliq/{id}", method = RequestMethod.POST)
	public @ResponseBody BasicOutputDto undoPayroll(@PathVariable Long id){
		BasicOutputDto r = new BasicOutputDto();
		
		this.payrollService.remove(id);
		
		return r;
	}
	
	@RequestMapping(value = "/commitliq/{id}", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<PayrollDto> commitPayroll(@PathVariable Long id){
		ListOutputDto<PayrollDto> r = new ListOutputDto<PayrollDto>();
		
		PayrollDto p = this.payrollService.commitPayroll(id);
		if(p != null){
			r.add(p);
		}
		
		return r;
	}

}
