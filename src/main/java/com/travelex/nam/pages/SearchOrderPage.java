package com.travelex.nam.pages;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

@SuppressWarnings("unused")
public class SearchOrderPage extends LoadableComponent<SearchOrderPage> {
	
	static Logger logger = Logger.getLogger(SearchOrderPage.class.getName());
	WebDriver driver;
	
	@FindBy(name = "confirmationNumber" )
	WebElement txtConfirmationNumber;
	
	@FindBy(css = "input[tabindex='11'][type='reset']")
	WebElement btnClearTop;
	
	@FindBy(css = "input[tabindex='2']")
	WebElement btnSearchTop;
	
	@FindBy(name = "orderTypes")
	WebElement rbOrderType;
	
	@FindBy(name = "searchFrom")
	WebElement dtFrom;
	
	@FindBy(name = "searchTo")
	WebElement dtTo;
	
	@FindBy(name = "tellerLastName")
	WebElement txtTellerLastName;
	
	@FindBy(name = "tellerFirstName")
	WebElement txtFirstName;
	
	@FindBy(name = "customerLastName")
	WebElement txtCustomerLastName;
	
	@FindBy(name = "orderStatusId")
	WebElement lstOrderStatus;
	
	@FindBy(css = "input[tabindex='11'][type='button']")
	WebElement btnClearBottom;
	
	@FindBy(css = "input[tabindex='10']")
	WebElement btnSearchBottom;
	
	@FindBy(xpath = "//td[contains(text(),'Search Order Result')]")
	WebElement tblSearchOrderResult;
	
	public SearchOrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@Override
	public void isLoaded(){
		
	}
	
	@Override
	public void load(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void searchWithConfirmationNumber(String confirmationNumber) {
		confirmationNumber = "USD8C975BB";
		btnClearTop.click();
		txtConfirmationNumber.sendKeys(confirmationNumber);
		btnSearchTop.click();
	}
	
	public void searchWithFromAndTodDate(String orderType,String fromDate, String toDate, String tellerLastName,
			String firstName, String customerLastName, String orderStatus ) {
		
		btnClearBottom.click();
		if(!rbOrderType.isSelected()){
			rbOrderType.click();
		}
		dtFrom.sendKeys(fromDate);
		dtTo.sendKeys(toDate);
		txtTellerLastName.sendKeys(tellerLastName);
		txtFirstName.sendKeys(firstName);
		txtCustomerLastName.sendKeys(customerLastName);
		
		Select orderStatusList = new Select(lstOrderStatus);
		orderStatusList.selectByVisibleText(orderStatus);
	
		btnSearchBottom.click();
	}

	public void searchOrderResultsPage(String ConfirmationNumber) {
		ConfirmationNumber = "USD8C975BB";				
		List<WebElement> headerValues =driver.findElements(By.cssSelector(".normal tbody  tr[class='tableHeader'] td"));
		List<WebElement> rowsValues =driver.findElements(By.cssSelector(".normal tbody  tr[class='medBold'] td"));
		List<WebElement> rowsCount =driver.findElements(By.cssSelector(".normal tbody  tr[class='medBold']"));
		List<WebElement> columnCount=driver.findElements(By.cssSelector(".normal tbody  tr[class='medBold'] td"));
		
		HashMap<String,String> SearchOrderValues = new HashMap<String,String>();
		
		if(rowsCount.size()==1){
			if(ConfirmationNumber.contains(rowsValues.get(0).getText())){
				for(int i=0;i<=headerValues.size()-1;i++){
					SearchOrderValues.put(headerValues.get(i).getText(), rowsValues.get(i).getText());
					Reporter.log("Key :::    " + headerValues.get(i).getText() +"    Value :::       " + rowsValues.get(i).getText());
				}
			}else{
				Assert.fail("Expected Order Is Not Exist");
			}			
		}else{
			Assert.fail("Search Order Records Are Not Matched");
		}			
	}		
}
