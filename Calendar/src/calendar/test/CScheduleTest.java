package calendar.test;

import static org.junit.Assert.*;

import org.junit.Test;

import calendar.CMeeting;
import calendar.CSchedule;
import calendar.CTime;
import calendar.exceptions.CalendarException;
import calendar.statics.ErrorCode;

public class CScheduleTest {

	@Test
	public void testAddMeeting() {
		try {
			CSchedule schedule = new CSchedule();

			CTime startTime1 = new CTime(8, 0, -5);
			CTime endTime1 = new CTime(9, 30, -5);

			CTime startTime2 = new CTime(8, 0, -2);
			CTime endTime2 = new CTime(9, 30, -2);
			
			CTime startTime3 = new CTime(8, 0, 2);
			CTime endTime3 = new CTime(9, 30, 2);

			CMeeting meeting1 = new CMeeting(startTime1, endTime1, "Windsor");
			CMeeting meeting2 = new CMeeting(startTime2, endTime2, "London");
			CMeeting meeting3 = new CMeeting(startTime3, endTime3, "Berlin");

			schedule.addMeeting(meeting1);
			schedule.addMeeting(meeting2);
			schedule.addMeeting(meeting3);

			System.out.println(schedule);

		} catch (CalendarException e) {
			fail("Unexpected error!");
		}

		try {

			CSchedule schedule = new CSchedule();

			CTime startTime1 = new CTime(10, 0, -5);
			CTime endTime1 = new CTime(13, 30, -5);

			CTime startTime2 = new CTime(8, 0, -2);
			CTime endTime2 = new CTime(10, 30, -2);

			CTime startTime3 = new CTime(8, 0, 2);
			CTime endTime3 = new CTime(9, 30, 2);

			CMeeting meeting1 = new CMeeting(startTime1, endTime1, "Windsor");
			CMeeting meeting2 = new CMeeting(startTime2, endTime2, "London");
			CMeeting meeting3 = new CMeeting(startTime3, endTime3, "Berlin");

			schedule.addMeeting(meeting1);
			schedule.addMeeting(meeting2);
			fail("Expect error!");
			schedule.addMeeting(meeting3);

			
			System.out.println(schedule);

		} catch (CalendarException e) {
			assertEquals(2001, e.getErrorCode());
			assertEquals(ErrorCode.getErrorMessage(2001), e.getErrorMessage());
		}
		
		try {

			CSchedule schedule = new CSchedule();

			CTime startTime1 = new CTime(8, 0, -5);
			CTime endTime1 = new CTime(10, 30, -5);

			CTime startTime2 = new CTime(6, 0, -2);
			CTime endTime2 = new CTime(12, 30, -2);

			CTime startTime3 = new CTime(8, 0, 2);
			CTime endTime3 = new CTime(9, 30, 2);

			CMeeting meeting1 = new CMeeting(startTime1, endTime1, "Windsor");
			CMeeting meeting2 = new CMeeting(startTime2, endTime2, "London");
			CMeeting meeting3 = new CMeeting(startTime3, endTime3, "Berlin");

			schedule.addMeeting(meeting1);
			schedule.addMeeting(meeting3);
			schedule.addMeeting(meeting2);
			fail("Expect error!");
			
			
			System.out.println(schedule);

		} catch (CalendarException e) {
			assertEquals(2001, e.getErrorCode());
			assertEquals(ErrorCode.getErrorMessage(2001), e.getErrorMessage());
		}
		

	}

	@Test
	public void testToString() {

		CSchedule schedule = new CSchedule();

		CTime startTime1 = new CTime(8, 0, -5);
		CTime endTime1 = new CTime(9, 30, -5);

		CTime startTime2 = new CTime(8, 0, -2);
		CTime endTime2 = new CTime(9, 30, -2);
		try {
			CMeeting meeting1 = new CMeeting(startTime1, endTime1, "Windsor");
			CMeeting meeting2 = new CMeeting(startTime2, endTime2, "London");

			schedule.addMeeting(meeting1);
			schedule.addMeeting(meeting2);

		} catch (CalendarException e) {
			fail("Unexpected error!");
		}
		String expectResult = "Meeting 1:\n\tLocation: Windsor\n\tMeeting Time(local):"
				+ " 08:00 - 09:30\nMeeting 2:\n\tLocation: London\n\tMeeting Time(local): 06:00 - 07:30\n";

		assertEquals(expectResult, schedule.toString());
	}

}
