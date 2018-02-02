package com.travelex.foxweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.travelex.framework.common.WebDriverWrapper;


@SuppressWarnings("unused")
public class FoxwebHomePage extends LoadableComponent<FoxwebHomePage> {

	WebDriver driver;

	WebDriverWrapper wrapper ;
	private int timeOutPeriod = 30;
	private int waitTime=300;

	@FindBy(linkText = "Sign Out")
	WebElement lnkLogout;

	@FindBy(linkText = "Quote")
	WebElement lnkQuote;

	@FindBy(linkText = "Order")
	WebElement lnkOrder;

	@FindBy(name= "product")
	WebElement drpdownselProduct;
	
	@FindBy(id="menu_CSCproc_prepareview")
	WebElement lnkProcessTrans;
	
	@FindBy(linkText="Repurchase")
	WebElement lnkRepurchase;
	
	@FindBy(name="currency")
	WebElement drpdownselCurrency;
	
	@FindBy(id="main_next")
	WebElement btnNext;

	@FindBy(id="next")
	WebElement btnNext1;
	
	@FindBy(linkText = "Administration")
	WebElement lnkAdministration;
	
	@FindBy(className= "button")
	WebElement btnWorkPlace; //added by jachakN for workplace exception

	public FoxwebHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(lnkLogout, timeOutPeriod);
		if(lnkLogout.isDisplayed()){
			isPageLoaded = true;
		}
	}

	public void load(){
		try {
			wrapper.waitForElementToBeDisplayed(lnkLogout, timeOutPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isHomePageLoaded(){
		boolean isHomePageLoaded = false;
		if(lnkLogout.isDisplayed()){
			isHomePageLoaded = true;
		}
		return isHomePageLoaded;
	}

	public  QuoteDetailsPage navigateToGetQuotePAge(String currencytype) {

		lnkQuote.click();
		Select objSelPdct = new Select(drpdownselProduct);
		objSelPdct.selectByVisibleText(currencytype);
		return new QuoteDetailsPage(driver).get();
	}

	public OrderDetailsPage navigateToOrderPage(String productType) {
		lnkOrder.click();
		driver.findElement(By.className("next")).click();
		Select objSelPdct = new Select(drpdownselProduct);
		objSelPdct.selectByVisibleText(productType);

		return new OrderDetailsPage(driver).get();
	}

	public ProcessTransactionPage navigateToProcessTransactionPage() {
		
		lnkProcessTrans.click();
		return new ProcessTransactionPage(driver).get();
		
	}

	public RepurchaseDetailsPage navigateToRepurchasePage(String product,String currency) {
		lnkRepurchase.click();
		
		btnNext.click();
		
		Select selProduct = new Select(drpdownselProduct);
		selProduct.selectByVisibleText(product);
		
		Select selCurrency = new Select(drpdownselCurrency);
		selCurrency.selectByVisibleText(currency);
				
		
		return new RepurchaseDetailsPage(driver).get();
	}

	public UserMaintenanceAdminPage navigateToUserCreationAdminPage() {
		try {
			if(btnWorkPlace.isDisplayed()){
				
				btnWorkPlace.click();
				lnkAdministration.click();
			}
		} catch (Exception e) {
			
			lnkAdministration.click();
		}
		
			
			
		
		
		
		return new UserMaintenanceAdminPage(driver).get();
	}
	
	/**
	 * @author jachakN
	 * For Creating User
	 */

	
}
