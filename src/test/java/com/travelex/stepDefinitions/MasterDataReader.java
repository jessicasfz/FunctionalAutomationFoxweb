package com.travelex.stepDefinitions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.travelex.framework.common.ConfigurationProperties;
import com.travelex.framework.common.EnvironmentParameter;
import com.travelex.framework.common.WebDriverFactory;
import com.travelex.framework.common.ExcelFileReader;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class MasterDataReader {
	
	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	public static EnvironmentParameter environmentParameter;
	public static WebDriver driver;
	public static WebDriver Ddriver;
	public static Scenario scenario;
		
	public static Map<String,String> orderDetails;
	public static Map<String,String> customerDetails;
	public static Map<String,String> rapDetails;
	public static Map<String,Object> pageDetails;
	public static Map<String,String> plutoDetails;
	public static ArrayList<HashMap<String,String>> txnDetails;
	
	 @Before
	    public void openBrowser(Scenario scenario) throws MalformedURLException,Throwable {    	
			String platform = configurationProperties.getProperty(ConfigurationProperties.PLATFORM);
			String browserName = configurationProperties.getProperty(ConfigurationProperties.BROWSER_NAME);
			String browserVersion = configurationProperties.getProperty(ConfigurationProperties.BROWSER_VERSION);		
			environmentParameter = new EnvironmentParameter();
			environmentParameter.setBrowserName(browserName);
			environmentParameter.setBrowserVersion(browserVersion);
			environmentParameter.setPlatform(platform);	
			WebDriverFactory factory = new WebDriverFactory();
			driver = factory.get(environmentParameter);
			Ddriver = driver;
			MasterDataReader.scenario = scenario;
			pageDetails = new HashMap<String, Object>();
	    }
	   
	    /**
	     * Embed a screenshot in test report if test is marked as failed
	     */
	    
	    @After
	    public void embedScreenshot(Scenario scenario) {
	        if(scenario.isFailed()) {
	        try {
	            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	            scenario.embed(screenshot, "image/png");
	        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
	            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
	        }        
	        }
	        Ddriver.quit();
	        if(scenario.isFailed()){
	        	driver.quit();
	        }
	        pageDetails.clear();
	    }
		
	    @Given("^I read Excel data with AutomationID \"([^\"]*)\"$")
		public void i_read_Excel_data_with_AutomationID(String automationID) throws IOException{
			orderDetails = ExcelFileReader.readDataForAutomationID("OrderDetails",automationID);
			customerDetails = ExcelFileReader.readDataForAutomationID("CustomerDetails",automationID);
			rapDetails = ExcelFileReader.readDataForAutomationID("RAPDetails", automationID);
			plutoDetails = ExcelFileReader.readDataForAutomationID("PultoDetails", automationID);
			MasterDataReader.scenario.write("Order Details Data "+ MasterDataReader.orderDetails);
			MasterDataReader.scenario.write("Customer Details Data "+ MasterDataReader.customerDetails);
			MasterDataReader.scenario.write("RAP Details Data "+ MasterDataReader.rapDetails);
			MasterDataReader.scenario.write("Pluto Details Data "+ MasterDataReader.plutoDetails);
		}
}
