package com.travelex.nam.pages;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.travelex.framework.common.Log;
import com.travelex.framework.common.WebDriverWrapper;

@SuppressWarnings("unused")
public class TransactionAndCurrencyPage extends LoadableComponent<TransactionAndCurrencyPage>{
	public String delimiter ="\\|";
	Logger logger = Logger.getLogger(TransactionAndCurrencyPage.class.getName());
	WebDriver driver;
	WebDriverWrapper wrapper ;
	
	@FindBy(id = "searchTerm")
	WebElement txtBranch;
	
	@FindBy(id = "btnSearch")
	WebElement btnRetrieve;
	
	@FindBy(name = "branchId")
	WebElement lstBranchLocation;
	
	@FindBy(xpath = "//input[@value='  NEXT  ']")
	WebElement btnNext;
	
	@FindBy(xpath = "//*[contains(text(),'Spécialiste des fx') or contains(text(),'FX Specialist')]")
	WebElement lblHomePage;
	
	@FindBy(xpath = "//input[@value='1']")
	WebElement rbSale;
	
	@FindBy(xpath = "//input[@value='2']")
	WebElement rbPurchase;
	
	@FindBy(name = "productSetId")
	WebElement lstProductDetails;
	
	@FindBy(name = "productCode")
	WebElement lstCurrency;
	
	@FindBy(name = "domesticAmount")
	WebElement txtDomesticCurrency;
	
	@FindBy(name = "foreignAmount")
	WebElement txtForeignAmount;
	
	@FindBy(id = "btnQuoteAndView")
	WebElement btnQuote;
	
	@FindBy(name = "accountHoldingCustomer")
	WebElement chkConfirm;
	
	@FindBy(xpath = "//input[@value='Show Currency']")
	WebElement btnShowCurrency;
	
	@FindBy(xpath = "//input[@value='Clear Fields']")
	WebElement btnClearFields;
	
	@FindBy(id = "btnSubmitAddToOrder")
	WebElement btnAddToOrder;
	
	@FindBy(xpath = "//input[@value='Confirm Order' or @value='Confirmer La Commande']")
	WebElement btnConfirm;
	
	@FindBy(xpath = "//*[contains(text(),'En ligne') or contains(text(),'Online')]")
	WebElement lnkOnline;
	
	@FindBy(xpath = "//*[contains(text(),'Onsite') or contains(text(),'En stock')]")
	WebElement lnkOnsite;
	
	@FindBy(xpath = "//*contains(text(),'Wholesale') or contains(text(),'En gros')]")
	WebElement lnkWholeSale;
	
	public TransactionAndCurrencyPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}
	
	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(lblHomePage, 5000);
		if(lblHomePage.isDisplayed()){
			isPageLoaded = true;
		}
	}
	
	@Override
	public void load(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	public void selectBranch(String configBranchSelection, String branchName, String branchLocation) throws InterruptedException{
		if(!configBranchSelection.isEmpty()){
			txtBranch.sendKeys(branchName);
			btnRetrieve.click();
			Select branchLocationList = new Select(lstBranchLocation);
			branchLocationList.selectByVisibleText(branchLocation);
			btnNext.click();
			wrapper.waitForLoaderInvisibility();
		}			
	}
	
	public void submitTransactionAndCurrDetails(HashMap<String,String> scenarioTestData) {
		if(!scenarioTestData.get("ConfigConfirmBtn").equalsIgnoreCase("NA")){
			btnConfirm.click();
			Log.message("Clicked on Confirm Button");
			wrapper.waitForLoaderInvisibility();
		}		
	}

	
	public void enterTransAndCurrDetails(HashMap<String,String> scenarioTestData) throws InterruptedException{
		switch (scenarioTestData.get("TransactionType").toUpperCase()) {
		case "PURCHASE":
				rbPurchase.click();
				Log.message("Clicked on PURCHASE Radio Button");
				wrapper.waitForLoaderInvisibility();
			break;
			
		case "SALE":
				rbSale.click();
				Log.message("Clicked on SALE Radio Button");
				wrapper.waitForLoaderInvisibility();
			break;

		default:
			break;
		}
		
	 	String[] currencies= scenarioTestData.get("Currency").split(delimiter);
	 	String[] domesticAmountList = scenarioTestData.get("DomesticAmount").split(delimiter);
	 	String[] foreignAmountList= scenarioTestData.get("ForeignAmount").split(delimiter);
	 	
	 	if(!scenarioTestData.get("ConfigConfirmCheckBox").equalsIgnoreCase("NA")){
 			chkConfirm.click();
 			Log.message("Clicked on Confirm CheckBox");
 			wrapper.waitForLoaderInvisibility();
 		}
	 	
	 	for(int i=0; i<currencies.length;i++){
	 		Select productDetailsList = new Select(lstProductDetails);
	 		productDetailsList.selectByVisibleText(scenarioTestData.get("ProductType"));
	 		Log.message("Product Selected is : "+scenarioTestData.get("ProductType"));
	 		Select currencyList = new Select(lstCurrency);
	 		currencyList.selectByVisibleText(currencies[i]);
	 		Log.message("Currency Selected is : "+currencies[i]);
	 		txtForeignAmount.sendKeys(foreignAmountList[i]);
	 		Log.message("Entered Foreign Amount is : "+foreignAmountList[i]);
	 		if(!scenarioTestData.get("ConfigQuoteAndViewBtn").equalsIgnoreCase("NA")){
	 			btnQuote.click();
	 			Log.message("Clicked on Quote And View Button");
	 		}
	 		wrapper.waitForLoaderInvisibility();
	 		if(!scenarioTestData.get("ConfigAddToOrder").equalsIgnoreCase("NA")){
	 			btnAddToOrder.click();
	 			Log.message("Clicked on Add To Order Button");
	 		}
	 		wrapper.waitForLoaderInvisibility();
	 		if(!scenarioTestData.get("ConfigAlert").equalsIgnoreCase("Yes")){
	 			wrapper.dismissAlert(1000);
	 			Log.message("Clicked on Dismiss Alert");
	 		}else{
	 			wrapper.acceptAlert(1000);
	 			Log.message("Clicked on Accept Alert");
	 		}
	 		wrapper.waitForLoaderInvisibility();
	 	}
	}
	
	public void enterTransactionAndCurrDetails(HashMap<String,String> scenarioTestData) throws Throwable{
		enterTransAndCurrDetails(scenarioTestData);
		submitTransactionAndCurrDetails(scenarioTestData);
	}
	
	public CustomerDetailsPage customerDetailsPage() {
		return new CustomerDetailsPage(driver).get();
	}
	
}


