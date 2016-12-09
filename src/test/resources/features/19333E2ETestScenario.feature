Feature: End2End Scenarios [COL + RAP]

  @AutomationID_TC0128 @Regression
  Scenario Outline: Place Purchase from Customer onsite order of foreign currency for for Non - Account Holder
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
    Then I should see currencyViewRequiredPopup "<CurrencyViewRequired>"
    When I accept the alert
    And I click on Show Currency button and Switch to New Window
    Then I Should see Currency Window
    When I click on Close button In Currency Window
    Then I navigate to Transaction Page
    When I click Add To Order button
    And I Uncheck Waive Account Holder Fee
    And I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>", initial "<Initial>", bankID "<BankID>" , customerAccNo "<CustomerAccountNo>" and dataOfBirth "<DateOfBirth>"
    And I enter Customer Details customerAddressOne "<CustomerAddressOne>", customerAddressTwo "<CustomerAddressTwo>", city "<City>", state "<State>", zipcode "<Zipcode>", and country "<Country>"
    And I enter Customer Security Information primaryID "<PrimaryID>", IDNumber "<IDNumber>", countryofIssuance "<CountryofIssuance>", securitystate "<SecurityState>", issueDate "<IssueDate>", and ExpiryDate "<ExpiryDate>"
    And I enter Customer Contact details areaCode "<AreaCode>", phoneNumber "<PhoneNo>", and extension "<Extension>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | EnterAmountPopUp        | CurrencyViewRequired                                                                                                                            | CustomerRadioBtn | FirstName  | LastName   | Initial | BankID | CustomerAccountNo | DateOfBirth | CustomerAddressOne | CustomerAddressTwo | City   | State | Zipcode | Country | PrimaryID              | IDNumber | CountryofIssuance | SecurityState | IssueDate  | ExpiryDate | AreaCode | PhoneNo   | Extension |
      |    19333 | Pa$$word1 |     19333 | Onsite    | PURCHASE        | NonAccountHolder  | Foreign Currencies | CANADA -- CAD |           100 | Please enter an amount. | Viewing Currency is required before proceeding with transaction. Click the Show Currency button to view currency and continue with transaction. | Mr               | Automation | Automation | Y       | NA     | NA                | 10/07/1990  | TestingAddress     | TestingAddress1    | MUMBAI | AK    |  517214 | US      | Driving License Number |     1234 | United States     | AK            | 10/07/1990 | 10/07/2018 |      012 | 123456789 |      1234 |

  @AutomationID_TC0129 @Regression @SmokeTestDemo
  Scenario Outline: Place Purchase from customer onsite order of foreign currency as Account Holder
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
    Then I should see currencyViewRequiredPopup "<CurrencyViewRequired>"
    When I accept the alert
    And I click on Show Currency button and Switch to New Window
    Then I Should see Currency Window
    When I click on Close button In Currency Window
    Then I navigate to Transaction Page
    When I click Add To Order button
    And I check Waive Account Holder Fee
    And I select reason for Waiver Fee "<WaiveReason>"
    And I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>", initial "<Initial>", bankID "<BankID>" , customerAccNo "<CustomerAccountNo>" and dataOfBirth "<DateOfBirth>"
    And I enter Customer Security Information primaryID "<PrimaryID>", IDNumber "<IDNumber>", countryofIssuance "<CountryofIssuance>", securitystate "<SecurityState>", issueDate "<IssueDate>", and ExpiryDate "<ExpiryDate>"
    And I enter Customer Contact details areaCode "<AreaCode>", phoneNumber "<PhoneNo>", and extension "<Extension>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | EnterAmountPopUp        | CurrencyViewRequired                                                                                                                            | WaiveReason           | CustomerRadioBtn | FirstName  | LastName   | Initial | CustomerAccountNo | BankID                       | DateOfBirth | CustomerAddressOne | CustomerAddressTwo | City   | State | Zipcode | Country | PrimaryID              | IDNumber | CountryofIssuance | SecurityState | IssueDate  | ExpiryDate | AreaCode | PhoneNo   | Extension |
      |    19333 | Pa$$word1 |     19333 | Onsite    | PURCHASE        | AccountHolder     | Foreign Currencies | EUROPE -- EUR |             5 | Please enter an amount. | Viewing Currency is required before proceeding with transaction. Click the Show Currency button to view currency and continue with transaction. | Management Discretion | Mr               | Automation | Automation | Y       |           1234567 | First National Bank of Omaha | NA          | TestingAddress     | TestingAddress1    | MUMBAI | AK    |  517214 | US      | Driving License Number |     1234 | United States     | AK            | 10/07/1990 | 10/07/2018 |      012 | 123456789 |      1234 |

  @AutomationID_TC0121 @Regression
  Scenario Outline: Place Sale to customer Online order of foreign currency for  Non - Account Holder
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
    When I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    When I dismiss the alert
    And I Uncheck Waive Account Holder Fee
    And I select method Of payment "<MethodOfPayment>"
    And I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>", initial "<Initial>", bankID "<BankID>" , customerAccNo "<CustomerAccountNo>" and dataOfBirth "<DateOfBirth>"
    And I enter Customer Details customerAddressOne "<CustomerAddressOne>", customerAddressTwo "<CustomerAddressTwo>", city "<City>", state "<State>", zipcode "<Zipcode>", and country "<Country>"
    And I enter Customer Security Information primaryID "<PrimaryID>", IDNumber "<IDNumber>", countryofIssuance "<CountryofIssuance>", securitystate "<SecurityState>", issueDate "<IssueDate>", and ExpiryDate "<ExpiryDate>"
    And I enter Customer Contact details areaCode "<AreaCode>", phoneNumber "<PhoneNo>", and extension "<Extension>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | EnterAmountPopUp        | DenomincationMessage                                                                             | CustomerRadioBtn | FirstName  | LastName   | Initial | BankID | CustomerAccountNo | DateOfBirth | CustomerAddressOne | CustomerAddressTwo | City   | State | Zipcode | Country | PrimaryID              | IDNumber | CountryofIssuance | SecurityState | IssueDate  | ExpiryDate | AreaCode | PhoneNo   | Extension | MethodOfPayment |
      |    19333 | Pa$$word1 |     19333 | Online    | SALE            | NonAccountHolder  | Foreign Currencies | CANADA -- CAD |             5 | Please enter an amount. | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Mr               | Automation | Automation | Y       | NA     | NA                | 10/07/1990  | TestingAddress     | TestingAddress1    | MUMBAI | AK    |  517214 | US      | Driving License Number |     1234 | United States     | AK            | 10/07/1990 | 10/07/2018 |      012 | 123456789 |      1234 | Cash            |

  @AutomationID_TC0120 @Regression
  Scenario Outline: Place Sale to customer Online order of foreign currency for Account Holder
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
    When I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    When I dismiss the alert
    And I check Waive Account Holder Fee
    And I select reason for Waiver Fee "<WaiveReason>"
    And I select method Of payment "<MethodOfPayment>"
    And I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>", initial "<Initial>", bankID "<BankID>" , customerAccNo "<CustomerAccountNo>" and dataOfBirth "<DateOfBirth>"
    And I enter Customer Security Information primaryID "<PrimaryID>", IDNumber "<IDNumber>", countryofIssuance "<CountryofIssuance>", securitystate "<SecurityState>", issueDate "<IssueDate>", and ExpiryDate "<ExpiryDate>"
    And I enter Customer Contact details areaCode "<AreaCode>", phoneNumber "<PhoneNo>", and extension "<Extension>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | EnterAmountPopUp        | DenomincationMessage                                                                             | CustomerRadioBtn | FirstName  | LastName   | Initial | BankID                       | CustomerAccountNo | DateOfBirth | CustomerAddressOne | CustomerAddressTwo | City | State | Zipcode | Country | PrimaryID              | IDNumber | CountryofIssuance | SecurityState | IssueDate  | ExpiryDate | AreaCode | PhoneNo   | Extension | MethodOfPayment       | WaiveReason           |
      |    19333 | Pa$$word1 |     19333 | Online    | SALE            | AccountHolder     | Foreign Currencies | EUROPE -- EUR |             5 | Please enter an amount. | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Mr               | Automation | Automation | Y       | First National Bank of Omaha |         123456789 | NA          | NA                 | NA                 | NA   | NA    | NA      | NA      | Driving License Number |     1234 | United States     | AK            | 10/07/1990 | 10/07/2018 |      012 | 123456789 |      1234 | Bank Debit of Account | Management Discretion |

  @AutomationID_TC0092 @Regression
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
    When I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    When I dismiss the alert
    And I click on Show Currency button and Switch to New Window
    Then I Should see Currency Window
    When I click on Close button In Currency Window
    Then I navigate to Transaction Page
    When I check Waive Account Holder Fee
    And I select reason for Waiver Fee "<WaiveReason>"
    And I select method Of payment "<MethodOfPayment>"
    And I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>", initial "<Initial>", bankID "<BankID>" , customerAccNo "<CustomerAccountNo>" and dataOfBirth "<DateOfBirth>"
    And I enter Customer Security Information primaryID "<PrimaryID>", IDNumber "<IDNumber>", countryofIssuance "<CountryofIssuance>", securitystate "<SecurityState>", issueDate "<IssueDate>", and ExpiryDate "<ExpiryDate>"
    And I enter Customer Contact details areaCode "<AreaCode>", phoneNumber "<PhoneNo>", and extension "<Extension>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | EnterAmountPopUp        | DenomincationMessage                                                                             | WaiveReason           | CustomerRadioBtn | FirstName  | LastName   | Initial | CustomerAccountNo | BankID                       | DateOfBirth | CustomerAddressOne | CustomerAddressTwo | City   | State | Zipcode | Country | PrimaryID              | IDNumber | CountryofIssuance | SecurityState | IssueDate  | ExpiryDate | AreaCode | PhoneNo   | Extension | MethodOfPayment |
      |    19333 | Pa$$word1 |     19333 | Onsite    | SALE            | AccountHolder     | Foreign Currencies | EUROPE -- EUR |             5 | Please enter an amount. | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Management Discretion | Mr               | Automation | Automation | Y       |           1234567 | First National Bank of Omaha | NA          | TestingAddress     | TestingAddress1    | MUMBAI | AK    |  517214 | US      | Driving License Number |     1234 | United States     | AK            | 10/07/1990 | 10/07/2018 |      012 | 123456789 |      1234 | Cash            |

  @AutomationID_TC0093 @Regression
  Scenario Outline: Place Sale to customer onsite order of foreign currency for Non - Account Holder
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
    And I click on Show Currency button and Switch to New Window
    Then I Should see Currency Window
    When I click on Close button In Currency Window
    Then I navigate to Transaction Page
    When I Uncheck Waive Account Holder Fee
    And I select method Of payment "<MethodOfPayment>"
    And I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>", initial "<Initial>", bankID "<BankID>" , customerAccNo "<CustomerAccountNo>" and dataOfBirth "<DateOfBirth>"
    And I enter Customer Details customerAddressOne "<CustomerAddressOne>", customerAddressTwo "<CustomerAddressTwo>", city "<City>", state "<State>", zipcode "<Zipcode>", and country "<Country>"
    And I enter Customer Security Information primaryID "<PrimaryID>", IDNumber "<IDNumber>", countryofIssuance "<CountryofIssuance>", securitystate "<SecurityState>", issueDate "<IssueDate>", and ExpiryDate "<ExpiryDate>"
    And I enter Customer Contact details areaCode "<AreaCode>", phoneNumber "<PhoneNo>", and extension "<Extension>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | EnterAmountPopUp        | DenomincationMessage                                                                             | WaiveReason           | CustomerRadioBtn | FirstName  | LastName   | Initial | CustomerAccountNo | BankID | DateOfBirth | CustomerAddressOne | CustomerAddressTwo | City   | State | Zipcode | Country | PrimaryID              | IDNumber | CountryofIssuance | SecurityState | IssueDate  | ExpiryDate | AreaCode | PhoneNo   | Extension | MethodOfPayment |
      |    19333 | Pa$$word1 |     19333 | Onsite    | SALE            | NonAccountHolder  | Foreign Currencies | CANADA -- CAD |           100 | Please enter an amount. | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Management Discretion | Mr               | Automation | Automation | Y       | NA                | NA     | 10/07/1990  | TestingAddress     | TestingAddress1    | MUMBAI | AK    |  517214 | US      | Driving License Number |     1234 | United States     | AK            | 10/07/1990 | 10/07/2018 |      012 | 123456789 |      1234 | Cash            |

  @AutomationID_TC0098 @Regression
  Scenario Outline: Verify Method of Payment drop down for Account Holder
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
    And I click on Show Currency button and Switch to New Window
    Then I Should see Currency Window
    When I click on Close button In Currency Window
    Then I navigate to Transaction Page
    When I check Waive Account Holder Fee
    And I select reason for Waiver Fee "<WaiveReason>"
    And I select method Of payment "<MethodOfPayment>"
    And I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>", initial "<Initial>", bankID "<BankID>" , customerAccNo "<CustomerAccountNo>" and dataOfBirth "<DateOfBirth>"
    And I enter Customer Security Information primaryID "<PrimaryID>", IDNumber "<IDNumber>", countryofIssuance "<CountryofIssuance>", securitystate "<SecurityState>", issueDate "<IssueDate>", and ExpiryDate "<ExpiryDate>"
    And I enter Customer Contact details areaCode "<AreaCode>", phoneNumber "<PhoneNo>", and extension "<Extension>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | EnterAmountPopUp        | DenomincationMessage                                                                             | WaiveReason           | CustomerRadioBtn | FirstName  | LastName   | Initial | CustomerAccountNo | BankID                       | DateOfBirth | CustomerAddressOne | CustomerAddressTwo | City   | State | Zipcode | Country | PrimaryID              | IDNumber | CountryofIssuance | SecurityState | IssueDate  | ExpiryDate | AreaCode | PhoneNo   | Extension | MethodOfPayment |
      |    19333 | Pa$$word1 |     19333 | Onsite    | SALE            | AccountHolder     | Foreign Currencies | EUROPE -- EUR |             5 | Please enter an amount. | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Management Discretion | Mr               | Automation | Automation | Y       |           1234567 | First National Bank of Omaha | NA          | TestingAddress     | TestingAddress1    | MUMBAI | AK    |  517214 | US      | Driving License Number |     1234 | United States     | AK            | 10/07/1990 | 10/07/2018 |      012 | 123456789 |      1234 | Check           |

  @AutomationID_TC0099 @Regression
  Scenario Outline: Verify Method of Payment drop down for Non Account Holder
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
    And I click on Show Currency button and Switch to New Window
    Then I Should see Currency Window
    When I click on Close button In Currency Window
    Then I navigate to Transaction Page
    When I Uncheck Waive Account Holder Fee
    And I select method Of payment "<MethodOfPayment>"
    And I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>", initial "<Initial>", bankID "<BankID>" , customerAccNo "<CustomerAccountNo>" and dataOfBirth "<DateOfBirth>"
    And I enter Customer Details customerAddressOne "<CustomerAddressOne>", customerAddressTwo "<CustomerAddressTwo>", city "<City>", state "<State>", zipcode "<Zipcode>", and country "<Country>"
    And I enter Customer Security Information primaryID "<PrimaryID>", IDNumber "<IDNumber>", countryofIssuance "<CountryofIssuance>", securitystate "<SecurityState>", issueDate "<IssueDate>", and ExpiryDate "<ExpiryDate>"
    And I enter Customer Contact details areaCode "<AreaCode>", phoneNumber "<PhoneNo>", and extension "<Extension>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | EnterAmountPopUp        | DenomincationMessage                                                                             | WaiveReason           | CustomerRadioBtn | FirstName  | LastName   | Initial | CustomerAccountNo | BankID | DateOfBirth | CustomerAddressOne | CustomerAddressTwo | City   | State | Zipcode | Country | PrimaryID              | IDNumber | CountryofIssuance | SecurityState | IssueDate  | ExpiryDate | AreaCode | PhoneNo   | Extension | MethodOfPayment |
      |    19333 | Pa$$word1 |     19333 | Onsite    | SALE            | NonAccountHolder  | Foreign Currencies | CANADA -- CAD |           100 | Please enter an amount. | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Management Discretion | Mr               | Automation | Automation | Y       | NA                | NA     | 10/07/1990  | TestingAddress     | TestingAddress1    | MUMBAI | AK    |  517214 | US      | Driving License Number |     1234 | United States     | AK            | 10/07/1990 | 10/07/2018 |      012 | 123456789 |      1234 | Cash            |

  @AutomationID_TC0117 @Regression
  Scenario Outline: Verify NEXT ORDER button of confirmation page
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
    And I click on Show Currency button and Switch to New Window
    Then I Should see Currency Window
    When I click on Close button In Currency Window
    Then I navigate to Transaction Page
    When I check Waive Account Holder Fee
    And I select reason for Waiver Fee "<WaiveReason>"
    And I select method Of payment "<MethodOfPayment>"
    And I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>", initial "<Initial>", bankID "<BankID>" , customerAccNo "<CustomerAccountNo>" and dataOfBirth "<DateOfBirth>"
    And I enter Customer Security Information primaryID "<PrimaryID>", IDNumber "<IDNumber>", countryofIssuance "<CountryofIssuance>", securitystate "<SecurityState>", issueDate "<IssueDate>", and ExpiryDate "<ExpiryDate>"
    And I enter Customer Contact details areaCode "<AreaCode>", phoneNumber "<PhoneNo>", and extension "<Extension>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Next button on Transaction Page

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | AccountHolderType | ProductType        | Currency      | ForeignAmount | EnterAmountPopUp        | DenomincationMessage                                                                             | WaiveReason           | CustomerRadioBtn | FirstName  | LastName   | Initial | CustomerAccountNo | BankID                       | DateOfBirth | CustomerAddressOne | CustomerAddressTwo | City   | State | Zipcode | Country | PrimaryID              | IDNumber | CountryofIssuance | SecurityState | IssueDate  | ExpiryDate | AreaCode | PhoneNo   | Extension | MethodOfPayment |
      |    19333 | Pa$$word1 |     19333 | Onsite    | SALE            | AccountHolder     | Foreign Currencies | EUROPE -- EUR |             5 | Please enter an amount. | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Management Discretion | Mr               | Automation | Automation | Y       |           1234567 | First National Bank of Omaha | NA          | TestingAddress     | TestingAddress1    | MUMBAI | AK    |  517214 | US      | Driving License Number |     1234 | United States     | AK            | 10/07/1990 | 10/07/2018 |      012 | 123456789 |      1234 | Check           |

  @AutomationID_TC0100 @Regression
  Scenario Outline: Place Purchase from Travelex Wholesale order of foreign currency
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>", product type "<ProductType>" and currency "<Currency>"
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    Then I see Todays Rate
    When I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    When I dismiss the alert
    And I click on Show Currency button and Switch to New Window
    Then I Should see Currency Window
    When I click on Close button In Currency Window
    Then I navigate to Transaction Page
    When I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer Contact details areaCode "<AreaCode>", phoneNumber "<PhoneNo>", and extension "<Extension>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | DenomincationMessage                                                                             | AreaCode | PhoneNo | Extension |
      |    19333 | Pa$$word1 |     19333 | WHOLESALE | PURCHASE        | Foreign Currencies | CANADA -- CAD |           100 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. |      012 | 1234589 | NA        |

  @AutomationID_TC0103  @Regression
  Scenario Outline: Place Sale To Travelex Wholesale order of foreign currency
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>", product type "<ProductType>" and currency "<Currency>"
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    Then I see Todays Rate
    When I click Add To Order button
    Then I should see currencyViewRequiredPopup "<CurrencyViewRequired>"
    When I dismiss the alert
    And I click on Show Currency button and Switch to New Window
    Then I Should see Currency Window
    When I click on Close button In Currency Window
    Then I navigate to Transaction Page
    When I click Add To Order button
    And I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer Contact details areaCode "<AreaCode>", phoneNumber "<PhoneNo>", and extension "<Extension>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CurrencyViewRequired                                                                                                                            | AreaCode | PhoneNo | Extension |
      |    19333 | Pa$$word1 |     19333 | WHOLESALE | SALE            | Foreign Currencies | CANADA -- CAD |           100 | Viewing Currency is required before proceeding with transaction. Click the Show Currency button to view currency and continue with transaction. |      012 | 1234589 | NA        |

  @AutomationID_TC0101 @Regression
  Scenario Outline: Place Purchase from Travelex Wholesale order of foreign currency providing denoms
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>", product type "<ProductType>" and currency "<Currency>"
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    Then I see Todays Rate
    When I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    When I accept the alert
    And I enter specific denoms CurrencyValue "<Quantity>" DenomsQuantity "<Denoms>"
    And I click Add To Order button
    And I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer Contact details areaCode "<AreaCode>", phoneNumber "<PhoneNo>", and extension "<Extension>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | DenomincationMessage                                                                             | AreaCode | PhoneNo | Extension | Quantity | Denoms |
      |    19333 | Pa$$word1 |     19333 | WHOLESALE | PURCHASE        | Foreign Currencies | CANADA -- CAD |           100 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. |      012 | 1234589 | NA        |       10 |     10 |

  @AutomationID_TC0102 @Regression
  Scenario Outline: Place Purchase from Travelex Multi CCY Wholesale order of foreign currency
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>", product type "<ProductType>"
    And I  Select currency "<Currency>"
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    Then I see Todays Rate
    When I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    When I dismiss the alert
    And I  Select currency "<CurrencyTwo>"
    And I enter amount "<ForeignAmountTwo>"
    And I click on Quote and View button
    Then I see Todays Rate
    When I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    When I dismiss the alert
    And I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer Contact details areaCode "<AreaCode>", phoneNumber "<PhoneNo>", and extension "<Extension>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | DenomincationMessage                                                                             | AreaCode | PhoneNo | Extension | Quantity | Denoms | CurrencyTwo   | ForeignAmountTwo |
      |    19333 | Pa$$word1 |     19333 | WHOLESALE | PURCHASE        | Foreign Currencies | CANADA -- CAD |           100 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. |      012 | 1234589 | NA        |       10 |     10 | EUROPE -- EUR |              100 |

  @AutomationID_TC0104 @Regression
  Scenario Outline: Place Sale from Travelex Multi CCY Wholesale order of foreign currency
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>", product type "<ProductType>"
    And I  Select currency "<Currency>"
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    Then I see Todays Rate
    When I click Add To Order button
    Then I should see currencyViewRequiredPopup "<CurrencyViewRequired>"
    When I dismiss the alert
    And I click on Show Currency button and Switch to New Window
    Then I Should see Currency Window 
    When I click on Close button In Currency Window
    Then I navigate to Transaction Page
    When I click Add To Order button
    And I  Select currency "<CurrencyTwo>"
    And I enter amount "<ForeignAmountTwo>"
    And I click on Quote and View button
    Then I see Todays Rate
    When I click Add To Order button
    Then I should see denominationMessage "<CurrencyViewRequired>"
    When I dismiss the alert
    And I click on Show Currency button and Switch to New Window
    Then I Should see Currency Window
    When I click on Close button In Currency Window
    Then I navigate to Transaction Page
    When I click Add To Order button
    And I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer Contact details areaCode "<AreaCode>", phoneNumber "<PhoneNo>", and extension "<Extension>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details

    Examples: 
      | UserName | Password  | PartnerID | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CurrencyViewRequired                                                                                                                            | AreaCode | PhoneNo | Extension | Quantity | Denoms | CurrencyTwo   | ForeignAmountTwo |
      |    19333 | Pa$$word1 |     19333 | WHOLESALE | SALE            | Foreign Currencies | CANADA -- CAD |           100 | Viewing Currency is required before proceeding with transaction. Click the Show Currency button to view currency and continue with transaction. |      012 | 1234589 | NA        |       10 |     10 | EUROPE -- EUR |              100 |

