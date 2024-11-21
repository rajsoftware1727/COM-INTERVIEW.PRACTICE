package Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import com.ibm.icu.util.Calendar;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TravelcityDateSelection {

	static WebDriver driver;

	@Test
	public static void date() throws InterruptedException, ParseException {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.travelocity.com");

		driver.findElement(By.xpath("//button[@name='EGDSDateRange-date-selector-trigger']")).click();
		TravelcityDateSelection.selectDate("12-02-2025");
		Thread.sleep(Duration.ofSeconds(2));
		driver.close();
	}

	public static void selectDate(String targetDate) throws ParseException {
		Calendar cal = Calendar.getInstance();
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse(targetDate);
		cal.setTime(date);
		int tMonth = cal.get(Calendar.MONTH);
		int tYear = cal.get(Calendar.YEAR);

		int tDay = cal.get(Calendar.DAY_OF_MONTH);

		// get data actual data s that setup the comparisontargetMonth
		System.out.println("month is " + tMonth);
		System.out.println("year is " + tYear);
		System.out.println("day is " + tDay);

		/*
		 * String currentMonthYear=driver.findElement(By.
		 * xpath("(//div[@class='uitk-month uitk-month-single']/span[@class='uitk-align-center uitk-month-label'])[2]"
		 * )).getText(); System.out.println(currentMonthYear);
		 */

	}

}
