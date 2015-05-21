package dataAccess.entities;

import java.util.Date;

public abstract class EntityWithTime {
	protected final Date date;
	protected long duration;
	
	public EntityWithTime(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public long getDuration() {
		return duration;
	}
	
	public abstract EntityWithTime copy();
}
