package dataAccess.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dataAccess.Context;
import dataAccess.entities.ApplicationHistory;
import dataAccess.entities.Device;

@Deprecated
public class ApplicationHistoryRepository {
	public List<ApplicationHistory> getAllByDevice(Device device) {
		List<ApplicationHistory> apps = new LinkedList<>();
		Connection sqlConn = Context.getConnection();

		try {
			String query = 	"SELECT application_name, is_system_app, package_name, timestamp " +
							"FROM applications_history " +
							"WHERE device_id = \'" + device.getDeviceId() + "\'" +
							"ORDER BY timestamp";
			ResultSet rs = sqlConn.createStatement().executeQuery(query);
			while(rs.next()) {
				String appName = rs.getString(1);
				boolean isSystemApp = rs.getBoolean(2);
				String packageName = rs.getString(3);
				Date date = new Date(rs.getLong(4));
				ApplicationHistory app = new ApplicationHistory(date, appName, isSystemApp, device, packageName);
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
