package com.common.util.business.tool;

import java.io.Serializable;

/**
 * La clase que nos permite manipular las importes de dinero dentro de un sistema.
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class CurrencyUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * El simbolo que vamos a ocupar para referenciar las monedas de uso comercial. Por omisión tiene el valor:
	 * 
	 * <pre>
	 * currencySimbol = &quot;$&quot;
	 * </pre>
	 */
	protected static String currencySimbol = "$";
	/**
	 * La cantidad de decimales que queremos que se muestren en el valor formateado. Por omisión va a tener el valor:
	 * 
	 * <pre>
	 * decimals = 2
	 * </pre>
	 */
	protected static Integer decimals = 2;
	/**
	 * El caracter que vamos a utilizar como separador de grupos. Por omisión va a tener el valor:
	 * 
	 * <pre>
	 * groupSeparator = &quot;.&quot;
	 * </pre>
	 */
	protected static Character groupSeparator = '.';
	/**
	 * El caracter que vamos a utilizar como separador de decimales. Por omisión va a tener el valor:
	 * 
	 * <pre>
	 * decimalSeparator = &quot;,&quot;
	 * </pre>
	 */
	protected static Character decimalSeparator = ',';

	/**
	 * Se encarga de cargar el simbolo del dinero que vamos a utilizar.
	 * 
	 * @see CurrencyUtil#currencySimbol
	 * 
	 * @param currencySimbol
	 *            El simbolo para referenciar las monedas de uso comercial.
	 */
	public void setCurrencySimbol(String currencySimbol) {
		CurrencyUtil.currencySimbol = currencySimbol;
	}

	/**
	 * Se encarga de cargar la cantidad de decimales que vamos a mostrar para la parte decimal del dinero.
	 * 
	 * @see CurrencyUtil#decimals
	 * 
	 * @param decimals
	 *            La cantidad de dígitos decinales que vamos a usar para formatear el dinero.
	 */
	public void setDecimals(Integer decimals) {
		CurrencyUtil.decimals = decimals;
	}

	/**
	 * Se encarga de cargar el caracter que vamos a utilizar para separar los grupos de la parte entera del dinero.
	 * 
	 * @see CurrencyUtil#groupSeparator
	 * 
	 * @param groupSeparator
	 *            El caracter para separar los grupos de la parte entera del dinero.
	 */
	public void setGroupSeparator(Character groupSeparator) {
		CurrencyUtil.groupSeparator = groupSeparator;
	}

	/**
	 * Se encarga de cargar el caracter que vamos a utilizar para separar la parte decimal del dinero.
	 * 
	 * @see CurrencyUtil#decimalSeparator
	 * 
	 * @param decimalSeparator
	 *            El caracter para separar la parte decimal del dinero.
	 */
	public void setDecimalSeparator(Character decimalSeparator) {
		CurrencyUtil.decimalSeparator = decimalSeparator;
	}

	/**
	 * Se encarga de transformar un número recibido en una cadena con el formato del dinero que tenemos dentro del sistema.
	 * 
	 * @param currency
	 *            El valor que queremos formatear de acuerdo al formato del dinero que tenemos dentro del sistema.
	 * @return La cadena que contiene el valor del dinero formateado. En caso de que el valor sea nulo, se retorna una cadena vacia.
	 */
	public static String formatCurrency(Double currency) {
		// Si el valor es nulo, retornamos la cadena vacia.
		if (currency == null) {
			return "";
		}

		String formatedValue = NumberUtil.formatNumber(currency, 1, null, CurrencyUtil.decimals, CurrencyUtil.decimals,
				CurrencyUtil.decimalSeparator, 3, CurrencyUtil.groupSeparator);

		// Retornamos el valor formateado.
		return CurrencyUtil.currencySimbol + " " + formatedValue;
	}
}