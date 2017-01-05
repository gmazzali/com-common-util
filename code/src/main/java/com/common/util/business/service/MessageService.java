package com.common.util.business.service;

import java.io.Serializable;
import java.util.Locale;

import com.common.util.domain.model.dto.LogEntryDto;
import com.common.util.domain.model.log.LogEntry;

/**
 * La interfaz que nos va a permitir manejar los mensajes dentro del sistema.
 * 
 * @since 19/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface MessageService extends Serializable {

	public String getMessage(Locale locale, String defaultMessage, String key, Object[] parameter);

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
	public String getMessage(String defaultMessage, String key, Object... parameter);

	/**
	 * La función encargada de leer los recursos y retornar el mensaje dado su clave y su conjunto de parámetros.
	 * 
	 * @param key
	 *            La clave para buscar el mensaje dentro de los recursos. Puede ser <code>null</code>.
	 * @param parameter
	 *            Los parámetros necesarios para completar el mensaje en caso de que se requiera.
	 * @return El mensaje correspondiente a la clave recibida.
	 */
	public String getMessage(String key, Object... parameter);

	/**
	 * Sen encarga de leer los mensajes del sistema y retornar el mensaje dado un detalle de error. En caso de no encontrar el mensaje dado, se
	 * retorna el mensaje que tenemos por omisión.
	 * 
	 * @param errorDetail
	 *            El detalle del error que vamos a procesar para recuperar el mensaje.
	 * @return El mensaje correspondiente al detalle de error recibido. En caso de no encontrar ninguna entrada para este detalle de error, se retorna
	 *         el mensaje que tenemos definido por omisión.
	 */
	public String getMessage(LogEntryDto errorDetail);

	/**
	 * Sen encarga de leer los mensajes del sistema y retornar el mensaje dado un detalle de información. En caso de no encontrar el mensaje dado, se
	 * retorna el mensaje que tenemos por omisión.
	 * 
	 * @param infoDetail
	 *            El detalle del información que vamos a procesar para recuperar el mensaje.
	 * @return El mensaje correspondiente al detalle de información recibido. En caso de no encontrar ninguna entrada para este detalle de
	 *         información, se retorna el mensaje que tenemos definido por omisión.
	 */
	public String getMessage(LogEntry infoDetail);
}