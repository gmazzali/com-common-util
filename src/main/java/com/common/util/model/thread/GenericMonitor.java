package com.common.util.model.thread;

import java.io.Serializable;

/**
 * La clase que va a representar un monitor de un proceso o una tarea para ser escuchado desde otro lado del sistema y asi poder monitorear dicho
 * proceso o tarea.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <V>
 *            La clase que vamos a utilizar como valores para monitorizar un proceso.
 */
public class GenericMonitor<V extends Serializable> implements Serializable {

	private static final long serialVersionUID = -6246401889822665171L;
	
	/**
	 * El valor que estamos monitoreando.
	 */
	private V value;
	/**
	 * El elemento que nos permite bloquear el valor de lectura/escritura dentro del monitor.
	 */
	private Object mutex = new Object();

	/**
	 * El contructor por omisión.
	 */
	public GenericMonitor() {
		this.value = null;
	}

	/**
	 * La función encargada de setear el valor que vamos a almacenar en el monitor para poder accederlo desde otro lugar del aplicativo.
	 * 
	 * @param value
	 *            El valor que vamos a almacenar dentro del monitor.
	 */
	public void setValue(V value) {
		synchronized (this.mutex) {
			this.value = value;
		}
	}

	/**
	 * La función encargada de recuperar el valor que almacenamos dentro del monitor.
	 * 
	 * @return El valor almacenado dentro del monitor.
	 */
	public V getValue() {
		return this.value;
	}
}
