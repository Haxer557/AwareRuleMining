package model;

import logic.Configuration;

public enum TrafficValue {
	TV_VERY_LOW, TV_LOW, TV_MEDIUM, TV_HIGH, TV_VERY_HIGH;
	
	public static TrafficValue getByBytes(long bytes) {
		if(bytes < Configuration.getInstance().trafficThresholds.get(TV_VERY_LOW))
			return TV_VERY_LOW;
		if(bytes < Configuration.getInstance().trafficThresholds.get(TV_LOW))
			return TV_LOW;
		if(bytes < Configuration.getInstance().trafficThresholds.get(TV_MEDIUM))
			return TV_MEDIUM;
		if(bytes < Configuration.getInstance().trafficThresholds.get(TV_HIGH))
			return TV_HIGH;
		return TV_VERY_HIGH;
	}
}
