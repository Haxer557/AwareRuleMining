package model;

import logic.Configuration;

public enum BatteryLevel {
	BL_VERY_LOW, BL_LOW, BL_MEDIUM, BL_HIGH, BL_FULL;
	
	public static BatteryLevel getByPercentage(double percentage) {
		if(percentage < Configuration.getInstance().batteryLevelThresholds.get(BL_VERY_LOW))
			return BL_VERY_LOW;
		if(percentage < Configuration.getInstance().batteryLevelThresholds.get(BL_LOW))
			return BL_LOW;
		if(percentage < Configuration.getInstance().batteryLevelThresholds.get(BL_MEDIUM))
			return BL_MEDIUM;
		if(percentage < Configuration.getInstance().batteryLevelThresholds.get(BL_HIGH))
			return BL_HIGH;
		return BL_FULL;
	}
}
