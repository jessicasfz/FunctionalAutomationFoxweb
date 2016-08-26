package com.travelex.nam.pages;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.travelex.framework.utilities.RandomCodeGenerator;

@SuppressWarnings("unused")
public class CustomerDetailsPage extends LoadableComponent<CustomerDetailsPage>{
	
	Logger logger = Logger.getLogger(CustomerDetailsPage.class.getName());
	
	WebDriver driver;
	
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
	
	public CustomerDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		if(lblDeliveryDetails.isDisplayed()){
			isPageLoaded = true;
		}
		//return isPageLoaded;
	}
	
	@Override
	public void load(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void enterCustomerDetails(String customerType) throws InterruptedException{
		switch (customerType.toUpperCase()) {
		case "MR":
			rbMr.click();
			break;
		case "MS":
			rbMs.click();
			break;
		case "MRS":
			rbMrs.click();
			break;
		default:
			break;
		}
		
		txtFirstName.sendKeys(RandomCodeGenerator.randomNameGeneratorUsingCharacter());
		txtLastName.sendKeys(RandomCodeGenerator.randomNameGeneratorUsingCharacter());
		if(txtCustomerAccNumber.isDisplayed()){
			txtCustomerAccNumber.sendKeys(RandomCodeGenerator.randomNumberGenerator(9));	
		}
	}	
	
	public void enterDeliveryDetails(){
		if(chkHomeBranch.isDisplayed()){
			chkHomeBranch.click();
		}
		txtAttention.sendKeys(RandomCodeGenerator.randomNameGeneratorUsingCharacter(6));
	//	txtBranchContact.sendKeys(RandomCodeGenerator.randomNumberGenerator(3));
		txtPhoneNumber.sendKeys(RandomCodeGenerator.randomNumberGenerator(7));
	}
	
	public void submitCustomerAndDeliveryDetails(String automationId) {
		
			btnCompleteOrder.click();
			String Ordertext = lblConfirmationNumber.getText();
			String[] OrderNo = Ordertext.split(":");
			String confirmationNumber = OrderNo[1].trim();
			System.out.println(confirmationNumber);
			if(confirmationNumber!="")	{
				   //FileUtils.writeFile("fileName", "columnname", automationId, confirmationNumber);
			   }
	}
	
	public void submitCustomerAndDeliveryDetails(String customerType,String automationId) throws Throwable{
		enterCustomerDetails(customerType);
		enterDeliveryDetails();
		submitCustomerAndDeliveryDetails(automationId);
	}
	
}
