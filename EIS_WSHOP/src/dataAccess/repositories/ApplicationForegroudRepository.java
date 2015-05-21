package dataAccess.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dataAccess.Context;
import dataAccess.entities.ApplicationForeground;
import dataAccess.entities.Device;

public class ApplicationForegroudRepository {
	public List<ApplicationForeground> getAllByDevice(Device device) {
		List<ApplicationForeground> apps = new LinkedList<>();
		Connection sqlConn = Context.getConnection();

		try {
			String query = 	"SELECT application_name, is_system_app, package_name, timestamp " +
							"FROM applications_foreground " +
							"WHERE device_id = \'" + device.getDeviceId() + "\'" +
							"ORDER BY timestamp";
			ResultSet rs = sqlConn.createStatement().executeQuery(query);
			while(rs.next()) {
				String appName = rs.getString(1);
				boolean isSystemApp = rs.getBoolean(2);
				String packageName = rs.getString(3);
				Date date = new Date(rs.getLong(4));
				ApplicationForeground app = new ApplicationForeground(date, appName, isSystemApp, device, packageName);
				apps.add(app);
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
		return apps;
	}
}
