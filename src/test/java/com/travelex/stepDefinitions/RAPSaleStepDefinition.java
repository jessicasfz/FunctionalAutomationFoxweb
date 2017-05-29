package com.travelex.stepDefinitions;

import com.travelex.framework.common.UpdateDataInExcel;
import com.travelex.rap.pages.RAPSaleModuleLocator;

import cucumber.api.java.en.When;

public class RAPSaleStepDefinition {
	
	RAPSaleModuleLocator rAPSaleModuleLocator;
	
	@When("^I launch RAPSale application$")
	public void i_launch_RAPSale_application() throws Throwable {
		rAPSaleModuleLocator = new RAPSaleModuleLocator();
		//Changed and Commented for multiline
		//rAPSaleModuleLocator.fetchSaleRecordsFromDB(MasterDataReader.rapDetails.get("OrderID"));
		rAPSaleModuleLocator.fetchSaleRecordsFromDB("1234",true);
		rAPSaleModuleLocator.launchSaleVolta();
		rAPSaleModuleLocator.Afterlaunch();
	}
	
	@When("^I perform order extraction$")
	public void i_perform_order_extraction() throws Throwable {
		rAPSaleModuleLocator = new RAPSaleModuleLocator();
		rAPSaleModuleLocator.extractOrders();
		
		//Commented for multiline
		/*
		MasterDataReader.scenario.write(RAPSaleModuleLocator.saleDetails.get("OrderRef"));
		String strQuery = "select * from rap_confirmation where order_id = "+RAPSaleModuleLocator.saleDetails.get("OrderRef")+"";
		boolean toContinue = rAPSaleModuleLocator.checkRapConfirmation(strQuery);

		if(!toContinue){
			MasterDataReader.scenario.write("Order Extraction Failed. Error message is : "+RAPSaleModuleLocator.saleDetails.get("errMsg"));
			Assert.fail();
		}
		
		String statusExtQuery = "select * from order_currency where order_id = "+RAPSaleModuleLocator.saleDetails.get("OrderRef")+"";
		String ordStatus = rAPSaleModuleLocator.getOrderStatus(statusExtQuery,"No");
		MasterDataReader.scenario.write("Order status after extraction is : "+ordStatus);*/
	}
	
	@When("^I schedule order$")
	public void i_schedule_order() throws Throwable {
		rAPSaleModuleLocator = new RAPSaleModuleLocator();
		rAPSaleModuleLocator.scheduleOrders();
		
		//Commented for multiline
		/*
		String statusScdQuery = "select * from order_currency where order_id = "+RAPSaleModuleLocator.saleDetails.get("OrderRef")+"";
		String schdStatus = rAPSaleModuleLocator.getOrderStatus(statusScdQuery,"No");//Change to Yes once Reprint customer for Cash payment method is resolved
		MasterDataReader.scenario.write("Order status after Schedule Function is : "+schdStatus);*/
		
		
		String batchQuery = "select batch_id from stat_schedule where print_date is null";
		rAPSaleModuleLocator.getBatchNoDetails(batchQuery);
		for(int i=0;i<RAPSaleModuleLocator.batchDetails.size();i++){
		rAPSaleModuleLocator.reprintCustAdvice(RAPSaleModuleLocator.batchDetails.get(i),"4");
		}
	}
	
	@When("^I perform Manual Batch Confirmation$")
	public void i_perform_manual_batch_confirmation() throws Throwable {
		rAPSaleModuleLocator = new RAPSaleModuleLocator();
		rAPSaleModuleLocator.confirmManualBatch();
		
		//Commented for multiline
		/*
		String statusConfBatchQuery = "select * from order_currency where order_id = "+RAPSaleModuleLocator.saleDetails.get("OrderRef")+"";
		String cBatchStatus = rAPSaleModuleLocator.getOrderStatus(statusConfBatchQuery,"No");
		MasterDataReader.scenario.write("Order status after Manual Batch Confirmation is : "+cBatchStatus);*/
	}	

	@When("^I close sale RAP$")
	public void i_close_sale_RAP() throws Throwable {
		rAPSaleModuleLocator = new RAPSaleModuleLocator();
		rAPSaleModuleLocator.closeSalesVolta();
	}
	
	@When("^I perform AutoPR process to complete order fullfilment$")
	public void i_perform_AutoPR_process_to_complete_order_fullfilment() throws Throwable {
		rAPSaleModuleLocator = new RAPSaleModuleLocator();
		rAPSaleModuleLocator.launchAutoPRVolta();
		rAPSaleModuleLocator.autoPRProcess();
		
		//Commented for multiline
		/*
		String rapConfQuery = "select * from rap_confirmation where order_id = "+RAPSaleModuleLocator.saleDetails.get("OrderRef")+"";
		String schdStatus = rAPSaleModuleLocator.getOrderStatus(rapConfQuery,"No");
		MasterDataReader.scenario.write("Order status after AutoPR is : "+schdStatus);
		
		UpdateDataInExcel up = new UpdateDataInExcel();
		String[] data = {RAPSaleModuleLocator.saleDetails.get("OrderRef"),RAPSaleModuleLocator.saleDetails.get("CustRef"),RAPSaleModuleLocator.saleDetails.get("status")};
		String[] cols = {"OrderID","CustomerRef","Status"};
		
		for(int i=0;i<cols.length;i++){
			up.updateDataInExcel("RAPDetails", cols[i], data[i], MasterDataReader.rapDetails.get("AutomationID"));
		}*/
		
	}
	
	
	@When("^I check and update order status$")
	public void i_check_and_update_order_status() throws Throwable {
		rAPSaleModuleLocator = new RAPSaleModuleLocator();
		String rapConfQuery = "select * from rap_confirmation where order_id = "+RAPSaleModuleLocator.saleDetails.get("OrderRef")+"";
		String schdStatus = rAPSaleModuleLocator.getOrderStatus(rapConfQuery,"No");
		MasterDataReader.scenario.write("Order status after AutoPR is : "+schdStatus);
		
		UpdateDataInExcel up = new UpdateDataInExcel();
		String[] data = {MasterDataReader.customerDetails.get("ConfirmationNumber"),RAPSaleModuleLocator.saleDetails.get("status")};
		String[] cols = {"CustomerRef","Status"};
		
		for(int i=0;i<cols.length;i++){
			up.updateDataInExcel("RAPDetails", cols[i], data[i], MasterDataReader.rapDetails.get("AutomationID"));
		}
		
	}

}
