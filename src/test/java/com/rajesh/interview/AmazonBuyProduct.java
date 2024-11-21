package com.rajesh.interview;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonBuyProduct {

	static WebDriver driver;

	@Test
	public void buyIPHONE() throws Exception {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.amazon.in/");
		String parentWindow = driver.getWindowHandle();
		System.out.println("first page title is :" + driver.getTitle());
		AmazonBuyProduct.takeScreenshotPage("firstPage");
		String childWindow;
		WebElement productSerachBar = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		productSerachBar.sendKeys("iphone");
		List<WebElement> listOfIPhonesDisplay = driver
				.findElements(By.xpath("//div[@class='left-pane-results-container']/div"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(listOfIPhonesDisplay));

		driver.findElement(By.xpath("//span[(text()=' 15 pro max')]")).click();

		/*
		 * for(WebElement t:listOfIPhonesDisplay) { String
		 * varietyOfIPhones=t.getAttribute("aria-label");
		 * if(varietyOfIPhones.equalsIgnoreCase("iphone 15 128gb")) { t.click(); } }
		 */
		Set<String> windowID = driver.getWindowHandles();
		for (String windowid : windowID) {
			if (!windowid.equalsIgnoreCase(parentWindow)) {
				driver.switchTo().window(windowid);

			}
		}
		System.out.println("second page title is :" + driver.getTitle());
		AmazonBuyProduct.takeScreenshotPage("secondPage");

		childWindow = driver.getWindowHandle();
		String uniqueProductWindow;
		driver.findElement(By.xpath("(//span[text()='Apple iPhone 15 Pro Max (256 GB) - Black Titanium'])[1]")).click();
		windowID = driver.getWindowHandles();

		for (String windowid : windowID) {
			if (!(windowid.equalsIgnoreCase(parentWindow) & windowid.equalsIgnoreCase(childWindow))) {

				driver.switchTo().window(windowid);
			}
		}
		System.out.println("Third page title is :" + driver.getTitle());
		AmazonBuyProduct.takeScreenshotPage("ThirdPage");

		uniqueProductWindow = driver.getWindowHandle();

		String price = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[5]")).getText();
		System.out.println("mobile price is :" + price);
		// click buy now button
		driver.findElement(By.id("buy-now-button")).click();
		windowID = driver.getWindowHandles();
		for (String windowid : windowID) {
			driver.switchTo().window(windowid);
		}
		String buynowWindow = driver.getWindowHandle();
		System.out.println("Fourth page title is :" + driver.getTitle());
		AmazonBuyProduct.takeScreenshotPage("FourthPage");

		// take screen shot page

		driver.close();
	}

	public static void takeScreenshotPage(String pageName) throws Exception {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File("D:\\screenshotImages\\" + pageName + ".jpg");
		try {
			FileUtils.copyFile(screenshot, destFile);
		} catch (IOException e) {

			throw new Exception("file not found");
		}
	}

}
