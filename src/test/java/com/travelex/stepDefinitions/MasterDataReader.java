package com.travelex.stepDefinitions;

import java.io.IOException;
import java.net.MalformedURLException;
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

	public static Map<String,Object> pageDetails;
	public static Map<String,String> foxwebOrderDetails;
	public static Map<String,String> StandardIDDetails;
	public static Map<String,String> UserCreationDetails;//Added by jachakN for user creation

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
		driver.quit();
		pageDetails.clear();
	}

	@Given("^I read Excel data with AutomationID \"([^\"]*)\"$")
	public void i_read_Excel_data_with_AutomationID(String automationID) throws IOException{
		foxwebOrderDetails = ExcelFileReader.readDataForAutomationID("FoxwebOrderDetails", automationID);
		
		try {
			StandardIDDetails = ExcelFileReader.readDataForAutomationID("StandardIDDetails", automationID);
			MasterDataReader.scenario.write("StandardID Details Data "+ MasterDataReader.StandardIDDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		try {
			UserCreationDetails = ExcelFileReader.readDataForAutomationID("UserCreationDetails", automationID);
			MasterDataReader.scenario.write("UserCreationDetails Details Data "+ MasterDataReader.UserCreationDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		
		MasterDataReader.scenario.write("Foxweb Details Data "+ MasterDataReader.foxwebOrderDetails);
		
		
		
	}
}
