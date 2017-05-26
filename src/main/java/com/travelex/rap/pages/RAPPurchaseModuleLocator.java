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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.travelex.framework.utilities.DriverFactory;
import com.travelex.framework.utilities.Locator;
import com.travelex.framework.utilities.RAPUtilities;
import com.travelex.framework.utilities.Setup;
import com.travelex.framework.utilities.WebdriverWrapper;
import com.travelex.stepDefinitions.MasterDataReader;

public class RAPPurchaseModuleLocator extends WebdriverWrapper {
	
	public static String fitAmount;
		
	public static Locator rapPurchaseModule=new Locator ("RAPPurchaseModule","name","RAP - Purchase Module"); 
	public static Locator purchasesDialogWindow=new Locator ("PurchasesDialogWindow","name","Purchases");
	public static Locator exchangeRateMessage=new Locator ("ExchangeRateMessage","id","65535");
	
	public static Locator afterLaunchMessageBox=new Locator ("AfterLaunchMessage","name","Purchases");
	public static Locator afterLaunchYes=new Locator ("Yes After launch","id","6");
	
	public static Locator importCompletedDialog=new Locator ("ImportCompleted","name","RAP");
	public static Locator importPurchasesWindow=new Locator ("ImportPurchaseWindow","name","Import Purchases"); 
	public static Locator importButton=new Locator ("ImportButton","name","Import"); 
	public static Locator yesButton=new Locator ("YesButton","name","Yes"); 
	public static Locator noButton=new Locator ("NoButton","name","No"); 
	public static Locator okButton=new Locator ("OkButton","id","2"); 
	public static Locator supervisorMenu=new Locator ("SupervisorMenu","name","Supervisor"); 
	public static Locator importPurchasesMenu=new Locator ("ImportPurchasesMenu","name","Import Purchases"); 
	//public static Locator selectPurchaseBag=new Locator ("SelectPurchaseBagWindow","name","Select Purchase Bag");
	public static Locator selectPurchaseBag=new Locator ("SelectPurchaseBagWindow","name","Select Purchase Order");
	public static Locator selectPurchaseBag1=new Locator ("SelectPurchaseBagWindowq","class","FNWNS3120");
	//public static Locator retrieveButton=new Locator ("RetrieveButton","id","1006"); 
	public static Locator retrieveButton=new Locator ("RetrieveButton","name","Retrieve");
	//public static Locator selectButton=new Locator ("SelectButton","id","1020");
	public static Locator selectButton=new Locator ("SelectButton","name","Select"); 
	public static Locator selectPurchaseOrderWindow=new Locator ("SelectPurchaseOrderWindow","name","Select Purchase Order"); 
	public static Locator remittanceWindow=new Locator ("RemittanceWindow","name","Remittance");
	
	public static Locator purchaseOrderRef=new Locator ("PurchaseOrderRef","name","purchases_purchase_id");
	//public static Locator shortQtyTextField=new Locator ("ShortQuantityTextField","name","short_qty");
	public static Locator shortQtyTextField=new Locator ("ShortQuantityTextField","name","Edit");
	public static Locator saveButton=new Locator ("SaveButton","id","1017");
	//public static Locator saveButton=new Locator ("SaveButton","name","Save");
	//public static Locator refCodeTextField=new Locator ("RefCodeTextField","name","reference_code");
	public static Locator refCodeTextField=new Locator ("RefCodeTextField","id","11");
	public static Locator closeBtn=new Locator ("CloseBtn","id","1003"); 
	public static Locator amalgamationWindow=new Locator ("AmalgamationWindow","name","Amalgamation"); 
	public static Locator previewButton=new Locator ("PreviewButton","name","Preview");
	public static Locator amalgamateButton=new Locator ("AmalgamateButton","name","Amalgamate");
		
	public static Locator previewPaneClick=new Locator ("PreviewPaneClick","id","1000");
	public static Locator amalgamationRowClick=new Locator ("AmalgamationRowClick","name","amalgamation_id");
	public static Locator confirmButton=new Locator ("ConfirmButton","name","Confirm");
	public static Locator batchNumberMessage=new Locator ("BatchNumberMessage","id","65535");
	
	public static Locator updatePurchInventoryWindow=new Locator ("updatePurchaseInventoryWindow","name","Update Purchase Inventory");	
	public static Locator updateButton=new Locator ("UpdateButton","name","Update"); 	
	public static Locator close=new Locator ("CloseButton","id","1003");
	public static Locator closeButton=new Locator ("CloseButton","name","Close");	
	public static Locator saleRAPWindow=new Locator ("saleRAPWindow","name","Travelex Currency Services RAP"); 
	
	public static Locator stock=new Locator ("stock","name","Stock"); 
	public static Locator importPurchaseInventory=new Locator ("stock","name","Import Purchase Inventory"); 
	public static Locator saleUpdateButton=new Locator ("SaleUpdateButton","name","Update"); 
	public static Locator exitButton=new Locator ("exitButton","id","1005");  
	public static Locator dialogRAP=new Locator ("dialogRAP","name","RAP");
	public static Locator rapDialogOK=new Locator ("DialogBoxOK","name","OK");
	
	public static Locator errorMessage = new Locator("errmessage","id","65535");
	//public static Locator errorMessage = new Locator("errmessage","name","Denom value cannot be null. (E4007)");
	
	public static Locator denomValueDropDown = new Locator("denomValueDropDown","name","denom_value");
	//public static Locator fitTextField = new Locator("FitAmountTextField","name","fit");65546
	public static Locator fitTextField = new Locator("FitAmountTextField","name","65546");
	//public static Locator fitTextField = new Locator("FitAmountTextField","id","12");
	public static Locator cancelBtn = new Locator("CancelBtn","name","Cancel");
	public static Locator cancelerrmessage = new Locator("CancelBtn","id","65535");
	
	public static Locator orderCancellationReason = new Locator("OrderCancellationReason","name","Order Cancellation Reason");
	public static Locator cancellationReasonTxtField = new Locator("CancellationReasonTextField","name","cancellation_reason");
	public static Locator okBtn = new Locator("OK Button","id","1001");	
	public static Locator importToBeginMessage = new Locator("ImportToBeginMessage","name","Click Import Button to begin Download");	
	public static Locator importConfirmationMessage = new Locator("ImportConfirmationMessage","name","Do you wish to proceed with the Purchases Import ? (W3812)");	
	public static Locator importCompleted = new Locator("ImportCompleted","name","The Import has completed.");
	public static Locator noDataerrorMessage = new Locator("NoDataErrorMessage","name","No data could be retrieved using the information contained in the search area. (I0001)");	
	public static Locator remittanceCancelledMsg = new Locator("RemittanceCancelledMsg","name","The remittance will be cancelled from the file.\r\nAre you sure you want to Continue? (W3809)");	
	public static Locator exitPurchaseModule = new Locator("ExitPurchaseModule","name","Do you want to exit RAP - Purchase Module? (W3722)");
	
	public static Locator reasonerrorMessage = new Locator("readerErrorMessage","name","reason code cannot be null. (E4007)");	
	
	public static Locator xchangeRate = new Locator("rate","name","actual_customer_exch_rate");
	public static Locator curr = new Locator("rate","id","1000");
	public static Locator rapMess=new Locator ("RAP","name","RAP");  
	
	//Checks AMalgamation
	public static Locator checksAmalPane = new Locator("Checks Amal Pane","name","Amalgamate Checks");
	public static Locator updateInvchecksPane = new Locator("Update Inv Checks Pane","name","Update Check Inventory");
	public static Locator updateBtn = new Locator("Update button","name","Update");
	
	public static Locator noRateMsg = new Locator("No rate","name","There is no exchange rate available for today.\r\nDo you wish to continue ? (W3814)");
	public static Locator invMovementMsg= new Locator("Inventory Movement","name","Moving Fit Bal  for TVX/ CASH/All currencies/All denoms from FIT to DEFAULT. Please confirm. (W3805)");
	
	public static Locator UpdateChkInvPane = new Locator("Update Check Pane","id","1000");
	public static Locator checkInvCount = new Locator("Update Check Pane count","name","bank");

	//actual_customer_exch_rate
	
	Connection con = null;
	Statement stmt = null;
	ResultSet dbRecs = null;
	ResultSet dbRecsChk = null;
	ResultSet dbRecsDel = null;
	ResultSet rstodenom = null;
	ResultSet rstSuppliers = null;
	String selectQuery = "",updateQuery = null;
	
	/*URL = "GBPB-RAP-DE31";
	dbName = "RAP";*/
	public static String URL = "usvi-lvcs-st31.cwqiaun4zkm4.us-east-1.rds.amazonaws.com";
	public static String dbName = "RAP";
	public static String userName = "gusaina";
	public static String passWord = "Travelex1";	
	
	public static HashMap<String,String> RAPOrderDetails ;
	public static ArrayList<HashMap<String,String>> currList ;
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to Launch Purchase RAP
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Rakesh Karkare
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */	
	
	
	public void launchPurchaseRAP(){
		try{
		Date purchaseRAPLaunchTime = new java.util.Date();
		Reporter.log("<B><I><font size='3' color='Blue'> Sucess Message Pop Up Apperance Time : "+ new Timestamp(purchaseRAPLaunchTime.getTime())+"</font></I></B>");
		WebDriver driver = Setup.voltaPurchaseDriver();
		MasterDataReader.driver = driver;
		DriverFactory.setWebDriver(driver);
		}catch(Exception e){
			Assert.fail("Launch Purchases Function Failed"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void afterLaunchPurchase(){
		try{
		waitForElementToBeDisplayed("Pop up Message", afterLaunchMessageBox);
		Thread.sleep(3000);
		clickElementForVolta("Yes Button", afterLaunchYes);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to Import Purchases
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Rakesh Karkare
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */	
	
	public void importPurchases(){
		Reporter.log("<B><I><font size='4' color='Black'>"+"----------------Import Purchases ----------------"+ "</font></I></B>");
		try {
			waitForElementToBeClickable("RAP Purchase Module", rapPurchaseModule);
			waitForElementToBeDisplayed("RAP Purchase Module", rapPurchaseModule);
			Thread.sleep(1000);
			WebElement element = getWebDriver().findElement(By.name("Supervisor"));
			element.sendKeys(Keys.chord(Keys.ALT+"I"));
			
			waitForElementToBeDisplayed("Import Purchases Window", importPurchasesWindow);
			Thread.sleep(1000);
			verifyAttributeValueForVolta("Click Import To Begin", importToBeginMessage, "Click Import Button to begin Download", "Name");
			clickElementForVolta("Import Button", importButton);
			
			waitForElementToBeDisplayed("RAP Purchases Dialog", purchasesDialogWindow);
			verifyAttributeValueForVolta("Import Confirmation Message", importConfirmationMessage, "Do you wish to proceed with the Purchases Import ? (W3812)", "Name");
			clickElementForVolta("Yes Button", yesButton);
			
			waitForElementToBeDisplayed("Import Completed Dialog", importCompletedDialog);
			verifyAttributeValueForVolta("Import Completed Message", importCompleted, "The Import has completed.", "Name");
			
			clickElementForVolta("Ok Button", okButton);
		} catch (Exception e) {
			Assert.fail("Import Purchases Function Failed"+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to Process Purchase Order
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Rakesh Karkare
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */	
	
	public void processPurchaseOrder(String OrderNumber,String Denom,String Amount){
		WebDriver driver = DriverFactory.getDriver();
		String[] amounts=Amount.split(delimiter);
		
			String amt = amounts[0];
			int amount = Integer.parseInt(amt);
			int denom = Integer.parseInt(Denom);
			int fitAmt = amount/denom;
			fitAmount = String.valueOf(fitAmt);
		
		Reporter.log("<B><I><font size='4' color='Black'>"+"----------------Process Purchase Order ----------------"+ "</font></I></B>");
		try {
		waitForElementToBeDisplayed("Volta Purchase Module", rapPurchaseModule);
		
		WebElement element = getWebDriver().findElement(By.name("Process"));
		element.sendKeys(Keys.chord(Keys.ALT+"P"));
		
		waitForElementToBeDisplayed("Select Purchase Bag Window", selectPurchaseBag);
		clickElementForVolta("Retrieve Button", retrieveButton);
		
		clickElementForVolta("Select Button", selectButton);
		
		waitForElementToBeDisplayed("Select Purchase Order Window", selectPurchaseOrderWindow);
		clearAndSendKeysForVolta(refCodeTextField, OrderNumber);
		clickElementForVolta("Retrieve Button", retrieveButton);
		
		clickElementForVolta("Select Button", selectButton);
		
		waitForElementToBeDisplayed("Remittance Window", remittanceWindow);
		Thread.sleep(3000);
		
		Robot rb = new Robot();
		rb.delay(500);
		rb.keyPress(KeyEvent.VK_ALT);
		rb.keyPress(KeyEvent.VK_S);
		rb.keyRelease(KeyEvent.VK_ALT);
		
		rb.delay(3000);
		//clearAndSendKeysForVolta(shortQtyTextField, "0");
		//clickElementForVolta("Save Button", saveButton);
		if(MasterDataReader.rapDetails.get("ProductType").equalsIgnoreCase("FC")){
			if(isExists("Denom Value Cannot be Null MSG", errorMessage)){
				clickElementForVolta("OK Button", okButton);
				Thread.sleep(2000);

				Robot robot;
				robot = new Robot();
				robot.delay(500);			
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.delay(1000);

				for(int i=0;i<fitAmount.length();i++){
					char c = fitAmount.charAt(i);
					robot.keyPress(Character.valueOf(c));
					robot.delay(500);
				}

				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.delay(1500);
			}
		}

		waitForElementToBeDisplayed("Select Purchase order Window", selectPurchaseOrderWindow);
		
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ALT);
		r.keyPress(KeyEvent.VK_C);
		r.keyRelease(KeyEvent.VK_ALT);

		Thread.sleep(2000);

		r.keyPress(KeyEvent.VK_ALT);
		r.keyPress(KeyEvent.VK_C);
		r.keyRelease(KeyEvent.VK_ALT);

		Thread.sleep(1000);
		}catch (Exception e) {
			Assert.fail("Process Order Function Failed"+e.getMessage());
			e.printStackTrace();
		}
		
	}	
	
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to Amalgamation
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Rakesh Karkare
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */
	
	public void amalgamation(String Supplier,String OrderReferenceNo){
		Reporter.log("<B><I><font size='4' color='Black'>"+"----------------Amalgamation ----------------"+ "</font></I></B>");
		try {

			waitForElementToBeDisplayed("RAP Purchase Module", rapPurchaseModule);
			WebElement element = getWebDriver().findElement(By.name("Supervisor"));
			element.sendKeys(Keys.chord(Keys.ALT+"M"));

			waitForElementToBeDisplayed("Amalgamation Window", amalgamationWindow);

			WebElement ele = getWebDriver().findElement(By.name("supplier_id"));
			ele.sendKeys(Supplier);
			clickElementForVolta("Preview Button", previewButton);

			if(isExists("No Data Error Message", noDataerrorMessage)){
				Reporter.log("<B><I><font size='4' color='Red'>"+" No Data Found "+ "</font></I></B>");
				Assert.fail("No Data Found");
			}

			waitForElementToBeClickable("Amalgamate Button", amalgamateButton);
			clickElementForVolta("Amalgamate Button", amalgamateButton);

			/*waitForElementToBeDisplayed("RAP Purchases Dialog", purchasesDialogWindow);
		clickElementForVolta("No Button", noButton);
		waitForElementToBeDisplayed("Amalgamation Window", amalgamationWindow);*/
			waitForElementToBeDisabled("Min Purchase module", rapPurchaseModule);

			clickElementForVolta("Preview Pane Click", previewPaneClick);
			Robot robot;
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_DOWN);

			List<WebElement> rowCollection=getWebDriver().findElements(By.name("amalgamation_id"));

			int rowCount = rowCollection.size();

			System.out.println("Numer of rows in this table: "+rowCount);

			for(int i=1;i<=rowCount;i++){
				clickElementForVolta("Amalgamation Row Click", amalgamationRowClick);
				clickElementForVolta("Confirm Button", confirmButton);
				waitForElementToBeDisplayed("RAP Purchases Dailog", purchasesDialogWindow);
				clickElementForVolta("Yes Button", yesButton);
				String batchNoMessage=getAttributeValueForVolta("BatchNumber", batchNumberMessage,"Name");
				String batchNumber = batchNoMessage.substring(7, 12);
				clickElementForVolta("Ok Button", okButton);
				if(i == rowCount){
					Reporter.log("<B><I><font size='4' color='Blue'>"+" Batch Number is-->>"+ batchNumber+ "</font></I></B>");
					waitForElementToBeDisplayed("Amalgamation Window", amalgamationWindow);
					clickElementForVolta("Close Button", closeButton);
				}
			}
			for(int i=1;i<=10;i++){
				Thread.sleep(1000);
			}
		} catch (Exception e) {
		Assert.fail("Amalgamation Function Failed"+e.getMessage());
		 e.printStackTrace();
		} 
		
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to Amalgamation of checks
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
	
	public void amalgamationChecks(String Supplier,String OrderReferenceNo){
		Reporter.log("<B><I><font size='4' color='Black'>"+"----------------Amalgamation Checks ----------------"+ "</font></I></B>");
		try{
			
			waitForElementToBeDisplayed("RAP Purchase Module", rapPurchaseModule);
			WebElement element = getWebDriver().findElement(By.name("Supervisor"));
			element.click();
			Robot robot= new Robot();
			robot.keyPress(KeyEvent.VK_A);
			
			waitForElementToBeDisplayed("Amalgamation Checks", checksAmalPane);
			waitForElementToBeClickable("Amalgamate Button", amalgamateButton);
			clickElementForVolta("Amalgamate Button", amalgamateButton);
			Thread.sleep(3000);
			clickElementForVolta("Close Button", closeButton);
			
		}catch(Exception e){
			Assert.fail("Amalgamation of Checks Function Failed"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed for Update checks inventory
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
	
	public void updateInvntoryChecks(){
		Reporter.log("<B><I><font size='4' color='Black'>"+"----------------Amalgamation Checks ----------------"+ "</font></I></B>");
		try{
			
			waitForElementToBeDisplayed("RAP Purchase Module", rapPurchaseModule);
			WebElement element = getWebDriver().findElement(By.name("Supervisor"));
			element.click();
			Robot robot= new Robot();
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.delay(200);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.delay(200);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.delay(200);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.delay(200);
			robot.keyPress(KeyEvent.VK_ENTER);
			
			waitForElementToBeDisplayed("Update Inventory Checks", updateInvchecksPane);
			waitForElementToBeClickable("Update Button", updateBtn);
			Thread.sleep(5000);
			
			clickElementForVolta("Update Check Pane", UpdateChkInvPane);
			List<WebElement> noOfChecks = getWebDriver().findElements(By.name("currency_code"));
			
			
			for(int i=0;i<noOfChecks.size();i++){
				clickElementForVolta("Update Check Bank Check Box", checkInvCount);
				clickElementForVolta("Update Button", updateBtn);
				Thread.sleep(3000);
			}
			
			Thread.sleep(5000);
			waitForElementToBeDisplayed("Update Inventory Checks", updateInvchecksPane);
			clickElementForVolta("Close Button", closeButton);
		}catch(Exception e){
			Assert.fail("Update checks invetory Function Failed"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to Update Inventory
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Rakesh Karkare
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */
	
	public void updateInventory(){
		Reporter.log("<B><I><font size='4' color='Black'>"+"---------------- Update Inventory ----------------"+ "</font></I></B>");
		try{
		waitForElementToBeDisplayed("RAP Purchase Module", rapPurchaseModule);
		Thread.sleep(1000);
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_ALT);
		rb.delay(100);
		rb.keyPress(KeyEvent.VK_U);
		rb.delay(300);
		rb.keyRelease(KeyEvent.VK_ALT);
		waitForElementToBeDisplayed("Update Purchase Inventory", updatePurchInventoryWindow);
		
		clickElementForVolta("Update Button", updateButton);
		
		/*if(isExists("NO rate msg", noRateMsg)){
			clickElementForVolta("Yes Button", yesButton);
			waitForElementToBeDisplayed("Inv Movement Msg", invMovementMsg);
			clickElementForVolta("Yes Button", yesButton);
		}*/
		
		if(isExists("inv msg", invMovementMsg)){
			clickElementForVolta("Yes Button", yesButton);
		}
		
		Thread.sleep(10000);
		
		/*waitForElementToBeDisplayed("RAP Purchases Dailog", purchasesDialogWindow);
		clickElementForVolta("Yes Button", yesButton);
		waitForElementToBeDisplayed("RAP Purchases Dailog", purchasesDialogWindow);
		clickElementForVolta("No Button", noButton);*/
		
		waitForElementToBeDisplayed("Update Purchase Inventory", updatePurchInventoryWindow);
		clickElementForVolta("Close Button", closeButton);
		Thread.sleep(2000);
		} catch (Exception e) {
		Assert.fail("Update Inventory Function Failed"+e.getMessage());
		 e.printStackTrace();
		} 
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to Close Purchase RAP
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Rakesh Karkare
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */
	
	public void closePurchaseRAP(){
		try {
			Reporter.log("<B><I><font size='4' color='Black'>"+"---------------- Close Purchase Volta ----------------"+ "</font></I></B>");
			waitForElementToBeDisplayed("RAP Purchase Module", rapPurchaseModule);
			clickElementForVolta("Close Button", closeButton);
			
			verifyAttributeValueForVolta("Exit Purchase Module", exitPurchaseModule, "Do you want to exit RAP - Purchase Module? (W3722)", "Name");
			waitForElementToBeDisplayed("RAP Purchases Dailog", purchasesDialogWindow);
			clickElementForVolta("Yes Button", yesButton);
			
			Thread.sleep(3000);
		} catch (Exception e) {
			Assert.fail("Close Purchase RAP Function Failed"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to Launch Sale RAP
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Rakesh Karkare
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */
	
	public void launchSaleRAP(){
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
	'Description 	 : This method is developed to Import Purchase Inventory
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Rakesh Karkare
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */
	
	public void ImportPurchaseInventory(){
		try{
		Reporter.log("<B><I><font size='4' color='Black'>"+"---------------- Import Purchase Inventory ----------------"+ "</font></I></B>");
		waitForElementToBeDisplayed("Travelex Currency Services RAP", saleRAPWindow);
		
		clickElementForVolta("Stock", stock);
		clickElementForVolta("ImportPurchaseInventory", importPurchaseInventory);
		waitForElementToBeDisplayed("Update Button", saleUpdateButton);
		clickElementForVolta("Update Button", saleUpdateButton);
		Thread.sleep(10000);
		clickElementForVolta("Close Button", closeButton);
		waitForElementToBeDisplayed("Travelex Currency Services RAP", saleRAPWindow);
		clickElementForVolta("Exit Button",exitButton);
		waitForElementToBeDisplayed("Dialog RAP", dialogRAP);
		clickElementForVolta("Yes Button",yesButton);
		}
		catch(Exception e){
			Assert.fail("Import Purchase Inventory Function Failed"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to Amalgamation ID Select Query
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Rakesh Karkare
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */
	
	public String amalgamationIDSelectQuery(String voltaDB,String OrderReferenceNo)  throws SQLException{
		   
		Connection dbObj = null;
		Statement stmt = null;
		//ResultSet dBRecs = null;
		String URL = "10.186.1.99", dbName = voltaDB, userName = "drop4", passWord = "drop4";
			
			dbObj = connectDB(URL, dbName, userName, passWord);
			
	        String AmalgamationID ="";
	        try{
	        	stmt = dbObj.createStatement();
				String selectQuery = "select pd.amalgamation_id from "
						+ "purchase_denomination pd, purchases p where "
						+ "pd.purchase_id = p.purchase_id and p.reference_code = '"+OrderReferenceNo+"'";
				System.out.println(selectQuery);
	        	ResultSet inputData =  stmt.executeQuery(selectQuery);
				while (inputData.next()) {
					
					AmalgamationID = inputData.getString("amalgamation_id");
					
					if (!(AmalgamationID == null) && !(AmalgamationID == "")) {
						break;
					}
				}
				
				if ((AmalgamationID == null) || (AmalgamationID == "")) {
					Reporter.log("No Amalgamation ID");
					return AmalgamationID;
				}
	        }
				catch(Exception e){
					e.printStackTrace();
				}
				
				finally {
					if(stmt != null) stmt.close();
					if(dbObj != null) dbObj.close();

				}
			return AmalgamationID;
	    	
		}
	
	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to Cancel Order 
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Rakesh Karkare
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */
	
	public void cancelOrder(String OrderReferenceNo,String Denom,String Amount){
		Reporter.log("<B><I><font size='4' color='Black'>"+"----------------Cancel Purchase Order ----------------"+ "</font></I></B>");
		try {
		waitForElementToBeDisplayed("RAP Purchase Module", rapPurchaseModule);
		
		WebElement element = getWebDriver().findElement(By.name("Process"));
		element.sendKeys(Keys.chord(Keys.ALT+"P"));
		
		waitForElementToBeDisplayed("Select Purchase Bag Window", selectPurchaseBag);
		clickElementForVolta("Retrieve Button", retrieveButton);
		
		clickElementForVolta("Select Button", selectButton);
		
		waitForElementToBeDisplayed("Select Purchase Order Window", selectPurchaseOrderWindow);
		clearAndSendKeysForVolta(refCodeTextField, OrderReferenceNo);
		clickElementForVolta("Retrieve Button", retrieveButton);
		
		clickElementForVolta("Select Button", selectButton);
		
		waitForElementToBeDisplayed("Remittance Window", remittanceWindow);
		clickElementForVolta("Cancel Button", cancelBtn);
		if(isExists("Cancel Order Message", cancelerrmessage)){
			verifyAttributeValueForVolta("Remittance Will be Cancelled Msg", remittanceCancelledMsg, "The remittance will be cancelled from the file.\r\nAre you sure you want to Continue? (W3809)", "Name");
			clickElementForVolta("Yes Button", yesButton);
			waitForElementToBeDisplayed("Order Cancellation Reason", orderCancellationReason);
			clearAndSendKeysForVolta(cancellationReasonTxtField, "Automation Testing");
			clickElementForVolta("OK Button", okBtn);
		}
		
		waitForElementToBeDisplayed("Select Purchase Order Window", selectPurchaseOrderWindow);
		clickElementForVolta("Close Button", closeBtn);
		waitForElementToBeDisplayed("Select Purchase Bag Window", selectPurchaseBag);
		clickElementForVolta("Close Button", closeBtn);
		
		}
		catch(Exception e){
			Assert.fail("Cancel Order Function Failed"+e.getMessage());
			e.printStackTrace();
		}
		
		
	}

	/*
	'***************************************************************************************************
	'Description 	 : This method is developed to Normal Purchase Order
	'InputParameters : NA 
	'OutPutParameters: NA
	'Author			 : Rakesh Karkare
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */
	
	public void fetchRecordsFromDB(String orderID) throws SQLException{
		try{
			RAPUtilities rapUtils = new RAPUtilities();
			RAPOrderDetails = new HashMap<String,String>();

			if(dbName.equalsIgnoreCase("RAP")){
				//selectQuery = "select TXM_ORDER_HEADER.* from TXM_ORDER_HEADER where order_id in (select order_id from txm_line_item where product_type = 'FC' group by order_id having count(order_id) < 2) AND PROCESSED = '2' AND ORDER_TYPE = 'BUY'";
				//selectQuery = "select DISTINCT corporate_products.supplier_id, TXM_ORDER_HEADER.* from TXM_ORDER_HEADER,corporate_products where order_id in (select order_id from txm_line_item where product_type = 'FC' group by order_id having count(order_id) < 2) AND PROCESSED = '2' AND ORDER_TYPE = 'BUY' and TXM_ORDER_HEADER.corporate_id = corporate_products.corporate_id and TXM_ORDER_HEADER.corporate_id = corporate_products.corporate_id and corporate_products.supplier_id not in ('BAML','NULL')"; 
				selectQuery = "select TXM_ORDER_HEADER.*,txm_line_item.product_type from TXM_ORDER_HEADER,txm_line_item where TXM_ORDER_HEADER.order_id = txm_line_item.order_id and txm_line_item.customer_order_ref = '"+orderID+"'";
			}else{
				//selectQuery = "select TXM_ORDER_HEADER.* from TXM_ORDER_HEADER where order_id in (select order_id from txm_line_item where product_type = 'FC' group by order_id having count(order_id) < 2) AND PROCESSED = '1' AND ORDER_TYPE = 'BUY'";
				selectQuery = "select TXM_ORDER_HEADER.*,txm_line_item.product_type from TXM_ORDER_HEADER,txm_line_item where TXM_ORDER_HEADER.order_id = txm_line_item.order_id and txm_line_item.customer_order_ref = '"+orderID+"'";
			}

			String deleteQuery = "";
			String updateQuery = "";
			String denomQuery = "";
			String supplierQuery = "";
			String findCurrencyQuery = "";

			String supplierID = "";
			String supplierDesc = "";

			con = rapUtils.dbConnect(URL, dbName, userName, passWord);
			stmt = con.createStatement();
			
			
			
			dbRecs = stmt.executeQuery(selectQuery);
			boolean isPresent = dbRecs.next();

			while(isPresent){
				String orderId = dbRecs.getString("order_id");
				String CustRefNum = dbRecs.getString("customer_order_ref");
				String ProductType = dbRecs.getString("product_type");
				if(URL.equalsIgnoreCase("GBPB-RAP-DE31")){
					supplierID = dbRecs.getString("supplier_id");
				}
				RAPOrderDetails.put("OrderRef", orderId);
				RAPOrderDetails.put("CustRef", CustRefNum);
				RAPOrderDetails.put("prodType", ProductType);

				//find currency and denomination

				String currency = "";
				String foreignAmt = "";
				String[] upCurr = null;
				ArrayList<String> denom = new ArrayList<String>();

				//findCurrencyQuery = "select * from purchase_currency where purchase_id = "+orderId+"";
				findCurrencyQuery = "select * from txm_line_item where order_id = "+orderId+"";
				rstodenom = stmt.executeQuery(findCurrencyQuery);
				while(rstodenom.next()){
					currency = rstodenom.getString("currency_code");
					//foreignAmt = rstodenom.getString("foreign_amount");
					foreignAmt = rstodenom.getString("foreign_value");
					upCurr = foreignAmt.split("\\.");
					break;
				}
				
				denomQuery = "select * from denomination where currency_code = '"+currency+"'";
				rstodenom = stmt.executeQuery(denomQuery);

				while(rstodenom.next()){
					denom.add(rstodenom.getString("denom_value"));
				}
				RAPOrderDetails.put("denomVal", denom.get(0));
				RAPOrderDetails.put("fAmt", upCurr[0].trim().toString());

				if(URL.equalsIgnoreCase("GBPB-RAP-DE31")){

					supplierQuery = "select * from supplier where supplier_id = '"+supplierID+"'";
					rstSuppliers = stmt.executeQuery(supplierQuery);

					while(rstSuppliers.next()){
						supplierDesc = rstSuppliers.getString("description");
					}
					RAPOrderDetails.put("supplier",supplierDesc);
				}
				
				//only for DB orders
				/*String[] table = {"purchases","purchase_currency","purchase_keywords","purchase_denomination","purchase_reason","draft_payment","chq_orders","rap_confirmation","rap_order_header","rap_line_item","rap_denomination"};
				for(int i=0;i<table.length;i++){
					if(i<=6){
						deleteQuery = "delete from "+table[i]+" where purchase_id = "+orderId+"";
						int totalRecs = stmt.executeUpdate(deleteQuery);
					}else{
						deleteQuery = "delete from "+table[i]+" where order_id = "+orderId+"";
						int totalRecs = stmt.executeUpdate(deleteQuery);
					}

				}
				updateQuery = "update txm_order_header set processed = '#' where  order_id = "+orderId+"";
				stmt.executeUpdate(updateQuery);*/
				
				break;
			}
			if(isPresent==false){
				System.out.println("Record not found");
				Assert.fail();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbRecs.close();
			stmt.close();
			con.close();
		}

	}
	
	
	public void updateSnC(String query) throws SQLException{
		RAPUtilities rapUtils = new RAPUtilities();

		//con = rapUtils.dbConnect("GBPB-RAP-DE31", "SC");
		con = rapUtils.dbConnect(URL, "SC", userName, passWord);
		stmt = con.createStatement();
		stmt.executeUpdate(query);
		
		stmt.close();
		con.close();

	}
	
	public String checkPOStatus(String query) throws SQLException{
		RAPUtilities rapUtils = new RAPUtilities();
		String poStatus = null;

		con = rapUtils.dbConnect(URL, dbName, userName, passWord);
		stmt = con.createStatement();
		dbRecs = stmt.executeQuery(query);

		boolean isExist = dbRecs.next();

		while(isExist){
			poStatus = dbRecs.getString("po_status");
			break;
		}

		dbRecs.close();
		stmt.close();
		con.close();
		//RAPOrderDetails.put("poStatus", poStatus);
		return poStatus;
	}

}