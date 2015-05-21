package model;

import java.util.Date;

import logic.Configuration;
import logic.DateTools;
import logic.DayOfWeek;
import logic.TimeSpan;

public abstract class EntityModel {
	protected final Date date;
	protected final DayOfWeek dayOfWeek;
	protected TimeSpan groupedBy;
	
	public EntityModel(Date date) {
		this.date = new Date(date.getTime()-date.getTime()%Configuration.getInstance().singleTimeSpan);
		this.dayOfWeek = DateTools.toDayOfWeek(date);
	}
	
	public Date getDate() {
		return date;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public TimeSpan getGroupedBy() {
		return groupedBy;
	}

	public void setGroupedBy(TimeSpan groupedBy) {
		this.groupedBy = groupedBy;
	}
}
