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
	 * El constructor de una instancia de {@link UncheckedException} que recibe como par�metro un elemento {@link Throwable} para mantener el problema
	 * que produjo el lanzamiento de esta {@link UncheckedException}.
	 * 
	 * @param cause
	 *            La causa de un problema que vamos a contener dentro de esta excepci�n.
	 */
	public UncheckedException(Throwable cause) {
		super(cause);
		this.log = new Log();
	}

	/**
	 * El constructor de una instancia de {@link UncheckedException} que recibe como par�metro el conjunto de {@link Errors} que vamos a contener.
	 * 
	 * @param errors
	 *            El conjunto de errores que vamos a contener dentro de esta excepci�n.
	 */
	public UncheckedException(Log log) {
		super();
		this.log = log;
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
		this.log = new Log();
		this.log.addError(defaultMessage, keyMessage, parameters);
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
		this.log = new Log();
		this.log.addError(defaultMessage, keyMessage, parameters);
	}

	/**
	 * Retorna los errores que tenemos dentro de esta escepci�n.
	 * 
	 * @return El listado de los errores de esta excepci�n.
	 */
	@Override
	public String toString() {
		return this.log.toString();
	}

	/**
	 * La funci�n encargada de retornar los errores que tenemos dentro de esta excepci�n.
	 * 
	 * @return El conjunto de errores que tenemos dentro de esta excepci�n.
	 */
	public Log getLog() {
		return log;
	}
}