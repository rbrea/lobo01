package com.icetea.manager.pagodiario.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Maps;
import com.icetea.manager.pagodiario.jasper.factory.sample.BillTicketFactory;

@Controller
@RequestMapping(value = "/html/pdf")
public class PdfController extends ExceptionHandlingController {

	@Inject
	private ServletContext servletContext;
	
	@Override
	protected Logger getOwnLogger() {
		return null;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void exportBillTickets(HttpServletResponse response){
		Map<String, Object> params = Maps.newHashMap();
		try {
			String fullpath = this.servletContext.getRealPath("/WEB-INF/jasper/sgpd_ticket.jasper");
			// [roher] otra forma de hacerlo ... x ahora uso directamente el jasper, parece que es mas rapido ...
//			JasperDesign jasperDesign = JRXmlLoader.load(fullpath);
//			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			InputStream is = new FileInputStream(fullpath);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, params, new JRBeanCollectionDataSource(
					BillTicketFactory.createBeanCollection()));
			response.setContentType("application/pdf");
			response.addHeader("Content-disposition", "attachment; filename=report_bill.pdf"); 
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
