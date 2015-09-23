package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.util.LinkedList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icetea.manager.pagodiario.api.dto.FileMeta;
import com.icetea.manager.pagodiario.api.dto.chart.ChartDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.service.chart.ChartService;

@Controller
@RequestMapping
public class IndexController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(IndexController.class);
	@Inject
	private ChartService chartService; 
	
	@RequestMapping(value = "/html/index", method = RequestMethod.GET)
    public String index(){
		
        return "index";
    }
	
    /***************************************************
     * URL: /rest/controller/get/{value}
     * get(): get file as an attachment
     * @param response : passed by the server
     * @param value : value from the URL
     * @return void
     ****************************************************/
    @RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
    public void get(HttpServletResponse response, @PathVariable String value){
    	LinkedList<FileMeta> files = new LinkedList<FileMeta>();
    	FileMeta getFile = files.get(Integer.parseInt(value));
    	try {      
    		response.setContentType(getFile.getFileType());
    		response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
    		FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}

	@RequestMapping(value = "/html/chart")
	public @ResponseBody ChartDto getChart(){
		
		ChartDto chart = this.chartService.search();
		
		if(chart != null){
			chart.setStatus(0);
		} else {
			chart = new ChartDto();
			chart.setCause("No se pueden tomar las graficas");
			chart.setMessage("No se pueden tomar las graficas");
			chart.setErrorType(ErrorType.VALIDATION_ERRORS);
		}
		
		return chart;
	}
	
}
