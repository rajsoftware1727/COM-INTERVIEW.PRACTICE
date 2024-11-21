package Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibm.icu.util.Calendar;

public class DateFormat {

	public static void main(String args[]) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
		dateFormat.setLenient(false);
		Date date = dateFormat.parse("15/january/1990");
		System.out.println(date);
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int tDay = cal.get(Calendar.DAY_OF_MONTH);
			int tYear = cal.get(Calendar.YEAR);
			System.out.println("year is " + tYear);
			System.out.println("day is " + tDay);

		} catch (Exception e) {
			throw new Exception("invalid date");
		}

		// get data actual data s that setup the comparisontargetMonth

	}
}
