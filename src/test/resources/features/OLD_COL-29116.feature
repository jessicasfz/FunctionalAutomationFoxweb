Feature: Test cases for COL application Partner - OLD_29116

  @End2End @OLD_29116 
  Scenario Outline: To verify that user is able to place an order for single prepaid card_One card/two card
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    And I create order of transaction for customer
    And I select currency of product
    And I click on Quote and view after entering amount
    And I enter beneficiary details
    And I update check details
    And I click on AddToOrder
    And I select delivery method
    Then I validate the total amount
    When I select without payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1600001  |
      

  @End2End @OLD_29116
  Scenario Outline: To verify that user is able to place an order for MultiCCY prepaid card_One card
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    And I create order of transaction for customer
    And I confirm this trasaction
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I update check details
    And I click on AddToOrder
    And I add more currency do not add denomination on add order click On currency view
    Then I validate the total amount
    When I select without payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1600003  |
      
      @End2End @OLD_29116
  Scenario Outline: To verify that user is able to place an order for MultiCCY prepaid card_One card
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    And I create order of transaction for customer
    And I confirm this trasaction
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I update check details
    And I click on AddToOrder
    And I add more currency do not add denomination on add order click On currency view
    Then I validate the total amount
    When I select without payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1600002  |

 @End2End @OLD_29116 
  Scenario Outline: To verify that user is able to place an order for single prepaid card_One card/two card
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    And I create order of transaction for customer
    And I confirm this trasaction
    And I select currency of product
    And I click on Quote and view after entering amount
    And I enter beneficiary details
    And I update check details
    And I click on AddToOrder
    And I do not add denomination on add order
    Then I validate the total amount
    When I select without payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1600004  |
      
 @End2End @OLD_29116
  Scenario Outline: To verify that user is able to place an order for MultiCCY prepaid card_One card
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    And I create order of transaction for customer
		And I confirm this trasaction
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I update check details
    And I click on AddToOrder
    And I do not add denomination on add order
    And I add more currency do not add denomination on add order click On currency view
    Then I validate the total amount
    When I select without payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1600005  |
      | Auto1600006  |
