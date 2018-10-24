package com.qa.RESTfulAPI.TestCases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.qa.RESTfulAPI.PojoClass.Data;
import com.qa.RESTfulAPI.PojoClass.GetterSetterUserClass;
import com.qa.RESTfulAPI.TestBase.TestBase;
import com.qa.RESTfulAPI.TestUtil.TestUtil;
import com.qa.RESTfulAPI.WebServiceUtility.WebServiceUtilityClass;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class GetRESTful_WebServices_Test extends TestBase{

	WebServiceUtilityClass wbService;
	Response httpResponse;
	String BaseURI;
	SoftAssert sa;
	int i=1;
	
	@BeforeTest
	public void setUpExtentReport()
	{
		TestUtil.setUpExtentReport();
	}
	
	@BeforeMethod
	public void setUpTestCase()
	{
		wbService=new WebServiceUtilityClass();
		BaseURI=prop.getProperty("hostURL")+prop.getProperty("getServiceURL");
		sa=new SoftAssert();
	}
	
	@Test(priority=1,description="Validation of Get Request using Jackson core databind")
	public void getRESTful_WebService_TC01()
	{
		TestUtil.logger=TestUtil.report.startTest("getRESTful_WebService_TC01");
			try{
					httpResponse=wbService.getRESTful_API_Request(BaseURI);
					sa.assertEquals(httpResponse.getStatusCode(), RESPONSE_SUCCESS);
					
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
							
							
							ObjectMapper mapper=new ObjectMapper();
							GetterSetterUserClass userResponseData=mapper.readValue(httpResponse.asString(), GetterSetterUserClass.class);
							sa.assertEquals(userResponseData.getId(), "11");
							sa.assertEquals(userResponseData.getTitle().getName(), "Bhaag Milka Bhaag");
							sa.assertEquals(userResponseData.getTitle().getEditor(), "FRK Films");
							sa.assertEquals(userResponseData.getTitle().getAddress().getCity(), "Mumbai");
							sa.assertEquals(userResponseData.getTitle().getAddress().getCountry(), "India");
							sa.assertEquals(userResponseData.getTitle().getYear(), "2014");
							List<String> launchingCity=userResponseData.getTitle().getLaunchingCity();
								for(String cities:launchingCity)
									{
										System.out.println("Sno:"+i+" City Name:"+cities);
										i++;
									}
							List<Data> datalist=userResponseData.getAuthor().getData();
								for(Data list:datalist)
									{
										System.out.println("Sno:"+i+" Author FirstName:"+list.getAuthorName().getFirstname()+" Author LastName:"+list.getAuthorName().getLastname()+" Author Debut Year:"+list.getAuthorDOY());
										i++;
									}
						}
					else 	if(httpResponse.getStatusCode()==RESPONSE_BAD_REQUEST)
						{
							System.out.println("Please verify the Request");
						}
					
					else 	if(httpResponse.getStatusCode()==RESPONSE_PAGE_NOT_FOUND)
						{
							System.out.println("Please verify the End Point");
						}
					
					else 	if(httpResponse.getStatusCode()==RESPONSE_SERVER_ERROR)
						{
							System.out.println("Please verify the End Server is up and running");
						}	
					sa.assertAll();
		}catch(Exception e)
		{
				System.out.println("Exception:"+e);
		}
	}

	@Test(priority=2, description="Validation of Get Request using Google GSON")
	public void getRESTful_WebService_TC02()
	{
		TestUtil.logger=TestUtil.report.startTest("getRESTful_WebService_TC02");
		try{
				httpResponse=wbService.getRESTful_API_Request(BaseURI);
				System.out.println("Response Status Code:"+httpResponse.getStatusCode());
				Gson gsonMapper=new Gson();
				GetterSetterUserClass userResponseObject=gsonMapper.fromJson(httpResponse.asString(), GetterSetterUserClass.class);
				System.out.println("ID in Response:"+userResponseObject.getId());
				System.out.println("Name of the Movie:"+userResponseObject.getTitle().getName());
				System.out.println("Editor of the Movie:"+userResponseObject.getTitle().getEditor());
				System.out.println("Year of the Movie:"+userResponseObject.getTitle().getYear());
				System.out.println("City of the Movie:"+userResponseObject.getTitle().getAddress().getCity());
				System.out.println("Country of the Movie:"+userResponseObject.getTitle().getAddress().getCountry());
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
