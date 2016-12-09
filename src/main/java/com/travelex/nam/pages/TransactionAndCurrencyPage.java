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

import com.travelex.framework.common.WebDriverWrapper;

public class TransactionAndCurrencyPage extends LoadableComponent<TransactionAndCurrencyPage>{
	public String delimiter ="\\|";
	//Logger logger = Logger.getLogger(TransactionAndCurrencyPage.class.getName());
	WebDriver driver;
	WebDriverWrapper wrapper ;
	private int timeOutPeriod = 3000;
	private int waitTime=300;
	public WebElement ele = null;
	public static double transactionTotal,serviceCharge,accountFee,deleiveryCharge;
	public static int noOfrows;

	@FindBy(id = "searchTerm")
	WebElement txtBranch;

	@FindBy(id = "btnSearch")
	WebElement btnRetrieve;

	@FindBy(name = "branchId")
	WebElement lstBranchLocation;

	@FindBy(xpath = "//input[@value='  NEXT  ']")
	WebElement btnNext;

	@FindBy(xpath = "//input[@value='Next Order']")
	WebElement btnNextOrder;

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

	public void orderTypeSelection(String orderType){
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

	public void transactionDetails(String transactionType, String productType, String currency){
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

		Select productDetailsList = new Select(lstProductDetails);
		productDetailsList.selectByVisibleText(productType);
		Select currencyList = new Select(lstCurrency);
		currencyList.selectByVisibleText(currency);
	}

	public void transactionDetails(String transactionType, String productType){
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

		Select productDetailsList = new Select(lstProductDetails);
		productDetailsList.selectByVisibleText(productType);

	}

	public void transactionDetails(String currency){
		Select currencyList = new Select(lstCurrency);
		currencyList.selectByVisibleText(currency);

	}


	public void enterForeignAmount(String foreignAmount){
		txtForeignAmount.clear();
		txtForeignAmount.sendKeys(foreignAmount);
		if(wrapper.isAlertPresent()){
			wrapper.acceptAlert(timeOutPeriod);
			txtForeignAmount.clear();
			txtForeignAmount.sendKeys(foreignAmount);
		}
	}
	
	public void checkForeignAmount(String foreignAmount){
		String newAmount = txtForeignAmount.getText();
		Assert.assertEquals(newAmount, foreignAmount);
	}

	public void deliveryTypeBtn(String deliveryType){
		wrapper.waitForLoaderInvisibility(waitTime);
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

	public void acceptAlert(){
		wrapper.acceptAlert(timeOutPeriod);
		wrapper.waitForLoaderInvisibility(waitTime);
	}

	//Code modified for IE :RD
	public void addOrderBtnClick() throws InterruptedException{
		wrapper.waitForElementToBeClickable(btnAddToOrder, 3000000);
		btnAddToOrder.click();
		//wrapper.waitForLoaderInvisibility(waitTime);
		Thread.sleep(3000);
	}

	public void confirmOrderBtn(){
		btnConfirm.click();
		wrapper.waitForLoaderInvisibility(waitTime);
	}

	public CustomerDetailsPage customerDetailsPage() {
		btnConfirm.click();
		wrapper.waitForLoaderInvisibility(waitTime);
		return new CustomerDetailsPage(driver).get();
	}

	public void clickOnConfirmButtion(){
		btnConfirm.click();
		wrapper.waitForLoaderInvisibility(waitTime);
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

	public void checkBoxClick(String browser) throws InterruptedException{
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

	public boolean isTransactionPageLoaded(){
		boolean isTransactionPageLoaded = false;
		if(lblHomePage.isDisplayed()){
			isTransactionPageLoaded = true;
		}
		return isTransactionPageLoaded;

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

	public void nextOrderButtonClick(){
		btnNextOrder.click();
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

	/**
	 *  Submit Transaction Details 
	 * @param configConfirmBtn
	 */

	public void submitTransactionAndCurrDetails(String configConfirmBtn) {
		if(!configConfirmBtn.equalsIgnoreCase("NA")){
			btnConfirm.click();
			wrapper.waitForLoaderInvisibility(waitTime);
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

	public void transactionDetailsAndcustomerType(String transactionType,String accountHolderType){
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
	}

	public void productTypeAndcurrencySelection(String productType,String currency){
		Select productDetailsList = new Select(lstProductDetails);
		productDetailsList.selectByVisibleText(productType);
		Select currencyList = new Select(lstCurrency);
		currencyList.selectByVisibleText(currency);
	}

	public void enterBeneficiaryDetails(String beneficiary,String add,String city,String state,String zipcode){
		txtBeneficiary.sendKeys(beneficiary);
		txtAddress1.sendKeys(add);
		txtCity.sendKeys(city);
		txtState.sendKeys(state);
		txtZipCode.sendKeys(zipcode);
	}

	public void selectCountry(String country){
		Select countryList = new Select(lstCountry);
		countryList.selectByVisibleText(country);
	}

	public void enterComments(String comment){
		txtComments.sendKeys(comment);
	}

	public String switchtoPopUpAndGetMessage(){
		String popupMessage = null;
		popupMessage =driver.switchTo().alert().getText();
		return popupMessage;
	}

	public void clickShowCurrencyButton(){
		btnShowCurrency.click();
		wrapper.waitForLoaderInvisibility(waitTime);
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


	@FindBy(id = "reasons")
	WebElement lstReason;

	@FindBy(id = "paymentMethod")
	WebElement lstPaymentMethod;	


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

	public void clearButtonClick(){
		btnClearFields.click();
		wrapper.waitForLoaderInvisibility(waitTime);
	}

	public void deleteButtonClick(){
		btndeleteOrder.click();
		wrapper.waitForLoaderInvisibility(waitTime);
	}

	//New Addition RD

	@SuppressWarnings("unused")
	public ArrayList<HashMap<String, String>> fetchOrderDetails(String Currency){
		String[] curr = Currency.split(",");
		ArrayList<HashMap<String,String>> ordDe = new ArrayList<HashMap<String,String>>();

		for(int i=0;i<curr.length;i++){
			HashMap<String,String> ordDet = new HashMap<String,String>();
			int row=1;
			row = row+i;
			String Currdesc = "CurrDesc"+i;
			String Units = "CurrDesc"+i;
			String keyCurrency= curr[i]+i;
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
			ordDet.put("USDAmount", USDValue);
			ordDe.add(ordDet);
		}
		
		
		return ordDe;
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

	public void fillDenomQuantity(String denomQuantity){
		txtDenomQty.sendKeys(denomQuantity);
	}

	public boolean isDenomInfoLoaded(){
		boolean isDenomInfoLoaded = false;
		if(txtDenomQty.isDisplayed()){
			isDenomInfoLoaded = true;
		}
		return isDenomInfoLoaded;

	}

	public void changeOrderButtonClick(){
		btnchangeOrder.click();
		wrapper.waitForLoaderInvisibility(waitTime);

	}

	public void editButtonClick(){
		editButtonImg.click();
		wrapper.waitForLoaderInvisibility(waitTime);

	}

	public void changeBranchLink(){
		lnkChangeBranch.click();
		wrapper.waitForLoaderInvisibility(waitTime);
	}

	public boolean isBranchSelectionPageLoaded(){
		boolean isBranchSelectionPageLoaded = false;
		if(txtBranchSearch.isDisplayed()){
			isBranchSelectionPageLoaded = true;
		}
		return isBranchSelectionPageLoaded;
	}

	public boolean isBranchDropDownDisplayed(){
		boolean isBranchDropDownDisplayed = false;
		if(lstBranchID.isDisplayed()){
			isBranchDropDownDisplayed = true;
		}
		return isBranchDropDownDisplayed;
	}
	
	public void selectCurrency(String currency){
        Select currencyList = new Select(lstCurrency);
        currencyList.selectByVisibleText(currency);
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


