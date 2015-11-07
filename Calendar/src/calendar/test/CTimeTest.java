package calendar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import calendar.CTime;

/**
 * @author Yan Zhi Hao
 *
 */
public class CTimeTest {

	@Test
	public void testValidateHour() {
		// case 1
		assertEquals(12, CTime.validateHour(12));
		// case 2
		assertEquals(23, CTime.validateHour(-1));
		// case 3
		assertEquals(0, CTime.validateHour(24));
		// case 4
		assertEquals(0, CTime.validateHour(0));
		// case 5
		assertEquals(23, CTime.validateHour(23));
		// case 6
		assertNotNull(CTime.validateHour(Integer.MIN_VALUE));
		// case 7
		assertNotNull(CTime.validateHour(Integer.MAX_VALUE));
	}

	@Test
	public void testValidateMinute() {
		// case 8
		assertEquals(25, CTime.validateMinute(25));
		// case 9
		assertEquals(59, CTime.validateMinute(-1));
		// case 10
		assertEquals(0, CTime.validateMinute(60));
		// case 11
		assertEquals(0, CTime.validateMinute(0));
		// case 12
		assertEquals(59, CTime.validateMinute(59));
		// case 13
		assertNotNull(CTime.validateMinute(Integer.MIN_VALUE));
		// case 14
		assertNotNull(CTime.validateMinute(Integer.MAX_VALUE));
	}

	@Test
	public void testValidateTimeZone() {
		// case 15
		assertEquals(0, CTime.validateTimeZone(0));
		// case 16
		assertEquals(-12, CTime.validateTimeZone(-13));
		// case 17
		assertEquals(14, CTime.validateTimeZone(15));
		// case 18
		assertEquals(-12, CTime.validateTimeZone(-12));
		// case 19
		assertEquals(14, CTime.validateTimeZone(14));
		// case 20
		assertNotNull(CTime.validateMinute(Integer.MIN_VALUE));
		// case 21
		assertNotNull(CTime.validateMinute(Integer.MAX_VALUE));
	}

	@Test
	public void testTranslateTime() {
		// case 22
		CTime time = new CTime(20, 0, 11);
		CTime newTime;
		newTime = CTime.translateTime(time, 0);
		assertEquals(7, newTime.getHour());
		assertEquals(0, newTime.getMinute());
		assertEquals(0, newTime.getTimeZone());
		assertEquals(1, newTime.getDayPlus());
		// case 23
		time = new CTime(5, 0, -11);
		newTime = CTime.translateTime(time, 0);
		assertEquals(18, newTime.getHour());
		assertEquals(0, newTime.getMinute());
		assertEquals(0, newTime.getTimeZone());
		assertEquals(-1, newTime.getDayPlus());
		// case 24
		time = new CTime(12, 0, 5);
		newTime = CTime.translateTime(time, 0);
		assertEquals(17, newTime.getHour());
		assertEquals(0, newTime.getMinute());
		assertEquals(0, newTime.getTimeZone());
		assertEquals(0, newTime.getDayPlus());

	}

	// EXTENTION TEST
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

}
