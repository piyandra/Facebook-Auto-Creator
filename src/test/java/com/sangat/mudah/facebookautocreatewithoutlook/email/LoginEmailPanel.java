package com.sangat.mudah.facebookautocreatewithoutlook.email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.jetbrains.com/
public class LoginEmailPanel {

    private String password = "";
    private String url = "";
    private String username = "";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @FindBy(xpath = "//input[@name='username']")
    public WebElement inputUsername;

    @FindBy(xpath = "//*[@id='password']")
    public WebElement inputPassword;

    @FindBy(xpath = "//button")
    public WebElement signIn;

    public LoginEmailPanel(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

}
