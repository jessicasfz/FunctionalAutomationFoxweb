@FoxwebE2E
Feature: To verify the Get Quote Feature 
@GetQuoteFW @Smoke @Regression
Scenario Outline: To Verify I am able to Get Quote for a particular currency from a Branch Login
Given I read Excel data with AutomationID "<AutomationId>"
And I launch Foxweb application
When I navigate to Get Quote Page
And I enter the Quote Details
Then I should see Quote Details
Examples:
|AutomationId|
|AutoFoxWebGetQuote_01|
#|AutoFoxWebGetQuote_02|

@GetQuoteFW1 @Smoke @Regression
Scenario Outline: To Verify I am able to Get Quote for a particular currency from a Branch Login
Given I read Excel data with AutomationID "<AutomationId>"
And I launch Foxweb application
When I navigate to Order Page
And I enter the Order Details
And I preview and confirm Details
And I select Suspicious Transaction
Then I click on Order Confirmation
And I get the Order Confirmation Success message


Examples:
|AutomationId|
|AutoFoxWebGetQuote_01|
#|AutoFoxWebGetQuote_02|