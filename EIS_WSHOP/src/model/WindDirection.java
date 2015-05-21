package model;

public enum WindDirection {
	WD_N, WD_NE, WD_E, WD_SE, WD_S, WD_SW, WD_W, WD_NW;
	
	public static WindDirection getByValue(double value) {
		if(value >= 337.5 || value < 22.5)
			return WD_N;
		if(value < 67.5)
			return WD_NE;
		if(value < 112.5)
			return WD_E;
		if(value < 157.5)
			return WD_SE;
		if(value < 202.5)
			return WD_S;
		if(value < 247.5)
			return WD_SW;
		if(value < 292.5)
			return WD_W;
		return WD_NW;
	}
}
