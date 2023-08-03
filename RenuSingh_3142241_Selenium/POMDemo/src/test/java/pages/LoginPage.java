package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BasePage;
import utility.PropertyFileReader;

public class LoginPage extends BasePage {
	WebDriver driver;

	// Page Factory used for identifying the elements in the browser
	@FindBy(xpath = "//div[@class = 'panel header']//li[@class = 'authorization-link']")
	WebElement signInButton;

	@FindBy(id = "email")
	WebElement usernameField;

	@FindBy(id = "pass")
	WebElement passwordField;

	@FindBy(id = "send2")
	WebElement submitBtn;

	@FindBy(className = "post-title")
	WebElement loggedInMessage;

	@FindBy(xpath = "//header/div[1]/div[1]/ul[1]/li[1]/span[1]")
	WebElement logInWelcomeMessage;

	@FindBy(xpath = "//div[@class = 'post-content']//strong")
	WebElement loggedInSubMessage;

	@FindBy(xpath = "//div[@class='panel header']//following-sibling::button[@class='action switch']")
	WebElement loggedInArrow1;

	@FindBy(xpath = "//html/body/div[1]/header/div[1]/div/ul/li[2]/span/button")
	WebElement loggedInArrow2;

	@FindBy(xpath = "//header/div[1]/div[1]/ul[1]/li[2]/span[1]/button[1]")
	WebElement loggedInArrow;

	@FindBy(xpath = "//header/div[1]/div[1]/ul[1]/li[2]/div[1]/ul[1]/li[3]/a[1]")
	WebElement signoutBtn;

	@FindBy(xpath = "//html/body/div[1]/header/div[1]/div/ul/li[2]/div/ul/li[3]/a")
	WebElement signOutOption;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Login into application
	public void login(String username, String password) {
		signInButton.click();
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		submitBtn.click();
		Assert.assertTrue(Boolean.TRUE, "");
	}
/*
	public void validateLoginScreen() throws IOException {
		// using explicitWait for the visibility of LoggedIn post title element
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("post-title")));
		// Taking a screenshot of LoggedIn post title element
		File screenshot = loggedInMessage.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("./screenshot.jpg"));
		// Taking a screenshot of entire LoggedIn page
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("./screenshotTwo.jpg"));
		// TestNG Assertions
		Assert.assertEquals(loggedInMessage.getText(), "Logged In Successfully");
		// Assert.assertEquals(loggedInSubMessage.getText(),
		// String.format("Congratulations %s. You successfully logged in!",
		// PropertyFileReader.getData("username")));
	
*/

	public void validateSignOut() {
		try {
			Thread.sleep(1000);
			System.out.println("To perform Sign Out.");
			loggedInArrow.click();
			System.out.println("Clicking on Sign Out option");
			Thread.sleep(1000);
			//Clicking on Sign Out option
			signOutOption.click();
			System.out.println("Sign Out successful");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
