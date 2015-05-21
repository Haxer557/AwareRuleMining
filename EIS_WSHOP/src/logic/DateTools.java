package logic;

import java.util.Calendar;
import java.util.Date;

public class DateTools {
	public static DayOfWeek toDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return DayOfWeek.intToDayOfWeek((calendar.get(Calendar.DAY_OF_WEEK)+6)%7);
	}
	
	public static boolean isWeekend(DayOfWeek dow) {
		return dow == DayOfWeek.DOW_FRIDAY || dow == DayOfWeek.DOW_SATURDAY || dow == DayOfWeek.DOW_SUNDAY; 
	}
	
	public static boolean isWeekend(Date date) {
		return isWeekend(toDayOfWeek(date));
	}
	
	public static boolean isWorkday(Date date) {
		return !isWeekend(date);
	}
	
	public static boolean isWorkday(DayOfWeek dow) {
		return !isWeekend(dow);
	}
	
	@SuppressWarnings("deprecation")
	public static int toDayTimeSeconds(Date date) {
		return date.getHours()*3600+date.getMinutes()*60+date.getSeconds();
	}
}
