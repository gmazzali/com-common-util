package com.common.util.domain.model.info;

import java.io.Serializable;
import java.util.Arrays;

import com.common.util.business.tool.StringUtil;
import com.common.util.domain.model.error.ErrorDetail;

/**
 * Los detalles de las notificaciones dados mediante una frase dada y un conjuntos de par�metros que permiten complementar el mismo.
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
	 * El mensaje por omisi�n de este detalle de la notificaci�n.
	 */
	protected final String defaultMessage;
	/**
	 * La clave que corresponde con el mensaje de este detalle de la notificaci�n.
	 */
	protected final String keyMessage;
	/**
	 * Los par�metros que nos permiten completar los datos del detalle de esta notificaci�n.
	 */
	protected final Object[] parameters;

	/**
	 * El construtor de un detalle de una notificaci�n.
	 * 
	 * @param defaultMessage
	 *            El mensaje por omisi�n de este detalle de la notificaci�n.
	 * @param keyMessage
	 *            La clave que corresponde con el mensaje de este detalle de la notificaci�n.
	 * @param parameters
	 *            Los par�metros que nos permiten completar los datos del detalle de esta notificaci�n.
	 */
	public InfoDetail(String defaultMessage, String keyMessage, Object... parameters) {
		super();
		this.defaultMessage = defaultMessage;
		this.keyMessage = keyMessage;
		this.parameters = parameters;
	}

	/**
	 * La funci�n que despliega el detalle de la notificaci�n de la forma:
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
	 * Se ncargada de retornar el mensaje por omisi�n que contiene el detalle de una notificaci�n.
	 * 
	 * @return El mensaje por omisi�n de detalle de la notificaci�n.
	 */
	public String getDefaultMessage() {
		return this.defaultMessage;
	}

	/**
	 * Se encargada de retornar la clave del mensaje que contiene el detalle de una notificaci�n.
	 * 
	 * @return La clave del mensaje que contiene el detalle de la notificaci�n.
	 */
	public String getKeyMessage() {
		return this.keyMessage;
	}

	/**
	 * Se encargada de retornar el conjunto de par�metros que recibimos para complementar el detalle de la notificaci�n.
	 * 
	 * @return El conjunto de elementos para complementar el detalle de la notificaci�n.
	 */
	public Object[] getParameters() {
		return this.parameters;
	}
}