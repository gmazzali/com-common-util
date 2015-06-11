package com.common.util.business.tool.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.common.util.business.util.DatePrecision;

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
	 * La pruebas sobre el método <i><b>createDate</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#createDate(String)
	 * @see DateUtil#createDate(String, String)
	 */
	@Test
	public void testCreateDate() {
		// DateUtil#createDate(String)
		Assert.assertNull(DateUtil.createDate(null));
		Assert.assertNull(DateUtil.createDate(""));
		Assert.assertNull(DateUtil.createDate("ASD"));
		Assert.assertNotNull(DateUtil.createDate("01/01/2014"));

		// DateUtil#createDate(String, String)
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
		Assert.assertNull(DateUtil.createDate("ASD", "yyyy/MM/dd HH:mm:ss SSS"));
		Assert.assertNull(DateUtil.createDate("2000/01/01 00:00:00 000", "ASD"));
		Assert.assertNotNull(DateUtil.createDate("2014/01/01 00:00:00 000", "yyyy/MM/dd HH:mm:ss SSS"));
	}

	/**
	 * La pruebas sobre el método <i><b>formatDate</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#formatDate(Date)
	 * @see DateUtil#formatDate(Date, String)
	 */
	@Test
	public void testFormatDate() {
		// DateUtil#formatDate(Date)
		Assert.assertNull(DateUtil.formatDate(null));
		Assert.assertNotNull(DateUtil.formatDate(new Date()));

		// DateUtil#createDate(String, String)
		try {
			DateUtil.formatDate(null, null);
			Assert.fail();
		} catch (Exception e) {
		}
		try {
			DateUtil.formatDate(new Date(), null);
			Assert.fail();
		} catch (Exception e) {
		}
		try {
			DateUtil.formatDate(null, "yyyy/MM/dd HH:mm:ss SSS");
			Assert.fail();
		} catch (Exception e) {
		}
		Assert.assertNull(DateUtil.formatDate(new Date(), "ASD"));
		Assert.assertNotNull(DateUtil.formatDate(new Date(), "yyyy/MM/dd HH:mm:ss SSS"));

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

			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");

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
				Assert.assertTrue(DateUtil.compare(date1, date2, null) == 0);
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertTrue(DateUtil.compare(null, null, DatePrecision.YEAR) > 0);
				Assert.fail();
			} catch (Exception e) {
			}

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.YEAR) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.YEAR) == 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.YEAR) < 0);

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MONTH) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MONTH) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MONTH) == 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MONTH) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MONTH) < 0);

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.DAY) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.DAY) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.DAY) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.DAY) == 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.DAY) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.DAY) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.DAY) < 0);

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) == 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.HOUR) < 0);

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) == 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MINUTE) < 0);

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) == 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.SECOND) < 0);

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 001");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) > 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) == 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 001");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.compare(date1, date2, DatePrecision.MILLISECOND) < 0);
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
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

			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");

			try {
				Assert.assertFalse(DateUtil.before(null, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.before(date1, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.before(null, date2, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.before(date1, date2, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.before(null, null, DatePrecision.YEAR));
			} catch (Exception e) {
				Assert.fail();
			}

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.YEAR));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MONTH));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.DAY));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.HOUR));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MINUTE));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.SECOND));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 001");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 001");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.before(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
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

			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");

			try {
				Assert.assertFalse(DateUtil.beforeOrEqual(null, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.beforeOrEqual(date1, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.beforeOrEqual(null, date2, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.beforeOrEqual(null, null, DatePrecision.YEAR));
			} catch (Exception e) {
				Assert.fail();
			}

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.YEAR));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MONTH));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.DAY));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.HOUR));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MINUTE));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.SECOND));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 001");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 001");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.beforeOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
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

			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");

			try {
				Assert.assertFalse(DateUtil.equal(null, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.equal(date1, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.equal(null, date2, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertTrue(DateUtil.equal(date1, date2, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.equal(null, null, DatePrecision.YEAR));
			} catch (Exception e) {
				Assert.fail();
			}

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.equal(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.YEAR));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.equal(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MONTH));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.equal(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.DAY));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.equal(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.HOUR));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MINUTE));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.SECOND));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 001");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 001");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.equal(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
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

			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");

			try {
				Assert.assertFalse(DateUtil.afterOrEqual(null, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.afterOrEqual(date1, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.afterOrEqual(null, date2, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.afterOrEqual(null, null, DatePrecision.YEAR));
			} catch (Exception e) {
				Assert.fail();
			}

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.YEAR));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MONTH));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.DAY));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.HOUR));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MINUTE));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.SECOND));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 001");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 001");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.afterOrEqual(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
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

			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");

			try {
				Assert.assertFalse(DateUtil.after(null, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.after(date1, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.after(null, date2, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.after(date1, date2, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.after(null, null, DatePrecision.YEAR));
			} catch (Exception e) {
				Assert.fail();
			}

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.YEAR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.YEAR));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MONTH));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MONTH));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.DAY));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.DAY));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.HOUR));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.HOUR));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MINUTE));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MINUTE));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.SECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.SECOND));

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 001");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 001");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:01 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));
			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.after(date1, date2, DatePrecision.MILLISECOND));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La pruebas sobre el método <i><b>between</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#between(Date, Date, Date, DatePrecision)
	 */
	@Test
	public void testBetween() {
		try {
			Date date = null;
			Date after = null;
			Date before = null;

			date = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");

			try {
				Assert.assertFalse(DateUtil.between(null, null, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.between(date, null, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.between(null, after, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.between(null, null, before, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.between(null, null, null, DatePrecision.YEAR));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertFalse(DateUtil.between(date, null, null, DatePrecision.YEAR));
			} catch (Exception e) {
				Assert.fail();
			}

			// YEAR
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2002/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.YEAR));
			date = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.YEAR));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.YEAR));
			date = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2002/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.YEAR));

			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			after = null;
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.YEAR));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = null;
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.YEAR));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2002/01/01 00:00:00 000");
			after = null;
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.YEAR));

			date = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.YEAR));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.YEAR));
			date = DateUtilTest.FORMAT_DATE.parse("2002/01/01 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.YEAR));

			// MONTH
			date = DateUtilTest.FORMAT_DATE.parse("2001/02/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/03/01 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MONTH));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MONTH));
			date = DateUtilTest.FORMAT_DATE.parse("2001/02/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MONTH));
			date = DateUtilTest.FORMAT_DATE.parse("2001/03/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/02/01 00:00:00 000");
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.MONTH));

			date = DateUtilTest.FORMAT_DATE.parse("2001/02/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = null;
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MONTH));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = null;
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MONTH));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/02/01 00:00:00 000");
			after = null;
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.MONTH));

			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/02/01 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MONTH));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MONTH));
			date = DateUtilTest.FORMAT_DATE.parse("2001/02/01 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.MONTH));

			// DAY
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/02 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/03 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.DAY));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.DAY));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/02 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.DAY));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/03 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/02 00:00:00 000");
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.DAY));

			date = DateUtilTest.FORMAT_DATE.parse("2001/01/02 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = null;
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.DAY));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = null;
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.DAY));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/02 00:00:00 000");
			after = null;
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.DAY));

			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/02 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.DAY));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.DAY));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/02 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.DAY));

			// HOUR
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 01:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 02:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.HOUR));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.HOUR));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 01:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.HOUR));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 02:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 01:00:00 000");
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.HOUR));

			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 01:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = null;
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.HOUR));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = null;
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.HOUR));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/02 01:00:00 000");
			after = null;
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.HOUR));

			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 01:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.HOUR));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.HOUR));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 01:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.HOUR));

			// MINUTE
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:01:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:02:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MINUTE));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MINUTE));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:01:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MINUTE));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:02:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:01:00 000");
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.MINUTE));

			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:01:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = null;
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MINUTE));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = null;
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MINUTE));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/02 00:01:00 000");
			after = null;
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.MINUTE));

			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:01:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MINUTE));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MINUTE));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:01:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.MINUTE));

			// SECOND
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:01 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:02 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.SECOND));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:01 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.SECOND));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:01 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:01 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.SECOND));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:02 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:01 000");
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.SECOND));

			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:01 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = null;
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.SECOND));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = null;
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.SECOND));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/02 00:00:01 000");
			after = null;
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.SECOND));

			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:01 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.SECOND));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.SECOND));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:01 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.SECOND));

			// MILLISECOND
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 001");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 002");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MILLISECOND));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 001");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MILLISECOND));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 001");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 001");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MILLISECOND));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 002");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 001");
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.MILLISECOND));

			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 001");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = null;
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MILLISECOND));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			after = null;
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MILLISECOND));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = DateUtilTest.FORMAT_DATE.parse("2001/01/02 00:00:00 001");
			after = null;
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.MILLISECOND));

			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 001");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MILLISECOND));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertTrue(DateUtil.between(date, before, after, DatePrecision.MILLISECOND));
			date = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 001");
			before = null;
			after = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertFalse(DateUtil.between(date, before, after, DatePrecision.MILLISECOND));

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

			date1 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");

			try {
				DateUtil.truncate(null, null);
				Assert.fail();
			} catch (Exception e) {
			}

			try {
				Assert.assertEquals(date1, DateUtil.truncate(date1, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				DateUtil.truncate(null, DatePrecision.YEAR);
				Assert.fail();
			} catch (Exception e) {
			}

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.YEAR);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.YEAR);
			Assert.assertEquals(truncateDate1, truncateDate2);
			date1 = DateUtilTest.FORMAT_DATE.parse("2001/02/02 01:01:01 001");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.YEAR);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.YEAR);
			Assert.assertEquals(truncateDate1, truncateDate2);

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.MONTH);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.MONTH);
			Assert.assertEquals(truncateDate1, truncateDate2);
			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/02 01:01:01 001");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.MONTH);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.MONTH);
			Assert.assertEquals(truncateDate1, truncateDate2);

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.DAY);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.DAY);
			Assert.assertEquals(truncateDate1, truncateDate2);
			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 01:01:01 001");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.DAY);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.DAY);
			Assert.assertEquals(truncateDate1, truncateDate2);

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.HOUR);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.HOUR);
			Assert.assertEquals(truncateDate1, truncateDate2);
			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:01:01 001");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.HOUR);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.HOUR);
			Assert.assertEquals(truncateDate1, truncateDate2);

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.MINUTE);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.MINUTE);
			Assert.assertEquals(truncateDate1, truncateDate2);
			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:01 001");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.MINUTE);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.MINUTE);
			Assert.assertEquals(truncateDate1, truncateDate2);

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.SECOND);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.SECOND);
			Assert.assertEquals(truncateDate1, truncateDate2);
			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 001");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.SECOND);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.SECOND);
			Assert.assertEquals(truncateDate1, truncateDate2);

			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			truncateDate1 = DateUtil.truncate(date1, DatePrecision.MILLISECOND);
			truncateDate2 = DateUtil.truncate(date2, DatePrecision.MILLISECOND);
			Assert.assertEquals(truncateDate1, truncateDate2);
			date1 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			date2 = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
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
	 * @see DateUtil#getHigher(Date, Date, DatePrecision)
	 */
	@Test
	public void testGetHigher() {
		try {
			Date highDate = null;
			Date lowDate = null;

			highDate = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");

			try {
				Assert.assertEquals(null, DateUtil.getHigher(null, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertEquals(null, DateUtil.getHigher(null, null, DatePrecision.YEAR));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertEquals(highDate, DateUtil.getHigher(highDate, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertEquals(lowDate, DateUtil.getHigher(null, lowDate, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertEquals(highDate, DateUtil.getHigher(highDate, lowDate, null));
			} catch (Exception e) {
				Assert.fail();
			}

			highDate = DateUtilTest.FORMAT_DATE.parse("2002/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigher(highDate, lowDate, DatePrecision.YEAR));
			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigher(highDate, lowDate, DatePrecision.YEAR));

			highDate = DateUtilTest.FORMAT_DATE.parse("2001/02/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigher(highDate, lowDate, DatePrecision.MONTH));
			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigher(highDate, lowDate, DatePrecision.MONTH));

			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/02 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigher(highDate, lowDate, DatePrecision.DAY));
			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigher(highDate, lowDate, DatePrecision.DAY));

			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 01:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigher(highDate, lowDate, DatePrecision.HOUR));
			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigher(highDate, lowDate, DatePrecision.HOUR));

			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:01:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigher(highDate, lowDate, DatePrecision.MINUTE));
			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigher(highDate, lowDate, DatePrecision.MINUTE));

			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:01 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigher(highDate, lowDate, DatePrecision.SECOND));
			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigher(highDate, lowDate, DatePrecision.SECOND));

			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 001");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigher(highDate, lowDate, DatePrecision.MILLISECOND));
			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(highDate, DateUtil.getHigher(highDate, lowDate, DatePrecision.MILLISECOND));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La pruebas sobre el método <i><b>getLowerDate</b></i> de {@link DateUtil}
	 * 
	 * @see DateUtil#getLower(Date, Date, DatePrecision)
	 */
	@Test
	public void testGetLower() {
		try {
			Date highDate = null;
			Date lowDate = null;

			highDate = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2000/01/01 00:00:00 000");

			try {
				Assert.assertEquals(null, DateUtil.getLower(null, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertEquals(null, DateUtil.getLower(null, null, DatePrecision.YEAR));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertEquals(highDate, DateUtil.getLower(highDate, null, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertEquals(lowDate, DateUtil.getLower(null, lowDate, null));
			} catch (Exception e) {
				Assert.fail();
			}

			try {
				Assert.assertEquals(lowDate, DateUtil.getLower(highDate, lowDate, null));
			} catch (Exception e) {
				Assert.fail();
			}

			highDate = DateUtilTest.FORMAT_DATE.parse("2002/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLower(highDate, lowDate, DatePrecision.YEAR));
			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLower(highDate, lowDate, DatePrecision.YEAR));

			highDate = DateUtilTest.FORMAT_DATE.parse("2001/02/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLower(highDate, lowDate, DatePrecision.MONTH));
			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLower(highDate, lowDate, DatePrecision.MONTH));

			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/02 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLower(highDate, lowDate, DatePrecision.DAY));
			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLower(highDate, lowDate, DatePrecision.DAY));

			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 01:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLower(highDate, lowDate, DatePrecision.HOUR));
			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLower(highDate, lowDate, DatePrecision.HOUR));

			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:01:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLower(highDate, lowDate, DatePrecision.MINUTE));
			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLower(highDate, lowDate, DatePrecision.MINUTE));

			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:01 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLower(highDate, lowDate, DatePrecision.SECOND));
			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLower(highDate, lowDate, DatePrecision.SECOND));

			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 001");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLower(highDate, lowDate, DatePrecision.MILLISECOND));
			highDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			lowDate = DateUtilTest.FORMAT_DATE.parse("2001/01/01 00:00:00 000");
			Assert.assertEquals(lowDate, DateUtil.getLower(highDate, lowDate, DatePrecision.MILLISECOND));

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