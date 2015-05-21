package model;

import logic.Configuration;

public enum RainValue {
	RV_NONE, RV_MIST, RV_RAIN, RV_STORM;
	
	public static RainValue getByValue(double value) {
		if(value == 0)
			return RV_NONE;
		if(value < Configuration.getInstance().rainThresholds.get(RV_MIST))
			return RV_MIST;
		if(value < Configuration.getInstance().rainThresholds.get(RV_RAIN))
			return RV_RAIN;
		return RV_STORM;
	}
}
