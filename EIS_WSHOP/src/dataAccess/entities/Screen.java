package dataAccess.entities;

import java.util.Date;

public final class Screen extends EntityWithTime {
	private final Device device;
	private final int screenStatus;
	
	public Screen(Date date, Device device, int screenStatus) {
		super(date);
		this.device = device;
		this.screenStatus = screenStatus;
	}

	public Device getDevice() {
		return device;
	}

	public int getScreenStatus() {
		return screenStatus;
	}

	@Override
	public EntityWithTime copy() {
		return new Screen(new Date(date.getTime()), device, screenStatus);
	}
}
