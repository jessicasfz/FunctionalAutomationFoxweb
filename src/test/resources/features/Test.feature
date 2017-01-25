Feature: Smoke Feature File
	
	@SmoTest
  Scenario Outline: Place Sale to customer order of foreign currency without clicking quote and view button
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order
    And I enter customer details
    Then I get Order details

    Examples: 
      | AutoID | RowIndex |
      | Auto03 |        1 |

  @Smoke
  Scenario Outline: Place Sale to customer order of foreign currency using quote and view button
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order using QuoteAndView button
    And I enter customer details
    Then I get Order details

    Examples: 
      | AutoID | RowIndex |
      | AUTO02 |        1 |

  @testFramess
  Scenario Outline: Place Sale to customer order of foreign Drafts without clicking quote and view button
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order
    And I enter customer details
    Then I get Order details

    Examples: 
      | AutoID | RowIndex |
      | AUTO01 |        1 |

  Scenario Outline: Verify NEXT ORDER button of confirmation page
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order
    And I enter customer details
    Then I get Order details

    Examples: 
      | AutoID | RowIndex |
      | AUTO04 |        1 |

  @testFramess
  Scenario Outline: Place Sale to customer order of foreign currency using quote and view button
    Given I read Excel data with AutomationID "<AutoID>" for iteration "<RowIndex>"
    And I login to COL application
    When I Place Sale order using QuoteAndView button
    And I enter customer details
    Then I get Order details

    Examples: 
      | AutoID | RowIndex |
      | Auto05 |        1 |
