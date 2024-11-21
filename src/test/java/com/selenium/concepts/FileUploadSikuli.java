package com.selenium.concepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FileUploadSikuli {

	WebDriver driver;

	@Test
	public void upload() throws FindFailed {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://lpigroup.com/careers/");
		driver.findElement(By.id("gform_browse_button_1_5")).click();
		String filepath = "C:\\Users\\AKILA RAJESH\\Downloads\\New folder (2)\\filepathimage.jpg";
		String open = "C:\\Users\\AKILA RAJESH\\Downloads\\New folder (2)\\open.jpg";
		String resume = "C:\\Users\\AKILA RAJESH\\OneDrive\\Desktop\\Resume.pdf";
		Pattern p = new Pattern(filepath);
		Pattern p1 = new Pattern(open);
		Screen s = new Screen();
		s.wait(p, 20);
		s.type(p, resume);
		s.click(p1);
		System.out.println("done");
	}

}
