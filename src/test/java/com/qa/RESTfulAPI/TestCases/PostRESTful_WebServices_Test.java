package com.qa.RESTfulAPI.TestCases;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.RESTfulAPI.PojoClass.Address;
import com.qa.RESTfulAPI.PojoClass.Author;
import com.qa.RESTfulAPI.PojoClass.AuthorName;
import com.qa.RESTfulAPI.PojoClass.Data;
import com.qa.RESTfulAPI.PojoClass.GetterSetterUserClass;
import com.qa.RESTfulAPI.PojoClass.Title;
import com.qa.RESTfulAPI.TestBase.TestBase;
import com.qa.RESTfulAPI.TestUtil.TestUtil;
import com.qa.RESTfulAPI.WebServiceUtility.WebServiceUtilityClass;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class PostRESTful_WebServices_Test extends TestBase {
	
	WebServiceUtilityClass wbService;
	Response httpResponse;
	String BaseURI;
	SoftAssert sa;
	int i=1;
	String jsonPayload;

	@BeforeTest
	public void setUpExtentReport()
	{
		TestUtil.setUpExtentReport();
	}
	
	@BeforeMethod
	public void setUpTestCase()
	{
		wbService=new WebServiceUtilityClass();
		BaseURI=prop.getProperty("hostURL")+prop.getProperty("postServiceURL");
		sa=new SoftAssert();
	}
	
	@Test(priority=1, description="Creating JSON Request Payload via POJO class")
	public void postRESTful_WebService_TC01()
	{
		TestUtil.logger=TestUtil.report.startTest("postRESTful_WebService_TC01");
		Map<String,String> headermap=new HashMap<String,String>();
		headermap.put("Content-Type", "application/json");
		
		ObjectMapper jacksonMapper=new ObjectMapper();
		AuthorName authornameRequest=new AuthorName("Mathew", "Hayward");
		Data dataRequest=new Data(authornameRequest, "2008");
		List<Data> listDataRequest=new ArrayList<Data>();
		listDataRequest.add(dataRequest);
		Author authorRequest=new Author(listDataRequest);
		List<String> launchingCities=new ArrayList<String>();
		launchingCities.add("Mumbai");
		launchingCities.add("Bangalore");
		Address addressRequest=new Address("Kolkata", "India");
		Title titleRequest=new Title("Me Before You", "Sujata Chakraborty", addressRequest, "2016", launchingCities);
		GetterSetterUserClass userRequest=new GetterSetterUserClass("13", titleRequest, authorRequest);
			try {
						jsonPayload=jacksonMapper.writeValueAsString(userRequest);
			} catch (JsonProcessingException e) {
				System.out.println("Exception in Creating the Serializing the JSON Request Payload:"+e);
		}
			try{
						httpResponse=wbService.postRESTful_API_Request(BaseURI, headermap, jsonPayload);
						sa.assertEquals(httpResponse.getStatusCode(), RESPONSE_CREATED);
						
						if(httpResponse.getStatusCode()==RESPONSE_CREATED)
							{
								Headers headerArray=httpResponse.getHeaders();
								Map<String,String> responseHeaders=new HashMap<String,String>();
									for(Header head:headerArray)
										{
											responseHeaders.put(head.getName(), head.getValue());
										}
										for(Map.Entry<String, String> headerEntry:responseHeaders.entrySet())
											{
												System.out.println("Header Key:"+headerEntry.getKey()+" and Header Value:"+headerEntry.getValue());
											}
								
								GetterSetterUserClass userResponse=jacksonMapper.readValue(httpResponse.asString(), GetterSetterUserClass.class);
								sa.assertEquals(userResponse.getId(), userRequest.getId());
								sa.assertEquals(userResponse.getTitle().getName(), userRequest.getTitle().getName());
								sa.assertAll();
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
						
			} catch(Exception e)
			{
				System.out.println("Exception in processing the JSON Request and Response:"+e);
		}
	}
	
	@Test(priority=2,description="Creating JSON Request Payload via JSOn file")
	public void postRESTful_WebService_TC02()
	{
		TestUtil.logger=TestUtil.report.startTest("postRESTful_WebService_TC02");
		Map<String,String> headermap=new HashMap<String,String>();
		headermap.put("Content-Type", "application/json");
		File jsonPayloadFile=new File(prop.getProperty("jsonFile"));
		try
				{
					httpResponse=wbService.postRESTful_API_Request(BaseURI, headermap, jsonPayloadFile);
					sa.assertEquals(httpResponse.getStatusCode(), RESPONSE_CREATED);
					
					if(httpResponse.getStatusCode()==RESPONSE_CREATED)
						{
							Headers headerArray=httpResponse.getHeaders();
							Map<String,String> responseHeaders=new HashMap<String,String>();
								for(Header head:headerArray)
									{
										responseHeaders.put(head.getName(), head.getValue());
									}
									for(Map.Entry<String, String> headerEntry:responseHeaders.entrySet())
										{
											System.out.println("Header Key:"+headerEntry.getKey()+" and Header Value:"+headerEntry.getValue());
										}
							System.out.println("JSON Response Body:"+httpResponse.asString());
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
