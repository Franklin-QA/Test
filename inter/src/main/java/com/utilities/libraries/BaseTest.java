package com.utilities.libraries;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;


public class BaseTest {

	//Object declaration for Support Libraries
	 SeleniumFunctions seleniumObj = new SeleniumFunctions();
	    
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		
	}  
	 
	 @BeforeSuite
	 public static void beforeSuite() throws IOException {
		 System.out.println(System.getProperty("user.dir"));
 	}
	 
  @BeforeTest
  public void browserSetUp() throws IOException, InterruptedException
  {
	  SeleniumFunctions.launchBrowser();
  }
  
  //Methods to run test after every suite
  @AfterTest
  public void afterTest() {
	  seleniumObj.quitBrowser();
	  
  }
}
