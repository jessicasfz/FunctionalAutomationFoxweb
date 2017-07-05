package com.travelex.foxweb.pages;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.travelex.framework.common.ConfigurationProperties;
import com.travelex.framework.common.WebDriverWrapper;
import com.travelex.framework.utilities.RandomCodeGenerator;
import com.travelex.stepDefinitions.MasterDataReader;

@SuppressWarnings("unused")
public class QuoteDetailsPage extends LoadableComponent<QuoteDetailsPage>{	

	private WebDriver driver;

	private WebDriverWrapper wrapper ;
	private int timeOutPeriod = 3000;
	private int waitTime=300;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(name = "currency")
	WebElement selectForeignCurrency;

	@FindBy(name="forexAmt")
	WebElement txtForeignAmount;

	@FindBy(css="#orderitem>table>tbody>tr:nth-child(1)>td:nth-child(1)")
	WebElement labelQuote;

	@FindBy(id = "convert")
	WebElement btnConvert;

	@FindBy(name="next1")
	WebElement btnNext;




	public QuoteDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(labelQuote, timeOutPeriod);
		if(labelQuote.isDisplayed()){
			isPageLoaded = true;
		}
	}



	public boolean isQuoteDetailsPageLoaded(){
		boolean isQuoteDetailsPageLoaded = false;
		if(labelQuote.isDisplayed()){
			isQuoteDetailsPageLoaded = true;
		}
		return isQuoteDetailsPageLoaded;
	}

	public void enterQuoteDetails(String currency, String fgnamount) {
		// TODO Auto-generated method stub

		Select selFgnCurrency  = new Select(selectForeignCurrency);
		selFgnCurrency.selectByVisibleText(currency);

		txtForeignAmount.clear();
		txtForeignAmount.sendKeys(fgnamount);
		driver.switchTo().alert().accept();
	//	txtForeignAmount.sendKeys(fgnamount);

		txtForeignAmount.sendKeys(Keys.TAB);
		btnNext.click();
	}

	@Override
	protected void load() {

	}

	public boolean verifyQuoteDetailsisDisplayed() {
		boolean verifyQuoteDetailsisDisplayed =false;
		if(btnConvert.isDisplayed())
		{
			verifyQuoteDetailsisDisplayed = true;
		}	
		return verifyQuoteDetailsisDisplayed;
	}

}
