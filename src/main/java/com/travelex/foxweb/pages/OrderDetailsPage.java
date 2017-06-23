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
public class OrderDetailsPage extends LoadableComponent<OrderDetailsPage>{	

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
	
	@FindBy(id="next")
	WebElement btnNext1;

	@FindBy(css= "input[type=text][name=customer]")
	WebElement txtCustName;




	public OrderDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(txtCustName, timeOutPeriod);
		if(labelQuote.isDisplayed()){
			isPageLoaded = true;
		}
	}



	public boolean isOrderDetailsPageLoaded(){
		boolean isOrderDetailsPageLoaded = false;
		if(txtCustName.isDisplayed()){
			isOrderDetailsPageLoaded = true;
		}
		return isOrderDetailsPageLoaded;
	}

	public PreviewDetailsPage enterOrderDetails(String currency, String fgnamount, String custName) {
		// TODO Auto-generated method stub

		Select selFgnCurrency  = new Select(selectForeignCurrency);
		selFgnCurrency.selectByVisibleText(currency);

		txtForeignAmount.clear();

		driver.switchTo().alert().accept();
		txtForeignAmount.sendKeys(fgnamount);

		txtForeignAmount.sendKeys(Keys.TAB);
		txtCustName.clear();
		txtCustName.sendKeys(custName);

		btnNext.click();
		btnNext.click();
		btnNext1.click();

		return new PreviewDetailsPage(driver).get();



	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public boolean verifyQuoteDetailsisDisplayed() {
		// TODO Auto-generated method stub
		boolean verifyQuoteDetailsisDisplayed =false;


		if(btnConvert.isDisplayed())
		{
			verifyQuoteDetailsisDisplayed = true;
		}	
		return verifyQuoteDetailsisDisplayed;







	}

}
