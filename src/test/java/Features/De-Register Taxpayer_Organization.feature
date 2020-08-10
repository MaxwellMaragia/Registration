Feature: [SUC:01-10] De-Register Taxpayer_Organization

  Background:
    Given Browser is launched and trips URL loaded in address bar
    And User logged in as revenue officer
      | tripsuser | Passw0rd |

  @UAT_TCS-01.16.3
  Scenario: UAT_TCS-01.16.3-To verify the process of not finding a Taxpayer for Deregistration
    Then Click on registration > manage taxpayer > deregister tax
    Then Search for tin "C0015817"
    Then Click search : id "SearchForm:j_idt42"
    Then Verify no data is found in table

  @UAT_TCS-01.16.4
  Scenario: UAT_TCS-01.16.4-To verify the process of checking Validation error during Deregistration
    Then Click on registration > manage taxpayer > deregister tax
    Then Search for tin "V0028293"
    Then Click search : id "SearchForm:j_idt42"
    Then Click table column "//*[@id='DeregisterRegime:deregTable_data']/tr[1]/td[3]"
    Then Enter EDD date
    Then Click de register
    Then Verify error message "Reason: Validation Error: Value is required."


  @UAT_TCS-01.16.5
  Scenario: UAT_TCS-01.16.5-To verify the process of Abandoning Deregistration process
    Then Click on registration > manage taxpayer > deregister tax
    Then Search for tin "V0028293"
    Then Click search : id "SearchForm:j_idt42"
    Then Click Cancel "DeregisterRegime:Cancel"
    Then Verify abandon process "http://18.202.88.7:8001/trips-ui/faces/login/Welcome.xhtml"