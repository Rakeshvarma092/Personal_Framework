package com.tests.hooks;

import driver.DriverManager;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import config.ConfigReader;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Cucumber Hooks for setup and teardown activities.
 * Handles WebDriver lifecycle and screenshot capture for UI scenarios.
 * Tags: @ui or @Browser trigger these hooks.
 */
public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    private final AtomicInteger stepCounter = new AtomicInteger(1);

    @Before("@ui or @Browser")
    public void setupUI(Scenario scenario) {
        stepCounter.set(1); // Reset counter for each scenario
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
        boolean screenshotEveryStep = Boolean.parseBoolean(ConfigReader.getProperty("screenshot.every.step"));
        
        if (scenario.isFailed()) {
            captureScreenshot(scenario, "Failed_Step_" + stepCounter.getAndIncrement());
        } else if (screenshotEveryStep) {
            captureScreenshot(scenario, "Step_" + stepCounter.getAndIncrement());
        } else {
            stepCounter.incrementAndGet();
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
                String timestamp = String.valueOf(System.currentTimeMillis());
                String sanitizedScenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
                String screenshotName = prefix + "_" + sanitizedScenarioName + "_" + timestamp;
                
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", screenshotName);
                logger.debug("Screenshot attached: {}", screenshotName);
            }
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: {}", e.getMessage());
        }
    }
}