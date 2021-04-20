package com.selenium.selenium_testing;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeleniumTestingApplicationTests {

	@Test
	void isSEOCompliant() {
		// Arrange
		System.setProperty("webdriver.firefox.driver",
				"/home/dio/Documents/Github/Je12emy/selenium_testing/geckodriver");
		WebDriver fDriver = new FirefoxDriver();
		fDriver.navigate().to("https://duckduckgo.com/");

		// Act
		fDriver.findElement(By.id("search_form_input_homepage")).sendKeys("valuelabs");
		fDriver.findElement(By.id("search_button_homepage")).click();

		// Asert
		Assert.assertTrue(
				fDriver.findElement(By.partialLinkText("ValueLabs | Global software, Product development and IT ..."))
						.isDisplayed());
	}

}
