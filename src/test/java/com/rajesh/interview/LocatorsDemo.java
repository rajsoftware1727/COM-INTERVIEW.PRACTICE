package com.rajesh.interview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocatorsDemo {

	WebDriver driver;

	/*
	 * by using css selectors substring locate the webelement
	 */
	@Test
	public void findElement() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.get("http://www.google.com");
		// substring prefix
		driver.findElement(By.cssSelector("img[class^='lnXdpd']"));
		System.out.println("got it");
	}

}
