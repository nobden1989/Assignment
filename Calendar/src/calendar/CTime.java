package calendar;

public class CTime {

	public final static int STANDER_TIME = 0;

	private int dayPlus;
	private int hour;
	private int minute;
	private int timeZone;
	private boolean enableDST = false;

	public int getDayPlus() {
		return dayPlus;
	}

	public void setDayPlus(int dayPlus) {
		this.dayPlus = dayPlus;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(int timeZone) {
		this.timeZone = timeZone;
	}

	public boolean isEnableDST() {
		return enableDST;
	}

	public void setEnableDST(boolean enableDST) {
		this.enableDST = enableDST;
	}

	public CTime() {
		this.dayPlus = 0;
		this.hour = 0;
		this.minute = 0;
		this.timeZone = STANDER_TIME;
	}

	public CTime(int hour, int minute, int timeZone) {
		this.dayPlus = 0;
		this.hour = validateHour(hour);
		this.minute = validateMinute(minute);
		this.timeZone = validateTimeZone(timeZone);
	}

	public CTime(int hour, int minute, int timeZone, boolean enableDST) {
		this(hour, minute, timeZone);
		this.enableDST = enableDST;
	}

	public static CTime getStandarTime(CTime localTime) {
		return translateTime(localTime, STANDER_TIME);
	}

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

	// TODO:DST support needed.
	public static CTime translateTime(CTime orgTime, int destTimezone) {

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
		resultTime.setDayPlus(dayPlus);
		return resultTime;
	}

	public static int calculateHour(int startHour, int timeSpan) {
		int resultTime = validateHour(startHour) + validateHour(timeSpan);
		return validateHour(resultTime);
	}

	public static int validateHour(int hour) {
		int resultHour = hour % 24;
		if (resultHour < 0) {
			resultHour += 24;
		}
		return resultHour;
	}

	public static int validateMinute(int minute) {
		int resultMinute = minute % 60;
		if (resultMinute < 0) {
			resultMinute += 60;
		}
		return resultMinute;
	}

	public static int validateTimeZone(int timeZone) {
		if (timeZone < -12)
			return -12;
		else if (timeZone > 14)
			return 14;
		else
			return timeZone;
	}
}
