@FoxwebE2E
Feature: To verify the Repurchase Feature
@Repurchase
Scenario Outline: To Verify I am able to Repurchase a particular currency from a Branch Login
Given I read Excel data with AutomationID "<AutomationId>"
And I launch Foxweb application
When I navigate to Repurchase Page
And I enter the Repurchase Details
And I preview and confirm Details for Repurchase
Then I click on Repurchase Confirmation
And I get the Repurchase Confirmation Success message
Examples:
|AutomationId|
#|AutoFoxWebRP_01|
#|AutoFoxWebRP_02|
#|AutoFoxWebRP_03|
#|AutoFoxWebRP_04|
#|AutoFoxWebRP_05|
#|AutoFoxWebRP_06|
#|AutoFoxWebRP_07|
#|AutoFoxWebRP_08|
#|AutoFoxWebRP_09|
#|AutoFoxWebRP_10|
#|AutoFoxWebRP_11|
|AutoFoxWebRP_12|
|AutoFoxWebRP_13|
#|AutoFoxWebRP_14|
#|AutoFoxWebRP_15|