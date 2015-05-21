package dataAccess.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import dataAccess.Context;
import dataAccess.entities.Device;
import dataAccess.entities.NetworkTraffic;

public class NetworkTrafficRepository {
	public List<NetworkTraffic> getAllByDevice(Device device) {
		List<NetworkTraffic> nts = new LinkedList<>();
		Connection sqlConn = Context.getConnection();

		try {
			String query = 	"SELECT timestamp, double_received_bytes, double_sent_bytes " +
							"FROM network_traffic " +
							"WHERE device_id = \'" + device.getDeviceId() + "\'" +
							"ORDER BY timestamp";
			ResultSet rs = sqlConn.createStatement().executeQuery(query);
			while(rs.next()) {
				long timestamp = rs.getLong(1);
				Date date = new Date(timestamp);
				long bytes_received = rs.getLong(2);
				long bytes_sent = rs.getLong(3);
				NetworkTraffic nt = new NetworkTraffic(device, date, bytes_received, bytes_sent);
				nts.add(nt);
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
		return nts;
	}
}
