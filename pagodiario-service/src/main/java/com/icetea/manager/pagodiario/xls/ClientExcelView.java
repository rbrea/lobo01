package com.icetea.manager.pagodiario.xls;

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

import com.icetea.manager.pagodiario.api.dto.ClientDto;
import com.icetea.manager.pagodiario.utils.Counter;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.StringUtils;

@Named
public class ClientExcelView extends AbstractXlsView {

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
        
        List<ClientDto> list = (List<ClientDto>) model.get("list");
        
        for(ClientDto l : list){
        	this.setRow(sheet, counter, l);
        }

	}

	protected void setRow(Sheet sheet, Counter counter, ClientDto dto){
		Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue(dto.getId());
		cell = row.createCell(1);
		cell.setCellValue(dto.getName());
		cell = row.createCell(2);
		cell.setCellValue(StringUtils.nvl(dto.getDocumentNumber()));
		cell = row.createCell(3);
		cell.setCellValue(StringUtils.nvl(dto.getEmail()));
		cell = row.createCell(4);
		cell.setCellValue(StringUtils.nvl(dto.getCompanyPhone()));
		cell = row.createCell(5);
		cell.setCellValue(StringUtils.nvl(dto.getCompanyAddress()));
		cell = row.createCell(6);
		cell.setCellValue(StringUtils.nvl(dto.getNearStreets()));
		cell = row.createCell(7);
		cell.setCellValue(StringUtils.nvl(dto.getCompanyCity()));
		cell = row.createCell(8);
		cell.setCellValue(StringUtils.nvl(dto.getCompanyType()));
		cell = row.createCell(9);
		cell.setCellValue(StringUtils.nvl(dto.getPhone()));
		cell = row.createCell(10);
		cell.setCellValue(StringUtils.nvl(dto.getAddress()));
		cell = row.createCell(11);
		cell.setCellValue(StringUtils.nvl(dto.getCity()));
		cell = row.createCell(12);
		cell.setCellValue(StringUtils.nvl(dto.getReductionMark()));
		cell = row.createCell(13);
		cell.setCellValue(StringUtils.nvl(dto.getCancelationMark()));
	}
	protected void setHeaderRow(Sheet sheet, Counter counter){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue("CODIGO");
		cell = row.createCell(1);
		cell.setCellValue("NOMBRE Y APELLIDO");
		cell = row.createCell(2);
		cell.setCellValue("DNI");
		cell = row.createCell(3);
		cell.setCellValue("EMAIL");
		cell = row.createCell(4);
		cell.setCellValue("TEL COMERCIO");
		cell = row.createCell(5);
		cell.setCellValue("DOMICILIO COMERCIO");
		cell = row.createCell(6);
		cell.setCellValue("ENTRE CALLES COMERCIO");
		cell = row.createCell(7);
		cell.setCellValue("LOCALIDAD/BARRIO COMERCIO");
		cell = row.createCell(8);
		cell.setCellValue("TIPO COMERCIO");
		cell = row.createCell(9);
		cell.setCellValue("TELEFONO PARTICULAR");
		cell = row.createCell(10);
		cell.setCellValue("DOMICILIO PARTICULAR");
		cell = row.createCell(11);
		cell.setCellValue("LOCALIDAD/BARRIO PARTICULAR");
		cell = row.createCell(12);
		cell.setCellValue("TUVO BAJA?");
		cell = row.createCell(13);
		cell.setCellValue("CANCELÓ?");
    }
	
	protected void setTitleRow(Sheet sheet, Counter counter){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue("Reporte de Clientes");
    }
	
	protected void setDateRow(Sheet sheet, Counter counter){
    	Row row = sheet.createRow(counter.increment());
    	Cell cell = row.createCell(0);
		cell.setCellValue("FECHA: " + DateUtils.currentDate());
    }
	
}
