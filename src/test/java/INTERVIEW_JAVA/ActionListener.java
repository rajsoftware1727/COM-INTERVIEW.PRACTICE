package INTERVIEW_JAVA;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ActionListener implements ITestListener{

	ExtentReports extentReports;

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("on test started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("on test success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("on test failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("on test skipped");
	}




	@Override
	public void onStart(ITestContext context) {
		System.out.println("on start");

		String timeStamp=new SimpleDateFormat("dd-MM-yyyy.HH.mm.ss").format(new Date());
		String report="Test_Report-"+timeStamp+".html";
		extentReports=new ExtentReports();
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"//test-output//Current-Results//"+report);
		extentReports.attachReporter(sparkReporter);
		ExtentTest extentTest=extentReports.createTest("Test1");
		extentTest.pass("test passed");



	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("on finished");
		extentReports.flush();
		try {
			Desktop.getDesktop().browse(new File(System.getProperty("user.dir")+"test-output//index.html").toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}





}
