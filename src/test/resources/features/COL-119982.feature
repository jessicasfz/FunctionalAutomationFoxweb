Feature: Test cases for COL application Partner - 119982

  @End2End
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
      | Auto1200001  |

  @End2End
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
      | Auto1200005  |
      | Auto1200003  |
      | Auto1200004  |
      | Auto1200002  |

  @End2End @Regression
  Scenario Outline: Place Multi CCY banknote sale order by specifying denoms and by changing default branch address
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    And I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    #includes select country of issue, check date and check checkboxes
    #And I update check details
    #handle show currency button popup message here based on product type
    And I click on AddToOrder
    And I do not add denomination on add order
    And I add more currency do not add denomination on add order click On currency view
    Then I validate the total amount
    When I select without payment method to confirm order
    And I enter customer details
    And I change branch details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1200007  |

  @End2End @SnT
  Scenario Outline: To verify that user is able to place an Foreign check order_Multi CCY
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    And I create order of transaction for customer
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
      | Auto1200002  |

  @End2End
  Scenario Outline: To verify that user is able to place Sale to Customer order of Foreign Drafts
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    And I create order of transaction for customer
    And I select currency of product
    And I click on Quote and view after entering amount
    And I enter beneficiary details
    And I click on AddToOrder
    Then I validate the total amount
    When I select without payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1200009  |
