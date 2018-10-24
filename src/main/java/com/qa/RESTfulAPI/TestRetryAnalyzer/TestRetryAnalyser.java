package com.qa.RESTfulAPI.TestRetryAnalyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetryAnalyser implements IRetryAnalyzer{

	int initialCount=0;
	int finalCount=2;
	public boolean retry(ITestResult result) {
		if(initialCount<finalCount)
			{
				initialCount++;
				return true;
			}
		return false;
	}
}
