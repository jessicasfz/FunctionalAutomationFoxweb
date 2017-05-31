package com.travelex.pluto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	private int timeOutPeriod = 3000;
	public JavascriptExecutor myExecutor;
		
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
			btnSearchMatch.click();
			wrapper.waitForElementToBeClickable(lstBranches, timeOutPeriod);
			Select branchlist = new Select(lstBranches);
			branchlist.selectByIndex(0);
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
	
	public void selectProductAndCurrency(String tranType, String salutaion,String fname,String lname){
		myExecutor = ((JavascriptExecutor) driver);
		if(tranType.equalsIgnoreCase("PurchaseOrder")){
			Select salutaionList = new Select(lstSalutation);
			salutaionList.selectByVisibleText(salutaion);
			
			myExecutor.executeScript("arguments[0].value='"+fname+"';", txtfirstname);
			myExecutor.executeScript("arguments[0].value='"+lname+"';", txtLastname);

		}
	}
	
	public void clearAndEnterForeignAmount(String foreignAmount){
		txtForeignAmount.clear();
		txtForeignAmount.sendKeys(foreignAmount);
	}
	
	public void enterProductDetails(String multiproductDetails){
		myExecutor = ((JavascriptExecutor) driver);
		String[] listOfProductsInDetailDenoms = multiproductDetails.split("\\|");
		int noOfProducts = listOfProductsInDetailDenoms.length;
		
		for(int i=0;i<=noOfProducts-1;i++){
			if(!(i==0)){
			btnAddCurrency.click();
			}
			String listOfProductDetails = listOfProductsInDetailDenoms[i];
			String[] eachProductDetails = listOfProductDetails.split("\\#");
			
			if(eachProductDetails[0].equalsIgnoreCase("Foreign Currency")){
				String productType = eachProductDetails[0].trim();
				String currency = eachProductDetails[1].trim();
				String foreignAmount = eachProductDetails[2].trim();
				String quantity = eachProductDetails[3].trim();
				String denom = eachProductDetails[4].trim();
				if(i==0){
					if(!productType.equalsIgnoreCase("NA")){			
						Select productTypeList = new Select(listProductType);
						productTypeList.selectByVisibleText(productType);
					}
					if(!currency.equalsIgnoreCase("NA")){			
						Select currencyList = new Select(listCurrencyCards);
						currencyList.selectByVisibleText(currency);
					}
					if(!foreignAmount.equalsIgnoreCase("NA")){			
						myExecutor.executeScript("arguments[0].value='"+foreignAmount+"';", txtForeignAmount);
					}
				}else{
					listProductType = driver.findElement(By.name("SiteProductTypes"+i));
					Select productTypeList = new Select(listProductType);
					productTypeList.selectByVisibleText(productType);
					listCurrencyCards = driver.findElement(By.name("SiteProducts"+i));
					Select currencyList = new Select(listCurrencyCards);
					currencyList.selectByVisibleText(currency);
					txtForeignAmount = driver.findElement(By.name("txtForeignValue"+i));
					myExecutor.executeScript("arguments[0].value='"+foreignAmount+"';", txtForeignAmount);
					
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
	
	
	public void clickOnNextBtn(){
		btnNext.click();
	}
		
	
	
}
