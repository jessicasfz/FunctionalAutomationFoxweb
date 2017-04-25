Feature: Test cases for COL application Partner - 29116

  @End2End @29116 
  Scenario Outline: Place Sale to customer online order of foreign currency without denom and changing default branch | Place Purchase from customer online order of foreign currency
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    When I create order of transaction for customer
    And I confirm this trasaction
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
      | Auto_1  |
      | Auto_2  |

@End2End @29116
  Scenario Outline: Place Sale to Travelex Multi CCY online order of foreign currency without denom | Place Purchase from Travelex Multi CCY Online order of foreign currency without denom
     Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    When I create order of transaction for customer
    And I confirm this trasaction
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    And I add more currency do not add denomination on add order click On currency view
    Then I validate the total amount
    And I select payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto_3  |
      | Auto_4  |
      
 @End2End @29116
  Scenario Outline: Place Sale to customer online order of foreign currency with denom
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    When I create order of transaction for customer
    And I confirm this trasaction
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I add denomination on add order
    Then I validate the total amount
    And I select payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto_5  |
      
  @End2End @29116
  Scenario Outline: Place Sale to customer online order of foreign currency without denom
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    When I create order of transaction for customer
    And I confirm this trasaction
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    Then I validate the total amount
    And I select payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto_6  |
      
@End2End @29116 
  Scenario Outline: Place Sale to Travelex Multi CCY online order of foreign currency with denom
     Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    When I create order of transaction for customer
    And I confirm this trasaction
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I add denomination on add order
    And I add more currency add denomination on add order click On currency view
    Then I validate the total amount
    And I select payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto_7  |
      
  @End2End @29116
  Scenario Outline: Place Sale to customer online order of foreign drafts Single Line
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
    Then I validate the total amount
    When I select without payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto_8 |
      