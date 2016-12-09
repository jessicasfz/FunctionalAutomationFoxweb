Feature: Sale order for canada non-Stockist branch(Login Functionality)

  @PraveenTest
  Scenario Outline: This feature will test Login functionality of COL with Valid credentials for Canada partners
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    And I close my Browser
    And I Relaunch Browser
    And I quit My Browser

    Examples: 
      | UserName   | Password  | PartnerID |
      | a00010@usb | Pa$$word1 |     29116 |

  Scenario Outline: This feature will test Login functionality of COL with invalid username for Canada partners
    Given I Login to COL with incorrect credetials username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I should see error message "<ErrorMessage>"

    Examples: 
      | UserName      | Password  | PartnerID | ErrorMessage                                                                                                                                                                                              |
      | a00010@Random | Pa$$word1 |     27905 | Unable to find UserID/Password. Please contact your administrator for access. Impossible de trouver votre Identification d'utilisateur/Mot de passe. Veuillez contacter votre administrateur pour entrer. |

  Scenario Outline: This feature will test Login functionality of COL with incorrect password for Canada partners
    Given I Login to COL with incorrect credetials username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I should see error message "<ErrorMessage>"

    Examples: 
      | UserName   | Password        | PartnerID | ErrorMessage                                                                                                                                                                                              |
      | a00010@usb | Pa$$word1Random |     27905 | Unable to find UserID/Password. Please contact your administrator for access. Impossible de trouver votre Identification d'utilisateur/Mot de passe. Veuillez contacter votre administrateur pour entrer. |

  Scenario Outline: This feature will test Login functionality of COL with Blank Username for Canada partners
    Given I Login to COL with incorrect credetials username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I should see error message "<ErrorMessage>"

    Examples: 
      | UserName | Password        | PartnerID | ErrorMessage                                                                                                                                                                                              |
      |          | Pa$$word1Random |     27905 | Unable to find UserID/Password. Please contact your administrator for access. Impossible de trouver votre Identification d'utilisateur/Mot de passe. Veuillez contacter votre administrateur pour entrer. |

  Scenario Outline: This feature will test Login functionality of COL with Blank Password for Canada partners
    Given I Login to COL with incorrect credetials username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I should see error message "<ErrorMessage>"

    Examples: 
      | UserName   | Password | PartnerID | ErrorMessage                                                                                                                                                                                              |
      | a00010@usb |          |     27905 | Unable to find UserID/Password. Please contact your administrator for access. Impossible de trouver votre Identification d'utilisateur/Mot de passe. Veuillez contacter votre administrateur pour entrer. |

  Scenario Outline: This feature will test Login functionality of COL with Blank Username & Password for Canada partners
    Given I Login to COL with incorrect credetials username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I should see error message "<ErrorMessage>"

    Examples: 
      | UserName | Password | PartnerID | ErrorMessage                                                                                                                                                                                              |
      |          |          |     27905 | Unable to find UserID/Password. Please contact your administrator for access. Impossible de trouver votre Identification d'utilisateur/Mot de passe. Veuillez contacter votre administrateur pour entrer. |

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
    Then I should see denominationMessage "<DenominationMessage>"
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
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CustomerRadioBtn | FirstName | LastName | GLAccNum | Attention   | BranchContact | PhoneNumber | DenominationMessage                                                                              | DeliveryType     |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Mr               | Auto      | Test     | NA       | Hosung Song |           022 |     1234567 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Two Day Delivery |

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
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    And I click Add To Order button
    Then I should see denominationMessage "<DenominationMessage>"
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
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CustomerRadioBtn | FirstName | LastName | GLAccNum | Attention   | BranchContact | PhoneNumber | DenominationMessage                                                                              | DeliveryType     |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Mr               | Auto      | Test     | NA       | Hosung Song |           022 |     1234567 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Two Day Delivery |

  Scenario Outline: Place Sale to customer order of foreign currency by providing Denominations
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
    And I click on Quote and View button
    And I click Add To Order button
    Then I should see denominationMessage "<DenominationMessage>"
    And I click Ok button on Transaction Page
    Then I navigate to Denomination Information Page
    When I enter quantity "<Quantity>" on Denomination Table
    And I click Add To Order button
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
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CustomerRadioBtn | FirstName | LastName | GLAccNum | Attention   | BranchContact | PhoneNumber | DenominationMessage                                                                              | DeliveryType     | Quantity |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Mr               | Auto      | Test     | NA       | Hosung Song |           022 |     1234567 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Two Day Delivery |      100 |

  Scenario Outline: Verify Warning message on denomination page if quantity and total amount is mismatch
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
    And I click on Quote and View button
    And I click Add To Order button
    Then I should see denominationMessage "<DenominationMessage>"
    And I click Ok button on Transaction Page
    Then I navigate to Denomination Information Page
    When I enter amount "<IncorrectForeignAmount>"
    Then I should see error message "<ErrorMessage>"
    And I click Ok button on Transaction Page
    Then I navigate to Transaction Page
    And I see updated order amount "<IncorrectForeignAmount>"

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | IncorrectForeignAmount | DenominationMessage                                                                              | ErrorMessage                                                                                   |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 |                   1000 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | The total of your requested denominations does not equal the originally-entered Foreign Amount |

  Scenario Outline: Verify CLEAR FIELDS button of Choose Transaction Type page
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
    And I click on Quote and View button
    When I click clear button on Transaction Page
    Then I navigate to Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 |

  Scenario Outline: Verify DELETE button of Order Details page
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
    And I click on Quote and View button
    And I click Add To Order button
    Then I should see denominationMessage "<DenominationMessage>"
    And I click Cancel button on Transaction Page
    Then I navigate to Transaction Page
    When I click Delete button on Transaction Page
    Then I navigate to Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | DenominationMessage                                                                              |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. |

  Scenario Outline: Verify EDIT button of Order Details page
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
    And I click on Quote and View button
    And I click Add To Order button
    Then I should see denominationMessage "<DenominationMessage>"
    And I click Cancel button on Transaction Page
    Then I navigate to Transaction Page
    And I click edit button on Transaction Page
    And I navigate to Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | DenominationMessage                                                                              |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. |

  Scenario Outline: Verify Clear Fields button on denomination page
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
    And I click on Quote and View button
    And I click Add To Order button
    Then I should see denominationMessage "<DenominationMessage>"
    And I click Ok button on Transaction Page
    Then I navigate to Denomination Information Page
    And I enter amount "<ForeignAmount>"
    When I click clear button on Transaction Page
    Then I navigate to Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | DenominationMessage                                                                              |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. |

  Scenario Outline: Verify CHANGE ORDER button on Customer Details page
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
    And I click on Quote and View button
    And I click Add To Order button
    Then I should see denominationMessage "<DenominationMessage>"
    And I click Cancel button on Transaction Page
    Then I navigate to Transaction Page
    When I select delivery Type "<DeliveryType>"
    Then I get calculated total amount according to delivery charges
    When I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>" and GLNo "<GLAccNum>"
    And I enter Delivery details attention "<Attention>" branchContact "<BranchContact>" phoneNumber "<PhoneNumber>"
    And I click change order on Transaction Page
    Then I navigate to Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CustomerRadioBtn | FirstName | LastName | GLAccNum | Attention   | BranchContact | PhoneNumber | DenominationMessage                                                                              | DeliveryType     |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Mr               | Auto      | Test     | NA       | Hosung Song |           022 |     1234567 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Two Day Delivery |

  Scenario Outline: Verify CANCEL ORDER button on Customer Details page
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
    And I click on Quote and View button
    And I click Add To Order button
    Then I should see denominationMessage "<DenominationMessage>"
    And I click Cancel button on Transaction Page
    Then I navigate to Transaction Page
    When I select delivery Type "<DeliveryType>"
    Then I get calculated total amount according to delivery charges
    When I click complete order button on Transaction Page
    Then I navigate to Customer Details Page
    When I enter Customer details customerRadioBtn "<CustomerRadioBtn>", firstName "<FirstName>", lastName "<LastName>" and GLNo "<GLAccNum>"
    And I enter Delivery details attention "<Attention>" branchContact "<BranchContact>" phoneNumber "<PhoneNumber>"
    And I click Cancel button on Transaction Page
    Then I navigate to Home Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CustomerRadioBtn | FirstName | LastName | GLAccNum | Attention   | BranchContact | PhoneNumber | DenominationMessage                                                                              | DeliveryType     |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Mr               | Auto      | Test     | NA       | Hosung Song |           022 |     1234567 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Two Day Delivery |

  Scenario Outline: Place Sale to customer order for multiple foreign currencies
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
    Then I should see denominationMessage "<DenominationMessage>"
    And I click Cancel button on Transaction Page
    When I select product type "<ProductType>" and currency "<CurrencyOne>"
    And I enter amount "<ForeignAmountOne>"
    And I click Add To Order button
    Then I should see denominationMessage "<DenominationMessage>"
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
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CurrencyOne | ForeignAmountOne | CustomerRadioBtn | FirstName | LastName | GLAccNum | Attention   | BranchContact | PhoneNumber | DenominationMessage                                                                              | DeliveryType     |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | FIJI -- FJD |             4000 | Mr               | Auto      | Test     | NA       | Hosung Song |           022 |     1234567 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Two Day Delivery |

  Scenario Outline: Verify Specify denom functionality when order is edited
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
    And I click on Quote and View button
    And I click Add To Order button
    Then I should see denominationMessage "<DenominationMessage>"
    And I click Cancel button on Transaction Page
    Then I navigate to Transaction Page
    When I click edit button on Transaction Page
    And I click Add To Order button
    Then I should see denominationMessage "<DenominationMessage>"
    And I click Ok button on Transaction Page
    Then I navigate to Denomination Information Page
    When I enter quantity "<Quantity>" on Denomination Table
    And I click Add To Order button
    Then I navigate to Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | DenominationMessage                                                                              | Quantity |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. |      500 |

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
    And I click on Quote and View button
    And I click Add To Order button
    Then I should see denominationMessage "<DenominationMessage>"
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
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CustomerRadioBtn | FirstName | LastName | GLAccNum | Attention   | BranchContact | PhoneNumber | DenominationMessage                                                                              | DeliveryType     |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Mr               | Auto      | Test     | NA       | Hosung Song |           022 |     1234567 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Two Day Delivery |

  Scenario Outline: Verify Rate Sheet link
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I click on Retrive button
    And I select branch "<BranchLocation>"
    And I click on Next button
    When I click on Rate Sheet
    Then I navigate to Rate Sheet page
    And I click on View Rate Sheet
    And I should see report

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA |

  @Smoke
  Scenario Outline: Verify Daily Branch Activity link
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I click on Retrive button
    And I select branch "<BranchLocation>"
    And I click on Next button
    When I click on Daily Branch Activity
    Then I navigate to Daily Branch Activity page
    And I click on Export to excel
    And I should see report

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA |

  Scenario Outline: Verify Change Branch link with complete order
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
    Then I should see denominationMessage "<DenominationMessage>"
    And I click Cancel button on Transaction Page
    When I select product type "<ProductType>" and currency "<CurrencyOne>"
    And I enter amount "<ForeignAmountOne>"
    And I click Add To Order button
    Then I should see denominationMessage "<DenominationMessage>"
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
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | CurrencyOne | ForeignAmountOne | CustomerRadioBtn | FirstName | LastName | GLAccNum | Attention   | BranchContact | PhoneNumber | DenominationMessage                                                                              | DeliveryType     |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | FIJI -- FJD |             4000 | Mr               | Auto      | Test     | NA       | Hosung Song |           022 |     1234567 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Two Day Delivery |

  @tobetested
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
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType    | Currency           | ForeignAmount | Beneficiary | Address | City   | State       | ZipCode | Country | Comments | CustomerRadioBtn | FirstName | LastName | GLAccNum  | Attention    | BranchContact | PhoneNumber |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Drafts | AUSTRALIA -- DRAFT |          5000 | Testing     | Mumbai  | Mumbai | Maharashtra |  400705 | India   | Testing  | Mr               | Auto      | Test     | 123456789 | Guy Holtkamp |           022 |     1234567 |

  @tobetested
  Scenario Outline: Place Sale to customer order of foreign Drafts using quote and view button
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
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType    | Currency           | ForeignAmount | Beneficiary | Address | City   | State       | ZipCode | Country | Comments | CustomerRadioBtn | FirstName | LastName | GLAccNum  | Attention    | BranchContact | PhoneNumber |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Drafts | AUSTRALIA -- DRAFT |          5000 | Testing     | Mumbai  | Mumbai | Maharashtra |  400705 | India   | Testing  | Mr               | Auto      | Test     | 123456789 | Guy Holtkamp |           022 |     1234567 |

  @tobetested
  Scenario Outline: Place Sale to customer order of foreign Drafts for multiple currencies using quote and view button
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
    When I enter amount "<ForeignAmountOne>"
    And I select product type "<ProductType>" and currency "<CurrencyOne>"
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
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType    | Currency           | ForeignAmount | CurrencyOne    | ForeignAmountOne | Beneficiary | Address | City   | State       | ZipCode | Country | Comments | CustomerRadioBtn | FirstName | LastName | GLAccNum  | Attention    | BranchContact | PhoneNumber |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Drafts | AUSTRALIA -- DRAFT |          5000 | JAPAN -- DRAFT |             4000 | Testing     | Mumbai  | Mumbai | Maharashtra |  400705 | India   | Testing  | Mr               | Auto      | Test     | 123456789 | Guy Holtkamp |           022 |     1234567 |

  @Test1
  Scenario Outline: Validate minimum foreign amount
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
    Then I should see denominationMessage "<DenomincationMessage>"
    When I click Cancel button on Transaction Page
    Then I navigate to Transaction Page
    When I select delivery Type "<DeliveryType>"
    Then I get calculated total amount according to delivery charges
    When I click complete order button on Order Page
    Then I should see popup message as "<PopUpMessage>"

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | DenomincationMessage                                                                             | DeliveryType     | PopUpMessage                                       |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |             1 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Two Day Delivery | The order values must be between $ 20 and $ 15,000 |

  @Test1
  Scenario Outline: Validate maximum foreign amount
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
    Then I should see denominationMessage "<DenomincationMessage>"
    When I click Cancel button on Transaction Page
    Then I navigate to Transaction Page
    When I select delivery Type "<DeliveryType>"
    Then I get calculated total amount according to delivery charges
    When I click complete order button on Order Page
    Then I should see popup message as "<PopUpMessage>"

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | DenomincationMessage                                                                             | DeliveryType     | PopUpMessage                                       |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |         20000 | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Two Day Delivery | The order values must be between $ 20 and $ 15,000 |

  @tobetested
  Scenario Outline: Search order using Confirmation Number
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I click on Retrive button
    And I select branch "<BranchLocation>"
    And I click on Next button
    Then I navigate to Transaction Page
    When I select searchOrder Link
    Then I navigate to Search Order Page
    When I enter confirmation Number"<ConfirmationNumber>" in orderNo field
    And I click on Search button on Search Order Result Page
    And I click on order number link
    Then I navigate to Order Summary Page
    And I click on Previous Page button
    Then I navigate to search order result Page
    And I click on Export To Excel button
    And I click on Previous Page button
    Then I navigate to Search Order Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   | ConfirmationNumber |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA |                    |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA |               1234 |

  @tobetested
  Scenario Outline: Search order using From and To Date range
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I click on Retrive button
    And I select branch "<BranchLocation>"
    And I click on Next button
    Then I navigate to Transaction Page
    When I select searchOrder Link
    Then I navigate to Search Order Page
    When I enter fromDate as "<FromDate>" and toDate as "<ToDate>"
    And I click on Search button on Search Order Result Page
    And I click on Next Link
    And I click on Previous Page button

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   | FromDate   | ToDate     |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | 10/15/2016 | 11/15/2016 |

  @tobetested
  Scenario Outline: Error message when invalid Date range is entered
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I click on Retrive button
    And I select branch "<BranchLocation>"
    And I click on Next button
    Then I navigate to Transaction Page
    When I select searchOrder Link
    Then I navigate to Search Order Page
    When I enter fromDate as "<FromDate>" and toDate as "<ToDate>"
    And I click on Search button on Search Order Result Page
    Then I should see errorMessage as "<ErrorMessage>"

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   | ErrorMessage                              | FromDate   | ToDate     |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Please enter a valid month for this date. | 15/13/2016 | 16/13/2016 |

  @tobetested
  Scenario Outline: Search order using Teller Name
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I click on Retrive button
    And I select branch "<BranchLocation>"
    And I click on Next button
    Then I navigate to Transaction Page
    When I select searchOrder Link
    Then I navigate to Search Order Page
    And I enter TellerName "<TellerName>"
    And I click on Search button on Search Order Result Page
    And I click on order number link
    Then I navigate to Order Summary Page
    And I click on Previous Page button
    Then I navigate to search order result Page
    And I click on Export To Excel button
    And I click on Previous Page button
    Then I navigate to Search Order Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   | TellerName |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Kimberly   |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA |       1234 |

  @tobetested
  Scenario Outline: Search order using Customer Name
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I click on Retrive button
    And I select branch "<BranchLocation>"
    And I click on Next button
    Then I navigate to Transaction Page
    When I select searchOrder Link
    Then I navigate to Search Order Page
    And I enter CustomerName "<CustomerName>"
    And I click on Search button on Search Order Result Page
    And I click on order number link
    Then I navigate to Order Summary Page
    And I click on Previous Page button
    Then I navigate to search order result Page
    And I click on Export To Excel button
    And I click on Previous Page button
    Then I navigate to Search Order Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   | CustomerName |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA |              |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA |         1234 |

  @tobetested
  Scenario Outline: Search order using Order status
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I click on Retrive button
    And I select branch "<BranchLocation>"
    And I click on Next button
    Then I navigate to Transaction Page
    When I select searchOrder Link
    Then I navigate to Search Order Page
    And I select orderStatus "<OrderStatus>"
    And I click on Search button on Search Order Result Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation   | OrderStatus |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Pending     |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Complete    |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | Cancelled   |
      | hosung2011 | Pa$$word1 |     10015 | HSBC10031 OTTAWA | All         |
