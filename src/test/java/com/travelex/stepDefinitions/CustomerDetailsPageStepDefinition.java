package com.travelex.stepDefinitions;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.codoid.products.fillo.Connection;
import com.travelex.framework.common.UpdateDataInExcel;
import com.travelex.nam.pages.CustomerDetailsPage;
import com.travelex.nam.pages.PrinterFriendlyPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CustomerDetailsPageStepDefinition {
	public static Connection connection = UpdateDataInExcel.getConnection();
	
	@When("^I enter customer details$")
	public void i_enter_customer_details() throws Throwable {
		CustomerDetailsPage customerDetailsPage = (CustomerDetailsPage)MasterDataReader.pageDetails.get("CustomerDetailsPage");
		customerDetailsPage.enterCustomerDetails(MasterDataReader.customerDetails.get("CustomerSalutation"), MasterDataReader.customerDetails.get("FirstName"), MasterDataReader.customerDetails.get("LastName"), MasterDataReader.customerDetails.get("GLAccNumber"),MasterDataReader.customerDetails.get("BankID"), MasterDataReader.customerDetails.get("DOB"));
		customerDetailsPage.enterCustomerAddress(MasterDataReader.customerDetails.get("AddressLine1"), MasterDataReader.customerDetails.get("AddressLine2"), MasterDataReader.customerDetails.get("City"), MasterDataReader.customerDetails.get("State"), MasterDataReader.customerDetails.get("ZipCode"), MasterDataReader.customerDetails.get("CountryOfIssue"));
		customerDetailsPage.enterDeliveryDetails(MasterDataReader.customerDetails.get("AttentionName"), MasterDataReader.customerDetails.get("BranchContact"), MasterDataReader.customerDetails.get("PhoneNumber"), MasterDataReader.orderDetails.get("PartnerID"));
		customerDetailsPage.enterCustomerSecurityInformation(MasterDataReader.customerDetails.get("PrimaryID"), MasterDataReader.customerDetails.get("IDNumber"), MasterDataReader.customerDetails.get("CountryOfIssue"), MasterDataReader.customerDetails.get("IDState"), MasterDataReader.customerDetails.get("IssueDate"), MasterDataReader.customerDetails.get("ExpiryDate"));	
	}
	
	@When("^I change branch details$")
	public void i_change_branch_details() throws Throwable {
		CustomerDetailsPage customerDetailsPage = (CustomerDetailsPage)MasterDataReader.pageDetails.get("CustomerDetailsPage");
		customerDetailsPage.enterBranchDetails(MasterDataReader.customerDetails.get("ConfigChangeBranchLink"), MasterDataReader.customerDetails.get("CompanyDetails"),MasterDataReader.orderDetails.get("PartnerID"));
	}
		
	@When("^I complete the order$")
	public void i_complete_the_order() throws Throwable {
		CustomerDetailsPage customerDetailsPage = (CustomerDetailsPage)MasterDataReader.pageDetails.get("CustomerDetailsPage");
		PrinterFriendlyPage printerFriendlyPage = customerDetailsPage.submitCustomerDetails();
		MasterDataReader.pageDetails.put("PrinterPage", printerFriendlyPage);
	}
	
	@Then("^I get order confirmation no$")
	public void i_get_order_confirmation_no() throws Throwable {
		CustomerDetailsPage customerDetailsPage = (CustomerDetailsPage)MasterDataReader.pageDetails.get("CustomerDetailsPage");
		String orderConfNumber = customerDetailsPage.getConfirmationNumber();
		String orderTotal = customerDetailsPage.getOrderTotal();
		String serviceCharge = customerDetailsPage.getServiceCharge();
		String shippingCharge = customerDetailsPage.getShippingCharge();
		String todayAsString = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(new Date());
		
		UpdateDataInExcel up = new UpdateDataInExcel();
		up.updateDataInExcel("CustomerDetails", "ConfirmationNumber", orderConfNumber, MasterDataReader.customerDetails.get("AutomationID"),connection);
		up.updateDataInExcel("CustomerDetails", "TotalOrderAmount", orderTotal, MasterDataReader.customerDetails.get("AutomationID"),connection);
		up.updateDataInExcel("CustomerDetails", "ExecutionDate", todayAsString, MasterDataReader.customerDetails.get("AutomationID"),connection);
		up.updateDataInExcel("CustomerDetails", "ServiceCharge", serviceCharge, MasterDataReader.customerDetails.get("AutomationID"),connection);
		up.updateDataInExcel("CustomerDetails", "ShippingCharge", shippingCharge, MasterDataReader.customerDetails.get("AutomationID"),connection);

	}
}
