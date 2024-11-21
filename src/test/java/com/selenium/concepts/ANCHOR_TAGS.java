package com.selenium.concepts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ANCHOR_TAGS {
	
	static WebDriver driver;
	int count=0;
	
	@Test
	public void findAnchorTags()
	{
		
		ANCHOR_TAGS.openBrowser();
		List<WebElement> webElements=driver.findElements(By.tagName("a"));
		for(WebElement t:webElements)
		{
			String url=t.getAttribute("href");
			System.out.println(url);
			count++;
		}
		ANCHOR_TAGS.close();
		System.out.println(count);
		System.out.println(webElements.size());
	}
	
	public static void openBrowser()
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("http://www.google.com");
	}
	
	public static void close()
	{
		driver.close();
	}
	
	

}
