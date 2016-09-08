package com.travelex.nam.testscripts;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.travelex.framework.common.ConfigurationProperties;
import com.travelex.framework.common.EmailReport;
import com.travelex.framework.common.EnvironmentParameter;
import com.travelex.framework.common.Log;
import com.travelex.framework.common.WebDriverFactory;
import com.travelex.nam.pages.CustomerDetailsPage;
import com.travelex.nam.pages.HomePage;
import com.travelex.nam.pages.LoginPage;
import com.travelex.nam.pages.TransactionAndCurrencyPage;
import com.travelex.nam.utilities.InputDataProvider;

@Listeners(EmailReport.class)
public class SaleOrderFlow  {
	EnvironmentParameter environmentParameter;
	Logger logger = Logger.getLogger(SaleOrderFlow.class);
	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	
	@Parameters({ "Browser", "Version", "Platform"})
	@BeforeTest(alwaysRun = true)
	public void setEnviromentParameters(String browserName,String browserVersion, String platform) {
		environmentParameter = new EnvironmentParameter();
		environmentParameter.setBrowserName(browserName);
		environmentParameter.setBrowserVersion(browserVersion);
		environmentParameter.setPlatform(platform);
	}

	@DataProvider(name = "CreateAndVerifySaleOrder_DP", parallel = true)
	public Object[][] CreateAndVerifySaleOrder_DP(){
		InputDataProvider inputDataProvider = new InputDataProvider();
		Object[][] retObjArr = inputDataProvider.getRowDataMap("TestData","SaleOrder");
		return(retObjArr);
	}

	@Test(testName = "SaleOrder", groups = { "SaleOrder","functional" }, dataProvider = "CreateAndVerifySaleOrder_DP")
	public void CreateAndVerifySaleOrder_NAM(HashMap<String,String> saleOrderData) throws Throwable {
		
		WebDriver driver = null;
		try {
			String colWebSiteURL = configurationProperties.getProperty(ConfigurationProperties.COL_APPLICATION_URL) + saleOrderData.get("PartnerID");  
			driver = WebDriverFactory.get(environmentParameter);			
			Log.testCaseInfo("Sale order scenarios");
			LoginPage loginPage = new LoginPage(driver, colWebSiteURL);
		    HomePage homePage = loginPage.clickLogin(saleOrderData.get("LoginID"), saleOrderData.get("Password"));
			Log.assertThat(homePage != null, "Successful Login", "User is not logged in, Please check the credentials", driver);
			TransactionAndCurrencyPage transCurrPage = homePage.navigateToTransactionPage(saleOrderData);
			Log.assertThat(transCurrPage != null, "Transaction Page Loaded Successfully", "Transaction Page not Loaded", driver);
			transCurrPage.enterTransactionAndCurrDetails(saleOrderData);
			CustomerDetailsPage customerDetailsPage = transCurrPage.customerDetailsPage();
			Log.assertThat(customerDetailsPage != null, "Customer Details Page Loaded Successfully", "Customer Details Page not Loaded", driver);
			customerDetailsPage.submitCustomerAndDeliveryDetails(saleOrderData);
			Log.testCaseResult();		
		} catch (Exception exception) {	
				Log.exception(exception, driver);
			
		} finally {
			if(driver != null){
				driver.quit();
			}
			Log.endTestCase();
		}
	}
	
}
