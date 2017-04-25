Feature: Test cases for COL application Partner - 129750

  @End2End @129750 
  Scenario Outline: To verify that user is able to place an order for single prepaid card_One card/two card
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    And I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    #includes select country of issue, check date and check checkboxes
    And I update check details
    #handle show currency button popup message here based on product type
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
      | Auto2500001  |
      | Auto25000011  |

  @End2End @129750
  Scenario Outline: To verify that user is able to place an order for MultiCCY prepaid card_One card
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    And I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    #includes select country of issue, check date and check checkboxes
    And I update check details
    #handle show currency button popup message here based on product type
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
      | Auto2500002  |
      | Auto2500003  |
      | Auto2500004  |
