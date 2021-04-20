package com.selenium.selenium_testing;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeleniumTestingApplicationTests {

	private static final String siteURL = "https://duckduckgo.com/";
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

	@AfterAll
	static void close() {
		fDriver.close();
	}

	@Test
	void isSEOCompliant() {
		// Arrange
		// System.setProperty("webdriver.firefox.driver",
		// "/home/dio/Documents/Github/Je12emy/selenium_testing/geckodriver");

		// Act
		fDriver.findElement(By.id("search_form_input_homepage")).sendKeys("valuelabs");
		fDriver.findElement(By.id("search_button_homepage")).click();

		// Asert
		Assert.assertTrue(
				fDriver.findElement(By.partialLinkText("ValueLabs | Global software, Product development and IT ..."))
						.isDisplayed());
	}

}
