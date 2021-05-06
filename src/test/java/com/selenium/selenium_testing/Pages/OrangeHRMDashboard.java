package com.selenium.selenium_testing.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrangeHRMDashboard {
    @FindBy(xpath = "//div[starts-with(@class,'head')]//child::h1")
    WebElement dashHeader;

    private WebDriver driver;

    public OrangeHRMDashboard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getDashboardHeader() {
        return dashHeader.getText();
    }

}
