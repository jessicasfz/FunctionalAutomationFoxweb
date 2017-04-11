package com.travelex.cif.pages;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CIFFileReader {

	
	public static HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>> fileHeaderInfoCollectionList(String[] lineSplitUsingTab, HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>> Header){	
		String recordType, corporateID, fileType, gateWayID, gateWayControlSequenceNumber, bookCurrency, dateFileCreation, timeFileCreation;  				
  		
		recordType = lineSplitUsingTab[0];
  		corporateID = lineSplitUsingTab[1];
  		fileType = lineSplitUsingTab[2];
  		gateWayID = lineSplitUsingTab[3];
  		gateWayControlSequenceNumber = lineSplitUsingTab[4];
  		bookCurrency = lineSplitUsingTab[5];
  		dateFileCreation = lineSplitUsingTab[6];
  		timeFileCreation = lineSplitUsingTab[7];
  		
  		HashMap<String, ArrayList<HashMap<String ,String>>> HeaderData = new HashMap<String, ArrayList<HashMap<String ,String>>>();
  		HashMap<String ,String> FileHeader = new HashMap<String ,String>();
  		ArrayList<HashMap<String ,String>> ListOfFileHeader = new ArrayList<HashMap<String ,String>>();
  		
  		FileHeader.put("RecordType", recordType);
  		FileHeader.put("CorporateID", corporateID);
  		FileHeader.put("FileType", fileType);
  		FileHeader.put("GateWayID", gateWayID);
  		FileHeader.put("GateWayControlSequenceNumber", gateWayControlSequenceNumber);
  		FileHeader.put("BookCurrency", bookCurrency);
  		FileHeader.put("DateFileCreation", dateFileCreation);
  		FileHeader.put("TimeFileCreation", timeFileCreation);
  		
  		ListOfFileHeader.add(FileHeader);
  		HeaderData.put(recordType, ListOfFileHeader);
  		Header.put("FileHeader", HeaderData);
		
  		return Header;		
	}

	
	public static HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>> fileFooterInfoCollectionList(String[] lineSplitUsingTab,HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>> Trailer){
		String recordType,corporateID,countOfLineItems,totalBookCurrencyValue,totalCommisionValue,totalPromotonFees,totalDeliveryfees,fileRecordCount,checkSum;
  		
  		recordType = lineSplitUsingTab[0];
		corporateID =lineSplitUsingTab[1];
		countOfLineItems =lineSplitUsingTab[2];
		totalBookCurrencyValue =lineSplitUsingTab[3];
		totalCommisionValue =lineSplitUsingTab[4];
		totalPromotonFees =lineSplitUsingTab[5];
		totalDeliveryfees =lineSplitUsingTab[6];
		fileRecordCount =lineSplitUsingTab[7];
		checkSum =lineSplitUsingTab[8];
		
		HashMap<String, ArrayList<HashMap<String ,String>>> TrailerData = new HashMap<String, ArrayList<HashMap<String ,String>>>();
  		ArrayList<HashMap<String ,String>> ListOfFileTrailers = new ArrayList<HashMap<String ,String>>();
  		HashMap<String ,String> FileTrailer = new HashMap<String ,String>();
  		
		FileTrailer.put("RecordType", recordType);
		FileTrailer.put("CorporateID", corporateID);
		FileTrailer.put("CountOfLineItems", countOfLineItems);
		FileTrailer.put("TotalBookCurrencyValue", totalBookCurrencyValue);
		FileTrailer.put("TotalCommisionValue", totalCommisionValue);
		FileTrailer.put("TotalPromotonFees", totalPromotonFees);
		FileTrailer.put("TotalDeliveryfees", totalDeliveryfees);
		FileTrailer.put("FileRecordCount", fileRecordCount);
		FileTrailer.put("CheckSum", checkSum);
		
		ListOfFileTrailers.add(FileTrailer);
		TrailerData.put(recordType, ListOfFileTrailers);
		Trailer.put("FileTrailer", TrailerData);
		
		return Trailer;
		
	}		


	public static HashMap<String ,ArrayList<HashMap<String,String>>> orderHeaderInfoCollectionList(String[] lineSplitUsingTab,HashMap<String ,ArrayList<HashMap<String,String>>> OrderData){
		String recordType,corporateID,recordSequenceNumber,customersOrderReferenceNo,version,orderType,orderStatus,billingMethod,billingCurrency,paymentMethod,paymentStatus,
		   customerType,orderLocation,userName,dateOrderPlaced,timeOrderPlaced,originalOrderReference,tcsOrderReference,formReference,faultAllocation,authorizationReference,
		   requiredByDate,requiredByTime,deliveryOption,deliveryLocationCompany,deliveryLocationTravelex,deliveryCharge,commision,surName,initials,title,source,rewardPointsEligibility,
		   messageID,cardHandlingFee;
		
		ArrayList<HashMap<String ,String>> ListOfOrderHeaders = new ArrayList<HashMap<String ,String>>();
		HashMap<String,String> OrderHeader = new HashMap<String,String>();
				
		recordType = lineSplitUsingTab[0];
		corporateID =lineSplitUsingTab[1];
		recordSequenceNumber =lineSplitUsingTab[2];
		customersOrderReferenceNo =lineSplitUsingTab[3];
		version =lineSplitUsingTab[4];
		orderType =lineSplitUsingTab[5];
		orderStatus =lineSplitUsingTab[6];
		billingMethod =lineSplitUsingTab[7];
		billingCurrency =lineSplitUsingTab[8];
		paymentMethod =lineSplitUsingTab[9];
		paymentStatus =lineSplitUsingTab[10];
		customerType =lineSplitUsingTab[11];
		orderLocation =lineSplitUsingTab[12];
		userName =lineSplitUsingTab[13];
		dateOrderPlaced =lineSplitUsingTab[14];
		timeOrderPlaced =lineSplitUsingTab[15];
		originalOrderReference =lineSplitUsingTab[16];
		tcsOrderReference =lineSplitUsingTab[17];
		formReference =lineSplitUsingTab[18];
		faultAllocation =lineSplitUsingTab[19];
		authorizationReference =lineSplitUsingTab[20];
		requiredByDate =lineSplitUsingTab[21];
		requiredByTime =lineSplitUsingTab[22];
		deliveryOption =lineSplitUsingTab[23];
		deliveryLocationCompany =lineSplitUsingTab[24];
		deliveryLocationTravelex =lineSplitUsingTab[25];
		deliveryCharge =lineSplitUsingTab[26];
		commision =lineSplitUsingTab[27];
		surName =lineSplitUsingTab[28];
		initials =lineSplitUsingTab[29];
		title =lineSplitUsingTab[30];
		source =lineSplitUsingTab[31];
		rewardPointsEligibility =lineSplitUsingTab[32];
		messageID =lineSplitUsingTab[33];
		cardHandlingFee =lineSplitUsingTab[34];
		
		OrderHeader.put("RecordType", recordType);
		OrderHeader.put("CorporateID", corporateID);
		OrderHeader.put("RecordSequenceNumber", recordSequenceNumber);
		OrderHeader.put("CustomersOrderReferenceNo", customersOrderReferenceNo);
		OrderHeader.put("Version", version);
		OrderHeader.put("OrderType", orderType);
		OrderHeader.put("OrderStatus", orderStatus);
		OrderHeader.put("BillingMethod", billingMethod);
		OrderHeader.put("BillingCurrency", billingCurrency);
		OrderHeader.put("PaymentMethod", paymentMethod);
		OrderHeader.put("PaymentStatus", paymentStatus);
		OrderHeader.put("CustomerType", customerType);
		OrderHeader.put("OrderLocation", orderLocation);
		OrderHeader.put("UserName", userName);
		OrderHeader.put("DateOrderPlaced", dateOrderPlaced);
		OrderHeader.put("TimeOrderPlaced", timeOrderPlaced); 	  			 
		OrderHeader.put("OriginalOrderReference", originalOrderReference);
		OrderHeader.put("TcsOrderReference", tcsOrderReference);
		OrderHeader.put("FormReference", formReference);
		OrderHeader.put("FaultAllocation", faultAllocation);
		OrderHeader.put("AuthorizationReference", authorizationReference);
		OrderHeader.put("RequiredByDate", requiredByDate);
		OrderHeader.put("RequiredByTime", requiredByTime);
		OrderHeader.put("DeliveryOption", deliveryOption);
		OrderHeader.put("DeliveryLocationCompany", deliveryLocationCompany);
		OrderHeader.put("DeliveryLocationTravelex", deliveryLocationTravelex);
		OrderHeader.put("DeliveryCharge", deliveryCharge);
		OrderHeader.put("Commision", commision);
		OrderHeader.put("SurName", surName);
		OrderHeader.put("Initials", initials);
		OrderHeader.put("Title", title);
		OrderHeader.put("Source", source);	  			 
		OrderHeader.put("RewardPointsEligibility", rewardPointsEligibility);
		OrderHeader.put("MessageID", messageID);
		OrderHeader.put("CardHandlingFee", cardHandlingFee);
		
		ListOfOrderHeaders.add(OrderHeader);		
		OrderData.put("OrderHeader", ListOfOrderHeaders);
		
		return OrderData;
	}


	public static HashMap<String ,ArrayList<HashMap<String,String>>> addressDetailsInfoCollectionList(String[] orderLineReaderTab,HashMap<String ,ArrayList<HashMap<String,String>>> OrderData, ArrayList<HashMap<String ,String>> ListOfAddress){
		String recordType,customerID,recordSequenceNumber,customersOrderReference,version,addressType,telephoneNumber,companyName,departmentName,addressLine1,addressLine2,
		addressLine3,addressLine4City,addressLine5Country,postalCode,country;
				
		HashMap<String,String> Address = new HashMap<String,String>();
				
		recordType = orderLineReaderTab[0];
		customerID =orderLineReaderTab[1];
		recordSequenceNumber =orderLineReaderTab[2];
		customersOrderReference =orderLineReaderTab[3];
		version =orderLineReaderTab[4];
		addressType =orderLineReaderTab[5];
		telephoneNumber =orderLineReaderTab[6];
		companyName =orderLineReaderTab[7];
		departmentName =orderLineReaderTab[8];
		addressLine1 =orderLineReaderTab[9];
		addressLine2 =orderLineReaderTab[10];
		addressLine3 =orderLineReaderTab[11];
		addressLine4City =orderLineReaderTab[12];
		addressLine5Country =orderLineReaderTab[13];
		postalCode =orderLineReaderTab[14];
		country =orderLineReaderTab[15];
		
		Address.put("RecordType", recordType);
		Address.put("CustomerID", customerID);
		Address.put("RecordSequenceNumber", recordSequenceNumber);
		Address.put("CustomersOrderReference", customersOrderReference);
		Address.put("Version", version);
		Address.put("AddressType", addressType);
		Address.put("TelephoneNumber", telephoneNumber);
		Address.put("CompanyName", companyName);
		Address.put("DepartmentName", departmentName);
		Address.put("AddressLine1", addressLine1);
		Address.put("AddressLine2", addressLine2);
		Address.put("AddressLine3", addressLine3);
		Address.put("AddressLine4City", addressLine4City);
		Address.put("AddressLine5Country", addressLine5Country);
		Address.put("PostalCode", postalCode);
		Address.put("Country", country);
		
		ListOfAddress.add(Address);
		OrderData.put("Address", ListOfAddress);		
		
		return OrderData;
		
	}
	

	public static HashMap<String ,ArrayList<HashMap<String,String>>> orderTrailerInfoCollectionList(String[] orderLineReaderTab,HashMap<String ,ArrayList<HashMap<String,String>>> OrderData, ArrayList<HashMap<String ,String>> ListOfOrderTrailer){
		String recordType,customerID,recordSequenceNumber,customerOrderReference,version,countOfLineItems,totalBookCurrencyValue,totalCommissionValue,totalPromotionFees,
		totalDeliveryFees,orderRecordCount,checkSum;
						
		recordType = orderLineReaderTab[0];
		customerID =orderLineReaderTab[1];
		recordSequenceNumber =orderLineReaderTab[2];
		customerOrderReference =orderLineReaderTab[3];
		version =orderLineReaderTab[4];
		countOfLineItems =orderLineReaderTab[5];
		totalBookCurrencyValue =orderLineReaderTab[6];
		totalCommissionValue =orderLineReaderTab[7];
		totalPromotionFees =orderLineReaderTab[8];
		totalDeliveryFees =orderLineReaderTab[9];
		orderRecordCount =orderLineReaderTab[10];
		checkSum =orderLineReaderTab[11];
		
		HashMap<String,String> OrderTrailer = new HashMap<String,String>();

		OrderTrailer.put("RecordType", recordType);
		OrderTrailer.put("CustomerID", customerID);
		OrderTrailer.put("RecordSequenceNumber", recordSequenceNumber);
		OrderTrailer.put("CustomerOrderReference", customerOrderReference);
		OrderTrailer.put("Version", version);
		OrderTrailer.put("CountOfLineItems", countOfLineItems);
		OrderTrailer.put("TotalBookCurrencyValue", totalBookCurrencyValue);
		OrderTrailer.put("TotalCommissionValue", totalCommissionValue);
		OrderTrailer.put("TotalPromotionFees", totalPromotionFees);
		OrderTrailer.put("TotalDeliveryFees", totalDeliveryFees);
		OrderTrailer.put("OrderRecordCount", orderRecordCount);
		OrderTrailer.put("CheckSum", checkSum);
		ListOfOrderTrailer.add(OrderTrailer);
		OrderData.put("OrderTrailer", ListOfOrderTrailer);		
		return OrderData;

	}
	

	public static HashMap<String ,ArrayList<HashMap<String,String>>> denominationsInfoCollectionList(String[] orderLineReaderTab,HashMap<String ,ArrayList<HashMap<String,String>>> OrderData, ArrayList<HashMap<String ,String>> ListOfDenominations){
		String recordType,customerID,recordSequenceNumber,customerOrderReference,version,lineItem,denominationValue,quantity;
		
		recordType = orderLineReaderTab[0];
		customerID =orderLineReaderTab[1];
		recordSequenceNumber =orderLineReaderTab[2];
		customerOrderReference =orderLineReaderTab[3];
		version =orderLineReaderTab[4];
		lineItem =orderLineReaderTab[5];
		denominationValue =orderLineReaderTab[6];
		quantity =orderLineReaderTab[7];
		
		HashMap<String,String> Denominations = new HashMap<String,String>();
		
		Denominations.put("RecordType", recordType);
		Denominations.put("CustomerID", customerID);
		Denominations.put("RecordSequenceNumber", recordSequenceNumber);
		Denominations.put("CustomerOrderReference", customerOrderReference);
		Denominations.put("Version", version);
		Denominations.put("LineItem", lineItem);
		Denominations.put("DenominationValue", denominationValue);
		Denominations.put("Quantity", quantity);
		
		ListOfDenominations.add(Denominations);
		OrderData.put("Denominations", ListOfDenominations);		
		return OrderData;
		
	}
	

	public static HashMap<String ,ArrayList<HashMap<String,String>>> lineItemInfoCollectionList(String[] orderLineReaderTab,HashMap<String ,ArrayList<HashMap<String,String>>> OrderData, ArrayList<HashMap<String ,String>> ListOfLineItem){
		String recordType,customerID,recordSequenceNumber,customerOrderReference,version,lineItem,productType,currency,foreignValue,exchangeRate,bookCurrencyValue,
		denominationsType,commissionAmount,issuer;
		
		recordType = orderLineReaderTab[0];
		customerID =orderLineReaderTab[1];
		recordSequenceNumber =orderLineReaderTab[2];
		customerOrderReference =orderLineReaderTab[3];
		version =orderLineReaderTab[4];
		lineItem =orderLineReaderTab[5];
		productType =orderLineReaderTab[6];
		currency =orderLineReaderTab[7];
		foreignValue =orderLineReaderTab[8];
		exchangeRate =orderLineReaderTab[9];
		bookCurrencyValue =orderLineReaderTab[10];
		denominationsType =orderLineReaderTab[11];
		commissionAmount =orderLineReaderTab[12];
		issuer =orderLineReaderTab[13];
		
		HashMap<String,String> LineItem = new HashMap<String,String>();
				
		LineItem.put("RecordType", recordType);
		LineItem.put("CustomerID", customerID);
		LineItem.put("RecordSequenceNumber", recordSequenceNumber);
		LineItem.put("CustomerOrderReference", customerOrderReference);
		LineItem.put("Version", version);
		LineItem.put("LineItem", lineItem);		
		LineItem.put("ProductType", productType);
		LineItem.put("Currency", currency);
		LineItem.put("ForeignValue", foreignValue);
		LineItem.put("ExchangeRate", exchangeRate);
		LineItem.put("BookCurrencyValue", bookCurrencyValue);
		LineItem.put("DenominationsType", denominationsType);
		LineItem.put("CommissionAmount", commissionAmount);
		LineItem.put("Issuer", issuer);
		
		ListOfLineItem.add(LineItem);
		OrderData.put("LineItem", ListOfLineItem);		
		return OrderData;
		
	}
	

	public static boolean isListContainSequence(ArrayList<String> orderReferenceNo, String sequnce){
		for(String str: orderReferenceNo) {
		    if(str.trim().contains(sequnce))
		       return true;
		}
		return false;
	}


	public static List<HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>>> mainMethod() throws IOException{
		  String[] lineSplitUsingTab;String[] orderLineReaderTab;
		  String recordType,recordTypeOrderLevel,orderNoString,loopOrderNo,strLine,strLineOrder;  
		  
		  List<HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>>> MainList = new ArrayList<HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>>>();
		  		  
		  FileInputStream fstream = new FileInputStream("C:\\Users\\reddyp\\Desktop\\TX050420170753.ord");
	      BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	      
	      while ((strLine = br.readLine()) != null){
	    	  	lineSplitUsingTab = strLine.split("\t");	    	  	
	    	  	recordType = lineSplitUsingTab[0];
	    	 	    	  	
	    	  	if(recordType.contains("000")){
	    	  		HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>> Header = new HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>>();
	    	  		Header = fileHeaderInfoCollectionList(lineSplitUsingTab,Header);
	    	  		MainList.add(Header);
	        	}
	    	  	
	    	  	if(recordType.contains("001")){
	    	  		orderNoString = lineSplitUsingTab[3];
	    	  		
	    	  		HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>> OrdersRelatedData = new HashMap<String,HashMap<String,ArrayList<HashMap<String,String>>>>();
	    	  		HashMap<String ,ArrayList<HashMap<String,String>>> OrderData = new HashMap<String ,ArrayList<HashMap<String,String>>>();
	    	  		
	    	  		ArrayList<HashMap<String ,String>> ListOfAddress = new ArrayList<HashMap<String ,String>>();
	    	  		ArrayList<HashMap<String ,String>> ListOfLineItem = new ArrayList<HashMap<String ,String>>();
	    	  		ArrayList<HashMap<String ,String>> ListOfDenominations = new ArrayList<HashMap<String ,String>>();
	    	  		ArrayList<HashMap<String ,String>> ListOfOrderTrailer = new ArrayList<HashMap<String ,String>>();
	    	  		ArrayList<HashMap<String ,String>> ListOfCustomDetails = new ArrayList<HashMap<String ,String>>();
	    	  		
	    	  		OrderData = orderHeaderInfoCollectionList(lineSplitUsingTab,OrderData);
	    	  		OrdersRelatedData.put(orderNoString, OrderData);
	    	  		
	    	  		FileInputStream fstream1 = new FileInputStream("C:\\Users\\reddyp\\Desktop\\TX050420170753.ord");
	    	  		BufferedReader orderLevelReader = new BufferedReader(new InputStreamReader(fstream1));
		    	  		while ((strLineOrder = orderLevelReader.readLine()) != null){		    	  			
			    	  		orderLineReaderTab = strLineOrder.split("\t");
			    	  		loopOrderNo = orderLineReaderTab[3];
			    	  		recordTypeOrderLevel = orderLineReaderTab[0];
			    	  		if(loopOrderNo.contains(orderNoString)){			    	  			
			    	  			if(recordTypeOrderLevel.contains("002")){			    	  				
			    	  				OrderData = addressDetailsInfoCollectionList(orderLineReaderTab, OrderData, ListOfAddress);
			    	  			}			    	  			
			    	  			if(recordTypeOrderLevel.contains("004")){	    	  				
			    	  				OrderData = customDetailsDetailsInfoCollectionList(orderLineReaderTab, OrderData, ListOfCustomDetails);
			    	  			}			    	  			
			    	  			if(recordTypeOrderLevel.contains("006")){
			    	  				OrderData = lineItemInfoCollectionList(orderLineReaderTab, OrderData, ListOfLineItem);			    	  				
			    	  			}			    	  			
			    	  			if(recordTypeOrderLevel.contains("007")){
			    	  				OrderData = denominationsInfoCollectionList(orderLineReaderTab, OrderData, ListOfDenominations);
			    	  			}			    	  			
			    	  			if(recordTypeOrderLevel.contains("099")){
			    	  				OrderData = orderTrailerInfoCollectionList(orderLineReaderTab, OrderData, ListOfOrderTrailer);			    	  				
			    	  			}			    	  			
			    	  		}			    	  		
		    	  		}
		    	  	orderLevelReader.close();
		    	  	OrdersRelatedData.put(orderNoString, OrderData);
		    	  	MainList.add(OrdersRelatedData);
	        	}
	    	  	
	    	  	if(recordType.contains("999")){    	  		
	    	  		HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>> Trailer = new HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>>();	    	  		
	    	  		Trailer = fileFooterInfoCollectionList(lineSplitUsingTab,Trailer);
	    	  		MainList.add(Trailer);
	        	}	    	  	
	      }	      
	      br.close();
	      
		return MainList;
	}

	
	public static HashMap<String ,ArrayList<HashMap<String,String>>> customDetailsDetailsInfoCollectionList(String[] orderLineReaderTab,HashMap<String ,ArrayList<HashMap<String,String>>> OrderData, ArrayList<HashMap<String ,String>> ListOfCustomDetails){
		String /*recordType,customerID,recordSequenceNumber,customersOrderReference,version,*/keyword,keyworddata;
				
		HashMap<String,String> CustomDetails = new HashMap<String,String>();
				
/*		recordType = orderLineReaderTab[0];
		customerID =orderLineReaderTab[1];
		recordSequenceNumber =orderLineReaderTab[2];
		customersOrderReference =orderLineReaderTab[3];
		version =orderLineReaderTab[4];*/
		keyword =orderLineReaderTab[5];
		keyworddata =orderLineReaderTab[6];
		
/*		CustomDetails.put("RecordType", recordType);
		CustomDetails.put("CustomID", customerID);
		CustomDetails.put("RecordSequenceNumber", recordSequenceNumber);
		CustomDetails.put("CustomersOrderReference", customersOrderReference);
		CustomDetails.put("Version", version);
		CustomDetails.put("Keyword", keyword);*/
		CustomDetails.put(keyword, keyworddata);
				
		ListOfCustomDetails.add(CustomDetails);
		OrderData.put("CustomDetails", ListOfCustomDetails);		
		
		return OrderData;
		
	}

	
	public static boolean isCPLineItemExistsInOrder(List<HashMap<String, HashMap<String,ArrayList<HashMap<String,String>>>>> mainList,String OrderNo,int j){
		int lineItemsCount = mainList.get(j).get(OrderNo).get("LineItem").size();
		boolean Value = false;
		
		for(int k=0;k<=lineItemsCount;k++){
			if(mainList.get(j).get(OrderNo).get("LineItem").get(k).get("ProductType").contains("CP")){
				Value = true;
				break;				
			}
		}
		return Value;		 
	}

	
}
