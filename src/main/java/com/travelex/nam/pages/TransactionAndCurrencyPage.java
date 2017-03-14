package com.travelex.nam.pages;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.travelex.framework.common.ConfigurationProperties;
import com.travelex.framework.common.WebDriverWrapper;
import com.travelex.stepDefinitions.MasterDataReader;

public class TransactionAndCurrencyPage extends LoadableComponent<TransactionAndCurrencyPage>{
	public String delimiter ="\\|";
	public WebDriver driver;
	WebDriverWrapper wrapper ;
	private int timeOutPeriod = 3000;
	private int waitTime=300;
	public WebElement ele = null;
	public static double transactionTotal,serviceCharge,accountFee,deleiveryCharge;
	public static int noOfrows;
	String parentWindow = null;

		
	private ConfigurationProperties configurationProperties = new ConfigurationProperties();
	String browserName = configurationProperties.getProperty(ConfigurationProperties.BROWSER_NAME);

	@FindBy(id = "searchTerm")
	WebElement txtBranch;

	@FindBy(id = "btnSearch")
	WebElement btnRetrieve;
	
	@FindBy(id = "f_date_c")
	WebElement txtCheckData;
	
	@FindBy(id = "countryCodeId")
	WebElement lstCountryOfIssue;
	
	@FindBy(name = "issuerEndorsed")
	WebElement rbnIssuerEndorsed;
	
	@FindBy(name = "payeeEndorsed")
	WebElement rbnPayeeEndorsed;
		
	@FindBy(name = "branchId")
	WebElement lstBranchLocation;

	@FindBy(xpath = "//input[@value='  NEXT  ']")
	WebElement btnNext;

	@FindBy(css = "#mainpage")
	WebElement lblTransactionPage;

	@FindBy(xpath = "//td[starts-with(normalize-space(),'Sale to')]/input")
	WebElement rbSale;

	@FindBy(xpath = "//td[starts-with(normalize-space(),'Purchase from')]/input")
	WebElement rbPurchase;

	@FindBy(xpath = "//*[@id='frmDeliveryOptionsId']//tr[1]//input")
	WebElement rbPrirityOvernightBtn;
	
	@FindBy(xpath = "//*[@id='frmDeliveryOptionsId']//tr[1]//input/../following-sibling::td[6]")
	WebElement rbPrirityOvernightFee;

	@FindBy(xpath = "//*[@id='frmDeliveryOptionsId']//tr[2]//input")
	WebElement rbNextDeliveryBtn;
	
	@FindBy(xpath = "//*[@id='frmDeliveryOptionsId']//tr[2]//input/../following-sibling::td[6]")
	WebElement rbNextDeliveryFee;

	@FindBy(name = "productSetId")
	WebElement lstProductDetails;

	@FindBy(name = "productCode")
	WebElement lstCurrency;

	@FindBy(name = "domesticAmount")
	WebElement txtDomesticCurrency;

	@FindBy(name = "foreignAmount")
	WebElement txtForeignAmount;

	/*@FindBy(id = "btnQuoteAndView")
	WebElement btnQuote;*/
	
	@FindBy(xpath = "//input[@value='Quote & View']")
	WebElement btnQuote;

	@FindBy(name = "accountHoldingCustomer")
	WebElement chkConfirm;

	@FindBy(xpath = "//input[@value='Show Currency']")
	WebElement btnShowCurrency;

	@FindBy(xpath = "//input[@value='Clear Fields']")
	WebElement btnClearFields;

	@FindBy(xpath = "//input[@value='Add To Order']")
	WebElement btnAddToOrder;

	@FindBy(xpath = "//input[@value='Confirm Order' or @value='Confirmer La Commande']")
	WebElement btnConfirm;

	@FindBy(xpath = "//*[contains(text(),'En ligne') or contains(text(),'Online')]")
	WebElement lnkOnline;

	@FindBy(xpath = "//*[contains(text(),'Onsite') or contains(text(),'En stock')]")
	WebElement lnkOnsite;

	@FindBy(xpath = "//*[contains(text(),'Wholesale') or contains(text(),'En gros')]")
	WebElement lnkWholeSale;

	@FindBy(xpath = "//tr[2]/td/input[@name='customerTypeId']")
	WebElement accountHolderRdoBtn;

	@FindBy(xpath = "//tr[3]/td/input[@name='customerTypeId']")
	WebElement nonAccountHolderRdoBtn;

	@FindBy(name = "name")
	WebElement txtBeneficiary;

	@FindBy(name = "address1")
	WebElement txtAddress1;

	@FindBy(name = "address2")
	WebElement txtAddress2;

	@FindBy(name = "city")
	WebElement txtCity;

	@FindBy(name = "state")
	WebElement txtState;

	@FindBy(name = "postalCode")
	WebElement txtZipCode;

	@FindBy(name = "countryCode")
	WebElement lstCountry;

	@FindBy(name = "description")
	WebElement txtComments;

	@FindBy(name = "Close")
	WebElement btnClose;

	@FindBy(xpath = "//span[contains(text(),'rate is:')]")
	WebElement todaysRate;

	@FindBy(id = "waiveFeeCheck")
	WebElement rbWaiveFeeCheck;
	
	@FindBy(css = ".shoppingCartDomesticAmount>b")
	WebElement lblwaiverFee;

	@FindBy(xpath = "//input[@value='Delete Order']")
	WebElement btndeleteOrder;

	@FindBy(xpath = "//a[contains(text(),'Search Order')]")
	WebElement searchOrderLink;

	@FindBy(id = "mainpage")
	WebElement mainPage;

	@FindBy(id = "denomQuantityTxt0")
	WebElement txtDenomQty;

	@FindBy(id = "changeOrderButton")
	WebElement btnchangeOrder;

	@FindBy(name = "edit")
	WebElement editButtonImg;

	@FindBy(xpath = "//a[contains(text(),'Change Branch')]")
	WebElement lnkChangeBranch;


	@FindBy(name = "searchTerm")
	WebElement txtBranchSearch;


	@FindBy(name = "branchId")
	WebElement lstBranchID;
	
	@FindBy(xpath = "count(//*[@class='shoppingCartDescription'])")
	WebElement currCount;
	
	@FindBy(xpath = "//td[contains(text(),'Foreign Currency Service Charge')]/following-sibling::td[6]")
	WebElement serviceCharges;
	
	@FindBy(xpath = "//td[contains(text(),'Account Holder Fee')]/following-sibling::td[6]")
	WebElement accFee;
	
	@FindBy(xpath = "//*[(text()='Account Holder Fee')]/../following-sibling::td[6]/b")
	WebElement accFee2;
	
	@FindBy(xpath = "//td[contains(text(),'Priority Overnight')]/following-sibling::td[6]")
	WebElement deleiveryCharges;
	
	@FindBy(xpath = "//td[contains(text(),'Next Day Delivery')]/following-sibling::td[6]")
	WebElement deleiveryCharge2;
	
	@FindBy(id = "spanOrderTotal")
	WebElement orderTotal;
	
	@FindBy(xpath = "//a[contains(text(),'Rate Sheet')]")
	WebElement rateSheet;
	
	@FindBy(xpath = "//a[contains(text(),'View Rate Sheet')]")
	WebElement viewRateSheet;
		
	@FindBy(xpath = "//a[contains(text(),'Daily Branch Activity')]")
	WebElement dailyBranch;
	
	@FindBy(xpath = "//input[@value='Export To Excel']")
	WebElement exportExcelBtn;
	
	@FindBy(xpath = "//td[contains(text(),'No search results found')]")
	WebElement exportExcellocator;
	
	
	@FindBy(id = "reasons")
	WebElement lstReason;

	@FindBy(id = "paymentMethod")
	WebElement lstPaymentMethod;
	
	@FindBy(xpath = "//*[@id='quoteMessage']//span[contains(text(),'rate is:')]/../../following-sibling::tr[2]/td/span")
	WebElement lblUpAmt;
	
	@FindBy(xpath = "//*[@id='quoteMessage']//span[contains(text(),'rate is:')]/../../following-sibling::tr[4]/td/span")
	WebElement lblUpAmt2;

	@FindBy(id = "quoteChoice")
	WebElement rdbtnUpAmt;
	
	@FindBy(xpath = "//td[contains(text(),'Total limit for the order must')]")
	WebElement amtErrMsg;
	
	//PPC
	@FindBy(id = "primaryTitle")
	WebElement lstTitle;
	
	@FindBy(id = "primaryFirstName")
	WebElement lblFirstNamePPC;
	
	@FindBy(id = "primaryLastName")
	WebElement lblLastNamePPC;
	
	@FindBy(id = "primaryAddress1")
	WebElement lblAddress1PPC;
	
	@FindBy(id = "primaryAddress2")
	WebElement lblAddress2PPC;
	
	@FindBy(id = "primaryCity")
	WebElement lblCityPPC;
	
	@FindBy(id = "primaryState")
	WebElement lstStatePPC;
	
	@FindBy(id = "primaryZip")
	WebElement lblZipcodePPC;
	
	@FindBy(name = "primaryCountry")
	WebElement lblCountryPPC;
	
	@FindBy(id = "primaryDayPhoneArea")
	WebElement lblPhone1PPC;
	
	@FindBy(id = "primaryDayPhone")
	WebElement lblPhone2PPC;
	
	@FindBy(id = "primaryEmail")
	WebElement lblEmailPPC;
	
	@FindBy(id = "primaryMaidenName")
	WebElement lblmaidenNamePPC;
	
	@FindBy(id = "primarySecurityIdentity")
	WebElement lblSsnPPC;
	
	@FindBy(id = "primaryDateOfBirth_MM")
	WebElement lblMonthPPC;
	
	@FindBy(id = "primaryDateOfBirth_DD")
	WebElement lblDayPPC;
	
	@FindBy(id = "primaryDateOfBirth_YYYY")
	WebElement lblYearPPC;
	
	@FindBy(id = "btnSubmitAddToOrder2")
	WebElement btnSubmitPPC;
	
	@FindBy(id = "secondaryCardSelected")
	WebElement chkSecCardPPC;
	
	
	
	public TransactionAndCurrencyPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@SuppressWarnings("unused")
	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(lblTransactionPage, timeOutPeriod);
		if(lblTransactionPage.isDisplayed()){
			isPageLoaded = true;
		}
	}
	
	@Override
	public void load(){
		try {
			wrapper.waitForElementToBeDisplayed(lblTransactionPage, timeOutPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void selectBranch(String branchLocation) {
		Select branchLocationList = new Select(lstBranchLocation);
		branchLocationList.selectByVisibleText(branchLocation);			
	}
	
	public void selectTransactionTypeDetails(String TransactionType, String CustomerType) throws InterruptedException{
		switch (TransactionType.toUpperCase()) {
			case "PURCHASE":
				rbPurchase.click();
				wrapper.waitForLoaderInvisibility(waitTime);
				break;

			case "SALE":
				rbSale.click();
				wrapper.waitForLoaderInvisibility(waitTime);
				break;

			default:
				break;
		}			
		
		switch (CustomerType.toUpperCase()) {
			case "ACCOUNTHOLDER":
				accountHolderRdoBtn.click();
				wrapper.waitForLoaderInvisibility(waitTime);
				break;

			case "NONACCOUNTHOLDER":
				nonAccountHolderRdoBtn.click();
				wrapper.waitForLoaderInvisibility(waitTime);
				break;
			case "NA":				
				MasterDataReader.scenario.write("CustomerType selection is not required");
				break;	

			default:
				break;
			}
		
		
		/*if(WebDriverWrapper.isConfigTrue(ConfigAccountHoldingCheckBox)){
			checkBoxClick(browserName,ConfigAccountHoldingCheckBox);
		}*/
			
	}
			
	public void selectProductAndCurrency(String currency, String ProductType){
		if(!ProductType.equalsIgnoreCase("NA")){
			Select productDetailsList = new Select(lstProductDetails);
			productDetailsList.selectByVisibleText(ProductType);				
		}

		if(!currency.equalsIgnoreCase("NA")){
			Select currencyList = new Select(lstCurrency);
			currencyList.selectByVisibleText(currency);
		}
	}
		
	public void clickingOrNotClickingOnQuoteAndViewBeforeAndAfterEnteringAmount(String clickStr,String beforeAfter,String foreignAmount) throws InterruptedException{
		
		if(beforeAfter.equalsIgnoreCase("after") && clickStr.equalsIgnoreCase("do not click")){
			if(beforeAfter.equalsIgnoreCase("after")){
				txtForeignAmount.clear();
				txtForeignAmount.sendKeys(foreignAmount);
			}	
		}else if(beforeAfter.equalsIgnoreCase("before") && clickStr.equalsIgnoreCase("click")){
			if(clickStr.equalsIgnoreCase("click")){
				clickQuoteAndViewButton();
			}
			boolean alertExist = wrapper.isAlertPresent();
			if(alertExist){
				driver.switchTo().alert();
				String actualErrorMessgae = driver.switchTo().alert().getText();
				driver.switchTo().alert().accept();
				String expectedErrorMessage = "Please enter an amount.";
				Assert.assertEquals(actualErrorMessgae, expectedErrorMessage);
			}else{
				Assert.fail("Expected Error Message Not Exist In The System ::: Please Enter Amount. ");
			}		
		}else if(beforeAfter.equalsIgnoreCase("after") && clickStr.equalsIgnoreCase("click")){
			if(beforeAfter.equalsIgnoreCase("after")){
				txtForeignAmount.clear();
				txtForeignAmount.sendKeys(foreignAmount);
			}
			if(clickStr.equalsIgnoreCase("click")){
				clickQuoteAndViewButton();
			}			
		}
	}
	
	public void clearAndEnterForeignAmount(String foreignAmount){
		txtForeignAmount.clear();
		txtForeignAmount.sendKeys(foreignAmount);
	}
	
	public void addtoOrderAndViewCurrencyWindow(String configCurrencyView,String configErrMsg) {
		
		if(WebDriverWrapper.isConfigTrue(configErrMsg)){
			String expMsg = amtErrMsg.getText();

			String[] arrMsg = expMsg.split("This");
			
			String strSub = arrMsg[0].substring(42);
			String[] strSplit = strSub.split("and");
			
			String[] arrStr = strSplit[0].split("\\$");
			
			String[] arrStr2 = strSplit[1].split("\\$");
			String newStr = null;
			if(arrStr2[1].endsWith(". ")){
				newStr = arrStr2[1].replaceFirst("00. ", "00");
			}
			
			DecimalFormat df = new DecimalFormat("#");
			String one = df.format(Double.parseDouble((arrStr[1].trim())));
			
			DecimalFormat df2 = new DecimalFormat("##,###");
			DecimalFormat df3 = new DecimalFormat("#,###");
			
			String two = null;
			if(Double.parseDouble(newStr)<=1000.00){
				two = df3.format(Double.parseDouble((newStr.trim())));
			}else{
				two = df2.format(Double.parseDouble((newStr.trim())));
			}
			
			String finalErrMsg = "The order values must be between $ "+one+" and $ "+two+"";
	
			MasterDataReader.orderDetails.put("ErrMsg", finalErrMsg);			
		}
		
		wrapper.waitForElementToBeClickable(btnAddToOrder, 3000000);
		btnAddToOrder.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		if(WebDriverWrapper.isConfigTrue(configCurrencyView)){
			clickShowCurrencyButton();
		}
	}
	
	public void checkwaiveFeeCheck(String WaiveReason){
		boolean value = false;
		value = rbWaiveFeeCheck.isSelected();
		if(value != true){
			rbWaiveFeeCheck.click();
			wrapper.waitForLoaderInvisibility(waitTime);
				Assert.assertEquals(lblwaiverFee.getText().trim(), "0.00");
				MasterDataReader.scenario.write("<B><font size='3 color='Black'>Assert passed.</font></B> Actual Output :"+lblwaiverFee.getText().trim()+System.lineSeparator()+"Expected Output : "+"0.00");
				selectReasonForwaiver(WaiveReason);
		}			
	}
		
	public void uncheckwaiveFeeCheck(){		
		boolean value = false;
		value = rbWaiveFeeCheck.isSelected();
		if(value){
			rbWaiveFeeCheck.click();
		}
	
}	
		
	public CustomerDetailsPage selectPaymentMethodAndCinfirmOrder(String paymentMethod){
		
		if(!paymentMethod.equalsIgnoreCase("NA")){
			selectPaymentMethod(paymentMethod);
		}				
		CustomerDetailsPage customerDetailsPage = confirmOrderBtn();
		
		return customerDetailsPage;	
	}
			
	public void checkForeignAmount(String foreignAmount){
		String newAmount = txtForeignAmount.getText();
		Assert.assertEquals(newAmount, foreignAmount);
	}

	public void deliveryTypeBtn(String configDeliveryType, String deliveryType){
		wrapper.waitForLoaderInvisibility(waitTime);
		if(WebDriverWrapper.isConfigTrue(configDeliveryType)){
		if(deliveryType.equalsIgnoreCase("Next Day Delivery")){
			if(!rbNextDeliveryBtn.isSelected()){
				rbNextDeliveryBtn.click();
			}
		}
		else{
			if(!rbPrirityOvernightBtn.isSelected()){
				rbPrirityOvernightBtn.click();
			}
		}
		wrapper.waitForLoaderInvisibility(waitTime);
		}

	}

	public void acceptAlert(){
		wrapper.acceptAlert(timeOutPeriod);
		wrapper.waitForLoaderInvisibility(waitTime);
	}

	//Code modified for IE :RD
	public void addOrderBtnClick(String addToOrderconfig,String denomMsg,String configDenomAlert,String denomQty,String warningMsg) throws InterruptedException{
		if(WebDriverWrapper.isConfigTrue(addToOrderconfig)){
		wrapper.waitForElementToBeClickable(btnAddToOrder, 3000000);
		btnAddToOrder.click();
		//wrapper.waitForLoaderInvisibility(waitTime);
		Thread.sleep(3000);
		}
		
		if(!(denomMsg.equalsIgnoreCase("NA"))){
			Assert.assertEquals(accountHolderMessagePoPupValidation(), denomMsg);
			if(WebDriverWrapper.isConfigTrue(configDenomAlert)){
				acceptAlert();
				fillDenomQuantity(denomQty,warningMsg);
			}else{
				dismissAlert();
			}
		}
	}
	
	public void addOrderBtnClickforMulti(String addToOrderconfig,String configConfirmChkBox) throws InterruptedException{

		if(WebDriverWrapper.isConfigTrue(addToOrderconfig)){
			wrapper.waitForElementToBeClickable(btnAddToOrder, 3000000);
			btnAddToOrder.click();
			//wrapper.waitForLoaderInvisibility(waitTime);
			Thread.sleep(3000);
			
				acceptAlert();
				checkBoxClick(browserName,configConfirmChkBox);
			
		}
	}
	
	public void addOrderBtnClickQuoteView(String addToOrderconfig,String denomMsg,String configDenomAlert,
			String denomQty,String accMsg, String confirmChkBox,String warningMsg) throws InterruptedException{
				
		if(WebDriverWrapper.isConfigTrue(addToOrderconfig)){
		wrapper.waitForElementToBeClickable(btnAddToOrder, 3000000);
		btnAddToOrder.click();
		//wrapper.waitForLoaderInvisibility(waitTime);
		Thread.sleep(3000);
		}
		
		if(!(accMsg.equalsIgnoreCase("NA"))){			
			String actualMsg = accountHolderMessagePoPupValidation();
			Assert.assertEquals(accountHolderMessagePoPupValidation(), accMsg);
			MasterDataReader.scenario.write("<B><font size='3 color='Black'>Assert passed.</font></B> Actual Output :"+actualMsg+System.lineSeparator()+"Expected Output : "+accMsg);
			acceptAlert();
		}
		
		checkBoxClick(browserName,confirmChkBox);		
		if(WebDriverWrapper.isConfigTrue(addToOrderconfig)){
			btnAddToOrder.click();
			Thread.sleep(3000);
		}
		
		if(!(denomMsg.equalsIgnoreCase("NA"))){
			String actMsg = denominationMessagePoPupValidation();
			Assert.assertEquals(denominationMessagePoPupValidation(), denomMsg);
			MasterDataReader.scenario.write("<B><font size='3 color='Black'>Assert passed.</font></B> Actual Output :"+actMsg+System.lineSeparator()+"Expected Output : "+denomMsg);
			if(WebDriverWrapper.isConfigTrue(configDenomAlert)){
				acceptAlert();
				fillDenomQuantity(denomQty,warningMsg);
			}else{
				dismissAlert();
			}
		}
	}

	public CustomerDetailsPage confirmOrderBtn(){
		btnConfirm.click();
		wrapper.waitForLoaderInvisibility(waitTime);		
		return new CustomerDetailsPage(driver).get();
	}

	public void buttonClick(String configConfirmBtn) {
		if(WebDriverWrapper.isConfigTrue(configConfirmBtn)){
		btnConfirm.click();
		wrapper.waitForLoaderInvisibility(waitTime);
		}
		
	}

	public String accountHolderMessagePoPupValidation(){
		String actualMessage = "";
		actualMessage = driver.switchTo().alert().getText();
		return actualMessage;
	}

	public String denominationMessagePoPupValidation(){
		String actualMessage = "";
		actualMessage = driver.switchTo().alert().getText();
		return actualMessage;
	}
	
	public String amountHolderMessage(){
		String amountMessage = "";
		amountMessage = driver.switchTo().alert().getText();
		return amountMessage;

	}

	public void checkBoxClick(String browser,String configConfirmChkBox) throws InterruptedException{
		if(WebDriverWrapper.isConfigTrue(configConfirmChkBox)){
			if(browser.equalsIgnoreCase("IE")){
				chkConfirm.click();
				mainPage.click();
				Thread.sleep(2000);
				wrapper.waitForLoaderInvisibility(waitTime);
			}else{
				chkConfirm.click();
				wrapper.waitForLoaderInvisibility(waitTime);
			}
		}
	}

	//   Code modified for IE
	public void dismissAlert() throws InterruptedException{
		wrapper.dismissAlert(timeOutPeriod);
		wrapper.waitForElementToBeClickable(btnConfirm, timeOutPeriod);
	}

	public void nextButtonClick() {
		btnNext.click();
		wrapper.waitForLoaderInvisibility(waitTime);
	}
	
	public void switchToWindow(String windowTitle,WebDriver driver) {
		try{
			Set<String> windows = driver.getWindowHandles();

			for (String window : windows) {
				driver.switchTo().window(window);
				driver.manage().window().maximize();

				driver.getTitle().contains(windowTitle);
			}
		}

		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void closeWindow(String windowTitle,WebDriver driver) {
		try {
			Thread.sleep(1000);
			driver.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnRetrive(){
		btnRetrieve.click();
		wrapper.waitForLoaderInvisibility(waitTime);
	}

	public void clickQuoteAndViewButton() throws InterruptedException{
		btnQuote.click();
		Thread.sleep(3000);
		//wrapper.waitForLoaderInvisibility(waitTime); Code modified for IE
	}

	public void enterBeneficiaryDetails(String beneficiary,String add,String add1,String city,String state,String zipcode,String country,String comment){
		if(!(beneficiary.equalsIgnoreCase("NA"))){
		txtBeneficiary.sendKeys(beneficiary);
		txtAddress1.sendKeys(add);
		txtAddress2.sendKeys(add1);
		txtCity.sendKeys(city);
		txtState.sendKeys(state);
		txtZipCode.sendKeys(zipcode);
		Select countryList = new Select(lstCountry);
		countryList.selectByVisibleText(country);
		txtComments.sendKeys(comment);
		}
	}

	public String switchtoPopUpAndGetMessage(String errorMessage){
		String popupMessage = "";
		if(!errorMessage.equalsIgnoreCase("NA")){
			popupMessage =driver.switchTo().alert().getText();
		}
		MasterDataReader.scenario.write("<B><font size='3 color='Black'>Assert passed.</font></B> Actual Output :"+popupMessage+System.lineSeparator()+"Expected Output : "+errorMessage);
		return popupMessage;
	}

	public void clickShowCurrencyButton(){
		String actualalert = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();	
		String expectedalert = "Viewing Currency is required";
		if(actualalert.contains(expectedalert)){
			btnShowCurrency.click();
			wrapper.waitForLoaderInvisibility(waitTime);
			switchTolatestWindow();
			boolean actualMsg = validateCurrencywindowVisibility();
			Assert.assertEquals(validateCurrencywindowVisibility(), true);
			MasterDataReader.scenario.write("<B><font size='3 color='Black'>Assert passed.</font></B> Actual Output :"+actualMsg+System.lineSeparator()+"Expected Output : "+true);
			closeCurrencyWindowAndSwitchBackMainWindow();
			btnAddToOrder.click();			
		}else{
			Assert.fail("Not Visible Currency Alert");
		}

	}

	public void switchTolatestWindow(){
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}			
	}

	public boolean validateCurrencywindowVisibility(){
		boolean displayed = false;
		displayed = btnClose.isDisplayed();		
		return displayed;
	}

	public void closeCurrencyWindowAndSwitchBackMainWindow(){
		btnClose.click();
		switchTolatestWindow();
		wrapper.waitForLoaderInvisibility(waitTime);
	}

	public void todaysRateIsDisplay(){
		todaysRate.isDisplayed();
	}

	public void selectReasonForwaiver(String waiveReason){
		Select reasonWaiverList = new Select(lstReason);
		reasonWaiverList.selectByVisibleText(waiveReason);
	}

	public void selectPaymentMethod(String paymentmethod){
		Select paymentMethodList = new Select(lstPaymentMethod);
		paymentMethodList.selectByVisibleText(paymentmethod);

	}

	public void clickOnclearFieldsButton(){
		btnClearFields.click();
		wrapper.waitForLoaderInvisibility(waitTime);
	}

	public void clickOnDeleteOrderButton(){
		btndeleteOrder.click();
		wrapper.waitForLoaderInvisibility(waitTime);
	}

	public void denomSelection(String quantity, String denomination) throws InterruptedException{				
		String[] denominationlist = denomination.split(",");
		String[] quantitylist = quantity.split(",");		
		int denomsCount = denominationlist.length;

		for (int j=0;j<=denomsCount-1;j++) {
			String quantityValue = quantitylist[j];
			String denominationValue = denominationlist[j];
			driver.findElement(By.xpath("//div[normalize-space(text())='"+ denominationValue +"']/../../td[3]/span/span/input")).sendKeys(quantityValue);
		}
		
		btnAddToOrder.click();
		Thread.sleep(1000);
		
	}

	public SearchOrderPage clickSearchOrderLink() throws WebDriverException{
		searchOrderLink.click();
		wrapper.waitForLoaderInvisibility(waitTime);
		return new SearchOrderPage(driver).get();

	}

	public void clearButtonClick(String configClearButton){
		if(WebDriverWrapper.isConfigTrue(configClearButton)){
			btnClearFields.click();
			wrapper.waitForLoaderInvisibility(waitTime);
		}
	}

	public void deleteButtonClick(String configDeleteButton){
		if(WebDriverWrapper.isConfigTrue(configDeleteButton)){
			btndeleteOrder.click();
			wrapper.waitForLoaderInvisibility(waitTime);
		}
	}
		
	@SuppressWarnings("unused")
	public ArrayList<HashMap<String,String>> getCurrencies(){
			
		List<WebElement> rowCount = driver.findElements(By.xpath("//*[@id='frmShopppingCardId']/table[2]/tbody//*[@class='shoppingCartNumber']"));
		List<WebElement> colValues = driver.findElements(By.xpath("//*[@id='frmShopppingCardId']//*[@class='tableheader']/td"));
		
		ArrayList<HashMap<String,String>> ordDetails = new ArrayList<HashMap<String,String>>();
		List<WebElement> rowValues;
		/*for(int i=1;i<=rowCount.size()-1;i++){
			rowValues = driver.findElements(By.xpath("//*[@id='frmShopppingCardId']/table[2]/tbody//*[@class='shoppingCartBackground"+i+"']/td"));
				for(int j=0;j<rowValues.size();j++){
					ordDetails.put(colValues.get(j).getText(), rowValues.get(j).getText());
				}
		}*/
		noOfrows = rowCount.size();
		for(int i=0;i<rowCount.size()-1;i++){
			HashMap<String,String> ordDet = new HashMap<String,String>();
			int row=1;
			row = row+i;
			String Currdesc = "CurrDesc"+i;
			String Units = "CurrDesc"+i;
			String keyCurrency= "currency"+i;
			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+row+"]/td[3]"));
			String currDesc = ele.getText();

			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+row+"]/td[5]"));
			String units = ele.getText();

			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+row+"]/td[7]"));
			String rate = ele.getText();

			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+row+"]/td[9]"));
			String USDValue = ele.getText();

			ordDet.put("Desc", currDesc);
			ordDet.put("Units", units);
			ordDet.put("Rate", rate);
			ordDet.put(keyCurrency, USDValue);
			ordDetails.add(ordDet);
		}
		
		String foreignSerCharge = serviceCharges.getText();
		serviceCharge = Double.parseDouble(foreignSerCharge);
		
		String accFees = accFee.getText();
		accountFee = Double.parseDouble(accFees);
		
		boolean isSelected = driver.findElement(By.xpath("(//input[@name='deliveryOptionId'])[1]")).isSelected();
		String delCharge;
		if(isSelected){
			delCharge = deleiveryCharges.getText();
		}else{
			delCharge = deleiveryCharge2.getText();
		}
		
		deleiveryCharge = Double.parseDouble(delCharge);
		
		
		String ordTot = orderTotal.getText();
		double ordTotal = Double.parseDouble(ordTot);
		
		double calculatedTotal = 0.0;
		for(int j=0;j<rowCount.size()-1;j++){
			double tot = Double.parseDouble(ordDetails.get(j).get("currency"+j+""));
			calculatedTotal = calculatedTotal+tot;
		}		
		
		transactionTotal = calculatedTotal+serviceCharge+accountFee+deleiveryCharge;
		
		if(ordTotal==transactionTotal){
			System.out.println("Transaction total validation is successful");
		}
		return ordDetails;
		
	}

	public void fillDenomQuantity(String denomQuantity,String warningMsg) throws InterruptedException{
		txtDenomQty.sendKeys(denomQuantity);
		wrapper.waitForElementToBeClickable(btnAddToOrder, 3000000);
		boolean isExists = driver.findElement(By.id("quoteChoice")).isDisplayed();
		String accWarningMsg = null;
		String arrMsg = null;
		if(isExists){
			String warning = lblUpAmt2.getText();
			String[] arrWarn = warning.split("=");
			arrMsg = arrWarn[0].trim();
			accWarningMsg = "The total of your requested denominations does not equal the originally-entered Foreign Amount of "+arrMsg+".  Please click Ok to continue and add the new amount to your order.  Click Cancel to modify the denomination request.";
		}else{
			String warning = lblUpAmt.getText();
			String[] arrWarn = warning.split("=");
			arrMsg = arrWarn[0].trim();
			accWarningMsg = "The total of your requested denominations does not equal the originally-entered Foreign Amount of "+arrMsg+".  Please click Ok to continue and add the new amount to your order.  Click Cancel to modify the denomination request.";
		}
		btnAddToOrder.click();
		Thread.sleep(3000);
		if(!warningMsg.equalsIgnoreCase("NA")){
			 String actualMsg = denominationMessagePoPupValidation();
			 Assert.assertEquals(actualMsg, accWarningMsg);
			 MasterDataReader.scenario.write("<B><font size='3 color='Black'>Assert passed.</font></B> Actual Output :"+actualMsg+System.lineSeparator()+"Expected Output : "+accWarningMsg);
		}
	}

	public void changeOrderButtonClick(){
		btnchangeOrder.click();
		wrapper.waitForLoaderInvisibility(waitTime);

	}

	public void editButtonClick(String configEditButton,String foreignAmount,
			String addToOrderconfig,String denomMsg,String configDenomAlert,String warningMsg,String denomQty,String specifyDenom) throws InterruptedException{
		if(WebDriverWrapper.isConfigTrue(configEditButton)){
			editButtonImg.click();
			wrapper.waitForLoaderInvisibility(waitTime);
			txtForeignAmount.clear();
			String amount = "100";
			txtForeignAmount.sendKeys(amount);
			
			if(WebDriverWrapper.isConfigTrue(addToOrderconfig)){
				wrapper.waitForElementToBeClickable(btnAddToOrder, 3000000);
				btnAddToOrder.click();
				//wrapper.waitForLoaderInvisibility(waitTime);
				Thread.sleep(3000);
			}
			
			if(!(denomMsg.equalsIgnoreCase("NA"))){
				String actMsg = denominationMessagePoPupValidation();
				Assert.assertEquals(denominationMessagePoPupValidation(), denomMsg);
				MasterDataReader.scenario.write("<B><font size='3 color='Black'>Assert passed.</font></B> Actual Output :"+actMsg+System.lineSeparator()+"Expected Output : "+denomMsg);
				if(WebDriverWrapper.isConfigTrue(configDenomAlert)){
					acceptAlert();
					fillDenomQuantity(denomQty,warningMsg);
				}else{
					dismissAlert();
				}
			}
			
		}
		
		specifyDenom(specifyDenom, addToOrderconfig);
	
	}
	
	
	public void editOrderButtonClick() throws InterruptedException{

		editButtonImg.click();
		wrapper.waitForLoaderInvisibility(waitTime);
		/*txtForeignAmount.clear();
		String amount = "100";
		txtForeignAmount.sendKeys(amount);
		wrapper.waitForElementToBeClickable(btnAddToOrder, 3000000);
		btnAddToOrder.click();
		//wrapper.waitForLoaderInvisibility(waitTime);
		Thread.sleep(3000);*/
			}	
	
	public void specifyDenom(String specifyDenom, String addToOrderconfig) throws InterruptedException{
		if(WebDriverWrapper.isConfigTrue(specifyDenom)){
			editButtonImg.click();
			wrapper.waitForLoaderInvisibility(waitTime);
			if(WebDriverWrapper.isConfigTrue(addToOrderconfig)){
				wrapper.waitForElementToBeClickable(btnAddToOrder, 3000000);
				btnAddToOrder.click();
				//wrapper.waitForLoaderInvisibility(waitTime);
				Thread.sleep(3000);
			}
		}
	}

	public void changeBranchLink(){
		lnkChangeBranch.click();
		wrapper.waitForLoaderInvisibility(waitTime);
	}

	public void rateSheetClick(){
		rateSheet.click();
		wrapper.waitForLoaderInvisibility(waitTime);
	}
	
	public void viewRateSheetClick(){
		viewRateSheet.click();
		wrapper.waitForLoaderInvisibility(waitTime);
	}
	
	public void dailyBranchClick(){
		dailyBranch.click();
		wrapper.waitForLoaderInvisibility(waitTime);
	}
	
	public void exportExcelClick(){
		boolean isExists = driver.findElement(By.xpath("//input[@value='Export To Excel']")).isDisplayed();
		if(isExists){
			deleteFile();
		exportExcelBtn.click();
		wrapper.waitForLoaderInvisibility(waitTime);
		}else{
			System.out.println("No transactions available");
		}
	}

	public boolean isFileDownloaded(String downloadpath, String fileName){
		boolean flag = false;		
		File dir = new File(downloadpath);
		File[] dirContents = dir.listFiles();
		for(int i=0;i<dirContents.length;i++){
			if(dirContents[i].getName().equals(fileName)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public void fileStatusMsg(String downloadPath,String fileName){
		boolean status = isFileDownloaded(downloadPath, fileName);
		if(status){
			System.out.println("File is successfully downloaded");
		}else{
			System.out.println("File not found");
			Assert.assertEquals(status, true);
		}
	}
	
	public void deleteFile(){
    	String homeDir = System.getProperty("user.home");
        String downloadpath = homeDir+"\\Downloads";
    	String filename = "Order History Report.XLS";
		File dir = new File(downloadpath);
		File[] fileList = dir.listFiles();
		
		for(int j=0;j<fileList.length;j++){
			if(fileList[j].getName().equals(filename)){
				fileList[j].delete();
				break;
			}
		}
	}
	
	
	public boolean isAlertPresent() {
		boolean isAlertPresent = false;
		try {
			driver.switchTo().alert();
			isAlertPresent = true;
		} catch (Exception e) {
			isAlertPresent = false;
		}
		return isAlertPresent;
	}
	
	
	public String acceptAlerts() {
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		alert.accept();
		return alertMessage;
	}
	
	public void currencyLoop(String currency,String foreignAmount,String beneficiary,String add,String add1,String city,String state,String zipcode,String country,String comment,
			String addToOrderconfig,String denomMsg,String configDenomAlert,String denomQty,String warningMsg,String configConfirmChkBox) throws InterruptedException{
				
		String[] curr = currency.split("\\|");
		String[] amt = foreignAmount.split("\\|");
		
		for(int i=0;i<curr.length;i++){
			
		Select currencyList = new Select(lstCurrency);
		currencyList.selectByVisibleText(curr[i].trim());

		if(!(foreignAmount.equalsIgnoreCase("NA"))){
			txtForeignAmount.clear();
			txtForeignAmount.sendKeys(amt[i].trim());
			if(wrapper.isAlertPresent()){
				wrapper.acceptAlert(timeOutPeriod);
				txtForeignAmount.clear();
				txtForeignAmount.sendKeys(amt[i].trim());
			}
		}
		
		enterBeneficiaryDetails(beneficiary, add, add1, city, state, zipcode, country, comment);
		//not required
		//addOrderBtnClickforMulti(addToOrderconfig, denomMsg, configDenomAlert, denomQty, warningMsg,configChkBox);
		//not required
		
		
		boolean isChecked = chkConfirm.isSelected();

		if(!isChecked){
			addOrderBtnClickforMulti(addToOrderconfig, configConfirmChkBox);
			
			if(!(foreignAmount.equalsIgnoreCase("NA"))){
				txtForeignAmount.clear();
				txtForeignAmount.sendKeys(amt[i].trim());
				if(wrapper.isAlertPresent()){
					wrapper.acceptAlert(timeOutPeriod);
					txtForeignAmount.clear();
					txtForeignAmount.sendKeys(amt[i].trim());
				}
			}
			
			if(curr[0].contains("DRAFT")){
				enterBeneficiaryDetails(beneficiary, add, add1, city, state, zipcode, country, comment);
			}
			
			btnAddToOrder.click();
			//wrapper.waitForLoaderInvisibility(waitTime);
			Thread.sleep(3000);
			
		}else{
			btnAddToOrder.click();
			//wrapper.waitForLoaderInvisibility(waitTime);
			Thread.sleep(3000);
		}
		
		if(!(denomMsg.equalsIgnoreCase("NA"))){
			Assert.assertEquals(accountHolderMessagePoPupValidation(), denomMsg);
			if(WebDriverWrapper.isConfigTrue(configDenomAlert)){
				acceptAlert();
				fillDenomQuantity(denomQty,warningMsg);
			}else{
				dismissAlert();
			}
		}
		}
	}
	
	public void updateCheckDetails(String countryOfIssue,String checkDate,String issuerEndorsed,String payeeEndorsed){
		
		if(!countryOfIssue.equalsIgnoreCase("NA")){
			Select currencyList = new Select(lstCountryOfIssue);
			currencyList.selectByVisibleText(countryOfIssue);
		}
		
		if(!checkDate.equalsIgnoreCase("NA")){
			txtCheckData.clear();
			txtCheckData.sendKeys(checkDate);
		}
		
		if(issuerEndorsed.equalsIgnoreCase("Yes")){
			boolean value = rbnIssuerEndorsed.isSelected();
			if(!value){
				rbnIssuerEndorsed.click();
			}			
		}else if(issuerEndorsed.equalsIgnoreCase("No")){
			boolean value = rbnIssuerEndorsed.isSelected();
			if(value){
				rbnIssuerEndorsed.click();
			}
			
		}
		
		if(payeeEndorsed.equalsIgnoreCase("Yes")){
			boolean value = rbnPayeeEndorsed.isSelected();
			if(!value){
				rbnPayeeEndorsed.click();
			}
		}else if(payeeEndorsed.equalsIgnoreCase("No")){
			boolean value = rbnPayeeEndorsed.isSelected();
			if(value){
				rbnPayeeEndorsed.click();
			}
		}
	
	}
	
	
	public ArrayList<HashMap<String,String>> validateOrdDetails(String currency,String partner, String TransType){

		ArrayList<HashMap<String,String>> ordDetails = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> hashDetails = new HashMap<String,String>();
		WebElement ele = null;
		int row = 1;

		ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+row+"]/td[3]"));
		String currDesc = ele.getText();

		ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+row+"]/td[5]"));
		String units = ele.getText();

		ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+row+"]/td[7]"));
		String rate = ele.getText();

		ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+row+"]/td[9]"));
		String USDValue = ele.getText();
		
		String DraftserCharge = null;
		if(currency.contains("DRAFT")){
			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+(row+1)+"]/td[9]"));
			DraftserCharge = ele.getText();
			hashDetails.put("DSerCharge", DraftserCharge);
		}

		hashDetails.put("Desc", currDesc);
		hashDetails.put("Units", units);
		hashDetails.put("rate", rate);
		

		double unit = Double.parseDouble(units);
		double cRate = Double.parseDouble(rate);
		double usdVal = Double.parseDouble(USDValue);

		DecimalFormat df = new DecimalFormat("#.##");
		double CalculatedUSD = Double.parseDouble(df.format(unit*cRate));

			if(CalculatedUSD==usdVal){
				hashDetails.put("USD", USDValue);
				System.out.println("USD validation Succesful");
			}else{
				System.out.println("USD validation failed");
			}

		String FCSerCharge,acFee = null;
		
		boolean isExists = driver.findElements(By.xpath("//td[contains(text(),'Foreign Currency Service Charge')]/following-sibling::td[6]")).size() > 0;
			
			if(isExists){
				FCSerCharge = serviceCharges.getText();
			}else{
				FCSerCharge = "0.00";
			}

		//isExists = driver.findElement(By.xpath("//td[contains(text(),'Account Holder Fee')]/following-sibling::td[6]")).isDisplayed();
		isExists = driver.findElements(By.xpath("//*[(text()='Account Holder Fee')]")).size() > 0;
		
			if(isExists){
				if(partner.equals("29116")){
				acFee = accFee.getText();
				}else{
					acFee = accFee2.getText();
				}
			}else{
				acFee = "0.00";
			}

		String deliveryCharge = null;
			isExists = driver.findElements(By.xpath("//*[@id='frmDeliveryOptionsId']//tr[1]//input")).size() > 0;
		if(isExists){
			if(rbPrirityOvernightBtn.isSelected()){
				deliveryCharge = rbPrirityOvernightFee.getText() ;
			}else if(rbNextDeliveryBtn.isSelected()){
				deliveryCharge = rbNextDeliveryFee.getText();
				deliveryCharge = (deliveryCharge.equalsIgnoreCase("FREE")) ? "0.00" : deliveryCharge;
			}
		}else{
			deliveryCharge = "0.00";
		}
			double CalculatedorderTotal = 0.00;
		
			if(TransType.equalsIgnoreCase("Sale")){
				if(currency.contains("DRAFT")){
					CalculatedorderTotal = CalculatedUSD + Double.parseDouble(FCSerCharge) + Double.parseDouble(DraftserCharge) +Double.parseDouble(acFee) + Double.parseDouble(deliveryCharge);
				}else{
					CalculatedorderTotal = CalculatedUSD + Double.parseDouble(FCSerCharge) + Double.parseDouble(acFee) + Double.parseDouble(deliveryCharge);
				}
			}else if(TransType.equalsIgnoreCase("Purchase")){
				if(currency.contains("DRAFT")){
					CalculatedorderTotal = CalculatedUSD - Double.parseDouble(FCSerCharge) - Double.parseDouble(DraftserCharge) - Double.parseDouble(acFee) - Double.parseDouble(deliveryCharge);
				}else{
					CalculatedorderTotal = CalculatedUSD - Double.parseDouble(FCSerCharge) - Double.parseDouble(acFee) - Double.parseDouble(deliveryCharge);
				}
			}
		
		String ActualTotal = orderTotal.getText();

			if(CalculatedorderTotal == Double.parseDouble(ActualTotal)){
				MasterDataReader.scenario.write("Order total validation is successful. Expected Total: "+CalculatedorderTotal+" Actual Total :"+ActualTotal+"");
			}else{
				MasterDataReader.scenario.write("Order total validation falied. Expected Total: "+CalculatedorderTotal+" Actual Total :"+ActualTotal+"");
			}

		hashDetails.put("OrderTotal", ActualTotal);
		ordDetails.add(hashDetails);

		return ordDetails;

	}
	
	public void verifyClearFieldsPage(){
		boolean isExists = driver.findElements(By.xpath("//*[@id='quoteMessage'][contains(@style,'display: none;')]")).size() > 0;
		
		String domesticAmt = txtDomesticCurrency.getText();
		String ForeignAmt = txtForeignAmount.getText();
		if(domesticAmt.trim().equalsIgnoreCase("") && ForeignAmt.trim().equalsIgnoreCase("") && isExists){
			MasterDataReader.scenario.write("Succesfully verified clear fields button");
		}else{
			MasterDataReader.scenario.write("Verification of clear fields button failed");
		}
	}
	
	public void verifyDeleteOrderPage(){
		boolean isExists = driver.findElements(By.xpath("//input[@value='Confirm Order' or @value='Confirmer La Commande']")).size() > 0;
		
		boolean isExists2 = driver.findElements(By.xpath("//input[@value='Delete Order']")).size() > 0;
		String domesticAmt = txtDomesticCurrency.getText();
		String ForeignAmt = txtForeignAmount.getText();
		if(domesticAmt.trim().equalsIgnoreCase("") && ForeignAmt.trim().equalsIgnoreCase("") && !isExists && !isExists2){
			MasterDataReader.scenario.write("Succesfully verified Delete Order button");
		}else{
			MasterDataReader.scenario.write("Verification of Delete Order button failed");
		}
	}
	
	public void verifyEditOrderPage() throws InterruptedException{
		boolean isExists = driver.findElements(By.xpath("//input[@value='Confirm Order' or @value='Confirmer La Commande']")).size() > 0;
		
		boolean isExists2 = driver.findElements(By.xpath("//input[@value='Delete Order']")).size() > 0;
		
		boolean isExists3 = driver.findElement(By.xpath("//span[contains(text(),'rate is:')]")).isDisplayed();
		
		if(isExists3 && !isExists && !isExists2){
			
			MasterDataReader.scenario.write("Succesfully verified Edit Order button");
			
			/*txtForeignAmount.clear();
			String amount = "100";
			txtForeignAmount.sendKeys(amount);
			Thread.sleep(1000);
			btnAddToOrder.click();
			wrapper.waitForLoaderInvisibility(waitTime);
			dismissAlert();
			wrapper.waitForLoaderInvisibility(waitTime);
			
			isExists = driver.findElements(By.xpath("//input[@value='Confirm Order' or @value='Confirmer La Commande']")).size() > 0;
			isExists2 = driver.findElements(By.xpath("//input[@value='Delete Order']")).size() > 0;
			
			if(isExists && isExists2){			
			MasterDataReader.scenario.write("Succesfully verified Edit Order button");
			}else{
				Assert.fail();
			}*/
		}else{
			MasterDataReader.scenario.write("Verification of Edit Order button failed");
			Assert.fail();
		}
	}
	
	public void verifyChangeOrderPage(String currency){
		
		WebElement ele = null;
		int row = 1;
		String[] curr = currency.split("\\|");
		for(int i=0;i<curr.length;i++){
			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+row+"]/td[3]"));
			String currDesc = ele.getText();

			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+row+"]/td[5]"));
			String units = ele.getText();

			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+row+"]/td[7]"));
			String rate = ele.getText();

			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+row+"]/td[9]"));
			String USDValue = ele.getText();
			
			String ActualTotal = orderTotal.getText();
			
			Assert.assertEquals(currDesc, MasterDataReader.txnDetails.get(i).get("Desc"));
			Assert.assertEquals(units, MasterDataReader.txnDetails.get(i).get("Units"));
			Assert.assertEquals(rate, MasterDataReader.txnDetails.get(i).get("rate"));
			Assert.assertEquals(USDValue, MasterDataReader.txnDetails.get(i).get("USD"));
			Assert.assertEquals(ActualTotal, MasterDataReader.txnDetails.get(i).get("OrderTotal"));
		}
	}
	
	public void verifyAmtAlert(String errorMsg){
		boolean isAlertPresent = wrapper.isAlertPresent();
		String txtAlert = null;
		if(isAlertPresent){
			driver.switchTo().alert();
			txtAlert = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();
			Assert.assertEquals(txtAlert, errorMsg);
			MasterDataReader.scenario.write("Verification is succesful. Actual Message : "+txtAlert+", Expected Message :"+errorMsg+"");
		}else{
			MasterDataReader.scenario.write("Verification Failed. Actual Message : "+txtAlert+", Expected Message :"+errorMsg+"");
			Assert.fail("No alert present");
		}
	}
	
	public boolean selectPaymentMethodAndConfirmOrderForPPC(String paymentMethod){
		boolean isPageLoaded = false;
		if(!paymentMethod.equalsIgnoreCase("NA")){
			selectPaymentMethod(paymentMethod);
		}
		
		parentWindow = driver.getWindowHandle();
		
		btnConfirm.click();
		wrapper.waitForLoaderInvisibility(waitTime);
		
		@SuppressWarnings("unused")
		Set<String> allWindowHandles = driver.getWindowHandles();
		
		for(String ChildWin : driver.getWindowHandles()){
			if(ChildWin.equals(parentWindow)){
				continue;
			}
			driver.switchTo().window(ChildWin);
			isPageLoaded = driver.findElement(By.id("secondaryCardSelected")).isDisplayed();
		}
		
		return isPageLoaded;
	}
	
	public void enterKYCDetails(String ppcDetails){
		if(!ppcDetails.equalsIgnoreCase("NA")){
			String[] allPPCDetails = ppcDetails.split("\\#");
			String title = allPPCDetails[0].trim();
			String fName = allPPCDetails[1].trim();
			String lName = allPPCDetails[2].trim();
			String address = allPPCDetails[3].trim();
			String city = allPPCDetails[4].trim();
			String state = allPPCDetails[5].trim();
			String zipcode = allPPCDetails[6].trim();
			String phone1 = allPPCDetails[7].trim();
			String phone2 = allPPCDetails[8].trim();
			String email = allPPCDetails[9].trim();
			String month = allPPCDetails[10].trim();
			String day = allPPCDetails[11].trim();
			String year = allPPCDetails[12].trim();
			String ssn = allPPCDetails[13].trim();
			String maiden = allPPCDetails[14].trim();
			
			Select titleSel = new Select(lstTitle);
			titleSel.selectByValue(title);
			
			lblFirstNamePPC.sendKeys(fName);
			lblLastNamePPC.sendKeys(lName);
			lblAddress1PPC.sendKeys(address);
			lblCityPPC.sendKeys(city);

			Select stateSel = new Select(lstStatePPC);
			stateSel.selectByValue(state);
			
			lblZipcodePPC.sendKeys(zipcode);
			lblPhone1PPC.sendKeys(phone1);
			lblPhone2PPC.sendKeys(phone2);
			lblEmailPPC.sendKeys(email);
			lblMonthPPC.sendKeys(month);
			lblDayPPC.sendKeys(day);
			lblYearPPC.sendKeys(year);
			lblmaidenNamePPC.sendKeys(maiden);
			lblSsnPPC.sendKeys(ssn);
		}
	}
	
	public void submitKYCDetails(){
		btnSubmitPPC.click();
		wrapper.waitForLoaderInvisibility(waitTime);
		
		boolean isAlertExist = wrapper.isAlertPresent();
		String actualMsg,expMsg = null;
		if(isAlertExist){
			driver.switchTo().alert();
			actualMsg = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();
			expMsg = "Customer Has Passed KYC Check";
			if(!actualMsg.equalsIgnoreCase(expMsg)){
				new TransactionAndCurrencyPage(driver).get();
				MasterDataReader.scenario.write("KYC verification failed.Please try again. Actual Message :"+actualMsg+" Expected Message :"+expMsg+"");
				Assert.fail();
			}else{
				driver.switchTo().window(parentWindow);
				CustomerDetailsPage customerDetailsPage = new CustomerDetailsPage(driver).get();
				MasterDataReader.scenario.write("KYC verification is Successful. Actual Message :"+actualMsg+" Expected Message :"+expMsg+"");
				MasterDataReader.pageDetails.put("CustomerDetailsPage", customerDetailsPage);
			}
		}
	}
}


