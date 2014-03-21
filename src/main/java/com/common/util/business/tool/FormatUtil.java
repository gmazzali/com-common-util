package com.common.util.business.tool;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

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
	 * La localidad que vamos a tomar como base para los formatos.
	 */
	protected static Locale locale = Locale.GERMANY;

	/**
	 * La constante para el formato de despliegue de una fecha. Por omisión tiene el valor:
	 * 
	 * <pre>
	 * dateFormat = &quot;dd/MM/yyyy&quot;
	 * </pre>
	 */
	protected static String dateFormat = "dd/MM/yyyy";

	/**
	 * Se encarga de cargar la localidad del contexto donde se ejecuta el formateador. Por omisión, la localidad que vamos a utilizar es el cargado a
	 * partir de {@link Locale#GERMANY}.
	 * 
	 * @param locale
	 *            La localidad donde se van a formatear los elementos.
	 */
	public void setLocale(Locale locale) {
		FormatUtil.locale = locale;
	}

	/**
	 * Se encarga de cargar el formato de la fecha que vamos a utilizar. Por omisión, el formato de la fecha que vamos a utilizar es el cargado en
	 * {@link FormatUtil#dateFormat}.
	 * 
	 * @param dateFormat
	 *            El formato de la fecha que vamos a utilizar para formatear.
	 */
	public void setDateFormat(String dateFormat) {
		FormatUtil.dateFormat = dateFormat;
	}

	/**
	 * Se encarga de formatear una fecha recibida de acuerdo al patrón recibido. Verifica que alguno de los parámetros no sea nulo.
	 * 
	 * @param date
	 *            La fecha que vamos a formatear.
	 * @param pattern
	 *            El patrón con el que vamos a formatear la fecha.
	 * @return La fecha formateada.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros sea nulo.
	 */
	public static String formatDate(Date date, String pattern) {
		// Verificamos que la fecha recibida no sea nula.
		if (date == null) {
			throw new UncheckedException("The date cannot be null.");
		}

		// Verificamos que el patrón recibido no sea nulo.
		if (pattern == null) {
			throw new UncheckedException("The pattern cannot be null.");
		}

		// Retornamos la fecha formateada.
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * Se encarga de formatear una fecha recibida de acuerdo al patrón por omisión {@link FormatUtil#dateFormat}. En caso de que la fecha recibida
	 * sean nula, se devuelve una cadena vacía.
	 * 
	 * @param date
	 *            La fecha que vamos a formatear.
	 * @return La fecha formateada o una cadena vacia si la fecha recibida es nula.
	 */
	public static String formatDate(Date date) {
		return date != null ? FormatUtil.formatDate(date, FormatUtil.dateFormat) : "";
	}

	/**
	 * Permite obtener el formateador de los números de acuerdo a la configuración por omisión para Alemania.
	 * 
	 * @see Locale#GERMANY
	 * @see FormatUtil#getNumberFormat(Integer, Integer)
	 * 
	 * @return El formateador por omisión para Alemania.
	 */
	private static DecimalFormat getNumberFormat() {
		return (DecimalFormat) NumberFormat.getNumberInstance(FormatUtil.locale);
	}

	/**
	 * Permite formatear un valor númerico de acuerdo a los parámetros recibidos como la cantidad mínimas o máximas de digitos, como la cantidad de
	 * digitos por los que vamos a agrupar al mismo.
	 * 
	 * <br>
	 * <br>
	 * <b>NUMEROS ENTEROS</b><br>
	 * 
	 * <ul>
	 * <li><code>value</code>: <i>1234567890</i></li>
	 * <li><code>minimumIntegerSize</code>: <i>15</i></li>
	 * <li><code>maximumIntegerSize</code>: <i>null</i></li>
	 * <li><code>minimumDecimalSize</code>: <i>null</i></li>
	 * <li><code>maximumDecimalSize</code>: <i>null</i></li>
	 * <li><code>decimalSeparator</code>: <i>null</i></li>
	 * <li><code>groupSize</code>: <i>3</i></li>
	 * <li><code>groupSeparator</code>: <i>'.'</i></li>
	 * </ul>
	 * 
	 * <pre>
	 * <b>OUTPUT: "000.001.234.567.890"</b>
	 * </pre>
	 * 
	 * <ul>
	 * <li><code>value</code>: <i>-12345</i></li>
	 * <li><code>minimumIntegerSize</code>: <i>10</i></li>
	 * <li><code>maximumIntegerSize</code>: <i>null</i></li>
	 * <li><code>minimumDecimalSize</code>: <i>null</i></li>
	 * <li><code>maximumDecimalSize</code>: <i>null</i></li>
	 * <li><code>decimalSeparator</code>: <i>null</i></li>
	 * <li><code>groupSize</code>: <i>2</i></li>
	 * <li><code>groupSeparator</code>: <i>'_'</i></li>
	 * </ul>
	 * 
	 * <pre>
	 * <b>OUTPUT: "-00_00_01_23_45"</b>
	 * </pre>
	 * 
	 * <br>
	 * <b>NUMEROS FRACCIONARIOS</b><br>
	 * <br>
	 * 
	 * <ul>
	 * <li><code>value</code>: <i>1234567890.123456</i></li>
	 * <li><code>minimumIntegerSize</code>: <i>15</i></li>
	 * <li><code>maximumIntegerSize</code>: <i>null</i></li>
	 * <li><code>minimumDecimalSize</code>: <i>10</i></li>
	 * <li><code>maximumDecimalSize</code>: <i>null</i></li>
	 * <li><code>decimalSeparator</code>: <i>','</i></li>
	 * <li><code>groupSize</code>: <i>3</i></li>
	 * <li><code>groupSeparator</code>: <i>'.'</i></li>
	 * </ul>
	 * 
	 * <pre>
	 * <b>OUTPUT: "000.001.234.567.890,1234560000"</b>
	 * </pre>
	 * 
	 * <ul>
	 * <li><code>value</code>: <i>1234567890.123456</i></li>
	 * <li><code>minimumIntegerSize</code>: <i>15</i></li>
	 * <li><code>maximumIntegerSize</code>: <i>null</i></li>
	 * <li><code>minimumDecimalSize</code>: <i>null</i></li>
	 * <li><code>maximumDecimalSize</code>: <i>3</i></li>
	 * <li><code>decimalSeparator</code>: <i>','</i></li>
	 * <li><code>groupSize</code>: <i>3</i></li>
	 * <li><code>groupSeparator</code>: <i>'.'</i></li>
	 * </ul>
	 * 
	 * <pre>
	 * <b>OUTPUT: "000.001.234.567.890,123"</b>
	 * </pre>
	 * 
	 * @param value
	 *            El valor entero que vamos a formatear.
	 * @param minimumIntegerSize
	 *            La mínima cantidad de digitos con la que vamos a formatear la parte entera del número. En caso de que este valor sea nulo o menor o
	 *            igual a cero, se carga por default un valor de <b>0</b>.
	 * @param maximumIntegerSize
	 *            La máxima cantidad de digitos con la que vamos a formatear la parte entera del número. En caso de que este valor sea nulo o menor o
	 *            igual a cero, se carga por default un valor de {@link Integer#MAX_VALUE}.
	 * @param minimumDecimalSize
	 *            La mínima cantidad de digitos con la que vamos a formatear la parte decimal del número. En caso de que este valor sea nulo o menor o
	 *            igual a cero, se carga por default un valor de <b>0</b>.
	 * @param maximumDecimalSize
	 *            La máxima cantidad de digitos con la que vamos a formatear la parte decimal del número. En caso de que este valor sea nulo o menor o
	 *            igual a cero, se carga por default un valor de {@link Integer#MAX_VALUE}.
	 * @param decimalSeparator
	 *            El caracter que vamos a utilizar para separar la parte entera de la parte decimal del número. En caso de que este valor sea nulo o
	 *            un espacio, se deja el valor por omisión dentro del sistema.
	 * @param groupSize
	 *            La cantidad de digitos por la que vamos a agrupar el entero. Si es nulo, no se agrupa los digitos del numero.
	 * @param groupSeparator
	 *            El caracter que se va a utilizar para separar los grupos de dígitos en caso de que se quiera agrupar. En caso de que este valor sea
	 *            nulo o un espacio, se deja el valor por omisión dentro del sistema.
	 * @return El entero formateado de acuerdo a los parámetros recibidos.
	 * @throws UncheckedException
	 *             En caso de que alguno de los parámetros sea nulo.
	 */
	public static String formatNumber(Number value, Integer minimumIntegerSize, Integer maximumIntegerSize, Integer minimumDecimalSize,
			Integer maximumDecimalSize, Character decimalSeparator, Integer groupSize, Character groupSeparator) {
		// Verificamos que el número a formatear no sea nulo.
		if (value == null) {
			throw new UncheckedException("The value cannot be null.");
		}

		DecimalFormat formatter = FormatUtil.getNumberFormat();
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

		// Seteamos la cantidad de digitos enteros a mostrar.
		if (minimumIntegerSize != null && minimumIntegerSize > 0) {
			formatter.setMinimumIntegerDigits(minimumIntegerSize);
		} else {
			formatter.setMinimumIntegerDigits(0);
		}

		if (maximumIntegerSize != null && maximumIntegerSize > 0) {
			formatter.setMaximumIntegerDigits(maximumIntegerSize);
		} else {
			formatter.setMaximumIntegerDigits(Integer.MAX_VALUE);
		}

		// Si se quiere agrupar el conjunto de numeros.
		if (groupSize != null && groupSize > 0) {
			formatter.setGroupingSize(groupSize);
			formatter.setGroupingUsed(true);

			// Cargamos el caracter separador solo si lo recibimos.
			if (groupSeparator != null) {
				symbols.setGroupingSeparator(groupSeparator);
			}
		} else {
			formatter.setGroupingUsed(false);
		}

		// Solo agregamos los decimales si el numero lo soporta.
		Boolean decimal = !(value instanceof Long || value instanceof Integer || value instanceof Short || value instanceof Byte
				|| value instanceof AtomicInteger || value instanceof AtomicLong || (value instanceof BigInteger && ((BigInteger) value).bitLength() < 64));

		if (decimal) {
			// Seteamos la cantidad de digitos a mostrar.
			if (minimumDecimalSize != null && minimumDecimalSize > 0) {
				formatter.setMinimumFractionDigits(minimumDecimalSize);
			} else {
				formatter.setMinimumFractionDigits(0);
			}

			if (maximumDecimalSize != null && maximumDecimalSize > 0) {
				formatter.setDecimalSeparatorAlwaysShown(true);
				formatter.setMaximumFractionDigits(maximumDecimalSize);
			} else {
				formatter.setMaximumFractionDigits(Integer.MAX_VALUE);
			}

			// Cargamos el caracter separador solo si lo recibimos.
			if (decimalSeparator != null) {
				symbols.setDecimalSeparator(decimalSeparator);
			}
		}

		// Volvemos a setear el conjunto de simbolos al formateador.
		formatter.setDecimalFormatSymbols(symbols);

		return value != null ? formatter.format(value) : "";
	}

	/**
	 * La función que agrega una cantidad dada de ceros para que la salida tenga una longitud mínima recibida.
	 * 
	 * <ul>
	 * <li><code>value</code>: <i>1234567890</i></li>
	 * <li><code>minimumSize</code>: <i>15</i></li>
	 * </ul>
	 * 
	 * <pre>
	 * <b>OUTPUT: "000001234567890"</b>
	 * </pre>
	 * 
	 * <ul>
	 * <li><code>value</code>: <i>-1234567890</i></li>
	 * <li><code>minimumSize</code>: <i>15</i></li>
	 * </ul>
	 * 
	 * <pre>
	 * <b>OUTPUT: "-000001234567890"</b>
	 * </pre>
	 * 
	 * @param value
	 *            El valor entero que vamos a completar con cero para llegar a una longitud dada.
	 * @param minimumSize
	 *            La cantidad minima de digitos que queremos que tenga el número formateado.
	 * @return La cadena con el valor recibido formateado con la longitud mínima recibida.
	 */
	public static String zeroFill(Number value, Integer minimumSize) {
		return value != null ? FormatUtil.formatNumber(value, minimumSize, null, null, null, null, null, null) : "";
	}
}