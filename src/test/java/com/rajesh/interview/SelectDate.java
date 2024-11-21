package com.rajesh.interview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectDate {
	WebDriver driver;

	@Test(enabled = false)
	public void SelectDate1() throws Exception {

		// get target Date

		String targetDate = "29-mar-2024";
		// set the date format by dateformat class

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar calendar = Calendar.getInstance();

		try {
			Date Tdate = dateFormat.parse(targetDate);// return type of Date class
			calendar.setTime(Tdate);

			// get targetted date month and year

			int Tday = calendar.get(Calendar.DAY_OF_MONTH);
			int Tmonth = calendar.get(Calendar.MONTH);
			int Tyear = calendar.get(Calendar.YEAR);

		} catch (ParseException e) {
			throw new Exception("user entered invalid format");
		}

	}

	@Test(enabled = true)
	public void openBrowser() throws Exception {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.redbus.in/");//
		driver.findElement(By.id("onwardCal")).click();
		String actualDate = driver.findElement(By.xpath("(//div[contains(.,'Feb 20241 Holiday')])[12]")).getText();
		System.out.println(actualDate);
		String[] Adate = actualDate.split(" ");
		System.out.println(Adate[0] + Adate[1]);
		System.out.println("done");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(new SimpleDateFormat("MMMyyyy").parse(Adate[0] + Adate[1]));
			int Aday = calendar.get(Calendar.DAY_OF_MONTH);
			int Amonth = calendar.get(Calendar.MONTH);
			int Ayear = calendar.get(Calendar.YEAR);

			String Tdate = "29-feb-2024";
			calendar.setTime(new SimpleDateFormat("dd-MMM-yyyy").parse(Tdate));
			int Tday = calendar.get(Calendar.DAY_OF_MONTH);
			int Tmonth = calendar.get(Calendar.MONTH);
			int Tyear = calendar.get(Calendar.YEAR);

			while (Tmonth > Amonth || Tyear > Ayear) {
				// click forward arrow button

				driver.findElement(By.xpath("//svg[contains(@height,'15px')]")).click();

				actualDate = driver.findElement(By.xpath("(//div[contains(.,'Feb 20241 Holiday')])[12]")).getText();
				Adate = actualDate.split(" ");
				calendar = Calendar.getInstance();
				System.out.println(Adate[0] + Adate[1]);
				calendar.setTime(new SimpleDateFormat("MMMyyyy").parse(Adate[0] + Adate[1]));
				Aday = calendar.get(Calendar.DAY_OF_MONTH);
				Amonth = calendar.get(Calendar.MONTH);
				Ayear = calendar.get(Calendar.YEAR);

			}

		} catch (ParseException e) {
			throw new Exception("date is invalid");
		}

	}

}
