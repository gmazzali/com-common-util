package com.common.util.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.common.util.tools.date.DatePrecision;
import com.common.util.tools.date.DateUtil;

/**
 * La clase de prueba para las utilerías de fechas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class DateUtilUnitTest {

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss SSS");

	/**
	 * La pruebas sobre el método <i><b>createDate</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#createDate(String, String)
	 */
	@Test
	public void testCreateDate() {

		try {
			DateUtil.createDate(null, null);
			Assert.fail();
		} catch (Exception e) {
		}

		try {
			DateUtil.createDate("2000/01/01 00:00:00 000", null);
			Assert.fail();
		} catch (Exception e) {
		}

		try {
			DateUtil.createDate(null, "yyyy/MM/dd HH:mm:ss SSS");
			Assert.fail();
		} catch (Exception e) {
		}
	}

	/**
	 * La pruebas sobre el método <i><b>compare</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#compare(Date, Date, DatePrecision)
	 */
	@Test
	public void testCompare() {
		try {
			Date date1 = null;
			Date date2 = null;

			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");

			try {
				Assert.assertTrue(DateUtil.compare(null, null, null) > 0);
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.compare(date1, null, null) > 0);
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.compare(null, date2, null) > 0);
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.compare(date1, date2, null) > 0);
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.compare(null, null, DatePrecision.YEAR) > 0);
				Assert.fail();
			} catch (Exception e) {
			}

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.YEAR) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.YEAR) == 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.YEAR) < 0);

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MONTH) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MONTH) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MONTH) == 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MONTH) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MONTH) < 0);

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.DAY) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.DAY) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.DAY) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.DAY) == 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.DAY) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.DAY) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.DAY) < 0);

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) == 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) < 0);

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) == 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) < 0);

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) == 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) < 0);

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 001");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) > 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) == 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 001");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) < 0);
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) < 0);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La pruebas sobre el método <i><b>before</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#before(Date, Date, DatePrecision)
	 */
	@Test
	public void testBefore() {
		try {
			Date date1 = null;
			Date date2 = null;

			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");

			try {
				Assert.assertTrue(DateUtil.before(null, null, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.before(date1, null, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.before(null, date2, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.before(date1, date2, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.before(null, null, DatePrecision.YEAR));
				Assert.fail();
			} catch (Exception e) {
			}

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.YEAR));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MONTH));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.DAY));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.HOUR));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MINUTE));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.SECOND));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 001");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 001");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La pruebas sobre el método <i><b>beforeOrEqual</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#beforeOrEqual(Date, Date, DatePrecision)
	 */
	@Test
	public void testBeforeOrEqual() {
		try {
			Date date1 = null;
			Date date2 = null;

			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");

			try {
				Assert.assertTrue(DateUtil.beforeOrEqual(null, null, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.beforeOrEqual(date1, null, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.beforeOrEqual(null, date2, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.beforeOrEqual(null, null, DatePrecision.YEAR));
				Assert.fail();
			} catch (Exception e) {
			}

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.YEAR));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MONTH));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.DAY));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 001");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 001");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La pruebas sobre el método <i><b>beforeOrEqual</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#equal(Date, Date, DatePrecision)
	 */
	@Test
	public void testEqual() {
		try {
			Date date1 = null;
			Date date2 = null;

			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");

			try {
				Assert.assertTrue(DateUtil.equal(null, null, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.equal(date1, null, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.equal(null, date2, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.equal(date1, date2, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.equal(null, null, DatePrecision.YEAR));
				Assert.fail();
			} catch (Exception e) {
			}

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.equal(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.YEAR));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.equal(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MONTH));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.equal(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.DAY));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.equal(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.HOUR));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 001");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 001");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La pruebas sobre el método <i><b>afterOrEqual</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#afterOrEqual(Date, Date, DatePrecision)
	 */
	@Test
	public void testAfterOrEqual() {
		try {
			Date date1 = null;
			Date date2 = null;

			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");

			try {
				Assert.assertTrue(DateUtil.afterOrEqual(null, null, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.afterOrEqual(date1, null, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.afterOrEqual(null, date2, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.afterOrEqual(null, null, DatePrecision.YEAR));
				Assert.fail();
			} catch (Exception e) {
			}

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.YEAR));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MONTH));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.DAY));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 001");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 001");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La pruebas sobre el método <i><b>after</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#after(Date, Date, DatePrecision)
	 */
	@Test
	public void testAfter() {
		try {
			Date date1 = null;
			Date date2 = null;

			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");

			try {
				Assert.assertTrue(DateUtil.after(null, null, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.after(date1, null, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.after(null, date2, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.after(date1, date2, null));
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertTrue(DateUtil.after(null, null, DatePrecision.YEAR));
				Assert.fail();
			} catch (Exception e) {
			}

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.YEAR));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MONTH));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.DAY));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.DAY));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.HOUR));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MINUTE));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.SECOND));

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 001");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 001");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:01 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La pruebas sobre el método <i><b>truncate</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#truncate(Date, DatePrecision)
	 */
	@Test
	public void testTruncate() {
		try {
			Date date1 = null;
			Date date2 = null;
			Date truncateDate1 = null;
			Date truncateDate2 = null;

			date1 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");

			try {
				DateUtil.truncate(null, null);
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				DateUtil.truncate(date1, null);
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				DateUtil.truncate(null, DatePrecision.YEAR);
				Assert.fail();
			} catch (Exception e) {
			}

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.YEAR);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.YEAR);
			Assert.assertEquals(truncateDate1, truncateDate2);
			date1 = DateUtilUnitTest.FORMAT.parse("2001/02/02 01:01:01 001");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.YEAR);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.YEAR);
			Assert.assertEquals(truncateDate1, truncateDate2);

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.MONTH);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.MONTH);
			Assert.assertEquals(truncateDate1, truncateDate2);
			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/02 01:01:01 001");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.MONTH);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.MONTH);
			Assert.assertEquals(truncateDate1, truncateDate2);

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.DAY);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.DAY);
			Assert.assertEquals(truncateDate1, truncateDate2);
			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 01:01:01 001");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.DAY);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.DAY);
			Assert.assertEquals(truncateDate1, truncateDate2);

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.HOUR);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.HOUR);
			Assert.assertEquals(truncateDate1, truncateDate2);
			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:01:01 001");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.HOUR);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.HOUR);
			Assert.assertEquals(truncateDate1, truncateDate2);

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.MINUTE);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.MINUTE);
			Assert.assertEquals(truncateDate1, truncateDate2);
			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:01 001");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.MINUTE);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.MINUTE);
			Assert.assertEquals(truncateDate1, truncateDate2);

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.SECOND);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.SECOND);
			Assert.assertEquals(truncateDate1, truncateDate2);
			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 001");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.SECOND);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.SECOND);
			Assert.assertEquals(truncateDate1, truncateDate2);

			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.MILLISECOND);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.MILLISECOND);
			Assert.assertEquals(truncateDate1, truncateDate2);
			date1 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.MILLISECOND);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.MILLISECOND);
			Assert.assertEquals(truncateDate1, truncateDate2);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La pruebas sobre el método <i><b>getHigherDate</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#getHigherDate(Date, Date, DatePrecision)
	 */
	@Test
	public void testGetHigherDate() {
		try {
			Date highDate = null;
			Date lowDate = null;

			highDate = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");

			try {
				DateUtil.getHigherDate(null, null, null);
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				DateUtil.getHigherDate(null, null, DatePrecision.YEAR);
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				DateUtil.getHigherDate(highDate, null, null);
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				DateUtil.getHigherDate(null, lowDate, null);
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				DateUtil.getHigherDate(highDate, lowDate, null);
				Assert.fail();
			} catch (Exception e) {
			}

			highDate = DateUtilUnitTest.FORMAT.parse("2002/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigherDate(highDate, lowDate, DatePrecision.YEAR));
			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigherDate(highDate, lowDate, DatePrecision.YEAR));

			highDate = DateUtilUnitTest.FORMAT.parse("2001/02/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigherDate(highDate, lowDate, DatePrecision.MONTH));
			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigherDate(highDate, lowDate, DatePrecision.MONTH));

			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/02 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigherDate(highDate, lowDate, DatePrecision.DAY));
			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigherDate(highDate, lowDate, DatePrecision.DAY));

			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 01:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigherDate(highDate, lowDate, DatePrecision.HOUR));
			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigherDate(highDate, lowDate, DatePrecision.HOUR));

			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:01:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigherDate(highDate, lowDate, DatePrecision.MINUTE));
			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigherDate(highDate, lowDate, DatePrecision.MINUTE));

			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:01 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigherDate(highDate, lowDate, DatePrecision.SECOND));
			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigherDate(highDate, lowDate, DatePrecision.SECOND));

			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 001");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigherDate(highDate, lowDate, DatePrecision.MILLISECOND));
			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigherDate(highDate, lowDate, DatePrecision.MILLISECOND));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La pruebas sobre el método <i><b>getLowerDate</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#getLowerDate(Date, Date, DatePrecision)
	 */
	@Test
	public void testGetLowerDate() {
		try {
			Date highDate = null;
			Date lowDate = null;

			highDate = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");

			try {
				DateUtil.getLowerDate(null, null, null);
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				DateUtil.getLowerDate(null, null, DatePrecision.YEAR);
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				DateUtil.getLowerDate(highDate, null, null);
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				DateUtil.getLowerDate(null, lowDate, null);
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				DateUtil.getLowerDate(highDate, lowDate, null);
				Assert.fail();
			} catch (Exception e) {
			}

			highDate = DateUtilUnitTest.FORMAT.parse("2002/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLowerDate(highDate, lowDate, DatePrecision.YEAR));
			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLowerDate(highDate, lowDate, DatePrecision.YEAR));

			highDate = DateUtilUnitTest.FORMAT.parse("2001/02/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLowerDate(highDate, lowDate, DatePrecision.MONTH));
			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLowerDate(highDate, lowDate, DatePrecision.MONTH));

			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/02 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLowerDate(highDate, lowDate, DatePrecision.DAY));
			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLowerDate(highDate, lowDate, DatePrecision.DAY));

			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 01:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLowerDate(highDate, lowDate, DatePrecision.HOUR));
			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLowerDate(highDate, lowDate, DatePrecision.HOUR));

			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:01:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLowerDate(highDate, lowDate, DatePrecision.MINUTE));
			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLowerDate(highDate, lowDate, DatePrecision.MINUTE));

			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:01 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLowerDate(highDate, lowDate, DatePrecision.SECOND));
			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLowerDate(highDate, lowDate, DatePrecision.SECOND));

			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 001");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLowerDate(highDate, lowDate, DatePrecision.MILLISECOND));
			highDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilUnitTest.FORMAT.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLowerDate(highDate, lowDate, DatePrecision.MILLISECOND));

		} catch (ParseException e) {
			e.printStackTrace();
		}
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

			date = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");

			try {
				Assert.assertTrue(DateUtil.isWeekend(null));
				Assert.fail();
			} catch (Exception e) {
			}

			date = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.isWeekend(date));

			date = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.isWeekend(date));

			date = DateUtilUnitTest.FORMAT.parse("2000/01/03 00:00:00 000");
			Assert.assertFalse(DateUtil.isWeekend(date));

			date = DateUtilUnitTest.FORMAT.parse("2000/01/04 00:00:00 000");
			Assert.assertFalse(DateUtil.isWeekend(date));

			date = DateUtilUnitTest.FORMAT.parse("2008/03/01 00:00:00 000");
			Assert.assertTrue(DateUtil.isWeekend(date));

			date = DateUtilUnitTest.FORMAT.parse("2008/03/02 00:00:00 000");
			Assert.assertTrue(DateUtil.isWeekend(date));

			date = DateUtilUnitTest.FORMAT.parse("2008/03/03 00:00:00 000");
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

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");

			try {
				DateUtil.getPreviousMonth(null);
				Assert.fail();
			} catch (Exception e) {
			}

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			expectedDate = DateUtilUnitTest.FORMAT.parse("1999/12/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/12/01 00:00:00 000");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/11/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/12/12 12:12:12 120");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/11/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/02/02 00:00:00 000");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/02/02 01:00:00 000");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/02/02 01:01:00 000");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/02/02 01:01:01 000");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getPreviousMonth(originalDate));

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/02/02 01:01:01 001");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
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

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");

			try {
				DateUtil.getNextMonth(null);
				Assert.fail();
			} catch (Exception e) {
			}

			originalDate = DateUtilUnitTest.FORMAT.parse("1999/12/01 00:00:00 000");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/01/01 00:00:00 000");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/11/01 00:00:00 000");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/12/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/11/12 12:12:12 120");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/12/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/01/02 00:00:00 000");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/01/02 01:00:00 000");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/01/02 01:01:00 000");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/01/02 01:01:01 000");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

			originalDate = DateUtilUnitTest.FORMAT.parse("2000/01/02 01:01:01 001");
			expectedDate = DateUtilUnitTest.FORMAT.parse("2000/02/01 00:00:00 000");
			Assert.assertEquals(expectedDate, DateUtil.getNextMonth(originalDate));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}