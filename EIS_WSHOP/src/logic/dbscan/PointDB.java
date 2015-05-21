package logic.dbscan;

import logic.Configuration;
import dataAccess.entities.Location;

public class PointDB {
	private final Location location;
	private boolean visited;
	private String cluster;
	private final int longitudeIndex;
	private final int latitudeIndex;
	
	public PointDB(Location location, double minLongitude, double minLatitude, double maxLongitude, double maxLatitude) {
		this.location = location;
		setVisited(false);
		double deltaLongitude = maxLongitude - minLongitude;
		double deltaLatitude = maxLatitude - minLatitude;
		double relLongitude = location.getLongitude() - minLongitude;
		double relLatitude = location.getLatitude() - minLatitude;
		this.longitudeIndex = (int)((relLongitude / deltaLongitude) * Configuration.getInstance().longitudeMapSize);
		this.latitudeIndex = (int)((relLatitude / deltaLatitude) * Configuration.getInstance().latitudeMapSize);
		cluster = null;
	}
	
	public double getLongitude() {
		return location.getLongitude();
	}
	
	public double getLatitude() {
		return location.getLatitude();
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
		location.setLocationName(cluster);
	}

	public int getLongitudeIndex() {
		return longitudeIndex;
	}

	public int getLatitudeIndex() {
		return latitudeIndex;
	}
}
