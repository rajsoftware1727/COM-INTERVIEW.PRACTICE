
package com.selenium.concepts;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration;
import org.bouncycastle.util.Arrays.Iterator;
import org.openqa.selenium.Alert;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.Action2;
import Utilities.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class SeleniumPractice {

	public static WebDriver driver;
	java.util.logging.Logger logger;

	// navigation command

	@BeforeTest(enabled = true)
	public void openBrowser() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		logger = TestListener.logger;
		logger.info("browser opened");

		// driver.get("https://demo.guru99.com/test/upload/");
		// driver.get("https://demo.automationtesting.in/FileDownload.html");
		driver.get("https://file-examples.com/index.php/sample-documents-download/");
		logger.info("getting url and ready to open");
		// driver.get("https://demo.automationtesting.in/Static.html");
		// driver.get("https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver");

	}

	@Test(enabled = false)
	public void fileExists() {
		File f = new File("C:\\Users\\AKILA RAJESH\\Downloads\\samplefile.pdf");
		if (f.exists() == true) {
			System.out.println("passed");
		} else {
			System.out.println("fail");
		}
	}

	@Test(enabled = false)
	public void resetWindowSize() {
		// some times you may set window size explicitly by using Dimension class

		Dimension dimension = driver.manage().window().getSize();
		int windowHeight = dimension.height;
		int windowWidth = dimension.width;
		System.out.println("browser window sizes : height is :" + windowHeight + " " + "width is :" + windowWidth);
		dimension = new Dimension(500, 500);
		driver.manage().window().setSize(dimension);
	}

	@AfterClass(enabled = false)
	public void tearDown() {
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
		Alert t = wait.until(ExpectedConditions.alertIsPresent());
		if (t.getText().equalsIgnoreCase("yes element find")) {
			// Action2.waitDemo();
			driver.quit();
		}
	}

	// navigation command

	@Test(enabled = false)
	public void NavigateCommand() {
		driver.navigate().refresh();
		driver.navigate().back();
		driver.navigate().forward();
	}

	// border to webelement

	public void borderElement(WebElement element) {
	}

	// date format

	@Test(enabled = false)
	public void setDate() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hyrtutorials.com/p/calendar-practice.html");
		driver.findElement(By.id("second_date_picker")).click();

		try {
			Calendar calendar = Calendar.getInstance();

			String date = "32-feb-2021";
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			dateFormat.setLenient(false);
			Date formattedDate = dateFormat.parse(date);
			calendar.setTime(formattedDate);
			int targetDay = calendar.get(Calendar.DAY_OF_MONTH);
			int targetMonth = calendar.get(Calendar.MONTH);
			int targetYear = calendar.get(Calendar.YEAR);
			String actualmonthYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();
			System.out.println(actualmonthYear);

			calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualmonthYear));
			int actualMonth = calendar.get(Calendar.MONTH);
			int actualYear = calendar.get(Calendar.YEAR);
			while (targetMonth < actualMonth || targetYear < actualYear) {
				driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
				actualmonthYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']")).getText();

				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualmonthYear));
				actualMonth = calendar.get(Calendar.MONTH);
				actualYear = calendar.get(Calendar.YEAR);
			}

			driver.findElement(By.xpath(
					"//table[@class='ui-datepicker-calendar']//td[not(contains(@class,'ui-datepicker-other-month'))]//a[text()="
							+ targetDay + "]"))
					.click();

		} catch (ParseException e) {
			throw new Exception("date is invalid");
		}
	}

	/*
	 * @Test(enabled=false) public void pickDate() throws Exception { try {
	 * DateClass.selectDate(driver,"15-08-1990","dd-MM-yyyy"); } catch
	 * (ParseException e) { throw new Exception("user entred invalid date "); } }
	 */

	@Test(enabled = false)
	public void MouseEvents() {
		// Context click or right click

		/*
		 * create the object of Actions class Generate the action sequence Build the
		 * sequence and perform
		 */

		// open google then type google press with shift key result GOOGLE

		WebElement inputTextBar = driver.findElement(By.className("gLFyf"));
		Actions actions = new Actions(driver);
		actions.keyDown(inputTextBar, Keys.SHIFT);
		actions.sendKeys("google");
		actions.keyUp(Keys.SHIFT);
		Action action = actions.build();
		action.perform();

		// also write
		// actions.keyDown(inputTextBar,Keys.SHIFT).sendKeys("google").keyUp(Keys.SHIFT).build().perform();
	}

	@Test(enabled = false)
	public void DragDropDemo() {
		Actions actions = new Actions(driver);
		// scroll the page
		// actions.sendKeys(Keys.PAGE_DOWN).build().perform();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,3000)");
		WebElement source = driver.findElement(By.xpath("//img[@id='mongo']"));
		WebElement target = driver.findElement(By.xpath("//div[@id='droparea']"));

		actions.dragAndDrop(source, target);

		actions.contextClick(source);

	}

	@Test(enabled = false)
	public void JavaScriptExecutorDemo() {
		WebElement searchBar = driver.findElement(By.xpath("//textarea[@class='gLFyf']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].click",searchBar);
		// js.executeScript("alert('yes am clicked search bar google');");
		String URL = js.executeAsyncScript("return document.URL").toString();
		System.out.println("URL of the page " + URL);
		String Title = js.executeAsyncScript("return document.title").toString();
		System.out.println("Title of the page " + Title);

	}

	@Test(enabled = false)
	public void findAppsINGoogleHomePage() {
		driver.findElement(By.xpath("//a[@aria-label='Google apps']")).click();
		List<WebElement> appsNames = driver.findElements(By.xpath("//ul[@jsname='k77Iif']/li"));
		for (WebElement appnames : appsNames) {
			System.out.println(appnames.getText());
		}
	}

	@Test(enabled = false)
	public void findLinksINGooglePage() {
		List<WebElement> anchorTag = driver.findElements(By.tagName("a"));
		for (WebElement e : anchorTag) {
			String link = e.getAttribute("href");
			System.out.println(link);
		}

	}

	// Desired capabilities

	@Test(enabled = false)
	public void DesiredCapabilitiesTest() {
		EdgeOptions caps = new EdgeOptions();
		driver = new EdgeDriver(caps);
		caps.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);

		driver.get("http://www.google.com");
	}

	public static String getScreenShot() {
		TakesScreenshot shot = (TakesScreenshot) driver;
		return shot.getScreenshotAs(OutputType.BASE64);

	}

	@Test(enabled = false)
	public void takeScreenShotDemo() throws IOException {
		// type cast the driver into TakesScreenshot interface

		TakesScreenshot shot = (TakesScreenshot) driver;
		/*
		 * File srcFile=shot.getScreenshotAs(OutputType.FILE);
		 * FileUtils.copyFile(srcFile,new
		 * File(System.getProperty("user.dir")+"//screenshot"+"//google.jpg"));
		 */
		// shot.getScreenshotAs(OutputType.BASE64);
		byte[] image = shot.getScreenshotAs(OutputType.BYTES);
		FileOutputStream fo = new FileOutputStream(System.getProperty("user.dir") + "//screenshot" + "//google1.jpg");
		fo.write(image);
		fo.close();

	}

	@Test(enabled = false)
	public void FileOutputStreamDemo() throws IOException {
		String data = "Hai how r u";
		FileOutputStream fo = new FileOutputStream(System.getProperty("user.dir") + "//screenshot" + "//Data.txt");
		byte[] g = data.getBytes();
		fo.write(g);
		fo.close();

	}

	// take screenshot by using Ashot class

	@Test(enabled = false)
	public void screenShotByAshot() throws IOException {
		// create the object of Ashot class
		AShot shot = new AShot();
		Screenshot screenShot = shot.shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver); // it
																														// take
																														// screen
																														// shot
		ImageIO.write(screenShot.getImage(), "jpg",
				new File(System.getProperty("user.dir") + "screenshot" + "\\30000.jpg")); // it write the screen shot
																							// image to given file

	}

	@Test(enabled = false)
	public void compareScreenshotImages() throws IOException {
		// directly take screenshot particular element
		/*
		 * WebElement
		 * googleImage=driver.findElement(By.xpath("//img[@class='lnXdpd']")); File
		 * src=googleImage.getScreenshotAs(OutputType.FILE); FileUtils.copyFile(src,new
		 * File(System.getProperty("user.dir")+"//screenshot//actual.jpg"));
		 */
		BufferedImage expectedImage = ImageIO
				.read(new File(System.getProperty("user.dir") + "//screenshot//actual.jpg"));
		// getting actual image
		WebElement googleImage = driver.findElement(By.xpath("//img[@class='lnXdpd']"));
		TakesScreenshot ts = (TakesScreenshot) driver;
		File f = ts.getScreenshotAs(OutputType.FILE);
		BufferedImage g = ImageIO.read(f);
		// get location of element
		org.openqa.selenium.Point t = googleImage.getLocation();
		int width = googleImage.getSize().getWidth();
		int height = googleImage.getSize().getHeight();
		BufferedImage actualImage = g.getSubimage(t.getX(), t.getY(), width, height);
		ImageIO.write(actualImage, "png", f);
		FileUtils.copyFile(f, new File(System.getProperty("user.dir") + "//screenshot//actual1.jpg"));

		// compare the images
		// create the object of IageDiffer class
		ImageDiffer differ = new ImageDiffer();
		ImageDiff diff = differ.makeDiff(expectedImage, actualImage);
		if (diff.hasDiff()) {
			System.out.println("images are different");
			logger.info("images are different");

		} else {
			System.out.println("images are same");
			logger.info("images are same");

		}

	}

	@Test(enabled = false)
	public void ExplicitWaitDemo() {
		// explicit wait tell webdriver to wait somw amount of time when given
		// conditions are met

		WebElement element = driver.findElement(By.id("populate-text"));
		element.click();
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
		// Alert g=wait.until(ExpectedConditions.alertIsPresent());
		Boolean g = wait.until(
				ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("h2")), "Selenium Webdriver"));
		/*
		 * String alertText=driver.switchTo().alert().getText();
		 * System.out.println(alertText);
		 */

		System.out.println(driver.findElement(By.id("h2")).getText());

	}

	@Test(enabled = false)
	public void FluentWaitDemo() {
		WebElement thirdElement = driver.findElement(By.id("display-other-button"));
		thirdElement.click();
		// define fluent wait
		System.out.println(new Date());
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(java.time.Duration.ofSeconds(15))
				.pollingEvery(java.time.Duration.ofSeconds(5)).ignoring(Exception.class);
		WebElement t1 = wait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver t) {
				return t.findElement(By.xpath("//button[text()='Button']"));
			}

		});
		System.out.println(new Date());

		System.out.println(t1.isEnabled());

	}

	@Test(enabled = false)
	public void WaitDemo1() {
		WebElement thirdElement = driver.findElement(By.id("enable-button"));
		thirdElement.click();
		// define fluent wait
		System.out.println("started time is :" + new Date().getTime());
		long i1 = new Date().getTime();
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
		WebElement t1 = wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[text()='Button']"))));
		System.out.println(driver.findElement(By.xpath("//button[text()='Button']")).getText());
		System.out.println("ending time is :" + new Date().getTime());
		long i2 = new Date().getTime();
		System.out.println("total time taken is :" + (i2 - i1));

		System.out.println(t1.isEnabled());

	}

	@Test(enabled = false)
	public void readDataFromExcel() throws IOException {
		FileInputStream fi = new FileInputStream("C:\\Users\\AKILA RAJESH\\OneDrive\\Desktop\\DataProvider.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(fi);
		XSSFSheet sheet = workBook.getSheet("OptionsInManager1");
		int lastRowno = sheet.getLastRowNum();
		int lastColumnno = sheet.getRow(0).getLastCellNum();
		for (int row = 0; row < lastRowno; row++) {
			XSSFRow currentRow = sheet.getRow(row);
			for (int column = 0; column < lastColumnno; column++) {
				XSSFCell currentCell = currentRow.getCell(column);
				switch (currentCell.getCellType()) {
				case STRING:
					System.out.println(currentCell.getStringCellValue());
					break;
				case NUMERIC:
					System.out.println(currentCell.getNumericCellValue());
					break;
				case BOOLEAN:
					System.out.println(currentCell.getBooleanCellValue());
					break;
				case FORMULA:
					System.out.println(currentCell.getCellFormula());
					break;

				}
			}
		}

	}

	@Test(enabled = false)
	public void writeToExcel() throws IOException {
		Object data[][] = { { "RAJ", "AKI" }, { "NISHAVU", "THRISHIV" }, { 10, 20 } };
		String path = "C:\\Users\\AKILA RAJESH\\OneDrive\\Desktop\\DataProvider2.xlsx";

		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet("Raju Baiii");
		for (int row = 0; row < data.length; row++) {
			XSSFRow currentRow = sheet.createRow(row);
			for (int column = 0; column < data.length - 1; column++) {
				XSSFCell currentCell = currentRow.createCell(column);
				Object value = data[row][column];
				if (value instanceof String)
					currentCell.setCellValue((String) value);
				if (value instanceof Integer)
					currentCell.setCellValue((Integer) value);

			}

		}
		FileOutputStream fo = new FileOutputStream(path);
		workBook.write(fo);
		workBook.close();
		fo.close();
		System.out.println("done success");
	}

	/*
	 * Locators demo
	 */

	@Test(enabled = false)
	public void Locators() {
		// by css selectors
		// Action2.getWebElement("//img[@class='lnXdpd']","xpath");
		/*
		 * by using css locators to find an element
		 */
		// tag with id
		// tag with class
		// Action2.getWebElement("img.lnXdpd[class='lnXdpd']","css");
		/*
		 * by css using substring
		 */
		// prefix ---starts with tag[attribute^='value']
		// Action2.getWebElement("img[class^='lnX']","css");
		// suffix
		// contains
		// Action2.getWebElement("img[class*='nXdp']","css");
		// Action2.getWebElement("//a[contains(@class,'gb_H')]","xpath");
		// Action2.getWebElement("//a[contains(text(),'Gmail')]","xpath");
		// Action2.getWebElement("//a[starts-with(text(),'Gma')]","xpath");
		// Action2.getWebElement("//a[starts-with(@class,'gb')]","xpath");
		// Action2.getWebElement("//div[@id='uploader']/descendant::span","xpath");

		System.out.println("done");
	}

	@Test(enabled = false)
	public void alert() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('yes element find');");
	}

	@Test(enabled = false)
	public void fileUpload() {
		File file = new File("C:\\Users\\AKILA RAJESH\\Downloads\\actor.png");
		Action2.getWebElement("//input[@type='file']", "xpath").sendKeys(file.getAbsolutePath());
		System.out.println("done uploaded");

	}

	@Test(enabled = false)
	public void fileUploadSikuli() throws FindFailed, InterruptedException {
		WebElement t = driver.findElement(By.xpath("//input[@type='file']"));
		t.click();
		Thread.sleep(java.time.Duration.ofSeconds(5000));
		String inputTabImage = "C:\\Users\\AKILA RAJESH\\Downloads\\sikuli\\textbox.png";
		String openImage = "C:\\Users\\AKILA RAJESH\\Downloads\\sikuli\\open.png";
		Pattern inputBox = new Pattern(inputTabImage);
		Pattern openButton = new Pattern(openImage);
		org.sikuli.script.Screen s = new org.sikuli.script.Screen();
		s.wait(inputBox, 20);
		s.type(inputBox, "C:\\Users\\AKILA RAJESH\\Downloads\\sikuli\\actor.png");
		s.click(openButton);
	}

	@Test(enabled = false)
	public void fileDownload() {
		driver.findElement(By.xpath("//a[text()='Download']")).click();
		driver.findElement(By.id("textbox")).sendKeys("rajesh");
		driver.findElement(By.id("createTxt")).click();
		driver.findElement(By.id("pdfbox")).sendKeys("hai raj");
		driver.findElement(By.id("createPdf")).click();
	}

	@Test(enabled = true)
	public void fileDownloadUsingRobot() throws AWTException {
		WebElement upload = driver.findElement(By.id("gform_browse_button_1_5"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.srollByVisibilityOfElement", upload);
		upload.click();

		String path = "C:\\Users\\AKILA RAJESH\\Downloads\\Resume.docx";
		Robot r = new Robot();
		StringSelection s = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);

		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		System.out.println("uploaded by robot ");

	}

}
