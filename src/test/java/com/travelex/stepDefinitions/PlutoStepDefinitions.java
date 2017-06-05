package com.travelex.stepDefinitions;

import com.travelex.framework.common.ConfigurationProperties;
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
		
	@When("^I launch PLUTO application$")
	public void i_launch_pluto_application() {
		String plutoUrl = configurationProperties.getProperty(ConfigurationProperties.PLUTO_APPLICATION_URL);
		String browser = MasterDataReader.environmentParameter.getBrowserName();
		LoginPage loginPage = new LoginPage(MasterDataReader.driver,plutoUrl,browser);
		HomePage homePage = loginPage.clickLogin(MasterDataReader.plutoDetails.get("Username"), MasterDataReader.plutoDetails.get("Password"), MasterDataReader.plutoDetails.get("Company"));
		MasterDataReader.pageDetails.put("HomePage", homePage);
	}
	
	
	@And("^I select order type$")
	public void i_select_order_type() {
		HomePage homePage = (HomePage) MasterDataReader.pageDetails.get("HomePage");
		TransactionPage transactionPage = homePage.navigateToTransactionPage(MasterDataReader.plutoDetails.get("TransType"),MasterDataReader.plutoDetails.get("PurchaseType"));
		MasterDataReader.pageDetails.put("TransactionPage", transactionPage);
	}
	
	@And("^I enter company name customer type and delivery method$")
	public void i_enter_company_name_customer_type_and_Delivery_method() {
		TransactionPage transactionPage = (TransactionPage) MasterDataReader.pageDetails.get("TransactionPage");
		transactionPage.selectCustomerTypeAndDeliveryType(MasterDataReader.plutoDetails.get("CustomerType"), MasterDataReader.plutoDetails.get("DeliveryType"));
	}
	
	@And("^I search branch location$")
	public void i_search_branch_location() {
		TransactionPage transactionPage = (TransactionPage) MasterDataReader.pageDetails.get("TransactionPage");
		transactionPage.clickOnSearchAndSelectBranchLocation(MasterDataReader.plutoDetails.get("Location"));
	}
	
	@And("^I select product type and currency$")
	public void i_select_product_type_and_currency() {
		TransactionPage transactionPage = (TransactionPage) MasterDataReader.pageDetails.get("TransactionPage");
		transactionPage.enterCustomerDetailsInPurchse(MasterDataReader.plutoDetails.get("TransType"),MasterDataReader.plutoDetails.get("Salutation"),MasterDataReader.plutoDetails.get("FirstName"),MasterDataReader.plutoDetails.get("LastName"));
		transactionPage.enterProductDetails(MasterDataReader.plutoDetails.get("MultipleCurrencies"));
	}
		
	@And("^I enter customer detail$")
	public void i_enter_customer_details() {
		CustomerDetailsPage customerDetailsPage = new CustomerDetailsPage(MasterDataReader.driver);
		customerDetailsPage.enterCustomerDeliveryDetails(MasterDataReader.plutoDetails.get("Salutation"),MasterDataReader.plutoDetails.get("FirstName"), MasterDataReader.plutoDetails.get("LastName"), 
				MasterDataReader.plutoDetails.get("CollectionDate"),MasterDataReader.plutoDetails.get("DeliveryType"),MasterDataReader.plutoDetails.get("AwayBranchLocation"),MasterDataReader.plutoDetails.get("Address1"),
				MasterDataReader.plutoDetails.get("Address2"),MasterDataReader.plutoDetails.get("State"),MasterDataReader.plutoDetails.get("Country"),MasterDataReader.plutoDetails.get("ZipCode"),MasterDataReader.plutoDetails.get("HomeTelephoneNo"));
	}
	
	@And("^I click on Next button$")
	public void i_click_in_Next_button() {
		TransactionPage transactionPage = (TransactionPage) MasterDataReader.pageDetails.get("TransactionPage");
		transactionPage.clickOnNextBtn();
	}
	
	
	@And("^I enter Company Additional Info$")
	public void i_enter_Comapny_Additional_Info() {
		AdditionalInformationPage additionalInformationPage = new AdditionalInformationPage(MasterDataReader.driver);
		additionalInformationPage.enterAdditionalInfo(MasterDataReader.plutoDetails.get("Comments"), MasterDataReader.plutoDetails.get("OtherFee"), MasterDataReader.plutoDetails.get("TellerName"));
		additionalInformationPage.clickOnNextBtn();
		MasterDataReader.pageDetails.put("AdditionalInformationPage", additionalInformationPage);
	}
	
	@And("^I enter Company Additional Info For Sale$")
	public void i_enter_Comapny_Additional_Info_For_Sale() {
		AdditionalInformationPage additionalInformationPage = new AdditionalInformationPage(MasterDataReader.driver);
		additionalInformationPage.enterAdditionalInfo(MasterDataReader.plutoDetails.get("DeliveryType"),MasterDataReader.plutoDetails.get("ConfigAdditionalInfo"),MasterDataReader.plutoDetails.get("Comments"), MasterDataReader.plutoDetails.get("OtherFee"), MasterDataReader.plutoDetails.get("TellerName"),MasterDataReader.plutoDetails.get("AlternateTellerName"),MasterDataReader.plutoDetails.get("DeliveryFee"));
		additionalInformationPage.clickOnNextBtn();
		MasterDataReader.pageDetails.put("AdditionalInformationPage", additionalInformationPage);
	}
	
	@And("^I confirm the order$")
	public void i_confirm_the_order() {
		AdditionalInformationPage additionalInformationPage = (AdditionalInformationPage) MasterDataReader.pageDetails.get("AdditionalInformationPage");
		additionalInformationPage.clickOnNextBtn();
	}
	
	@And("^I get the reference number$")
	public void i_get_the_reference_number() {
		AdditionalInformationPage additionalInformationPage = (AdditionalInformationPage) MasterDataReader.pageDetails.get("AdditionalInformationPage");
		String confirmationNum = additionalInformationPage.getConfirmationNum();
		MasterDataReader.scenario.write("Order confirmation number is : "+ confirmationNum);
	}
	
	@And("^I authorized the order$")
	public void i_authorized_the_order() {
		AdditionalInformationPage additionalInformationPage = (AdditionalInformationPage) MasterDataReader.pageDetails.get("AdditionalInformationPage");
		additionalInformationPage.authenticationRequired(MasterDataReader.plutoDetails.get("AuthUname"),MasterDataReader.plutoDetails.get("AuthPwd"));
	}
	
}
