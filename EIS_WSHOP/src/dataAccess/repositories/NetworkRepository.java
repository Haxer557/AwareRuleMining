package dataAccess.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dataAccess.Context;
import dataAccess.entities.Device;
import dataAccess.entities.Network;

public class NetworkRepository {
	public List<Network> getAllByDevice(Device device) {
		List<Network> nets = new LinkedList<>();
		Connection sqlConn = Context.getConnection();

		try {
			String query = 	"SELECT timestamp, network_type, network_state " +
							"FROM network " +
							"WHERE device_id = \'" + device.getDeviceId() + "\'" +
							"ORDER BY timestamp";
			ResultSet rs = sqlConn.createStatement().executeQuery(query);
			while(rs.next()) {
				long timestamp = rs.getLong(1);
				Date date = new Date(timestamp);
				int networkType = rs.getInt(2);
				int networkState = rs.getInt(3);
				Network net = new Network(device, date, networkType, networkState);
				nets.add(net);
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
		return nets;
	}
}
