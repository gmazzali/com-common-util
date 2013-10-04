package com.common.util.exception;

import com.common.util.exception.error.ErrorDetail;
import com.common.util.exception.error.Errors;
import com.common.util.holder.HolderMessage;

/**
 * Las excepciones chequeadas que nos permite realizar un seguimiento de los lanzamiento de la misma para un mejor control en tiempo de desarrollo.
 *  
 *  @see UncheckedException
 *  
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class CheckedException extends Exception {

	private static final long serialVersionUID = -3282018311354032412L;

	/**
	 * El listado de los errores.
	 */
	protected Errors errors;

	/**
	 * El constructor de una instancia de {@link CheckedException} que recibe como parámetro el conjunto de {@link Errors} que vamos a
	 * contener.
	 * 
	 * @param errors
	 *            El conjunto de errores que vamos a contener dentro de esta excepción.
	 */
	public CheckedException(Errors errors) {
		super();
		this.errors = errors;
	}

	/**
	 * El constructor de una instancia de {@link CheckedException} que recibe como parámetro un mensaje de {@link ErrorDetail} que vamos a
	 * crear en el momento.
	 * 
	 * @param message
	 *            El mensaje del error.
	 * @param parameter
	 *            Los parámetros que requerimos para el detalle del error.
	 */
	public CheckedException(String message, Object... parameter) {
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

	/**
	 * El mensaje que detalla del error.
	 */
	@Deprecated
	private String key = "";

	/**
	 * El constructor de una excepción {@link CheckedException} para ser lanzada en algún caso de error dentro del sistema.
	 * 
	 * @param key
	 *            La clave que permite saber que mensaje va a desplegarse.
	 */
	@Deprecated
	public CheckedException(String key) {
		super(key);
		this.key = key;
	}

	/**
	 * El constructor de una excepción {@link CheckedException} que va a contener una excepción encapsulada dentro de esta.
	 * 
	 * @param throwable
	 *            La excepción que vamos a encapsular dentro de esta.
	 */
	@Deprecated
	public CheckedException(Throwable throwable) {
		super(throwable);
		this.key = throwable.getMessage();
	}

	/**
	 * Función encargada de retornar el mensaje que detalla el error que produjo el lanzamiento de esta excepción para poder desplegarse dicho detalle
	 * dentro de algún cuadro o almacenarse dentro de un archivo de LOG.
	 * 
	 * @return El mensaje de error que detalla el estado del sistema que la lanzo.
	 */
	@Override
	@Deprecated
	public String getMessage() {
		return HolderMessage.getMessage(this.key);
	}
}
