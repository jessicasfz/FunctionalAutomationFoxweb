package com.travelex.pluto.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerDetailsPage {
	
	@FindBy(name = "cmbTitle")
	WebElement listTitle;
	
	@FindBy(name = "txtShippingFirstName")
	WebElement txtFirstName;
	
	@FindBy(name = "txtShippingSurName")
	WebElement txtLastName;
		
	@FindBy(name = "txtAddress1")
	WebElement txtAddress1;
	
	@FindBy(name = "txtAddress2")
	WebElement txtAddress2;
	
	@FindBy(name = "txtProvince")
	WebElement txtState;
	
	@FindBy(name = "cmbCountry")
	WebElement listCountry;
	
	@FindBy(name = "txtPostCode")
	WebElement txtZipcode;
	
	@FindBy(name = "txtHomeTelephone")
	WebElement txtHomeTelephoneNo;
	
	@FindBy(name = "btnBranchMatch")
	WebElement btnSearchName;
	
	@FindBy(name = "cmbCollectionDate")
	WebElement listCollectionDateAndDeliveryDate;
	
}
