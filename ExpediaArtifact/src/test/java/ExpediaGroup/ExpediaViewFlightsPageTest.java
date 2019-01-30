package ExpediaGroup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class ExpediaViewFlightsPageTest {

	private WebDriver _driver;

	@BeforeTest
	public void ConfigureWebDriver() {
		System.setProperty(GeneralConstants.DriverConstant, GeneralConstants.DriverLocation);
		_driver = new ChromeDriver();
		_driver.get(GeneralConstants.WebURLLocation);
		_driver.manage().window().maximize();
		_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void CheckValidData() throws InterruptedException {
		_driver.findElement(By.xpath(XPathConstants.FlightsTabX)).click();
		Thread.sleep(1000);

		_driver.findElement(By.xpath(XPathConstants.FlyingFromInputValX)).sendKeys("SLC");
		Thread.sleep(1000);
		_driver.findElement(By.xpath(XPathConstants.FlyingFromInputValX)).sendKeys(Keys.TAB);

		_driver.findElement(By.xpath(XPathConstants.FlyingToInputValX)).sendKeys("JFK");
		Thread.sleep(1000);
		_driver.findElement(By.xpath(XPathConstants.FlyingToInputValX)).sendKeys(Keys.TAB);

		_driver.findElement(By.xpath(XPathConstants.DepartDateInputX)).click();
		_driver.findElement(By.xpath(XPathConstants.DepartDateSelectX)).click();

		_driver.findElement(By.xpath(XPathConstants.ReturningDateInputX)).click();
		_driver.findElement(By.xpath(XPathConstants.NextMonthX)).click();
		_driver.findElement(By.xpath(XPathConstants.ReturnDateSelectX)).click();
		_driver.findElement(By.xpath(XPathConstants.TravelersButtonX)).click();
		_driver.findElement(By.xpath(XPathConstants.AdultsButtonX)).click();
		_driver.findElement(By.xpath(XPathConstants.ChildButtonX)).click();
		_driver.findElement(By.xpath(XPathConstants.InfantsButtonX)).click();

		Select dropDownAgeChild = new Select(_driver.findElement(By.xpath(XPathConstants.SelectChildAgeDrpDownX)));
		dropDownAgeChild.selectByIndex(10);

		Select dropDownAgeInfant = new Select(_driver.findElement(By.xpath(XPathConstants.SelectInfantAgeDrpDownX)));
		dropDownAgeInfant.selectByIndex(1);

		Thread.sleep(1000);
		_driver.findElement(By.xpath(XPathConstants.SubmitButtonX)).click();

		String currentURL = _driver.getCurrentUrl();
		Assert.assertNotSame(currentURL, GeneralConstants.WebURLLocation);
	}

	@AfterTest
	public void CloseBrowser() {
		_driver.quit();
	}
}
