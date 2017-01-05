package com.common.util.domain.model.dto;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.common.util.domain.model.util.LogLevel;

/**
 * Represent the log detail.
 * 
 * @see LogDto
 * @see LogLevel
 * 
 * @since 23/11/2016
 * @author Guillermo S. Mazzali
 * @version 1.0
 */
public class LogEntryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The log level of this log detail.
	 */
	private final LogLevel logLevel;
	/**
	 * The default message of this log detail.
	 */
	private final String message;
	/**
	 * The message key of this log detail for retrieve the final message.
	 */
	private final String keyMessage;
	/**
	 * The parameters of this log detail for retrieve the final message.
	 */
	private final Object[] parameters;

	/**
	 * The constructor of this log detail. Receive all the required data for this log detail.
	 * 
	 * @param logLevel
	 *            The log level of this log.
	 * @param message
	 *            The default message of this log.
	 * @param keyMessage
	 *            The key message for this log entry for retrieve the final message
	 * @param parameters
	 *            The parameters for retrieve the message with the message key.
	 */
	public LogEntryDto(LogLevel logLevel, String message, String keyMessage, Object... parameters) {
		super();
		this.logLevel = logLevel;
		this.message = message;
		this.keyMessage = keyMessage;
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("LEVEL: ");
		buffer.append(this.logLevel);
		buffer.append(" - DEFAULT: ");
		buffer.append(this.message);
		buffer.append(" KEY: ");
		buffer.append(this.keyMessage);
		buffer.append(" VALUES: [");
		buffer.append(StringUtils.join(this.parameters, ", "));
		buffer.append("]");
		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.keyMessage == null) ? 0 : this.keyMessage.hashCode());
		result = prime * result + ((this.logLevel == null) ? 0 : this.logLevel.hashCode());
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		LogEntryDto other = (LogEntryDto) obj;
		if (this.keyMessage == null) {
			if (other.keyMessage != null) {
				return false;
			}
		} else if (!this.keyMessage.equals(other.keyMessage)) {
			return false;
		}
		if (this.logLevel != other.logLevel) {
			return false;
		}
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
	 * Retrieve the log level of this log.
	 * 
	 * @return The log level of this log.
	 */
	public LogLevel getLogLevel() {
		return logLevel;
	}

	/**
	 * Retrieve the default message of this log (not the message refer of the key message).
	 * 
	 * @return The default message of this log.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Retrieve the key message for this log entry for retrieve the final message.
	 * 
	 * @return The key message for this log entry for retrieve the final message.
	 */
	public String getKeyMessage() {
		return this.keyMessage;
	}

	/**
	 * Retrieve the parameters for retrieve the message with the message key.
	 * 
	 * @return The parameters for retrieve the message with the message key.
	 */
	public Object[] getParameters() {
		return this.parameters;
	}
}