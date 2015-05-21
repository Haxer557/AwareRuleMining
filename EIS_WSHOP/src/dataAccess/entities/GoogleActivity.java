package dataAccess.entities;

import java.util.Date;

public final class GoogleActivity extends EntityWithTime {
	private final Device device;
	private final String activities;
	
	public GoogleActivity(Date date, Device device, String activities) {
		super(date);
		this.device = device;
		this.activities = activities;
	}

	public Device getDevice() {
		return device;
	}

	public String getActivities() {
		return activities;
	}
	
	@Override
	public EntityWithTime copy() {
		return new GoogleActivity(new Date(date.getTime()), device, activities);
	}
}
