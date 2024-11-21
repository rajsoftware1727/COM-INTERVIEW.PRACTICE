package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.selenium.concepts.SeleniumPractice;
import org.apache.log4j.PropertyConfigurator;

public class TestListener implements ITestListener {

	ExtentReports extentReport;
	ExtentSparkReporter sparkReport;
	ExtentTest extentTest;
	String screenShotPath;
	String reportName;
	public static Logger logger;

	@Override
	public void onStart(ITestContext context) {

		System.out.println("Test on started");
		logger = Logger.getLogger(context.getName());
		PropertyConfigurator.configure(System.getProperty("user.dir") + "//Log4j.properties");
		// extent report creation
		String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date());
		reportName = "TestReport-" + timeStamp + ".html";
		extentReport = new ExtentReports();
		sparkReport = new ExtentSparkReporter(System.getProperty("user.dir") + "//test-output//" + reportName);
		extentReport.attachReporter(sparkReport);

	}

	@Override
	public void onTestStart(ITestResult result) {

		System.out.println("test on started");

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		System.out.println("test success");

		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));

		// if test passes i want to take screen shot

		screenShotPath = SeleniumPractice.getScreenShot();
		extentTest.addScreenCaptureFromBase64String(screenShotPath);

	}

	@Override
	public void onTestFailure(ITestResult result) {

		System.out.println("test failed");
		extentTest = extentReport.createTest(result.getName());

		extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		// if test fails i want to take screen shot

		screenShotPath = SeleniumPractice.getScreenShot();
		extentTest.addScreenCaptureFromBase64String(screenShotPath);

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		System.out.println("test skipped");

	}

	@Override
	public void onFinish(ITestContext context) {

		System.out.println("test on finished");
		extentReport.flush();
		try {
			Desktop.getDesktop()
					.browse(new File(System.getProperty("user.dir") + "//test-output//" + reportName).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
