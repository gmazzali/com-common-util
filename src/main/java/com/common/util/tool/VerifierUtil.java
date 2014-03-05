package com.common.util.tool;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * La clase encargada de verificar que los campos de entrada de una aplicaci�n tenga los valores correctos y que no sean cualquier cosa.
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
	 * Se encarga de validar que una cadena de caracteres recibida coincida con un patr�n de validaci�n.
	 * 
	 * @see PatternUtil
	 * 
	 * @param string
	 *            La cadena que vamos a validar.
	 * @param pattern
	 *            El patr�n con el que vamos a validar la cadena.
	 * @return <i>true</i> en caso que la cadena recibida coincida con el patr�n recibido, en caso contrario, retornamos <i>false</i>.
	 */
	public static boolean matchString(String string, String pattern) {
		return (string == null) ? false : Pattern.matches(pattern, string.toLowerCase().trim());
	}

	/**
	 * Se encarga de validar que los 2 elementos recibidos sean iguales. Permite recibir los 2 elementos nulos, lo que retorna que los elementos sean
	 * iguales.
	 * 
	 * <ul>
	 * <li>VerifierUtil.<String>isEqual(null, null) = true</li>
	 * <li>VerifierUtil.<String>isEqual("bob", "bob") = true</li>
	 * <li>VerifierUtil.<String>isEqual("bob", null) = false</li>
	 * <li>VerifierUtil.<String>isEqual(null, "bob") = false</li>
	 * <li>VerifierUtil.<String>isEqual("bob1", "bob2") = false</li>
	 * </ul>
	 * 
	 * @see Object#equals(Object)
	 * 
	 * @param firstEntity
	 *            El primer elemento que vamos a comparar, puede ser nulo.
	 * @param secondEntity
	 *            El segundo elemento que vamos a comparar, puede ser nulo.
	 * @return <i>true</i> en caso que la primer entidad sea igual a la segunda, en caso contrario, retornamos <i>false</i>.
	 */
	public static <S extends Serializable> boolean equals(S firstEntity, S secondEntity) {
		if (firstEntity == null && secondEntity == null) {
			return true;
		} else if (firstEntity != null) {
			return firstEntity.equals(secondEntity);
		} else {
			return secondEntity.equals(firstEntity);
		}
	}
}