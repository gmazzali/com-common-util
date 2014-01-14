package com.common.util.tools;

import java.util.regex.Pattern;

/**
 * La clase encargada de verificar que los campos de entrada de una aplicación tenga los valores correctos y que no sean cualquier cosa.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Verifier {

	/**
	 * Se encarga de validar que una cadena de caracteres recibida coincida con un patrón de validación.
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

	/**
	 * Se encarga de verificar que el nombre de una persona física sea válido dentro del sistema.
	 * 
	 * <pre>
	 * nombre |= [a-z]+(['|\s][a-z]+)*
	 * </pre>
	 * 
	 * @param name
	 *            La cadena que tiene el nombre de la persona que vamos a verificar.
	 * @return <i>true</i> en caso de que el nombre recibido coincida con el patrón de un nombre válido, en caso contrario retorna <i>false</i>.
	 */
	public boolean isValidPersonName(String name) {
		String pattern = "[a-zñáéíóúü]+(['|\\s][a-zñáéíóúü]+)*";
		return this.matchString(name, pattern);
	}

	/**
	 * Se encarga de verificar que el apellido de una persona física sea válido dentro del sistema.
	 * 
	 * <pre>
	 * apellido |= [a-z]+(['|\s][a-z]+)*
	 * </pre>
	 * 
	 * @param lastname
	 *            La cadena que contiene el apellido de la persona que vamos a verificar.
	 * @return <i>true</i> en caso de que el apellido recibido coincida con el patrón de un apellido válido, en caso contrario retorna <i>false</i>.
	 */
	public boolean isValidPersonLastname(String lastname) {
		String pattern = "[a-zñáéíóúü]+(['|\\s][a-zñáéíóúü]+)*";
		return this.matchString(lastname, pattern);
	}

	/**
	 * Se encarga de verificar que el número recibido dentro de la cadena sea válido para un número de CUIT de una persona física o jurídica.
	 * 
	 * <pre>
	 * cuit |= [0-9]{2}-[0-9]{6,8}-[0-9]{1}
	 * </pre>
	 * 
	 * @param cuit
	 *            El cuit de la empresa que va a verificarse.
	 * @return <i>true</i> en caso de que el CUIT recibido coincida con el patrón de un CUIT válido, en caso contrario retorna <i>false</i>.
	 */
	public boolean isValidCuit(String cuit) {
		String pattern = "^\\d{2}\\-\\d{6,8}\\-\\d{1}$";
		return this.matchString(cuit, pattern);
	}

	/**
	 * Se encarga de verificar que una cadena contenga un DNI válido dentro del sistema.
	 * 
	 * <pre>
	 * DNI |= [0-9]{6,8}
	 * </pre>
	 * 
	 * @param dni
	 *            La cadena que contiene el DNI a verificarse.
	 * @return <i>true</i> en caso de que el DNI recibido coincida con el patrón de un DNI válido, en caso contrario retorna <i>false</i>.
	 */
	public boolean isValidDni(String dni) {
		String pattern = "^\\d{6,8}";
		return this.matchString(dni, pattern);
	}

	/**
	 * Se encarga de verificar que la oración recibida corresponda con un domicilio dado el nombre de la calle y la altura.
	 * 
	 * <pre>
	 * cuit |= [0-9a-z]+[('| |.)0-9a-z]*
	 * </pre>
	 * 
	 * @param address
	 *            La oración que tiene el domicilio que va a verificarse.
	 * @return <i>true</i> en caso de que el domicilio recibido coincida con el patrón de un domicilio válido, en caso contrario retorna <i>false</i>.
	 */
	public boolean isValidAddress(String address) {
		String pattern = "[a-zñáéíóúü0-9]+[('|\\s\\.)a-zñáéíóúü0-9]*";
		return this.matchString(address, pattern);
	}

	/**
	 * Se encarga de verificar que la oración recibida corresponda a un número de teléfono de acuerdo el siguiente patrón de contenido:
	 * 
	 * <pre>
	 * telephone |= [0-9]+
	 * </pre>
	 * 
	 * @param telephone
	 *            La cadena que va a verificarse su correcto formato que corresponda con un número de teléfono.
	 * @return <i>true</i> en caso de que el teléfono recibido coincida con el patrón de un teléfono válido, en caso contrario retorna <i>false</i>.
	 */
	public boolean isValidTelephoneNumber(String telephone) {
		String pattern = "[0-9]+";
		return this.matchString(telephone, pattern);
	}

	/**
	 * Se encarga de verificar que la oración recibida corresponda a un e-mail válido dentro el sistema. Esta cadena debe estar de acuerdo al
	 * siguiente patrón de contenido:
	 * 
	 * <pre>
	 * email |= [0-9a-zñüáéíóú.-_]+[@][[0-9a-zñüáéíóú-_]+[.[0-9a-zñüáéíóú-_]+]]+
	 * </pre>
	 * 
	 * @param email
	 *            La cadena que va a verificarse su correcto formato que corresponda con un e-mail.
	 * @return <i>true</i> en caso de que el e-mail recibido coincida con el patrón de un e-mail válido, en caso contrario retorna <i>false</i>.
	 */
	public boolean isValidEmail(String email) {
		String userPattern = "([0-9a-zñüáéíóú][0-9a-zñüáéíóú\\.\\-\\_]+[0-9a-zñüáéíóú])";
		String domainPattern = "([0-9a-zñüáéíóú][0-9a-zñüáéíóú\\-\\_\\.]+[0-9a-zñüáéíóú])+";
		String pattern = userPattern + "@" + domainPattern;
		return this.matchString(email, pattern);
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
	 * @return <i>true</i> en caso de que el número recibido coincida con el patrón de un número válido, en caso contrario retorna <i>false</i>.
	 */
	public boolean isValidInteger(String number) {
		String pattern = "(-)?[0-9]+";
		return this.matchString(number, pattern);
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
	 * @return <i>true</i> en caso de que el número flotante recibido coincida con el patrón de un número flotante válido, en caso contrario retorna
	 *         <i>false</i>.
	 */
	public boolean isValidFloat(String number) {
		String pattern = "(-)?[0-9]+\\.[0-9]+";
		return this.matchString(number, pattern);
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
	 * @return <i>true</i> en caso de que el dirección IPv4 recibido coincida con el patrón de un dirección IPv4 válido, en caso contrario retorna
	 *         <i>false</i>.
	 */
	public boolean isValidIPv4Address(String address) {
		String pattern = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";
		return this.matchString(address, pattern);
	}

	/**
	 * Función encargada de verificar si una cadena de caracteres recibida como parámetro es un número de puerto inválido dentro del sistema.
	 * 
	 * @param portNumber
	 *            La cadena de caracteres con el número de puerto que queremos probar su validez.
	 * @return <i>true</i> en caso de que el número de puerto recibido coincida con el patrón de un númer de puerto válido, en caso contrario retorna
	 *         <i>false</i>.
	 */
	public boolean isValidPortNumber(String portNumber) {
		return this.isValidInteger(portNumber) && this.isValidPortNumber(Integer.parseInt(portNumber));
	}

	/**
	 * Función encargada de verificar si un número recibido como parámetro es un número de puerto inválido dentro del sistema.
	 * 
	 * @param portNumber
	 *            El número de puerto que queremos probar su validez.
	 * @return <i>true</i> en caso de que el número de puerto recibido coincida con el patrón de un númer de puerto válido, en caso contrario retorna
	 *         <i>false</i>.
	 */
	public boolean isValidPortNumber(Integer portNumber) {
		if (portNumber > 0 && portNumber < 65536) {
			return true;
		} else {
			return false;
		}
	}
}