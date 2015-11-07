package calendar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import calendar.CMeeting;
import calendar.CTime;
import calendar.exceptions.CalendarException;
import calendar.statics.ErrorCode;

public class CMeetingTest {

	@Test
	public void testCMeeting() {
		CTime startTime = new CTime(10, 0, -5);
		CTime endTime = new CTime(11, 0, -5);
		CMeeting meeting;

		try {
			meeting = new CMeeting(startTime, endTime, "Windsor");
		} catch (CalendarException e) {
			fail("Unexpected error.");
		}

		try {
			meeting = new CMeeting(startTime, null, "Windsor");
			fail("No CalendarException throwed!");
		} catch (CalendarException e) {
			assertEquals(1010, e.getErrorCode());
			assertEquals(ErrorCode.getErrorMessage(1010), e.getErrorMessage());
		}

		try {
			meeting = new CMeeting(null, endTime, "Windsor");
			fail("No CalendarException throwed!");
		} catch (CalendarException e) {
			assertEquals(1010, e.getErrorCode());
			assertEquals(ErrorCode.getErrorMessage(1010), e.getErrorMessage());
		}

		startTime = new CTime(10, 40, -5);
		endTime = new CTime(11, 30, -5);

		try {
			meeting = new CMeeting(startTime, endTime, "Windsor");
		} catch (CalendarException e) {
			fail("Unexpected error.");
		}

		startTime = new CTime(10, 0, -5);
		endTime = new CTime(10, 0, -6);

		try {
			meeting = new CMeeting(startTime, endTime, "Windsor");
			fail("No CalendarException throwed!");
		} catch (CalendarException e) {
			assertEquals(1020, e.getErrorCode());
			assertEquals(ErrorCode.getErrorMessage(1020), e.getErrorMessage());
		}

		startTime = new CTime(10, 0, -5);
		endTime = new CTime(10, 0, -4);

		try {
			meeting = new CMeeting(startTime, endTime, "Windsor");
		} catch (CalendarException e) {
			fail("Unexpected error.");
		}

		startTime = new CTime(10, 0, -5);
		endTime = new CTime(10, 0, -6);

		try {
			meeting = new CMeeting(startTime, endTime, "Windsor");
			fail("No CalendarException throwed!");
		} catch (CalendarException e) {
			assertEquals(1020, e.getErrorCode());
			assertEquals(ErrorCode.getErrorMessage(1020), e.getErrorMessage());
		}

		startTime = new CTime(7, 0, 0);
		endTime = new CTime(9, 0, 0);

		try {
			meeting = new CMeeting(startTime, endTime, "Windsor", true);
			fail("No CalendarException throwed!");
		} catch (CalendarException e) {
			assertEquals(1030, e.getErrorCode());
			assertEquals(ErrorCode.getErrorMessage(1030), e.getErrorMessage());
		}

		startTime = new CTime(7, 30, 0);
		endTime = new CTime(8, 40, 0);

		try {
			meeting = new CMeeting(startTime, endTime, "Windsor", true);
			fail("No CalendarException throwed!");
		} catch (CalendarException e) {
			assertEquals(1030, e.getErrorCode());
			assertEquals(ErrorCode.getErrorMessage(1030), e.getErrorMessage());
		}

		startTime = new CTime(7, 0, -5);
		endTime = new CTime(8, 0, -5);

		try {
			meeting = new CMeeting(startTime, endTime, "Windsor", true);
			fail("No CalendarException throwed!");
		} catch (CalendarException e) {
			assertEquals(1040, e.getErrorCode());
			assertEquals(ErrorCode.getErrorMessage(1040), e.getErrorMessage());
		}

		startTime = new CTime(17, 0, -5);
		endTime = new CTime(18, 0, -5);

		try {
			meeting = new CMeeting(startTime, endTime, "Windsor", true);
			fail("No CalendarException throwed!");
		} catch (CalendarException e) {
			assertEquals(1040, e.getErrorCode());
			assertEquals(ErrorCode.getErrorMessage(1040), e.getErrorMessage());
		}

	}

}
