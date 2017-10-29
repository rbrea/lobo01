package com.icetea.manager.pagodiario.xls;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.icetea.manager.pagodiario.api.dto.ProductDto;
import com.icetea.manager.pagodiario.utils.Counter;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class ProductExcelView extends AbstractXlsView {

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
        
        List<ProductDto> list = (List<ProductDto>) model.get("list");
        
        BigDecimal totPrice = BigDecimal.ZERO;
        int totStock = 0;
        
        for(ProductDto payment : list){
        	totPrice = NumberUtils.add(totPrice, payment.getPrice());
        	totStock += payment.getStockCount();
        	this.setRow(sheet, counter, payment);
        }
        this.setTotalRow(sheet, counter, totPrice, totStock);
	}
	
	protected void setRow(Sheet sheet, Counter counter, ProductDto dto){
		Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue(dto.getCode());
		cell = row.createCell(1);
		cell.setCellValue(dto.getDescription());
		cell = row.createCell(2);
		cell.setCellValue(dto.getPrice());
		cell = row.createCell(3);
		cell.setCellValue(dto.getDailyInstallment());
		cell = row.createCell(4);
		cell.setCellValue(dto.getWeekInstallment());
		cell = row.createCell(5);
		cell.setCellValue(dto.getTwoWeeksInstallment());
		cell = row.createCell(6);
		cell.setCellValue(dto.getPriceWithDiscount());
		cell = row.createCell(7);
		cell.setCellValue(dto.getProductTypeDescription());
		cell = row.createCell(8);
		cell.setCellValue(dto.getStockCount());
	}
	protected void setHeaderRow(Sheet sheet, Counter counter){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue("CODIGO");
		cell = row.createCell(1);
		cell.setCellValue("DESCRIPCION");
		cell = row.createCell(2);
		cell.setCellValue("PRECIO");
		cell = row.createCell(3);
		cell.setCellValue("CUOTA DIARIA");
		cell = row.createCell(4);
		cell.setCellValue("CUOTA SEMANAL");
		cell = row.createCell(5);
		cell.setCellValue("CUOTA QUINCENAL");
		cell = row.createCell(6);
		cell.setCellValue("PRECIO CON DESCUENTO");
		cell = row.createCell(7);
		cell.setCellValue("TIPO PRODUCTO");
		cell = row.createCell(8);
		cell.setCellValue("STOCK");
    }
	
	protected void setTitleRow(Sheet sheet, Counter counter){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue("Reporte de Productos");
    }
	
	protected void setDateRow(Sheet sheet, Counter counter){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue("FECHA: " + DateUtils.currentDate());
    }
	
	protected void setTotalRow(Sheet sheet, Counter counter, BigDecimal price, int totStock){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue("TOTALES");
		cell = row.createCell(1);
		cell.setCellValue("");
		cell = row.createCell(2);
		cell.setCellValue(NumberUtils.toString(price));
		cell = row.createCell(3);
		cell.setCellValue("");
		cell = row.createCell(4);
		cell.setCellValue("");
		cell = row.createCell(5);
		cell.setCellValue("");
		cell = row.createCell(6);
		cell.setCellValue("");
		cell = row.createCell(7);
		cell.setCellValue("");
		cell = row.createCell(8);
		cell.setCellValue(totStock);
    }

}
