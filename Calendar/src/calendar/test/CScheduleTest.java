package calendar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import calendar.CMeeting;
import calendar.CSchedule;
import calendar.CTime;
import calendar.exceptions.CalendarException;
import calendar.statics.ErrorCode;

/**
 * @author Federica, Li
 *
 */
public class CScheduleTest {

	@Test
	public void testAddMeeting() {

		try {

			// Prepare data
			CMeeting meeting1 = new CMeeting(new CTime(9, 30, 5), new CTime(10, 0, 5), "Location 1");
			meeting1.setMeetingID(10001);
			CMeeting meeting2 = new CMeeting(new CTime(10, 30, 5), new CTime(11, 0, 5), "Location 2");
			meeting2.setMeetingID(10002);
			CMeeting meeting3 = new CMeeting(new CTime(11, 30, 5), new CTime(12, 0, 5), "Location 3");
			meeting3.setMeetingID(10003);
			CMeeting meeting4 = new CMeeting(new CTime(10, 50, 5), new CTime(11, 40, 5), "Location 4");
			meeting4.setMeetingID(10004);

			// Case 1
			CSchedule schedule = new CSchedule();

			try {
				assertEquals(false, schedule.addMeeting(null));
			} catch (CalendarException e) {
				fail("Unexpected error!");
			}

			// Case 2
			schedule = new CSchedule();

			try {
				assertEquals(true, schedule.addMeeting(meeting1));
				System.out.println(schedule);
			} catch (CalendarException e) {
				fail("Unexpected error!");
			}

			// Case 3
			schedule = new CSchedule();

			try {
				schedule.addMeeting(meeting1);
				schedule.addMeeting(meeting2);
				assertEquals(true, schedule.addMeeting(meeting3));
				System.out.println(schedule);
			} catch (CalendarException e) {
				fail("Unexpected error!");
			}

			// Case 4
			schedule = new CSchedule();

			try {
				schedule.addMeeting(meeting1);
				schedule.addMeeting(meeting2);
				assertEquals(true, schedule.addMeeting(meeting4));
				fail("Expected error but not occur!");
			} catch (CalendarException e) {
				assertEquals(2010, e.getErrorCode());
				assertEquals(ErrorCode.getErrorMessage(2010), e.getErrorMessage());
			}

		} catch (CalendarException e1) {
			fail("Unexpected error!");
		}
	}

	@Test
	public void testSearchMeeting() {

		try {

			// Prepare data
			CMeeting meeting1 = new CMeeting(new CTime(9, 30, 5), new CTime(10, 0, 5), "Location 1");
			meeting1.setMeetingID(10001);
			CMeeting meeting2 = new CMeeting(new CTime(10, 30, 5), new CTime(11, 0, 5), "Location 2");
			meeting2.setMeetingID(10002);
			CMeeting meeting3 = new CMeeting(new CTime(11, 30, 5), new CTime(12, 0, 5), "Location 3");
			meeting3.setMeetingID(10003);
			CMeeting meeting4 = new CMeeting(new CTime(10, 50, 5), new CTime(11, 40, 5), "Location 4");
			meeting4.setMeetingID(10004);

			// Case 5
			CSchedule schedule = new CSchedule();

			assertEquals(null, schedule.searchMeeting(10001));

			// Case 6
			schedule = new CSchedule();

			try {
				schedule.addMeeting(meeting1);
				schedule.addMeeting(meeting2);
				schedule.addMeeting(meeting3);
			} catch (CalendarException e1) {
				fail("Unexpected error!");
			}

			assertEquals(null, schedule.searchMeeting(10004));

			// Case 7
			schedule = new CSchedule();

			try {
				schedule.addMeeting(meeting1);
				schedule.addMeeting(meeting2);
				schedule.addMeeting(meeting3);
			} catch (CalendarException e1) {
				fail("Unexpected error!");
			}

			assertEquals(meeting2, schedule.searchMeeting(10002));

		} catch (CalendarException e1) {
			fail("Unexpected error!");
		}
	}

	@Test
	public void testRemoveMeeting() {

		try {

			// Prepare data
			CMeeting meeting1 = new CMeeting(new CTime(9, 30, 5), new CTime(10, 0, 5), "Location 1");
			meeting1.setMeetingID(10001);
			CMeeting meeting2 = new CMeeting(new CTime(10, 30, 5), new CTime(11, 0, 5), "Location 2");
			meeting2.setMeetingID(10002);
			CMeeting meeting3 = new CMeeting(new CTime(11, 30, 5), new CTime(12, 0, 5), "Location 3");
			meeting3.setMeetingID(10003);
			CMeeting meeting4 = new CMeeting(new CTime(10, 50, 5), new CTime(11, 40, 5), "Location 4");
			meeting4.setMeetingID(10004);

			// Case 8
			CSchedule schedule = new CSchedule();

			assertEquals(false, schedule.removeMeeting(10001));

			// Case 9
			schedule = new CSchedule();

			try {
				schedule.addMeeting(meeting1);
				schedule.addMeeting(meeting2);
				schedule.addMeeting(meeting3);
			} catch (CalendarException e1) {
				fail("Unexpected error!");
			}

			assertEquals(false, schedule.removeMeeting(10004));

			// Case 10
			schedule = new CSchedule();
			CSchedule schedule2 = new CSchedule();

			try {
				schedule.addMeeting(meeting1);
				schedule.addMeeting(meeting2);
				schedule.addMeeting(meeting3);

				schedule2.addMeeting(meeting1);
				schedule2.addMeeting(meeting3);

			} catch (CalendarException e1) {
				fail("Unexpected error!");
			}

			assertEquals(true, schedule.removeMeeting(10002));
			assertEquals(schedule2.toString(), schedule.toString());

		} catch (CalendarException e1) {
			fail("Unexpected error!");
		}
	}

	// EXTENTION TEST
	/*
	 * @Test public void testAddMeeting_EX() { try { CSchedule schedule = new
	 * CSchedule();
	 * 
	 * CTime startTime1 = new CTime(8, 0, -5); CTime endTime1 = new CTime(9, 30,
	 * -5);
	 * 
	 * CTime startTime2 = new CTime(8, 0, -2); CTime endTime2 = new CTime(9, 30,
	 * -2);
	 * 
	 * CTime startTime3 = new CTime(8, 0, 2); CTime endTime3 = new CTime(9, 30,
	 * 2);
	 * 
	 * CMeeting meeting1 = new CMeeting(startTime1, endTime1, "Windsor");
	 * CMeeting meeting2 = new CMeeting(startTime2, endTime2, "London");
	 * CMeeting meeting3 = new CMeeting(startTime3, endTime3, "Berlin");
	 * 
	 * schedule.addMeeting(meeting1); schedule.addMeeting(meeting2);
	 * schedule.addMeeting(meeting3);
	 * 
	 * System.out.println(schedule);
	 * 
	 * } catch (CalendarException e) { fail("Unexpected error!"); }
	 * 
	 * try {
	 * 
	 * CSchedule schedule = new CSchedule();
	 * 
	 * CTime startTime1 = new CTime(10, 0, -5); CTime endTime1 = new CTime(13,
	 * 30, -5);
	 * 
	 * CTime startTime2 = new CTime(8, 0, -2); CTime endTime2 = new CTime(10,
	 * 30, -2);
	 * 
	 * CTime startTime3 = new CTime(8, 0, 2); CTime endTime3 = new CTime(9, 30,
	 * 2);
	 * 
	 * CMeeting meeting1 = new CMeeting(startTime1, endTime1, "Windsor");
	 * CMeeting meeting2 = new CMeeting(startTime2, endTime2, "London");
	 * CMeeting meeting3 = new CMeeting(startTime3, endTime3, "Berlin");
	 * 
	 * schedule.addMeeting(meeting1); schedule.addMeeting(meeting2); fail(
	 * "Expect error!"); schedule.addMeeting(meeting3);
	 * 
	 * System.out.println(schedule);
	 * 
	 * } catch (CalendarException e) { assertEquals(2010, e.getErrorCode());
	 * assertEquals(ErrorCode.getErrorMessage(2010), e.getErrorMessage()); }
	 * 
	 * try {
	 * 
	 * CSchedule schedule = new CSchedule();
	 * 
	 * CTime startTime1 = new CTime(8, 0, -5); CTime endTime1 = new CTime(10,
	 * 30, -5);
	 * 
	 * CTime startTime2 = new CTime(6, 0, -2); CTime endTime2 = new CTime(12,
	 * 30, -2);
	 * 
	 * CTime startTime3 = new CTime(8, 0, 2); CTime endTime3 = new CTime(9, 30,
	 * 2);
	 * 
	 * CMeeting meeting1 = new CMeeting(startTime1, endTime1, "Windsor");
	 * CMeeting meeting2 = new CMeeting(startTime2, endTime2, "London");
	 * CMeeting meeting3 = new CMeeting(startTime3, endTime3, "Berlin");
	 * 
	 * schedule.addMeeting(meeting1); schedule.addMeeting(meeting3);
	 * schedule.addMeeting(meeting2); fail("Expect error!");
	 * 
	 * System.out.println(schedule);
	 * 
	 * } catch (CalendarException e) { assertEquals(2001, e.getErrorCode());
	 * assertEquals(ErrorCode.getErrorMessage(2001), e.getErrorMessage()); }
	 * 
	 * }
	 * 
	 * @Test public void testToString() {
	 * 
	 * CSchedule schedule = new CSchedule();
	 * 
	 * CTime startTime1 = new CTime(8, 0, -5); CTime endTime1 = new CTime(9, 30,
	 * -5);
	 * 
	 * CTime startTime2 = new CTime(8, 0, -2); CTime endTime2 = new CTime(9, 30,
	 * -2); try { CMeeting meeting1 = new CMeeting(startTime1, endTime1,
	 * "Windsor"); CMeeting meeting2 = new CMeeting(startTime2, endTime2,
	 * "London");
	 * 
	 * schedule.addMeeting(meeting1); schedule.addMeeting(meeting2);
	 * 
	 * } catch (CalendarException e) { fail("Unexpected error!"); } String
	 * expectResult = "Meeting 1:\n\tLocation: Windsor\n\tMeeting Time(local):"
	 * +
	 * " 08:00 - 09:30\nMeeting 2:\n\tLocation: London\n\tMeeting Time(local): 06:00 - 07:30\n"
	 * ;
	 * 
	 * assertEquals(expectResult, schedule.toString()); }
	 */
}
