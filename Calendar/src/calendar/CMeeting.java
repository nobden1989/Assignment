package calendar;

import calendar.exceptions.CalendarException;

/**
 * CMeeting class<br />
 * Manage start time, end time and location of a certain meeting.<br />
 * Include Meeting time and all the validation methods.
 * 
 * @author Nedias
 * 
 */
public class CMeeting {

	private static int id = 0;
	/**
	 * The ID that identify a specific meeting.
	 */
	private int meetingID;
	/**
	 * The name or topic of the meeting.
	 */
	private String meetingName;

	/**
	 * The start time of the meeting.
	 */
	private CTime startTime;
	/**
	 * The end time of the meeting
	 */
	private CTime endTime;
	/**
	 * The location where the meeting will be held.
	 */
	private String location;
	/**
	 * Whether business hour will be checked.
	 */
	private boolean enableBHCK;

	/**
	 * Getter for meeting name.
	 * 
	 * @return meeting name
	 */
	public String getMeetingName() {
		return meetingName;
	}

	/**
	 * Setter for meeting name.
	 * 
	 * @param meeting
	 *            name
	 */
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	/**
	 * Getter for meeting id.
	 * 
	 * @return meeting id.
	 */
	public int getMeetingID() {
		return meetingID;
	}

	/**
	 * Getter for start time.
	 * 
	 * @return start time
	 */
	public CTime getStartTime() {
		return startTime;
	}

	/**
	 * Getter for end time.
	 * 
	 * @return end time
	 */
	public CTime getEndTime() {
		return endTime;
	}

	/**
	 * Getter for location.
	 * 
	 * @return location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Getter for business hour check flag.
	 * 
	 * @return true:check business hour; false: no business hour check.
	 */
	public boolean isEnableBHCK() {
		return enableBHCK;
	}

	/**
	 * Setter for business hour check.
	 * 
	 * @param true:check
	 *            business hour; false: no business hour check.
	 */
	public void setEnableBHCK(boolean enableBHCK) {
		this.enableBHCK = enableBHCK;
	}

	/**
	 * Constructor for CMeeting class
	 * 
	 * @param Start
	 *            time
	 * @param End
	 *            time
	 * @param Location
	 * @throws CalendarException
	 */
	public CMeeting(CTime startTime, CTime endTime, String location) throws CalendarException {
		this(startTime, endTime, location, false);
	}

	/**
	 * Constructor for CMeeting class, included setting business hour check
	 * flag.
	 * 
	 * @param Start
	 *            time
	 * @param End
	 *            time
	 * @param Location
	 * @param true:
	 *            check business hour¡£ ¡¶br /¡·false: no business hour check.
	 * @throws CalendarException
	 */
	public CMeeting(CTime startTime, CTime endTime, String location, boolean enableBHCK)
			throws CalendarException {
		id++;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
		this.enableBHCK = enableBHCK;
		this.meetingID = id;
		validateMeeting();
	}

	/**
	 * Check if start time is before end time.
	 * 
	 * @param startTime
	 * @param endTime
	 * @return true:start time is before end time. <br />
	 *         false: start time is after end time.
	 */
	private boolean checkTimeSpan(CTime startTime, CTime endTime) {
		return CTime.compairTime(startTime, endTime) < 0;
	}

	/**
	 * Check if start time and end time is within business hour.
	 * 
	 * @return true:start time and end time is within business hour.<br />
	 *         false: start time and end time is out of business hour.
	 */
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

	/**
	 * Check if the meeting is valid, include all the necessary checks
	 * 
	 * @throws CalendarException
	 *             include error code and error message.
	 */
	private void validateMeeting() throws CalendarException {
		if (startTime == null || endTime == null) {
			throw new CalendarException(1001);
		}

		if (!checkTimeSpan(startTime, endTime)) {
			throw new CalendarException(1002);
		}

		if (enableBHCK && checkIfBusinessHour()) {
			throw new CalendarException(1003);
		}
	}

}
