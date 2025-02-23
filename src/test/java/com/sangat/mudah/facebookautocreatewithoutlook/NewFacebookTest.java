package com.sangat.mudah.facebookautocreatewithoutlook;


import com.sangat.mudah.facebookautocreatewithoutlook.credentials.NameGenerator;
import com.sangat.mudah.facebookautocreatewithoutlook.email.CreateEmail;
import com.sangat.mudah.facebookautocreatewithoutlook.email.LoginEmailPanel;
import org.junit.jupiter.api.*;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class NewFacebookTest {
    private static final Logger log = LoggerFactory.getLogger(NewFacebookTest.class);
    private WebDriver driver;
    private LoginEmailPanel loginEmailPanel;
    private CreateEmail createEmail;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        NameGenerator nameGenerator = new NameGenerator();
        loginEmailPanel = new LoginEmailPanel(driver);
        createEmail = new CreateEmail(driver);
        nameGenerator.setFirstName(nameGenerator.nameGenerator());
        nameGenerator.setLastName(nameGenerator.nameGenerator());
        loginEmailPanel.setUsername("admin");
        loginEmailPanel.setPassword("tIn9Jj9UNKhj5JUv");
        createEmail.setEmailusername();
        createEmail.setEmailPassword();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
    @Test
    @Order(1)
    public void loginWebPanelTest() {
        driver.get("https://152.42.203.70:8090/");
        while (true){
            try {
                if (loginEmailPanel.inputUsername.isDisplayed()) {
                    try {
                        WebDriverWait waitForElementToBeClickable = new WebDriverWait(driver, Duration.ofMillis(100));
                        waitForElementToBeClickable.until(ExpectedConditions.elementToBeClickable(loginEmailPanel.inputPassword));
                        break;
                    } catch (TimeoutException timeoutException) {
                        log.debug(timeoutException.getMessage());
                    }
                }
            } catch (NoSuchElementException noSuchElementException) {
                log.debug(noSuchElementException.getMessage());
            }
        }
        loginEmailPanel.inputUsername.sendKeys(loginEmailPanel.getUsername());
        loginEmailPanel.inputPassword.sendKeys(loginEmailPanel.getPassword());
        while (true){
            try {
                if (loginEmailPanel.signIn.isDisplayed()) {
                    try {
                        WebDriverWait waitUntilSignInButtonClickable = new WebDriverWait(driver, Duration.ofMillis(100));
                        waitUntilSignInButtonClickable.until(ExpectedConditions.elementToBeClickable(loginEmailPanel.signIn));
                        loginEmailPanel.signIn.click();
                        break;
                    } catch (TimeoutException timeoutException) {
                        log.debug(timeoutException.getMessage());
                    }
                }
            } catch (ElementClickInterceptedException elementClickInterceptedException){
                log.debug(elementClickInterceptedException.getMessage());
            }
        }
        Assertions.assertFalse(loginEmailPanel.signIn.isDisplayed());
    }
    @Test
    @Order(2)
    public void createEmailTest(){
        driver.get("https://152.42.203.70:8090/");
        while (true){
            try {
                if (loginEmailPanel.inputUsername.isDisplayed()) {
                    try {
                        WebDriverWait waitForElementToBeClickable = new WebDriverWait(driver, Duration.ofMillis(100));
                        waitForElementToBeClickable.until(ExpectedConditions.elementToBeClickable(loginEmailPanel.inputPassword));
                        break;
                    } catch (TimeoutException timeoutException) {
                        log.debug(timeoutException.getMessage());
                    }
                }
            } catch (NoSuchElementException noSuchElementException) {
                log.debug(noSuchElementException.getMessage());
            }
        }
        loginEmailPanel.inputUsername.sendKeys(loginEmailPanel.getUsername());
        loginEmailPanel.inputPassword.sendKeys(loginEmailPanel.getPassword());
        while (true){
            try {
                if (loginEmailPanel.signIn.isDisplayed()) {
                    try {
                        WebDriverWait waitUntilSignInButtonClickable = new WebDriverWait(driver, Duration.ofMillis(100));
                        waitUntilSignInButtonClickable.until(ExpectedConditions.elementToBeClickable(loginEmailPanel.signIn));
                        loginEmailPanel.signIn.click();
                        break;
                    } catch (TimeoutException timeoutException) {
                        log.debug(timeoutException.getMessage());
                    }
                }
            } catch (ElementClickInterceptedException elementClickInterceptedException){
                log.debug(elementClickInterceptedException.toString());
            }
        }
        while (true){
            try {
                if (!createEmail.linkEmail.isDisplayed()){
                    try {
                        WebDriverWait waitUntilEmailMenuButtonDisplayed = new WebDriverWait(driver, Duration.ofMillis(100));
                        waitUntilEmailMenuButtonDisplayed.until(ExpectedConditions.visibilityOf(createEmail.linkEmail));
                    } catch (TimeoutException timeoutException){
                        log.debug(timeoutException.getMessage());
                    }
                }
                try {
                    createEmail.linkEmail.click();
                    break;
                } catch (ElementClickInterceptedException elementClickInterceptedException) {
                    log.debug(elementClickInterceptedException.getMessage());
                }
            } catch (NoSuchElementException noSuchElementException){
                log.debug(noSuchElementException.getMessage());
            }
        }
        try {
            createEmail.linkCreateEmailAccount.click();
        } catch (NoSuchElementException noSuchElementException){
            log.debug(noSuchElementException.getMessage());
        }
        while (true){
            if (createEmail.selectEmail.isDisplayed()){
                break;
            }
        }
        while (true){
            if (createEmail.selectEmail.isDisplayed()){
                try {
                    Select selectEmailDomain = new Select(createEmail.selectEmail);
                    selectEmailDomain.selectByValue("yann.my.id");
                    break;
                } catch (ElementClickInterceptedException elementClickInterceptedException){
                    log.debug(elementClickInterceptedException.getMessage());
                }
            }
        }
        while (true){
            if (createEmail.usernameField.isDisplayed()){
                break;
            }
        }
        createEmail.usernameField.sendKeys(createEmail.getEmailusername());
        createEmail.passwordField.sendKeys(createEmail.getEmailPassword());
        while (true){
            if (createEmail.buttonCreateEmail.isDisplayed()){
                try {
                    createEmail.buttonCreateEmail.click();
                    break;
                } catch (ElementClickInterceptedException elementClickInterceptedException){
                    log.debug(elementClickInterceptedException.getMessage());
                }
            }
        }
        WebDriverWait waitUntilEmailCreated = new WebDriverWait(driver, Duration.ofMillis(2_000L));
        waitUntilEmailCreated.until(ExpectedConditions.visibilityOf(createEmail.emailCreated));

        Assertions.assertTrue(createEmail.emailCreated.isDisplayed());
    }
}
