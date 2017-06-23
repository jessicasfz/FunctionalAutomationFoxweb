package com.travelex.foxweb.pages;

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
import com.travelex.stepDefinitions.MasterDataReader;

import cucumber.api.Scenario;

@SuppressWarnings("unused")
public class FoxwebLoginPage extends LoadableComponent<FoxwebLoginPage>  {
	
	WebDriverWrapper wrapper ;
	WebDriver driver;
	private int timeOutPeriod = 3000;
	
	
	@FindBy(css = "input[id='username']")
	WebElement txtUserId;
	
	@FindBy(css="input[id='entitycode']")
	WebElement txtAccountCode;
	
	@FindBy(css="input[id='password']")
	WebElement txtPassword;
	
	@FindBy(css = "button[type='submit']")
	WebElement btnLogin;
	
	@FindBy(className = "medRedBold")
	WebElement errorMsg;

	
	//Modified code for IE compatibility : Ram Devalkar
	
	public FoxwebLoginPage(WebDriver driver, String url,String browser){
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
	 * @param accountcode
	 * @param password
	 * @return HomePage
	 * @throws WebDriverException
	 */
	
	public FoxwebHomePage clickLogin(String loginID,String accountcode, String password) throws WebDriverException{
		txtUserId.clear();
		txtUserId.sendKeys(loginID);
		txtAccountCode.clear();
		txtAccountCode.sendKeys(accountcode);
		txtPassword.clear();
		txtPassword.sendKeys(password);
		btnLogin.click();
		return new FoxwebHomePage(driver).get();
		
	}	
}
