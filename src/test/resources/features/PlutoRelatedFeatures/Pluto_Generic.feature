Feature: Pluto

  @PlutoPurchase
  Scenario Outline: Pluto purchase order
    Given I read Excel data with AutomationID "<AutomationId>"
    When I launch PLUTO application
    And I select order type
    And I select customer type and delivery method
    And I search branch location
    And I select product type and currency
    And I enter Company Additional Info
    And I confirm the order
    And I get the reference number

    Examples: 
      | AutomationId |
      | AutoChelsea4 |

  @PlutoSale
  Scenario Outline: Pluto Sale order
    Given I read Excel data with AutomationID "<AutomationId>"
    When I launch PLUTO application
    And I select order type
    And I select customer type and delivery method
    And I search branch location
    And I select product type and currency
    And I enter customer detail
    And I enter Company Additional Info
    And I confirm the order
    And I get the reference number

    Examples: 
      | AutomationId |
      | AutoChelsea3 |
