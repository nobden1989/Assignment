package calendar.test;

import static org.junit.Assert.*;

import org.junit.Test;

import calendar.CTime;

public class CTimeTest {

	@Test
	public void testGetStandarTime() {

		CTime testTime = new CTime(12, 30, 5);
		testTime = CTime.getStandarTime(testTime);
		assertEquals(17, testTime.getHour());
		assertEquals(30, testTime.getMinute());
		assertEquals(0, testTime.getDayPlus());
		assertEquals(CTime.STANDER_TIME, testTime.getTimeZone());

		testTime = new CTime(5, 30, 14);
		testTime = CTime.getStandarTime(testTime);
		assertEquals(19, testTime.getHour());
		assertEquals(30, testTime.getMinute());
		assertEquals(0, testTime.getDayPlus());
		assertEquals(CTime.STANDER_TIME, testTime.getTimeZone());

		testTime = new CTime(5, 30, 15);
		testTime = CTime.getStandarTime(testTime);
		assertEquals(19, testTime.getHour());
		assertEquals(30, testTime.getMinute());
		assertEquals(0, testTime.getDayPlus());
		assertEquals(CTime.STANDER_TIME, testTime.getTimeZone());

		testTime = new CTime(-10, -29, 5);
		testTime = CTime.getStandarTime(testTime);
		assertEquals(19, testTime.getHour());
		assertEquals(31, testTime.getMinute());
		assertEquals(0, testTime.getDayPlus());
		assertEquals(CTime.STANDER_TIME, testTime.getTimeZone());

		testTime = new CTime(23, 59, 5);
		testTime = CTime.getStandarTime(testTime);
		assertEquals(4, testTime.getHour());
		assertEquals(59, testTime.getMinute());
		assertEquals(1, testTime.getDayPlus());
		assertEquals(CTime.STANDER_TIME, testTime.getTimeZone());

		testTime = new CTime(12, 30, -12);
		testTime = CTime.getStandarTime(testTime);
		assertEquals(0, testTime.getHour());
		assertEquals(30, testTime.getMinute());
		assertEquals(0, testTime.getDayPlus());
		assertEquals(CTime.STANDER_TIME, testTime.getTimeZone());

		testTime = new CTime(12, 30, -13);
		testTime = CTime.getStandarTime(testTime);
		assertEquals(0, testTime.getHour());
		assertEquals(30, testTime.getMinute());
		assertEquals(0, testTime.getDayPlus());
		assertEquals(CTime.STANDER_TIME, testTime.getTimeZone());

		testTime = new CTime(6, 30, -13, true);
		testTime = CTime.getStandarTime(testTime);
		assertEquals(19, testTime.getHour());
		assertEquals(30, testTime.getMinute());
		assertEquals(-1, testTime.getDayPlus());
		assertEquals(CTime.STANDER_TIME, testTime.getTimeZone());

	}

	@Test
	public void testCompairTime() {

		CTime sTime = new CTime(23, 0, 8);
		CTime eTime = new CTime(23, 0, 12);

		assertEquals(-1, CTime.compairTime(sTime, eTime));
		assertEquals(1, CTime.compairTime(eTime, sTime));

		sTime = new CTime(0, 0, -8);
		eTime = new CTime(0, 0, -12);

		assertEquals(1, CTime.compairTime(sTime, eTime));
		assertEquals(-1, CTime.compairTime(eTime, sTime));

		sTime = new CTime(20, 30, 8);
		eTime = new CTime(20, 30, 8, true);

		assertEquals(-1, CTime.compairTime(sTime, eTime));
		assertEquals(1, CTime.compairTime(eTime, sTime));

		sTime = new CTime(20, 30, 8);
		eTime = new CTime(20, 31, 8);

		assertEquals(-1, CTime.compairTime(sTime, eTime));
		assertEquals(1, CTime.compairTime(eTime, sTime));

		sTime = new CTime(20, 30, 8);
		eTime = new CTime(20, 30, 8);

		assertEquals(0, CTime.compairTime(sTime, eTime));
		assertEquals(0, CTime.compairTime(eTime, sTime));
		
		sTime = new CTime(20, -29, -8);
		eTime = new CTime(1, 31, 11);

		assertEquals(0, CTime.compairTime(sTime, eTime));
		assertEquals(0, CTime.compairTime(eTime, sTime));

	}

	@Test
	public void testCalculateHour() {

		assertEquals(12, CTime.calculateHour(12, 0));
		assertEquals(11, CTime.calculateHour(12, -1));
		assertEquals(23, CTime.calculateHour(12, -13));
		assertEquals(13, CTime.calculateHour(12, 1));
		assertEquals(0, CTime.calculateHour(12, 12));
		assertEquals(12, CTime.calculateHour(12, 24));

		assertNotNull(CTime.calculateHour(Integer.MIN_VALUE, Integer.MIN_VALUE));
		assertNotNull(CTime.calculateHour(Integer.MAX_VALUE, Integer.MAX_VALUE));
	}

	@Test
	public void testValidateHour() {
		assertEquals(11, CTime.validateHour(-13));
		assertEquals(23, CTime.validateHour(-1));
		assertEquals(0, CTime.validateHour(0));
		assertEquals(12, CTime.validateHour(12));
		assertEquals(23, CTime.validateHour(23));
		assertEquals(0, CTime.validateHour(24));
		assertEquals(13, CTime.validateHour(37));
		assertNotNull(CTime.validateHour(Integer.MIN_VALUE));
		assertNotNull(CTime.validateHour(Integer.MAX_VALUE));
	}

	@Test
	public void testValidateMinute() {
		assertEquals(29, CTime.validateMinute(-31));
		assertEquals(59, CTime.validateMinute(-1));
		assertEquals(0, CTime.validateMinute(0));
		assertEquals(30, CTime.validateMinute(30));
		assertEquals(59, CTime.validateMinute(59));
		assertEquals(0, CTime.validateMinute(60));
		assertEquals(31, CTime.validateMinute(91));
		assertNotNull(CTime.validateMinute(Integer.MIN_VALUE));
		assertNotNull(CTime.validateMinute(Integer.MAX_VALUE));
	}

	@Test
	public void testValidateTimeZone() {
		assertEquals(-12, CTime.validateTimeZone(-13));
		assertEquals(-12, CTime.validateTimeZone(-12));
		assertEquals(0, CTime.validateTimeZone(0));
		assertEquals(6, CTime.validateTimeZone(6));
		assertEquals(14, CTime.validateTimeZone(14));
		assertEquals(14, CTime.validateTimeZone(15));
		assertNotNull(CTime.validateMinute(Integer.MIN_VALUE));
		assertNotNull(CTime.validateMinute(Integer.MAX_VALUE));
	}

}
