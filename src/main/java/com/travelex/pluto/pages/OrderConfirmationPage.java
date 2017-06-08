package com.travelex.pluto.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.travelex.framework.common.WebDriverWrapper;

public class OrderConfirmationPage extends LoadableComponent<AdditionalInformationPage> implements CustomerAdditionalInfo{
	public WebDriver driver;
	WebDriverWrapper wrapper ;
	private int timeOutPeriod = 30;
	
	@FindBy(name = "btnNext")
	WebElement btnNext;
	
	@FindBy(name = "txtUserName")
	WebElement txtAuthUsername;

	@FindBy(name = "txtPassword")
	WebElement txtAuthPwd;
	
	@FindBy(xpath = "//*[contains(text(),'Reference Number')]")
	WebElement lblOrderRefNo;
	
	@FindBy(xpath = "//*[contains(text(),'Reference Number')]/../following-sibling::td")
	WebElement lbltextOrderRefNo;
	
	
	public OrderConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@SuppressWarnings("unused")
	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(lblOrderRefNo, timeOutPeriod);
		if(lblOrderRefNo.isDisplayed()){
			isPageLoaded = true;
		}
	}

	@Override
	public void load(){
		try {
			wrapper.waitForElementToBeDisplayed(lblOrderRefNo, timeOutPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void clickOnNextBtn(){
		btnNext.click();	
	}
	
	public void authenticationRequired(String username, String pwd){
		boolean isrequired = false;
		try {
			isrequired = txtAuthUsername.isDisplayed();
		} catch (Exception ex) {
			isrequired = false;
		}
		if (isrequired) {
			wrapper.sendKeysUsingJavaScript(txtAuthUsername, username);
			wrapper.sendKeysUsingJavaScript(txtAuthPwd, pwd);
			clickOnNextBtn();
		}
	}
	
	
	public String getConfirmationNum(){
		String orderNo = lbltextOrderRefNo.getText().trim();
		return orderNo;
	}

}
