package calendar;

import java.util.ArrayList;
import java.util.List;

import calendar.exceptions.CalendarException;

/**
 * CSchedule class<br />
 * Manage a list of meetings and show the schedule. <br />
 * Include all information for a schedule and validation methods.
 * 
 * @author Nedias
 *
 */
public class CSchedule {

	/**
	 * The list that contains all meetings.
	 */
	private List<CMeeting> meetingList;

	/**
	 * Constructor for the CSchedule.<br />
	 * Initiates the meeting list.
	 */
	public CSchedule() {
		meetingList = new ArrayList<CMeeting>();
	}

	public void removeMeeting(CMeeting newMeeting) throws CalendarException {

		for (CMeeting meeting : meetingList) {
			if (checkIfOverlap(meeting, newMeeting)) {
				throw new CalendarException(2001);
			}
		}

		meetingList.add(newMeeting);

	}
	
	public void addMeeting(CMeeting newMeeting) throws CalendarException {

		for (CMeeting meeting : meetingList) {
			if (checkIfOverlap(meeting, newMeeting)) {
				throw new CalendarException(2001);
			}
		}

		meetingList.add(newMeeting);

	}

	private boolean checkIfOverlap(CMeeting meeting, CMeeting newMeeting) {

		if (CTime.compairTime(meeting.getStartTime(), newMeeting.getEndTime()) >= 0
				|| CTime.compairTime(meeting.getEndTime(), newMeeting.getStartTime()) >= 0) {
			return true;
		} else {
			return false;
		}

	}

	public String toString() {

		StringBuilder sb = new StringBuilder();
		int meetingNum = 0;

		for (CMeeting meeting : meetingList) {

			meetingNum++;
			int timeZone = CTime.calculateTimeZone(meeting.getLocation());
			CTime startTime = CTime.translateTime(meeting.getStartTime(), timeZone);
			CTime endTime = CTime.translateTime(meeting.getEndTime(), timeZone);

			sb.append("Meeting ").append(meetingNum).append(":").append("\n\tLocation: ")
					.append(meeting.getLocation()).append("\n\tMeeting Time(local): ")
					.append(String.format("%02d", startTime.getHour())).append(":")
					.append(String.format("%02d", startTime.getMinute())).append(" - ")
					.append(String.format("%02d", endTime.getHour())).append(":")
					.append(String.format("%02d", endTime.getMinute())).append("\n");

		}
		return sb.toString();

	}

}
