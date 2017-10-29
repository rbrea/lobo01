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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.icetea.manager.pagodiario.api.dto.ConciliationItemDto;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.api.dto.PayrollDto;
import com.icetea.manager.pagodiario.api.dto.PayrollItemDto;
import com.icetea.manager.pagodiario.api.dto.SupervisorConciliationItemDto;
import com.icetea.manager.pagodiario.api.dto.SupervisorPayrollItemDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.exception.ErrorTypedException;
import com.icetea.manager.pagodiario.service.ConciliationItemService;
import com.icetea.manager.pagodiario.service.PayrollItemService;
import com.icetea.manager.pagodiario.service.PayrollService;
import com.icetea.manager.pagodiario.service.SupervisorPayrollItemService;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Controller
@RequestMapping(value = "/html/conciliationItem")
public class ConciliationItemController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(ConciliationItemController.class);
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@Inject
	private ConciliationItemService conciliationItemService;
	@Inject
	private PayrollService payrollService;
	@Inject
	private SupervisorPayrollItemService supervisorPayrollItemService;
	@Inject
	private ServletContext servletContext;
	@Inject
	private PayrollItemService payrollItemService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm(@RequestParam(required = false) Long payrollId, 
			@RequestParam(required = false) Long payrollItemId,
			@RequestParam(required = false) String traderName,
			@RequestParam(required = false) String totalCollect,
			@RequestParam(required = false) String totalDiscount,
			@RequestParam(required = false) String totalTrader,
			ModelMap modelMap){
		modelMap.addAttribute("payrollId", payrollId);
		modelMap.addAttribute("payrollItemId", payrollItemId);
		
		PayrollDto payroll = this.payrollService.searchById(payrollId);
		
		modelMap.addAttribute("fromDate", payroll.getFromDate());
		modelMap.addAttribute("toDate", payroll.getToDate());
		modelMap.addAttribute("traderName", traderName);
		
		modelMap.addAttribute("totalCollect", totalCollect);
		modelMap.addAttribute("totalDiscount", totalDiscount);
		modelMap.addAttribute("totalTrader", totalTrader);
		
		return "conciliationItem";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<ConciliationItemDto> get(@RequestParam(required = false) Long payrollItemId){
		ListOutputDto<ConciliationItemDto> r = new ListOutputDto<ConciliationItemDto>();

		List<ConciliationItemDto> list = Lists.newArrayList();

		
		PayrollItemDto payrollItem = this.payrollItemService.searchById(payrollItemId);
		
		list.addAll(payrollItem.getConciliationItems());
		
//		list = this.conciliationItemService.searchByPayrollItemId(payrollItemId);
		
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "/supervisor/index", method = RequestMethod.GET)
	public String showSupervisorForm(@RequestParam(required = false) Long payrollId, 
			@RequestParam(required = false) Long payrollItemId,
			@RequestParam(required = false) String traderName,
			@RequestParam(required = false) String totalCollect,
			@RequestParam(required = false) String totalDiscount,
			@RequestParam(required = false) String totalTrader,
			ModelMap modelMap){
		modelMap.addAttribute("payrollId", payrollId);
		modelMap.addAttribute("payrollItemId", payrollItemId);
		
		PayrollDto payroll = this.payrollService.searchById(payrollId);
		
		SupervisorPayrollItemDto supervisorPayrollItemDto = this.supervisorPayrollItemService.searchDetail(payrollItemId);
		
		modelMap.addAttribute("fromDate", payroll.getFromDate());
		modelMap.addAttribute("toDate", payroll.getToDate());
		modelMap.addAttribute("supervisorName", supervisorPayrollItemDto.getSupervisorName());
		
		modelMap.addAttribute("totalCredit", (StringUtils.isNotBlank(supervisorPayrollItemDto.getTotalCreditAmount())) 
				? supervisorPayrollItemDto.getTotalCreditAmount() : NumberUtils._ZERO);
		modelMap.addAttribute("totalDev", (StringUtils.isNotBlank(supervisorPayrollItemDto.getTotalDevAmount())) 
				? supervisorPayrollItemDto.getTotalDevAmount() : NumberUtils._ZERO);
		modelMap.addAttribute("totalBonus", (StringUtils.isNotBlank(supervisorPayrollItemDto.getTotalBonusAmount())) 
				? supervisorPayrollItemDto.getTotalBonusAmount() : NumberUtils._ZERO);
		modelMap.addAttribute("totalReduction", (StringUtils.isNotBlank(supervisorPayrollItemDto.getTotalReductionAmount())) 
				? supervisorPayrollItemDto.getTotalReductionAmount() : NumberUtils._ZERO);
		modelMap.addAttribute("totalAmount", (StringUtils.isNotBlank(supervisorPayrollItemDto.getTotalAmount())) 
				? supervisorPayrollItemDto.getTotalAmount() : NumberUtils._ZERO);
		
		return "supervisorConciliationItem";
	}
	
	@RequestMapping(value = "/supervisor", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<SupervisorConciliationItemDto> getSupervisor(@RequestParam(required = false) Long payrollItemId){
		ListOutputDto<SupervisorConciliationItemDto> r = new ListOutputDto<SupervisorConciliationItemDto>();

		List<SupervisorConciliationItemDto> list = Lists.newArrayList();

		SupervisorPayrollItemDto supervisorPayrollItemDto = this.supervisorPayrollItemService.searchDetail(payrollItemId);		
		
		list = supervisorPayrollItemDto.getSupervisorConciliationItemList();
		
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "/export/trader", method = RequestMethod.POST)
	public void exportTraderPayroll(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) Long payrollItemId){
		Map<String, Object> params = Maps.newHashMap();
		
		PayrollItemDto payrollItemDto = this.payrollItemService.searchById(payrollItemId);
		
		params.put("TRADER_NAME", payrollItemDto.getTraderName());
		params.put("LIQ_DATE", "DESDE " + payrollItemDto.getPayrollDateFrom() 
				+ " HASTA " + payrollItemDto.getPayrollDateTo());
		params.put("SUBTOTAL_COLLECT", payrollItemDto.getSubtotalCollect());
		params.put("SUBTOTAL_DISCOUNT", payrollItemDto.getSubtotalDiscount());
		params.put("TOTAL_AMOUNT", payrollItemDto.getTotalAmount());
		
		List<ConciliationItemDto> list = this.conciliationItemService.searchByPayrollItemId(payrollItemId); 
		
		try {
			String fullpath = this.servletContext.getRealPath("/WEB-INF/jasper/liq-vend.jasper");
			// [roher] otra forma de hacerlo ... x ahora uso directamente el jasper, parece que es mas rapido ...
//			JasperDesign jasperDesign = JRXmlLoader.load(fullpath);
//			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			InputStream is = new FileInputStream(fullpath);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, params, 
					new JRBeanCollectionDataSource(list));
			response.setContentType("application/pdf");
			String filename = "liq-vendedor-" + System.currentTimeMillis() + ".pdf";
			response.addHeader("Content-disposition", "attachment; filename=" + filename); 
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		} catch (Exception e) {
			LOGGER.error("Error inesperado al tratar de generar el pdf de tickets de cobro", e);
			throw new ErrorTypedException("Ha ocurrido un error al tratar generar los tickets de cobro", 
					ErrorType.UNKNOWN_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/export/supervisor", method = RequestMethod.POST)
	public void exportSupervisorPayroll(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) Long payrollItemId){
		Map<String, Object> params = Maps.newHashMap();
		
		SupervisorPayrollItemDto supervisorPayrollItemDto = this.supervisorPayrollItemService.searchDetail(payrollItemId);
		
		params.put("SUP_NAME", supervisorPayrollItemDto.getSupervisorName());
		params.put("LIQ_DATE", "DESDE " + supervisorPayrollItemDto.getDateFrom() 
				+ " HASTA " + supervisorPayrollItemDto.getDateTo());
		params.put("TOTAL_CREDIT", (StringUtils.isNotBlank(supervisorPayrollItemDto.getTotalCreditAmount())) 
				? supervisorPayrollItemDto.getTotalCreditAmount() : NumberUtils._ZERO);
		params.put("TOTAL_DEV", (StringUtils.isNotBlank(supervisorPayrollItemDto.getTotalDevAmount())) 
				? supervisorPayrollItemDto.getTotalDevAmount() : NumberUtils._ZERO);
		params.put("TOTAL_BONUS", (StringUtils.isNotBlank(supervisorPayrollItemDto.getTotalBonusAmount())) 
				? supervisorPayrollItemDto.getTotalBonusAmount() : NumberUtils._ZERO);
		params.put("TOTAL_REDUCTION", (StringUtils.isNotBlank(supervisorPayrollItemDto.getTotalReductionAmount())) 
				? supervisorPayrollItemDto.getTotalReductionAmount() : NumberUtils._ZERO);
		params.put("TOTAL_TOTAL", (StringUtils.isNotBlank(supervisorPayrollItemDto.getTotalAmount())) 
				? supervisorPayrollItemDto.getTotalAmount() : NumberUtils._ZERO);
		
		List<SupervisorConciliationItemDto> list = supervisorPayrollItemDto.getSupervisorConciliationItemList();
		
		try {
			String fullpath = this.servletContext.getRealPath("/WEB-INF/jasper/liq-supervisor.jasper");
			// [roher] otra forma de hacerlo ... x ahora uso directamente el jasper, parece que es mas rapido ...
//			JasperDesign jasperDesign = JRXmlLoader.load(fullpath);
//			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			InputStream is = new FileInputStream(fullpath);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, params, 
					new JRBeanCollectionDataSource(list));
			response.setContentType("application/pdf");
			String filename = "liq-supervisor-" + System.currentTimeMillis() + ".pdf";
			response.addHeader("Content-disposition", "attachment; filename=" + filename); 
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		} catch (Exception e) {
			LOGGER.error("Error inesperado al tratar de generar el pdf de tickets de cobro", e);
			throw new ErrorTypedException("Ha ocurrido un error al tratar generar los tickets de cobro", 
					ErrorType.UNKNOWN_ERROR);
		}
		
	}

}
