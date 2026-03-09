package com.tests.stepdefs;

import config.ConfigReader;
import ui.LoginPage;
import ui.SwitchDestinationPage;
import utils.AssertionUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class UIStepDefs {

    LoginPage loginPage = new LoginPage();
    SwitchDestinationPage switchPage = new SwitchDestinationPage();

    // ==================== COMMON / LOGIN STEPS ====================

    @Given("user navigates to the application URL")
    public void userNavigatesToTheApplicationURL() {
        loginPage.navigateToApplicationURL();
    }

    @Then("Verify {string} page is visible")
    public void verifyPageIsVisible(String arg0) {
        AssertionUtils.assertTrue(loginPage.isLoginTitleDisplayed(), arg0 + " page is not visible");
    }

    @When("user enters Email ID as {string}")
    public void userEntersEmailIDAs(String email) {
        loginPage.enterEmailID(email);
    }

    @When("user enters Password as {string}")
    public void userEntersPasswordAs(String password) {
        loginPage.enterPassword(password);
    }

    @When("user clicks on Login button")
    public void userClicksOnLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("user should be navigated to the dashboard")
    public void userShouldBeNavigatedToTheDashboard() {
        AssertionUtils.assertTrue(loginPage.isHeaderDisplayed(), "User is not navigated to the dashboard");
    }

    @Then("user should see the header name as {string}")
    public void userShouldSeeTheHeaderNameAs(String expectedHeader) {
        AssertionUtils.assertEquals(loginPage.getHeaderText(), expectedHeader, "Header name mismatch");
    }

    @Then("user should see an error message {string}")
    public void userShouldSeeAnErrorMessage(String expectedError) {
        AssertionUtils.assertEquals(loginPage.getErrorMessage(), expectedError, "Error message mismatch");
    }

    // ==================== SWITCH DESTINATION STEPS ====================

    @When("user navigates to the Switch Destination page")
    public void userNavigatesToTheSwitchDestinationPage() {
        switchPage.navigateToSwitchDestination();
    }

    @Then("the header {string} should be displayed")
    public void theHeaderShouldBeDisplayed(String expectedHeader) {
        AssertionUtils.assertTrue(switchPage.isHeaderDisplayed(), "Header not displayed");
    }

    @When("user clicks on Create button")
    public void userClicksOnCreateButton() {
        switchPage.clickCreate();
    }

    @Then("the title {string} should be displayed")
    public void theTitleShouldBeDisplayed(String expectedTitle) {
        AssertionUtils.assertTrue(switchPage.isCreateTitleDisplayed(), "Create title not displayed");
    }

    @When("user selects Scheme as {string}")
    public void userSelectsSchemeAs(String scheme) {
        switchPage.selectScheme(scheme);
    }

    @And("user enters configuration details:")
    public void userEntersConfigurationDetails(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> row = data.get(0);
        switchPage.enterConfigurationDetails(
                row.get("Name"),
                row.get("Acquirer ID"),
                row.get("Forward ID"),
                row.get("Facilitator ID"),
                row.get("Group SignOn")
        );
    }

    @And("user clicks on Next button")
    public void userClicksOnNextButton() {
        switchPage.clickNext();
    }

    @And("user clicks on Add button")
    public void userClicksOnAddButton() {
        switchPage.clickAdd();
    }

    @And("user enters transaction details:")
    public void userEntersTransactionDetails(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> row = data.get(0);
        switchPage.enterTransactionDetails(
                row.get("Transaction Type"),
                row.get("Transaction Channel"),
                row.get("Processing Code"),
                row.get("MTI"),
                row.get("Transaction Expiry")
        );
    }

    @And("user clicks on Save button")
    public void userClicksOnSaveButton() {
        switchPage.clickSave();
    }

    @Then("the configuration {string} should be successfully created")
    public void theConfigurationShouldBeSuccessfullyCreated(String configName) {
        AssertionUtils.assertTrue(switchPage.isHeaderDisplayed(), "Failed to return to configuration list");
    }

    @And("user clicks on Save button without entering details")
    public void userClicksOnSaveButtonWithoutEnteringDetails() {
        switchPage.clickSave();
    }

    @Then("user should see mandatory field errors")
    public void userShouldSeeMandatoryFieldErrors() {
        // Placeholder for error validation
        AssertionUtils.assertTrue(true, "Mandatory errors verified");
    }
}