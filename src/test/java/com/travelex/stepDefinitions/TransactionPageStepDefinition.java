package com.travelex.stepDefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.travelex.framework.Dataset.CustomerDetailsDataSet;
import com.travelex.framework.Dataset.OrderDetailsDataSet;
import com.travelex.framework.common.ConfigurationProperties;
import com.travelex.framework.common.WebDriverWrapper;
import com.travelex.nam.pages.TransactionAndCurrencyPage;

import cucumber.api.Scenario;
import cucumber.api.java.en.When;

public class TransactionPageStepDefinition {

	public WebDriver driver;
	private TransactionAndCurrencyPage transactionAndCurrencyPage;
	public int index;
	public Scenario scenario;
	private List<OrderDetailsDataSet> ordDataset;
	private List<CustomerDetailsDataSet> custDataset;

	public TransactionPageStepDefinition(){
		driver = MasterDataReader.driver;
		scenario = MasterDataReader.scenario;
		ordDataset = MasterDataReader.orderDataset;
		custDataset = MasterDataReader.custDataset;
		index = MasterDataReader.index;
	}

	@When("^I Place Sale order$")
	public void i_Place_Sale_order() throws Throwable {
		transactionAndCurrencyPage = new TransactionAndCurrencyPage(driver).get();
		transactionAndCurrencyPage.orderTypeSelection(ordDataset.get(index).getOrderType(),
				ordDataset.get(index).getConfigOrderLink());
		
		String currency = ordDataset.get(index).getCurrency();
		String[] arrcurr = currency.split("\\|");
		String amount = ordDataset.get(index).getForeignAmount();
		String[] arrAmt = amount.split("\\|");
		String qty = ordDataset.get(index).getQuantity();
		String[] arrQty = qty.split("\\|");
		
		for(int i=0;i<arrcurr.length;i++){
		transactionAndCurrencyPage.transactionDetails(ordDataset.get(index).getTransactionType(),
				ordDataset.get(index).getProductType(),
				arrcurr[i].trim(),
				ordDataset.get(index).getCustomerType(),
				arrAmt[i].trim(),
				ordDataset.get(index).getConfigConfirmChkBox());
		transactionAndCurrencyPage.enterBeneficiaryDetails(custDataset.get(index).getBeneficiary(),
				custDataset.get(index).getAddressLine1(),
				custDataset.get(index).getAddressLine2(),
				custDataset.get(index).getCity(),
				custDataset.get(index).getState(),
				custDataset.get(index).getZipCode(),
				custDataset.get(index).getDepositCountry(),
				custDataset.get(index).getComments());
		String text = "Transaction Type : "+ ordDataset.get(index).getTransactionType()+System.lineSeparator()+"Product Type : "+ ordDataset.get(index).getProductType()+System.lineSeparator()+
				"Currency : "+arrcurr[i]+System.lineSeparator()+"Account Holder Type : "+
				ordDataset.get(index).getCustomerType();
		scenario.write(text);

		transactionAndCurrencyPage.addOrderBtnClick(ordDataset.get(index).getConfigAddToOrder(),
				ordDataset.get(index).getDenomMessage(),
				ordDataset.get(index).getConfigDenomAlert(),
				arrQty[i].trim(),ordDataset.get(index).getWarningMessage());
		}
		
		
		transactionAndCurrencyPage.editButtonClick(ordDataset.get(index).getConfigEditButton(), ordDataset.get(index).getForeignAmount(), 
				ordDataset.get(index).getConfigAddToOrder(), ordDataset.get(index).getDenomMessage(), ordDataset.get(index).getConfigDenomAlert(), 
				ordDataset.get(index).getWarningMessage(), ordDataset.get(index).getQuantity(),ordDataset.get(index).getConfigSpecifyDenom());
		transactionAndCurrencyPage.clickShowCurrencyButton(ordDataset.get(index).getConfigShowCurrency());
		transactionAndCurrencyPage.clearButtonClick(ordDataset.get(index).getConfigClearFields());
		transactionAndCurrencyPage.deleteButtonClick(ordDataset.get(index).getConfigDeleteBtn());
		transactionAndCurrencyPage.deliveryTypeBtn(ordDataset.get(index).getConfigDeliveryType(),
				ordDataset.get(index).getDeliveryType());
		
		transactionAndCurrencyPage.fetchOrderDetails(ordDataset.get(index).getCurrency(),
				ordDataset.get(index).getConfigFetchOrderDetails());
		
		transactionAndCurrencyPage.buttonClick(ordDataset.get(index).getConfigConfirmBtn());	
		transactionAndCurrencyPage.switchtoPopUpAndGetMessage(ordDataset.get(index).getErrorMessage());
		
	}


	@When("^I Place Sale order using QuoteAndView button$")
	public void i_Place_Sale_order_using_QuoteAndView_button() throws Throwable {
		transactionAndCurrencyPage = new TransactionAndCurrencyPage(driver).get();
		transactionAndCurrencyPage.transDetailsQuoteView(ordDataset.get(index).getTransactionType(),
				ordDataset.get(index).getProductType(),
				ordDataset.get(index).getCurrency(),
				ordDataset.get(index).getCustomerType(),
				ordDataset.get(index).getForeignAmount(),
				ordDataset.get(index).getConfigConfirmChkBox(),
				ordDataset.get(index).getAmountMsg(),
				ordDataset.get(index).getConfigQuoteAndViewBtn());
		
		transactionAndCurrencyPage.addOrderBtnClickQuoteView(ordDataset.get(index).getConfigAddToOrder(),
				ordDataset.get(index).getDenomMessage(),
				ordDataset.get(index).getConfigDenomAlert(),
				ordDataset.get(index).getQuantity(),
				custDataset.get(index).getAccountHolderMessage(),
				ordDataset.get(index).getConfigConfirmChkBox(),ordDataset.get(index).getWarningMessage());
		transactionAndCurrencyPage.deliveryTypeBtn(ordDataset.get(index).getConfigDeliveryType(),
				ordDataset.get(index).getDeliveryType());
		transactionAndCurrencyPage.clearButtonClick(ordDataset.get(index).getConfigClearFields());
		transactionAndCurrencyPage.clickShowCurrencyButton(ordDataset.get(index).getConfigShowCurrency());
		transactionAndCurrencyPage.fetchOrderDetails(ordDataset.get(index).getCurrency(),
				ordDataset.get(index).getConfigFetchOrderDetails());
		transactionAndCurrencyPage.buttonClick(ordDataset.get(index).getConfigConfirmBtn());		
	}
}
