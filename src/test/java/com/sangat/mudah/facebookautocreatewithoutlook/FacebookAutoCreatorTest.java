package com.sangat.mudah.facebookautocreatewithoutlook;

import com.sangat.mudah.facebookautocreatewithoutlook.credentials.EmailCredentials;
import com.sangat.mudah.facebookautocreatewithoutlook.credentials.NameGenerator;
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
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LoginEmailPanelTest {

    private LoginEmailPanel loginEmailPanel;
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        /*
        Block ini untuk mendaftarkan WebDriver menggunakan Firefox
        Silakan ganti dengan WebDriver yang sesuai dengan kebutuhan
         */
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://152.42.203.70:8090/");
        loginEmailPanel = new LoginEmailPanel(driver);


    }

    @AfterEach
    void tearDown() {
        /*
        Block ini untuk menutup WebDriver
         */
        driver.quit();
    }

    @Test
    public void loginPanel() throws InterruptedException {
        long start = System.currentTimeMillis();
        /*
        Block ini untuk melakukan login ke panel email
         */
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        /*
        Block ini untuk melakukan login ke panel email
        Generate Nama dan Email untuk mendaftar Facebook
        Generate Generate Nama, Tanggal Lahir untuk Facebook
         */
        EmailCredentials emailCredentials = new EmailCredentials();
        NameGenerator facebookGenerator = new NameGenerator();
        facebookGenerator.setDay();
        facebookGenerator.setMonth();
        facebookGenerator.setYear();
        facebookGenerator.setFirstName(facebookGenerator.nameGenerator());
        facebookGenerator.setLastName(facebookGenerator.nameGenerator());
        /*
        Set Panel Credential yang didapat dari Cyberpanel
         */
        emailCredentials.setUsername("admin");
        emailCredentials.setPassword("tIn9Jj9UNKhj5JUv");

        // Memasukkan Username dan Password Panel
        loginEmailPanel.inputUsername.sendKeys(emailCredentials.getUsername());
        loginEmailPanel.inputPassword.sendKeys(emailCredentials.getPassword());
        /*
        Dikarenakan ada loading animasi dari cyberpanel dan mengatasi koneksi lemot,
        Menggunakan while loop untuk menunggu element muncul
         */
        while (true){
            if (loginEmailPanel.signIn.isDisplayed()){
                try {
                    loginEmailPanel.signIn.click();
                    break;
                } catch (ElementClickInterceptedException ignore){

                }
            }
        }
        /*
        Block ini untuk generate email baru
        Meregister Nama dan Password yang didapat dari CreateEmail
         */
        CreateEmail createEmail = new CreateEmail(driver);
        createEmail.setEmailPassword();
        createEmail.setEmailusername();
        /*
        Dikarenakan ada loading animasi dari cyberpanel dan mengatasi koneksi lemot,
        menggunakan while loop untuk menunggu element muncul, dengan jeda 100ms
         */
        while (true){
            try {
                WebDriverWait waitEmail = new WebDriverWait(driver, Duration.ofMillis(100));
                waitEmail.until(ExpectedConditions.elementToBeClickable(createEmail.linkEmail));
                if (createEmail.linkEmail.isDisplayed()){
                    try{
                        createEmail.linkEmail.click();
                        break;
                    } catch (ElementClickInterceptedException ignore){
                        // Catch jika element tidak bisa di click
                        // Jika element tidak bisa di click, maka akan diulang
                    }
                }
            } catch (TimeoutException ignore){
                // Catch jika element tidak muncul, dalam waktu 100ms
                // Jika element tidak muncul, maka akan diulang
            }
        }
        while (true){
            try {
                // Menunggu element tidak bisa di klik
                WebDriverWait waitCreateEmail = new WebDriverWait(driver, Duration.ofMillis(100));
                waitCreateEmail.until(ExpectedConditions.elementToBeClickable(createEmail.linkCreateEmailAccount));
                if (createEmail.linkCreateEmailAccount.isDisplayed()){
                    try {
                        // Jika Bisa Di Klik
                        createEmail.linkCreateEmailAccount.click();
                        break;
                    } catch (ElementClickInterceptedException ignore){
                        // Catch jika element tidak bisa di click
                        // Jika element tidak bisa di click, maka akan diulang
                    }
                }
            } catch (TimeoutException ignore){
                // Catch jika element tidak muncul, dalam waktu 100ms
                // Jika element tidak muncul, maka akan diulang
            }
        }
        while (true){
            try {
                WebDriverWait waitCreateEmail = new WebDriverWait(driver, Duration.ofMillis(100));
                waitCreateEmail.until(ExpectedConditions.elementToBeClickable(createEmail.selectEmail));
                if (createEmail.selectEmail.isDisplayed()){
                    try {
                        if (createEmail.selectEmail.isEnabled() && createEmail.selectEmail.isDisplayed()){
                            Select select = new Select(createEmail.selectEmail);
                            select.selectByVisibleText("yann.my.id");
                            break;
                        }
                    } catch (ElementClickInterceptedException ignore){

                    }
                }
            } catch (TimeoutException ignore){

            }
        }
        //Create Email
        wait.until(ExpectedConditions.elementToBeClickable(createEmail.usernameField));
        createEmail.usernameField.sendKeys(createEmail.getEmailusername());
        createEmail.passwordField.sendKeys(createEmail.getEmailPassword());
        System.out.println("Email : "+createEmail.getEmailusername()+"@yann.my.id");
        System.out.println("Password : "+createEmail.getEmailPassword());
        createEmail.buttonCreateEmail.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div[2]/div/div/form/div[6]/div/div[2]")));
        // Goes To Email
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://152.42.203.70:8090/snappymail/index.php");
        Webmail webmail = new Webmail(driver);
        while (true){
            if (webmail.inputEmail.isDisplayed()){
                break;
            }
        }
        webmail.inputEmail.sendKeys(createEmail.getEmailusername()+"@yann.my.id");
        webmail.inputPassword.sendKeys(createEmail.getEmailPassword());
        webmail.buttonCommandSubmit.click();
        while (true){
            try {
                WebDriverWait waitNotification = new WebDriverWait(driver, Duration.ofMillis(100));
                waitNotification.until(ExpectedConditions.elementToBeClickable(webmail.closeLinks));
                webmail.closeLinks.click();
                webmail.askingCloseWindow.click();
                webmail.askingCloseWindowButtonYes.click();
                break;
            } catch (TimeoutException ignore){
            }
        }
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
        registerFacebook.buttonWebSubmit.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Enter the code from your email']")));
        driver.switchTo().window(emailTab);
        while (true) {
            try {
                WebDriverWait waitingEmailFromFacebook = new WebDriverWait(driver, Duration.ofSeconds(1));
                waitingEmailFromFacebook.until(ExpectedConditions.visibilityOf(webmail.textSubject));
                break;
            } catch (TimeoutException ignore) {
                webmail.linkReloadMessage.click();
            }
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
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies){
            System.out.println(cookie.getName() + " : " + cookie.getValue());
        }
        System.out.println("Register Success in " + (System.currentTimeMillis() - start) + "ms");
    }
}
