package com.common.util.domain.exception;

import com.common.util.business.tool.StringUtil;
import com.common.util.domain.exception.error.ErrorDetail;
import com.common.util.domain.exception.error.Errors;

/**
 * Las excepciones chequeadas que nos permite realizar un seguimiento de los lanzamiento de la misma para un mejor control en tiempo de desarrollo.
 * 
 * @see Errors
 * @see ErrorDetail
 * @see UncheckedException
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class CheckedException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * El listado de los errores.
	 */
	protected Errors errors;

	/**
	 * El constructor de una instancia de {@link CheckedException} que no recibe parámetros.
	 */
	public CheckedException() {
		super();
		this.errors = new Errors();
	}

	/**
	 * El constructor de una instancia de {@link CheckedException} que recibe como parámetro un elemento {@link Throwable} para mantener el problema
	 * que produjo el lanzamiento de esta {@link CheckedException}.
	 * 
	 * @param cause
	 *            La causa de un problema que vamos a contener dentro de esta excepción.
	 */
	public CheckedException(Throwable cause) {
		super(cause);
		this.errors = new Errors();
	}

	/**
	 * El constructor de una instancia de {@link CheckedException} que recibe como parámetro el conjunto de {@link Errors} que vamos a contener.
	 * 
	 * @param errors
	 *            El conjunto de errores que vamos a contener dentro de esta excepción.
	 */
	public CheckedException(Errors errors) {
		super();
		this.errors = errors;
	}

	/**
	 * El constructor de una instancia de {@link CheckedException} que recibe como parámetro el {@link Throwable} para mantener el problema que
	 * produjo el lanzamiento de esta {@link CheckedException} y un un mensaje de {@link ErrorDetail} que vamos a crear en el momento.
	 * 
	 * @param cause
	 *            La causa de un problema que vamos a contener dentro de esta excepción.
	 * @param defaultMessage
	 *            El mensaje por omisión del detalle del error.
	 * @param keyMessage
	 *            La clave del mensaje del detalle del error.
	 * @param parameters
	 *            Los parámetros que requerimos para el detalle del error.
	 */
	public CheckedException(Throwable cause, String defaultMessage, String keyMessage, Object... parameters) {
		super(defaultMessage, cause);
		this.errors = new Errors();
		this.errors.addError(defaultMessage, keyMessage, parameters);
	}

	/**
	 * El constructor de una instancia de {@link CheckedException} que recibe como parámetro un mensaje de {@link ErrorDetail} que vamos a crear en el
	 * momento.
	 * 
	 * @param defaultMessage
	 *            El mensaje por omisión del detalle del error.
	 * @param keyMessage
	 *            La clave del mensaje del detalle del error.
	 * @param parameters
	 *            Los parámetros que requerimos para el detalle del error.
	 */
	public CheckedException(String defaultMessage, String keyMessage, Object... parameters) {
		super(defaultMessage);
		this.errors = new Errors();
		this.errors.addError(defaultMessage, keyMessage, parameters);
	}

	/**
	 * Retorna los errores que tenemos dentro de esta escepción.
	 * 
	 * @return El listado de los errores de esta excepción.
	 */
	@Override
	public String toString() {
		return this.errors.toString();
	}

	/**
	 * La función encargada de retornar los errores que tenemos dentro de esta excepción.
	 * 
	 * @return El conjunto de errores que tenemos dentro de esta excepción.
	 */
	public Errors getErrors() {
		return this.errors;
	}

	/**
	 * Función encargada de retornar los mensajes de errores que tenemos en este elemento.
	 * 
	 * @return El mensaje de los errores que tenemos dentro de esta excepción.
	 */
	@Override
	public String getMessage() {
		StringBuffer stringBuffer = new StringBuffer();

		for (ErrorDetail errorDetail : this.errors.getErrorDetails()) {
			stringBuffer.append("DEFAULT:" + errorDetail.getDefaultMessage() + "KEY: " + errorDetail.getKeyMessage() + " VALUES: {"
					+ StringUtil.arrayToDelimitedString(errorDetail.getParameters(), ", ") + "}");
			stringBuffer.append(this.getMessageSeparator());
		}

		return stringBuffer.toString();
	}

	/**
	 * La función encargada de retornar el separador para convertir el listado de errores en una oración completa.
	 * 
	 * @return La cadena que vamos a utilizar para separar los mensajes dentro de la oración completa.
	 */
	protected String getMessageSeparator() {
		return "\n";
	}
}