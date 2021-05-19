package com.selenium.selenium_testing;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

import com.selenium.selenium_testing.ExcelExportAndRead.ReadExcel;
import com.selenium.selenium_testing.operation.ReadObject;
import com.selenium.selenium_testing.operation.UIOperation;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

@SpringBootTest
public class Guru99ExecuteTest {
    @Test
    public void test_login_execute_login() throws Exception {
        FirefoxOptions driverFirefoxOptions = new FirefoxOptions();
        driverFirefoxOptions.addArguments("--no-sandbox");
        driverFirefoxOptions.addArguments("--disable-dev-shm-usage");
        driverFirefoxOptions.addArguments("--headless");
        WebDriver webDriver = new FirefoxDriver(driverFirefoxOptions);
        ReadExcel excelFile = new ReadExcel();
        ReadObject objectRepo = new ReadObject();
        Properties properties = objectRepo.getObjectRepository();
        UIOperation operation = new UIOperation(webDriver);

        // Get path in resource folder
        URL res = getClass().getClassLoader().getResource("Guru99ExcelFile.xlsx");
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();

        Sheet excelSheet = excelFile.readExcel(absolutePath, "Guru99ExcelFile.xlsx", "Guru99LoginPage");
        int rowCount = excelSheet.getLastRowNum() - excelSheet.getFirstRowNum();

        for (int i = 0; i < rowCount; i++) {
            Row row = excelSheet.getRow(i);
            if (row.getCell(0).toString().length() == 0) {
                System.out.println(row.getCell(1).toString() + "----" + row.getCell(2).toString() + "----"
                        + row.getCell(3).toString() + "----" + row.getCell(4).toString());
                operation.perform(properties, row.getCell(1).toString(), row.getCell(2).toString(),
                        row.getCell(3).toString(), row.getCell(4).toString());
            } else {
                System.out.println("New Testcase->" + row.getCell(0).toString() + " started.");
            }
        }
    }

}
