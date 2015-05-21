package dataAccess.entities;

import java.util.Date;

public class Location extends EntityWithTime {
	private final double longitude;
	private final double latitude;
	private String locationName;
	
	public Location(Date date, double longitude, double latitude) {
		super(date);
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	@Override
	public EntityWithTime copy() {
		Location location = new Location(new Date(date.getTime()), longitude, latitude);
		location.setLocationName(locationName);
		return location;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

}
