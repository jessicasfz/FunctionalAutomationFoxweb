Feature: Process purcase order in RAP

  @RAP-PurchaseTestsingle @Regression @both
  Scenario Outline: Process Purchase order
    Given I read Excel data with AutomationID "<AutomationId>"
    And I launch the RAP application
    And I import purchase orders
    When I process purchase order
    #And I retrieve the orders using bag id and refrence code
    When I perform Amalgamation process to confirm order
    Then I update inventory
    And I invoke Sale RAP to import inventory

    #When I navigate to Update Inventory on Supervsor menu
    #And I update the inventory
    Examples: 
      | AutomationId |
      | Auto0000013  |
      | Auto0000012  |

  @RAP-PurchaseTestMulti @Regression @RefDataTest
  Scenario Outline: Retrive data for multiple Purchase orders
    Given I read Excel data with AutomationID "<AutomationId>"
    And I retrieve the orders and update in DataSheet

    Examples: 
      | AutomationId   |
      | Auto22214407_3 |
      | Auto22214756_3 |
      | Auto22224265_3 |
      | Auto226249_3   |
      | Auto226249_5   |
      | Auto226249_6   |
      | Auto226447_3   |
      | Auto226447_5   |
      | Auto226447_6   |
      | Auto226465_3   |
      | Auto226465_5   |
      | Auto226465_6   |
      | Auto226468_3   |
      | Auto226468_5   |
      | Auto226468_6   |
      | Auto226564_3   |
      | Auto226564_5   |
      | Auto226564_6   |
      | Auto33321012_3 |
      | Auto4417008_3  |
      | Auto4417325_3  |
      | Auto4423788_3  |
      | Auto8817713_3  |

  @RAP-PurchaseTestMulti @Regression @RefDataTest
  Scenario: Import multiple Purchase orders
    When I launch and Import RAP to import multiple orders

  @RAP-PurchaseTestMulti @Regression @RefDataTest
  Scenario Outline: Process and Amalgamate multiple purchase orders
    Given I read Excel data with AutomationID "<AutomationId>"
    And I process and amalgamate order
    Then I update inventory
    And I invoke Sale RAP to import inventory

    Examples: 
      | AutomationId   |
      | Auto22214407_3 |
      | Auto22214756_3 |
      | Auto22224265_3 |
      | Auto226249_3   |
      | Auto226249_5   |
      | Auto226249_6   |
      | Auto226447_3   |
      | Auto226447_5   |
      | Auto226447_6   |
      | Auto226465_3   |
      | Auto226465_5   |
      | Auto226465_6   |
      | Auto226468_3   |
      | Auto226468_5   |
      | Auto226468_6   |
      | Auto226564_3   |
      | Auto226564_5   |
      | Auto226564_6   |
      | Auto33321012_3 |
      | Auto4417008_3  |
      | Auto4417325_3  |
      | Auto4423788_3  |
      | Auto8817713_3  |
