package com.travelex.cif.pages;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import com.travelex.framework.common.ConfigurationProperties;
import com.travelex.framework.common.ExcelFileReader;

public class CIFValidation {
	public static Map<String,String> orderDetails;
	public static Map<String,String> customerDetails;
		
	public void searchAndValidateOrderDetails(List<HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>>> mainList,Map<String,String> orderDetails,Map<String,String> customerDetails){
		String OrderNo = "USD9976531";
		int maxloop = mainList.size()-2;
		if(mainList.size() >= 3){						
			for(int j=1; j<=maxloop; j++){				
				boolean orderExist = mainList.get(j).containsKey(OrderNo);												
				if(orderExist){					
					HashMap<String, ArrayList<HashMap<String, String>>> ActualorderDetails = mainList.get(j).get(OrderNo);
					boolean orderHeader = mainList.get(j).get(OrderNo).containsKey("OrderHeader");										
					if(orderHeader){				
						Assert.assertEquals(ActualorderDetails.get("OrderHeader").get(0).get("OrderType"), "SEL");
/*						Assert.assertEquals(orderDetails.get("OrderHeader").get(0).get("DateOrderPlaced"), "30JAN2017");
						Assert.assertEquals(orderDetails.get("OrderHeader").get(0).get("RequiredByDate"), "30JAN2017");*/
						Assert.assertEquals(ActualorderDetails.get("OrderHeader").get(0).get("SurName"), "SMITH");
						Assert.assertEquals(ActualorderDetails.get("OrderHeader").get(0).get("Initials"), "J");
						Assert.assertEquals(ActualorderDetails.get("OrderHeader").get(0).get("Title"), "Mr.");						
					}					

					boolean isAddressLineContainsP01 = mainList.get(j).get(OrderNo).get("OrderHeader").get(0).get("DeliveryOption").contains("P01");
					boolean isAddressLineContainsC01 = mainList.get(j).get(OrderNo).get("OrderHeader").get(0).get("DeliveryOption").contains("C01");
					boolean addressDetails = mainList.get(j).get(OrderNo).containsKey("Address");
										
					if(isAddressLineContainsP01){						
						if(addressDetails){
							int addressLinesCount = mainList.get(j).get(OrderNo).get("Address").size();								
							if(addressLinesCount>0){																		
								Assert.assertEquals(ActualorderDetails.get("Address").get(0).get("AddressLine4City"), "new city");
								Assert.assertEquals(ActualorderDetails.get("Address").get(0).get("CompanyName"), "New company");
								Assert.assertEquals(ActualorderDetails.get("Address").get(0).get("Country"), "US");
								Assert.assertEquals(ActualorderDetails.get("Address").get(0).get("PostalCode"), "30318");
								Assert.assertEquals(ActualorderDetails.get("Address").get(0).get("TelephoneNumber"), "3031322648");
								Assert.assertEquals(ActualorderDetails.get("Address").get(0).get("AddressLine1"), "Street 90");
								Assert.assertEquals(ActualorderDetails.get("Address").get(0).get("AddressLine2"), "lane 98");
								Assert.assertEquals(ActualorderDetails.get("Address").get(0).get("AddressLine5Country"), "GA");
								Assert.assertEquals(ActualorderDetails.get("Address").get(0).get("AddressLine3"), "");
							}
						}						
					}else if(isAddressLineContainsC01){
						if(addressDetails){
							Assert.assertFalse(true," Order Line Should Not Contain Address Line Still Its Exists");
						}
					}					
					boolean customDetails = mainList.get(j).get(OrderNo).containsKey("CustomDetails");
					//boolean cpOrderExistOrNot = isCPLineItemExistsInOrder(mainList,OrderNo,j);					
					if(customDetails){
						int customDetailsLinesCount = mainList.get(j).get(OrderNo).get("CustomDetails").size();
							for(int k=0;k<=customDetailsLinesCount-1;k++){
								for (Map.Entry<String, String> iterator1 : mainList.get(j).get(OrderNo).get("CustomDetails").get(k).entrySet()) {
									String keyValue = iterator1.getKey();									
									
									switch (keyValue.toUpperCase()) {																			
										/*case "SOURCE_ORDER_ID":
											Assert.assertEquals(orderDetails.get("CustomDetails").get(k).get(keyValue), "160916785");
											break;*/
											
										case "FIRSTNAME":
											Assert.assertEquals(ActualorderDetails.get("CustomDetails").get(k).get(keyValue), "JOHN");
											break;
											
										case "EMAIL":
											Assert.assertEquals(ActualorderDetails.get("CustomDetails").get(k).get(keyValue), "test@test.com");
											break;
											
										case "ATTENTION":
											Assert.assertEquals(ActualorderDetails.get("CustomDetails").get(k).get(keyValue), "Test new attention");
											break;
											
										case "DOB":
											Assert.assertEquals(ActualorderDetails.get("CustomDetails").get(k).get(keyValue), "02/28/1975");
											break;
											
										case "MOTHERS_MAIDEN_NAME":
											Assert.assertEquals(ActualorderDetails.get("CustomDetails").get(k).get(keyValue), "MOM");
											break;
											
										case "KYC_REFERENCE":
											Assert.assertEquals(ActualorderDetails.get("CustomDetails").get(k).get(keyValue), "1568506933");
											break;
											
										case "KYC_ID_TYPE":
											Assert.assertEquals(ActualorderDetails.get("CustomDetails").get(k).get(keyValue), "SSN");
											break;
											
										case "KYC_ID_VALUE":
											Assert.assertEquals(ActualorderDetails.get("CustomDetails").get(k).get(keyValue), "112223333");
											break;
											
										case "SECURITY_QUESTION_ID":
											Assert.assertEquals(ActualorderDetails.get("CustomDetails").get(k).get(keyValue), "5000014");
											break;
											
										case "SECURITY_QUESTION_VALUE":
											Assert.assertEquals(ActualorderDetails.get("CustomDetails").get(k).get(keyValue), "2698563");
											break;
											
										case "SECURITY_QUESTION_COUNTRY":
											Assert.assertEquals(ActualorderDetails.get("CustomDetails").get(k).get(keyValue), "US");
											break;
											
										case "SECURITY_QUESTION_STATE":
											Assert.assertEquals(ActualorderDetails.get("CustomDetails").get(k).get(keyValue), "AK");
											break;
											
										case "SECURITY_QUESTION_ISSUE_DATE":
											Assert.assertEquals(ActualorderDetails.get("CustomDetails").get(k).get(keyValue), "01-01-1990");
											break;
											
										case "SECURITY_QUESTION_EXPIRY_DATE":
											Assert.assertEquals(ActualorderDetails.get("CustomDetails").get(k).get(keyValue), "01-01-2020");
											break;
											
										case "ADDITIONAL_CARD_FLAG":
											Assert.assertEquals(ActualorderDetails.get("CustomDetails").get(k).get(keyValue), "Y");
											break;	
									}																		
								}						
							}					
					}
					

					boolean lineItemDetails = mainList.get(j).get(OrderNo).containsKey("LineItem");
					if(lineItemDetails){
						System.out.println("LineItem Details :: --- ");
						System.out.println( mainList.get(j).get(OrderNo).get("LineItem"));
					}
					
					boolean denominationsDetails = mainList.get(j).get(OrderNo).containsKey("Denominations");
					if(denominationsDetails){
						System.out.println("Denominations Details :: --- ");
						System.out.println( mainList.get(j).get(OrderNo).get("Denominations"));
					}
					
					boolean orderTrailerDetails = mainList.get(j).get(OrderNo).containsKey("OrderTrailer");
					if(orderTrailerDetails){
						System.out.println("Order Trailer Details :: --- ");
						System.out.println(mainList.get(j).get(OrderNo).get("OrderTrailer"));
					}					
					break;	
				}				
				if(maxloop == j){
					System.out.println("Order Not Exist In The File");
					break;	
				}				
			}			
		 }else{
			 System.out.println("No Orders Exist In System");
		 }
	}
	
	public void fileHeaderAndFileTrailerValidation(List<HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>>> mainList){
		
		HashMap<String, HashMap<String, ArrayList<HashMap<String, String>>>> FileHeader = mainList.get(0);		
		Assert.assertEquals(FileHeader.get("FileHeader").get("000").get(0).get("CorporateID"), "COL");
		Assert.assertEquals(FileHeader.get("FileHeader").get("000").get(0).get("GateWayID"), "COL001");
		Assert.assertEquals(FileHeader.get("FileHeader").get("000").get(0).get("BookCurrency"), "USD");
								
		HashMap<String, HashMap<String, ArrayList<HashMap<String, String>>>> FileTrailer = mainList.get(mainList.size()-1);		
		Assert.assertEquals(FileTrailer.get("FileTrailer").get("999").get(0).get("CorporateID"), "COL");
		
	}
	
	public static void main(String args[]) throws IOException {				
		List<HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>>> mainList = CIFFileReader.mainMethod();		
		CIFValidation cifvalidation = new CIFValidation();
		cifvalidation.fileHeaderAndFileTrailerValidation(mainList);
		String SheetName = "CustomerDetails";
		
		ConfigurationProperties configurationProperties = new ConfigurationProperties();  
		String excelFilePath = configurationProperties.getProperty(ConfigurationProperties.Test_Data_Folder_Path);  
		excelFilePath = System.getProperty("user.dir") + excelFilePath;
		FileInputStream inputStream = new FileInputStream(excelFilePath);		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);  
		XSSFSheet sheet = workbook.getSheet(SheetName);		
		int NumberOfRows = sheet.getPhysicalNumberOfRows();
		
		for(int l=0;l<=NumberOfRows-1;l++){
			XSSFRow row = sheet.getRow(l);
			XSSFCell value = row.getCell(28);
			if(value.toString().isEmpty()){
				System.out.println("Blank Cell ");
			}else{
				String OrderNumber = value.getStringCellValue();
				orderDetails = ExcelFileReader.readDataForAutomationID("OrderDetails",OrderNumber);
				customerDetails = ExcelFileReader.readDataForAutomationID("CustomerDetails",OrderNumber);								
				cifvalidation.searchAndValidateOrderDetails(mainList, orderDetails, customerDetails);	
				System.out.println(OrderNumber);
			}			
		}	   
	}

}
