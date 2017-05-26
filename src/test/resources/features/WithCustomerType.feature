Feature: Test cases for COL application Partner - test

 @End2End 
  Scenario Outline: Place order of foreign currency with customer type
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I enter beneficiary details
    And I update check details
    And I click on AddToOrder
    And I add denomination on add order
    And I add more currency add denomination on add order click On currency view
    And I select delivery method
    Then I validate the total amount
    And I select payment method to confirm order
    And I enter customer details
    And I change branch details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
| AutomationId |
| 19333 |
| 29116 |
| 126525 |
| 126526 |
| 127657 |
| 128380 |
| 131349 |