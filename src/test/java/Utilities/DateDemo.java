package Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DateDemo {

	WebDriver driver;

	@Test(enabled = false)
	public void selectData() throws Exception {
		System.out.println("thread name is " + Thread.currentThread().getName());
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.hyrtutorials.com/p/calendar-practice.html");
		driver.findElement(By.id("second_date_picker")).click();
		try {
			String tDate = "15-08-1990";
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(tDate));
			int tDay = calendar.get(Calendar.DAY_OF_MONTH);
			int tMonth = calendar.get(Calendar.MONTH);
			int tYear = calendar.get(Calendar.YEAR);
			// get actual date from calendar
			String aMonthYear = driver.findElement(By.className("ui-datepicker-title")).getText();
			System.out.println(aMonthYear);
			calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(aMonthYear));
			int aDay = calendar.get(Calendar.DAY_OF_MONTH);
			int aMonth = calendar.get(Calendar.MONTH);
			int aYear = calendar.get(Calendar.YEAR);

			// check actual date and target date

			while (tMonth < aMonth || tYear < aYear) {
				driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
				aMonthYear = driver.findElement(By.className("ui-datepicker-title")).getText();
				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(aMonthYear));
				aDay = calendar.get(Calendar.DAY_OF_MONTH);
				aMonth = calendar.get(Calendar.MONTH);
				aYear = calendar.get(Calendar.YEAR);
			}
			while (tMonth > aMonth || tYear > aYear) {
				driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
				aMonthYear = driver.findElement(By.className("ui-datepicker-title")).getText();
				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(aMonthYear));
				aDay = calendar.get(Calendar.DAY_OF_MONTH);
				aMonth = calendar.get(Calendar.MONTH);
				aYear = calendar.get(Calendar.YEAR);
			}

			driver.findElement(By.xpath(
					"//table[@class='ui-datepicker-calendar']//td[not(contains(@class,'ui-datepicker-other-month'))]//a[text()="
							+ tDay + "]"))
					.click();

		} catch (ParseException e) {
			throw new Exception("date format is invalid" + e.getMessage() + e.getStackTrace());
		}

	}

	@Test(invocationCount = 5, enabled = false)
	public void InvocationCountDemo() {
		System.out.println("thread name is " + Thread.currentThread().getName());

		System.out.println("hai am Rajesh");
	}

	@Test(expectedExceptions = Exception.class)
	public void test2() {
		System.out.println(5 / 0);
	}

}
