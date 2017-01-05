package com.common.util.business.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;

import com.common.util.business.service.MessageService;
import com.common.util.business.tool.StringUtil;
import com.common.util.domain.model.dto.LogEntryDto;
import com.common.util.domain.model.log.LogEntry;

/**
 * The services for the messages.
 * 
 * @since 19/11/2014
 * @author Guillermo S. Mazzali
 * @version 1.0
 */
public class MessageServiceImpl implements MessageService {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(MessageServiceImpl.class);

	/**
	 * The messages sources.
	 */
	private MessageSource resources;
	/**
	 * The current locale.
	 */
	private Locale locale;

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
	public String getMessage(Locale locale, String defaultMessage, String key, Object... parameter) {
		LOGGER.debug("DEFAULT:" + defaultMessage + "KEY: " + key + " VALUES: {" + StringUtil.arrayToDelimitedString(parameter, ", ") + "}");
		if (key != null) {
			return this.resources.getMessage(key, parameter, defaultMessage, locale);
		} else {
			return defaultMessage;
		}
	}

	@Override
	public String getMessage(LogEntryDto logEntryDto) {
		checkNotNull(logEntryDto);
		LOGGER.debug("ERROR DETAIL:" + logEntryDto.toString());
		return this.resources.getMessage(logEntryDto.getKeyMessage(), logEntryDto.getParameters(), logEntryDto.getMessage(), this.locale);
	}

	@Override
	public String getMessage(LogEntry logEntry) {
		checkNotNull(logEntry);
		LOGGER.debug("INFO DETAIL:" + logEntry.toString());
		return this.resources.getMessage(logEntry.getKeyMessage(), logEntry.getParameters(), logEntry.getMessage(), this.locale);
	}

	/**
	 * Set the messages sources.
	 * 
	 * @param resources
	 *            The new messages sources.
	 */
	public void setResources(MessageSource resources) {
		this.resources = resources;
	}

	/**
	 * Set the current locale.
	 * 
	 * @param locale
	 *            The current locale.
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}