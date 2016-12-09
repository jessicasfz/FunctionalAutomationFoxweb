package com.travelex.framework.utilities;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import com.google.common.base.Predicate;
import java.awt.Robot;


public class WebdriverWrapper {	
	
	Utilities utils = new Utilities();
	public static WebDriver getWebDriver(){
		 return   DriverFactory.getDriver();
	}
	public WebDriverWait getDriverWait(){
		 return   DriverFactory.getWait();	
	}

/*
	'***************************************************************************************************
	'Description 	 : This function is useful to enter text into edit box
	'InputParameters : ElementName, identifier, textTobeEntered and RemoteWebDriver object
	***************************************************************************************
*/
	
	public void setTextValue(Locator loc, String textTobeEntered){
		try{			
			WebElement element = null;
			element=getWebElement(loc);
			
			element.clear();
			element.sendKeys(textTobeEntered);
			Robot rb =new Robot();
			rb.keyPress(java.awt.event.KeyEvent.VK_TAB);
			
			Reporter.log( "Enter Value in " +loc.getKey()+" text field "+textTobeEntered+ " entered successfully");	
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Enter Value in " +loc.getKey()+" text field "+textTobeEntered+"</font></I></B>");
			Assert.fail( "Enter Value in " +loc.getKey()+" text field "+textTobeEntered+ "Found Exception while entering value into "+e.getMessage());			
		}
			
	}

	
	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to enter text into edit box
	'InputParameters : ElementName, identifier, textTobeEntered and RemoteWebDriver object
	*************************************************************************************
*/
	
	public void overrideElementText(Locator loc, String textTobeEntered){

		try{	
			WebElement element = null;
			element=getWebElement(loc);
			Thread.sleep(500);
			element.sendKeys(Keys.chord(Keys.CONTROL, "a"), textTobeEntered);
			element.sendKeys(Keys.TAB);
			Reporter.log( "Enter Value in " +loc.getKey()+" text field "+textTobeEntered+ " entered successfully");	
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Enter Value in " +loc.getKey()+" text field "+textTobeEntered+ "</font></I></B>");
			Assert.fail( "Enter Value in " +loc.getKey()+" text field "+textTobeEntered+ "Found Exception while entering value into "+e.getMessage());			
		}
			
	}
	




	public String getUniqueTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaadd_MMM_yyyy");
		Date date = new Date();
		return (dateFormat.format(date));
	}


	/*
	'***************************************************************************************************
	'Description 	 : This function is to Check the WebElement Is Displayed Or Not
	'InputParameters : Locater,Time
	'OutPutParameters: None
	'Author			 : PRAVEEN KUMAR REDDY
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/

	public WebElement waitForElementToBeDisplayed(final WebElement element,int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(getWebDriver(), timeOutPeriod);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);

		return webDriverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				try {
					if (element.isDisplayed())
						return element;
					else
						return null;
				} catch (NoSuchElementException ex) {
					return null;
				} catch (StaleElementReferenceException ex) {
					return null;
				} catch (NullPointerException ex) {
					return null;
				}
			}

		});
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function Will wait Upto Specified Time and check Either WebElement is Displayed Or Not
	'InputParameters : Locater,Time
	'OutPutParameters: WebElement
	'Author			 : PRAVEEN KUMAR REDDY
	'Date		     : 23 March 2016
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/
	
	public WebElement waitForElementToBeDisplayed(final By by, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(getWebDriver(), timeOutPeriod);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
		return webDriverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				try {
					WebElement element = driver.findElement(by);
					if (element.isDisplayed())
						return element;
					else
						return null;
				} catch (NoSuchElementException ex) {
					return null;
				} catch (StaleElementReferenceException ex) {
					return null;
				} catch (NullPointerException ex) {
					return null;
				}
			}

		});
	}

	/*
	'***************************************************************************************************
	'Description 	 : This function Will wait Upto Specified Time and check Either WebElement is clickable Or Not
	'InputParameters : Locater,Time
	'OutPutParameters: WebElement
	'Author			 : PRAVEEN KUMAR REDDY
	'Date		     : 21 March 2016
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/
	
	public WebElement waitForElementToBeClickable(final By by, int timeOutPeriod) {

		WebDriverWait webDriverWait = new WebDriverWait(getWebDriver(), timeOutPeriod);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
		return webDriverWait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				try {
					WebElement element = driver.findElement(by);
					if (element.isEnabled() && element.isDisplayed())
						return element;
					else
						return null;
				} catch (NoSuchElementException ex) {
					return null;
				} catch (StaleElementReferenceException ex) {
					return null;
				} catch (NullPointerException ex) {
					return null;
				}
			}

		});

	}
	


	public WebElement waitForElementToBeClickable(final WebElement element,
			int timeOutPeriod) {

		WebDriverWait webDriverWait = new WebDriverWait(getWebDriver(), timeOutPeriod);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);

		return webDriverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				try {

					if (element.isEnabled() && element.isDisplayed())
						return element;
					else
						return null;
				} catch (NoSuchElementException ex) {
					return null;
				} catch (StaleElementReferenceException ex) {
					return null;
				} catch (NullPointerException ex) {
					return null;
				}
			}

		});

	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function Will wait Upto Specified Time and check Either WebElement is Enabled Or Not
	'InputParameters : Locater,Time
	'OutPutParameters: WebElement
	'Author			 : PRAVEEN KUMAR REDDY
	'Date		     : 21 March 2016
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/

	public WebElement waitForElementToBeEnabled(final WebElement element,
			int timeOutPeriod) {

		WebDriverWait webDriverWait = new WebDriverWait(getWebDriver(), timeOutPeriod);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);

		return webDriverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				try {

					if (element.isEnabled())
						return element;
					else
						return null;
				} catch (NoSuchElementException ex) {
					return null;
				} catch (StaleElementReferenceException ex) {
					return null;
				} catch (NullPointerException ex) {
					return null;
				}
			}

		});

	}

	
	public WebElement waitForElementToBeEnabled(final By by, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(getWebDriver(), timeOutPeriod);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
		return webDriverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				try {
					WebElement element = driver.findElement(by);
					if (element.isEnabled())
						return element;
					else
						return null;
				} catch (NoSuchElementException ex) {
					return null;
				} catch (StaleElementReferenceException ex) {
					return null;
				} catch (NullPointerException ex) {
					return null;
				}
			}

		});

	}

	
	/*
	'***************************************************************************************************
	'Description 	 : This function Will wait Upto Specified Time and check Either WebElement is Populated In List Or Not
	'InputParameters : Locater,Time
	'OutPutParameters: WebElement
	'Author			 : PRAVEEN KUMAR REDDY
	'Date		     : 23 March 2016
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/
	public WebElement waitForOptionToBePopulatedInList(
			final WebElement dropdownList, int timeOutPeriod) {

		WebDriverWait webDriverWait = new WebDriverWait(getWebDriver(), timeOutPeriod);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
		return webDriverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				try {
					List<WebElement> options = dropdownList.findElements(By
							.tagName("option"));
					if (options.size() >= 1) {
						return dropdownList;
					} else
						return null;

				} catch (NoSuchElementException ex) {
					return null;
				} catch (StaleElementReferenceException ex) {
					return null;
				} catch (NullPointerException ex) {
					return null;
				}
			}

		});

	}
	
	
	/*
	'***************************************************************************************************
	'Description 	 : This function Will wait Upto Specified Time and check Either WebElement Consist the Text Or Not 
	'InputParameters : Locater,Time
	'OutPutParameters: WebElement
	'Author			 : PRAVEEN KUMAR REDDY
	'Date		     : 23 March 2016
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/

	public WebElement waitForTextToAppearInTextField(
			final WebElement webElement, int timeOutPeriod) {

		WebDriverWait webDriverWait = new WebDriverWait(getWebDriver(), timeOutPeriod);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
		return webDriverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				try {
					String text = webElement.getText();
					if (text != null && text.equals("")) {
						return webElement;
					} else
						return null;

				} catch (NoSuchElementException ex) {
					return null;
				} catch (StaleElementReferenceException ex) {
					return null;
				} catch (NullPointerException ex) {
					return null;
				}
			}

		});

	}


	public boolean waitForOptionToBePresentInList(final By by, String value,
			int timeOutPeriod) {

		WebDriverWait webDriverWait = new WebDriverWait(getWebDriver(), timeOutPeriod);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
		try {
			webDriverWait.until(ExpectedConditions.textToBePresentInElement(
					getWebDriver().findElement(by), value));
			return true;
		} catch (TimeoutException e) {
			return false;
		}

		// return
		// webDriverWait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(by),
		// value));
	}

	public void waitForElementToDisapper(Locator loc, int timeOutPeriod) {
		By by=getBy(loc);
		FluentWait<By> fluentWait = new FluentWait<By>(by);
		fluentWait.pollingEvery(10, TimeUnit.MICROSECONDS);
		fluentWait.withTimeout(timeOutPeriod, TimeUnit.SECONDS);
		fluentWait.until(new Predicate<By>() {
			public boolean apply(By by) {
				try {
					return !getWebDriver().findElement(by).isDisplayed();

				} catch (NoSuchElementException ex) {
					return true;
				} catch (StaleElementReferenceException ex) {
					return true;
				}
			}
		});

	}

	public void bringElementInView(WebElement element) {
		((JavascriptExecutor) getWebDriver()).executeScript(
				"arguments[0].scrollIntoView(true);", element);

	}

	public void waitForElementToDisapper(final WebElement element,
			int timeOutPeriod) {

		FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(element);
		fluentWait.pollingEvery(10, TimeUnit.MICROSECONDS);
		fluentWait.withTimeout(timeOutPeriod, TimeUnit.SECONDS);
		fluentWait.until(new Predicate<WebElement>() {
			public boolean apply(WebElement element) {
				try {
					return !element.isDisplayed();
				} catch (NoSuchElementException ex) {
					return true;
				} catch (StaleElementReferenceException ex) {
					return true;
				}
			}
		});
	}

	public void waitForAlert(int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(getWebDriver(), timeOutPeriod);
		webDriverWait
				.ignoring(NoSuchElementException.class,
						StaleElementReferenceException.class)
				.pollingEvery(10, TimeUnit.MILLISECONDS)
				.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForAlert(int timeOutPeriod, int pollingTime) {
		WebDriverWait webDriverWait = new WebDriverWait(getWebDriver(), timeOutPeriod);
		webDriverWait
				.ignoring(NoSuchElementException.class,
						StaleElementReferenceException.class)
				.pollingEvery(pollingTime, TimeUnit.MILLISECONDS)
				.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertMessage(int timeOutPeriod) {
		waitForAlert(timeOutPeriod);
		Alert alert = getWebDriver().switchTo().alert();
		String AlertMessage = alert.getText();
		return AlertMessage;
	}

	public String acceptAlert(int timeOutPeriod, String AlertMessage) {
		waitForAlert(timeOutPeriod);
		Alert alert = getWebDriver().switchTo().alert();
	
		alert.accept();
		return AlertMessage;
	}

	public String acceptAlert(int timeOutPeriod) {
		for (int checkAttempts = 1; checkAttempts <= 3; checkAttempts++) {
			waitForAlert(timeOutPeriod);
			if (isAlertPresent()) {
				break;
			}
		}
		Alert alert = getWebDriver().switchTo().alert();
		String AlertMessage = alert.getText();
	;
		alert.accept();
		return AlertMessage;
	}

	public String dismissAlert(int timeOutPeriod) {
		waitForAlert(timeOutPeriod);
		Alert alert = getWebDriver().switchTo().alert();
		String alertMessage = alert.getText();

		alert.dismiss();
		return alertMessage;
	}

	public void acceptAlertForFirefox() {
		
			try {
				waitForAlert(20);
				getWebDriver().switchTo().alert().accept();
			} catch (Exception e) {

			
		}
	}

	public String getAlertMessage() {
		String alertMessage = new String("");
		try {
			waitForAlert(5);
		
			alertMessage = getWebDriver().switchTo().alert().getText();
			
		} catch (Exception e) {

		}
		return alertMessage;
	}

	public boolean isAlertPresentForScreenshot() {
		boolean isAlertPresent = false;
		try {
			getWebDriver().switchTo().alert();
			isAlertPresent = false;
		} catch (Exception e) {

		}
		return isAlertPresent;
	}

	public boolean isAlertPresent() {
		boolean isAlertPresent = false;
		try {
			waitForAlert(3);
			getWebDriver().switchTo().alert();
			isAlertPresent = true;
		} catch (Exception e) {

		}
		return isAlertPresent;
	}

	public boolean isAlertWithSpecifiedMessagePresent(String errorMessage) {
		boolean isAlertPresent = false;
		try {
			// waitForAlert(3);
			isAlertPresent = getWebDriver().switchTo().alert().getText()
					.contains(errorMessage);
		} catch (Exception e) {

		}
		return isAlertPresent;
	}

	public boolean isAlertNotPresent() {
		boolean isAlertNotPresent = false;
		try {
			getWebDriver().switchTo().alert();
			isAlertNotPresent = false;
		} catch (Exception e) {
			isAlertNotPresent = true;
		}
		return isAlertNotPresent;
	}

	public void explicitWait(int waitTime) {

		try {
			Thread.sleep(waitTime * 1000);
		} catch (InterruptedException e) {

		}
	}



	

	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to get text property of the object
	'InputParameters : ElementName, identifier and RemoteWebDriver object
	'OutPutParameters: text property of the object
	
	'**************************************************************************************************
*/	
	public String getTextValue(String strElementName, Locator loc){
		
		String strTextValue=null;
		try{
			WebElement element = null;
			element=getWebElement(loc);	

			strTextValue=element.getText();
			Reporter.log("Get Value from " +strElementName + " Value stored is "+strTextValue);
			
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Failed to Fetch Text Value"+loc.getKey()+ "</font></I></B>");
			Assert.fail("Found Exception while storing value " +e.getMessage());
		}
		return strTextValue;	
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to compare text property of the object with the Input parameter
	'InputParameters : ElementName, identifier,expectedValue and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public String delimiter ="\\|";
	public void verifyTextValue(String strElementName, Locator loc,String expectedValue){
		
		String strTextValue=null;
		try{
			
			
			WebElement element = null;
			element=getWebElement(loc);	
			
			strTextValue=element.getText();
			if(strTextValue.contains(",")){
				strTextValue=strTextValue.replace(",", "");
			}
			if(expectedValue.contains(",")){
				expectedValue=expectedValue.replace(",", "");
			}
			if(strTextValue.equalsIgnoreCase(expectedValue)){
				Reporter.log("Verify Text value of " +strElementName+" Expected value is "+expectedValue+" Pass Actual value  is "+strTextValue);
				System.out.println("Verify Text value of " +strElementName+" Expected value is "+expectedValue+" Pass Actual value  is "+strTextValue);
			}
			else{
				Reporter.log("Verify Text value of "+strElementName+" Expected value is "+expectedValue+" Fail Actual value  is "+strTextValue);
				System.out.println("Verify Text value of "+strElementName+" Expected value is "+expectedValue+" Fail Actual value  is "+strTextValue);
			}
			
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify Text value of " +strElementName+" Expected value is "+expectedValue+"</font></I></B>");
			Assert.fail("Verify Text value of " +strElementName+" Expected value is "+expectedValue+" Fail Found Exception while comparing "+e.getMessage());


		}
			
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to convert object's  text to float and Compares with the Input
	'InputParameters : ElementName, identifier,expectedVal and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public void verifyFloatValue(String strElementName, Locator loc,String expectedVal){

		String strTxtValue=null;
		Float expectedValue= null;
		
		try{
			
			
			if(expectedVal.contains(",")){
				expectedVal=expectedVal.replace(",", "");
			}
			WebElement element = null;
			element=getWebElement(loc);	
			expectedValue= Float.parseFloat(expectedVal);
			strTxtValue=element.getText();
			if(strTxtValue.contains(",")){
				strTxtValue=strTxtValue.replace(",", "");
			}
			Float strTextValue=Float.parseFloat(strTxtValue);
			if(Float.compare(expectedValue, strTextValue)==0){
				Reporter.log("Verify Text value of " +strElementName+" Expected value is "+expectedValue+" Pass Actual value  is "+strTextValue);	
			}
			else{
				Reporter.log("Verify Text value of " +strElementName+" Expected value is "+expectedValue+"Fail Actual value  is "+strTextValue);
			}
			
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify Text value of " +strElementName+" Expected value is "+expectedValue+"</font></I></B>");
			Assert.fail("Verify Text value of " +strElementName+" Expected value is "+expectedValue+" Fail Found Exception while comparing "+e.getMessage());
		}
			
	}

	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to verify whether Input parameter is substring of text property of the object
	'InputParameters : ElementName, identifier,expectedVal and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	
	public void verifyTextValueContains(String strElementName, Locator loc,String expectedValue ){
		
		String strTextValue=null;
		try{
			
			
			WebElement element = null;
			element=getWebElement(loc);	
			
			strTextValue=element.getText();
			if(strTextValue.contains(",")){
				strTextValue=strTextValue.replace(",", "");
			}
			if(expectedValue.contains(",")){
				expectedValue=expectedValue.replace(",", "");
			}
			if(strTextValue.trim().contains(expectedValue.trim())){
				Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify Text value of " +strElementName+" Expected : "+expectedValue+" should contain "+strTextValue+"<br> Actual value  is :"+strTextValue+" ::Passed"+"</font></I></B>");
				System.out.println("Verify Text value of " +strElementName+" Expected : "+expectedValue+" should contain "+strTextValue+" Actual value  is "+strTextValue);	
			}
			else{
				Reporter.log("<B><I><font size='4' color='Red'>"+"Verify Text value of " +strElementName+ " Expected : "+expectedValue+" should contain "+strTextValue+ "<br> Actual value  is :"+strTextValue+" ::Failed"+"</font></I></B>");
				System.out.println("Verify Text value of " +strElementName+ " Expected : "+expectedValue+" should contain "+strTextValue+ " Actual value  is :"+strTextValue);
			}
			
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Red'>"+"Verify Text value of " +strElementName+" Expected : "+expectedValue+" should contain "+strTextValue+ "</font></I></B>");
			Assert.fail( "Verify Text value of " +strElementName+" Expected : "+expectedValue+" should contain "+strTextValue+ " Fail Found Exception "+e.getMessage());
			
		}
			
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is to automate key board commands
	'InputParameters : Key and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public void sendKeys( String key,RemoteWebDriver driver){
		
		try{
			
			driver.getKeyboard().sendKeys(Keys.valueOf(key));

		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Send key command "+key+" Fail Found Exception while sending key board commands"+"</font></I></B>");
			Assert.fail( "Send key command "+key+" Fail Found Exception while sending key board commands"+e.getMessage());
			
		}
		
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function developed to get Current webDriver's title
	'InputParameters : RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public String getDriverTitle( ){
		String strTextValue=null;
		
		try{
			
			
			
			
			strTextValue=getWebDriver().getTitle();
			Reporter.log( "Get Browser Title"+strTextValue+ " Pass Value stored is "+strTextValue);
			
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Get Browser Title"+strTextValue+"Fail Found Exception while storing driver title "+"</font></I></B>");
			Assert.fail( "Get Browser Title"+strTextValue+"Fail Found Exception while storing driver title "+e.getMessage());
			
		
		}
		return strTextValue;	
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function developed to get Compare Quotient of given strings with expected
	'InputParameters : ElementName, dividend,divisor,expQuotient, accuracy and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Yogesh Nipate
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public void divideNCompare(String eleMentName, String dividend,String divisor,String expQuotient,int decimalsReq){
		
			Reporter.log("inside divideNCompare");
			String expOutput=null;
			try{
			
			String div="1";
			for(int i=0;i<decimalsReq;i++){
				div=div+"0"; 
			}
			double roundingval=Double.parseDouble(div);
			
			double eOutput=0.00;
			//eOutput=Float.parseFloat(input1)*Float.parseFloat(input2);
			eOutput=Double.parseDouble(dividend)*(1/Double.parseDouble(divisor));
			eOutput=(double) (Math.round(eOutput*roundingval)/roundingval);
			double aOutput=Double.parseDouble(expQuotient.replace(",",""));
			System.out.println("eOutput "+eOutput+" aOutput "+aOutput);
			Reporter.log("eOutput "+eOutput+" aOutput "+aOutput);
			//if(Double.compare(eOutput, aOutput)==0){
			if(String.valueOf(eOutput)==String.valueOf(aOutput) || Double.compare(eOutput, aOutput)==0){				 	System.out.println("Verify Calculation of " +eleMentName+" Expected value is "+eOutput+" Pass Actual value  is "+aOutput);
					Reporter.log( "Verify Calculation of " +eleMentName+" Expected value is "+eOutput+" Pass Actual value  is "+aOutput);	
				}
				else{
					System.out.println("Double.compare(eOutput, aOutput) "+Double.compare(eOutput, aOutput));
					System.out.println(String.valueOf(eOutput)+" = "+String.valueOf(aOutput) );
					System.out.println( "Verify Calculation of " +eleMentName+" Expected value is "+eOutput+ "Fail Actual value  is "+aOutput);
					Reporter.log( "Verify Calculation of " +eleMentName+" Expected value is "+eOutput+ "Fail Actual value  is "+aOutput);	
				}
			}catch(Exception e){
				Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify Calculation of " +eleMentName+" Expected value is "+expOutput+ " Fail Found Exception"+"</font></I></B>");
				Assert.fail( "Verify Calculation of " +eleMentName+" Expected value is "+expOutput+ " Fail Found Exception"+e.getMessage());	
			}
		
		}
	/*
	'***************************************************************************************************
	'Description 	 : This function developed to get Compare Quotient of given strings with expected
	'InputParameters : ElementName, dividend,divisor,expQuotient, accuracy and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Yogesh Nipate
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
	 */	
	public void addNCompare(String eleMentName,String firstNumber, String secondNumber,String sum,int decimalsReq, String operator){
		
			Reporter.log("inside addNCompare");
			String expOutput=null;
			try{
			String div="1";
			for(int i=0;i<decimalsReq;i++){
				div=div+"0"; 
			}
			double roundingval=Double.parseDouble(div);
			
			double eOutput=0.00;
			if(operator.equals("+")){
			eOutput=Double.parseDouble(firstNumber)+Double.parseDouble(secondNumber);
			}
			else{
				eOutput=Double.parseDouble(firstNumber)-Double.parseDouble(secondNumber);
			}
			eOutput=(double) (Math.round(eOutput*roundingval)/roundingval);
			
			double aOutput=Double.parseDouble(sum.replace(",",""));
			
			System.out.println("eOutput "+eOutput+" aOutput "+aOutput);
			
			Reporter.log("eOutput "+eOutput+" aOutput "+aOutput);
			
			 //if(Double.compare(eOutput, aOutput)==0){
			 if(String.valueOf(eOutput)==String.valueOf(aOutput) || Double.compare(eOutput, aOutput)==0){
				 	System.out.println("Verify Calculation of " +eleMentName+" Expected value is "+eOutput+" Pass Actual value  is "+aOutput);
					Reporter.log( "Verify Calculation of " +eleMentName+" Expected value is "+eOutput+" Pass Actual value  is "+aOutput);	
				}
				else{
					System.out.println("String.valueOf(eOutput)==String.valueOf(aOutput) "+String.valueOf(eOutput)+" == "+String.valueOf(aOutput)+" Double.compare(eOutput, aOutput)==0"+Double.compare(eOutput, aOutput));
					System.out.println( "Verify Calculation of " +eleMentName+" Expected value is "+eOutput+ "Fail Actual value  is "+aOutput);
					Reporter.log( "Verify Calculation of " +eleMentName+" Expected value is "+eOutput+ "Fail Actual value  is "+aOutput);	
				}
			}catch(Exception e){
				Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify Calculation of " +eleMentName+" Expected value is "+expOutput+ " Fail Found Exception"+"</font></I></B>");
				Assert.fail( "Verify Calculation of " +eleMentName+" Expected value is "+expOutput+ " Fail Found Exception"+e.getMessage());	
			}
		
		}

	/*
	'***************************************************************************************************
	'Description 	 : This function developed to get Compare Quotient of given strings with expected
	'InputParameters : ElementName, factors, ExpProduct,accuracy and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public void multiplyNCompare(String eleMentName, String factor1,String factor2,String expProduct,int decimalsReq){
		System.out.println("inside multiplyNCompare");
		Reporter.log("inside multiplyNCompare");
		String expOutput=null;
		try{
		
		String div="1";
		for(int i=0;i<decimalsReq;i++){
			div=div+"0"; 
		}
		double divisor=Double.parseDouble(div);
		
		double eOutput=0.00;
		if(factor1.contains(","))factor1=factor1.replace(",", "");
		if(factor2.contains(","))factor2=factor2.replace(",", "");	
		if(expProduct.contains(","))expProduct=expProduct.replace(",", "");
		//eOutput=Float.parseFloat(input1)*Float.parseFloat(input2);
		eOutput=Double.parseDouble(factor1)*Double.parseDouble(factor2);
		eOutput=(double) (Math.round(eOutput*divisor)/divisor);
		double aOutput=Double.parseDouble(expProduct);
		System.out.println("eOutput "+eOutput+" aOutput "+aOutput);
		System.out.println("String.valueOf(eOutput)==String.valueOf(aOutput) "+String.valueOf(eOutput)==String.valueOf(aOutput));
		// if(Double.compare(eOutput, aOutput)==0){
		 if(String.valueOf(eOutput)==String.valueOf(aOutput) || Double.compare(eOutput, aOutput)==0){
			 	System.out.println("Verify Calculation of " +eleMentName+" Expected value is "+eOutput+" Pass Actual value  is "+aOutput);
				Reporter.log( "Verify Calculation of " +eleMentName+" Expected value is "+eOutput+" Pass Actual value  is "+aOutput);	
			}
			else{
				System.out.println("Double.compare(eOutput, aOutput) "+Double.compare(eOutput, aOutput));
				System.out.println("Verify Calculation of " +eleMentName+" Expected value is "+eOutput+" Fail Actual value  is "+aOutput);
				Reporter.log( "Verify Calculation of " +eleMentName+" Expected value is "+eOutput+" Fail Actual value  is "+aOutput);	
			}
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify Calculation of " +eleMentName+" Expected value is "+expOutput+" Fail Found Exception"+e.getMessage()+"</font></I></B>");	
		}
	
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function developed to get list of the divers existing
	'InputParameters : RemoteWebDriver object
	'OutPutParameters: Existing window ID's Set
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public Set<String>  getWindowIdsSet(RemoteWebDriver driver){
		Set<String>  existingWindows=null;
		
		try{
			Thread.sleep(5000);
		 existingWindows = driver.getWindowHandles();
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Get Existing window ids list Fail Found Exception while getting window ids list "+e.getMessage()+"</font></I></B>");	
			
		
		}
		return existingWindows;
		
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to enter text in the object by handling Pop up.
						 Enters text using Robot Send keys to avoid multiple Popups(work around method)
	'InputParameters : ElementName, identifier, textTobeEntered and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/
	public void clearandSetText(String strElementName, Locator loc,String textTobeEntered){
		try{
			if(textTobeEntered == null || textTobeEntered == ""||textTobeEntered.equalsIgnoreCase("NA")){
				
				return;
			}

			
			WebElement element = null;
			element=getWebElement(loc);
			 Thread.sleep(2000);
			element.clear();
			 Thread.sleep(1000);
			 Alert alert = getWebDriver().switchTo().alert();
			 System.out.println("Alert Test "+ alert.getText());
			 System.out.println();
			 alert.accept();
			 Thread.sleep(1000);
			 element.sendKeys(textTobeEntered);
			 element.sendKeys(Keys.TAB);
			System.out.println("Test");
			//element.clear();
			 /* Actions act = new Actions(getWebDriver());
				 act.clickAndHold(element).build().perform();
				  Thread.sleep(3000);
				  Robot r = new Robot();
			        r.keyPress(KeyEvent.VK_CONTROL); 
			        r.keyPress(KeyEvent.VK_A);
			        r.keyPress(KeyEvent.VK_DELETE);
			        r.keyRelease(KeyEvent.VK_DELETE);
			        r.keyRelease(KeyEvent.VK_A);
			        r.keyRelease(KeyEvent.VK_CONTROL);			
				  Thread.sleep(3000);*/
			System.out.println("Enter Value in " +strElementName+" text field "+textTobeEntered+" Pass Value "+textTobeEntered+ " entered successfully");
			Reporter.log( "Enter Value in " +strElementName+" text field "+textTobeEntered+" Pass Value "+textTobeEntered+ " entered successfully");	
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Enter Value in " +strElementName+" text field "+textTobeEntered+" Fail Found Exception while entering value into "+strElementName+" "+e.getMessage()+"</font></I></B>");
			System.out.println("Enter Value in " +strElementName+" text field "+textTobeEntered+" Fail Found Exception while entering value into "+strElementName+" "+e.getMessage());
		}
		
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function developed to get ID of the in focus webDriver
	'InputParameters : RemoteWebDriver object
	'OutPutParameters: ID of the in Focus web driver
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public String getWindowId(RemoteWebDriver driver){
		
		String existingWindowID=null;
		
			try{
			existingWindowID = driver.getWindowHandle();
			}catch(Exception e){
				Reporter.log("<B><I><font size='4' color='Blue'>"+"Get current  window id Fail Found Exception while getting window id"+e.getMessage()+"</font></I></B>");
				
			}
		
			return existingWindowID;
			
		}
/*
	'***************************************************************************************************
	'Description 	 : This function developed to switch to newly opened webdriver.
	'InputParameters : Set<String>  winIDs and  RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public void switchToNewlyOpenedWindow(Set<String>  winIDs,RemoteWebDriver driver){
		
		try{
			Set<String> afterWindow = driver.getWindowHandles();
			//remove all the handles from before the popup window appears
			afterWindow.removeAll(winIDs);
			//there should be only one window handle left
			if(afterWindow.size() == 1) {
			         driver.switchTo().window((String)afterWindow.toArray()[0]);
			        
			         Thread.sleep(1000);
			          Reporter.log("New Window Title : " + driver.getTitle());
			         Reporter.log("New Window Url : " + driver.getCurrentUrl());
			}
			
			
			Reporter.log( "Switch to newly opened window Pass Successfully switched to new window");
			
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Switch to newly opened window Fail Found Exception while switching to new window "+e.getMessage()+"</font></I></B>");
			
		}
		
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function developed to close tab window and switch to main Window.
	'InputParameters : winDowID and  RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void returnToMainWindow(String winDowID,RemoteWebDriver driver){
		
		try{
			driver.close();
		    driver.switchTo().window(winDowID);
            Thread.sleep(1000);
            Reporter.log("New Window Title : " + driver.getTitle());
            Reporter.log("New Window Url : " + driver.getCurrentUrl());
			
			Reporter.log( "Return to main window Pass Successfully switched to main window");
			
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Return to main window Fail Found Exception while switching to main window "+e.getMessage()+"</font></I></B>");
			
		}
		
	}
/*
	'***************************************************************************************************
	'Description 	 : This function is useful to get required Attribute  of the object
	'InputParameters : ElementName, identifier ,attribute and RemoteWebDriver object
	'OutPutParameters: Attribute  of the object
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public String getAttributeValue(String strElementName, Locator loc, String attribute){
		String strTextValue=null;
		
		try{
			
			WebElement element = null;
			element=getWebElement(loc);
			
			strTextValue=element.getAttribute(attribute);
			Reporter.log( "Get "+attribute+" from " +strElementName+" "+strTextValue+" Pass Value stored is "+strTextValue);
			
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Get "+attribute+" from " +strElementName+" "+strTextValue+" Fail Found Exception while storing value "+e.getMessage()+"</font></I></B>");
			
		  
		}
		return strTextValue;	
	}
	
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to check/Uncheck a combo box
	'InputParameters : ElementName, identifier ,statuReq and RemoteWebDriver object
	'OutPutParameters: NA
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/
	public void changeCheckboxStatus(String strElementname,Locator loc, String statuReq){
				
	
				WebElement element = null;
				try{
				
				
				element=getWebElement(loc);
				if(statuReq.equalsIgnoreCase("uncheck") && element.isSelected()){
					element.click();	
				}
				if(statuReq.equalsIgnoreCase("check") && !element.isSelected()){
					element.click();
				}
				Reporter.log( "Change "+strElementname+" checkbox status "+statuReq+" Pass Checkbox is " + statuReq.toUpperCase() + "ED");
		}catch(Exception e1){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Change "+strElementname+" checkbox status"+statuReq+" Fail Found Exception "+e1.getMessage()+"</font></I></B>");
			
		}
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to verify alert's text and close it
	'InputParameters : ElementName, expectedValue ,operation and RemoteWebDriver object
	'OutPutParameters: NA
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public void  verifyAlertandClose(String elementName, String expectedValue,String operation){
			
		String alertText= null;
		
				try{
					
				Alert alert = getWebDriver().switchTo().alert();
			
			      alertText = alert.getText();
			     if (operation.equalsIgnoreCase("accept")) {
			       alert.accept();
			     } else {
			       alert.dismiss();
			     }
		if(alertText.equalsIgnoreCase(expectedValue)){
			Reporter.log( "Verify Alert "+elementName+" Expected value is "+expectedValue+" Pass Actual value  is "+alertText);	
		}
		else{
			Reporter.log( "Verify Alert Text "+elementName +" Expected value is "+expectedValue+" Fail Actual value  is "+alertText);
		}
		
	}catch(Exception e){
		Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify Alert Text  Expected value is "+expectedValue+" Fail Found Exception while comparing "+e.getMessage()+"</font></I></B>");
		
	}
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to verify alert's text and close it
	'InputParameters : ElementName, expectedValue ,operation and RemoteWebDriver object
	'OutPutParameters: NA
	'Author			 : Yogesh Nipate
	'Date		     : 21 Aug 2015
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public void  closeAlertIfExist(String till){
	System.out.println("Inside closeAlertIfExist");
	String alertText= null;
	try{
		Alert alert = getWebDriver().switchTo().alert();
		alertText = alert.getText();
		System.out.println("Text on alert box "+alertText);
		System.out.println("check "+alertText.contains("Would you like to fulfill this order from an external till"));
		if(alertText.contains("Would you like to fulfill this order from an external till")){
			if(till.contains("External")){
				alert.accept();
			}else if(till.contains("Local")){
				alert.dismiss();
			}
        
		}
        Thread.sleep(2000);
        Alert alert2 = getWebDriver().switchTo().alert();
		alertText = alert2.getText();
		System.out.println("Text on alert box "+alertText);
        alert2.accept();
	}catch(Exception e){
		Reporter.log("<B><I><font size='4' color='Blue'>"+"No alert generated"+"</font></I></B>");
		
	}
	}
	
/*
	'***************************************************************************************************
	'Description 	 : This function is developed to find check box status
	'InputParameters : ElementName, identifier and RemoteWebDriver object
	'OutPutParameters: NA
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
		
	public boolean getCheckboxStatus(String strElementname,Locator loc){
		boolean status= false;
		
		WebElement element = null;
		
		try{
		
		
		element=getWebElement(loc);
		status= element.isSelected();
		Reporter.log( "Verify checkbox status "+strElementname+" Pass Checkbox is " + status);
		}catch(Exception e1){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify checkbox status "+strElementname+" Fail Found Exception " + status+"</font></I></B>");

		  }
		
		return status;
		}
/*
	'***************************************************************************************************
	'Description 	 : This function is useful to find whether given string is sub string of Attribute value of the object
	'InputParameters : ElementName, identifier,expectedValue,attribute and RemoteWebDriver object
	'OutPutParameters: NA
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public void verifyAttributeValueContains(String strElementName, Locator loc, String expectedValue,String attribute,RemoteWebDriver driver){
		
		String strTextValue=null;
		try{
			
			WebElement element = null;
			element=getWebElement(loc);
			
			strTextValue=element.getAttribute(attribute);
			if(strTextValue.contains(",")){
				strTextValue=strTextValue.replace(",", "");
			}
			if(expectedValue.contains(",")){
				expectedValue=expectedValue.replace(",", "");
			}
			if(strTextValue.contains(expectedValue)){
				Reporter.log( "Verify "+attribute+" of " +strElementName+" Expected : "+expectedValue+" should contain "+strTextValue+" Pass Actual value  is "+strTextValue);	
			}
			else{
				Reporter.log( "Verify "+attribute+" of " +strElementName+" Expected : "+expectedValue+" should contain "+strTextValue+" Fail Actual value  is "+strTextValue);
			}
			
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify "+attribute+" of " +strElementName+" Expected : "+expectedValue+" should contain "+strTextValue+" Fail Found Exception "+e.getMessage()+"</font></I></B>");
			
		}
		
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to convert object's  Attribute value to float and Compares with the Input
	'InputParameters : ElementName, identifier,expectedValue,attribute and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public void verifyAttributeValueasFloat(String strElementName, Locator loc, Float expectedValue,String attribute){
		
		String strTextValue=null;
		try{
			
			WebElement element = null;
			element=getWebElement(loc);
			
			strTextValue=element.getAttribute(attribute);
			if(strTextValue.contains(",")){
				strTextValue=strTextValue.replace(",", "");
			}
			
			Float strTextVal=Float.parseFloat(strTextValue);
			if(Float.compare(strTextVal, expectedValue)==0){
				Reporter.log( "Verify "+attribute+" of " +strElementName+" Expected : "+expectedValue+" Pass Actual value  is "+strTextValue);	
			}
			else{
				Reporter.log( "Verify "+attribute+" of " +strElementName+" Expected : "+expectedValue+" Fail Actual value  is "+strTextValue);
			}
			
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify "+attribute+" of " +strElementName+" Expected : "+expectedValue+" Fail Found Exception "+e.getMessage()+"</font></I></B>");
			
		}
		
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to Compare Attribute value and Input
	'InputParameters : ElementName, identifier,expectedValue,attribute and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public void verifyAttributeValue(String strElementName, Locator loc, String expectedValue,String attribute){
		
		String strTextValue=null;
		try{
			
			WebElement element = null;
			element=getWebElement(loc);
			
			strTextValue=element.getAttribute(attribute);
			if(strTextValue.contains(",")){
				strTextValue=strTextValue.replace(",", "");
			}
			if(expectedValue.contains(",")){
				expectedValue=expectedValue.replace(",", "");
			}
			
			if(strTextValue.equalsIgnoreCase(expectedValue)){
				Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify "+attribute+" of " +strElementName+" Expected : "+expectedValue+" Pass Actual value  is "+strTextValue+"</font></I></B>");	
			}
			else{
				Reporter.log( "Verify "+attribute+" of " +strElementName+" Expected : "+expectedValue+" Fail Actual value  is "+strTextValue);
			}
			
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify "+attribute+" of " +strElementName+" Expected : "+expectedValue+" Fail Found Exception "+e.getMessage()+"</font></I></B>");
			
		}
		
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to Click web element
	'InputParameters : ElementName, identifier and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void clickElement(String strElementName, Locator loc){
//		
//		
			WebElement element = null;
			element=getWebElement(loc);
		//	getWebElement(loc).click();
			if(element.isDisplayed())
			{
				element.click();
				Reporter.log("Clicking on  "+strElementName);
				System.out.println("Clicking on "+strElementName);
				try{
					Thread.sleep(2000);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
				Assert.fail(strElementName+"Element "+strElementName+" is not displayed");
	}
	
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to create webList and clicks on the required element based on Index or text of the object
	'InputParameters : ElementName, identifier,identification and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void clickWebListElement(String strElementName, Locator loc,String identification){
		
		try{
			
			String[] identity=identification.split(":");
			List<WebElement> lst= getWebList(loc);
			WebElement element = null;
			if(identity[0].equalsIgnoreCase("index")){
				element=lst.get(Integer.parseInt(identity[1]));
			}else if(identity[0].equalsIgnoreCase("text")){
				for(int i=0; i<lst.size();i++){
					try{
						if(lst.get(i).getText().equalsIgnoreCase(identity[1])) element=lst.get(i);
					}catch(Exception e ){
						e.printStackTrace();
					}
				}
			}
			
		if(!(element== null)) element.click();	
			Reporter.log( "Click " +strElementName+" Pass Clicked "+strElementName+" Successfully");	
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Click " +strElementName+" Fail Found Exception "+e.getMessage()+"</font></I></B>");
			
		}
			
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is to perform double click operation using Actions class
	'InputParameters : ElementName, identifier and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void doubleClick(String strElementName, Locator loc){
		
		try{
		
			Actions doubleClickAction = new Actions(getWebDriver()); 
			
			
			WebElement element = null;
			element=getWebElement(loc);
			
			doubleClickAction.moveToElement(element).doubleClick().build().perform();
			Reporter.log( "Double Click " +strElementName+" Pass Clicked "+strElementName+" Successfully");	
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Double Click " +strElementName+" Fail Found Exception "+e.getMessage()+"</font></I></B>");
		//	executionFlag=false;
		}
			
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is to select value from a Web list based on the Visible text option
	'InputParameters : ElementName, identifier,valueTobeSelected and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void selectValue(String strElementName, Locator loc,String valueTobeSelected){
		if(valueTobeSelected == null || valueTobeSelected == ""||valueTobeSelected.equalsIgnoreCase("NA")){
			return;
		}
		try{
			
			
			WebElement element = null;
			element=getWebElement(loc);
			System.out.println("Selecting "+valueTobeSelected);
			Select webCheckBox= new Select(element);
			if(valueTobeSelected.equalsIgnoreCase("EMPTY")){
				webCheckBox.selectByVisibleText("");
			}else{
				webCheckBox.selectByVisibleText(valueTobeSelected);
			}
			
			Reporter.log( "Select value from " +strElementName+" "+valueTobeSelected+" Pass Selected "+valueTobeSelected+" Successfully");	
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Select value from " +strElementName+" "+valueTobeSelected+" Fail  Found Exception while selecting value "+e.getMessage()+"</font></I></B>");	
			
		}
			
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is to select value from a Web list based on the index option
	'InputParameters : ElementName, identifier,valueTobeSelected and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void selectValueByIndex(String strElementName, Locator loc,int index ){
		String valueSelected="";
		try{
			
			
			WebElement element = null;
			element=getWebElement(loc);
			Select webCheckBox= new Select(element);
			webCheckBox.selectByIndex(index);
			valueSelected=webCheckBox.getFirstSelectedOption().getText();
			Reporter.log( "Select value from " +strElementName+" "+valueSelected+" Pass Selected "+valueSelected+" Successfully");	
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Select value from " +strElementName+" "+valueSelected+" Fail  Found Exception while selecting value "+e.getMessage()+"</font></I></B>");	
			
		}
			
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is to select value from a Web list based on the Visible text option
	'InputParameters : ElementName, identifier,valueTobeSelected and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void selectValueContains(String strElementName, Locator loc,String valueTobeSelected){
		if(valueTobeSelected == null || valueTobeSelected == ""||valueTobeSelected.equalsIgnoreCase("NA")){
			return;
		}
		try{
			
			boolean isElementFound=false;
			WebElement element = null;
			element=getWebElement(loc);
			element.getSize();
			List<WebElement> options =element.findElements(By.tagName("option"));
			
			System.out.println("Selecting "+valueTobeSelected+" inside selectValueContains");
			//Select webCheckBox= new Select(element);
			/*if(valueTobeSelected.equalsIgnoreCase("EMPTY")){
				webCheckBox.selectByVisibleText("");
			}else{*/
				for(int i=0;i<options.size();i++){
					
					if(options.get(i).getText().contains(valueTobeSelected)){
						//webCheckBox.selectByIndex(i);
						System.out.println("Selecting "+options.get(i).getText()+" inside selectValueContains");
						//webCheckBox.selectByVisibleText(options.get(i).getText());
						selectValue(strElementName,loc,options.get(i).getText());
						isElementFound=true;
						System.out.println("Breking loop in selectValueContains after "+i);
						break;
					}
				}
			//}
			if(isElementFound){
				Reporter.log( "Select value from " +strElementName+" "+valueTobeSelected+" Pass Selected "+valueTobeSelected+" Successfully");	
			}else{
				Reporter.log( "Select value from " +strElementName+" "+valueTobeSelected+" Fail  value not found in the list Items");	
			}
				
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Select value from " +strElementName+" "+valueTobeSelected+" Fail  Found Exception while selecting value "+e.getMessage()+"</font></I></B>");	
			
		}
			
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is to select value from a Web list based on value option
	'InputParameters : ElementName, identifier,valueTobeSelected and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Yogesh Nipate
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void selectByValue(String strElementName, Locator loc,String valueTobeSelected){
		if(valueTobeSelected == null || valueTobeSelected == ""||valueTobeSelected.equalsIgnoreCase("NA")){
			return;
		}
		try{
			
			
			WebElement element = null;
			element=getWebElement(loc);
			System.out.println("Selecting by value "+valueTobeSelected);
			Select webCheckBox= new Select(element);
			if(valueTobeSelected.equalsIgnoreCase("EMPTY")){
				webCheckBox.selectByValue("");
			}else{
				webCheckBox.selectByValue(valueTobeSelected);
			}
			
			Reporter.log( "Select value from " +strElementName+" "+valueTobeSelected+" Pass Selected "+valueTobeSelected+" Successfully");	
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Select value from " +strElementName+" "+valueTobeSelected+" Fail  Found Exception while selecting value "+e.getMessage()+"</font></I></B>");	
			
		}
			
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to compare value of Current selection of a Web list with Input
	'InputParameters : ElementName, identifier,expectedValue and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Yogesh Nipate
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public void verifySelectedValue(String strElementName, Locator loc,String expectedValue){
		
		try{
			WebElement element = null;
			element=getWebElement(loc);
			Select webSelectList= new Select(element);
			String item = webSelectList.getFirstSelectedOption().getText();
				if (item.trim().equals(expectedValue.trim()))
				{
				Reporter.log( "Verify Selected List value from " +strElementName+" "+ expectedValue+" Pass Selected value is"+expectedValue);
				}
				else
				{
				Reporter.log( "Verify Selected List value from " +strElementName+" "+expectedValue+" Fail Selected value "+item+" does not match "+expectedValue);
				}
		}catch(Exception e){
		Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify Selected List value from  " +strElementName+" "+expectedValue+"Fail Found Exception while verifying selected element "+e.getMessage()+"</font></I></B>");
		 
		}
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to get value of Current selection of a Web list
	'InputParameters : ElementName, identifier and RemoteWebDriver object
	'OutPutParameters: Value selected
	'Author			 : Yogesh Nipate
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public String getSelectedValue(String strElementName, Locator loc){
	String item =null;
	
		try{
			WebElement element = null;
			element=getWebElement(loc);
			Select webSelectList= new Select(element);
			item = webSelectList.getFirstSelectedOption().getText();
			Reporter.log( "Get Selected List value from " +strElementName+" "+strElementName+" Pass Selected value is "+item);
		}catch(Exception e){
		Reporter.log("<B><I><font size='4' color='Blue'>"+"Get Selected List value from " +strElementName+" "+strElementName+" Done Found Exception while verifying selected element "+e.getMessage()+"</font></I></B>");
		}
	
	return item;
	}

	/*
	'***************************************************************************************************
	'Description 	 : This function is to select value from a Web list available in web table based on the Visible text option
							Or if Object's tab is other than select
	'InputParameters : ElementName, identifier,valueTobeSelected and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void selectValueFromTable(String strElementName, Locator loc,String valueTobeSelected){
		if(valueTobeSelected == null || valueTobeSelected == ""||valueTobeSelected.equalsIgnoreCase("NA")){
			return;
		}
		try{

			

			WebElement element = null;
			element=getWebElement(loc);
			List<WebElement> options1 = element.findElements(By.tagName("option"));
			boolean isPresent = false;
			for(WebElement option : options1){
				if(option.getText().equals(valueTobeSelected)){
					element.click();
					option.click();
					isPresent = true;
					break;
				}
			}
			if(isPresent){
				Reporter.log( "Select value from " +strElementName+ " Listbox "+ valueTobeSelected+" Pass "+valueTobeSelected+" option is selected from the dropdown");
			}else{
				Reporter.log( "Select value from " +strElementName+ " Listbox "+ valueTobeSelected+ " Fail "+valueTobeSelected+" option is not selected from the dropdown");
			}
				
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Select value from " +strElementName+" "+valueTobeSelected+" Fail Found Exception while selecting value "+e.getMessage()+"</font></I></B>");	
		
		}
			
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is to find No of Business days between two Dates provided
	'InputParameters : fromDate, toDate,expectedDiff
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	
	public void bDayDiff(String fromDate, String toDate,int expectedDiff ){
		
		//boolean isPresent = false;
		try{
			DateFormat dateFormatdisplay = new SimpleDateFormat("MMMM d,yyyy");
			Date d =dateFormatdisplay.parse(fromDate);
		    List<String> strDates = Arrays.asList("April 6, 2015","April 27, 2015","May 4, 2015","May 5, 2015","May 14, 2015","May 25, 2015","December 24, 2015","December 25, 2015","December 31, 2015");
		List<Date> dates = new ArrayList<Date>(); for(int i=0;i<strDates.size();i++){ 
			try { 
				dates.add((Date)dateFormatdisplay.parse(strDates.get(i)));
		} catch (Exception e)
		{
		e.printStackTrace();
		}
		}

		//Date expDate= dateFormatdisplay.parse(toDate);
		    Calendar c = Calendar.getInstance();
		    c.setTime(d);
		    
		    c.set(Calendar.HOUR_OF_DAY,00);
		    c.set(Calendar.MINUTE,00);
		    c.set(Calendar.SECOND,0);
		    c.set(Calendar.MILLISECOND,0);
		    c.add(Calendar.DATE, expectedDiff);
		    d.setTime(c.getTime().getTime());
		    
		    dateFormatdisplay.format(d);
		    
		    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		    
		   boolean isBusinessDay=false;
		    while(!isBusinessDay){
		    if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY && !dates.contains(d)) 
		    {
		    break;
		    }
		       c.setTime(d);
		   c.add(Calendar.DATE, 1);
		   d.setTime(c.getTime().getTime());
		  dateFormatdisplay.format(d);
		    dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		   
		    }
		}catch(Exception e){
			
		}
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to make driver wait until object is Clickable
	'InputParameters : ElementName, identifier,wait object and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public void waitForElementToBeClickable(String strElementName, Locator loc){
		
		
		try{

		
		
		 getDriverWait().until(ExpectedConditions.elementToBeClickable(getBy(loc)));
	
		}catch(Exception e){
		
		Reporter.log("<B><I><font size='4' color='Blue'>"+"Wait for" +strElementName +"Fail"+ "Element  "+strElementName+" not found "+"</font></I></B>");
		}
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to find whether an element is displayed or not
	'InputParameters : ElementName, identifier and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	
	public void isElementDisplayed(String strElementName, Locator loc,RemoteWebDriver driver){
		
		
		try{
		
		
	
		By by=getBy(loc);
		
		 if(driver.findElement(by).isDisplayed()){
			 Reporter.log( "Verify Element "+strElementName+" Pass  Element exists");
		 }
		 else{
			 Reporter.log( "Verify Element "+strElementName+" Fail Element doesn't exists");	 
		 }
		
		}catch(Exception e){
			 Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify Element "+strElementName+" Fail  Exception found "+e.getMessage()+"</font></I></B>");
			 
		}
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to verify all the available options of Web list
	'InputParameters : ElementName, identifier,expectedValues and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Yogesh Nipate
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public void verifyListElements(String strElementName, Locator loc,String expectedValues){
		

		List<String> actualListValues=new ArrayList<String>();
		 List<String> expectedListValues=Arrays.asList(expectedValues.split(delimiter)); 
		try{
		

		WebElement element = null;
		element=getWebElement(loc);
		List<WebElement> allOptions = element.findElements(By.tagName("option"));	
			for(WebElement option:allOptions){
				actualListValues.add(option.getText());
			}

		 if(actualListValues.equals(expectedListValues)){
				 Reporter.log( "Verify List Values of "+strElementName+" Pass List Values populated as Expected");
			 }
			 else{
				 Reporter.log( "Verify List Values of "+strElementName+" Fail Missed values are "+expectedListValues.removeAll(actualListValues));
			}
		}catch(Exception e){
			 Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify List Values of "+strElementName+" Fail Found Exception"+"</font></I></B>");
			 
		}
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to get object's status(Displayed or Not) as return value
	'InputParameters : ElementName, identifier and RemoteWebDriver object
	'OutPutParameters: Status
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/
	public boolean isExists(String strElementName, Locator loc){
		boolean elementExists = false;
		
		
		try{

		
	
		By by=getBy(loc);
		
		 if(getWebDriver().findElement(by).isDisplayed()){
			 elementExists=true;
		 }
		
		}catch(Exception e){
			 //Reporter.log(e.getMessage());
		}
	 		return elementExists;
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to confirm object is not displayed
	'InputParameters : ElementName, identifier and RemoteWebDriver object
	'OutPutParameters: Status
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public void isAbsent(String strElementName, Locator loc,RemoteWebDriver driver){
		
		
		try{
		
		
	
		By by=getBy(loc);
		
		 if(!driver.findElement(by).isDisplayed()){
			 Reporter.log( "Verify Element "+strElementName+" Expected: Element should not be displayed Pass Element not exists");	 
		 }
		 else{
			 Reporter.log( "Verify Element "+strElementName+" Expected: Element should not be displayed Fail Element exists");	 
		 }
		
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify Element "+strElementName+" Expected: Element should not be displayed Pass Element not exists"+"</font></I></B>");	 
			
		}
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to verify whether an Element is enabled\disabled
	'InputParameters : ElementName, identifier and RemoteWebDriver object
	'OutPutParameters: Status
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void isDisabled(String strElementName, String status,Locator loc,RemoteWebDriver driver){
		
		
		try{
		
		
		WebElement element = null;
		element=getWebElement(loc);
		boolean isEnabled=element.isEnabled();
		 if((isEnabled&&status.toUpperCase().contains("ENABLED"))||(!isEnabled&&status.toUpperCase().contains("DISABLED"))){
			 Reporter.log( "Verify Element "+strElementName+" is "+status+" Expected: Element should  be "+status+" Pass Element is "+status);	 
		 }
		 else{
			 Reporter.log( "Verify Element "+strElementName+" is "+status+" Expected: Element should  be "+status+"F ail Element is not "+status);	 
		 }
		
		}catch(Exception e){
			Reporter.log( "<B><I><font size='4' color='Blue'>"+"Verify Element "+strElementName+" is "+status+" Expected: Element should  be "+status+" Fail Found Exception "+e.getMessage()+"</font></I></B>");	 
			
		}
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to make driver wait until object is disabled
	'InputParameters : ElementName, identifier,wait object and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void waitForElementToBeDisabled(String strElementName, Locator loc){
		
		Long elapsedTime = null,startTime=null;
		
		try{
	
		
		 startTime = System.currentTimeMillis();
		
		By by=getBy(loc);
		getDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(by));
		elapsedTime = (System.currentTimeMillis()-startTime)/1000;
		Reporter.log( "Wait for Invisibility of " +strElementName+" Pass Invisible after "+strElementName+" in "+elapsedTime);
		}catch(Exception e){
		elapsedTime = (System.currentTimeMillis()-startTime)/1000;
		Reporter.log("<B><I><font size='4' color='Blue'>"+"Wait for Invisibility of " +strElementName+" Fail Element  "+strElementName+" not found in "+elapsedTime+" "+e.getMessage()+"</font></I></B>");
		}
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to connect to SQL Database
	'InputParameters : URL, dbName,userName and passWord
	'OutPutParameters: Connection Object
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public Connection connectDB(String URL,String dbName,String userName,String passWord) throws SQLException{
		  Connection connect=null;  
		
		 try{	 
			Class.forName( "net.sourceforge.jtds.jdbc.Driver" );
		  connect = DriverManager.getConnection( "jdbc:jtds:sqlserver://"+URL+"/"+dbName, userName, passWord );
		   
		    
	 	}catch(Exception e){
	 		e.printStackTrace();
	 		
		}
	 
		 return connect;
	}
	
	   protected void waitForLoaderInvisibility()
       {
              try {
                     WebDriverWait webDriverWait=new WebDriverWait(getWebDriver(), 300);
                     WebElement element = getWebDriver().findElement(By.xpath("//div[contains(@class,'ajaxbg')]"));
                     for (int counter = 0; counter < 3; counter++) {
                            webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'ajaxbg')]")));
                           if (!element.isDisplayed()) break;
                     }
              }
              catch(Exception exception) {
              }
       }

	   
	/*
	'***************************************************************************************************
	'Description 	 : This function executes  SQL query and returns ResultSet Object
	'InputParameters : Connection object and SQL query
	'OutPutParameters: ResultSet Object
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public ResultSet executeSQLQuery(Connection connect,String query) throws SQLException{
		Statement stmt=null;
		  ResultSet rs=null;
		
		
		 try{	 
		   stmt = connect.createStatement();
		   rs = stmt.executeQuery(query);
		   
		    
	 	}catch(Exception e){
	 		e.printStackTrace();
	 		
		
	}
		 return rs;
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function closes given Data base connection and Result set objects
	'InputParameters : Connection object and ResultSet object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public void closeConnections(Connection con,ResultSet rs) throws SQLException{
		 
		 try{	 
			 rs.close();
			 con.close();
		    
	 	}catch(Exception e){
	 		e.printStackTrace();
		}
		
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function converts given strings to float and compares them
	'InputParameters : strElementName,expectedVal and actualVal
	'OutPutParameters: NA
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void floatCompare(String strElementName,String expectedVal,String actualVal){
		
		
		Float expectedValue= null;
		try{
			
			if(expectedVal.contains(",")){
				expectedVal=expectedVal.replace(",", "");
			}
			
			expectedValue= Float.parseFloat(expectedVal);
			
			Float strTextValue=Float.parseFloat(actualVal);
			if(Float.compare(expectedValue, strTextValue)==0){
				Reporter.log( "<B><I><font size='4' color='Blue'> Verify Text value of " +strElementName+" Expected value is "+expectedValue+" Pass Actual value  is "+strTextValue+"</font></I></B>");	
			}
			else{
				Reporter.log( "<B><I><font size='4' color='Red'> Verify Text value of " +strElementName+" Expected value is "+expectedValue+" Fail Actual value  is "+strTextValue+"</font></I></B>");
			}
			
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Red'>"+"Verify  value of " +strElementName+" Expected value is "+expectedValue+" Fail Found Exception while comparing "+e.getMessage()+"</font></I></B>");


		}
			
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function compares two given strings based on the relation provided
	'InputParameters : strElementName,expectedVal,actualVal and relation
	'OutPutParameters: NA
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void stringComp(String strElementName,String expectedVal,String  actualVal,String relation){
		
		expectedVal=expectedVal.trim();
		actualVal=actualVal.trim();
		switch(relation.toUpperCase()){
		
		case "EQUALS":
			
			if(expectedVal.equals(actualVal)){
				Reporter.log( "<B><I><font size='4' color='Blue'> Verify Text value of " +strElementName+" Expected value is "+expectedVal+" Pass Actual value  is "+actualVal+"</font></I></B>");	
			}
			else{
				Reporter.log( "<B><I><font size='4' color='Red'> Verify Text value of " +strElementName+" Expected value is "+expectedVal+" Fail Actual value  is "+actualVal+"</font></I></B>");
			}
			break;
		case "EQUALSIGNORECASE":
			if(expectedVal.equalsIgnoreCase(actualVal)){
				Reporter.log( "<B><I><font size='4' color='Blue'> Verify Text value of " +strElementName+" Expected value is "+expectedVal+" Pass Actual value  is "+actualVal+"</font></I></B>");	
			}
			else{
				Reporter.log( "<B><I><font size='4' color='Red'> Verify Text value of " +strElementName+" Expected value is "+expectedVal+" Fail Actual value  is "+actualVal+"</font></I></B>");
			}
			break;
		case "CONTAINS":
			if(expectedVal.contains(actualVal)){
				Reporter.log( "<B><I><font size='4' color='Blue'> Verify Text value of " +strElementName+" Expected "+expectedVal+" contain "+actualVal+" Pass "+ expectedVal+" contains "+actualVal+"</font></I></B>");	
			}
			else{
				Reporter.log( "<B><I><font size='4' color='Blue'> Verify Text value of " +strElementName+" Expected "+expectedVal+" contain "+actualVal+" Pass "+expectedVal+" doesn't contain "+actualVal+"</font></I></B>");
			}
			break;
		}
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to make driver wait until object is displayed on the page
	'InputParameters : ElementName, identifier,wait object and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void waitForElementToBeDisplayed(String strElementName, Locator loc){
		try{
			By by=getBy(loc);
			getDriverWait().until(ExpectedConditions.presenceOfElementLocated(by));
		}catch(Exception e){
		System.out.println( "Wait for" +strElementName+" Fail Element  "+strElementName+" not found ");
		}
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to create and return web element
	'InputParameters : identifier and RemoteWebDriver object
	'OutPutParameters: web element
	
	'
	'**************************************************************************************************
*/		
	public  WebElement getWebElement(Locator loc)
	{
		 String by=loc.getbyType().toLowerCase();
		 String ele=loc.getValue();
 		 String key=loc.getKey();
		WebElement webEle=null;
		try{
		if(by.equalsIgnoreCase("id"))
			webEle=getWebDriver().findElement(By.id(ele));
		else if(by.equalsIgnoreCase("class"))
			webEle=getWebDriver().findElement(By.className(ele));
		else if(by.equalsIgnoreCase("name"))
			webEle=getWebDriver().findElement(By.name(ele));
		else if(by.equalsIgnoreCase("link"))
			webEle=getWebDriver().findElement(By.linkText(ele));
		else if(by.equalsIgnoreCase("xpath"))
			webEle=getWebDriver().findElement(By.xpath(ele));
		else if(by.equalsIgnoreCase("css"))
			webEle=getWebDriver().findElement(By.cssSelector(ele));
		else if(by.equalsIgnoreCase("tag"))
			webEle=getWebDriver().findElement(By.tagName(ele));
		if(by.toString().equals(null))
			webEle=getWebDriver().findElement(By.xpath(ele));;
		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webEle, "border: 3px solid red;");
		}catch(Exception e){
			System.out.println("Not able to locate element :"+key +" with locator" +ele);
			System.out.println("Exception "+e);
			
		}
		
		return webEle;
}
	

	
	


	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to highlight the web element
	'InputParameters : elementName,identifier,duration  and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void elementHighlight(String elementName,Locator loc,int duration ) {
		WebElement element = null;
		element=getWebElement(loc);
		if(!(element==null)){
		for (int i = 0; i < duration; i++) {
			
			JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "color: orange; border: 3px solid orange;");
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "");
		}
		}else{
			Reporter.log( "Highlight Element "+elementName+" Fail Found Exception while accessing  "+elementName);	
		}
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to create and return Web list of given Identifier
	'InputParameters : identifier and RemoteWebDriver object
	'OutPutParameters: List<WebElement>
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/
	public List<WebElement> getWebList(Locator loc ){		
		List<WebElement> lst = null;	
				try{
					By by=getBy(loc);
					lst=getWebDriver().findElements(by);
					} catch(Exception elenotFound){
						Reporter.log("<B><I><font size='4' color='Blue'>"+"Get Web List : 'Fail' Found Exception while accessing element "+elenotFound.getMessage()+"</font></I></B>");
					}
				
				return lst;
			}
	
	public boolean verifyLinkPresent(RemoteWebDriver webDriver, String strElement){
		
		boolean exists = false;
				
		for(int interval = 0; interval < 30;interval++){
			if(webDriver.findElementsByLinkText(strElement).size() != 0 && webDriver.findElementByLinkText(strElement).isDisplayed()){
				exists = true;
				break;
			}else if(webDriver.findElementsByName(strElement).size() != 0 && webDriver.findElementByName(strElement).isDisplayed()){
				exists = true;
				break;
			}else if(webDriver.findElementsById(strElement).size() != 0 && webDriver.findElementById(strElement).isDisplayed()){
				exists = true;
				break;
			}else if(webDriver.findElementsByXPath(strElement).size() != 0 && webDriver.findElementByXPath(strElement).isDisplayed()){
				exists = true;
				break;
			}else{
				exists = false;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		//String strBrowserType = utils.getConfigValues("Browser Type");
		
		if(exists){
			return exists;
		}else{
			return exists;
		}
		
	}
	
	public boolean verifyElementText(RemoteWebDriver webDriver, String strElement, String strElementProperty, String strExpectedText){
		
		//verifyElementPresent(webDriver, strElement);
		String strActualText = null;
		
		if(strElementProperty.equalsIgnoreCase("id")){
			strActualText = webDriver.findElementById(strElement).getText();
		}	
		
		if(strElementProperty.equalsIgnoreCase("name")){
			strActualText = webDriver.findElementByName(strElement).getText();
		}
		
		if(strElementProperty.equalsIgnoreCase("xpath")){
			strActualText = webDriver.findElementByXPath(strElement).getText();
		}	
		
		if(strActualText.equals(strExpectedText)){
			return true;
		}else{
			return false;
		}
		
	}
	
	public boolean verifyListValue(RemoteWebDriver webDriver, String strElement, String strElementProperty, String strExpectedValue){
		
		//verifyElementPresent(webDriver, strElement);
		WebElement listbox = null;
				
		if(strElementProperty.equalsIgnoreCase("id")){
			listbox = webDriver.findElementById(strElement);
		}	
		
		if(strElementProperty.equalsIgnoreCase("name")){
			listbox = webDriver.findElementByName(strElement);
		}
		
		if(strElementProperty.equalsIgnoreCase("xpath")){
			listbox = webDriver.findElementByXPath(strElement);
		}	
		
		List<WebElement> options = listbox.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if (option.getText().equals(strExpectedValue))
                return true;
        }
        return false;
	}
	
	public boolean verifyListValues(RemoteWebDriver webDriver, String strElement, String strElementProperty, String strExpectedValues){
		
		//verifyElementPresent(webDriver, strElement);
		WebElement listbox = null;
		String [] arrListValues = strExpectedValues.split(";");
		int counter = 0;
				
		if(strElementProperty.equalsIgnoreCase("id")){
			listbox = webDriver.findElementById(strElement);
		}	
		
		if(strElementProperty.equalsIgnoreCase("name")){
			listbox = webDriver.findElementByName(strElement);
		}
		
		if(strElementProperty.equalsIgnoreCase("xpath")){
			listbox = webDriver.findElementByXPath(strElement);
		}	
		
		List<WebElement> options = listbox.findElements(By.tagName("option"));
		for(int i=0; i<arrListValues.length;i++){
	        for (WebElement option : options) {
	            if (option.getText().equals(arrListValues[i]))
	            	counter++;
	            	break;
	        }
		}
		if(counter==arrListValues.length){
			return true;
		}else{
			return false;
		}
	}
	
	
	public boolean verifySelectedListValue(RemoteWebDriver webDriver, String strElement, String strElementProperty, String strExpectedSelectedValue){
		
		//verifyElementPresent(webDriver, strElement);
		WebElement listbox = null;
				
		if(strElementProperty.equalsIgnoreCase("id")){
			listbox = webDriver.findElementById(strElement);
		}	
		
		if(strElementProperty.equalsIgnoreCase("name")){
			listbox = webDriver.findElementByName(strElement);
		}
		
		if(strElementProperty.equalsIgnoreCase("xpath")){
			listbox = webDriver.findElementByXPath(strElement);
		}	
		
		List<WebElement> options = listbox.findElements(By.tagName("option"));
		for (WebElement option : options) {
            if (option.isSelected())
                return true;
        }
        return false;
	}
	
	
	
	public boolean verifyCheckboxStatus(RemoteWebDriver webDriver, String strElement, String strElementProperty, String strExpectedText){
		
		//verifyElementPresent(webDriver, strElement);
		String strActualStatus = null;
		
		if(strElementProperty.equalsIgnoreCase("id")){
			strActualStatus = webDriver.findElementById(strElement).getAttribute("checked");
		}	
		
		if(strElementProperty.equalsIgnoreCase("name")){
			strActualStatus = webDriver.findElementByName(strElement).getAttribute("checked");
		}
		
		if(strElementProperty.equalsIgnoreCase("xpath")){
			strActualStatus = webDriver.findElementByXPath(strElement).getAttribute("checked");
		}	
		
		if(strActualStatus.equalsIgnoreCase("true")){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean verifyRadioButtonStatus(RemoteWebDriver webDriver, String strElement, String strElementProperty, String strExpectedText){
		//verifyElementPresent(webDriver, strElement);
		String strActualStatus = null;
		
		if(strElementProperty.equalsIgnoreCase("id")){
			strActualStatus = webDriver.findElementById(strElement).getAttribute("checked");
		}	
		
		if(strElementProperty.equalsIgnoreCase("name")){
			strActualStatus = webDriver.findElementByName(strElement).getAttribute("checked");
		}
		
		if(strElementProperty.equalsIgnoreCase("xpath")){
			strActualStatus = webDriver.findElementByXPath(strElement).getAttribute("checked");
		}	
		
		if(strActualStatus.equalsIgnoreCase("true")){
			return true;
		}else{
			return false;
		}
		
	}
   /*
	'***************************************************************************************************
	'Description 	 : This function is developed to create and return object identifier
	'InputParameters : identifier 
	'OutPutParameters: By
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/  
	public By getBy(Locator loc){
		By by=null;
		String locValue= null;
		locValue = loc.getValue();
		
					switch(loc.getbyType().toUpperCase()){
					
						case "NAME":
							by = By.name(locValue);
							break;
						case "LINKTEXT":
							by = By.linkText(locValue);
							break;
						case "XPATH":
							by = By.xpath(locValue);
							break;
						case "TAGNAME":
							by = By.tagName(locValue);
							break;
						case "CSS":  case "CSSSELECTOR":
							by = By.cssSelector(locValue);
							break;	
							default:
								by = By.id(locValue);
			
						}
			
				return by;
		}
	
	public Double stringMultiply(String input1,String input2) {
		Double product = null;
		if(input1.contains(",")){
			input1=input1.replace(",", "");
		}
		if(input2.contains(",")){
			input2=input2.replace(",", "");
		}
		try {
			Double dbl1=Double.parseDouble(input1);
			Double dbl2=Double.parseDouble(input2);
			product=dbl1*dbl2;
		} catch (Exception e) {

			e.printStackTrace();
		}

 		return product;
	}
	

	/*
	'***************************************************************************************************
	'Description 	 : This function is used to switch the window with windowTitle
	'Author			 : Rakesh Karkare
	'Date		     : 22 March 2016
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/
	public void switchToWindow(String windowTitle) {
		try{
			Set<String> windows = getWebDriver().getWindowHandles();

			for (String window : windows) {
				getWebDriver().switchTo().window(window);
				getWebDriver().manage().window().maximize();

				getWebDriver().getTitle().contains(windowTitle);
			}
		}

		catch(Exception e){
			e.printStackTrace();
		}
	}
	/*
	'***************************************************************************************************
	'Description 	 : This function is used to Close the window with windowTitle
	'Author			 : Rakesh Karkare
	'Date		     : 22 March 2016
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/
	public void closeWindow(String windowTitle) {
		try {
			Thread.sleep(1000);
			getWebDriver().close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	'***************************************************************************************************
	'Description 	 : This function is used to click Auto Suggest Link With Tab
	'Author			 : Rakesh Karkare
	'Date		     : 22 March 2016
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/
	public void clickAutoSuggestLink(Locator loc, String textTobeEntered){

		try{	
			WebElement element = null;
			element=getWebElement(loc);
			
			element.clear();
			element.sendKeys(textTobeEntered);
			Thread.sleep(2000);
			Robot rb =new Robot();
			rb.keyPress(java.awt.event.KeyEvent.VK_TAB);
			//element.sendKeys(Keys.TAB);
			Thread.sleep(3000);
			
			Reporter.log( "Enter Value in " +loc.getKey()+" text field "+textTobeEntered+ " entered successfully");	
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Enter Value in " +loc.getKey()+" text field "+textTobeEntered+"</font></I></B>");
			Assert.fail( "Enter Value in " +loc.getKey()+" text field "+textTobeEntered+ "Found Exception while entering value into "+e.getMessage());
			
		}
			
	}
	


	
	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to get required Attribute  of the object
	'InputParameters : ElementName, identifier ,attribute and RemoteWebDriver object
	'OutPutParameters: Attribute  of the object
	'Author			 : Rakesh Karkare
	'Date		     : 25 Apr 2016
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public String getAttributeValueForVolta(String strElementName, Locator loc, String attribute){
		String strTextValue=null;
		
		try{
			
			WebElement element = null;
			element=getWebElementForVolta(loc);
			
			strTextValue=element.getAttribute(attribute);
			Reporter.log( "Get "+attribute+" from " +strElementName+" "+strTextValue+" Pass Value stored is "+strTextValue);
			
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Get "+attribute+" from " +strElementName+" "+strTextValue+" Fail Found Exception while storing value "+e.getMessage()+"</font></I></B>");
			
		  
		}
		return strTextValue;	
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to Click web element
	'InputParameters : ElementName, identifier and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Rakesh Karkare
	'Date		     : 25 April 2016
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void clickElementForVolta(String strElementName, Locator loc){
		
			WebElement element = null;
			element=getWebElementForVolta(loc);
			if(element.isDisplayed())
			{
				element.click();
				Reporter.log("Clicking on  "+strElementName);
				System.out.println("Clicking on "+strElementName);
				try{
					Thread.sleep(2000);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
				Assert.fail(strElementName+"Element "+strElementName+" is not displayed");
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is developed to create and return web element
	'InputParameters : identifier and RemoteWebDriver object
	'OutPutParameters: web element
	
	'
	'**************************************************************************************************
*/		
	public  WebElement getWebElementForVolta(Locator loc)
	{
		 String by=loc.getbyType().toLowerCase();
		 String ele=loc.getValue();
		 String key=loc.getKey();
		WebElement webEle=null;
		try{
		if(by.equalsIgnoreCase("id"))
			webEle=getWebDriver().findElement(By.id(ele));
		else if(by.equalsIgnoreCase("class"))
			webEle=getWebDriver().findElement(By.className(ele));
		else if(by.equalsIgnoreCase("name"))
			webEle=getWebDriver().findElement(By.name(ele));
		else if(by.equalsIgnoreCase("link"))
			webEle=getWebDriver().findElement(By.linkText(ele));
		else if(by.equalsIgnoreCase("xpath"))
			webEle=getWebDriver().findElement(By.xpath(ele));
		else if(by.equalsIgnoreCase("css"))
			webEle=getWebDriver().findElement(By.cssSelector(ele));
		else if(by.equalsIgnoreCase("tag"))
			webEle=getWebDriver().findElement(By.tagName(ele));
		if(by.toString().equals(null))
			webEle=getWebDriver().findElement(By.xpath(ele));;
		}catch(Exception e){
			System.out.println("Not able to locate element :"+key +" with locator" +ele);
			System.out.println("Exception "+e);
			
		}
		
		return webEle;
}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to Clear And Send Keys web element
	'InputParameters : ElementName, identifier and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Rakesh Karkare
	'Date		     : 25 April 2016
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	
	public void clearAndSendKeysForVolta(Locator loc, String textTobeEntered){

		try{	
			WebElement element = null;
			element=getWebElementForVolta(loc);
			
			element.clear();
			element.sendKeys(textTobeEntered);
			
			Reporter.log( "Enter Value in " +loc.getKey()+" text field "+textTobeEntered+ " entered successfully");	
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Enter Value in " +loc.getKey()+" text field "+textTobeEntered+"</font></I></B>");
			Assert.fail( "Enter Value in " +loc.getKey()+" text field "+textTobeEntered+ "Found Exception while entering value into "+e.getMessage());
			
		}
			
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to get text property of the object
	'InputParameters : ElementName, identifier and RemoteWebDriver object
	'OutPutParameters: text property of the object
	
	'**************************************************************************************************
*/	
	public String getTextValueForVolta(String strElementName, Locator loc){
		
		String strTextValue=null;
		try{
			WebElement element = null;
			element=getWebElementForVolta(loc);	

			strTextValue=element.getText();
			Reporter.log("Get Value from " +strElementName + " Value stored is "+strTextValue);
			
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Failed to Fetch Text Value"+loc.getKey()+ "</font></I></B>");
			Assert.fail("Found Exception while storing value " +e.getMessage());
		}
		return strTextValue;	
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is to select value from a Web list based on the index option
	'InputParameters : ElementName, identifier,valueTobeSelected and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/		
	public void selectValueByIndexForVolta(String strElementName, Locator loc,int index ){
		String valueSelected="";
		try{
			
			
			WebElement element = null;
			element=getWebElementForVolta(loc);
			Select webCheckBox= new Select(element);
			webCheckBox.selectByIndex(index);
			valueSelected=webCheckBox.getFirstSelectedOption().getText();
			Reporter.log( "Select value from " +strElementName+" "+valueSelected+" Pass Selected "+valueSelected+" Successfully");	
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Select value from " +strElementName+" "+valueSelected+" Fail  Found Exception while selecting value "+e.getMessage()+"</font></I></B>");	
			
		}
			
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to Clear Web Element
	'InputParameters : ElementName, identifier and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Rakesh Karkare
	'Date		     : 25 April 2016
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	
	public void clearTextAmend(Locator loc, String textTobeEntered){

		try{	
			WebElement element = null;
			element=getWebElementForVolta(loc);
			
			element.clear();
 			
			Reporter.log( "Enter Value in " +loc.getKey()+" text field "+textTobeEntered+ " entered successfully");	
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Enter Value in " +loc.getKey()+" text field "+textTobeEntered+"</font></I></B>");
			Assert.fail( "Enter Value in " +loc.getKey()+" text field "+textTobeEntered+ "Found Exception while entering value into "+e.getMessage());
			
		}
			
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to Set Text Without Clear Web Element
	'InputParameters : ElementName, identifier and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Rakesh Karkare
	'Date		     : 25 April 2016
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	
	public void enterText(Locator loc, String textTobeEntered){

		try{	
			WebElement element = null;
			element=getWebElementForVolta(loc);
			element.sendKeys(textTobeEntered);
			Reporter.log( "Enter Value in " +loc.getKey()+" text field "+textTobeEntered+ " entered successfully");	
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Enter Value in " +loc.getKey()+" text field "+textTobeEntered+"</font></I></B>");
			Assert.fail( "Enter Value in " +loc.getKey()+" text field "+textTobeEntered+ "Found Exception while entering value into "+e.getMessage());
			
		}
			
	}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to getFiles from Directory
	'InputParameters : ElementName, identifier and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Rakesh Karkare
	'Date		     : 10 May 2016
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	
	public List<String> getFiles(String directory,String fileType) {
		  List<String> listFiles = new ArrayList<String>();
		  File dir = new File(directory);
		  for (File file : dir.listFiles()) {
		    if (file.getName().endsWith(("."+fileType.toLowerCase()))) {
		    	listFiles.add(file.getName());
		    }
		  }
		 return listFiles;
		}
	
	/*
	'***************************************************************************************************
	'Description 	 : This function is useful to Compare Attribute value and Input
	'InputParameters : ElementName, identifier,expectedValue,attribute and RemoteWebDriver object
	'OutPutParameters: None
	'Author			 : Ravikumar Chimmili
	'Date		     : 
	'**************************************************************************************************
	'                               C  H  A  N  G  E                       H  I  S  T  O  R  Y
	'**************************************************************************************************
	' Date                                      Change made by                  Purpose of change
	'--------                    ------------------- -------------------------------------------------
	'
	'**************************************************************************************************
*/	
	public void verifyAttributeValueForVolta(String strElementName, Locator loc, String expectedValue,String attribute){
		
		String strTextValue=null;
		try{
			
			WebElement element = null;
			element=getWebElementForVolta(loc);
			
			strTextValue=element.getAttribute(attribute);
			if(strTextValue.contains(",")){
				strTextValue=strTextValue.replace(",", "");
			}
			if(expectedValue.contains(",")){
				expectedValue=expectedValue.replace(",", "");
			}
			
			if(strTextValue.equalsIgnoreCase(expectedValue)){
				Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify "+attribute+" of " +strElementName+" Expected : "+expectedValue+" Pass Actual value  is "+strTextValue+"</font></I></B>");	
			}
			else{
				Reporter.log( "Verify "+attribute+" of " +strElementName+" Expected : "+expectedValue+" Fail Actual value  is "+strTextValue);
			}
			
		}catch(Exception e){
			Reporter.log("<B><I><font size='4' color='Blue'>"+"Verify "+attribute+" of " +strElementName+" Expected : "+expectedValue+" Fail Found Exception "+e.getMessage()+"</font></I></B>");
			
		}
		
	}

}

