package model;

import logic.Configuration;

public enum PressureLevel {
	PL_VERY_HIGH, PL_HIGH, PL_NORMAL, PL_LOW, PL_VERY_LOW;
	
	public static PressureLevel getByValue(double value) {
		if(value < Configuration.getInstance().pressureThresholds.get(PL_VERY_LOW))
			return PressureLevel.PL_VERY_LOW;
		if(value < Configuration.getInstance().pressureThresholds.get(PL_LOW))
			return PressureLevel.PL_LOW;
		if(value < Configuration.getInstance().pressureThresholds.get(PL_NORMAL))
			return PressureLevel.PL_NORMAL;
		if(value < Configuration.getInstance().pressureThresholds.get(PL_HIGH))
			return PressureLevel.PL_HIGH;
		return PL_VERY_HIGH;
	}
}
