package com.common.util.model.thread;

/**
 * La enumeración que contiene los posibles estados de un proceso. Esta enumeración se va a usar internamente dentro del proceso para controlar la
 * ejecución del mismo.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum TaskStatus {
	/**
	 * El estado que indica que un proceso se inició con éxito.
	 */
	INIT,
	/**
	 * El estado que indica que un proceso se encuentra en ejecución.
	 */
	RUNNING,
	/**
	 * | El estado que indica que un proceso fue pausado de su ejecución.
	 */
	PAUSE,
	/**
	 * El estado que indica que un proceso fue detenido de manera imprevista.
	 */
	STOP,
	/**
	 * El estado que indica que un proceso finalizó con éxito.
	 */
	FINISH;
}