Feature: Pluto

  @plutos
  Scenario Outline: Pluto purchase order
    Given I read Excel data with AutomationID "<AutomationId>"
    When I launch PLUTO application
    And I select order type
    And I enter company name customer type and delivery method
    And I search branch location
    And I select product type and currency
    And I click on Next button
    And I enter Company Additional Info
    And I confirm the order    
    And I authorized the order
    And I get the reference number

    Examples: 
      | AutomationId |
      | AutoChelsea1 |
