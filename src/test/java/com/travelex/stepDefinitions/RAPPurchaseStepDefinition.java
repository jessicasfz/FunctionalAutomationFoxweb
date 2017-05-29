package com.travelex.stepDefinitions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.travelex.framework.common.UpdateDataInExcel;
import com.travelex.rap.pages.RAPPurchaseModuleLocator;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RAPPurchaseStepDefinition {
	
	RAPPurchaseModuleLocator rAPPurchaseModuleLocator;
	
	@Given("^I launch the RAP application$")
	public void i_launch_the_RAP_application() throws Throwable {
		rAPPurchaseModuleLocator = new RAPPurchaseModuleLocator();
		rAPPurchaseModuleLocator.fetchRecordsFromDB(MasterDataReader.rapDetails.get("OrderID"));
		rAPPurchaseModuleLocator.launchPurchaseRAP();
		//rAPPurchaseModuleLocator.afterLaunchPurchase();
	}
	
	@Given("^I import purchase orders$")
	public void i_import_purchase_orders() throws Throwable {
		rAPPurchaseModuleLocator.importPurchases();

		String statusQuery = "select * from purchases where purchase_id = "+RAPPurchaseModuleLocator.RAPOrderDetails.get("OrderRef")+"";
		String ordStatus = rAPPurchaseModuleLocator.checkPOStatus(statusQuery);
		MasterDataReader.scenario.write("Purchase order status after import purchase is : "+ordStatus);
	}
	
	@When("^I process purchase order$")
	public void i_process_purchase_order() throws Throwable {
		rAPPurchaseModuleLocator.processPurchaseOrder(RAPPurchaseModuleLocator.RAPOrderDetails.get("CustRef"), RAPPurchaseModuleLocator.RAPOrderDetails.get("denomVal"), RAPPurchaseModuleLocator.RAPOrderDetails.get("fAmt"));
		
		String statusQuery = "select * from purchases where purchase_id = "+RAPPurchaseModuleLocator.RAPOrderDetails.get("OrderRef")+"";
		String ordStatus = rAPPurchaseModuleLocator.checkPOStatus(statusQuery);
		MasterDataReader.scenario.write("Purchase order status after Process purchase order is : "+ordStatus);
	}
	
	@When("^I retrieve the orders and update in DataSheet$")
	public void i_retrieve_the_orders_and_update_in_datasheet() throws Throwable {
		rAPPurchaseModuleLocator = new RAPPurchaseModuleLocator();
		rAPPurchaseModuleLocator.updateSnC("update user_application set logged_on = 'N'");
		rAPPurchaseModuleLocator.fetchRecordsFromDB(MasterDataReader.customerDetails.get("ConfirmationNumber"));
		UpdateDataInExcel up = new UpdateDataInExcel();
		String[] dataToUpdate = {RAPPurchaseModuleLocator.RAPOrderDetails.get("OrderRef"),RAPPurchaseModuleLocator.RAPOrderDetails.get("CustRef"),RAPPurchaseModuleLocator.RAPOrderDetails.get("denomVal"), RAPPurchaseModuleLocator.RAPOrderDetails.get("fAmt"),RAPPurchaseModuleLocator.RAPOrderDetails.get("prodType")};
		String[] colsToTpdate = {"OrderID","CustomerRef","DenomValue","Famount","ProductType"};
		for(int i=0;i<colsToTpdate.length;i++){
			up.updateDataInExcel("RAPDetails", colsToTpdate[i], dataToUpdate[i], MasterDataReader.rapDetails.get("AutomationID"));
		}
	}	
	
	@Then("^I should see the currency denomination and other order details$")
	public void i_should_see_the_currency_denomination_and_other_order_details() throws Throwable {
		//delete the code
		rAPPurchaseModuleLocator = new RAPPurchaseModuleLocator();
		rAPPurchaseModuleLocator.fetchRecordsFromDB(MasterDataReader.rapDetails.get("OrderID"));
		rAPPurchaseModuleLocator.launchPurchaseRAP();
		rAPPurchaseModuleLocator.afterLaunchPurchase();
		rAPPurchaseModuleLocator.amalgamation(RAPPurchaseModuleLocator.RAPOrderDetails.get("supplier"), RAPPurchaseModuleLocator.RAPOrderDetails.get("CustRef"));
	}
	
	@When("^I perform Amalgamation process to confirm order$")
	public void i_perform_Amalgamation_process_to_confirm_order() throws Throwable {
		
		if(RAPPurchaseModuleLocator.RAPOrderDetails.get("prodType").equalsIgnoreCase("CQ")){
			rAPPurchaseModuleLocator.amalgamationChecks("RAP", RAPPurchaseModuleLocator.RAPOrderDetails.get("CustRef"));
		}else{		
			rAPPurchaseModuleLocator.amalgamation("RAP", RAPPurchaseModuleLocator.RAPOrderDetails.get("CustRef"));
		}
		
		String statusQuery = "select po_status from purchases where purchase_id = "+RAPPurchaseModuleLocator.RAPOrderDetails.get("OrderRef")+"";
		String ordStatus = rAPPurchaseModuleLocator.checkPOStatus(statusQuery);
		
		if(ordStatus.equalsIgnoreCase("AMAL")){
			MasterDataReader.scenario.write("Order is succesfully processed. Order status is "+ordStatus+"");
		}else{
			MasterDataReader.scenario.write("Order process failed. Order status is "+ordStatus+"");
		}
		UpdateDataInExcel up = new UpdateDataInExcel();
		String[] dataToUpdate = {RAPPurchaseModuleLocator.RAPOrderDetails.get("OrderRef"),RAPPurchaseModuleLocator.RAPOrderDetails.get("CustRef"),RAPPurchaseModuleLocator.RAPOrderDetails.get("poStatus")};
		String[] colsToTpdate = {"OrderID","CustomerRef","Status"};
		for(int i=0;i<colsToTpdate.length;i++){
			up.updateDataInExcel("RAPDetails", colsToTpdate[i], dataToUpdate[i], MasterDataReader.rapDetails.get("AutomationID"));
		}
	}
	
	@Then("^I update inventory$")
	public void i_update_inventory() throws Throwable {
		if(MasterDataReader.rapDetails.get("ProductType").equalsIgnoreCase("CQ")){
			rAPPurchaseModuleLocator.updateInvntoryChecks();
		}else{
			rAPPurchaseModuleLocator.updateInventory();
		}
		rAPPurchaseModuleLocator.closePurchaseRAP();
	}
	
	@Then("^I invoke Sale RAP to import inventory$")
	public void i_invoke_sale_RAP_to_import_inventory() throws Throwable {
		if(MasterDataReader.rapDetails.get("ProductType").equalsIgnoreCase("CQ")){
			MasterDataReader.scenario.write("Import purchase inventory is not required as this is Checks Order");
		}else{
			rAPPurchaseModuleLocator.launchSaleRAP();
			rAPPurchaseModuleLocator.Afterlaunch();
			rAPPurchaseModuleLocator.ImportPurchaseInventory();
		}
		
	}
	
	@When("^I preview and confirm the order$")
	public void i_preview_and_confirm_the_order() throws Throwable {
		
	}
	
	@When("^I navigate to Update Inventory on Supervsor menu$")
	public void i_navigate_to_Update_Inventory_on_Supervsor_menu() throws Throwable {
		
	}
	
	@When("^I process and amalgamate order$")
	public void i_process_and_amalgamate_order() throws Throwable {
		rAPPurchaseModuleLocator = new RAPPurchaseModuleLocator();
		rAPPurchaseModuleLocator.updateSnC("update user_application set logged_on = 'N'");
		rAPPurchaseModuleLocator.launchPurchaseRAP();
		//rAPPurchaseModuleLocator.afterLaunchPurchase();
		rAPPurchaseModuleLocator.processPurchaseOrder(MasterDataReader.rapDetails.get("CustomerRef"), MasterDataReader.rapDetails.get("DenomValue"), MasterDataReader.rapDetails.get("Famount"));
		if(MasterDataReader.rapDetails.get("ProductType").equalsIgnoreCase("CQ")){
		rAPPurchaseModuleLocator.amalgamationChecks("RAP", MasterDataReader.rapDetails.get("CustomerRef"));
		}else{
			rAPPurchaseModuleLocator.amalgamation("RAP", MasterDataReader.rapDetails.get("CustomerRef"));
		}
		
		String statusQuery = "select po_status from purchases where reference_code in ('"+MasterDataReader.rapDetails.get("CustomerRef")+"')";
		String ordStatus = rAPPurchaseModuleLocator.checkPOStatus(statusQuery);
		
		if(ordStatus.equalsIgnoreCase("AMAL")){
			MasterDataReader.scenario.write("Order is succesfully processed. Order status is "+ordStatus+"");
		}else{
			MasterDataReader.scenario.write("Order process failed. Order status is "+ordStatus+"");
		}
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		UpdateDataInExcel up = new UpdateDataInExcel();
		String[] dataToBeupdated = {ordStatus,df.format(date)};
		String[] colToBeUpdated = {"Status","ProcessedDate"};
		for(int i=0;i<colToBeUpdated.length;i++){
			up.updateDataInExcel("RAPDetails",colToBeUpdated[i], dataToBeupdated[i], MasterDataReader.rapDetails.get("AutomationID"));
		}
	}
	
	@Then("^Order gets processed$")
	public void order_gets_processed() throws Throwable {
		
	}

	
	@When("^I launch and Import RAP to import multiple orders$")
	public void i_launch_and_Import_RAP_to_import_multiple_orders() throws Throwable {
		rAPPurchaseModuleLocator = new RAPPurchaseModuleLocator();
		rAPPurchaseModuleLocator.launchPurchaseRAP();
		//rAPPurchaseModuleLocator.afterLaunchPurchase();
		rAPPurchaseModuleLocator.importPurchases();
		rAPPurchaseModuleLocator.closePurchaseRAP();
	}
	
}
