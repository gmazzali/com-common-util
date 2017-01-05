package com.common.util.business.service.impl;

import static com.common.util.business.tool.VerifierUtil.checkNotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.common.util.business.service.DateService;
import com.common.util.business.util.DatePrecisionEnum;

/**
 * The class to manipulate the dates.
 * 
 * @since 11/11/2014
 * @author Guillermo S. Mazzali
 * @version 1.0
 */
public class DateServiceImpl implements DateService {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(DateServiceImpl.class);

	private String dateFormat = "dd/MM/yyyy";

	@Override
	public Date getCurrentDate() {
		return new Date();
	}

	@Override
	public Date createDate(String date) {
		return StringUtils.isNotBlank(date) ? this.createDate(date, this.dateFormat) : null;
	}

	@Override
	public Date createDate(String date, String pattern) {
		checkNotNull(date, "The date cannot be null");
		checkNotNull(pattern, "The pattern cannot be null");
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (Exception e) {
			LOGGER.warn("Fail to create a date (date: " + date + ", format: " + pattern + ")", e);
			return null;
		}
	}

	@Override
	public String formatDate(Date date) {
		return date != null ? this.formatDate(date, this.dateFormat) : null;
	}

	@Override
	public String formatDate(Date date, String pattern) {
		checkNotNull(date, "The date cannot be null");
		checkNotNull(pattern, "The pattern cannot be null");
		try {
			return new SimpleDateFormat(pattern).format(date);
		} catch (Exception e) {
			LOGGER.warn("Fail to format a date (date: " + date + ", format: " + pattern + ")", e);
			return null;
		}
	}

	@Override
	public int compare(Date date, Date otherDate, DatePrecisionEnum datePrecisionEnum) {
		checkNotNull(date, "The date cannot be null");
		checkNotNull(otherDate, "The otherDate cannot be null");

		if (datePrecisionEnum == null) {
			LOGGER.debug("The precision is set to MILLISECOND");
			datePrecisionEnum = DatePrecisionEnum.MILLISECOND;
		}

		// Creamos los calendarios para comparar.
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(otherDate);

		// Si el nivel el del año.
		if (datePrecisionEnum.getLevel() <= DatePrecisionEnum.YEAR.getLevel()) {
			return calendar1.get(Calendar.YEAR) - calendar2.get(Calendar.YEAR);
		} else if (calendar1.get(Calendar.YEAR) != calendar2.get(Calendar.YEAR)) {
			return calendar1.get(Calendar.YEAR) - calendar2.get(Calendar.YEAR);
		}
		// Si hasta los años son iguales y el nivel de precisión es de mes.
		if (datePrecisionEnum.getLevel() <= DatePrecisionEnum.MONTH.getLevel()) {
			return calendar1.get(Calendar.MONTH) - calendar2.get(Calendar.MONTH);
		} else if (calendar1.get(Calendar.MONTH) != calendar2.get(Calendar.MONTH)) {
			return calendar1.get(Calendar.MONTH) - calendar2.get(Calendar.MONTH);
		}
		// Si hasta los meses son iguales y el nivel de precisión es de día.
		if (datePrecisionEnum.getLevel() <= DatePrecisionEnum.DAY.getLevel()) {
			return calendar1.get(Calendar.DAY_OF_MONTH) - calendar2.get(Calendar.DAY_OF_MONTH);
		} else if (calendar1.get(Calendar.DAY_OF_MONTH) != calendar2.get(Calendar.DAY_OF_MONTH)) {
			return calendar1.get(Calendar.DAY_OF_MONTH) - calendar2.get(Calendar.DAY_OF_MONTH);
		}
		// Si hasta los días son iguales y el nivel de precisión es de hora.
		if (datePrecisionEnum.getLevel() <= DatePrecisionEnum.HOUR.getLevel()) {
			return calendar1.get(Calendar.HOUR) - calendar2.get(Calendar.HOUR);
		} else if (calendar1.get(Calendar.HOUR) != calendar2.get(Calendar.HOUR)) {
			return calendar1.get(Calendar.HOUR) - calendar2.get(Calendar.HOUR);
		}
		// Si hasta las horas son iguales y el nivel de precisión es de minuto.
		if (datePrecisionEnum.getLevel() <= DatePrecisionEnum.MINUTE.getLevel()) {
			return calendar1.get(Calendar.MINUTE) - calendar2.get(Calendar.MINUTE);
		} else if (calendar1.get(Calendar.MINUTE) != calendar2.get(Calendar.MINUTE)) {
			return calendar1.get(Calendar.MINUTE) - calendar2.get(Calendar.MINUTE);
		}
		// Si hasta los minutos son iguales y el nivel de precisión es de segundos.
		if (datePrecisionEnum.getLevel() <= DatePrecisionEnum.SECOND.getLevel()) {
			return calendar1.get(Calendar.SECOND) - calendar2.get(Calendar.SECOND);
		} else if (calendar1.get(Calendar.SECOND) != calendar2.get(Calendar.SECOND)) {
			return calendar1.get(Calendar.SECOND) - calendar2.get(Calendar.SECOND);
		}
		// Si hasta los segundos son iguales y el nivel de precisión es de milisegundos.
		if (datePrecisionEnum.getLevel() <= DatePrecisionEnum.MILLISECOND.getLevel()) {
			return calendar1.get(Calendar.MILLISECOND) - calendar2.get(Calendar.MILLISECOND);
		} else {
			return calendar1.get(Calendar.MILLISECOND) - calendar2.get(Calendar.MILLISECOND);
		}
	}

	@Override
	public boolean before(Date date, Date beforeDate, DatePrecisionEnum datePrecisionEnum) {
		if (date == null || beforeDate == null) {
			LOGGER.warn("Any date is null");
			return false;
		}
		try {
			return this.compare(date, beforeDate, datePrecisionEnum) > 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean beforeOrEqual(Date date, Date beforeOrEqualDate, DatePrecisionEnum datePrecisionEnum) {
		if (date == null || beforeOrEqualDate == null) {
			LOGGER.warn("Any date is null");
			return false;
		}
		try {
			return this.compare(date, beforeOrEqualDate, datePrecisionEnum) >= 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean equal(Date date, Date equalDate, DatePrecisionEnum datePrecisionEnum) {
		if (date == null || equalDate == null) {
			LOGGER.warn("Any date is null");
			return false;
		}
		try {
			return this.compare(date, equalDate, datePrecisionEnum) == 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean afterOrEqual(Date date, Date afterOrEqualDate, DatePrecisionEnum datePrecisionEnum) {
		if (date == null || afterOrEqualDate == null) {
			LOGGER.warn("Any date is null");
			return false;
		}
		try {
			return this.compare(date, afterOrEqualDate, datePrecisionEnum) <= 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean after(Date date, Date afterDate, DatePrecisionEnum datePrecisionEnum) {
		if (date == null || afterDate == null) {
			LOGGER.warn("Any date is null");
			return false;
		}
		try {
			return this.compare(date, afterDate, datePrecisionEnum) < 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean between(Date date, Date beforeDate, Date afterDate, DatePrecisionEnum datePrecisionEnum) {
		if (date == null) {
			LOGGER.warn("The date is null");
			return false;
		}
		if (beforeDate == null) {
			if (afterDate == null) {
				return false;
			} else {
				return this.afterOrEqual(date, afterDate, datePrecisionEnum);
			}
		} else {
			if (afterDate == null) {
				return this.beforeOrEqual(date, beforeDate, datePrecisionEnum);
			} else {
				Date myBeforeDate = this.getLower(beforeDate, afterDate, datePrecisionEnum);
				Date myAfterDate = this.getHigher(beforeDate, afterDate, datePrecisionEnum);
				return this.beforeOrEqual(date, myBeforeDate, datePrecisionEnum) && this.afterOrEqual(date, myAfterDate, datePrecisionEnum);
			}
		}
	}

	@Override
	public Date truncate(Date date, DatePrecisionEnum datePrecisionEnum) {
		checkNotNull(date, "The date cannot be null");

		if (datePrecisionEnum == null) {
			LOGGER.info("datePrecision = MILISECOND -> return the same date");
			return date;
		}

		// Seteamos la instancia del calendario.
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		if (datePrecisionEnum.getLevel() < DatePrecisionEnum.MILLISECOND.getLevel()) {
			calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
		}
		if (datePrecisionEnum.getLevel() < DatePrecisionEnum.SECOND.getLevel()) {
			calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		}
		if (datePrecisionEnum.getLevel() < DatePrecisionEnum.MINUTE.getLevel()) {
			calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		}
		if (datePrecisionEnum.getLevel() < DatePrecisionEnum.HOUR.getLevel()) {
			calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		}
		if (datePrecisionEnum.getLevel() < DatePrecisionEnum.DAY.getLevel()) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		}
		if (datePrecisionEnum.getLevel() < DatePrecisionEnum.MONTH.getLevel()) {
			calendar.set(Calendar.MONTH, calendar.getActualMinimum(Calendar.MONTH));
		}
		if (datePrecisionEnum.getLevel() < DatePrecisionEnum.YEAR.getLevel()) {
			calendar.set(Calendar.YEAR, calendar.getActualMinimum(Calendar.YEAR));
		}

		return calendar.getTime();
	}

	@Override
	public Date getHigher(Date date1, Date date2, DatePrecisionEnum datePrecisionEnum) {
		if (date1 == null) {
			LOGGER.info("The first date is null");
			return date2;
		}
		if (date2 == null) {
			LOGGER.info("The second date is null");
			return date1;
		}
		if (datePrecisionEnum == null) {
			LOGGER.info("datePrecision = MILISECOND");
			datePrecisionEnum = DatePrecisionEnum.MILLISECOND;
		}
		if (this.beforeOrEqual(date1, date2, datePrecisionEnum)) {
			return date1;
		} else {
			return date2;
		}
	}

	@Override
	public Date getLower(Date date1, Date date2, DatePrecisionEnum datePrecisionEnum) {
		if (date1 == null) {
			LOGGER.info("The first date is null");
			return date2;
		}
		if (date2 == null) {
			LOGGER.info("The second date is null");
			return date1;
		}
		if (datePrecisionEnum == null) {
			LOGGER.info("datePrecision = MILISECOND");
			datePrecisionEnum = DatePrecisionEnum.MILLISECOND;
		}
		if (this.afterOrEqual(date1, date2, datePrecisionEnum)) {
			return date1;
		} else {
			return date2;
		}
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
}