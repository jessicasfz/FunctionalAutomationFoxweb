Feature: Test cases for COL application Partner - 16974

  @End2End @16974 
  Scenario Outline: Place Sale to customer online order of foreign currency without denom and changing default branch | Place Purchase from customer online order of foreign currency
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
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
      | Auto1800001  |
      | Auto1800002  |

@End2End @16974
  Scenario Outline: Place Sale to Travelex Multi CCY online order of foreign currency without denom | Place Purchase from Travelex Multi CCY Online order of foreign currency without denom
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
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1800003  |
      | Auto1800004  |

  @End2End @16974
  Scenario Outline: Place Sale to customer online order of foreign currency with denom
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I add denomination on add order
    #And I do not waive fee
    Then I validate the total amount
    And I select payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1800005  |
      
  @End2End @16974
  Scenario Outline: Place Sale to customer online order of foreign currency without denom
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    #And I do not waive fee
    Then I validate the total amount
    And I select payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1800006  |
      
@End2End @16974 
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
    #And I do not waive fee
    Then I validate the total amount
    And I select payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1800007  |
      