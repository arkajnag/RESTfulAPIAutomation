package com.qa.RESTfulAPI.TestCases;

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
import io.restassured.response.Response;

public class GetRESTful_WebService_UsingBasicAuthentication extends TestBase{

	WebServiceUtilityClass wbservice;
	String BaseURI;
	SoftAssert sa;
	Response httpResponse;
	
	@BeforeTest
	public void setUpExtentReport()
	{
		TestUtil.setUpExtentReport();
	}
	
	@BeforeMethod
	public void setUpTestCase()
	{
		wbservice=new WebServiceUtilityClass();
		BaseURI=prop.getProperty("authenticationHostURL")+prop.getProperty("authenticationServiceURL");
		sa=new SoftAssert();
	}
	
	@Test(priority=1,description="Get Request using Valid Authentication in Rest Assured")
	public void getRESTful_Webservice_basicAuthentication_TC01()
	{
		TestUtil.logger=TestUtil.report.startTest("getRESTful_Webservice_basicAuthentication_TC01");
		try{
					httpResponse=wbservice.getRESTful_API_RequestWithBasicAuthentication(BaseURI, prop.getProperty("validusername"), prop.getProperty("validpassword"));
					System.out.println("Response Status Code:"+httpResponse.getStatusCode());
						if(httpResponse.getStatusCode()==RESPONSE_SUCCESS)
							{
								System.out.println("Response Body:"+httpResponse.asString());
							}
						else if(httpResponse.getStatusCode()==RESPONSE_AUTHENTICATION_ERROR)
							{
								System.out.println("Response Error:"+httpResponse.asString());
								Assert.assertTrue(false, "Authentication Error; Wrong Username and Password");
							} 
						else if(httpResponse.getStatusCode()==RESPONSE_SERVER_ERROR)
							{
								System.out.println("Response Error:"+httpResponse.asString());
								Assert.assertTrue(false, "Server is not running");
							} 
						else if(httpResponse.getStatusCode()==RESPONSE_BAD_REQUEST)
							{
								System.out.println("Response Error:"+httpResponse.asString());
								Assert.assertTrue(false, "Please review the Request");
							} 
						else if(httpResponse.getStatusCode()==RESPONSE_PAGE_NOT_FOUND)
							{
								System.out.println("Response Error:"+httpResponse.asString());
								Assert.assertTrue(false, "Please review the endpoint");
							} 
			}catch(Exception e)
			{
				System.out.println("Exception:"+e);
		}
	}
	
	@Test(priority=2, description="Get Request using Invalid Authentication in Rest Assured")
	public void getRESTful_Webservice_basicAuthentication_TC02()
	{
		TestUtil.logger=TestUtil.report.startTest("getRESTful_Webservice_basicAuthentication_TC02");
		try{
					httpResponse=wbservice.getRESTful_API_RequestWithBasicAuthentication(BaseURI, prop.getProperty("invalidusername"), prop.getProperty("invalidpassword"));
					System.out.println("Response Status Code:"+httpResponse.getStatusCode());
						if(httpResponse.getStatusCode()==RESPONSE_SUCCESS)
							{
								System.out.println("Response Body:"+httpResponse.asString());
							}
						else if(httpResponse.getStatusCode()==RESPONSE_AUTHENTICATION_ERROR)
							{
								System.out.println("Response Error:"+httpResponse.asString());
								Assert.assertTrue(false, "Authentication Error; Wrong Username and Password");
							} 
						else if(httpResponse.getStatusCode()==RESPONSE_SERVER_ERROR)
							{
								System.out.println("Response Error:"+httpResponse.asString());
								Assert.assertTrue(false, "Server is not running");
							} 
						else if(httpResponse.getStatusCode()==RESPONSE_BAD_REQUEST)
							{
								System.out.println("Response Error:"+httpResponse.asString());
								Assert.assertTrue(false, "Please review the Request");
							} 
						else if(httpResponse.getStatusCode()==RESPONSE_PAGE_NOT_FOUND)
							{
								System.out.println("Response Error:"+httpResponse.asString());
								Assert.assertTrue(false, "Please review the endpoint");
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
