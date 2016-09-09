package com.travelex.nam.pages;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.travelex.framework.common.Log;
import com.travelex.framework.common.WebDriverWrapper;
import com.travelex.framework.utilities.RandomCodeGenerator;
import com.travelex.nam.utilities.InputDataProvider;

@SuppressWarnings("unused")
public class CustomerDetailsPage extends LoadableComponent<CustomerDetailsPage>{
	
	Logger logger = Logger.getLogger(CustomerDetailsPage.class.getName());
	
	WebDriver driver;
	WebDriverWrapper wrapper ;
	
	@FindBy(xpath = "//input[@value='Mr.  'or @value='M.  ']")
	WebElement rbMr;
	
	@FindBy(xpath = "//input[@value='Ms.  'or @value='Mlle.  ']")
	WebElement rbMs;
	
	@FindBy(xpath = "//input[@value='Mrs.  'or @value='Mme.  ']")
	WebElement rbMrs;
	
	@FindBy(xpath = "//input[@name='firstName']")
	WebElement txtFirstName;
	
	@FindBy(xpath = "//input[@name='middleInitial']")
	WebElement txtInitial;
	
	@FindBy(xpath = "//input[@name='lastName']")
	WebElement txtLastName;
	
	@FindBy(xpath = "//input[@name='customerAccountNumber']")
	WebElement txtCustomerAccNumber;
	
	@FindBy(xpath = "//input[@name='alternateAddressSelected']")
	WebElement chkHomeBranch;
	
	@FindBy(xpath = "//input[@name='attention']")
	WebElement txtAttention;
	
	@FindBy(xpath = "//input[@name='companyName']")
	WebElement txtCompanyName;
	
	@FindBy(xpath = "//input[@name='address1']")
	WebElement txtAddress1;
	
	@FindBy(xpath = "//input[@name='address2']")
	WebElement txtAddress2;
	
	@FindBy(xpath = "//input[@name='city']")
	WebElement txtCity;
	
	@FindBy(xpath = "//input[@name='region']")
	WebElement lstState;
	
	@FindBy(xpath = "//input[@name='postalCode']")
	WebElement txtZipCode;
	
	@FindBy(xpath = "//input[@name='country']")
	WebElement txtCountry;
	
	@FindBy(xpath = "//input[@name='areaCode']")
	WebElement txtAreaCode;
	
	@FindBy(xpath = "//input[@name='contactPhoneNumber']")
	WebElement txtPhoneNumber;
	
	@FindBy(xpath = "//input[@name='extension']")
	WebElement txtExtension;
	
	@FindBy(xpath = "//input[@name='changeOrderButton']")
	WebElement btnChangeOrder;
	
	@FindBy(xpath = "//input[@value='Cancel Order']")
	WebElement btnCancelOrder;
	
	@FindBy(xpath = "//input[@name='confirmOrder']")
	WebElement btnCompleteOrder;
	
	@FindBy(xpath = "//td[contains(text(),'Delivery Details') or contains(text(),'Détails de livraison')]")
	WebElement lblDeliveryDetails;
	
	@FindBy(css = "tr:nth-child(2) > td.medBold")
	WebElement lblConfirmationNumber;
	
	@FindBy(xpath = "//input[@name='areaCode']")
	WebElement txtBranchContact;
	
	public CustomerDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}
	
	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(lblDeliveryDetails, 5000);
		if(lblDeliveryDetails.isDisplayed()){
			isPageLoaded = true;
		}
	}
	
	@Override
	public void load(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void enterCustomerDetails(HashMap<String,String> scenarioTestData) throws InterruptedException{
		switch (scenarioTestData.get("CustomerRadioBtn").toUpperCase()) {
		case "MR":
			rbMr.click();
			wrapper.waitForLoaderInvisibility();
			break;
		case "MS":
			rbMs.click();
			wrapper.waitForLoaderInvisibility();
			break;
		case "MRS":
			rbMrs.click();
			wrapper.waitForLoaderInvisibility();
			break;
		default:
			break;
		}
		if(!scenarioTestData.get("FirstName").equalsIgnoreCase("NA")){
			txtFirstName.sendKeys(scenarioTestData.get("FirstName"));
			Log.message("Entered First Name is: "+scenarioTestData.get("FirstName"));
		}
		if(!scenarioTestData.get("LastName").equalsIgnoreCase("NA")){
			txtLastName.sendKeys(scenarioTestData.get("LastName"));
			Log.message("Entered Last Name is: "+scenarioTestData.get("LastName"));
		}
		if(!scenarioTestData.get("GLAccNumber").equalsIgnoreCase("NA")){
			txtCustomerAccNumber.sendKeys(scenarioTestData.get("GLAccNumber"));
			Log.message("Entered GL Acc Number is: "+scenarioTestData.get("GLAccNumber"));
		}
	}	
	
	public void enterDeliveryDetails(HashMap<String,String> scenarioTestData){
		if(chkHomeBranch.isDisplayed()){
			chkHomeBranch.click();
			Log.message("Clicked on Home Branch CheckBox");
			wrapper.waitForLoaderInvisibility();
		}
		if(!scenarioTestData.get("AttentionName").equalsIgnoreCase("NA")){
			txtAttention.sendKeys(scenarioTestData.get("AttentionName"));
			Log.message("Entered Attention Text Field is: "+scenarioTestData.get("AttentionName"));
		}
		if(!scenarioTestData.get("BranchContact").equalsIgnoreCase("NA")){
			txtBranchContact.sendKeys(scenarioTestData.get("BranchContact"));	
			Log.message("Entered Branch Contact is: "+scenarioTestData.get("BranchContact"));
		}
		if(!scenarioTestData.get("PhoneNumber").equalsIgnoreCase("NA")){
			txtPhoneNumber.sendKeys(scenarioTestData.get("PhoneNumber"));	
			Log.message("Entered Phone Number is: "+scenarioTestData.get("PhoneNumber"));
		}
	}
	
	public void submitCustomerDetails(HashMap<String,String> scenarioTestData) {
		if(!scenarioTestData.get("ConfigCompleteOrderBtn").equalsIgnoreCase("NA")){
			btnCompleteOrder.click();	
			Log.message("Clicked on Complete Order");
		}
		wrapper.waitForLoaderInvisibility();
		String Ordertext = lblConfirmationNumber.getText();
		String[] OrderNo = Ordertext.split(":");
		String confirmationNumber = OrderNo[1].trim();
		System.out.println(confirmationNumber);
		if(confirmationNumber!="")	{
			InputDataProvider inputDataProvider = new InputDataProvider();
			inputDataProvider.updateData("TestData", scenarioTestData.get("AutomationID"), confirmationNumber);
			Log.message("Confirmation Number is: "+confirmationNumber);
		}
	}
	
	public void submitCustomerAndDeliveryDetails(HashMap<String,String> scenarioTestData) throws Throwable{
		enterCustomerDetails(scenarioTestData);
		enterDeliveryDetails(scenarioTestData);
		submitCustomerDetails(scenarioTestData);
	}
	
}
