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
	 * Sen encarga de leer los mensajes del sistema y retornar el mensaje dado su clave y su conjunto de par�metros. En caso de no encontrar el
	 * mensaje dado, se retorna el mensaje que tenemos por omisi�n.
	 * 
	 * @param defaultMessage
	 *            El mensaje que vamos a manejar por omisi�n en caso de que no se encuentra la clave recibida. Puede ser <code>null</code>.
	 * @param key
	 *            La clave para buscar el mensaje dentro de los recursos. Puede ser <code>null</code>.
	 * @param parameter
	 *            Los par�metros necesarios para completar el mensaje en caso de que se requiera.
	 * @return El mensaje correspondiente a la clave recibida. En caso de no encontrar ninguna entrada para la clave, se retorna el mensaje que
	 *         tenemos definido por omisi�n.
	 */
	public String getMessage(String defaultMessage, String key, Object... parameter);

	/**
	 * La funci�n encargada de leer los recursos y retornar el mensaje dado su clave y su conjunto de par�metros.
	 * 
	 * @param key
	 *            La clave para buscar el mensaje dentro de los recursos. Puede ser <code>null</code>.
	 * @param parameter
	 *            Los par�metros necesarios para completar el mensaje en caso de que se requiera.
	 * @return El mensaje correspondiente a la clave recibida.
	 */
	public String getMessage(String key, Object... parameter);

	/**
	 * Sen encarga de leer los mensajes del sistema y retornar el mensaje dado un detalle de error. En caso de no encontrar el mensaje dado, se
	 * retorna el mensaje que tenemos por omisi�n.
	 * 
	 * @param errorDetail
	 *            El detalle del error que vamos a procesar para recuperar el mensaje.
	 * @return El mensaje correspondiente al detalle de error recibido. En caso de no encontrar ninguna entrada para este detalle de error, se retorna
	 *         el mensaje que tenemos definido por omisi�n.
	 */
	public String getMessage(LogEntryDto errorDetail);

	/**
	 * Sen encarga de leer los mensajes del sistema y retornar el mensaje dado un detalle de informaci�n. En caso de no encontrar el mensaje dado, se
	 * retorna el mensaje que tenemos por omisi�n.
	 * 
	 * @param infoDetail
	 *            El detalle del informaci�n que vamos a procesar para recuperar el mensaje.
	 * @return El mensaje correspondiente al detalle de informaci�n recibido. En caso de no encontrar ninguna entrada para este detalle de
	 *         informaci�n, se retorna el mensaje que tenemos definido por omisi�n.
	 */
	public String getMessage(LogEntry infoDetail);
}