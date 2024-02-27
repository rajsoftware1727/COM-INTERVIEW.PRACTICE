package com.selenium.concepts;

 import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.DateClass;
import Utilities.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class SeleniumPractice {
	
	static WebDriver driver;
	java.util.logging.Logger logger;
	
	//navigation command 
	
	@BeforeTest(enabled=true)
	public void openBrowser()
	{
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		driver.manage().window().maximize();
		logger=TestListener.logger;
 		logger.info("browser opened");
 		
		driver.get("http://www.google.com");
		logger.info("getting url and ready to open");
		//driver.get("https://demo.automationtesting.in/Static.html");

		
	}
	
	@Test(enabled=false)
	public void resetWindowSize()
	{
		//some times you may set window size explicitly by using Dimension class
		
		Dimension dimension=driver.manage().window().getSize();
		int windowHeight=dimension.height;
		int windowWidth=dimension.width;
		System.out.println("browser window sizes : height is :"+windowHeight+" "+"width is :"+windowWidth);
		dimension=new Dimension(500,500);
		driver.manage().window().setSize(dimension);
	}
	
	@AfterTest(enabled=true)
	public void tearDown()
	{
		driver.quit();
	}
	
	//navigation command 
	
	@Test(enabled=false)
	public void NavigateCommand()
	{
		driver.navigate().refresh();
		driver.navigate().back();
		driver.navigate().forward();
	}
	
	//border to webelement
	
	public void borderElement(WebElement element)
	{
 	}
	
	//date format
	
	@Test(enabled=false)
	public void setDate() throws Exception 
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hyrtutorials.com/p/calendar-practice.html");
		driver.findElement(By.id("second_date_picker")).click();
 		
 		 
		try
		{
			Calendar calendar=Calendar.getInstance();
			

			String date="32-feb-2021";
			SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
			dateFormat.setLenient(false);
 			Date formattedDate=dateFormat.parse(date);
 			calendar.setTime(formattedDate);
 			int targetDay=calendar.get(Calendar.DAY_OF_MONTH);
 	 		int targetMonth=calendar.get(Calendar.MONTH);
 	 		int targetYear=calendar.get(Calendar.YEAR);
			String actualmonthYear=driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
			System.out.println(actualmonthYear);

			calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualmonthYear));
			int actualMonth=calendar.get(Calendar.MONTH);
 	 		int actualYear=calendar.get(Calendar.YEAR);
 	 		while(targetMonth<actualMonth||targetYear<actualYear)
 	 		{
 	 			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
 	 			  actualmonthYear=driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();

 				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualmonthYear));
 				  actualMonth=calendar.get(Calendar.MONTH);
 	 	 		  actualYear=calendar.get(Calendar.YEAR);
 	 		}
			
  		 
 		driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//td[not(contains(@class,'ui-datepicker-other-month'))]//a[text()="+targetDay+"]")).click();


		}
		catch(ParseException e)
		{
			throw new Exception("date is invalid");
		}
	}
	
	 
	
	
	@Test(enabled=false)
	public void pickDate() throws Exception 
	{
		try {
			DateClass.selectDate(driver,"15-08-1990","dd-MM-yyyy");
		} catch (ParseException e) {
			throw new Exception("user entred invalid date ");
  		}
	}
	
	@Test(enabled=false)
	public void MouseEvents()
	{
		//Context click or right click
		
		/*
		 * create the object of Actions class
		 * Generate the action sequence
		 * Build the sequence and perform 
		 */
		
		// open google then type google press with shift key result GOOGLE
		
		WebElement inputTextBar=driver.findElement(By.className("gLFyf"));
		Actions actions=new Actions(driver);
		actions.keyDown(inputTextBar,Keys.SHIFT);
		actions.sendKeys("google");
		actions.keyUp(Keys.SHIFT);
		Action action=actions.build();
		action.perform();
		
		//also write 
		//actions.keyDown(inputTextBar,Keys.SHIFT).sendKeys("google").keyUp(Keys.SHIFT).build().perform();
	}
	
	@Test(enabled=false)
	public void DragDropDemo()
	{
 		Actions actions=new Actions(driver);
 		//scroll the page 
 		//actions.sendKeys(Keys.PAGE_DOWN).build().perform();
 		JavascriptExecutor js=(JavascriptExecutor)driver;
 		js.executeScript("window.scrollBy(0,3000)");
		WebElement source=driver.findElement(By.xpath("//img[@id='mongo']"));
		WebElement target=driver.findElement(By.xpath("//div[@id='droparea']"));

		
		actions.dragAndDrop(source,target);
		
		actions.contextClick(source);
		
	}
	
	@Test(enabled=false)
	public void JavaScriptExecutorDemo()
	{
		WebElement searchBar=driver.findElement(By.xpath("//textarea[@class='gLFyf']"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click",searchBar);
		//js.executeScript("alert('yes am clicked search bar google');");
		String URL=js.executeAsyncScript("return document.URL").toString();
		System.out.println("URL of the page "+ URL);
		String Title=js.executeAsyncScript("return document.title").toString();
		System.out.println("Title of the page "+Title);
		
		
	}
	
	@Test(enabled=false)
	public void findAppsINGoogleHomePage()
	{
		driver.findElement(By.xpath("//a[@aria-label='Google apps']")).click();
		List<WebElement> appsNames=driver.findElements(By.xpath("//ul[@jsname='k77Iif']/li"));
		for(WebElement appnames:appsNames)
		{
			System.out.println(appnames.getText());
 		}
	}
	
	@Test(enabled=false)
	public void findLinksINGooglePage()
	{
		List<WebElement> anchorTag=driver.findElements(By.tagName("a"));
		for(WebElement e:anchorTag)
		{
			String link=e.getAttribute("href");
			System.out.println(link);
		}
		
		 
	}
	
	//Desired capabilities
	
	@Test(enabled=false)
	public void DesiredCapabilitiesTest()
	{
		EdgeOptions caps=new EdgeOptions();
		driver=new EdgeDriver(caps);
 		caps.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
 		
		driver.get("http://www.google.com");
	}
	
 	public static String getScreenShot()
	{
		TakesScreenshot shot=(TakesScreenshot)driver;
		return shot.getScreenshotAs(OutputType.BASE64);
		  
	}
 	
 	@Test(enabled=false)
 	public void takeScreenShotDemo() throws IOException
 	{
 		//type cast the driver into TakesScreenshot interface
 		
		TakesScreenshot shot=(TakesScreenshot)driver;
		/*File srcFile=shot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"//screenshot"+"//google.jpg"));*/
		//shot.getScreenshotAs(OutputType.BASE64);
		byte[] image=shot.getScreenshotAs(OutputType.BYTES);
		FileOutputStream fo=new FileOutputStream(System.getProperty("user.dir")+"//screenshot"+"//google1.jpg");
		fo.write(image);
		fo.close();

 	}
 	@Test(enabled=false)
 	public void FileOutputStreamDemo() throws IOException
 	{
 		String data="Hai how r u";
		FileOutputStream fo=new FileOutputStream(System.getProperty("user.dir")+"//screenshot"+"//Data.txt");
		byte[] g=data.getBytes();
		fo.write(g);
		fo.close();

 		
 	}
 	
 	//take screenshot by using Ashot class
 	
 	@Test(enabled=false)
 	public void screenShotByAshot() throws IOException
 	{
 		//create the object of Ashot class
  		AShot shot=new AShot();
		Screenshot screenShot=shot.shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver); //it take screen shot
 		ImageIO.write(screenShot.getImage(),"jpg",new File(System.getProperty("user.dir")+"screenshot"+"\\30000.jpg"));  //it write the screen shot image to given file
 		
 	}
 	
 	@Test(enabled=true)
 	public void compareScreenshotImages() throws IOException
 	{
 		//directly take screenshot particular element
 		/* WebElement googleImage=driver.findElement(By.xpath("//img[@class='lnXdpd']"));
 		File src=googleImage.getScreenshotAs(OutputType.FILE);
 		FileUtils.copyFile(src,new File(System.getProperty("user.dir")+"//screenshot//actual.jpg"));*/
 		BufferedImage expectedImage=ImageIO.read(new File(System.getProperty("user.dir")+"//screenshot//actual.jpg"));
 		//getting actual image
 		 WebElement googleImage=driver.findElement(By.xpath("//img[@class='lnXdpd']"));
 		TakesScreenshot ts=(TakesScreenshot)driver;
 		File f=ts.getScreenshotAs(OutputType.FILE);
 		BufferedImage g=ImageIO.read(f);
 		//get location of element
 		org.openqa.selenium.Point t=googleImage.getLocation();
 		int width=googleImage.getSize().getWidth();
 		int height=googleImage.getSize().getHeight();
 		BufferedImage j=g.getSubimage(t.getX(),t.getY(),width,height);
 		ImageIO.write(j,"png", f);
 		FileUtils.copyFile(f,new File(System.getProperty("user.dir")+"//screenshot//actual1.jpg"));
 		

 		
 		
 		 
		
 		
 	}

	

}





	 
	
	 

 
