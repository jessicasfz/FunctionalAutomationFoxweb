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
		lnkPassport.click();
		//if(!MasterDataReader.StandardIDDetails.keySet().isEmpty()){
		Select objSelPrivacyScript = new Select(selPrivacyScript);
		//objSelPrivacyScript.selectByValue("Yes");

		objSelPrivacyScript.selectByValue(MasterDataReader.StandardIDDetails.get("PleaseSelect"));


		//txtFname.sendKeys("Fname");
		txtFname.sendKeys(MasterDataReader.StandardIDDetails.get("Fname"));
		//txtLname.sendKeys("Lname");
		txtLname.sendKeys(MasterDataReader.StandardIDDetails.get("Lname"));
		//txtDOB.sendKeys("02/02/2000");
		txtDOB.sendKeys(MasterDataReader.StandardIDDetails.get("DOB"));
		//txtIdNo.sendKeys("AutoID01");
		txtIdNo.sendKeys(MasterDataReader.StandardIDDetails.get("IDNo"));
		Select objCountryIssue = new Select(selCountryOfIssue);
		//objCountryIssue.selectByVisibleText("Australia");
		objCountryIssue.selectByVisibleText(MasterDataReader.StandardIDDetails.get("CountryOfIssue"));
		//txtExpiryDate.sendKeys("10/08/2020");
		txtExpiryDate.sendKeys(MasterDataReader.StandardIDDetails.get("ExpiryDate"));
		//txtStrAddress1.sendKeys("SAL1");
		txtStrAddress1.sendKeys(MasterDataReader.StandardIDDetails.get("SAL1"));
		//txtStrAddress2.sendKeys("SAL2");
		txtStrAddress2.sendKeys(MasterDataReader.StandardIDDetails.get("SAL2"));
		//txtCity.sendKeys("City");
		txtCity.sendKeys(MasterDataReader.StandardIDDetails.get("City"));
		//txtState.sendKeys("State");
		txtState.sendKeys(MasterDataReader.StandardIDDetails.get("State"));
		//txtPostCode.sendKeys("909999");
		txtPostCode.sendKeys(MasterDataReader.StandardIDDetails.get("PostCode"));
		Select objCountryRefuse = new Select(selAddCountry);
		//objCountryRefuse.selectByVisibleText("Australia");
		objCountryRefuse.selectByVisibleText(MasterDataReader.StandardIDDetails.get("CountryOfIssue"));
		lnkConfirm.click();
		driver.findElement(By.id("continueLink")).click();
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
