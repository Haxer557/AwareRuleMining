package dataAccess.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dataAccess.Context;
import dataAccess.entities.Device;
import dataAccess.entities.WeatherCurrent;

public class WeatherCurrentRepository {
	public List<WeatherCurrent> getAllByDevice(Device device) {
		List<WeatherCurrent> weathers = new LinkedList<>();
		Connection sqlConn = Context.getConnection();

		try {
			String query = 	"SELECT timestamp, temperature_value_current, humidity, pressure, " +
							"wind_speed, wind_angle, rain " +
							"FROM plugin_weather_current " +
							"WHERE device_id = \'" + device.getDeviceId() + "\'" +
							"ORDER BY timestamp";
			ResultSet rs = sqlConn.createStatement().executeQuery(query);
			while(rs.next()) {
				Date date = new Date(rs.getLong(1));
				double temperatureValueCurrent = rs.getDouble(2);
				int humidity = rs.getInt(3);
				int pressure = rs.getInt(4);
				double windSpeed = rs.getDouble(5);
				double windAngle = rs.getDouble(6);
				double rain = rs.getDouble(7);
				WeatherCurrent weather = new WeatherCurrent(date, device, temperatureValueCurrent, humidity, pressure, windSpeed, windAngle, rain);
				weathers.add(weather);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				sqlConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return weathers;
	}
}
