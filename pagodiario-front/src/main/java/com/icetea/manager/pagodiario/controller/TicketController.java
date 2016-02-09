package com.icetea.manager.pagodiario.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.icetea.manager.pagodiario.api.dto.BasicOutputDto;
import com.icetea.manager.pagodiario.api.dto.FileMeta;
import com.icetea.manager.pagodiario.api.dto.FileResponseDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.api.pojo.jasper.BillTicketPojo;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.exception.ErrorTypedException;
import com.icetea.manager.pagodiario.factory.TicketNumberListFactory;
import com.icetea.manager.pagodiario.manager.FilePrintManager;
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
	@Inject
	private TicketNumberListFactory ticketNumberListFactory;
	@Inject
	private FilePrintManager filePrintManager;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String showForm() {

		return "ticket";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public void doGenerate(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) String ticketDateValue,
			@RequestParam(required = false) Long zone,
			@RequestParam(required = false) String fecDesdeValue,
			@RequestParam(required = false) String fecHastaValue,
			@RequestParam(required = false) String printKey) {
		
		Map<String, Object> params = Maps.newHashMap();

		List<BillTicketPojo> list = Lists.newArrayList();
		if(StringUtils.isNotBlank(printKey)){
			list = this.filePrintManager.get(printKey);
			
			if(list == null || list.isEmpty()){
				throw new ErrorTypedException(
						"Ha ocurrido un error al tratar generar los tickets de cobro, la lista proveniente del archivo es vacía.",
						ErrorType.UNKNOWN_ERROR);
			}
		} else {
			list = this.billService.searchBillsByCollectorZone(
					ticketDateValue, zone, fecDesdeValue, fecHastaValue);
		}

		try {
			String fullpath = this.servletContext
					.getRealPath("/WEB-INF/jasper/sgpd_ticket.jasper");
			// [roher] otra forma de hacerlo ... x ahora uso directamente el
			// jasper, parece que es mas rapido ...
			// JasperDesign jasperDesign = JRXmlLoader.load(fullpath);
			// JasperReport jasperReport =
			// JasperCompileManager.compileReport(jasperDesign);
			InputStream is = new FileInputStream(fullpath);

			DefaultJasperReportsContext context = DefaultJasperReportsContext
					.getInstance();
			JRPropertiesUtil.getInstance(context).setProperty(
					"net.sf.jasperreports.default.font.name", "Arial");
			JRPropertiesUtil.getInstance(context).setProperty(
					"net.sf.jasperreports.default.pdf.embedded", "true");
			JRPropertiesUtil.getInstance(context).setProperty(
					"net.sf.jasperreports.default.pdf.font.name", "Arial");

			JasperPrint jasperPrint = JasperFillManager.fillReport(is, params,
					new JRBeanCollectionDataSource(list));
			response.setContentType("application/pdf");
			String filename = "tickets-cobro-" + System.currentTimeMillis()
					+ ".pdf";
			response.addHeader("Content-disposition", "attachment; filename="
					+ filename);

			JasperExportManager.exportReportToPdfStream(jasperPrint,
					response.getOutputStream());
		} catch (Exception e) {
			LOGGER.error(
					"Error inesperado al tratar de generar el pdf de tickets de cobro",
					e);
			throw new ErrorTypedException(
					"Ha ocurrido un error al tratar generar los tickets de cobro",
					ErrorType.UNKNOWN_ERROR);
		}

	}

	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public BasicOutputDto validate(
			@RequestParam(required = false) String ticketDateValue,
			@RequestParam(required = false) Long collectorId,
			@RequestParam(required = false) String fecDesdeValue,
			@RequestParam(required = false) String fecHastaValue) {

		BasicOutputDto d = new BasicOutputDto();
		d.setStatus(0);

		List<BillTicketPojo> list = this.billService
				.searchBillsByCollectorZone(ticketDateValue, collectorId,
						fecDesdeValue, fecHastaValue);

		if (list == null || list.isEmpty()) {
			throw new ErrorTypedException(
					"No se registran tickets para la zona: " + collectorId,
					ErrorType.VALIDATION_ERRORS);
		}

		return d;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody FileResponseDto doUpload(HttpServletResponse response,
			MultipartHttpServletRequest request) {

		FileResponseDto resp = new FileResponseDto();
		
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();

		FileMeta fileMeta = null;
		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		List<Long> ticketNumberList = Lists.newArrayList();
		
		// 2. get each file
		while (itr.hasNext()) {

			// 2.1 get next MultipartFile
			mpf = request.getFile(itr.next());
			LOGGER.debug(mpf.getOriginalFilename() + " uploaded! "
					+ files.size());

			// 2.2 if files > 10 remove the first from the list
			if (files.size() >= 10)
				files.pop();

			// 2.3 create new fileMeta
			fileMeta = new FileMeta();
			fileMeta.setFileName(mpf.getOriginalFilename());
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());
			try {
				fileMeta.setBytes(mpf.getBytes());
			} catch (IOException e) {
				LOGGER.error("Unhandled IOEXception", e);
			}
			
			ticketNumberList.addAll(this.ticketNumberListFactory.build(mpf));

			// 2.4 add to files
			files.add(fileMeta);
		}

		ErrorTypedConditions.checkArgument(!ticketNumberList.isEmpty(), "El archivo está vacío. Pruebe nuevamente.");
		
		List<BillTicketPojo> list = this.billService.searchBillsByCreditNumber(ticketNumberList);

		String printKey = this.filePrintManager.putValue(list);
		
		resp.setPrintKey(printKey);
		
		return resp;
	}
	
}
