package dataAccess.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dataAccess.Context;
import dataAccess.entities.Device;
import dataAccess.entities.Screen;

public class ScreenRepository {
	public List<Screen> getAllByDevice(Device device) {
		List<Screen> screens = new LinkedList<>();
		Connection sqlConn = Context.getConnection();

		try {
			String query = 	"SELECT timestamp, screen_status " +
							"FROM screen " +
							"WHERE device_id = \'" + device.getDeviceId() + "\'" +
							"ORDER BY timestamp";
			ResultSet rs = sqlConn.createStatement().executeQuery(query);
			while(rs.next()) {
				Date date = new Date(rs.getLong(1));
				int screenStatus = rs.getInt(2);
				Screen screen = new Screen(date, device, screenStatus);
				screens.add(screen);
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
		return screens;
	}
}
