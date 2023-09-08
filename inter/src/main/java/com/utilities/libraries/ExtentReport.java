package com.utilities.libraries;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
 
/**
 * Class for extent report
 * @author automation
 *
 */
public class ExtentReport {
	
	private ExtentReport(){
	}
	  
	@SuppressWarnings("deprecation")
	/**
	 * Method to create instance for extent report
	 * @return
	 */
	public static ExtentReports Instance()
    {
		ExtentReports extent;
		String sPath = "target\\surefire-reports\\extent-report\\extent-report.html";
		extent = new ExtentReports(System.getProperty("user.dir") + File.separator + sPath, true);
		extent.config()
		           .documentTitle("Automation Report")
		           .reportName("Regression");
		
		return extent;
	}
	
	/**
	 * Method to capture screenshot
	 * @param ImagesPath
	 * @return
	 */
	public static String CaptureScreen(String ImagesPath)
	{
		TakesScreenshot oScn = (TakesScreenshot) SeleniumFunctions.driver;
		File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
		File oDest = new File(ImagesPath);
		try {
		   FileUtils.copyFile(oScnShot, oDest);
		} 
		catch (IOException e) {
			Reporter.log(""+e);
		}
		return ImagesPath;
	}
	    
}