package com.selenium.concepts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FileUploadDemo {
	WebDriver driver;

	// using robot class to upload the file
	@Test
	public void upload() throws AWTException {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://lpigroup.com/careers/");
		driver.findElement(By.id("gform_browse_button_1_5")).click();
		Robot r = new Robot();
		StringSelection s = new StringSelection("C:\\Users\\AKILA RAJESH\\OneDrive\\Desktop\\Resume.docx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		r.delay(300);

		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("file uploaded");

	}

}
