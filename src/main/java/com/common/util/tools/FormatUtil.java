package com.common.util.tools;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.common.util.exception.UncheckedException;

/**
 * La clase que nos permite definir funciones de formateo.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class FormatUtil {

	/**
	 * La localidad que vamos a tomar como base para los formatos.
	 */
	protected static Locale formatLocale = Locale.GERMANY;

	/**
	 * La constante para el formato de despliegue de una fecha. Por omisión tiene el valor:
	 * 
	 * <pre>
	 * dateFormat = &quot;dd/MM/yyyy&quot;
	 * </pre>
	 */
	protected static String dateFormat = "dd/MM/yyyy";

	/**
	 * El simbolo que vamos a ocupar para referenciar las monedas de uso comercial. Por omisión tiene el valor:
	 * 
	 * <pre>
	 * currencySimbol = &quot;$&quot;
	 * </pre>
	 */
	protected static String currencySimbol = "$";

	/**
	 * Se encarga de cargar la localidad del contexto donde se ejecuta el formateador. Por omisión, la localidad que vamos a utilizar es el cargado a
	 * partir de {@link Locale#GERMANY}.
	 * 
	 * @param formatLocale
	 *            La localidad donde se van a formatear los elementos.
	 */
	public void setFormatLocale(Locale formatLocale) {
		FormatUtil.formatLocale = formatLocale;
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
	 * Se encarga de cargar el simbolo del dinero que vamos a utilizar. Por omisión, el simbolo que vamos a utilizar que vamos a utilizar es el
	 * cargado en {@link FormatUtil#currencySimbol}.
	 * 
	 * @param currencySimbol
	 *            El simbolo para referenciar las monedas de uso comercial.
	 */
	public void setCurrencySimbol(String currencySimbol) {
		FormatUtil.currencySimbol = currencySimbol;
	}

	/**
	 * Se encarga de formatear una fecha recibida de acuerdo al patrón recibido. Verifica que alguno de los parámetros no sea nulo.
	 * 
	 * @param date
	 *            La fecha que vamos a formatear.
	 * @param pattern
	 *            El patrón con el que vamos a formatear la fecha.
	 * @return La fecha formateada.
	 * @throws RuntimeException
	 *             En caso de que alguno de los parámetros sea nulo.
	 */
	public static String formatDate(Date date, String pattern) {
		// Verificamos que la fecha recibida no sea nula.
		if (date == null) {
			throw new UncheckedException("The date don't must be null.");
		}

		// Verificamos que el patrón recibido no sea nulo.
		if (pattern == null) {
			throw new UncheckedException("The pattern don't must be null.");
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
		return (DecimalFormat) NumberFormat.getNumberInstance(FormatUtil.formatLocale);
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
	 */
	public static String formatNumber(Number value, Integer minimumIntegerSize, Integer maximumIntegerSize, Integer minimumDecimalSize,
			Integer maximumDecimalSize, Character decimalSeparator, Integer groupSize, Character groupSeparator) {
		// Verificamos que el número a formatear no sea nulo.
		if (value == null) {
			throw new UncheckedException("The value don't must be null.");
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

				// Mostramos el caracter separador solo si tenemos como mínimo un valor decimal.
				if (minimumDecimalSize > 0) {
					formatter.setDecimalSeparatorAlwaysShown(true);
				}

				// Cargamos el caracter separador solo si lo recibimos.
				if (decimalSeparator != null) {
					symbols.setDecimalSeparator(decimalSeparator);
				}
			} else {
				formatter.setMinimumFractionDigits(0);
			}

			if (maximumDecimalSize != null && maximumDecimalSize > 0) {
				formatter.setMaximumFractionDigits(maximumDecimalSize);
			} else {
				formatter.setMaximumFractionDigits(Integer.MAX_VALUE);
			}
		}

		// Volvemos a setear el conjunto de simbolos al formateador.
		formatter.setDecimalFormatSymbols(symbols);

		return value != null ? formatter.format(value) : "";
	}

	/**
	 * La función que convierte un valor entero a una cadena de texto plano.
	 * 
	 * <pre>
	 * 123.456.789 => 123456789
	 * </pre>
	 * 
	 * @param value
	 *            El valor entero que vamos a convertir.
	 * @return La cadena con el valor recibido formateado.
	 */
	public static String formatInteger(Integer value) {
		return value != null ? FormatUtil.formatNumber(value, 0, null, null, null, null, null, null) : "";
	}

	/**
	 * La función que convierte un valor entero largo a una cadena de texto plano.
	 * 
	 * <pre>
	 * 123.456.789 => 123456789
	 * </pre>
	 * 
	 * @param value
	 *            El valor entero largo que vamos a convertir.
	 * @return La cadena con el valor recibido como texto plano.
	 */
	public static String formatLong(Long value) {
		return value != null ? FormatUtil.formatNumber(value, 0, null, null, null, null, null, null) : "";
	}
}
