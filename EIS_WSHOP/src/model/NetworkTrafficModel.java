package model;

import java.util.Date;

public final class NetworkTrafficModel extends EntityModel{
	private final TrafficValue sentData;
	private final TrafficValue receivedData;
	
	public NetworkTrafficModel(Date date, long sentDataB, long receivedDataB) {
		super(date);
		this.sentData = TrafficValue.getByBytes(sentDataB);
		this.receivedData = TrafficValue.getByBytes(receivedDataB);
	}

	public TrafficValue getSentData() {
		return sentData;
	}

	public TrafficValue getReceivedData() {
		return receivedData;
	}
	
	public static String toString(NetworkTrafficModel model) {
		if(model == null)
			return "?,?";
		return model.getSentData().toString() + "," + model.getReceivedData().toString();
	}
}
