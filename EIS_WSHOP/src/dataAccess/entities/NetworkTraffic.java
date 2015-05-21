package dataAccess.entities;

import java.util.Date;

public final class NetworkTraffic extends EntityWithTime {
	private final Device device;
	private final long dataReceivedB;
	private final long dataSentB;
	
	public NetworkTraffic(Device device, Date date, long dataReceivedB, long dataSentB) {
		super(date);
		this.device = device;
		this.dataReceivedB = dataReceivedB;
		this.dataSentB = dataSentB;
	}

	public Device getDevice() {
		return device;
	}

	public long getDataReceivedB() {
		return dataReceivedB;
	}

	public long getDataSentB() {
		return dataSentB;
	}

	@Override
	public EntityWithTime copy() {
		return new NetworkTraffic(device, new Date(date.getTime()), dataReceivedB, dataSentB);
	}
}
