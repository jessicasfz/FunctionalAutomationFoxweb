package com.travelex.nam.testscripts;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.travelex.framework.common.EmailReport;
import com.travelex.framework.common.Log;
import com.travelex.framework.common.WebDriverFactory;
import com.travelex.nam.pages.HomePage;
import com.travelex.nam.pages.LoginPage;


@Listeners(EmailReport.class)
public class SaleOrderFlow  {
	
	Logger logger = Logger.getLogger(SaleOrderFlow.class);
	String webSite;
	String browser;
	
	@BeforeTest
	public void init(ITestContext context) {
		webSite = (System.getProperty("webSite") != null ? System.getProperty("webSite") : context.getCurrentXmlTest().getParameter("webSite")).toLowerCase();
		browser = (System.getProperty("browser") != null ? System.getProperty("browser") : context.getCurrentXmlTest().getParameter("browser")).toLowerCase();
	}

/*	@DataProvider(name = "CreateAndVerifySaleOrder_DP", parallel = true)
	public Object[][] CreateAndVerifySaleOrder_DP(){
		Object[][] retObjArr = InputDataProvider.getTableArray("SaleOrder");
		return(retObjArr);
	}*/

	@Test(testName = "SaleOrder", groups = { "SaleOrder","functional" })
	public void CreateAndVerifySaleOrder_NAM()
			throws Throwable {
		
		WebDriver driver = null;
		try {
			
			driver = WebDriverFactory.get(browser);	
			Log.testCaseInfo("Sale order scenarios");
			LoginPage loginPage = new LoginPage(driver, webSite).get();
			String loginId = "a00010@usb";
			String password = "Pa$$word1";
			HomePage homePage = loginPage.clickLogin(loginId, password);
			Log.assertThat(homePage != null, "Successful Login", "User is not logged in, Please check the credentials", driver);
			Log.testCaseResult();
			System.out.println("end of tc");
		} catch (Exception exception) {			
			Log.exception(exception, driver);
		} finally {
			Log.endTestCase();
			driver.quit();
		}
	}
	
}
