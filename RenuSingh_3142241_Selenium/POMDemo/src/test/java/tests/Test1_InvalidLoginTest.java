package tests;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Log;

import pages.LoginPage;
import utility.PropertyFileReader;

public class Test1_InvalidLoginTest extends BaseTest{
	LoginPage login;
	
	@BeforeMethod
	public void setup() {
		openBrowser();
		navigateToHomePage();			
		login = new LoginPage(driver);
	}
	
	@Test
	public void invalidLogin() {
		// Hard coded Invalid Login Credentials
		//login.login("r.s@nagarro.com", "Password123");
		
		//Reading Invalid Login Credentials from Config file
		PropertyFileReader pfr = new PropertyFileReader();
		String invalidEmailId =  pfr.getData("invalidUsername");
		String pwd =  pfr.getData("password");
		String configWait = pfr.getData("globalWaitTime");
		
		// Entering the Invalid Email Id and Password values
		System.out.println("Page shown : " + driver.getTitle());
		login.login(invalidEmailId, pwd);
		
		System.out.println("Entering Invalid Email Id and Password value");
		System.out.println("Page shown : " + driver.getTitle());
		
		//Verifying that login is failed for Invalid credentials
		Assert.assertEquals(driver.getTitle(), "Home page");
		System.out.println("Login Failed !!! Customer Login page is still shown.");
		
		//test.log(LogStatus.INFO, "Page title is matched.");
		//test.log(LogStatus.FAIL, "Page title is not matched.");
		
	}
	
	@AfterMethod
	public void tearDown() {
		System.out.println("Closing the browser of LUMA application...");
		quit();
		System.out.println("Browser closed successfully.");
	}
}
