package com.travelex.stepDefinitions;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;

import com.travelex.framework.common.ConfigurationProperties;
import com.travelex.framework.common.EnvironmentParameter;
import com.travelex.framework.common.WebDriverFactory;
import com.travelex.framework.common.WebDriverWrapper;
import com.travelex.nam.pages.CustomerDetailsPage;
import com.travelex.nam.pages.HomePage;
import com.travelex.nam.pages.LoginPage;
import com.travelex.nam.pages.PrinterFriendlyPage;
import com.travelex.nam.pages.SearchOrderPage;
import com.travelex.nam.pages.TransactionAndCurrencyPage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SuppressWarnings("unused")
public class StepDefinitions {	
	private WebDriver driver;
	private static Scenario scenario;
	private LoginPage loginPage;
	private HomePage homePage;
	private SearchOrderPage searchOrderPage;
	private TransactionAndCurrencyPage transactionAndCurrencyPage;
	WebDriverWrapper wrapper;
	private int timeOutPeriod = 3000;
	private int waitTime=300;
	private CustomerDetailsPage customerDetailsPage;
	private PrinterFriendlyPage printerFriendlyPage;
	ArrayList<HashMap<String,String>> orderDetails;
	HashMap<String,String> addDetails;

	ConfigurationProperties configurationProperties = ConfigurationProperties.getInstance();
	static EnvironmentParameter environmentParameter;
	public static HashMap<String,String> hashmap;	
      
    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */
    
    @Before
    public void openBrowser(Scenario scenario) throws MalformedURLException,Throwable {    	
		String platform = configurationProperties.getProperty(ConfigurationProperties.PLATFORM);
		String browserName = configurationProperties.getProperty(ConfigurationProperties.BROWSER_NAME);
		String browserVersion = configurationProperties.getProperty(ConfigurationProperties.BROWSER_VERSION);		
		environmentParameter = new EnvironmentParameter();
		environmentParameter.setBrowserName(browserName);
		environmentParameter.setBrowserVersion(browserVersion);
		environmentParameter.setPlatform(platform);		 		
		driver = WebDriverFactory.get(environmentParameter);
		hashmap=new HashMap<String,String>();
		StepDefinitions.scenario = scenario;
    }
   
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    
    @After
    public void embedScreenshot(Scenario scenario) {
        if(scenario.isFailed()) {
        try {
            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }        
        }
        driver.quit();
    }
					
	@Given("^I Login to COL with username \"([^\"]*)\" and password \"([^\"]*)\" for Partner \"([^\"]*)\"$")
	public void i_Login_to_COL_with_username_and_password_for_Partner(String username, String password, String partnerid) throws Throwable {
		String colWebSiteURL = configurationProperties.getProperty(ConfigurationProperties.COL_APPLICATION_URL) + partnerid;
		String browserName = configurationProperties.getProperty(ConfigurationProperties.BROWSER_NAME);
		loginPage = new LoginPage(driver, colWebSiteURL, browserName).get();
		homePage = loginPage.clickLogin(username,password);	    
	}
	
	@Given("^I Login to COL with incorrect credetials username \"([^\"]*)\" and password \"([^\"]*)\" for Partner \"([^\"]*)\"$")
	public void i_Login_to_COL_with_incorrect_credetials_username_and_password_for_Partner(String username, String password, String partnerid) throws Throwable {
		String colWebSiteURL = configurationProperties.getProperty(ConfigurationProperties.COL_APPLICATION_URL) + partnerid;
		String browserName = configurationProperties.getProperty(ConfigurationProperties.BROWSER_NAME);
		loginPage = new LoginPage(driver, colWebSiteURL, browserName).get();
		loginPage = loginPage.clickInvalidLogin(username,password);	    
	}
	
	@Then("^I navigate to Home Page$")
	public void i_navigate_to_Home_Page() throws Throwable {
		Assert.assertEquals(homePage.isHomePageLoaded(), true);
	}

	@When("^I click on Retrive button$")
	public void i_click_on_Retrive_button() throws Throwable {
		transactionAndCurrencyPage = new TransactionAndCurrencyPage(driver).get();
		transactionAndCurrencyPage.btnRetrive();
	}
	
	@When("^I select branch \"([^\"]*)\"$")
	public void i_select_branch(String branchLocation) throws Throwable {
		transactionAndCurrencyPage.selectBranch(branchLocation);
	}

	@When("^I click on Next button$")
	public void i_click_on_Next_button() throws Throwable {
		transactionAndCurrencyPage.nextButtonClick();
	}

	@Then("^I navigate to Transaction Page$")
	public void i_navigate_to_Transaction_Page() throws Throwable {
		Assert.assertEquals(transactionAndCurrencyPage.isTransactionPageLoaded(), true);
	}

	@When("^I select orderType \"([^\"]*)\" Link$")
	public void i_select_orderType_Link(String orderType) throws Throwable {
		transactionAndCurrencyPage = new TransactionAndCurrencyPage(driver).get();
		transactionAndCurrencyPage.orderTypeSelection(orderType);
	}

	@When("^I select transaction type \"([^\"]*)\", product type \"([^\"]*)\" and currency \"([^\"]*)\"$")
	public void i_select_transaction_type_product_type_and_currency(String transactionType, String productType, String currency) throws Throwable {
		transactionAndCurrencyPage.transactionDetails(transactionType,productType,currency);
	}

	@When("^I enter amount \"([^\"]*)\"$")
	public void i_enter_amount(String foreignAmount) throws Throwable {
		transactionAndCurrencyPage.enterForeignAmount(foreignAmount);
	}
	
	@When("^I see updated order amount \"([^\"]*)\"$")
	public void i_see_updated_order_amount(String foreignAmount) throws Throwable {
		transactionAndCurrencyPage.enterForeignAmount(foreignAmount);
	}

	@When("^I click Add To Order button$")
	public void i_click_Add_To_Order_button() throws Throwable {
		transactionAndCurrencyPage.addOrderBtnClick();
	}

	@Then("^I should see accountHolderMessage \"([^\"]*)\"$")
	public void i_should_see_accountHolderMessage(String expectedValue) throws Throwable {
		Assert.assertEquals(transactionAndCurrencyPage.accountHolderMessagePoPupValidation(), expectedValue);
		transactionAndCurrencyPage.acceptAlert();
	}

	@When("^I select confirm transaction Checkbox$")
	public void i_select_Checkbox() throws Throwable {
		String browserName = configurationProperties.getProperty(ConfigurationProperties.BROWSER_NAME);
		transactionAndCurrencyPage.checkBoxClick(browserName);
	}

	@Then("^I should see denominationMessage \"([^\"]*)\"$")
	public void i_should_see_denominationMessage(String expectedValue) throws Throwable {
		Assert.assertEquals(transactionAndCurrencyPage.denominationMessagePoPupValidation(), expectedValue);
	}

	@Then("^I click Cancel button on Transaction Page$")
	public void i_click_Cancel_button() throws Throwable {
		transactionAndCurrencyPage.dismissAlert();
	}

	@When("^I select delivery Type \"([^\"]*)\"$")
	public void i_select_delivery_Type(String deliveryType) throws Throwable {
		transactionAndCurrencyPage.deliveryTypeBtn(deliveryType);
	}

	@Then("^I get calculated total amount according to delivery charges$")
	public void i_get_calculated_total_amount_according_to_delivery_charges() throws Throwable {
		orderDetails = transactionAndCurrencyPage.getCurrencies();
		//transactionAndCurrencyPage.fetchOrderDetails(Currency);
	}

	@When("^I click complete order button on Transaction Page$")
	public void i_click_complete_order_button_on_transaction_Page() throws Throwable {
		customerDetailsPage = transactionAndCurrencyPage.customerDetailsPage();
	}
	
	@When("^I click complete order button$")
	public void i_click_complete_order_button() throws Throwable {
		transactionAndCurrencyPage.clickOnConfirmButtion();
	}

	@Then("^I navigate to Customer Details Page$")
	public void i_navigate_to_Customer_Details_Page() throws Throwable {
		customerDetailsPage = new CustomerDetailsPage(driver).get();
		Assert.assertEquals(customerDetailsPage.isCustomerDetailsPageLoaded(),true);
	}
	
	@When("^I enter Customer details customerRadioBtn \"([^\"]*)\", firstName \"([^\"]*)\", lastName \"([^\"]*)\" and GLNo \"([^\"]*)\"$")
	public void i_enter_Customer_details_customerRadioBtn_firstName_lastName_and_GLNo(String customerRadio, String firstName, String lastName, String glNo) throws Throwable {
		customerDetailsPage.enterCustomerDetails(customerRadio,firstName,lastName,glNo);
		/*hashmap = new HashMap<String,String>();
		hashmap.put("radioBtn", customerRadio);
		hashmap.put("fName", firstName);
		hashmap.put("lName", lastName);
		hashmap.put("GLNo", glNo);*/
	}

	@When("^I enter Delivery details attention \"([^\"]*)\" branchContact \"([^\"]*)\" phoneNumber \"([^\"]*)\"$")
	public void i_enter_Delivery_details_attention_branchContact_phoneNumber(String attention, String branchContact, String phoneNumber) throws Throwable {
		customerDetailsPage.enterDeliveryDetails(attention,branchContact,phoneNumber);
		/*hashmap.put("atten", attention);
		hashmap.put("contact", branchContact);
		hashmap.put("phone", phoneNumber);*/

	}

	@When("^I click Complete Order button on Customer Details Page$")
	public void i_click_completeOrder_button_on_Customer_Details_Page() throws Throwable {
		/*addDetails = customerDetailsPage.fetchAddressDetails();*/
		customerDetailsPage.submitCustomerDetails();
	}
	

	@Then("^I get Confirmation number$")
	public void i_get_Confirmation_number() throws Throwable {
		String confirmationNumber = customerDetailsPage.fetchConfirmationNumber();
		scenario.write("Confirmation Number is : " +confirmationNumber);
	}

	@When("^I click Printer Friendly button$")
	public void i_click_Printer_Friendly_button() throws Throwable {
		String browserName = configurationProperties.getProperty(ConfigurationProperties.BROWSER_NAME);
		String confirmWindow = "https://172.16.116.55/col/orderConfirmation.do";
		customerDetailsPage.printerFriendlyBtn(browserName,confirmWindow);
	}

	@Then("^I should see order details$")
	public void i_should_see_order_details() throws Throwable {
		String printWindow = "https://172.16.116.55/col/orderConfirmationPrint.do";
		String confirmWindow = "https://172.16.116.55/col/orderConfirmation.do";
		transactionAndCurrencyPage.switchToWindow(printWindow,driver);	
		transactionAndCurrencyPage.closeWindow(printWindow,driver);
		transactionAndCurrencyPage.switchToWindow(confirmWindow,driver);
		/*printerFriendlyPage = new PrinterFriendlyPage(driver);
		printerFriendlyPage.verifyOrderDetails(orderDetails,addDetails);
		scenario.write("Order Validation is successful");*/
	}

	@Then("^I should see amountMessage \"([^\"]*)\"$")
	public void i_should_see_amountMessage(String expectedValue) throws Throwable {
		Assert.assertEquals(transactionAndCurrencyPage.amountHolderMessage(), expectedValue);
		transactionAndCurrencyPage.acceptAlert();
	}

	@Then("^I click on Quote and View button$")
	public void i_click_on_Quote_and_View_button() throws Throwable {
		transactionAndCurrencyPage.quoteAndViewButton();
	}

	@Then("^I enter customerName \"([^\"]*)\", attention \"([^\"]*)\" and branchDetails \"([^\"]*)\"$")
	public void i_enter_customerName_attention_and_branchDetails(String arg1, String arg2, String arg3) throws Throwable {
	 
	}
	
	@And("^I click Next button on Transaction Page$")
	public void i_click_Next_button_on_Transaction_Page() throws Throwable {
		transactionAndCurrencyPage.nextOrderButtonClick();
	}
	
	@When("^I select transaction type \"([^\"]*)\" and customerType \"([^\"]*)\"$")
    public void i_select_transaction_type_and_customerType(String transactionType, String accountHolderType) throws Throwable {
       transactionAndCurrencyPage.transactionDetailsAndcustomerType(transactionType, accountHolderType);
    }

    @When("^I select product type \"([^\"]*)\" and currency \"([^\"]*)\"$")
    public void i_select_product_type_and_currency(String productType, String currency) throws Throwable {
           transactionAndCurrencyPage.productTypeAndcurrencySelection(productType, currency);       
    }

    @When("^I enter beneficiary \"([^\"]*)\", address \"([^\"]*)\", city \"([^\"]*)\", state \"([^\"]*)\" and zipCode \"([^\"]*)\"$")
    public void i_enter_beneficiary_address_city_state_and_zipCode(String beneficiary, String address, String city, String state, String zipcode) throws Throwable {
           transactionAndCurrencyPage.enterBeneficiaryDetails(beneficiary, address, city, state, zipcode);
    }

    @When("^I select country \"([^\"]*)\"$")
    public void i_select_country(String country) throws Throwable {
           transactionAndCurrencyPage.selectCountry(country);
    }

    @When("^I enter comments \"([^\"]*)\"$")
    public void i_enter_comments(String comment) throws Throwable {
           transactionAndCurrencyPage.enterComments(comment);
    }

    @Then("^I should see enterAmountPopupMessage \"([^\"]*)\"$")
    public void i_should_see_enterAmountPopupMessage(String enterAmountPopUp) throws Throwable {
    	Assert.assertEquals(transactionAndCurrencyPage.switchtoPopUpAndGetMessage(), enterAmountPopUp);
    }

    @When("^I accept the alert$")
    public void i_accept_the_alert() throws Throwable {
       transactionAndCurrencyPage.acceptAlert();
    }
    
    @When("^I dismiss the alert$")
    public void i_dismiss_the_alert() throws Throwable {
    	transactionAndCurrencyPage.dismissAlert();
    }

    @Then("^I see Todays Rate$")
    public void i_see_Todays_Rate() throws Throwable {
    	transactionAndCurrencyPage.todaysRateIsDisplay();       
    }

    @Then("^I should see currencyViewRequiredPopup \"([^\"]*)\"$")
    public void i_should_see_currencyViewRequiredPopup(String CurrencyViewRequired) throws Throwable {
    	Assert.assertEquals(transactionAndCurrencyPage.switchtoPopUpAndGetMessage(), CurrencyViewRequired);
    }

    @When("^I click on Show Currency button and Switch to New Window$")
    public void i_click_on_Show_Currency_button_and_Switch_to_New_Window() throws Throwable {
    	transactionAndCurrencyPage.clickShowCurrencyButton();
    	transactionAndCurrencyPage.switchTolatestWindow();
    	
    }

    @Then("^I Should see Currency Window$")
    public void i_Should_see_Currency_Window() throws Throwable {
    	Assert.assertEquals(transactionAndCurrencyPage.validateCurrencywindowVisibility(), true);
    }
    
    @When("^I click on Close button In Currency Window$")
    public void i_click_on_Close_button_In_Currency_Window() throws Throwable {
    	transactionAndCurrencyPage.closeCurrencyWindowAndSwitchBackMainWindow();     
    }

    @When("^I Uncheck Waive Account Holder Fee$")
    public void i_Uncheck_Waive_Account_Holder_Fee() throws Throwable {
        transactionAndCurrencyPage.uncheckwaiveFeeCheck();
    }
    

    @When("^I enter Customer Details customerAddressOne \"([^\"]*)\", customerAddressTwo \"([^\"]*)\", city \"([^\"]*)\", state \"([^\"]*)\", zipcode \"([^\"]*)\", and country \"([^\"]*)\"$")
    public void enterCustomerAddressdetails(String customerAddressOne, String customerAddressTwo, String city, String state, String zipCode, String country) throws Throwable {
    	customerDetailsPage.enterCustomerAddress(customerAddressOne, customerAddressTwo, city, state, zipCode, country);
    }

    @When("^I enter Customer Security Information primaryID \"([^\"]*)\", IDNumber \"([^\"]*)\", countryofIssuance \"([^\"]*)\", securitystate \"([^\"]*)\", issueDate \"([^\"]*)\", and ExpiryDate \"([^\"]*)\"$")
    public void enterCustomerSecurityDetails(String primaryID, String idNumber, String countryOfIssuance, String state, String issueDate, String expiryDate) throws Throwable {
        customerDetailsPage.enterCustomerSecurityInformation(primaryID, idNumber, countryOfIssuance, state, issueDate, expiryDate);
    }
    
    @When("^I enter Customer Contact details areaCode \"([^\"]*)\", phoneNumber \"([^\"]*)\", and extension \"([^\"]*)\"$")
    public void enterCustomerContactDetails(String areaCode, String phoneNumber, String extension) throws Throwable {
    	customerDetailsPage.enterCustomerContactDetails(areaCode, phoneNumber, extension);
    }
    
    @When("^I enter Customer details customerRadioBtn \"([^\"]*)\", firstName \"([^\"]*)\", lastName \"([^\"]*)\", initial \"([^\"]*)\", bankID \"([^\"]*)\" , customerAccNo \"([^\"]*)\" and dataOfBirth \"([^\"]*)\"$")
    public void i_enter_Customer_details_customerRadioBtn_firstName_lastName_initial_bankID_customerAccNo_and_dataOfBirth(String customerRadioBtn, String firstName, String lastName, String initial, String bankID, String dateOfBirth, String customerAccountNo) throws Throwable {
    	customerDetailsPage.enterCustomerDetails(customerRadioBtn,firstName, lastName, initial, dateOfBirth, bankID, customerAccountNo);
    }
    
    
    @When("^I check Waive Account Holder Fee$")
    public void i_check_Waive_Account_Holder_Fee() throws Throwable {
        transactionAndCurrencyPage.checkwaiveFeeCheck();
    }
 
    @When("^I select reason for Waiver Fee \"([^\"]*)\"$")
    public void i_select_reason_for_Waiver_Fee(String waiveReason) throws Throwable {
    	transactionAndCurrencyPage.selectReasonForwaiver(waiveReason);        
    }
    
    @When("^I select method Of payment \"([^\"]*)\"$")
    public void i_select_method_Of_payment(String methodOfPayment) throws Throwable {
        transactionAndCurrencyPage.selectPaymentMethod(methodOfPayment);
    }
    
    @When("^I enter specific denoms CurrencyValue \"([^\"]*)\" DenomsQuantity \"([^\"]*)\"$")
    public void i_enter_specific_denoms_CurrencyValue_DenomsQuantity(String currencyValue, String denomsQuantity) throws Throwable {
    	transactionAndCurrencyPage.denomSelection(currencyValue, denomsQuantity);       
    }
        
    @When("^I select transaction type \"([^\"]*)\", product type \"([^\"]*)\"$")
    public void i_select_transaction_type_product_type(String transactionType, String productType) throws Throwable {
    	transactionAndCurrencyPage.transactionDetails(transactionType,productType);        
    }

    @When("^I  Select currency \"([^\"]*)\"$")
    public void i_Select_currency(String currency) throws Throwable {
    	transactionAndCurrencyPage.transactionDetails(currency);
    }
    
    @Then("^I should see ErrorMeassage  \"([^\"]*)\"$")
    public void i_should_see_ErrorMeassage(String errorMessage) throws Throwable {
    	Assert.assertEquals(transactionAndCurrencyPage.switchtoPopUpAndGetMessage(), errorMessage);
    	transactionAndCurrencyPage.acceptAlert();
    }
    
    @Then("^I click on clearFilelds button$")
    public void i_click_on_clearFilelds_button() throws Throwable {
    	transactionAndCurrencyPage.clickOnclearFieldsButton();
    }

    @Then("^I See all input fields as empty Except CCY$")
    public void i_See_all_input_fields_as_empty_Except_CCY() throws Throwable {
       
    }
    
    @When("^I click On cancel Order button$")
    public void i_click_On_Cancel_Order_button() throws Throwable {
        customerDetailsPage.clickOnCancelOrderButton();
    }
    
    @When("^I click On change Order button$")
    public void i_click_On_Change_Order_button() throws Throwable {
        customerDetailsPage.clickOnChangeOrderButton();
    }
    
    
    @When("^I click On delete button in Transaction Page$")
    public void i_click_On_delete_button_in_Transaction_Page() throws Throwable {
        transactionAndCurrencyPage.clickOnDeleteOrderButton();
    }
    
    @When("^I select searchOrder Link$")
    public void i_select_searchOrder_Link() throws Throwable {
    	searchOrderPage = transactionAndCurrencyPage.clickSearchOrderLink();   
    }
    
    @Then("^I navigate to Search Order Page$")
    public void i_navigate_to_Search_Order_Page() throws Throwable {
    	Assert.assertEquals(searchOrderPage.isSearchOrderPageLoaded(), true);
    }
    
    @Then("^I select orderStatus \"([^\"]*)\"$")
    public void i_select_orderStatus(String orderStaus) throws Throwable {
    	searchOrderPage.selectOrderStatus(orderStaus);
    }
    
    @Then("^I click on Search button on Search Order Result Page$")
    public void i_click_on_Search_button_on_Search_Order_Result_Page() throws Throwable {
    	searchOrderPage.searchButtonClick();
    }
    
    @When("^I enter confirmation Number\"([^\"]*)\" in orderNo field$")
    public void i_enter_confirmation_Number_in_orderNo_field(String confirmationNo) throws Throwable {
    	searchOrderPage.enterConfirmOrderNumber(confirmationNo);
    }
    
    @When("^I click on order number link$")
    public void i_click_on_order_number_link() throws Throwable {
    	searchOrderPage.orderNumberLinkClick();
    }

    @Then("^I navigate to Order Summary Page$")
    public void i_navigate_to_Order_Summary_Page() throws Throwable {
    	Assert.assertEquals(searchOrderPage.isOrderSummaryPageLoaded(), true);
    }
    
    @Then("^I click on Previous Page button$")
    public void i_click_on_Previous_Page_button() throws Throwable {
    	searchOrderPage.previousOrderSumPageBtn();
    }
    
    @Then("^I navigate to search order result Page$")
    public void i_navigate_to_search_order_result_Page() throws Throwable {
    	Assert.assertEquals(searchOrderPage.isSearchOrderResultPageLoaded(), true);
    }
    
    @Then("^I click on Export To Excel button$")
    public void i_click_on_Export_To_Excel_button() throws Throwable {
    	searchOrderPage.exportToExcelClick();
    }
    
    @Then("^I enter TellerName \"([^\"]*)\"$")
    public void i_enter_TellerName(String tellerName) throws Throwable {
    	searchOrderPage.enterTellerName(tellerName);
    }
    
    @Then("^I enter CustomerName \"([^\"]*)\"$")
    public void i_enter_CustomerName(String customerName) throws Throwable {
    	searchOrderPage.enterCustomerName(customerName);
    }
    
    @When("^I enter fromDate as \"([^\"]*)\" and toDate as \"([^\"]*)\"$")
    public void i_enter_fromDate_as_and_toDate_as(String fromDate, String toDate) throws Throwable {
    	searchOrderPage.enterValidDate(fromDate, toDate);
    }
    
    @Then("^I should see errorMessage as \"([^\"]*)\"$")
    public void i_should_see_errorMessage_as(String errorMessage) throws Throwable {
    	Assert.assertEquals(searchOrderPage.switchtoPopUpAndGetMessage(), errorMessage);
    	searchOrderPage.acceptAlert();
    }

    @When("^I click on Next Link$")
    public void i_click_on_Next_Link() throws Throwable {
    	searchOrderPage.nextLinkClick();
    }
    
    @Then("^I should see popup message as \"([^\"]*)\"$")
    public void i_should_see_popup_message_as(String errorMessage) throws Throwable {
    	Assert.assertEquals(transactionAndCurrencyPage.switchtoPopUpAndGetMessage(), errorMessage);
    	transactionAndCurrencyPage.acceptAlert();
    }
    
    @When("^I click complete order button on Order Page$")
	public void i_click_complete_order_button_on_Order_Page() throws Throwable {
		transactionAndCurrencyPage.clickOnConfirmButtion();
	}

    @And("^I click clear button on Transaction Page$")
	public void i_click_on_clear_button_on_Transaction_Page() throws Throwable {
		transactionAndCurrencyPage.clearButtonClick();
	}

    @And("^I click Ok button on Transaction Page$")
   	public void i_click_on_Ok_button_on_Transaction_Page() throws Throwable {
    	transactionAndCurrencyPage.acceptAlert();
   	}
    
    @Then("^I click Delete button on Transaction Page$")
	public void i_click_Delete_button() throws Throwable {
		transactionAndCurrencyPage.deleteButtonClick();
	}
    
    @Then("^I enter quantity \"([^\"]*)\" on Denomination Table$")
    public void i_enter_quantity_on_Denomination_Table(String denomQuantity) throws Throwable {
        transactionAndCurrencyPage.fillDenomQuantity(denomQuantity);
    }
    
    @Then("^I navigate to Denomination Information Page$")
    public void i_navigate_to_Denomination_Transaction_Page() throws Throwable {
            Assert.assertEquals(transactionAndCurrencyPage.isDenomInfoLoaded(), true);
    }
    
    @And("^I click change order on Transaction Page$")
    public void i_click_Change_Order_button() throws Throwable {
            transactionAndCurrencyPage.changeOrderButtonClick();
    }

    @Then("^I click edit button on Transaction Page$")
    public void i_click_Edit_button() throws Throwable {
            transactionAndCurrencyPage.editButtonClick();
    }

    @And("^I click on change branch link$")
    public void i_click_on_change_branch_link() throws Throwable {
    transactionAndCurrencyPage.changeBranchLink();
    }

    @Then("^I navigate to Branch Selection Page$")
    public void i_navigate_to_Branch_Selection_Page() throws Throwable {
            Assert.assertEquals(transactionAndCurrencyPage.isBranchSelectionPageLoaded(), true);
    }


    @Then("^I should see branch drop down$")
    public void i_should_see_branch_drop_down() throws Throwable {
           Assert.assertEquals(transactionAndCurrencyPage.isBranchSelectionPageLoaded(), true);
    }
    
    @Then("^I should see error message \"([^\"]*)\"$")
	public void I_should_see_error_message(String expectedValue){
		Assert.assertEquals(loginPage.errorMessagePoPupValidation(), expectedValue);
	}
    
    @When("^I select currency \"([^\"]*)\"$")
    public void i_select_currency(String currency) throws Throwable {
            transactionAndCurrencyPage.selectCurrency(currency);
    }
    
    @Then("^I should see denominationWarningMessage \"([^\"]*)\"$")
    public void i_should_see_denominationWarningMessage(String expectedValue) throws Throwable {
            Assert.assertEquals(transactionAndCurrencyPage.denominationMessagePoPupValidation(), expectedValue);
    }
    
    @And("^I modify foreignAmount \"([^\"]*)\"$")
    public void i_modify_foreign_amount(String ForeignAmountOne) throws Throwable {
            transactionAndCurrencyPage.enterForeignAmount(ForeignAmountOne);
    }

    @And("^I click specify denom button on Transaction Page$")
    public void i_click_specify_denom_button_on_Transaction_Page() throws Throwable {
    transactionAndCurrencyPage.addOrderBtnClick();
    transactionAndCurrencyPage.acceptAlert();
    }

    @When("^I click on Rate Sheet$")
	public void i_click_on_Rate_Sheet() throws Throwable {
		transactionAndCurrencyPage.rateSheetClick();
	}
    
    @Then("^I navigate to Rate Sheet page$")
	public void i_navigate_to_Rate_Sheet_Page() throws Throwable {
		Assert.assertEquals(transactionAndCurrencyPage.isTransactionPageLoaded(), true);
	}
    
    @When("^I click on View Rate Sheet$")
   	public void i_click_on_View_Rate_Sheet() throws Throwable {
   		transactionAndCurrencyPage.viewRateSheetClick();
   	}
    
    @When("^I click on Daily Branch Activity$")
   	public void i_click_on_Daily_Branch_Activity() throws Throwable {
   		transactionAndCurrencyPage.dailyBranchClick();
   	}

    @When("^I click on Export to excel$")
   	public void i_click_on_Export_to_excel() throws Throwable {
   		transactionAndCurrencyPage.exportExcelClick();
   	}
    
    @Then("^I navigate to Daily Branch Activity page$")
	public void i_navigate_to_Daily_Branch_Activity_page() throws Throwable {
		Assert.assertEquals(transactionAndCurrencyPage.isTransactionPageLoaded(), true);
	}
    
    @Then("^I should see report$")
    public void i_should_see_report() {
    	String homeDir = System.getProperty("user.home");
        String downloadpath = homeDir+"\\Downloads";
    	String filename = "Order History Report.XLS";
    	transactionAndCurrencyPage.fileStatusMsg(downloadpath, filename);
    }
    
    @Given("^I close my Browser$")
    public void i_close_my_Browser() throws Throwable {
        driver.quit();
    }

    @Given("^I Relaunch Browser$")
    public void i_Relaunch_Browser() throws Throwable {
		String platform = configurationProperties.getProperty(ConfigurationProperties.PLATFORM);
		String browserName = configurationProperties.getProperty(ConfigurationProperties.BROWSER_NAME);
		String browserVersion = configurationProperties.getProperty(ConfigurationProperties.BROWSER_VERSION);		
		environmentParameter = new EnvironmentParameter();
		environmentParameter.setBrowserName(browserName);
		environmentParameter.setBrowserVersion(browserVersion);
		environmentParameter.setPlatform(platform);
    	driver = WebDriverFactory.get(environmentParameter);    	
    }

    @Given("^I quit My Browser$")
    public void i_quit_My_Browser() throws Throwable {
    	driver.quit();
    }
}
