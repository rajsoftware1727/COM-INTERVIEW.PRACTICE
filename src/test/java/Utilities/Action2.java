package Utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.concepts.SeleniumPractice;

public class Action2 {

	public static WebDriver driver1;

	public static WebElement t;

	public static WebElement getWebElement(String value, String locatorName) {
		driver1 = SeleniumPractice.driver;
		switch (locatorName) {
		case "id":

			t = driver1.findElement(Action2.ByID(value));
			border(t);

			break;

		case "xpath":
			t = driver1.findElement(Action2.ByXpath(value));
			border(t);

			break;

		case "css":
			t = driver1.findElement(Action2.Bycss(value));
			border(t);

			break;

		}
		return t;
	}

	public static void border(WebElement t) {
		JavascriptExecutor js = (JavascriptExecutor) driver1;
		js.executeScript("arguments[0].style.border='5px solid red'", t);
	}

	public static By ByID(String value) {
		return By.id(value);
	}

	public static By ByXpath(String value) {
		return By.xpath(value);
	}

	public static By Bycss(String value) {
		return By.cssSelector(value);
	}

	public static void waitDemo() {
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeContains(driver1.findElement(By.xpath("//[@input='lnXdpd']")), "style",
				"border: 5px solid red;"));

	}

}
