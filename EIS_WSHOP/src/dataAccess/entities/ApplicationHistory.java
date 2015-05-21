package dataAccess.entities;

import java.util.Date;

@Deprecated
public final class ApplicationHistory extends EntityWithTime {
	private final String applicationName;
	private final boolean systemApp;
	private final Device device;
	private final String packageName;
	
	public ApplicationHistory(Date date, String applicationName, boolean systemApp, Device device, String packageName) {
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
		return new ApplicationHistory (new Date(date.getTime()), applicationName, systemApp, device, packageName);
	}
}
