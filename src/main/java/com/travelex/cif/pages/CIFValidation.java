package com.travelex.cif.pages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import com.codoid.products.exception.FilloException;
import com.travelex.framework.common.ConfigurationProperties;
import com.travelex.framework.common.ExcelFileReader;
import com.travelex.framework.common.UpdateDataInExcel;

public class CIFValidation {
	public static Map<String,String> orderDetails;
	public static Map<String,String> customerDetails;
		
	public void searchAndValidateOrderDetails(List<HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>>> mainList,Map<String,String> orderDetails,Map<String,String> customerDetails){
		String OrderNo = customerDetails.get("ConfirmationNumber");
		int maxloop = mainList.size()-2;
		if(mainList.size() >= 3){			
				for(int j=1; j<=maxloop; j++){
					boolean orderExist = mainList.get(j).containsKey(OrderNo);												
					if(orderExist){
						boolean assertValue = true;
						HashMap<String, ArrayList<HashMap<String, String>>> ActualorderDetails = mainList.get(j).get(OrderNo);
						boolean orderHeader = mainList.get(j).get(OrderNo).containsKey("OrderHeader");										
						
						if(orderHeader){
							assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("RecordType"), "001",assertValue);
							assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("CustomersOrderReferenceNo"), customerDetails.get("ConfirmationNumber"),assertValue);
							
							if(orderDetails.get("TransactionType").contains("Sale") && !orderDetails.get("OrderType").contains("WholeSale")){
								assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("OrderType"), "SEL",assertValue);
							}else if(orderDetails.get("TransactionType").contains("Purchase") && !orderDetails.get("OrderType").contains("WholeSale")){
								assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("OrderType"), "BUY",assertValue);
							}else if(orderDetails.get("TransactionType").contains("Sale") && orderDetails.get("OrderType").contains("WholeSale")){
								assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("OrderType"), "STK",assertValue);
							}else if(orderDetails.get("TransactionType").contains("Purchase") && orderDetails.get("OrderType").contains("WholeSale")){
								assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("OrderType"), "BTK",assertValue);
							}
							
							assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("OrderStatus"), "000000",assertValue);
							assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("BillingMethod"), "1",assertValue);
							assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("PaymentStatus"), "PFU",assertValue);
							assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("PaymentMethod"), "AC",assertValue);
							assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("BillingCurrency"), "USD",assertValue);														
							assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("DateOrderPlaced"), currentDateInDDMMMYYYY(),assertValue);
							assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("RequiredByDate"), currentdatePlusOneInDDMMMYYYY(),assertValue);													
							assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("SurName"), customerDetails.get("LastName"),assertValue);
							assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("Initials"), customerDetails.get("FirstName").substring(0, 1),assertValue);
							assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("Title"), customerDetails.get("CustomerSalutation")+".",assertValue);												
							assertValue = softAssert(ActualorderDetails.get("OrderHeader").get(0).get("Source"), "COL",assertValue);
						}					
	
						boolean isAddressLineContainsP01 = mainList.get(j).get(OrderNo).get("OrderHeader").get(0).get("DeliveryOption").contains("P01");
						boolean isAddressLineContainsC01 = mainList.get(j).get(OrderNo).get("OrderHeader").get(0).get("DeliveryOption").contains("C01");
						boolean addressDetails = mainList.get(j).get(OrderNo).containsKey("Address");
											
						if(isAddressLineContainsP01){						
							if(addressDetails){
								int addressLinesCount = mainList.get(j).get(OrderNo).get("Address").size();								
								if(addressLinesCount>0){
									String branchDetails = customerDetails.get("CompanyDetails");
									String[] allDetails = branchDetails.split("\\#");
									String companyName = allDetails[1].trim();
									String add1 = allDetails[2].trim();
									String add2 = allDetails[3].trim();
									String city = allDetails[4].trim();
									String state = allDetails[5].trim();
									String zipcode = allDetails[6].trim();
									
									assertValue = softAssert(ActualorderDetails.get("Address").get(0).get("RecordType"), "002",assertValue);
									assertValue = softAssert(ActualorderDetails.get("Address").get(0).get("CustomersOrderReference"), customerDetails.get("ConfirmationNumber"),assertValue);
									
									assertValue = softAssert(ActualorderDetails.get("Address").get(0).get("CompanyName"), companyName,assertValue);						
									assertValue = softAssert(ActualorderDetails.get("Address").get(0).get("TelephoneNumber"),customerDetails.get("BranchContact")+customerDetails.get("PhoneNumber"),assertValue);									
									assertValue = softAssert(ActualorderDetails.get("Address").get(0).get("Country"), "USA",assertValue);
									assertValue = softAssert(ActualorderDetails.get("Address").get(0).get("PostalCode"), zipcode,assertValue);								
									assertValue = softAssert(ActualorderDetails.get("Address").get(0).get("AddressLine1"), add1,assertValue);
									assertValue = softAssert(ActualorderDetails.get("Address").get(0).get("AddressLine2"), add2,assertValue);
									assertValue = softAssert(ActualorderDetails.get("Address").get(0).get("AddressLine4City"), city,assertValue);
									assertValue = softAssert(ActualorderDetails.get("Address").get(0).get("AddressLine5Country"), state,assertValue);
								}
							}						
						}else if(isAddressLineContainsC01){
							if(addressDetails){
								updateFailStatus();
							}
						}					
					
						boolean lineItemDetails = mainList.get(j).get(OrderNo).containsKey("LineItem");
						if(lineItemDetails){						
							int countOfLineItems =	mainList.get(j).get(OrderNo).get("LineItem").size();												
							String multiCurrencyDetails = orderDetails.get("MultiCurrencyDetails");
							ArrayList<HashMap<String, String>> listOfProductDetails  = productDetailsList(multiCurrencyDetails);
							
							for(int a=0;a<=countOfLineItems-1;a++){								
								String productValue = mainList.get(j).get(OrderNo).get("LineItem").get(a).get("ProductType");
								String currencyValue = mainList.get(j).get(OrderNo).get("LineItem").get(a).get("Currency");
								String foreignAmountValue = mainList.get(j).get(OrderNo).get("LineItem").get(a).get("ForeignValue");
								
								String productName = listOfProductDetails.get(a).get("ProductName").trim();
								String currencyName = listOfProductDetails.get(a).get("CurrencyName").trim();
															
								if(productName.contains("Foreign Currencies")){
									productName = "FC";
								}else if(productName.contains("Foreign Check")){
									productName = "CQ";
								}else if(productName.contains("Foreign Drafts")){
									productName = "DR";
								}							
								int listSize = listOfProductDetails.size();
								for(int b=0;b<=listSize-1;b++){															
									if(productName.contains(productValue) && currencyName.substring(currencyName.length()-3).contains(currencyValue)){										
										assertValue = softAssert(mainList.get(j).get(OrderNo).get("LineItem").get(a).get("RecordType"), "006",assertValue);
										assertValue = softAssert(mainList.get(j).get(OrderNo).get("LineItem").get(a).get("CustomerOrderReference"), customerDetails.get("ConfirmationNumber"),assertValue);
										assertValue = softAssert(mainList.get(j).get(OrderNo).get("LineItem").get(a).get("ProductType"),productName,assertValue);
										assertValue = softAssert(mainList.get(j).get(OrderNo).get("LineItem").get(a).get("Currency"),currencyName.substring(currencyName.length()-3),assertValue);
										assertValue = softAssert(mainList.get(j).get(OrderNo).get("LineItem").get(a).get("ForeignValue"),foreignAmountValue,assertValue);
										
										if(mainList.get(j).get(OrderNo).get("LineItem").get(a).get("DenominationsType").contains("X")){
											boolean denominationsDetails = mainList.get(j).get(OrderNo).containsKey("Denominations");
											if(denominationsDetails){
												int denominationsOfLineItems =	mainList.get(j).get(OrderNo).get("Denominations").size();
												for(int c=0;c<=denominationsOfLineItems-1;c++){
													if(mainList.get(j).get(OrderNo).get("Denominations").get(c).get("LineItem").contains(Integer.toString(a+1))){														
														assertValue = softAssert(mainList.get(j).get(OrderNo).get("Denominations").get(c).get("RecordType"), "007",assertValue);
														assertValue = softAssert(mainList.get(j).get(OrderNo).get("Denominations").get(c).get("CustomerOrderReference"), customerDetails.get("ConfirmationNumber"),assertValue);
														assertValue = softAssert(mainList.get(j).get(OrderNo).get("Denominations").get(c).get("Quantity"), listOfProductDetails.get(a).get("Quantity").trim(),assertValue);
														assertValue = softAssert(mainList.get(j).get(OrderNo).get("Denominations").get(c).get("DenominationValue"), listOfProductDetails.get(a).get("Denomination").trim(),assertValue);													
													}
												}
											}																														
										}									
										break;
									}else{
										System.out.println("Expected Line Item Is Not Exist");
									}
								}
							}					
						}										
						boolean orderTrailerDetails = mainList.get(j).get(OrderNo).containsKey("OrderTrailer");
						if(orderTrailerDetails){							
							assertValue =  softAssert(ActualorderDetails.get("OrderTrailer").get(0).get("RecordType"),"099",assertValue);
							assertValue =  softAssert(ActualorderDetails.get("OrderTrailer").get(0).get("CustomerOrderReference"),customerDetails.get("ConfirmationNumber"),assertValue);						
						}					
						break;	
					}				
					if(maxloop == j){
						updateFailStatus();
						break;	
					}
			}			
		 }else{
			updateFailStatus();
		 }
	}	

	public void fileHeaderAndFileTrailerValidation(List<HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>>> mainList) {
				
		HashMap<String, HashMap<String, ArrayList<HashMap<String, String>>>> FileHeader = mainList.get(0);		
		Assert.assertEquals(FileHeader.get("FileHeader").get("000").get(0).get("CorporateID"), "COL");
		Assert.assertEquals(FileHeader.get("FileHeader").get("000").get(0).get("GateWayID"), "COL001");
		Assert.assertEquals(FileHeader.get("FileHeader").get("000").get(0).get("BookCurrency"), "USD");
		//Assert.assertEquals(FileHeader.get("FileHeader").get("000").get(0).get("DateFileCreation"), currentDateInDDMMMYYYY());
		Assert.assertEquals(FileHeader.get("FileHeader").get("000").get(0).get("RecordType"), "000");
								
		HashMap<String, HashMap<String, ArrayList<HashMap<String, String>>>> FileTrailer = mainList.get(mainList.size()-1);		
		Assert.assertEquals(FileTrailer.get("FileTrailer").get("999").get(0).get("CorporateID"), "COL");
		Assert.assertEquals(FileTrailer.get("FileTrailer").get("999").get(0).get("RecordType"), "999");
		
	}
	
	public static void main(String args[]) throws IOException {
		List<HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>>> mainList = CIFFileReader.mainMethod();
		CreateHtmlReport createNewHTMLReport = new CreateHtmlReport();
		createNewHTMLReport.createDirectoryForReports();
		File file = new File(System.getProperty("user.dir") + "/target/CIFValidationReport/CIFReport.html");
		file.createNewFile();		
		FileWriter fWriter = null;
		BufferedWriter writer = null;
		try {
		    fWriter = new FileWriter(file);
		    writer = new BufferedWriter(fWriter);		    
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
			writer.write("<html><body>");
			for(int l=1;l<=NumberOfRows-1;l++){
				XSSFRow row = sheet.getRow(l);
				XSSFCell orderNumber = row.getCell(28);
				XSSFCell automationID = row.getCell(0);
				if(orderNumber.toString().isEmpty()){
				}else{
					String AutomationID = automationID.getStringCellValue();
					writer.write("<h3>" +"Order Number ::: "+orderNumber.toString());
					writer.newLine();
					orderDetails = ExcelFileReader.readDataForAutomationID("OrderDetails",AutomationID);
					customerDetails = ExcelFileReader.readDataForAutomationID("CustomerDetails",AutomationID);								
					cifvalidation.searchAndValidateOrderDetails(mainList, orderDetails, customerDetails);	
				}			
			}
			writer.write("</body></html>");
		    writer.close();
		}catch (Exception e) {
			
		}	   
	}
		
	public ArrayList<HashMap<String, String>> productDetailsList(String listOfcurrenciesInDetail){
		String[] listOfProductsInDetailDenoms = listOfcurrenciesInDetail.split("\\|");
		int noOfProducts = listOfProductsInDetailDenoms.length;
		ArrayList<HashMap<String, String>> listofProducts = new ArrayList<HashMap<String, String>>();
		
		HashMap<String, String> productDetailsMain = new HashMap<>();
		String producttype = orderDetails.get("ProductType");
		String currencyValue = orderDetails.get("Currency");
		String foreignAmountvalue = orderDetails.get("ForeignAmount");
		String quantityvalue = orderDetails.get("Quantity");
		String denomvalue = orderDetails.get("Denomination");
		
		productDetailsMain.put("ProductName", producttype);
		productDetailsMain.put("CurrencyName", currencyValue);
		productDetailsMain.put("ForeignAmount", foreignAmountvalue);
		productDetailsMain.put("Quantity", quantityvalue);
		productDetailsMain.put("Denomination", denomvalue);		
		listofProducts.add(productDetailsMain);
		
		for(int i=0;i<=noOfProducts-1;i++){
			HashMap<String, String> productDetails = new HashMap<>();
			String listOfProductDetails = listOfProductsInDetailDenoms[i];
			String[] eachProductDetails = listOfProductDetails.split("\\#");

			if(eachProductDetails[0].equalsIgnoreCase("Foreign Currencies")){
				String product = eachProductDetails[0].trim();
				String currency = eachProductDetails[1].trim();
				String foreignAmount = eachProductDetails[2].trim();
				String quantity = eachProductDetails[3].trim();
				String denom = eachProductDetails[4].trim();

				productDetails.put("ProductName", product);
				productDetails.put("CurrencyName", currency);
				productDetails.put("ForeignAmount", foreignAmount);				
				productDetails.put("Quantity", quantity);
				productDetails.put("Denomination", denom);	
				listofProducts.add(productDetails);
			}

			else if(eachProductDetails[0].equalsIgnoreCase("Foreign Check")){
				String product = eachProductDetails[0].trim();
				String currency = eachProductDetails[1].trim();
				String foreignAmount = eachProductDetails[2].trim();
				productDetails.put("ProductName", product);
				productDetails.put("CurrencyName", currency);
				productDetails.put("ForeignAmount", foreignAmount);
				listofProducts.add(productDetails);
														
			}

			else if(eachProductDetails[0].equalsIgnoreCase("Foreign Drafts")){
				String product = eachProductDetails[0].trim();
				String currency = eachProductDetails[1].trim();
				String foreignAmount = eachProductDetails[2].trim();
				productDetails.put("ProductName", product);
				productDetails.put("CurrencyName", currency);
				productDetails.put("ForeignAmount", foreignAmount);
				listofProducts.add(productDetails);

			}
			
			else if(eachProductDetails[0].equalsIgnoreCase("Pre-Paid Cards")){					
				String product = eachProductDetails[0].trim();
				String currency = eachProductDetails[1].trim();
				String foreignAmount = eachProductDetails[2].trim();
				productDetails.put("ProductName", product);
				productDetails.put("CurrencyName", currency);
				productDetails.put("ForeignAmount", foreignAmount);
				listofProducts.add(productDetails);
			}
		}
		return listofProducts;								
	}
		
	public boolean softAssert(String actual, String expected,boolean assertValue){
		if(assertValue){
			UpdateDataInExcel up = new UpdateDataInExcel();
			try{
		        Assert.assertEquals(expected, actual);
		        try {
					up.updateDataInExcel("CustomerDetails", "CIFValidationStatus", "PASS",orderDetails.get("AutomationID"));
				} catch (FilloException e) {
					e.printStackTrace();
				}
		        }catch(AssertionError e){
		            System.out.println("Assertion Error Expected And Actual Values Are Not Same ::: Expected Value -- "+ expected + "   Actual Value --"+ actual);
		            assertValue = false;		            
		            try {
						up.updateDataInExcel("CustomerDetails", "CIFValidationStatus", "FAIL",orderDetails.get("AutomationID"));
					} catch (FilloException e1) {
						e1.printStackTrace();
					}
		        } 			
		}
		return assertValue;
	}

	public String currentDateInDDMMMYYYY(){
		Date currDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
		String strCurrDate = dateFormat.format(currDate);
		strCurrDate = strCurrDate.replaceAll("/", "").toUpperCase();
		return strCurrDate;		
	}
	
	public String currentdatePlusOneInDDMMMYYYY(){
		Date dt = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
		String strCurrDate = dateFormat.format(dt);
		strCurrDate = strCurrDate.replaceAll("/", "").toUpperCase();		
		return strCurrDate;
	}

	public boolean updateFailStatus(){
		boolean assertValue = false;
		UpdateDataInExcel up = new UpdateDataInExcel();
		try {
			up.updateDataInExcel("CustomerDetails", "CIFValidationStatus", "FAIL",orderDetails.get("AutomationID"));
		} catch (FilloException e) {
			e.printStackTrace();
		}
		return assertValue;
	}
	
}
