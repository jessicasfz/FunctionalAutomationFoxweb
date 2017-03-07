package com.travelex.stepDefinitions;

import com.travelex.framework.common.ConfigurationProperties;
import com.travelex.nam.pages.HomePage;
import com.travelex.nam.pages.LoginPage;
import com.travelex.nam.pages.TransactionAndCurrencyPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginPageStepDefinition {
	
	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	
	@Given("^I launch COL application$")
	public void i_launch_COL_application() throws Throwable {
		String colWebSiteURL = configurationProperties.getProperty(ConfigurationProperties.COL_APPLICATION_URL) + MasterDataReader.orderDetails.get("PartnerID");
		String browserName = MasterDataReader.environmentParameter.getBrowserName();
		LoginPage loginPage = new LoginPage(MasterDataReader.driver, colWebSiteURL, browserName).get();
		HomePage homePage = loginPage.clickLogin(MasterDataReader.orderDetails.get("Username"),MasterDataReader.orderDetails.get("Password"));
		MasterDataReader.pageDetails.put("HomePage", homePage);
	}
	
	
	@When("^I select branch and branch location$")
	public void i_select_branch_and_branch_location() throws Throwable {
		HomePage homePage = (HomePage)MasterDataReader.pageDetails.get("HomePage");
		homePage.selectBranchLocation(MasterDataReader.orderDetails.get("BranchLocation"));
		MasterDataReader.pageDetails.put("HomePage", homePage);
	}


	@Then("^I validate the total amount$")
	public void i_validate_the_total_amount() throws Throwable {
		TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
		MasterDataReader.txnDetails = transactionAndCurrencyPage.validateOrdDetails(MasterDataReader.orderDetails.get("Currency"),MasterDataReader.orderDetails.get("PartnerID"),MasterDataReader.orderDetails.get("TransactionType"));
	}

	@When("^I confirm order$")
	public void i_confirm_order() throws Throwable {
	    
	}
}
