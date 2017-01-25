package com.travelex.rap.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;


import com.travelex.framework.utilities.DriverFactory;
import com.travelex.framework.utilities.Locator;
import com.travelex.framework.utilities.Setup;
import com.travelex.framework.utilities.WebdriverWrapper;

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
		
	}
	
	public void Afterlaunch() {
		try{
		waitForElementToBeDisplayed("RAP Sales Module", rapMess);
		/*Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_ENTER);*/
		clickElementForVolta("Extract Orders Button", rapDialogOK);
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

	public void extractOrders(String vault) {
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
			waitForElementToBeDisplayed("Rap Sale dialog", rapSaleDialog);
			clickElementForVolta("RAP Dialog box", rapDialogYes);
			Thread.sleep(1000);
			if (isExists("RAP Dialog Error", rapSaleDialogError1)) {
				clickElementForVolta("RAP Dialog box", rapDialogOK);
				String vault2 = vault.toLowerCase();
				if (vault2.equals("johor")) {
					vault2 = "johur";
				}
				String voltaDB = "volta_" + vault2;
				//upDateQueryForExtractOrd(voltaDB);

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
	
	public void extractOrdersRap(String vault) {
		Reporter.log("<B><I><font size='4' color='Black'>"
				+ "----------------Extract Orders ----------------"
				+ "</font></I></B>");
		try {
			
			waitForElementToBeDisplayed("RAP Sales Module", rapSalesModule);
			Thread.sleep(1000);
			clickElementForVolta("Extract Orders Button", extractOrdersButton);
			// waitForElementToBeDisplayed("Print records", printRecords);
			clickElementForVolta("Extract pane",rapExtract2);
			Robot robot;
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			waitForElementToBeDisplayed("RAP Sale dialog", rapSaleDialog);
			clickElementForVolta("RAP Dialog box", rapDialogYes);
			Thread.sleep(1000);
			waitForElementToBeDisplayed("Succesful Extract Dialog",
					rapSaleDialogSuccess);
			clickElementForVolta("RAP Dialog box", rapDialogOK);
			/*if (isExists("Volta Dialog Error", voltaSaleDialogError1)) {
				clickElementForVolta("Volta Dialog box", voltaDialogOK);
				String vault2 = vault.toLowerCase();
				if (vault2.equals("johor")) {
					vault2 = "johur";
				}
				String voltaDB = "volta_" + vault2;
				upDateQueryForExtractOrd(voltaDB);

			} else {
				
			}*/
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
			clickElementForVolta("Schedule all orders", scheduleOrdersButton);
			if (isExists("RAP Error dialog", rapSaleDialogError2)) {
				clickElementForVolta("RAP Dialog box", rapDialogOK);
				Assert.fail();
				Reporter.log("There are no orders to schedule.");
			}
			waitForElementToBeDisplayed("Confirm schedule Pane",
					confSchdRunPane);
			clickElementForVolta("Confirm schedules", confirmButton);
			waitForElementToBeDisplayed("RAP Sale dialog", rapSaleDialog);
			clickElementForVolta("RAP Dialog box", rapDialogNo);
			waitForElementToBeDisplayed("RAP Sales Module", rapSalesModule);
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

			} else {
				clickElementForVolta("Select All batches", selectAllButton);
				clickElementForVolta("Confirm selection", confirmButtonTab);
			}
			waitForElementToBeDisplayed("RAP Sale dialog", rapSaleDialog);
			clickElementForVolta("RAP Dialog box", rapDialogYes);
			/*
			 * waitForElementToBeDisplayed("Volta Sale dialog",
			 * voltaSaleDialog); clickElementForVolta("Volta Dialog box",
			 * voltaDialogOK);
			 */
			waitForElementToBeDisplayed("RAP Sale dialog success message",
					rapSaleDialogSuccess2);
			clickElementForVolta("RAP Dialog box", rapDialogOK);
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
            WebElement element = getWebDriver().findElement(By.name("RAP AutoPR - Instance 1"));
            waitForElementToBeDisplayed(element, 5000);
            element.sendKeys(Keys.chord(Keys.TAB));
            element.sendKeys(Keys.chord(Keys.ALT+"S"));
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
	
	public void viewOrders(String orderRef){
		Reporter.log("<B><I><font size='4' color='Black'>"+ "---------------- View Orders ----------------"+ "</font></I></B>");
		try{
		clickElementForVolta("View Orders", viewOrders);
		waitForElementToBeDisplayed("ViewOrderRef", orderRefLbl);
		clearAndSendKeysForVolta(orderRefField, orderRef);
		clickElementForVolta("Retrieve", retrieveButtonTab);
		waitForElementToBeDisplayed("ViewOrderRef", orderRefLbl2);
		clickElementForVolta("CancelOrder Button", cancelOrder);
		Thread.sleep(2000);
		waitForElementToBeDisplayed("Reason Text", canReasonTxt);
		
		clearAndSendKeysForVolta(canReasonTxt,"Automation Testing");
		clickElementForVolta("RAP Dialog box", rapDialogOK);
		waitForElementToBeDisplayed("RAP Sale dialog", rapSaleDialog);
		clickElementForVolta("RAP Dialog box", rapDialogYes);
		waitForElementToBeDisplayed("RAP Sale dialog", rapSaleDialog);
		clickElementForVolta("RAP Dialog box", rapDialogOK);
		waitForElementToBeDisplayed("Close button", closeButtonTab);
		clickElementForVolta("Close button", closeButtonTab);
		}catch (Exception e){
			Assert.fail("View Orders Function Failed"+e.getMessage());
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
		
	
	/*public void upDateQueryForExtractOrd(String voltaDB) throws SQLException {
		Recordset inputData = getRecordSet("select * from IT2 where TestCase_Name='ReportInventory'");

		Connection dbObj = null;
		Statement stmt = null;
		String URL = "", dbName = "", userName = "", passWord = "";

		try {
			while (inputData.next()) {
				URL = inputData.getField("URL");
				dbName = inputData.getField("dbName");
				userName = inputData.getField("userName");
				passWord = inputData.getField("passWord");

				if (!(URL == null) && !(URL == "")) {
					break;
				}
			}

			if ((URL == null) || (URL == "")) {
				Reporter.log("No Data base Url Found");
				return;
			}

			dbObj = connectDB(URL, dbName, userName, passWord);
			stmt = dbObj.createStatement();
			String updateQuery = "use ["
					+ voltaDB
					+ "] "
					+ "update rap_system_parameter set extract_in_progress = 'N'";
			System.out.println(updateQuery);
			stmt.executeUpdate(updateQuery);
			System.out.println("Updated queries Successfully: ");

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (stmt != null)
				stmt.close();
			if (dbObj != null)
				dbObj.close();

		}

	}*/
	
/*	public void cancelOrderInSale(String OrderReference) {
		Reporter.log("<B><I><font size='4' color='Black'>"+ "----------------Cancel Order In Sale ----------------"+ "</font></I></B>");
		try {
			waitForElementToBeDisplayed("Volta Sales Module", voltaSalesModule);
			Thread.sleep(1000);
			clickElementForVolta("Extract Orders Button", extractOrdersButton);
			// waitForElementToBeDisplayed("Print records", printRecords);
			clickElementForVolta("Extract All Orders checkbox",
					extractAllChkbox);
			// Thread.sleep(500);
			// waitForElementToBeDisplayed("Select records button",
			// selectButton);
			// clickElementForVolta("Select extracted orders", selectButton);
			Robot robot;
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			waitForElementToBeDisplayed("Volta Sale dialog", voltaSaleDialog);
			clickElementForVolta("Volta Dialog box", voltaDialogYes);
			Thread.sleep(1000);
			if (isExists("Volta Dialog Error", voltaSaleDialogError1)) {
				clickElementForVolta("Volta Dialog box", voltaDialogOK);
				String vault2 = vault.toLowerCase();
				if (vault2.equals("johor")) {
					vault2 = "johur";
				}
				String voltaDB = "volta_" + vault2;
				upDateQueryForExtractOrd(voltaDB);

			} else {
				waitForElementToBeDisplayed("Succesful Extract Dialog",
						voltaSaleDialogSuccess);
				clickElementForVolta("Volta Dialog box", voltaDialogOK);
			}
			// waitForElementToBeDisabled("Extract Dialog",
			// extractOrdersDialog);
		} catch (Exception e) {

		}

	}*/

}
