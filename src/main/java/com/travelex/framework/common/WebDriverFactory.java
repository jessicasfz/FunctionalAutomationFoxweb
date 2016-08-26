package com.travelex.framework.common;


import java.lang.reflect.Field;
import java.net.MalformedURLException;
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
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.xml.XmlTest;

/**
 * WebdriverFactory class used to get a web driver instance. 
 * It also consists page wait load for images/frames/document
 */

public class WebDriverFactory {
	private static ConfigurationProperties configProperty = ConfigurationProperties.getInstance();

	static String driverHost;
	static String driverPort;
	static String browserName;
	static String deviceName;
	static URL hubURL;

	static DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
	static DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
	static DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
	static ChromeOptions opt = new ChromeOptions();
	static FirefoxProfile fp = new FirefoxProfile();
	public static ExpectedCondition<Boolean> documentLoad;
	public static ExpectedCondition<Boolean> framesLoad;
	public static ExpectedCondition<Boolean> imagesLoad;
	public static int maxPageLoadWait = 120;

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

		XmlTest test = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
		driverHost = System.getProperty("hubHost") != null ? System.getProperty("hubHost") : test.getParameter("deviceHost");
		driverPort = System.getProperty("hubPort") != null ? System.getProperty("hubPort") : test.getParameter("devicePort");

		maxPageLoadWait = configProperty.getProperty("maxPageLoadWait") != null ? Integer.valueOf(configProperty.getProperty("maxPageLoadWait")) : maxPageLoadWait;
		opt.addArguments("--ignore-certificate-errors");
		opt.addArguments("--disable-bundled-ppapi-flash");
		opt.addArguments("--disable-extensions");
		opt.addArguments("--disable-web-security");
		opt.addArguments("--always-authorize-plugins");
		opt.addArguments("--allow-running-insecure-content");
		opt.addArguments("--test-type");
		opt.addArguments("--enable-npapi");
		chromeCapabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		try {
			hubURL = new URL("http://" + driverHost + ":" + driverPort + "/wd/hub");
		} catch (MalformedURLException e) {
			// e.printStackTrace();
		}

	}

	/**
	 * Method to get instance of web driver using default parameters
	 * 
	 * @return driver
	 */
	public static WebDriver get() {
		browserName = System.getProperty("browserName") != null ? System.getProperty("browserName") : Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browserName").toLowerCase();
		return get(browserName);
	}

	/**
	 * Webdriver to get the web driver with browser name and platform and
	 * setting the desired capabilities for browsers
	 */
	public static WebDriver get(String browserName) {

		String browser = browserName;
		String platform = "ANY";
				
		WebDriver driver = null;
		
		try {
				if ("chrome".equalsIgnoreCase(browser)) {
					chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, opt);
					chromeCapabilities.setPlatform(Platform.fromString(platform));
					driver = new RemoteWebDriver(hubURL, chromeCapabilities);
					
				} else if ("iexplorer".equalsIgnoreCase(browser)) {
					ieCapabilities.setCapability("enablePersistentHover", false);
					ieCapabilities.setCapability("ignoreZoomSetting", true);
					ieCapabilities.setCapability("nativeEvents", false);
					ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
					ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					ieCapabilities.setPlatform(Platform.fromString(platform));
					driver = new RemoteWebDriver(hubURL, ieCapabilities);
				} else {
					synchronized (WebDriverFactory.class) {
						firefoxCapabilities.setCapability("unexpectedAlertBehaviour", "ignore");
						firefoxCapabilities.setPlatform(Platform.fromString(platform));
						driver = new RemoteWebDriver(hubURL, firefoxCapabilities);
					}
					driver.manage().timeouts().pageLoadTimeout(maxPageLoadWait, TimeUnit.SECONDS);
				}
				Assert.assertNotNull(driver, "Driver did not intialize...\n Please check if hub is running / configuration settings are corect.");
				driver.manage().window().maximize();
				
			} catch (UnreachableBrowserException e) {
				e.printStackTrace();
				throw new SkipException("Hub is not started or down.");
			} catch (WebDriverException e) {

				try {
					if (driver != null) {
						driver.quit();
					}
				} catch (Exception e1) {
					e.printStackTrace();
				}

				if (e.getMessage().toLowerCase().contains("error forwarding the new session empty pool of vm for setup")) {
					throw new SkipException("Node is not started or down.");
				} else if (e.getMessage().toLowerCase().contains("error forwarding the new session empty pool of vm for setup") || e.getMessage().toLowerCase().contains("cannot get automation extension") || e.getMessage().toLowerCase().contains("chrome not reachable")) {
					//Log.message("&emsp;<b> --- Re-tried as browser crashed </b>");
					try {
						driver.quit();
					} catch (WebDriverException e1) {
						e.printStackTrace();
					}
					driver = get();
				} else {
					throw e;
				}
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Exception encountered in getDriver Method." + e.getMessage().toString());
			} finally {
				// ************************************************************************************************************
				// * Update start time of the tests once free slot is assigned by
				// RemoteWebDriver - Just for reporting purpose
				// *************************************************************************************************************/
				try {
					Field f = Reporter.getCurrentTestResult().getClass().getDeclaredField("m_startMillis");
					f.setAccessible(true);
					f.setLong(Reporter.getCurrentTestResult(), Calendar.getInstance().getTime().getTime());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			//Log.event("Driver::Get", StopWatch.elapsedTime(startTime));
			//Log.addTestRunMachineInfo(driver);
			return driver;
		}

		

		
	
}
