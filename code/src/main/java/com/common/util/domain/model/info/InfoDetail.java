package com.common.util.domain.model.info;

import java.io.Serializable;
import java.util.Arrays;

import com.common.util.business.tool.StringUtil;
import com.common.util.domain.model.error.ErrorDetail;

/**
 * Los detalles de las notificaciones dados mediante una frase dada y un conjuntos de parámetros que permiten complementar el mismo.
 * 
 * @see Infos
 * @see ErrorDetail
 * 
 * @since 26/08/2015
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class InfoDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * El mensaje por omisión de este detalle de la notificación.
	 */
	protected final String defaultMessage;
	/**
	 * La clave que corresponde con el mensaje de este detalle de la notificación.
	 */
	protected final String keyMessage;
	/**
	 * Los parámetros que nos permiten completar los datos del detalle de esta notificación.
	 */
	protected final Object[] parameters;

	/**
	 * El construtor de un detalle de una notificación.
	 * 
	 * @param defaultMessage
	 *            El mensaje por omisión de este detalle de la notificación.
	 * @param keyMessage
	 *            La clave que corresponde con el mensaje de este detalle de la notificación.
	 * @param parameters
	 *            Los parámetros que nos permiten completar los datos del detalle de esta notificación.
	 */
	public InfoDetail(String defaultMessage, String keyMessage, Object... parameters) {
		super();
		this.defaultMessage = defaultMessage;
		this.keyMessage = keyMessage;
		this.parameters = parameters;
	}

	/**
	 * La función que despliega el detalle de la notificación de la forma:
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
		InfoDetail other = (InfoDetail) obj;
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
	 * Se ncargada de retornar el mensaje por omisión que contiene el detalle de una notificación.
	 * 
	 * @return El mensaje por omisión de detalle de la notificación.
	 */
	public String getDefaultMessage() {
		return this.defaultMessage;
	}

	/**
	 * Se encargada de retornar la clave del mensaje que contiene el detalle de una notificación.
	 * 
	 * @return La clave del mensaje que contiene el detalle de la notificación.
	 */
	public String getKeyMessage() {
		return this.keyMessage;
	}

	/**
	 * Se encargada de retornar el conjunto de parámetros que recibimos para complementar el detalle de la notificación.
	 * 
	 * @return El conjunto de elementos para complementar el detalle de la notificación.
	 */
	public Object[] getParameters() {
		return this.parameters;
	}
}