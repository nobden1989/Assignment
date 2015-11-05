package calendar;

import calendar.exceptions.CalendarException;

public class CMeeting {

	private CTime startTime;
	private CTime endTime;
	private String location;
	private boolean enableBHCK;

	public CTime getStartTime() {
		return startTime;
	}

	public CTime getEndTime() {
		return endTime;
	}

	public String getLocation() {
		return location;
	}

	public boolean isEnableBHCK() {
		return enableBHCK;
	}

	public void setEnableBHCK(boolean enableBHCK) {
		this.enableBHCK = enableBHCK;
	}

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

	private boolean checkIfBusinessHour() {
		int timeZone = CTime.calculateTimeZone(location);
		CTime localSTime = CTime.translateTime(startTime, timeZone);
		CTime localETime = CTime.translateTime(endTime, timeZone);

		if (localSTime.getHour() > 8 && localSTime.getHour() < 16 && localETime.getHour() > 8
				&& localETime.getHour() < 16) {
			return false;
		} else {
			return true;
		}
	}

	private void validateMeeting() throws CalendarException {
		if (!checkTimeSpan(startTime, endTime)) {
			throw new CalendarException(1001);
		}

		if (enableBHCK && checkIfBusinessHour()) {
			throw new CalendarException(1002);
		}
	}

}
