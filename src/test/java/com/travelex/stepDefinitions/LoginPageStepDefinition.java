package com.travelex.stepDefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.travelex.framework.Dataset.OrderDetailsDataSet;
import com.travelex.framework.common.ConfigurationProperties;
import com.travelex.framework.common.EnvironmentParameter;
import com.travelex.framework.common.WebDriverWrapper;
import com.travelex.nam.pages.LoginPage;
import com.travelex.nam.pages.TransactionAndCurrencyPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginPageStepDefinition {
	
	public WebDriver driver;
	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	static EnvironmentParameter environmentParameter;
	private LoginPage loginPage;
	private TransactionAndCurrencyPage transactionAndCurrencyPage;
	public int index;
	private List<OrderDetailsDataSet> ordDataset;
		
	public LoginPageStepDefinition(){
		driver = MasterDataReader.driver;
		ordDataset = MasterDataReader.orderDataset;
		index = MasterDataReader.index;
	}
	
	@Given("^I login to COL application$")
	public void i_login_to_COL_application() throws Throwable {
		String colWebSiteURL = configurationProperties.getProperty(ConfigurationProperties.COL_APPLICATION_URL) + ordDataset.get(index).getPartnerID();
		String browserName = configurationProperties.getProperty(ConfigurationProperties.BROWSER_NAME);
		loginPage = new LoginPage(driver, colWebSiteURL, browserName).get();
		loginPage.clickLogin(ordDataset.get(index).getUsername(),ordDataset.get(index).getPassword());
		if(WebDriverWrapper.isConfigTrue(ordDataset.get(index).getConfigBranch())){
			transactionAndCurrencyPage = new TransactionAndCurrencyPage(driver).get();
			transactionAndCurrencyPage.btnRetrive();
			transactionAndCurrencyPage.selectBranch(ordDataset.get(index).getBranchLocation());
			transactionAndCurrencyPage.nextButtonClick();
		}
		
	}

}
