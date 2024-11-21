package com.rajesh.interview;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LTICareer {

	WebDriver driver;

	@BeforeTest
	public void openBrowser() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.google.com");
	}

	@Test
	public void checkLTI() {
		driver.findElement(By.className("gLFyf")).sendKeys("lti career", Keys.SPACE);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='wM6W7d'])[4]")));
		List<WebElement> listOfLTI = driver.findElements(By.xpath("(//ul[@class='G43f7e'])[1]//li"));
		System.out.println(listOfLTI.size());
		for (WebElement t : listOfLTI) {
			if (t.getText().equalsIgnoreCase("lti mindtree career")) {
				t.click();
				break;
			}

		}
	}

	/*
	 * @AfterTest public void tearDown() { driver.quit(); }
	 */

}
