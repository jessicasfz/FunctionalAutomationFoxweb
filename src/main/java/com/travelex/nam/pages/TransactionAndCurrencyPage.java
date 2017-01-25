package com.travelex.nam.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.travelex.framework.Dataset.SharedData;
import com.travelex.framework.common.ConfigurationProperties;
import com.travelex.framework.common.WebDriverWrapper;
import com.travelex.framework.utilities.WebdriverWrapper;
import com.travelex.stepDefinitions.MasterDataReader;

public class TransactionAndCurrencyPage extends LoadableComponent<TransactionAndCurrencyPage>{
	public String delimiter ="\\|";
	WebDriver driver;
	WebDriverWrapper wrapper ;
	private int timeOutPeriod = 3000;
	private int waitTime=300;
	public WebElement ele = null;
	public static double transactionTotal,serviceCharge,accountFee,deleiveryCharge;
	public static int noOfrows;
	public static List<SharedData> ordDetails = new ArrayList<SharedData>();
	public static SharedData data = new SharedData();
	public static int counter = 1;
	
	private ConfigurationProperties configurationProperties = new ConfigurationProperties();
	String browserName = configurationProperties.getProperty(ConfigurationProperties.BROWSER_NAME);

	@FindBy(id = "searchTerm")
	WebElement txtBranch;

	@FindBy(id = "btnSearch")
	WebElement btnRetrieve;

	@FindBy(name = "branchId")
	WebElement lstBranchLocation;

	@FindBy(xpath = "//input[@value='  NEXT  ']")
	WebElement btnNext;

	@FindBy(xpath = "//td/a[contains(text(),'Logout')]")
	WebElement lblHomePage;

	@FindBy(xpath = "//td[starts-with(normalize-space(),'Sale to')]/input")
	WebElement rbSale;

	@FindBy(xpath = "//td[starts-with(normalize-space(),'Purchase from')]/input")
	WebElement rbPurchase;

	@FindBy(xpath = "//input[@name='deliveryOptionId'][1]")
	WebElement rbPrirityOvernightBtn;

	@FindBy(xpath = "//*[@id='frmDeliveryOptionsId']//tr[2]//input")
	WebElement rbNextDeliveryBtn;

	@FindBy(name = "productSetId")
	WebElement lstProductDetails;

	@FindBy(name = "productCode")
	WebElement lstCurrency;

	@FindBy(name = "domesticAmount")
	WebElement txtDomesticCurrency;

	@FindBy(name = "foreignAmount")
	WebElement txtForeignAmount;

	@FindBy(id = "btnQuoteAndView")
	WebElement btnQuote;

	@FindBy(name = "accountHoldingCustomer")
	WebElement chkConfirm;

	@FindBy(xpath = "//input[@value='Show Currency']")
	WebElement btnShowCurrency;

	@FindBy(xpath = "//input[@value='Clear Fields']")
	WebElement btnClearFields;

	@FindBy(id = "btnSubmitAddToOrder")
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

	@FindBy(xpath = "//tbody/tr[1]/td[1]/span[@class='med']")
	WebElement todaysRate;

	@FindBy(id = "waiveFeeCheck")
	WebElement rbWaiveFeeCheck;

	@FindBy(xpath = "//input[@value='Delete Order']")
	WebElement btndeleteOrder;

	//RK
	@FindBy(xpath = "//a[contains(text(),'Search Order')]")
	WebElement searchOrderLink;

	//RD
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
	
	//Pravin addition
	
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
	
	
	
	public TransactionAndCurrencyPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@SuppressWarnings("unused")
	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(lblHomePage, timeOutPeriod);
		if(lblHomePage.isDisplayed()){
			isPageLoaded = true;
		}
	}

	@Override
	public void load(){
		try {
			wrapper.waitForElementToBeDisplayed(lblHomePage, timeOutPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	/**
	 * Selection Of Branch location In Transaction Page   
	 *
	 * @param branchLocation
	 * @throws InterruptedException
	 */

	public void selectBranch(String branchLocation) {
		Select branchLocationList = new Select(lstBranchLocation);
		branchLocationList.selectByVisibleText(branchLocation);			
	}

	public void orderTypeSelection(String orderType,String orderConfig){
		if(WebDriverWrapper.isConfigTrue(orderConfig)){
			switch (orderType.toUpperCase()) {
			case "ONLINE":
				lnkOnline.click();
				break;
			case "ONSITE":
				lnkOnsite.click();
				break;
			case "WHOLESALE":
				lnkWholeSale.click();
				break;

			default:
				break;
			}
			wrapper.waitForLoaderInvisibility(waitTime);
		}
	}
	
	public void transactionDetails(String transactionType, String productType, String currency,
			String accountHolderType,String foreignAmount,String configConfirmChkBox) throws Throwable{
		
		if(counter==1){
		switch (transactionType.toUpperCase()) {
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

		if(accountHolderType != "NA"){
			switch (accountHolderType.toUpperCase()) {
			case "ACCOUNTHOLDER":
				accountHolderRdoBtn.click();
				wrapper.waitForLoaderInvisibility(waitTime);
				break;

			case "NONACCOUNTHOLDER":
				nonAccountHolderRdoBtn.click();
				wrapper.waitForLoaderInvisibility(waitTime);
				break;

			default:
				break;
			}
		}

		checkBoxClick(browserName,configConfirmChkBox);
		
		Select productDetailsList = new Select(lstProductDetails);
		productDetailsList.selectByVisibleText(productType);
		}
		
		Select currencyList = new Select(lstCurrency);
		currencyList.selectByVisibleText(currency);

		if(!(foreignAmount.equalsIgnoreCase("NA"))){
			txtForeignAmount.clear();
			txtForeignAmount.sendKeys(foreignAmount);
			if(wrapper.isAlertPresent()){
				wrapper.acceptAlert(timeOutPeriod);
				txtForeignAmount.clear();
				txtForeignAmount.sendKeys(foreignAmount);
			}
		}
		counter++;
		
	}
	
	public void transDetailsQuoteView(String transactionType, String productType, String currency,String accountHolderType,String foreignAmount,String configConfirmChkBox,String amtMsg,String configQuote) throws Throwable{
		
		switch (transactionType.toUpperCase()) {
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

		if(accountHolderType != "NA"){
			switch (accountHolderType.toUpperCase()) {
			case "ACCOUNTHOLDER":
				accountHolderRdoBtn.click();
				wrapper.waitForLoaderInvisibility(waitTime);
				break;

			case "NONACCOUNTHOLDER":
				nonAccountHolderRdoBtn.click();
				wrapper.waitForLoaderInvisibility(waitTime);
				break;

			default:
				break;
			}
		}

		Select productDetailsList = new Select(lstProductDetails);
		productDetailsList.selectByVisibleText(productType);
		Select currencyList = new Select(lstCurrency);
		currencyList.selectByVisibleText(currency);
		
		if(WebDriverWrapper.isConfigTrue(configQuote)){
			quoteAndViewButton();
			Assert.assertEquals(amountHolderMessage(), amtMsg);
			acceptAlert();
		}

		if(!(foreignAmount.equalsIgnoreCase("NA"))){
			txtForeignAmount.clear();
			txtForeignAmount.sendKeys(foreignAmount);
			if(wrapper.isAlertPresent()){
				wrapper.acceptAlert(timeOutPeriod);
				txtForeignAmount.clear();
				txtForeignAmount.sendKeys(foreignAmount);
			}
		}
		quoteAndViewButton();
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

	public void confirmOrderBtn(){
		btnConfirm.click();
		wrapper.waitForLoaderInvisibility(waitTime);
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
	
	public void logoutlink(){
		lblHomePage.click();
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

	public void quoteAndViewButton() throws InterruptedException{
		btnQuote.click();
		Thread.sleep(1000);
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

	public void clickShowCurrencyButton(String configShowCurrency){
		if(WebDriverWrapper.isConfigTrue(configShowCurrency)){
			btnShowCurrency.click();
			wrapper.waitForLoaderInvisibility(waitTime);
			switchTolatestWindow();
			boolean actualMsg = validateCurrencywindowVisibility();
			Assert.assertEquals(validateCurrencywindowVisibility(), true);
			MasterDataReader.scenario.write("<B><font size='3 color='Black'>Assert passed.</font></B> Actual Output :"+actualMsg+System.lineSeparator()+"Expected Output : "+true);
			closeCurrencyWindowAndSwitchBackMainWindow();
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


	public void uncheckwaiveFeeCheck(){
		boolean value = false;
		value = rbWaiveFeeCheck.isSelected();
		if(value){
			rbWaiveFeeCheck.click();
		}	
	}

	/*--------------- Praveen New Code Addition ------------------------*/


	public void checkwaiveFeeCheck(){
		boolean value = false;
		value = rbWaiveFeeCheck.isSelected();
		if(value != true){
			rbWaiveFeeCheck.click();
			wrapper.waitForLoaderInvisibility(waitTime);
		}	
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

	public void denomSelection(String quantity, String denomination){				
		String[] denominationlist = denomination.split(",");
		String[] quantitylist = quantity.split(",");		
		int denomsCount = denominationlist.length;

		for (int j=0;j<=denomsCount-1;j++) {
			String quantityValue = quantitylist[j];
			String denominationValue = denominationlist[j];
			driver.findElement(By.xpath("//div[normalize-space(text())='"+ denominationValue +"']/../../td[3]/span/span/input")).sendKeys(quantityValue);
		}	   
	}

	//RK
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

	//New Addition RD

	public List<SharedData> fetchOrderDetails(String Currency,String configFetchorderDetails){
		
		if(WebDriverWrapper.isConfigTrue(configFetchorderDetails)){
		String[] curr = Currency.split("\\|");
		
		for(int i=1;i<=curr.length;i++){
			
			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+i+"]/td[3]"));
			String currDesc = ele.getText();

			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+i+"]/td[5]"));
			String units = ele.getText();

			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+i+"]/td[7]"));
			String rate = ele.getText();

			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+i+"]/td[9]"));
			String USDValue = ele.getText();
			
			data.setDescription(currDesc);
			data.setUnits(units);
			data.setRate(rate);
			data.setUSD(USDValue);
			
			ordDetails.add(data);
		}
		
		String OrderTotal = orderTotal.getText();
		data.setOrdTotal(OrderTotal);
		
		}
		return ordDetails;
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
}


