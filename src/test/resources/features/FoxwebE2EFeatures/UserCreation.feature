@FoxwebE2E
Feature: To Verify I am able to create new user from  Admin Login

  @Usercreation @Regression
  Scenario Outline: To Verify I am able to create new user from  Admin Login
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch Foxweb application
    When I navigate to User Maintenance page
    And I enter the user details
    Then I verify successful message is displayed when user created

    Examples: 
      | AutomationId    |
      | AutoFoxWebUC_01 |
    # | AutoFoxWebUC_02 |
    #	| AutoFoxWebUC_03 |
