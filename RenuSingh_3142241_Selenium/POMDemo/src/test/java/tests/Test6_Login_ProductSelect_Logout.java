package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import utility.PropertyFileReader;

public class Test6_Login_ProductSelect_Logout extends BaseTest {
	LoginPage login;
	HomePage womenCategory;
	HomePage menCategory;

	@BeforeClass
	public void setup() {
		openBrowser();
		navigateToHomePage();
		login = new LoginPage(driver);
		womenCategory = new HomePage(driver);
		menCategory = new HomePage(driver);
	}

	@Test(priority = 1)
	public void login() {
		// Reading the Login Credentials from the Config.properties File
		PropertyFileReader pfr = new PropertyFileReader();
		String emailId = pfr.getData("username");
		String pwd = pfr.getData("password");
		// Entering the Email Id and Password values
		login.login(emailId, pwd);
		System.out.println("Entering the Email Id and Password values from the Config.properties File.");
		Assert.assertEquals(driver.getTitle(), "Home Page");
		System.out.println("Login successful");
	}

	@Test(priority = 2)
	public void womenTopSelect() throws InterruptedException {
		womenCategory.womenProductSelect();
	}
	
	@Test(priority = 3)
	public void menBottomSelect() throws InterruptedException {
		menCategory.menProductSelect();
	}
	
	@Test(priority = 4)
	public void AddRemoveWishListItem() throws InterruptedException {
		womenCategory.womenWishListTop();
		womenCategory.removeWishListTop();
	}

	@Test(priority = 5)
	public void verifySignOut() throws IOException {
		login.validateSignOut();
	}
	

	@Test(priority = 6)
	public void tearDown() {
		System.out.println("Closing the browser of LUMA application...");
		quit();
		System.out.println("Browser closed successfully.");
	}
}
