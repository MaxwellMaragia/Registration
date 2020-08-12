Feature: [SUC:01-20] Track Registration application_Organization

  Background:
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |

  @SUC:01-20
  Scenario: UAT_TCS-03.06.1-To verify the process of verifying all the fields and buttons in the Track Registration Application screen & UAT_TCS-03.06.2-To verify the process of Tracking Registration application
    Then Click on registration > register taxpayer > process registration application
    Then Enter registration application reference number as "ARN/00020791/2020"
    Then Click search : id "SearchForm:j_idt42"
    Then Click view
    Then Verify reference number, organization name, application status and tpin fields


  @SUC:01-20
  Scenario: UAT_TCS-03.06.3-To verify the process of not finding an application number
    Then Click on registration > register taxpayer > process registration application
    Then Enter registration application reference number as "ARN/18025/2019"
    Then Click search : id "SearchForm:j_idt42"
    Then Verify no data is found in table