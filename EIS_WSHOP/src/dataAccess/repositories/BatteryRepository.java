package dataAccess.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dataAccess.Context;
import dataAccess.entities.Battery;
import dataAccess.entities.Device;

public class BatteryRepository {
	public List<Battery> getAllByDevice(Device device) {
		List<Battery> batteries = new LinkedList<>();
		Connection sqlConn = Context.getConnection();

		try {
			String query = 	"SELECT timestamp, battery_status, battery_level, battery_scale, battery_voltage, " +
							"battery_temperature, battery_adaptor, battery_health " +
							"FROM battery " +
							"WHERE device_id = \'" + device.getDeviceId() + "\'" +
							"ORDER BY timestamp";
			ResultSet rs = sqlConn.createStatement().executeQuery(query);
			while(rs.next()) {
				Date date = new Date(rs.getLong(1));
				int batteryStatus = rs.getInt(2);
				int batteryLevel = rs.getInt(3);
				int batteryScale = rs.getInt(4);
				int batteryVoltage = rs.getInt(5);
				int batteryTemperature = rs.getInt(6);
				int batteryAdaptor = rs.getInt(7);
				int batteryHealth = rs.getInt(8);
				Battery battery = new Battery(date, device, batteryStatus, batteryLevel, batteryScale, batteryVoltage, batteryTemperature, batteryAdaptor, batteryHealth);
				batteries.add(battery);
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
		return batteries;
	}
}
