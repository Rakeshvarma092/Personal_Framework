package com.tests.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import com.framework.ui.LoginPage;
import com.framework.utils.AssertionUtils;
import com.framework.config.ConfigReader;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class UIStepDefs extends LoginPage {

//    @Given("I navigate to Google")
//    public void i_navigate_to_google() {
//        driver.get(ConfigReader.getProperty("ui.url"));
//    }
//
//    @When("I search for {string}")
//    public void i_search_for(String searchTerm) {
//        sendKeys(By.name("q"), searchTerm + Keys.ENTER);
//    }
//
//    @Then("I should see search results related to {string}")
//    public void i_should_see_search_results_related_to(String expectedText) {
//        boolean titleContains = wait.until(ExpectedConditions.titleContains(expectedText));
//        AssertionUtils.assertTrue(titleContains, "Title does not contain " + expectedText + ". Actual title: " + driver.getTitle());
//    }

    @Given("user navigates to the application URL")
    public void userNavigatesToTheApplicationURL() {
        driver.get(ConfigReader.getProperty("SWITCH.URL"));
    }

    @Then("Verify {string} page is visible")
    public void verifyPageIsVisible(String arg0) {
        AssertionUtils.assertTrue(isLoginTitleDisplayed(), arg0 + " page is not visible");
    }
}
