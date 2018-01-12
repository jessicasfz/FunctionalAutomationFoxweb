package com.travelex.foxweb.pages;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.travelex.framework.common.ConfigurationProperties;
import com.travelex.framework.common.WebDriverWrapper;
import com.travelex.framework.utilities.RandomCodeGenerator;
import com.travelex.stepDefinitions.MasterDataReader;

@SuppressWarnings("unused")
public class UserMaintenanceAdminPage extends LoadableComponent<UserMaintenanceAdminPage>{	

	private WebDriver driver;

	private WebDriverWrapper wrapper ;
	private int timeOutPeriod = 3000;
	private int waitTime=300;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(linkText = "Administration")
	WebElement lnkAdministration;
	
	@FindBy(linkText = "User Maintenance")
	WebElement lnkUserMaintenance;
	
	@FindBy(className = "button")
	WebElement btnAccountName;
	
	@FindBy(name = "entitycode2")
	WebElement txtEntityCode;
	
	@FindBy(linkText = "Retrieve Rows")
	WebElement lnkRetrieveRows;
	
	@FindBy(linkText = "Create New User")
	WebElement lnkCreateNewUser;
	
	@FindBy(name = "copyuser")
	WebElement drpdwnCopyUsersRole;
	
	@FindBy(name = "usercode")
	WebElement txtUserCode;
	
	@FindBy(css ="input[type='radio'][value='255']")
	WebElement selUserAccessLevelSupervisor;
	
	@FindBy(css ="input[type='radio'][value='1']")
	WebElement selUserAccessLevelUser;
	
	@FindBy(name = "temppass")
	WebElement txtPassword;
	
	@FindBy(name ="firstn")
	WebElement txtFirstName;
		
	@FindBy(name ="lastn")
	WebElement txtLastName;
			
	@FindBy(name="roleid")
	WebElement selUserRole;
	
	@FindBy(linkText = "Save Changes")
	WebElement lnkConfirmUser;
	
	@FindBy(name ="changePasswordCheckbox")
	WebElement chkboxChangePassword;
	
	@FindBy(linkText ="Edit")
	WebElement lnkEditUser;
	
	@FindBy(xpath = "//table[@class='list']/tbody/tr[3]/td/table/tbody/tr[2]/td[3]")
	WebElement lblCreatedUserCode;
	
	
	
	public UserMaintenanceAdminPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	@Override
	public void isLoaded(){
		boolean isPageLoaded = false;
		wrapper.waitForElementToBeDisplayed(lnkUserMaintenance, timeOutPeriod);
		if(lnkUserMaintenance.isDisplayed()){
			isPageLoaded = true;
		}
	}
	
	public boolean isUserMaintenanceAdminPageLoaded(){
		boolean isUserMaintenanceAdminPageLoaded = false;
		if(lnkUserMaintenance.isDisplayed()){
			isUserMaintenanceAdminPageLoaded = true;
		}
		return isUserMaintenanceAdminPageLoaded;
	}
	
	public boolean isUserEnterDetailsPageLoaded(){
		boolean isUserEnterDetailsPageLoaded = false;
		if(txtUserCode.isDisplayed()){
			isUserEnterDetailsPageLoaded = true;
		}
		return isUserEnterDetailsPageLoaded;
	}



/*public void load(){
	try {
		wrapper.waitForElementToBeDisplayed(txtEntityCode, timeOutPeriod);
	} catch (Exception e) {
		e.printStackTrace();
	}
}*/

public void navigateToEnterUserDetails(String EntityCode) {
	
	lnkUserMaintenance.click();
	txtEntityCode.clear();
	txtEntityCode.sendKeys(EntityCode);
	lnkRetrieveRows.click();
	lnkCreateNewUser.click();
	
	//return new UserCreationAdminPage(driver).get();
	
	
}

public void enterUserCreationDetails(String userRolesCopy, String userCode, String password, String firstName, String lastName, String accessLevel, String userRoleAccessLevel) {
	
		
		Select selUserCopyRole = new Select(drpdwnCopyUsersRole);
		selUserCopyRole.selectByVisibleText(userRolesCopy);
		txtUserCode.sendKeys(userCode);
		
		if("Supervisor".equalsIgnoreCase(accessLevel.trim())){
			selUserAccessLevelSupervisor.click();
		}
		
		txtPassword.sendKeys(password);
		txtFirstName.sendKeys(firstName);
		txtLastName.sendKeys(lastName);
		Select selUserRoleLevel = new Select(selUserRole);
		selUserRoleLevel.selectByVisibleText(userRoleAccessLevel);
		lnkConfirmUser.click();
	
	//return new UserCreationAdminPage(driver).get();
}

public void verifyUserCreatedSuccessfully(String userCode) {
	txtUserCode.sendKeys(userCode);
	lnkRetrieveRows.click();
	
	if (userCode.equals(lblCreatedUserCode.getText())){
		
		lnkEditUser.click();
	} 
	else
	{
	System.out.println("User is not created");
	}
	
	//wrapper.waitForElementToBeDisplayed(lnkEditUser, 10);
	
	wrapper.waitForElementToBeDisplayed(chkboxChangePassword, 10);
	chkboxChangePassword.click();
	lnkConfirmUser.click();
	
}

@Override
protected void load() {
	// TODO Auto-generated method stub
	
}
}
