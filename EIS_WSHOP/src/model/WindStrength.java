package model;

import logic.Configuration;

public enum WindStrength {
	WS_WEAK, WS_STRONG, WS_VERY_STRONG;
	
	public static WindStrength getByValue(double value) {
		if(value < Configuration.getInstance().windThresholds.get(WS_WEAK))
			return WS_WEAK;
		if(value < Configuration.getInstance().windThresholds.get(WS_STRONG))
			return WS_STRONG;
		return WS_VERY_STRONG;
	}
}
