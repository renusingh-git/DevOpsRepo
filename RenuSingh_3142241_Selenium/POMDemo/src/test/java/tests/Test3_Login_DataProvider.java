package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.LoginPage;


@Listeners(utilities.ExtentReport.class)
public class Test3_Login_DataProvider extends BaseTest {
	LoginPage login;

	@BeforeClass
	public void setup() {
		openBrowser();
		navigateToHomePage();
		login = new LoginPage(driver);
		System.out.println("Page shown before login: " + driver.getTitle());
	}

	public static class ExcelDataProvider {
		@DataProvider(name = "testdata")
		public Object[][] testData() throws IOException {
			// File file = new File("DataProviderInput.xlsx");
			File file = new File("src\\test\\java\\TestData\\DataProviderInput.xlsx");

			System.out.println("Opening the DataProviderInput excel file.");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			System.out.println("Reading the Email ID and Password from the excel file in Sheet 1.");
			XSSFSheet sheet = workbook.getSheet("Sheet1");

			// Getting the count of Rows and Columns in the excel file in Sheet 1.
			int totalRows = sheet.getLastRowNum();
			int totalColums = sheet.getRow(0).getPhysicalNumberOfCells();

			// Read data from excel and store the same in the Object Array.
			Object data[][] = new Object[totalRows][totalColums];
			for (int i = 0; i < totalRows; i++) {
				data[i][0] = sheet.getRow(i + 1).getCell(0).toString();
				data[i][1] = sheet.getRow(i + 1).getCell(1).toString();
			}

			// Closing the excel workbook
			workbook.close();
			fis.close();

			return data;
		}
	}

	@Test(dataProvider = "testdata", dataProviderClass = ExcelDataProvider.class, priority = 1)
	public void testLogin(String username, String password) {
		// Entering the user credentials from the excel sheet using Data Provider
		System.out.println("Entering the user credentials");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login(username, password);
		// assert that login was successful
		Assert.assertEquals(driver.getTitle(), "Home Page");
		System.out.println("Login successful");
	}

	@Test(priority = 2)
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
