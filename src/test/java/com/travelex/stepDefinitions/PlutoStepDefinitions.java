package com.travelex.stepDefinitions;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.travelex.framework.common.ConfigurationProperties;
import com.travelex.framework.common.UpdateDataInExcel;
import com.travelex.pluto.pages.AdditionalInformationPage;
import com.travelex.pluto.pages.CustomerDetailsPage;
import com.travelex.pluto.pages.HomePage;
import com.travelex.pluto.pages.LoginPage;
import com.travelex.pluto.pages.TransactionPage;
import com.travelex.stepDefinitions.MasterDataReader;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

public class PlutoStepDefinitions {

	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	public static Connection connection = UpdateDataInExcel.getConnection();
	
	@When("^I launch PLUTO application$")
	public void i_launch_pluto_application() {
		String plutoUrl = configurationProperties.getProperty(ConfigurationProperties.PLUTO_APPLICATION_URL);
		MasterDataReader.driver.manage().window().maximize();
		MasterDataReader.driver.get(plutoUrl);
		LoginPage loginPage = new LoginPage(MasterDataReader.driver,plutoUrl).get();
		HomePage homePage = loginPage.clickLogin(MasterDataReader.plutoDetails.get("Username"), MasterDataReader.plutoDetails.get("Password"), MasterDataReader.plutoDetails.get("Company"));
		MasterDataReader.pageDetails.put("HomePage", homePage);
	}


	@And("^I select order type$")
	public void i_select_order_type() {
		HomePage homePage = (HomePage) MasterDataReader.pageDetails.get("HomePage");
		TransactionPage transactionPage = homePage.navigateToTransactionPage(MasterDataReader.plutoDetails.get("TransType"),MasterDataReader.plutoDetails.get("PurchaseType"));
		MasterDataReader.pageDetails.put("TransactionPage", transactionPage);
	}

	@And("^I select customer type and delivery method$")
	public void i_enter_company_name_customer_type_and_Delivery_method() {
		TransactionPage transactionPage = (TransactionPage) MasterDataReader.pageDetails.get("TransactionPage");
		transactionPage.selectCustomerTypeAndDeliveryType(MasterDataReader.plutoDetails.get("CustomerType"), MasterDataReader.plutoDetails.get("DeliveryType"));
	}

	@And("^I search branch location$")
	public void i_search_branch_location() throws InterruptedException {
		TransactionPage transactionPage = (TransactionPage) MasterDataReader.pageDetails.get("TransactionPage");
		transactionPage.clickOnSearchAndSelectBranchLocation(MasterDataReader.plutoDetails.get("Location"));
	}

	@And("^I select product type and currency$")
	public void i_select_product_type_and_currency() {
		TransactionPage transactionPage = (TransactionPage) MasterDataReader.pageDetails.get("TransactionPage");
		if(MasterDataReader.plutoDetails.get("TransType").equalsIgnoreCase("PurchaseOrder")){
			transactionPage.enterCustomerDetailsInPurchse(MasterDataReader.plutoDetails.get("Salutation"),MasterDataReader.plutoDetails.get("FirstName"),MasterDataReader.plutoDetails.get("LastName"));	
		}
		transactionPage.enterProductDetails(MasterDataReader.plutoDetails.get("MultipleCurrencies"));
		transactionPage.clickOnNextBtn();
		
	}

	@And("^I enter customer detail$")
	public void i_enter_customer_details() throws InterruptedException {
		//Get Driver From transaction page return type 
		CustomerDetailsPage customerDetailsPage = new CustomerDetailsPage(MasterDataReader.driver);
		customerDetailsPage.enterCustomerDeliveryDetails(MasterDataReader.plutoDetails.get("Salutation"),MasterDataReader.plutoDetails.get("FirstName"), MasterDataReader.plutoDetails.get("LastName"), 
				MasterDataReader.plutoDetails.get("CollectionDate"),MasterDataReader.plutoDetails.get("DeliveryType"),MasterDataReader.plutoDetails.get("AwayBranchLocation"),MasterDataReader.plutoDetails.get("Address1"),
				MasterDataReader.plutoDetails.get("Address2"),MasterDataReader.plutoDetails.get("State"),MasterDataReader.plutoDetails.get("Country"),MasterDataReader.plutoDetails.get("ZipCode"),MasterDataReader.plutoDetails.get("HomeTelephoneNo"));
		customerDetailsPage.clickOnNextBtn();
	}

	@And("^I enter Company Additional Info$")
	public void i_enter_Comapny_Additional_Info() { 
		AdditionalInformationPage additionalInformationPage = new AdditionalInformationPage(MasterDataReader.driver);
		if(MasterDataReader.scenario.getName().contains("purchase order")){
			additionalInformationPage.enterAdditionalInfo(MasterDataReader.plutoDetails.get("CollectionCheck"),MasterDataReader.plutoDetails.get("Comments"), MasterDataReader.plutoDetails.get("OtherFee"), MasterDataReader.plutoDetails.get("TellerName"), MasterDataReader.plutoDetails.get("AdditionalInfo"));
		}else if(MasterDataReader.scenario.getName().contains("Sale order")){
			additionalInformationPage.enterAdditionalInfo(MasterDataReader.plutoDetails.get("DeliveryType"),MasterDataReader.plutoDetails.get("ConfigAdditionalInfo"),MasterDataReader.plutoDetails.get("Comments"), MasterDataReader.plutoDetails.get("OtherFee"), MasterDataReader.plutoDetails.get("TellerName"),MasterDataReader.plutoDetails.get("AlternateTellerName"),MasterDataReader.plutoDetails.get("DeliveryFee"));
		}		
		additionalInformationPage.clickOnNextBtn();
		MasterDataReader.pageDetails.put("AdditionalInformationPage", additionalInformationPage);
	}

	@And("^I confirm the order$")
	public void i_confirm_the_order() {
		AdditionalInformationPage additionalInformationPage = (AdditionalInformationPage) MasterDataReader.pageDetails.get("AdditionalInformationPage");
		additionalInformationPage.clickOnNextBtn();
		additionalInformationPage.authenticationRequired(MasterDataReader.plutoDetails.get("AuthUname"),MasterDataReader.plutoDetails.get("AuthPwd"));
	}

	@And("^I get the reference number$")
	public void i_get_the_reference_number() throws FilloException {
		AdditionalInformationPage additionalInformationPage = (AdditionalInformationPage) MasterDataReader.pageDetails.get("AdditionalInformationPage");
		String confirmationNum = additionalInformationPage.getConfirmationNum();
		UpdateDataInExcel up = new UpdateDataInExcel();
		up.updateDataInExcel("PlutoDetails", "ReferenceNumber", confirmationNum, MasterDataReader.customerDetails.get("AutomationID"),connection);
		MasterDataReader.scenario.write("Order confirmation number is : "+ confirmationNum);		
	}
}
