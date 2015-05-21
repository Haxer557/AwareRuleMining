package dataAccess.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import dataAccess.Context;
import dataAccess.entities.Device;

public class DeviceRepository {
	public List<Device> getAll() {
		List<Device> devices = new LinkedList<>();
		Connection sqlConn = Context.getConnection();

		try {
			String query = "SELECT device_id FROM aware_device";
			ResultSet rs = sqlConn.createStatement().executeQuery(query);
			while(rs.next()) {
				String uuid = rs.getString(1);
				Device device = new Device(UUID.fromString(uuid));
				devices.add(device);
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
		return devices;
	}
}
