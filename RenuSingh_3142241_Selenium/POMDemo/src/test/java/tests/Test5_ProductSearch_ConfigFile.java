	package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.HomePage;
import utility.PropertyFileReader;

public class Test5_ProductSearch_ConfigFile extends BaseTest {
	LoginPage login;
	HomePage searchLumaItem;

	@BeforeClass
	public void setup() {
		openBrowser();
		navigateToHomePage();
		login = new LoginPage(driver);
		searchLumaItem = new HomePage(driver);
		System.out.println("Page shown : " + driver.getTitle());
	}

	@Test(priority = 1)
	public void login() {		
		// Reading the Login Credentials from the Config.properties File
		PropertyFileReader pfr = new PropertyFileReader();
		String emailId =  pfr.getData("username");
		String pwd =  pfr.getData("password");
		// Entering the Email Id and Password values
		System.out.println("Entering the Email Id and Password values from the Config.properties File.");
		login.login(emailId, pwd);
		Assert.assertEquals(driver.getTitle(), "Home Page");
		System.out.println("Login successful");
	}
	
	@Test(priority = 2)
	public void productSearch() throws IOException { 
		PropertyFileReader pfr = new PropertyFileReader();
		String searchPrd = pfr.getData("searchLumaProduct");
		searchLumaItem.searchProduct(searchPrd);
	}

	@Test(priority = 3)
	public void verifySignOut() throws IOException { 
		login.validateSignOut();
	}

	@Test(priority = 4)
	public void tearDown() {
		System.out.println("Closing the browser of LUMA application...");
		quit();
		System.out.println("Browser closed successfully.");
	}
}
