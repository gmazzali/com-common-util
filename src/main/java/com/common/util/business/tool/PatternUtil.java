package com.common.util.business.tool;

/**
 * Contiene los patrones de cadenas de texto (Expresiones Regulares) de los casos más comunes.
 * 
 * @see VerifierUtil
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class PatternUtil {

	/**
	 * La expresión regular de un numero entero.
	 * 
	 * <pre>
	 * <b>número:</b> <i>(-)?[0-9]+</i>
	 * </pre>
	 */
	public static final String NUMBER_INTEGER_PATTERN = "(-)?[0-9]+";

	/**
	 * La expresión regular de un numero decimal.
	 * 
	 * <pre>
	 * <b>número:</b> <i>(-)?[0-9]+.[0-9]+</i>
	 * </pre>
	 */
	public static final String NUMBER_DECIMAL_PATTERN = "(-)?[0-9]+\\.[0-9]+";

	/**
	 * La expresión regular del nombre de una persona física.
	 * 
	 * <pre>
	 * <b>nombre:</b> <i>[a-z]+(['|\s][a-z]+)*</i>
	 * </pre>
	 */
	public static final String NAME_PATTERN = "[a-zñáéíóúü]+(['|\\s][a-zñáéíóúü]+)*";

	/**
	 * La expresión regular del apellido de una persona física.
	 * 
	 * <pre>
	 * <b>apellido:</b> <i>[a-z]+(['|\s][a-z]+)*</i>
	 * </pre>
	 */
	public static final String LASTNAME_PATTERN = "[a-zñáéíóúü]+(['|\\s][a-zñáéíóúü]+)*";

	/**
	 * La expresión regular de un número de DNI.
	 * 
	 * <pre>
	 * <b>DNI:</b> <i>[0-9]{6,8}</i>
	 * </pre>
	 */
	public static final String DNI_PATTERN = "^\\d{6,8}";

	/**
	 * La expresión regular de un número de CUIT.
	 * 
	 * <pre>
	 * <b>CUIT:</b> <i>[0-9]{2}-[0-9]{6,8}-[0-9]{1}</i>
	 * </pre>
	 */
	public static final String CUIT_PATTERN = "^\\d{2}\\-\\d{6,8}\\-\\d{1}$";

	/**
	 * La expresión regular de la dirección física de una ubicación.
	 * 
	 * <pre>
	 * <b>dirección:</b> <i>[a-z0-9]+(['|\s][a-z0-9]+)*</i>
	 * </pre>
	 */
	public static final String PHISIC_ADDRESS_PATTERN = "[a-zñáéíóúü0-9]+[('|\\s\\.)a-zñáéíóúü0-9]*";

	/**
	 * La expresión regular de un número de teléfono.
	 * 
	 * <pre>
	 * <b>teléfono:</b> <i>[0-9]+</i>
	 * </pre>
	 */
	public static final String TELEPHONE_PATTERN = "[0-9]+";

	/**
	 * La expresión regular de una dirección de email.
	 * 
	 * <pre>
	 * <b>email:</b> <i>[0-9a-zñüáéíóú.-_]+[@][[0-9a-zñüáéíóú-_]+[.[0-9a-zñüáéíóú-_]+]]+</i>
	 * </pre>
	 */
	public static final String EMAIL_PATTERN = "([0-9a-zñüáéíóú][0-9a-zñüáéíóú\\.\\-\\_]+[0-9a-zñüáéíóú])@([0-9a-zñüáéíóú][0-9a-zñüáéíóú\\-\\_\\.]+[0-9a-zñüáéíóú])+";

	/**
	 * La expresión regular de un número de puerto.
	 * 
	 * <pre>
	 * <b>puerto:</b> <i>(6553[0-5]|655[0-2]\\d|65[0-4]\\d{2}|6[0-4]\\d{3}|[1-5]\\d{4}|[1-9]\\d{0,3})</i>
	 * </pre>
	 */
	public static final String PORT_NUMBER_PATTERN = "(6553[0-5]|655[0-2]\\d|65[0-4]\\d{2}|6[0-4]\\d{3}|[1-5]\\d{4}|[1-9]\\d{0,3})";

	/**
	 * La expresión regular de una dirección IPv4.
	 * 
	 * <pre>
	 * <b>IPv4:</b> <i>^(25[0-5]|2[0-4]\d|[0-1]?\d?\d)(\.(25[0-5]|2[0-4]\d|[0-1]?\d?\d)){3}$</i>
	 * </pre>
	 */
	public static final String IPV4_PATTERN = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";
}