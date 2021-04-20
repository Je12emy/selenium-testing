package com.selenium.selenium_testing;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CheckBoxTests {
    private static final String siteURL = "https://www.seleniumeasy.com/test/basic-checkbox-demo.html";
    private static WebDriver fDriver;

    @BeforeAll
    static void navigateToSite() {
        fDriver = new FirefoxDriver();
        fDriver.navigate().to(siteURL);
    }

    @BeforeEach
    void clearBoxes() {
        WebElement checkBoxElement = fDriver.findElement(By.cssSelector("#isAgeSelected"));
        WebElement checkAllWebElement = fDriver.findElement(By.id("check1"));
        if (checkBoxElement.isSelected()) {
            checkBoxElement.click();
        }
        if (checkAllWebElement.getAttribute("value").equals("Uncheck All")) {
            checkAllWebElement.click();
        }
    }

    void closeLightBox() {
        try {
            fDriver.findElement(By.className("at-cm-no-button")).click();

        } catch (NoSuchElementException e) {
        }
    }

    @Test
    void selectsASingleCheckBox() {
        // Arrange
        closeLightBox();
        WebElement checkBoxElement = fDriver.findElement(By.cssSelector("#isAgeSelected"));
        // Act
        checkBoxElement.click();
        // Assert
        Assert.assertTrue(checkBoxElement.isSelected());
    }

    @Test
    void selects3Elements() {
        // Arrange
        closeLightBox();
        List<WebElement> multipleCheckBoxWebElement = fDriver
                .findElements(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[*]"));

        WebElement checkAllWebElement = fDriver.findElement(By.id("check1"));
        // Act
        // Need to dig down the label child element, else the driver won't click
        // directly on it.
        multipleCheckBoxWebElement.get(0).findElement(By.tagName("label")).click();
        multipleCheckBoxWebElement.get(1).findElement(By.tagName("label")).click();
        multipleCheckBoxWebElement.get(2).findElement(By.tagName("label")).click();

        // Assert
        Assert.assertTrue(multipleCheckBoxWebElement.size() > 0);
        Assert.assertTrue(multipleCheckBoxWebElement.get(0).findElement(By.tagName("input")).isSelected());
        Assert.assertTrue(multipleCheckBoxWebElement.get(1).findElement(By.tagName("input")).isSelected());
        Assert.assertTrue(multipleCheckBoxWebElement.get(2).findElement(By.tagName("input")).isSelected());

        Assert.assertEquals("Check All", checkAllWebElement.getAttribute("value"));
    }

    @Test
    void checksAll() {
        // Arrange
        closeLightBox();
        List<WebElement> multipleCheckBoxWebElement = fDriver
                .findElements(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[*]"));
        WebElement checkAllWebElement = fDriver.findElement(By.id("check1"));
        // Act
        checkAllWebElement.click();

        // Assert
        Assert.assertTrue(multipleCheckBoxWebElement.size() > 0);

        Assert.assertTrue(multipleCheckBoxWebElement.get(0).findElement(By.tagName("input")).isSelected());
        Assert.assertTrue(multipleCheckBoxWebElement.get(1).findElement(By.tagName("input")).isSelected());
        Assert.assertTrue(multipleCheckBoxWebElement.get(2).findElement(By.tagName("input")).isSelected());
        Assert.assertTrue(multipleCheckBoxWebElement.get(3).findElement(By.tagName("input")).isSelected());

        Assert.assertEquals("Uncheck All", checkAllWebElement.getAttribute("value"));
    }

    @Test
    void unchecksAll() {
        // Arrange
        closeLightBox();
        List<WebElement> multipleCheckBoxWebElement = fDriver
                .findElements(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[2]/div[2]/div[*]"));
        WebElement checkAllWebElement = fDriver.findElement(By.id("check1"));
        // Act

        // Assert
        Assert.assertTrue(multipleCheckBoxWebElement.size() > 0);

        Assert.assertFalse(multipleCheckBoxWebElement.get(0).findElement(By.tagName("input")).isSelected());
        Assert.assertFalse(multipleCheckBoxWebElement.get(1).findElement(By.tagName("input")).isSelected());
        Assert.assertFalse(multipleCheckBoxWebElement.get(2).findElement(By.tagName("input")).isSelected());
        Assert.assertFalse(multipleCheckBoxWebElement.get(3).findElement(By.tagName("input")).isSelected());

        Assert.assertEquals("Check All", checkAllWebElement.getAttribute("value"));
    }

}
