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

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Maps;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.api.pojo.jasper.BillTicketPojo;
import com.icetea.manager.pagodiario.exception.ErrorTypedException;
import com.icetea.manager.pagodiario.service.BillService;

@Controller
@RequestMapping(value = "/html/ticket")
public class TicketController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(TicketController.class);
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@Inject
	private ServletContext servletContext;
	@Inject
	private BillService billService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm(){
		
		return "ticket";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void doGenerate(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) Integer zone,
			@RequestParam(required = false) String fecDesdeValue,
			@RequestParam(required = false) String fecHastaValue){
		Map<String, Object> params = Maps.newHashMap();
		
		List<BillTicketPojo> list = this.billService.searchBillsByCollectorId(zone, fecDesdeValue, fecHastaValue);
		
		try {
			String fullpath = this.servletContext.getRealPath("/WEB-INF/jasper/sgpd_ticket.jasper");
			// [roher] otra forma de hacerlo ... x ahora uso directamente el jasper, parece que es mas rapido ...
//			JasperDesign jasperDesign = JRXmlLoader.load(fullpath);
//			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			InputStream is = new FileInputStream(fullpath);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, params, 
					new JRBeanCollectionDataSource(list));
			response.setContentType("application/pdf");
			String filename = "tickets-cobro-" + System.currentTimeMillis() + ".pdf";
			response.addHeader("Content-disposition", "attachment; filename=" + filename); 
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		} catch (Exception e) {
			LOGGER.error("Error inesperado al tratar de generar el pdf de tickets de cobro", e);
			throw new ErrorTypedException("Ha ocurrido un error al tratar generar los tickets de cobro", 
					ErrorType.UNKNOWN_ERROR);
		}
	}

}
