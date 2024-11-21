package com.selenium.concepts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FileUpload {

	WebDriver driver;
	static WebDriver drv;

	@Test(enabled = false)
	public void fileDownloadUsingRobot() throws AWTException {
		WebDriverManager.edgedriver().setup();

		driver = new EdgeDriver();
		driver.get("https://lpigroup.com/careers/");
		WebElement upload = driver.findElement(By.id("gform_browse_button_1_5"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.srollByVisibilityOfElement", upload);
		upload.click();

		String path = "C:\\Users\\AKILA RAJESH\\OneDrive\\Desktop\\Resume.docx";
		Robot robot = new Robot();
		StringSelection s = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);

		robot.delay(300);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(200);
		robot.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("uploaded by robot ");

	}

	@Test(enabled = false)
	public void d() throws AWTException {
		// TODO Auto-generated method stub
		WebDriverManager.edgedriver().setup();
		drv = new EdgeDriver(); // starting Firefox browser
		drv.manage().window().maximize(); // maximizing window
		drv.get("https://www.grammarly.com/plagiarism-checker");// open testing website

		JavascriptExecutor js = (JavascriptExecutor) drv; // Scroll operation using Js Executor
		js.executeScript("window.scrollBy(0,200)"); // Scroll Down(+ve) upto browse option

		WebElement browse = drv.findElement(By.xpath("(//span[@class='content_PbRSndwM'])[10]"));
		// using linkText, to click on browse element
		browse.click(); // Click on browse option on the webpage

		// creating object of Robot class
		Robot rb = new Robot();

		// copying File path to Clipboard
		StringSelection str = new StringSelection("C:\\Users\\AKILA RAJESH\\OneDrive\\Desktop\\Resume.pdf");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		// press Contol+V for pasting
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		// release Contol+V for pasting
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		// for pressing and releasing Enter
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
	}

	@Test(enabled = false)
	public void fileDownloadUsingRobot1() throws AWTException, IOException {
		WebDriverManager.edgedriver().setup();

		driver = new EdgeDriver();
		driver.get("https://lpigroup.com/careers/");
		WebElement upload = driver.findElement(By.id("gform_browse_button_1_5"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.srollByVisibilityOfElement", upload);
		upload.click();
		/*
		 * ControlFocus("open","","Edit1") ControlSetText("open","",
		 * "Edit1","C:\\Users\\AKILA RAJESH\\OneDrive\\Desktop\\Resume.docx");
		 * ControlFocus();
		 */
		Runtime.getRuntime().exec("C:\\Users\\AKILA RAJESH\\OneDrive\\Desktop\\upload.exe");

	}

	@Test(enabled = true)
	public void downloadFile() {
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> pref = new HashMap<String, Object>();
		pref.put("plugins.always_open_pdf_externally", true);
		pref.put("download.default_directory", "path");
		options.setExperimentalOption("pref", pref);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
		/*
		 * String fileType="PDF";
		 * driver.findElement(By.xpath("//a[starts-with(text(),'Download sample')]")).
		 * click();
		 */

	}

	public void doubt() {
		JavascriptExecutor driver = new EdgeDriver();

		JavascriptExecutor js = (JavascriptExecutor) driver;

	}
}
