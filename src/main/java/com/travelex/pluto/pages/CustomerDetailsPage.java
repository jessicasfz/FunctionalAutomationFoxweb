package com.travelex.pluto.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.travelex.framework.common.WebDriverWrapper;

public class CustomerDetailsPage extends LoadableComponent<CustomerDetailsPage>{
	
	WebDriverWrapper wrapper ;
	WebDriver driver;
	private int timeOutPeriod = 3000;
	public JavascriptExecutor myExecutor;
	
	@FindBy(name = "cmbTitle")
	WebElement listTitle;
	
	@FindBy(name = "txtShippingFirstName")
	WebElement txtFirstName;
	
	@FindBy(name = "txtShippingSurName")
	WebElement txtLastName;
		
	@FindBy(name = "txtAddress1")
	WebElement txtAddress1;
	
	@FindBy(name = "txtAddress2")
	WebElement txtAddress2;
	
	@FindBy(name = "txtProvince")
	WebElement txtState;
	
	@FindBy(name = "cmbCountry")
	WebElement listCountry;
	
	@FindBy(name = "txtPostCode")
	WebElement txtZipcode;
	
	@FindBy(name = "txtHomeTelephone")
	WebElement txtHomeTelephoneNo;
	
	@FindBy(name = "btnBranchMatch")
	WebElement btnSearchName;
	
	@FindBy(name = "cmbCollectionDate")
	WebElement listCollectionDateAndDeliveryDate;
	
	@FindBy(css = "ul li a")
	WebElement autoSuggest;
	
	
	public CustomerDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@SuppressWarnings("unused")
	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(txtFirstName, timeOutPeriod);
		if(txtFirstName.isDisplayed()){
			isPageLoaded = true;
		}
	}

	@Override
	public void load(){
		try {
			wrapper.waitForElementToBeDisplayed(txtFirstName, timeOutPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void enterCustomerDeliveryDetails(String title, String fName, String lName, String collectionDate,String deliveryType,String awayBranchLocation,String address1,String address2,String state,String country,String zipcode,String homeTelephone) {
		wrapper.waitForElementToBeDisplayed(listTitle, timeOutPeriod);
		myExecutor = ((JavascriptExecutor) driver);
		Select select = new Select(listTitle);
		select.selectByValue(title);		
		myExecutor.executeScript("arguments[0].value='"+fName+"';", txtFirstName);
		myExecutor.executeScript("arguments[0].value='"+lName+"';", txtLastName);
		
		if(deliveryType.contains("Away Branch")){
			if(!awayBranchLocation.equalsIgnoreCase("NA")){
				TransactionPage transactionPage = new TransactionPage(driver);
				transactionPage.clickOnSearchAndSelectBranchLocation(awayBranchLocation);
			}
			
		}
		
		select = new Select(listCollectionDateAndDeliveryDate);
		select.selectByIndex(Integer.parseInt(collectionDate));
		
		if(deliveryType.contains("Home Delivery")){
			myExecutor.executeScript("arguments[0].value='"+address1+"';", txtAddress1);
			myExecutor.executeScript("arguments[0].value='"+address2+"';", txtAddress2);
			
			txtState.sendKeys(state);
			wrapper.waitForElementToBeDisplayed(autoSuggest, timeOutPeriod);
			autoSuggest.click();
			
			select = new Select(listCountry);
			select.selectByVisibleText(country);
			
			myExecutor.executeScript("arguments[0].value='"+zipcode+"';", txtZipcode);
			myExecutor.executeScript("arguments[0].value='"+homeTelephone+"';", txtHomeTelephoneNo);
			
		}	
	}
	
}
