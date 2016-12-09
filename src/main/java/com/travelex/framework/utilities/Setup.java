package com.travelex.framework.utilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;


public class Setup {

	static String absolutePath=new File("").getAbsolutePath();
	static String IEDriverLocation=absolutePath+"/drivers/IEDriverServer.exe";
	static String ChromeDriverLocation=absolutePath+"/drivers/chromedriver.exe";
	public static WebDriver createInstance(String browserName) {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
        WebDriver driver = null;
        if (browserName.toLowerCase().equalsIgnoreCase("FIREFOX")) {
            //driver = new FirefoxDriver();
        	capabilities = DesiredCapabilities.firefox();
        	try{
        			String hubURL = Utilities.getConfigValues("hubUrl");
        			
        			driver = new RemoteWebDriver(new URL(hubURL),capabilities);
        		}
        		catch(Exception e){
        			e.printStackTrace();
        			Reporter.log("Unable to launch browser instance due to following exception : "+e.getMessage());
        		}
        		
        		catch(Error err){
        			err.printStackTrace();
        			Reporter.log("Unable to launch browser instance due to following error : "+err.getMessage());
        		}
        		
        		((RemoteWebDriver)driver).setFileDetector(new LocalFileDetector());
        		driver.manage().window().maximize();
        		driver.manage().deleteAllCookies();
            return driver;
        }
        if (browserName.toLowerCase().equalsIgnoreCase("IE")) {
        	String jenkins = Utilities.getConfigValues("jenkins");
        	if(jenkins.equalsIgnoreCase("true")){
        		String driverPath="C:\\Grid";
        		try {
    				Runtime.getRuntime().exec("wscript "+driverPath+"/drivers/protectedmodeon.vbs");
    				System.out.println("Set IE Protected Mode Settings");
    			} catch (IOException e1) {
    				
    				e1.printStackTrace();
    			}
        	}
        	else
        	{
        	try {
				Runtime.getRuntime().exec("wscript "+absolutePath+"/drivers/protectedmodeon.vbs");
				System.out.println("Set IE Protected Mode Settings");
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
        	}
			//System.setProperty("webdriver.ie.driver",IEDriverLocation);
        	
    		System.out.println("iexplore");
    		capabilities = DesiredCapabilities.internetExplorer();
    		capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,false);
    		capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,false);
    		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
    		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,false);
    		capabilities.setCapability("ignoreProtectedModeSettings", true);
    		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);   
    		
    		try{
    		//	URL hubUrl = new URL(Utilities.getConfigValues("hubUrl"));
    			String hubURL = Utilities.getConfigValues("hubUrl");
    			
    			driver = new RemoteWebDriver(new URL(hubURL),capabilities);
    		}
    		catch(Exception e){
    			e.printStackTrace();
    			Reporter.log("Unable to launch browser instance due to following exception : "+e.getMessage());
    		}
    		
    		catch(Error err){
    			err.printStackTrace();
    			Reporter.log("Unable to launch browser instance due to following error : "+err.getMessage());
    		}
    		
    		((RemoteWebDriver)driver).setFileDetector(new LocalFileDetector());
    		driver.manage().window().maximize();
    		driver.manage().deleteAllCookies();
    		//driver = new InternetExplorerDriver(capabilities);
            return driver;
        }
        if (browserName.toLowerCase().equalsIgnoreCase("CHROME")) {
        	
        	capabilities = DesiredCapabilities.chrome();
        	capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        	capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
        	ChromeOptions options = new ChromeOptions();
        	options.addArguments("chrome.switches","--disable-extensions");
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

           //driver = new ChromeDriver(capabilities);
        	try{
        			String hubURL = Utilities.getConfigValues("hubUrl");
        			driver = new RemoteWebDriver(new URL(hubURL),capabilities);
        		}
        		catch(Exception e){
        			e.printStackTrace();
        			Reporter.log("Unable to launch browser instance due to following exception : "+e.getMessage());
        		}
        		
        		catch(Error err){
        			err.printStackTrace();
        			Reporter.log("Unable to launch browser instance due to following error : "+err.getMessage());
        		}
        		
        		((RemoteWebDriver)driver).setFileDetector(new LocalFileDetector());
        		driver.manage().window().maximize();
        		driver.manage().deleteAllCookies();
            return driver;
        }
        return driver;
    }
   
	public static WebDriver relaunchWebdriverBrowser(){
		 String browserName =Utilities.getConfigValues("Browser_Type");
		 WebDriver driver = Setup.createInstance(browserName);
         DriverFactory.setWebDriver(driver);
		return driver;
		
	}
	
	public static WebDriver voltaPurchaseDriver(){
		WebDriver driver = null;
		Reporter.log("<B><I><font size='4' color='Black'>"+"---------Launching Purchase Volta--------"+ "</font></I></B>");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("app", "C:\\Volta Purchases\\purchases.exe");
        cap.setCapability("launchDelay", "5");   
               
        try {
           driver = new RemoteWebDriver(new URL("http://localhost:9999"),cap);
           
           Thread.sleep(20000);
        } catch (Exception e){
            e.printStackTrace();
        }
		
		return driver;
		
	}
	
	public static WebDriver voltaSaleDriver(){
		WebDriver driver = null;
		Reporter.log("<B><I><font size='4' color='Black'>"+"---------Launching Sale Volta--------"+ "</font></I></B>");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("app", "C:\\Volta\\rap.exe");
			cap.setCapability("launchDelay", "15");
			
				try {
					driver = new RemoteWebDriver(new URL("http://localhost:9999"),cap);
					
					//driver = new WiniumDriver(new URL("http://localhost:9999"),options);
				} catch (MalformedURLException e) {
					
					e.printStackTrace();
				}
		
		return driver;
	}

	public static WebDriver voltaAutoPRDriver(){
		WebDriver driver = null;
		Reporter.log("<B><I><font size='4' color='Black'>"+"---------Launching AutoPR Volta--------"+ "</font></I></B>");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("app", "C:\\SaleAutoPRexe\\rapautopr.exe.lnk");
			cap.setCapability("launchDelay", "15");
			
				try {
					driver = new RemoteWebDriver(new URL("http://localhost:9999"),cap);
					
					//driver = new WiniumDriver(new URL("http://localhost:9999"),options);
				} catch (MalformedURLException e) {
					
					e.printStackTrace();
				}
		
		return driver;
	}

}