package calendar.statics;

public class ErrorCode {
	private final static String ERR_0000 = "Unkown Error.";
	private final static String ERR_1010 = "Start time or end time is null object.";
	private final static String ERR_1020 = "Start time must before end time.";
	private final static String ERR_1030 = "The meeting must shorter than an hour.";
	private final static String ERR_1040 = "Start time and end time must within the business hour.";
	private final static String ERR_2010 = "Meeting time overlaped!";

	public static String getErrorMessage(int errorCode) {
		String eMsg;
		switch (errorCode) {
		case 1010:
			eMsg = ERR_1010;
			break;
		case 1020:
			eMsg = ERR_1020;
			break;
		case 1030:
			eMsg = ERR_1030;
			break;
		case 1040:
			eMsg = ERR_1040;
			break;
		case 2010:
			eMsg = ERR_2010;
			break;
		default:
			eMsg = ERR_0000;
			break;
		}

		return eMsg;
	}

}
