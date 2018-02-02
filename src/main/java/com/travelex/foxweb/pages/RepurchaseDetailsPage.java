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
public class RepurchaseDetailsPage extends LoadableComponent<RepurchaseDetailsPage>{	

	private WebDriver driver;

	private WebDriverWrapper wrapper ;
	private int timeOutPeriod = 3000;
	private int waitTime=300;

	@FindBy(id="next")
	WebElement btnNext1;
	
	@FindBy(name="next1")
	WebElement btnNext2; //Added by @jachakN for document page
	
	@FindBy(css=".message_error")
	WebElement msgError;
	
	@FindBy(id="forexAmt")
	WebElement txtForeignAmount; //Added by Author @jachakN

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}


	@FindBy(css="input[type='text'][name='customer']")
	WebElement txtCustName;



	public RepurchaseDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(txtCustName, timeOutPeriod);
		if(txtCustName.isDisplayed()){
			isPageLoaded = true;
		}
	}



	public boolean isRepurchasePageLoaded(){
		boolean isRepurchasePageLoaded = false;
		if(txtCustName.isDisplayed()){
			isRepurchasePageLoaded = true;
		}
		return isRepurchasePageLoaded;
	}


	@Override
	protected void load() {
	}
	
	/**@jachakN
	 * Change in code for RTN document product as well as capture settlement method
	 **/

	public PreviewRepurchaseDetailsPage enterRepurchaseDetails(String quantity, String denomination, String fgnAmount ) throws InterruptedException {
	
		String productType = MasterDataReader.foxwebOrderDetails.get("ProductType");
		
		boolean isIdCheckRequired = false;
		
		
		if ("Foreign Cash".equals(productType)){
			
			enterRepurchaseDetailsForeignCash(quantity, denomination);
			
			btnNext1.click();			
			
			OrderDetailsPage obj = new OrderDetailsPage(driver);
			obj.standardIdMessageDisplayPage(productType);
			
		}
		
		else if ("Documents".equals(productType)){
			
				
			enterRepurchaseDetailsDocument(fgnAmount);
						
			btnNext2.click();
						
			OrderDetailsPage obj = new OrderDetailsPage(driver);
			obj.enterStandardIDDetails(productType);
			
		}
		
			
		
		//Thread.sleep(1000);
		
				
		return new PreviewRepurchaseDetailsPage(driver).get();
	
	}
	
	/**@jachakN
	 * added separate methods for different product (CCN and Document)
	 **/
	

	public void enterRepurchaseDetailsForeignCash(String quantity, String denomination){		
		txtCustName.sendKeys("AutoCustomer1");
		String[] denominationlist = denomination.split(",");
		String[] quantitylist = quantity.split(",");            
		int denomsCount = denominationlist.length;
		System.out.println(denomsCount);
		for (int j=0;j<=denomsCount-1;j++) {
			String quantityValue = quantitylist[j].trim();
			String denominationValue = denominationlist[j].trim();
			driver.findElement(By.xpath("//*[normalize-space(text())='"+ denominationValue +"']/../td[2]/input")).sendKeys(quantityValue);
		}

		
	}
	
	public void enterRepurchaseDetailsDocument(String fgnAmount){
		
		txtForeignAmount.sendKeys(fgnAmount);
		driver.findElement(By.name("cheqno")).sendKeys(MasterDataReader.foxwebOrderDetails.get("ChequeNumber"));
		driver.findElement(By.name("bank")).sendKeys(MasterDataReader.foxwebOrderDetails.get("BankDrawnOn"));
		driver.findElement(By.name("account")).sendKeys(MasterDataReader.foxwebOrderDetails.get("BeneficiaryBankAccountNo/IBAN"));
		driver.findElement(By.name("beneficiary")).sendKeys(MasterDataReader.foxwebOrderDetails.get("BeneficiaryFullName"));
		driver.findElement(By.name("drawer")).sendKeys(MasterDataReader.foxwebOrderDetails.get("SendingClientFullName"));
		driver.findElement(By.name("customer")).sendKeys(MasterDataReader.foxwebOrderDetails.get("CustomerName"));
		
		
	}



}
