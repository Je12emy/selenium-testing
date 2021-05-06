package com.selenium.selenium_testing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReadCredentials {
    private static final String siteURL = "https://www.seleniumeasy.com/test/input-form-demo.html";
    private static WebDriver fDriver;

    @BeforeAll
    static void navigateToSite() {
        FirefoxOptions driverFirefoxOptions = new FirefoxOptions();
        driverFirefoxOptions.addArguments("--no-sandbox");
        driverFirefoxOptions.addArguments("--disable-dev-shm-usage");
        driverFirefoxOptions.addArguments("--headless");

        fDriver = new FirefoxDriver(driverFirefoxOptions);
        fDriver.navigate().to(siteURL);
    }

    // @BeforeEach
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
    void readCredentialsAndFillsForm() throws IOException {
        // Arrange
        FileInputStream fs;
        fs = new FileInputStream("src/main/resources/TestCredentials.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Select select = new Select(fDriver.findElement(By.xpath(
                "/html[1]/body[1]/div[2]/div[1]/div[2]/section[1]/form[1]/fieldset[1]/div[7]/div[1]/div[1]/select[1]")));

        Row row = sheet.getRow(1);
        Cell firstName = row.getCell(1);

        row = sheet.getRow(2);
        Cell lastName = row.getCell(1);

        row = sheet.getRow(3);
        Cell email = row.getCell(1);

        row = sheet.getRow(4);
        Cell phone = row.getCell(1);

        row = sheet.getRow(5);
        Cell address = row.getCell(1);

        row = sheet.getRow(6);
        Cell city = row.getCell(1);

        row = sheet.getRow(7);
        Cell state = row.getCell(1);

        row = sheet.getRow(8);
        Cell zipCode = row.getCell(1);

        row = sheet.getRow(9);
        Cell website = row.getCell(1);

        row = sheet.getRow(10);
        Cell hosting = row.getCell(1);

        row = sheet.getRow(11);
        Cell description = row.getCell(1);

        ArrayList<Cell> cellValues = new ArrayList<Cell>();
        cellValues.add(firstName);
        cellValues.add(lastName);
        cellValues.add(email);
        cellValues.add(phone);
        cellValues.add(address);
        cellValues.add(city);
        cellValues.add(zipCode);
        cellValues.add(website);

        List<WebElement> inputWebElements;
        WebElement otherWebElement;

        // Act
        inputWebElements = fDriver.findElements(By.xpath(
                "/html[1]/body[1]/div[2]/div[1]/div[2]/section[1]/form[1]/fieldset[1]/div[*]/div[1]/div[1]/input[1]"));

        for (int i = 0; i < inputWebElements.size(); i++) {
            inputWebElements.get(i).click();
            inputWebElements.get(i).sendKeys(cellValues.get(i).toString());
        }

        select.selectByVisibleText(state.toString().trim());

        if (hosting.toString().equalsIgnoreCase("Yes")) {
            otherWebElement = fDriver.findElement(By.xpath(
                    "/html[1]/body[1]/div[2]/div[1]/div[2]/section[1]/form[1]/fieldset[1]/div[10]/div[1]/div[1]/label[1]"));
        } else {
            otherWebElement = fDriver.findElement(By.xpath(
                    "/html[1]/body[1]/div[2]/div[1]/div[2]/section[1]/form[1]/fieldset[1]/div[10]/div[1]/div[2]/label[1]"));
        }

        otherWebElement.click();

        otherWebElement = fDriver.findElement(By.xpath("//textarea[@class='form-control']"));
        otherWebElement.click();
        otherWebElement.sendKeys(description.toString());

        otherWebElement = fDriver.findElement(By.xpath("//button[contains(text(),'Send')]"));
        // Assert
        Assert.assertTrue(otherWebElement.isEnabled());
        workbook.close();
    }
}
