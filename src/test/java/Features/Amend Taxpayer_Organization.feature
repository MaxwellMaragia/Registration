Feature: SUC:01-08-Amend Taxpayer

  @UAT_TCS-01.08.3 @SUC:01-08
  Scenario Outline: UAT_TCS 01.08.3-To verify the process of amendment request received from Taxpayer Portal
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "V0028293"
    Then Click search : id "SearchForm:j_idt42"
    Then Enter Organization name "<name>"
    Then Select reason for amendment : organization "<amendmentReason>"
    Then Click save "OrganisationSummaryDetails:submitTaxpayerRegistration"
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
    Examples:
      | name               | amendmentReason | Status   | SuccessMessage                          |
      | Codei technologies | Change of Name  | Approved | Processing Completed - Reference Number |

  @UAT_TCS-01.08.4 @SUC:01-08
  Scenario: UAT_TCS 01.08.4-To verify the process of Taxpayer not found for Amendment
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "P0028182"
    Then Click search : id "SearchForm:j_idt42"
    Then Verify no data is found in table

  @UAT_TCS-01.08.5 @SUC:01-08
  Scenario: UAT_TCS 01.08.5-To verify the process of validation error during amendment
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "V0024699"
    Then Click search : id "SearchForm:j_idt42"
    Then Click save "OrganisationSummaryDetails:submitTaxpayerRegistration"
    Then Verify error message "Amendment Reason: Validation Error: Value is required"

  @UAT_TCS-01.08.6 @SUC:01-08
  Scenario: UAT_TCS 01.08.6-To verify the process of validation error during amendment
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "V0024699"
    Then Click search : id "SearchForm:j_idt42"
    Then Click Cancel "OrganisationSummaryDetails:Cancel"
    Then Verify exit from update page

  @UAT_TCS-01.08.8 @SUC:01-08
  Scenario: UAT_TCS 01.08.9-To verify the process of rejecting amendment task
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "V0024699"
    Then Click search : id "SearchForm:j_idt42"
    Then Enter Organization name "<name>"
    Then Select reason for amendment : organization "<amendmentReason>"
    Then Click save "OrganisationSummaryDetails:submitTaxpayerRegistration"
    Then Verify save success message "Processing Completed - Reference Number"
    Then Obtain reference number "<SuccessMessage>"
    Then Open CRM and close modal

  @UAT_TCS-01.08.9 @SUC:01-08
  Scenario Outline: UAT_TCS 01.08.9-To verify the process of rejecting amendment task
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "V0028293"
    Then Click search : id "SearchForm:j_idt42"
    Then Enter Organization name "<name>"
    Then Select reason for amendment : organization "<amendmentReason>"
    Then Click save "OrganisationSummaryDetails:submitTaxpayerRegistration"
    Then Verify save success message "Processing Completed - Reference Number"
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
      | name               | amendmentReason | Notes        | SuccessMessage                          |
      | Codei technologies | Change of Name  | Invalid data | Processing Completed - Reference Number |

  @UAT_TCS-01.07.11 @SUC:01-08
  Scenario Outline: UAT_TCS 01.07.11-To verify the process of Amending when taxpayer status is 'Pending Approval'
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "V0028293"
    Then Click search : id "SearchForm:j_idt42"
    Then Enter Organization name "<name>"
    Then Select reason for amendment : organization "<amendmentReason>"
    Then Click save "OrganisationSummaryDetails:submitTaxpayerRegistration"
    Then Verify error message "Taxpayer has an existing application which is pending approval, hence the request cannot be processed at this time."
    Examples:
      | name          | amendmentReason |
      | Bakam Systems | Change of Name  |

  @UAT_TCS-01.08.12 @SUC:01-08
  Scenario: UAT_TCS 01.08.12-To verify the process of Amending when taxpayer status is 'De-Registered'
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "C0015817"
    Then Click search : id "SearchForm:j_idt42"
    Then Verify no data is found in table

  @UAT_TCS-01.08.13 @SUC:01-08
  Scenario: UAT_TCS 01.08.13-To verify the process of Amending when taxpayer and other relationships contains same TIN number
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "V0024699"
    Then Click search : id "SearchForm:j_idt42"
    Then Click relationships tab : organization
    Then Click add "OrganisationSummaryDetails:organisationAccordion:relationshipHandler:AddRelationshipDetails"
    Then Switch to frame
    Then Select relationship type
    Then Click find "RelationshipDetails:FindTin"
    Then Switch to default
    Then Switch to frame 2
    Then Search for relationship with same tin "V0024699"
    Then Switch to frame
    Then Click ok: xpath "//*[@id='RelationshipDetails:Ok']"
    Then Verify error message "The selected taxpayer cannot be added to itself as any other relationship."

  @UAT_TCS-01.08.14 @SUC:01-08
  Scenario: UAT_TCS 01.08.14-To verify the process of Transferring Property
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "V0028293"
    Then Click search : id "SearchForm:j_idt42"
    Then Select reason for amendment : organization "Change of property"
    Then Click properties tab : organization
    Then Obtain property id of first property item in list : organization
    Then Click table column "//*[@id='OrganisationSummaryDetails:organisationAccordion:propertyTableHandler_data']/tr/td[1]"
    Then Click transfer : organization
    Then Switch to frame
    Then Click find "TransferPropertyDetails:FindTin"
    Then Switch to default
    Then Switch to frame 2
    Then Search for taxpayer to transfer property to "V0028293"
    Then Switch to frame
    Then Click ok: xpath "//*[@id='TransferPropertyDetails:Ok']"
    Then Click save "OrganisationSummaryDetails:submitTaxpayerRegistration"
    Then Verify save success message "Processing Completed - Reference Number"
    Then Go to registration > manage taxpayer > update taxpayer
    Then Enter tin as "V0028293"
    Then Click search : id "SearchForm:j_idt42"
    Then Click properties tab : organization
    Then Validate transfer of property by checking property id : organization