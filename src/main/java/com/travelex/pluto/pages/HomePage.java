package com.travelex.pluto.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

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

	@FindBy(name = "cboPurchaseType")
	WebElement listPurchaseType;

	@FindBy(name = "btnNext")
	WebElement btnNext;

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

	public TransactionPage navigateToTransactionPage(String transactonType,String purchaseType) {
		wrapper.waitForElementToBeDisplayed(linkPurchaseOrder, timeOutPeriod);
		switch (transactonType.toUpperCase()) {
		case "SALEORDER":
			linkSaleOrder.click();
			try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
			break;

		case "WHOLESALESALEORDER":
			linkWholeSaleSaleOrder.click();
			break;

		case "PURCHASEORDER":
			linkPurchaseOrder.click();
			selectTypeOfPurchase(transactonType,purchaseType);
			break;

		case "WHOLESALEPURCHASEORDER":
			linkWholeSalePurchase.click();
			selectTypeOfPurchase(transactonType,purchaseType);
			break;
		}
		return new TransactionPage(driver).get();
	}

	public void selectTypeOfPurchase(String transactionType,String purchaseType){
		if(transactionType.equalsIgnoreCase("PurchaseOrder")){
			wrapper.waitForElementToBeDisplayed(listPurchaseType, timeOutPeriod);
			Select listPurchaseTypeList = new Select(listPurchaseType);
			listPurchaseTypeList.selectByVisibleText(purchaseType);
			btnNext.click();
		}		
	}

}
