package logic.modelCalculators;

import logic.DataContainer;
import model.HumidityLevel;
import model.PressureLevel;
import model.RainValue;
import model.TemperatureLevel;
import model.WeatherModel;
import model.WindDirection;
import model.WindStrength;
import dataAccess.entities.WeatherCurrent;

public class WeatherCalculator implements ModelCalculator<WeatherCurrent, WeatherModel> {

	@Override
	public WeatherModel calculate(DataContainer<WeatherCurrent> dataContainer) {
		double sampleCount = dataContainer.getElements().size();
		if(sampleCount == 0)
			return null;
		
		double totalHumidity = 0.0;
		double totalTemperature = 0.0;
		double totalPressure = 0.0;
		double totalWindSpeed = 0.0;
		double totalWindAngle = 0.0;
		double totalRain = 0.0;
		
		for (WeatherCurrent weatherCurrent : dataContainer.getElements()) {
			totalHumidity += weatherCurrent.getHumidity();
			totalTemperature += fahrenheitToCelsius(weatherCurrent.getTemperatureValueCurrent());
			totalPressure += weatherCurrent.getPressure();
			totalWindSpeed += weatherCurrent.getWindSpeed();
			totalWindAngle += weatherCurrent.getWindAngle();
			totalRain += weatherCurrent.getRain();
		}
		
		
		totalHumidity /= sampleCount;
		totalTemperature /= sampleCount;
		totalPressure /= sampleCount;
		totalWindSpeed /= sampleCount;
		totalWindAngle /= sampleCount;
		totalRain /= sampleCount;
		HumidityLevel humidityLevel = HumidityLevel.getbyPercentage(totalHumidity/100.0);
		TemperatureLevel temperatureLevel = TemperatureLevel.getByValue(totalTemperature);
		PressureLevel pressureLevel = PressureLevel.getByValue(totalPressure);
		WindStrength windStrength = WindStrength.getByValue(totalWindSpeed);
		WindDirection windDirection = WindDirection.getByValue(totalWindAngle);
		RainValue rainValue = RainValue.getByValue(totalRain);
		return new WeatherModel(dataContainer.getDate(), temperatureLevel, humidityLevel, pressureLevel, windStrength, windDirection, rainValue);
	}

	private static double fahrenheitToCelsius(double f) {
		return (f-32.0)/1.8;
	}
}
