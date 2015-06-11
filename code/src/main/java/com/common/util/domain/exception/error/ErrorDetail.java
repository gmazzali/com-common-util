package com.common.util.domain.exception.error;

import java.io.Serializable;
import java.util.Arrays;

import com.common.util.business.tool.StringUtil;

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
	 * El mensaje por omisión de este detalle del error.
	 */
	protected final String defaultMessage;
	/**
	 * La clave que corresponde con el mensaje de este detalle del error.
	 */
	protected final String keyMessage;
	/**
	 * Los parámetros que nos permiten completar los datos del detalle de este error.
	 */
	protected final Object[] parameters;

	/**
	 * El construtor de un detalle de un error.
	 * 
	 * @param defaultMessage
	 *            El mensaje por omisión de este detalle del error.
	 * @param keyMessage
	 *            La clave que corresponde con el mensaje de este detalle del error.
	 * @param parameters
	 *            Los parámetros que nos permiten completar los datos del detalle de este error.
	 */
	public ErrorDetail(String defaultMessage, String keyMessage, Object... parameters) {
		super();
		this.defaultMessage = defaultMessage;
		this.keyMessage = keyMessage;
		this.parameters = parameters;
	}

	/**
	 * La función que despliega el detalle de error de la forma:
	 * 
	 * <pre>
	 * DEFAULT: {@link #defaultMessage} KEY: {@link #keyMessage} VALUES: {@link #parameters} };
	 * </pre>
	 * 
	 * @return El mensaje que contiene el detalle formateado.
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("DEFAULT: ");
		buffer.append(this.defaultMessage);
		buffer.append(" KEY: ");
		buffer.append(this.keyMessage);
		buffer.append(" VALUES: {");
		buffer.append(StringUtil.arrayToDelimitedString(parameters, ", "));
		buffer.append("};");

		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((defaultMessage == null) ? 0 : defaultMessage.hashCode());
		result = prime * result + ((keyMessage == null) ? 0 : keyMessage.hashCode());
		result = prime * result + Arrays.hashCode(parameters);
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		ErrorDetail other = (ErrorDetail) obj;
		if (defaultMessage == null) {
			if (other.defaultMessage != null) {
				return false;
			}
		} else if (!defaultMessage.equals(other.defaultMessage)) {
			return false;
		}
		if (keyMessage == null) {
			if (other.keyMessage != null) {
				return false;
			}
		} else if (!keyMessage.equals(other.keyMessage)) {
			return false;
		}
		if (!Arrays.equals(parameters, other.parameters)) {
			return false;
		}
		return true;
	}

	/**
	 * Se ncargada de retornar el mensaje por omisión que contiene el detalle de un error.
	 * 
	 * @return El mensaje por omisión de detalle del error.
	 */
	public String getDefaultMessage() {
		return this.defaultMessage;
	}

	/**
	 * Se encargada de retornar la clave del mensaje que contiene el detalle de un error.
	 * 
	 * @return La clave del mensaje que contiene el detalle del error.
	 */
	public String getKeyMessage() {
		return this.keyMessage;
	}

	/**
	 * Se encargada de retornar el conjunto de parámetros que recibimos para complementar el detalle del error.
	 * 
	 * @return El conjunto de elementos para complementar el detalle del error.
	 */
	public Object[] getParameters() {
		return this.parameters;
	}
}