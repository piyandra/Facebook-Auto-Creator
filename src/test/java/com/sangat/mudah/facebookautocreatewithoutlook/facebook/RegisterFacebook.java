package com.sangat.mudah.facebookautocreatewithoutlook.facebook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterFacebook {
    
    private String firstName;

    public String getFirstName() {
        firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1);
        return firstName;
    }

    public RegisterFacebook setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1);
        return lastName;
    }

    public RegisterFacebook setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterFacebook setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterFacebook setPassword(String password) {
        this.password = password;
        return this;
    }

    private String lastName;
    private String email;
    private String password;
    
    @FindBy(css = "a[data-testid='open-registration-form-button']")
    public WebElement linkCreateNewAccount;

    @FindBy(name = "firstname")
    public WebElement firstNameInput;

    @FindBy(name = "lastname")
    public WebElement lastNameInput;

    @FindBy(name = "birthday_day")
    public WebElement selectDay;

    @FindBy(name = "birthday_month")
    public WebElement selectMonth;

    @FindBy(name = "birthday_year")
    public WebElement selectYear;

    @FindBy(xpath = "//*[text() = 'Male']")
    public WebElement labelMale;

    @FindBy(name = "reg_email__")
    public WebElement inputText;

    @FindBy(xpath = "//*[@id='password_step_input']")
    public WebElement inputPassword;

    @FindBy(xpath = "//*[text() = 'Sign Up']")
    public WebElement buttonWebSubmit;

    @FindBy(xpath = "//*[@id=\"code_in_cliff\"]")
    public WebElement inputCode;

    @FindBy(name = "confirm")
    public WebElement buttonConfirm;

    public RegisterFacebook(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
