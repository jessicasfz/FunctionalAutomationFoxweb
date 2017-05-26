package com.travelex.stepDefinitions;

import org.testng.Assert;

import com.travelex.framework.common.WebDriverWrapper;
import com.travelex.nam.pages.CustomerDetailsPage;
import com.travelex.nam.pages.HomePage;
import com.travelex.nam.pages.PrinterFriendlyPage;
import com.travelex.nam.pages.TransactionAndCurrencyPage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TransactionPageStepDefinition {
	WebDriverWrapper wrapper ;

	@When("^I create order of transaction for customer$")
	public void i_create_order_of_transaction_for_customer() throws Throwable {		
		HomePage homePage = (HomePage)MasterDataReader.pageDetails.get("HomePage");
		String orderType = MasterDataReader.orderDetails.get("OrderType");
		TransactionAndCurrencyPage transactionAndCurrencyPage = homePage.selectOrderType(orderType);
		transactionAndCurrencyPage.selectTransactionTypeDetails(MasterDataReader.orderDetails.get("TransactionType"), MasterDataReader.orderDetails.get("CustomerType"));
		MasterDataReader.pageDetails.put("TransactionAndCurrencyPage", transactionAndCurrencyPage);
		i_confirm_this_trasaction();
	}

	@When("^I select currency of product$")
	public void i_select_currency_of_product() throws Throwable {
		TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
		transactionAndCurrencyPage.selectProductAndCurrency(MasterDataReader.orderDetails.get("Currency"),MasterDataReader.orderDetails.get("ProductType"));
	}

	@When("^I (do not click|click) on Quote and view (after|before) entering amount$")
	public void i_do_not_click_on_Quote_and_view_after_entering_amount(String clickStr, String beforeAfter) throws Throwable {
		TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");		

		if(clickStr.equalsIgnoreCase("click")){
			if(beforeAfter.equalsIgnoreCase("after")){
				transactionAndCurrencyPage.clearAndEnterForeignAmount(MasterDataReader.orderDetails.get("ForeignAmount"));
				transactionAndCurrencyPage.clickQuoteAndViewButton();
				transactionAndCurrencyPage.clickOnRoundedValue();
			}else{
				transactionAndCurrencyPage.clickQuoteAndViewButton();
				transactionAndCurrencyPage.clickOnRoundedValue();
				boolean alertExist = transactionAndCurrencyPage.isAlertPresent();
				if(alertExist){
					String actualErrorMessgae = transactionAndCurrencyPage.acceptAlerts();
					String expectedErrorMessage = "Please enter an amount.";
					Assert.assertEquals(actualErrorMessgae, expectedErrorMessage);
				}else{
					Assert.fail("Expected Error Message Not Exist In The System ::: Please Enter Amount. ");
				}
			}
		}else{
			transactionAndCurrencyPage.clearAndEnterForeignAmount(MasterDataReader.orderDetails.get("ForeignAmount"));
		}
	}

	@When("^I enter beneficiary details$")
	public void i_enter_beneficiary_details() throws Throwable {
		TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");	
		transactionAndCurrencyPage.enterBeneficiaryDetails(MasterDataReader.orderDetails.get("DraftBeneficiary"), MasterDataReader.orderDetails.get("DraftAddressLine1"), MasterDataReader.orderDetails.get("DraftAddressLine2"), MasterDataReader.orderDetails.get("DraftCity"), MasterDataReader.orderDetails.get("DraftState"), MasterDataReader.orderDetails.get("DraftZipCode"), MasterDataReader.orderDetails.get("DraftDepositCountry"), MasterDataReader.orderDetails.get("DraftComments"));
	}

	@When("^I click on AddToOrder$")
	public void i_click_on_AddToOrder() throws Throwable {
		TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
		transactionAndCurrencyPage.addtoOrderAndViewCurrencyWindow(MasterDataReader.orderDetails.get("ConfigCurrencyView"),MasterDataReader.orderDetails.get("ConfigErrMsg"));
	}

	@When("^I (do not add|add) denomination on add order$")
	public void i_do_not_add_denomination_on_add_order(String addDenomination) throws Throwable {
		if(MasterDataReader.orderDetails.get("TransactionType").equalsIgnoreCase("Sale") && MasterDataReader.orderDetails.get("OrderType").equalsIgnoreCase("WholeSale")) {

		}
		else if(!(MasterDataReader.orderDetails.get("TransactionType").equalsIgnoreCase("Purchase")) || (MasterDataReader.orderDetails.get("TransactionType").equalsIgnoreCase("Purchase") && MasterDataReader.orderDetails.get("OrderType").equalsIgnoreCase("WholeSale"))){
			TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
			if((addDenomination.equalsIgnoreCase("do not add")  && MasterDataReader.orderDetails.get("ProductType").contains("Currencies")) || (MasterDataReader.orderDetails.get("ConfigDenoms").contains("No"))){
				transactionAndCurrencyPage.driver.switchTo().alert().dismiss();			
			}else if(addDenomination.equalsIgnoreCase("add") && MasterDataReader.orderDetails.get("ProductType").contains("Currencies")){
				transactionAndCurrencyPage.driver.switchTo().alert().accept();
				transactionAndCurrencyPage.denomSelection(MasterDataReader.orderDetails.get("Quantity"), MasterDataReader.orderDetails.get("Denomination"));
			}
		}
	}

	@When("^I (waive|do not waive) fee$")
	public void i_waive_fee(String waiveFee) throws Throwable {
		TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
		if(waiveFee.equalsIgnoreCase("waive")){
			if(!MasterDataReader.orderDetails.get("WaiveReason").equalsIgnoreCase("NA")) {
				transactionAndCurrencyPage.checkwaiveFeeCheck(MasterDataReader.orderDetails.get("WaiveReason"));
			}
		}else{
			transactionAndCurrencyPage.uncheckwaiveFeeCheck();
		}
	}

	@When("^I select (payment method|delivery time|without payment method) to confirm order$")
	public void i_select_payment_method_to_confirm_order(String confirmBeforeCondition) throws Throwable {
		TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
		CustomerDetailsPage customerDetailsPage = null;
		boolean pageStatus = false;
		if(MasterDataReader.orderDetails.get("ProductType").equalsIgnoreCase("Pre-Paid Cards")){
			pageStatus = transactionAndCurrencyPage.selectPaymentMethodAndConfirmOrderForPPC(MasterDataReader.orderDetails.get("PaymentMethod"));
			if(pageStatus){
				Assert.assertTrue(pageStatus, "PPC page succesfully loaded");
			}else{
				Assert.fail("Unable to load PPC page");
			}
		}else{
			customerDetailsPage = transactionAndCurrencyPage.selectPaymentMethodAndCinfirmOrder(MasterDataReader.orderDetails.get("PaymentMethod"));
			MasterDataReader.pageDetails.put("CustomerDetailsPage", customerDetailsPage);
		}
	}

	@When("^I add more currency (do not add|add) denomination on add order (do not click|click) On currency view$")
	public void i_add_more_currency_do_not_add_denomination_on_add_order_do_not_click_On_currency_view(String addDenomination,String clickCurrencyView) throws Throwable {
		TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");	
		String configaddMoreCurrencies = MasterDataReader.orderDetails.get("ConfigAddMoreCurrencies");
		String listOfcurrenciesInDetail = MasterDataReader.orderDetails.get("MultiCurrencyDetails");

		if(WebDriverWrapper.isConfigTrue(configaddMoreCurrencies)){
			String[] listOfProductsInDetailDenoms = listOfcurrenciesInDetail.split("\\|");
			int noOfProducts = listOfProductsInDetailDenoms.length;

			for(int i=0;i<=noOfProducts-1;i++){
				String listOfProductDetails = listOfProductsInDetailDenoms[i];
				String[] eachProductDetails = listOfProductDetails.split("\\#");

				if(eachProductDetails[0].equalsIgnoreCase("Foreign Currencies")){
					String product = eachProductDetails[0].trim();
					String currency = eachProductDetails[1].trim();
					String foreignAmount = eachProductDetails[2].trim();

					transactionAndCurrencyPage.selectProductAndCurrency(currency,product);
					transactionAndCurrencyPage.clickingOrNotClickingOnQuoteAndViewBeforeAndAfterEnteringAmount("click", "after", foreignAmount);
					transactionAndCurrencyPage.clickOnRoundedValue();
					transactionAndCurrencyPage.addtoOrderAndViewCurrencyWindow(MasterDataReader.orderDetails.get("ConfigCurrencyView"),MasterDataReader.orderDetails.get("ConfigErrMsg"));

					if(!MasterDataReader.orderDetails.get("TransactionType").equalsIgnoreCase("Purchase") || (MasterDataReader.orderDetails.get("TransactionType").equalsIgnoreCase("Purchase") && MasterDataReader.orderDetails.get("OrderType").equalsIgnoreCase("WholeSale"))){
						if(addDenomination.equalsIgnoreCase("do not add") || (MasterDataReader.orderDetails.get("ConfigDenoms").contains("No"))){
							if(!(MasterDataReader.orderDetails.get("TransactionType").equalsIgnoreCase("Sale") && MasterDataReader.orderDetails.get("OrderType").equalsIgnoreCase("WholeSale"))){
								transactionAndCurrencyPage.dismissAlert();
							}
						}else if(addDenomination.equalsIgnoreCase("add")){
							transactionAndCurrencyPage.acceptAlert();
							String quantity = eachProductDetails[3].trim();
							String denom = eachProductDetails[4].trim();

							if(!quantity.equalsIgnoreCase("NA")){
								transactionAndCurrencyPage.denomSelection(quantity.toString(), denom.toString());
							}							
						}
					}
				}

				else if(eachProductDetails[0].equalsIgnoreCase("Foreign Check")){
					String product = eachProductDetails[0].trim();
					String currency = eachProductDetails[1].trim();
					String foreignAmount = eachProductDetails[2].trim();
					String countryOfIssue = eachProductDetails[3].trim();
					String checkDate = eachProductDetails[4].trim();
					String issuerEndorsed = eachProductDetails[5].trim();
					String payeeEndorsed = eachProductDetails[6].trim();

					transactionAndCurrencyPage.selectProductAndCurrency(currency,product);
					transactionAndCurrencyPage.clickingOrNotClickingOnQuoteAndViewBeforeAndAfterEnteringAmount("click", "after", foreignAmount);				
					transactionAndCurrencyPage.updateCheckDetails(countryOfIssue,checkDate,issuerEndorsed,payeeEndorsed);
					transactionAndCurrencyPage.addtoOrderAndViewCurrencyWindow(MasterDataReader.orderDetails.get("ConfigCurrencyView"),MasterDataReader.orderDetails.get("ConfigErrMsg"));																		
				}

				else if(eachProductDetails[0].equalsIgnoreCase("Foreign Drafts")){
					String product = eachProductDetails[0].trim();
					String currency = eachProductDetails[1].trim();
					String foreignAmount = eachProductDetails[2].trim();
					String beneficiary = eachProductDetails[3].trim();
					String addressLine1 = eachProductDetails[4].trim();
					String addressLine2 = eachProductDetails[5].trim();
					String city = eachProductDetails[6].trim();
					String state = eachProductDetails[7].trim();
					String zipCode = eachProductDetails[8].trim();
					String country = eachProductDetails[9].trim();
					String comments = eachProductDetails[10].trim();

					transactionAndCurrencyPage.selectProductAndCurrency(currency,product);
					transactionAndCurrencyPage.clickingOrNotClickingOnQuoteAndViewBeforeAndAfterEnteringAmount("click", "after", foreignAmount);
					transactionAndCurrencyPage.enterBeneficiaryDetails(beneficiary, addressLine1, addressLine2, city, state, zipCode, country, comments);
					transactionAndCurrencyPage.addtoOrderAndViewCurrencyWindow(MasterDataReader.orderDetails.get("ConfigCurrencyView"),MasterDataReader.orderDetails.get("ConfigErrMsg"));
				}

				else if(eachProductDetails[0].equalsIgnoreCase("Pre-Paid Cards")){

					String product = eachProductDetails[0].trim();
					String currency = eachProductDetails[1].trim();
					String foreignAmount = eachProductDetails[2].trim();

					transactionAndCurrencyPage.selectProductAndCurrency(currency,product);
					transactionAndCurrencyPage.clickingOrNotClickingOnQuoteAndViewBeforeAndAfterEnteringAmount("do not click", "after", foreignAmount);
					transactionAndCurrencyPage.addtoOrderAndViewCurrencyWindow(MasterDataReader.orderDetails.get("ConfigCurrencyView"),MasterDataReader.orderDetails.get("ConfigErrMsg"));
				}
			}						
		}	
	}

	@Then("^I verify order details$")
	public void i_verify_order_details() throws Throwable {

	}

	@When("^I update check details$")
	public void i_update_check_details() throws Throwable {
		TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
		transactionAndCurrencyPage.updateCheckDetails(MasterDataReader.orderDetails.get("CountryOfIssue"),MasterDataReader.orderDetails.get("CheckDate"),MasterDataReader.orderDetails.get("IssuerEndorsed"),MasterDataReader.orderDetails.get("PayeeEndorsed"));	    
	}

	@Then("^I click on (Cancel Order|Clear Fields|Delete order|Change order|Next order|Edit Order) button$")
	public void i_click_on_Cancel_Order_button(String button) throws Throwable {
		if(button.equalsIgnoreCase("Cancel Order")){
			CustomerDetailsPage customerDetailsPage = (CustomerDetailsPage)MasterDataReader.pageDetails.get("CustomerDetailsPage");
			customerDetailsPage.clickOnCancelOrderButton();

		}else if(button.equalsIgnoreCase("Clear Fields")){
			TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
			transactionAndCurrencyPage.clickOnclearFieldsButton();

		}else if(button.equalsIgnoreCase("Delete order")){
			TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
			transactionAndCurrencyPage.clickOnDeleteOrderButton();

		}else if(button.equalsIgnoreCase("Change order")){
			CustomerDetailsPage customerDetailsPage = (CustomerDetailsPage)MasterDataReader.pageDetails.get("CustomerDetailsPage");
			customerDetailsPage.clickOnChangeOrderButton();

		}else if(button.equalsIgnoreCase("Next order")){
			PrinterFriendlyPage printerFriendlyPage = (PrinterFriendlyPage)MasterDataReader.pageDetails.get("PrinterPage");
			printerFriendlyPage.nextOrderButtonClick();

		}else if(button.equalsIgnoreCase("Edit Order")){
			TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
			transactionAndCurrencyPage.editOrderButtonClick();

		}
	}

	@Then("^I verify page for (Cancel Order|Clear Fields|Delete Order|Change Order|Next Order|Edit Order)$")
	public void i_verify_page(String page) throws Throwable {
		if(page.equalsIgnoreCase("Cancel Order")){
			CustomerDetailsPage customerDetailsPage = (CustomerDetailsPage)MasterDataReader.pageDetails.get("CustomerDetailsPage");
			HomePage homepage = (HomePage) customerDetailsPage.verifyCancelOrderBtn();
			if(homepage!=null){
				MasterDataReader.scenario.write("Page loaded successfully. Cancel order Verification is passed");
			}else{
				MasterDataReader.scenario.write("Cancel order Verification is Failed");
			}
		}else if(page.equalsIgnoreCase("Clear Fields")){
			TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
			transactionAndCurrencyPage.verifyClearFieldsPage();
		}else if(page.equalsIgnoreCase("Delete order")){
			TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
			transactionAndCurrencyPage.verifyDeleteOrderPage();
		}else if(page.equalsIgnoreCase("Change order")){
			TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
			transactionAndCurrencyPage.verifyChangeOrderPage(MasterDataReader.orderDetails.get("Currency"));
			MasterDataReader.scenario.write("Page loaded successfully. Change order Verification is passed");
		}else if(page.equalsIgnoreCase("Next order")){
			PrinterFriendlyPage printerFriendlyPage = (PrinterFriendlyPage)MasterDataReader.pageDetails.get("PrinterPage");
			TransactionAndCurrencyPage transactionAndCurrencyPage = printerFriendlyPage.verifyNextOrderBtn();
			if(transactionAndCurrencyPage!=null){
				MasterDataReader.scenario.write("Page loaded successfully. Next order Verification is passed");
			}else{
				MasterDataReader.scenario.write("Next order Verification is Failed");
			}
		}else if(page.equalsIgnoreCase("Edit Order")){
			TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
			transactionAndCurrencyPage.verifyEditOrderPage();
		}
	}

	@Then("^I get (maximumAmt|minimumAmt) error message$")
	public void i_get_maximumAmt_error_message(String msg) throws Throwable {
		if(msg.equalsIgnoreCase("minimumAmt")){

		}else if(msg.equalsIgnoreCase("maximumAmt")){
			TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
			transactionAndCurrencyPage.verifyAmtAlert(MasterDataReader.orderDetails.get("ErrMsg"));
		}
	}

	@When("^I select checkbox for secondary card$")
	public void i_select_checkbox_for_secondary_card(String selection) throws Throwable {
		//TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");

	}

	@When("^I enter valid Primary cardholder details$")
	public void i_enter_valid_primary_cardholder_details() throws Throwable {
		TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
		transactionAndCurrencyPage.enterKYCDetails(MasterDataReader.customerDetails.get("PPCDetails"));
	}

	@When("^I get KYC success message on submitting the details$")
	public void i_get_kyc_success_message_on_submitting_the_details() throws Throwable {
		TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
		transactionAndCurrencyPage.submitKYCDetails();
	}

	@When("^I confirm this trasaction$")
	public void i_confirm_this_trasaction() throws Throwable {		
		TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
		String flag = MasterDataReader.orderDetails.get("ConfirmTransaction");
		transactionAndCurrencyPage.checkBoxClick("CHROME", flag.equalsIgnoreCase("YES")? "Y":"NA");
		MasterDataReader.pageDetails.put("TransactionAndCurrencyPage", transactionAndCurrencyPage);
	}

	@When("^I select delivery method$")
	public void i_select_delivery_method() throws Throwable {		
		TransactionAndCurrencyPage transactionAndCurrencyPage = (TransactionAndCurrencyPage)MasterDataReader.pageDetails.get("TransactionAndCurrencyPage");
		String temp = MasterDataReader.orderDetails.get("DeliveryMethod");
		transactionAndCurrencyPage.deliveryTypeBtn(temp.equalsIgnoreCase("NA") ? "N" : "Y", temp);
		MasterDataReader.pageDetails.put("TransactionAndCurrencyPage", transactionAndCurrencyPage);
	}
}
