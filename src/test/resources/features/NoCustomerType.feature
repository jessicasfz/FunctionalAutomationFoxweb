Feature: Test cases for COL application Partner - test

 @End2End 
  Scenario Outline: Place order of foreign currency
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
| 11226 |
| 11227 |
| 13571 |
| 16974 |
| 19340 |
| 91066 |
| 91084 |
| 91212 |
| 113008 |
| 114040 |
| 116198 |
| 118970 |
| 124973 |
| 126054 |
| 126187 |
| 126548 |
| 126634 |
| 127105 |
| 127551 |
| 127875 |
| 128103 |
| 128282 |
| 128586 |
| 128748 |
| 129526 |
| 129666 |
| 129750 |
| 129989 |
| 130936 |
| 131320 |
| 131786 |
| 221508 |
| 881247 |
| 4423786 |
| 22214264 |
| 22214337 |
| 22214349 |
| 22214384 |
| 22214413 |
| 22214416 |
| 22214426 |
| 22214437 |
| 22214641 |
| 22214724 |
| 22214730 |
| 22214989 |
