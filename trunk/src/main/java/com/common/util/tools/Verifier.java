package com.common.util.tools;

import java.util.regex.Pattern;

/**
 * La clase encargada de verificar que los campos de entrada de una aplicaci�n tenga los valores correctos y que no sean cualquier cosa.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Verifier {

	/**
	 * Se encarga de validar que una cadena de caracteres recibida coincida con un patr�n de validaci�n.
	 * 
	 * @param string
	 *            La cadena que vamos a validar.
	 * @param pattern
	 *            El patr�n con el que vamos a validar la cadena.
	 * @return <i>true</i> en caso que la cadena recibida tcoincida con el patr�n recibido, en caso contrario, retornamos <i>false</i>.
	 */
	public boolean matchString(String string, String pattern) {
		return (string == null) ? false : Pattern.matches(pattern, string.toLowerCase().trim());
	}

	/**
	 * Se encarga de verificar que el nombre de una persona f�sica sea v�lido dentro del sistema.
	 * 
	 * <pre>
	 * nombre |= [a-z]+(['|\s][a-z]+)*
	 * </pre>
	 * 
	 * @param name
	 *            La cadena que tiene el nombre de la persona que vamos a verificar.
	 * @return <i>true</i> en caso de que el nombre recibido coincida con el patr�n de un nombre v�lido, en caso contrario retorna <i>false</i>.
	 */
	public boolean isValidPersonName(String name) {
		String pattern = "[a-z�������]+(['|\\s][a-z�������]+)*";
		return this.matchString(name, pattern);
	}

	/**
	 * Se encarga de verificar que el apellido de una persona f�sica sea v�lido dentro del sistema.
	 * 
	 * <pre>
	 * apellido |= [a-z]+(['|\s][a-z]+)*
	 * </pre>
	 * 
	 * @param lastname
	 *            La cadena que contiene el apellido de la persona que vamos a verificar.
	 * @return <i>true</i> en caso de que el apellido recibido coincida con el patr�n de un apellido v�lido, en caso contrario retorna <i>false</i>.
	 */
	public boolean isValidPersonLastname(String lastname) {
		String pattern = "[a-z�������]+(['|\\s][a-z�������]+)*";
		return this.matchString(lastname, pattern);
	}

	/**
	 * Se encarga de verificar que el n�mero recibido dentro de la cadena sea v�lido para un n�mero de CUIT de una persona f�sica o jur�dica.
	 * 
	 * <pre>
	 * cuit |= [0-9]{2}-[0-9]{6,8}-[0-9]{1}
	 * </pre>
	 * 
	 * @param cuit
	 *            El cuit de la empresa que va a verificarse.
	 * @return <i>true</i> en caso de que el CUIT recibido coincida con el patr�n de un CUIT v�lido, en caso contrario retorna <i>false</i>.
	 */
	public boolean isValidCuit(String cuit) {
		String pattern = "^\\d{2}\\-\\d{6,8}\\-\\d{1}$";
		return this.matchString(cuit, pattern);
	}

	/**
	 * Se encarga de verificar que una cadena contenga un DNI v�lido dentro del sistema.
	 * 
	 * <pre>
	 * DNI |= [0-9]{6,8}
	 * </pre>
	 * 
	 * @param dni
	 *            La cadena que contiene el DNI a verificarse.
	 * @return <i>true</i> en caso de que el DNI recibido coincida con el patr�n de un DNI v�lido, en caso contrario retorna <i>false</i>.
	 */
	public boolean isValidDni(String dni) {
		String pattern = "^\\d{6,8}";
		return this.matchString(dni, pattern);
	}

	/**
	 * Se encarga de verificar que la oraci�n recibida corresponda con un domicilio dado el nombre de la calle y la altura.
	 * 
	 * <pre>
	 * cuit |= [0-9a-z]+[('| |.)0-9a-z]*
	 * </pre>
	 * 
	 * @param address
	 *            La oraci�n que tiene el domicilio que va a verificarse.
	 * @return <i>true</i> en caso de que el domicilio recibido coincida con el patr�n de un domicilio v�lido, en caso contrario retorna <i>false</i>.
	 */
	public boolean isValidAddress(String address) {
		String pattern = "[a-z�������0-9]+[('|\\s\\.)a-z�������0-9]*";
		return this.matchString(address, pattern);
	}

	/**
	 * Se encarga de verificar que la oraci�n recibida corresponda a un n�mero de tel�fono de acuerdo el siguiente patr�n de contenido:
	 * 
	 * <pre>
	 * telephone |= [0-9]+
	 * </pre>
	 * 
	 * @param telephone
	 *            La cadena que va a verificarse su correcto formato que corresponda con un n�mero de tel�fono.
	 * @return <i>true</i> en caso de que el tel�fono recibido coincida con el patr�n de un tel�fono v�lido, en caso contrario retorna <i>false</i>.
	 */
	public boolean isValidTelephoneNumber(String telephone) {
		String pattern = "[0-9]+";
		return this.matchString(telephone, pattern);
	}

	/**
	 * Se encarga de verificar que la oraci�n recibida corresponda a un e-mail v�lido dentro el sistema. Esta cadena debe estar de acuerdo al
	 * siguiente patr�n de contenido:
	 * 
	 * <pre>
	 * email |= [0-9a-z�������.-_]+[@][[0-9a-z�������-_]+[.[0-9a-z�������-_]+]]+
	 * </pre>
	 * 
	 * @param email
	 *            La cadena que va a verificarse su correcto formato que corresponda con un e-mail.
	 * @return <i>true</i> en caso de que el e-mail recibido coincida con el patr�n de un e-mail v�lido, en caso contrario retorna <i>false</i>.
	 */
	public boolean isValidEmail(String email) {
		String userPattern = "([0-9a-z�������][0-9a-z�������\\.\\-\\_]+[0-9a-z�������])";
		String domainPattern = "([0-9a-z�������][0-9a-z�������\\-\\_\\.]+[0-9a-z�������])+";
		String pattern = userPattern + "@" + domainPattern;
		return this.matchString(email, pattern);
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
	 * @return <i>true</i> en caso de que el n�mero recibido coincida con el patr�n de un n�mero v�lido, en caso contrario retorna <i>false</i>.
	 */
	public boolean isValidInteger(String number) {
		String pattern = "(-)?[0-9]+";
		return this.matchString(number, pattern);
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
	 * @return <i>true</i> en caso de que el n�mero flotante recibido coincida con el patr�n de un n�mero flotante v�lido, en caso contrario retorna
	 *         <i>false</i>.
	 */
	public boolean isValidFloat(String number) {
		String pattern = "(-)?[0-9]+\\.[0-9]+";
		return this.matchString(number, pattern);
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
	 * @return <i>true</i> en caso de que el direcci�n IPv4 recibido coincida con el patr�n de un direcci�n IPv4 v�lido, en caso contrario retorna
	 *         <i>false</i>.
	 */
	public boolean isValidIPv4Address(String address) {
		String pattern = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";
		return this.matchString(address, pattern);
	}

	/**
	 * Funci�n encargada de verificar si una cadena de caracteres recibida como par�metro es un n�mero de puerto inv�lido dentro del sistema.
	 * 
	 * @param portNumber
	 *            La cadena de caracteres con el n�mero de puerto que queremos probar su validez.
	 * @return <i>true</i> en caso de que el n�mero de puerto recibido coincida con el patr�n de un n�mer de puerto v�lido, en caso contrario retorna
	 *         <i>false</i>.
	 */
	public boolean isValidPortNumber(String portNumber) {
		return this.isValidInteger(portNumber) && this.isValidPortNumber(Integer.parseInt(portNumber));
	}

	/**
	 * Funci�n encargada de verificar si un n�mero recibido como par�metro es un n�mero de puerto inv�lido dentro del sistema.
	 * 
	 * @param portNumber
	 *            El n�mero de puerto que queremos probar su validez.
	 * @return <i>true</i> en caso de que el n�mero de puerto recibido coincida con el patr�n de un n�mer de puerto v�lido, en caso contrario retorna
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