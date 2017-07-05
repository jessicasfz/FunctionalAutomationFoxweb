package com.travelex.stepDefinitions;

import org.testng.Assert;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.travelex.foxweb.pages.FoxwebHomePage;
import com.travelex.foxweb.pages.FoxwebLoginPage;
import com.travelex.foxweb.pages.OrderConfirmationPage;
import com.travelex.foxweb.pages.OrderDetailsPage;
import com.travelex.foxweb.pages.PreviewDetailsPage;
import com.travelex.foxweb.pages.ProcessTransactionPage;
import com.travelex.foxweb.pages.QuoteDetailsPage;
import com.travelex.framework.common.ConfigurationProperties;
import com.travelex.framework.common.UpdateDataInExcel;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FoxwebLoginPageStepDefinition {

	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	public static Connection connection = UpdateDataInExcel.getConnection();


	@Given("^I launch Foxweb application$")
	public void i_launch_Foxweb_application() throws Throwable {
		String foxwebWebSiteURL = configurationProperties.getProperty(ConfigurationProperties.FOXWEB_APPLICATION_URL_SIT);
		System.out.println(foxwebWebSiteURL);
		String browserName = MasterDataReader.environmentParameter.getBrowserName();
		FoxwebLoginPage fwloginPage = new FoxwebLoginPage(MasterDataReader.driver, foxwebWebSiteURL, browserName).get();
		FoxwebHomePage fwhomePage = fwloginPage.clickLogin(MasterDataReader.foxwebOrderDetails.get("Username"),MasterDataReader.foxwebOrderDetails.get("AccountCode"),MasterDataReader.foxwebOrderDetails.get("Password"));
		MasterDataReader.pageDetails.put("FoxwebHomePage", fwhomePage);
	}

	@When("^I navigate to Get Quote Page$")
	public void i_navigate_to_Get_Quote_Page() throws Throwable {
		FoxwebHomePage fwhomePage = (FoxwebHomePage)MasterDataReader.pageDetails.get("FoxwebHomePage");
		QuoteDetailsPage quoteDetailsPage= fwhomePage.navigateToGetQuotePAge(MasterDataReader.foxwebOrderDetails.get("ProductType"));
		MasterDataReader.pageDetails.put("QuoteDetailsPage", quoteDetailsPage);
	}

	@When("^I enter the Quote Details$")
	public void i_enter_the_Quote_Details() throws Throwable {
		QuoteDetailsPage quoteDetailsPage = (QuoteDetailsPage)MasterDataReader.pageDetails.get("QuoteDetailsPage");
		quoteDetailsPage.enterQuoteDetails(MasterDataReader.foxwebOrderDetails.get("ForeignCurrency"),MasterDataReader.foxwebOrderDetails.get("ForeignAmount"));


	}

	@Then("^I should see Quote Details$")
	public void i_should_see_Quote_Details() throws Throwable {
		QuoteDetailsPage quoteDetailsPage = (QuoteDetailsPage)MasterDataReader.pageDetails.get("QuoteDetailsPage");
		boolean flag = quoteDetailsPage.verifyQuoteDetailsisDisplayed();
		Assert.assertTrue(flag);
	}

	@When("^I click on Convert Button$")
	public void i_click_on_Convert_Button() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
	}

	@When("^I enter the Customer Details$")
	public void i_enter_the_Customer_Details() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
	}

	@When("^I preview and confirm Details$")
	public void i_preview_and_confirm_Details() throws Throwable {
		PreviewDetailsPage previewDetailsPage = (PreviewDetailsPage)MasterDataReader.pageDetails.get("PreviewDetailsPage");
		Boolean statusPreviewOrderPage =previewDetailsPage.isPreviewOrderPageLoaded();
		Assert.assertTrue(statusPreviewOrderPage);
		MasterDataReader.pageDetails.put("PreviewDetailsPage", previewDetailsPage);
	}



	@When("^I navigate to Order Page$")
	public void i_navigate_to_Order_Page() throws Throwable {
		FoxwebHomePage fwhomePage = (FoxwebHomePage)MasterDataReader.pageDetails.get("FoxwebHomePage");
		OrderDetailsPage orderDetailsPage = fwhomePage.navigateToOrderPage(MasterDataReader.foxwebOrderDetails.get("ProductType"));
		MasterDataReader.pageDetails.put("OrderDetailsPage", orderDetailsPage);
	}

	@When("^I enter the Order Details$")
	public void i_enter_the_Order_Details() throws Throwable {
		OrderDetailsPage orderDetailsPage = (OrderDetailsPage)MasterDataReader.pageDetails.get("OrderDetailsPage");
		PreviewDetailsPage previewDetailsPage = orderDetailsPage.enterOrderDetails(MasterDataReader.foxwebOrderDetails.get("ForeignCurrency"),MasterDataReader.foxwebOrderDetails.get("ForeignAmount"),MasterDataReader.foxwebOrderDetails.get("CustomerName"));
		MasterDataReader.pageDetails.put("PreviewDetailsPage", previewDetailsPage);
	}

	@And("^I select Suspicious Transaction$")
	public void i_select_Suspicious_Transaction() throws Throwable {
		PreviewDetailsPage previewDetailsPage = (PreviewDetailsPage)MasterDataReader.pageDetails.get("PreviewDetailsPage");
		previewDetailsPage.selectSuspiciousTransaction(MasterDataReader.foxwebOrderDetails.get("SuspiciousTransactionType"));


	}

	@Then("^I click on Order Confirmation$")
	public void i_click_on_Order_Confirmation() throws Throwable {
		PreviewDetailsPage previewDetailsPage = (PreviewDetailsPage)MasterDataReader.pageDetails.get("PreviewDetailsPage");
		OrderConfirmationPage orderConfirmationPage = previewDetailsPage.clickOnOrderConfirmationButton();
		MasterDataReader.pageDetails.put("OrderConfirmationPage", orderConfirmationPage);
	}

	@Then("^I get the Order Confirmation Success message$")
	public void i_get_the_Order_Confirmation_Success_message() throws FilloException {
		OrderConfirmationPage orderConfirmationPage = (OrderConfirmationPage)MasterDataReader.pageDetails.get("OrderConfirmationPage");
		boolean status = orderConfirmationPage.verifyOrderConfirmationSuccessMessageIsDisplayed();
		Assert.assertTrue(status);
		boolean statusOrder = orderConfirmationPage.verifyOrderNumberIsGenerated();

		UpdateDataInExcel up = new UpdateDataInExcel();

		up.updateDataInExcel("FoxwebOrderDetails", "OrderNo", orderConfirmationPage.OrderNo, MasterDataReader.foxwebOrderDetails.get("AutomationID"),connection);
		up.updateDataInExcel("FoxwebOrderDetails", "OrderStatus", orderConfirmationPage.txtOrderStatusSt, MasterDataReader.foxwebOrderDetails.get("AutomationID"),connection);
		Assert.assertTrue(statusOrder);
		MasterDataReader.pageDetails.put("OrderConfirmationPage", orderConfirmationPage);
	}


	@When("^I navigate to Process Transactions Page$")
	public void i_navigate_to_Process_Transactions_Page()  {
		FoxwebHomePage fwhomePage = (FoxwebHomePage)MasterDataReader.pageDetails.get("FoxwebHomePage");
		ProcessTransactionPage processTransactionpage = fwhomePage.navigateToProcessTransactionPage();
		MasterDataReader.pageDetails.put("ProcessTransactionPage", processTransactionpage);
	}

	@When("^I enter the Process Transactions Details$")
	public void i_enter_the_Process_Transactions_Details()  {
		ProcessTransactionPage processTransactionpage= (ProcessTransactionPage)MasterDataReader.pageDetails.get("ProcessTransactionPage");
		processTransactionpage.enterProcessTransactionDetail(MasterDataReader.foxwebOrderDetails.get("TransactionType"));
		MasterDataReader.pageDetails.put("ProcessTransactionPage", processTransactionpage);

	}

	@When("^I process the transaction$")
	public void i_process_the_transaction()  {
		ProcessTransactionPage processTransactionpage= (ProcessTransactionPage)MasterDataReader.pageDetails.get("ProcessTransactionPage");
		processTransactionpage.clickOnProcessTransactionButton();
		MasterDataReader.pageDetails.put("ProcessTransactionPage", processTransactionpage);
	}

	@Then("^I get the Process Transaction Success message$")
	public void i_get_the_Process_Transaction_Success_message()  {
		ProcessTransactionPage processTransactionpage= (ProcessTransactionPage)MasterDataReader.pageDetails.get("ProcessTransactionPage");
		boolean OrderStatus = processTransactionpage.verifyProcessTransactionConfirmationmessage();
		Assert.assertTrue(OrderStatus);
	}


}
