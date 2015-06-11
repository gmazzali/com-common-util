package com.common.util.domain.exception.error;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.common.util.domain.exception.CheckedException;
import com.common.util.domain.exception.UncheckedException;

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
	protected final Set<ErrorDetail> errorDetails;

	/**
	 * El constructor por omisión de un conjunto de errores.
	 */
	public Errors() {
		this.errorDetails = new HashSet<ErrorDetail>();
	}

	/**
	 * Permite retornar el listado de los errores que tenemos dentro de este contenedor.
	 * 
	 * @return El listado de los errores y sus parámetros.
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		for (ErrorDetail error : this.errorDetails) {
			buffer.append(error.toString());
			buffer.append("\n");
		}

		return buffer.toString();
	}

	/**
	 * Se encarga de agregar un nuevo detalle dentro de este conjunto.
	 * 
	 * @param defaultMessage
	 *            El mensaje por omisión del detalle del error.
	 * @param keyMessage
	 *            La clave del mensaje del detalle del error.
	 * @param parameters
	 *            El listado de los parámetros que vamos a utilizar para detallar el error.
	 */
	public void addError(String defaultMessage, String keyMessage, Object... parameters) {
		this.errorDetails.add(new ErrorDetail(defaultMessage, keyMessage, parameters));
	}

	/**
	 * Permite juntar a un conjunto de errores dentro de este.
	 * 
	 * @param errors
	 *            El conjunto de errores que vamos a unir.
	 */
	public void merge(Errors errors) {
		if (errors != null && errors.getErrorDetails() != null) {
			this.errorDetails.addAll(errors.getErrorDetails());
		}
	}

	/**
	 * Se encarga de verificar si hay algún error cargado dentro de este elemento.
	 * 
	 * @return TRUE en caso de que exista al menos un error dentro de este elemento, en caso contrario retorna FALSE.
	 */
	public Boolean hasErrorsDetails() {
		return this.errorDetails.size() > 0;
	}

	/**
	 * Se encarga de retornar el listado de detalles de errores.
	 * 
	 * @return El listado de los detalles de los errores.
	 */
	public Set<ErrorDetail> getErrorDetails() {
		return this.errorDetails;
	}
}