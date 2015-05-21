package model;

public enum ScreenStatus {
	SS_OFF, SS_ON, SS_LOCKED, SS_UNLOCKED;
	
	public static ScreenStatus getByInt(int status) {
		switch(status) {
		case 0:
			return SS_OFF;
		case 1:
			return SS_ON;
		case 2:
			return SS_LOCKED;
		case 3:
			return SS_UNLOCKED;
		default:
			throw new UnsupportedOperationException("Nieznany typ ScreenStatus: " + status);
		}
	}
}
