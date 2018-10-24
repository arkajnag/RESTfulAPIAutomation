package com.qa.RESTfulAPI.TestCases;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.qa.RESTfulAPI.TestBase.TestBase;
import com.qa.RESTfulAPI.TestUtil.TestUtil;
import com.qa.RESTfulAPI.WebServiceUtility.WebServiceUtilityClass;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class DeleteRESTful_WebServices_Test extends TestBase {

	WebServiceUtilityClass wbService;
	Response httpResponse;
	String BaseURI;
	SoftAssert sa;
	
	@BeforeTest
	public void setUpExtentReport()
	{
		TestUtil.setUpExtentReport();
	}
	
	@BeforeMethod
	public void setUpTestCase()
	{
		wbService=new WebServiceUtilityClass();
		BaseURI=prop.getProperty("hostURL")+prop.getProperty("deleteServiceURL");
		sa=new SoftAssert();
	}
	
	@Test(priority=1)
	public void deleteRESTful_WebService_TC01()
	{
		TestUtil.logger=TestUtil.report.startTest("deleteRESTful_WebService_TC01");
		try{
				httpResponse=wbService.deleteRESTful_API_Request(BaseURI);
				System.out.println("Response Status Code:"+httpResponse.getStatusCode());
				if(httpResponse.getStatusCode()==RESPONSE_SUCCESS)
					{
						Headers allHeaders=httpResponse.getHeaders();
						Map<String,String> headerMap=new HashMap<String,String>();
						for(Header head:allHeaders)
							{
								headerMap.put(head.getName(), head.getValue());
							}
						System.out.println("********************* Headers Returned in Response **************************");
						for(Map.Entry<String, String> entry:headerMap.entrySet())
							{
								System.out.println("Header Key::"+entry.getKey()+" || Header Value::"+entry.getValue());
							}
					}
				else if(httpResponse.getStatusCode()==RESPONSE_AUTHENTICATION_ERROR)
					{
						Assert.assertTrue(false, "Authentication Error. Please verify Username and Password passed as Token");
					}
				else if(httpResponse.getStatusCode()==RESPONSE_SERVER_ERROR)
					{
						Assert.assertTrue(false, "Please check with server team if the Server is up and in running state.");
					}
				else if(httpResponse.getStatusCode()==RESPONSE_BAD_REQUEST)
					{
						Assert.assertTrue(false, "Please verify the Request Body and Header; something doesn't seem proper in Request");
					}
				else if(httpResponse.getStatusCode()==RESPONSE_PAGE_NOT_FOUND)
					{
						Assert.assertTrue(false, "Please verify the Endpoint URL");
					}
		}catch(Exception e)
		{
			System.out.println("Exception:"+e);
		}
	}
	
	@AfterMethod
	public void tearDownTestCase(ITestResult result)
	{
		TestUtil.report.endTest(TestUtil.logger);
		TestUtil.tearDownExtentReport(result);
	}
	
	@AfterTest
	public void tearDownExtentReport()
	{
		TestUtil.report.flush();
		TestUtil.report.close();
	}
}
