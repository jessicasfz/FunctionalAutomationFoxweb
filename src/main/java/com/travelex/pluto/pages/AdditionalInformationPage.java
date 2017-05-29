package com.travelex.pluto.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdditionalInformationPage {
	
	@FindBy(name = "COMMENTS")
	WebElement txtComments;
	
	@FindBy(name = "Commission")
	WebElement txtOtherFee;
	
	@FindBy(name = "DeliveryCharge")
	WebElement txtDeliveryCharge;
	
	@FindBy(name = "UserName")
	WebElement txtTellerName;
	
}
