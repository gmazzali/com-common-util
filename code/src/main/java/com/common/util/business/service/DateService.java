package com.common.util.business.service;

import java.io.Serializable;
import java.util.Date;

import com.common.util.business.tool.DateUtil;
import com.common.util.business.tool.VerifierUtil;
import com.common.util.business.util.DatePrecisionEnum;

/**
 * Define la interfaz donde se van a poder obtener la fecha actual.
 * 
 * @since 11/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface DateService extends Serializable {

	/**
	 * Permite recuperar la fecha actual del sistema.
	 * 
	 * @return La fecha actual del sistema.
	 */
	public Date getCurrentDate();

	/**
	 * Crea una fecha de acuerdo a una cadena que contiene la misma y de acuerdo al patrón por omisión {@link DateUtil#dateFormat}. En caso de que la cadena este vacía, se va a
	 * retornar una fecha <code>null</code>.
	 * 
	 * @param date
	 *            La cadena que contiene la fecha que vamos a crear.
	 * @return La fecha correctamente parseada de acuerdo al patrón {@link DateUtil#dateFormat}. En caso que sea imposible parsear la fecha, se retorna un valor <code>null</code>.
	 */
	public Date createDate(String date);

	/**
	 * Crea una fecha de acuerdo a una cadena que contiene la misma y al patron de fecha que se recibe.
	 * 
	 * @param date
	 *            La cadena que contiene la fecha que vamos a crear.
	 * @param pattern
	 *            El patrón con el que va a leer la fecha que recibimos anteriormente.
	 * @return La fecha correctamente parseada de acuerdo al patrón recibido. En caso que sea imposible parsear la fecha, se retorna un valor <code>null</code>.
	 * @throws NullPointerException
	 *             En caso de que alguno de los parámetros sea nulo.
	 */
	public Date createDate(String date, String pattern);

	/**
	 * Se encarga de formatear una fecha recibida de acuerdo al patrón por omisión {@link DateUtil#dateFormat}. En caso de que la fecha recibida sean <code>null</code>, se devuelve
	 * una cadena <code>null</code>.
	 * 
	 * @param date
	 *            La fecha que vamos a formatear.
	 * @return La fecha formateada o una cadena <code>null</code> si la fecha recibida es <code>null</code>.
	 */
	public String formatDate(Date date);

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
	public String formatDate(Date date, String pattern);

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
	 * @return Un valor positivo si la fecha <code>date</code> es mayor a la fecha <code>otherDate</code>, un valor cero (0) si las 2 fechas son iguales o un valor negativo si la
	 *         fecha <code>otherDate</code> es mayor a la fecha <code>date</code>.
	 * @throws NullPointerException
	 *             En caso de que alguno de los parámetros recibidos sea inválidos o nulos.
	 */
	public int compare(Date date, Date otherDate, DatePrecisionEnum datePrecisionEnum);

	/**
	 * Determina si la fecha <code>beforeDate</code> es menor a la fecha <code>date</code> de acuerdo al nivel de precisión recibida. Este método es null-safe.
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
	 * @return <code>true</code> en caso que la fecha <code>beforeDate</code> es anterior a la fecha <code>date</code>, en caso contrario, o en caso de que alguno de los parámetros
	 *         recibidos sea <code>null</code>, retornamos <code>false</code>.
	 */
	public boolean before(Date date, Date beforeDate, DatePrecisionEnum datePrecisionEnum);

	/**
	 * Determina si la fecha <code>beforeOrEqualDate</code> es menor o igual la fecha <code>date</code> de acuerdo al nivel de precisión recibida. Este método es null-safe.
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
	 * @return <code>true</code> en caso que la fecha <code>beforeOrEqualDate</code> es anterior o igual a la fecha <code>date</code>, en caso contrario, o en caso de que alguno de
	 *         los parámetros recibidos sea <code>null</code>, retornamos <code>false</code>.
	 */
	public boolean beforeOrEqual(Date date, Date beforeOrEqualDate, DatePrecisionEnum datePrecisionEnum);

	/**
	 * Determina si la fecha <code>equalDate</code> es igual la fecha <code>date</code> de acuerdo al nivel de precisión recibida. Este método es null-safe. *
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
	 * @return <code>true</code> en caso que la fecha <code>equalDate</code> es igual a la fecha <code>date</code>, en caso contrario, o en caso de que alguno de los parámetros
	 *         recibidos sea <code>null</code>, retornamos <code>false</code>.
	 */
	public boolean equal(Date date, Date equalDate, DatePrecisionEnum datePrecisionEnum);

	/**
	 * Determina si la fecha <code>afterOrEqualDate</code> es mayor a la fecha <code>date</code> de acuerdo al nivel de precisión recibida. Este método es null-safe.
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
	 * @return <code>true</code> en caso que la fecha <code>afterOrEqualDate</code> es posterior o igual a la fecha <code>date</code>, en caso contrario, o en caso de que alguno de
	 *         los parámetros recibidos sea <code>null</code>, retornamos <code>false</code>.
	 */
	public boolean afterOrEqual(Date date, Date afterOrEqualDate, DatePrecisionEnum datePrecisionEnum);

	/**
	 * Determina si la fecha <code>afterDate</code> es mayor a la fecha <code>date</code> de acuerdo al nivel de precisión recibida. Este método es null-safe.
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
	 * @return <code>true</code> en caso que la fecha <code>afterDate</code> es posterior a la fecha <code>date</code>, en caso contrario, o en caso de que alguno de los parámetros
	 *         recibidos sea <code>null</code>, retornamos <code>false</code>.
	 */
	public boolean after(Date date, Date afterDate, DatePrecisionEnum datePrecisionEnum);

	/**
	 * Determina si la fecha <code>beforeDate</code> es menor a la fecha <code>date</code> y la fecha <code>afterDate</code> es mayor a la fecha <code>date</code> de acuerdo al
	 * nivel de precisión recibida. En caso de recibir un valor <code>null</code> para alguna de las fechas <code>beforeDate</code> o <code>afterDate</code> se toma como el
	 * intervalo abierto. En caso de recibir las fechas del intervalo invertidas, las colocamos de manera correcta para su verificación. Este método es null-safe.
	 * 
	 * @param date
	 *            La fecha que vamos a comparar.
	 * @param beforeDate
	 *            La fecha que corresponde con el inicio del intervalo de las fechas. En caso de ser <code>null</code>, esta fecha se omite.
	 * @param afterDate
	 *            La fecha que corresponde con el fin del intervalo de las fechas. En caso de ser <code>null</code>, esta fecha se omite.
	 * @param datePrecisionEnum
	 *            La precisión con la que van a compararse las fechas. Si es <code>null</code> se toma la precisión {@link DatePrecisionEnum#MILLISECOND}.
	 * @return <code>true</code> en caso que la fecha <code>afterDate</code> es posterior a la fecha <code>date</code>, en caso contrario, retornamos <code>false</code>.
	 */
	public boolean between(Date date, Date beforeDate, Date afterDate, DatePrecisionEnum datePrecisionEnum);

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
	public Date truncate(Date date, DatePrecisionEnum datePrecisionEnum);

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
	public Date getHigher(Date date1, Date date2, DatePrecisionEnum datePrecisionEnum);

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
	public Date getLower(Date date1, Date date2, DatePrecisionEnum datePrecisionEnum);
}