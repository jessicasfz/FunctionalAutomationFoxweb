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
	
	@FindBy(css=".message_error")
	WebElement msgError;

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

	public PreviewRepurchaseDetailsPage enterRepurchaseDetails(String quantity, String denomination) throws InterruptedException 
	{
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

		btnNext1.click();


		Thread.sleep(1000);
		
		try {
			if(msgError.isDisplayed())
			{
				OrderDetailsPage obj = new OrderDetailsPage(driver);
			//to be modified--	obj.enterStandardIDDetails();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			btnNext1.click();
			e.printStackTrace();
		}
		return new PreviewRepurchaseDetailsPage(driver).get();



	}




}
