package com.travelex.framework.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactory {

	 private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	 private static WebDriverWait wait;
	 private static long waitT = 30;
	 
	    public static WebDriver getDriver() {
	        return webDriver.get();
	    }
	 
	    public static void setWebDriver(WebDriver driver) {
	        webDriver.set(driver);
	    }

		public static WebDriverWait getWait(){
		 wait = new WebDriverWait(getDriver(), waitT);
		 return wait;
		}
		
		public static void setWebDriverWait(long waittime){
			waitT = waittime;
		}
}
