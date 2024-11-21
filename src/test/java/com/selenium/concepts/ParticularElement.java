package com.selenium.concepts;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParticularElement {

	WebDriver driver;

	@Test
	public void getImage() throws IOException {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.google.com");
		WebElement t = driver.findElement(By.className("lnXdpd"));
		TakesScreenshot ts = (TakesScreenshot) driver;
		File f = ts.getScreenshotAs(OutputType.FILE);
		BufferedImage bImage = ImageIO.read(f);
		Point p = t.getLocation();
		int x = t.getSize().getWidth();
		int y = t.getSize().getHeight();
		BufferedImage j = bImage.getSubimage(p.getX(), p.getY(), x, y);
		ImageIO.write(j, "png", f);
		FileUtils.copyFile(f, new File("D:\\JAVA PROJECTS\\COM.INTERVIEW.PRACTICE\\screenshot1\\100.png"));
		System.out.println("success");

	}

}
