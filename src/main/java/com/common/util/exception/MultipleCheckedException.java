package com.common.util.exception;

import com.common.util.exception.error.ErrorDetail;
import com.common.util.exception.error.Errors;

/**
 * Las excepciones chequeadas que nos permite realizar un seguimiento de la misma para control en tiempo de desarrollo y que dentro de la misma se
 * detalla un listado de errores.
 * 
 * @see Errors
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class MultipleCheckedException extends Exception {

	private static final long serialVersionUID = -3963903981867781202L;

	/**
	 * El listado de los errores.
	 */
	protected Errors errors;

	/**
	 * El constructor de una instancia de {@link MultipleCheckedException} que recibe como par�metro el conjunto de {@link Errors} que vamos a
	 * contener.
	 * 
	 * @param errors
	 *            El conjunto de errores que vamos a contener dentro de esta excepci�n.
	 */
	public MultipleCheckedException(Errors errors) {
		super();
		this.errors = errors;
	}

	/**
	 * El constructor de una instancia de {@link MultipleCheckedException} que recibe como par�metro un mensaje de {@link ErrorDetail} que vamos a
	 * crear en el momento.
	 * 
	 * @param message
	 *            El mensaje del error.
	 * @param parameter
	 *            Los par�metros que requerimos para el detalle del error.
	 */
	public MultipleCheckedException(String message, Object... parameter) {
		super(message);
		this.errors = new Errors();
		this.errors.addError(message, parameter);
	}

	/**
	 * La funci�n encargada de retornar los errores que tenemos dentro de esta excepci�n.
	 * 
	 * @return El conjunto de errores que tenemos dentro de esta excepci�n.
	 */
	public Errors getErrors() {
		return this.errors;
	}
}