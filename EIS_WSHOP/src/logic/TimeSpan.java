package logic;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.EntityModel;

public class TimeSpan {
	private final DayOfWeek dayOfWeek;
	private final int startTimeSeconds;
	private final int endTimeSeconds;
	
	public TimeSpan(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
		this.startTimeSeconds = 0;
		this.endTimeSeconds = 23*3600+59*60+59;
	}
	
	public TimeSpan(DayOfWeek dayOfWeek, int startTimeSeconds, int endTimeSeconds) {
		this.dayOfWeek = dayOfWeek;
		this.startTimeSeconds = startTimeSeconds;
		this.endTimeSeconds = endTimeSeconds;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public int getStartTimeSeconds() {
		return startTimeSeconds;
	}

	public int getEndTimeSeconds() {
		return endTimeSeconds;
	}
	
	public boolean covers(EntityModel model) {
		if(dayOfWeek != null && model.getDayOfWeek()!=dayOfWeek)
			return false;
		int modelTimeSeconds = DateTools.toDayTimeSeconds(model.getDate());
		if(modelTimeSeconds < startTimeSeconds || modelTimeSeconds > endTimeSeconds)
			return false;
		return true;
	}
	
	public String toStringTime() {
		Date from = new Date(startTimeSeconds*1000 - 3600000);
		Date to = new Date(endTimeSeconds*1000 - 3600000);
		Format formatter = new SimpleDateFormat("HH:mm");
		return formatter.format(from) + "-" + formatter.format(to);
	}
}
