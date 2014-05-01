package com.common.util.business.holder;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;

import com.common.util.business.tool.StringUtil;

/**
 * La clase que va a contener las propiedades que van a tener los mensajes propios de las excepciones que van a desplegarse dentro de la aplicación.
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class HolderMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * El conjunto de los mensaje que vamos a manejar dentro del sistema para los componentes.
	 */
	private static MessageSource resources;
	/**
	 * La ubicación para la que vamos a manejar los mensajes.
	 */
	private static Locale locale;

	/**
	 * La función encargada de cargar el recurso desde donde vamos a leer los mensajes.
	 * 
	 * @param resources
	 *            Los recursos desde donde vamos a tomar los mensajes del sistema.
	 */
	public void setResources(MessageSource resources) {
		HolderMessage.resources = resources;
	}

	/**
	 * La función encargada de cargar la localidad sobre la que vamos a leer los mensajes del sistema.
	 * 
	 * @param locale
	 *            La localidad sobre la que vamos a leer los mensajes.
	 */
	public void setLocale(Locale locale) {
		HolderMessage.locale = locale;
	}

	/**
	 * La función encargada de leer los recursos y retornar el mensaje dado su clave y su conjunto de parámetros.
	 * 
	 * @param key
	 *            La clave para buscar el mensaje dentro de los recursos. Puede ser <code>null</code>.
	 * @param parameter
	 *            Los parámetros necesarios para completar el mensaje en caso de que se requiera.
	 * @return El mensaje correspondiente a la clave recibida.
	 */
	public static String getMessage(String key, Object... parameter) {
		if (HolderMessage.resources != null && key != null) {
			return HolderMessage.resources.getMessage(key, parameter, key, HolderMessage.locale);
		} else {
			if (parameter == null || parameter.length == 0) {
				return key;
			} else {
				return "KEY: " + key + " VALUES: {" + StringUtils.arrayToDelimitedString(parameter, ", ") + "}";
			}
		}
	}

	/**
	 * Sen encarga de leer los mensajes del sistema y retornar el mensaje dado su clave y su conjunto de parámetros. En caso de no encontrar el
	 * mensaje dado, se retorna el mensaje que tenemos por omisión.
	 * 
	 * @param defaultMessage
	 *            El mensaje que vamos a manejar por omisión en caso de que no se encuentra la clave recibida. Puede ser <code>null</code>.
	 * @param key
	 *            La clave para buscar el mensaje dentro de los recursos. Puede ser <code>null</code>.
	 * @param parameter
	 *            Los parámetros necesarios para completar el mensaje en caso de que se requiera.
	 * @return El mensaje correspondiente a la clave recibida. En caso de no encontrar ninguna entrada para la clave, se retorna el mensaje que
	 *         tenemos definido por omisión.
	 */
	public static String getMessage(String defaultMessage, String key, Object... parameter) {
		if (HolderMessage.resources != null && key != null) {
			return HolderMessage.resources.getMessage(key, parameter, defaultMessage, HolderMessage.locale);
		} else {
			if (parameter == null || parameter.length == 0) {
				return key;
			} else {
				return "DEFAULT:" + defaultMessage + "KEY: " + key + " VALUES: {" + StringUtil.arrayToDelimitedString(parameter, ", ") + "}";
			}
		}
	}
}