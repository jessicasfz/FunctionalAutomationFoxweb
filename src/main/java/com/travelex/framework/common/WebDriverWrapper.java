package com.travelex.framework.common;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class WebDriverWrapper {
	//static Logger logger = Logger.getLogger(WebDriverWrapper.class.getName());
	WebDriver driver;

	public WebDriverWrapper(WebDriver driver) {
		this.driver = driver;
	}

	public static String getCurrentBrowserName(WebDriver driver) {
		Capabilities cp = ((RemoteWebDriver) driver).getCapabilities();
		return cp.getBrowserName();
	}
	
	public String getCurrentBrowserName() {
		Capabilities cp = ((RemoteWebDriver) driver).getCapabilities();
		return cp.getBrowserName();
	}
	
	public Capabilities getCurrentCapability() {
		Capabilities cp = ((RemoteWebDriver) driver).getCapabilities();
		return cp;
	}
	
	/**
	 * it will capture screenshot and place into the specified destination in PNG format
	 * 
	 * @param destinationFilePathLocation
	 * @param snapShotName
	 * @return fileName
	 * @throws IOException
	 */
	
	public String captureScreenShot(File destinationFilePathLocation, String snapShotName) throws IOException {
		String fileName = new String();
		if (isAlertPresentForScreenshot()) {
			dismissAlert(2);
		}
		WebDriver augmentedDriver = driver;
		if (!(driver instanceof InternetExplorerDriver))
			augmentedDriver = new Augmenter().augment(driver);
		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		fileName = snapShotName + ".png";
		File destinationFilePath = new File((destinationFilePathLocation + File.separator + fileName));
		FileUtils.copyFile(screenshot, destinationFilePath);
		return fileName;
	}

	
	/**
	 * it returns Unique Time Stamp HH-MM-SSAADD-MMM-YYYY
	 * 
	 * @return String TimeStamp
	 */
	
	public String getUniqueTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaadd_MMM_yyyy");
		Date date = new Date();
		return (dateFormat.format(date));
	}


	/**
	 * it will wait and verify WebElement is displayed or not in Specified Time Period
	 * 
	 * @param element
	 * @param timeOutPeriod
	 * @return WebElement
	 */
	
	public WebElement waitForElementToBeDisplayed(final WebElement element, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
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
	
	/**
	 * it will wait and verify WebElement is displayed or not in Specified Time Period
	 * 
	 * @param by
	 * @param timeOutPeriod
	 * @return WebElement
	 */

	public WebElement waitForElementToBeDisplayed(final By by, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
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

	
	/**
	 * it will wait and verify either WebElement is Clickable or not in Specified Time Period
	 * 
	 * @param by
	 * @param timeOutPeriod
	 * @return WebElement
	 */
	

	public WebElement waitForElementToBeClickable(final By by, int timeOutPeriod) {

		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
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
	
	/**
	 * it will wait and verify either WebElement is Clickable or not in Specified Time Period
	 * 
	 * @param element
	 * @param timeOutPeriod
	 * @return WebElement
	 */

	public WebElement waitForElementToBeClickable(final WebElement element,int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
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

	/**
	 * it will wait and verify either WebElement is Enabled or not in Specified Time Period 
	 * 
	 * @param element
	 * @param timeOutPeriod
	 * @return WebElement
	 */
	
	public WebElement waitForElementToBeEnabled(final WebElement element,int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
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
	
	/**
	 * it will wait and verify either WebElement is Enabled or not in Specified Time Period
	 * 
	 * @param by
	 * @param timeOutPeriod
	 * @return WebElement
	 */

	public WebElement waitForElementToBeEnabled(final By by, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
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
	
	/**
	 * it will wait and Select the list item in specified Time Period
	 * 
	 * @param dropdownList
	 * @param timeOutPeriod
	 * @return WebElement
	 */
	
	public WebElement waitForOptionToBePopulatedInList(final WebElement dropdownList, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
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

	
	/**
	 * it will check Presence of Text In the TextField in specified Time Period
	 * 
	 * @param webElement
	 * @param timeOutPeriod
	 * @return WebElement
	 */
	
	public WebElement waitForTextToAppearInTextField(final WebElement webElement, int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
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

	/**
	 * it will wait and check the DropdownList item present or not in Specified Time Period
	 * 
	 * @param by
	 * @param value
	 * @param timeOutPeriod
	 * @return boolean 
	 */
	
	public boolean waitForOptionToBePresentInList(final By by, String value,int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
		webDriverWait.pollingEvery(10, TimeUnit.MICROSECONDS);
		try {
			webDriverWait.until(ExpectedConditions.textToBePresentInElement(
					driver.findElement(by), value));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * it will wait WebElement to be disappear in specified Time Period
	 * 
	 * @param by
	 * @param timeOutPeriod
	 */
	
	public void waitForElementToDisapper(final By by, int timeOutPeriod) {
		FluentWait<By> fluentWait = new FluentWait<By>(by);
		fluentWait.pollingEvery(10, TimeUnit.MICROSECONDS);
		fluentWait.withTimeout(timeOutPeriod, TimeUnit.SECONDS);
		fluentWait.until(new Predicate<By>() {
			public boolean apply(By by) {
				try {
					return !driver.findElement(by).isDisplayed();

				} catch (NoSuchElementException ex) {
					return true;
				} catch (StaleElementReferenceException ex) {
					return true;
				}
			}
		});

	}
	
	/**
	 * brings Web Element In View Mode
	 * @param element
	 */
	
	public void bringElementInView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	
	/**
	 * it will wait For WebElement to be disappear in specified Time Period
	 * 
	 * @param element
	 * @param timeOutPeriod
	 */
	
	public void waitForElementToDisapper(final WebElement element,int timeOutPeriod) {
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
	
	/**
	 * it will wait For Alert To be Present In The Specified Time Period
	 * @param timeOutPeriod
	 */

	public void waitForAlert(int timeOutPeriod) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
		webDriverWait.ignoring(NoSuchElementException.class,StaleElementReferenceException.class)
				.pollingEvery(10, TimeUnit.MILLISECONDS).until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * it will wait For Alert To be Present In The Specified Time Period
	 * @param timeOutPeriod
	 * @param pollingTime
	 */
	
	public void waitForAlert(int timeOutPeriod, int pollingTime) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
		webDriverWait.ignoring(NoSuchElementException.class,StaleElementReferenceException.class)
				.pollingEvery(pollingTime, TimeUnit.MILLISECONDS).until(ExpectedConditions.alertIsPresent());
	}
	
	/**
	 * accept alert based on Expected message and return alert message
	 * 
	 * @param timeOutPeriod
	 * @param alertMessage
	 * @return String alertMessage
	 */

	public String acceptAlert(int timeOutPeriod, String alertMessage) {
		waitForAlert(timeOutPeriod);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		return alertMessage;
	}
	
	/**
	 * accept alert and return alert message
	 * 
	 * @param timeOutPeriod
	 * @return String alertMessage
	 */
	
	public String acceptAlert(int timeOutPeriod) {
		for (int checkAttempts = 1; checkAttempts <= 3; checkAttempts++) {
			waitForAlert(timeOutPeriod);
			if (isAlertPresent()) {
				break;
			}
		}
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		alert.accept();
		return alertMessage;
	}

	/**
	 *dismiss alert and return alert message
	 * 
	 * @param timeOutPeriod
	 * @return String alertMessage
	 */
	
	public String dismissAlert(int timeOutPeriod) {
		waitForAlert(timeOutPeriod);
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		alert.dismiss();
		return alertMessage;
	}

	/**
	 * switch to Alert and get the Alert Message 
	 * @return String alertMessage
	 */
	
	public String getAlertMessage() {
		String alertMessage = new String("");
		try {
			waitForAlert(5);
			alertMessage = driver.switchTo().alert().getText();
		} catch (Exception e) {
		}
		return alertMessage;
	}
	
	/**
	 * it will check Either Alert is Present Or Not if it present return True else it returns False 
	 * @return boolean isAlertPresent
	 */
	
	public boolean isAlertPresentForScreenshot() {
		boolean isAlertPresent = false;
		try {
			driver.switchTo().alert();
			isAlertPresent = false;
		} catch (Exception e) {

		}
		return isAlertPresent;
	}
	
	/**
	 * it will check Either Alert is Present Or Not if it present return True else it returns False
	 * @return boolean isAlertPresent
	 */

	public boolean isAlertPresent() {
		boolean isAlertPresent = false;
		try {
			waitForAlert(3);
			driver.switchTo().alert();
			isAlertPresent = true;
		} catch (Exception e) {
			isAlertPresent = false;
		}
		return isAlertPresent;
	}


	/**
	 * it will check Either Alert is Present Or Not if it present return False else it returns True
	 * @return boolean isAlertPresent
	 */

	public boolean isAlertNotPresent() {
		boolean isAlertNotPresent = false;
		try {
			driver.switchTo().alert();
			isAlertNotPresent = false;
		} catch (Exception e) {
			isAlertNotPresent = true;
		}
		return isAlertNotPresent;
	}
	
	/**
	 * it will check For Loader invisibility in specified Time Period
	 * @param waitTime
	 */
	
	public void waitForLoaderInvisibility(int waitTime)
	{
		try {
			WebDriverWait webDriverWait=new WebDriverWait(driver, waitTime);
			WebElement element = driver.findElement(By.xpath("//div[contains(@class,'ajaxbg')]"));
			for (int counter = 0; counter < 3; counter++) {
				webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'ajaxbg')]")));
				if (!element.isDisplayed()) 
					break;
			}
		}
		catch(Exception exception) {
		}
	}

}
