package com.icetea.manager.pagodiario.xls.bill;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.utils.Counter;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class BillExcelView extends AbstractXlsxView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setContentType("application/msexcel");
        Sheet sheet = workbook.createSheet();
        sheet.setDefaultColumnWidth(16);
        
        Counter counter = new Counter();
        
        this.setTitleRow(sheet, counter);
        this.setDateRow(sheet, counter);
        this.setHeaderRow(sheet, counter);
        
        List<BillDto> list = (List<BillDto>) model.get("list");
        BigDecimal totDaily = BigDecimal.ZERO; 
        BigDecimal totAmount = BigDecimal.ZERO;
        BigDecimal totRemaining = BigDecimal.ZERO;
        for(BillDto bill : list){
        	totDaily = NumberUtils.add(totDaily, bill.getTotalDailyInstallment());
        	totAmount = NumberUtils.add(totAmount, bill.getTotalAmount());
        	totRemaining = NumberUtils.add(totRemaining, bill.getRemainingAmount());
        	this.setRow(sheet, counter, bill);
        }
        this.setTotalRow(sheet, counter, totDaily, totAmount, totRemaining);
	}
	
	protected void setRow(Sheet sheet, Counter counter, BillDto bill){
		Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue(bill.getCreditNumber());
		cell = row.createCell(1);
		cell.setCellValue(bill.getStartDate());
		cell = row.createCell(2);
		cell.setCellValue(bill.getEndDate());
		cell = row.createCell(3);
		cell.setCellValue(bill.getCollectorZone() + " / " + bill.getCollectorDescription());
		cell = row.createCell(4);
		cell.setCellValue(bill.getOverdueDays());
		cell = row.createCell(5);
		cell.setCellValue(bill.getTotalDailyInstallment());
		cell = row.createCell(6);
		cell.setCellValue(bill.getTotalAmount());
		cell = row.createCell(7);
		cell.setCellValue(bill.getRemainingAmount());
		cell = row.createCell(8);
		cell.setCellValue(bill.getStatus());
		cell = row.createCell(9);
		cell.setCellValue(bill.getPayrollDate());
		cell = row.createCell(10);
		cell.setCellValue(BooleanUtils.toString(bill.getDevTotalMark(), "SI", "NO", "-"));
		cell = row.createCell(11);
		cell.setCellValue(bill.getTraderName());
		cell = row.createCell(12);
		cell.setCellValue(bill.getTraderPhone());
		cell = row.createCell(13);
		cell.setCellValue(bill.getClientName());
	}
	
	protected void setHeaderRow(Sheet sheet, Counter counter){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue("NRO CREDITO");
		cell = row.createCell(1);
		cell.setCellValue("FECHA INICIO");
		cell = row.createCell(2);
		cell.setCellValue("FECHA FIN");
		cell = row.createCell(3);
		cell.setCellValue("ZONA/COBRADOR");
		cell = row.createCell(4);
		cell.setCellValue("DIAS ATRASO");
		cell = row.createCell(5);
		cell.setCellValue("$ CUOTA DIARIA");
		cell = row.createCell(6);
		cell.setCellValue("IMPORTE TOTAL");
		cell = row.createCell(7);
		cell.setCellValue("SALDO RESTANTE");
		cell = row.createCell(8);
		cell.setCellValue("ESTADO");
		cell = row.createCell(9);
		cell.setCellValue("FECHA LIQUIDACION");
		cell = row.createCell(10);
		cell.setCellValue("DEV TOTAL?");
		cell = row.createCell(11);
		cell.setCellValue("VENDEDOR");
		cell = row.createCell(12);
		cell.setCellValue("VENDEDOR TEL");
		cell = row.createCell(13);
		cell.setCellValue("CLIENTE");
    }
	
	protected void setTitleRow(Sheet sheet, Counter counter){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue("Reporte de Créditos Activos");
    }
	
	protected void setDateRow(Sheet sheet, Counter counter){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue("FECHA: " + DateUtils.currentDate());
    }
	
	protected void setTotalRow(Sheet sheet, Counter counter, BigDecimal totDaily, BigDecimal totAmount, BigDecimal totRemaining){
		Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue("");
		cell = row.createCell(1);
		cell.setCellValue("");
		cell = row.createCell(2);
		cell.setCellValue("");
		cell = row.createCell(3);
		cell.setCellValue("");
		cell = row.createCell(4);
		cell.setCellValue("TOTALES: ");
		cell = row.createCell(5);
		cell.setCellValue(NumberUtils.toString(totDaily));
		cell = row.createCell(6);
		cell.setCellValue(NumberUtils.toString(totAmount));
		cell = row.createCell(7);
		cell.setCellValue(NumberUtils.toString(totRemaining));
		cell = row.createCell(8);
		cell.setCellValue("");
		cell = row.createCell(9);
		cell.setCellValue("");
		cell = row.createCell(10);
		cell.setCellValue("");
		cell = row.createCell(11);
		cell.setCellValue("");
		cell = row.createCell(12);
		cell.setCellValue("");
		cell = row.createCell(13);
		cell.setCellValue("");
    }
	
}
