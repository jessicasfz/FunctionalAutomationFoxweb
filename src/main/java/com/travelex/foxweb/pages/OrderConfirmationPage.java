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
public class OrderConfirmationPage extends LoadableComponent<OrderConfirmationPage>{	

	private WebDriver driver;
	
	public static String OrderNo;
	
	public static String txtOrderStatusSt;

	private WebDriverWrapper wrapper ;
	private int timeOutPeriod = 3000;
	private int waitTime=300;

	@FindBy(xpath="//h3[contains(.,'Order Confirmation')]")
	WebElement labelOrderConfirmation;

	@FindBy(xpath = "//td[contains(.,'Your Order Number is')]")
	WebElement txtOrderConfirmation;
	
	@FindBy(xpath="//table[@class='costingColor']/tbody/tr[2]/td/table[1]/tbody/tr[2]/td[10]")
	WebElement statusOrder;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}


	public OrderConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(labelOrderConfirmation, timeOutPeriod);
		if(labelOrderConfirmation.isDisplayed()){
			isPageLoaded = true;
		}
	}



	public boolean isOrderConfirmationPageLoaded(){
		boolean isOrderConfirmationPageLoaded = false;
		if(labelOrderConfirmation.isDisplayed()){
			isOrderConfirmationPageLoaded = true;
		}
		return isOrderConfirmationPageLoaded;
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public boolean verifyOrderConfirmationSuccessMessageIsDisplayed() {
		boolean verifyOrderConfirmationSuccessMessageIsDisplayed =false;

		wrapper.waitForElementToBeDisplayed(txtOrderConfirmation, 30);
		if(txtOrderConfirmation.isDisplayed())
		{
			verifyOrderConfirmationSuccessMessageIsDisplayed = true;
		}	
		return verifyOrderConfirmationSuccessMessageIsDisplayed;

	}
	
	public boolean verifyOrderNumberIsGenerated(){
		
		
		String Orderno = driver.findElement(By.xpath("//span[@class='text3']")).getText();
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
