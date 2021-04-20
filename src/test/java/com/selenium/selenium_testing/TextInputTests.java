package com.selenium.selenium_testing;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TextInputTests {
    private static final String siteURL = "https://www.seleniumeasy.com/test/basic-first-form-demo.html";
    private static WebDriver fDriver;

    @BeforeAll
    static void navigateToSite() {
        fDriver = new FirefoxDriver();
        fDriver.navigate().to(siteURL);
    }

    @BeforeEach
    void closeLightBox() {
        try {
            WebElement closeLightBoxElement = fDriver.findElement(By.className("at-cm-no-button"));
            WebDriverWait wait = new WebDriverWait(fDriver, 20);
            wait.until(ExpectedConditions.visibilityOf(closeLightBoxElement));
            closeLightBoxElement.click();

        } catch (NoSuchElementException e) {
        }
    }

    @AfterAll
    static void close() {
        fDriver.close();
    }

    @Test
    void findInputBoxAndEntersMessage() {
        // Arrange
        WebElement singleInputField = fDriver.findElement(By.id("user-message"));

        // Act
        singleInputField.click();
        singleInputField.sendKeys("This is my message");

        fDriver.findElement(By.xpath("//button[contains(text(),'Show Message')]")).click();
        WebElement labelInputMessage = fDriver.findElement(By.xpath("//span[@id='display']"));

        // Assert
        Assert.assertTrue(labelInputMessage.isDisplayed());
        Assert.assertEquals(singleInputField.getAttribute("value"), labelInputMessage.getText());
    }

    @Test
    void entersNumbersAndComparesResult() {
        // Arrange
        WebElement firstInpuElement = fDriver.findElement(By.id("sum1"));
        WebElement seccondElement = fDriver.findElement(By.id("sum2"));
        WebElement resultElement = fDriver.findElement(By.id("displayvalue"));
        // Act
        firstInpuElement.click();
        firstInpuElement.sendKeys("1");
        seccondElement.click();
        seccondElement.sendKeys("2");

        fDriver.findElement(By.xpath("//button[contains(text(),'Get Total')]")).click();
        // Assert
        Assert.assertEquals("3", resultElement.getText());
    }
}
