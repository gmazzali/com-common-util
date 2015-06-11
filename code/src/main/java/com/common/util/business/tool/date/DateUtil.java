package com.common.util.business.tool.date;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.common.util.business.tool.StringUtil;
import com.common.util.business.tool.VerifierUtil;
import com.common.util.business.util.DatePrecisionEnum;

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
	private static final Logger LOGGER = Logger.getLogger(DateUtil.class);

	/**
	 * La constante para el formato de despliegue de una fecha. Por omisión tiene el valor:
	 * 
	 * <pre>
	 * dateFormat = &quot;dd/MM/yyyy&quot;
	 * </pre>
	 */
	protected static String dateFormat = "dd/MM/yyyy";

	/**
	 * Se encarga de cargar el formato de la fecha que vamos a utilizar. Por omisión, el formato de la fecha que vamos a utilizar es el cargado en
	 * {@link DateUtil#dateFormat}.
	 * 
	 * @param dateFormat
	 *            El formato de la fecha que vamos a utilizar para formatear.
	 */
	public void setDateFormat(String dateFormat) {
		DateUtil.dateFormat = dateFormat;
	}

	/**
	 * Se encarga de formatear una fecha recibida de acuerdo al patrón por omisión {@link DateUtil#dateFormat}. En caso de que la fecha recibida sean
	 * <code>null</code>, se devuelve una cadena <code>null</code>.
	 * 
	 * @param date
	 *            La fecha que vamos a formatear.
	 * @return La fecha formateada o una cadena <code>null</code> si la fecha recibida es <code>null</code>.
	 */
	public static String formatDate(Date date) {
		return date != null ? DateUtil.formatDate(date, DateUtil.dateFormat) : null;
	}

	/**
	 * Se encarga de formatear una fecha recibida de acuerdo al patrón recibido. Verifica que alguno de los parámetros no sea nulo.
	 * 
	 * @param date
	 *            La fecha que vamos a formatear.
	 * @param pattern
	 *            El patrón con el que vamos a formatear la fecha.
	 * @return La fecha formateada de acuerdo al patrón recibido. En caso de que el patrón no sea válido, retorna una cadena <code>null</code>.
	 * @throws NullPointerException
	 *             En caso de que alguno de los parámetros sea nulo.
	 */
	public static String formatDate(Date date, String pattern) {
		VerifierUtil.checkNotNull(date, "The date cannot be null");
		VerifierUtil.checkNotNull(pattern, "The pattern cannot be null");
		try {
			return new SimpleDateFormat(pattern).format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Crea una fecha de acuerdo a una cadena que contiene la misma y de acuerdo al patrón por omisión {@link DateUtil#dateFormat}. En caso de que la
	 * cadena este vacía, se va a retornar una fecha <code>null</code>.
	 * 
	 * @param date
	 *            La cadena que contiene la fecha que vamos a crear.
	 * @return La fecha correctamente parseada de acuerdo al patrón {@link DateUtil#dateFormat}. En caso que sea imposible parsear la fecha, se
	 *         retorna un valor <code>null</code>.
	 */
	public static Date createDate(String date) {
		return StringUtil.isNotEmpty(date) ? DateUtil.createDate(date, DateUtil.dateFormat) : null;
	}

	/**
	 * Crea una fecha de acuerdo a una cadena que contiene la misma y al patron de fecha que se recibe.
	 * 
	 * @param date
	 *            La cadena que contiene la fecha que vamos a crear.
	 * @param pattern
	 *            El patrón con el que va a leer la fecha que recibimos anteriormente.
	 * @return La fecha correctamente parseada de acuerdo al patrón recibido. En caso que sea imposible parsear la fecha, se retorna un valor
	 *         <code>null</code>.
	 * @throws NullPointerException
	 *             En caso de que alguno de los parámetros sea nulo.
	 */
	public static Date createDate(String date, String pattern) {
		VerifierUtil.checkNotNull(date, "The date cannot be null");
		VerifierUtil.checkNotNull(pattern, "The pattern cannot be null");
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Compara las 2 fechas que recibe para determinar cual de las 2 fechas es menor a la otra de acuerdo a un nivel de precisión recibido.
	 * 
	 * <pre>
	 * DateUtil.compare(null, null, null)                                                  = NullPointerException
	 * DateUtil.compare("2014/01/01 00:00:00 001", null, null)                             = NullPointerException
	 * DateUtil.compare(null, "2014/01/01 00:00:00 001", null)                             = NullPointerException
	 * 
	 * DateUtil.compare("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 000", null)        = 1
	 * DateUtil.compare("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 001", null)        = -1
	 * DateUtil.compare("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 001", null)        = 0
	 * 
	 * DateUtil.compare("2015/01/01 00:00:00 000", "2014/01/01 00:00:00 000", YEAR)        = 1
	 * DateUtil.compare("2014/01/01 00:00:00 000", "2015/01/01 00:00:00 000", YEAR)        = -1
	 * DateUtil.compare("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", YEAR)        = 0
	 * 
	 * DateUtil.compare("2014/02/01 00:00:00 000", "2014/01/01 00:00:00 000", MONTH)       = 1
	 * DateUtil.compare("2014/01/01 00:00:00 000", "2014/02/01 00:00:00 000", MONTH)       = -1
	 * DateUtil.compare("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", MONTH)       = 0
	 * 
	 * DateUtil.compare("2014/01/02 00:00:00 000", "2014/01/01 00:00:00 000", DAY)         = 1
	 * DateUtil.compare("2014/01/01 00:00:00 000", "2014/01/02 00:00:00 000", DAY)         = -1
	 * DateUtil.compare("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", DAY)         = 0
	 * 
	 * DateUtil.compare("2014/01/01 01:00:00 000", "2014/01/01 00:00:00 000", HOUR)        = 1
	 * DateUtil.compare("2014/01/01 00:00:00 000", "2014/01/01 01:00:00 000", HOUR)        = -1
	 * DateUtil.compare("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", HOUR)        = 0
	 * 
	 * DateUtil.compare("2014/01/01 00:01:00 000", "2014/01/01 00:00:00 000", MINUTE)      = 1
	 * DateUtil.compare("2014/01/01 00:00:00 000", "2014/01/01 00:01:00 000", MINUTE)      = -1
	 * DateUtil.compare("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", MINUTE)      = 0
	 * 
	 * DateUtil.compare("2014/01/01 00:00:01 000", "2014/01/01 00:00:00 000", SECOND)      = 1
	 * DateUtil.compare("2014/01/01 00:00:00 000", "2014/01/01 00:00:01 000", SECOND)      = -1
	 * DateUtil.compare("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", SECOND)      = 0
	 * 
	 * DateUtil.compare("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 000", MILLISECOND) = 1
	 * DateUtil.compare("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 001", MILLISECOND) = -1
	 * DateUtil.compare("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 001", MILLISECOND) = 0
	 * </pre>
	 * 
	 * @param date
	 *            la primer fecha a comparar.
	 * @param otherDate
	 *            la segunda fecha a comparar.
	 * @param datePrecisionEnum
	 *            La precisión con la que va a compararse la fecha. Si es <code>null</code> se toma la precisión {@link DatePrecisionEnum#MILLISECOND} .
	 * 
	 * @return Un valor positivo si la fecha <code>date</code> es mayor a la fecha <code>otherDate</code>, un valor cero (0) si las 2 fechas son
	 *         iguales o un valor negativo si la fecha <code>otherDate</code> es mayor a la fecha <code>date</code>.
	 * @throws NullPointerException
	 *             En caso de que alguno de los parámetros recibidos sea inválidos o nulos.
	 */
	public static int compare(Date date, Date otherDate, DatePrecisionEnum datePrecisionEnum) {
		VerifierUtil.checkNotNull(date, "The date cannot be null");
		VerifierUtil.checkNotNull(otherDate, "The otherDate cannot be null");

		if (datePrecisionEnum == null) {
			LOGGER.info("The precision is set to MILLISECOND");
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

	/**
	 * Determina si la fecha <code>beforeDate</code> es menor a la fecha <code>date</code> de acuerdo al nivel de precisión recibida. Este método es
	 * null-safe.
	 * 
	 * <pre>
	 * DateUtil.before(null, null, null)                                                  = false
	 * DateUtil.before("2014/01/01 00:00:00 001", null, null)                             = false
	 * DateUtil.before(null, "2014/01/01 00:00:00 001", null)                             = false
	 * 
	 * DateUtil.before("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 000", null)        = true
	 * DateUtil.before("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 001", null)        = false
	 * DateUtil.before("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 001", null)        = false
	 * 
	 * DateUtil.before("2015/01/01 00:00:00 000", "2014/01/01 00:00:00 000", YEAR)        = true
	 * DateUtil.before("2014/01/01 00:00:00 000", "2015/01/01 00:00:00 000", YEAR)        = false
	 * DateUtil.before("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", YEAR)        = false
	 * 
	 * DateUtil.before("2014/02/01 00:00:00 000", "2014/01/01 00:00:00 000", MONTH)       = true
	 * DateUtil.before("2014/01/01 00:00:00 000", "2014/02/01 00:00:00 000", MONTH)       = false
	 * DateUtil.before("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", MONTH)       = false
	 * 
	 * DateUtil.before("2014/01/02 00:00:00 000", "2014/01/01 00:00:00 000", DAY)         = true
	 * DateUtil.before("2014/01/01 00:00:00 000", "2014/01/02 00:00:00 000", DAY)         = false
	 * DateUtil.before("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", DAY)         = false
	 * 
	 * DateUtil.before("2014/01/01 01:00:00 000", "2014/01/01 00:00:00 000", HOUR)        = true
	 * DateUtil.before("2014/01/01 00:00:00 000", "2014/01/01 01:00:00 000", HOUR)        = false
	 * DateUtil.before("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", HOUR)        = false
	 * 
	 * DateUtil.before("2014/01/01 00:01:00 000", "2014/01/01 00:00:00 000", MINUTE)      = true
	 * DateUtil.before("2014/01/01 00:00:00 000", "2014/01/01 00:01:00 000", MINUTE)      = false
	 * DateUtil.before("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", MINUTE)      = false
	 * 
	 * DateUtil.before("2014/01/01 00:00:01 000", "2014/01/01 00:00:00 000", SECOND)      = true
	 * DateUtil.before("2014/01/01 00:00:00 000", "2014/01/01 00:00:01 000", SECOND)      = false
	 * DateUtil.before("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", SECOND)      = false
	 * 
	 * DateUtil.before("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 000", MILLISECOND) = true
	 * DateUtil.before("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 001", MILLISECOND) = false
	 * DateUtil.before("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 001", MILLISECOND) = false
	 * </pre>
	 * 
	 * @param date
	 *            la fecha que vamos a comparar.
	 * @param beforeDate
	 *            la fecha que consideramos que es anterior a la fecha de comparación.
	 * @param datePrecisionEnum
	 *            La precisión con la que van a compararse las fechas. Si es <code>null</code> se toma la precisión {@link DatePrecisionEnum#MILLISECOND}.
	 * @return <code>true</code> en caso que la fecha <code>beforeDate</code> es anterior a la fecha <code>date</code>, en caso contrario, o en caso
	 *         de que alguno de los parámetros recibidos sea <code>null</code>, retornamos <code>false</code>.
	 */
	public static boolean before(Date date, Date beforeDate, DatePrecisionEnum datePrecisionEnum) {
		if (date == null || beforeDate == null) {
			LOGGER.warn("Any date is null");
			return false;
		}
		try {
			return DateUtil.compare(date, beforeDate, datePrecisionEnum) > 0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determina si la fecha <code>beforeOrEqualDate</code> es menor o igual la fecha <code>date</code> de acuerdo al nivel de precisión recibida.
	 * Este método es null-safe.
	 * 
	 * <pre>
	 * DateUtil.beforeOrEqual(null, null, null)                                                  = false
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 001", null, null)                             = false
	 * DateUtil.beforeOrEqual(null, "2014/01/01 00:00:00 001", null)                             = false
	 * 
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 000", null)        = true
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 001", null)        = false
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 001", null)        = true
	 * 
	 * DateUtil.beforeOrEqual("2015/01/01 00:00:00 000", "2014/01/01 00:00:00 000", YEAR)        = true
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 000", "2015/01/01 00:00:00 000", YEAR)        = false
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", YEAR)        = true
	 * 
	 * DateUtil.beforeOrEqual("2014/02/01 00:00:00 000", "2014/01/01 00:00:00 000", MONTH)       = true
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 000", "2014/02/01 00:00:00 000", MONTH)       = false
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", MONTH)       = true
	 * 
	 * DateUtil.beforeOrEqual("2014/01/02 00:00:00 000", "2014/01/01 00:00:00 000", DAY)         = true
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 000", "2014/01/02 00:00:00 000", DAY)         = false
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", DAY)         = true
	 * 
	 * DateUtil.beforeOrEqual("2014/01/01 01:00:00 000", "2014/01/01 00:00:00 000", HOUR)        = true
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 000", "2014/01/01 01:00:00 000", HOUR)        = false
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", HOUR)        = true
	 * 
	 * DateUtil.beforeOrEqual("2014/01/01 00:01:00 000", "2014/01/01 00:00:00 000", MINUTE)      = true
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:01:00 000", MINUTE)      = false
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", MINUTE)      = true
	 * 
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:01 000", "2014/01/01 00:00:00 000", SECOND)      = true
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:01 000", SECOND)      = false
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", SECOND)      = true
	 * 
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 000", MILLISECOND) = true
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 001", MILLISECOND) = false
	 * DateUtil.beforeOrEqual("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 001", MILLISECOND) = true
	 * </pre>
	 * 
	 * @param date
	 *            la fecha que vamos a comparar.
	 * @param beforeOrEqualDate
	 *            la fecha que consideramos que es anterior o igual a la fecha de comparación.
	 * @param datePrecisionEnum
	 *            La precisión con la que van a compararse las fechas. Si es <code>null</code> se toma la precisión {@link DatePrecisionEnum#MILLISECOND}.
	 * @return <code>true</code> en caso que la fecha <code>beforeOrEqualDate</code> es anterior o igual a la fecha <code>date</code>, en caso
	 *         contrario, o en caso de que alguno de los parámetros recibidos sea <code>null</code>, retornamos <code>false</code>.
	 */
	public static boolean beforeOrEqual(Date date, Date beforeOrEqualDate, DatePrecisionEnum datePrecisionEnum) {
		if (date == null || beforeOrEqualDate == null) {
			LOGGER.warn("Any date is null");
			return false;
		}
		try {
			return DateUtil.compare(date, beforeOrEqualDate, datePrecisionEnum) >= 0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determina si la fecha <code>equalDate</code> es igual la fecha <code>date</code> de acuerdo al nivel de precisión recibida. Este método es
	 * null-safe. *
	 * 
	 * <pre>
	 * DateUtil.equal(null, null, null)                                                  = false
	 * DateUtil.equal("2014/01/01 00:00:00 001", null, null)                             = false
	 * DateUtil.equal(null, "2014/01/01 00:00:00 001", null)                             = false
	 * 
	 * DateUtil.equal("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 000", null)        = false
	 * DateUtil.equal("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 001", null)        = false
	 * DateUtil.equal("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 001", null)        = true
	 * 
	 * DateUtil.equal("2015/01/01 00:00:00 000", "2014/01/01 00:00:00 000", YEAR)        = false
	 * DateUtil.equal("2014/01/01 00:00:00 000", "2015/01/01 00:00:00 000", YEAR)        = false
	 * DateUtil.equal("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", YEAR)        = true
	 * 
	 * DateUtil.equal("2014/02/01 00:00:00 000", "2014/01/01 00:00:00 000", MONTH)       = false
	 * DateUtil.equal("2014/01/01 00:00:00 000", "2014/02/01 00:00:00 000", MONTH)       = false
	 * DateUtil.equal("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", MONTH)       = true
	 * 
	 * DateUtil.equal("2014/01/02 00:00:00 000", "2014/01/01 00:00:00 000", DAY)         = false
	 * DateUtil.equal("2014/01/01 00:00:00 000", "2014/01/02 00:00:00 000", DAY)         = false
	 * DateUtil.equal("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", DAY)         = true
	 * 
	 * DateUtil.equal("2014/01/01 01:00:00 000", "2014/01/01 00:00:00 000", HOUR)        = false
	 * DateUtil.equal("2014/01/01 00:00:00 000", "2014/01/01 01:00:00 000", HOUR)        = false
	 * DateUtil.equal("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", HOUR)        = true
	 * 
	 * DateUtil.equal("2014/01/01 00:01:00 000", "2014/01/01 00:00:00 000", MINUTE)      = false
	 * DateUtil.equal("2014/01/01 00:00:00 000", "2014/01/01 00:01:00 000", MINUTE)      = false
	 * DateUtil.equal("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", MINUTE)      = true
	 * 
	 * DateUtil.equal("2014/01/01 00:00:01 000", "2014/01/01 00:00:00 000", SECOND)      = false
	 * DateUtil.equal("2014/01/01 00:00:00 000", "2014/01/01 00:00:01 000", SECOND)      = false
	 * DateUtil.equal("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", SECOND)      = true
	 * 
	 * DateUtil.equal("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 000", MILLISECOND) = false
	 * DateUtil.equal("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 001", MILLISECOND) = false
	 * DateUtil.equal("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 001", MILLISECOND) = true
	 * </pre>
	 * 
	 * @see VerifierUtil#equals(Serializable, Serializable)
	 * 
	 * @param date
	 *            la fecha que vamos a comparar.
	 * @param equalDate
	 *            la fecha que consideramos que es igual a la fecha de comparación.
	 * @param datePrecisionEnum
	 *            La precisión con la que van a compararse las fechas. Si es <code>null</code> se toma la precisión {@link DatePrecisionEnum#MILLISECOND}.
	 * @return <code>true</code> en caso que la fecha <code>equalDate</code> es igual a la fecha <code>date</code>, en caso contrario, o en caso de
	 *         que alguno de los parámetros recibidos sea <code>null</code>, retornamos <code>false</code>.
	 */
	public static boolean equal(Date date, Date equalDate, DatePrecisionEnum datePrecisionEnum) {
		if (date == null || equalDate == null) {
			LOGGER.warn("Any date is null");
			return false;
		}
		try {
			return DateUtil.compare(date, equalDate, datePrecisionEnum) == 0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determina si la fecha <code>afterOrEqualDate</code> es mayor a la fecha <code>date</code> de acuerdo al nivel de precisión recibida. Este
	 * método es null-safe.
	 * 
	 * <pre>
	 * DateUtil.afterOrEqual(null, null, null)                                                  = false
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 001", null, null)                             = false
	 * DateUtil.afterOrEqual(null, "2014/01/01 00:00:00 001", null)                             = false
	 * 
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 000", null)        = false
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 001", null)        = true
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 001", null)        = true
	 * 
	 * DateUtil.afterOrEqual("2015/01/01 00:00:00 000", "2014/01/01 00:00:00 000", YEAR)        = false
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 000", "2015/01/01 00:00:00 000", YEAR)        = true
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", YEAR)        = true
	 * 
	 * DateUtil.afterOrEqual("2014/02/01 00:00:00 000", "2014/01/01 00:00:00 000", MONTH)       = false
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 000", "2014/02/01 00:00:00 000", MONTH)       = true
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", MONTH)       = true
	 * 
	 * DateUtil.afterOrEqual("2014/01/02 00:00:00 000", "2014/01/01 00:00:00 000", DAY)         = false
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 000", "2014/01/02 00:00:00 000", DAY)         = true
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", DAY)         = true
	 * 
	 * DateUtil.afterOrEqual("2014/01/01 01:00:00 000", "2014/01/01 00:00:00 000", HOUR)        = false
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 000", "2014/01/01 01:00:00 000", HOUR)        = true
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", HOUR)        = true
	 * 
	 * DateUtil.afterOrEqual("2014/01/01 00:01:00 000", "2014/01/01 00:00:00 000", MINUTE)      = false
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:01:00 000", MINUTE)      = true
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", MINUTE)      = true
	 * 
	 * DateUtil.afterOrEqual("2014/01/01 00:00:01 000", "2014/01/01 00:00:00 000", SECOND)      = false
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:01 000", SECOND)      = true
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", SECOND)      = true
	 * 
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 000", MILLISECOND) = false
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 001", MILLISECOND) = true
	 * DateUtil.afterOrEqual("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 001", MILLISECOND) = true
	 * </pre>
	 * 
	 * @param date
	 *            la fecha que vamos a comparar.
	 * @param afterOrEqualDate
	 *            la fecha que consideramos que es mayor o igual a la fecha de comparación.
	 * @param datePrecisionEnum
	 *            La precisión con la que van a compararse las fechas. Si es <code>null</code> se toma la precisión {@link DatePrecisionEnum#MILLISECOND}.
	 * @return <code>true</code> en caso que la fecha <code>afterOrEqualDate</code> es posterior o igual a la fecha <code>date</code>, en caso
	 *         contrario, o en caso de que alguno de los parámetros recibidos sea <code>null</code>, retornamos <code>false</code>.
	 */
	public static boolean afterOrEqual(Date date, Date afterOrEqualDate, DatePrecisionEnum datePrecisionEnum) {
		if (date == null || afterOrEqualDate == null) {
			LOGGER.warn("Any date is null");
			return false;
		}
		try {
			return DateUtil.compare(date, afterOrEqualDate, datePrecisionEnum) <= 0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determina si la fecha <code>afterDate</code> es mayor a la fecha <code>date</code> de acuerdo al nivel de precisión recibida. Este método es
	 * null-safe.
	 * 
	 * <pre>
	 * DateUtil.after(null, null, null)                                                  = false
	 * DateUtil.after("2014/01/01 00:00:00 001", null, null)                             = false
	 * DateUtil.after(null, "2014/01/01 00:00:00 001", null)                             = false
	 * 
	 * DateUtil.after("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 000", null)        = false
	 * DateUtil.after("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 001", null)        = true
	 * DateUtil.after("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 001", null)        = false
	 * 
	 * DateUtil.after("2015/01/01 00:00:00 000", "2014/01/01 00:00:00 000", YEAR)        = false
	 * DateUtil.after("2014/01/01 00:00:00 000", "2015/01/01 00:00:00 000", YEAR)        = true
	 * DateUtil.after("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", YEAR)        = false
	 * 
	 * DateUtil.after("2014/02/01 00:00:00 000", "2014/01/01 00:00:00 000", MONTH)       = false
	 * DateUtil.after("2014/01/01 00:00:00 000", "2014/02/01 00:00:00 000", MONTH)       = true
	 * DateUtil.after("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", MONTH)       = false
	 * 
	 * DateUtil.after("2014/01/02 00:00:00 000", "2014/01/01 00:00:00 000", DAY)         = false
	 * DateUtil.after("2014/01/01 00:00:00 000", "2014/01/02 00:00:00 000", DAY)         = true
	 * DateUtil.after("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", DAY)         = false
	 * 
	 * DateUtil.after("2014/01/01 01:00:00 000", "2014/01/01 00:00:00 000", HOUR)        = false
	 * DateUtil.after("2014/01/01 00:00:00 000", "2014/01/01 01:00:00 000", HOUR)        = true
	 * DateUtil.after("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", HOUR)        = false
	 * 
	 * DateUtil.after("2014/01/01 00:01:00 000", "2014/01/01 00:00:00 000", MINUTE)      = false
	 * DateUtil.after("2014/01/01 00:00:00 000", "2014/01/01 00:01:00 000", MINUTE)      = true
	 * DateUtil.after("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", MINUTE)      = false
	 * 
	 * DateUtil.after("2014/01/01 00:00:01 000", "2014/01/01 00:00:00 000", SECOND)      = false
	 * DateUtil.after("2014/01/01 00:00:00 000", "2014/01/01 00:00:01 000", SECOND)      = true
	 * DateUtil.after("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 000", SECOND)      = false
	 * 
	 * DateUtil.after("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 000", MILLISECOND) = false
	 * DateUtil.after("2014/01/01 00:00:00 000", "2014/01/01 00:00:00 001", MILLISECOND) = true
	 * DateUtil.after("2014/01/01 00:00:00 001", "2014/01/01 00:00:00 001", MILLISECOND) = false
	 * </pre>
	 * 
	 * @param date
	 *            la fecha que vamos a comparar.
	 * @param afterDate
	 *            la fecha que consideramos que es mayor a la fecha de comparación.
	 * @param datePrecisionEnum
	 *            La precisión con la que van a compararse las fechas. Si es <code>null</code> se toma la precisión {@link DatePrecisionEnum#MILLISECOND}.
	 * @return <code>true</code> en caso que la fecha <code>afterDate</code> es posterior a la fecha <code>date</code>, en caso contrario, o en caso
	 *         de que alguno de los parámetros recibidos sea <code>null</code>, retornamos <code>false</code>.
	 */
	public static boolean after(Date date, Date afterDate, DatePrecisionEnum datePrecisionEnum) {
		if (date == null || afterDate == null) {
			LOGGER.warn("Any date is null");
			return false;
		}
		try {
			return DateUtil.compare(date, afterDate, datePrecisionEnum) < 0;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determina si la fecha <code>beforeDate</code> es menor a la fecha <code>date</code> y la fecha <code>afterDate</code> es mayor a la fecha
	 * <code>date</code> de acuerdo al nivel de precisión recibida. En caso de recibir un valor <code>null</code> para alguna de las fechas
	 * <code>beforeDate</code> o <code>afterDate</code> se toma como el intervalo abierto. En caso de recibir las fechas del intervalo invertidas, las
	 * colocamos de manera correcta para su verificación. Este método es null-safe.
	 * 
	 * @param date
	 *            La fecha que vamos a comparar.
	 * @param beforeDate
	 *            La fecha que corresponde con el inicio del intervalo de las fechas. En caso de ser <code>null</code>, esta fecha se omite.
	 * @param afterDate
	 *            La fecha que corresponde con el fin del intervalo de las fechas. En caso de ser <code>null</code>, esta fecha se omite.
	 * @param datePrecisionEnum
	 *            La precisión con la que van a compararse las fechas. Si es <code>null</code> se toma la precisión {@link DatePrecisionEnum#MILLISECOND}.
	 * @return <code>true</code> en caso que la fecha <code>afterDate</code> es posterior a la fecha <code>date</code>, en caso contrario, retornamos
	 *         <code>false</code>.
	 */
	public static boolean between(Date date, Date beforeDate, Date afterDate, DatePrecisionEnum datePrecisionEnum) {
		if (date == null) {
			LOGGER.warn("The date is null");
			return false;
		}
		if (beforeDate == null) {
			if (afterDate == null) {
				return false;
			} else {
				return DateUtil.afterOrEqual(date, afterDate, datePrecisionEnum);
			}
		} else {
			if (afterDate == null) {
				return DateUtil.beforeOrEqual(date, beforeDate, datePrecisionEnum);
			} else {
				Date myBeforeDate = DateUtil.getLower(beforeDate, afterDate, datePrecisionEnum);
				Date myAfterDate = DateUtil.getHigher(beforeDate, afterDate, datePrecisionEnum);

				return DateUtil.beforeOrEqual(date, myBeforeDate, datePrecisionEnum) && DateUtil.afterOrEqual(date, myAfterDate, datePrecisionEnum);
			}
		}
	}

	/**
	 * Trunca la fecha recibida de acuerdo a la resolución que se reciba, es decir, a los datos bajo la resolución los deja en el mínimo valor.
	 * 
	 * @param date
	 *            La fecha que vamos a truncar.
	 * @param datePrecisionEnum
	 *            La precisión con la que va a recortarse la fecha. Si es <code>null</code> se va a retornar la misma fecha recibida.
	 * @return La fecha truncada de acuerdo a la precisión.
	 * @throws NullPointerException
	 *             En caso de que alguno de los parámetros recibidos sea inválidos o nulos.
	 */
	public static Date truncate(Date date, DatePrecisionEnum datePrecisionEnum) {
		VerifierUtil.checkNotNull(date, "The date cannot be null");

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

	/**
	 * Devuelve la fecha que es la mayor de las 2 fechas recibidas que son comparadas de acuerdo a un nivel de precisión recibido.
	 * 
	 * @param date1
	 *            La primer fecha que vamos a comparar.
	 * @param date2
	 *            La segunda fecha que vamos a comparar.
	 * @param datePrecisionEnum
	 *            La precisión con la que va a compararse las fechas. Si es <code>null</code> se toma como {@link DatePrecisionEnum#MILLISECOND}.
	 * @return La fecha que sea la mayor de las 2 de acuerdo al nivel de precisión.
	 */
	public static Date getHigher(Date date1, Date date2, DatePrecisionEnum datePrecisionEnum) {
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
		if (DateUtil.beforeOrEqual(date1, date2, datePrecisionEnum)) {
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
	 * @param datePrecisionEnum
	 *            La precisión con la que va a compararse las fechas. Si es <code>null</code> se toma como {@link DatePrecisionEnum#MILLISECOND}.
	 * @return La fecha que sea la menor de las 2 de acuerdo al nivel de precisión.
	 */
	public static Date getLower(Date date1, Date date2, DatePrecisionEnum datePrecisionEnum) {
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
		if (DateUtil.afterOrEqual(date1, date2, datePrecisionEnum)) {
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
	 * @return <code>true</code> en caso que la fecha recibida caiga un fin de semana, en caso contrario, retornamos <code>false</code>.
	 * @throws NullPointerException
	 *             En caso de que la fecha recibida sea nula.
	 */
	public static boolean isWeekend(Date date) {
		VerifierUtil.checkNotNull(date, "The date cannot be null");

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
		VerifierUtil.checkNotNull(date, "The date cannot be null");

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
		VerifierUtil.checkNotNull(date, "The date cannot be null");

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
		VerifierUtil.checkNotNull(date, "The date cannot be null");

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
		VerifierUtil.checkNotNull(date, "The date cannot be null");

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