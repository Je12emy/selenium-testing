package com.selenium.selenium_testing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.selenium.selenium_testing.Pages.OrangeHRMDashboard;
import com.selenium.selenium_testing.Pages.OrangeHRMLogin;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

// Source: https://www.guru99.com/page-object-model-pom-page-factory-in-selenium-ultimate-guide.html
@SpringBootTest
class LoginAssignment {
    WebDriver driver;
    OrangeHRMLogin objLogin;
    OrangeHRMDashboard objDashboard;
    private final String LOGIN_URL = "https://opensource-demo.orangehrmlive.com/index.php/auth/login";

    private String username;
    private String password;

    @BeforeTest
    public void setup() {
        FirefoxOptions driverFirefoxOptions = new FirefoxOptions();
        driverFirefoxOptions.addArguments("--no-sandbox");
        driverFirefoxOptions.addArguments("--disable-dev-shm-usage");
        driverFirefoxOptions.addArguments("--headless");
        driver = new FirefoxDriver(driverFirefoxOptions);
        driver.get(LOGIN_URL);
    }

    @BeforeTest
    public void loadCredentials() {
        FileInputStream fs;
        try {
            fs = new FileInputStream("src/main/resources/TestData.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fs);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(2);
            username = row.getCell(4).toString();

            row = sheet.getRow(3);
            password = row.getCell(4).toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void test_Login_Page_Appears_Correct() {
        objLogin = new OrangeHRMLogin(driver);
        String loginFormHeader = objLogin.getLoginFormHeader();
        Assert.assertTrue(loginFormHeader.toLowerCase().equals("login panel"));
    }

    @Test
    void test_Signin_With_Correct_Credentials() {
        objLogin = new OrangeHRMLogin(driver);
        objDashboard = new OrangeHRMDashboard(driver);

        objLogin.loginToOrangeHRM(username, password);
        String dashHeader = objDashboard.getDashboardHeader();
        Assert.assertTrue(dashHeader.toLowerCase().equals("dashboard"));
    }

}
