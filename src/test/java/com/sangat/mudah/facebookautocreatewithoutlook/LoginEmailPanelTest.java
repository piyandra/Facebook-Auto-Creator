package com.sangat.mudah.facebookautocreatewithoutlook;

import com.sangat.mudah.facebookautocreatewithoutlook.credentials.EmailCredentials;
import com.sangat.mudah.facebookautocreatewithoutlook.credentials.FacebookGenerator;
import com.sangat.mudah.facebookautocreatewithoutlook.email.CreateEmail;
import com.sangat.mudah.facebookautocreatewithoutlook.email.LoginEmailPanel;
import com.sangat.mudah.facebookautocreatewithoutlook.email.Webmail;
import com.sangat.mudah.facebookautocreatewithoutlook.facebook.RegisterFacebook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginEmailPanelTest {

    private LoginEmailPanel loginEmailPanel;
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://152.42.203.70:8090/");
        loginEmailPanel = new LoginEmailPanel(driver);


    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    public void loginPanel() throws InterruptedException {
        // Register Object
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        EmailCredentials emailCredentials = new EmailCredentials();
        FacebookGenerator facebookGenerator = new FacebookGenerator();
        facebookGenerator.setDay();
        facebookGenerator.setMonth();
        facebookGenerator.setYear();
        facebookGenerator.setFirstName(facebookGenerator.nameGenerator());
        facebookGenerator.setLastName(facebookGenerator.nameGenerator());
        //Set Panel Credentials
        emailCredentials.setUsername("admin");
        emailCredentials.setPassword("tIn9Jj9UNKhj5JUv");
        loginEmailPanel.inputUsername.sendKeys(emailCredentials.getUsername());
        loginEmailPanel.inputPassword.sendKeys(emailCredentials.getPassword());
        while (true){
            if (loginEmailPanel.signIn.isDisplayed() && !driver.findElement(By.id("loading")).isDisplayed()){
                loginEmailPanel.signIn.click();
                break;
            }

        }
        //Creating Email
        CreateEmail createEmail = new CreateEmail(driver);
        createEmail.setEmailPassword();
        createEmail.setEmailusername();
        while (true){
            if (createEmail.linkEmail.isDisplayed() && !driver.findElement(By.id("loading")).isDisplayed()){
                try {
                    createEmail.linkEmail.click();
                    break;
                } catch (ElementClickInterceptedException ignored){
                }
            }

        }
        while (true){
            if (createEmail.linkCreateEmailAccount.isDisplayed() && !driver.findElement(By.id("loading")).isDisplayed()){
                try {
                    createEmail.linkCreateEmailAccount.click();
                    break;
                } catch (ElementClickInterceptedException ignored){
                }
            }

        }
        while (true){
            if (createEmail.selectEmail.isDisplayed() && !driver.findElement(By.id("loading")).isDisplayed()) {
                break;
            }
        }
        Select select = new Select(createEmail.selectEmail);
        select.selectByValue("yann.my.id");
        while (true){
            if (createEmail.usernameField.isDisplayed()){
                break;
            }
        }
        createEmail.usernameField.sendKeys(createEmail.getEmailusername());
        createEmail.passwordField.sendKeys(createEmail.getEmailPassword());
        System.out.println("Email : "+createEmail.getEmailusername()+"@yann.my.id");
        System.out.println("Password : "+createEmail.getEmailPassword());
        createEmail.buttonCreateEmail.click();
        // Goes To Email
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://152.42.203.70:8090/snappymail/index.php");
        Webmail webmail = new Webmail(driver);
        while(true) {
            if (webmail.inputEmail.isDisplayed()) {
                break;
            }
        }
        webmail.inputEmail.sendKeys(createEmail.getEmailusername()+"@yann.my.id");
        webmail.inputPassword.sendKeys(createEmail.getEmailPassword());
        webmail.buttonCommandSubmit.click();
        while (!webmail.editIdentity.isDisplayed()){
        }
        webmail.closeLinks.click();
        webmail.askingCloseWindow.click();
        webmail.askingCloseWindowButtonYes.click();
        String emailTab = driver.getWindowHandle();


        // Register Facebook
        RegisterFacebook registerFacebook = new RegisterFacebook(driver);
        registerFacebook.setEmail(createEmail.getEmailusername()+"@yann.my.id");
        registerFacebook.setPassword(createEmail.getEmailPassword());
        registerFacebook.setFirstName(facebookGenerator.getFirstName());
        registerFacebook.setLastName(facebookGenerator.getLastName());

        //Goes To Facebook
        driver.switchTo().newWindow(WindowType.TAB);
        String facebookTab = driver.getWindowHandle();
        driver.get("https://www.facebook.com/");
        registerFacebook.linkCreateNewAccount.click();
        registerFacebook.firstNameInput.sendKeys(registerFacebook.getFirstName());
        registerFacebook.lastNameInput.sendKeys(registerFacebook.getLastName());
        registerFacebook.selectDay.sendKeys(facebookGenerator.getDay());
        registerFacebook.selectMonth.sendKeys(facebookGenerator.getMonth());
        registerFacebook.selectYear.sendKeys(facebookGenerator.getYear());
        registerFacebook.labelMale.click();
        registerFacebook.inputText.sendKeys(registerFacebook.getEmail());
        registerFacebook.inputPassword.sendKeys(registerFacebook.getPassword());
        Thread.sleep(500);
        registerFacebook.buttonWebSubmit.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Enter the code from your email']")));
        driver.switchTo().window(emailTab);
        while (true){
            Thread.sleep(1_000L);
            if (!webmail.emptyListNotFoundEmail.isDisplayed() && webmail.textSubject.isDisplayed()){
                break;
            }
            webmail.linkReloadMessage.click();
        }

        // Get Code
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(webmail.textSubject.getText());
        StringBuilder sb = new StringBuilder();
        while (matcher.find()){
            sb.append(matcher.group());
        }
        driver.switchTo().window(facebookTab);
        if (registerFacebook.inputCode.isDisplayed()){
            registerFacebook.inputCode.sendKeys(sb.toString());
        }
        wait.until(ExpectedConditions.elementToBeClickable(registerFacebook.buttonConfirm));
        registerFacebook.buttonConfirm.click();
    }
}