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
public class ProcessTransactionPage extends LoadableComponent<ProcessTransactionPage>{	

	private WebDriver driver;

	private WebDriverWrapper wrapper ;
	private int timeOutPeriod = 3000;
	private int waitTime=300;

	@FindBy(name="transactiontypeid")
	WebElement transtype;
	
	@FindBy(name="currency")
	WebElement selcurrency;
	
	@FindBy(id="servicecentreentity")
	WebElement selServiceCentre;

	@FindBy(id="main_next")
	WebElement btnSearch;

	@FindBy(xpath="//h3[contains(text(),'Process Transactions')]")
	WebElement labelProcTrans;
	
	@FindBy(linkText = "Process  ")
	WebElement btnProcess;
	
	@FindBy(xpath="//span[contains(text(),'transaction(s) have been sent for processing. To view the status, please click on Batch ID')]")
	WebElement msgSuccessProcTrans;

	public WebDriver getDriver() {
		return driver;
	}
	public ProcessTransactionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;

	}


	@Override
	protected void isLoaded()  {
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(labelProcTrans, timeOutPeriod);
		if(labelProcTrans.isDisplayed()){
			isPageLoaded = true;
		}

	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}
	public void enterProcessTransactionDetail(String trnstype) {

		Select seltranstype = new Select(transtype);
		seltranstype.selectByVisibleText(trnstype);
		
		Select objselcurrency = new Select(selcurrency);
		objselcurrency.selectByVisibleText(MasterDataReader.foxwebOrderDetails.get("ForeignCurrency"));
		
		Select objselServiceCntre = new Select(selServiceCentre);
		objselServiceCntre.selectByVisibleText(MasterDataReader.foxwebOrderDetails.get("ServiceCentre"));
		
		btnSearch.click();
		//write code to search from the table for a particular order id and process it
		WebElement Webtable=driver.findElement(By.id("processTransTable")); // Replace TableID with Actual Table ID or Xpath


		List<WebElement> TotalRowCount=Webtable.findElements(By.cssSelector("#processTransTable>tbody>tr"));

		System.out.println("No. of Rows in the WebTable: "+TotalRowCount.size());
		int RowIndex=1;
		for(WebElement rowElement:TotalRowCount)
		{
		      List<WebElement> TotalColumnCount=rowElement.findElements(By.xpath("td"));
		      int ColumnIndex=1;
		      for(WebElement colElement:TotalColumnCount)
		      {
		    	  
		    	  if(colElement.getText().matches(MasterDataReader.foxwebOrderDetails.get("OrderNo")))
		    		  
		    	  {
		    		  System.out.println("Row "+RowIndex+" Column "+ColumnIndex+" Data "+colElement.getText());
		    		  driver.findElement(By.cssSelector("#processTransTable>tbody>tr:nth-child("+RowIndex+")>td:nth-child(1)>input")).click();
		    	  }
		    	  
		       
		           ColumnIndex=ColumnIndex+1;
		       }
		      RowIndex=RowIndex+1;
		 }


		
	}
	public void clickOnProcessTransactionButton() {
		btnProcess.click();
		
	}
	public boolean verifyProcessTransactionConfirmationmessage()
	{    
		driver.switchTo().alert().accept();
		wrapper.waitForElementToBeDisplayed(msgSuccessProcTrans, 30);
		boolean status = msgSuccessProcTrans.isDisplayed();
		return status;
		
	}
	
	

}
