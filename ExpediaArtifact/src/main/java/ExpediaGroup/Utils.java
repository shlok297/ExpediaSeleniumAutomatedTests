package ExpediaGroup;

public class Utils {

	public static String GetDate(String day, String month, String year) {
		String dateString = AppendZero(month) + "/" + AppendZero(day) + "/" + year;
		return dateString;
	}
	
	public static String AppendZero(String number) {
		int numberInt = Integer.parseInt(number);
		if(numberInt < 10)
		{
			return "0" + number;
		} 
		return number;
	}
	
	public static int ConvertStringDollarsToInteger(String stringValue) {
		if(stringValue.contains("$"))
		{
			String stringNumber = "";
			for (char character : stringValue.toCharArray()) {
				if(character != '$' && character != ',') {
					stringNumber +=  character;
				}
			}
			return Integer.parseInt(stringNumber);
		}
		return 0;
	}
	
	public static int ConvertFlightDurationIntoMinutes(String flightDuration)
	{
		String[] durationString = flightDuration.split(" ");
		return  Utils.ConvertHrsIntoMins(durationString[0]) + Utils.ConvertMinsStringIntoMins(durationString[1]);
	}
	
	public static int ConvertHrsIntoMins(String hrsString)
	{
		String hoursNumber = "";
		for (char character : hrsString.toCharArray()) {
			if(character != 'h')
			{
				hoursNumber += character;
			}
		}
		return Integer.parseInt(hoursNumber) * 60;
	}
	
	public static int ConvertMinsStringIntoMins(String minsString)
	{
		String minutesNumber = "";
		for (char character : minsString.toCharArray()) {
			if(character != 'm')
			{
				minutesNumber += character;
			}
		}
		return Integer.parseInt(minutesNumber);
	}
}
