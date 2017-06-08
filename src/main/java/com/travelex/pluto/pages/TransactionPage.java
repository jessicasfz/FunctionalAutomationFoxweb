package com.travelex.pluto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.travelex.framework.common.WebDriverWrapper;

public class TransactionPage extends LoadableComponent<TransactionPage>{

	WebDriverWrapper wrapper ;
	WebDriver driver;
	private int timeOutPeriod = 30;

	@FindBy(id = "cboPurchaseType")
	WebElement listPurchaseType;

	@FindBy(id = "txtCompanyAutocomplete")
	WebElement txtCompanyName;

	@FindBy(id = "OrderTypes")
	WebElement listCustomerType;

	@FindBy(id = "DeliveryMethods")
	WebElement listDeliveryMethods;

	@FindBy(name = "btnBranchMatch")
	WebElement btnSearchName;

	@FindBy(name = "btnSubmit")
	WebElement btnSearchMatch;

	@FindBy(name = "SiteProductTypes")
	WebElement listProductType;

	@FindBy(name = "SiteProducts")
	WebElement listCurrencyCards;

	@FindBy(name = "txtExchangeRate")
	WebElement txtRate;

	@FindBy(name = "txtInverseExchangeRate")
	WebElement txtInverseRate;

	@FindBy(name = "txtForeignValue")
	WebElement txtForeignAmount;

	@FindBy(name = "txtLocalValue")
	WebElement txtDomesticAmount;		

	@FindBy(name = "txtServiceFee")
	WebElement txtServiceFee;

	@FindBy(name = "btnDenom")
	WebElement btnDenom;

	@FindBy(name = "btnDraftDetail")
	WebElement btnDraftDetail;



	@FindBy(name = "btnCurrencyDelete")
	WebElement btnDelete;

	@FindBy(name = "btnNext")
	WebElement btnNext;

	@FindBy(xpath = "//input[@value='Preview Order']")
	WebElement btnPreviewOrder;

	@FindBy(xpath = "//input[@value='Add Currency']")
	WebElement btnAddCurrency;

	@FindBy(xpath = "//input[@value='Start Again']")
	WebElement btnStartAgain;

	@FindBy(xpath = "//input[@value='Calculate']")
	WebElement btnCalculate;

	@FindBy(name = "txtMatch")
	WebElement txtbranchLocation;

	@FindBy(name = "btnSubmit")
	WebElement btnFindMatch;

	@FindBy(name = "btnSelect")
	WebElement btnSelect;

	@FindBy(name = "btnClose")
	WebElement btnClose;

	@FindBy(name = "txtBeneficiaryName")
	WebElement txtBeneficiaryName;

	@FindBy(name = "txtBeneficiaryAddressLine1")
	WebElement txtBeneficiaryAddressLine1;

	@FindBy(name = "txtBeneficiaryAddressLine2")
	WebElement txtBeneficiaryAddressLine2;

	@FindBy(name = "txtBeneficiaryCity")
	WebElement txtBeneficiaryCity;

	@FindBy(name = "txtBeneficiaryCountry")
	WebElement listBenificiaryCountry;

	@FindBy(name = "txtBeneficiaryState")
	WebElement listBeneficiaryState;

	@FindBy(name = "txtBeneficiaryZipCode")
	WebElement txtBeneficiaryZipCode;

	@FindBy(name = "txtBeneficiaryComment")
	WebElement txtBeneficiaryComment;

	@FindBy(name = "btnBeneficiaryOK")
	WebElement btnBeneficiaryOK;

	@FindBy(name = "btnClear")
	WebElement btnClear;

	@FindBy(name = "lstBranches")
	WebElement lstBranches;

	@FindBy(name = "cmbTitle")
	WebElement lstSalutation;

	@FindBy(name = "txtShippingFirstName")
	WebElement txtfirstname;

	@FindBy(name = "txtShippingSurName")
	WebElement txtLastname;

	@FindBy(name = "btnAcceptDenoms")
	WebElement btnAcceptDenoms;

	public TransactionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@SuppressWarnings("unused")
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(txtCompanyName, timeOutPeriod);
		if(txtCompanyName.isDisplayed()){
			isPageLoaded = true;
		}
	}

	public void load(){
		try {
			wrapper.waitForElementToBeDisplayed(txtCompanyName, timeOutPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectCustomerTypeAndDeliveryType(String customerType,String deliveryMethod){
		if(!customerType.equalsIgnoreCase("NA")){	
			wrapper.waitForElementToBeDisplayed(listCustomerType, timeOutPeriod);
			Select listCustomerTypeList = new Select(listCustomerType);
			listCustomerTypeList.selectByVisibleText(customerType);
		}

		if(!deliveryMethod.equalsIgnoreCase("NA")){	
			wrapper.waitForElementToBeDisplayed(listDeliveryMethods, timeOutPeriod);
			Select listDeliveryMethodsList = new Select(listDeliveryMethods);
			listDeliveryMethodsList.selectByVisibleText(deliveryMethod);
		}
	}

	public void clickOnSearchAndSelectBranchLocation(String branchLocation) throws InterruptedException{
		wrapper.waitForElementToBeDisplayed(btnSearchName, timeOutPeriod);
		btnSearchName.click();
		Thread.sleep(3000);
		switchToNewWindowOrPreviousWindow();
		wrapper.waitForElementToBeDisplayed(txtbranchLocation, timeOutPeriod);
		wrapper.sendKeysUsingJavaScript(txtbranchLocation, branchLocation);
		btnSearchMatch.click();
		wrapper.waitForElementToBeClickable(lstBranches, timeOutPeriod);
		Select branchlist = new Select(lstBranches);
		branchlist.selectByIndex(0);
		btnSelect.click();
		switchToNewWindowOrPreviousWindow();			
	}

	public String switchToNewWindowOrPreviousWindow(){		
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}		
		String Title = driver.getTitle();
		return  Title;		
	}

	public void enterCustomerDetailsInPurchse(String salutaion, String fname, String lname){
			Select salutaionList = new Select(lstSalutation);
			salutaionList.selectByVisibleText(salutaion);
			wrapper.sendKeysUsingJavaScript(txtfirstname, fname);
			wrapper.sendKeysUsingJavaScript(txtLastname, lname);
	}

	public void enterCustomerDetails(String tranType, String salutaion, String fname, String lname){
		if(tranType.equalsIgnoreCase("PurchaseOrder")){
			Select salutaionList = new Select(lstSalutation);
			salutaionList.selectByVisibleText(salutaion);
			wrapper.sendKeysUsingJavaScript(txtfirstname, fname);
			wrapper.sendKeysUsingJavaScript(txtLastname, lname);
		}
	}

	public void clearAndEnterForeignAmount(String foreignAmount){
		txtForeignAmount.clear();
		txtForeignAmount.sendKeys(foreignAmount);
	}

	public void enterProductDetails(String multiproductDetails){
		String[] listOfProductsInDetailDenoms = multiproductDetails.split("\\|");
		int noOfProducts = listOfProductsInDetailDenoms.length;
		String ch = "";
		for(int i=0;i<=noOfProducts-1;i++){
			if(!(i==0)){
				btnAddCurrency.click();
				ch=String.valueOf(i);
			}
			String listOfProductDetails = listOfProductsInDetailDenoms[i];
			String[] eachProductDetails = listOfProductDetails.split("\\#");

			if(eachProductDetails[0].equalsIgnoreCase("Foreign Currency")){
				String productType = eachProductDetails[0].trim();
				String currency = eachProductDetails[1].trim();
				String foreignAmount = eachProductDetails[2].trim();
				String quantity = eachProductDetails[3].trim();
				String denom = eachProductDetails[4].trim();
					listProductType = driver.findElement(By.name("SiteProductTypes"+ch));
					Select productTypeList = new Select(listProductType);
					productTypeList.selectByVisibleText(productType);
					listCurrencyCards = driver.findElement(By.name("SiteProducts"+ch));
					Select currencyList = new Select(listCurrencyCards);
					currencyList.selectByVisibleText(currency);
					txtForeignAmount = driver.findElement(By.name("txtForeignValue"+ch));
					wrapper.sendKeysUsingJavaScript(txtForeignAmount, foreignAmount);
					if(!quantity.equalsIgnoreCase("NA")){
							denomSelection(quantity.toString(), denom.toString(), i);
					}
			}else if(eachProductDetails[0].equalsIgnoreCase("Drafts")){
				String productType = eachProductDetails[0].trim();
				String currency = eachProductDetails[1].trim();
				String foreignAmount = eachProductDetails[2].trim();				
				String benificiaryDetails = eachProductDetails[3].trim();
					listProductType = driver.findElement(By.name("SiteProductTypes"+ch));
					Select productTypeList = new Select(listProductType);
					productTypeList.selectByVisibleText(productType);
					listCurrencyCards = driver.findElement(By.name("SiteProducts"+ch));
					Select currencyList = new Select(listCurrencyCards);
					currencyList.selectByVisibleText(currency);
					txtForeignAmount = driver.findElement(By.name("txtForeignValue"+ch));
					wrapper.sendKeysUsingJavaScript(txtForeignAmount, foreignAmount);
					benificiaryDetails(benificiaryDetails, i);
			}else if(eachProductDetails[0].equalsIgnoreCase("Checks")){
				String productType = eachProductDetails[0].trim();
				String currency = eachProductDetails[1].trim();
				String foreignAmount = eachProductDetails[2].trim();
					listProductType = driver.findElement(By.name("SiteProductTypes"+ch));
					Select productTypeList = new Select(listProductType);
					productTypeList.selectByVisibleText(productType);
					listCurrencyCards = driver.findElement(By.name("SiteProducts"+ch));
					Select currencyList = new Select(listCurrencyCards);
					currencyList.selectByVisibleText(currency);
					txtForeignAmount = driver.findElement(By.name("txtForeignValue"+ch));
					wrapper.sendKeysUsingJavaScript(txtForeignAmount, foreignAmount);				
			}
		}
	}

	public void denomSelection(String quantity,String denom, int row) {			
		if (row == 0) {
			btnDenom = driver.findElement(By.name("btnDenom"));
		} else {
			btnDenom = driver.findElement(By.name("btnDenom" + row));
		}
		btnDenom.click();
		String[] denominationlist = denom.split(",");
		String[] quantitylist = quantity.split(",");		
		int denomsCount = denominationlist.length;
		for (int j=0;j<=denomsCount-1;j++) {
			WebElement element,element2 = null;
			String quantityValue = quantitylist[j].trim();
			String denominationValue = denominationlist[j].trim();
			element = driver.findElement(By.xpath("//td/div/font[normalize-space(text())='" + denominationValue + "']/../../following-sibling::*/input"));
			element2 = driver.findElement(By.xpath("//td/div/font[normalize-space(text())='" + denominationValue + "']/../../following-sibling::td[2]/input"));
			wrapper.sendKeysUsingJavaScript(element, quantityValue);				
			int foreignValue = (Integer.parseInt(denominationValue)) * (Integer.parseInt(quantityValue));
			wrapper.sendKeysUsingJavaScript(element2, foreignValue+"");
		}			
		clickOnAcceptDenomBtn();
	}

	public CustomerAdditionalInfo clickOnNextBtn(String TransType,String CustomerType){
		wrapper.waitForElementToBeDisplayed(btnNext, timeOutPeriod);
		btnNext.click();
		if(TransType.equalsIgnoreCase("PurchaseOrder")){
			if(CustomerType.equalsIgnoreCase("Customer") || CustomerType.equalsIgnoreCase("Citigold") || CustomerType.equalsIgnoreCase("Private Banking") || CustomerType.equalsIgnoreCase("Corporate") 
					|| CustomerType.equalsIgnoreCase("Gold")|| CustomerType.equalsIgnoreCase("Platinum") || CustomerType.equalsIgnoreCase("Employee")|| CustomerType.equalsIgnoreCase("CitiBusiness")){
				return new OrderConfirmationPage(driver).get();					
			}else{
				return new AdditionalInformationPage(driver).get();
			}			
		}else{
			return new CustomerDetailsPage(driver).get();
		}
	}

	public void clickOnAcceptDenomBtn(){
		wrapper.waitForElementToBeDisplayed(btnAcceptDenoms, timeOutPeriod);
		btnAcceptDenoms.click();		
		if (wrapper.isAlertPresent()) {
			wrapper.acceptAlert();
			btnAcceptDenoms.click();
		}
	}	

	public void benificiaryDetails(String benificiaryDetails, int row){
		if (row == 0) {
			btnDraftDetail = driver.findElement(By.name("btnDraftDetail"));
		} else {
			btnDraftDetail = driver.findElement(By.name("btnDraftDetail" + row));
		}
		wrapper.waitForElementToBeDisplayed(btnDraftDetail, timeOutPeriod);
		btnDraftDetail.click();
		wrapper.waitForElementToBeDisplayed(listBeneficiaryState, timeOutPeriod);
		String[] benificiaryDetailsList = benificiaryDetails.split(",");
		String benificiaryName = benificiaryDetailsList[0];
		String address1 = benificiaryDetailsList[1];
		String address2 = benificiaryDetailsList[2];
		String city = benificiaryDetailsList[3];
		String country = benificiaryDetailsList[4];
		String state = benificiaryDetailsList[5];
		String zipcode = benificiaryDetailsList[6];
		String draftComment = benificiaryDetailsList[7];

		txtBeneficiaryName.sendKeys(benificiaryName);

		wrapper.sendKeysUsingJavaScript(txtBeneficiaryName, benificiaryName);
		wrapper.sendKeysUsingJavaScript(txtBeneficiaryAddressLine1, address1);
		wrapper.sendKeysUsingJavaScript(txtBeneficiaryAddressLine2, address2);
		wrapper.sendKeysUsingJavaScript(txtBeneficiaryCity, city);
		

		Select currencyList = new Select(listBenificiaryCountry);
		currencyList.selectByVisibleText(country);

		Select stateList = new Select(listBeneficiaryState);
		stateList.selectByVisibleText(state);
		
		wrapper.sendKeysUsingJavaScript(txtBeneficiaryZipCode, zipcode);
		wrapper.sendKeysUsingJavaScript(txtBeneficiaryComment, draftComment);
		
		btnBeneficiaryOK.click();

	}
}
