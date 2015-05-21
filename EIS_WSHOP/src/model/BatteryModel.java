package model;

import java.util.Date;

public final class BatteryModel extends EntityModel {
	private final BatteryStatus batteryStatus;
	private final BatteryLevel batteryLevel;
	
	public BatteryModel(Date date, BatteryStatus batteryStatus, BatteryLevel batteryLevel) {
		super(date);
		this.batteryStatus = batteryStatus;
		this.batteryLevel = batteryLevel;
	}

	public BatteryStatus getBatteryStatus() {
		return batteryStatus;
	}

	public BatteryLevel getBatteryLevel() {
		return batteryLevel;
	}
	
	public static String toString(BatteryModel model) {
		if(model == null)
			return "?,?";
		return model.getBatteryStatus().toString() + "," + model.getBatteryLevel().toString();
	}
}
