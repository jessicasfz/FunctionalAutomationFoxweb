Feature: COL-91084
       Test cases for COL application for Partner- 91084

  @mytag @SnT
  Scenario Outline: To verify that user is able to place an order for single prepaid card_One card
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    Then I validate the total amount
    When I select payment method to confirm order
    #And I select checkbox for secondary card
    And I enter valid Primary cardholder details
    And I get KYC success message on submitting the details
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1100001  |

  @mytag
  Scenario Outline: To verify that user is able to place an order for single prepaid card_MultiCCY
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I add more currency do not add denomination on add order click On currency view
    Then I validate the total amount
    When I select payment method to confirm order
    #And I select checkbox for secondary card
    And I enter valid Primary cardholder details
    And I get KYC success message on submitting the details
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1100002  |

  @mytag
  Scenario Outline: To verify that user is able to place an order for MultiCCY prepaid card_One card
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I select branch and branch location
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    #handle show currency button popup message here based on product type
    And I do not add denomination on add order
    And I add more currency
    Then I validate the total amount
    And I should see the available delivery options
    And I validate the total order amount
    When I select payment method to confirm order
    #pass value true/ false from excel for clicking on this checkbox
    And I select the checkbox for secondary card
    And I enter valid Primary card holder details
    And I get KYC success message on submitting the details
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1100002  |
      | Auto1100004  |
      | Auto1100005  |
      | Auto1100006  |
