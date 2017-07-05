@FoxwebE2E
Feature: To verify the Get Quote Feature
@GetQuoteFW @SmokeFW @Regression
Scenario Outline: To Verify I am able to Get Quote for a particular currency from a Branch Login
Given I read Excel data with AutomationID "<AutomationId>"
And I launch Foxweb application
When I navigate to Get Quote Page
#And I enter the Quote Details
#Then I should see Quote Details
Examples:
|AutomationId|
|AutoFoxWebGQ_01|
|AutoFoxWebGQ_02|



@PlaceOrder @SmokeFW @Regression
Scenario Outline: To Verify I am able to Place Order without any check for Suspicious transactions for a particular currency from a Branch Login
Given I read Excel data with AutomationID "<AutomationId>"
And I launch Foxweb application
When I navigate to Order Page
And I enter the Order Details
And I preview and confirm Details
Then I click on Order Confirmation
And I get the Order Confirmation Success message

Examples:
|AutomationId|
|AutoFoxWebPO_01|
#|AutoFoxWebPO_02|


@ConfirmOrder @SmokeFW @Regression
Scenario Outline: To Verify I am able to Place Order without any check for Suspicious transactions for a particular currency from a Branch Login
Given I read Excel data with AutomationID "<AutomationId>"
And I launch Foxweb application
When I navigate to Process Transactions Page
And I enter the Process Transactions Details
#When I process the transaction
#Then I get the Process Transaction Success message

Examples:
|AutomationId|
|AutoFoxWebCO_01|
#|AutoFoxWebCO_02|



