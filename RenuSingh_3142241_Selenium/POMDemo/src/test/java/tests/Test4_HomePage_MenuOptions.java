package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import utility.PropertyFileReader;

public class Test4_HomePage_MenuOptions extends BaseTest {
	LoginPage login;
	HomePage sale;
	HomePage newItems;
	HomePage womenCategory;
	HomePage menCategory;
	HomePage gearCategory;
	HomePage training;

	@BeforeClass
	public void setup() {
		openBrowser();
		navigateToHomePage();
		System.out.println("Page shown before login: " + driver.getTitle());
		login = new LoginPage(driver);
		sale = new HomePage(driver);
		newItems = new HomePage(driver);
		womenCategory = new HomePage(driver);
		menCategory = new HomePage(driver);
		gearCategory = new HomePage(driver);
		training = new HomePage(driver);
	}

	@Test(priority = 10)
	public void login() {
		// Reading the Login Credentials from the Config.properties File
		PropertyFileReader pfr = new PropertyFileReader();
		String emailId = pfr.getData("username");
		String pwd = pfr.getData("password");
		// Entering the Email Id and Password values
		System.out.println("Entering the Email Id and Password values from the Config.properties File.");
		login.login(emailId, pwd);
		Assert.assertEquals(driver.getTitle(), "Home Page");
		System.out.println("Login successful");
	}

	@Test(priority = 11)
	public void salePage() throws InterruptedException {
		sale.salePage();
	}

	@Test(priority = 12)
	public void whatsNew() throws InterruptedException {
		newItems.whatsNewPage();
	}

	@Test(priority = 13)
	public void womenCategory() throws InterruptedException {
		womenCategory.womenProducts();
	}

	@Test(priority = 14)
	public void menCategory() throws InterruptedException {
		menCategory.menProducts();
	}

	@Test(priority = 15)
	public void gearCategory() throws InterruptedException {
		gearCategory.gearProducts();
	}

	@Test(priority = 16)
	public void training() throws InterruptedException {
		training.trainingVideo();
	}

	@Test(priority = 17)
	public void verifySignOut() throws IOException {
		login.validateSignOut();
	}

	@AfterClass
	public void tearDown() {
		System.out.println("Closing the browser of LUMA application...");
		quit();
		System.out.println("Browser closed successfully.");
	}
}
