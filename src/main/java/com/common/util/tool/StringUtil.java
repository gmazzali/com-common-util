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
	 * <li>VerifierUtil.isEmpty(null) = true</li>
	 * <li>VerifierUtil.isEmpty("") = true</li>
	 * <li>VerifierUtil.isEmpty(" ") = false</li>
	 * <li>VerifierUtil.isEmpty("bob") = false</li>
	 * <li>VerifierUtil.isEmpty(" bob ") = false</li>
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
	 * <li>VerifierUtil.isBlank(null) = true</li>
	 * <li>VerifierUtil.isBlank("") = true</li>
	 * <li>VerifierUtil.isBlank(" ") = true</li>
	 * <li>VerifierUtil.isBlank("bob") = false</li>
	 * <li>VerifierUtil.isBlank(" bob ") = false</li>
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