package model;

import logic.Configuration;

public enum VolumeLevel {
	VL_NONE, VL_LOW, VL_MEDIUM, VL_HIGH;
	
	public static VolumeLevel getByPercentage(double level) {
		if(level == 0.0)
			return VL_NONE;
		if(level < Configuration.getInstance().volumeThresholds.get(VL_LOW))
			return VL_LOW;
		if(level < Configuration.getInstance().volumeThresholds.get(VL_MEDIUM))
			return VL_MEDIUM;
		return VL_HIGH;
	}
}
