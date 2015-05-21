package model;

import java.util.Date;

public final class ApplicationForegroundModel extends EntityModel {
	private final String applicationName;
	private final boolean systemApp;
	
	public ApplicationForegroundModel(Date date, String applicationName, boolean systemApp) {
		super(date);
		this.applicationName = applicationName.replace(' ', '_');
		this.systemApp = systemApp;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public boolean isSystemApp() {
		return systemApp;
	}
	
	public static String toString(ApplicationForegroundModel model) {
		if(model == null)
			return "?,?";
		return model.getApplicationName()
				+ ","
				+ (model.isSystemApp()
					? "TRUE"
					: "FALSE");
	}
}
