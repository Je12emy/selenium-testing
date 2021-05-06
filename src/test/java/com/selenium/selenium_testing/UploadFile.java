package com.selenium.selenium_testing;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UploadFile {
    @Test
    void uploadsPicture() {
        String baseUrl = "http://demo.guru99.com/test/upload/";
        WebDriver driver = new FirefoxDriver();

        driver.get(baseUrl);
        WebElement uploadElement = driver.findElement(By.id("uploadfile_0"));

        uploadElement.sendKeys("/home/dio/Documents/2021-03-30_08-48.png");

        driver.findElement(By.id("terms")).click();

        driver.findElement(By.name("send")).click();
    }
}
