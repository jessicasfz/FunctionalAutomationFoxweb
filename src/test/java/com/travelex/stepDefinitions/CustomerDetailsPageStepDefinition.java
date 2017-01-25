package com.travelex.stepDefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.travelex.framework.Dataset.CustomerDetailsDataSet;
import com.travelex.framework.common.UpdateDataInExcel;
import com.travelex.nam.pages.CustomerDetailsPage;

import cucumber.api.Scenario;
import cucumber.api.java.en.When;

public class CustomerDetailsPageStepDefinition {
	
	public WebDriver driver;
	public int index;
	public Scenario scenario;
	private CustomerDetailsPage customerDetailsPage;
	private List<CustomerDetailsDataSet> custDataset;
	
	public CustomerDetailsPageStepDefinition(){
		driver = MasterDataReader.driver;
		scenario = MasterDataReader.scenario;
		custDataset = MasterDataReader.custDataset;
		index = MasterDataReader.index;
	}
	
	@When("^I enter customer details$")
	public void i_enter_customer_details() throws Throwable {
		customerDetailsPage = new CustomerDetailsPage(driver).get();
		
		customerDetailsPage.enterCustomerDetails(custDataset.get(index).getCustomerSalutation(),
				custDataset.get(index).getFirstName(),
				custDataset.get(index).getLastName(),
				custDataset.get(index).getGLAccNumber(),
				custDataset.get(index).getBankID(),
				custDataset.get(index).getDOB());
		customerDetailsPage.enterDeliveryDetails(custDataset.get(index).getAttentionName(),
				custDataset.get(index).getBranchContact(),
				custDataset.get(index).getPhoneNumber());
		customerDetailsPage.clickOnChangeOrderButton(custDataset.get(index).getConfigChangeOrderBtn());
		customerDetailsPage.clickOnCancelOrderButton(custDataset.get(index).getConfigCancelOrderBtn());
		
		String orderConfNumber = customerDetailsPage.submitCustomerDetails(custDataset.get(index).getConfigCompleteOrderBtn());
		UpdateDataInExcel up = new UpdateDataInExcel();
		up.updateDataInExcel("CustomerDetails", "ConfirmationNumber", orderConfNumber, custDataset.get(index).getAutomationID());
		
	}
}
