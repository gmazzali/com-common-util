package com.common.util.business.tool;

import java.io.Serializable;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.common.util.business.tool.collection.ArrayUtil;
import com.common.util.business.util.Patterns;

/**
 * La utilería para el manejo de los String.
 * 
 * @since 06/03/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class StringUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(StringUtil.class);

	/**
	 * Se encarga de hacer una llamada al método {@link Object#toString()} del modo Null-Safe.
	 * 
	 * @param entity
	 *            La entidad que vamos a convertir en un string. Puede ser <code>null</code>.
	 * @return La cadena que corresponde con el elemento recibido convertido a un string, o un valor nulo si la entidad es <code>null</code>.
	 */
	public static String toString(Object entity) {
		return entity != null ? entity.toString() : null;
	}

	/**
	 * Se encarga de verificar que una cadena de caracteres este vacia. Retorna <code>true</code> en caso de que la cadena recibida sea
	 * <code>null</code>.
	 * 
	 * <pre>
	 * StringUtil.isEmpty(null)    = true
	 * StringUtil.isEmpty("")      = true
	 * StringUtil.isEmpty(" ")     = false
	 * StringUtil.isEmpty("bob")   = false
	 * StringUtil.isEmpty(" bob ") = false
	 * </pre>
	 * 
	 * @param string
	 *            La cadena que vamos a verificar.
	 * @return <code>true</code> en caso de que la cadena recibida sea <code>null</code> o este vacia completamente, en caso contrario retorna
	 *         <code>false</code>.
	 */
	public static boolean isEmpty(String string) {
		return string != null ? string.isEmpty() : true;
	}

	/**
	 * Se encarga de verificar que una cadena de caracteres no este vacia. Retorna <code>false</code> en caso de que la cadena recibida sea
	 * <code>null</code>.
	 * 
	 * <pre>
	 * StringUtil.isNotEmpty(null)    = false
	 * StringUtil.isNotEmpty("")      = false
	 * StringUtil.isNotEmpty(" ")     = true
	 * StringUtil.isNotEmpty("bob")   = true
	 * StringUtil.isNotEmpty(" bob ") = true
	 * </pre>
	 * 
	 * @param string
	 *            La cadena que vamos a verificar.
	 * @return <code>false</code> en caso de que la cadena recibida sea <code>null</code> o este vacia completamente, en caso contrario retorna
	 *         <code>true</code>.
	 */
	public static boolean isNotEmpty(String string) {
		return !StringUtil.isEmpty(string);
	}

	/**
	 * Se encarga de verificar que una cadena de caracteres este compuesta solo de espacios en blanco. Retorna <code>true</code> en caso de que la
	 * cadena recibida sea <code>null</code>.
	 * 
	 * <pre>
	 * StringUtil.isBlank(null)     = true
	 * StringUtil.isBlank("")       = true
	 * StringUtil.isBlank(" ")      = true
	 * StringUtil.isBlank("bob")    = false
	 * StringUtil.isBlank(" bob ")  = false
	 * </pre>
	 * 
	 * @param string
	 *            La cadena que vamos a verificar.
	 * @return <code>true</code> en caso de que la cadena recibida sea nula o este compuesta solo de espacios en blanco, en caso contrario retorna
	 *         <code>false</code>.
	 */
	public static boolean isBlank(String string) {
		return string != null ? string.trim().isEmpty() : true;
	}

	/**
	 * Se encarga de verificar que una cadena de caracteres no este compuesta solo de espacios en blanco. Retorna <code>true</code> en caso de que la
	 * cadena recibida sea <code>null</code>.
	 * 
	 * <pre>
	 * StringUtil.isNotBlank(null)     = false
	 * StringUtil.isNotBlank("")       = false
	 * StringUtil.isNotBlank(" ")      = false
	 * StringUtil.isNotBlank("bob")    = true
	 * StringUtil.isNotBlank(" bob ")  = true
	 * </pre>
	 * 
	 * @param string
	 *            La cadena que vamos a verificar.
	 * @return <code>false</code> en caso de que la cadena recibida sea nula o este compuesta solo de espacios en blanco, en caso contrario retorna
	 *         <code>true</code>.
	 */
	public static boolean isNotBlank(String string) {
		return !StringUtil.isBlank(string);
	}

	/**
	 * Permite cargar dentro de un string, una cadena de caracteres que recibimos como parámetros.
	 * 
	 * <pre>
	 * StringUtil.arrayToString()                      = ""
	 * StringUtil.arrayToString(null)                  = ""
	 * StringUtil.arrayToString({})                    = ""
	 * StringUtil.arrayToString({'a', 'b', 'c', 'd'})  = "abcd"
	 * </pre>
	 * 
	 * @see StringUtil#stringToArray(String)
	 * 
	 * @param characters
	 *            Los caracteres que recibimos para devolverlos dentro de un string.
	 * @return El string que contiene todos los caracteres que recibimos como parámetros.
	 */
	public static String arrayToString(Character... characters) {
		StringBuffer buffer = new StringBuffer();
		if (characters != null) {
			for (Character character : characters) {
				if (character != null) {
					buffer.append(character);
				}
			}
		}
		return buffer.toString();
	}

	/**
	 * Permite convertir una cadena de texto en un arreglo de todos los caracteres que tiene la misma. En caso de que la cadena sea <code>null</code>
	 * se retorna un arreglo vacío.
	 * 
	 * <pre>
	 * StringUtil.arrayToString(null)    = {}
	 * StringUtil.arrayToString("")      = {}
	 * StringUtil.arrayToString(" ")     = {' '}
	 * StringUtil.arrayToString("  ")    = {' ', ' '}
	 * StringUtil.arrayToString("abcd")  = {'a', 'b', 'c', 'd'}
	 * </pre>
	 * 
	 * @see StringUtil#arrayToString(Character...)
	 * 
	 * @param string
	 *            La cadena que vamos a convertir en un arreglo de caracteres.
	 * @return El arreglo de caracteres que corresponde con la cadena recibida.
	 */
	public static Character[] stringToArray(String string) {
		if (StringUtil.isEmpty(string)) {
			return new Character[] {};
		}

		Character[] characters = new Character[string.length()];
		for (int i = 0; i < string.length(); i++) {
			characters[i] = string.charAt(i);
		}

		return characters;
	}

	/**
	 * Quita los caracteres de control (char &lt;= 32) de ambos lados del string recibido, retornando <code>null</code> cuando recibimos un string
	 * <code>null</code>.
	 * 
	 * <pre>
	 * StringUtil.trim(null)       = null
	 * StringUtil.trim("")         = ""
	 * StringUtil.trim(" ")        = ""
	 * StringUtil.trim("abc")      = "abc"
	 * StringUtil.trim(" abc ")    = "abc"
	 * StringUtil.trim(" a b c ")  = "a b c"
	 * </pre>
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
	 * Quita los caracteres de control (char &lt;= 32) de ambos lados del string recibido, retornando <code>""</code> cuando recibimos un string
	 * <code>null</code>.
	 * 
	 * <pre>
	 * StringUtil.trimToEmpty(null)       = ""
	 * StringUtil.trimToEmpty("")         = ""
	 * StringUtil.trimToEmpty(" ")        = ""
	 * StringUtil.trimToEmpty("abc")      = "abc"
	 * StringUtil.trimToEmpty(" abc ")    = "abc"
	 * StringUtil.trimToEmpty(" a b c ")  = "a b c"
	 * </pre>
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
	 * Quita los caracteres de control (char &lt;= 32) de ambos lados del String recibido, retornando <code>null</code> cuando recibimos un string
	 * <code>null</code> o cuando el String recibido es vacío.
	 * 
	 * <pre>
	 * StringUtil.trimToNull(null)       = null
	 * StringUtil.trimToNull("")         = null
	 * StringUtil.trimToNull(" ")        = null
	 * StringUtil.trimToNull("abc")      = "abc"
	 * StringUtil.trimToNull(" abc ")    = "abc"
	 * StringUtil.trimToNull(" a b c ")  = "a b c"
	 * </pre>
	 * 
	 * @see StringUtil#trim(String)
	 * @see StringUtil#trimToEmpty(String)
	 * 
	 * @param string
	 *            La cadena que vamos a cortar, puede ser nula.
	 * @return La cadena sin espacios laterales o <code>null</code> en caso de que la cadena recibida sea nula o vacía.
	 */
	public static String trimToNull(String string) {
		string = trim(string);
		return StringUtil.isEmpty(string) ? null : string;
	}

	/**
	 * Quita los caracteres recibidos del comienzo y el fin del string. Su funcionalidad es similar a {@link StringUtil#trim(String)} pero con los
	 * caracteres recibidos en el segundo parámetro en vez de los caracteres de control. En caso de que el string sea <code>null</code> se retorna
	 * <code>null</code>. Si los caracteres a quitar son <code>null</code>, se quitan los caracteres en blanco definidos por
	 * {@link Character#isWhitespace(char)}.
	 * 
	 * <pre>
	 * StringUtil.strip(null, *)                      = null
	 * StringUtil.strip("", *)                        = ""
	 * StringUtil.strip("abc")                        = "abc"
	 * StringUtil.strip(" abc")                       = "abc"
	 * StringUtil.strip("abc ")                       = "abc"
	 * StringUtil.strip(" abc ")                      = "abc"
	 * StringUtil.strip(" a b c ")                    = "a b c"
	 * StringUtil.strip(" abcd", 'c', 'd', 'e', 'f')  = " ab"
	 * </pre>
	 * 
	 * @param string
	 *            La cadena a la que le vamos a quitar los caracteres al comienzo y al final. Puede ser nula.
	 * @param removeCharacters
	 *            Los caracteres que vamos a quitar.
	 * @return La cadena con los caracteres al principio y al final quitados, o <code>null</code> en caso de que la cadena recibida sea nula.
	 */
	public static String strip(String string, Character... removeCharacters) {
		if (StringUtil.isEmpty(string)) {
			return string;
		}
		return StringUtil.stripEnd(StringUtil.stripStart(string, removeCharacters), removeCharacters);
	}

	/**
	 * Quita los caracteres en blanco del comienzo y el fin del string. Su funcionalidad es similar a {@link StringUtil#trim(String)} pero con los
	 * caracteres recibidos en el segundo parámetro en vez de los caracteres de control. En caso de que el string sea <code>null</code> se retorna
	 * <code>""</code>. Si los caracteres a quitar son <code>null</code>, se quitan los caracteres en blanco definidos por
	 * {@link Character#isWhitespace(char)}.
	 * 
	 * <pre>
	 * StringUtil.stripToEmpty(null)                  = ""
	 * StringUtil.stripToEmpty("")                    = ""
	 * StringUtil.stripToEmpty(" ")                   = ""
	 * StringUtil.stripToEmpty("abc")                 = "abc"
	 * StringUtil.stripToEmpty(" abc")                = "abc"
	 * StringUtil.stripToEmpty("abc ")                = "abc"
	 * StringUtil.stripToEmpty(" abc ")               = "abc"
	 * StringUtil.stripToEmpty(" a b c ")             = "a b c"
	 * StringUtil.stripToEmpty("abc", 'a')            = "bc"
	 * StringUtil.stripToEmpty("abc", 'a', 'b', 'c')  = ""
	 * </pre>
	 * 
	 * @param string
	 *            La cadena a la que le vamos a quitar los blancos al comienzo y al final. Puede ser nula.
	 * @param removeCharacters
	 *            Los caracteres que vamos a quitar.
	 * @return La cadena con los espacios al principio y al final quitados, o <code>""</code> en caso de que la cadena recibida sea nula o vacía.
	 */
	public static String stripToEmpty(String string, Character... removeCharacters) {
		return string != null ? StringUtil.strip(string, removeCharacters) : "";
	}

	/**
	 * Quita los caracteres en blanco del comienzo y el fin del string. Su funcionalidad es similar a {@link #trimToNull(String)} pero solo con los
	 * caracteres en blanco definidos por {@link Character#isWhitespace(char)}. En caso de que el string sea <code>null</code> o vacío se retorna
	 * <code>null</code>.
	 * 
	 * <pre>
	 * StringUtil.stripToNull(null)                  = null
	 * StringUtil.stripToNull("")                    = null
	 * StringUtil.stripToNull(" ")                   = null
	 * StringUtil.stripToNull("abc")                 = "abc"
	 * StringUtil.stripToNull(" abc")                = "abc"
	 * StringUtil.stripToNull("abc ")                = "abc"
	 * StringUtil.stripToNull(" abc ")               = "abc"
	 * StringUtil.stripToNull(" a b c ")             = "a b c"
	 * StringUtil.stripToNull("abc", 'a')            = "bc"
	 * StringUtil.stripToNull("abc", 'a', 'b', 'c')  = null
	 * </pre>
	 * 
	 * @param string
	 *            La cadena a la que le vamos a quitar los blancos al comienzo y al final. Puede ser nula.
	 * @param removeCharacters
	 *            Los caracteres que vmosa a quitarle a la cadena de texto.
	 * @return La cadena con los espacios al principio y al final quitados, o <code>null</code> en caso de que la cadena recibida sea nula o vacía.
	 */
	public static String stripToNull(String string, Character... removeCharacters) {
		if (string == null) {
			return null;
		}
		string = StringUtil.strip(string, removeCharacters);
		return StringUtil.isEmpty(string) ? null : string;
	}

	/**
	 * Quita los caracteres recibidos del comienzo del string. En caso de que el string sea <code>null</code> se retorna <code>null</code>. Si los
	 * caracteres a quitar son <code>null</code>, se quitan los caracteres en blanco definidos por {@link Character#isWhitespace(char)}.
	 * 
	 * <pre>
	 * StringUtil.stripStart(null, *)                  = null
	 * StringUtil.stripStart("", *)                    = ""
	 * StringUtil.stripStart("abc", null)              = "abc"
	 * StringUtil.stripStart(" abc", null)             = "abc"
	 * StringUtil.stripStart("abc ", null)             = "abc "
	 * StringUtil.stripStart(" abc ", null)            = "abc "
	 * StringUtil.stripStart(" a b c ", null)          = "a b c "
	 * StringUtil.stripStart("xyabc ", 'x', 'y', 'z')  = "abc "
	 * </pre>
	 * 
	 * @param string
	 *            La cadena a la que le vamos a quitar los caracteres al comienzo. Puede ser nula.
	 * @param removeCharacters
	 *            Los caracteres que vamos a quitar.
	 * @return La cadena con los caracteres al principio quitados, o <code>null</code> en caso de que la cadena recibida sea nula.
	 */
	public static String stripStart(String string, Character... removeCharacters) {
		if (StringUtil.isEmpty(string)) {
			return string;
		}

		int current = 0;
		int length = string.length();
		String characters = StringUtil.arrayToString(removeCharacters);

		if (StringUtil.isEmpty(characters)) {
			while ((current != length) && Character.isWhitespace(string.charAt(current))) {
				current++;
			}
		} else {
			while ((current != length) && (characters.indexOf(string.charAt(current)) != -1)) {
				current++;
			}
		}

		return string.substring(current);
	}

	/**
	 * Quita los caracteres recibidos del final del string. En caso de que el string sea <code>null</code> se retorna <code>null</code>. Si los
	 * caracteres recibidos son nulos, se quitan los caracteres en blanco definidos por {@link Character#isWhitespace(char)}.
	 * 
	 * <pre>
	 * StringUtils.stripEnd(null, *)                   = null
	 * StringUtils.stripEnd("", *)                     = ""
	 * StringUtils.stripEnd("abc", "")                 = "abc"
	 * StringUtils.stripEnd("abc", null)               = "abc"
	 * StringUtils.stripEnd("  abc", null)             = "  abc"
	 * StringUtils.stripEnd("abc  ", null)             = "abc"
	 * StringUtils.stripEnd(" abc ", null)             = " abc"
	 * StringUtils.stripEnd("  abcyx", 'x', 'y', 'z')  = "  abc"
	 * StringUtils.stripEnd("120.00", '.', '0')        = "12"
	 * </pre>
	 * 
	 * @param string
	 *            La cadena a la que le vamos a quitar los caracteres al final. Puede ser nula.
	 * @param removeCharacters
	 *            Los caracteres que vamos a quitar.
	 * @return La cadena con los caracteres al final quitados, o <code>null</code> en caso de que la cadena recibida sea nula.
	 */
	public static String stripEnd(String string, Character... removeCharacters) {
		if (StringUtil.isEmpty(string)) {
			return string;
		}

		int current = string.length();
		String characters = StringUtil.arrayToString(removeCharacters);

		if (StringUtil.isEmpty(characters)) {
			while ((current != 0) && Character.isWhitespace(string.charAt(current - 1))) {
				current--;
			}
		} else {
			while ((current != 0) && (characters.indexOf(string.charAt(current - 1)) != -1)) {
				current--;
			}
		}

		return string.substring(0, current);
	}

	/**
	 * Se encarga de validar que una cadena de caracteres recibida coincida con un patrón de validación.
	 * 
	 * @see Patterns
	 * 
	 * @param string
	 *            La cadena que vamos a validar.
	 * @param pattern
	 *            El patrón con el que vamos a validar la cadena.
	 * @return <code>true</code> en caso que la cadena recibida coincida con el patrón recibido, en caso contrario, retornamos <code>false</code>.
	 */
	public static boolean match(String string, String pattern) {
		if (string == null) {
			return pattern == null;
		}
		try {
			return Pattern.matches(pattern, string);
		} catch (Exception ex) {
			LOGGER.error("pattern failed", ex);
			return false;
		}
	}

	/**
	 * Permite crear una cadena conteniendo el listado de los objetos que recibimos en el arreglo separados por un delimitador dado.
	 * 
	 * @param objects
	 *            El arreglo que vamos a recorrer para convertirlo en una cadena.
	 * @param delimiter
	 *            El delimitador que vamos a usar entre medio de cada objeto del arreglo.
	 * @return La cadena convertida de todos los objetos del arreglo separador por un delimitador entre medio de cada par de objetos. En caso de
	 *         recibir un arreglo vacío, se retorna una cadena vacía.
	 */
	public static String arrayToDelimitedString(Object[] objects, String delimiter) {
		if (ArrayUtil.isEmpty(objects)) {
			return "";
		}

		if (objects.length == 1) {
			return StringUtil.toString(objects[0]);
		}

		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < objects.length; i++) {
			if (i > 0) {
				buffer.append(delimiter);
			}
			buffer.append(objects[i]);
		}
		return buffer.toString();
	}
}