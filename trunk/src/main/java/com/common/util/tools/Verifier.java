package com.common.util.tools;

import java.io.IOException;
import java.net.InetAddress;
import java.util.regex.Pattern;

/**
 * La clase encargada de verificar que los campos de entrada de una aplicaci�n tenga los valores correctos y que no sean cualquier cosa.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Verifier {

	/**
	 * Funci�n encargada de verificar que el nombre de una persona f�sica sea v�lido dentro del sistema.
	 * 
	 * <pre>
	 * nombre |= [a-z]+(['|\s][a-z]+)*
	 * </pre>
	 * 
	 * @param name
	 *            La cadena que tiene el nombre de la persona que vamos a veriicar.
	 * @return FALSE en caso de que el nombre recibido sea nulo, este vacio o contenga alg�n elemento no v�lido para un nombre, en caso contrario
	 *         retorna TRUE.
	 */
	public static boolean isValidPersonName(String name) {
		String pattern = "[a-z�������]+(['|\\s][a-z�������]+)*";
		return (name == null) ? false : Pattern.matches(pattern, name.toLowerCase().trim());
	}

	/**
	 * Funci�n encargada de verificar que el apellido de una persona f�sica sea v�lido dentro del sistema.
	 * 
	 * <pre>
	 * apellido |= [a-z]+(['|\s][a-z]+)*
	 * </pre>
	 * 
	 * @param lastname
	 *            La cadena que contiene el apellido de la persona que vamos a verificar.
	 * @return FALSE en caso de que el apellido recibido sea nulo, este vacio o contenga alg�n caracter no v�lido para un apellido, en caso contrario
	 *         retorna TRUE.
	 */
	public static boolean isValidPersonLastname(String lastname) {
		String pattern = "[a-z�������]+(['|\\s][a-z�������]+)*";
		return (lastname == null) ? false : Pattern.matches(pattern, lastname.toLowerCase().trim());
	}

	/**
	 * Funci�n encargada de verificar que el n�mero recibido dentro de la cadena sea v�lido para un n�mero de CUIT de una persona f�sica o jur�dica.
	 * 
	 * <pre>
	 * cuit |= [0-9]{2}-[0-9]{6,8}-[0-9]{1}
	 * </pre>
	 * 
	 * @param cuit
	 *            El cuit de la empresa que va a verificarse.
	 * @return FALSE en caso de que la cadena recibida sea nula o contenga valores inv�lidos para un CUIT, en caso contrario retornar� TRUE.
	 */
	public static boolean isValidCuitNumber(String cuit) {
		String pattern = "^\\d{2}\\-\\d{6,8}\\-\\d{1}$";
		return (cuit == null) ? false : Pattern.matches(pattern, cuit.toLowerCase().trim());
	}

	/**
	 * Funci�n encargada de verificar que una cadena contenga un DNI v�lido dentro del sistema.
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
	 * Funci�n encargada de verificar que la oraci�n recibida corresponda con un domicilio dado el nombre de la calle y la altura.
	 * 
	 * <pre>
	 * cuit |= [0-9a-z]+[('| |.)0-9a-z]*
	 * </pre>
	 * 
	 * @param address
	 *            La oraci�n que tiene el domicilio que va a verificarse.
	 * @return FALSE en caso de que el domicilio sea nulo, este vacio o contenga alg�n caracter no v�lido dentro de la oraci�n, en caso contrario
	 *         retorna TRUE.
	 */
	public static boolean isValidPostalAddress(String address) {
		String pattern = "[a-z�������0-9]+[('|\\s\\.)a-z�������0-9]*";
		return (address == null) ? false : Pattern.matches(pattern, address.toLowerCase().trim());
	}

	/**
	 * Funci�n destinada a la verificaci�n de una cadena de contener un n�mero de tel�fono de acuerdo el siguiente patr�n de contenido:
	 * 
	 * <pre>
	 * telephone |= [0-9]+
	 * </pre>
	 * 
	 * @param telephone
	 *            La cadena que va a verificarse su correcto formato que corresponda con un n�mero de tel�fono.
	 * @return true en caso de que la cadena recibida corresponda con el formato valido para un n�mero de tel�fono, en caso contrario esta funci�n
	 *         retornar� false.
	 */
	public static boolean isValidTelephoneNumber(String telephone) {
		String pattern = "[0-9]+";
		return (telephone == null) ? false : Pattern.matches(pattern, telephone.toLowerCase().trim());
	}

	/**
	 * Funci�n destinada a la verificaci�n de una cadena para ver si la misma posee un "E-Mail" v�lido dentro el sistema. Esta cadena debe estar de
	 * acuerdo al siguiente patr�n de contenido:
	 * 
	 * <pre>
	 * email |= [0-9a-z�������.-_]+[@][[0-9a-z�������-_]+[.[0-9a-z�������-_]+]]+
	 * </pre>
	 * 
	 * @param email
	 *            La cadena que va a verificarse su correcto formato que corresponda con un e-mail.
	 * @return true en caso de que la cadena recibida corresponda con el formato valido para un E-mail, en caso contrario esta funci�n retornar�
	 *         false.
	 */
	public static boolean isValidEmail(String email) {
		String userPattern = "([0-9a-z�������][0-9a-z�������\\.\\-\\_]+[0-9a-z�������])";
		String domainPattern = "([0-9a-z�������][0-9a-z�������\\-\\_\\.]+[0-9a-z�������])+";
		String pattern = userPattern + "@" + domainPattern;
		return (email == null) ? false : Pattern.matches(pattern, email.toLowerCase().trim());
	}

	/**
	 * Funci�n utilizada para corroborar que una cadena de car�teres recibida corresponda con una cadena que solo contiene n�meros en su interior. El
	 * patr�n de esta cadena es la siguiente:
	 * 
	 * <pre>
	 * number |= (-)?[0-9]+
	 * </pre>
	 * 
	 * @param number
	 *            La cadena que vamos a verificar que su conteniddo sean solamente n�meros.
	 * @return true en caso de que la cadena recibida como par�metros tenga en su interior solo car�cteres num�ricos. En caso contrario, esta
	 *         retornar� false.
	 */
	public static boolean isValidInteger(String number) {
		String pattern = "(-)?[0-9]+";
		return (number == null) ? false : Pattern.matches(pattern, number.toLowerCase().trim());
	}

	/**
	 * Funci�n utilizada para corroborar que una cadena de car�teres recibida corresponda con una cadena que solo contiene n�meros flotantes en su
	 * interior. El patr�n de esta cadena es la siguiente:
	 * 
	 * <pre>
	 * number |= (-)?[0-9]+.[0-9]+
	 * </pre>
	 * 
	 * @param number
	 *            La cadena que vamos a verificar que su conteniddo sean solamente n�meros flotantes.
	 * @return true en caso de que la cadena recibida como par�metros tenga en su interior solo car�cteres num�ricos. En caso contrario, esta
	 *         retornar� false.
	 */
	public static boolean isValidFloat(String number) {
		String pattern = "(-)?[0-9]+\\.[0-9]+";
		return (number == null) ? false : Pattern.matches(pattern, number.toLowerCase().trim());
	}

	/**
	 * Funci�n encargada de verificar el contenido de una cadena de car�cteres para que el mismo contenga el patr�n de una direcci�n IPv4 dentro de
	 * ella de acuerdo al siguiente patr�n:
	 * 
	 * <pre>
	 * address |= ^(25[0-5]|2[0-4]\d|[0-1]?\d?\d)(\.(25[0-5]|2[0-4]\d|[0-1]?\d?\d)){3}$
	 * </pre>
	 * 
	 * @param address
	 *            La cadena de car�cteres que vamos a verificar si cumple con el patr�n de una direcci�n IPv4.
	 * @return true en caso de que la cadena recibida corresponda con el patr�n de una direcci�n IPv4 de la forma 255.255.255.255, en caso de que no
	 *         cumpla con dicho patr�n, esta funci�n retornar� false.
	 */
	public static boolean isValidIPv4Address(String address) {
		String pattern = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";
		return (address == null) ? false : Pattern.matches(pattern, address.toLowerCase().trim());
	}

	/**
	 * Funci�n encargada de verificar que una direcci�n IP sea alcanzable desde esta maquina para verificar si se puede generar una conexi�n con dicho
	 * dispositivo.
	 * 
	 * @param address
	 *            La direcci�n IP sobre la que vamos a realizar las pruebas de alcanzabilidad.
	 * @return true en caso de que la direcci�n IP recibida como par�metros sea alcanzable por este sistema, en caso que no se pueda establecer una
	 *         conexi�n con dicha direcci�n, esta funci�n retornar� false.
	 */
	public static boolean isHostReachable(InetAddress address) {
		try {
			return address.isReachable(3000);
		} catch (IOException ex) {
			return false;
		}
	}

	/**
	 * Funci�n encargada de verificar si una cadena de caracteres recibida como par�metro es un n�mero de puerto inv�lido dentro del sistema.
	 * 
	 * @param portNumber
	 *            La cadena de caracteres con el n�mero de puerto que queremos probar su validez.
	 * @return true en caso de que el n�mero recibido caiga dentro del rango de valores v�lidos para los puertos del alg�n protocolo de capa de
	 *         transporte, en caso de que no cumpla con eso, este retornar� false.
	 */
	public static boolean isValidPortNumber(String portNumber) {
		return Verifier.isValidInteger(portNumber) && Verifier.isValidPortNumber(Integer.parseInt(portNumber));
	}

	/**
	 * Funci�n encargada de verificar si un n�mero recibido como par�metro es un n�mero de puerto inv�lido dentro del sistema.
	 * 
	 * @param portNumber
	 *            El n�mero de puerto que queremos probar su validez.
	 * @return true en caso de que el n�mero recibido caiga dentro del rango de valores v�lidos para los puertos del alg�n protocolo de capa de
	 *         transporte, en caso de que no cumpla con eso, este retornar� false.
	 */
	public static boolean isValidPortNumber(Integer portNumber) {
		if (portNumber > 0 && portNumber < 65536) {
			return true;
		} else {
			return false;
		}
	}
}