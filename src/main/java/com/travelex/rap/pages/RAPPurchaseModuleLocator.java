package com.travelex.rap.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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

public class RAPPurchaseModuleLocator extends WebdriverWrapper {
	
	public static String fitAmount;
		
	public static Locator rapPurchaseModule=new Locator ("RAPPurchaseModule","name","RAP - Purchase Module"); 
	public static Locator purchasesDialogWindow=new Locator ("PurchasesDialogWindow","name","Purchases");
	public static Locator exchangeRateMessage=new Locator ("ExchangeRateMessage","id","65535");
	
	public static Locator importCompletedDialog=new Locator ("ImportCompleted","name","RAP");
	public static Locator importPurchasesWindow=new Locator ("ImportPurchaseWindow","name","Import Purchases"); 
	public static Locator importButton=new Locator ("ImportButton","name","Import"); 
	public static Locator yesButton=new Locator ("YesButton","name","Yes"); 
	public static Locator noButton=new Locator ("NoButton","name","No"); 
	public static Locator okButton=new Locator ("OkButton","id","2"); 
	public static Locator supervisorMenu=new Locator ("SupervisorMenu","name","Supervisor"); 
	public static Locator importPurchasesMenu=new Locator ("ImportPurchasesMenu","name","Import Purchases"); 
	public static Locator selectPurchaseBag=new Locator ("SelectPurchaseBagWindow","name","Select Purchase Bag"); 
	public static Locator retrieveButton=new Locator ("RetrieveButton","id","1006"); 
	public static Locator selectButton=new Locator ("SelectButton","id","1020"); 
	public static Locator selectPurchaseOrderWindow=new Locator ("SelectPurchaseOrderWindow","name","Select Purchase Order"); 
	public static Locator remittanceWindow=new Locator ("RemittanceWindow","name","Remittance");
	
	public static Locator purchaseOrderRef=new Locator ("PurchaseOrderRef","name","purchases_purchase_id");
	public static Locator shortQtyTextField=new Locator ("ShortQuantityTextField","name","short_qty");
	public static Locator saveButton=new Locator ("SaveButton","id","1017");
	public static Locator refCodeTextField=new Locator ("RefCodeTextField","name","reference_code"); 
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
	
	public static Locator errorMessage = new Locator("errmessage","id","65535");	
	public static Locator denomValueDropDown = new Locator("denomValueDropDown","name","denom_value");
	public static Locator fitTextField = new Locator("FitAmountTextField","name","fit");	
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
		Date purchaseRAPLaunchTime = new java.util.Date();
		Reporter.log("<B><I><font size='3' color='Blue'> Sucess Message Pop Up Apperance Time : "+ new Timestamp(purchaseRAPLaunchTime.getTime())+"</font></I></B>");
		WebDriver driver = Setup.voltaPurchaseDriver();
		DriverFactory.setWebDriver(driver);
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
		String[] amounts=Amount.split(delimiter);
		
			String amt = amounts[0];
			int amount = Integer.parseInt(amt);
			int denom = Integer.parseInt(Denom);
			int fitAmt = amount/denom;
			fitAmount = String.valueOf(fitAmt);
		
		Reporter.log("<B><I><font size='4' color='Black'>"+"----------------Process Purchase Order ----------------"+ "</font></I></B>");
		try {
		waitForElementToBeDisplayed("RAP Purchase Module", rapPurchaseModule);
		
		WebElement element = getWebDriver().findElement(By.name("Process"));
		element.sendKeys(Keys.chord(Keys.ALT+"P"));
		
		waitForElementToBeDisplayed("Select Purchase Bag Window", selectPurchaseBag);
		clickElementForVolta("Retrieve Button", retrieveButton);
		
		clickElementForVolta("Select Button", selectButton);
		
		waitForElementToBeDisplayed("Select Purchase Order Window", selectPurchaseOrderWindow);
		clearAndSendKeysForVolta(refCodeTextField, OrderNumber);
		clickElementForVolta("Retrieve Button", retrieveButton);
		
		if(isExists("No Data Error Message", noDataerrorMessage)){
			Reporter.log("<B><I><font size='4' color='Red'>"+" Order number is not available for processing"+ "</font></I></B>");
			Assert.fail(" Order number is not available for processing");
		}
		
		clickElementForVolta("Select Button", selectButton);
		
		waitForElementToBeDisplayed("Remittance Window", remittanceWindow);
		
		clearAndSendKeysForVolta(shortQtyTextField, "0");
		clickElementForVolta("Save Button", saveButton);
		Thread.sleep(3000);
		
		if(isExists("Denom Value Cannot be Null MSG", errorMessage)){
			clickElementForVolta("OK Button", okButton);
			element = getWebDriver().findElement(By.name("denom_value"));
			element.click();
			Robot robot;
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_TAB);
			
			clearAndSendKeysForVolta(fitTextField, fitAmount);
			clickElementForVolta("Save Button", saveButton);
		}
		
		waitForElementToBeDisplayed("Select Purchase Order Window", selectPurchaseOrderWindow);
		Thread.sleep(1000);
		clickElementForVolta("Close Button", closeBtn);
		waitForElementToBeDisplayed("Select Purchase Bag Window", selectPurchaseBag);
		Thread.sleep(1000);
		clickElementForVolta("Close Button", closeBtn);
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
	
	public void amalgamation(String Vault,String OrderReferenceNo){
		Reporter.log("<B><I><font size='4' color='Black'>"+"----------------Amalgamation ----------------"+ "</font></I></B>");
		try {
	
		waitForElementToBeDisplayed("RAP Purchase Module", rapPurchaseModule);
		WebElement element = getWebDriver().findElement(By.name("Supervisor"));
		element.sendKeys(Keys.chord(Keys.ALT+"M"));
		
		waitForElementToBeDisplayed("Amalgamation Window", amalgamationWindow);
		clickElementForVolta("Preview Button", previewButton);
		
		if(isExists("No Data Error Message", noDataerrorMessage)){
			Reporter.log("<B><I><font size='4' color='Red'>"+" No Data Found "+ "</font></I></B>");
			Assert.fail("No Data Found");
		}
		
		waitForElementToBeClickable("Amalgamate Button", amalgamateButton);
		clickElementForVolta("Amalgamate Button", amalgamateButton);
		
		waitForElementToBeDisplayed("RAP Purchases Dialog", purchasesDialogWindow);
		clickElementForVolta("No Button", noButton);
		waitForElementToBeDisplayed("Amalgamation Window", amalgamationWindow);
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
		
		} catch (Exception e) {
		Assert.fail("Amalgamation Function Failed"+e.getMessage());
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
		Thread.sleep(1000);
		WebElement element = getWebDriver().findElement(By.name("Supervisor"));
		element.sendKeys(Keys.chord(Keys.ALT+"U"));
		waitForElementToBeDisplayed("Update Purchase Inventory", updatePurchInventoryWindow);
		
		clickElementForVolta("Update Button", updateButton);
		waitForElementToBeDisplayed("RAP Purchases Dailog", purchasesDialogWindow);
		clickElementForVolta("Yes Button", yesButton);
		waitForElementToBeDisplayed("RAP Purchases Dailog", purchasesDialogWindow);
		clickElementForVolta("No Button", noButton);
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
		Thread.sleep(8000);
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
		
	public void normalPurchaseOrder(String OrderReferenceNo,String Vault,String Denom,String Amount){
		launchPurchaseRAP();
		importPurchases();
		processPurchaseOrder(OrderReferenceNo,Denom,Amount);
		amalgamation(Vault,OrderReferenceNo);
		updateInventory();
		closePurchaseRAP();
		launchSaleRAP();
		ImportPurchaseInventory();
		
	}

}
