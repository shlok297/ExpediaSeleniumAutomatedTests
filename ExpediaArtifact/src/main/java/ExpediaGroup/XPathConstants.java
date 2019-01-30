package ExpediaGroup;

public class XPathConstants {
	public static final String HotelTabX = "//button[@id='tab-hotel-tab-hp']";
	public static final String GoingToX = "//*[@id=\'gcw-hotel-form-hp-hotel\']/div[3]/div/div/div[1]/label/span[1]";
	public static final String CheckInX = "//*[@id='hotel-checkin-wrapper-hp-hotel']/label/span[1]";
	public static final String CheckOutX = "//*[@id='hotel-checkout-wrapper-hp-hotel']/label/span[1]";
	public static final String CarsTabX = "//*[@id=\'tab-car-tab-hp\']";
	public static final String PickUpX = "//*[@id=\'gcw-cars-form-hp-car\']/div[3]/div[1]/div/div[1]/label/span[1]";
	public static final String DropOffX = "//*[@id=\'car-dropoff-container-hp-car\']/div/div[1]/label/span[1]";
	public static final String PickUpDateX = "//*[@id=\'car-pickup-date-start-hp-car\']/label/span[1]";
	public static final String DropOffDateX = "//*[@id=\'car-dropoff-date-end-hp-car\']/label/span[1]";
	public static final String FlightsTabX = "//*[@id=\'tab-flight-tab-hp\']"; 
	public static final String FlyingFromX = "//*[@id=\'gcw-flights-form-hp-flight\']/div[3]/div[1]/div/div[1]/label/span[1]";
	public static final String FlyingToX = "//*[@id=\'gcw-flights-form-hp-flight\']/div[3]/div[2]/div/div[1]/label/span[1]";
	public static final String DepartingX = "//*[@id=\'flight-departing-wrapper-hp-flight\']/label/span[1]";
	public static final String ReturningX = "//*[@id=\'flight-returning-wrapper-hp-flight\']/label/span[1]";
	public static final String TravelersX = "//*[@id=\'traveler-selector-hp-flight\']/div/ul/li/button/span/span[3]";
	public static final String FlyingFromInputX = "//*[@id=\'flight-origin-hp-flight-ta\']"; 
	public static final String CancelButtonFlyFromX = "//*[@id=\'gcw-flights-form-hp-flight\']/div[3]/div[1]/div/div[1]/button";
	public static final String FlyingFromInputValX = "//*[@id=\'flight-origin-hp-flight\']";
	public static final String FlyingToInputX = "//*[@id=\'flight-destination-hp-flight-ta\']";
	public static final String CancelButtonFlyToX = "//*[@id=\'gcw-flights-form-hp-flight\']/div[3]/div[2]/div/div[1]/button";
	public static final String FlyingToInputValX = "//*[@id=\'flight-destination-hp-flight\']";
	public static final String SubmitButtonX = "//*[@id=\'gcw-flights-form-hp-flight\']/div[7]/label/button";
	public static final String ErrorMessageForStartDate = "//*[@id=\'gcw-flights-form-hp-flight\']/div[2]/div/ul/li[1]/a";
	public static final String ErrorMessafeForReturnDate = "//*[@id=\'gcw-flights-form-hp-flight\']/div[2]/div/ul/li[2]/a";
	public static final String ErrorMessageFromAirport = "//*[@id=\'gcw-flights-form-hp-flight\']/div[2]/div/ul/li[1]/a";
	public static final String ErrorMessageToAirport = "//*[@id=\'gcw-flights-form-hp-flight\']/div[2]/div/ul/li[2]/a";
	public static final String ErrorMessageForStartDateTest2 = "//*[@id=\'gcw-flights-form-hp-flight\']/div[2]/div/ul/li[3]/a";
	public static final String ErrorMessafeForReturnTest2 = "//*[@id=\'gcw-flights-form-hp-flight\']/div[2]/div/ul/li[4]/a";
	public static final String ErrorMessageForExtraPeopleX = "//*[@id=\'gcw-flights-form-hp-flight\']/div[2]/div/ul/li[5]/a";
	public static final String ErrorMessageForNullChilderenAgeX = "//*[@id=\'gcw-flights-form-hp-flight\']/div[2]/div/ul/li[6]/a";
	public static final String TravelersButtonX = "//*[@id='traveler-selector-hp-flight']/div/ul/li/button";
	public static final String AdultsButtonX = "//*[@id=\'traveler-selector-hp-flight\']/div/ul/li/div/div/div/div[1]/div[4]/button";
	public static final String ChildButtonX = "//*[@id=\'traveler-selector-hp-flight\']/div/ul/li/div/div/div/div[2]/div[1]/div[4]/button";
	public static final String InfantsButtonX = "//*[@id=\'traveler-selector-hp-flight\']/div/ul/li/div/div/div/div[3]/div[1]/div[4]/button";
	public static final String AdultsCountX = "//*[@id=\'traveler-selector-hp-flight\']/div/ul/li/div/div/div/div[1]/div[3]/span";
	public static final String ChildrenCountX = "//*[@id=\'traveler-selector-hp-flight\']/div/ul/li/div/div/div/div[2]/div[1]/div[3]/span";
	public static final String InfantsCountX = "//*[@id=\'traveler-selector-hp-flight\']/div/ul/li/div/div/div/div[3]/div[1]/div[3]/span";
	public static final String DepartDateInputX = "//*[@id=\'flight-departing-wrapper-hp-flight\']";
	public static final String ReturningDateInputX = "//*[@id=\'flight-returning-wrapper-hp-flight\']";
	public static final String DepartDateSelectX = "//*[@id=\'flight-departing-wrapper-hp-flight\']/div/div/div[3]/table/tbody/tr[3]/td[4]/button";
	public static final String NextMonthX = "//*[@id=\'flight-returning-wrapper-hp-flight\']/div/div/button[2]";
	public static final String ReturnDateSelectX = "//*[@id=\'flight-returning-wrapper-hp-flight\']/div/div/div[3]/table/tbody/tr[3]/td[5]/button";

	//under test
	public static final String SelectChildAgeDrpDownX = "//*[@id='flight-age-select-1-hp-flight']";
	public static final String SelectInfantAgeDrpDownX = "//*[@id=\'traveler-selector-hp-flight\']/div/ul/li/div/div/div/div[3]/div[2]/label[1]/select";

}
