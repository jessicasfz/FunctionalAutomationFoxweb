package com.travelex.nam.pages;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;
import org.testng.Reporter;


@SuppressWarnings("unused")
public class HomePage extends LoadableComponent<HomePage> {
	
	static Logger logger = Logger.getLogger(HomePage.class.getName());
	WebDriver driver;
	
	@FindBy(xpath = "//a[contains(text(),'Online')]")
	WebElement lnkOnline;
	
	@FindBy(xpath = "//a[contains(text(),'Search Order')]")
	WebElement lnkSearchOrder;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void isLoaded(){
		boolean isPageLoaded = false;
		if(lnkOnline.isDisplayed()){
			isPageLoaded = true;
		}
		//return isPageLoaded; 
	}
	
	public void load(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SearchOrderPage clickSearchOrderLink() {
		lnkSearchOrder.click();
		return new SearchOrderPage(driver).get();
	}

}
