package com.selenium.selenium_testing;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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

    void closeLightBox() {
        try {
            fDriver.findElement(By.className("at-cm-no-button")).click();

        } catch (NoSuchElementException e) {
        }
    }

    @Test
    void findInputBoxAndEntersMessage() {
        // Arrange
        closeLightBox();
        // Act
        WebElement singleInputField = fDriver.findElement(By.id("user-message"));
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
        closeLightBox();
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
