package com.common.util.tools;

import java.io.IOException;
import java.net.InetAddress;
import java.util.regex.Pattern;

/**
 * La clase encargada de verificar que los campos de entrada de una aplicación tenga los valores correctos y que no sean cualquier cosa.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Verifier {

	/**
	 * Función encargada de verificar que el nombre de una persona física sea válido dentro del sistema.
	 * 
	 * <pre>
	 * nombre |= [a-z]+(['|\s][a-z]+)*
	 * </pre>
	 * 
	 * @param name
	 *            La cadena que tiene el nombre de la persona que vamos a veriicar.
	 * @return FALSE en caso de que el nombre recibido sea nulo, este vacio o contenga algún elemento no válido para un nombre, en caso contrario
	 *         retorna TRUE.
	 */
	public static boolean isValidPersonName(String name) {
		String pattern = "[a-zñáéíóúü]+(['|\\s][a-zñáéíóúü]+)*";
		return (name == null) ? false : Pattern.matches(pattern, name.toLowerCase().trim());
	}

	/**
	 * Función encargada de verificar que el apellido de una persona física sea válido dentro del sistema.
	 * 
	 * <pre>
	 * apellido |= [a-z]+(['|\s][a-z]+)*
	 * </pre>
	 * 
	 * @param lastname
	 *            La cadena que contiene el apellido de la persona que vamos a verificar.
	 * @return FALSE en caso de que el apellido recibido sea nulo, este vacio o contenga algún caracter no válido para un apellido, en caso contrario
	 *         retorna TRUE.
	 */
	public static boolean isValidPersonLastname(String lastname) {
		String pattern = "[a-zñáéíóúü]+(['|\\s][a-zñáéíóúü]+)*";
		return (lastname == null) ? false : Pattern.matches(pattern, lastname.toLowerCase().trim());
	}

	/**
	 * Función encargada de verificar que el número recibido dentro de la cadena sea válido para un número de CUIT de una persona física o jurídica.
	 * 
	 * <pre>
	 * cuit |= [0-9]{2}-[0-9]{6,8}-[0-9]{1}
	 * </pre>
	 * 
	 * @param cuit
	 *            El cuit de la empresa que va a verificarse.
	 * @return FALSE en caso de que la cadena recibida sea nula o contenga valores inválidos para un CUIT, en caso contrario retornará TRUE.
	 */
	public static boolean isValidCuitNumber(String cuit) {
		String pattern = "^\\d{2}\\-\\d{6,8}\\-\\d{1}$";
		return (cuit == null) ? false : Pattern.matches(pattern, cuit.toLowerCase().trim());
	}

	/**
	 * Función encargada de verificar que una cadena contenga un DNI válido dentro del sistema.
	 * 
	 * <pre>
	 * DNI |= [0-9]{6,8}
	 * </pre>
	 * 
	 * @param dni
	 *            La cadena que contiene el DNI a verificarse.
	 * @return FALSE en caso de que la cadena recibida sea nula, este vacia o contenga caracteres invalidos, en caso contrario retorna TRUE.
	 */
	public static boolean isValidDni(String dni) {
		String pattern = "^\\d{6,8}";
		return (dni == null) ? false : Pattern.matches(pattern, dni.toLowerCase().trim());
	}

	/**
	 * Función encargada de verificar que la oración recibida corresponda con un domicilio dado el nombre de la calle y la altura.
	 * 
	 * <pre>
	 * cuit |= [0-9a-z]+[('| |.)0-9a-z]*
	 * </pre>
	 * 
	 * @param address
	 *            La oración que tiene el domicilio que va a verificarse.
	 * @return FALSE en caso de que el domicilio sea nulo, este vacio o contenga algún caracter no válido dentro de la oración, en caso contrario
	 *         retorna TRUE.
	 */
	public static boolean isValidPostalAddress(String address) {
		String pattern = "[a-zñáéíóúü0-9]+[('|\\s\\.)a-zñáéíóúü0-9]*";
		return (address == null) ? false : Pattern.matches(pattern, address.toLowerCase().trim());
	}

	/**
	 * Función destinada a la verificación de una cadena de contener un número de teléfono de acuerdo el siguiente patrón de contenido:
	 * 
	 * <pre>
	 * telephone |= [0-9]+
	 * </pre>
	 * 
	 * @param telephone
	 *            La cadena que va a verificarse su correcto formato que corresponda con un número de teléfono.
	 * @return true en caso de que la cadena recibida corresponda con el formato valido para un número de teléfono, en caso contrario esta función
	 *         retornará false.
	 */
	public static boolean isValidTelephoneNumber(String telephone) {
		String pattern = "[0-9]+";
		return (telephone == null) ? false : Pattern.matches(pattern, telephone.toLowerCase().trim());
	}

	/**
	 * Función destinada a la verificación de una cadena para ver si la misma posee un "E-Mail" válido dentro el sistema. Esta cadena debe estar de
	 * acuerdo al siguiente patrón de contenido:
	 * 
	 * <pre>
	 * email |= [0-9a-zñüáéíóú.-_]+[@][[0-9a-zñüáéíóú-_]+[.[0-9a-zñüáéíóú-_]+]]+
	 * </pre>
	 * 
	 * @param email
	 *            La cadena que va a verificarse su correcto formato que corresponda con un e-mail.
	 * @return true en caso de que la cadena recibida corresponda con el formato valido para un E-mail, en caso contrario esta función retornará
	 *         false.
	 */
	public static boolean isValidEmail(String email) {
		String userPattern = "([0-9a-zñüáéíóú][0-9a-zñüáéíóú\\.\\-\\_]+[0-9a-zñüáéíóú])";
		String domainPattern = "([0-9a-zñüáéíóú][0-9a-zñüáéíóú\\-\\_\\.]+[0-9a-zñüáéíóú])+";
		String pattern = userPattern + "@" + domainPattern;
		return (email == null) ? false : Pattern.matches(pattern, email.toLowerCase().trim());
	}

	/**
	 * Función utilizada para corroborar que una cadena de caráteres recibida corresponda con una cadena que solo contiene números en su interior. El
	 * patrón de esta cadena es la siguiente:
	 * 
	 * <pre>
	 * number |= (-)?[0-9]+
	 * </pre>
	 * 
	 * @param number
	 *            La cadena que vamos a verificar que su conteniddo sean solamente números.
	 * @return true en caso de que la cadena recibida como parámetros tenga en su interior solo carácteres numéricos. En caso contrario, esta
	 *         retornará false.
	 */
	public static boolean isValidInteger(String number) {
		String pattern = "(-)?[0-9]+";
		return (number == null) ? false : Pattern.matches(pattern, number.toLowerCase().trim());
	}

	/**
	 * Función utilizada para corroborar que una cadena de caráteres recibida corresponda con una cadena que solo contiene números flotantes en su
	 * interior. El patrón de esta cadena es la siguiente:
	 * 
	 * <pre>
	 * number |= (-)?[0-9]+.[0-9]+
	 * </pre>
	 * 
	 * @param number
	 *            La cadena que vamos a verificar que su conteniddo sean solamente números flotantes.
	 * @return true en caso de que la cadena recibida como parámetros tenga en su interior solo carácteres numéricos. En caso contrario, esta
	 *         retornará false.
	 */
	public static boolean isValidFloat(String number) {
		String pattern = "(-)?[0-9]+\\.[0-9]+";
		return (number == null) ? false : Pattern.matches(pattern, number.toLowerCase().trim());
	}

	/**
	 * Función encargada de verificar el contenido de una cadena de carácteres para que el mismo contenga el patrón de una dirección IPv4 dentro de
	 * ella de acuerdo al siguiente patrón:
	 * 
	 * <pre>
	 * address |= ^(25[0-5]|2[0-4]\d|[0-1]?\d?\d)(\.(25[0-5]|2[0-4]\d|[0-1]?\d?\d)){3}$
	 * </pre>
	 * 
	 * @param address
	 *            La cadena de carácteres que vamos a verificar si cumple con el patrón de una dirección IPv4.
	 * @return true en caso de que la cadena recibida corresponda con el patrón de una dirección IPv4 de la forma 255.255.255.255, en caso de que no
	 *         cumpla con dicho patrón, esta función retornará false.
	 */
	public static boolean isValidIPv4Address(String address) {
		String pattern = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";
		return (address == null) ? false : Pattern.matches(pattern, address.toLowerCase().trim());
	}

	/**
	 * Función encargada de verificar que una dirección IP sea alcanzable desde esta maquina para verificar si se puede generar una conexión con dicho
	 * dispositivo.
	 * 
	 * @param address
	 *            La dirección IP sobre la que vamos a realizar las pruebas de alcanzabilidad.
	 * @return true en caso de que la dirección IP recibida como parámetros sea alcanzable por este sistema, en caso que no se pueda establecer una
	 *         conexión con dicha dirección, esta función retornará false.
	 */
	public static boolean isHostReachable(InetAddress address) {
		try {
			return address.isReachable(3000);
		} catch (IOException ex) {
			return false;
		}
	}

	/**
	 * Función encargada de verificar si una cadena de caracteres recibida como parámetro es un número de puerto inválido dentro del sistema.
	 * 
	 * @param portNumber
	 *            La cadena de caracteres con el número de puerto que queremos probar su validez.
	 * @return true en caso de que el número recibido caiga dentro del rango de valores válidos para los puertos del algún protocolo de capa de
	 *         transporte, en caso de que no cumpla con eso, este retornará false.
	 */
	public static boolean isValidPortNumber(String portNumber) {
		return Verifier.isValidInteger(portNumber) && Verifier.isValidPortNumber(Integer.parseInt(portNumber));
	}

	/**
	 * Función encargada de verificar si un número recibido como parámetro es un número de puerto inválido dentro del sistema.
	 * 
	 * @param portNumber
	 *            El número de puerto que queremos probar su validez.
	 * @return true en caso de que el número recibido caiga dentro del rango de valores válidos para los puertos del algún protocolo de capa de
	 *         transporte, en caso de que no cumpla con eso, este retornará false.
	 */
	public static boolean isValidPortNumber(Integer portNumber) {
		if (portNumber > 0 && portNumber < 65536) {
			return true;
		} else {
			return false;
		}
	}
}