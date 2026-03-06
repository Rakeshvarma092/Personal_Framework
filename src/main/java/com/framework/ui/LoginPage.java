package com.framework.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.framework.driver.DriverManager;

import java.time.Duration;

public class LoginPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(xpath = "//h6[normalize-space()='Login']")
    private WebElement verify_TitleName;
    @FindBy(xpath = "//input[@name='userName']")
    private WebElement txt_EmailID;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement txt_Password;
    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement btn_Login;
    @FindBy(xpath = "//h6[normalize-space()='SWITCH OVERVIEW']")
    private WebElement verify_HeaderName;
    public LoginPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    protected void sendKeys(By locator, String text) {
        waitForVisibility(locator).sendKeys(text);
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isLoginTitleDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(verify_TitleName));
            return verify_TitleName.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void enterEmailID(String email) {
        wait.until(ExpectedConditions.visibilityOf(txt_EmailID));
        txt_EmailID.clear();
        txt_EmailID.sendKeys(email);
    }
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(txt_Password));
        txt_Password.clear();
        txt_Password.sendKeys(password);
    }
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btn_Login));
        btn_Login.click();
    }


}
