package com.common.util.business.tool;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.common.util.business.util.PatternUtilEnum;
import com.common.util.domain.exception.UncheckedException;

/**
 * La clase que nos permite definir funciones de manipulaci�n de n�meros.
 * 
 * @since 30/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class NumberUtil {

	/**
	 * La localidad que vamos a tomar como base para los formatos.
	 */
	protected static Locale locale = Locale.GERMANY;

	/**
	 * Se encarga de cargar la localidad del formateador. Por omisi�n {@link Locale#GERMANY}.
	 * 
	 * @param locale
	 *            La localidad.
	 */
	public void setLocale(Locale locale) {
		NumberUtil.locale = locale;
	}

	/**
	 * Valida que el string recibido conincida con el patr�n de un n�mero entero.
	 * 
	 * @param number
	 *            El string que vamos a validar que sea un n�mero entero.
	 * @return <code>true</code> en caso de que el string recibido corresponda con un formato de un n�mero entero, en caso contrario retorna
	 *         <code>false</code>.
	 */
	public static boolean isLongNumber(String number) {
		return StringUtil.match(number, PatternUtilEnum.NUMBER_INTEGER_PATTERN.getPattern());
	}

	/**
	 * Valida que el string recibido conincida con el patr�n de un n�mero decimal.
	 * 
	 * @param number
	 *            El string que vamos a validar que sea un n�mero decimal.
	 * @return <code>true</code> en caso de que el string recibido corresponda con un formato de un n�mero decimal, en caso contrario retorna
	 *         <code>false</code>.
	 */
	public static boolean isDecimalNumber(String number) {
		return StringUtil.match(number, PatternUtilEnum.NUMBER_DECIMAL_PATTERN.getPattern());
	}

	/**
	 * Permite obtener el formateador de los n�meros de acuerdo a la configuraci�n por omisi�n para Alemania.
	 * 
	 * @see Locale#GERMANY
	 * @see FormatUtil#getNumberFormat(Integer, Integer)
	 * 
	 * @return El formateador por omisi�n para Alemania.
	 */
	private static DecimalFormat getNumberFormat() {
		return (DecimalFormat) NumberFormat.getNumberInstance(NumberUtil.locale);
	}

	/**
	 * Permite formatear un valor n�merico de acuerdo a los par�metros recibidos como la cantidad m�nimas o m�ximas de digitos, como la cantidad de
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
	 *            El tama�o m�nimo del campo de los enteros. Puede ser <code>null</code> y toma el valor <code>0</code>.
	 * @param maximumIntegerSize
	 *            El tama�o m�ximo del campo de los enteros. Puede ser <code>null</code> y toma el valor <code>{@link Integer#MAX_VALUE}</code>.
	 * @param minimumDecimalSize
	 *            El tama�o m�nimo del campo de los decimales. Puede ser <code>null</code> y toma el valor <code>0</code>.
	 * @param maximumDecimalSize
	 *            El tama�o m�ximo del campo de los decimales. Puede ser <code>null</code> y toma el valor <code>{@link Integer#MAX_VALUE}</code>.
	 * @param decimalSeparator
	 *            El separador de los decimales. Puede ser <code>null</code> y toma el valor por omisi�n del sistema.
	 * @param groupSize
	 *            El tama�o del grupo de enteros. Puede ser <code>null</code> y en ese caso, no se agrupan los enteros.
	 * @param groupSeparator
	 *            El separador del grupo de enteros. Puede ser <code>null</code> y toma el valor por omisi�n del sistema.
	 * @return El entero formateado de acuerdo a los par�metros recibidos.
	 * @throws UncheckedException
	 *             En caso de que alguno de los par�metros sea nulo.
	 */
	public static String formatNumber(Number value, Integer minimumIntegerSize, Integer maximumIntegerSize, Integer minimumDecimalSize,
			Integer maximumDecimalSize, Character decimalSeparator, Integer groupSize, Character groupSeparator) {
		VerifierUtil.checkNotNull(value, "The number cannot be null");
		return value != null ? NumberUtil.getDecimalFormat(minimumIntegerSize, maximumIntegerSize, minimumDecimalSize, maximumDecimalSize,
				decimalSeparator, groupSize, groupSeparator).format(value) : "";
	}

	/**
	 * Permite tomar un string y convertirlo en un n�mero de acuerdo a la configuraci�n del formato del mismo.
	 * 
	 * <br>
	 * <br>
	 * <b>NUMEROS ENTEROS</b><br>
	 * 
	 * <ul>
	 * <li><code>value</code>: <i>"000.001.234.567.890"</i></li>
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
	 * <b>OUTPUT: 1234567890</b>
	 * </pre>
	 * 
	 * <ul>
	 * <li><code>value</code>: <i>"-00_00_01_23_45"</i></li>
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
	 * <b>OUTPUT: -12345</b>
	 * </pre>
	 * 
	 * <br>
	 * <b>NUMEROS FRACCIONARIOS</b><br>
	 * <br>
	 * 
	 * <ul>
	 * <li><code>value</code>: <i>"000.001.234.567.890,1234560000"</i></li>
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
	 * <b>OUTPUT: 1234567890.123456</b>
	 * </pre>
	 * 
	 * <ul>
	 * <li><code>value</code>: <i>"000.001.234.567.890,123"</i></li>
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
	 * <b>OUTPUT: 1234567890.123456</b>
	 * </pre>
	 * 
	 * @param value
	 *            El string que vamos a parsear.
	 * @param minimumIntegerSize
	 *            El tama�o m�nimo del campo de los enteros. Puede ser <code>null</code> y toma el valor <code>0</code>.
	 * @param maximumIntegerSize
	 *            El tama�o m�ximo del campo de los enteros. Puede ser <code>null</code> y toma el valor <code>{@link Integer#MAX_VALUE}</code>.
	 * @param minimumDecimalSize
	 *            El tama�o m�nimo del campo de los decimales. Puede ser <code>null</code> y toma el valor <code>0</code>.
	 * @param maximumDecimalSize
	 *            El tama�o m�ximo del campo de los decimales. Puede ser <code>null</code> y toma el valor <code>{@link Integer#MAX_VALUE}</code>.
	 * @param decimalSeparator
	 *            El separador de los decimales. Puede ser <code>null</code> y toma el valor por omisi�n del sistema.
	 * @param groupSize
	 *            El tama�o del grupo de enteros. Puede ser <code>null</code> y en ese caso, no se agrupan los enteros.
	 * @param groupSeparator
	 *            El separador del grupo de enteros. Puede ser <code>null</code> y toma el valor por omisi�n del sistema.
	 * @return El entero formateado de acuerdo a los par�metros recibidos.
	 * @throws UncheckedException
	 *             En caso de que alguno de los par�metros sea nulo.
	 */
	public static Number formatString(String value, Integer minimumIntegerSize, Integer maximumIntegerSize, Integer minimumDecimalSize,
			Integer maximumDecimalSize, Character decimalSeparator, Integer groupSize, Character groupSeparator) {
		VerifierUtil.checkNotNull(value, "The number cannot be null");
		try {
			return NumberUtil.getDecimalFormat(minimumIntegerSize, maximumIntegerSize, minimumDecimalSize, maximumDecimalSize, decimalSeparator,
					groupSize, groupSeparator).parse(value);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Permite crear el formateador de los n�meros de acuerdo a los par�metos recibidos.
	 * 
	 * @param minimumIntegerSize
	 *            El tama�o m�nimo del campo de los enteros. Puede ser <code>null</code> y toma el valor <code>0</code>.
	 * @param maximumIntegerSize
	 *            El tama�o m�ximo del campo de los enteros. Puede ser <code>null</code> y toma el valor <code>{@link Integer#MAX_VALUE}</code>.
	 * @param minimumDecimalSize
	 *            El tama�o m�nimo del campo de los decimales. Puede ser <code>null</code> y toma el valor <code>0</code>.
	 * @param maximumDecimalSize
	 *            El tama�o m�ximo del campo de los decimales. Puede ser <code>null</code> y toma el valor <code>{@link Integer#MAX_VALUE}</code>.
	 * @param decimalSeparator
	 *            El separador de los decimales. Puede ser <code>null</code> y toma el valor por omisi�n del sistema.
	 * @param groupSize
	 *            El tama�o del grupo de enteros. Puede ser <code>null</code> y en ese caso, no se agrupan los enteros.
	 * @param groupSeparator
	 *            El separador del grupo de enteros. Puede ser <code>null</code> y toma el valor por omisi�n del sistema.
	 * @return El formateador de n�meros.
	 */
	private static DecimalFormat getDecimalFormat(Integer minimumIntegerSize, Integer maximumIntegerSize, Integer minimumDecimalSize,
			Integer maximumDecimalSize, Character decimalSeparator, Integer groupSize, Character groupSeparator) {
		DecimalFormat formatter = NumberUtil.getNumberFormat();
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
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
		if (groupSize != null && groupSize > 0) {
			formatter.setGroupingSize(groupSize);
			formatter.setGroupingUsed(true);
			if (groupSeparator != null) {
				symbols.setGroupingSeparator(groupSeparator);
			}
		} else {
			formatter.setGroupingUsed(false);
		}
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
		if (decimalSeparator != null) {
			symbols.setDecimalSeparator(decimalSeparator);
		}
		formatter.setDecimalFormatSymbols(symbols);
		return formatter;
	}

	/**
	 * La funci�n que agrega una cantidad dada de ceros para que la salida tenga una longitud m�nima recibida.
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
	 *            La cantidad minima de digitos que queremos que tenga el n�mero formateado.
	 * @return La cadena con el valor recibido formateado con la longitud m�nima recibida.
	 */
	public static String zeroFill(Number value, Integer minimumSize) {
		return value != null ? NumberUtil.formatNumber(value, minimumSize, null, null, null, null, null, null) : "";
	}
}