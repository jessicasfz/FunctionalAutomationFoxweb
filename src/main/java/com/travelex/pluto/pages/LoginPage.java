package com.travelex.pluto.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.travelex.framework.common.WebDriverWrapper;

@SuppressWarnings("unused")
public class LoginPage extends LoadableComponent<LoginPage>{
	
	WebDriverWrapper wrapper ;
	WebDriver driver;
	private int timeOutPeriod = 3000;
		
	@FindBy(name = "txtuserid")
	WebElement txtuserid;
		
	@FindBy(name = "txtPassword")
	WebElement txtPassword;
	
	@FindBy(id = "txtCompanyAutocomplete")
	WebElement txtCompanyAutocomplete;
	
	@FindBy(name = "btnLogon")
	WebElement btnLogon;

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
		btnLogon.click();
		return new HomePage(driver).get();
		
	} 
}
