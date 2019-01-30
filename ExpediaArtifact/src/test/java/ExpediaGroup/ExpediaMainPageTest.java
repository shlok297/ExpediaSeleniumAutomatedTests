package ExpediaGroup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class ExpediaMainPageTest {
	// Sample to check the commit
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
	public void CheckTitle() {
		Assert.assertEquals(ExpectedInputConstants.ExpediaURL, _driver.getCurrentUrl());
	}

	@Test(priority = 2)
	public void ClickHotels() {
		_driver.findElement(By.xpath(XPathConstants.HotelTabX)).click();

		String goingTo = _driver.findElement(By.xpath(XPathConstants.GoingToX)).getText();
		String checkIn = _driver.findElement(By.xpath(XPathConstants.CheckInX)).getText();
		String checkOut = _driver.findElement(By.xpath(XPathConstants.CheckOutX)).getText();

		Assert.assertEquals(ExpectedInputConstants.GoingTo, goingTo);
		Assert.assertEquals(ExpectedInputConstants.CheckIn, checkIn);
		Assert.assertEquals(ExpectedInputConstants.CheckOut, checkOut);
	}

	@Test(priority = 3)
	public void ClickCars() {
		_driver.findElement(By.xpath(XPathConstants.CarsTabX)).click();

		String pickUp = _driver.findElement(By.xpath(XPathConstants.PickUpX)).getText();
		String dropOff = _driver.findElement(By.xpath(XPathConstants.DropOffX)).getText();
		String pickUpDate = _driver.findElement(By.xpath(XPathConstants.PickUpDateX)).getText();
		String dropOffDate = _driver.findElement(By.xpath(XPathConstants.DropOffDateX)).getText();

		Assert.assertEquals(ExpectedInputConstants.PickUp, pickUp);
		Assert.assertEquals(ExpectedInputConstants.DropOff, dropOff);
		Assert.assertEquals(ExpectedInputConstants.DropOffDate, dropOffDate);
		Assert.assertEquals(ExpectedInputConstants.PickUpDate, pickUpDate);
	}

	@Test(priority = 4)
	public void ClickFlights() {
		_driver.findElement(By.xpath(XPathConstants.FlightsTabX)).click();

		String flyingFrom = _driver.findElement(By.xpath(XPathConstants.FlyingFromX)).getText();
		String flyingTo = _driver.findElement(By.xpath(XPathConstants.FlyingToX)).getText();
		String departing = _driver.findElement(By.xpath(XPathConstants.DepartingX)).getText();
		String returning = _driver.findElement(By.xpath(XPathConstants.ReturningX)).getText();
		String travelers = _driver.findElement(By.xpath(XPathConstants.TravelersX)).getText();

		Assert.assertEquals(ExpectedInputConstants.FlyingFrom, flyingFrom);
		Assert.assertEquals(ExpectedInputConstants.FlyingTo, flyingTo);
		Assert.assertEquals(ExpectedInputConstants.Departing, departing);
		Assert.assertEquals(ExpectedInputConstants.Returning, returning);
		Assert.assertEquals(ExpectedInputConstants.Travelers, travelers);
	}

	@Test(priority = 5, dependsOnMethods = { "ClickFlights" })
	public void ValidateDateFields() {

		if (_driver.findElement(By.xpath(XPathConstants.CancelButtonFlyFromX)).isEnabled()) {
			_driver.findElement(By.xpath(XPathConstants.CancelButtonFlyFromX)).click();
		}

		_driver.findElement(By.xpath(XPathConstants.FlyingFromInputValX)).sendKeys("SLC");
		_driver.findElement(By.xpath(XPathConstants.FlyingFromInputValX)).sendKeys(Keys.TAB);

		if (_driver.findElement(By.xpath(XPathConstants.CancelButtonFlyToX)).isEnabled()) {
			_driver.findElement(By.xpath(XPathConstants.CancelButtonFlyToX)).click();
		}

		_driver.findElement(By.xpath(XPathConstants.FlyingToInputValX)).sendKeys("LAX");
		_driver.findElement(By.xpath(XPathConstants.FlyingToInputValX)).sendKeys(Keys.TAB);

		_driver.findElement(By.xpath(XPathConstants.SubmitButtonX)).click();
		String startDate = _driver.findElement(By.xpath(XPathConstants.ErrorMessageForStartDate)).getText();
		String returnDate = _driver.findElement(By.xpath(XPathConstants.ErrorMessafeForReturnDate)).getText();

		Assert.assertEquals(ExpectedInputConstants.ErrorStartDate, startDate);
		Assert.assertEquals(ExpectedInputConstants.ErrorReturnDate, returnDate);
	}

	@Test(priority = 6, dependsOnMethods = { "ClickFlights" })
	public void ValidateDateAndLocationFields() {

		if (_driver.findElement(By.xpath(XPathConstants.CancelButtonFlyFromX)).isEnabled()) {
			_driver.findElement(By.xpath(XPathConstants.CancelButtonFlyFromX)).click();
		}

		_driver.findElement(By.xpath(XPathConstants.FlyingFromInputValX)).sendKeys("");
		_driver.findElement(By.xpath(XPathConstants.FlyingFromInputValX)).sendKeys(Keys.TAB);

		if (_driver.findElement(By.xpath(XPathConstants.CancelButtonFlyToX)).isEnabled()) {
			_driver.findElement(By.xpath(XPathConstants.CancelButtonFlyToX)).click();
		}

		_driver.findElement(By.xpath(XPathConstants.FlyingToInputValX)).sendKeys("");
		_driver.findElement(By.xpath(XPathConstants.SubmitButtonX)).click();

		String destination = _driver.findElement(By.xpath(XPathConstants.ErrorMessageToAirport)).getText();
		String source = _driver.findElement(By.xpath(XPathConstants.ErrorMessageFromAirport)).getText();
		String startDate = _driver.findElement(By.xpath(XPathConstants.ErrorMessageForStartDateTest2)).getText();
		String returnDate = _driver.findElement(By.xpath(XPathConstants.ErrorMessafeForReturnTest2)).getText();

		Assert.assertEquals(ExpectedInputConstants.ErrorDestination, destination);
		Assert.assertEquals(ExpectedInputConstants.ErrorSource, source);
		Assert.assertEquals(ExpectedInputConstants.ErrorStartDate, startDate);
		Assert.assertEquals(ExpectedInputConstants.ErrorReturnDate, returnDate);
	}

	@Test(priority = 7, dependsOnMethods = { "ClickFlights" })
	public void CheckCountTravelersField() {
		int counter = 0;

		_driver.findElement(By.xpath(XPathConstants.TravelersButtonX)).click();
		while (counter < 5) {
			_driver.findElement(By.xpath(XPathConstants.AdultsButtonX)).click();
			counter++;
		}

		counter = 0;
		while (counter < 6) {
			_driver.findElement(By.xpath(XPathConstants.ChildButtonX)).click();
			counter++;
		}

		counter = 0;
		while (counter < 6) {
			_driver.findElement(By.xpath(XPathConstants.InfantsButtonX)).click();
			counter++;
		}

		int adultCount = Integer.parseInt(_driver.findElement(By.xpath(XPathConstants.AdultsCountX)).getText());
		int childrenCount = Integer.parseInt(_driver.findElement(By.xpath(XPathConstants.ChildrenCountX)).getText());
		int infantsCount = Integer.parseInt(_driver.findElement(By.xpath(XPathConstants.InfantsCountX)).getText());

		Assert.assertEquals(6, adultCount);
		Assert.assertEquals(6, childrenCount);
		Assert.assertEquals(6, infantsCount);
	}

	@Test(priority = 8, dependsOnMethods = { "ClickFlights" })
	public void ValidateTravelersField() {
		_driver.findElement(By.xpath(XPathConstants.SubmitButtonX)).click();

		String errorMorePeople = _driver.findElement(By.xpath(XPathConstants.ErrorMessageForExtraPeopleX)).getText();
		String errorNullAge = _driver.findElement(By.xpath(XPathConstants.ErrorMessageForNullChilderenAgeX)).getText();

		Assert.assertEquals(ExpectedInputConstants.ErrorTravelerExtra, errorMorePeople);
		Assert.assertEquals(ExpectedInputConstants.ErrorTravelerNullChildrenAge, errorNullAge);
	}

	@AfterTest
	public void CloseBrowser() {
		_driver.quit();
	}
}
