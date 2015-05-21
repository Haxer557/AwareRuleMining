package model;

public enum RingerMode {
	RM_NORMAL, RM_SILENT, RM_VIBRATE;
	
	public static RingerMode getByInt(int mode) {
		switch(mode) {
		case 0:
			return RM_SILENT;
		case 1:
			return RM_VIBRATE;
		case 2:
			return RM_NORMAL;
		default:
			throw new UnsupportedOperationException("niepoprawne dane RingerMode");	
		}
	}
}
