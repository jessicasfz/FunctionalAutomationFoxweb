Feature: COL Related Scenarios

  @AutomationID_TC0094 @AutomationID_TC0095  
  Scenario Outline: Verify warning message for less than minium order amts (Account Holder & Non Account Holder)
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    When I select orderType "<OrderType>" Link
    When I select transaction type "<TransactionType>" and customerType "<AccountHolderType>"
    And I select product type "<ProductType>" and currency "<Currency>"
    And I enter amount "<ForeignAmount>"
    And I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    When I dismiss the alert
    And I check Waive Account Holder Fee
    And I select reason for Waiver Fee "<WaiveReason>"
    And I select method Of payment "<MethodOfPayment>"
    And I click complete order button
    Then I should see ErrorMeassage  "<ErrorMessage>"

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | DenomincationMessage                                                                             | WaiveReason           | MethodOfPayment | ErrorMessage                                      |
      |    19333 | Pa$$word1 |     19333 | Onsite    | SALE            | AccountHolder     | Foreign Currencies | CANADA -- CAD |             1 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Management Discretion | Check           | The order values must be between $ 5 and $ 15,000 |
      |    19333 | Pa$$word1 |     19333 | Onsite    | SALE            | NonAccountHolder  | Foreign Currencies | CANADA -- CAD |             1 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Management Discretion | Check           | The order values must be between $ 5 and $ 1,000  |

  @AutomationID_TC0096 @AutomationID_TC0097 
  Scenario Outline: Verify warning message for less than Maximum order amts (Account Holder & Non Account Holder)
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    When I select orderType "<OrderType>" Link
    When I select transaction type "<TransactionType>" and customerType "<AccountHolderType>"
    And I select product type "<ProductType>" and currency "<Currency>"
    And I enter amount "<ForeignAmount>"
    And I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    When I dismiss the alert

    #    And I check Waive Account Holder Fee
    #    And I select reason for Waiver Fee "<WaiveReason>"
    #    And I select method Of payment "<MethodOfPayment>"
    #    And I click complete order button
    #    Then I should see ErrorMeassage  "<ErrorMessage>"
    #    When I accept the alert
    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | DenomincationMessage                                                                             | WaiveReason           | MethodOfPayment | ErrorMessage                                      |
      |    19333 | Pa$$word1 |     19333 | Onsite    | SALE            | AccountHolder     | Foreign Currencies | CANADA -- CAD |             1 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Management Discretion | Check           | The order values must be between $ 5 and $ 15,000 |

  @AutomationID_TC0105 @AutomationID_TC0107 
  Scenario Outline: Verify Show currency button of Choose Transaction Type page
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>" and customerType "<AccountHolderType>"
    And I select product type "<ProductType>" and currency "<Currency>"
    And I click on Show Currency button and Switch to New Window
    Then I Should see Currency Window
    When I click on Close button In Currency Window
    Then I navigate to Transaction Page

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount |
      |    19333 | Pa$$word1 |     19333 | Onsite    | SALE            | NonAccountHolder  | Foreign Currencies | CANADA -- CAD |             5 |

  @AutomationID_TC0110 
  Scenario Outline: Verify QUOTE AND VIEW button of Order Details page
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>" and customerType "<AccountHolderType>"
    And I select product type "<ProductType>" and currency "<Currency>"
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    Then I see Todays Rate

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount |
      |    19333 | Pa$$word1 |     19333 | Onsite    | SALE            | NonAccountHolder  | Foreign Currencies | CANADA -- CAD |             5 |

  @AutomationID_TC0112 
  Scenario Outline: Verify Show currency button of Denomination Information page
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>" and customerType "<AccountHolderType>"
    And I select product type "<ProductType>" and currency "<Currency>"
    And I click on Quote and View button
    Then I should see enterAmountPopupMessage "<EnterAmountPopUp>"
    When I accept the alert
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    Then I see Todays Rate
    When I click on Show Currency button and Switch to New Window
    Then I Should see Currency Window
    When I click on Close button In Currency Window
    Then I navigate to Transaction Page

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | EnterAmountPopUp        |
      |    19333 | Pa$$word1 |     19333 | Onsite    | SALE            | NonAccountHolder  | Foreign Currencies | CANADA -- CAD |             5 | Please enter an amount. |

  @AutomationID_TC0113 @AutomationID_TC0108 
  Scenario Outline: Verify CLEAR FIELDS button of Denomination Information page
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>" and customerType "<AccountHolderType>"
    And I select product type "<ProductType>" and currency "<Currency>"
    And I click on Quote and View button
    Then I should see enterAmountPopupMessage "<EnterAmountPopUp>"
    When I accept the alert
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    Then I see Todays Rate
    When I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    When I dismiss the alert
    Then I navigate to Transaction Page
    When I click on clearFilelds button
    Then I See all input fields as empty Except CCY

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | EnterAmountPopUp        | DenomincationMessage                                                                             |
      |    19333 | Pa$$word1 |     19333 | Onsite    | SALE            | AccountHolder     | Foreign Currencies | EUROPE -- EUR |             5 | Please enter an amount. | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. |

  @AutomationID_TC0106 
  Scenario Outline: Place Sale to customer onsite order of foreign currency as Account Holder
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>" and customerType "<AccountHolderType>"
    And I select product type "<ProductType>" and currency "<Currency>"
    And I click on Quote and View button
    Then I should see enterAmountPopupMessage "<EnterAmountPopUp>"
    When I accept the alert
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    Then I see Todays Rate
    When I click on clearFilelds button
    Then I See all input fields as empty Except CCY

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | EnterAmountPopUp        | DenomincationMessage                                                                             |
      |    19333 | Pa$$word1 |     19333 | Onsite    | SALE            | AccountHolder     | Foreign Currencies | EUROPE -- EUR |             5 | Please enter an amount. | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. |

  @AutomationID_TC0115 
  Scenario Outline: Verify CANCEL ORDER button of Customer Details page
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>" and customerType "<AccountHolderType>"
    And I select product type "<ProductType>" and currency "<Currency>"
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    Then I see Todays Rate
    When I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    When I dismiss the alert
    Then I navigate to Transaction Page
    When I Uncheck Waive Account Holder Fee
    And I select method Of payment "<MethodOfPayment>"
    And I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>", initial "<Initial>", bankID "<BankID>" , customerAccNo "<CustomerAccountNo>" and dataOfBirth "<DateOfBirth>"
    And I enter Customer Details customerAddressOne "<CustomerAddressOne>", customerAddressTwo "<CustomerAddressTwo>", city "<City>", state "<State>", zipcode "<Zipcode>", and country "<Country>"
    And I enter Customer Security Information primaryID "<PrimaryID>", IDNumber "<IDNumber>", countryofIssuance "<CountryofIssuance>", securitystate "<SecurityState>", issueDate "<IssueDate>", and ExpiryDate "<ExpiryDate>"
    And I enter Customer Contact details areaCode "<AreaCode>", phoneNumber "<PhoneNo>", and extension "<Extension>"
    And I click On cancel Order button
    Then I navigate to Home Page

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | EnterAmountPopUp        | DenomincationMessage                                                                             | WaiveReason           | CustomerRadioBtn | FirstName  | LastName   | Initial | CustomerAccountNo | BankID | DateOfBirth | CustomerAddressOne | CustomerAddressTwo | City   | State | Zipcode | Country | PrimaryID              | IDNumber | CountryofIssuance | SecurityState | IssueDate  | ExpiryDate | AreaCode | PhoneNo   | Extension | MethodOfPayment |
      |    19333 | Pa$$word1 |     19333 | Onsite    | SALE            | NonAccountHolder  | Foreign Currencies | CANADA -- CAD |           100 | Please enter an amount. | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Management Discretion | Mr               | Automation | Automation | Y       | NA                | NA     | 10/07/1990  | TestingAddress     | TestingAddress1    | MUMBAI | AK    |  517214 | US      | Driving License Number |     1234 | United States     | AK            | 10/07/1990 | 10/07/2018 |      012 | 123456789 |      1234 | Cash            |

  @AutomationID_TC0114 
  Scenario Outline: Verify CHANGE ORDER button of Customer Details page
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>" and customerType "<AccountHolderType>"
    And I select product type "<ProductType>" and currency "<Currency>"
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    Then I see Todays Rate
    When I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    When I dismiss the alert
    Then I navigate to Transaction Page
    When I Uncheck Waive Account Holder Fee
    And I select method Of payment "<MethodOfPayment>"
    And I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>", initial "<Initial>", bankID "<BankID>" , customerAccNo "<CustomerAccountNo>" and dataOfBirth "<DateOfBirth>"
    And I enter Customer Details customerAddressOne "<CustomerAddressOne>", customerAddressTwo "<CustomerAddressTwo>", city "<City>", state "<State>", zipcode "<Zipcode>", and country "<Country>"
    And I enter Customer Security Information primaryID "<PrimaryID>", IDNumber "<IDNumber>", countryofIssuance "<CountryofIssuance>", securitystate "<SecurityState>", issueDate "<IssueDate>", and ExpiryDate "<ExpiryDate>"
    And I enter Customer Contact details areaCode "<AreaCode>", phoneNumber "<PhoneNo>", and extension "<Extension>"
    And I click On change Order button
    Then I navigate to Transaction Page

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | EnterAmountPopUp        | DenomincationMessage                                                                             | WaiveReason           | CustomerRadioBtn | FirstName  | LastName   | Initial | CustomerAccountNo | BankID | DateOfBirth | CustomerAddressOne | CustomerAddressTwo | City   | State | Zipcode | Country | PrimaryID              | IDNumber | CountryofIssuance | SecurityState | IssueDate  | ExpiryDate | AreaCode | PhoneNo   | Extension | MethodOfPayment |
      |    19333 | Pa$$word1 |     19333 | Onsite    | SALE            | NonAccountHolder  | Foreign Currencies | CANADA -- CAD |           100 | Please enter an amount. | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Management Discretion | Mr               | Automation | Automation | Y       | NA                | NA     | 10/07/1990  | TestingAddress     | TestingAddress1    | MUMBAI | AK    |  517214 | US      | Driving License Number |     1234 | United States     | AK            | 10/07/1990 | 10/07/2018 |      012 | 123456789 |      1234 | Cash            |

  @AutomationID_TC0109 
  Scenario Outline: Verify DELETE button of Order Details page
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>" and customerType "<AccountHolderType>"
    And I select product type "<ProductType>" and currency "<Currency>"
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    Then I see Todays Rate
    When I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    When I dismiss the alert
    Then I navigate to Transaction Page
    When I Uncheck Waive Account Holder Fee
    And I select method Of payment "<MethodOfPayment>"
    And I click On delete button in Transaction Page
    Then I navigate to Transaction Page

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | EnterAmountPopUp        | DenomincationMessage                                                                             | WaiveReason           | MethodOfPayment |
      |    19333 | Pa$$word1 |     19333 | Onsite    | SALE            | NonAccountHolder  | Foreign Currencies | CANADA -- CAD |           100 | Please enter an amount. | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Management Discretion | Cash            |

