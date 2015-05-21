package model;

import logic.Configuration;

public enum TemperatureLevel {
	TL_VERY_HOT, TL_HOT, TL_COOL, TL_COLD, TL_FREEZING;
	
	public static TemperatureLevel getByValue(double value) {
		if(value < Configuration.getInstance().temperatureThresholds.get(TL_FREEZING))
			return TL_FREEZING;
		if(value < Configuration.getInstance().temperatureThresholds.get(TL_COLD))
			return TL_COLD;
		if(value < Configuration.getInstance().temperatureThresholds.get(TL_COOL))
			return TemperatureLevel.TL_COOL;
		if(value < Configuration.getInstance().temperatureThresholds.get(TL_HOT))
			return TL_HOT;
		return TL_VERY_HOT;
	}
}
