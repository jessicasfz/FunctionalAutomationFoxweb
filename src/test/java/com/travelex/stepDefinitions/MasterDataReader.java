package com.travelex.stepDefinitions;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.travelex.framework.Dataset.CustomerDetailsDataSet;
import com.travelex.framework.Dataset.OrderDetailsDataSet;
import com.travelex.framework.common.ConfigurationProperties;
import com.travelex.framework.common.EnvironmentParameter;
import com.travelex.framework.common.WebDriverFactory;
import com.travelex.framework.common.readFileUsingPOI;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MasterDataReader {
	
	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	static EnvironmentParameter environmentParameter;
	public static WebDriver driver;
	public static Scenario scenario;
	
	public static int index;
	public static List<OrderDetailsDataSet> orderDataset;
	public static List<CustomerDetailsDataSet> custDataset;

	
	 @Before
	    public void openBrowser(Scenario scenario) throws MalformedURLException,Throwable {    	
			String platform = configurationProperties.getProperty(ConfigurationProperties.PLATFORM);
			String browserName = configurationProperties.getProperty(ConfigurationProperties.BROWSER_NAME);
			String browserVersion = configurationProperties.getProperty(ConfigurationProperties.BROWSER_VERSION);		
			environmentParameter = new EnvironmentParameter();
			environmentParameter.setBrowserName(browserName);
			environmentParameter.setBrowserVersion(browserVersion);
			environmentParameter.setPlatform(platform);		 		
			driver = WebDriverFactory.get(environmentParameter);
			MasterDataReader.scenario = scenario;
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
	        driver.quit();
	    }
	
	@Given("^I read Excel data with AutomationID \"([^\"]*)\" for iteration \"([^\"]*)\"$")
	public void i_read_Excel_data_with_AutomationID_for_iteration(String arg1, String rowIndex) throws Throwable {
		
		index = Integer.parseInt(rowIndex)-1;
		orderDataset = readFileUsingPOI.readOrderDetailsFile(scenario.getName(), "OrderDetails");
		custDataset = readFileUsingPOI.readCustDetailsFile(scenario.getName(), "CustomerDetails");

	}


}
