package com.common.util.exception;

import com.common.util.holder.HolderMessage;

/**
 * Las excepciones no chequeadas que vamos a manejar dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class UncheckedException extends RuntimeException {

	private static final long serialVersionUID = 3912610403699752161L;

	/**
	 * El código del error obtenido.
	 */
	private final String key;

	/**
	 * El constructor de una excepción {@link UncheckedException} para ser lanzada en algún caso de error dentro del sistema.
	 * 
	 * @param key
	 *            La clave que permite saber que mensaje va a desplegarse.
	 */
	public UncheckedException(String key) {
		super(key);
		this.key = key;
	}

	/**
	 * El constructor de una excepción {@link UncheckedException} que recibe una excepción que vamos a encapsular dentro de esta.
	 * 
	 * @param throwable
	 *            La excepción que vamos a encapsular dentro de esta.
	 */
	public UncheckedException(Throwable throwable) {
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
	public String getMessage() {
		return HolderMessage.getMessage(this.key);
	}
}
