package logic;

public enum DayOfWeek {
	DOW_MONDAY(0), DOW_TUESDAY(1), DOW_WEDNESDAY(2), DOW_THURSDAY(3), DOW_FRIDAY(4), DOW_SATURDAY(5), DOW_SUNDAY(6);
	
	private final int value;
    private DayOfWeek(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static DayOfWeek intToDayOfWeek(int i) {
    	switch (i) {
		case 0:
			return DOW_MONDAY;
		case 1:
			return DOW_TUESDAY;
		case 2:
			return DOW_WEDNESDAY;
		case 3:
			return DOW_THURSDAY;
		case 4:
			return DOW_FRIDAY;
		case 5:
			return DOW_SATURDAY;
		case 6:
			return DOW_SUNDAY;
		default:
			return null;
		}
    }
}
