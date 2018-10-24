package com.qa.RESTfulAPI.TestBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	protected static Properties prop;
	protected static int RESPONSE_SUCCESS=200;
	protected static int RESPONSE_CREATED=201;
	protected static int RESPONSE_NO_CONTENT=204;
	protected static int RESPONSE_BAD_REQUEST=400;
	protected static int RESPONSE_AUTHENTICATION_ERROR=401;
	protected static int RESPONSE_PAGE_NOT_FOUND=404;
	protected static int RESPONSE_SERVER_ERROR=500;
	
	static
	{
		try{
				prop=new Properties();
				FileInputStream fis=new FileInputStream("C:\\Users\\arkaj\\workspace\\RestfulAPIAutomation\\src\\main\\java\\com\\qa\\RESTfulAPI\\config\\config.properties");
				prop.load(fis);
		}catch(IOException ex)
		{
			System.out.println("Exception:"+ex);
		}
	}
}
