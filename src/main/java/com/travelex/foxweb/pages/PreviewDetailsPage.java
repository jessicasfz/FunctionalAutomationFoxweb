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
public class PreviewDetailsPage extends LoadableComponent<PreviewDetailsPage>{	

	private WebDriver driver;

	private WebDriverWrapper wrapper ;
	private int timeOutPeriod = 3000;
	private int waitTime=300;

	@FindBy(css="input[type='radio'][value='0']")
	WebElement rdbNotSuspicious;

	@FindBy(css="input[type='radio'][value='1']")
	WebElement rdbSuspicious;
	
	@FindBy(id= "confirm_sell")
	WebElement btnOrderConfirm;
	
	@FindBy(css=".hmhead")
	WebElement previewPageTitle;
	
	@FindBy(css="input[type='radio']")
	WebElement spanSuspTrans;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}


	public PreviewDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(previewPageTitle, timeOutPeriod);
		String titlePreviewPage = previewPageTitle.getText();
		if(titlePreviewPage.matches("Preview Your Order")){
			isPageLoaded = true;
		}
	}



	public boolean isPreviewOrderPageLoaded(){
		boolean isPreviewOrderPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(previewPageTitle, timeOutPeriod);
		String titlePreviewPage = previewPageTitle.getText();
		System.out.println(titlePreviewPage);
		if(titlePreviewPage.matches("Preview Your Order")){
			isPreviewOrderPageLoaded = true;
		}
		return isPreviewOrderPageLoaded;
	}


	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public void selectSuspiciousTransaction(String valueSuspiciousTransaction ) {

		wrapper.waitForElementToBeDisplayed(rdbNotSuspicious, 60);

		if(valueSuspiciousTransaction.matches("No"))
		{
			rdbNotSuspicious.click();

			Boolean status = rdbNotSuspicious.isEnabled();
			System.out.println("Status is" +status );
			btnOrderConfirm.click();
		}
		else
		{
			rdbSuspicious.click();
			btnOrderConfirm.click();
		}

	} 

	public OrderConfirmationPage clickOnOrderConfirmationButton() {
		
		
		try {
			if(spanSuspTrans.isDisplayed())
			{
				selectSuspiciousTransaction(MasterDataReader.foxwebOrderDetails.get("SuspiciousTransactionType"));
			}
			
			
		} catch (Exception e) {
			btnOrderConfirm.click();
			e.printStackTrace();
		}
		return new OrderConfirmationPage(driver).get();

	}

}
