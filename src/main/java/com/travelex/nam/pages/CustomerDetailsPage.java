package com.travelex.nam.pages;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.travelex.framework.common.WebDriverWrapper;
import com.travelex.framework.utilities.RandomCodeGenerator;
import com.travelex.stepDefinitions.MasterDataReader;

@SuppressWarnings("unused")
public class CustomerDetailsPage extends LoadableComponent<CustomerDetailsPage>{	

	private WebDriver driver;
	
	private WebDriverWrapper wrapper ;
	private int timeOutPeriod = 3000;
	private int waitTime=300;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

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
	
	@FindBy(id = "/labels/customerDetails/accountElementID")
	WebElement lstBankID;
	
	
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
	
	@FindBy(id = "region")
	WebElement lstBranchState;
	
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
	
	@FindBy(xpath = "//td[@class='medBold']")
	WebElement lblConfirmationNumber;
	
	@FindBy(xpath = "//input[@name='areaCode']")
	WebElement txtBranchContact;
	
	@FindBy(xpath = "//input[@value='Printer Friendly']") 
	WebElement btnPrinterFriendly;
	
	@FindBy(id = "customerDOB")
	WebElement txtDateOfBirth;
	
	@FindBy(id = "customerAddress1")
	WebElement txtCustomerAddress1;
	
	@FindBy(id = "customerAddress2")
	WebElement txtCustomerAddress2;
	
	@FindBy(id = "customerRegion")
	WebElement lstCustomerRegion;
	
	@FindBy(id = "customerZipCode")
	WebElement txtCustomerZipCode;
	
	@FindBy(id = "customerCountry")
	WebElement txtcustomerCountry;
	
	@FindBy(id = "customerPrimarySID")
	WebElement lstPrimaryID;
	
	@FindBy(id = "customerPrimaryIDNum")
	WebElement txtIDNumber;
	
	@FindBy(id = "customerPrimaryIDCountry")
	WebElement lstCountryOfIssueance;
	
	@FindBy(id = "customerPrimaryIDState")
	WebElement lsttxtCustomerState;
	
	@FindBy(id = "customerPrimaryIDIssueDate")
	WebElement txtIssueDate;
	
	@FindBy(id = "customerPrimaryIDExpiryDate")
	WebElement txtExpiryDate;
	
	@FindBy(id = "customerCity")
	WebElement txtCustomerCity;
	
	@FindBy(id = "customerZipCode")
	WebElement txtCustomerZipcode;
	
	@FindBy(id = "customerRegion")
	WebElement lstCustomerState;
	
	@FindBy(xpath = "//td[contains(text(),'Customer Details')]") 
	WebElement lblCustomerDetails;
	
	@FindBy(xpath = "//form[@id='settlementInformationForm']/table[7]/tbody/tr[2]/td")
	WebElement addressField1;
	
	@FindBy(xpath = "//form[@id='settlementInformationForm']/table[7]/tbody/tr[3]/td")
	WebElement addressField2;
	
	@FindBy(xpath = "//form[@id='settlementInformationForm']/table[7]/tbody/tr[4]/td")
	WebElement addressField3;
	
	@FindBy(xpath = "//form[@id='settlementInformationForm']/table[7]/tbody/tr[6]/td")
	WebElement addressField4;
	
	@FindBy(xpath = "//form[@id='settlementInformationForm']/table[7]/tbody/tr[7]/td")
	WebElement addressField5;
	
	@FindBy(id = "resetDelivDetails")
	WebElement chkChangeBranch;
	

	public CustomerDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}
	
	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(lblCustomerDetails, timeOutPeriod);
		if(lblCustomerDetails.isDisplayed()){
			isPageLoaded = true;
		}
	}
	
	@Override
	public void load(){
		try {
			wrapper.waitForElementToBeDisplayed(lblCustomerDetails, timeOutPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * It will Enter Customer details [firstName, lastName, GL Account]
	 * 
	 * @param customerRadioBtn
	 * @param firstName
	 * @param lastName
	 * @param glAccountNumber
	 * @throws InterruptedException
	 */
	
	public void enterCustomerDetails(String customerRadioBtn, String firstName,String lastName, String glAccountNumber,String bankID,String dateOfBirth) throws Exception{
		
		if(!customerRadioBtn.equalsIgnoreCase("NA")){
		switch (customerRadioBtn.toUpperCase()) {
		case "MR":
			rbMr.click();
			wrapper.waitForLoaderInvisibility(waitTime);
			break;
		case "MS":
			rbMs.click();
			wrapper.waitForLoaderInvisibility(waitTime);
			break;
		case "MRS":
			rbMrs.click();
			wrapper.waitForLoaderInvisibility(waitTime);
			break;
		default:
			break;
		}
		}
		if(!firstName.equalsIgnoreCase("NA")){
			txtFirstName.sendKeys(firstName);
		}
		if(!lastName.equalsIgnoreCase("NA")){
			txtLastName.sendKeys(lastName);
		}

		if(!bankID.equalsIgnoreCase("NA")){
			Select bankIdList = new Select(lstBankID);
			bankIdList.selectByVisibleText(bankID);			
		}
		
		if(!glAccountNumber.equalsIgnoreCase("NA")){
			txtCustomerAccNumber.sendKeys(glAccountNumber);
		}
		
		
		if(!dateOfBirth.equalsIgnoreCase("NA")){
			txtDateOfBirth.sendKeys(dateOfBirth);
		}
	}	
	
	/**
	 * It will Enter Delivery details
	 * 
	 * @param attentionName
	 * @param branchContact
	 * @param phoneNumber
	 */
	
	public void enterDeliveryDetails(String attentionName, String branchContact, String phoneNumber,String partnerID) throws Exception{
/*		if(!partnerID.equalsIgnoreCase("19333") || !partnerID.equalsIgnoreCase("119982")){
			if(chkHomeBranch.isDisplayed()){
				chkHomeBranch.click();
				wrapper.waitForLoaderInvisibility(waitTime);
			}
		}*/
		if(!attentionName.equalsIgnoreCase("NA")){
			txtAttention.clear();
			txtAttention.sendKeys(attentionName);
		}
		if(!branchContact.equalsIgnoreCase("NA")){
			txtBranchContact.clear();
			txtBranchContact.sendKeys(branchContact);	
		}
		if(!phoneNumber.equalsIgnoreCase("NA")){
			txtPhoneNumber.clear();
			txtPhoneNumber.sendKeys(phoneNumber);	
		}
	}
	
	/**
	 * It will Submit And Captures Confirmation Number and Updates it into Excel 
	 *  
	 * @param configCompleteOrderBtn
	 * @param automationID
	 */
	
	public PrinterFriendlyPage submitCustomerDetails() throws Exception{		
			btnCompleteOrder.click();	
			wrapper.waitForLoaderInvisibility(waitTime);
			return new PrinterFriendlyPage(driver).get();
	}
	
	public String getConfirmationNumber(){
		String confirmationNumber = "";
		String Ordertext = lblConfirmationNumber.getText();
		String[] OrderNo = Ordertext.split(":");
		confirmationNumber = OrderNo[1].trim();
		System.out.println(confirmationNumber);
		MasterDataReader.scenario.write("<B><font size='3' color='Magenta'>Confirmation Number is : " +confirmationNumber+"</font></B>");
	return confirmationNumber;
	}
	
	
	//Modified code for IE compatibility & WindowHandling in IE
	public void printerFriendlyBtn(String browser,String windowTitle) throws InterruptedException{
		btnPrinterFriendly.click();
		Thread.sleep(2000);
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			if(!driver.getTitle().equals(windowTitle)){
			driver.switchTo().window(window);
			}
		}
		if(browser.equalsIgnoreCase("IE")){
			driver.manage().window().maximize();
			if(!driver.getTitle().contains("Certificate Error")) Assert.fail("Failed to launch the browser");
			driver.get("javascript:document.getElementById('overridelink').click();");
		}
	}
		
	
	public void enterCustomerAddress(String customerAddressOne,String customerAddressTwo,String city,String state,String zipCode,String country) throws Exception{
		
		if(!customerAddressOne.equalsIgnoreCase("NA")){
			txtCustomerAddress1.sendKeys(customerAddressOne);
		}
		
		if(!customerAddressTwo.equalsIgnoreCase("NA")){
			txtCustomerAddress2.sendKeys(customerAddressTwo);
		}
		
		if(!city.equalsIgnoreCase("NA")){
			txtCustomerCity.sendKeys(city);
		}
		
		if(!state.equalsIgnoreCase("NA")){
			Select stateList = new Select(lstCustomerState);
			stateList.selectByVisibleText(state);
		}	
		
		if(!zipCode.equalsIgnoreCase("NA")){
			txtCustomerZipcode.sendKeys(zipCode);
		}		
	}
	
	public void enterCustomerSecurityInformation(String primaryID,String idNumber,String countryOfIssuance, String state, String issueDate, String expiryDate) throws Exception{
		
		if(!primaryID.equalsIgnoreCase("NA")){
			Select primaryIDList = new Select(lstPrimaryID);
			primaryIDList.selectByVisibleText(primaryID);
		}

		
		if(!idNumber.equalsIgnoreCase("NA")){
			txtIDNumber.sendKeys(idNumber);
		}
				
		if(!countryOfIssuance.equalsIgnoreCase("NA")){
			Select countryOfIssuanceList = new Select(lstCountryOfIssueance);
			countryOfIssuanceList.selectByVisibleText(countryOfIssuance);
		}
		
		switch (primaryID.toUpperCase()) {
			case "DRIVING LICENSE NUMBER":
				Select stateListD = new Select(lsttxtCustomerState);
				stateListD.selectByVisibleText(state);
				break;
				
			case "MILITARY ID":
				Select stateListM = new Select(lsttxtCustomerState);
				stateListM.selectByVisibleText(state);			
				break;
				
			case "STATE ID":				
				Select stateList = new Select(lsttxtCustomerState);
				stateList.selectByVisibleText(state);			
				break;
				
			case "PASSPORT NUMBER":
				lsttxtCustomerState.sendKeys(state);			
				break;
			case "MEXICAN CONSULATE CARD":
				
				lsttxtCustomerState.sendKeys(state);			
				break;
				
			default:
				break;
		}
		
		if(!issueDate.equalsIgnoreCase("NA")){
			txtIssueDate.sendKeys(issueDate);
		}
		
		if(!expiryDate.equalsIgnoreCase("NA")){
			txtExpiryDate.sendKeys(expiryDate);
		}
	}	

	public void enterCustomerContactDetails(String areaCode,String phoneNumber,String extension ) throws Exception {
		
		if(!areaCode.equalsIgnoreCase("NA")){
			txtAreaCode.sendKeys(areaCode);
		}
		
		if(!phoneNumber.equalsIgnoreCase("NA")){
			txtPhoneNumber.sendKeys(phoneNumber);
		}
		
		if(!extension.equalsIgnoreCase("NA")){
			txtExtension.sendKeys(extension);
		}	
	
	}
	
	public void clickOnChangeOrderButton(){
			btnChangeOrder.click();
			wrapper.waitForLoaderInvisibility(waitTime);		
	}
	
	public void clickOnCancelOrderButton(){
			btnCancelOrder.click();
			wrapper.waitForLoaderInvisibility(waitTime);
	}
		
	public String switchtoPopUpAndGetMessage(){
		String popupMessage = null;
		popupMessage =driver.switchTo().alert().getText();
		return popupMessage;
	}
	
	public void acceptAlert(){
		wrapper.acceptAlert(timeOutPeriod);
		wrapper.waitForLoaderInvisibility(waitTime);
	}
	
	public HomePage verifyCancelOrderBtn(){
		return new HomePage(driver).get();
	}
	
	
	public void enterBranchDetails(String configChangeBranch,String branchDetails,String partner){
		if(!(configChangeBranch.equalsIgnoreCase("NA"))){
			
			String[] allDetails = branchDetails.split("\\#");
			String attentionName = allDetails[0].trim();
			String companyName = allDetails[1].trim();
			String add1 = allDetails[2].trim();
			String add2 = allDetails[3].trim();
			String city = allDetails[4].trim();
			String state = allDetails[5].trim();
			String zipcode = allDetails[6].trim();
			
			boolean isChecked = false;
			if(partner.equalsIgnoreCase("119982") || "16974".equalsIgnoreCase(partner) || "113008".equalsIgnoreCase(partner) ||
					"118970".equalsIgnoreCase(partner) || "126548".equalsIgnoreCase(partner) || "19340".equalsIgnoreCase(partner) || 
					"127875".equalsIgnoreCase(partner) || "128103".equalsIgnoreCase(partner) || "129666".equalsIgnoreCase(partner) ||
					"116198".equalsIgnoreCase(partner)){
				isChecked = chkHomeBranch.isSelected();
				if(isChecked){
					chkHomeBranch.click();
				}
			}else if(partner.equalsIgnoreCase("19333")){
				isChecked = chkChangeBranch.isSelected();
				if(isChecked){
					chkChangeBranch.click();
				}
			}
			
			if(!attentionName.equalsIgnoreCase("NA")){
				txtAttention.clear();
				txtAttention.sendKeys(attentionName);
			}
			if(!companyName.equalsIgnoreCase("NA")){
				txtCompanyName.clear();
				txtCompanyName.sendKeys(companyName);
			}
			
			if(!add1.equalsIgnoreCase("NA")){
				txtAddress1.clear();
				txtAddress1.sendKeys(add1);
				txtAddress2.clear();
				txtAddress2.sendKeys(add2);
			}
			
			if(!city.equalsIgnoreCase("NA")){
				txtCity.clear();
				txtCity.sendKeys(city);
			}
			
			Select stateList = new Select(lstBranchState);
			stateList.selectByValue(state);
			
			if(!zipcode.equalsIgnoreCase("NA")){
				txtZipCode.clear();
				txtZipCode.sendKeys(zipcode);
			}
		}
	}
}
