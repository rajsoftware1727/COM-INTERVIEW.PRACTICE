package com.selenium.concepts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JQueryDropDown {

	static WebDriver driver;

	@Test
	public void select() {
		JQueryDropDown.dropDown("choice 1", "choice 2");
	}

	public static void dropDown(String... choice) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.jqueryscript.net/demo/Drop-Down-Combo-Tree/");
		WebElement downArrow = driver.findElement(By.xpath("(//span[@class='comboTreeArrowBtnImg'])[1]"));
		downArrow.click();
		List<WebElement> choicesfullList = driver.findElements(By.xpath("//span[@class='comboTreeItemTitle']"));

		System.out.println(choicesfullList.size());
		if (!choice[0].equalsIgnoreCase("all")) {

			for (WebElement t : choicesfullList) {
				System.out.println(t.getText());
				for (String g : choice) {
					if (g.equalsIgnoreCase(t.getText())) {
						t.click();
						break;
					}
				}

			}
		} else {
			for (WebElement t : choicesfullList) {
				t.click();
			}

		}

	}

}
