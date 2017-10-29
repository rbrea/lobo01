package com.icetea.manager.pagodiario.xls;

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
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.icetea.manager.pagodiario.api.dto.PaymentDto;
import com.icetea.manager.pagodiario.utils.Counter;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class PaymentExcelView extends AbstractXlsView {

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
        
        List<PaymentDto> list = (List<PaymentDto>) model.get("list");
        
        BigDecimal amount = BigDecimal.ZERO;
        
        for(PaymentDto payment : list){
        	this.setRow(sheet, counter, payment);
        	amount = NumberUtils.add(amount, payment.getAmount());
        }
        
        this.setTotalRow(sheet, counter, amount);
	}

	protected void setRow(Sheet sheet, Counter counter, PaymentDto payment){
		Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue(payment.getId());
		cell = row.createCell(1);
		cell.setCellValue(payment.getCreditNumber());
		cell = row.createCell(2);
		cell.setCellValue(payment.getAmount());
		cell = row.createCell(3);
		cell.setCellValue(payment.getDate());
		cell = row.createCell(4);
		cell.setCellValue(payment.getCollectorZone());
		cell = row.createCell(5);
		cell.setCellValue(payment.getCollectorDescription());
		cell = row.createCell(6);
		cell.setCellValue(BooleanUtils.toString(payment.isTraderPayment(), "Si", "No", "No"));
		cell = row.createCell(7);
		cell.setCellValue(payment.getClientName());
	}
	
	protected void setHeaderRow(Sheet sheet, Counter counter){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue("ID");
		cell = row.createCell(1);
		cell.setCellValue("NRO DE CREDITO");
		cell = row.createCell(2);
		cell.setCellValue("MONTO");
		cell = row.createCell(3);
		cell.setCellValue("FECHA");
		cell = row.createCell(4);
		cell.setCellValue("ZONA");
		cell = row.createCell(5);
		cell.setCellValue("COBRADOR");
		cell = row.createCell(6);
		cell.setCellValue("PAGO VENDEDOR?");
		cell = row.createCell(7);
		cell.setCellValue("CLIENTE");
    }
	
	protected void setTitleRow(Sheet sheet, Counter counter){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue("Reporte de Pagos");
    }
	
	protected void setDateRow(Sheet sheet, Counter counter){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue("FECHA: " + DateUtils.currentDate());
    }
	
	protected void setTotalRow(Sheet sheet, Counter counter, BigDecimal amount){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue("TOTAL: ");
		cell = row.createCell(1);
		cell.setCellValue(NumberUtils.toString(amount));
    }
	
}
