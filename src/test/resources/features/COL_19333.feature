Feature: Test cases for COL application Partner - 19333

  @End2End @TP
  Scenario Outline: Place Sale to customer onsite order of foreign currency as Account Holder
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    And I waive fee
    Then I validate the total amount
    And I select payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      #| Auto1000001  |
      #| Auto1000005  |
      | Auto1000007  |

  @End2End
  Scenario Outline: Place Sale to customer onsite order of foreign currency for for Non - Account Holder
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I click on Quote and view before entering amount
    And I click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    And I do not waive fee
    And I select payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1000002  |
      | Auto1000006  |

  @End2End
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
      | Auto1000003  |

  @End2End
  Scenario Outline: Place Purchase from Customer onsite order of foreign currency for for Non - Account Holder
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I click on Quote and view before entering amount
    And I click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not waive fee
    And I select payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details

    Examples: 
      | AutomationId |
      | Auto1000008  |

  @End2End
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
      | Auto1000009  |

  @End2End
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
      | Auto1000010  |

  @End2End
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
      | Auto1000011  |

  @End2End
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
      | Auto1000004  |

  @End2End
  Scenario Outline: Verify Clear Fields button
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    And I do not waive fee
    And I click on Clear Fields button
    Then I verify page for Clear Fields

    Examples: 
      | AutomationId |
      | Auto1000022  |

	@End2End
  Scenario Outline: Verify Delete Order button
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    And I click on Delete order button
    Then I verify page for Delete Order

    Examples: 
      | AutomationId |
      | Auto1000023  |
	
	@End2End
  Scenario Outline: Verify Change Order button
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    And I waive fee
    Then I validate the total amount
    And I select payment method to confirm order
    And I enter customer details
    And I click on Change order button
    Then I verify page for Change Order

    Examples: 
      | AutomationId |
      | Auto1000025  |
	
	@End2End
  Scenario Outline: Verify Cancel Order button
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    And I waive fee
    And I select payment method to confirm order
    And I enter customer details
    And I click on Cancel Order button
    Then I verify page for Cancel Order

    Examples: 
      | AutomationId |
      | Auto1000026  |
	
	@End2End
  Scenario Outline: Verify Next Order button
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    And I waive fee
    Then I validate the total amount
    And I select payment method to confirm order
    And I enter customer details
    And I complete the order
    Then I get order confirmation no
    And I verify order details
    And I click on Next order button
    Then I verify page for Next Order

    Examples: 
      | AutomationId |
      | Auto1000029  |

  Scenario Outline: Verify rate sheet link
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I click on Rate Sheet link
    Then I verify details

    Examples: 
      | AutomationId |
      | Auto1000012  |

  Scenario Outline: Verify rate sheet onsite link
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I click on Rate Sheet onsite link
    Then I verify details

    Examples: 
      | AutomationId |
      | Auto1000013  |
	
  Scenario Outline: Verify warning message for less than minimum order amount as Account Holder
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    And I waive fee
    And I select payment method to confirm order
    Then I get minimumAmt error message

    Examples: 
      | AutomationId |
      | Auto1000018  |
	
	@End2End
  Scenario Outline: Verify warning message for greater than maximum order amount as Account Holder
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    Then I get maximumAmt error message

    Examples: 
      | AutomationId |
      | Auto1000019  |

  Scenario Outline: Verify warning message for less than minium order amount as Non Account Holder
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    And I waive fee
    And I select payment method to confirm order
    Then I get minimumAmt error message

    Examples: 
      | AutomationId |
      | Auto1000020  |

  @End2End
  Scenario Outline: Verify warning message for greater than maximum order amount as Non Account Holder
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    Then I get maximumAmt error message

    Examples: 
      | AutomationId |
      | Auto1000021  |
	
	@End2End
  Scenario Outline: Verify Edit button of Order Details page
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I create order of transaction for customer
    And I select currency of product
    And I do not click on Quote and view after entering amount
    And I click on AddToOrder
    And I do not add denomination on add order
    And I click on Edit Order button
    Then I verify page for Edit Order

    Examples: 
      | AutomationId |
      | Auto1000024  |

  Scenario Outline: Verify View Inventory link
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I click on View Inventory link
    Then I verify details

    Examples: 
      | AutomationId |
      | Auto1000027  |

  Scenario Outline: Verify View Receive Inventory link
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch COL application
    When I click on Receive Inventory link
    Then I verify details

    Examples: 
      | AutomationId |
      | Auto1000028  |
