package dataAccess.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dataAccess.Context;
import dataAccess.entities.Device;
import dataAccess.entities.GoogleActivity;

public class GoogleActivityRepository {
	public List<GoogleActivity> getAllByDevice(Device device) {
		List<GoogleActivity> googleActivities = new LinkedList<>();
		Connection sqlConn = Context.getConnection();

		try {
			String query = 	"SELECT timestamp, activities " +
							"FROM plugin_google_activity_recognition " +
							"WHERE device_id = \'" + device.getDeviceId() + "\'" +
							"ORDER BY timestamp";
			ResultSet rs = sqlConn.createStatement().executeQuery(query);
			while(rs.next()) {
				Date date = new Date(rs.getLong(1));
				String activities = rs.getString(2);
				GoogleActivity googleActivity = new GoogleActivity(date, device, activities);
				googleActivities.add(googleActivity);
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
		return googleActivities;
	}
}
