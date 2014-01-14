package com.common.util.tools.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.common.util.exception.UncheckedException;

/**
 * La clase que permite manipular las fechas dentro de un sistema.
 * 
 * @see Date
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class DateUtil {

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
			throw new UncheckedException("The date must don't be null.");
		}

		if (pattern == null) {
			throw new UncheckedException("The pattern must don't be null.");
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
			throw new UncheckedException("The dates must don't be null.");
		}

		if (datePrecision == null) {
			throw new UncheckedException("The precision must don't be null.");
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
	 * Determina si la fecha <i>beforeDate</i> es menor a la fecha <i>date</i> de acuerdo al nivel de precisión recibida.
	 * 
	 * @param date
	 *            la fecha que vamos a comparar.
	 * @param beforeDate
	 *            la fecha que consideramos que es anterior a la fecha de comparación.
	 * @param datePrecision
	 *            La precisión con la que van a compararse las fechas.
	 * @return <i>true</i> en caso que la fecha <i>beforeDate</i> es anterior a la fecha <i>date</i>, en caso contrario, retornamos <i>false</i>.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros recibidos sea inválidos o nulos.
	 */
	public static boolean before(Date date, Date beforeDate, DatePrecision datePrecision) {
		return DateUtil.compare(date, beforeDate, datePrecision) > 0;
	}

	/**
	 * Determina si la fecha <i>beforeOrEqualDate</i> es menor o igual la fecha <i>date</i> de acuerdo al nivel de precisión recibida.
	 * 
	 * @param date
	 *            la fecha que vamos a comparar.
	 * @param beforeOrEqualDate
	 *            la fecha que consideramos que es anterior o igual a la fecha de comparación.
	 * @param datePrecision
	 *            La precisión con la que van a compararse las fechas.
	 * @return <i>true</i> en caso que la fecha <i>beforeOrEqualDate</i> es anterior o igual a la fecha <i>date</i>, en caso contrario, retornamos
	 *         <i>false</i>.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros recibidos sea inválidos o nulos.
	 */
	public static boolean beforeOrEqual(Date date, Date beforeOrEqualDate, DatePrecision datePrecision) {
		return DateUtil.compare(date, beforeOrEqualDate, datePrecision) >= 0;
	}

	/**
	 * Determina si la fecha <i>equalDate</i> es igual la fecha <i>date</i> de acuerdo al nivel de precisión recibida.
	 * 
	 * @param date
	 *            la fecha que vamos a comparar.
	 * @param equalDate
	 *            la fecha que consideramos que es igual a la fecha de comparación.
	 * @param datePrecision
	 *            La precisión con la que van a compararse las fechas.
	 * @return <i>true</i> en caso que la fecha <i>equalDate</i> es igual a la fecha <i>date</i>, en caso contrario, retornamos <i>false</i>.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros recibidos sea inválidos o nulos.
	 */
	public static boolean equal(Date date, Date equalDate, DatePrecision datePrecision) {
		return DateUtil.compare(date, equalDate, datePrecision) == 0;
	}

	/**
	 * Determina si la fecha <i>afterOrEqualDate</i> es mayor a la fecha <i>date</i> de acuerdo al nivel de precisión recibida.
	 * 
	 * @param date
	 *            la fecha que vamos a comparar.
	 * @param afterOrEqualDate
	 *            la fecha que consideramos que es mayor o igual a la fecha de comparación.
	 * @param datePrecision
	 *            La precisión con la que van a compararse las fechas.
	 * @return <i>true</i> en caso que la fecha <i>afterOrEqualDate</i> es posterior o igual a la fecha <i>date</i>, en caso contrario, retornamos
	 *         <i>false</i>.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros recibidos sea inválidos o nulos.
	 */
	public static boolean afterOrEqual(Date date, Date afterOrEqualDate, DatePrecision datePrecision) {
		return DateUtil.compare(date, afterOrEqualDate, datePrecision) <= 0;
	}

	/**
	 * Determina si la fecha <i>afterDate</i> es mayor a la fecha <i>date</i> de acuerdo al nivel de precisión recibida.
	 * 
	 * @param date
	 *            la fecha que vamos a comparar.
	 * @param afterDate
	 *            la fecha que consideramos que es mayor a la fecha de comparación.
	 * @param datePrecision
	 *            La precisión con la que van a compararse las fechas.
	 * @return <i>true</i> en caso que la fecha <i>afterDate</i> es posterior a la fecha <i>date</i>, en caso contrario, retornamos <i>false</i>.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros recibidos sea inválidos o nulos.
	 */
	public static boolean after(Date date, Date afterDate, DatePrecision datePrecision) {
		return DateUtil.compare(date, afterDate, datePrecision) < 0;
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
			throw new UncheckedException("The date must don't be null.");
		}

		if (datePrecision == null) {
			throw new UncheckedException("The precision must don't be null.");
		}

		// Seteamos la instancia del calendario.
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		if (datePrecision.getLevel() < DatePrecision.MILLISECOND.getLevel()) {
			calendar.set(Calendar.MILLISECOND, 0);
		}

		if (datePrecision.getLevel() < DatePrecision.SECOND.getLevel()) {
			calendar.set(Calendar.SECOND, 0);
		}

		if (datePrecision.getLevel() < DatePrecision.MINUTE.getLevel()) {
			calendar.set(Calendar.MINUTE, 0);
		}

		if (datePrecision.getLevel() < DatePrecision.HOUR.getLevel()) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
		}

		if (datePrecision.getLevel() < DatePrecision.DAY.getLevel()) {
			calendar.set(Calendar.DAY_OF_MONTH, 1);
		}

		if (datePrecision.getLevel() < DatePrecision.MONTH.getLevel()) {
			calendar.set(Calendar.MONTH, 1);
		}

		if (datePrecision.getLevel() < DatePrecision.YEAR.getLevel()) {
			calendar.set(Calendar.YEAR, 1900);
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
		// Verificamos que el parámetro no sea nulo o negativo.
		if (date == null) {
			throw new UncheckedException("The date must don't be null.");
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);

		return (weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY);
	}

	/**
	 * Convierte la cantidad de horas recibidas a días, redondeando hacia el entero superior más próximo sobre el resultado de la operación
	 * <i>"hours / HOURS_PER_DAY"</i>.
	 * 
	 * <ul>
	 * <li>10 horas => 1 día</li>
	 * <li>24 horas => 1 día</li>
	 * <li>25 horas => 1 día</li>
	 * <li>36 horas => 2 días</li>
	 * <li>48 horas => 2 días</li>
	 * <li>49 horas => 3 días</li>
	 * </ul>
	 * 
	 * @param hours
	 *            La cantidad de horas que vamos a convertir a días.
	 * @return La cantidad de días que obtenemos de la operación.
	 * @throws UncheckedException
	 *             En caso de que las horas recibidas sean nulas o negativas.
	 */
	@Deprecated
	public static Integer converterHoursToDays(Integer hours) {
		// Verificamos que el parámetro no sea nulo o negativo.
		if (hours == null) {
			throw new UncheckedException("The hours must don't be null.");
		}

		if (hours < 0) {
			throw new UncheckedException("The hours must don't be negative.");
		}

		return (int) Math.ceil(hours / (double) DateUtil.HOURS_PER_DAY);
	}

	/**
	 * Obtiene los días de diferencia entre dos fechas dadas. Si startDate es anterior o igual a endDate el valor es positivo, sino es negativo
	 * 
	 * Date s = new Date("01/01/2010"); Date e = new Date("02/01/2010"); 1 == DateUtils.getDaysBetweenDates(s, e);
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Deprecated
	public static long getDaysBetweenDates(Date startDate, Date endDate) {
		long diferencia = endDate.getTime() - startDate.getTime();
		long dias = diferencia / DateUtil.MILLISECONDS_PER_DAY;
		return dias;
	}
}