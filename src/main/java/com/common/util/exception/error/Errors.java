package com.common.util.exception.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.common.util.exception.CheckedException;
import com.common.util.exception.UncheckedException;

/**
 * El conjunto de los detalles de los errores para manejarlos dentro del sistema.
 * 
 * @see ErrorDetail
 * @see CheckedException
 * @see UncheckedException
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Errors implements Serializable {
	private static final long serialVersionUID = 1L;

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
	 *            El listado de los parámetros que vamos a utilizar para detallar el error.
	 */
	public void addError(String message, Object... parameters) {
		this.errorDetails.add(new ErrorDetail(message, parameters));
	}

	/**
	 * La función encargada de verificar si hay algún error cargado dentro de este elemento.
	 * 
	 * @return TRUE en caso de que exista al menos un error dentro de este elemento, en caso contrario retorna FALSE.
	 */
	public Boolean hasErrorsDetails() {
		return this.errorDetails.size() > 0;
	}

	/**
	 * La función encargada de retornar el listado de detalles de errores.
	 * 
	 * @return El listado de los detalles de los errores.
	 */
	public List<ErrorDetail> getErrorDetails() {
		return this.errorDetails;
	}
}