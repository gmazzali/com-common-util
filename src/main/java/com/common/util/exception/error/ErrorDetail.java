package com.common.util.exception.error;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Los detalles de los errores dados mediante una frase dada y un conjuntos de parámetros que permiten complementar el mismo.
 * 
 * @see Errors
 * 
 * @since 11/02/2014
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
		StringBuffer buffer = new StringBuffer(this.message);
		
		if (this.parameters != null) {
			buffer.append(" [");
			for (int index = 0; index < this.parameters.length; index++) {
				buffer.append(this.parameters[index]);

				if (index < this.parameters.length - 1) {
					buffer.append(", ");
				}
			}
			buffer.append(" ]");
		}
		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.message == null) ? 0 : this.message.hashCode());
		result = prime * result + Arrays.hashCode(this.parameters);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (this.getClass() != obj.getClass()) {
			return false;
		}

		ErrorDetail other = (ErrorDetail) obj;
		if (this.message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!this.message.equals(other.message)) {
			return false;
		}

		if (!Arrays.equals(this.parameters, other.parameters)) {
			return false;
		}

		return true;
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