package calendar;

/**
 * CTime class Include all the information to define time and all until and
 * functionalities for calculate time.
 * 
 * @author Nedias
 *
 */
public class CTime {

	/**
	 * Standar timezone (Greenwich Mean Time)
	 */
	public final static int STANDER_TIME = 0;
	/**
	 * The day changes due to time zone changes.<br />
	 * Positive: plus day(Go across the dayline) <br />
	 * Negative: minus day(Go backwards the dayline)
	 */
	private int dayPlus;
	/**
	 * Hour in 24-hour system (0-23)
	 */
	private int hour;
	/**
	 * Minute: 0-59.
	 */
	private int minute;
	/**
	 * Time zone for current CTime class.
	 */
	private int timeZone;
	/**
	 * Enable day light saving time.
	 */
	private boolean enableDST = false;

	/**
	 * Getter for day plus.
	 * 
	 * @return dayplus
	 */
	public int getDayPlus() {
		return dayPlus;
	}

	/**
	 * Getter for hour.
	 * 
	 * @return hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Getter for minute.
	 * 
	 * @return minute
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Getter for time zone.
	 * 
	 * @return time zone
	 */
	public int getTimeZone() {
		return timeZone;
	}

	/**
	 * Getter for whether day light saving time is enabled.
	 * 
	 * @return true: enable day light saving time.<br />
	 *         false: disable day light saving time.
	 */
	public boolean isEnableDST() {
		return enableDST;
	}

	/**
	 * Setter for whether day light saving time is enabled.<br />
	 * true: enable day light saving time.<br />
	 * false: disable day light saving time.
	 */
	public void setEnableDST(boolean enableDST) {
		this.enableDST = enableDST;
	}

	/**
	 * Constructor for CTime class, initialize all data with 0.
	 */
	public CTime() {
		this.dayPlus = 0;
		this.hour = 0;
		this.minute = 0;
		this.timeZone = STANDER_TIME;
	}

	/**
	 * Constructor for CTime class, include initialize hour, minute and time
	 * zone.
	 */
	public CTime(int hour, int minute, int timeZone) {
		this.dayPlus = 0;
		this.hour = validateHour(hour);
		this.minute = validateMinute(minute);
		this.timeZone = validateTimeZone(timeZone);
	}

	/**
	 * Constructor for CTime class, include initialize hour, minute ,time zone
	 * and day light saving time flag.
	 */
	public CTime(int hour, int minute, int timeZone, boolean enableDST) {
		this(hour, minute, timeZone);
		this.enableDST = enableDST;
	}

	/**
	 * Get greenwich standar time from CTime.
	 * 
	 * @param LocalTime
	 * @return Greenwich standar time.
	 */
	public static CTime getStandarTime(CTime localTime) {
		return translateTime(localTime, STANDER_TIME);
	}

	/**
	 * Compare two CTimes. Consider time zone issue and day plus issue.
	 * 
	 * @param fromTime
	 * @param toTime
	 * @return -1: fromTime is before the toTime.<br />
	 *         0: fromTime is the same to toTime.<br />
	 *         1: fromTIme is after the toTime.
	 */
	public static int compairTime(CTime fromTime, CTime toTime) {
		CTime stdFTime = getStandarTime(fromTime);
		CTime stdTTime = getStandarTime(toTime);
		if (stdFTime.dayPlus > stdTTime.dayPlus) {
			return 1;
		} else if (stdFTime.dayPlus < stdTTime.dayPlus) {
			return -1;
		}

		if (stdFTime.getHour() > stdTTime.getHour()) {
			return 1;
		} else if (stdFTime.getHour() < stdTTime.getHour()) {
			return -1;
		}

		if (stdFTime.getMinute() > stdTTime.minute) {
			return 1;
		} else if (stdFTime.getMinute() < stdTTime.getMinute()) {
			return -1;
		} else {
			return 0;
		}

	}

	/**
	 * Translate one CTime to other time zone.
	 * 
	 * @param orgTime
	 *            original CTime
	 * @param destTimezone
	 *            destination time zone
	 * @return CTime in destination time zone
	 */
	public static CTime translateTime(CTime orgTime, int destTimezone) {
		// TODO:DST support needed.
		int hourSpan;

		if (orgTime.enableDST) {
			hourSpan = orgTime.getHour() + orgTime.getTimeZone() - destTimezone;
		} else {
			hourSpan = orgTime.getHour() + orgTime.getTimeZone() - destTimezone;
		}

		int dayPlus = hourSpan / 24;

		if (hourSpan < 0) {
			dayPlus--;
		}

		CTime resultTime = new CTime(validateHour(hourSpan), orgTime.getMinute(), destTimezone);
		resultTime.dayPlus = dayPlus;
		return resultTime;
	}

	/**
	 * Calculate hour after certain time span.
	 * 
	 * @param startHour
	 * @param timeSpan
	 * @return The time after the span.
	 */
	public static int calculateHour(int startHour, int timeSpan) {
		int resultTime = validateHour(startHour) + validateHour(timeSpan);
		return validateHour(resultTime);
	}

	/**
	 * Make sure that the hour is with in 0-23.<br />
	 * If hour goes beyond 23, it will start from 0, when below 0, start from
	 * 23.
	 * 
	 * @param hour
	 * @return valid hour
	 */
	public static int validateHour(int hour) {
		int resultHour = hour % 24;
		if (resultHour < 0) {
			resultHour += 24;
		}
		return resultHour;
	}

	/**
	 * Make sure that the minute is with in 0-59.<br />
	 * If hour goes beyond 59 it will start from 0, when below 0, start from 59.
	 * 
	 * @param minute
	 * @return valid minute
	 */
	public static int validateMinute(int minute) {
		int resultMinute = minute % 60;
		if (resultMinute < 0) {
			resultMinute += 60;
		}
		return resultMinute;
	}

	/**
	 * Translate from Name to time zone. (Not completed, only a demonstration)
	 * 
	 * @param location
	 *            the name of the location
	 * @return time zone
	 */
	public static int calculateTimeZone(String location) {
		// TODO:Calculate Time Zone from location.
		switch (location) {
		case "Windsor":
			return -5;
		case "London":
			return 0;
		case "Berlin":
			return 2;
		}
		return 0;
	}

	/**
	 * Make sure the time zone is within -12 to 14.<br />
	 * If time zone goes beyond 14, it will stay on 14, when below -12, it will
	 * stay on -12.
	 * 
	 * @param timeZone
	 * @return valid time zone
	 */
	public static int validateTimeZone(int timeZone) {
		if (timeZone < -12)
			return -12;
		else if (timeZone > 14)
			return 14;
		else
			return timeZone;
	}
}
