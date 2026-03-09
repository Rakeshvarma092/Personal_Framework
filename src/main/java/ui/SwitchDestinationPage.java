package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import driver.DriverManager;

import java.time.Duration;

public class SwitchDestinationPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final String SELECT_DROPDOWN = "//span[normalize-space()='%s']";

    @FindBy(xpath = "//body//app-root//button[4]")
    private WebElement nav_SwitchConfiguration;

    @FindBy(xpath = "//li[contains(normalize-space(),'Switch Destination')]")
    private WebElement select_Configuration;

    @FindBy(xpath = "//p[contains(normalize-space(),'Switch Configuration')]")
    private WebElement verify_HeaderName;

    @FindBy(xpath = "//span[normalize-space()='Create']")
    private WebElement btn_Create;

    @FindBy(xpath = "//p[contains(normalize-space(),'New Switch Configuration')]")
    private WebElement verify_TitleName;

    @FindBy(xpath = "//mat-select[@name='swdest'] | //mat-select[contains(.,'Select Scheme')]")
    private WebElement select_Scheme;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement txt_Name;

    @FindBy(xpath = "//input[@name='acqID']")
    private WebElement txt_AcquirerInstitutionID;

    @FindBy(xpath = "//input[@name='forwardID']")
    private WebElement txt_ForwardID;

    @FindBy(xpath = "//input[@name='pymtfacilitater']")
    private WebElement txt_PaymentFacilitatorID;

    @FindBy(xpath = "//input[@name='grpSignOn']")
    private WebElement txt_GroupSignOn;

    @FindBy(xpath = "//span[normalize-space()='Next']")
    private WebElement btn_Next;

    @FindBy(xpath = "//button[contains(.,'Add')]")
    private WebElement btn_Add;

    @FindBy(xpath = "//mat-select[contains(@name,'txnType')] | //mat-select[contains(.,'Transaction Type')]")
    private WebElement select_TransactionType;

    @FindBy(xpath = "//mat-select[contains(@name,'txnChannel')] | //mat-select[contains(.,'Transaction Channel')]")
    private WebElement select_TransactionChannel;

    @FindBy(xpath = "//input[contains(@name,'processingCode')]")
    private WebElement txt_ProcessingCode;

    @FindBy(xpath = "//input[contains(@name,'mti')]")
    private WebElement txt_MTI;

    @FindBy(xpath = "//input[contains(@name,'txnExpiry')]")
    private WebElement txt_TransactionExpiry;

    @FindBy(xpath = "//span[normalize-space()='Save'] | //button[contains(.,'Submit')]")
    private WebElement btn_Save;

    public SwitchDestinationPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    public void navigateToSwitchDestination() {
        wait.until(ExpectedConditions.elementToBeClickable(nav_SwitchConfiguration)).click();
        wait.until(ExpectedConditions.elementToBeClickable(select_Configuration)).click();
    }

    public boolean isHeaderDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(verify_HeaderName));
            return verify_HeaderName.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCreate() {
        wait.until(ExpectedConditions.elementToBeClickable(btn_Create)).click();
    }

    public boolean isCreateTitleDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(verify_TitleName));
            return verify_TitleName.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectScheme(String scheme) {
        wait.until(ExpectedConditions.elementToBeClickable(select_Scheme)).click();
        WebElement option = driver.findElement(By.xpath(String.format(SELECT_DROPDOWN, scheme)));
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void enterConfigurationDetails(String name, String acqID, String forwardID, String facilitatorID, String groupSignOn) {
        wait.until(ExpectedConditions.visibilityOf(txt_Name)).sendKeys(name);
        txt_AcquirerInstitutionID.sendKeys(acqID);
        txt_ForwardID.sendKeys(forwardID);
        txt_PaymentFacilitatorID.sendKeys(facilitatorID);
        txt_GroupSignOn.sendKeys(groupSignOn);
    }

    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(btn_Next)).click();
    }

    public void clickAdd() {
        wait.until(ExpectedConditions.elementToBeClickable(btn_Add)).click();
    }

    public void enterTransactionDetails(String type, String channel, String procCode, String mti, String expiry) {
        wait.until(ExpectedConditions.elementToBeClickable(select_TransactionType)).click();
        WebElement typeOption = driver.findElement(By.xpath(String.format(SELECT_DROPDOWN, type)));
        wait.until(ExpectedConditions.elementToBeClickable(typeOption)).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(select_TransactionChannel)).click();
        WebElement channelOption = driver.findElement(By.xpath(String.format(SELECT_DROPDOWN, channel)));
        wait.until(ExpectedConditions.elementToBeClickable(channelOption)).click();
        
        txt_ProcessingCode.sendKeys(procCode);
        txt_MTI.sendKeys(mti);
        txt_TransactionExpiry.sendKeys(expiry);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(btn_Save)).click();
    }
}
