package com.common.util.model.thread.monitor;

import java.io.Serializable;

import com.common.util.exception.error.Errors;

/**
 * Representa el monitor de un proceso para ser escuchado desde otro lado del sistema y asi poder tener una idea del estado de dicho proceso, como
 * tambien, monitorear el listado de errores que surjan en el proceso en si mismo.
 * 
 * @see GenericMonitor
 * 
 * @since 11/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <V>
 *            La clase que vamos a utilizar como valores para el monitor del proceso.
 */
public class GenericErrorMonitor<V extends Serializable> extends GenericMonitor<V> {
	private static final long serialVersionUID = 1L;

	/**
	 * El conjunto de errores que pueden aparecer en la ejecución de un proceso.
	 */
	protected Errors errors;

	/**
	 * El semaforo para el listado de errores de este monitor.
	 */
	private Object errorMutex;

	/**
	 * El constructor por omisión de este monitor.
	 */
	public GenericErrorMonitor() {
		super();
		this.errors = new Errors();
		this.errorMutex = new Object();
	}

	/**
	 * Permite verificar si un monitor capturo un conjunto de errores.
	 * 
	 * @return <i>true</i> en caso de que se haya capturado un conjunto de errores, en caso de que no, se retorna <i>false</i>.
	 */
	public boolean hasErrors() {
		return this.errors.hasErrorsDetails();
	}

	/**
	 * Permite agregar un error al monitor, para luego mandarlo a otra parte del sistema.
	 * 
	 * @param message
	 *            El mensaje del error.
	 * @param parameters
	 *            El conjunto de parámetros del sistema.
	 */
	public void addError(String message, Object... parameters) {
		synchronized (this.errorMutex) {
			this.errors.addError(message, parameters);
		}
	}

	/**
	 * Permite agregar un conjunto de errores al monitor, para luego mandarlo a otra parte del sistema.
	 * 
	 * @param errors
	 *            El conjunto de errores que vamos a cargar.
	 */
	public void addError(Errors errors) {
		synchronized (this.errorMutex) {
			this.errors.merge(errors);
		}
	}

	/**
	 * Permite recuperar el listado de los errores que tenemos dentro de este monitor. Esta función tambien se encarga de reiniciar el listado de
	 * errores dentro de este monitor.
	 * 
	 * @return El listado de los errores que tenemos dentro de este monitor antes de reiniciarlo.
	 */
	public Errors getErrors() {
		Errors temporalErrors = this.errors;

		synchronized (this.errorMutex) {
			this.errors = new Errors();
		}

		return temporalErrors;
	}
}