package com.travelex.foxweb.pages;

import org.junit.Assert;
import org.openqa.selenium.Alert;
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
import com.travelex.framework.exception.FoxwebAutoException;
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
	
	@FindBy(xpath="//form[@id='identification']/table[2]/tbody/tr/td/table/tbody/tr[12]/td[2]/input") //Added by Author @jachakN
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
	  
	  @FindBy(name= "atcountry") //Added by Author @jachakN for TT and draft product
	  WebElement selSenderCountry;
	  
	  @FindBy(name= "bankcountry")//Added by Author @jachakN for TT and draft product
	  WebElement selBankCountry;
	  
	  @FindBy(name= "payeecountry")//Added by Author @jachakN for TT and draft product
	  WebElement selBeneficiaryCountry;
	  
	  
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

	
	
	public  PreviewDetailsPage enterOrderDetails(String currency, String fgnAmount, String custName) {
		
		String productType = MasterDataReader.foxwebOrderDetails.get("ProductType");
		
		boolean isProductTypeForeignCash = false;
	
		if ("Foreign Cash".equals(productType)){
			
			enterOrderDetailsForeignCash(currency, fgnAmount, custName);
			
				
		}
		else if ("Telegraphic Transfer".equals(productType)){
			isProductTypeForeignCash = true;
			
			enterOrderDetailsTelegraphicTransfer(currency, fgnAmount);
		}
		
		else if ("Draft".equals(productType)){
			
			isProductTypeForeignCash = true;
			
			enterOrderDetailsDraft(currency, fgnAmount);
			
			
		}
		
		btnNext.click(); 
		
		/**@author jachakN
		*Added method for product TT and draft
		**/
		
		 
		
		/**
		 * @author jachakN
		 * For Checking the Settlement Method 
		 * Added method for handling pop up for processing via External Till
		 */
		
			
			
					
			if (!isProductTypeForeignCash)	{
				
				try {
					driver.switchTo().alert().accept();
					} 
				
				catch (Exception e) {
					/*// TODO Auto-generated catch block
					e.printStackTrace();*/
				}
								
				checkForOutOfStockEntity();
				
				btnNext.click();  //This is the Denomination Screen
				
			}
				 
			
			standardIdMessageDisplayPage(productType);
	

		//btnNext1.click();
		return new PreviewDetailsPage(driver).get();

	}

	public void enterStandardIDDetails(String productType) {
		
	
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
				if ("Foreign Cash".equals(productType)){
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
				}
				
				/**Author @jachakN
				*Added standardID details method for TT and draft
				*/
				
				else if ("Telegraphic Transfer".equals(productType) || ("Draft".equals(productType)) || ("Documents".equals(productType))){
					lnkPassport.click();
					driver.findElement(By.id("id1")).sendKeys(MasterDataReader.StandardIDDetails.get("Fname"));
					driver.findElement(By.id("id3")).sendKeys(MasterDataReader.StandardIDDetails.get("Lname"));
					driver.findElement(By.id("id4")).sendKeys(MasterDataReader.StandardIDDetails.get("DOB"));
					driver.findElement(By.id("id5")).sendKeys(MasterDataReader.StandardIDDetails.get("IDNo"));
					Select objCountryIssueTT = new Select(driver.findElement(By.id("id6")));
					objCountryIssueTT.selectByVisibleText(MasterDataReader.StandardIDDetails.get("CountryOfIssue"));
					driver.findElement(By.id("id7")).sendKeys(MasterDataReader.StandardIDDetails.get("ExpiryDate"));
					driver.findElement(By.id("addinfoid1")).sendKeys(MasterDataReader.StandardIDDetails.get("SAL1"));
					driver.findElement(By.id("addinfoid2")).sendKeys(MasterDataReader.StandardIDDetails.get("SAL2"));
					driver.findElement(By.id("addinfoid3")).sendKeys(MasterDataReader.StandardIDDetails.get("City"));
					driver.findElement(By.id("addinfoid4")).sendKeys(MasterDataReader.StandardIDDetails.get("State"));
					driver.findElement(By.id("addinfoid5")).sendKeys(MasterDataReader.StandardIDDetails.get("PostCode"));
					Select objCountryRefuseTT = new Select(driver.findElement(By.id("addinfoid6")));
					objCountryRefuseTT.selectByVisibleText(MasterDataReader.StandardIDDetails.get("CountryOfIssue"));
					
					if(isDisplayAdditionalFields()){
						
						Select objAddNationalityTT = new Select(driver.findElement(By.id("addinfoid7")));
						objAddNationalityTT.selectByVisibleText(MasterDataReader.StandardIDDetails.get("AdditionalNationality"));
						Select objOccupationTT = new Select(driver.findElement(By.id("addinfoid8")));
						objOccupationTT.selectByVisibleText(MasterDataReader.StandardIDDetails.get("Occupation"));						
						driver.findElement(By.id("addinfoid9")).sendKeys(MasterDataReader.StandardIDDetails.get("SourceOfFundsExplanation"));
						txtPurposeOfTransaction.sendKeys(MasterDataReader.StandardIDDetails.get("PurposeOfTransaction"));
												
					}
					
				}
				
				
				
				lnkConfirm.click();
				driver.findElement(By.id("continueLink")).click();
				
				isPEPCheckAlertDisplayed();
				}
	}
	
	/**
	 * @author jachakN
	 * For Checking the Settlement Method 
	 * Added method for handling additional info when order amount is greater than 10k
	 */
	public void processCaptureSettlemnt() {
			
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
	
	
	
	/**
	 * @author jachakN 
	 * Added method for handling when standard ID Message is displayed
	 */

	public void standardIdMessageDisplayPage(String productType)	{
		
		try {
			if(msgError.isDisplayed())
			{
				
				enterStandardIDDetails(productType);
			}
			

		}
		
		catch(FoxwebAutoException e){
		throw e ;
		}
		catch (Exception e) {
			//e.printStackTrace();			
			btnNext1.click();
			
										
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
				throw new FoxwebAutoException("Out of Stock");
		}
	}

	/**
	 * @author jachakN 
	 * Added method for handling PEP/SanctionCheck Hit
	 */
	
	public void isPEPCheckAlertDisplayed(){		
		
		boolean alertPresent = wrapper.isAlertPresent();
		
		Assert.assertTrue(alertPresent);
		
		boolean isPrimeHitAlertPresent = false;
				
		try {
			String popUpTransactionDeclineMessage = driver.switchTo().alert().getText();
			
			//Alert alert = driver.switchTo().alert();
			//String popUpTransactionDeclineMessage = alert.getText();
								
			if (popUpTransactionDeclineMessage.contains("The transaction could not be completed at this time.")){
				isPrimeHitAlertPresent = true;
			}
			driver.switchTo().alert().accept();
			
		} catch (Exception e) {
			
		}
		
		finally {
			if(isPrimeHitAlertPresent){
				
				throw new FoxwebAutoException("PEP/Sanction Check Hit");
			}
			
			
		}
				
	}


	/**Author @jachakN
	Added Enter order details method for CCN, TT and draft
	*/
	public void enterOrderDetailsForeignCash(String currency, String fgnamount, String custName) {
		
		Select selFgnCurrency  = new Select(selectForeignCurrency);
		selFgnCurrency.selectByVisibleText(currency);
		selectForeignCurrency.sendKeys(Keys.TAB);
		txtForeignAmount.clear();
		driver.switchTo().alert().accept();
		txtForeignAmount.sendKeys(fgnamount);
		txtForeignAmount.sendKeys(Keys.TAB);
		txtCustName.clear();
		txtCustName.sendKeys(custName);
		 
	}
	
	public void enterOrderDetailsTelegraphicTransfer(String currency, String fgnamount) {
		Select selFgnCurrency  = new Select(selectForeignCurrency);
		selFgnCurrency.selectByVisibleText(currency);
		selectForeignCurrency.sendKeys(Keys.TAB);
		//txtForeignAmount.clear();
		//driver.switchTo().alert().accept();
		txtForeignAmount.sendKeys(fgnamount);
		driver.findElement(By.name("customer")).sendKeys(MasterDataReader.foxwebOrderDetails.get("SendingClientFullName"));
		driver.findElement(By.name("senderaccount")).sendKeys(MasterDataReader.foxwebOrderDetails.get("SendersAccount"));
		driver.findElement(By.name("ataddr1")).sendKeys(MasterDataReader.foxwebOrderDetails.get("Sender'sStreetAddress"));
		driver.findElement(By.name("atcity")).sendKeys(MasterDataReader.foxwebOrderDetails.get("Sender'sCity"));
		driver.findElement(By.name("atstate")).sendKeys(MasterDataReader.foxwebOrderDetails.get("Sender'sState"));
		driver.findElement(By.name("atpcode")).sendKeys(MasterDataReader.foxwebOrderDetails.get("Sender'sPostcode"));
		Select selSndrCountry = new Select(selSenderCountry);
		selSndrCountry.selectByVisibleText(MasterDataReader.foxwebOrderDetails.get("Sender'sCountry"));		
		driver.findElement(By.name("atphone")).sendKeys(MasterDataReader.foxwebOrderDetails.get("Sender'sPhone"));
		driver.findElement(By.name("swiftcode")).sendKeys(MasterDataReader.foxwebOrderDetails.get("BeneficiaryBankSwiftCode"));
		driver.findElement(By.name("bankcode")).sendKeys(MasterDataReader.foxwebOrderDetails.get("BeneficiaryBankCode"));
		driver.findElement(By.name("bank")).sendKeys(MasterDataReader.foxwebOrderDetails.get("BeneficiaryBankName"));
		driver.findElement(By.name("address1")).sendKeys(MasterDataReader.foxwebOrderDetails.get("BeneficiaryBankAddress"));
		driver.findElement(By.name("bankcity")).sendKeys(MasterDataReader.foxwebOrderDetails.get("BeneficiaryBankCity"));
		Select selBnkCountry = new Select(selBankCountry);
		selBnkCountry.selectByVisibleText(MasterDataReader.foxwebOrderDetails.get("BeneficiaryBankCountry"));		
		driver.findElement(By.name("account")).sendKeys(MasterDataReader.foxwebOrderDetails.get("BeneficiaryBankAccountNo/IBAN"));
		driver.findElement(By.name("payee")).sendKeys(MasterDataReader.foxwebOrderDetails.get("BeneficiaryFullName"));
		driver.findElement(By.name("altaddress1")).sendKeys(MasterDataReader.foxwebOrderDetails.get("Beneficiary'sStreetAddress"));
		driver.findElement(By.name("payeecity")).sendKeys(MasterDataReader.foxwebOrderDetails.get("Beneficiary'sSuburb/City/Town"));
		driver.findElement(By.name("payeestate")).sendKeys(MasterDataReader.foxwebOrderDetails.get("Beneficiary'sState"));
		driver.findElement(By.name("payeepostcode")).sendKeys(MasterDataReader.foxwebOrderDetails.get("Beneficiary'sPostcode"));
		Select selBnfciaryCountry = new Select(selBeneficiaryCountry);
		selBnfciaryCountry.selectByVisibleText(MasterDataReader.foxwebOrderDetails.get("Beneficiary'sCountry"));
	}
	
	public void enterOrderDetailsDraft(String currency, String fgnAmount){
		Select selFgnCurrency  = new Select(selectForeignCurrency);
		selFgnCurrency.selectByVisibleText(currency);
		selectForeignCurrency.sendKeys(Keys.TAB);
		//txtForeignAmount.clear();
		//driver.switchTo().alert().accept();
		txtForeignAmount.sendKeys(fgnAmount);
		driver.findElement(By.name("customer")).sendKeys(MasterDataReader.foxwebOrderDetails.get("SendingClientFullName"));
		driver.findElement(By.name("ataddr1")).sendKeys(MasterDataReader.foxwebOrderDetails.get("Sender'sStreetAddress"));
		driver.findElement(By.name("atcity")).sendKeys(MasterDataReader.foxwebOrderDetails.get("Sender'sCity"));
		driver.findElement(By.name("atstate")).sendKeys(MasterDataReader.foxwebOrderDetails.get("Sender'sState"));
		driver.findElement(By.name("atpcode")).sendKeys(MasterDataReader.foxwebOrderDetails.get("Sender'sPostcode"));
		Select selSndrCountry = new Select(selSenderCountry);
		selSndrCountry.selectByVisibleText(MasterDataReader.foxwebOrderDetails.get("Sender'sCountry"));	
		driver.findElement(By.name("payee")).sendKeys(MasterDataReader.foxwebOrderDetails.get("BeneficiaryFullName"));
	}
	
		
	}
