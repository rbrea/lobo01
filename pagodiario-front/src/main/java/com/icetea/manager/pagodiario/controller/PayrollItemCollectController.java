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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.api.dto.PayrollCollectDto;
import com.icetea.manager.pagodiario.api.dto.PayrollItemCollectDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.exception.ErrorTypedException;
import com.icetea.manager.pagodiario.service.PayrollCollectService;
import com.icetea.manager.pagodiario.utils.StringUtils;

@Controller
@RequestMapping(value = "/html/payrollcollectitem")
public class PayrollItemCollectController extends ExceptionHandlingController {

private static final Logger LOGGER = getLogger(PayrollItemCollectController.class);
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@Inject
	private PayrollCollectService payrollCollectService;
	@Inject
	private ServletContext servletContext;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showFormCollect(@RequestParam(required = false) Long payrollId, ModelMap modelMap){
		modelMap.addAttribute("payrollId", payrollId);
		PayrollCollectDto p = this.payrollCollectService.searchById(payrollId);
		if(p != null){
			modelMap.addAttribute("totalCards", p.getTotalCards());
			modelMap.addAttribute("totalCardsReal", p.getTotalCardsReal());
			modelMap.addAttribute("totalToCollect", p.getTotalAmount());
			modelMap.addAttribute("totalCollectedGross", p.getTotalPayment());
			modelMap.addAttribute("totalCommission", p.getTotalAmountToPay());
			modelMap.addAttribute("totalCollectedNet", p.getTotalToCollect());
		}
		
		return "payrollCollectItem";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<PayrollItemCollectDto> getListItemCollect(@RequestParam(required = false) Long id){
		ListOutputDto<PayrollItemCollectDto> r = new ListOutputDto<PayrollItemCollectDto>();

		List<PayrollItemCollectDto> list = Lists.newArrayList();

		PayrollCollectDto p = this.payrollCollectService.searchById(id);
		if(p != null){
			list.addAll(p.getItems());
		}
		
		r.setData(list);
		
		return r;
	}

	@RequestMapping(value = "/export/pdf", method = RequestMethod.POST)
	public void exportTraderPayroll(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) Long payrollItemId){
		Map<String, Object> params = Maps.newHashMap();
		
		PayrollCollectDto p = this.payrollCollectService.searchById(payrollItemId);
		
		params.put("LIQ_DATE", p.getPayrollDate());
		params.put("TOTAL_CARDS", StringUtils.toString(p.getTotalCards()));
		params.put("TOTAL_AMOUNT", p.getTotalAmount());
		params.put("TOTAL_PAYMENT", p.getTotalPayment());
		params.put("AMOUNT_TO_PAY", p.getTotalAmountToPay());
		params.put("TOTAL_TO_COLLECT", p.getTotalToCollect());
		
		List<PayrollItemCollectDto> items = p.getItems(); 
		
		try {
			String fullpath = this.servletContext.getRealPath("/WEB-INF/jasper/liq-cobrador.jasper");
			// [roher] otra forma de hacerlo ... x ahora uso directamente el jasper, parece que es mas rapido ...
//			JasperDesign jasperDesign = JRXmlLoader.load(fullpath);
//			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			InputStream is = new FileInputStream(fullpath);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, params, 
					new JRBeanCollectionDataSource(items));
			response.setContentType("application/pdf");
			String filename = "liq-cobrador-" + System.currentTimeMillis() + ".pdf";
			response.addHeader("Content-disposition", "attachment; filename=" + filename); 
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		} catch (Exception e) {
			LOGGER.error("Error inesperado al tratar de generar el pdf de liq de cobrador", e);
			throw new ErrorTypedException("Ha ocurrido un error al tratar generar la liq de cobrador pdf", 
					ErrorType.UNKNOWN_ERROR);
		}
		
	}
	
}
