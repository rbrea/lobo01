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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.dto.ListOutputDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.api.pojo.jasper.VoucherPojo;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.exception.ErrorTypedException;
import com.icetea.manager.pagodiario.service.BillService;
import com.icetea.manager.pagodiario.transformer.jasper.BillToVoucherTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value = "/html/voucher")
public class VoucherController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(VoucherController.class);
	
	@Inject
	private BillToVoucherTransformer billToVoucherTransformer;
	@Inject
	private ServletContext servletContext;
	@Inject
	private BillService billService;
	
	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showVoucherForm(){
		
		return "voucher";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ListOutputDto<VoucherPojo> getVouchers(
			@RequestParam(required = false) String voucherDateFromValue,
			@RequestParam(required = false) String voucherDateToValue){
		ListOutputDto<VoucherPojo> r = new ListOutputDto<VoucherPojo>();

		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(voucherDateFromValue), "La fecha de Voucher desde es requerida");
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(voucherDateFromValue), "La fecha de Voucher hasta es requerida");
		
		List<BillDto> bills = this.billService.searchToMakeVouchers(voucherDateFromValue, voucherDateToValue);
		
		List<VoucherPojo> list = Lists.newArrayList();
		
		list.addAll(this.billToVoucherTransformer.transform(bills, DateUtils.parseDate(voucherDateToValue)));
		
		r.setData(list);
		
		return r;
	}

	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void exportVouchers(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam(required = false) String voucherDateFromValue,
			@RequestParam(required = false) String voucherDateToValue){
		Map<String, Object> params = Maps.newHashMap();
		
		List<BillDto> bills = this.billService.searchToMakeVouchers(voucherDateFromValue, voucherDateToValue);
		
		List<VoucherPojo> list = Lists.newArrayList();
		
		list.addAll(this.billToVoucherTransformer.transform(bills, DateUtils.parseDate(voucherDateToValue)));
		
		// TODO [roher] tngo q hacer el transformer de bill -> a voucher 
		try {
			String fullpath = this.servletContext.getRealPath("/WEB-INF/jasper/voucher2.jasper");

			InputStream is = new FileInputStream(fullpath);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(is, params, 
					new JRBeanCollectionDataSource(list));
			response.setContentType("application/pdf");
			String filename = "vouchers-" + System.currentTimeMillis() + ".pdf";
			response.addHeader("Content-disposition", "attachment; filename=" + filename); 
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		} catch (Exception e) {
			LOGGER.error("Error inesperado al generar vouchers de descuento", e);
			throw new ErrorTypedException("Ha ocurrido un error al tratar generar los vouchers de descuento", 
					ErrorType.UNKNOWN_ERROR);
		}
		
	}
	
}
