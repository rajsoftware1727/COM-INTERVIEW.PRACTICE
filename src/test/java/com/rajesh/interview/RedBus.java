package com.rajesh.interview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedBus {

	WebDriver driver;

	@Test
	public void redBusJourney() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.redbus.in/");
		selectDate();

	}

	public void selectDate() {
		String targetDate = "21-02-2024";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calendar = Calendar.getInstance();
		try {
			Date Tdate = dateFormat.parse(targetDate);
			calendar.setTime(Tdate);
			int Tday = calendar.get(Calendar.DAY_OF_MONTH);
			int Tmonth = calendar.get(Calendar.MONTH);
			int Tyear = calendar.get(Calendar.YEAR);
			// get the month and year of current calendar

			driver.findElement(By.xpath("//i[contains(@class,'sc-cSHVUG NyvQv icon icon-datev2')]")).click();
			String CmonthYear = driver
					.findElement(
							By.xpath("//div[@class='DayNavigator__IconBlock-qj8jdz-2 iZpveD'][contains(.,'Feb 2024')]"))
					.getText();
			String currentMonthYear[] = CmonthYear.split(" ");
			System.out.println(CmonthYear);

			calendar.setTime(new SimpleDateFormat("MMMyyyy").parse(currentMonthYear[0] + currentMonthYear[1]));
			int Cmonth = calendar.get(Calendar.MONTH);
			int Cyear = calendar.get(Calendar.YEAR);
			System.out.println(Cmonth);
			System.out.println(Cyear);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
