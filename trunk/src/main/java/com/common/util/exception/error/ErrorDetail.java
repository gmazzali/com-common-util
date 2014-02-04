package com.common.util.exception.error;

import java.io.Serializable;

/**
 * Los detalles de los errores dados mediante una frase dada y un conjuntos de parámetros que permiten complementar el mismo.
 * 
 * @see Errors
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ErrorDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * El mensaje que describe el error.
	 */
	protected final String message;
	/**
	 * El arreglo de elementos que complementan el detalle del error.
	 */
	protected final Object[] parameters;

	/**
	 * El constructor por copia de un detalle de un error.
	 * 
	 * @param message
	 *            El mensaje que describe el error.
	 * @param parameters
	 *            El listado de los parámetros que detallan el error.
	 */
	public ErrorDetail(String message, Object... parameters) {
		super();
		this.message = message;
		this.parameters = parameters;
	}

	/**
	 * El constructor de un detalle solo con un mensaje de error.
	 * 
	 * @param message
	 *            El mensaje que contiene una descripción del error.
	 */
	public ErrorDetail(String message) {
		this(message, new Object[0]);
	}

	/**
	 * El constructor por omisión.
	 */
	public ErrorDetail() {
		this(null, new Object[0]);
	}

	/**
	 * La función que despliega el detalle de error de la forma:
	 * 
	 * <pre>
	 * message [parameter1, parameter2, etc...]
	 * </pre>
	 * 
	 * @return El mensaje que contiene el detalle formateado.
	 */
	@Override
	public String toString() {
		String parameter = "";
		if (this.parameters != null) {
			parameter = " [";
			for (int index = 0; index < this.parameters.length; index++) {
				parameter += this.parameters[index];

				if (index < this.parameters.length - 1) {
					parameter += ", ";
				}
			}
			parameter = " ]";
		}
		return this.message + " " + parameter;
	}

	/**
	 * La función encargada de retornar el mensaje que contiene el detalle de un error.
	 * 
	 * @return El mensaje de detalle del error.
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * La función encargada de retornar el conjunto de parámetros que recibimos para complementar el detalle del error.
	 * 
	 * @return El conjunto de elementos para complementar el detalle del error.
	 */
	public Object[] getParameters() {
		return this.parameters;
	}
}