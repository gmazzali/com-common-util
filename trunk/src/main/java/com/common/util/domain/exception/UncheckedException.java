package com.common.util.domain.exception;

import com.common.util.business.holder.HolderMessage;
import com.common.util.domain.exception.error.ErrorDetail;
import com.common.util.domain.exception.error.Errors;

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
	 * El constructor de una instancia de {@link UncheckedException} que no recibe parámetros.
	 */
	public UncheckedException() {
		super();
		this.errors = new Errors();
	}

	/**
	 * El constructor de una instancia de {@link UncheckedException} que recibe como parámetro un elemento {@link Throwable} para mantener el problema
	 * que produjo el lanzamiento de esta {@link UncheckedException}.
	 * 
	 * @param cause
	 *            La causa de un problema que vamos a contener dentro de esta excepción.
	 */
	public UncheckedException(Throwable cause) {
		super(cause);
		this.errors = new Errors();
	}

	/**
	 * El constructor de una instancia de {@link UncheckedException} que recibe como parámetro el conjunto de {@link Errors} que vamos a contener.
	 * 
	 * @param errors
	 *            El conjunto de errores que vamos a contener dentro de esta excepción.
	 */
	public UncheckedException(Errors errors) {
		super();
		this.errors = errors;
	}

	/**
	 * El constructor de una instancia de {@link UncheckedException} que recibe como parámetro el {@link Throwable} para mantener el
	 * problema que produjo el lanzamiento de esta {@link UncheckedException} y un un mensaje de {@link ErrorDetail} que vamos a crear en el momento.
	 * 
	 * @param cause
	 *            La causa de un problema que vamos a contener dentro de esta excepción.
	 * @param message
	 *            El mensaje del error.
	 * @param parameter
	 *            Los parámetros que requerimos para el detalle del error.
	 */
	public UncheckedException(Throwable cause, String message, Object... parameter) {
		super(message, cause);
		this.errors = new Errors();
		
		if (message != null) {
			this.errors.addError(message);
		}
	}

	/**
	 * El constructor de una instancia de {@link UncheckedException} que recibe como parámetro un mensaje de {@link ErrorDetail} que vamos a crear en
	 * el momento.
	 * 
	 * @param message
	 *            El mensaje del error.
	 * @param parameter
	 *            Los parámetros que requerimos para el detalle del error.
	 */
	public UncheckedException(String message, Object... parameter) {
		super(message);
		this.errors = new Errors();
		
		if (message != null) {
			this.errors.addError(message, parameter);
		}
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
			stringBuffer.append(HolderMessage.getMessage(errorDetail.getMessage(), errorDetail.getParameters()));
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