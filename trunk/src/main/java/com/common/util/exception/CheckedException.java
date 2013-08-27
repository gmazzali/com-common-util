package com.common.util.exception;

import com.common.util.holder.HolderMessage;

/**
 * Las excepciones chequeadas que nos permite realizar un seguimiento de los lanzamiento de la misma para un mejor control en tiempo de desarrollo.
 * 
 * @see MultipleCheckedException
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class CheckedException extends Exception {

	private static final long serialVersionUID = -3282018311354032412L;

	/**
	 * El mensaje que detalla del error.
	 */
	private final String key;

	/**
	 * El constructor de una excepci�n {@link CheckedException} para ser lanzada en alg�n caso de error dentro del sistema.
	 * 
	 * @param key
	 *            La clave que permite saber que mensaje va a desplegarse.
	 */
	public CheckedException(String key) {
		super(key);
		this.key = key;
	}

	/**
	 * El constructor de una excepci�n {@link CheckedException} que va a contener una excepci�n encapsulada dentro de esta.
	 * 
	 * @param throwable
	 *            La excepci�n que vamos a encapsular dentro de esta.
	 */
	public CheckedException(Throwable throwable) {
		super(throwable);
		this.key = throwable.getMessage();
	}

	/**
	 * Funci�n encargada de retornar el mensaje que detalla el error que produjo el lanzamiento de esta excepci�n para poder desplegarse dicho detalle
	 * dentro de alg�n cuadro o almacenarse dentro de un archivo de LOG.
	 * 
	 * @return El mensaje de error que detalla el estado del sistema que la lanzo.
	 */
	@Override
	public String getMessage() {
		return HolderMessage.getMessage(this.key);
	}
}
