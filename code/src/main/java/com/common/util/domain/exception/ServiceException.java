package com.common.util.domain.exception;

import com.common.util.domain.model.error.ErrorDetail;
import com.common.util.domain.model.error.Errors;

/**
 * Las excepciones no chequeadas que vamos a manejar dentro de los servicios.
 * 
 * @since 16/10/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ServiceException extends UncheckedException {

	private static final long serialVersionUID = 1L;

	/**
	 * El constructor de una instancia de {@link ServiceException} que no recibe par�metros.
	 */
	public ServiceException() {
		super();
	}

	/**
	 * El constructor de una instancia de {@link ServiceException} que recibe como par�metro un elemento {@link Throwable} para mantener el problema
	 * que produjo el lanzamiento de esta {@link ServiceException}.
	 * 
	 * @param cause
	 *            La causa de un problema que vamos a contener dentro de esta excepci�n.
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * El constructor de una instancia de {@link ServiceException} que recibe como par�metro el conjunto de {@link Errors} que vamos a contener.
	 * 
	 * @param errors
	 *            El conjunto de errores que vamos a contener dentro de esta excepci�n.
	 */
	public ServiceException(Errors errors) {
		super(errors);
	}

	/**
	 * El constructor de una instancia de {@link ServiceException} que recibe como par�metro el {@link Throwable} para mantener el problema que
	 * produjo el lanzamiento de esta {@link ServiceException} y un un mensaje de {@link ErrorDetail} que vamos a crear en el momento.
	 * 
	 * @param cause
	 *            La causa de un problema que vamos a contener dentro de esta excepci�n.
	 * @param defaultMessage
	 *            El mensaje por omisi�n del detalle del error.
	 * @param keyMessage
	 *            La clave del mensaje del detalle del error.
	 * @param parameters
	 *            Los par�metros que requerimos para el detalle del error.
	 */
	public ServiceException(Throwable cause, String defaultMessage, String keyMessage, Object... parameters) {
		super(cause, defaultMessage, keyMessage, parameters);
	}

	/**
	 * El constructor de una instancia de {@link ServiceException} que recibe como par�metro un mensaje de {@link ErrorDetail} que vamos a crear en el
	 * momento.
	 * 
	 * @param defaultMessage
	 *            El mensaje por omisi�n del detalle del error.
	 * @param keyMessage
	 *            La clave del mensaje del detalle del error.
	 * @param parameters
	 *            Los par�metros que requerimos para el detalle del error.
	 */
	public ServiceException(String defaultMessage, String keyMessage, Object... parameters) {
		super(defaultMessage, keyMessage, parameters);
	}
}