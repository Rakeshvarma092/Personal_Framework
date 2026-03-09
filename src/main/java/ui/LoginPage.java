package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import driver.DriverManager;
import config.ConfigReader;

import java.time.Duration;

public class LoginPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(xpath = "//h1[normalize-space()='Login'] | //h2[normalize-space()='Login'] | //h3[normalize-space()='Login'] | //h4[normalize-space()='Login'] | //h5[normalize-space()='Login'] | //h6[normalize-space()='Login']")
    private WebElement verify_TitleName;
    @FindBy(xpath = "//input[@name='userName'] | //input[@id='userName'] | //input[@placeholder='Enter registered email ID']")
    private WebElement txt_EmailID;
    @FindBy(xpath = "//input[@name='password'] | //input[@id='password'] | //input[@placeholder='Enter password']")
    private WebElement txt_Password;
    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement btn_Login;
    @FindBy(xpath = "//*[normalize-space()='SWITCH OVERVIEW'] | //*[normalize-space()='DASHBOARD']")
    private WebElement verify_HeaderName;
    @FindBy(xpath = "//div[contains(@class,'error')] | //div[contains(@id,'error')] | //span[contains(@class,'error')]")
    private WebElement lbl_ErrorMessage;

    public LoginPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    public void navigateToApplicationURL() {
        driver.get(ConfigReader.getProperty("SWITCH.URL"));
    }

    protected void sendKeys(By locator, String text) {
        waitForVisibility(locator).sendKeys(text);
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isLoginTitleDisplayed() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(verify_TitleName),
                    ExpectedConditions.visibilityOf(txt_EmailID)
            ));
            return verify_TitleName.isDisplayed() || txt_EmailID.isDisplayed();
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

    public boolean isHeaderDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(verify_HeaderName));
            return verify_HeaderName.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getHeaderText() {
        wait.until(ExpectedConditions.visibilityOf(verify_HeaderName));
        return verify_HeaderName.getText().trim();
    }

    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(lbl_ErrorMessage));
            return lbl_ErrorMessage.getText().trim();
        } catch (Exception e) {
            return "Error message not displayed";
        }
    }

}