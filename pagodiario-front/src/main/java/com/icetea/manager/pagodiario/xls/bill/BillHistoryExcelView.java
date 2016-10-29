package com.icetea.manager.pagodiario.xls.bill;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.icetea.manager.pagodiario.api.dto.BillDetailDevolutionDto;
import com.icetea.manager.pagodiario.api.dto.BillDetailDiscountDto;
import com.icetea.manager.pagodiario.api.dto.BillDetailDto;
import com.icetea.manager.pagodiario.api.dto.BillDetailPaymentDto;
import com.icetea.manager.pagodiario.api.dto.BillDetailReductionDto;
import com.icetea.manager.pagodiario.api.dto.BillProductDetailDto;
import com.icetea.manager.pagodiario.utils.Counter;
import com.icetea.manager.pagodiario.utils.DateUtils;

public class BillHistoryExcelView
    extends AbstractXlsxView {

    public BillHistoryExcelView() {
		super();
	}

	@SuppressWarnings("unchecked")
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        this.setContentType("application/msexcel");
        Sheet sheet = workbook.createSheet();
        sheet.setDefaultColumnWidth(16);
        
        Font font= workbook.createFont();
        font.setFontHeightInPoints((short)10);
        font.setFontName("Arial");
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setItalic(false);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        Counter counter = new Counter();
        
        List<BillDetailDto> list = (List<BillDetailDto>) model.get("list");
        for(BillDetailDto d : list){
        	this.setDateRow(sheet, counter, font);
        	this.setCreditNumberRow(sheet, counter, d.getCreditNumber(), font);
        	sheet.createRow(counter.increment());
        	this.setDetailTable(sheet, counter, d, font);
        	sheet.createRow(counter.increment());
        	this.setProductRows(sheet, counter, d.getProducts());
        	sheet.createRow(counter.increment());
        	this.setPaymentRows(sheet, counter, d.getPayments());
        	sheet.createRow(counter.increment());
        	this.setDevsRows(sheet, counter, d.getDevolutions());
        	sheet.createRow(counter.increment());
        	this.setDiscountRows(sheet, counter, d.getDiscounts());
        	sheet.createRow(counter.increment());
        	this.setReductionRows(sheet, counter, d.getReductions());
        }
    }
	
	protected void setProductRows(Sheet sheet, Counter counter, List<BillProductDetailDto> products){
    	Row row = sheet.createRow(counter.increment());
    	row.createCell(0).setCellValue("PRODUCTOS");
    	row = sheet.createRow(counter.increment());
    	row.createCell(0).setCellValue("CANTIDAD");
    	row.createCell(1).setCellValue("CODIGO");
    	row.createCell(2).setCellValue("DESCRIPCION");
    	row.createCell(3).setCellValue("CUOTA DIARIA");
    	row.createCell(4).setCellValue("TOTAL");
    	
    	if(products != null){
    		for(BillProductDetailDto k : products){
    			row = sheet.createRow(counter.increment());
    			row.createCell(0).setCellValue(k.getCount());
    			row.createCell(1).setCellValue(k.getCodProducto());
    			row.createCell(2).setCellValue(k.getDescription());
    			row.createCell(3).setCellValue(k.getInstallmentAmount());
    			row.createCell(4).setCellValue(k.getTotalAmount());
    		}
    	}
    }
	
	protected void setPaymentRows(Sheet sheet, Counter counter, List<BillDetailPaymentDto> list){
    	Row row = sheet.createRow(counter.increment());
    	row.createCell(0).setCellValue("PAGOS");
    	row = sheet.createRow(counter.increment());
    	row.createCell(0).setCellValue("FECHA");
    	row.createCell(1).setCellValue("COBRADOR");
    	row.createCell(2).setCellValue("IMPORTE");
    	row.createCell(3).setCellValue("PAGO VENDEDOR?");
    	
    	if(list != null){
    		for(BillDetailPaymentDto k : list){
    			row = sheet.createRow(counter.increment());
    			row.createCell(0).setCellValue(k.getDate());
    			row.createCell(1).setCellValue(k.getCollector());
    			row.createCell(2).setCellValue(k.getAmount());
    			row.createCell(3).setCellValue(k.getTraderPayment());
    		}
    	}
    }
	
	protected void setDevsRows(Sheet sheet, Counter counter, List<BillDetailDevolutionDto> list){
    	Row row = sheet.createRow(counter.increment());
    	row.createCell(0).setCellValue("DEVOLUCIONES");
    	row = sheet.createRow(counter.increment());
    	row.createCell(0).setCellValue("FECHA");
    	row.createCell(1).setCellValue("CANT");
    	row.createCell(2).setCellValue("DESCRIPCION");
    	row.createCell(3).setCellValue("IMPORTE");
    	row.createCell(4).setCellValue("CUOTA");
    	
    	if(list != null){
    		for(BillDetailDevolutionDto k : list){
    			row = sheet.createRow(counter.increment());
    			row.createCell(0).setCellValue(k.getDate());
    			row.createCell(1).setCellValue(k.getCount());
    			row.createCell(2).setCellValue(k.getProductDescription());
    			row.createCell(3).setCellValue(k.getAmount());
    			row.createCell(4).setCellValue(k.getInstallmentAmount());
    		}
    	}
    }
	
	protected void setDiscountRows(Sheet sheet, Counter counter, List<BillDetailDiscountDto> list){
    	Row row = sheet.createRow(counter.increment());
    	row.createCell(0).setCellValue("DESCUENTOS");
    	row = sheet.createRow(counter.increment());
    	row.createCell(0).setCellValue("FECHA");
    	row.createCell(1).setCellValue("IMPORTE");
    	row.createCell(2).setCellValue("OBSERVACIONES");
    	
    	if(list != null){
    		for(BillDetailDiscountDto k : list){
    			row = sheet.createRow(counter.increment());
    			row.createCell(0).setCellValue(k.getDate());
    			row.createCell(1).setCellValue(k.getAmount());
    			row.createCell(2).setCellValue(k.getObservations());
    		}
    	}
    }
	
	protected void setReductionRows(Sheet sheet, Counter counter, List<BillDetailReductionDto> list){
    	Row row = sheet.createRow(counter.increment());
    	row.createCell(0).setCellValue("BAJAS");
    	row = sheet.createRow(counter.increment());
    	row.createCell(0).setCellValue("FECHA");
    	row.createCell(1).setCellValue("IMPORTE");
    	row.createCell(2).setCellValue("OBSERVACIONES");
    	
    	if(list != null){
    		for(BillDetailReductionDto k : list){
    			row = sheet.createRow(counter.increment());
    			row.createCell(0).setCellValue(k.getDate());
    			row.createCell(1).setCellValue(k.getAmount());
    			row.createCell(2).setCellValue(k.getObservations());
    		}
    	}
    }
	
	protected void setDateRow(Sheet sheet, Counter counter, Font font){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
    	CellStyle style = cell.getCellStyle();
    	style.setFont(font);
		cell.setCellValue("FECHA: " + DateUtils.currentDate());
    }
	
    protected void setCreditNumberRow(Sheet sheet, Counter counter, String creditNumber, Font font){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
    	CellStyle style = cell.getCellStyle();
    	style.setFont(font);
		cell.setCellValue("DETALLE DE CREDITO: " + creditNumber);
    }
    
    protected void setDetailTable(Sheet sheet, Counter counter, BillDetailDto dto, Font font){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
    	cell.setCellValue("Cliente");
    	CellStyle style = cell.getCellStyle();
    	style.setFont(font);
    	row.createCell(1).setCellValue(dto.getClientName());
    	row = sheet.createRow(counter.increment());
    	cell = row.createCell(0);
    	style = cell.getCellStyle();
    	style.setFont(font);
    	cell.setCellValue("Domicilio");
    	row.createCell(1).setCellValue(dto.getClientAddress());
    	row = sheet.createRow(counter.increment());
    	cell = row.createCell(0);
    	style = cell.getCellStyle();
    	style.setFont(font);
    	cell.setCellValue("Tipo Comercio");
    	row.createCell(1).setCellValue(dto.getCustomerCompanyType());
    	row = sheet.createRow(counter.increment());
    	cell = row.createCell(0);
    	cell.setCellValue("Zona/Cobrador");
    	row.createCell(1).setCellValue("Cod: " + dto.getCollectorId() + " - " + dto.getCollectorDescription());
    	row = sheet.createRow(counter.increment());
    	cell = row.createCell(0);
    	cell.setCellValue("Vendedor");
    	row.createCell(1).setCellValue(dto.getTraderName());
    	row = sheet.createRow(counter.increment());
    	cell = row.createCell(0);
    	cell.setCellValue("Fecha Credito");
    	row.createCell(1).setCellValue(dto.getCreditDate());
    	row = sheet.createRow(counter.increment());
    	cell = row.createCell(0);
    	cell.setCellValue("Fecha Credito");
    	row.createCell(1).setCellValue(dto.getCreditDate());
    	row = sheet.createRow(counter.increment());
    	cell = row.createCell(0);
    	cell.setCellValue("Importe Credito");
    	row.createCell(1).setCellValue(dto.getCreditAmount());
    	row = sheet.createRow(counter.increment());
    	cell = row.createCell(0);
    	cell.setCellValue("Total Abonado");
    	row.createCell(1).setCellValue(dto.getCurrentAmount());
    	row = sheet.createRow(counter.increment());
    	cell = row.createCell(0);
    	cell.setCellValue("Cuota");
    	row.createCell(1).setCellValue(dto.getInstallmentAmount());
    	row = sheet.createRow(counter.increment());
    	cell = row.createCell(0);
    	cell.setCellValue("Pago Semanal");
    	row.createCell(1).setCellValue(dto.getWeekAmount());
    	row = sheet.createRow(counter.increment());
    	cell = row.createCell(0);
    	cell.setCellValue("Pago Primer Cuota");
    	row.createCell(1).setCellValue(dto.getFirstInstallmentAmount());
    	row = sheet.createRow(counter.increment());
    	cell = row.createCell(0);
    	cell.setCellValue("Estado");
    	row.createCell(1).setCellValue(dto.getStatus());
    	row = sheet.createRow(counter.increment());
    	cell = row.createCell(0);
    	cell.setCellValue("Fecha Pago Total");
    	row.createCell(1).setCellValue(dto.getCompletedDate());
    }

    @Override
    protected Workbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
        return new XSSFWorkbook();
    }

}
