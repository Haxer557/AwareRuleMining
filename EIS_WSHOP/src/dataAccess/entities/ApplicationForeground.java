package dataAccess.entities;

import java.util.Date;

public final class ApplicationForeground extends EntityWithTime {
	private final String applicationName;
	private final boolean systemApp;
	private final Device device;
	private final String packageName;
	
	public ApplicationForeground(Date date, String applicationName, boolean systemApp, Device device, String packageName) {
		super(date);
		this.applicationName = applicationName;
		this.systemApp = systemApp;
		this.device = device;
		this.packageName = packageName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public boolean isSystemApp() {
		return systemApp;
	}

	public Device getDevice() {
		return device;
	}

	public String getPackageName() {
		return packageName;
	}

	@Override
	public EntityWithTime copy() {
		return new ApplicationForeground (new Date(date.getTime()), applicationName, systemApp, device, packageName);
	}
}
