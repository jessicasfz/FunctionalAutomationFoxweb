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
public class PreviewRepurchaseDetailsPage extends LoadableComponent<PreviewRepurchaseDetailsPage>{	

	private WebDriver driver;

	private WebDriverWrapper wrapper ;
	private int timeOutPeriod = 3000;
	private int waitTime=300;

	@FindBy(css="input[type='radio'][value='0']")
	WebElement rdbNotSuspicious;

	@FindBy(css="input[type='radio'][value='1']")
	WebElement rdbSuspicious;

	@FindBy(id= "confirm_buy")
	WebElement btnRepurchaseConfirm;

	@FindBy(css="input[type='radio']")
	WebElement spanSuspTrans;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}


	public PreviewRepurchaseDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(btnRepurchaseConfirm, timeOutPeriod);
		if(btnRepurchaseConfirm.isDisplayed()){
			isPageLoaded = true;
		}
	}



	public boolean isPreviewRepurchasePageLoaded(){
		boolean isPreviewRepurchasePageLoaded = false;
		wrapper.waitForElementToBeDisplayed(btnRepurchaseConfirm, timeOutPeriod);
		if(btnRepurchaseConfirm.isDisplayed()){
			isPreviewRepurchasePageLoaded = true;
		}
		return isPreviewRepurchasePageLoaded;
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
			btnRepurchaseConfirm.click();
		}
		else
		{
			rdbSuspicious.click();
			btnRepurchaseConfirm.click();
		}

	} 

	public RepurchaseConfirmationPage clickOnRepurchaseConfirmationButton() {


		try {
			if(spanSuspTrans.isDisplayed())
			{
				selectSuspiciousTransaction(MasterDataReader.foxwebOrderDetails.get("SuspiciousTransactionType"));
			}


		} catch (Exception e) {
			btnRepurchaseConfirm.click();
			e.printStackTrace();
		}
		return new RepurchaseConfirmationPage(driver).get();


	}



}
