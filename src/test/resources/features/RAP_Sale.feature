Feature: Process Sale Order in RAP

  @RAP-Sale @Regression @boths
  Scenario Outline: Process Sale order
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch RAPSale application
    When I perform order extraction
    And I schedule order
    And I perform Manual Batch Confirmation
    And I close sale RAP
    Then I perform AutoPR process to complete order fullfilment

    Examples: 
      | AutomationId |
      | Auto2222221  |

  # -----------------------------------Multiline New
  @FinalTest @RefDataTest @TestOnlySale
  Scenario: Process Multiple Sale orders
    And I launch RAPSale application
    #When I perform order extraction
    And I schedule order
    And I perform Manual Batch Confirmation
    And I close sale RAP
    Then I perform AutoPR process to complete order fullfilment

  @FinalTest @RefDataTest @TestOnlySale
  Scenario Outline: Check and update order status of sale orders
    Given I read Excel data with AutomationID "<AutomationId>"
    When I check and update order status

    Examples: 
      | AutomationId   |
      | Auto22214416_1 |
      | Auto22214426_1 |
      | Auto22214437_1 |
      | Auto881247_1   |
      | Auto22214641_1 |
      | Auto22214730_1 |
      | Auto13571_1    |
      | Auto16974_1    |

  #| Auto11227_1    |
  #| Auto11227_2    |
  # -----------------------------------Multiline
  @RAP-SaleMulti @Regression
  Scenario: Extract Multiple Sale orders
    When I launch RAPSale application
    When I perform order extraction
    And I close sale RAP

  @RAP-SaleMulti @Regression
  Scenario Outline: Schedule and perform batch confirmation for multiple Sale orders
    Given I read Excel data with AutomationID "<AutomationId>"
    When I launch RAPSale application
    And I schedule order
    And I perform Manual Batch Confirmation
    And I close sale RAP
    Then I perform AutoPR process to complete order fullfilment
    And Order gets processed

    Examples: 
      | AutomationId |
      | Auto113850_2 |
