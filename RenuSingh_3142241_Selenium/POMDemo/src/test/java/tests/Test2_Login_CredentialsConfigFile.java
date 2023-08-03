	package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import utility.PropertyFileReader;

public class Test2_Login_CredentialsConfigFile extends BaseTest {
	LoginPage login;

	@BeforeClass
	public void setup() {
		openBrowser();
		navigateToHomePage();
		login = new LoginPage(driver);
		System.out.println("Page shown : " + driver.getTitle());
	}

	@Test(priority = 1)
	public void login() {
		// Hard-coded Login Credentials:
		//login.login("renu.singh@nagarro.com", "Password123");
		
		// Reading the Login Credentials from the Config.properties File
		PropertyFileReader pfr = new PropertyFileReader();
		String emailId =  pfr.getData("username");
		String pwd =  pfr.getData("password");
		String configWait = pfr.getData("globalWaitTime");
		
		// Entering the Email Id and Password values
		System.out.println("Entering the Email Id and Password values from the Config.properties File.");
		login.login(emailId, pwd);
		Assert.assertEquals(driver.getTitle(), "Home Page");
		System.out.println("Login successful");
	}

	@Test(priority = 2)
	public void verifySignOut() throws IOException { 
		login.validateSignOut();
	}

	@Test(priority = 3)
	public void tearDown() {
		System.out.println("Closing the browser of LUMA application...");
		quit();
		System.out.println("Browser closed successfully.");
	}
}
