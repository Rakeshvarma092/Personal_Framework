package com.tests.hooks;

import driver.DriverManager;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cucumber Hooks for setup and teardown activities.
 * Handles WebDriver lifecycle and screenshot capture for UI scenarios.
 * Tags: @ui or @Browser trigger these hooks.
 */
public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before("@ui or @Browser")
    public void setupUI(Scenario scenario) {
        logger.info("Starting Scenario: {}", scenario.getName());
        try {
            // Initialize driver via DriverManager
            DriverManager.getDriver();
            logger.info("WebDriver initialized successfully for scenario: {}", scenario.getName());
        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver for scenario: {}", scenario.getName(), e);
            throw e;
        }
    }

    @BeforeStep("@ui or @Browser")
    public void beforeStep(Scenario scenario) {
        // Optional: Can add step-level logging or pre-step checks
    }

    @AfterStep("@ui or @Browser")
    public void afterStepActions(Scenario scenario) {
        if (scenario.isFailed()) {
            captureScreenshot(scenario, "Failed_Step_Screenshot");
        }
    }

    @After("@ui or @Browser")
    public void tearDownUI(Scenario scenario) {
        try {
            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                // Attach final screenshot if scenario passed (optional context)
                if (!scenario.isFailed()) {
                    captureScreenshot(scenario, "Completed_Scenario_Success");
                }
                logger.info("Finished Scenario: {} - Result: {}", scenario.getName(), scenario.getStatus());
            }
        } catch (Exception e) {
            logger.warn("Error during teardown screenshot capture: {}", e.getMessage());
        } finally {
            DriverManager.quitDriver();
            logger.info("WebDriver session closed.");
        }
    }

    /**
     * Helper method to capture and attach screenshots to Cucumber scenario.
     */
    private void captureScreenshot(Scenario scenario, String prefix) {
        try {
            WebDriver driver = DriverManager.getDriver();
            if (driver instanceof TakesScreenshot) {
                String screenshotName = prefix + "_" + scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", screenshotName);
                logger.debug("Screenshot attached: {}", screenshotName);
            }
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
        }
    }
}