package Steps;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class stepDefinitions extends BaseClass {

    public String ReferenceNumber;
    public String propertyID;
    public String organizationPropertyID;

    //--------------------Maxwell Maragia---------------------------------------------------------//
    //--------------------UTILS-------------------------------------------------------------------//
    @Given("^Browser is launched and trips URL loaded in address bar$")
    public void loadTripsLink() throws Throwable {
        driver.get(Pro.getProperty("MRA_BackOffice_URL"));
        driver.manage().window().maximize();
    }

    @Given("^Open portal URL$")
    public void loadPortalLink() throws Throwable {
        driver.get(Pro.getProperty("PORTAL_URL"));
        driver.manage().window().maximize();
    }

    @And("^User logged in as revenue officer$")
    public void login_as_revenue_officer(DataTable data) throws Throwable {
        List<List<String>> obj = data.asLists();
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("BackOffice_UserName_ID")))).sendKeys(obj.get(0).get(0));
        driver.findElement(By.id(Pro.getProperty("BackOffice_Password_ID"))).sendKeys(obj.get(0).get(1));
        driver.findElement(By.id(Pro.getProperty("BackOffice_Login_ID"))).click();
    }

    //login to taxpayer portal
    @Then("^Login to portal$")
    public void portalLogin(DataTable data) throws Throwable {
        BaseClass.waitForPageToLoad();
        List<List<String>> obj = data.asLists();
        driver.findElement(By.id(Pro.getProperty("USERNAME_ID"))).sendKeys(obj.get(0).get(0));
        driver.findElement(By.id(Pro.getProperty("PASSWORD_ID"))).sendKeys(obj.get(0).get(1));
        driver.findElement(By.id(Pro.getProperty("LOGIN_ID"))).click();
    }

    @Then("^Click next \"([^\"]*)\"$")
    public void clickNext(String NextButtonId) {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.id(NextButtonId)).click();
    }

    @Then("^Click search$")
    public void click_search() {
        driver.findElement(By.id("SearchForm:j_idt42")).click();
    }

    @Then("^Click table column \"([^\"]*)\"$")
    public void click_table_column(String ColumnXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ColumnXpath))).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER);
    }

    @Then("^Click continue \"([^\"]*)\"$")
    public void click_continue(String continueID) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id(continueID)).click();
    }

    @Then("^Switch to frame 2$")
    public void shift_focus_to_second_frame() throws Throwable {
        Thread.sleep(2000);
        driver.switchTo().frame(1);
    }

    @Then("^Switch to frame$")
    public void shift_focus_to_frame() throws Throwable {
        Thread.sleep(7000);
        WebElement Iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(Iframe);
    }

    @Then("^Switch to default$")
    public void switch_to_default() throws Throwable {
        Thread.sleep(1000);
        driver.switchTo().defaultContent();
    }

    @Then("^Verify error message \"([^\"]*)\"$")
    public void verify_error_message(String error) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + error + "')]")));
        if (errorMessage.isDisplayed()) {
            //This will scroll the page till the element is found
            System.out.println("Error message ('" + error + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @Then("^Verify no data is found in table$")
    public void verify_no_data_is_found_in_table() throws Throwable {
        WebElement noDataXpath = driver.findElement(By.xpath("//td[contains(text(),'No records found.')]"));
        if (noDataXpath.isDisplayed()) {
            Assert.assertTrue("No data found in table", true);
        } else {
            Assert.assertFalse("Data found in table", false);
        }
    }

    @Then("^Click Cancel \"([^\"]*)\"$")
    public void click_cancel(String cancelID) {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.id(cancelID)).click();
    }

    @Then("^Verify save success message \"([^\"]*)\"$")
    public void verify_success_message(String Message) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,20);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + Message + "')]")));
        if (successMessage.isDisplayed()) {
            System.out.println("Success message ('" + Message + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @Then("^Click add \"([^\"]*)\"$")
    public void click_add(String addID) throws Throwable {
        Thread.sleep(1000);
        BaseClass.waitForPageToLoad();
        driver.findElement(By.id(addID)).click();
    }

    @And("^Click ok: xpath \"([^\"]*)\"$")
    public void click_ok_xpath(String okXpath) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath(okXpath)).click();
        Thread.sleep(5000);
    }

    @Then("^Click find \"([^\"]*)\"$")
    public void click_find(String findID) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id(findID)).click();
    }

    @Then("^Click search : id \"([^\"]*)\"$")
    public void click_search_id(String searchID) throws Throwable {
        driver.findElement(By.id(searchID)).click();
    }

    //-------------------------END UTILS----------------------------------------------------------------------------------//
    //-------------------------Submit Registration Application------------------------------------------------------------//

    @Then("^Click on registration > register taxpayer > register individual$")
    public void openIndividualRegistration() {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[1]/ul/li[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[1]")).click();
    }

    @Then("^Click on registration > register taxpayer > register organization$")
    public void openOrganizationRegistration() {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[1]/ul/li[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[2]")).click();
    }

    @Then("^Select category : organization \"([^\"]*)\"$")
    public void select_category_organization(String category) throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:LegalStatus\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + category + "')]")).click();
    }

    @Then("^Select category as \"([^\"]*)\"$")
    public void select_category_individual(String category) throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:LegalStatusInd\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + category + "')]")).click();
    }

    @Then("^Select title as \"([^\"]*)\"$")
    public void select_title(String title) throws Throwable {

        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:Title\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + title + "')]")).click();
    }

    @Then("^Enter first name \"([^\"]*)\" and last name \"([^\"]*)\"$")
    public void enter_names(String firstName, String lastName) throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.id("RegisterIndividual:FirstName")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("RegisterIndividual:FirstName")).sendKeys(firstName);
        Thread.sleep(1000);
        driver.findElement(By.id("RegisterIndividual:LastName")).clear();
        Thread.sleep(1000);
        driver.findElement(By.id("RegisterIndividual:LastName")).sendKeys(lastName);
        Thread.sleep(2000);
    }

    @Then("^Select gender \"([^\"]*)\"$")
    public void select_gender(String gender) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:Gender\"]/div[3]")).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
        //        driver.findElement(By.xpath("//li[contains(text(),'" + gender + "')]")).click();
    }

    @Then("^Select marital status \"([^\"]*)\"$")
    public void select_marital_status(String maritalStatus) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:MaritalStatus\"]/div[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'" + maritalStatus + "')]")).click();
    }

    @Then("^Enter date of birth \"([^\"]*)\"$")
    public void enter_date_of_birth(String dob) throws Throwable {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('RegisterIndividual:individualAccordion:DOB_input').setAttribute('value', '" + dob + "')");
    }

    @Then("^Select nationality \"([^\"]*)\"$")
    public void select_nationality(String nationality) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:Nationality\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'Kenya')]")).click();
    }

    @Then("^Select country of residence \"([^\"]*)\"$")
    public void select_country_of_residence(String nationality) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:CountryOfResidence\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + nationality + "')]")).click();
    }

    @Then("^Enter place of birth \"([^\"]*)\"$")
    public void enter_place_of_birth(String placeOfBirth) throws Throwable {
        driver.findElement(By.id("RegisterIndividual:individualAccordion:PlaceOfBirth")).sendKeys(placeOfBirth);
    }

    @Then("^Select reason for tin application \"([^\"]*)\"$")
    public void select_reason_for_tin_application_individual(String reasonForApplication) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:ReasonForTIN\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + reasonForApplication + "')]")).click();
    }

    @Then("^Select reason for tin application : organization \"([^\"]*)\"$")
    public void select_reason_for_tin_application_organization(String reasonForApplication) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion:ReasonForTIN\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + reasonForApplication + "')]")).click();
    }

    @Then("^Select occupation status \"([^\"]*)\"$")
    public void select_occupation_status(String occupationStatus) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:occupationStatus\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + occupationStatus + "')]")).click();
    }

    @Then("^Select main category \"([^\"]*)\"$")
    public void select_main_category(String mainCategory) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:MainCategory\"]/div[3]")).click();
        Thread.sleep(1500);
        //driver.findElement(By.xpath("//li[contains(text(),'" + mainCategory + "')]")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select Precise category \"([^\"]*)\"$")
    public void select_precise_category(String preciseCategory) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:PreciseCategory\"]/div[3]")).click();
        Thread.sleep(1500);
        //driver.findElement(By.xpath("//li[contains(text(),'" + preciseCategory + "')]")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
    }

    @And("^Click occupation - business interest tab$")
    public void click_occupation_business_interest_tab() {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[4]/a")).click();
    }

    @And("^Click on identification tab$")
    public void click_on_identification_tab() {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[2]/a")).click();
    }

    @And("^Click directors tab$")
    public void click_on_directors_tab() {
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion\"]/ul/li[14]/a")).click();
    }

    @Then("^Select identification \"([^\"]*)\"$")
    public void select_identification(String identification) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"Identification:IdentificationType\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + identification + "')]")).click();
    }

    @Then("^Enter identification number \"([^\"]*)\"$")
    public void enter_identification_number(String idNumber) throws Throwable {
        Thread.sleep(4000);
        driver.findElement(By.id("Identification:IdentificationNumber")).sendKeys(idNumber);
    }

    @Then("^Enter date of issue \"([^\"]*)\"$")
    public void enter_date_of_issue(String dateOfIssue) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('Identification:IdentificationEffectiveDate_input').setAttribute('value', '" + dateOfIssue + "')");
    }

    @Then("^Enter expiry date \"([^\"]*)\"$")
    public void enter_expiry_date(String expiryDate) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('Identification:ExpiryDate_input').setAttribute('value', '" + expiryDate + "')");
    }

    @And("^Click attachments tab$")
    public void click_attachments_tab() throws Throwable {
        Thread.sleep(2500);
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[10]/a")).click();
    }

    @And("^Click attachments tab : organization$")
    public void click_attachments_tab_organization_registration() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion\"]/ul/li[8]/a")).click();
    }

    @Then("^Select document type \"([^\"]*)\"$")
    public void select_document_type(String documentType) throws Throwable {
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"AttachmentDetails:DocType\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + documentType + "')]")).click();
    }

    @Then("^Enter document number \"([^\"]*)\"$")
    public void enter_document_number(String idNumber) throws Throwable {
        driver.findElement(By.id("AttachmentDetails:Reference")).sendKeys(idNumber);
    }

    @Then("^Browse for attachment \"([^\"]*)\"$")
    public void browse_for_attachment(String path) throws Throwable {
        driver.findElement(By.id("AttachmentDetails:AttachmentPath_input")).sendKeys(path);
    }

    @Then("^Click save \"([^\"]*)\"$")
    public void click_save(String saveID) throws Throwable {
        driver.findElement(By.id(saveID)).click();
    }

    @Then("^Click address tab$")
    public void click_address_tab_individual() {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[5]/a")).click();
    }

    @Then("^Click address tab : organization$")
    public void click_address_tab_organization() {
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion\"]/ul/li[2]/a")).click();
    }

    @Then("^Click relationships tab$")
    public void click_relationship_tab_individual() {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[14]/a")).click();
    }

    @Then("^Click relationships tab : organization$")
    public void click_relationship_tab_organization() {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion\"]/ul/li[10]/a")).click();
    }

    @Then("^Select relationship type$")
    public void select_relationship_type() throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"RelationshipDetails:RelationshipType\"]/div[3]")).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Search for relationship with same tin \"([^\"]*)\"$")
    public void search_for_relationship_with_same_tin(String tin) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.id("SearchForm:accountNumber")).sendKeys(tin);
        driver.findElement(By.id("SearchForm:j_idt21")).click();
    }

    @Then("^Select address type \"([^\"]*)\"$")
    public void select_address_type(String addressType) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"AddressDetails:AddressType\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + addressType + "')]")).click();
    }

    @Then("^Uncheck primary indicator checkbox$")
    public void uncheck_primary_indicator_checkbox() throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id("AddressDetails:PrimaryAddressType")).click();
    }

    @Then("^Then enter town \"([^\"]*)\"$")
    public void then_enter_town(String town) throws Throwable {
        Thread.sleep(2500);
        driver.findElement(By.id("AddressDetails:City")).sendKeys(town);
    }

    @Then("^Select region \"([^\"]*)\" and district \"([^\"]*)\"$")
    public void select_region_and_district(String region, String district) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"AddressDetails:PostalRegion\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + region + "')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"AddressDetails:District\"]/div[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'" + district + "')]")).click();
    }

    @Then("^Click Contact methods tab$")
    public void click_contact_methods_tab() {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[6]/a")).click();
    }

    @Then("^Select purpose$")
    public void select_purpose() throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"ContactDetails:Purpose\"]/div[3]")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Enter contact details \"([^\"]*)\"$")
    public void enter_contact_details(String email) {
        driver.findElement(By.id("ContactDetails:ContactMethodDetailForWeb")).sendKeys(email);
    }

    @Then("^Click employment details tab$")
    public void click_employment_details_tab() throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[3]/a")).click();
    }

    @Then("^Enter employers name \"([^\"]*)\" and employment start date \"([^\"]*)\"$")
    public void enter_employers_details(String employersName, String startDate) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.id("EmploymentDetails:employersName")).sendKeys(employersName);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('EmploymentDetails:StartDate_input').setAttribute('value', '" + startDate + "')");
    }

    @Then("^Enter Organization name \"([^\"]*)\"$")
    public void select_organization_name(String name) throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.id("OrganisationSummaryDetails:LegalName")).clear();
        Thread.sleep(2000);
        driver.findElement(By.id("OrganisationSummaryDetails:LegalName")).sendKeys(name);
    }

    @Then("^Select Account end day \"([^\"]*)\"$")
    public void select_account_end_day(String endDay) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion:AccountYearEndDateDD\"]/div[3]")).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select Account end month \"([^\"]*)\"$")
    public void select_account_end_month(String endMonth) throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion:AccountYearEndDateMM\"]/div[3]")).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Enter source of capital \"([^\"]*)\"$")
    public void enter_source_of_capital(String sourceOfCapital) {
        driver.findElement(By.id("OrganisationSummaryDetails:organisationAccordion:SourceOfCapital")).sendKeys(sourceOfCapital);
    }

    @Then("^Select place of incorporation \"([^\"]*)\"$")
    public void select_place_of_incorporation(String nationality) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion:PlaceOfIncorporation\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + nationality + "')]")).click();
    }

    @Then("^Enter director start date \"([^\"]*)\"$")
    public void enter_director_start_date(String directorStartDate) throws Throwable {
        Thread.sleep(2000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('DirectorsDetails:PositionHeldSince_input').setAttribute('value', '" + directorStartDate + "')");
    }

    @Then("^Select business sector$")
    public void select_business_sector() throws Throwable {
        //driver.findElement(By.id("OrganisationSummaryDetails:organisationAccordion:businessDetailsHandler:AddBusinessSD")).click();
        driver.findElement(By.xpath("//*[@id=\"BusinessSectorDetails:BusinessCode\"]/div[3]")).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Click primary indicator checkbox \"([^\"]*)\"$")
    public void click_primary_indicator_checkbox(String primaryID) throws Throwable {
        driver.findElement(By.id(primaryID)).click();
    }

    @Then("^Enter E-permit number \"([^\"]*)\" and E-permit type$")
    public void enter_epermit_number_something_and_epermit_type(String enumber) throws Throwable {
        driver.findElement(By.id("Identification:ePermitNumber")).sendKeys(enumber);
        driver.findElement(By.xpath("//*[@id=\"Identification:ePermitType\"]/div[3]")).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select country of issue \"([^\"]*)\"$")
    public void select_country_of_issue(String countryOfIssue) throws Throwable {
        driver.findElement(By.xpath("//*[@id=\"Identification:CountryOfIssue\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + countryOfIssue + "')]")).click();
    }

    @Then("^Go to registration > manage taxpayer > update taxpayer$")
    public void go_to_registration_manage_taxpayer_update_taxpayer() throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[1]/ul/li[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[2]")).click();
    }

    @Then("^Enter tin as \"([^\"]*)\"$")
    public void enter_tin_as(String tin) throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.id("SearchForm:accountNumber")).sendKeys(tin);
    }

    @Then("^Select reason for amendment \"([^\"]*)\"$")
    public void select_reason_for_amendment(String amendmentReason) throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:AmmendmentReason\"]/div[3]")).click();
        Thread.sleep(2500);
        //        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //        WebElement reason = driver.findElement(By.xpath("//li[contains(text(),'" + amendmentReason + "')]"));
        //        reason.click();

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select reason for amendment : organization \"([^\"]*)\"$")
    public void select_reason_for_amendment_organization(String amendmentReason) throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion:AmendmentReason\"]/div[3]")).click();
        Thread.sleep(2500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Obtain reference number \"([^\"]*)\"$")
    public void split_string_to_obtain_reference_number(String SuccessMessage) {
        //get full message
        String FullMessage = driver.findElement(By.xpath("//span[contains(text(),'" + SuccessMessage + "')]")).getText();
        System.out.println(FullMessage);
        //Processing Completed - Reference Number - CRAL/000001959/2020
        ReferenceNumber = FullMessage.substring(41);
        System.out.println(ReferenceNumber);
    }

    @Then("^Open CRM and close modal$")
    public void open_crm_and_close_modal() throws Throwable {

        driver.get(Pro.getProperty("CRM_URL"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement specificframe = (driver.findElement(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame__ID"))));
        driver.switchTo().frame(specificframe);
        WebDriverWait CloseWindow = new WebDriverWait(driver, 60);
        CloseWindow.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame_Close_ID")))).click();
    }

    @Then("^Click on registration application link$")
    public void click_on_accounting_application_link() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("Cases_Management_Dropdown_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id("tbg_registrationapplication")).click();
    }

    @Then("^switch to frame$")
    public void switch_to_frame() throws Throwable {
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement specificframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("NextStage_Frame_ID"))));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);

    }

    @Then("^search for reference number$")
    public void search_for_reference_number() throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.id(Pro.getProperty("Search_Field_ID"))).sendKeys(ReferenceNumber);
        driver.findElement(By.id(Pro.getProperty("Search_Field_Submit_ID"))).click();
    }

    @Then("^Click on reference number$")
    public void click_on_reference_number() {
        WebElement elementLocator = driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_Select_ReffNo_XPATH")));
        Actions actions = new Actions(driver);
        actions.doubleClick(elementLocator).perform();
        driver.switchTo().defaultContent();
    }

    @Then("^Click next stage button$")
    public void Click_next_stage() throws Throwable {

        driver.switchTo().frame("contentIFrame1");
        //        Thread.sleep(15000);
        //        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //        driver.findElement(By.id("stageAdvanceActionContainer")).click();

        Thread.sleep(20000);
        WebDriverWait wait = new WebDriverWait(driver, 40);
        WebElement nextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stageAdvanceActionContainer")));
        nextButton.click();
        Thread.sleep(12000);
        driver.switchTo().defaultContent();
    }

    @Then("^approve transaction$")
    public void approve_transaction() throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(9000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.xpath("//*[@id=\"header_process_tbg_approvaloutcome3\"]/div[1]"));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Thread.sleep(5000);
        } else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }
    }

    @And("^Reject transaction$")
    public void RejectTransaction() throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(10000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.xpath("//*[@id=\"header_process_tbg_approvaloutcome3\"]/div[1]"));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }
    }

    @And("^clicks Decline from the dropdown$")
    public void clicks_Decline_from_the_dropdown() throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(10000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        WebElement Outcome = driver.findElement(By.xpath("//*[@id=\"header_process_tbg_approvaloutcome3\"]/div[1]"));
        WebElement hasLoaded = driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if (hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }
    }

    @Then("^Enter Outcome Notes \"([^\"]*)\"$")
    public void enter_Outcome_Notes(String Notes) throws Throwable {
        Thread.sleep(5000);
        Actions action1 = new Actions(driver);
        WebElement element1 = driver.findElement(By.id((Pro.getProperty("Individual_NextStage_RefNum_Reject_OutComeNotes_ID"))));
        action1.sendKeys(element1, Notes).build().perform();
        Thread.sleep(5000);
    }

    @Then("^Enter Outcome Reason for Taxpayer accounting$")
    public void enter_Outcome_Reason_for_Taxpayer_accounting() throws Throwable {
        WebElement specificframe = driver.findElement(By.id("WebResource_tbg_rejectionRefernceData"));
        driver.switchTo().frame(specificframe);
        WebElement dropDown = driver.findElement(By.id("viewoption"));
        //WebElement dropDown = driver.findElement(By.xpath("//*[@id=\"statuscode_i\"]"));
        dropDown.click();
        driver.findElement(By.xpath("//option[text()='Duplicate entity found']")).click();
    }

    @Then("^Click save CRM$")
    public void ClickSaveCRM() throws Throwable {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("tbg_registrationapplication|NoRelationship|Form|Mscrm.Form.tbg_registrationapplication.Save")).click();
        //    	driver.findElement(By.id("tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save")).click();
        //    	driver.findElement(By.xpath(Pro.getProperty("//*[@id=\"tbg_accountingapplication|NoRelationship|Form|Mscrm.Form.tbg_accountingapplication.Save\"]"))).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Then("^Status should be \"([^\"]*)\"$")
    public void Verify_status_from_CRM(String Status) throws Throwable {
        driver.switchTo().frame("contentIFrame1");
        Thread.sleep(5000);
        String text = driver.findElement(By.xpath("//*[@id='Status_label']")).getText();
        System.out.println(text);
        if (text.contains(Status)) {
            Assert.assertTrue("Approved", true);
        } else {
            Assert.fail("Approval failed");
        }
        Thread.sleep(2000);
    }

    @Then("^Check if changes reflect \"([^\"]*)\"$")
    public void check_if_changes_reflect_something(String firstName) throws Throwable {
        BaseClass.waitForPageToLoad();
        String original = driver.findElement(By.id("RegisterIndividual:FirstName")).getText();
        if (original.equals(firstName)) {
            Assert.assertTrue("Changes have reflected", true);
        } else {
            Assert.assertFalse("Changes not reflected", false);
        }
    }

    @Then("^Click properties tab$")
    public void click_properties_tab_individual() throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion\"]/ul/li[16]/a")).click();
    }

    @Then("^Click properties tab : organization$")
    public void click_properties_tab_organization() throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion\"]/ul/li[12]/a")).click();
    }


    @Then("^Obtain property id of first property item in list$")
    public void obtain_property_id_of_first_property_item_in_list() throws Throwable {
        Thread.sleep(2000);
        propertyID = driver.findElement(By.xpath("//*[@id=\"RegisterIndividual:individualAccordion:propertyTableHandler_data\"]/tr[1]/td[2]")).getText();
    }

    @Then("^Obtain property id of first property item in list : organization$")
    public void obtain_property_id_of_first_property_item_in_list_organization() throws Throwable {
        Thread.sleep(2000);
        organizationPropertyID = driver.findElement(By.xpath("//*[@id=\"OrganisationSummaryDetails:organisationAccordion:propertyTableHandler_data\"]/tr/td[2]")).getText();
    }

    @Then("^Click transfer$")
    public void click_transfer() throws Throwable {
        Thread.sleep(1500);
        driver.findElement(By.id("RegisterIndividual:individualAccordion:propertyTableHandler:TransferProperty")).click();
    }

    @Then("^Click transfer : organization$")
    public void click_transfer_organization() throws Throwable {
        Thread.sleep(1500);
        driver.findElement(By.id("OrganisationSummaryDetails:organisationAccordion:propertyTableHandler:TransferProperty")).click();
    }

    @Then("^Search for taxpayer to transfer property to \"([^\"]*)\"$")
    public void search_for_taxpayer_to_transfer_property_to(String tin) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.id("SearchForm:accountNumber")).sendKeys(tin);
        driver.findElement(By.id("SearchForm:j_idt42")).click();
    }

    @Then("^Validate transfer of property by checking property id$")
    public void validate_transfer_of_property_by_checking_property_id() throws Throwable {
        Thread.sleep(2000);
        WebElement property = driver.findElement(By.xpath("//td[contains(text(),'" + propertyID + "')]"));
        if (property.isDisplayed()) {
            System.out.println("Property ID matches : Transfer was successful");
            Assert.assertTrue("Property ID matches : Transfer was successful", true);
        } else {
            Assert.assertFalse("Property ID does not match", false);
        }
    }

    @Then("^Validate transfer of property by checking property id : organization$")
    public void validate_transfer_of_property_by_checking_property_id_organization() throws Throwable {
        Thread.sleep(2000);
        WebElement property = driver.findElement(By.xpath("//td[contains(text(),'" + organizationPropertyID + "')]"));
        if (property.isDisplayed()) {
            System.out.println("Property ID matches : Transfer was successful");
            Assert.assertTrue("Property ID matches : Transfer was successful", true);
        } else {
            Assert.assertFalse("Property ID does not match", false);
        }
    }

    @Then("^Verify exit from update page$")
    public void verifyExitFromUpdatePage() throws Throwable {
        BaseClass.waitForPageToLoad();
        WebElement pendingTasksDiv = driver.findElement(By.xpath("//*[@id=\"welcomeForm:pendingTasksAccordion\"]/div[1]"));
        if (pendingTasksDiv.isDisplayed()) {
            System.out.println("Cancel was successful. No changes made");
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse("Exit not successful", false);
        }
    }


    //................................Print Organization Reports................................................//
    @Then("^Click reporting > reports$")
    public void goToReportingScreen() throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[4]/ul/li[1]")).click();
    }

    @Then("^Select report to print \"([^\"]*)\"$")
    public void select_report_to_print(String reportType) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + reportType + "']"))).click();

    }

    @Then("^Select report file type \"([^\"]*)\"$")
    public void select_report_file_type(String reportFormat) throws Throwable {
        BaseClass.waitForPageToLoad();
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:ReportFormat\"]/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'" + reportFormat + "')]")).click();
    }

    @Then("^Click run report \"([^\"]*)\"$")
    public void click_run_report(String buttonID) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id(buttonID)).click();
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                // File has been found, it can now be deleted:
                dirContents[i].delete();
                return true;
            }
        }
        return false;
    }

    @Then("^Verify file \"([^\"]*)\" has been downloaded in downloads directory \"([^\"]*)\"$")
    public void verify_file_has_been_downloaded_in_downloads_directory(String fileName, String downloadPath) throws Throwable {
        Thread.sleep(3000);
        if (isFileDownloaded(downloadPath, fileName)) {
            System.out.println(fileName + ": has been downloaded");
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(fileName + ": has not been downloaded", false);
        }
    }

    @Then("^Select tax office \"([^\"]*)\"$")
    public void select_tax_office(String taxOffice) throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE\"]/div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[contains(text(),'" + taxOffice + "')]")).click();
    }

    @Then("^Select business sector \"([^\"]*)\"$")
    public void select_business_sector(String businessSector) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + businessSector + "')]")).click();
    }

    @Then("^Select district \"([^\"]*)\"$")
    public void select_district(String district) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:DISTRICT\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + district + "')]")).click();
    }

    @Then("^Select source \"([^\"]*)\"$")
    public void select_source(String source) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:SOURCE\"]/div[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + source + "')]")).click();
    }

    @Then("^Select reason \"([^\"]*)\"$")
    public void select_reason(String reason) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:REASON\"]/div[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'" + reason + "')]")).click();
    }

    @Then("^Select tax type \"([^\"]*)\"$")
    public void select_tax_type(String taxType) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_TYPE\"]/div[3]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//li[contains(text(),'" + taxType + "')]")).click();
    }

    @Then("^Select taxpayer category \"([^\"]*)\"$")
    public void select_taxpayer_category(String taxpayerCategory) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category\"]/div[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),'" + taxpayerCategory + "')]")).click();
    }

    @Then("^Select tax office from \"([^\"]*)\"$")
    public void select_taxoffice_from(String taxOfficeFrom) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:STATION_FROM\"]/div[3]")).click();
        Thread.sleep(3000);
        //driver.findElement(By.xpath("//li[contains(text(),'" + taxOfficeFrom + "')]")).click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER);
    }

    @Then("^Select tax office to \"([^\"]*)\"$")
    public void select_taxoffice_to(String taxOfficeTo) throws Throwable {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"frmReportDetails:STATION_TO\"]/div[3]")).click();
        Thread.sleep(3000);
        //driver.findElement(By.xpath("//li[contains(text(),'" + taxOfficeTo + "')]")).click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER);
    }

    @Then("^Verify report page is abandoned$")
    public void verify_report_page_is_abandoned() throws Throwable {
        BaseClass.waitForPageToLoad();
        WebElement reportsListHeading = driver.findElement(By.xpath("//label[contains(text(),'Reports List')]"));
        if(reportsListHeading.isDisplayed())
        {
            Assert.assertTrue("Report page abandoned",true);
        }
        else{
            Assert.fail();
        }
    }

    //.....................De-register taxpayer......................................//
    @Then("^Click on registration > manage taxpayer > deregister tax$")
    public void click_on_registration_manage_taxpayer_deregister_tax() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[1]/a"))).click();

        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[1]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[6]/a")).click();
    }

    @Then("^Search for tin \"([^\"]*)\"$")
    public void search_for_tin(String tin) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:accountNumber"))).sendKeys(tin);
    }

    @Then("^Enter EDD date$")
    public void enter_edd_date(){
        driver.findElement(By.id("DeregisterRegime:EDD_input")).sendKeys(Keys.ENTER);
    }

    @Then("^Click de register$")
    public void click_de_register() throws Throwable {
       driver.findElement(By.id("DeregisterRegime:deregisterBtn")).click();
    }

    @Then("^Verify abandon process \"([^\"]*)\"$")
    public void displayWelcomePage(String url) throws Throwable {
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, url );
    }


    //......................SUC:01-20..........................................................//
    @Then("^Click on registration > register taxpayer > process registration application$")
    public void accessProcessRegistrationScreen(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[1]/a"))).click();

        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[1]/ul/li[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"sub1\"]/ul/li[4]/a")).click();
    }

    @Then("^Enter registration application reference number as \"([^\"]*)\"$")
    public void enter_registration_application_reference_number(String refNumber)  {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:applicationReference"))).sendKeys(refNumber);
    }

    @Then("^Click view$")
    public void click_view() throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id("SearchForm:j_id19")).click();
    }

    @Then("^Verify reference number, applicant name, application status and tpin fields$")
    public void verify_reference_number_applicant_name_application_status_and_tpin_fields() throws Throwable {
         WebDriverWait wait = new WebDriverWait(driver,30);
         WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("RegisterIndividual:FirstName")));
         Assert.assertFalse(firstNameField.isEnabled());

         WebElement referenceNumberField = driver.findElement(By.id("RegisterIndividual:ApplicationReference"));
         Assert.assertFalse(referenceNumberField.isEnabled());

         WebElement applicationStatusField = driver.findElement(By.id("RegisterIndividual:StatusInd"));
         Assert.assertFalse(applicationStatusField.isEnabled());

         WebElement tinField = driver.findElement(By.id("RegisterIndividual:TINInd"));
         Assert.assertFalse(tinField.isEnabled());
    }

    @Then("^Verify reference number, organization name, application status and tpin fields$")
    public void verify_reference_number_organization_name_application_status_and_tpin_fields() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver,60);
        WebElement organizationNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OrganisationSummaryDetails:LegalName")));
        Assert.assertFalse(organizationNameField.getAttribute("readonly"),false);

        WebElement referenceNumberField = driver.findElement(By.id("OrganisationSummaryDetails:ApplicationReference"));
        Assert.assertFalse(referenceNumberField.getAttribute("readonly"),false);

        WebElement applicationStatusField = driver.findElement(By.id("OrganisationSummaryDetails:Status"));
        Assert.assertFalse(applicationStatusField.getAttribute("readonly"),false);

        WebElement tinField = driver.findElement(By.id("OrganisationSummaryDetails:TIN"));
        Assert.assertFalse(tinField.getAttribute("readonly"),false);
    }
}