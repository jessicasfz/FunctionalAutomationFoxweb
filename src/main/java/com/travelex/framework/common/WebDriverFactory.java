package com.travelex.framework.common;


import java.lang.reflect.Field;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;
import org.testng.Reporter;

import com.travelex.framework.common.EnvironmentParameter;
import com.travelex.framework.exceptions.IllegalEnvironmentValueException;



/**
 * WebdriverFactory class used to get a web driver instance. 
 * It also consists page wait load for images/frames/document
 */

public class WebDriverFactory {
	private static ConfigurationProperties configProperty = ConfigurationProperties.getInstance();

	public static ExpectedCondition<Boolean> documentLoad;
	public static ExpectedCondition<Boolean> framesLoad;
	public static ExpectedCondition<Boolean> imagesLoad;
	public static int maxPageLoadWait = 100;

	static {
		documentLoad = new ExpectedCondition<Boolean>() {
			public final Boolean apply(final WebDriver driver) {
				final JavascriptExecutor js = (JavascriptExecutor) driver;
				boolean docReadyState = false;
				try {
					docReadyState = (Boolean) js.executeScript("return (function() { if (document.readyState != 'complete') {  return false; } if (window.jQuery != null && window.jQuery != undefined && window.jQuery.active) { return false;} if (window.jQuery != null && window.jQuery != undefined && window.jQuery.ajax != null && window.jQuery.ajax != undefined && window.jQuery.ajax.active) {return false;}  if (window.angular != null && angular.element(document).injector() != null && angular.element(document).injector().get('$http').pendingRequests.length) return false; return true;})();");
				} catch (WebDriverException e) {
					docReadyState = true;
				}
				return docReadyState;
			}
		};

		imagesLoad = new ExpectedCondition<Boolean>() {
			public final Boolean apply(final WebDriver driver) {
				boolean docReadyState = true;
				try {
					JavascriptExecutor js;
					List<WebElement> images = driver.findElements(By.cssSelector("img[src]"));
					for (int i = 0; i < images.size(); i++) {
						try {
							js = (JavascriptExecutor) driver;
							docReadyState = docReadyState && (Boolean) js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", images.get(i));
							if (!docReadyState) {
								break;
							}
						} catch (StaleElementReferenceException e) {
							images = driver.findElements(By.cssSelector("img[src]"));
							i--;
							continue;
						} catch (WebDriverException e) {
							docReadyState = true;
						}
					}
				} catch (WebDriverException e) {
					docReadyState = true;
				}
				return docReadyState;
			}
		};

		framesLoad = new ExpectedCondition<Boolean>() {
			public final Boolean apply(final WebDriver driver) {
				boolean docReadyState = true;
				try {
					JavascriptExecutor js;
					List<WebElement> frames = driver.findElements(By.cssSelector("iframe[style*='hidden']"));
					for (WebElement frame : frames) {
						try {
							driver.switchTo().defaultContent();
							driver.switchTo().frame(frame);
							js = (JavascriptExecutor) driver;
							docReadyState = docReadyState && (Boolean) js.executeScript("return (document.readyState==\"complete\")");
							driver.switchTo().defaultContent();
							if (!docReadyState) {
								break;
							}
						} catch (WebDriverException e) {
							docReadyState = true;
						}
					}
				} catch (WebDriverException e) {
					docReadyState = true;
				} finally {
					driver.switchTo().defaultContent();
				}
				return docReadyState;
			}
		};
		maxPageLoadWait = configProperty.getProperty("maxPageLoadWait") != null ? Integer.valueOf(configProperty.getProperty("maxPageLoadWait")) : maxPageLoadWait;
	}

	
	public static WebDriver get(EnvironmentParameter environmentParameter) throws Throwable {		
		DesiredCapabilities capability = new DesiredCapabilities();
		ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
		String browser = environmentParameter.getBrowserName();
		
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
		
		WebDriver driver = null;
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
			Reporter.log("Unable to launch browser instance due to following error : "+ e.getMessage());
		} catch (Error err) {
			err.printStackTrace();
			Reporter.log("Unable to launch browser instance due to following error : "+ err.getMessage());
		} finally {
			try {
				Field f = Reporter.getCurrentTestResult().getClass().getDeclaredField("m_startMillis");
				f.setAccessible(true);
				f.setLong(Reporter.getCurrentTestResult(), Calendar.getInstance().getTime().getTime());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		driver.manage().timeouts().pageLoadTimeout(maxPageLoadWait, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		Assert.assertNotNull(driver, "Driver did not intialize...\n Please check if hub is running / configuration settings are corect.");		
		return driver;
	}
	
}
