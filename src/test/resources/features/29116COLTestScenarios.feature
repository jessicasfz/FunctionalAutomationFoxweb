Feature: COL Scenarios for US Non Stockist Upto Transaction Page

  
  Scenario Outline: Verify Show currency button of Choose Transaction Type page
    Given I Login to COL with username "<UserName>" and password "<Password>" for Partner "<PartnerID>"
    Then I navigate to Home Page
    When I click on Retrive button
    And I select branch "<BranchLocation>"
    And I click on Next button
    Then I navigate to Transaction Page
    When I select orderType "<OrderType>" Link
    Then I navigate to Transaction Page
    When I select transaction type "<TransactionType>", product type "<ProductType>" and currency "<Currency>"
    And I click on Show Currency button and Switch to New Window
    Then I Should see Currency Window
    When I click on Close button In Currency Window
    Then I navigate to Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | CANADA -- CAD |

  
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
    And I click on Quote and View button
    Then I should see amountMessage "<AmountMessage>"
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    And I click Add To Order button
    Then I should see accountHolderMessage "<AccountHolderMessage>"
    When I select confirm transaction Checkbox
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    And I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    And I click Ok button on Transaction Page
    Then I navigate to Transaction Page
    And I enter quantity "<Quantity>" on Denomination Table
    And I click Add To Order button
    And I should see denominationWarningMessage "<DenomWarningMessage>"
    And I click Ok button on Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | AccountHolderMessage                                                              | DenomincationMessage                                                                             | Quantity | DenomWarningMessage                                                                                                                                                                                                                 | AmountMessage           |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | CANADA -- CAD |          4500 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. |      100 | The total of your requested denominations does not equal the originally-entered Foreign Amount of 5400.00 CAD.  Please click Ok to continue and add the new amount to your order.  Click Cancel to modify the denomination request. | Please enter an amount. |

  
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
    And I click on Quote and View button
    Then I should see amountMessage "<AmountMessage>"
    And I enter amount "<ForeignAmount>"
    And I click on Quote and View button
    And I click clear button on Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | AmountMessage           |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Please enter an amount. |

  
  Scenario Outline: Verify Show currency button of Order Details page
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
    And I click on Show Currency button and Switch to New Window
    Then I Should see Currency Window
    When I click on Close button In Currency Window
    Then I navigate to Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | AccountHolderMessage                                                              | DenomincationMessage                                                                             |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. |

  
  Scenario Outline: Verify CLEAR FIELDS button of Order Details page
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
    And I click clear button on Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | AccountHolderMessage                                                              | DenomincationMessage                                                                             |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. |

  
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
    And I click Add To Order button
    Then I should see accountHolderMessage "<AccountHolderMessage>"
    When I select confirm transaction Checkbox
    And I enter amount "<ForeignAmount>"
    And I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    And I click Cancel button on Transaction Page
    And I click Delete button on Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | AccountHolderMessage                                                              | DenomincationMessage                                                                             |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. |

  
  Scenario Outline: Verify QUOTE AND VIEW button of Order Details page
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
    And I click on Quote and View button

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | AccountHolderMessage                                                              | DenomincationMessage                                                                             |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. |

  
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
    And I click Add To Order button
    Then I should see accountHolderMessage "<AccountHolderMessage>"
    When I select confirm transaction Checkbox
    And I enter amount "<ForeignAmount>"
    And I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    And I click Cancel button on Transaction Page
    And I click edit button on Transaction Page
    And I modify foreignAmount "<ForeignAmountOne>"
    And I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    And I click Cancel button on Transaction Page
    Then I navigate to Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | AccountHolderMessage                                                              | DenomincationMessage                                                                             | ForeignAmountOne |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. |             4500 |

  
  Scenario Outline: Verify Show currency button of Denomination Information page
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
    Then I should see enterAmountPopupMessage "<EnterAmountPopUp>"
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
    And I click on Show Currency button and Switch to New Window
    Then I Should see Currency Window
    When I click on Close button In Currency Window
    Then I navigate to Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | AccountHolderMessage                                                              | DenomincationMessage                                                                             | EnterAmountPopUp        | Quantity | DeliveryType      |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | CANADA -- CAD |          4500 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Please enter an amount. |          | Next Day Delivery |

  
  Scenario Outline: Verify CLEAR FIELDS button of Denomination Information page
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
    Then I should see enterAmountPopupMessage "<EnterAmountPopUp>"
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
    And I click clear button on Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | AccountHolderMessage                                                              | DenomincationMessage                                                                             | EnterAmountPopUp        | Quantity | DeliveryType      |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | CANADA -- CAD |          4500 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Please enter an amount. |          | Next Day Delivery |

  
  Scenario Outline: Verify CHANGE ORDER button of Customer Details page
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
    And I click change order on Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | AccountHolderMessage                                                              | DenomincationMessage                                                                             | DeliveryType      | CustomerRadioBtn | FirstName | LastName | GLAccNum  | Attention    | BranchContact | PhoneNumber |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Next Day Delivery | Mr               | Auto      | Test     | 123456789 | Guy Holtkamp |           022 |     1234567 |

  
  Scenario Outline: Verify CANCEL ORDER button of Customer Details page
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
    And I click Cancel button on Transaction Page
    Then I navigate to Home Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | AccountHolderMessage                                                              | DenomincationMessage                                                                             | DeliveryType      | CustomerRadioBtn | FirstName | LastName | GLAccNum  | Attention    | BranchContact | PhoneNumber |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Next Day Delivery | Mr               | Auto      | Test     | 123456789 | Guy Holtkamp |           022 |     1234567 |

  
  Scenario Outline: Verify SPECIFY DENOM button of Choose Transaction Type page when edited
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
    And I click edit button on Transaction Page
    Then I navigate to Transaction Page
    And I click specify denom button on Transaction Page
    Then I navigate to Denomination Information Page
    And I enter quantity "<Quantity>" on Denomination Table
    Then I navigate to Transaction Page

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | AccountHolderMessage                                                              | DenomincationMessage                                                                             | Quantity |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |          5000 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. |      100 |

  
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
    Then I should see accountHolderMessage "<AccountHolderMessage>"
    When I select confirm transaction Checkbox
    And I enter amount "<ForeignAmount>"
    And I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    And I click Cancel button on Transaction Page
    Then I navigate to Transaction Page
    When I select delivery Type "<DeliveryType>"
    Then I get calculated total amount according to delivery charges
    When I click complete order button on Order Page
    Then I should see popup message as "<PopUpMessage>"

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | AccountHolderMessage                                                              | DenomincationMessage                                                                             | DeliveryType      | PopUpMessage                                       |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |            10 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Next Day Delivery | The order values must be between $ 20 and $ 15,000 |

  
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
    Then I should see accountHolderMessage "<AccountHolderMessage>"
    When I select confirm transaction Checkbox
    And I enter amount "<ForeignAmount>"
    And I click Add To Order button
    Then I should see denominationMessage "<DenomincationMessage>"
    And I click Cancel button on Transaction Page
    Then I navigate to Transaction Page
    When I select delivery Type "<DeliveryType>"
    Then I get calculated total amount according to delivery charges
    When I click complete order button on Order Page
    Then I should see popup message as "<PopUpMessage>"

    Examples: 
      | UserName   | Password  | PartnerID | BranchLocation           | OrderType | TransactionType | ProductType        | Currency      | ForeignAmount | AccountHolderMessage                                                              | DenomincationMessage                                                                             | DeliveryType      | PopUpMessage                                       |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Online    | Sale            | Foreign Currencies | EUROPE -- EUR |         20000 | Please confirm, by clicking the check-box, that the customer is an Account holder | Would you like to specify denominations for this order? Click Ok to specify, Cancel to continue. | Next Day Delivery | The order values must be between $ 20 and $ 15,000 |

  
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
      | UserName   | Password  | PartnerID | BranchLocation           | ConfirmationNumber |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls |                    |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls |               1234 |

  
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
      | UserName   | Password  | PartnerID | BranchLocation           | FromDate   | ToDate     |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | 10/15/2016 | 11/15/2016 |

  
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
      | UserName   | Password  | PartnerID | BranchLocation           | ErrorMessage                              | FromDate   | ToDate     |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Please enter a valid month for this date. | 15/13/2016 | 16/13/2016 |

  
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
      | UserName   | Password  | PartnerID | BranchLocation           | TellerName |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Kimberly   |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls |       1234 |

  
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
      | UserName   | Password  | PartnerID | BranchLocation           | CustomerName |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls |              |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls |         1234 |

  
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
      | UserName   | Password  | PartnerID | BranchLocation           | OrderStatus |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Pending     |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Complete    |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | Cancelled   |
      | a00010@usb | Pa$$word1 |     29116 | USB102 St. Anthony Falls | All         |
