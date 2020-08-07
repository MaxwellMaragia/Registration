Feature: SUC:01-17-Amend Taxpayer - Portal

  Scenario: UAT_TCS 04.03.1-To verify the process of Amending Taxpayer Details from Taxpayer portal
    Given User launches taxpayer portal
    And Login to portal as applicant
    | v-bakam@microsoft.com | Passw0rd@123 |
    Then Click next
    Then Click new "btnAdd"
    Then Select contact purpose "Business"
    Then Click add "btnSave"
    Then Click save as draft
    Then Click no in the exit confirmation dialogue
    Then Verify portal save success message "Application data saved successfully!"
    Then Click next
    Then Click new "id_newAddress"
    Then Select address type as "Local Postal Address"
    Then Enter town as "Lilongwe"
    Then Select region as "Central Region"
    Then Select district "Lilongwe"
    Then Click add "btnSave"
    Then Click save as draft
    Then Click no in the exit confirmation dialogue
    Then Click next
    Then Click new "id_newAddress"

  //*[@id="id_IndForm"]/form-wizard/div/div/div[2]/div[3]/button
  //*[@id="id_IndForm"]/form-wizard/div/div/div[2]/div[3]/button
  //*[@id="id_IndForm"]/form-wizard/div/div/div[2]/div[3]/button



    