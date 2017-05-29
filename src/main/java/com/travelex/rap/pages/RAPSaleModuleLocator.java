package com.travelex.rap.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;










import com.travelex.framework.utilities.DriverFactory;
import com.travelex.framework.utilities.Locator;
import com.travelex.framework.utilities.RAPUtilities;
import com.travelex.framework.utilities.Setup;
import com.travelex.framework.utilities.WebdriverWrapper;
import com.travelex.stepDefinitions.MasterDataReader;

public class RAPSaleModuleLocator extends WebdriverWrapper {
                
	public static Locator rapSalesModule=new Locator ("RapSalesModule","name","Travelex Currency Services RAP");//change in Name Volta -> RAP
	public static Locator exitRapSales=new Locator ("ExitRapSalesModule","name","Exit");
	public static Locator rapAutoPRModule=new Locator ("RapAutoPRModule","xpath","//*[contains(@ClassName,'FNWND3120')]");

	//Extract Order
	public static Locator extractOrdersButton=new Locator ("ExtractOrders","name","Extract Orders");
	public static Locator extractAllChkbox=new Locator ("ExtractAll","name","Extract All");
	public static Locator selectButton=new Locator ("SelectOrder","id","1006");
	public static Locator printRecords=new Locator ("PrintRecords","name","Print Reports");
	public static Locator extractOrdersDialog=new Locator ("ExtractOrderDialogBox","name","Extracting Orders");

	//this can be used for print message
	public static Locator rapSaleDialog=new Locator ("RapDialogBox","name","RAP");//change in Name
	public static Locator rapSaleDialog2=new Locator ("RapDialogBox2","class","FNWNS3120");
	
	public static Locator rapSaleDialogError1=new Locator ("rapDialogBoxErrorMessage","name","Another extract is currently in progress. (E3751)");
	public static Locator rapSaleDialogSuccess=new Locator ("RapDialogBoxSuccessMessage","name","The Extract has completed.");
	public static Locator rapSaleDialogError2=new Locator ("rapDialogBoxErrorMessage","name","There are no orders to schedule for this run. (I3607)");
	public static Locator rapSaleDialogError3=new Locator ("rapDialogBoxErrorMessage","name","No data could be retrieved using the information contained in the search area. (I0001)");
	public static Locator rapSaleDialogSuccess2=new Locator ("RapDialogBoxSuccessMessage","name","Selected Batches have been confirmed as successfully dispensed. (I3603)");
	public static Locator rapDialogYes=new Locator ("DialogBox","name","Yes");
	public static Locator rapDialogNo=new Locator ("DialogBox","name","No");
	
	//need to use this locator for error messages like "another extract currently in process"
	public static Locator rapDialogOK=new Locator ("DialogBoxOK","name","OK");
	public static Locator cnfschdRunScen=new Locator ("Pane","name","order_currency_run_id_t");

	//Schedule order
	public static Locator scheduleOrdersButtonTab=new Locator ("ScheduleOrders","name","Schedule Orders");
	public static Locator allOrdersList=new Locator ("AllOrdersRunProfile","name","All Orders");
	public static Locator retrieveButton=new Locator ("RetrieveOrder","name","Retrieve");
	public static Locator scheduleOrdersButton=new Locator ("ScheduleOrder","name","Schedule");
	public static Locator confSchdRunPane=new Locator ("ConfirmSchedule","name","Confirm Schedule Run");
	public static Locator confirmButton=new Locator ("ConfirmScheduleRun","name","Confirm");

	//tab
	public static Locator ordersButtonTab=new Locator ("Orders","name","Orders");
	public static Locator batchButtonTab=new Locator ("Batch","name","Batch");
	public static Locator cnfManualBatchButtonTab=new Locator ("ConfirmManualBatch","name","Confirm Manual Batch");
	public static Locator retrieveButtonTab=new Locator ("RetrieveBatch","name","Retrieve");
	public static Locator confirmButtonTab=new Locator ("ConfirmBatch","name","Confirm");
	public static Locator closeButtonTab=new Locator ("CloseBatch","name","Close");
	public static Locator selectAllButton=new Locator ("SelectAll","name","Select All");
	public static Locator selectionAreaPane=new Locator ("SelectionAreaPane","name","Selection Area   ");

	//AutoPR
	public static Locator startButton=new Locator ("StartAutoPR","id","1006");
	public static Locator closeButton=new Locator ("CloseAutoPR","id","1009");
	                
	// View Order
	public static Locator viewOrders=new Locator ("ViewOrderButton","name","View Orders");
	public static Locator orderRefLbl=new Locator ("OrderReferenceLable","name","t_3");
	public static Locator orderRefField=new Locator ("OrderReferenceTextField","name","orderreference");
	public static Locator cancelOrder=new Locator ("CancelOrderButton","name","Cancel Order");
	public static Locator orderRefLbl2=new Locator ("OrderReferenceLable","name","reference_code");
	public static Locator canReasonTxt=new Locator ("CancellationReasonTextField","id","10");
	public static Locator canReasonTxt1=new Locator ("CancellationReasonTextField","name","cancellation_reason_t");
	public static Locator cancelerrmessage = new Locator("CancelBtn","id","65535");
	public static Locator orderCancellationReason = new Locator("OrderCancellationReason","name","Order Cancellation Reason");
	public static Locator cancellationReasonTxtField = new Locator("CancellationReasonTextField","name","cancellation_reason");
    
	// RAP
	public static Locator rapDialogue=new Locator ("RAP Auto","name","RAPSelect   ");
	public static Locator rapExtract2=new Locator ("RAP Auto","name","Select   ");   
	public static Locator rapMess=new Locator ("RAP","name","RAP");  
	
	public static Locator maxBatchSize=new Locator ("Maximum BatchSize","name","batch_size");  
	public static Locator collatorOrder=new Locator ("Collator Order","name","auto_orders_per_run");
	public static Locator manualOrder=new Locator ("Collator Order","name","manual_orders_per_run");
	public static Locator rapSaleScheduleMsg=new Locator ("RapDialogBoxScheduleMessage","name","The manual batch size will exceed 100 orders. Continue ? (W3822)");
	public static Locator autoPR = new Locator("autoPR","name","RAP AutoPR - Instance 1");
	public static Locator overrideAutoOrd = new Locator("auto Orders","name","Override Auto Orders");
	
	
	public static Locator printername = new Locator("printer name","name","\\\\inmum-inf-pr01\\Sharp Smart Printer on Ne01:");
	public static Locator runIds = new Locator("runID","name","no_of_orders");
	
	//reprint Cust advice
	public static Locator reprintCustAdvincePane = new Locator("Reprint cust Pane","name","Reprint Customer Advice");
	public static Locator colltorOnRCPane = new Locator("collator","id","10");
	public static Locator batchonRCPane = new Locator("batch","id","12");
	public static Locator reprintBtn = new Locator("reprint","name","Reprint");
	public static Locator CancelBtn = new Locator("runID","name","Cancel");
	public static Locator errorMsgRCPane = new Locator("Error","name","Run ID and Batch ID must not be blank.  Please enter the required information. (E0029)");
	
	Connection con = null;
	Statement stmt = null;
	ResultSet dbRecs = null;
	ResultSet dbRecsChk = null;
	ResultSet dbRecsDel = null;
	ResultSet rstodenom = null;
	String selectQuery = "",
			updateQuery = null;
	
	public static String URL = "usvi-lvcs-st31.cwqiaun4zkm4.us-east-1.rds.amazonaws.com";
	public static String dbName = "RAP";
	public static String userName = "gusaina";
	public static String passWord = "Travelex1";
	/*URL = "GBPB-RAP-DE31";
	dbName = "RAP";*/	
	public static HashMap<String,String> saleDetails ;
	public static ArrayList<String> batchDetails;
	
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to launch Sales RAP
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Ramchandra Devalkar
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */	
	
	public void launchSaleVolta() {
		
		Date salevoltaLaunchTime = new java.util.Date();
		Reporter.log("<B><I><font size='3' color='Blue'> Sucess Message Pop Up Apperance Time : "+ new Timestamp(salevoltaLaunchTime.getTime())+"</font></I></B>");
		WebDriver driver = Setup.voltaSaleDriver();
		DriverFactory.setWebDriver(driver);
		MasterDataReader.driver = driver;
		
	}
	
	public void Afterlaunch() {
		try{
		waitForElementToBeDisplayed("RAP Sales Module", rapMess);
		clickElementForVolta("Ok Button", rapDialogOK);
		Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to perform extract orders in sales RAP
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Ramchandra Devalkar
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */

	public void extractOrders() {
		Reporter.log("<B><I><font size='4' color='Black'>"
				+ "----------------Extract Orders ----------------"
				+ "</font></I></B>");
		try {
			waitForElementToBeDisplayed("RAP Sales Module", rapSalesModule);
			Thread.sleep(1000);
			clickElementForVolta("Extract Orders Button", extractOrdersButton);
			// waitForElementToBeDisplayed("Print records", printRecords);
			clickElementForVolta("Extract All Orders checkbox",extractAllChkbox);
			Robot robot;
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			//Thread.sleep(900000);
			DriverFactory.setWebDriverWait(900);
			waitForElementToBeDisplayed("Rap Sale dialog", rapSaleDialog);
			DriverFactory.setWebDriverWait(30);
			clickElementForVolta("RAP Dialog box", rapDialogYes);
			Thread.sleep(1000);
			if (isExists("RAP Dialog Error", rapSaleDialogError1)) {
				clickElementForVolta("RAP Dialog box", rapDialogOK);
			} else {
				waitForElementToBeDisplayed("Succesful Extract Dialog",
						rapSaleDialogSuccess);
				clickElementForVolta("RAP Dialog box", rapDialogOK);
			}
			
		} catch (Exception e) {
			Assert.fail("Extract Order Function Failed"+e.getMessage());
			e.printStackTrace();
		}

	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to perform Schedule orders in sales RAP
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Ramchandra Devalkar
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */
	
	public void scheduleOrders() {
		Reporter.log("<B><I><font size='4' color='Black'>"+ "----------------Schedule Orders ----------------"+ "</font></I></B>");
		try {
			
			Robot rb = new Robot();
			rb.delay(500);
			rb.keyPress(KeyEvent.VK_ALT);
			rb.keyPress(KeyEvent.VK_F);
			rb.keyPress(KeyEvent.VK_U);
			rb.keyRelease(KeyEvent.VK_ALT);
			

			waitForElementToBeDisplayed("printer list", printername);
			clickElementForVolta("printer", printername);
			rb.delay(300);
			clickElementForVolta("RAP Dialog box", rapDialogOK);
			
			waitForElementToBeDisplayed("RAP Sales Module", rapSalesModule);
			Thread.sleep(1000);
			clickElementForVolta("Schedule Orders Button",
					scheduleOrdersButtonTab);
			/*WebElement element = getWebDriver().findElement(
					By.name("run_profile_id"));*/
			//element.click();
			//element.sendKeys(pname);
			clickElementForVolta("Retrive all orders", retrieveButton);
			Thread.sleep(500);
			
			clearAndSendKeysForVolta(maxBatchSize, "500");
			clearAndSendKeysForVolta(collatorOrder, "1000");
			clearAndSendKeysForVolta(manualOrder, "500");
			clickElementForVolta("Override Auto orders", overrideAutoOrd);
			
			clickElementForVolta("Schedule all orders", scheduleOrdersButton);
			if (isExists("RAP Error dialog", rapSaleDialogError2)) {
				clickElementForVolta("RAP Dialog box", rapDialogOK);
				Assert.fail();
				Reporter.log("There are no orders to schedule.");
			}
			if(isExists("Schedule Error Msg", rapSaleScheduleMsg)){
				clickElementForVolta("RAP Dialog box", rapDialogYes);
			}
			
			waitForElementToBeDisplayed("Confirm schedule Pane",confSchdRunPane);
			clickElementForVolta("Confirm schedules", confirmButton);
			if(isExists("RAP Popup Message", rapSaleDialog)){
				//waitForElementToBeDisabled("RAP Dialog box", rapSaleDialog);
				waitForElementToBeDisplayed("Schedule Pane",rapSalesModule);
				Thread.sleep(15000);
			}
			waitForElementToBeDisabled("RAP Dialog box", rapSaleDialog);
			waitForElementToBeDisplayed("RAP Instance Pane",rapSalesModule);
		} catch (Exception e) {
			Assert.fail("Schedule Order Function Failed"+e.getMessage());
			e.printStackTrace();
		}

	}

	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to perform ConfirmManualBatch orders in sales RAP
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Ramchandra Devalkar
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */
	
	public void confirmManualBatch() {
		Reporter.log("<B><I><font size='4' color='Black'>"+ "----------------Confirm Manual Batch Orders ----------------"+ "</font></I></B>");
		try {
			clickElementForVolta("Orders tab", ordersButtonTab);
			clickElementForVolta("Batch", batchButtonTab);
			clickElementForVolta("Confirm Manual Batch",
					cnfManualBatchButtonTab);
			Thread.sleep(500);
			clickElementForVolta("Retrieve batches", retrieveButtonTab);
			if (isExists("Error message", rapSaleDialogError3)) {
				clickElementForVolta("RAP Dialog box", rapDialogOK);
				Assert.fail("No data could be retrieved using the information contained in the search area.");
				Reporter.log("No data could be retrieved using the information contained in the search area.");
			}

			if (isExists("DetailsPane", cnfschdRunScen)) {
				clickElementForVolta("Confirm selection", confirmButtonTab);
				waitForElementToBeDisplayed("RAP Sale dialog", rapSaleDialog);
				clickElementForVolta("RAP Dialog box", rapDialogYes);
				
				waitForElementToBeDisplayed("RAP Sale dialog success message",
						rapSaleDialogSuccess2);
				clickElementForVolta("RAP Dialog box", rapDialogOK);

			} else {
				List<WebElement> rows = getWebDriver().findElements(By.name("no_of_orders"));
				int noofRows = rows.size();
				for(int i=0;i<noofRows;i++){
					clickElementForVolta("Run id", runIds);
					clickElementForVolta("Confirm selection", confirmButtonTab);
					waitForElementToBeDisplayed("RAP Sale dialog", rapSaleDialog);
					clickElementForVolta("RAP Dialog box", rapDialogYes);
					
					waitForElementToBeDisplayed("RAP Sale dialog success message",
							rapSaleDialogSuccess2);
					clickElementForVolta("RAP Dialog box", rapDialogOK);
				}
				
			}
			
			
			//Update the code once SELECT ALL is fixed
			/*
			 
				clickElementForVolta("Select All batches", selectAllButton);
				clickElementForVolta("Confirm selection", confirmButtonTab);
			}
			waitForElementToBeDisplayed("RAP Sale dialog", rapSaleDialog);
			clickElementForVolta("RAP Dialog box", rapDialogYes);
			
			waitForElementToBeDisplayed("RAP Sale dialog success message",
					rapSaleDialogSuccess2);
			clickElementForVolta("RAP Dialog box", rapDialogOK);
			 */
			
			clickElementForVolta("Close Pane", closeButtonTab);
			Thread.sleep(500);

		} catch (Exception e) {
			Assert.fail("Confirm Manual Batch Function Failed"+e.getMessage());
			e.printStackTrace();
		}

	}

	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to exit sales RAP
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Ramchandra Devalkar
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */
	
	public void closeSalesVolta() {
		Reporter.log("<B><I><font size='4' color='Black'>"+ "---------------- Close Sales RAP ----------------"+ "</font></I></B>");
		waitForElementToBeDisplayed("RAP Sales Module", rapSalesModule);
		clickElementForVolta("Exit Button", exitRapSales);

		waitForElementToBeDisplayed("RAP Sale dialog", rapSaleDialog);
		clickElementForVolta("RAP Dialog box", rapDialogYes);

	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to launch AutoPR RAP
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Ramchandra Devalkar
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */

	public void launchAutoPRVolta() {
		Date autoPRvoltaLaunchTime = new java.util.Date();
		Reporter.log("<B><I><font size='3' color='Blue'> Sucess Message Pop Up Apperance Time : "+ new Timestamp(autoPRvoltaLaunchTime.getTime())+"</font></I></B>");
		WebDriver driver = Setup.voltaAutoPRDriver();
		DriverFactory.setWebDriver(driver);
		MasterDataReader.driver = driver;
	}

	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to start AutoPR process 
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Ramchandra Devalkar
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */
	
	public void autoPRProcess() {
		Reporter.log("<B><I><font size='4' color='Black'>"+ "---------------- AutoPR Process ----------------"+ "</font></I></B>");
		try {		
			Thread.sleep(3000);
			waitForElementToBeDisplayed("AutoPR instance",autoPR);
            WebElement element = getWebDriver().findElement(By.className("FNWND3120"));
            element.click();
            element.sendKeys(Keys.chord(Keys.TAB));
            element.sendKeys(Keys.chord(Keys.ALT+"S"));
            /*Robot rb = new Robot();
            rb.keyPress(KeyEvent.VK_TAB);
            rb.keyPress(KeyEvent.VK_ALT);
            rb.keyPress(KeyEvent.VK_S);
            rb.keyRelease(KeyEvent.VK_ALT);*/
            Thread.sleep(10000);
            clickElementForVolta("Close AutoPR process", closeButton);
            Thread.sleep(10000);
         
            if(isExists("Close AutoPR process", closeButton)){
                 	clickElementForVolta("Close AutoPR process", closeButton);
                 	Thread.sleep(10000);
            }
           
		} catch (Exception e) {
			Assert.fail("AutoPR Process Function Failed"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to perform view orders in sales RAP
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Ramchandra Devalkar
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */
	
	public void reprintCustAdvice(String batchid,String collatorID){
		try{
		waitForElementToBeDisplayed("RAP Sales Module", rapSalesModule);
		clickElementForVolta("Orders tab", ordersButtonTab);
		clickElementForVolta("Batch", batchButtonTab);
		clickElementForVolta("Reprint advice pane", reprintCustAdvincePane);
		waitForElementToBeDisplayed("Reprint advice pane", reprintCustAdvincePane);
		clickElementForVolta("Collator", colltorOnRCPane);
		clearAndSendKeysForVolta(colltorOnRCPane, collatorID);
		clickElementForVolta("Retrieve", retrieveButtonTab);
		if(isExists("error msg", errorMsgRCPane)){
			clickElementForVolta("Ok Button", rapDialogOK);
		}
		WebElement ele = getWebDriver().findElement(By.id("11"));
		ele.click();
		ele.sendKeys(batchid);
	
		clickElementForVolta("Retrieve", retrieveButtonTab);
		Thread.sleep(500);
		ele = getWebDriver().findElement(By.name("reference_code"));
		ele.click();
		clickElementForVolta("Reprint", reprintBtn);
		Thread.sleep(15000);
		clickElementForVolta("Cancel", CancelBtn);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to update DB for sales RAP(in progress)
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Ramchandra Devalkar
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */
	

	@SuppressWarnings("unused")
	public void fetchSaleRecordsFromDB(String orderRef,boolean isMulti) throws SQLException{
		try{
			RAPUtilities rapUtils = new RAPUtilities();
			saleDetails = new HashMap<String,String>();
			
			if(!isMulti){
				selectQuery = "select * from TXM_ORDER_HEADER where customer_order_ref = '"+orderRef+"'";
				String deleteQuery = "";
				String updateQuery = "";

				con = rapUtils.dbConnect(URL, dbName, userName, passWord);
				stmt = con.createStatement();
				dbRecs = stmt.executeQuery(selectQuery);
				boolean isPresent = dbRecs.next();

				while(isPresent){
					String orderId = dbRecs.getString("order_id");
					String CustRefNum = dbRecs.getString("customer_order_ref");
					saleDetails.put("OrderRef", orderId);
					saleDetails.put("CustRef", CustRefNum);

					//only for DB orders
					/*String[] table = {"orders","order_currency","order_keywords","order_denomination","part_orders","order_custom_information","cpp_orders","cpp_order_lineitems","cpp_add_info","rap_confirmation","rap_order_header","rap_line_item","rap_denomination","Draft_Order_Beneficiaries","rap_part_orders"};
				for(int i=0;i<table.length;i++){
					deleteQuery = "delete from "+table[i]+" where order_id = "+orderId+"";
					int totalRecs = stmt.executeUpdate(deleteQuery);
				}
				updateQuery = "update txm_order_header set processed = '#' where  order_id = "+orderId+"";
				stmt.executeUpdate(updateQuery);*/
					break;
				}
				if(isPresent==false){
					System.out.println("Record not found");
					Assert.fail();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}/*finally{
			dbRecs.close();
			stmt.close();
			con.close();
		}*/

	}
	
	public String getOrderStatus(String query,String flag) throws SQLException{
		RAPUtilities rapUtils = new RAPUtilities();
		String status = null;
		String runId = null;
	
		con = rapUtils.dbConnect(URL, dbName, userName, passWord);
		stmt = con.createStatement();
		dbRecs = stmt.executeQuery(query);
		
		while(dbRecs.next()){
			status = dbRecs.getString("order_status");
			if(flag.equalsIgnoreCase("Yes")){
				runId = dbRecs.getString("run_id");
			}
		}
		
		dbRecs.close();
		stmt.close();
		con.close();
		saleDetails.put("status", status);
		saleDetails.put("runid", runId);
		return status;
	}
	
	public boolean checkRapConfirmation(String query) throws SQLException{
		RAPUtilities rapUtils = new RAPUtilities();
		boolean isSuccess = false;
		String errorMsg = null;
		
		con = rapUtils.dbConnect(URL, dbName, userName, passWord);
		stmt = con.createStatement();
		dbRecs = stmt.executeQuery(query);
		
		boolean isExist = dbRecs.next();
		
		if(isExist){
				errorMsg = dbRecs.getString("repair_detail");
		}else{
			isSuccess = true;
		}
		dbRecs.close();
		stmt.close();
		con.close();
		saleDetails.put("errMsg", errorMsg);
		return isSuccess;
	}
	
	
	public void getBatchNoDetails(String query) throws SQLException{
		RAPUtilities rapUtils = new RAPUtilities();
		batchDetails = new ArrayList<String>();
		con = rapUtils.dbConnect(URL, dbName, userName, passWord);
		stmt = con.createStatement();
		dbRecs = stmt.executeQuery(query);
		
		boolean isExist = dbRecs.next();
		if(isExist){
			while(isExist){
				batchDetails.add(dbRecs.getString("batch_id"));
				if(!dbRecs.isLast()){
				dbRecs.next();
				}else{
					break;
				}
			}
		}else{
			System.out.println("Nothing to print");
		}
		
		dbRecs.close();
		stmt.close();
		con.close();
	}

}
