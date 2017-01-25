package com.travelex.nam.pages;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import com.travelex.framework.common.WebDriverWrapper;

import cucumber.api.Scenario;


@SuppressWarnings("unused")
public class LoginPage extends LoadableComponent<LoginPage>  {
	
	WebDriverWrapper wrapper ;
	WebDriver driver;
	private int timeOutPeriod = 3000;
	
	@FindBy(xpath = "//input[@name='userId']")
	WebElement txtUserId;
	
	@FindBy(name = "password")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement btnEnter;
	
	@FindBy(name = "Close")
	WebElement btnVoltaClose;
	
	@FindBy(name = "Yes")
	WebElement btnVoltaYes;
	
	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	WebElement lnkLogout;
	
	//New RD
	
	@FindBy(className = "medRedBold")
	WebElement errorMsg;

	
	//Modified code for IE compatibility : Ram Devalkar
	public LoginPage(WebDriver driver, String url,String browser){
		this.driver = driver;
		this.driver.get(url);
		if(browser.equalsIgnoreCase("IE")){
			driver.manage().window().maximize();
			if(!driver.getTitle().contains("Certificate Error")) Assert.fail("Failed to launch the browser");
			driver.get("javascript:document.getElementById('overridelink').click();");
			
		}
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}
	
	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(txtUserId, timeOutPeriod);
		if(txtUserId.isDisplayed()){
			isPageLoaded = true;
		}
	}
	
	@Override
	public void load(){
		try {
			wrapper.waitForElementToBeDisplayed(txtUserId, timeOutPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Enters Login Details and Click on Submit Button 
	 *  	
	 * @param loginID
	 * @param password
	 * @return HomePage
	 * @throws WebDriverException
	 */
	
	public HomePage clickLogin(String loginID, String password) throws WebDriverException{
		txtUserId.sendKeys(loginID);
		txtPassword.sendKeys(password);
		btnEnter.click();
		return new HomePage(driver).get();
		
	}	
}
