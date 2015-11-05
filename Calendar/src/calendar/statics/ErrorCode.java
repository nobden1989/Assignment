package calendar.statics;

public class ErrorCode {
	private final static String ERR_0000 = "Unkown Error.";
	private final static String ERR_1001 = "Start time must before end time.";
	private final static String ERR_1002 = "Start time and end time must within the business hour.";

	public static String getErrorMessage(int errorCode) {
		String eMsg;
		switch (errorCode) {
		case 1001:
			eMsg = ERR_1001;
		case 1002:
			eMsg = ERR_1002;
		default:
			eMsg = ERR_0000;
		}

		return eMsg;
	}

}
