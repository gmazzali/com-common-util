package com.common.util.domain.exception;

import com.common.util.business.tool.StringUtil;
import com.common.util.domain.model.error.ErrorDetail;
import com.common.util.domain.model.error.Errors;

/**
 * Las excepciones no chequeadas que vamos a manejar dentro del sistema.
 * 
 * @see CheckedException
 * @see ErrorDetail
 * @see Errors
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class UncheckedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * El listado de los errores.
	 */
	protected Errors errors;

	/**
	 * El constructor de una instancia de {@link UncheckedException} que no recibe par�metros.
	 */
	public UncheckedException() {
		super();
		this.errors = new Errors();
	}

	/**
	 * El constructor de una instancia de {@link UncheckedException} que recibe como par�metro un elemento {@link Throwable} para mantener el problema
	 * que produjo el lanzamiento de esta {@link UncheckedException}.
	 * 
	 * @param cause
	 *            La causa de un problema que vamos a contener dentro de esta excepci�n.
	 */
	public UncheckedException(Throwable cause) {
		super(cause);
		this.errors = new Errors();
	}

	/**
	 * El constructor de una instancia de {@link UncheckedException} que recibe como par�metro el conjunto de {@link Errors} que vamos a contener.
	 * 
	 * @param errors
	 *            El conjunto de errores que vamos a contener dentro de esta excepci�n.
	 */
	public UncheckedException(Errors errors) {
		super();
		this.errors = errors;
	}

	/**
	 * El constructor de una instancia de {@link UncheckedException} que recibe como par�metro el {@link Throwable} para mantener el problema que
	 * produjo el lanzamiento de esta {@link UncheckedException} y un un mensaje de {@link ErrorDetail} que vamos a crear en el momento.
	 * 
	 * @param cause
	 *            La causa de un problema que vamos a contener dentro de esta excepci�n.
	 * @param defaultMessage
	 *            El mensaje por omisi�n del detalle del error.
	 * @param keyMessage
	 *            La clave del mensaje del detalle del error.
	 * @param parameters
	 *            Los par�metros que requerimos para el detalle del error.
	 */
	public UncheckedException(Throwable cause, String defaultMessage, String keyMessage, Object... parameters) {
		super(defaultMessage, cause);
		this.errors = new Errors();
		this.errors.addError(defaultMessage, keyMessage, parameters);
	}

	/**
	 * El constructor de una instancia de {@link UncheckedException} que recibe como par�metro un mensaje de {@link ErrorDetail} que vamos a crear en
	 * el momento.
	 * 
	 * @param defaultMessage
	 *            El mensaje por omisi�n del detalle del error.
	 * @param keyMessage
	 *            La clave del mensaje del detalle del error.
	 * @param parameters
	 *            Los par�metros que requerimos para el detalle del error.
	 */
	public UncheckedException(String defaultMessage, String keyMessage, Object... parameters) {
		super(defaultMessage);
		this.errors = new Errors();
		this.errors.addError(defaultMessage, keyMessage, parameters);
	}

	/**
	 * Retorna los errores que tenemos dentro de esta escepci�n.
	 * 
	 * @return El listado de los errores de esta excepci�n.
	 */
	@Override
	public String toString() {
		return this.errors.toString();
	}

	/**
	 * La funci�n encargada de retornar los errores que tenemos dentro de esta excepci�n.
	 * 
	 * @return El conjunto de errores que tenemos dentro de esta excepci�n.
	 */
	public Errors getErrors() {
		return this.errors;
	}

	/**
	 * Funci�n encargada de retornar los mensajes de errores que tenemos en este elemento.
	 * 
	 * @return El mensaje de los errores que tenemos dentro de esta excepci�n.
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
	 * La funci�n encargada de retornar el separador para convertir el listado de errores en una oraci�n completa.
	 * 
	 * @return La cadena que vamos a utilizar para separar los mensajes dentro de la oraci�n completa.
	 */
	protected String getMessageSeparator() {
		return "\n";
	}
}