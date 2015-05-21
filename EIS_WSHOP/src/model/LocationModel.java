package model;

import java.util.Date;

public class LocationModel extends EntityModel {
	private final String locationName;
	public LocationModel(Date date, String locationName) {
		super(date);
		this.locationName = locationName;
	}
	
	public String getLocationName() {
		return locationName;
	}
	
	public static String toString(LocationModel model) {
		if(model == null)
			return "?";
		return model.locationName;
	}
}
