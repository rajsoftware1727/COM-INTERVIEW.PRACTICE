package com.selenium.concepts;

import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocatorsDemo {

	WebDriver driver;

	@BeforeTest
	public void open() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		driver.get("http://www.google.com");
	}

	@Test(enabled = false)
	public void locators() {

		/*
		 * locators by css property
		 */
		// id
		WebElement element = driver.findElement(By.cssSelector("img.lnXdpd"));
		LocatorsDemo.border(driver, element);

	}

	public static void border(WebDriver driver, WebElement t) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", t);
	}

	@Test(enabled = true)
	public void locateDemo() {
		/*
		 * ^ tells the browser to match a string that’s at the beginning of an
		 * attributes value. $ matches a string at the end of an attributes value.
		 * matches any part of an attributes value. ~ matches specific letters anywhere
		 * in the attributes value even if it has spaces. | matches whole words at the
		 * beginning.
		 */

		// WebElement t=driver.findElement(By.cssSelector("img[class^='lnX'"));
		// WebElement t=driver.findElement(By.cssSelector("img[class$='dpd'"));
		// WebElement t=driver.findElement(By.cssSelector("img[class*='lnX'"));
		WebElement t = driver.findElement(By.cssSelector("img[class~=''"));

		LocatorsDemo.border(driver, t);

	}

}
