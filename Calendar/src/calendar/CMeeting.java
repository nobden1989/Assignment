package calendar;

public class CMeeting {

	private CTime startTime;
	private CTime endTime;
	private String location;
	private boolean enableBHCK;

	public CMeeting(CTime startTime, CTime endTime, String location) throws Exception {
		this(startTime, endTime, location, false);
	}

	public CMeeting(CTime startTime, CTime endTime, String location, boolean enableBHCK) throws Exception {
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
		this.enableBHCK = enableBHCK;
		validateMeeting();
	}

	private boolean checkTimeSpan(CTime startTime, CTime endTime) {
		return CTime.compairTime(startTime, endTime) < 0;
	}

	private boolean checkBusinessHour() {
		int timeZone = calculateTimeZone(location);
		CTime localSTime = CTime.translateTime(startTime, timeZone);
		CTime localETime = CTime.translateTime(endTime, timeZone);

		if (localSTime.getHour() > 8 && localETime.getHour() < 5) {
			return true;
		} else {
			return false;
		}
	}

	private int calculateTimeZone(String location) {
		// TODO:Calculate Time Zone from location.
		return 0;
	}

	private void validateMeeting() throws Exception {
		if (!checkTimeSpan(startTime, endTime)) {
			throw new Exception("Start time must before end time.");
		}

		if (enableBHCK && checkBusinessHour()) {
			throw new Exception("Start time and end time must within the business hour.");
		}
	}

}
