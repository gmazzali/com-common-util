package com.common.util.domain.exception;

import org.springframework.validation.Errors;

import com.common.util.domain.model.log.Log;

/**
 * Las excepciones no chequeadas que vamos a manejar dentro del sistema.
 * 
 * @see CheckedException
 * @see Log
 * 
 * @since 05/02/2014
 * @author Guillermo S. Mazzali
 * @version 1.0
 */
public class UncheckedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * The LOGs of this exception.
	 */
	protected Log log;

	/**
	 * The default constructor.
	 */
	public UncheckedException() {
		super();
		this.log = new Log();
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
		this.log = new Log();
	}

	/**
	 * El constructor de una instancia de {@link UncheckedException} que recibe como parámetro el conjunto de {@link Errors} que vamos a contener.
	 * 
	 * @param errors
	 *            El conjunto de errores que vamos a contener dentro de esta excepción.
	 */
	public UncheckedException(Log log) {
		super();
		this.log = log;
	}

	/**
	 * El constructor de una instancia de {@link UncheckedException} que recibe como parámetro el {@link Throwable} para mantener el problema que
	 * produjo el lanzamiento de esta {@link UncheckedException} y un un mensaje de {@link ErrorDetail} que vamos a crear en el momento.
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
	public UncheckedException(Throwable cause, String defaultMessage, String keyMessage, Object... parameters) {
		super(defaultMessage, cause);
		this.log = new Log();
		this.log.addError(defaultMessage, keyMessage, parameters);
	}

	/**
	 * El constructor de una instancia de {@link UncheckedException} que recibe como parámetro un mensaje de {@link ErrorDetail} que vamos a crear en
	 * el momento.
	 * 
	 * @param defaultMessage
	 *            El mensaje por omisión del detalle del error.
	 * @param keyMessage
	 *            La clave del mensaje del detalle del error.
	 * @param parameters
	 *            Los parámetros que requerimos para el detalle del error.
	 */
	public UncheckedException(String defaultMessage, String keyMessage, Object... parameters) {
		super(defaultMessage);
		this.log = new Log();
		this.log.addError(defaultMessage, keyMessage, parameters);
	}

	/**
	 * Retorna los errores que tenemos dentro de esta escepción.
	 * 
	 * @return El listado de los errores de esta excepción.
	 */
	@Override
	public String toString() {
		return this.log.toString();
	}

	/**
	 * La función encargada de retornar los errores que tenemos dentro de esta excepción.
	 * 
	 * @return El conjunto de errores que tenemos dentro de esta excepción.
	 */
	public Log getLog() {
		return log;
	}
}