package dataAccess.entities;

import java.util.Date;

public final class Battery extends EntityWithTime{
	private final Device device;
	private final int batteryStatus;
	private final int batteryLevel;
	private final int batteryScale;
	private final int batteryVoltage;
	private final int batteryTemperature;
	private final int batteryAdaptor;
	private final int batteryHealth;
	
	public Battery(Date date, Device device, int batteryStatus, int batteryLevel, int batteryScale, int batteryVoltage, int batteryTemperature, int batteryAdaptor, int batteryHealth) {
		super(date);
		this.device = device;
		this.batteryStatus = batteryStatus;
		this.batteryLevel = batteryLevel;
		this.batteryScale = batteryScale;
		this.batteryVoltage = batteryVoltage;
		this.batteryTemperature = batteryTemperature;
		this.batteryAdaptor = batteryAdaptor;
		this.batteryHealth = batteryHealth;
	}

	public int getBatteryStatus() {
		return batteryStatus;
	}

	public int getBatteryLevel() {
		return batteryLevel;
	}

	public int getBatteryScale() {
		return batteryScale;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public int getBatteryTemperature() {
		return batteryTemperature;
	}

	public int getBatteryAdaptor() {
		return batteryAdaptor;
	}

	public int getBatteryHealth() {
		return batteryHealth;
	}

	public Device getDevice() {
		return device;
	}

	@Override
	public EntityWithTime copy() {
		return new Battery(new Date(date.getTime()), device, batteryStatus, batteryLevel, batteryScale, batteryVoltage, batteryTemperature, batteryAdaptor, batteryHealth);
	}
}
