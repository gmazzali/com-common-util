package com.common.util.tool;

import java.util.regex.Pattern;

/**
 * La clase encargada de verificar que los campos de entrada de una aplicación tenga los valores correctos y que no sean cualquier cosa.
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class VerifierUtil {

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
	 * @return <i>true</i> en caso que la cadena recibida tcoincida con el patrón recibido, en caso contrario, retornamos <i>false</i>.
	 */
	public boolean matchString(String string, String pattern) {
		return (string == null) ? false : Pattern.matches(pattern, string.toLowerCase().trim());
	}
}