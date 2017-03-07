package com.travelex.nam.pages;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.travelex.framework.common.WebDriverWrapper;
//import com.travelex.stepDefinitions.StepDefinitions;

public class PrinterFriendlyPage extends LoadableComponent<PrinterFriendlyPage>{
	
	private WebDriver driver;
	public WebElement ele = null;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	private WebDriverWrapper wrapper ;
	private int timeOutPeriod = 3000;

	private int waitTime=300;
	
	@FindBy(xpath="//td[contains(text(),'Confirmation Number')]")
	WebElement lblPrinterPage;
	
	@FindBy(xpath = "//td[contains(text(),'Confirmation Number:')]")
	WebElement PageIdentifier;
	
	@FindBy(xpath = "//b[contains(text(),'Customer Account Number :')]/../../../preceding-sibling::tr[1]/td/div")
	WebElement CustomerName;
	
	@FindBy(xpath = "//b[contains(text(),'Customer Account Number :')]/..")
	WebElement CustomerAccNumber;
	
	@FindBy(xpath = "//table[3]/tbody/tr[1]/td[2]/div[1]")
	WebElement CustomerAddress;
	
	@FindBy(xpath = "//*[contains(text(),'Ordering')]/../following-sibling::div[1]")
	WebElement CustomerAddress2;
	
	@FindBy(xpath = "//*[contains(text(),'Ordering')]/../following-sibling::div[2]")
	WebElement CustomerAddress3;
	
	@FindBy(xpath = "//*[contains(text(),'Ordering')]/../following-sibling::div[3]")
	WebElement CustomerAddress4;
	
	@FindBy(xpath = "//td[contains(text(),'Foreign Currency Service Charge')]/following-sibling::td[6]")
	WebElement serviceCharge;
	
	@FindBy(xpath = "//td[contains(text(),'Account Holder Fee')]/following-sibling::td[6]")
	WebElement accFee;
	
	@FindBy(id = "spanOrderTotal")
	WebElement Transactiontotal;
	
	@FindBy(xpath = "//span[contains(text(),'Shipping Charge')]/../following-sibling::td[4]")
	WebElement shippingCharge;
	
	@FindBy(xpath = "//input[@value='Next Order']")
	WebElement btnNextOrder;
	
	@FindBy(xpath = "//a[contains(text(),'Change Branch')]")
	WebElement lnkChangeBranch;

	@SuppressWarnings("unused")
	@Override
	protected void isLoaded() {
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(lblPrinterPage, timeOutPeriod);
		if(lblPrinterPage.isDisplayed()){
			isPageLoaded = true;
		}
	}

	@Override
	protected void load() {
		try {
			wrapper.waitForElementToBeDisplayed(lblPrinterPage, timeOutPeriod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public PrinterFriendlyPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}
	
	public void nextOrderButtonClick(){
		btnNextOrder.click();
		wrapper.waitForLoaderInvisibility(waitTime);
	}
	
	public void changeBranchLink(String configChangeBranchLink){
		if(WebDriverWrapper.isConfigTrue(configChangeBranchLink)){
		lnkChangeBranch.click();
		wrapper.waitForLoaderInvisibility(waitTime);
		}
	}
	
	@SuppressWarnings("unused")
	public void verifyOrderDetails(ArrayList<HashMap<String,String>> ordDetails,HashMap<String,String> custDetails){
		double expectedTotal = TransactionAndCurrencyPage.transactionTotal;
		int rowcount = TransactionAndCurrencyPage.noOfrows - 1;
		
		String custName = CustomerName.getText();
		String[] custNam = custName.split(":");
		String customerName = custNam[1].trim();
		
		//String fullName = StepDefinitions.hashmap.get("radioBtn")+"."+" "+StepDefinitions.hashmap.get("fName")+" "+StepDefinitions.hashmap.get("lName");
		/*if(customerName.equalsIgnoreCase(fullName)){
			System.out.println("Name validation is successful");
		}else{
			System.out.println("Name validation failed");
		}*/
		//Assert.assertEquals(customerName, fullName);
		
		String custAccNo = CustomerAccNumber.getText();
		String[] custAcc = custAccNo.split(":");
		String customerAccNumber = custAcc[1].trim();
		//Assert.assertEquals(customerAccNumber, StepDefinitions.hashmap.get("GLNo"));
		
		String address1 = CustomerAddress.getText();
		String[] add = address1.split("\n");
		String add1 = add[0].trim();
		String add2 = add[1].trim();
		String add3 = add[2].trim();
		String add4 = add[3].trim();
//		String add5 = add[4].trim();
		//String add6 = add[5].trim();
		
		String add7 = CustomerAddress2.getText().trim();
		String add8 = CustomerAddress3.getText().trim();
		String add9 = CustomerAddress4.getText().trim();
		
		Assert.assertEquals(custDetails.get("addString1"), add2);
		Assert.assertEquals(custDetails.get("addString2"), add3);
		Assert.assertEquals(custDetails.get("addString3"), add4);
		Assert.assertEquals(custDetails.get("addString4"), add7);
		Assert.assertEquals(custDetails.get("addString5"), add8);
		
		ArrayList<HashMap<String, String>> accListValues = new ArrayList<HashMap<String, String>>();
		
		for(int i=0;i<rowcount;i++){
			HashMap<String, String> ordVeriDetails = new HashMap<String,String>();
			int row=1;
			row = row+i;
			String keyCurrency2= "currency"+i;
			String Currdesc = "CurrDesc"+i;
			String Units = "CurrDesc"+i;
			String keyCurrency= "Curr"+i;
			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+row+"]/td[3]"));
			String currDesc = ele.getText();
			
			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+row+"]/td[5]"));
			String units = ele.getText();
			
			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+row+"]/td[7]"));
			String rate = ele.getText();
			
			ele = driver.findElement(By.xpath("//td[contains(text(),'Item')]/../following-sibling::tr["+row+"]/td[9]"));
			String USDValue = ele.getText();
			
			ordVeriDetails.put("CurrDesc", currDesc);
			ordVeriDetails.put("Units", units);
			ordVeriDetails.put("Rate", rate);
			ordVeriDetails.put("USDValue", USDValue);
			
			//CurrDesc
			Assert.assertEquals(ordDetails.get(i).get("Desc"), ordVeriDetails.get("CurrDesc"));
			
			//Units
			
			Assert.assertEquals(ordDetails.get(i).get("Units"), ordVeriDetails.get("Units"));
			
			//Rate
			
			Assert.assertEquals(ordDetails.get(i).get("Rate"), ordVeriDetails.get("Rate"));
			
			//To calculate & Validate USD values
			double noOfUnits = Double.parseDouble(units);
			double txnRate = Double.parseDouble(rate);
			DecimalFormat df = new DecimalFormat("#.##");
			double expUSDValue = Double.parseDouble(df.format(noOfUnits*txnRate));
			System.out.println("Expected USD Value is "+expUSDValue);
			String expectedValue = String.valueOf(expUSDValue);
			if(ordDetails.get(i).get(keyCurrency2).equals(expectedValue)){
			Assert.assertEquals(ordDetails.get(i).get(keyCurrency2), expectedValue);
			}else{
				System.out.println("USD validation failed");
			}
			
			
			accListValues.add(ordVeriDetails);
		}
		
		String serCharge = serviceCharge.getText();
		double serCh = Double.parseDouble(serCharge);
		Assert.assertEquals(serCh,TransactionAndCurrencyPage.serviceCharge);
		
		String accFees = accFee.getText();
		double accfees = Double.parseDouble(accFees);
		Assert.assertEquals(accfees,TransactionAndCurrencyPage.accountFee);
		
		String delCharge = shippingCharge.getText();
		double shippCharge = Double.parseDouble(delCharge);
		Assert.assertEquals(shippCharge,TransactionAndCurrencyPage.deleiveryCharge);
		
		String total = Transactiontotal.getText();
		double TrxnTotal = Double.parseDouble(total);
		Assert.assertEquals(TrxnTotal,TransactionAndCurrencyPage.transactionTotal);
	}
	
	
	public PrinterFriendlyPage orderVerificationPage() {
		return new PrinterFriendlyPage(driver).get();
	}
	
	public TransactionAndCurrencyPage verifyNextOrderBtn(){
		return new TransactionAndCurrencyPage(driver).get();
	}
}
