package com.travelex.nam.pages;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.travelex.framework.common.WebDriverWrapper;

@SuppressWarnings("unused")
public class TransactionAndCurrencyPage extends LoadableComponent<TransactionAndCurrencyPage>{
	public String delimiter ="\\|";
	Logger logger = Logger.getLogger(TransactionAndCurrencyPage.class.getName());
	WebDriver driver;
	
	
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
	}
	
	@Override
	public void isLoaded(){
		
	}
	
	@Override
	public void load(){
		
	}
	
	public void navigateToTransactionAndCurrencyPage(String createOrderType, String configOrderLink, String branchName, String branchLocation){
		if(txtBranch.isDisplayed()){
			txtBranch.sendKeys(branchName);
			btnRetrieve.click();
			Select branchLocationList = new Select(lstBranchLocation);
			branchLocationList.selectByVisibleText(branchLocation);
			btnNext.click();
		}
		if(!configOrderLink.isEmpty())
		switch (createOrderType.toUpperCase()) {
		case "ONLINE":
			lnkOnline.click();
			break;
		case "ONSITE":
			lnkOnsite.click();
			break;
		case "WHOLESALE":
			lnkWholeSale.click();
			break;

		default:
			break;
		}
			
	}
	
	public void selectBranch(String configBranchSelection, String branchName, String branchLocation) throws InterruptedException{
		if(!configBranchSelection.isEmpty()){
			txtBranch.sendKeys(branchName);
			btnRetrieve.click();
			Select branchLocationList = new Select(lstBranchLocation);
			branchLocationList.selectByVisibleText(branchLocation);
			btnNext.click();
		}
			
	}
	
	public void submitTransactionAndCurrDetails() {
			btnConfirm.click();
	}

	
	public void enterTransAndCurrDetails(String configConfirmCheckBox, String transactionType, String productType, String currency,
			String domesticAmount,String foreignAmount) throws InterruptedException{
		
		switch (transactionType.toUpperCase()) {
		case "PURCHASE":
				rbPurchase.click();
			break;
			
		case "SALE":
				rbSale.click();
			break;

		default:
			break;
		}
		
	 	String[] currencies= currency.split(delimiter);
	 	String[] domesticAmountList = domesticAmount.split(delimiter);
	 	String[] foreignAmountList= foreignAmount.split(delimiter);
	 	
	 	for(int i=0; i<currencies.length;i++){
	 		Select productDetailsList = new Select(lstProductDetails);
	 		productDetailsList.selectByVisibleText(productType);
	 		
	 		Select currencyList = new Select(lstCurrency);
	 		currencyList.selectByVisibleText(currencies[i]);
	 		
	 		if(!configConfirmCheckBox.isEmpty()){
	 			
	 			chkConfirm.click();
	 		}
	 		
	 		txtForeignAmount.sendKeys(foreignAmountList[i]);
	 		btnQuote.click();
	 		btnAddToOrder.click();
	 		
	 		String msg = new WebDriverWrapper(driver).dismissAlert(5000);	
	 	}
		
	}
	
	public void enterTransactionAndCurrDetails(String configConfirmCheckBox,String transactionType, String productType, String currency,
			String domesticAmount, String foreignAmount, String branchName, String branchLocation) throws Throwable{
		enterTransAndCurrDetails(configConfirmCheckBox, transactionType, productType, currency, domesticAmount, foreignAmount);
		submitTransactionAndCurrDetails();
		
	}
	
}


