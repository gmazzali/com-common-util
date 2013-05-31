package com.common.util.exception;

import com.common.util.holder.HolderMessage;

/**
 * Las excepciones chequeadas que nos permite recorrer el el c�digo el arbol de llamada.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class CheckedException extends Exception {

	private static final long serialVersionUID = -3282018311354032412L;

	/**
	 * El c�digo del error obtenido.
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
	 * El constructor de una excepci�n {@link CheckedException} que va a recibir un conjunto de errores dentro de un elemento {@link Errors}.
	 * 
	 * @param errors
	 *            El conjunto de errores que vamos a contener dentro de esta excepci�n.
	 */
	// TODO Falta hacer lo de la creaci�n de la excepci�n con un conjunto de errores.
	// public CheckedException(Errors errors) {
	//
	// }

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
