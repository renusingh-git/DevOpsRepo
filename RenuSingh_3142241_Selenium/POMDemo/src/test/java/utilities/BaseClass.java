package utilities;
import java.io.File;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import tests.BaseTest;

public class BaseClass{
	public static WebDriver driver;
	public static boolean bResult=true;
	public static String errorValidation="";
	public static String snapshotFolderPath="";
	public static String failedSnapshotFolderPath="";
	public static String passedSnapshotFolderPath="";
	public static ExtentReports report;
	public static ExtentTest test;
	protected static String sTestCaseName;
	protected static int iTestCaseRow;
	
	 @BeforeSuite
	  public void beforeSuite() throws Exception {
		  //Setting log4j xml
		  DOMConfigurator.configure("log4j.xml");
		  
		  //Setting testData file
		  //report = new ExtentReports(System.getProperty("C:\\Users\\renu2143\\Desktop\\SeleniumWorkspace\\POMDemo") + "\\ExtentReportResults.html");
		  report = new ExtentReports(System.getProperty("\\POMDemo\\extent-reports") + "\\ExtentReportResults.html");
		  report.loadConfig(new File("extent-config.xml"));
	  }
	 
	  @AfterMethod
	  public void afterMehod() {
		  driver.quit();
	  }

	  @AfterSuite
	  public void aftersuite() {	
		  report.endTest(test);
	  }
}
