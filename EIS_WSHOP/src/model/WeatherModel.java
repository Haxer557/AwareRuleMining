package model;

import java.util.Date;

public class WeatherModel extends EntityModel {
	private final TemperatureLevel temperatureLevel;
	private final HumidityLevel humidityLevel;
	private final PressureLevel pressureLevel;
	private final WindStrength windStrength;
	private final WindDirection windDirection;
	private final RainValue rainValue;
	
	public WeatherModel(Date date, TemperatureLevel temperatureLevel, HumidityLevel humidityLevel, PressureLevel pressureLevel, WindStrength windStrength, WindDirection windDirection, RainValue rainValue) {
		super(date);
		this.temperatureLevel = temperatureLevel;
		this.humidityLevel = humidityLevel;
		this.pressureLevel = pressureLevel;
		this.windStrength = windStrength;
		this.windDirection = windDirection;
		this.rainValue = rainValue;
	}

	public TemperatureLevel getTemperatureLevel() {
		return temperatureLevel;
	}

	public HumidityLevel getHumidityLevel() {
		return humidityLevel;
	}

	public PressureLevel getPressureLevel() {
		return pressureLevel;
	}

	public WindStrength getWindStrength() {
		return windStrength;
	}

	public WindDirection getWindDirection() {
		return windDirection;
	}

	public RainValue getRainValue() {
		return rainValue;
	}

	public static String toString(WeatherModel model) {
		if(model == null)
			return "?,?,?,?,?,?";
		return model.getTemperatureLevel().toString() + ","
			+ model.getHumidityLevel().toString() + ","
			+ model.getPressureLevel().toString() + ","
			+ model.getWindStrength().toString() + ","
			+ model.getWindDirection().toString() + ","
			+ model.getRainValue().toString();
	}
}
