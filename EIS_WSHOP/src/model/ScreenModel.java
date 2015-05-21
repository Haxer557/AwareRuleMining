package model;

import java.util.Date;

public class ScreenModel extends EntityModel {
	private final ScreenStatus screenStatus;
	
	public ScreenModel(Date date, ScreenStatus screenStatus) {
		super(date);
		this.screenStatus = screenStatus;
	}

	public ScreenStatus getScreenStatus() {
		return screenStatus;
	}
	
	public static String toString(ScreenModel model) {
		if(model == null)
			return "?";
		return model.getScreenStatus().toString();
	}
}
