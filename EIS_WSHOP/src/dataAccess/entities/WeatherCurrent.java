package dataAccess.entities;

import java.util.Date;

public final class WeatherCurrent extends EntityWithTime{
	private final Device device;
	private final double temperatureValueCurrent;
	private final int humidity;
	private final double pressure;
	private final double windSpeed;
	private final double windAngle;
	private final double rain;
	
	
	public WeatherCurrent(Date date, Device device, double temperatureValueCurrent, int humidity, double pressure, double windSpeed, double windAngle, double rain) {
		super(date);
		this.device = device;
		this.temperatureValueCurrent = temperatureValueCurrent;
		this.humidity = humidity;
		this.pressure = pressure;
		this.windSpeed = windSpeed;
		this.windAngle = windAngle;
		this.rain = rain;
	}

	public Device getDevice() {
		return device;
	}

	public double getTemperatureValueCurrent() {
		return temperatureValueCurrent;
	}

	public int getHumidity() {
		return humidity;
	}

	public double getPressure() {
		return pressure;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public double getWindAngle() {
		return windAngle;
	}

	public double getRain() {
		return rain;
	}

	@Override
	public EntityWithTime copy() {
		return new WeatherCurrent(new Date(date.getTime()), device, temperatureValueCurrent, humidity, pressure, windSpeed, windAngle, rain);
	}
}
