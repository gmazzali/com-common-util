package com.common.util.tool;

import java.io.Serializable;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * La utilería para el manejo de los String.
 * 
 * @since 06/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class StringUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(StringUtil.class);

	/**
	 * Se encarga de verificar que una cadena de caracteres este vacia. Retorna <i>true</i> en caso de que la cadena recibida sea nula.
	 * 
	 * <ul>
	 * <li>StringUtil.isEmpty(null) = true</li>
	 * <li>StringUtil.isEmpty("") = true</li>
	 * <li>StringUtil.isEmpty(" ") = false</li>
	 * <li>StringUtil.isEmpty("bob") = false</li>
	 * <li>StringUtil.isEmpty(" bob ") = false</li>
	 * </ul>
	 * 
	 * @param string
	 *            La cadena que vamos a verificar.
	 * @return <i>true</i> en caso de que la cadena recibida sea nula o este vacia completamente, en caso contrario retorna <i>false</i>.
	 */
	public static boolean isEmpty(String string) {
		return string != null ? string.isEmpty() : true;
	}

	/**
	 * Se encarga de verificar que una cadena de caracteres este compuesta solo de espacios en blanco. Retorna <i>true</i> en caso de que la cadena
	 * recibida sea nula.
	 * 
	 * <ul>
	 * <li>StringUtil.isBlank(null) = true</li>
	 * <li>StringUtil.isBlank("") = true</li>
	 * <li>StringUtil.isBlank(" ") = true</li>
	 * <li>StringUtil.isBlank("bob") = false</li>
	 * <li>StringUtil.isBlank(" bob ") = false</li>
	 * </ul>
	 * 
	 * @param string
	 *            La cadena que vamos a verificar.
	 * @return <i>true</i> en caso de que la cadena recibida sea nula o este compuesta solo de espacios en blanco, en caso contrario retorna
	 *         <i>false</i>.
	 */
	public static boolean isBlank(String string) {
		return string != null ? string.trim().isEmpty() : true;
	}

	/**
	 * Quita los caracteres de control (char &lt;= 32) de ambos lados del String recibido, retornando <code>null</code> cuando recibimos un String
	 * <code>null</code>.
	 * 
	 * <ul>
	 * <li>StringUtil.trim(null) = null</li>
	 * <li>StringUtil.trim("") = ""</li>
	 * <li>StringUtil.trim(" ") = ""</li>
	 * <li>StringUtil.trim("abc") = "abc"</li>
	 * <li>StringUtil.trim(" abc ") = "abc"</li>
	 * <li>StringUtil.trim(" a b c ") = "a b c"</li>
	 * </ul>
	 * 
	 * @see String#trim()
	 * @see StringUtil#trimToEmpty(String)
	 * @see StringUtil#trimToNull(String)
	 * 
	 * @param string
	 *            La cadena que vamos a cortar, puede ser nula.
	 * @return La cadena sin espacios laterales o <code>null</code> en caso de que la cadena recibida sea nula.
	 */
	public static String trim(String string) {
		return string != null ? string.trim() : null;
	}

	/**
	 * Quita los caracteres de control (char &lt;= 32) de ambos lados del String recibido, retornando <code>""</code> cuando recibimos un String
	 * <code>null</code>.
	 * 
	 * <ul>
	 * <li>StringUtil.trim(null) = ""</li>
	 * <li>StringUtil.trim("") = ""</li>
	 * <li>StringUtil.trim(" ") = ""</li>
	 * <li>StringUtil.trim("abc") = "abc"</li>
	 * <li>StringUtil.trim(" abc ") = "abc"</li>
	 * <li>StringUtil.trim(" a b c ") = "a b c"</li>
	 * </ul>
	 * 
	 * @see StringUtil#trim(String)
	 * @see StringUtil#trimToNull(String)
	 * 
	 * @param string
	 *            La cadena que vamos a cortar, puede ser nula.
	 * @return La cadena sin espacios laterales o <code>""</code> en caso de que la cadena recibida sea nula o vacía.
	 */
	public static String trimToEmpty(String string) {
		return string != null ? string.trim() : "";
	}

	/**
	 * Quita los caracteres de control (char &lt;= 32) de ambos lados del String recibido, retornando <code>null</code> cuando recibimos un String
	 * <code>null</code> o cuando el String recibido es vacío.
	 * 
	 * <ul>
	 * <li>StringUtil.trim(null) = null</li>
	 * <li>StringUtil.trim("") = null</li>
	 * <li>StringUtil.trim(" ") = null</li>
	 * <li>StringUtil.trim("abc") = "abc"</li>
	 * <li>StringUtil.trim(" abc ") = "abc"</li>
	 * <li>StringUtil.trim(" a b c ") = "a b c"</li>
	 * </ul>
	 * 
	 * @see StringUtil#trim(String)
	 * @see StringUtil#trimToEmpty(String)
	 * 
	 * @param string
	 *            La cadena que vamos a cortar, puede ser nula.
	 * @return La cadena sin espacios laterales o <code>null</code> en caso de que la cadena recibida sea nula o vacía.
	 */
	public static String trimToNull(String string) {
		String temp = trim(string);
		return StringUtil.isEmpty(temp) ? null : temp;
	}

	/**
	 * Se encarga de validar que una cadena de caracteres recibida coincida con un patrón de validación.
	 * 
	 * @see PatternUtil
	 * 
	 * @param string
	 *            La cadena que vamos a validar.
	 * @param pattern
	 *            El patrón con el que vamos a validar la cadena.
	 * @return <i>true</i> en caso que la cadena recibida coincida con el patrón recibido, en caso contrario, retornamos <i>false</i>.
	 */
	public static boolean match(String string, String pattern) {
		if (string == null) {
			return pattern == null;
		}
		try {
			return Pattern.matches(pattern, string);
		} catch (Exception ex) {
			log.error("pattern failed", ex);
			return false;
		}
	}
}