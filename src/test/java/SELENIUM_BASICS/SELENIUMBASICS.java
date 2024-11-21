package SELENIUM_BASICS;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SELENIUMBASICS {
	
	static WebDriver driver;
	
	 
	 
	
	
	
	
	
	
	
	
	
	public static void border(WebElement t)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.border='3px solid red'",t);
	}
	
	public static void openBrowser(String url)
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
	}
	
	 
	
	@Test(enabled=false)
	 public static void openGoogle()
	 {
		SELENIUMBASICS.openBrowser("http://www.google.com");
		WebElement t=driver.findElement(By.xpath("//img[@alt='Google']/ancestor::div[1]"));
		SELENIUMBASICS.border(t);
		
	 }
	
	@Test(enabled=true)
	public static void readExcelData() throws IOException
	{
		FileInputStream fi=new FileInputStream("");
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sheet=wb.getSheet("");
		int totalRows=sheet.getPhysicalNumberOfRows();
		XSSFRow currentRow=sheet.getRow(0);
		int totalCellcount=currentRow.getPhysicalNumberOfCells();
		
	}

}


