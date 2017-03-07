package com.travelex.framework.common;


import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.travelex.framework.common.EnvironmentParameter;
import com.travelex.framework.exception.IllegalEnvironmentValueException;

/**
 * WebdriverFactory class used to get a web driver instance. 
 * It also consists page wait load for images/frames/document
 */

public class WebDriverFactory {
	@SuppressWarnings("unused")
	private static ConfigurationProperties configProperty = ConfigurationProperties.getInstance();

	public static int maxPageLoadWait = 100;
	public WebDriver driver = null;

	/**
	 * Launches remote web driver with specified browser, version, platform, and desired capabilities
	 * 
	 * @param environmentParameter
	 * @return driver
	 * @throws Throwable
	 */
	
	public WebDriver get(EnvironmentParameter environmentParameter) throws Throwable {
		if(driver == null){
		DesiredCapabilities capability = new DesiredCapabilities();
		ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
		String browser = environmentParameter.getBrowserName();
		String absolutePath=new File("").getAbsolutePath();
		
		if (browser.equalsIgnoreCase("IE")) {
			capability = DesiredCapabilities.internetExplorer();
			capability.setCapability("enablePersistentHover", false);
			capability.setCapability("ignoreZoomSetting", true);
			capability.setCapability("nativeEvents", false);
			capability.setCapability("ignoreProtectedModeSettings", true);
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			capability.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			capability.setJavascriptEnabled(true);
			capability.setVersion(environmentParameter.getBrowserVersion());
			Runtime.getRuntime().exec("wscript "+absolutePath+"/gridConfig/protectedmodeon.vbs");
		}else if (browser.equalsIgnoreCase("FIREFOX")) {
			capability = DesiredCapabilities.firefox();			
		}else if (browser.equalsIgnoreCase("PHANTOM")) {
			capability = DesiredCapabilities.phantomjs();
			capability.setJavascriptEnabled(true);			
		}else if (browser.equalsIgnoreCase("CHROME")) {
			capability = DesiredCapabilities.chrome();
			capability.setJavascriptEnabled(true);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--disable-bundled-ppapi-flash");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-web-security");
			options.addArguments("--always-authorize-plugins");
			options.addArguments("--allow-running-insecure-content");
			options.addArguments("--test-type");
			options.addArguments("--enable-npapi");
			options.addArguments("--disable-extensions");
			options.addArguments("start-maximized");
			capability.setVersion(environmentParameter.getBrowserVersion());
			capability.setCapability(ChromeOptions.CAPABILITY, options);
			capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);			
		} 
		else throw new IllegalEnvironmentValueException("Browser Name: "+ browser	+ " is not valid. Try any one of following \n1 IE, \n2 FIREFOX, \n3 CHROME ");		
		
		//WebDriver driver = null;
		String platform = environmentParameter.getPlatform();
		
		if (platform.equalsIgnoreCase("WINDOWS")) {
			capability.setPlatform(Platform.WINDOWS);
			
		}else if(platform.equalsIgnoreCase("LINUX")){
			capability.setPlatform(Platform.LINUX);
			
		}
		try {
			URL gridURL = new URL(configurationProperties.getProperty(ConfigurationProperties.REMOTE_SERVER_URL));
			driver = new RemoteWebDriver(gridURL, capability);
		} catch (Exception e) {
		} catch (Error err) {
			err.printStackTrace();
		} finally {
			try {
				Field f = Reporter.getCurrentTestResult().getClass().getDeclaredField("m_startMillis");
				f.setAccessible(true);
				f.setLong(Reporter.getCurrentTestResult(), Calendar.getInstance().getTime().getTime());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Assert.assertNotNull(driver, "Driver did not intialize...\n Please check if hub is running / configuration settings are corect.");
		driver.manage().timeouts().pageLoadTimeout(maxPageLoadWait, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		return driver;
		}
		else{
			return driver;
		}
		
	}
}
