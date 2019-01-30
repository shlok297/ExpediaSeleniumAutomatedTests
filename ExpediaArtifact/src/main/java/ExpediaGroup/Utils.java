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
}
