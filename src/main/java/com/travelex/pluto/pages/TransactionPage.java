package com.travelex.pluto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.travelex.framework.common.WebDriverWrapper;

public class TransactionPage {
	
	WebDriverWrapper wrapper ;
	WebDriver driver;
	private int timeOutPeriod = 3000;
		
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
	
	public void selectTypeOfPurchase(String transactionType,String purchaseType){
		if(transactionType.equalsIgnoreCase("PurchaseOrder")){			
			Select listPurchaseTypeList = new Select(listPurchaseType);
			listPurchaseTypeList.selectByVisibleText(purchaseType);
			btnNext.click();
		}		
	}
	
	public void selectCustomerTypeAndDeliveryType(String customerType,String deliveryMethod){
		if(!customerType.equalsIgnoreCase("NA")){			
			Select listCustomerTypeList = new Select(listCustomerType);
			listCustomerTypeList.selectByVisibleText(customerType);
		}
		
		if(!deliveryMethod.equalsIgnoreCase("NA")){			
			Select listDeliveryMethodsList = new Select(listDeliveryMethods);
			listDeliveryMethodsList.selectByVisibleText(deliveryMethod);
		}
	}
	
	public void clickOnSearchAndSelectBranchLocation(String branchLocation){
		if(!branchLocation.equalsIgnoreCase("NA")){
			btnSearchName.click();
			switchToNewWindowOrPreviousWindow();
			txtbranchLocation.sendKeys(branchLocation);
			btnSearchName.click();
			// Need To Select Exact Match 
			btnSelect.click();
			switchToNewWindowOrPreviousWindow();			
		}
	}
	
	public String switchToNewWindowOrPreviousWindow(){		
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}		
		String Title = driver.getTitle();
		return  Title;		
	}
	
	public void enterProductDetails(String multiproductDetails){
		String[] listOfProductsInDetailDenoms = multiproductDetails.split("\\|");
		int noOfProducts = listOfProductsInDetailDenoms.length;
		
		for(int i=0;i<=noOfProducts-1;i++){
			String listOfProductDetails = listOfProductsInDetailDenoms[i];
			String[] eachProductDetails = listOfProductDetails.split("\\#");
			
			if(eachProductDetails[0].equalsIgnoreCase("Foreign Currencies")){
				String productType = eachProductDetails[0].trim();
				String currency = eachProductDetails[1].trim();
				String foreignAmount = eachProductDetails[2].trim();
				String quantity = eachProductDetails[3].trim();
				String denom = eachProductDetails[4].trim();
				
				if(!productType.equalsIgnoreCase("NA")){			
					Select productTypeList = new Select(listProductType);
					productTypeList.selectByVisibleText(productType);
				}
				
				if(!currency.equalsIgnoreCase("NA")){			
					Select currencyList = new Select(listCurrencyCards);
					currencyList.selectByVisibleText(currency);
				}
				
				if(!foreignAmount.equalsIgnoreCase("NA")){			
					txtForeignAmount.sendKeys(foreignAmount);
				}
				
				if(!quantity.equalsIgnoreCase("NA")){
					try {
						denomSelection(quantity.toString(), denom.toString());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}else if(eachProductDetails[0].equalsIgnoreCase("Foreign Drafts")){
				String productType = eachProductDetails[0].trim();
				String currency = eachProductDetails[1].trim();
				String foreignAmount = eachProductDetails[2].trim();
				String benificiaryDetails = eachProductDetails[3].trim();
				
				if(!productType.equalsIgnoreCase("NA")){			
					Select productTypeList = new Select(listProductType);
					productTypeList.selectByVisibleText(productType);
				}
				
				if(!currency.equalsIgnoreCase("NA")){			
					Select currencyList = new Select(listCurrencyCards);
					currencyList.selectByVisibleText(currency);
				}
				
				if(!foreignAmount.equalsIgnoreCase("NA")){			
					txtForeignAmount.sendKeys(foreignAmount);
				}
				
				if(!benificiaryDetails.equalsIgnoreCase("NA")){
					
				}
			}else if(eachProductDetails[0].equalsIgnoreCase("Foreign checks")){
				String productType = eachProductDetails[0].trim();
				String currency = eachProductDetails[1].trim();
				String foreignAmount = eachProductDetails[2].trim();
				String benificiaryDetails = eachProductDetails[3].trim();
				
				if(!productType.equalsIgnoreCase("NA")){			
					Select productTypeList = new Select(listProductType);
					productTypeList.selectByVisibleText(productType);
				}
				
				if(!currency.equalsIgnoreCase("NA")){			
					Select currencyList = new Select(listCurrencyCards);
					currencyList.selectByVisibleText(currency);
				}
				
				if(!foreignAmount.equalsIgnoreCase("NA")){			
					txtForeignAmount.sendKeys(foreignAmount);
				}
				
				if(!benificiaryDetails.equalsIgnoreCase("NA")){
					
				}
			}
		}
		
	}
	
	public void denomSelection(String quantity,String denom) throws InterruptedException{			
			btnDenom.click();			
			String[] denominationlist = denom.split(",");
			String[] quantitylist = quantity.split(",");		
			int denomsCount = denominationlist.length;
			for (int j=0;j<=denomsCount-1;j++) {
				String quantityValue = quantitylist[j].trim();
				String denominationValue = denominationlist[j].trim();
				driver.findElement(By.xpath("//div[normalize-space(text())='"+ denominationValue +"']/../../td[3]/span/span/input")).sendKeys(quantityValue);
			}			

			Thread.sleep(1000);		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
