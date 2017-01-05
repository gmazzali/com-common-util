package com.common.util.domain.model.log;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.common.util.domain.model.util.LogLevel;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Represent the log for any process or activity.
 * 
 * @see LogEntry
 * @see LogLevel
 * 
 * @since 16/11/2016
 * @author Guillermo S. Mazzali
 * @version 1.0
 */
public class Log implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The lists of the log entries depending of its level.
	 */
	private List<LogEntry> entries = Lists.newArrayList();

	/**
	 * The constructor with the first log entry.
	 * 
	 * @param logLevel
	 *            The log level of the log entry.
	 * @param message
	 *            The default message for the log entry.
	 * @param keyMessage
	 *            The message key for retrieve the final message.
	 * @param parameters
	 *            The parameters for retrieve the final message.
	 */
	public Log(LogLevel logLevel, String message, String keyMessage, Object... parameters) {
		this();
		this.addLogEntry(new LogEntry(logLevel, message, keyMessage, parameters));
	}

	/**
	 * The default constructor.
	 */
	public Log() {
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (LogEntry logEntry : this.entries) {
			buffer.append(logEntry.toString());
			buffer.append("\n");
		}
		return buffer.toString();
	}

	/**
	 * Allow add a new log entry depending of its level.
	 * 
	 * @param logEntry
	 *            The log entry what is included inside this log.
	 */
	private void addLogEntry(LogEntry logEntry) {
		Preconditions.checkNotNull(logEntry);
		this.entries.add(logEntry);
	}

	/**
	 * Allow merging other log inside this log.
	 * 
	 * @param log
	 *            The other log that is merge with this.
	 */
	public void merge(Log log) {
		if (log != null) {
			this.entries.addAll(log.entries);
		}
	}

	/**
	 * Retrieve all the log entries for the log level recieved.
	 * 
	 * @param logLevel
	 *            The log level for search the log entries.
	 * @return The list of the log entries for the log level recieved.
	 */
	public List<LogEntry> getEntries(final LogLevel logLevel) {
		return Lists.newArrayList(Iterables.filter(this.entries, new Predicate<LogEntry>() {

			@Override
			public boolean apply(LogEntry logEntry) {
				return logEntry != null && Objects.equal(logEntry.getLogLevel(), logLevel);
			}
		}));
	}

	/**
	 * Verify if exist any log with is the same level of the level receive.
	 * 
	 * @return <i>true</i> when exist some log entry for the level received. In another case, return <i>false</i>.
	 */
	public Boolean hasEntry(LogLevel logLevel) {
		return logLevel != null && CollectionUtils.isNotEmpty(this.getEntries(logLevel));
	}

	/**
	 * Verify if exist any log of level TRACE.
	 * 
	 * @return <i>true</i> when exist any log of level TRACE. In another case, return <i>false</i>.
	 */
	public Boolean hasTrace() {
		return this.hasEntry(LogLevel.TRACE);
	}

	/**
	 * Verify if exist any log of level DEBUG.
	 * 
	 * @return <i>true</i> when exist any log of level DEBUG. In another case, return <i>false</i>.
	 */
	public Boolean hasDebug() {
		return this.hasEntry(LogLevel.DEBUG);
	}

	/**
	 * Verify if exist any log of level INFO.
	 * 
	 * @return <i>true</i> when exist any log of level INFO. In another case, return <i>false</i>.
	 */
	public Boolean hasInformation() {
		return this.hasEntry(LogLevel.INFO);
	}

	/**
	 * Verify if exist any log of level WARN.
	 * 
	 * @return <i>true</i> when exist any log of level WARN. In another case, return <i>false</i>.
	 */
	public Boolean hasWarning() {
		return this.hasEntry(LogLevel.WARN);
	}

	/**
	 * Verify if exist any log of level ERROR.
	 * 
	 * @return <i>true</i> when exist any log of level ERROR. In another case, return <i>false</i>.
	 */
	public Boolean hasError() {
		return this.hasEntry(LogLevel.ERROR);
	}

	/**
	 * Verify if exist any log of level FATAL.
	 * 
	 * @return <i>true</i> when exist any log of level FATAL. In another case, return <i>false</i>.
	 */
	public Boolean hasFatal() {
		return this.hasEntry(LogLevel.FATAL);
	}

	/**
	 * Allow add a new log entry of TRACE level.
	 * 
	 * @param message
	 *            The default message for the log entry.
	 * @param keyMessage
	 *            The message key for retrieve the final message.
	 * @param parameters
	 *            The parameters for retrieve the final message.
	 */
	public void addTrace(String message, String keyMessage, Object... parameters) {
		this.addLogEntry(new LogEntry(LogLevel.TRACE, message, keyMessage, parameters));
	}

	/**
	 * Allow add a new log entry of DEBUG level.
	 * 
	 * @param message
	 *            The default message for the log entry.
	 * @param keyMessage
	 *            The message key for retrieve the final message.
	 * @param parameters
	 *            The parameters for retrieve the final message.
	 */
	public void addDebug(String message, String keyMessage, Object... parameters) {
		this.addLogEntry(new LogEntry(LogLevel.DEBUG, message, keyMessage, parameters));
	}

	/**
	 * Allow add a new log entry of INFO level.
	 * 
	 * @param message
	 *            The default message for the log entry.
	 * @param keyMessage
	 *            The message key for retrieve the final message.
	 * @param parameters
	 *            The parameters for retrieve the final message.
	 */
	public void addInfo(String message, String keyMessage, Object... parameters) {
		this.addLogEntry(new LogEntry(LogLevel.INFO, message, keyMessage, parameters));
	}

	/**
	 * Allow add a new log entry of WARN level.
	 * 
	 * @param message
	 *            The default message for the log entry.
	 * @param keyMessage
	 *            The message key for retrieve the final message.
	 * @param parameters
	 *            The parameters for retrieve the final message.
	 */
	public void addWarn(String message, String keyMessage, Object... parameters) {
		this.addLogEntry(new LogEntry(LogLevel.WARN, message, keyMessage, parameters));
	}

	/**
	 * Allow add a new log entry of ERROR level.
	 * 
	 * @param message
	 *            The default message for the log entry.
	 * @param keyMessage
	 *            The message key for retrieve the final message.
	 * @param parameters
	 *            The parameters for retrieve the final message.
	 */
	public void addError(String message, String keyMessage, Object... parameters) {
		this.addLogEntry(new LogEntry(LogLevel.ERROR, message, keyMessage, parameters));
	}

	/**
	 * Allow add a new log entry of FATAL level.
	 * 
	 * @param message
	 *            The default message for the log entry.
	 * @param keyMessage
	 *            The message key for retrieve the final message.
	 * @param parameters
	 *            The parameters for retrieve the final message.
	 */
	public void addFatal(String message, String keyMessage, Object... parameters) {
		this.addLogEntry(new LogEntry(LogLevel.FATAL, message, keyMessage, parameters));
	}

	/**
	 * Retrieve the entries for the level of TRACE.
	 * 
	 * @return the entries for the level of TRACE.
	 */
	public List<LogEntry> getTraceEntries() {
		return this.getEntries(LogLevel.TRACE);
	}

	/**
	 * Retrieve the entries for the level of DEBUG.
	 * 
	 * @return the entries for the level of DEBUG.
	 */
	public List<LogEntry> getDebugEntries() {
		return this.getEntries(LogLevel.DEBUG);
	}

	/**
	 * Retrieve the entries for the level of INFO.
	 * 
	 * @return the entries for the level of INFO.
	 */
	public List<LogEntry> getInfoEntries() {
		return this.getEntries(LogLevel.INFO);
	}

	/**
	 * Retrieve the entries for the level of WARN.
	 * 
	 * @return the entries for the level of WARN.
	 */
	public List<LogEntry> getWarnEntries() {
		return this.getEntries(LogLevel.WARN);
	}

	/**
	 * Retrieve the entries for the level of FATAL.
	 * 
	 * @return the entries for the level of FATAL.
	 */
	public List<LogEntry> getFatalEntries() {
		return this.getEntries(LogLevel.FATAL);
	}
}