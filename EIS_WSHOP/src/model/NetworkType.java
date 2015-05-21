package model;

public enum NetworkType {
	NT_AIRPLANE, NT_WIFI, NT_BLUETOOTH, NT_GPS, NT_MOBILE, NT_WIMAX;
	
	public static NetworkType getByInt(int type) {
		switch(type) {
		case -1:
			return NT_AIRPLANE;
		case 1:
			return NT_WIFI;
		case 2:
			return NT_BLUETOOTH;
		case 3:
			return NT_GPS;
		case 4:
			return NT_MOBILE;
		case 5:
			return NT_WIMAX;
		default:
			throw new UnsupportedOperationException("nieznany NETWORK TYPE: " + type);
		}
	}
}
