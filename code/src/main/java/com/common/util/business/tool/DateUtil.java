package com.common.util.business.tool;

import static com.common.util.business.tool.VerifierUtil.checkNotNull;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * La clase que permite manipular las fechas dentro de un sistema.
 * 
 * @see Date
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.1
 */
public class DateUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Determina si la fecha recibida es una fecha de un fin de semana (sabado o domingo).
	 * 
	 * @param date
	 *            La fecha que vamos a verificar si cae un fin de semana.
	 * @return <code>true</code> en caso que la fecha recibida caiga un fin de semana, en caso contrario, retornamos <code>false</code>.
	 * @throws NullPointerException
	 *             En caso de que la fecha recibida sea nula.
	 */
	public static boolean isWeekend(Date date) {
		checkNotNull(date, "The date cannot be null");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);

		return (weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY);
	}

	/**
	 * Permite obtener el primer día del mes anterior a la fecha recibida.
	 * 
	 * <pre>
	 * DateUtil.getPreviousMonth(null)                  = NullPointerException
	 * DateUtil.getPreviousMonth("2014/01/10 00:00:00") = "2013/12/01 00:00:00"
	 * DateUtil.getPreviousMonth("2013/12/10 00:00:00") = "2013/11/01 00:00:00"
	 * DateUtil.getPreviousMonth("2014/02/01 00:00:00") = "2014/01/01 00:00:00"
	 * DateUtil.getPreviousMonth("2014/02/10 00:00:00") = "2014/01/01 00:00:00"
	 * DateUtil.getPreviousMonth("2014/02/28 00:00:00") = "2014/01/01 00:00:00"
	 * DateUtil.getPreviousMonth("2014/02/28 23:59:59") = "2014/01/01 00:00:00"
	 * </pre>
	 * 
	 * @param date
	 *            La fecha que nos permite calcular el mes anterior a la misma.
	 * @return La fecha correspondiente al primer dia del mes anterior a la fecha recibida.
	 * @throws NullPointerException
	 *             En caso de que la fecha recibida sea nula.
	 */
	public static Date getPreviousMonth(Date date) {
		checkNotNull(date, "The date cannot be null");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		Integer year = calendar.get(Calendar.YEAR);
		Integer month = calendar.get(Calendar.MONTH);

		if (month == calendar.getActualMinimum(Calendar.MONTH)) {
			year -= 1;
			month = calendar.getActualMaximum(Calendar.MONTH);
		} else {
			month--;
		}

		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));

		return calendar.getTime();
	}

	/**
	 * Permite obtener el primer día del mes siguiente a la fecha recibida.
	 * 
	 * <pre>
	 * DateUtil.getNextMonth(null)                  = NullPointerException
	 * DateUtil.getNextMonth("2014/02/01 00:00:00") = "2014/03/01 00:00:00"
	 * DateUtil.getNextMonth("2014/02/10 00:00:00") = "2014/03/01 00:00:00"
	 * DateUtil.getNextMonth("2014/02/28 00:00:00") = "2014/03/01 00:00:00"
	 * DateUtil.getNextMonth("2014/02/28 23:59:59") = "2014/03/01 00:00:00"
	 * DateUtil.getNextMonth("2014/12/10 00:00:00") = "2015/01/01 00:00:00"
	 * </pre>
	 * 
	 * @param date
	 *            La fecha que nos permite calcular el mes siguiente a la misma.
	 * @return La fecha correspondiente al primer dia del mes siguiente a la fecha recibida.
	 * @throws NullPointerException
	 *             En caso de que la fecha recibida sea nula.
	 */
	public static Date getNextMonth(Date date) {
		checkNotNull(date, "The date cannot be null");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		Integer year = calendar.get(Calendar.YEAR);
		Integer month = calendar.get(Calendar.MONTH);

		if (month == calendar.getActualMaximum(Calendar.MONTH)) {
			year += 1;
			month = calendar.getActualMinimum(Calendar.MONTH);
		} else {
			month++;
		}

		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));

		return calendar.getTime();
	}

	/**
	 * Permite obtener el primer dia del mes de una fecha recibida.
	 * 
	 * @param date
	 *            La fecha recibida a la que vamos a calcular el primer dia del mes.
	 * @return El primer dia del mes que corresponde a la fecha recibida y que ademas contiene la hora <b>00:00:00.000</b>.
	 * @throws NullPointerException
	 *             En caso de que la fecha recibida sea nula.
	 */
	public static Date getFirstDayOfMonth(Date date) {
		checkNotNull(date, "The date cannot be null");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));

		return calendar.getTime();
	}

	/**
	 * Permite obtener el último dia del mes de una fecha recibida.
	 * 
	 * @param date
	 *            La fecha recibida a la que vamos a calcular el último dia del mes.
	 * @return El último dia del mes que corresponde a la fecha recibida y que ademas contiene la hora <b>23:59:59.999</b>.
	 * @throws NullPointerException
	 *             En caso de que la fecha recibida sea nula.
	 */
	public static Date getLastDayOfMonth(Date date) {
		checkNotNull(date, "The date cannot be null");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

		return calendar.getTime();
	}
}