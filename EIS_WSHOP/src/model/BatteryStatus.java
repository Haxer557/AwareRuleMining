package model;

public enum BatteryStatus {
	BS_CHARGING, BS_DISCHARGING, BS_FULL, BS_NOT_CHARGING, BS_UNKNOWN, BS_SHUTDOWN, BS_REBOOTED;
	
	public static BatteryStatus getByInt(int status) {
		switch(status) {
		case -2:
			return BS_REBOOTED;
		case -1:
			return BS_SHUTDOWN;
		case 2:
			return BS_CHARGING;
		case 3:
			return BS_DISCHARGING;
		case 4:
			return BS_NOT_CHARGING;
		case 5:
			return BS_FULL;
		default:
			return BS_UNKNOWN;
		}
	}
}
