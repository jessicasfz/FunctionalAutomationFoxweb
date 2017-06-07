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
	private int timeOutPeriod = 30;
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

	@FindBy(name = "ATTENTION")
	WebElement txtAlternateTellerName;

	@FindBy(name = "ADDRESS_LINE_1")
	WebElement AddressLine1;

	@FindBy(name = "ADDRESS_LINE_2")
	WebElement AddressLine2;

	@FindBy(name = "ADDRESS_LINE_3")
	WebElement AddressLine3;

	@FindBy(name = "CITY")
	WebElement txtCity;

	@FindBy(name = "STATE")
	WebElement txtState;

	@FindBy(name = "TELEPHONE_NO")
	WebElement txtTelephone;

	@FindBy(name = "ZIP_CODE")
	WebElement txtZipCode;
	
	@FindBy(css = "ul li a")
	WebElement autoSuggest;

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

	// Added code if condition, need this for Single FC scenario
	// Also added collectionCheck condition, required for Single Check scenario
	public void enterAdditionalInfo(String CollectionCheck, String comments,String fee,String tellerName, String additionalInfo){
		if ("YES".equalsIgnoreCase(CollectionCheck)) {
			wrapper.waitForElementToBeDisplayed(rdoCollectionCheque, timeOutPeriod);
			rdoCollectionCheque.click();
		}
		myExecutor = ((JavascriptExecutor) driver);
		if (!"NA".equalsIgnoreCase(comments)) {
			wrapper.waitForElementToBeDisplayed(txtComments, timeOutPeriod);
			myExecutor.executeScript("arguments[0].value='" + comments + "';", txtComments);
		}
		if (!"NA".equalsIgnoreCase(fee)) {
			myExecutor.executeScript("arguments[0].value='" + fee + "';", txtOtherFeeCheque);
		}
		if (!"NA".equalsIgnoreCase(tellerName)) {
			myExecutor.executeScript("arguments[0].value='" + tellerName + "';", txtTellerName);
		}

		if (!"NA".equalsIgnoreCase(additionalInfo)) {
			String[] arr = additionalInfo.split("#");
			myExecutor.executeScript("arguments[0].value='" + arr[0] + "';", AddressLine1);
			myExecutor.executeScript("arguments[0].value='" + arr[1] + "';", AddressLine2);
			myExecutor.executeScript("arguments[0].value='" + arr[2] + "';", AddressLine3);
			myExecutor.executeScript("arguments[0].value='" + arr[3] + "';", txtCity);

			txtState.sendKeys(arr[4]);
			wrapper.waitForElementToBeDisplayed(autoSuggest, 2);
			autoSuggest.click();
			
			myExecutor.executeScript("arguments[0].value='" + arr[5] + "';", txtTelephone);
			myExecutor.executeScript("arguments[0].value='" + arr[6] + "';", txtZipCode);
		}
	}

	public void clickOnNextBtn(){
		try {
			btnNext.click();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void authenticationRequired(String username, String pwd){
		boolean isrequired = false;
		try {
			isrequired = txtAuthUsername.isDisplayed();
		} catch (Exception ex) {
			isrequired = false;
		}
		if (isrequired) {
			myExecutor = ((JavascriptExecutor) driver);
			myExecutor.executeScript("arguments[0].value='" + username + "';", txtAuthUsername);
			myExecutor.executeScript("arguments[0].value='" + pwd + "';", txtAuthPwd);
			clickOnNextBtn();
		}
	}

	public String getConfirmationNum(){
		String orderNo = null;
		orderNo = lblOrderRefNo.getText().trim();
		return orderNo;
	}


	public void enterAdditionalInfo(String deliveryType,String configAdditionalInfo, String comments,String otherfee,String tellerName,String alternateTellername,String deliveryCharge){
		if (configAdditionalInfo.equalsIgnoreCase("Yes")) {
			myExecutor = ((JavascriptExecutor) driver);
			wrapper.waitForElementToBeDisplayed(txtComments, timeOutPeriod);
			if (!deliveryType.contains("Home Delivery")) {
				myExecutor.executeScript("arguments[0].value='" + alternateTellername + "';", txtAlternateTellerName);
			}
			myExecutor.executeScript("arguments[0].value='" + comments + "';", txtComments);
			myExecutor.executeScript("arguments[0].value='" + otherfee + "';", txtOtherFee);
			myExecutor.executeScript("arguments[0].value='" + deliveryCharge + "';", txtDeliveryCharge);			
			myExecutor.executeScript("arguments[0].value='" + tellerName + "';", txtTellerName);

		}
	}
}
