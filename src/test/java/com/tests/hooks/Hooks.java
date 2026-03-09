package com.tests.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import driver.DriverManager;

public class Hooks {

    @Before("@ui or @Browser")
    public void setupUI() {
        // Initialize driver
        DriverManager.getDriver();
    }

    @AfterStep("@ui or @Browser")
    public void captureScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed_Step_Screenshot_" + scenario.getName().replace(" ", "_"));
            }
        }
    }

    @After("@ui or @Browser")
    public void tearDownUI(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            // Capture final screenshot on completion (optional, can be commented out if too noisy)
            if (!scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Completed_Scenario_" + scenario.getName().replace(" ", "_"));
            }
        }
        DriverManager.quitDriver();
    }
}
