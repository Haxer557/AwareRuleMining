package model;

import java.util.Date;


public class GoogleActivityModel extends EntityModel {
	private final String activity;
	
	public GoogleActivityModel(Date date, String activity) {
		super(date);
		this.activity = activity;
	}

	public String getActivity() {
		return activity;
	}

	public static String toString(GoogleActivityModel model) {
		if(model == null)
			return "?";
		return model.getActivity();
	}
}
