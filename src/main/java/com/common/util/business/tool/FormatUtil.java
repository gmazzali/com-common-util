package com.common.util.business.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.common.util.domain.exception.UncheckedException;

/**
 * La clase que nos permite definir funciones de formateo.
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class FormatUtil {

	/**
	 * La constante para el formato de despliegue de una fecha. Por omisi�n tiene el valor:
	 * 
	 * <pre>
	 * dateFormat = &quot;dd/MM/yyyy&quot;
	 * </pre>
	 */
	protected static String dateFormat = "dd/MM/yyyy";

	/**
	 * Se encarga de cargar el formato de la fecha que vamos a utilizar. Por omisi�n, el formato de la fecha que vamos a utilizar es el cargado en
	 * {@link FormatUtil#dateFormat}.
	 * 
	 * @param dateFormat
	 *            El formato de la fecha que vamos a utilizar para formatear.
	 */
	public void setDateFormat(String dateFormat) {
		FormatUtil.dateFormat = dateFormat;
	}

	/**
	 * Se encarga de formatear una fecha recibida de acuerdo al patr�n recibido. Verifica que alguno de los par�metros no sea nulo.
	 * 
	 * @param date
	 *            La fecha que vamos a formatear.
	 * @param pattern
	 *            El patr�n con el que vamos a formatear la fecha.
	 * @return La fecha formateada.
	 * @throws UncheckedException
	 *             En caso de que alguno de los par�metros sea nulo.
	 */
	public static String formatDate(Date date, String pattern) {
		VerifierUtil.checkNotNull(date, "The date cannot be null");
		VerifierUtil.checkNotNull(pattern, "The pattern cannot be null");
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * Se encarga de formatear una fecha recibida de acuerdo al patr�n por omisi�n {@link FormatUtil#dateFormat}. En caso de que la fecha recibida
	 * sean nula, se devuelve una cadena vac�a.
	 * 
	 * @param date
	 *            La fecha que vamos a formatear.
	 * @return La fecha formateada o una cadena vacia si la fecha recibida es nula.
	 */
	public static String formatDate(Date date) {
		return date != null ? FormatUtil.formatDate(date, FormatUtil.dateFormat) : "";
	}
}