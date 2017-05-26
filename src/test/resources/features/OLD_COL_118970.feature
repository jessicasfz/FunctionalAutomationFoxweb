Feature: Test cases for COL application Partner - 118970

  @End2End @14
  Scenario Outline: Place Sale to customer onsite order of foreign currency as Account Holder
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    #And I do not waive fee
    Then I validate the total amount
    And I select without payment method to confirm order
    And I enter customer details
    And I change branch details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1400001  |
      | Auto1400005  |
      | Auto1400007  |

  @End2End @14
  Scenario Outline: Place Sale to Travelex Wholesale order of foreign currency
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I click on Quote and view after entering amount
    And I click on AddToOrder
    And I select payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1400003  |

  @End2End @14
  Scenario Outline: Place Purchase from Travelex Wholesale order of foreign currency
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    And I select delivery time to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1400009  |

  @End2End @14
  Scenario Outline: Place Purchase from Travelex Wholesale order of foreign currency providing denoms
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I click on Quote and view after entering amount
    And I click on AddToOrder
    And I add denomination on add order
    And I select delivery time to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1400010  |

  @End2End @14
  Scenario Outline: Place Purchase from Travelex Multi CCY Wholesale order of foreign currency
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    And I add more currency do not add denomination on add order do not click On currency view
    And I select delivery time to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1400011  |

  @End2End @14
  Scenario Outline: Place Sale to Travelex Multi CCY Wholesale order of foreign currency
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I click on Quote and view after entering amount
    And I click on AddToOrder
    And I add more currency do not add denomination on add order click On currency view
    And I select payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1400004  |
