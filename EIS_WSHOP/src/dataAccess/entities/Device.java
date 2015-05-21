package dataAccess.entities;

import java.util.UUID;

public final class Device {
	private final UUID deviceId;

	public Device (UUID deviceId) {
		this.deviceId = deviceId;
	}
	
	public UUID getDeviceId() {
		return deviceId;
	}
}
