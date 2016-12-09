Feature: Sale Order of Foreign Currency

  Scenario Outline: Place Sale to customer order of foreign currency by providing denominations
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
    And I enter amount "<ForeignAmount>"
    And I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    And I click Ok button on Transaction Page
    Then I navigate to Transaction Page
    And I enter quantity "<Quantity>" on Denomination Table
    And I click Add To Order button
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
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CustomerRadioBtn | FirstName | LastName | GLAccNum  | Attention    | BranchContact | PhoneNumber | AccountHolderMessage                                                              | DenomincationMessage                                                                             | DeliveryType      | Quantity |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | CANADA -- CAD |          4500 | Mr               | Auto      | Test     | 123456789 | Guy Holtkamp |           022 |     1234567 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Next Day Delivery |          |

	
  Scenario Outline: Place Multi currency Sale to customer order of foreign currency
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
    When I select currency "<CurrencyOne>"
    And I enter amount "<ForeignAmountOne>"
    And I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    And I click Cancel button on Transaction Page
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
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CustomerRadioBtn | FirstName | LastName | GLAccNum  | Attention    | BranchContact | PhoneNumber | AccountHolderMessage                                                              | DenomincationMessage                                                                             | DeliveryType      | CurrencyOne   | ForeignAmountOne |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Mr               | Auto      | Test     | 123456789 | Guy Holtkamp |           022 |     1234567 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Next Day Delivery | CANADA -- CAD |             2000 |

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
    Then I navigate to Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CustomerRadioBtn | FirstName | LastName | GLAccNum  | Attention    | BranchContact | PhoneNumber | AccountHolderMessage                                                              | DenomincationMessage                                                                             | DeliveryType      |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Mr               | Auto      | Test     | 123456789 | Guy Holtkamp |           022 |     1234567 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Next Day Delivery |

  
  Scenario Outline: Verify Change branch link
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
    And I click on change branch link
    Then I navigate to Branch Selection Page
    When I click on Retrive button
    Then I should see branch drop down

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CustomerRadioBtn | FirstName | LastName | GLAccNum  | Attention    | BranchContact | PhoneNumber | AccountHolderMessage                                                              | DenomincationMessage                                                                             | DeliveryType      |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Mr               | Auto      | Test     | 123456789 | Guy Holtkamp |           022 |     1234567 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Next Day Delivery |

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
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType    | Currency      | ForeignAmount | Beneficiary | Address | City   | State       | ZipCode | Country | Comments | CustomerRadioBtn | FirstName | LastName | GLAccNum  | Attention    | BranchContact | PhoneNumber |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Drafts | EUROPE -- EUR |          5000 | Testing     | Mumbai  | Mumbai | Maharashtra |  400705 | India   | Testing  | Mr               | Auto      | Test     | 123456789 | Guy Holtkamp |           022 |     1234567 |

  Scenario Outline: Place Multi CCY Sale to customer order of foreign Drafts using quote and view button
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
    And I enter beneficiary "<Beneficiary>", address "<Address>", city "<City>", state "<State>" and zipCode "<ZipCode>"
    When I select country "<Country>"
    And I enter comments "<Comments>"
    And I click Add To Order button
    Then I navigate to Transaction Page
    When I select currency "<CurrencyOne>"
    And I enter amount "<ForeignAmountOne>"
    And I click on Quote and View button
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
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType    | Currency      | ForeignAmount | Beneficiary | Address | City   | State       | ZipCode | Country | Comments | CustomerRadioBtn | FirstName | LastName | GLAccNum  | Attention    | BranchContact | PhoneNumber | CurrencyOne   | ForeignAmountOne |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Drafts | EUROPE -- EUR |          4500 | Testing     | Mumbai  | Mumbai | Maharashtra |  400705 | India   | Testing  | Mr               | Auto      | Test     | 123456789 | Guy Holtkamp |           022 |     1234567 | CANADA -- CAD |             5000 |
