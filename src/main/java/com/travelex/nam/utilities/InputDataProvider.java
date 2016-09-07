package com.travelex.nam.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.travelex.framework.common.ConfigurationProperties;

public class InputDataProvider {

    private static XSSFWorkbook xlWBook;
    private static XSSFSheet xlSheet;
    private static XSSFRow xlRow;
    private static XSSFCell xlCell;
    public static int dataSetCounter;
    public static int execFlagCounter;

    ConfigurationProperties configurationProperties = new ConfigurationProperties();
    String dataPath = configurationProperties.getProperty(ConfigurationProperties.Test_Data_Folder_Path);

    Map<String,Integer> index = new HashMap<String,Integer>();

    public int getExcelDataIndex(String sheetName){

           int j = 0;
           int noOfRows = 0;
           try{
                  File file = new File(dataPath);
                  FileInputStream fis = new FileInputStream(file);

                  xlWBook = new XSSFWorkbook(fis);
                  xlSheet = xlWBook.getSheet(sheetName);
                  xlRow = xlSheet.getRow(0);
                  noOfRows = xlSheet.getPhysicalNumberOfRows();
                  for(Cell cell:xlRow){
                        index.put(cell.getStringCellValue() ,j);
                        j++; 
                  }
           }
           catch(Exception e){
                  System.out.println("Could not find test Data File");
           }
           return noOfRows;
    }

    public String[][] rowExcelData(int noOfRows,int noOfColumns,String AutomationID){
           String[][] excelData = new String[noOfRows][noOfColumns];
           execFlagCounter = 0;
           for (int r = 1; r < noOfRows; r++) {
                  int c;
                  for (c = 0; c < noOfColumns; c++) {
                        xlRow = xlSheet.getRow(r);
                        xlCell = xlRow.getCell(c);
                        if(xlRow.getCell(c).getStringCellValue().equalsIgnoreCase(AutomationID) && 
                                      xlRow.getCell(noOfColumns-1).getStringCellValue().equalsIgnoreCase("Yes")){
                               
                               execFlagCounter = execFlagCounter+1;
                        }
                        excelData[r][c] = xlCell.getStringCellValue();
                  }
           }
           return excelData;

    }

    public void updateData(String sheetName,String AutomationID,String ColumnName){
           int noOfRows = 0;
           File file = null;
           FileOutputStream fileOut;
           try{
                  file = new File(dataPath);
                  FileInputStream fis = new FileInputStream(file);

                  xlWBook = new XSSFWorkbook(fis);
                  xlSheet = xlWBook.getSheet(sheetName);
                  xlRow = xlSheet.getRow(0);
                  noOfRows = xlSheet.getPhysicalNumberOfRows();
                  int noOfColumns = xlRow.getLastCellNum();
                  Cell cell = null;
                  for(int i = 1;i <= noOfRows;i++) {
                        if(xlSheet.getRow(i).getCell(0).getStringCellValue().equals(AutomationID)){
                               cell = xlSheet.getRow(i).getCell(noOfColumns-2);
                               cell.setCellValue(ColumnName);
                               break;
                        }
                  } 

           }
           catch(Exception e){
                  System.out.println("Could not Update test Data File" + e.getMessage());
           }
           finally{
                  try {
                        fileOut = new FileOutputStream(file);
                        xlWBook.write(fileOut);
                        fileOut.close();
                  } catch (Exception e) {
                        System.out.println("Could not Write and Close the file"+ e.getMessage());
                  }

           }

    }

    @SuppressWarnings("rawtypes")
    public Map getRowDataMap(int noOfColumns, int row){
           Map<String, Object> rowdatamap = new HashMap<String, Object>();
           for (String key : index.keySet()) {
                  int columnnum = (Integer) index.get(key);
                  int col;
                  for (col = 0; col < noOfColumns; col++) {
                        xlRow = xlSheet.getRow(row);
                        xlCell = xlRow.getCell(columnnum);

                        rowdatamap.put(key, xlCell.getStringCellValue());
                  }

           }

           return rowdatamap;

    }

    @SuppressWarnings("unchecked")
    public Object[][] getRowDataMap(String sheetName,String AutomationID) {
           dataSetCounter =  0;
           Object[][] dataset = null;
           try{
                  Map<String, Object> rowdatamap = null;

                  String[][] excelData = null;
                  int noOfRows = getExcelDataIndex(sheetName);

                  int noOfColumns = xlRow.getLastCellNum();

                  excelData = rowExcelData(noOfRows,noOfColumns,AutomationID);

                  String[][] isExecuted = new String[noOfRows][1];
                  dataset = new Object[execFlagCounter][1];
                  for (int row = 1; row < noOfRows; row++) {
                        rowdatamap = new HashMap<String, Object>();
                        isExecuted[row][0] = excelData[row][noOfColumns - 1];

                        if (isExecuted[row][0].equalsIgnoreCase("yes") && excelData[row][1].equals(AutomationID)) {
                               rowdatamap = getRowDataMap(noOfColumns,row);
                               dataset[dataSetCounter][0] = rowdatamap;
                               System.out.println(rowdatamap);
                               dataSetCounter = dataSetCounter+1;
                        }

                  }
           }
           catch(Exception e){
                  e.printStackTrace();
           }
           return dataset;
    }

}

