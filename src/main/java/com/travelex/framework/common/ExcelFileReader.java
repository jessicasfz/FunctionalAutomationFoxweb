package com.travelex.framework.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader {	


	public static Map<String, String> readDataForAutomationID(String sheetName,String automationID) throws IOException{		
		ConfigurationProperties configurationProperties = new ConfigurationProperties();  
		String excelFilePath = configurationProperties.getProperty(ConfigurationProperties.Test_Data_Folder_Path);  
		excelFilePath = System.getProperty("user.dir") + excelFilePath;
		FileInputStream inputStream = new FileInputStream(excelFilePath);
		Map<String, String> hashmapObject = new HashMap<String,String>();
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);  
		XSSFSheet sheet = workbook.getSheet(sheetName);
		Iterator<Row> iterator = sheet.iterator();		
		XSSFRow row = sheet.getRow(0);
		
		while (iterator.hasNext()) {  
			Row nextRow = iterator.next();  
			if(nextRow.getRowNum()==0)  {
				continue;  			
			}
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			if(nextRow.getCell(0).getStringCellValue().contains(automationID)){
				int i=0;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();  
					hashmapObject.put(row.getCell(i).getStringCellValue(), cell.getStringCellValue());
					i=i+1;
				} 
				break;
			}
		}  
		inputStream.close();
		return hashmapObject;
	}
	

}
