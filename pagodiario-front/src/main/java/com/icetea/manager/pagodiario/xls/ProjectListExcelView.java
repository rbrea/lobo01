package com.icetea.manager.pagodiario.xls;

import java.util.Map;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

@Named
public class ProjectListExcelView extends AbstractExcelView {


	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		this.setContentType("application/msexcel");
		HSSFSheet excelSheet = workbook.createSheet("Project XLS Result");
		excelSheet.setDefaultColumnWidth(16);
//		this.setExcelHeader(excelSheet, workbook);
		
//		List<ProjectDto> projects = (List<ProjectDto>) model.get("projects");
//		this.setExcelRows(excelSheet, projects);
	}
	
//	private static CellStyle createBorderedStyle(Workbook wb) {
//        CellStyle style = wb.createCellStyle();
//        style.setBorderRight(CellStyle.BORDER_THIN);
//        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
//        style.setBorderBottom(CellStyle.BORDER_THIN);
//        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
//        style.setBorderLeft(CellStyle.BORDER_THIN);
//        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
//        style.setBorderTop(CellStyle.BORDER_THIN);
//        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
//     
//        return style;
//    }
//
//	public void setExcelHeader(HSSFSheet excelSheet, Workbook wb) {
//		HSSFRow excelHeader = excelSheet.createRow(0);
//		
//		Font headerFont = wb.createFont();
//        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
//        headerFont.setFontHeightInPoints((short) 12);
//        
//		CellStyle style = createBorderedStyle(wb);
//        style.setAlignment(CellStyle.ALIGN_CENTER);
//        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        style.setFont(headerFont);
//
//        excelHeader.setRowStyle(style);
//		
//        excelHeader.createCell(0).setCellValue("Nombre de Proyecto");
//		excelHeader.createCell(1).setCellValue("Versión");
//		excelHeader.createCell(2).setCellValue("Nombre de Tabla");
//		excelHeader.createCell(3).setCellValue("Nombre de Columna");
//		excelHeader.createCell(4).setCellValue("Tipo");
//		excelHeader.createCell(5).setCellValue("Tamaño");
//		excelHeader.createCell(6).setCellValue("Es Unique?");
//		excelHeader.createCell(7).setCellValue("Es Nullable?");
//		excelHeader.createCell(8).setCellValue("Es PK?");
//		excelHeader.createCell(9).setCellValue("Es FK?");
//		excelHeader.createCell(10).setCellValue("Descripción");
//	}
//	
//	public void setExcelRows(HSSFSheet excelSheet, List<ProjectDto> clients){
//		int record = 1;
//		List<ProjectXlsRow> rows = this.projectXlsRowFactory.createList(clients);
//		for(ProjectXlsRow row : rows){
//			
//			this.addRowToSheet(excelSheet, record++, row.getProjectName(), row.getVersion(), 
//					row.getTableName(), row.getName(), row.getType(), 
//					row.getSize(), row.getUnique(), 
//					row.getNullable(), row.getPk(), row.getFk(), row.getDescription());
//		}
//		
//	}
//	
//	public void addRowToSheet(HSSFSheet excelSheet, final int rowNumber, 
//			String projectName, String version, String tableName, 
//			String columnName, String type, String size, String unique, String nullable, 
//			String pk, String fk, String description){
//		HSSFRow excelRow = excelSheet.createRow(rowNumber);
//		excelRow.createCell(0).setCellValue(projectName);
//		excelRow.createCell(1).setCellValue(version);
//		excelRow.createCell(2).setCellValue(tableName);
//		excelRow.createCell(3).setCellValue(columnName);
//		excelRow.createCell(4).setCellValue(type);
//		excelRow.createCell(5).setCellValue(size);
//		excelRow.createCell(6).setCellValue(unique);
//		excelRow.createCell(7).setCellValue(nullable);
//		excelRow.createCell(8).setCellValue(pk);
//		excelRow.createCell(9).setCellValue(fk);
//		excelRow.createCell(10).setCellValue(description);
//	}
	
}
