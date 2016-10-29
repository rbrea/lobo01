package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.icetea.manager.pagodiario.api.dto.BasicOutputDto;
import com.icetea.manager.pagodiario.api.dto.BillDetailDto;
import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.dto.BillInfoDto;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.api.dto.PaydayDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.api.pojo.jasper.CreditDetailPojo;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.exception.ErrorTypedException;
import com.icetea.manager.pagodiario.service.BillService;
import com.icetea.manager.pagodiario.transformer.jasper.CreditDetailTransformer;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.util.FileResolver;

@Controller
@RequestMapping(value = "/html/bill")
public class BillController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(BillController.class);
	
	@Inject
	private BillService billService;
	@Inject
	private ServletContext servletContext;
	@Inject
	private CreditDetailTransformer creditDetailTransformer;
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showBillForm(){
		
		return "bill";
	}
	
	@RequestMapping(value = "/history/index", method = RequestMethod.GET)
	public String showHistory(){
		
		return "bill-history";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<BillDto> getBills(@RequestParam(required = false) Long id,
			@RequestParam(required = false) Long creditNumber,
			@RequestParam(required = false) Long collectorId,
			@RequestParam(required = false) String status,
			@RequestParam(required = false) Long clientId,
			@RequestParam(required = false) String dateFrom,
			@RequestParam(required = false) String dateTo,
			@RequestParam(required = false) Boolean devTotalMark){
		
		ListOutputDto<BillDto> r = new ListOutputDto<BillDto>();

		List<BillDto> list = Lists.newArrayList();
		
		if(id != null){
			BillDto bill = this.billService.searchById(id);
			if(bill != null){
				list.add(bill);
			}
		} else if(creditNumber != null){
			BillDto bill = this.billService.searchByCreditNumber(creditNumber);
			if(bill != null){
				list.add(bill);
			}
		} else if(collectorId != null || StringUtils.isNotBlank(status)
				|| clientId != null){
			List<BillDto> bills = this.billService.searchByFilter(
					creditNumber, collectorId, status, clientId, dateFrom, dateTo, devTotalMark);
			if(bills != null){
				list.addAll(bills);
			}
		} else {
			list = this.billService.searchAll();
		}
		
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<BillDto> addBill(@RequestBody BillDto input){
		ListOutputDto<BillDto> r = new ListOutputDto<BillDto>();

		List<BillDto> list = Lists.newArrayList();
		BillDto client = null;
		if(input.getId() != null){
			client = this.billService.update(input);
			list.add(client);
		} else {
			client = this.billService.insert(input);
			list.add(client);
		}
		r.setData(list);
		
		return r;
	}

	@RequestMapping(value = "/detail/index", method = RequestMethod.GET)
	public String showDetail(@RequestParam(required = false) Long billId, 
			@RequestParam(required = false) Long creditNumber,
			ModelMap model){
		
		ErrorTypedConditions.checkArgument(billId != null, ErrorType.BILL_REQUIRED);
		
		model.addAttribute("billId", billId);
		model.addAttribute("creditNumber", creditNumber);
		
		return "bill-detail";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<BillDetailDto> getDetails(@RequestParam(required = false) Long billId, 
			ModelMap model){
		
		ErrorTypedConditions.checkArgument(billId != null, ErrorType.BILL_REQUIRED);
		
		
		ListOutputDto<BillDetailDto> r = new ListOutputDto<BillDetailDto>();

		BillDetailDto d = this.billService.searchDetail(billId);
		if(d != null){
			r.getData().add(d);
		}
		
		return r;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody BasicOutputDto delete(@PathVariable Long id){
		BasicOutputDto r = new BasicOutputDto();

		this.billService.remove(id);
		
		return r;
	}

	@RequestMapping(value = "/export/pdf", method = RequestMethod.POST)
	public void exportBills(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) Long bhCollectorId,
			@RequestParam(required = false, value = "bhCreditNumber") Long creditNumber,
			@RequestParam(required = false, value = "bhStatus") String status,
			@RequestParam(required = false, value = "bhClientId") Long clientId,
			@RequestParam(required = false, value = "bhDateFrom") String dateFrom,
			@RequestParam(required = false, value = "bhDateTo") String dateTo,
			@RequestParam(required = false, value = "bhDevTotalMark") Boolean devTotalMark){
		Map<String, Object> params = Maps.newHashMap();
		
		List<BillDto> list = this.billService.searchByFilter(
				creditNumber, bhCollectorId, status, clientId, dateFrom, dateTo, devTotalMark);
		
		try {
			String fullpath = this.servletContext.getRealPath("/WEB-INF/jasper/billhistory.jasper");
			// [roher] otra forma de hacerlo ... x ahora uso directamente el jasper, parece que es mas rapido ...
//			JasperDesign jasperDesign = JRXmlLoader.load(fullpath);
//			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			InputStream is = new FileInputStream(fullpath);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, params, 
					new JRBeanCollectionDataSource(list));
			response.setContentType("application/pdf");
			String filename = "historial_de_creditos-" + System.currentTimeMillis() + ".pdf";
			response.addHeader("Content-disposition", "attachment; filename=" + filename); 
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		} catch (Exception e) {
			LOGGER.error("Error inesperado al exportar las facturas", e);
			throw new ErrorTypedException("Ha ocurrido un error al tratar generar el export de pdf de creditos", 
					ErrorType.UNKNOWN_ERROR);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/detail/export/pdf", method = RequestMethod.POST)
	public void exportBillDetail(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam Long billId){
		Map<String, Object> params = Maps.newHashMap();
		
		BillDetailDto d = this.billService.searchDetail(billId);
		
		try {
			String fullpath = this.servletContext.getRealPath("/WEB-INF/jasper/detalleCredito.jasper");
			// [roher] otra forma de hacerlo ... x ahora uso directamente el jasper, parece que es mas rapido ...
//			JasperDesign jasperDesign = JRXmlLoader.load(fullpath);
//			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			
			InputStream is = new FileInputStream(fullpath);
			
			final String fileResolverPath = this.servletContext.getRealPath("/WEB-INF/jasper/");
			
			String devsfullpath = this.servletContext.getRealPath("/WEB-INF/jasper/devs_sb.jrxml");
//			JasperReport devsSR = (JasperReport)JRLoader.loadObjectFromFile(devsfullpath);
			
			JasperReport devsSR = JasperCompileManager.compileReport(devsfullpath);
			
			params.put("devs_sb.jasper", devsSR);
			
			FileResolver devsfileResolver = new FileResolver() {
				
				@Override
				public File resolveFile(String fileName) {
					
					return new File(fileResolverPath + "/" + fileName);
				}
			}; 
			
			params.put("REPORT_FILE_RESOLVER", devsfileResolver);
			
			CreditDetailPojo creditDetail = this.creditDetailTransformer.transform(d);
			
			JRBeanCollectionDataSource creditDetailDs = new JRBeanCollectionDataSource(Lists.newArrayList(creditDetail));
			JRBeanCollectionDataSource devsDs = new JRBeanCollectionDataSource(creditDetail.getDevs());
			
			Map<String, Object>[] masterData = new Map[1];
   		    masterData[0] = Maps.newHashMap();
			masterData[0].put("arrayDataSourceMov",creditDetailDs);
			masterData[0].put("arrayDataSourceVendors",devsDs);
			
			JRMapArrayDataSource jrMapArrayDataSource = new JRMapArrayDataSource(masterData);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, params, creditDetailDs);
			response.setContentType("application/pdf");
			String filename = "detalle_de_credito_" + billId + "-" + System.currentTimeMillis() + ".pdf";
			response.addHeader("Content-disposition", "attachment; filename=" + filename); 
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		} catch (Exception e) {
			LOGGER.error("Error inesperado al exportar el detalle de credito", e);
			throw new ErrorTypedException("Ha ocurrido un error al tratar generar el export de pdf de detalle de credito", 
					ErrorType.UNKNOWN_ERROR);
		}
		
	}

	@RequestMapping(value = "/canceldiscount", method = RequestMethod.POST)
	public @ResponseBody ListOutputDto<BillInfoDto> add(@RequestParam(required = false) Long id){
		ListOutputDto<BillInfoDto> r = new ListOutputDto<BillInfoDto>();

		List<BillInfoDto> list = Lists.newArrayList();
		
		BillInfoDto billInfoDto = this.billService.setCancelDiscount(id);
		if(billInfoDto != null){
			list.add(billInfoDto);
		}
		
		r.setData(list);
		
		return r;
	}
	
	@RequestMapping(value = "/collector/{billId}/{collectorId}", method = RequestMethod.PUT)
	public @ResponseBody ListOutputDto<BillDto> changeCollector(@PathVariable Long billId,
			@PathVariable Long collectorId){
		ListOutputDto<BillDto> r = new ListOutputDto<BillDto>();
		
		BillDto bill = this.billService.updateCollector(billId, collectorId);

		r.add(bill);
		
		r.setStatus(0);
		
		return r;
	}
	
	@RequestMapping(value = "/payday", method = RequestMethod.GET)
	public @ResponseBody PaydayDto getPayday(@RequestParam(required = false) Long billId){
		
		PaydayDto r = this.billService.searchPayday(billId);

		r.setStatus(0);
		
		return r;
	}

	@RequestMapping(value = "/payday/{billId}", method = RequestMethod.POST)
	public @ResponseBody BasicOutputDto changePayday(@PathVariable Long billId,
			@RequestBody PaydayDto paydayDto){
		BasicOutputDto r = new BasicOutputDto();
		
		this.billService.updateBillWithPayday(paydayDto);
		
		r.setStatus(0);
		
		return r;
	}

	@RequestMapping(value = "/detail/export/xls", method = RequestMethod.POST)
	public ModelAndView exportBillDetailXls(HttpServletResponse response, @RequestParam Long billId){
		
		BillDetailDto d = this.billService.searchDetail(billId);
		
		String filename = "detalle_de_credito_" + billId + "-" + System.currentTimeMillis() + ".xls";
		response.setHeader("Content-Disposition", "inline; filename=" + filename);
		response.setContentType("application/msexcel");
			
		return new ModelAndView("BillHistoryExcelView", "list", Lists.newArrayList(d));
		
	}

	@RequestMapping(value = "/export/xls", method = RequestMethod.POST)
	public ModelAndView exportBillsXls(HttpServletResponse response,
			@RequestParam(required = false) Long bhCollectorId,
			@RequestParam(required = false, value = "bhxCreditNumber") Long creditNumber,
			@RequestParam(required = false, value = "bhxStatus") String status,
			@RequestParam(required = false, value = "bhxClientId") Long clientId,
			@RequestParam(required = false, value = "bhxDateFrom") String dateFrom,
			@RequestParam(required = false, value = "bhxDateTo") String dateTo,
			@RequestParam(required = false, value = "bhxDevTotalMark") Boolean devTotalMark){
		
		List<BillDto> list = this.billService.searchByFilter(
				creditNumber, bhCollectorId, status, clientId, dateFrom, dateTo, devTotalMark);
		
		String filename = "creditos_" + System.currentTimeMillis() + ".xls";
		response.setHeader("Content-Disposition", "inline; filename=" + filename);
		response.setContentType("application/msexcel");
		
		return new ModelAndView("billExcelView", "list", list);
	}
	
}
