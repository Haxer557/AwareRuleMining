package dataAccess.entities;

import java.util.Date;

public final class Network extends EntityWithTime{
	private final Device device;
	private final int networkType;
	private final int networkState;
	
	public Network(Device device, Date date, int networkType, int networkState) {
		super(date);
		this.device = device;
		this.networkType = networkType;
		this.networkState = networkState;
	}

	public int getNetworkType() {
		return networkType;
	}

	public int getNetworkState() {
		return networkState;
	}

	public Device getDevice() {
		return device;
	}

	@Override
	public EntityWithTime copy() {
		return new Network(device, new Date(date.getTime()), networkType, networkState);
	}
}
