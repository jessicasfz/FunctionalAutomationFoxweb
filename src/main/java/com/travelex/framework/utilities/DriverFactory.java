package com.travelex.framework.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactory {

	 private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	    
	    public static WebDriver getDriver() {
	        return webDriver.get();
	    }
	 
	    public static void setWebDriver(WebDriver driver) {
	        webDriver.set(driver);
	    }

		public static WebDriverWait getWait(){
		 WebDriverWait wait = new WebDriverWait(getDriver(), 20);
		 return wait;
		}
}
