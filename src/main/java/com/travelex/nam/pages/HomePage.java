package com.travelex.nam.pages;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.log4j.Logger;
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

import com.travelex.framework.common.Log;
import com.travelex.framework.common.WebDriverWrapper;


@SuppressWarnings("unused")
public class HomePage extends LoadableComponent<HomePage> {
	
	static Logger logger = Logger.getLogger(HomePage.class.getName());
	WebDriver driver;
	
	WebDriverWrapper wrapper ;
	
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
	
	@FindBy(xpath = "//*[contains(text(),'Spécialiste des fx') or contains(text(),'FX Specialist')]")
	WebElement homePage;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}
	
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(homePage, 5000);
		if(homePage.isDisplayed()){
			isPageLoaded = true;
		}
	}
	
	public void load(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public SearchOrderPage clickSearchOrderLink() {
		lnkSearchOrder.click();
		return new SearchOrderPage(driver).get();
	}
	
	public TransactionAndCurrencyPage navigateToTransactionPage(HashMap<String,String> saleOrderData) throws WebDriverException{
			if(txtBranch.isDisplayed()){
				if(!saleOrderData.get("BranchName").equalsIgnoreCase("NA")){
					txtBranch.sendKeys(saleOrderData.get("BranchName"));
					Log.message("Branch Name Entered in Text Field"+saleOrderData.get("BranchName"));
				}
				btnRetrieve.click();
				wrapper.waitForLoaderInvisibility();
				Log.message("Clicked on Retrieve Button");
				Select branchLocationList = new Select(lstBranchLocation);
				branchLocationList.selectByVisibleText(saleOrderData.get("BranchLocation"));
				Log.message("Branch Location Selected is:"+saleOrderData.get("BranchLocation"));
				btnNext.click();
				wrapper.waitForLoaderInvisibility();
				Log.message("Clicked on Next Button");
			}
		
		if(!saleOrderData.get("ConfigOrderLink").equalsIgnoreCase("NA"))
		switch (saleOrderData.get("CreateOrderType").toUpperCase()) {
		case "ONLINE":
			lnkOnline.click();
			Log.message("Clicked On ONLINE Link");
			break;
		case "ONSITE":
			lnkOnsite.click();
			Log.message("Clicked On ONSITE Link");
			break;
		case "WHOLESALE":
			lnkWholeSale.click();
			Log.message("Clicked On WHOLESALE Link");
			break;

		default:
			break;
		}
	
		return new TransactionAndCurrencyPage(driver).get();
		
	}

}
