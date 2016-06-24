package com.common.util.business.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * La clase de prueba para las utilerías de fechas.
 * 
 * @since 05/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class DateUtilTest {

	/**
	 * El formateador de las fechas.
	 */
	private static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss SSS");

	@BeforeClass
	public static void initClass() {
		BasicConfigurator.configure();
	}

	/**
	 * La pruebas sobre el método <i><b>isWeekend</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#isWeekend(Date)
	 */
	@Test
	public void testIsWeekend() {
		try {
			Date date = null;

			try {
				Assert.assertTrue(DateUtil.isWeekend(null));
				Assert.fail();
			} catch (Exception e) {
			}

			date = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.isWeekend(date));

			date = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.isWeekend(date));

			date = DateUtilTest.FORMAT_DATE.parse("2000/01/03 00:00:00 000");
			Assert.assertFalse(DateUtil.isWeekend(date));

			date = DateUtilTest.FORMAT_DATE.parse("2000/01/04 00:00:00 000");
			Assert.assertFalse(DateUtil.isWeekend(date));

			date = DateUtilTest.FORMAT_DATE.parse("2008/03/01 00:00:00 000");
			Assert.assertTrue(DateUtil.isWeekend(date));

			date = DateUtilTest.FORMAT_DATE.parse("2008/03/02 00:00:00 000");
			Assert.assertTrue(DateUtil.isWeekend(date));

			date = DateUtilTest.FORMAT_DATE.parse("2008/03/03 00:00:00 000");
			Assert.assertFalse(DateUtil.isWeekend(date));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La pruebas sobre el método <i><b>getPreviousMonth</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#getPreviousMonth(Date)
	 */
	@Test
	public void testGetPreviousMonth() {
		try {
			Date originalDate = null;
			Date expectedDate = null;

			try {
				DateUtil.getPreviousMonth(null);
				Assert.fail();
			} catch (Exception e) {
			}

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("1999/12/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/12/01 00:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/11/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/12/12 12:12:12 120");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/11/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/02/02 00:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/02/02 01:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/02/02 01:01:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/02/02 01:01:01 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/02/02 01:01:01 001");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La pruebas sobre el método <i><b>getNextMonth</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#getNextMonth(Date)
	 */
	@Test
	public void testGetNextMonth() {
		try {
			Date originalDate = null;
			Date expectedDate = null;

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");

			try {
				DateUtil.getNextMonth(null);
				Assert.fail();
			} catch (Exception e) {
			}

			originalDate = DateUtilTest.FORMAT_DATE.parse("1999/12/01 00:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/11/01 00:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/12/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/11/12 12:12:12 120");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/12/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/01/02 01:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/01/02 01:01:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/01/02 01:01:01 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2000/01/02 01:01:01 001");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La pruebas sobre el método <i><b>getFirstDayOfMonth</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#getFirstDayOfMonth(Date)
	 */
	@Test
	public void testGetFirstDayMonth() {
		try {
			Date originalDate = null;
			Date expectedDate = null;

			try {
				DateUtil.getFirstDayOfMonth(null);
				Assert.fail();
			} catch (Exception e) {
			}

			originalDate = DateUtilTest.FORMAT_DATE.parse("2013/01/01 00:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2013/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getFirstDayOfMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2013/12/20 00:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2013/12/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getFirstDayOfMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2013/01/01 01:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2013/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getFirstDayOfMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2013/01/01 01:01:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2013/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getFirstDayOfMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2013/01/01 01:01:01 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2013/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getFirstDayOfMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2013/01/01 01:01:01 001");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2013/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getFirstDayOfMonth(originalDate));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La pruebas sobre el método <i><b>getLastDayOfMonth</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#getLastDayOfMonth(Date)
	 */
	@Test
	public void testGetLastDayOfMonth() {
		try {
			Date originalDate = null;
			Date expectedDate = null;

			try {
				DateUtil.getLastDayOfMonth(null);
				Assert.fail();
			} catch (Exception e) {
			}

			originalDate = DateUtilTest.FORMAT_DATE.parse("2013/01/31 23:59:59 999");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2013/01/31 23:59:59 999");
			Assert.assertEquals(expectedDate, DateUtil.getLastDayOfMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2013/01/01 00:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2013/01/31 23:59:59 999");
			Assert.assertEquals(expectedDate, DateUtil.getLastDayOfMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2012/02/01 00:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2012/02/29 23:59:59 999");
			Assert.assertEquals(expectedDate, DateUtil.getLastDayOfMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2013/02/01 00:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2013/02/28 23:59:59 999");
			Assert.assertEquals(expectedDate, DateUtil.getLastDayOfMonth(originalDate));

			originalDate = DateUtilTest.FORMAT_DATE.parse("2013/04/01 00:00:00 000");
			expectedDate = DateUtilTest.FORMAT_DATE.parse("2013/04/30 23:59:59 999");
			Assert.assertEquals(expectedDate, DateUtil.getLastDayOfMonth(originalDate));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}