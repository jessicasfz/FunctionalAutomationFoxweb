package com.travelex.pluto.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.travelex.framework.common.WebDriverWrapper;

public class AdditionalInformationPage extends LoadableComponent<AdditionalInformationPage>{
	
	public WebDriver driver;
	WebDriverWrapper wrapper ;
	private int timeOutPeriod = 3000;
	public JavascriptExecutor myExecutor;
	
	@FindBy(name = "COMMENTS")
	WebElement txtComments;
	
	@FindBy(name = "Commission")
	WebElement txtOtherFee;
	
	@FindBy(name = "DeliveryCharge")
	WebElement txtDeliveryCharge;
	
	@FindBy(name = "UserName")
	WebElement txtTellerName;
	
	@FindBy(name = "COLLECTION_CHQ")
	WebElement rdoCollectionCheque;
		
	@FindBy(name = "COMMISSION")
	WebElement txtOtherFeeCheque;
	
	@FindBy(name = "btnNext")
	WebElement btnNext;
	
	@FindBy(className = "TITLETEXTSTD")
	WebElement lblAuthorisationPage;
	
	@FindBy(name = "txtUserName")
	WebElement txtAuthUsername;
	
	@FindBy(name = "txtPassword")
	WebElement txtAuthPwd;
	
	@FindBy(xpath = "//*[contains(text(),'Reference Number')]/../following-sibling::td")
	WebElement lblOrderRefNo;
	
	
	public AdditionalInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@SuppressWarnings("unused")
	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(txtOtherFee, timeOutPeriod);
		if(txtOtherFee.isDisplayed()){
			isPageLoaded = true;
		}
	}

	@Override
	public void load(){
		try {
			wrapper.waitForElementToBeDisplayed(txtOtherFee, timeOutPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public void enterAdditionalInfo(String comments,String fee,String tellerName){
		myExecutor = ((JavascriptExecutor) driver);
		wrapper.waitForElementToBeDisplayed(txtComments, timeOutPeriod);
		myExecutor.executeScript("arguments[0].value='"+comments+"';", txtComments);
		myExecutor.executeScript("arguments[0].value='"+fee+"';", txtOtherFeeCheque);
		myExecutor.executeScript("arguments[0].value='"+tellerName+"';", txtTellerName);		
	}

	public void clickOnNextBtn(){
		btnNext.click();
	}
	
	public void authenticationRequired(String username, String pwd){
		boolean isrequired = txtAuthUsername.isDisplayed();
		if(isrequired){
			myExecutor = ((JavascriptExecutor) driver);
			myExecutor.executeScript("arguments[0].value='"+username+"';", txtAuthUsername);
			myExecutor.executeScript("arguments[0].value='"+pwd+"';", txtAuthPwd);
			clickOnNextBtn();
		}
	}
	
	public String getConfirmationNum(){
		String orderNo = null;
		orderNo = lblOrderRefNo.getText().trim();
		return orderNo;
	}
}
