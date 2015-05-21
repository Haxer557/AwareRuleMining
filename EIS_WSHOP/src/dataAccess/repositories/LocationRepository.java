package dataAccess.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dataAccess.Context;
import dataAccess.entities.Device;
import dataAccess.entities.Location;

public class LocationRepository {
	public List<Location> getAllByDevice(Device device) {
		List<Location> locations = new LinkedList<>();
		Connection sqlConn = Context.getConnection();

		try {
			String query = 	"SELECT timestamp, double_longitude, double_latitude " +
							"FROM locations " +
							"WHERE device_id = \'" + device.getDeviceId() + "\' " +
							"AND _id % 30 = 0 " + // skip 29/30 of data
							"ORDER BY timestamp";
			ResultSet rs = sqlConn.createStatement().executeQuery(query);
			while(rs.next()) {
				Date date = new Date(rs.getLong(1));
				double longitude = rs.getDouble(2);
				double latitude = rs.getDouble(3);
				Location location = new Location(date, longitude, latitude);
				locations.add(location);
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
		return locations;
	}
	
	public double[] getLocationMinMaxes(Device device) {
		double[] minMaxes = new double[4];
		Connection sqlConn = Context.getConnection();

		try {
			String query = 	"SELECT min(double_longitude), min(double_latitude), " +
							"max(double_longitude), max(double_latitude) " +
							"FROM locations " +
							"WHERE device_id = \'" + device.getDeviceId() + "\'" +
							"ORDER BY timestamp";
			ResultSet rs = sqlConn.createStatement().executeQuery(query);
			while(rs.next()) {
				minMaxes[0] = rs.getDouble(1);
				minMaxes[1] = rs.getDouble(2);
				minMaxes[2] = rs.getDouble(3);
				minMaxes[3] = rs.getDouble(4);
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
		return minMaxes;
	}
}
