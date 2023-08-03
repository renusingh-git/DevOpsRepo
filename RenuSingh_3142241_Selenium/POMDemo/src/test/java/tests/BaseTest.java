package tests;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.github.dockerjava.transport.DockerHttpClient.Request.Method;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.CopyTestOutputFolder;
//import utilities.ExtentManager;
import utilities.ScreenShots;
import utility.PropertyFileReader;

public class BaseTest {
	public WebDriver driver;
	static PropertyFileReader readingPropertiesFile = new PropertyFileReader();
	public static ExtentReports extent;
	public static ExtentTest test;

	public WebDriver getDriver() {
		return driver;
	}

	public void openBrowser() {
		String browser = readingPropertiesFile.getData("browser");

		// Reading and opening the Browser type mentioned in Config file.
		if (browser.equalsIgnoreCase("Chrome")) {
			// Initializing driver as Chrome driver
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("Chrome Browser initialized.");
		} else if (browser.equalsIgnoreCase("Firefox")) {
			// Initializing driver as Firefox driver
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("Firefox Browser initialized.");
		} else if (browser.equalsIgnoreCase("IE")) {
			// Initializing driver as InternetExplorer driver
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			System.out.println("Internet Explorer Browser initialized.");
		} else if (browser.equalsIgnoreCase("Edge")) {
			// Initializing driver as IE Edge driver
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("IE Edge Browser initialized.");
		} else {
			System.out.println("Browser not supplied/supported.");
			System.out.println("Please provide Browser option in config.properties as : Chrome/Firefox/IE/Edge" + "\n");
		}

		// Opening the browser in fullscreen mode
		driver.manage().window().maximize();
	}

	public void navigateToHomePage() {
		System.out.println("Opening the LUMA application in the browser.");
		PropertyFileReader pfr = new PropertyFileReader();
		driver.get(pfr.getData("url"));
	}

	public void quit() {
		driver.quit();
	}

	@AfterSuite
	public static void copyTestExecution_In_Folder() throws IOException {
		// Performing TestOutput Backup;
		System.out.println("\n" + "Performing BackUp of the Test Execution folder 'test-output' :-");
		CopyTestOutputFolder.perform_TestOutput_Backup();
	}
}
