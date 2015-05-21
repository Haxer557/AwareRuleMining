package model;

import java.util.Date;

public class NetworkModel extends EntityModel {
	private final boolean networkEnabled;
	private final NetworkType networkType;
	
	public NetworkModel(Date date, boolean networkEnabled, NetworkType networkType) {
		super(date);
		this.networkEnabled = networkEnabled;
		this.networkType = networkType;
	}

	public boolean isNetworkEnabled() {
		return networkEnabled;
	}

	public NetworkType getNetworkType() {
		return networkType;
	}
	
	public static String toString(NetworkModel model) {
		if(model == null)
			return "?,?";
		return (model.isNetworkEnabled() ? "TRUE" : "FALSE") + "," +
				model.getNetworkType().toString();
	}
}
