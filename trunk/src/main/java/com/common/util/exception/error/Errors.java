package com.common.util.exception.error;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase que nos permite almacenar un listado de detalles de errores para manejarlos dentro del sistema.
 * 
 * @see ErrorDetail
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Errors {

	/**
	 * El listado de los detalles de los errores.
	 */
	protected final List<ErrorDetail> errorDetails;

	/**
	 * El constructor por omisión de un conjunto de errores.
	 */
	public Errors() {
		this.errorDetails = new ArrayList<ErrorDetail>();
	}

	/**
	 * La función encargada de agregar un nuevo detalle dentro de este conjunto.
	 * 
	 * @param message
	 *            La clave del error.
	 * @param parameters
	 *            El listdo de los parámetros que vamos a utilizar para detallar el error.
	 */
	public void addMessage(String message, Object[] parameters) {
		this.errorDetails.add(new ErrorDetail(message, parameters));
	}

	/**
	 * La función encargada de retornar el listado de detalles de errores.
	 * 
	 * @return El listado de los detalles de los errores.
	 */
	public List<ErrorDetail> getMessages() {
		return this.errorDetails;
	}
}
