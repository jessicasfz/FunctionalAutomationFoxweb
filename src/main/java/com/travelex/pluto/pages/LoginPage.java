package com.travelex.pluto.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.travelex.framework.common.WebDriverWrapper;

@SuppressWarnings("unused")
public class LoginPage extends LoadableComponent<LoginPage>{
	
	WebDriverWrapper wrapper ;
	WebDriver driver;
	private int timeOutPeriod = 30;
		
	@FindBy(name = "txtuserid")
	WebElement txtuserid;
		
	@FindBy(name = "txtPassword")
	WebElement txtPassword;
	
	@FindBy(id = "txtCompanyAutocomplete")
	WebElement txtCompanyAutocomplete;
	
	@FindBy(name = "btnLogon")
	WebElement btnLogon;
	
	@FindBy(css = "ul li a")
	WebElement autoSuggest;
	
	public LoginPage(WebDriver driver,String url,String browser){
		this.driver = driver;
		/*if(browser.equalsIgnoreCase("IE")){
			if(!driver.getTitle().contains("Certificate Error")) Assert.fail("Failed to launch the browser");
			driver.get("javascript:document.getElementById('overrridelink').click();");
		}*/
		driver.manage().window().maximize();
		driver.get(url);
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(txtuserid, timeOutPeriod);
		if(txtuserid.isDisplayed()){
			isPageLoaded = true;
		}
	}
	
	@Override
	public void load(){
		try {
			wrapper.waitForElementToBeDisplayed(txtuserid, timeOutPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public HomePage clickLogin(String loginID, String password,String company) throws WebDriverException{
		txtuserid.clear();
		txtuserid.sendKeys(loginID);
		txtPassword.clear();
		txtPassword.sendKeys(password);
		txtCompanyAutocomplete.clear();
		txtCompanyAutocomplete.sendKeys(company);
		wrapper.waitForElementToBeDisplayed(autoSuggest, timeOutPeriod);
		autoSuggest.click();
		btnLogon.click();
		return new HomePage(driver).get();
		
	} 
}
