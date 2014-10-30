package com.common.util.business.util;

import com.common.util.business.tool.VerifierUtil;

/**
 * Contiene los patrones de cadenas de texto (Expresiones Regulares) de los casos más comunes.
 * 
 * <ul>
 * <li>{@link PatternUtilEnum#NUMBER_INTEGER_PATTERN}</li>
 * <li>{@link PatternUtilEnum#NUMBER_DECIMAL_PATTERN}</li>
 * <li>{@link PatternUtilEnum#NAME_PATTERN}</li>
 * <li>{@link PatternUtilEnum#LASTNAME_PATTERN}</li>
 * <li>{@link PatternUtilEnum#DNI_PATTERN}</li>
 * <li>{@link PatternUtilEnum#CUIT_PATTERN}</li>
 * <li>{@link PatternUtilEnum#PHISIC_ADDRESS_PATTERN}</li>
 * <li>{@link PatternUtilEnum#TELEPHONE_PATTERN}</li>
 * <li>{@link PatternUtilEnum#EMAIL_PATTERN}</li>
 * <li>{@link PatternUtilEnum#PORT_NUMBER_PATTERN}</li>
 * <li>{@link PatternUtilEnum#IPV4_PATTERN}</li>
 * </ul>
 * 
 * @see VerifierUtil
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum PatternUtilEnum {

	/**
	 * La expresión regular de un numero entero.
	 * 
	 * <pre>
	 * <b>número:</b> <i>(-)?[0-9]+</i>
	 * </pre>
	 */
	NUMBER_INTEGER_PATTERN("[-]?[0-9]+"),

	/**
	 * La expresión regular de un numero decimal.
	 * 
	 * <pre>
	 * <b>número:</b> <i>(-)?[0-9]+.[0-9]+</i>
	 * </pre>
	 */
	NUMBER_DECIMAL_PATTERN("[-]?\\d+(\\.\\d+)?$"),

	/**
	 * La expresión regular del nombre de una persona física.
	 * 
	 * <pre>
	 * <b>nombre:</b> <i>[a-z]+(['|\s][a-z]+)*</i>
	 * </pre>
	 */
	NAME_PATTERN("[a-zñáéíóúü]+(['|\\s][a-zñáéíóúü]+)*"),

	/**
	 * La expresión regular del apellido de una persona física.
	 * 
	 * <pre>
	 * <b>apellido:</b> <i>[a-z]+(['|\s][a-z]+)*</i>
	 * </pre>
	 */
	LASTNAME_PATTERN("[a-zñáéíóúü]+(['|\\s][a-zñáéíóúü]+)*"),

	/**
	 * La expresión regular de un número de DNI.
	 * 
	 * <pre>
	 * <b>DNI:</b> <i>[0-9]{6,8}</i>
	 * </pre>
	 */
	DNI_PATTERN("^\\d{6,8}"),

	/**
	 * La expresión regular de un número de CUIT.
	 * 
	 * <pre>
	 * <b>CUIT:</b> <i>[0-9]{2}-[0-9]{6,8}-[0-9]{1}</i>
	 * </pre>
	 */
	CUIT_PATTERN("^\\d{2}\\-\\d{6,8}\\-\\d{1}$"),

	/**
	 * La expresión regular de la dirección física de una ubicación.
	 * 
	 * <pre>
	 * <b>dirección:</b> <i>[a-z0-9]+(['|\s][a-z0-9]+)*</i>
	 * </pre>
	 */
	PHISIC_ADDRESS_PATTERN("[a-zñáéíóúü0-9]+[('|\\s\\.)a-zñáéíóúü0-9]*"),

	/**
	 * La expresión regular de un número de teléfono.
	 * 
	 * <pre>
	 * <b>teléfono:</b> <i>[0-9]+</i>
	 * </pre>
	 */
	TELEPHONE_PATTERN("[0-9]+"),

	/**
	 * La expresión regular de una dirección de email.
	 * 
	 * <pre>
	 * <b>email:</b> <i>[0-9a-zñüáéíóú.-_]+[@][[0-9a-zñüáéíóú-_]+[.[0-9a-zñüáéíóú-_]+]]+</i>
	 * </pre>
	 */
	EMAIL_PATTERN("([0-9a-zñüáéíóú][0-9a-zñüáéíóú\\.\\-\\_]+[0-9a-zñüáéíóú])@([0-9a-zñüáéíóú][0-9a-zñüáéíóú\\-\\_\\.]+[0-9a-zñüáéíóú])+"),

	/**
	 * La expresión regular de un número de puerto.
	 * 
	 * <pre>
	 * <b>puerto:</b> <i>(6553[0-5]|655[0-2]\\d|65[0-4]\\d{2}|6[0-4]\\d{3}|[1-5]\\d{4}|[1-9]\\d{0,3})</i>
	 * </pre>
	 */
	PORT_NUMBER_PATTERN("(6553[0-5]|655[0-2]\\d|65[0-4]\\d{2}|6[0-4]\\d{3}|[1-5]\\d{4}|[1-9]\\d{0,3})"),

	/**
	 * La expresión regular de una dirección IPv4.
	 * 
	 * <pre>
	 * <b>IPv4:</b> <i>^(25[0-5]|2[0-4]\d|[0-1]?\d?\d)(\.(25[0-5]|2[0-4]\d|[0-1]?\d?\d)){3}$</i>
	 * </pre>
	 */
	IPV4_PATTERN("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

	/**
	 * El patrón.
	 */
	private String pattern;

	/**
	 * El constructor de un tipo de patrón.
	 * 
	 * @param pattern
	 *            El patrón.
	 */
	private PatternUtilEnum(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * Retorna el patrón de la expresión.
	 * 
	 * @return El patrón de la expresión.
	 */
	public String getPattern() {
		return pattern;
	}
}