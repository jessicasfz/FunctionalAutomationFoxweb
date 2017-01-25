Feature: COL Scenarios 29116 Partner

  @Done
  Scenario Outline: Place Sale to customer order of foreign currency by providing denominations
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order using QuoteAndView button
    And I enter customer details
    Then I get Order details

    Examples: 
      | AutoID | RowIndex |
      | AUTO06 |        1 |

  Scenario Outline: Verify Change branch link
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order
    And I enter customer details
    Then I get Order details

    Examples: 
      | AutoID | RowIndex |
      | AUTO07 |        1 |

  Scenario Outline: Verify Show currency button of Choose Transaction Type page
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order

    Examples: 
      | AutoID | RowIndex |
      | AUTO08 |        1 |

  @Done
  Scenario Outline: Verify Warning message on denomination page if quantity and total amount is mismatch
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order using QuoteAndView button

    Examples: 
      | AutoID | RowIndex |
      | AUTO09 |        1 |
      | AUTO22 |        2 |

  @Done
  Scenario Outline: Verify CLEAR FIELDS button of Choose Transaction Type page
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order using QuoteAndView button

    Examples: 
      | AutoID | RowIndex |
      | AUTO10 |        1 |

  Scenario Outline: Verify CLEAR FIELDS button of Order Details page
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order

    Examples: 
      | AutoID | RowIndex |
      | AUTO11 |        1 |

 
  Scenario Outline: Verify Show currency button of Order Details page
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order

    Examples: 
      | AutoID | RowIndex |
      | AUTO12 |        1 |

  Scenario Outline: Verify DELETE button of Order Details page
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order

    Examples: 
      | AutoID | RowIndex |
      | AUTO13 |        1 |

  Scenario Outline: Verify EDIT button of Order Details page
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order

    Examples: 
      | AutoID | RowIndex |
      | AUTO14 |        1 |

  Scenario Outline: Verify Show currency button of Denomination Information page
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order using QuoteAndView button

    Examples: 
      | AutoID | RowIndex |
      | AUTO15 |        1 |

  Scenario Outline: Verify CLEAR FIELDS button of Denomination Information page
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order using QuoteAndView button

    Examples: 
      | AutoID | RowIndex |
      | AUTO16 |        1 |

  Scenario Outline: Verify CHANGE ORDER button of Customer Details page
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order
    And I enter customer details

    Examples: 
      | AutoID | RowIndex |
      | AUTO17 |        1 |
	
  Scenario Outline: Verify CANCEL ORDER button of Customer Details page
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order
    And I enter customer details

    Examples: 
      | AutoID | RowIndex |
      | AUTO18 |        1 |

  Scenario Outline: Verify SPECIFY DENOM button of Choose Transaction Type page when edited
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order

    Examples: 
      | AutoID | RowIndex |
      | AUTO19 |        1 |

 
  Scenario Outline: Validate minimum Or maximum foreign amount
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order

    Examples: 
      | AutoID | RowIndex |
      | AUTO20 |        1 |
      | AUTO21 |        2 |

  @TestingE
  Scenario Outline: Place Multi currency Sale to customer order of foreign currency without clicking quote and view button
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order
    And I enter customer details
    Then I get Order details

    Examples: 
      | AutoID | RowIndex |
      | Auto22 |        1 |