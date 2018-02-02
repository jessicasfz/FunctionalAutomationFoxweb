@FoxwebE2E
Feature: To verify the Get Quote Feature
@GetQuoteFW @SmokeFW @Regression
Scenario Outline: To Verify I am able to Get Quote for a particular currency from a Branch Login
Given I read Excel data with AutomationID "<AutomationId>"
And I launch Foxweb application
When I navigate to Get Quote Page
And I enter the Quote Details
Then I should see Quote Details
Examples:
|AutomationId|
|AutoFoxWebGQ_01|
#|AutoFoxWebGQ_02|



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
#|AutoFoxWebPO_01|
#|AutoFoxWebPO_02|
#|AutoFoxWebPO_03|
#|AutoFoxWebPO_04|
#|AutoFoxWebPO_05|
#|AutoFoxWebPO_06|
#|AutoFoxWebPO_07|
#|AutoFoxWebPO_08|
#|AutoFoxWebPO_09|
#|AutoFoxWebPO_10|
#|AutoFoxWebPO_11|
#|AutoFoxWebPO_12|
#|AutoFoxWebPO_13|
#|AutoFoxWebPO_14|
#|AutoFoxWebPO_15|
#|AutoFoxWebPO_16|
#|AutoFoxWebPO_17|
#|AutoFoxWebPO_18|
#|AutoFoxWebPO_19|
#|AutoFoxWebPO_20|
#|AutoFoxWebPO_21|
#|AutoFoxWebPO_22|
#|AutoFoxWebPO_23|
#|AutoFoxWebPO_24|
#|AutoFoxWebPO_25|
#|AutoFoxWebPO_26|
#|AutoFoxWebPO_27|
#|AutoFoxWebPO_28|
#|AutoFoxWebPO_29|
#|AutoFoxWebPO_30|
#|AutoFoxWebPO_31|
#|AutoFoxWebPO_32|
#|AutoFoxWebPO_33|
#|AutoFoxWebPO_34|
#|AutoFoxWebPO_35|
#|AutoFoxWebPO_36|
#|AutoFoxWebPO_37|
#|AutoFoxWebPO_38|
#|AutoFoxWebPO_39|
#|AutoFoxWebPO_40|
|AutoFoxWebPO_41|
|AutoFoxWebPO_42|
|AutoFoxWebPO_43|
|AutoFoxWebPO_44|


#@PEP/SanctionCheck @SmokeFW @Regression
#Scenario Outline: To Verify I am able to Place Order with any check for PEP/Sanction hit transactions for a particular currency from a Branch Login
#Given I read Excel data with AutomationID "<AutomationId>"
#And I launch Foxweb application
#And I navigate to Order Page
#When I enter the Order Details
#Then I get PEP/Sanction hit error response for the above order
#
#Examples:
#|AutomationId|
#|AutoFoxWebPO_01|


@ConfirmOrder @SmokeFW @Regression
Scenario Outline: To Verify I am able to Place Order without any check for Suspicious transactions for a particular currency from a Branch Login
Given I read Excel data with AutomationID "<AutomationId>"
And I launch Foxweb application
When I navigate to Process Transactions Page
And I enter the Process Transactions Details
When I process the transaction
Then I get the Process Transaction Success message

Examples:
|AutomationId|
|AutoFoxWebCO_01|
#|AutoFoxWebCO_02|



