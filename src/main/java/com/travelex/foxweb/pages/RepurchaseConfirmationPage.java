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
public class RepurchaseConfirmationPage extends LoadableComponent<RepurchaseConfirmationPage>{	

	private WebDriver driver;
	
	public static String OrderNo;
	
	public static String txtOrderStatusSt;

	private WebDriverWrapper wrapper ;
	private int timeOutPeriod = 3000;
	private int waitTime=300;

	@FindBy(css="div.hmhead")
	WebElement labelRepurchaseConfirmation;

	@FindBy(css = "div.text4")
	WebElement txtOrderConfirmation;
	
	@FindBy(xpath="//table[@class='order-item-list']/tbody/tr[2]/td[10]")
	WebElement statusOrder;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}


	public RepurchaseConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(labelRepurchaseConfirmation, timeOutPeriod);
		if(labelRepurchaseConfirmation.isDisplayed()){
			isPageLoaded = true;
		}
	}



	public boolean isRepurchaseConfirmationPageLoaded(){
		boolean isRepurchaseConfirmationPageLoaded = false;
		if(labelRepurchaseConfirmation.isDisplayed()){
			isRepurchaseConfirmationPageLoaded = true;
		}
		return isRepurchaseConfirmationPageLoaded;
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public boolean verifyRepurchaseConfirmationSuccessMessageIsDisplayed() {
		boolean verifyRepurchaseConfirmationSuccessMessageIsDisplayed =false;

		wrapper.waitForElementToBeDisplayed(txtOrderConfirmation, 30);
		if(txtOrderConfirmation.isDisplayed())
		{
			verifyRepurchaseConfirmationSuccessMessageIsDisplayed = true;
		}	
		return verifyRepurchaseConfirmationSuccessMessageIsDisplayed;

	}
	
	public boolean verifyRepurchaseNumberIsGenerated(){
		
		
		String Orderno = driver.findElement(By.xpath("//span[@class='text3']")).getText();
		System.out.println(Orderno);
		OrderNo = Orderno;
		
		WebElement btnnext = driver.findElement(By.xpath("//a[@class='next']"));
		btnnext.click();
		
		txtOrderStatusSt = statusOrder.getText();
		
		boolean orderNoIsGenerated =false;
		if(!Orderno.isEmpty())
			
			orderNoIsGenerated = true;
		return orderNoIsGenerated;
		
		
	}

}
