package com.sangat.mudah.facebookautocreatewithoutlook.email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Webmail {
    @FindBy(xpath = "//input[@type='text']")
    public WebElement inputEmail;

    @FindBy(xpath = "//input[@name='Password']")
    public WebElement inputPassword;

    @FindBy(xpath = "//button")
    public WebElement buttonCommandSubmit;

    @FindBy(xpath = "//*[text() = 'Edit Identity']")
    public WebElement editIdentity;

    @FindBy(xpath = "//a[@data-bind='click: tryToClose']")
    public WebElement closeLinks;

    @FindBy(xpath = "//div[@data-bind='html: askDesc']")
    public WebElement askingCloseWindow;

    @FindBy(xpath = "//button[@data-icon='✔']")
    public WebElement askingCloseWindowButtonYes;

    @FindBy(xpath = "//div[@class='listEmptyMessage']")
    public WebElement emptyListNotFoundEmail;

    @FindBy(xpath = "//a[contains(@title, 'List')]")
    public WebElement linkReloadMessage;

    @FindBy(xpath = "//div[@class='subjectParent']")
    public WebElement textSubject;
    
    

    
    

    public Webmail(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    
    
}
