package com.common.util.domain.model.util;

import com.common.util.domain.model.log.Log;
import com.common.util.domain.model.log.LogEntry;

/**
 * Represent the log level for all the log details.
 * 
 * @see LogEntry
 * @see Log
 * 
 * @since 16/11/2016
 * @author Guillermo S. Mazzali
 * @version 1.0
 */
public enum LogLevel {

	/**
	 * Log level for traces.
	 */
	TRACE,
	/**
	 * Log level for debugging.
	 */
	DEBUG,
	/**
	 * Log level for information.
	 */
	INFO,
	/**
	 * Log level for warnings.
	 */
	WARN,
	/**
	 * Log level for errors.
	 */
	ERROR,
	/**
	 * Log level for fatals errors.
	 */
	FATAL;
}