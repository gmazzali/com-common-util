package com.common.util.business.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;

import com.common.util.business.service.MessageService;
import com.common.util.business.tool.StringUtil;
import com.common.util.domain.model.error.ErrorDetail;
import com.common.util.domain.model.info.InfoDetail;

/**
 * La clase que implementa la interfaz que nos va a permitir manejar los mensajes dentro del sistema.
 * 
 * @since 19/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class MessageServiceImpl implements MessageService {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(MessageServiceImpl.class);

	/**
	 * El conjunto de los mensaje que vamos a manejar dentro del sistema para los componentes.
	 */
	private MessageSource resources;
	/**
	 * La ubicación para la que vamos a manejar los mensajes.
	 */
	private Locale locale;

	/**
	 * La función encargada de cargar el recurso desde donde vamos a leer los mensajes.
	 * 
	 * @param resources
	 *            Los recursos desde donde vamos a tomar los mensajes del sistema.
	 */
	public void setResources(MessageSource resources) {
		this.resources = resources;
	}

	/**
	 * La función encargada de cargar la localidad sobre la que vamos a leer los mensajes del sistema.
	 * 
	 * @param locale
	 *            La localidad sobre la que vamos a leer los mensajes.
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@Override
	public String getMessage(String key, Object... parameter) {
		LOGGER.debug("KEY: " + key + " VALUES: {" + StringUtils.arrayToDelimitedString(parameter, ", ") + "}");
		if (key != null) {
			return this.resources.getMessage(key, parameter, key, this.locale);
		} else {
			return null;
		}
	}

	@Override
	public String getMessage(String defaultMessage, String key, Object... parameter) {
		LOGGER.debug("DEFAULT:" + defaultMessage + "KEY: " + key + " VALUES: {" + StringUtil.arrayToDelimitedString(parameter, ", ") + "}");
		if (key != null) {
			return this.resources.getMessage(key, parameter, defaultMessage, this.locale);
		} else {
			return defaultMessage;
		}
	}

	@Override
	public String getMessage(ErrorDetail errorDetail) {
		checkNotNull(errorDetail);
		LOGGER.debug("ERROR DETAIL:" + errorDetail.toString());
		return this.resources.getMessage(errorDetail.getKeyMessage(), errorDetail.getParameters(), errorDetail.getDefaultMessage(), this.locale);
	}

	@Override
	public String getMessage(InfoDetail infoDetail) {
		checkNotNull(infoDetail);
		LOGGER.debug("INFO DETAIL:" + infoDetail.toString());
		return this.resources.getMessage(infoDetail.getKeyMessage(), infoDetail.getParameters(), infoDetail.getDefaultMessage(), this.locale);
	}
}