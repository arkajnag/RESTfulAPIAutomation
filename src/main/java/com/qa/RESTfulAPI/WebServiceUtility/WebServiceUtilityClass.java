package com.qa.RESTfulAPI.WebServiceUtility;

import java.io.File;
import java.util.Map;
import com.qa.RESTfulAPI.TestBase.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WebServiceUtilityClass extends TestBase{

	Response httpResponse;
	
	public Response getRESTful_API_Request(String URI)
	{
		RequestSpecification reqSpec=RestAssured.given();
		try{
				httpResponse=reqSpec.request(Method.GET,URI);
		}catch(Exception e)
		{
			System.out.println("Exception:"+e);
		}
		return httpResponse;
	}
	
	public Response postRESTful_API_Request(String URI,Map<String,String> headers, String jsonPayload)
	{
		RequestSpecification reqSpec=RestAssured.given();
		reqSpec.headers(headers);
		reqSpec.body(jsonPayload);
			try{
					httpResponse=reqSpec.request(Method.POST,URI);
		}catch(Exception e)
		{
			System.out.println("Exception:"+e);
		}
		return httpResponse;
	}
	
	public Response postRESTful_API_Request(String URI, Map<String,String> headers, File jsonPayloadFile)
	{
		RequestSpecification reqSpec=RestAssured.given();
		reqSpec.headers(headers);
		reqSpec.body(jsonPayloadFile);
			try{
					httpResponse=reqSpec.request(Method.POST, URI);
			}catch(Exception e)
			{
				System.out.println("Exception:"+e);
		}
		return httpResponse;
	}
	
	public Response putRESTful_API_Request(String URI,Map<String,String> headers, String jsonPayload)
	{
		RequestSpecification reqSpec=RestAssured.given();
		reqSpec.headers(headers);
		reqSpec.body(jsonPayload);
			try{
					httpResponse=reqSpec.request(Method.PUT,URI);
			}catch(Exception e)
			{
				System.out.println("Exception:"+e);
		}
		return httpResponse;
	}
	
	public Response deleteRESTful_API_Request(String URI)
	{
		RequestSpecification reqSpec=RestAssured.given();
			try{
					httpResponse=reqSpec.request(Method.DELETE,URI);
		}catch(Exception e)
			{
				System.out.println("Exception:"+e);
		}
		return httpResponse;
	}

	public Response getRESTful_API_RequestWithBasicAuthentication(String URI,String username,String password)
	{
		RequestSpecification reqSpec=RestAssured.given();
		reqSpec.auth().basic(username, password);
			try{
					httpResponse=reqSpec.request(Method.GET, URI);
		}catch(Exception e)
		{
			System.out.println("Exception:"+e);
		}
		return httpResponse;
	}
}
