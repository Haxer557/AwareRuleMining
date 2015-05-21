package dataAccess.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dataAccess.Context;
import dataAccess.entities.AudioState;
import dataAccess.entities.Device;

public class AudioStateRepository {
	public List<AudioState> getAllByDevice(Device device) {
		List<AudioState> audioStates = new LinkedList<>();
		Connection sqlConn = Context.getConnection();

		try {
			String query = 	"SELECT timestamp, ringer_mode, music, wired_headphones, bt_headphones, " +
							"volume_media, volume_media_max, volume_ringtone, volume_ringtone_max " +
							"FROM plugin_audio_state " +
							"WHERE device_id = \'" + device.getDeviceId() + "\'" +
							"ORDER BY timestamp";
			ResultSet rs = sqlConn.createStatement().executeQuery(query);
			while(rs.next()) {
				Date date = new Date(rs.getLong(1));
				int ringerMode = rs.getInt(2);
				int music = rs.getInt(3);
				int wiredHeadphones = rs.getInt(4);
				int btHeadphones = rs.getInt(5);
				int volumeMedia = rs.getInt(6);
				int volumeMediaMax = rs.getInt(7);
				int volumeRingtone = rs.getInt(8);
				int volumeRingtoneMax = rs.getInt(9);
				AudioState audioState = new AudioState(date, device, ringerMode, music, wiredHeadphones, btHeadphones, volumeMedia, volumeMediaMax, volumeRingtone, volumeRingtoneMax);
				audioStates.add(audioState);
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
		return audioStates;
	}
}
