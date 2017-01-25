package com.travelex.framework.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;






import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readExcelData {
		
	String absolutePath = new File("").getAbsolutePath();
	String filePath = absolutePath+"/src/test/resources/DataSheet/Data.xlsx";
	
	 public HashMap<String,String> getAllColumnFromRow(String strField) throws IOException{
		 HashMap<String,String> data = new HashMap<String,String>();
			
			FileInputStream input = new FileInputStream(new File(filePath));
			Workbook wb = new XSSFWorkbook(input);
			
			XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(0);
			Iterator<Row> rowItr = sheet.iterator();
			XSSFRow row;
		 
		    String cellValue = "";
		    String information = "";
		    
		    while(rowItr.hasNext()){
		    	
		    	int headerCellCount = 0;
		    	row=(XSSFRow) rowItr.next();
		    	 Iterator<Cell> itr = row.iterator();
				   XSSFRow headerRow = row.getSheet().getRow(0);
				   if(row.getCell(0).toString().equalsIgnoreCase(strField)){
		    while(itr.hasNext()){
		        Cell cell = itr.next();
		        Cell headerValue = headerRow.getCell(headerCellCount++);
		        switch(cell.getCellType()) {
		        case XSSFCell.CELL_TYPE_BOOLEAN:
		            cellValue = cell.getBooleanCellValue() +"";
		            information = information + " " +headerValue+" - "+cellValue+ "; ";
		            break;
		        case XSSFCell.CELL_TYPE_NUMERIC:
		            cellValue = cell.getNumericCellValue() + "";
		            information = information + " " +headerValue+" - "+cellValue+ "; ";
		            break;
		        case XSSFCell.CELL_TYPE_STRING:
		            cellValue = cell.getStringCellValue();
		            information = information + " " +headerValue+" - "+cellValue+ "; ";
		            break;
		        case XSSFCell.CELL_TYPE_BLANK:
		            break;
		        }
		        String headVal = headerValue.toString(); 
		        data.put(headVal, cellValue);
		    }
		    }
		}
		    input.close();
		    return data;
	 }
	
	
	 @SuppressWarnings("unused")
	 public void updateData(String sheetName,String autoID,HashMap<String,String> ColumnValue) throws IOException{
		 int noOfRows = 0;
		 File file = null;
		 FileOutputStream fOut = null;
		 XSSFWorkbook xlWBook = null;

		 try{
			 file = new File(filePath);
			 FileInputStream fInp = new FileInputStream(file);

			 xlWBook = new XSSFWorkbook(fInp);
			 XSSFSheet xlSheet = xlWBook.getSheet(sheetName);
			 XSSFRow xlRow = xlSheet.getRow(0);
			 noOfRows = xlSheet.getPhysicalNumberOfRows();
			 int noOfColumns = xlRow.getLastCellNum();
			 Cell cell = null;

			 Iterator<Row> rIte = xlSheet.iterator();
			 int counter = 0;
			 
			 int rowCounter = 1;

			 XSSFRow headerRow = xlRow.getSheet().getRow(0);
			 Iterator<Cell> cellIt = headerRow.cellIterator();
			 			 
			 for(int mainRow = 1; mainRow<noOfRows; mainRow++){
				 if(xlSheet.getRow(mainRow).getCell(0).getStringCellValue().equalsIgnoreCase(autoID)){
					 while(headerRow.cellIterator().hasNext()){
						 String headerVal = headerRow.getCell(counter).toString();
						 if(ColumnValue.containsKey(headerVal)){

							 cell = xlSheet.getRow(mainRow).getCell(rowCounter-1);
							 cell.setCellValue(ColumnValue.get(headerVal));;
						 }
						 counter++;
						 rowCounter++;
						 if(counter==7){
							 break;
						 }
					 }
				 }
			 }	

		 }catch(Exception e){
			 System.out.println("Could not uodate data "+ e.getMessage());
		 }finally{
			 fOut = new FileOutputStream(file);
			 xlWBook.write(fOut);
			 fOut.close();	
		 }

	 }
}
