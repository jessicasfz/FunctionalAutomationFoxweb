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

	@FindBy(id="id3")
	WebElement txtLname;

	@FindBy(id="id4")

	WebElement txtDOB;

	@FindBy(id="id5")
	WebElement txtIdNo;

	@FindBy(id="id7")
	WebElement txtExpiryDate;

	@FindBy(id="addinfoid1")

	WebElement txtStrAddress1;


	@FindBy(id="addinfoid2")

	WebElement txtStrAddress2;


	@FindBy(id="addinfoid3")

	WebElement txtCity;


	@FindBy(id="addinfoid4")

	WebElement txtState;

	@FindBy(id="addinfoid5")

	WebElement txtPostCode;

	@FindBy(id="id6")
	WebElement selCountryOfIssue;

	@FindBy(id="addinfoid6")
	WebElement selAddCountry;


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
		// TODO Auto-generated method stub

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
		btnNext.click();  //Details Screen for Local Till
		try {
			if(msgError.isDisplayed())
			{
				enterStandardIDDetails();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			btnNext1.click();
			e.printStackTrace();
		}


		//btnNext1.click();
		return new PreviewDetailsPage(driver).get();

	}

	public void enterStandardIDDetails() {
		btnNext1.click();
		String skipidcheck = MasterDataReader.foxwebOrderDetails.get("SkipId");
		if (skipidcheck.equals("Yes"))
				{
				chkboxSkipId.click();
				driver.findElement(By.id("skipIdReasonTextBox")).sendKeys("ID Check Not Required");
				driver.findElement(By.id("continueLink")).click();
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
		lnkConfirm.click();
		driver.findElement(By.id("continueLink")).click();
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



}
