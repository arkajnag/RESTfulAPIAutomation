package com.qa.RESTfulAPI.TestUtil;

import org.testng.ITestResult;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestUtil {
	
	public static ExtentReports report;
	public static ExtentTest logger;
	
	public static void setUpExtentReport()
	{
		report=new ExtentReports("C:\\Users\\arkaj\\workspace\\RestfulAPIAutomation\\test-output\\ExtentReport.html", true);
		report.addSystemInfo("User Name", "Arkajyoti Nag");
		report.addSystemInfo("Execution Suite", "API Testing Suite");
	}
	
	public static void tearDownExtentReport(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
			{
				logger.log(LogStatus.FAIL, "Failed Test Case:"+result.getName());
				logger.log(LogStatus.FAIL, "Exception:"+result.getThrowable());
			}
		else 	if(result.getStatus()==ITestResult.SUCCESS)
			{
				logger.log(LogStatus.PASS, "Passed Test Case:"+result.getName());
			}
	}

}
