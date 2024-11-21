package Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DateClass {

	public static void selectDate(WebDriver driver, String targetdate, String dateFormat1) throws Exception {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hyrtutorials.com/p/calendar-practice.html");
		driver.findElement(By.id("second_date_picker")).click();

		// first get the targetted date
		String targetDate = targetdate;
		try {
			// set the date format
			SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormat1);
			dateFormat.setLenient(false);
			// call the parse method to pass targetDate as parameter
			Date date = dateFormat.parse(targetDate);// return the type Date
			Calendar calendar = Calendar.getInstance();// return the current date instance
			calendar.setTime(date);
			int targetMonth = calendar.get(calendar.MONTH);
			int targetYear = calendar.get(calendar.YEAR);
			int targetDay = calendar.get(calendar.DAY_OF_MONTH);

			// get the month and year of current calendar displaying

			String actualmonthYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
			calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualmonthYear));
			int currentMonth = calendar.get(calendar.MONTH);
			int currentYear = calendar.get(calendar.YEAR);

			// select the past dates

			while (targetMonth < currentMonth || targetYear < currentYear) {
				driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
				actualmonthYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualmonthYear));
				currentMonth = calendar.get(calendar.MONTH);
				currentYear = calendar.get(calendar.YEAR);
			}

			// select the future dates
			while (targetMonth > currentMonth || targetYear > currentYear) {
				driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
				actualmonthYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualmonthYear));
				currentMonth = calendar.get(calendar.MONTH);
				currentYear = calendar.get(calendar.YEAR);
			}

			driver.findElement(By.xpath(
					"//table[@class='ui-datepicker-calendar']//td[not(contains(@class,'ui-datepicker-other-month'))]//a[text()="
							+ targetDay + "]"))
					.click();

		} catch (ParseException e) {
			throw new Exception("entered date format is not valid");
		}
	}

}
