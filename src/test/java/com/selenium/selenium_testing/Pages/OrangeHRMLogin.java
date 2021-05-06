package com.selenium.selenium_testing.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrangeHRMLogin {
    private WebDriver driver;

    @FindBy(xpath = "//div[text()='LOGIN Panel' and @id='logInPanelHeading']")
    WebElement loginFormHeader;

    @FindBy(xpath = "//input[@id='txtUsername']")
    WebElement userNameTextField;

    @FindBy(xpath = "//input[@id='txtPassword']")
    WebElement passwordTextField;

    @FindBy(xpath = "//input[@id='btnLogin']")
    WebElement loginButton;

    @FindBy(xpath = "//div[@id='divLoginButton']//child::a")
    WebElement forgotPasswordLink;

    public OrangeHRMLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getLoginFormHeader() {
        return loginFormHeader.getText();
    }

    public void setUsername(String username) {
        userNameTextField.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordTextField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void loginToOrangeHRM(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
        this.clickLoginButton();
    }

}
