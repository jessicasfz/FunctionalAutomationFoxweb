package com.travelex.stepDefinitions;

import org.testng.Assert;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.travelex.foxweb.pages.FoxwebHomePage;
import com.travelex.foxweb.pages.FoxwebLoginPage;
import com.travelex.foxweb.pages.OrderConfirmationPage;
import com.travelex.foxweb.pages.OrderDetailsPage;
import com.travelex.foxweb.pages.PreviewDetailsPage;
import com.travelex.foxweb.pages.PreviewRepurchaseDetailsPage;
import com.travelex.foxweb.pages.ProcessTransactionPage;
import com.travelex.foxweb.pages.QuoteDetailsPage;
import com.travelex.foxweb.pages.RepurchaseConfirmationPage;
import com.travelex.foxweb.pages.RepurchaseDetailsPage;
import com.travelex.framework.common.ConfigurationProperties;
import com.travelex.framework.common.UpdateDataInExcel;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FoxwebRepurchaseStepDefinition {

	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	public static Connection connection = UpdateDataInExcel.getConnection();


	@When("^I navigate to Repurchase Page$")
	public void i_navigate_to_Repurchase_Page() throws Throwable {
		FoxwebHomePage fwhomePage = (FoxwebHomePage)MasterDataReader.pageDetails.get("FoxwebHomePage");
		RepurchaseDetailsPage fwRepurchaseDetailsPage = fwhomePage.navigateToRepurchasePage(MasterDataReader.foxwebOrderDetails.get("ProductType"),MasterDataReader.foxwebOrderDetails.get("ForeignCurrency"));
		MasterDataReader.pageDetails.put("RepurchasePage", fwRepurchaseDetailsPage);
	}

	@When("^I enter the Repurchase Details$")
	public void i_enter_the_Repurchase_Details() throws Throwable {
		RepurchaseDetailsPage fwRepurchaseDetailsPage = (RepurchaseDetailsPage)MasterDataReader.pageDetails.get("RepurchasePage");
		PreviewRepurchaseDetailsPage previewRepurchaseDetailsPage =	fwRepurchaseDetailsPage.enterRepurchaseDetails(MasterDataReader.foxwebOrderDetails.get("Quantity"), MasterDataReader.foxwebOrderDetails.get("Denomination"), MasterDataReader.foxwebOrderDetails.get("ForeignAmount"));
		MasterDataReader.pageDetails.put("PreviewRepurchaseDetailsPage", previewRepurchaseDetailsPage);
	}


	@Given("^I preview and confirm Details for Repurchase$")
	public void i_preview_and_confirm_Details_for_Repurchase() throws Throwable {
		PreviewRepurchaseDetailsPage previewRepurchaseDetailsPage = (PreviewRepurchaseDetailsPage)MasterDataReader.pageDetails.get("PreviewRepurchaseDetailsPage");
		boolean status = previewRepurchaseDetailsPage.isPreviewRepurchasePageLoaded();
		Assert.assertTrue(status);
		MasterDataReader.pageDetails.put("PreviewRepurchaseDetailsPage", previewRepurchaseDetailsPage);
	}

	@Then("^I click on Repurchase Confirmation$")
	public void i_click_on_Repurchase_Confirmation() throws Throwable {
		PreviewRepurchaseDetailsPage previewRepurchaseDetailsPage = (PreviewRepurchaseDetailsPage)MasterDataReader.pageDetails.get("PreviewRepurchaseDetailsPage");
		RepurchaseConfirmationPage repurchaseConfirmPage =previewRepurchaseDetailsPage.clickOnRepurchaseConfirmationButton();
		MasterDataReader.pageDetails.put("RepurchaseConfirmationPage", repurchaseConfirmPage);
	}

	@Then("^I get the Repurchase Confirmation Success message$")
	public void i_get_the_Repurchase_Confirmation_Success_message() throws Throwable {
		RepurchaseConfirmationPage repurchaseConfirmPage = (RepurchaseConfirmationPage)MasterDataReader.pageDetails.get("RepurchaseConfirmationPage");
		boolean status = repurchaseConfirmPage.verifyRepurchaseConfirmationSuccessMessageIsDisplayed();
		Assert.assertTrue(status);
		boolean statusOrder = repurchaseConfirmPage.verifyRepurchaseNumberIsGenerated();

		UpdateDataInExcel up = new UpdateDataInExcel();

		up.updateDataInExcel("FoxwebOrderDetails", "OrderNo", repurchaseConfirmPage.OrderNo, MasterDataReader.foxwebOrderDetails.get("AutomationID"),connection);
		up.updateDataInExcel("FoxwebOrderDetails", "OrderStatus", repurchaseConfirmPage.txtOrderStatusSt, MasterDataReader.foxwebOrderDetails.get("AutomationID"),connection);
		Assert.assertTrue(statusOrder);
		MasterDataReader.pageDetails.put("RepurchaseConfirmationPage", repurchaseConfirmPage);

	}




}