package calendar;

import calendar.exceptions.CalendarException;
import calendar.statics.ErrorCode;

public class CMeeting {

	private CTime startTime;
	private CTime endTime;
	private String location;
	private boolean enableBHCK;

	public CMeeting(CTime startTime, CTime endTime, String location) throws CalendarException {
		this(startTime, endTime, location, false);
	}

	public CMeeting(CTime startTime, CTime endTime, String location, boolean enableBHCK) throws CalendarException {
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

		if (localSTime.getHour() > 8 && localSTime.getHour() < 16 && localETime.getHour() > 8
				&& localETime.getHour() < 16) {
			return true;
		} else {
			return false;
		}
	}

	private int calculateTimeZone(String location) {
		// TODO:Calculate Time Zone from location.
		return 0;
	}

	private void validateMeeting() throws CalendarException {
		if (!checkTimeSpan(startTime, endTime)) {
			throw new CalendarException(1001);
		}
		
		boolean result = checkBusinessHour();

		if (enableBHCK && checkBusinessHour() == false) {
			throw new CalendarException(1002);
		}
	}

}
