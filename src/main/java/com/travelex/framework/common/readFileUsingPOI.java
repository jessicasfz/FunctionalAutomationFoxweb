package com.travelex.framework.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.travelex.framework.Dataset.CustomerDetailsDataSet;
import com.travelex.framework.Dataset.OrderDetailsDataSet;

public class readFileUsingPOI {	

	public static List<OrderDetailsDataSet> readOrderDetailsFile(String Scenario,String sheetName) throws IOException   
	{  
		ConfigurationProperties configurationProperties = new ConfigurationProperties();
		List<OrderDetailsDataSet> orderDetailDataset=new ArrayList<OrderDetailsDataSet>();  
		String excelFilePath = configurationProperties.getProperty(ConfigurationProperties.Test_Data_Folder_Path);  

		FileInputStream inputStream = new FileInputStream(excelFilePath);

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);  
		XSSFSheet sheet = workbook.getSheet(sheetName);

		Iterator<Row> iterator = sheet.iterator();  
		while (iterator.hasNext()) {  
			Row nextRow = iterator.next();  

			if(nextRow.getRowNum()==0)  
				continue;  

			OrderDetailsDataSet datasetObj=new OrderDetailsDataSet();  
			Iterator<Cell> cellIterator = nextRow.cellIterator();  

			if(nextRow.getCell(1).getStringCellValue().contains(Scenario)){
				while (cellIterator.hasNext()) {  
					Cell cell = cellIterator.next();  
					int columnIndex=cell.getColumnIndex();  

					switch (columnIndex+1) {  
					case 1:  
						datasetObj.setAutomationID(cell.getStringCellValue());  
						break;  
					case 2:
						datasetObj.setScenarioName(cell.getStringCellValue());
						break;
					case 3:
						datasetObj.setUsername(cell.getStringCellValue());
						break;
					case 4:
						datasetObj.setPassword(cell.getStringCellValue());
						break;
					case 5:
						datasetObj.setPartnerID(cell.getStringCellValue());
						break;
					case 6:
						datasetObj.setConfigBranch(cell.getStringCellValue());
						break;
					case 7:
						datasetObj.setBranchLocation(cell.getStringCellValue());
						break;
					case 8:
						datasetObj.setConfigOrderLink(cell.getStringCellValue());
						break;
					case 9:
						datasetObj.setOrderType(cell.getStringCellValue());
						break;
					case 10:
						datasetObj.setTransactionType(cell.getStringCellValue());
						break;
					case 11:
						datasetObj.setCustomerType(cell.getStringCellValue());
						break;
					case 12:
						datasetObj.setProductType(cell.getStringCellValue());
						break;
					case 13:
						datasetObj.setCurrency(cell.getStringCellValue());
						break;
					case 14:
						datasetObj.setConfigQuoteAndViewBtn(cell.getStringCellValue());
						break;
					case 15:
						datasetObj.setDomesticAmount(cell.getStringCellValue());
						break;
					case 16:
						datasetObj.setForeignAmount(cell.getStringCellValue());
						break;
					case 17:
						datasetObj.setConfigConfirmChkBox(cell.getStringCellValue());
						break;
					case 18:
						datasetObj.setConfigShowCurrency(cell.getStringCellValue());
						break;
					case 19:
						datasetObj.setConfigClearFields(cell.getStringCellValue());
						break;
					case 20:
						datasetObj.setConfigAddToOrder(cell.getStringCellValue());
						break;
					case 21:
						datasetObj.setDenomMessage(cell.getStringCellValue());
						break;
					case 22:
						datasetObj.setConfigDenomAlert(cell.getStringCellValue());
						break;
					case 23:
						datasetObj.setQuantity(cell.getStringCellValue());
						break;
					case 24:
						datasetObj.setConfigDeleteBtn(cell.getStringCellValue());
						break;
					case 25:
						datasetObj.setConfigConfirmBtn(cell.getStringCellValue());
						break;
					case 26:
						datasetObj.setAmountMsg(cell.getStringCellValue());
						break;
					case 27:
						datasetObj.setConfigDeliveryType(cell.getStringCellValue());
						break;
					case 28:
						datasetObj.setDeliveryType(cell.getStringCellValue());
						break;
					case 29:
						datasetObj.setConfigFetchOrderDetails(cell.getStringCellValue());
						break;
					case 30:
						datasetObj.setWarningMessage(cell.getStringCellValue());
						break;
					case 31:
						datasetObj.setConfigEditButton(cell.getStringCellValue());
						break;
					case 32:
						datasetObj.setConfigSpecifyDenom(cell.getStringCellValue());
						break;
					case 33:
						datasetObj.setErrorMessage(cell.getStringCellValue());
						break;

					}   

				}  
				orderDetailDataset.add(datasetObj);
			}
		}  

		inputStream.close();  

		return orderDetailDataset;  
	}  


	public static List<CustomerDetailsDataSet> readCustDetailsFile(String Scenario,String sheetName) throws IOException   
	{  
		ConfigurationProperties configurationProperties = new ConfigurationProperties();
		List<CustomerDetailsDataSet> custDetailDataset=new ArrayList<CustomerDetailsDataSet>();  
		String excelFilePath = configurationProperties.getProperty(ConfigurationProperties.Test_Data_Folder_Path);  

		FileInputStream inputStream = new FileInputStream(excelFilePath);

		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);  
		XSSFSheet sheet = workbook.getSheet(sheetName);

		Iterator<Row> iterator = sheet.iterator();  
		while (iterator.hasNext()) {  
			Row nextRow = iterator.next();  

			if(nextRow.getRowNum()==0)  
				continue;  

			CustomerDetailsDataSet datasetObj=new CustomerDetailsDataSet();  
			Iterator<Cell> cellIterator = nextRow.cellIterator();  

			if(nextRow.getCell(1).getStringCellValue().contains(Scenario)){
				while (cellIterator.hasNext()) {  
					Cell cell = cellIterator.next();  
					int columnIndex=cell.getColumnIndex();  

					switch (columnIndex+1) {  
					case 1:  
						datasetObj.setAutomationID(cell.getStringCellValue());  
						break;  
					case 2:
						datasetObj.setScenarioName(cell.getStringCellValue());
						break;
					case 3:
						datasetObj.setCustomerSalutation(cell.getStringCellValue());
						break;
					case 4:
						datasetObj.setFirstName(cell.getStringCellValue());
						break;
					case 5:
						datasetObj.setLastName(cell.getStringCellValue());
						break;
					case 6:
						datasetObj.setGLAccNumber(cell.getStringCellValue());
						break;
					case 7:
						datasetObj.setBankID(cell.getStringCellValue());
						break;
					case 8:
						datasetObj.setDOB(cell.getStringCellValue());
						break;
					case 9:
						datasetObj.setAttentionName(cell.getStringCellValue());
						break;
					case 10:
						datasetObj.setBranchContact(cell.getStringCellValue());
						break;
					case 11:
						datasetObj.setPhoneNumber(cell.getStringCellValue());
						break;
					case 12:
						datasetObj.setConfigChangeOrderBtn(cell.getStringCellValue());
						break;
					case 13:
						datasetObj.setConfigCancelOrderBtn(cell.getStringCellValue());
						break;
					case 14:
						datasetObj.setConfigCompleteOrderBtn(cell.getStringCellValue());
						break;
					case 15:
						datasetObj.setConfigNextOrderBtn(cell.getStringCellValue());
						break;
					case 16:
						datasetObj.setConfigPrinterFriendlyBtn(cell.getStringCellValue());
						break;
					case 17:
						datasetObj.setAccountHolderMessage(cell.getStringCellValue());
						break;
					case 18:
						datasetObj.setBeneficiary (cell.getStringCellValue());
						break;
					case 19:
						datasetObj.setAddressLine1(cell.getStringCellValue());
						break;
					case 20:
						datasetObj.setAddressLine2(cell.getStringCellValue());
						break;
					case 21:
						datasetObj.setCity(cell.getStringCellValue());
						break;
					case 22:
						datasetObj.setState(cell.getStringCellValue());
						break;
					case 23:
						datasetObj.setZipCode(cell.getStringCellValue());
						break;
					case 24:
						datasetObj.setDepositCountry(cell.getStringCellValue());
						break;
					case 25:
						datasetObj.setComments(cell.getStringCellValue());
						break;
					case 26:
						datasetObj.setConfigChangeBranchLink(cell.getStringCellValue());
						break;
					case 27:
						datasetObj.setConfirmationNumber(cell.getStringCellValue());
						break;
					}   

				}  
				custDetailDataset.add(datasetObj);
			}
		}  

		inputStream.close();  

		return custDetailDataset;  
	}  




}
