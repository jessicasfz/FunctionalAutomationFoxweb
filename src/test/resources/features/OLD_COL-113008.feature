Feature: Test cases for COL application Partner - 113008

  @End2End @113008
  Scenario Outline: To verify that user is able to place an order for single prepaid card_One card/two card
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    And I create order of transaction for customer
    And I select currency of product
    And I click on Quote and view after entering amount
    And I enter beneficiary details
    #includes select country of issue, check date and check checkboxes
    And I update check details
    #handle show currency button popup message here based on product type
    And I click on AddToOrder
    #And I do not add denomination on add order
    Then I validate the total amount
    When I select without payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1900001  |
      | Auto1900002  |

  @End2End @113008
  Scenario Outline: To verify that user is able to place an order for MultiCCY prepaid card_One card
     Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    And I add more currency do not add denomination on add order click On currency view
    #And I do not waive fee
    Then I validate the total amount
    And I select payment method to confirm order
    And I enter customer details
    And I change branch details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1900003  |
      | Auto1900004  |
      
@End2End @113008 
  Scenario Outline: Place Sale to customer online order of foreign currency with denom
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I add denomination on add order
    Then I validate the total amount
    And I select payment method to confirm order
    And I enter customer details
    And I change branch details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1900005  |
      
 @End2End @113008 
  Scenario Outline: Place Sale to customer online order of foreign currency without denom
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    Then I validate the total amount
    And I select payment method to confirm order
    And I enter customer details
    And I change branch details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1900006  |
      
@End2End @113008
  Scenario Outline: Place Sale to Travelex Multi CCY online order of foreign currency with denom
     Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I add denomination on add order
    And I add more currency add denomination on add order click On currency view
    Then I validate the total amount
    And I select payment method to confirm order
    And I enter customer details
    And I change branch details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1900007  |
      