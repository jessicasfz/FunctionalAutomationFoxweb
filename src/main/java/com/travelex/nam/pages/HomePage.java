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
	
	//static Logger logger = Logger.getLogger(HomePage.class.getName());
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
	
	@FindBy(xpath = "//*contains(text(),'Wholesale') or contains(text(),'En gros')]")
	WebElement lnkWholeSale;
	
	@FindBy(xpath = "//td/a[contains(text(),'Logout')]")
	WebElement homePage;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}
	
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(homePage, timeOutPeriod);
		if(homePage.isDisplayed()){
			isPageLoaded = true;
		}
	}
	
	public void load(){
		try {
			wrapper.waitForElementToBeDisplayed(homePage, timeOutPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isHomePageLoaded(){
		boolean isHomePageLoaded = false;
		if(homePage.isDisplayed()){
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
	
	

}
