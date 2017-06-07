package com.travelex.pluto.pages;

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
		if (!"NA".equalsIgnoreCase(comments)) {
			wrapper.waitForElementToBeDisplayed(txtComments, timeOutPeriod);
			wrapper.sendKeysUsingJavaScript(txtComments, comments);
		}
		if (!"NA".equalsIgnoreCase(fee)) {
			wrapper.sendKeysUsingJavaScript(txtOtherFeeCheque, fee);
		}
		if (!"NA".equalsIgnoreCase(tellerName)) {
			wrapper.sendKeysUsingJavaScript(txtTellerName, tellerName);
		}

		if (!"NA".equalsIgnoreCase(additionalInfo)) {
			String[] arr = additionalInfo.split("#");
			wrapper.sendKeysUsingJavaScript(AddressLine1, arr[0]);
			wrapper.sendKeysUsingJavaScript(AddressLine2, arr[1]);
			wrapper.sendKeysUsingJavaScript(AddressLine3, arr[2]);
			wrapper.sendKeysUsingJavaScript(txtCity, arr[3]);
		
			txtState.sendKeys(arr[4]);
			wrapper.waitForElementToBeDisplayed(autoSuggest, 2);
			autoSuggest.click();
			
			wrapper.sendKeysUsingJavaScript(txtTelephone, arr[5]);
			wrapper.sendKeysUsingJavaScript(txtZipCode, arr[6]);
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
		String orderNo = lblOrderRefNo.getText().trim();
		return orderNo;
	}


	public void enterAdditionalInfo(String deliveryType,String configAdditionalInfo, String comments,String otherfee,String tellerName,String alternateTellername,String deliveryCharge){
		if (configAdditionalInfo.equalsIgnoreCase("Yes")) {
			wrapper.waitForElementToBeDisplayed(txtComments, timeOutPeriod);
			if (!deliveryType.contains("Home Delivery")) {
				wrapper.sendKeysUsingJavaScript(txtAlternateTellerName, alternateTellername);
			}
			wrapper.sendKeysUsingJavaScript(txtComments, comments);
			wrapper.sendKeysUsingJavaScript(txtOtherFee, otherfee);
			wrapper.sendKeysUsingJavaScript(txtDeliveryCharge, deliveryCharge);
			wrapper.sendKeysUsingJavaScript(txtTellerName, tellerName);
		}
	}
}
