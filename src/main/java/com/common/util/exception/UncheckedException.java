package com.common.util.exception;

import com.common.util.exception.error.ErrorDetail;
import com.common.util.exception.error.Errors;

/**
 * Las excepciones no chequeadas que vamos a manejar dentro del sistema.
 * 
 * @see CheckedException
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class UncheckedException extends RuntimeException {

	private static final long serialVersionUID = 3912610403699752161L;

	/**
	 * El listado de los errores.
	 */
	protected Errors errors;

	/**
	 * El constructor de una instancia de {@link UncheckedException} que recibe como parámetro el conjunto de {@link Errors} que vamos a
	 * contener.
	 * 
	 * @param errors
	 *            El conjunto de errores que vamos a contener dentro de esta excepción.
	 */
	public UncheckedException(Errors errors) {
		super();
		this.errors = errors;
	}

	/**
	 * El constructor de una instancia de {@link UncheckedException} que recibe como parámetro un mensaje de {@link ErrorDetail} que vamos a
	 * crear en el momento.
	 * 
	 * @param message
	 *            El mensaje del error.
	 * @param parameter
	 *            Los parámetros que requerimos para el detalle del error.
	 */
	public UncheckedException(String message, Object... parameter) {
		super(message);
		this.errors = new Errors();
		this.errors.addError(message, parameter);
	}

	/**
	 * La función encargada de retornar los errores que tenemos dentro de esta excepción.
	 * 
	 * @return El conjunto de errores que tenemos dentro de esta excepción.
	 */
	public Errors getErrors() {
		return this.errors;
	}
}