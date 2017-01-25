package com.travelex.stepDefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.travelex.framework.Dataset.CustomerDetailsDataSet;
import com.travelex.framework.Dataset.OrderDetailsDataSet;
import com.travelex.nam.pages.PrinterFriendlyPage;
import com.travelex.nam.pages.SearchOrderPage;
import com.travelex.nam.pages.TransactionAndCurrencyPage;

import cucumber.api.Scenario;
import cucumber.api.java.en.Then;

public class OrderDetailsStepDefinition {
	
	public WebDriver driver;
	public int index;
	public Scenario scenario;
	PrinterFriendlyPage printerFriendlyPage;
	SearchOrderPage searchOrderPage;
	private List<CustomerDetailsDataSet> custDataset;
	private List<OrderDetailsDataSet> ordDataset;
	
	public OrderDetailsStepDefinition(){
		driver = MasterDataReader.driver;
		scenario = MasterDataReader.scenario;
		custDataset = MasterDataReader.custDataset;
		ordDataset = MasterDataReader.orderDataset;
		index = MasterDataReader.index;
	}
		
	@Then("^I get Order details$")
	public void i_get_Order_details() throws Throwable {
		printerFriendlyPage = new PrinterFriendlyPage(driver).get();
		printerFriendlyPage.nextOrderButtonClick(custDataset.get(index).getConfigNextOrderBtn());
		printerFriendlyPage.changeBranchLink(custDataset.get(index).getConfigChangeBranchLink());
		ordDataset.get(index).getCurrency();
		
		String[] curr = ordDataset.get(index).getCurrency().split("\\|");
		for(int i=0;i<curr.length;i++){
		scenario.write("Description :"+TransactionAndCurrencyPage.ordDetails.get(i).getDescription()+""+System.lineSeparator()+""+
				"Units :"+TransactionAndCurrencyPage.ordDetails.get(i).getUnits()+""+System.lineSeparator()+""+
				"USD :"+TransactionAndCurrencyPage.ordDetails.get(i).getUSD()+""+System.lineSeparator()+""+
				"Rate :"+ TransactionAndCurrencyPage.ordDetails.get(i).getRate());
		}
		scenario.write("Order Total is "+TransactionAndCurrencyPage.data.getOrdTotal());
	}
}
