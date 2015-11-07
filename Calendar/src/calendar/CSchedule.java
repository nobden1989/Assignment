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

	/**
	 * Search meeting by ID.
	 * 
	 * @param meetingID
	 * @return CMeeting the meeting specified by ID.
	 */
	public CMeeting searchMeeting(int meetingID) {

		for (CMeeting meeting : meetingList) {
			if (meeting.getMeetingID() == meetingID) {
				return meeting;
			}
		}

		return null;
	}

	/**
	 * Search meetings by meeting name or topic.
	 * 
	 * @param meetingName
	 * @return CMeeting the meetings specified by name or topic.
	 */
	public List<CMeeting> searchMeetings(String meetingName) {
		List<CMeeting> resultList = new ArrayList<CMeeting>();
		for (CMeeting meeting : meetingList) {
			if (meeting.getMeetingName() != null && meeting.getMeetingName().equals(meetingName)) {
				resultList.add(meeting);
			}
		}
		return resultList;
	}

	/**
	 * Remove a meeting from the meeting list by ID.
	 * 
	 * @param meetingID
	 * @return whether the meeting has been removed.
	 */
	public boolean removeMeeting(int meetingID) {
		return meetingList.remove(this.searchMeeting(meetingID));
	}

	/**
	 * Remove meetings from the meeting list by name or topic.
	 * 
	 * @param meetingName
	 * @return whether meetings has been removed.
	 */
	public boolean removeMeetings(String meetingName) {
		return meetingList.removeAll(this.searchMeetings(meetingName));
	}

	/**
	 * Add a meeting to the meeting list.
	 * 
	 * @param newMeeting
	 * @throws CalendarException
	 *             the new meeting is overlapped.
	 */
	public boolean addMeeting(CMeeting newMeeting) throws CalendarException {

		if (newMeeting == null) {
			return false;
		}

		for (CMeeting meeting : meetingList) {
			if (checkIfOverlap(meeting, newMeeting)) {
				throw new CalendarException(2010);
			}
		}
		return meetingList.add(newMeeting);
	}

	/**
	 * Check whether two meetings are overlapped.
	 * 
	 * @param meeting
	 * @param newMeeting
	 * @return true: two meeting is overlapped<br />
	 *         false: two meeting is not overlapped.
	 */
	private boolean checkIfOverlap(CMeeting meeting, CMeeting newMeeting) {

		if (newMeeting == null || meeting == null) {
			return false;
		}

		if (CTime.compairTime(meeting.getStartTime(), newMeeting.getEndTime()) >= 0
				|| CTime.compairTime(meeting.getEndTime(), newMeeting.getStartTime()) >= 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Override the toString method of Object.<br />
	 * Print all the meetings on the Schedule.
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		int meetingNum = 0;

		for (CMeeting meeting : meetingList) {

			meetingNum++;
			int timeZone = CTime.calculateTimeZone(meeting.getLocation());
			CTime startTime = CTime.translateTime(meeting.getStartTime(), timeZone);
			CTime endTime = CTime.translateTime(meeting.getEndTime(), timeZone);

			sb.append("Meeting ").append(meetingNum).append(":").append("\n\tLocation: ").append(meeting.getLocation())
					.append("\n\tMeeting Time(local): ").append(String.format("%02d", startTime.getHour())).append(":")
					.append(String.format("%02d", startTime.getMinute())).append(" - ")
					.append(String.format("%02d", endTime.getHour())).append(":")
					.append(String.format("%02d", endTime.getMinute())).append("\n");

		}
		return sb.toString();

	}

}