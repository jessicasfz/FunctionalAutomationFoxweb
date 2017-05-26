Feature: Pluto

  @pluto
  Scenario Outline: Place Order For Partner in Pluto Application
    Given I read data from Excel with AutomationID "<AutomationId>"
    When I launch PLUTO application
    And I select order type
    And I enter company name customer type and delivery method
    And I search branch location
    And I select product type and currency
    And I enter foreign amount and select denomination
    And I enter beneficiary detail
    And I click on Calculate
    And I enter more currencies select denomination and click on calculate
    And I click on Next button
    And I enter customer detail
    And I click on Next button
    And I enter Comapny Additional Info
    And I confirm the order
    And I get the reference number
    And I authorized the order
    Then I verify order detail

    Examples: 
      | AutomationId |
      |         1234 |
