package com.sangat.mudah.facebookautocreatewithoutlook.email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Random;

public class CreateEmail {


    private String emailPassword = "";
    private String emailusername = "";

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword() {
        ArrayList<String> emailPasswordList = new ArrayList<String>();
        int length = 8;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int j =0; j< 100;j++){
            for (int i = 0; i < length; i++) {
                int randomIndex = random.nextInt(alphabet.length());
                sb.append(alphabet.charAt(randomIndex));
            }
            emailPasswordList.add(sb.toString());
            sb.setLength(0);
        }
        this.emailPassword = emailPasswordList.get(random.nextInt(90));
    }

    public String getEmailusername() {
        return emailusername;
    }

    public void setEmailusername() {
        ArrayList<String> emailPasswordList = new ArrayList<String>();
        int length = 8;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int j =0; j< 100;j++){
            for (int i = 0; i < length; i++) {
                int randomIndex = random.nextInt(alphabet.length());
                sb.append(alphabet.charAt(randomIndex));
            }
            emailPasswordList.add(sb.toString());
            sb.setLength(0);
        }
        this.emailusername = emailPasswordList.get(random.nextInt(90));
    }



    @FindBy(xpath = "//a[@title='Email']")
    public WebElement linkEmail;

    @FindBy(xpath = "//a[@title='Create Email Account']")
    public WebElement linkCreateEmailAccount;

    @FindBy(xpath = "//select")
    public WebElement selectEmail;

    @FindBy(xpath = "//input[@ng-model='emailUsername']")
    public WebElement usernameField;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement passwordField;

    @FindBy(xpath = "//button[contains(@class, 'btn-lg')]")
    public WebElement buttonCreateEmail;

    @FindBy(xpath = "/html/body/div[3]/div[3]/div/div[2]/div[2]/div/div/form/div[6]/div/div[2]")
    public WebElement emailCreated;

    public CreateEmail(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
