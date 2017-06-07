package com.travelex.pluto.pages;

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
	private int timeOutPeriod = 30;
	
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
	
	@FindBy(name = "btnNext")
	WebElement btnNext;
	
	
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
	
	public void clickOnNextBtn(){
		btnNext.click();	
}
	
	public void enterCustomerDeliveryDetails(String title, String fName, String lName, String collectionDate,String deliveryType,String awayBranchLocation,String address1,String address2,String state,String country,String zipcode,String homeTelephone) throws InterruptedException {
		wrapper.waitForElementToBeDisplayed(listTitle, timeOutPeriod);
		Select select = new Select(listTitle);
		select.selectByValue(title);
		wrapper.sendKeysUsingJavaScript(txtFirstName, fName);
		wrapper.sendKeysUsingJavaScript(txtLastName, lName);		
			if(!awayBranchLocation.equalsIgnoreCase("NA")){
				TransactionPage transactionPage = new TransactionPage(driver);
				transactionPage.clickOnSearchAndSelectBranchLocation(awayBranchLocation);
			}
		
		select = new Select(listCollectionDateAndDeliveryDate);
		select.selectByIndex(Integer.parseInt(collectionDate));
		
		if(deliveryType.contains("Home Delivery")){
			wrapper.sendKeysUsingJavaScript(txtAddress1, address1);
			wrapper.sendKeysUsingJavaScript(txtAddress2, address2);
			
			txtState.sendKeys(state);
			wrapper.waitForElementToBeDisplayed(autoSuggest, timeOutPeriod);
			autoSuggest.click();
			
			select = new Select(listCountry);
			select.selectByVisibleText(country);
			
			wrapper.sendKeysUsingJavaScript(txtZipcode, zipcode);
			wrapper.sendKeysUsingJavaScript(txtHomeTelephoneNo, homeTelephone);
			
		}	
	}
	
}
