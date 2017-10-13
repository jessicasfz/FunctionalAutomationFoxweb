package com.travelex.foxweb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;


import com.travelex.framework.common.WebDriverWrapper;
import com.travelex.stepDefinitions.MasterDataReader;

@SuppressWarnings("unused")
public class OrderDetailsPage extends LoadableComponent<OrderDetailsPage>{	

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

	@FindBy(name = "currency")
	WebElement selectForeignCurrency;

	@FindBy(name="forexAmt")
	WebElement txtForeignAmount;

	@FindBy(css="#orderitem>table>tbody>tr:nth-child(1)>td:nth-child(1)")
	WebElement labelQuote;

	@FindBy(id = "convert")
	WebElement btnConvert;

	@FindBy(name="next1")
	WebElement btnNext;

	@FindBy(id="next")
	WebElement btnNext1;

	@FindBy(css=".message_error")
	WebElement msgError;

	@FindBy(css= "input[type=text][name=customer]")
	WebElement txtCustName;

	@FindBy(xpath = "//span/a[1]")
	WebElement lnkPassport;

	@FindBy(linkText= "Confirm  ")
	WebElement lnkConfirm;

	@FindBy(id= "skipIdCheckBox")
	WebElement chkboxSkipId;
	
	@FindBy(id="id1")
	WebElement selPrivacyScript;

	@FindBy(id="id2")
	WebElement txtFname;

	@FindBy(id="id4")
	WebElement txtLname;

	@FindBy(id="id5")
	WebElement txtDOB;

	@FindBy(id="id6")
	WebElement txtIdNo;
	
	@FindBy(id="id7")
	WebElement selCountryOfIssue;

	@FindBy(id="id8")
	WebElement txtExpiryDate;

	@FindBy(id="addinfoid1") //Added by Author @jachakN
	WebElement txtStrAddress1;


	@FindBy(id="addinfoid2") //Added by Author @jachakN
	WebElement txtStrAddress2;


	@FindBy(id="addinfoid3") //Added by Author @jachakN
	WebElement txtCity;


	@FindBy(id="addinfoid4") //Added by Author @jachakN
	WebElement txtState;

	@FindBy(id="addinfoid5") //Added by Author @jachakN
	WebElement txtPostCode;

	@FindBy(id="addinfoid6") //Added by Author @jachakN
	WebElement selAddCountry;
	
	@FindBy(id="addinfoid7")//Added by Author @jachakN
	WebElement selAddNationality;
	
	@FindBy(id= "addinfoid8")//Added by Author @jachakN
	WebElement selOccupation;
	
	@FindBy(id="addinfoid9")//Added by Author @jachakN
	WebElement txtSourceOfFundsExplanation;
	
	@FindBy(id= "addinfoid10") //Added by Author @jachakN
	WebElement txtPurposeOfTransaction;
		
	 @FindBy(xpath ="//div[@id='contentDiv']/div[1]") //Added by Author @jachakN
	  WebElement lblCaptureSettlement;
	  
	  @FindBy(name = "settlementmethod") //Added by Author @jachakN
	  WebElement drpdwnPaymentType;
	  
	  @FindBy(xpath = "//div[@id='contentDiv']/div[3]/a") //Added by Author @jachakN
	  WebElement btnNextCaptureSettlement;
	  
	  @FindBy(id = "knownCustomerCheckBox") //Added by Author @jachakN
	  WebElement chkboxKnownCustomer;
	  
	  @FindBy(name= "forexAmt") //Added by Author @jachakN
	  WebElement txtAmountStockNull;
	  
	  
	

	public OrderDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(txtCustName, timeOutPeriod);
		if(labelQuote.isDisplayed()){
			isPageLoaded = true;
		}
	}



	public boolean isOrderDetailsPageLoaded(){
		boolean isOrderDetailsPageLoaded = false;
		if(txtCustName.isDisplayed()){
			isOrderDetailsPageLoaded = true;
		}
		return isOrderDetailsPageLoaded;
	}

	

	public  PreviewDetailsPage enterOrderDetails(String currency, String fgnamount, String custName) {
		

		Select selFgnCurrency  = new Select(selectForeignCurrency);
		selFgnCurrency.selectByVisibleText(currency);
		selectForeignCurrency.sendKeys(Keys.TAB);
		txtForeignAmount.clear();
		driver.switchTo().alert().accept();
		txtForeignAmount.sendKeys(fgnamount);
		txtForeignAmount.sendKeys(Keys.TAB);
		txtCustName.clear();
		txtCustName.sendKeys(custName);
		btnNext.click();   
		
		/**
		 * @author jachakN
		 * For Checking the Settlement Method 
		 * Added method for handling pop up for processing via External Till
		 */
		

		try{
			
			driver.switchTo().alert().accept();
			
			}catch(Exception e){

			}
		
		checkForOutOfStockEntity();
		
		btnNext.click();  //This is the Denomination Screen 
			
		
		
		try {
			if(msgError.isDisplayed())
			{
				
				enterStandardIDDetails();
			}
			

		} catch (Exception e) {
			e.printStackTrace();			
			btnNext1.click();
			
					
		}


		//btnNext1.click();
		return new PreviewDetailsPage(driver).get();

	}

	public void enterStandardIDDetails() {
		
	
		wrapper.waitForElementToBeDisplayed(btnNext1, 30);
		btnNext1.click();
		
		
		
		processCaptureSettlemnt(); //added by author @jachakN
		
		String skipidcheck = MasterDataReader.foxwebOrderDetails.get("SkipId");
		if (skipidcheck.equals("Yes"))
			{
				try {
					chkboxSkipId.click();
					driver.findElement(By.id("skipIdReasonTextBox")).sendKeys("ID Check Not Required");
					driver.findElement(By.id("continueLink")).click();
				} catch (Exception e) {
					/**
					 * @author jachakN
					 * For another option of Skip ID 
					 */
					
					chkboxKnownCustomer.click();
					driver.findElement(By.id("continueLink")).click();
					
					
					e.printStackTrace();
				}
			}		
			else
			{
				//btnNext1.click();
			
				lnkPassport.click();
				//if(!MasterDataReader.StandardIDDetails.isEmpty()){
				Select objSelPrivacyScript = new Select(selPrivacyScript);
				objSelPrivacyScript.selectByValue(MasterDataReader.StandardIDDetails.get("PleaseSelect"));
				txtFname.sendKeys(MasterDataReader.StandardIDDetails.get("Fname"));
				txtLname.sendKeys(MasterDataReader.StandardIDDetails.get("Lname"));
				txtDOB.sendKeys(MasterDataReader.StandardIDDetails.get("DOB"));
				txtIdNo.sendKeys(MasterDataReader.StandardIDDetails.get("IDNo"));
				Select objCountryIssue = new Select(selCountryOfIssue);
				objCountryIssue.selectByVisibleText(MasterDataReader.StandardIDDetails.get("CountryOfIssue"));
				txtExpiryDate.sendKeys(MasterDataReader.StandardIDDetails.get("ExpiryDate"));
				txtStrAddress1.sendKeys(MasterDataReader.StandardIDDetails.get("SAL1"));
				txtStrAddress2.sendKeys(MasterDataReader.StandardIDDetails.get("SAL2"));
				txtCity.sendKeys(MasterDataReader.StandardIDDetails.get("City"));
				txtState.sendKeys(MasterDataReader.StandardIDDetails.get("State"));
				txtPostCode.sendKeys(MasterDataReader.StandardIDDetails.get("PostCode"));
				Select objCountryRefuse = new Select(selAddCountry);
				objCountryRefuse.selectByVisibleText(MasterDataReader.StandardIDDetails.get("CountryOfIssue"));
				
				
				/**
				 * @author jachakN
				 * For Checking the Settlement Method 
				 * Added method for handling additional info when order amount is greater than 10k
				 */
				if(isDisplayAdditionalFields()){				
					
					Select objAddNationality = new Select(selAddNationality);
					objAddNationality.selectByVisibleText(MasterDataReader.StandardIDDetails.get("AdditionalNationality"));
					Select objOccupation = new Select(selOccupation);
					objOccupation.selectByVisibleText(MasterDataReader.StandardIDDetails.get("Occupation"));
					txtSourceOfFundsExplanation.sendKeys(MasterDataReader.StandardIDDetails.get("SourceOfFundsExplanation"));
					txtPurposeOfTransaction.sendKeys(MasterDataReader.StandardIDDetails.get("PurposeOfTransaction"));
				}
				
				lnkConfirm.click();
				driver.findElement(By.id("continueLink")).click();
			}
	}
	
	/**
	 * @author jachakN
	 * For Checking the Settlement Method 
	 * Added method for handling additional info when order amount is greater than 10k
	 */
	private void processCaptureSettlemnt() {
			
			  /*String txtCaptureSettlement = lblCaptureSettlement.getText();*/
		
		try {
			if (lblCaptureSettlement.isDisplayed()) /*&& "Capture Settlement Method".equals(lblCaptureSettlement.getText())*/{
					
				Select objPaymentMethod = new Select(drpdwnPaymentType);
				objPaymentMethod.selectByVisibleText(MasterDataReader.foxwebOrderDetails.get("PaymentType"));
				
				String paymentType = MasterDataReader.foxwebOrderDetails.get("PaymentType");
					
				if("Bank cheque".equals(paymentType) ||  "Bank draft".equals(paymentType) || "Money / Postal order".equals(paymentType)){
					driver.findElement(By.id("dijit_form_ValidationTextBox_1")).sendKeys("Foxweb-Drawer");
					driver.findElement(By.id("dijit_form_ValidationTextBox_2")).sendKeys("Foxweb-Payee");
				} else if("Other".equals(paymentType)){
					driver.findElement(By.id("dijit_form_ValidationTextBox_3")).sendKeys("Foxweb-desciption");
				}
				
				
				
				btnNextCaptureSettlement.click();
				
				//displayAdditionalFields = true;
				setDisplayAdditionalFields(true);
			}
		} catch (Exception e) {
			System.out.println("Capture not applicable");
		}
		
		
	}

	@Override
	protected void load() {
	}

	public boolean verifyQuoteDetailsisDisplayed() {
		boolean verifyQuoteDetailsisDisplayed =false;
		if(btnConvert.isDisplayed())
		{
			verifyQuoteDetailsisDisplayed = true;
		}	
		return verifyQuoteDetailsisDisplayed;

	}

	/**
	 * @author jachakN
	 * For Checking the Settlement Method 
	 * Added method for handling additional info when order amount is greater than 10k
	 */
	private boolean displayAdditionalFields = false;

	public boolean isDisplayAdditionalFields() {
		return displayAdditionalFields;
	}

	public void setDisplayAdditionalFields(boolean displayAdditionalFields) {
		this.displayAdditionalFields = displayAdditionalFields;
	}

	
	/**
	 * @author jachakN
	 * Added method for exception handling when currency is out of stock 
	 */
	
	private void checkForOutOfStockEntity() {
		
		boolean isEntityOutOfStock = false;
		try {
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			String amountOutOfStock = (String)js.executeScript("return arguments[0].value", txtAmountStockNull);
			
			 if("0.00".equals(amountOutOfStock)){
				 isEntityOutOfStock = true;
				 
			 }
		} catch (Exception e) {
			
		}
		finally{					
			if (isEntityOutOfStock)
				throw new RuntimeException("Out of Stock");
		}
	}
	
	
	}
