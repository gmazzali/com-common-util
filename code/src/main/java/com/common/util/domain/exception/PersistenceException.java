package com.common.util.domain.exception;

import com.common.util.domain.model.log.Log;

/**
 * The unchecked exceptions inside the persistence layer.
 * 
 * @since 14/03/2017
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class PersistenceException extends UncheckedException {

	private static final long serialVersionUID = 1L;

	/**
	 * The default constructor.
	 */
	public PersistenceException() {
		super();
	}

	/**
	 * The constructor of an {@link PersistenceException}.
	 * 
	 * @param cause
	 *            The cause of the problem inside this exception.
	 */
	public PersistenceException(Throwable cause) {
		super(cause);
	}


	/**
	 * The constructor of an {@link PersistenceException}.
	 * 
	 * @param log
	 *            The log inside this exception.
	 */
	public PersistenceException(Log log) {
		super(log);
	}

	/**
	 * The constructor of an {@link PersistenceException}.
	 * 
	 * @param cause
	 *            The cause of the problem inside this exception.
	 * @param defaultMessage
	 *            The default message for the log entry.
	 * @param keyMessage
	 *            The message key for retrieve the final message.
	 * @param parameters
	 *            The parameters for retrieve the final message.
	 */
	public PersistenceException(Throwable cause, String defaultMessage, String keyMessage, Object... parameters) {
		super(cause, defaultMessage, keyMessage, parameters);
	}

	/**
	 * The constructor of an {@link PersistenceException}.
	 * 
	 * @param defaultMessage
	 *            The default message for the log entry.
	 * @param keyMessage
	 *            The message key for retrieve the final message.
	 * @param parameters
	 *            The parameters for retrieve the final message.
	 */
	public PersistenceException(String defaultMessage, String keyMessage, Object... parameters) {
		super(defaultMessage, keyMessage, parameters);
	}
}