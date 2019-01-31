package ExpediaGroup;

import java.util.ArrayList;
import java.util.Collections;
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
		Thread.sleep(2000);

		_driver.findElement(By.xpath(XPathConstants.FlyingFromInputValX)).sendKeys(ExpectedInputConstants.FlightFrom);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(XPathConstants.FlyingFromInputValX)).sendKeys(Keys.TAB);

		Thread.sleep(1000);
		_driver.findElement(By.xpath(XPathConstants.FlyingToInputValX)).sendKeys(ExpectedInputConstants.FlightTo);
		Thread.sleep(1000);
		_driver.findElement(By.xpath(XPathConstants.FlyingToInputValX)).sendKeys(Keys.TAB);

		_driver.findElement(By.xpath(XPathConstants.DepartDateInputX)).click();
		WebElement dateDepartureSelector = _driver.findElement(By.xpath(XPathConstants.DepartDateSelectX));
		_departingDateBeforeSearch = Utils.GetDate(dateDepartureSelector.getAttribute("data-day"),
				String.valueOf(Integer.parseInt(dateDepartureSelector.getAttribute("data-month")) + 1),
				dateDepartureSelector.getAttribute("data-year"));
		dateDepartureSelector.click();

		_driver.findElement(By.xpath(XPathConstants.ReturningDateInputX)).click();
		_driver.findElement(By.xpath(XPathConstants.NextMonthX)).click();
		WebElement dateReturnSelector = _driver.findElement(By.xpath(XPathConstants.ReturnDateSelectX));
		_returningDateBeforeSearch = Utils.GetDate(dateReturnSelector.getAttribute("data-day"),
				String.valueOf(Integer.parseInt(dateReturnSelector.getAttribute("data-month")) + 1),
				dateReturnSelector.getAttribute("data-year"));
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

	@Test(priority = 2, dependsOnMethods = { "CheckValidData" })
	public void ValidDataTransferToSearchPage() throws InterruptedException {
		String sourceString = _driver.findElement(By.xpath(XPathConstants.DepartureAirport)).getAttribute("value");
		String destinationString = _driver.findElement(By.xpath(XPathConstants.ArrivalAirport)).getAttribute("value");
		String departureDateString = _driver.findElement(By.xpath(XPathConstants.DepartureDateAfter))
				.getAttribute("value");
		String returningDateString = _driver.findElement(By.xpath(XPathConstants.ReturningDateAfter))
				.getAttribute("value");

		Assert.assertTrue(sourceString.contains(ExpectedInputConstants.FlightFrom));
		Assert.assertTrue(destinationString.contains(ExpectedInputConstants.FlightTo));
		Assert.assertEquals(_departingDateBeforeSearch, departureDateString);
		Assert.assertEquals(_returningDateBeforeSearch, returningDateString);
	}

	@Test(priority = 3, dependsOnMethods = { "CheckValidData" })
	public void ValidateSortingMaxAndMinPrice() throws InterruptedException {
		ArrayList<Integer> listOfFlightsLowToHigh = new ArrayList<Integer>();
		ArrayList<Integer> listOfFlightsHighToLow = new ArrayList<Integer>();
		int countFlightOptions = _driver.findElements(By.xpath(XPathConstants.NumberOfFlightOptionsX)).size();
		int counter = 1;

		while (counter < 5) {
			if (counter != 6 && counter != 12 && counter != 17) {
				String stringValue = _driver.findElement(By.xpath(
						"//*[@id='flightModuleList']/li[" + counter + "]/div[1]/div[1]/div[2]/div/div[1]/div[1]/span"))
						.getText();

				listOfFlightsLowToHigh.add(Utils.ConvertStringDollarsToInteger(stringValue));
			}
			counter++;
		}
		Collections.sort(listOfFlightsLowToHigh);

		int minValue = listOfFlightsLowToHigh.get(0);
		int firstChapeastFlight = Utils.ConvertStringDollarsToInteger(
				_driver.findElement(By.xpath(XPathConstants.PriceOfFirstFlightsX)).getText());

		Assert.assertTrue(countFlightOptions > 0);
		Assert.assertEquals(minValue, firstChapeastFlight);

		Select sortDropDown = new Select(_driver.findElement(By.xpath(XPathConstants.SortSelectDrpDownX)));
		sortDropDown.selectByValue("price:desc");

		Thread.sleep(2000);

		counter = 1;
		while (counter < 5) {
			if (counter != 6 && counter != 12 && counter != 17) {
				String stringValue = _driver.findElement(By.xpath(
						"//*[@id='flightModuleList']/li[" + counter + "]/div[1]/div[1]/div[2]/div/div[1]/div[1]/span"))
						.getText();

				listOfFlightsHighToLow.add(Utils.ConvertStringDollarsToInteger(stringValue));
			}
			counter++;
		}
		Collections.sort(listOfFlightsHighToLow);

		int maxValue = listOfFlightsHighToLow.get(listOfFlightsHighToLow.size() - 1);
		int firstCostliestFlight = Utils.ConvertStringDollarsToInteger(
				_driver.findElement(By.xpath(XPathConstants.PriceOfFirstFlightsX)).getText());
		Assert.assertEquals(maxValue, firstCostliestFlight);
	}

	@Test(priority = 4, dependsOnMethods = { "CheckValidData" })
	public void ValidateSortingBasedOnDuration() throws InterruptedException {
		ArrayList<Integer> listOfFlightsDurationLowToHigh = new ArrayList<Integer>();
		ArrayList<Integer> listOfFlightsDurationHighToLow = new ArrayList<Integer>();
		int counter = 1;

		Select sortDropDown = new Select(_driver.findElement(By.xpath(XPathConstants.SortSelectDrpDownX)));
		sortDropDown.selectByValue("duration:desc");

		Thread.sleep(2000);

		while (counter < 5) {
			String stringValue = _driver.findElement(By.xpath("//*[@id='flightModuleList']/li[" + counter
					+ "]/div[1]/div[1]/div[1]/div/div/div/div[2]/div[1]/span[1]")).getText();
			listOfFlightsDurationHighToLow.add(Utils.ConvertFlightDurationIntoMinutes(stringValue));
			counter++;
		}
		Collections.sort(listOfFlightsDurationHighToLow);

		int maxValue = listOfFlightsDurationHighToLow.get(listOfFlightsDurationHighToLow.size() - 1);
		int firstLongestFlight = Utils.ConvertFlightDurationIntoMinutes(
				_driver.findElement(By.xpath(XPathConstants.DurationOfTheFirstFlight)).getText());
		Assert.assertEquals(maxValue, firstLongestFlight);

		counter = 1;

		Select sortDropDownForDurationAsc = new Select(
				_driver.findElement(By.xpath(XPathConstants.SortSelectDrpDownX)));
		sortDropDownForDurationAsc.selectByValue("duration:asc");

		Thread.sleep(2000);

		while (counter < 5) {
			String stringValue = _driver.findElement(By.xpath("//*[@id='flightModuleList']/li[" + counter
					+ "]/div[1]/div[1]/div[1]/div/div/div/div[2]/div[1]/span[1]")).getText();
			listOfFlightsDurationLowToHigh.add(Utils.ConvertFlightDurationIntoMinutes(stringValue));
			counter++;
		}
		Collections.sort(listOfFlightsDurationLowToHigh);

		int minValue = listOfFlightsDurationLowToHigh.get(0);
		int firstShortestFlight = Utils.ConvertFlightDurationIntoMinutes(
				_driver.findElement(By.xpath(XPathConstants.DurationOfTheFirstFlight)).getText());
		Assert.assertEquals(minValue, firstShortestFlight);
	}

	@AfterTest
	public void CloseBrowser() {
		_driver.quit();
	}
}
