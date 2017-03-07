package com.travelex.nam.pages;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.travelex.framework.common.WebDriverWrapper;


@SuppressWarnings("unused")
public class HomePage extends LoadableComponent<HomePage> {
	
	WebDriver driver;
	
	WebDriverWrapper wrapper ;
	private int timeOutPeriod = 30;
	private int waitTime=300;
	
	@FindBy(xpath = "//a[contains(text(),'Search Order')]")
	WebElement lnkSearchOrder;
	
	@FindBy(id = "searchTerm")
	WebElement txtBranch;
	
	@FindBy(id = "btnSearch")
	WebElement btnRetrieve;
	
	@FindBy(name = "branchId")
	WebElement lstBranchLocation;
	
	@FindBy(xpath = "//input[@value='  NEXT  ']")
	WebElement btnNext;
	
	@FindBy(xpath = "//*[contains(text(),'En ligne') or contains(text(),'Online')]")
	WebElement lnkOnline;
	
	@FindBy(xpath = "//*[contains(text(),'Onsite') or contains(text(),'En stock')]")
	WebElement lnkOnsite;
	
	@FindBy(xpath = "//*[contains(text(),'Wholesale') or contains(text(),'En gros')]")
	WebElement lnkWholeSale;
	
	@FindBy(css = "a#nav_off[href*='Logout']")
	WebElement lnkLogout;
	
	
	public HomePage(WebDriver driver) {
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
	
	/**
	 *  Navigate To Search Order page 
	 * 
	 * @return null
	 */
	
	public SearchOrderPage clickSearchOrderLink() {
		lnkSearchOrder.click();
		return new SearchOrderPage(driver).get();
	}
	
	public TransactionAndCurrencyPage selectOrderType(String orderType){
		
		switch (orderType.toUpperCase()) {
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
			wrapper.waitForLoaderInvisibility(waitTime);
			return new TransactionAndCurrencyPage(driver).get();
		}
	
	public void selectBranchLocation(String branchLocation){		
		if(!branchLocation.equalsIgnoreCase("NA")){
			btnRetrive();
			wrapper.waitForLoaderInvisibility(waitTime);
			Select branchLocationList = new Select(lstBranchLocation);
			branchLocationList.selectByVisibleText(branchLocation);
			wrapper.waitForLoaderInvisibility(waitTime);
			nextButtonClick();
		}		
		wrapper.waitForLoaderInvisibility(waitTime);		
	}
	
	public void btnRetrive(){
		btnRetrieve.click();
		wrapper.waitForLoaderInvisibility(waitTime);
	}
	
	public void nextButtonClick() {
		btnNext.click();
		wrapper.waitForLoaderInvisibility(waitTime);
	}
	
	
}
