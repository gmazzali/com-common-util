package com.common.util.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.common.util.business.util.DatePrecisionEnum;

@RunWith(MockitoJUnitRunner.class)
public class DateServiceImplTest {

	private static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss SSS");

	@InjectMocks
	private DateServiceImpl service;

	@BeforeClass
	public static void initClass() {
		BasicConfigurator.configure();
	}

	/**
	 * La pruebas sobre el método {@link DateServiceImpl#createDate(String, String)}
	 */
	@Test
	public void testCreateDateString() throws Exception {
		assertNull(this.service.createDate(null));
		assertNull(this.service.createDate(""));
		assertNull(this.service.createDate("ASD"));
		assertNotNull(this.service.createDate("01/01/2014"));
		try {
			this.service.createDate(null, null);
			fail();
		} catch (Exception e) {
		}
		try {
			this.service.createDate("2000/01/01 00:00:00 000", null);
			fail();
		} catch (Exception e) {
		}
		try {
			this.service.createDate(null, "yyyy/MM/dd HH:mm:ss SSS");
			fail();
		} catch (Exception e) {
		}
		assertNull(this.service.createDate("ASD", "yyyy/MM/dd HH:mm:ss SSS"));
		assertNull(this.service.createDate("2000/01/01 00:00:00 000", "ASD"));
		assertNotNull(this.service.createDate("2014/01/01 00:00:00 000", "yyyy/MM/dd HH:mm:ss SSS"));
	}

	/**
	 * La pruebas sobre el método {@link DateServiceImpl#formatDate(Date, String)}
	 */
	@Test
	public void testFormatDateDateString() throws Exception {
		assertNull(this.service.formatDate(null));
		assertNotNull(this.service.formatDate(new Date()));
		try {
			this.service.formatDate(null, null);
			fail();
		} catch (Exception e) {
		}
		try {
			this.service.formatDate(new Date(), null);
			fail();
		} catch (Exception e) {
		}
		try {
			this.service.formatDate(null, "yyyy/MM/dd HH:mm:ss SSS");
			fail();
		} catch (Exception e) {
		}
		assertNull(this.service.formatDate(new Date(), "ASD"));
		assertNotNull(this.service.formatDate(new Date(), "yyyy/MM/dd HH:mm:ss SSS"));
	}

	/**
	 * La pruebas sobre el método {@link DateServiceImpl#compare(Date, Date, DatePrecisionEnum)}
	 */
	@Test
	public void testCompare() throws Exception {
		Date date1 = null;
		Date date2 = null;

		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");

		try {
			assertTrue(this.service.compare(null, null, null) > 0);
			fail();
		} catch (Exception e) {
		}

		try {
			assertTrue(this.service.compare(date1, null, null) > 0);
			fail();
		} catch (Exception e) {
		}

		try {
			assertTrue(this.service.compare(null, date2, null) > 0);
			fail();
		} catch (Exception e) {
		}

		try {
			assertTrue(this.service.compare(date1, date2, null) == 0);
		} catch (Exception e) {
			fail();
		}

		try {
			assertTrue(this.service.compare(null, null, DatePrecisionEnum.YEAR) > 0);
			fail();
		} catch (Exception e) {
		}

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.YEAR) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.YEAR) == 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.YEAR) < 0);

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MONTH) > 0);
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MONTH) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MONTH) == 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MONTH) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MONTH) < 0);

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.DAY) > 0);
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.DAY) > 0);
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.DAY) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.DAY) == 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.DAY) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.DAY) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.DAY) < 0);

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.HOUR) > 0);
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.HOUR) > 0);
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.HOUR) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.HOUR) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.HOUR) == 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.HOUR) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.HOUR) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.HOUR) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.HOUR) < 0);

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MINUTE) > 0);
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MINUTE) > 0);
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MINUTE) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MINUTE) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MINUTE) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MINUTE) == 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MINUTE) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MINUTE) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MINUTE) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MINUTE) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MINUTE) < 0);

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.SECOND) > 0);
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.SECOND) > 0);
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.SECOND) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.SECOND) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.SECOND) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.SECOND) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.SECOND) == 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.SECOND) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.SECOND) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.SECOND) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.SECOND) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.SECOND) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.SECOND) < 0);

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MILLISECOND) > 0);
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MILLISECOND) > 0);
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MILLISECOND) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MILLISECOND) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MILLISECOND) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MILLISECOND) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 001");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MILLISECOND) > 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MILLISECOND) == 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 001");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MILLISECOND) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MILLISECOND) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MILLISECOND) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MILLISECOND) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MILLISECOND) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MILLISECOND) < 0);
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.compare(date1, date2, DatePrecisionEnum.MILLISECOND) < 0);
	}

	/**
	 * La pruebas sobre el método {@link DateServiceImpl#before(Date, Date, DatePrecisionEnum)}
	 */
	@Test
	public void testBefore() throws Exception {
		Date date1 = null;
		Date date2 = null;

		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");

		try {
			assertFalse(this.service.before(null, null, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.before(date1, null, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.before(null, date2, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.before(date1, date2, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.before(null, null, DatePrecisionEnum.YEAR));
		} catch (Exception e) {
			fail();
		}

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.YEAR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.YEAR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.YEAR));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MONTH));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.DAY));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.HOUR));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MINUTE));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.SECOND));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 001");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.before(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 001");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.before(date1, date2, DatePrecisionEnum.MILLISECOND));
	}

	/**
	 * La pruebas sobre el método {@link DateServiceImpl#beforeOrEqual(Date, Date, DatePrecisionEnum)}
	 */
	@Test
	public void testBeforeOrEqual() throws Exception {
		Date date1 = null;
		Date date2 = null;

		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");

		try {
			assertFalse(this.service.beforeOrEqual(null, null, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.beforeOrEqual(date1, null, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.beforeOrEqual(null, date2, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertTrue(this.service.beforeOrEqual(date1, date2, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.beforeOrEqual(null, null, DatePrecisionEnum.YEAR));
		} catch (Exception e) {
			fail();
		}

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.YEAR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.YEAR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.YEAR));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MONTH));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.DAY));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.HOUR));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MINUTE));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.SECOND));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 001");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 001");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.beforeOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
	}

	/**
	 * La pruebas sobre el método {@link DateServiceImpl#equal(Date, Date, DatePrecisionEnum)}
	 */
	@Test
	public void testEqual() throws Exception {
		Date date1 = null;
		Date date2 = null;

		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");

		try {
			assertFalse(this.service.equal(null, null, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.equal(date1, null, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.equal(null, date2, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertTrue(this.service.equal(date1, date2, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.equal(null, null, DatePrecisionEnum.YEAR));
		} catch (Exception e) {
			fail();
		}

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.YEAR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.equal(date1, date2, DatePrecisionEnum.YEAR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.YEAR));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.equal(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MONTH));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.equal(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.DAY));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.equal(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.HOUR));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.equal(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MINUTE));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.equal(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.SECOND));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 001");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.equal(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 001");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.equal(date1, date2, DatePrecisionEnum.MILLISECOND));
	}

	/**
	 * La pruebas sobre el método {@link DateServiceImpl#afterOrEqual(Date, Date, DatePrecisionEnum)}
	 */
	@Test
	public void testAfterOrEqual() throws Exception {
		Date date1 = null;
		Date date2 = null;

		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");

		try {
			assertFalse(this.service.afterOrEqual(null, null, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.afterOrEqual(date1, null, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.afterOrEqual(null, date2, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertTrue(this.service.afterOrEqual(date1, date2, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.afterOrEqual(null, null, DatePrecisionEnum.YEAR));
		} catch (Exception e) {
			fail();
		}

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.YEAR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.YEAR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.YEAR));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MONTH));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.DAY));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.HOUR));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MINUTE));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.SECOND));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 001");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 001");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.afterOrEqual(date1, date2, DatePrecisionEnum.MILLISECOND));
	}

	/**
	 * La pruebas sobre el método {@link DateServiceImpl#after(Date, Date, DatePrecisionEnum)}
	 */
	@Test
	public void testAfter() throws Exception {
		Date date1 = null;
		Date date2 = null;

		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");

		try {
			assertFalse(this.service.after(null, null, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.after(date1, null, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.after(null, date2, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.after(date1, date2, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.after(null, null, DatePrecisionEnum.YEAR));
		} catch (Exception e) {
			fail();
		}

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.YEAR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.YEAR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.YEAR));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.MONTH));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.MONTH));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.DAY));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.DAY));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.HOUR));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.HOUR));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.MINUTE));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.MINUTE));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.SECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.SECOND));

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 001");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		assertFalse(this.service.after(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 001");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:01 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:01:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 01:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/02 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/02/01 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.MILLISECOND));
		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.after(date1, date2, DatePrecisionEnum.MILLISECOND));
	}

	/**
	 * La pruebas sobre el método {@link DateServiceImpl#between(Date, Date, Date, DatePrecisionEnum)}
	 */
	@Test
	public void testBetween() throws Exception {
		Date date = null;
		Date after = null;
		Date before = null;

		date = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2000/01/01 00:00:00 000");

		try {
			assertFalse(this.service.between(null, null, null, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.between(date, null, null, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.between(null, after, null, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.between(null, null, before, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.between(null, null, null, DatePrecisionEnum.YEAR));
		} catch (Exception e) {
			fail();
		}

		try {
			assertFalse(this.service.between(date, null, null, DatePrecisionEnum.YEAR));
		} catch (Exception e) {
			fail();
		}

		// YEAR
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2002/01/01 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.YEAR));
		date = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.YEAR));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.YEAR));
		date = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2002/01/01 00:00:00 000");
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.YEAR));

		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		after = null;
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.YEAR));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = null;
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.YEAR));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2002/01/01 00:00:00 000");
		after = null;
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.YEAR));

		date = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.YEAR));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.YEAR));
		date = FORMAT_DATE.parse("2002/01/01 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.YEAR));

		// MONTH
		date = FORMAT_DATE.parse("2001/02/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/03/01 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MONTH));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/02/01 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MONTH));
		date = FORMAT_DATE.parse("2001/02/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/02/01 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MONTH));
		date = FORMAT_DATE.parse("2001/03/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/02/01 00:00:00 000");
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.MONTH));

		date = FORMAT_DATE.parse("2001/02/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = null;
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MONTH));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = null;
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MONTH));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/02/01 00:00:00 000");
		after = null;
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.MONTH));

		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/02/01 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MONTH));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MONTH));
		date = FORMAT_DATE.parse("2001/02/01 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.MONTH));

		// DAY
		date = FORMAT_DATE.parse("2001/01/02 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/03 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.DAY));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/02 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.DAY));
		date = FORMAT_DATE.parse("2001/01/02 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/02 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.DAY));
		date = FORMAT_DATE.parse("2001/01/03 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/02 00:00:00 000");
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.DAY));

		date = FORMAT_DATE.parse("2001/01/02 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = null;
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.DAY));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = null;
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.DAY));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/02 00:00:00 000");
		after = null;
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.DAY));

		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/02 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.DAY));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.DAY));
		date = FORMAT_DATE.parse("2001/01/02 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.DAY));

		// HOUR
		date = FORMAT_DATE.parse("2001/01/01 01:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 02:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.HOUR));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 01:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.HOUR));
		date = FORMAT_DATE.parse("2001/01/01 01:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 01:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.HOUR));
		date = FORMAT_DATE.parse("2001/01/01 02:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 01:00:00 000");
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.HOUR));

		date = FORMAT_DATE.parse("2001/01/01 01:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = null;
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.HOUR));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = null;
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.HOUR));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/02 01:00:00 000");
		after = null;
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.HOUR));

		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 01:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.HOUR));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.HOUR));
		date = FORMAT_DATE.parse("2001/01/01 01:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.HOUR));

		// MINUTE
		date = FORMAT_DATE.parse("2001/01/01 00:01:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 00:02:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MINUTE));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 00:01:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MINUTE));
		date = FORMAT_DATE.parse("2001/01/01 00:01:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 00:01:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MINUTE));
		date = FORMAT_DATE.parse("2001/01/01 00:02:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 00:01:00 000");
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.MINUTE));

		date = FORMAT_DATE.parse("2001/01/01 00:01:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = null;
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MINUTE));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = null;
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MINUTE));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/02 00:01:00 000");
		after = null;
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.MINUTE));

		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:01:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MINUTE));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MINUTE));
		date = FORMAT_DATE.parse("2001/01/01 00:01:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.MINUTE));

		// SECOND
		date = FORMAT_DATE.parse("2001/01/01 00:00:01 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 00:00:02 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.SECOND));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 00:00:01 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.SECOND));
		date = FORMAT_DATE.parse("2001/01/01 00:00:01 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 00:00:01 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.SECOND));
		date = FORMAT_DATE.parse("2001/01/01 00:00:02 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 00:00:01 000");
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.SECOND));

		date = FORMAT_DATE.parse("2001/01/01 00:00:01 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = null;
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.SECOND));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = null;
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.SECOND));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/02 00:00:01 000");
		after = null;
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.SECOND));

		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:01 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.SECOND));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.SECOND));
		date = FORMAT_DATE.parse("2001/01/01 00:00:01 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.SECOND));

		// MILLISECOND
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 001");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 002");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MILLISECOND));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 001");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MILLISECOND));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 001");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 001");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MILLISECOND));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 002");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 001");
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.MILLISECOND));

		date = FORMAT_DATE.parse("2001/01/01 00:00:00 001");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = null;
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MILLISECOND));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		after = null;
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MILLISECOND));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = FORMAT_DATE.parse("2001/01/02 00:00:00 001");
		after = null;
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.MILLISECOND));

		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 001");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MILLISECOND));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertTrue(this.service.between(date, before, after, DatePrecisionEnum.MILLISECOND));
		date = FORMAT_DATE.parse("2001/01/01 00:00:00 001");
		before = null;
		after = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertFalse(this.service.between(date, before, after, DatePrecisionEnum.MILLISECOND));
	}

	/**
	 * La pruebas sobre el método {@link DateServiceImpl#truncate(Date, DatePrecisionEnum)}
	 */
	@Test
	public void testTruncate() throws Exception {
		Date date1 = null;
		Date date2 = null;
		Date truncateDate1 = null;
		Date truncateDate2 = null;

		date1 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2000/01/01 00:00:00 000");

		try {
			this.service.truncate(null, null);
			fail();
		} catch (Exception e) {
		}

		try {
			assertEquals(date1, this.service.truncate(date1, null));
		} catch (Exception e) {
			fail();
		}

		try {
			this.service.truncate(null, DatePrecisionEnum.YEAR);
			fail();
		} catch (Exception e) {
		}

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		truncateDate1 = this.service.truncate(date1, DatePrecisionEnum.YEAR);
		truncateDate2 = this.service.truncate(date2, DatePrecisionEnum.YEAR);
		assertEquals(truncateDate1, truncateDate2);
		date1 = FORMAT_DATE.parse("2001/02/02 01:01:01 001");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		truncateDate1 = this.service.truncate(date1, DatePrecisionEnum.YEAR);
		truncateDate2 = this.service.truncate(date2, DatePrecisionEnum.YEAR);
		assertEquals(truncateDate1, truncateDate2);

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		truncateDate1 = this.service.truncate(date1, DatePrecisionEnum.MONTH);
		truncateDate2 = this.service.truncate(date2, DatePrecisionEnum.MONTH);
		assertEquals(truncateDate1, truncateDate2);
		date1 = FORMAT_DATE.parse("2001/01/02 01:01:01 001");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		truncateDate1 = this.service.truncate(date1, DatePrecisionEnum.MONTH);
		truncateDate2 = this.service.truncate(date2, DatePrecisionEnum.MONTH);
		assertEquals(truncateDate1, truncateDate2);

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		truncateDate1 = this.service.truncate(date1, DatePrecisionEnum.DAY);
		truncateDate2 = this.service.truncate(date2, DatePrecisionEnum.DAY);
		assertEquals(truncateDate1, truncateDate2);
		date1 = FORMAT_DATE.parse("2001/01/01 01:01:01 001");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		truncateDate1 = this.service.truncate(date1, DatePrecisionEnum.DAY);
		truncateDate2 = this.service.truncate(date2, DatePrecisionEnum.DAY);
		assertEquals(truncateDate1, truncateDate2);

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		truncateDate1 = this.service.truncate(date1, DatePrecisionEnum.HOUR);
		truncateDate2 = this.service.truncate(date2, DatePrecisionEnum.HOUR);
		assertEquals(truncateDate1, truncateDate2);
		date1 = FORMAT_DATE.parse("2001/01/01 00:01:01 001");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		truncateDate1 = this.service.truncate(date1, DatePrecisionEnum.HOUR);
		truncateDate2 = this.service.truncate(date2, DatePrecisionEnum.HOUR);
		assertEquals(truncateDate1, truncateDate2);

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		truncateDate1 = this.service.truncate(date1, DatePrecisionEnum.MINUTE);
		truncateDate2 = this.service.truncate(date2, DatePrecisionEnum.MINUTE);
		assertEquals(truncateDate1, truncateDate2);
		date1 = FORMAT_DATE.parse("2001/01/01 00:00:01 001");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		truncateDate1 = this.service.truncate(date1, DatePrecisionEnum.MINUTE);
		truncateDate2 = this.service.truncate(date2, DatePrecisionEnum.MINUTE);
		assertEquals(truncateDate1, truncateDate2);

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		truncateDate1 = this.service.truncate(date1, DatePrecisionEnum.SECOND);
		truncateDate2 = this.service.truncate(date2, DatePrecisionEnum.SECOND);
		assertEquals(truncateDate1, truncateDate2);
		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 001");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		truncateDate1 = this.service.truncate(date1, DatePrecisionEnum.SECOND);
		truncateDate2 = this.service.truncate(date2, DatePrecisionEnum.SECOND);
		assertEquals(truncateDate1, truncateDate2);

		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		truncateDate1 = this.service.truncate(date1, DatePrecisionEnum.MILLISECOND);
		truncateDate2 = this.service.truncate(date2, DatePrecisionEnum.MILLISECOND);
		assertEquals(truncateDate1, truncateDate2);
		date1 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		date2 = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		truncateDate1 = this.service.truncate(date1, DatePrecisionEnum.MILLISECOND);
		truncateDate2 = this.service.truncate(date2, DatePrecisionEnum.MILLISECOND);
		assertEquals(truncateDate1, truncateDate2);
	}

	/**
	 * La pruebas sobre el método {@link DateServiceImpl#getHigher(Date, Date, DatePrecisionEnum)}
	 */
	@Test
	public void testGetHigher() throws Exception {
		Date highDate = null;
		Date lowDate = null;

		highDate = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2000/01/01 00:00:00 000");

		try {
			assertEquals(null, this.service.getHigher(null, null, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertEquals(null, this.service.getHigher(null, null, DatePrecisionEnum.YEAR));
		} catch (Exception e) {
			fail();
		}

		try {
			assertEquals(highDate, this.service.getHigher(highDate, null, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertEquals(lowDate, this.service.getHigher(null, lowDate, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertEquals(highDate, this.service.getHigher(highDate, lowDate, null));
		} catch (Exception e) {
			fail();
		}

		highDate = FORMAT_DATE.parse("2002/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(highDate, this.service.getHigher(highDate, lowDate, DatePrecisionEnum.YEAR));
		highDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(highDate, this.service.getHigher(highDate, lowDate, DatePrecisionEnum.YEAR));

		highDate = FORMAT_DATE.parse("2001/02/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(highDate, this.service.getHigher(highDate, lowDate, DatePrecisionEnum.MONTH));
		highDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(highDate, this.service.getHigher(highDate, lowDate, DatePrecisionEnum.MONTH));

		highDate = FORMAT_DATE.parse("2001/01/02 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(highDate, this.service.getHigher(highDate, lowDate, DatePrecisionEnum.DAY));
		highDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(highDate, this.service.getHigher(highDate, lowDate, DatePrecisionEnum.DAY));

		highDate = FORMAT_DATE.parse("2001/01/01 01:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(highDate, this.service.getHigher(highDate, lowDate, DatePrecisionEnum.HOUR));
		highDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(highDate, this.service.getHigher(highDate, lowDate, DatePrecisionEnum.HOUR));

		highDate = FORMAT_DATE.parse("2001/01/01 00:01:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(highDate, this.service.getHigher(highDate, lowDate, DatePrecisionEnum.MINUTE));
		highDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(highDate, this.service.getHigher(highDate, lowDate, DatePrecisionEnum.MINUTE));

		highDate = FORMAT_DATE.parse("2001/01/01 00:00:01 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(highDate, this.service.getHigher(highDate, lowDate, DatePrecisionEnum.SECOND));
		highDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(highDate, this.service.getHigher(highDate, lowDate, DatePrecisionEnum.SECOND));

		highDate = FORMAT_DATE.parse("2001/01/01 00:00:00 001");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(highDate, this.service.getHigher(highDate, lowDate, DatePrecisionEnum.MILLISECOND));
		highDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(highDate, this.service.getHigher(highDate, lowDate, DatePrecisionEnum.MILLISECOND));
	}

	/**
	 * La pruebas sobre el método {@link DateServiceImpl#getLower(Date, Date, DatePrecisionEnum)}
	 */
	@Test
	public void testGetLower() throws Exception {
		Date highDate = null;
		Date lowDate = null;

		highDate = FORMAT_DATE.parse("2000/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2000/01/01 00:00:00 000");

		try {
			assertEquals(null, this.service.getLower(null, null, null));
		} catch (Exception e) {
			fail();
		}
		try {
			assertEquals(null, this.service.getLower(null, null, DatePrecisionEnum.YEAR));
		} catch (Exception e) {
			fail();
		}

		try {
			assertEquals(highDate, this.service.getLower(highDate, null, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertEquals(lowDate, this.service.getLower(null, lowDate, null));
		} catch (Exception e) {
			fail();
		}

		try {
			assertEquals(lowDate, this.service.getLower(highDate, lowDate, null));
		} catch (Exception e) {
			fail();
		}

		highDate = FORMAT_DATE.parse("2002/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(lowDate, this.service.getLower(highDate, lowDate, DatePrecisionEnum.YEAR));
		highDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(lowDate, this.service.getLower(highDate, lowDate, DatePrecisionEnum.YEAR));

		highDate = FORMAT_DATE.parse("2001/02/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(lowDate, this.service.getLower(highDate, lowDate, DatePrecisionEnum.MONTH));
		highDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(lowDate, this.service.getLower(highDate, lowDate, DatePrecisionEnum.MONTH));

		highDate = FORMAT_DATE.parse("2001/01/02 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(lowDate, this.service.getLower(highDate, lowDate, DatePrecisionEnum.DAY));
		highDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(lowDate, this.service.getLower(highDate, lowDate, DatePrecisionEnum.DAY));

		highDate = FORMAT_DATE.parse("2001/01/01 01:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(lowDate, this.service.getLower(highDate, lowDate, DatePrecisionEnum.HOUR));
		highDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(lowDate, this.service.getLower(highDate, lowDate, DatePrecisionEnum.HOUR));

		highDate = FORMAT_DATE.parse("2001/01/01 00:01:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(lowDate, this.service.getLower(highDate, lowDate, DatePrecisionEnum.MINUTE));
		highDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(lowDate, this.service.getLower(highDate, lowDate, DatePrecisionEnum.MINUTE));

		highDate = FORMAT_DATE.parse("2001/01/01 00:00:01 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(lowDate, this.service.getLower(highDate, lowDate, DatePrecisionEnum.SECOND));
		highDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(lowDate, this.service.getLower(highDate, lowDate, DatePrecisionEnum.SECOND));

		highDate = FORMAT_DATE.parse("2001/01/01 00:00:00 001");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(lowDate, this.service.getLower(highDate, lowDate, DatePrecisionEnum.MILLISECOND));
		highDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		lowDate = FORMAT_DATE.parse("2001/01/01 00:00:00 000");
		assertEquals(lowDate, this.service.getLower(highDate, lowDate, DatePrecisionEnum.MILLISECOND));
	}
}