package com.selenium.concepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SikuliDemo {

	WebDriver driver;

	@Test
	public void Demo() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.get("https://cgi-lib.berkeley.edu/ex/fup.html");
		driver.findElement(By.xpath("//input[@type='file']")).click();
	}

}
