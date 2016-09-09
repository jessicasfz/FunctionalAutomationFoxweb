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
import org.openqa.selenium.support.ui.*;

import com.travelex.framework.common.Log;
import com.travelex.framework.common.WebDriverWrapper;


@SuppressWarnings("unused")
public class LoginPage extends LoadableComponent<LoginPage>  {
	
	static Logger logger = Logger.getLogger(LoginPage.class.getName());
	WebDriverWrapper wrapper ;
	WebDriver driver;
	
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
	
	public LoginPage(WebDriver driver, String url){
		this.driver = driver;
		this.driver.get(url);
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}
	
	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(txtUserId, 5000);
		if(txtUserId.isDisplayed()){
			isPageLoaded = true;
		}
	}
	
	@Override
	public void load(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		
	public HomePage clickLogin(HashMap<String,String> saleOrderData) throws WebDriverException{
		txtUserId.sendKeys(saleOrderData.get("LoginID"));
		Log.message("UserId entered is: "+saleOrderData.get("LoginID"));
		txtPassword.sendKeys(saleOrderData.get("Password"));
		Log.message("Password entered is: "+saleOrderData.get("Password"));
		btnEnter.click();
		Log.event("Clicked on login button");
		return new HomePage(driver).get();
		
	}

	
}
