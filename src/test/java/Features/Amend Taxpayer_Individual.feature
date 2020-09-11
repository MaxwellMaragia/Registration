Feature: SUC:01-07-Amend Taxpayer

  @SUC:01-07 @UAT_TCS-01.07.2
  Scenario Outline: UAT_TCS 01.07.2-To verify the process of amendment Request received from RGD (sole proprietors)
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "C0020598"
    Then Click search : id "SearchForm:j_idt42"
    Then Select reason for amendment "<amendmentReason>"
    Then Click Sole proprietor additional details tab
    Then Click table column "//*[@id='RegisterIndividual:individualAccordion:tradingNameTableHandler_data']/tr/td[4]"
    Then Click edit "RegisterIndividual:individualAccordion:tradingNameTableHandler:EditTradingNameDetails"
    Then Switch to frame
    Then Change RGD number to "32344560"
    Then Click ok: xpath "//*[@id='TradingNameDetails:OK']"
    Then Click save "RegisterIndividual:submitTaxpayerRegistration"
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "<SuccessMessage>"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame
    Then search for reference number
    Then Click on reference number
    Then Click next stage button
    Then Wait for text "Amendment Change Type" to load in frame "WebResource_RegistrationApplicationAngular"
    Then approve transaction
    Then Click save CRM
    Then Status should be "<Status>"
    Examples:
      | amendmentReason | Status   | SuccessMessage                          |
      | Change of Name  | Approved | Processing Completed - Reference Number |

  @SUC:01-07 @UAT_TCS-01.07.3
  Scenario Outline: UAT_TCS 01.07.3-To verify the process of amendment request received from Taxpayer Portal
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "C0028116"
    Then Click search : id "SearchForm:j_idt42"
    Then Enter first name "<firstName>" and last name "<lastName>"
    Then Select reason for amendment "<amendmentReason>"
    Then Click save "RegisterIndividual:submitTaxpayerRegistration"
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "<SuccessMessage>"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame
    Then search for reference number
    Then Click on reference number
    Then Click next stage button
    Then Click next stage button
    Then approve transaction
    Then Click save CRM
    Then Status should be "<Status>"
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "C0028116"
    Then Click search : id "SearchForm:j_idt42"
    Then Check if changes reflect "<firstName>"
    Examples:
      | firstName | lastName | amendmentReason | Status   | SuccessMessage                          |
      | Maxwell   | Maragia  | Change of Name  | Approved | Processing Completed - Reference Number |

  @SUC:01-07 @UAT_TCS-01.07.4
  Scenario: UAT_TCS 01.07.4-To verify the process of Taxpayer not found for Amendment
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "P0028182"
    Then Click search : id "SearchForm:j_idt42"
    Then Verify no data is found in table

  @SUC:01-07 @UAT_TCS-01.07.5
  Scenario Outline: UAT_TCS 01.07.5-To verify the process of validation error during amendment
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "P0021518"
    Then Click search : id "SearchForm:j_idt42"
    Then Select category as "<Category>"
    Then Select reason for amendment "<amendmentReason>"
    Then Click save "RegisterIndividual:submitTaxpayerRegistration"
    Then Verify error message "Turnover: Validation Error: Value is required."
    Examples:
      | Category      | amendmentReason |
      | Self-employed | Change of Name  |

  @SUC:01-07 @UAT_TCS-01.07.6
  Scenario Outline: UAT_TCS 01.07.6-To verify the process of Abandoning amendment
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "P0021518"
    Then Click search : id "SearchForm:j_idt42"
    Then Click save "RegisterIndividual:submitTaxpayerRegistration"
    Then Verify error message "Turnover: Validation Error: Value is required."
    Examples:
      | Category      | amendmentReason |
      | Self-employed | Change of Name  |

  @SUC:01-07 @UAT_TCS-01.07.7
  Scenario Outline: UAT_TCS 01.07.7-To verify the process of Duplicate check during Amendment
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "C0020598"
    Then Click search : id "SearchForm:j_idt42"
    Then Enter first name "<firstName>" and last name "<lastName>"
    Then Select reason for amendment "<amendmentReason>"
    Then Click save "RegisterIndividual:submitTaxpayerRegistration"
    Then Verify save success message "<SuccessMessage>"
    Then Obtain reference number "<SuccessMessage>"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame
    Then search for reference number
    Then Click on reference number
    Then Click next stage button
    Then Wait for text "First Name" to load in frame "WebResource_RegistrationApplicationAngular"
    Then Verify duplicate check returns duplicates
    Then Delete case
    Examples:
      | firstName | lastName | amendmentReason | SuccessMessage                          |
      | Max       | Suspend  | Invalid data    | Processing Completed - Reference Number |

  @SUC:01-07 @UAT_TCS-01.07.8
  Scenario Outline: UAT_TCS 01.07.8-To verify the process of checking duplicates [Organization initially registered in RGD]
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "C0028116"
    Then Click search : id "SearchForm:j_idt42"
    Then Enter first name "<firstName>" and last name "<lastName>"
    Then Select reason for amendment "<amendmentReason>"
    Then Click save "RegisterIndividual:submitTaxpayerRegistration"
    Then Verify save success message "<SuccessMessage>"
    Then Obtain reference number "<SuccessMessage>"
    Then Open CRM and close modal
    Then Click on registration application link
    Then switch to frame
    Then search for reference number
    Then Click on reference number
    Then Click next stage button
    Then Click next stage button
    And clicks Decline from the dropdown
    Then Enter Outcome Notes "<Notes>"
    And Enter Outcome Reason for Taxpayer accounting
    Then Click save CRM
    Then Status should be "Rejected"
    Examples:
      | firstName | lastName | amendmentReason | SuccessMessage                          | Notes      |
      | John      | Kamau    | Change of Name  | Processing Completed - Reference Number | Duplicates |

  @SUC:01-07 @UAT_TCS-01.07.10
  Scenario Outline: UAT_TCS 01.07.10-To verify the process of Amending when taxpayer status is 'Pending Approval'
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "P0022044"
    Then Click search : id "SearchForm:j_idt42"
    Then Enter first name "<firstName>" and last name "<lastName>"
    Then Select reason for amendment "<amendmentReason>"
    Then Click save "RegisterIndividual:submitTaxpayerRegistration"
    Then Verify error message "Taxpayer has an existing application which is pending approval, hence the request cannot be processed at this time."
    Examples:
      | firstName | lastName | amendmentReason |
      | John      | Kamau    | Change of Name  |

  @SUC:01-07 @UAT_TCS-01.07.11
  Scenario: UAT_TCS 01.07.11-To verify the process of Amending when a taxpayer status is 'De-Registered'
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "P0028182"
    Then Click search : id "SearchForm:j_idt42"
    Then Verify no data is found in table

  @SUC:01-07 @UAT_TCS-01.07.12
  Scenario: UAT_TCS 01.07.12-To verify the process of Amending when taxpayer and other relationships contains same TIN number
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "C0028116"
    Then Click search : id "SearchForm:j_idt42"
    Then Click relationships tab
    Then Click add "RegisterIndividual:individualAccordion:relationshipHandler:AddRelationshipDetails"
    Then Switch to frame
    Then Select relationship type
    Then Click find "RelationshipDetails:FindTin"
    Then Switch to default
    Then Switch to frame 2
    Then Search for relationship with same tin "C0028116"
    Then Switch to frame
    Then Click ok: xpath "//*[@id='RelationshipDetails:Ok']"
    Then Verify error message "The selected taxpayer cannot be added to itself as any other relationship."


  @SUC:01-07 @UAT_TCS-01.07.13
  Scenario: UAT_TCS 01.07.13-To verify the process of Transferring Property
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "V0024699"
    Then Click search : id "SearchForm:j_idt42"
    Then Select reason for amendment "Change of Property Details"
    Then Click properties tab
    Then Obtain property id of first property item in list
    Then Click table column "//*[@id='RegisterIndividual:individualAccordion:propertyTableHandler_data']/tr[1]/td[2]"
    Then Click transfer
    Then Switch to frame
    Then Click find "TransferPropertyDetails:FindTin"
    Then Switch to default
    Then Switch to frame 2
    Then Search for taxpayer to transfer property to "P0024640"
    Then Switch to frame
    Then Click ok: xpath "//*[@id='TransferPropertyDetails:Ok']"
    Then Click save "RegisterIndividual:submitTaxpayerRegistration"
    Then Verify save success message "Processing Completed - Reference Number"
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "C0028116"
    Then Click search : id "SearchForm:j_idt42"
    Then Click properties tab
    Then Validate transfer of property by checking property id
