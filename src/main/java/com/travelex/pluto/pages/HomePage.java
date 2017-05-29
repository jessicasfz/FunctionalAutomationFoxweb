package com.travelex.pluto.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.travelex.framework.common.WebDriverWrapper;

public class HomePage extends LoadableComponent<HomePage>{
	
	WebDriver driver;
	
	WebDriverWrapper wrapper ;
	private int timeOutPeriod = 30;

	@FindBy(id = "Menu1")
	WebElement linkSaleOrder;
		
	@FindBy(id = "Menu2")
	WebElement linkWholeSaleSaleOrder;
	
	@FindBy(id = "Menu3")
	WebElement linkPurchaseOrder;
	
	@FindBy(id = "Menu4")
	WebElement linkWholeSalePurchase;

	@FindBy(id = "Menu5")
	WebElement linkTransactionHistory;
	
	@FindBy(id = "Menu6")
	WebElement linkUserProfile;
		
	@FindBy(id = "Menu15")
	WebElement linkReports;
	
	@FindBy(id = "Menu18")
	WebElement linkSiteFloorLimits;
	
	@FindBy(id = "Menu19")
	WebElement linkCompanyFloorLimits;
	
	@FindBy(id = "Menu24")
	WebElement linkShortsList;
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}
	
	@SuppressWarnings("unused")
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(linkSaleOrder, timeOutPeriod);
		if(linkSaleOrder.isDisplayed()){
			isPageLoaded = true;
		}
	}
	
	public void load(){
		try {
			wrapper.waitForElementToBeDisplayed(linkSaleOrder, timeOutPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public HomePage navigateToTransactionPage(String transactonType) {

		switch (transactonType) {
		case "SaleOrder":
			linkSaleOrder.click();

		case "WholeSaleSaleOrder":
			linkWholeSaleSaleOrder.click();

		case "PurchaseOrder":
			linkPurchaseOrder.click();

		case "WholeSalePurchaseOrder":
			linkWholeSalePurchase.click();

		default:
			System.out.println("Transaction Type Not Exist");
			Assert.fail("Not Exist Transaction Type");
		}
		
		return new HomePage(driver).get();
	}
		
}
