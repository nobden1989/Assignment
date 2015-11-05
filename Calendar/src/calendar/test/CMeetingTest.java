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
		CTime endTime = new CTime(12, 0, -5);
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
			assertEquals(1001, e.getErrorCode());
			assertEquals(ErrorCode.getErrorMessage(1001), e.getErrorMessage());
		}

		try {
			meeting = new CMeeting(null, endTime, "Windsor");
			fail("No CalendarException throwed!");
		} catch (CalendarException e) {
			assertEquals(1001, e.getErrorCode());
			assertEquals(ErrorCode.getErrorMessage(1001), e.getErrorMessage());
		}

		startTime = new CTime(10, 0, -5);
		endTime = new CTime(10, 0, -8);

		try {
			meeting = new CMeeting(startTime, endTime, "Windsor");
			fail("No CalendarException throwed!");
		} catch (CalendarException e) {
			assertEquals(1002, e.getErrorCode());
			assertEquals(ErrorCode.getErrorMessage(1002), e.getErrorMessage());
		}

		startTime = new CTime(10, 0, -5);
		endTime = new CTime(10, 0, -3);

		try {
			meeting = new CMeeting(startTime, endTime, "Windsor");
		} catch (CalendarException e) {
			fail("Unexpected error.");
		}

		startTime = new CTime(10, 0, -5);
		endTime = new CTime(10, 0, -8);

		try {
			meeting = new CMeeting(startTime, endTime, "Windsor");
			fail("No CalendarException throwed!");
		} catch (CalendarException e) {
			assertEquals(1002, e.getErrorCode());
			assertEquals(ErrorCode.getErrorMessage(1002), e.getErrorMessage());
		}

		startTime = new CTime(7, 0, 0);
		endTime = new CTime(12, 0, 0);

		try {
			meeting = new CMeeting(startTime, endTime, "Windsor");
		} catch (CalendarException e) {
			fail("Unexpected error.");
		}

		try {
			meeting = new CMeeting(startTime, endTime, "Windsor", true);
			fail("No CalendarException throwed!");
		} catch (CalendarException e) {
			assertEquals(1003, e.getErrorCode());
			assertEquals(ErrorCode.getErrorMessage(1003), e.getErrorMessage());
		}

		startTime = new CTime(9, 0, 0);
		endTime = new CTime(17, 0, 0);

		try {
			meeting = new CMeeting(startTime, endTime, "Windsor");
		} catch (CalendarException e) {
			fail("Unexpected error.");
		}

		try {
			meeting = new CMeeting(startTime, endTime, "Windsor", true);
			fail("No CalendarException throwed!");
		} catch (CalendarException e) {
			assertEquals(1003, e.getErrorCode());
			assertEquals(ErrorCode.getErrorMessage(1003), e.getErrorMessage());
		}

	}

}
