package com.common.util.model.thread.monitor;

import java.io.Serializable;

/**
 * La clase que va a representar un monitor de un proceso o una tarea para ser escuchado desde otro lado del sistema y asi poder tener idea del estado
 * de dicho proceso.
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <V>
 *            La clase que vamos a utilizar como valores para el monitor del proceso.
 */
public class GenericMonitor<V extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * El valor que estamos monitoreando.
	 */
	private V value;

	/**
	 * El semáforo para el valor de este monitor.
	 */
	private Object valueMutex;

	/**
	 * El contructor por omisión.
	 */
	public GenericMonitor() {
		this.value = null;
		this.valueMutex = new Object();
	}

	/**
	 * Se encarga de recuperar el valor que almacenamos dentro del monitor.
	 * 
	 * @return El valor almacenado dentro del monitor.
	 */
	public V getValue() {
		return this.value;
	}

	/**
	 * La función encargada de setear el valor que vamos a almacenar en el monitor para poder accederlo desde otro lugar del aplicativo.
	 * 
	 * @param value
	 *            El valor que vamos a almacenar dentro del monitor.
	 */
	public void setValue(V value) {
		synchronized (this.valueMutex) {
			this.value = value;
		}
	}
}