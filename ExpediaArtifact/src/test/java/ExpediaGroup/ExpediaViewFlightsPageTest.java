package ExpediaGroup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class ExpediaViewFlightsPageTest {

	private WebDriver _driver;
	private String _departingDateBeforeSearch;
	private String _returningDateBeforeSearch;

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

		_driver.findElement(By.xpath(XPathConstants.FlyingFromInputValX)).sendKeys(ExpectedInputConstants.FlightFrom);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(XPathConstants.FlyingFromInputValX)).sendKeys(Keys.TAB);

		_driver.findElement(By.xpath(XPathConstants.FlyingToInputValX)).sendKeys(ExpectedInputConstants.FlightTo);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(XPathConstants.FlyingToInputValX)).sendKeys(Keys.TAB);

		_driver.findElement(By.xpath(XPathConstants.DepartDateInputX)).click();
		WebElement dateDepartureSelector = _driver.findElement(By.xpath(XPathConstants.DepartDateSelectX));
		_departingDateBeforeSearch = Utils
				.GetDate(
						dateDepartureSelector.getAttribute("data-day"),
						String.valueOf(Integer.parseInt(dateDepartureSelector.getAttribute("data-month")) + 1),
						dateDepartureSelector.getAttribute("data-year")
						);
		dateDepartureSelector.click();
		
		_driver.findElement(By.xpath(XPathConstants.ReturningDateInputX)).click();
		_driver.findElement(By.xpath(XPathConstants.NextMonthX)).click();
		WebElement dateReturnSelector = _driver.findElement(By.xpath(XPathConstants.ReturnDateSelectX));
		_returningDateBeforeSearch = Utils
				.GetDate(
						dateReturnSelector.getAttribute("data-day"),
						String.valueOf(Integer.parseInt(dateReturnSelector.getAttribute("data-month")) + 1),
						dateReturnSelector.getAttribute("data-year")
						);
		dateReturnSelector.click();
		
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

	@Test(priority = 2, dependsOnMethods = {"CheckValidData"})
	public void ValidDataTransferToSearchPage() throws InterruptedException {
		String sourceString = _driver.findElement(By.xpath(XPathConstants.DepartureAirport)).getAttribute("value");
		String destinationString = _driver.findElement(By.xpath(XPathConstants.ArrivalAirport)).getAttribute("value");
		String departureDateString = _driver.findElement(By.xpath(XPathConstants.DepartureDateAfter)).getAttribute("value");
		String returningDateString = _driver.findElement(By.xpath(XPathConstants.ReturningDateAfter)).getAttribute("value");
		
		Assert.assertTrue(sourceString.contains(ExpectedInputConstants.FlightFrom));
		Assert.assertTrue(destinationString.contains(ExpectedInputConstants.FlightTo));
		Assert.assertEquals(_departingDateBeforeSearch, departureDateString);
		Assert.assertEquals(_returningDateBeforeSearch, returningDateString);
	}
	
	@AfterTest
	public void CloseBrowser() {
		_driver.quit();
	}
}
