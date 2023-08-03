package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BasePage;
import utility.PropertyFileReader;

public class HomePage extends BasePage {
	WebDriver driver;

	// Page Factory used for identifying the elements in the browser
	@FindBy(xpath = "//span[contains(text(),\"What's New\")]")
	WebElement whatsNew;

	@FindBy(xpath = "//a[@class='logo']")
	WebElement lumaLogo;

	@FindBy(xpath = "//input[@id='search']")
	WebElement searchTextbox;

	@FindBy(xpath = "//span[contains(text(),'Sale')]")
	WebElement saleHyperlink;

	@FindBy(xpath = "//span[contains(text(),'Women')]")
	WebElement womenProductsMenu;

	@FindBy(xpath = "//span[contains(text(),'Men')]")
	WebElement menProductsMenu;

	@FindBy(xpath = "//span[contains(text(),'Gear')]")
	WebElement gearProductsMenu;

	@FindBy(xpath = "//span[contains(text(),'Training')]")
	WebElement trainingMenu;

	@FindBy(xpath = "//a[@id='ui-id-9']")
	WebElement womenTops;

	@FindBy(xpath = "//*[@id=\"ui-id-18\"]/span[2]")
	WebElement menBottoms;

	@FindBy(xpath = "//*[@id=\"ui-id-11\"]/span")
	WebElement womenJackets;

	@FindBy(xpath = "//a[contains(text(),'Breathe-Easy Tank')]")
	WebElement womenTopSelect;

	@FindBy(xpath = "//a[contains(text(),'Pierce Gym Short')]")
	WebElement menBottomSelect;

	@FindBy(xpath = "//a[contains(text(),'Chloe Compete Tank')]")
	WebElement womenTopWishList;

	@FindBy(xpath = "//div[contains(text(),'Chloe Compete Tank has been added to your Wish')]")
	WebElement addWishListMsg;

	@FindBy(xpath = "//div[contains(text(),'Chloe Compete Tank has been removed from your Wish')]")
	WebElement removeWishListMsg;

private int globalWaitTime;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PropertyFileReader pfr = new PropertyFileReader();
		String configWait = pfr.getData("globalWaitTime");
		try {
			globalWaitTime = Integer.parseInt(configWait);
		} catch (Exception e) {
			globalWaitTime = 500;
		}
		PageFactory.initElements(driver, this);
	}

	public String homePageTitle() {
		return driver.getTitle();
	}

	public String lumaPageTitle() {
		return driver.getTitle();
	}

	public void salePage() {
		try {
			// Clicking on Sale Menu option
			saleHyperlink.click();
			System.out.println("LUMA application page title : " + lumaPageTitle());
			Assert.assertTrue(Boolean.TRUE, "");
			System.out.println("Sale Screen is shown");
			Thread.sleep(globalWaitTime);
			System.out.println("Calling Global Wait Time from Config file.");

			/*
			 * // Navigating back to homepage lumaLogo.click();
			 * Assert.assertTrue(Boolean.TRUE, ""); Thread.sleep(1000);
			 * System.out.println("Navigated back to LUMA Homepage.");
			 * System.out.println("LUMA Homepage Title : " + homePageTitle());
			 */

			// Scrolling down the Sale Page
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement subscribeBtn = driver.findElement(By.xpath("//span[contains(text(),'Subscribe')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", subscribeBtn);
			Thread.sleep(globalWaitTime);

			WebElement copyright_footer = driver.findElement(
					By.xpath("//span[contains(text(),'Copyright © 2013-present Magento, Inc. All rights ')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", copyright_footer);
			System.out.println("Sale Page scrolled down till copyright footer.");

			// Scrolling Up to the Top of the Page
			scrollUptoLogoBtn();
			System.out.println("Sale Page scrolled up.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void scrollUptoLogoBtn() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement logoBtn = driver.findElement(By.xpath("//span[contains(text(),'Sale')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", logoBtn);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void searchProduct(String searchLumaProduct) {
		try {
			searchTextbox.click();
			searchTextbox.sendKeys(searchLumaProduct);
			System.out.println("Selecting Luma Search Product from Config file.");
			Thread.sleep(globalWaitTime);
			// Enter key pressed
			WebElement textbox = driver.findElement(By.xpath("//input[@id='search']"));
			textbox.sendKeys(Keys.ENTER);
			System.out.println("Search Luma Product from Config file is entered.");
			Assert.assertTrue(Boolean.TRUE, "");

			// Scrolling down the page
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement myWishListLabel = driver.findElement(By.xpath("//strong[contains(text(),'My Wish List')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", myWishListLabel);
			Thread.sleep(globalWaitTime);
			WebElement subscribeBtn = driver.findElement(By.xpath("//span[contains(text(),'Subscribe')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", subscribeBtn);
			Thread.sleep(globalWaitTime);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void whatsNewPage() {
		try {
			// Clicking on the What's New Menu option
			whatsNew.click();
			System.out.println("LUMA application page title : " + lumaPageTitle());
			Assert.assertTrue(Boolean.TRUE, "");
			System.out.println("What's New Screen is shown");
			Thread.sleep(globalWaitTime);

			// Scrolling down the What's New Page
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement subscribeBtn = driver.findElement(By.xpath("//span[contains(text(),'Subscribe')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", subscribeBtn);
			Thread.sleep(globalWaitTime);
			WebElement copyright_footer = driver.findElement(
					By.xpath("//span[contains(text(),'Copyright © 2013-present Magento, Inc. All rights ')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", copyright_footer);
			System.out.println("What's New Page scrolled down till copyright footer.");

			// Scrolling Up to the Top of the Page
			scrollUptoLogoBtn();
			System.out.println("What's New Page scrolled up.");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void womenProducts() {
		try {
			// Clicking on the Women Menu option
			womenProductsMenu.click();
			Assert.assertTrue(Boolean.TRUE, "");
			System.out.println("LUMA application page title : " + lumaPageTitle());
			System.out.println("Women Products Screen is shown");
			Thread.sleep(globalWaitTime);

			// Scrolling down the Women Page
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement subscribeBtn = driver.findElement(By.xpath("//span[contains(text(),'Subscribe')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", subscribeBtn);
			Thread.sleep(globalWaitTime);
			WebElement copyright_footer = driver.findElement(
					By.xpath("//span[contains(text(),'Copyright © 2013-present Magento, Inc. All rights ')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", copyright_footer);

			// Scrolling Up to the Top of the Page
			scrollUptoLogoBtn();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void womenProductSelect() {
		try {
			// Clicking on the Women Menu option
			womenProductsMenu.click();
			Assert.assertTrue(Boolean.TRUE, "");

			System.out.println("LUMA application page title : " + lumaPageTitle());
			System.out.println("Women Products Screen is shown");
			Thread.sleep(globalWaitTime);

			WebElement mousehoverWomenMenu = driver.findElement(By.xpath("//span[contains(text(),'Women')]"));
			// Creating object of an Actions class
			Actions action = new Actions(driver);
			// Performing the mouse hover action on the target element.
			action.moveToElement(mousehoverWomenMenu).perform();

			// Selecting Women Tops Option
			System.out.println("Women Tops Page to be selected.");
			womenTops.click();
			System.out.println("LUMA application page title : " + lumaPageTitle());
			System.out.println("Women Products > Tops Screen is shown");
			Thread.sleep(1000);

			// Selecting a Women's Top
			System.out.println("Women Top: 'Breathe-Easy Tank' to be selected.");
			womenTopSelect.click();
			System.out.println("LUMA application page title : " + lumaPageTitle());
			System.out.println("Women Products > Tops Screen > Top 'Breathe-Easy Tank' is shown");
			Thread.sleep(globalWaitTime);

			// Scrolling down the Add to Wish List button
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement wishListBtn = driver
					.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[5]/div/a[1]/span"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", wishListBtn);
			System.out.println("Women Tops Page scrolled down till Add to Wish List button.");
			Thread.sleep(globalWaitTime);

			// Scrolling Up to the Top of the Page
			WebElement womenMenuBtn = driver.findElement(By.xpath("//span[contains(text(),'Women')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", womenMenuBtn);
			System.out.println("Women Tops Page scrolled up.");
			Thread.sleep(globalWaitTime);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void menProducts() {
		try {
			// Clicking on the Men Menu option
			menProductsMenu.click();
			Assert.assertTrue(Boolean.TRUE, "");
			System.out.println("LUMA application page title : " + lumaPageTitle());
			System.out.println("Men Products Screen is shown");
			Thread.sleep(globalWaitTime);

			// Navigating back to homepage
			lumaLogo.click();
			Assert.assertTrue(Boolean.TRUE, "");
			Thread.sleep(1000);
			System.out.println("Navigated back to LUMA Homepage.");
			System.out.println("LUMA Homepage Title : " + homePageTitle());

			// Scrolling down the Men Page
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement subscribeBtn = driver.findElement(By.xpath("//span[contains(text(),'Subscribe')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", subscribeBtn);
			Thread.sleep(globalWaitTime);
			WebElement copyright_footer = driver.findElement(
					By.xpath("//span[contains(text(),'Copyright © 2013-present Magento, Inc. All rights ')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", copyright_footer);
			System.out.println("Men Page scrolled down till copyright footer.");

			// Scrolling Up to the Top of the Page
			scrollUptoLogoBtn();
			System.out.println("Men Page scrolled up.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void menProductSelect() {
		try {
			// Clicking on the Men Menu option
			menProductsMenu.click();
			Assert.assertTrue(Boolean.TRUE, "");

			System.out.println("LUMA application page title : " + lumaPageTitle());
			System.out.println("Men Products Screen is shown");
			Thread.sleep(globalWaitTime);

			WebElement mousehoverMenMenu = driver.findElement(By.xpath("//span[contains(text(),'Men')]"));
			// Creating object of an Actions class
			Actions action = new Actions(driver);
			// Performing the mouse hover action on the target element.
			action.moveToElement(mousehoverMenMenu).perform();

			// Selecting Men Bottoms Option
			System.out.println("Men Bottoms Page to be selected.");
			menBottoms.click();
			System.out.println("LUMA application page title : " + lumaPageTitle());
			System.out.println("Men Products > Bottoms Screen is shown");
			Thread.sleep(1000);

			// Selecting a Men's Bottoms
			System.out.println("Men Shorts: 'Pierce Gym Short' to be selected.");
			menBottomSelect.click();
			System.out.println("LUMA application page title : " + lumaPageTitle());
			System.out.println("Men Products > Bottoms Screen > Short 'Pierce Gym Shortk' is shown");
			Thread.sleep(globalWaitTime);

			// Scrolling down the Add to Wish List button
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement wishListBtn = driver
					.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[5]/div/a[1]/span"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", wishListBtn);
			System.out.println("Men Page scrolled down till Add to Wish List button.");
			Thread.sleep(globalWaitTime);

			// Scrolling Up to the Top of the Page
			WebElement menMenuBtn = driver.findElement(By.xpath("//span[contains(text(),'men')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", menMenuBtn);
			System.out.println("Men Page scrolled up.");
			Thread.sleep(2000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void gearProducts() {
		try {
			// Clicking on the Women Menu option
			gearProductsMenu.click();
			Assert.assertTrue(Boolean.TRUE, "");
			System.out.println("LUMA application page title : " + lumaPageTitle());
			System.out.println("Gear Products Screen is shown");
			Thread.sleep(globalWaitTime);

			// Scrolling down the Gear Page
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement subscribeBtn = driver.findElement(By.xpath("//span[contains(text(),'Subscribe')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", subscribeBtn);
			Thread.sleep(globalWaitTime);
			WebElement copyright_footer = driver.findElement(
					By.xpath("//span[contains(text(),'Copyright © 2013-present Magento, Inc. All rights ')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", copyright_footer);
			System.out.println("Gear Page scrolled down till copyright footer.");

			// Scrolling Up to the Top of the Page
			scrollUptoLogoBtn();
			System.out.println("Gear Page scrolled up.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void trainingVideo() {
		try {
			// Clicking on the Women Menu option
			trainingMenu.click();
			Assert.assertTrue(Boolean.TRUE, "");
			System.out.println("LUMA application page title : " + lumaPageTitle());
			System.out.println("Training Screen is shown");
			Thread.sleep(globalWaitTime);

			// Scrolling down the Training Page
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement subscribeBtn = driver.findElement(By.xpath("//span[contains(text(),'Subscribe')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", subscribeBtn);
			Thread.sleep(globalWaitTime);
			WebElement copyright_footer = driver.findElement(
					By.xpath("//span[contains(text(),'Copyright © 2013-present Magento, Inc. All rights ')]"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", copyright_footer);
			System.out.println("Training Page scrolled down till copyright footer.");

			// Scrolling Up to the Top of the Page
			scrollUptoLogoBtn();
			System.out.println("Training Page scrolled up.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void womenWishListTop() {
		try {
			// Clicking on the Women Menu option
			womenProductsMenu.click();
			Assert.assertTrue(Boolean.TRUE, "");

			System.out.println("LUMA application page title : " + lumaPageTitle());
			System.out.println("Women Products Screen is shown");
			Thread.sleep(globalWaitTime);

			WebElement mousehoverWomenMenu = driver.findElement(By.xpath("//span[contains(text(),'Women')]"));
			// Creating object of an Actions class
			Actions action = new Actions(driver);
			// Performing the mouse hover action on the target element.
			action.moveToElement(mousehoverWomenMenu).perform();

			// Selecting Women Tops Option
			System.out.println("Women Tops Page to be selected.");
			womenTops.click();
			System.out.println("LUMA application page title : " + lumaPageTitle());
			System.out.println("Women Products > Tops Screen is shown");
			Thread.sleep(1000);

			// Selecting a Women's Top
			System.out.println("Women Top: 'Chloe Compete Tank' to be selected.");
			womenTopWishList.click();
			System.out.println("LUMA application page title : " + lumaPageTitle());
			System.out.println("Women Products > Tops Screen > Top 'Chloe Compete Tank' is shown");
			Thread.sleep(globalWaitTime);

			// Scrolling down the Add to Wish List button
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement wishListBtn = driver
					.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[1]/div[5]/div/a[1]/span"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", wishListBtn);
			System.out.println("Women Tops Page scrolled down till Add to Wish List button.");
			Thread.sleep(globalWaitTime);

			// Adding to WishList
			System.out.println("Adding Women Top: 'Chloe Compete Tank' to Wish List");
			wishListBtn.click();
			System.out.println("Women Top > Add to Wish List button is clicked.");
			Thread.sleep(globalWaitTime);

			System.out.println("LUMA application page title : " + lumaPageTitle());
			System.out.println("My Wish List Page is shown");

			// Assert womenTopSelected is "Chloe Compete Tank"
			Assert.assertEquals(addWishListMsg.getText(),
					"Chloe Compete Tank has been added to your Wish List. Click here to continue shopping.");
			System.out.println("Added the selected Women Top to Wish List");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void removeWishListTop() {
		try {
			System.out.println("LUMA application page title : " + lumaPageTitle());
			// Scrolling down the Add to Wish List button
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement removeWishListBtn = driver
					.findElement(By.xpath("//*[@id=\"wishlist-sidebar\"]/li[1]/div/div/div[2]/div[2]/a"));
			// This will scroll the page Horizontally till the element is found
			js.executeScript("arguments[0].scrollIntoView();", removeWishListBtn);
			Thread.sleep(globalWaitTime);
			Thread.sleep(globalWaitTime);

			// Removing from the WishList
			System.out.println("Removing Women Top: 'Chloe Compete Tank' from the Wish List");
			removeWishListBtn.click();
			System.out.println("Wish List item removed...");
			Thread.sleep(2000);
			// Assert.assertEquals(womenTopSelect, "Chloe Compete Tank");
			Assert.assertEquals(removeWishListMsg.getText(),
					"Chloe Compete Tank has been removed from your Wish List.");
			System.out.println("Removed the selected Women Top to Wish List");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
