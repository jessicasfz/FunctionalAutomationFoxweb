Feature: Smoke Feature File

  @Smokes
  Scenario Outline: Place Sale to customer order of foreign currency without clicking quote and view button
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I click on Retrive button
    And I select branch "<BranchLocation>"
    And I click on Next button
    Then I navigate to Transaction Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>", product type "<ProductType>" and currency "<Currency>"
    And I enter amount "<ForeignAmount>"
    And I click Add To Order button
    Then I should see accountHolderMessage "<AccountHolderMessage>"
    When I select confirm transaction Checkbox
    And I enter amount "<ForeignAmount>"
    And I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    And I click Cancel button on Transaction Page
    Then I navigate to Transaction Page
    When I select delivery Type "<DeliveryType>"
    Then I get calculated total amount according to delivery charges
    When I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>" and GLNo "<GLAccNum>"
    And I enter Delivery details attention "<Attention>" branchContact "<BranchContact>" phoneNumber "<PhoneNumber>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CustomerRadioBtn | FirstName | LastName | GLAccNum  | Attention    | BranchContact | PhoneNumber | AccountHolderMessage                                                              | DenomincationMessage                                                                             | DeliveryType      |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Mr               | Auto      | Test     | 123456789 | Guy Holtkamp |           022 |     1234567 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Next Day Delivery |

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

  Scenario Outline: Place Sale to customer order of foreign currency using quote and view button
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I click on Retrive button
    And I select branch "<BranchLocation>"
    And I click on Next button
    Then I navigate to Transaction Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>", product type "<ProductType>" and currency "<Currency>"
    And I click on Quote and View button
    Then I should see amountMessage "<AmountMessage>"
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    And I click Add To Order button
    Then I should see accountHolderMessage "<AccountHolderMessage>"
    When I select confirm transaction Checkbox
    And I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    And I click Cancel button on Transaction Page
    Then I navigate to Transaction Page
    When I select delivery Type "<DeliveryType>"
    Then I get calculated total amount according to delivery charges
    When I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>" and GLNo "<GLAccNum>"
    And I enter Delivery details attention "<Attention>" branchContact "<BranchContact>" phoneNumber "<PhoneNumber>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CustomerRadioBtn | FirstName | LastName | GLAccNum  | Attention    | BranchContact | PhoneNumber | AccountHolderMessage                                                              | DenomincationMessage                                                                             | AmountMessage           | DeliveryType      |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | CANADA -- CAD |          4500 | Mr               | Auto      | Test     | 123456789 | Guy Holtkamp |           022 |     1234567 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Please enter an amount. | Next Day Delivery |

  Scenario Outline: Verify NEXT ORDER button of confirmation page
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I click on Retrive button
    And I select branch "<BranchLocation>"
    And I click on Next button
    Then I navigate to Transaction Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>", product type "<ProductType>" and currency "<Currency>"
    And I enter amount "<ForeignAmount>"
    And I click Add To Order button
    Then I should see accountHolderMessage "<AccountHolderMessage>"
    When I select confirm transaction Checkbox
    And I enter amount "<ForeignAmount>"
    And I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    And I click Cancel button on Transaction Page
    Then I navigate to Transaction Page
    When I select delivery Type "<DeliveryType>"
    Then I get calculated total amount according to delivery charges
    When I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>" and GLNo "<GLAccNum>"
    And I enter Delivery details attention "<Attention>" branchContact "<BranchContact>" phoneNumber "<PhoneNumber>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details
    And I click Next button on Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CustomerRadioBtn | FirstName | LastName | GLAccNum  | Attention    | BranchContact | PhoneNumber | AccountHolderMessage                                                              | DenomincationMessage                                                                             | DeliveryType      |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Mr               | Auto      | Test     | 123456789 | Guy Holtkamp |           022 |     1234567 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Next Day Delivery |

  @DemoTest
  Scenario Outline: Place Sale to customer order of foreign Drafts without clicking quote and view button
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I click on Retrive button
    And I select branch "<BranchLocation>"
    And I click on Next button
    Then I navigate to Transaction Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>", product type "<ProductType>" and currency "<Currency>"
    And I select confirm transaction Checkbox
    And I enter amount "<ForeignAmount>"
    And I enter beneficiary "<Beneficiary>", address "<Address>", city "<City>", state "<State>" and zipCode "<ZipCode>"
    When I select country "<Country>"
    And I enter comments "<Comments>"
    And I click Add To Order button
    Then I navigate to Transaction Page
    When I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>" and GLNo "<GLAccNum>"
    And I enter Delivery details attention "<Attention>" branchContact "<BranchContact>" phoneNumber "<PhoneNumber>"
    And I click Complete Order button on Customer Details Page
    Then I get Confirmation number
    When I click Printer Friendly button
    Then I should see order details

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType    | Currency           | ForeignAmount | Beneficiary | Address | City   | State       | ZipCode | Country | Comments | CustomerRadioBtn | FirstName | LastName | GLAccNum  | Attention    | BranchContact | PhoneNumber |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Drafts | AUSTRALIA -- DRAFT |          5000 | Testing     | Mumbai  | Mumbai | Maharashtra |  400705 | India   | Testing  | Mr               | Auto      | Test     | 123456789 | Guy Holtkamp |           022 |     1234567 |
