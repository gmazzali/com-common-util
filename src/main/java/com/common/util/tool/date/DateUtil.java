package com.common.util.tool.date;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.common.util.exception.UncheckedException;

/**
 * La clase que permite manipular las fechas dentro de un sistema.
 * 
 * @see Date
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class DateUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(DateUtil.class);

	public static final long HOURS_PER_DAY = 24;

	public static final long MINUTES_PER_HOUR = 60;

	public static final long SECONDS_PER_MINUTE = 60;

	public static final long MILLISECONDS_PER_DAY = DateUtil.HOURS_PER_DAY * DateUtil.MINUTES_PER_HOUR * DateUtil.SECONDS_PER_MINUTE * 1000;

	/**
	 * Crea una fecha de acuerdo a una cadena que contiene la misma y al patron de fecha que se recibe.
	 * 
	 * @param date
	 *            La cadena que contiene la fecha que vamos a crear.
	 * @param pattern
	 *            El patrón con el que va a leer la fecha que recibimos anteriormente.
	 * @return La fecha correctamente parseada de acuerdo al patrón recibido.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros sea nulo o que falle el parseo de la fecha.
	 */
	public static Date createDate(String date, String pattern) {
		// Verificamos que los parámetros no sean nulos.
		if (date == null) {
			log.warn("The date cannot be null.");
			throw new UncheckedException("The date cannot be null.");
		}

		if (pattern == null) {
			log.warn("The pattern cannot be null.");
			throw new UncheckedException("The pattern cannot be null.");
		}

		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			throw new UncheckedException(e.getMessage());
		}
	}

	/**
	 * Compara las 2 fechas que recibe para determinar cual de las 2 fechas es menor a la otra de acuerdo a un nivel de precisión recibido.
	 * 
	 * @param date
	 *            la primer fecha a comparar.
	 * @param otherDate
	 *            la segunda fecha a comparar.
	 * @param datePrecision
	 *            La precisión con la que va a compararse la fecha.
	 * 
	 * @return Un valor positivo si la fecha <i>date</i> es mayor a la fecha <i>otherDate</i>, un valor cero (0) si las 2 fechas son iguales o un
	 *         valor negativo si la fecha <i>otherDate</i> es mayor a la fecha <i>date</i>.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros recibidos sea inválidos o nulos.
	 */
	public static int compare(Date date, Date otherDate, DatePrecision datePrecision) {
		// Verificamos que los parámetros no sean nulos.
		if (date == null || otherDate == null) {
			log.warn("The dates cannot be null.");
			throw new UncheckedException("The dates cannot be null.");
		}

		if (datePrecision == null) {
			log.warn("The precision cannot be null.");
			throw new UncheckedException("The precision cannot be null.");
		}

		// Creamos los calendarios para comparar.
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(otherDate);

		// Si el nivel el del año.
		if (datePrecision.getLevel() <= DatePrecision.YEAR.getLevel()) {
			return calendar1.get(Calendar.YEAR) - calendar2.get(Calendar.YEAR);
		} else if (calendar1.get(Calendar.YEAR) != calendar2.get(Calendar.YEAR)) {
			return calendar1.get(Calendar.YEAR) - calendar2.get(Calendar.YEAR);
		}

		// Si hasta los años son iguales y el nivel de precisión es de mes.
		if (datePrecision.getLevel() <= DatePrecision.MONTH.getLevel()) {
			return calendar1.get(Calendar.MONTH) - calendar2.get(Calendar.MONTH);
		} else if (calendar1.get(Calendar.MONTH) != calendar2.get(Calendar.MONTH)) {
			return calendar1.get(Calendar.MONTH) - calendar2.get(Calendar.MONTH);
		}

		// Si hasta los meses son iguales y el nivel de precisión es de día.
		if (datePrecision.getLevel() <= DatePrecision.DAY.getLevel()) {
			return calendar1.get(Calendar.DAY_OF_MONTH) - calendar2.get(Calendar.DAY_OF_MONTH);
		} else if (calendar1.get(Calendar.DAY_OF_MONTH) != calendar2.get(Calendar.DAY_OF_MONTH)) {
			return calendar1.get(Calendar.DAY_OF_MONTH) - calendar2.get(Calendar.DAY_OF_MONTH);
		}

		// Si hasta los días son iguales y el nivel de precisión es de hora.
		if (datePrecision.getLevel() <= DatePrecision.HOUR.getLevel()) {
			return calendar1.get(Calendar.HOUR) - calendar2.get(Calendar.HOUR);
		} else if (calendar1.get(Calendar.HOUR) != calendar2.get(Calendar.HOUR)) {
			return calendar1.get(Calendar.HOUR) - calendar2.get(Calendar.HOUR);
		}

		// Si hasta las horas son iguales y el nivel de precisión es de minuto.
		if (datePrecision.getLevel() <= DatePrecision.MINUTE.getLevel()) {
			return calendar1.get(Calendar.MINUTE) - calendar2.get(Calendar.MINUTE);
		} else if (calendar1.get(Calendar.MINUTE) != calendar2.get(Calendar.MINUTE)) {
			return calendar1.get(Calendar.MINUTE) - calendar2.get(Calendar.MINUTE);
		}

		// Si hasta los minutos son iguales y el nivel de precisión es de segundos.
		if (datePrecision.getLevel() <= DatePrecision.SECOND.getLevel()) {
			return calendar1.get(Calendar.SECOND) - calendar2.get(Calendar.SECOND);
		} else if (calendar1.get(Calendar.SECOND) != calendar2.get(Calendar.SECOND)) {
			return calendar1.get(Calendar.SECOND) - calendar2.get(Calendar.SECOND);
		}

		// Si hasta los segundos son iguales y el nivel de precisión es de milisegundos.
		if (datePrecision.getLevel() <= DatePrecision.MILLISECOND.getLevel()) {
			return calendar1.get(Calendar.MILLISECOND) - calendar2.get(Calendar.MILLISECOND);
		} else {
			return calendar1.get(Calendar.MILLISECOND) - calendar2.get(Calendar.MILLISECOND);
		}
	}

	/**
	 * Determina si la fecha <i>beforeDate</i> es menor a la fecha <i>date</i> de acuerdo al nivel de precisión recibida. Este método es null-safe.
	 * 
	 * @param date
	 *            la fecha que vamos a comparar.
	 * @param beforeDate
	 *            la fecha que consideramos que es anterior a la fecha de comparación.
	 * @param datePrecision
	 *            La precisión con la que van a compararse las fechas. Si es <code>null</code> se toma la precisión {@link DatePrecision#MILLISECOND}.
	 * @return <i>true</i> en caso que la fecha <i>beforeDate</i> es anterior a la fecha <i>date</i>, en caso contrario, o en caso de que alguno de
	 *         los parámetros recibidos sea <code>null</code>, retornamos <i>false</i>.
	 */
	public static boolean before(Date date, Date beforeDate, DatePrecision datePrecision) {
		if (datePrecision == null) {
			datePrecision = DatePrecision.MILLISECOND;
		}

		try {
			return DateUtil.compare(date, beforeDate, datePrecision) > 0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determina si la fecha <i>beforeOrEqualDate</i> es menor o igual la fecha <i>date</i> de acuerdo al nivel de precisión recibida. Este método es
	 * null-safe.
	 * 
	 * @param date
	 *            la fecha que vamos a comparar.
	 * @param beforeOrEqualDate
	 *            la fecha que consideramos que es anterior o igual a la fecha de comparación.
	 * @param datePrecision
	 *            La precisión con la que van a compararse las fechas. Si es <code>null</code> se toma la precisión {@link DatePrecision#MILLISECOND}.
	 * @return <i>true</i> en caso que la fecha <i>beforeOrEqualDate</i> es anterior o igual a la fecha <i>date</i>, en caso contrario, o en caso de
	 *         que alguno de los parámetros recibidos sea <code>null</code>, retornamos <i>false</i>.
	 */
	public static boolean beforeOrEqual(Date date, Date beforeOrEqualDate, DatePrecision datePrecision) {
		if (datePrecision == null) {
			datePrecision = DatePrecision.MILLISECOND;
		}

		try {
			return DateUtil.compare(date, beforeOrEqualDate, datePrecision) >= 0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determina si la fecha <i>equalDate</i> es igual la fecha <i>date</i> de acuerdo al nivel de precisión recibida. Este método es null-safe.
	 * 
	 * @param date
	 *            la fecha que vamos a comparar.
	 * @param equalDate
	 *            la fecha que consideramos que es igual a la fecha de comparación.
	 * @param datePrecision
	 *            La precisión con la que van a compararse las fechas. Si es <code>null</code> se toma la precisión {@link DatePrecision#MILLISECOND}.
	 * @return <i>true</i> en caso que la fecha <i>equalDate</i> es igual a la fecha <i>date</i>, en caso contrario, o en caso de que alguno de los
	 *         parámetros recibidos sea <code>null</code>, retornamos <i>false</i>.
	 */
	public static boolean equal(Date date, Date equalDate, DatePrecision datePrecision) {
		if (datePrecision == null) {
			datePrecision = DatePrecision.MILLISECOND;
		}

		try {
			return DateUtil.compare(date, equalDate, datePrecision) == 0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determina si la fecha <i>afterOrEqualDate</i> es mayor a la fecha <i>date</i> de acuerdo al nivel de precisión recibida. Este método es
	 * null-safe.
	 * 
	 * @param date
	 *            la fecha que vamos a comparar.
	 * @param afterOrEqualDate
	 *            la fecha que consideramos que es mayor o igual a la fecha de comparación.
	 * @param datePrecision
	 *            La precisión con la que van a compararse las fechas. Si es <code>null</code> se toma la precisión {@link DatePrecision#MILLISECOND}.
	 * @return <i>true</i> en caso que la fecha <i>afterOrEqualDate</i> es posterior o igual a la fecha <i>date</i>, en caso contrario, o en caso de
	 *         que alguno de los parámetros recibidos sea <code>null</code>, retornamos <i>false</i>.
	 */
	public static boolean afterOrEqual(Date date, Date afterOrEqualDate, DatePrecision datePrecision) {
		if (datePrecision == null) {
			datePrecision = DatePrecision.MILLISECOND;
		}

		try {
			return DateUtil.compare(date, afterOrEqualDate, datePrecision) <= 0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determina si la fecha <i>afterDate</i> es mayor a la fecha <i>date</i> de acuerdo al nivel de precisión recibida. Este método es null-safe.
	 * 
	 * @param date
	 *            la fecha que vamos a comparar.
	 * @param afterDate
	 *            la fecha que consideramos que es mayor a la fecha de comparación.
	 * @param datePrecision
	 *            La precisión con la que van a compararse las fechas. Si es <code>null</code> se toma la precisión {@link DatePrecision#MILLISECOND}.
	 * @return <i>true</i> en caso que la fecha <i>afterDate</i> es posterior a la fecha <i>date</i>, en caso contrario, o en caso de que alguno de
	 *         los parámetros recibidos sea <code>null</code>, retornamos <i>false</i>.
	 */
	public static boolean after(Date date, Date afterDate, DatePrecision datePrecision) {
		if (datePrecision == null) {
			datePrecision = DatePrecision.MILLISECOND;
		}

		try {
			return DateUtil.compare(date, afterDate, datePrecision) < 0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determina si la fecha <i>beforeDate</i> es menor a la fecha <i>date</i> y la fecha <i>afterDate</i> es mayor a la fecha <i>date</i> de acuerdo
	 * al nivel de precisión recibida. En caso de recibir un valor <code>null</code> para alguna de las fechas <i>beforeDate</i> o <i>afterDate</i> se
	 * toma como el intervalo abierto. Este método es null-safe.
	 * 
	 * @param date
	 *            la fecha que vamos a comparar.
	 * @param beforeDate
	 *            la fecha que consideramos que es anterior a la fecha de comparación. En caso de ser <code>null</code>, esta fecha se omite.
	 * @param afterDate
	 *            la fecha que consideramos que es mayor a la fecha de comparación. En caso de ser <code>null</code>, esta fecha se omite.
	 * @param datePrecision
	 *            La precisión con la que van a compararse las fechas. Si es <code>null</code> se toma la precisión {@link DatePrecision#MILLISECOND}.
	 * @return <i>true</i> en caso que la fecha <i>afterDate</i> es posterior a la fecha <i>date</i>, en caso contrario, retornamos <i>false</i>.
	 */
	public static boolean between(Date date, Date beforeDate, Date afterDate, DatePrecision datePrecision) {
		if (date == null) {
			return false;
		}

		if (datePrecision == null) {
			datePrecision = DatePrecision.MILLISECOND;
		}

		if (beforeDate == null) {
			if (afterDate == null) {
				return false;
			} else {
				return DateUtil.afterOrEqual(date, afterDate, datePrecision);
			}
		} else {
			if (afterDate == null) {
				return DateUtil.beforeOrEqual(date, beforeDate, datePrecision);
			} else {
				Date myBeforeDate = DateUtil.getLowerDate(beforeDate, afterDate, datePrecision);
				Date myAfterDate = DateUtil.getHigherDate(beforeDate, afterDate, datePrecision);

				return DateUtil.beforeOrEqual(date, myBeforeDate, datePrecision) && DateUtil.afterOrEqual(date, myAfterDate, datePrecision);
			}
		}
	}

	/**
	 * Trunca la fecha recibida de acuerdo a la resolución que se reciba, es decir, a los datos bajo la resolución los deja en el mínimo valor.
	 * 
	 * @param date
	 *            La fecha que vamos a truncar.
	 * @param datePrecision
	 *            La precisión con la que va a recortarse la fecha.
	 * @return La fecha truncada de acuerdo a la precisión.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros recibidos sea inválidos o nulos.
	 */
	public static Date truncate(Date date, DatePrecision datePrecision) {
		// Verificamos que los parámetros no sean nulos.
		if (date == null) {
			log.warn("The date cannot be null.");
			throw new UncheckedException("The date cannot be null.");
		}

		if (datePrecision == null) {
			log.warn("The precision cannot be null.");
			throw new UncheckedException("The precision cannot be null.");
		}

		// Seteamos la instancia del calendario.
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		if (datePrecision.getLevel() < DatePrecision.MILLISECOND.getLevel()) {
			calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
		}

		if (datePrecision.getLevel() < DatePrecision.SECOND.getLevel()) {
			calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		}

		if (datePrecision.getLevel() < DatePrecision.MINUTE.getLevel()) {
			calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		}

		if (datePrecision.getLevel() < DatePrecision.HOUR.getLevel()) {
			calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
		}

		if (datePrecision.getLevel() < DatePrecision.DAY.getLevel()) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		}

		if (datePrecision.getLevel() < DatePrecision.MONTH.getLevel()) {
			calendar.set(Calendar.MONTH, calendar.getActualMinimum(Calendar.MONTH));
		}

		if (datePrecision.getLevel() < DatePrecision.YEAR.getLevel()) {
			calendar.set(Calendar.YEAR, calendar.getActualMinimum(Calendar.YEAR));
		}

		return calendar.getTime();
	}

	/**
	 * Devuelve la fecha que es la mayor de las 2 fechas recibidas que son comparadas de acuerdo a un nivel de precisión recibido.
	 * 
	 * @param date1
	 *            La primer fecha que vamos a comparar.
	 * @param date2
	 *            La segunda fecha que vamos a comparar.
	 * @return La fecha que sea la mayor de las 2 de acuerdo al nivel de precisión.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros recibidos sea inválidos o nulos.
	 */
	public static Date getHigherDate(Date date1, Date date2, DatePrecision datePrecision) {
		if (date1 == null) {
			return date2;
		}

		if (date2 == null) {
			return date1;
		}

		if (datePrecision == null) {
			datePrecision = DatePrecision.MILLISECOND;
		}

		if (DateUtil.beforeOrEqual(date1, date2, datePrecision)) {
			return date1;
		} else {
			return date2;
		}
	}

	/**
	 * Devuelve la fecha que es la menor de las 2 fechas recibidas que son comparadas de acuerdo a un nivel de precisión recibido.
	 * 
	 * @param date1
	 *            La primer fecha que vamos a comparar.
	 * @param date2
	 *            La segunda fecha que vamos a comparar.
	 * @return La fecha que sea la menor de las 2 de acuerdo al nivel de precisión.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros recibidos sea inválidos o nulos.
	 */
	public static Date getLowerDate(Date date1, Date date2, DatePrecision datePrecision) {
		if (date1 == null) {
			return date2;
		}

		if (date2 == null) {
			return date1;
		}

		if (datePrecision == null) {
			datePrecision = DatePrecision.MILLISECOND;
		}

		if (DateUtil.afterOrEqual(date1, date2, datePrecision)) {
			return date1;
		} else {
			return date2;
		}
	}

	/**
	 * Determina si la fecha recibida es una fecha de un fin de semana (sabado o domingo).
	 * 
	 * @param date
	 *            La fecha que vamos a verificar si cae un fin de semana.
	 * @return <i>true</i> en caso que la fecha recibida caiga un fin de semana, en caso contrario, retornamos <i>false</i>.
	 * @throws UncheckedException
	 *             En caso de que la fecha recibida sea nula.
	 */
	public static boolean isWeekend(Date date) {
		// Verificamos que el parámetro no sea nulo.
		if (date == null) {
			log.warn("The date cannot be null.");
			throw new UncheckedException("The date cannot be null.");
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);

		return (weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY);
	}

	/**
	 * Permite obtener el primer día del mes anterior a la fecha recibida.
	 * 
	 * @param date
	 *            La fecha que nos permite calcular el mes anterior a la misma.
	 * @return La fecha correspondiente al primer dia del mes anterior a la fecha recibida.
	 * @throws UncheckedException
	 *             En caso de que la fecha recibida sea nula.
	 */
	public static Date getPreviousMonth(Date date) {
		// Verificamos que la fecha no sea nula.
		if (date == null) {
			log.warn("The date cannot be null.");
			throw new UncheckedException("The date cannot be null.");
		}

		// Hacemos los calculos.
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
	 * @param date
	 *            La fecha que nos permite calcular el mes siguiente a la misma.
	 * @return La fecha correspondiente al primer dia del mes siguiente a la fecha recibida.
	 * @throws UncheckedException
	 *             En caso de que la fecha recibida sea nula.
	 */
	public static Date getNextMonth(Date date) {
		// Verificamos que la fecha no sea nula.
		if (date == null) {
			log.warn("The date cannot be null.");
			throw new UncheckedException("The date cannot be null.");
		}

		// Hacemos los calculos.
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
	 * Permite calcular el primer dia del mes de una fecha recibida.
	 * 
	 * @param date
	 *            La fecha recibida a la que vamos a calcular el primer dia del mes.
	 * @return El primer dia del mes que corresponde a la fecha recibida y que ademas contiene la hora <b>00:00:00.000</b>.
	 * @throws UncheckedException
	 *             En caso de que la fecha recibida sea nula.
	 */
	public static Date getFirstDayOfMonth(Date date) {
		// Verificamos que la fecha no sea nula.
		if (date == null) {
			log.warn("The date cannot be null.");
			throw new UncheckedException("The date cannot be null.");
		}

		// Hacemos los calculos.
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
	 * Permite calcular el último dia del mes de una fecha recibida.
	 * 
	 * @param date
	 *            La fecha recibida a la que vamos a calcular el último dia del mes.
	 * @return El último dia del mes que corresponde a la fecha recibida y que ademas contiene la hora <b>23:59:59.999</b>.
	 * @throws UncheckedException
	 *             En caso de que la fecha recibida sea nula.
	 */
	public static Date getLastDayOfMonth(Date date) {
		// Verificamos que la fecha no sea nula.
		if (date == null) {
			log.warn("The date cannot be null.");
			throw new UncheckedException("The date cannot be null.");
		}

		// Hacemos los calculos.
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