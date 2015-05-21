package model;

import logic.Configuration;

public enum HumidityLevel {
	HL_WET, HL_AVERAGE, HL_DRY;
	
	public static HumidityLevel getbyPercentage(double percentage) {
		if(percentage < Configuration.getInstance().humidityThresholds.get(HL_DRY))
			return HL_DRY;
		if(percentage < Configuration.getInstance().humidityThresholds.get(HL_AVERAGE))
			return HL_AVERAGE;
		return HL_WET;
	}
}
