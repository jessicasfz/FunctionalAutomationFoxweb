package com.travelex.pluto.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.travelex.framework.common.WebDriverWrapper;

public class TransactionPage {
	
	WebDriverWrapper wrapper ;
	WebDriver driver;
	private int timeOutPeriod = 3000;
	
	
	
	@FindBy(id = "cboPurchaseType")
	WebElement listPurchaseType;
	
	@FindBy(id = "txtCompanyAutocomplete")
	WebElement txtCompanyName;
		
	@FindBy(id = "OrderTypes")
	WebElement listCustomerType;
	
	@FindBy(id = "DeliveryMethods")
	WebElement listDeliveryMethods;
	
	@FindBy(name = "btnBranchMatch")
	WebElement btnSearchName;
		
	@FindBy(name = "SiteProductTypes")
	WebElement listProductType;
	
	@FindBy(name = "SiteProducts")
	WebElement listCurrencyCards;
	
	@FindBy(name = "txtExchangeRate")
	WebElement txtRate;
	
	@FindBy(name = "txtInverseExchangeRate")
	WebElement txtInverseRate;
	
	@FindBy(name = "txtForeignValue")
	WebElement txtForeignAmount;
	
	@FindBy(name = "txtLocalValue")
	WebElement txtDomesticAmount;		
	
	@FindBy(name = "txtServiceFee")
	WebElement txtServiceFee;
	
	@FindBy(name = "btnDenom")
	WebElement btnDenom;
	
	@FindBy(name = "btnCurrencyDelete")
	WebElement btnDelete;
		
	@FindBy(name = "btnNext")
	WebElement btnNext;
	
	@FindBy(xpath = "//input[@value='Preview Order']")
	WebElement btnPreviewOrder;
	
	@FindBy(xpath = "//input[@value='Add Currency']")
	WebElement btnAddCurrency;
	
	@FindBy(xpath = "//input[@value='Start Again']")
	WebElement btnStartAgain;
	
	@FindBy(xpath = "//input[@value='Calculate']")
	WebElement btnCalculate;
		
	@FindBy(name = "txtMatch")
	WebElement txtbranchLocation;
	
	@FindBy(name = "btnSubmit")
	WebElement btnFindMatch;
	
	@FindBy(name = "btnSelect")
	WebElement btnSelect;
	
	@FindBy(name = "btnClose")
	WebElement btnClose;
	
	
	public TransactionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}
	
	@SuppressWarnings("unused")
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(txtCompanyName, timeOutPeriod);
		if(txtCompanyName.isDisplayed()){
			isPageLoaded = true;
		}
	}
	
	public void load(){
		try {
			wrapper.waitForElementToBeDisplayed(txtCompanyName, timeOutPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void selectTypeOfPurchase(String transactionType,String purchaseType){
		if(transactionType.equalsIgnoreCase("PurchaseOrder")){			
			Select listPurchaseTypeList = new Select(listPurchaseType);
			listPurchaseTypeList.selectByVisibleText(purchaseType);
			btnNext.click();
		}		
	}
	
	public void selectCustomerTypeAndDeliveryType(String customerType,String deliveryMethod){
		if(!customerType.equalsIgnoreCase("NA")){			
			Select listCustomerTypeList = new Select(listCustomerType);
			listCustomerTypeList.selectByVisibleText(customerType);
		}
		
		if(!deliveryMethod.equalsIgnoreCase("NA")){			
			Select listDeliveryMethodsList = new Select(listDeliveryMethods);
			listDeliveryMethodsList.selectByVisibleText(deliveryMethod);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
