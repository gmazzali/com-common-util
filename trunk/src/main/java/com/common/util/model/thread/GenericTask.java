package com.common.util.model.thread;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.common.util.model.Entity;

/**
 * La clase que nos permite definir una tarea a ejecutarse en segundo plano para la ejecución concurrente de tareas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <M>
 *            La clase que vamos a utilizar dentro del monitor de esta tarea.
 */
public abstract class GenericTask<M extends Serializable> extends Entity<Integer> {

	private static final long serialVersionUID = 72323549692226354L;

	/**
	 * El Logger que vamos a ocupar dentro de la clase.
	 */
	private static final Logger log = Logger.getLogger(GenericTask.class);

	/**
	 * La enumeración que contiene los posibles estados en los que va a estar un proceso dentro de un sistema.
	 * 
	 * @author Guillermo Mazzali
	 * @version 1.0
	 */
	public enum TaskStatus {
		INIT, RUNNING, PAUSE, STOP;
	}

	/**
	 * El nombre del proceso.
	 */
	protected String name;
	/**
	 * El proceso que va a contener la tarea para su ejecución en segundo plano.
	 */
	protected Thread thread;
	/**
	 * El monitor que vamos a manejar dentro de esta tarea.
	 */
	protected GenericMonitor<M> taskMonitor;
	/**
	 * El estado en el que tenemos la tarea.
	 */
	protected TaskStatus taskState;
	/**
	 * El elemento que nos permite bloquear el valor de lectura/escritura dentro del monitor.
	 */
	protected Object mutex = new Object();
	/**
	 * El valor booleano que nos dice si el proceso va a ser de usuario o un demonio.
	 */
	protected Boolean daemon = false;

	/**
	 * El constructor de una tarea para su ejecución en segundo plano.
	 */
	public GenericTask() {
		this("DEFAULT TASK");
	}

	/**
	 * El constructor de una tarea para su ejecución en segundo plano que recibe el nombre de la misma.
	 * 
	 * @param name
	 *            El nombre de la tarea que vamos a ejecutar.
	 */
	public GenericTask(String name) {
		GenericTask.log.trace("GenericTask create");

		this.name = name;
		this.taskMonitor = new GenericMonitor<M>();

		this.initTask();
	}

	/**
	 * La función encargada de inicializar el proceso que va a contener la tarea que vamos a realizar y de inicializar el estado de la misma.
	 */
	private void initTask() {
		GenericTask.log.trace("GenericTask initTask");

		this.thread = new Thread() {
			@Override
			public void run() {
				GenericTask.this.beforeExecute();
				GenericTask.this.execute();
				GenericTask.this.afterExecute();
			};
		};
		this.thread.setDaemon(this.daemon);

		this.taskState = TaskStatus.INIT;
	}

	/**
	 * La función encargada de arrancar la tarea en caso de que este en estado inicial, o de resumir la ejecuión en caso de que el proceso este
	 * pausado.
	 */
	public void start() {

		synchronized (this.mutex) {
			GenericTask.log.trace("GenericTask start");

			switch (this.taskState) {
				case INIT:
					this.taskState = TaskStatus.RUNNING;
					this.thread.start();
					break;

				case RUNNING:
					break;

				case PAUSE:
					break;

				case STOP:
					break;

				default:
					break;
			}
		}
	}

	/**
	 * La función encargada de resumir la ejecución de un proceso en caso de que este se haya pausado.
	 */
	public void resume() {

		synchronized (this.mutex) {
			GenericTask.log.trace("GenericTask resume");

			switch (this.taskState) {
				case INIT:
					break;

				case RUNNING:
					break;

				case PAUSE:
					this.taskState = TaskStatus.RUNNING;
					this.mutex.notify();
					break;

				case STOP:
					break;

				default:
					break;
			}
		}
	}

	/**
	 * La función encargada de pausar de manera temporal la ejecución de la tarea para realizar otras acciones.
	 */
	public void pause() {

		synchronized (this.mutex) {
			GenericTask.log.trace("GenericTask pause");

			switch (this.taskState) {
				case INIT:
					break;

				case RUNNING:
					this.taskState = TaskStatus.PAUSE;
					break;

				case PAUSE:
					break;

				case STOP:
					break;

				default:
					break;
			}
		}
	}

	/**
	 * Función encargada de detener de la ejecución de la tarea de manera definida.
	 */
	public void stop() {

		synchronized (this.mutex) {
			GenericTask.log.trace("GenericTask stop");

			switch (this.taskState) {
				case INIT:
					break;

				case RUNNING:
					this.taskState = TaskStatus.STOP;
					break;

				case PAUSE:
					this.taskState = TaskStatus.STOP;
					this.mutex.notify();
					break;

				case STOP:
					break;

				default:
					break;
			}
		}
	}

	/**
	 * La función encargada de reiniciar el proceso que estamos utilizando dentro de esta tarea.
	 */
	public synchronized void reboot() {

		synchronized (this.mutex) {
			GenericTask.log.trace("GenericTask reboot");

			try {
				this.stop();
				this.thread.join();
				this.initTask();

			} catch (InterruptedException e) {
				GenericTask.log.error("reboot GenericTask failed", e);
			}
		}
	}

	/**
	 * La función encargada de setear al proceso que estamos ejecutando como de usuario (FALSE) o como un demonio (TRUE).
	 * 
	 * @param daemon
	 *            El valor booleano que nos va a definir si el proceso va a ser de usuario (FALSE) o como un demonio (TRUE).
	 */
	public void setDemon(Boolean daemon) {
		GenericTask.log.trace("GenericTask setDemon");

		if (daemon != null) {
			this.daemon = daemon;
			this.thread.setDaemon(this.daemon);
		}
	}

	/**
	 * La función encargada de setear el monitor que vamos a ocupar dentro de este método.
	 * 
	 * @param taskMonitor
	 *            El monitor que vamos a ocupar dentro de este proceso.
	 */
	public void setMonitor(GenericMonitor<M> taskMonitor) {
		if (taskMonitor != null) {
			this.taskMonitor = taskMonitor;
		}
	}

	/**
	 * La función que nos permite esperar hasta que el proceso interno de la tarea finalize.
	 * 
	 * @throws InterruptedException
	 *             La excepción que lanza en caso de que falle la ejecución del proceso durante la espera de su finalización.
	 */
	public void join() throws InterruptedException {
		GenericTask.log.trace("GenericTask join");

		this.thread.join();
	}

	/**
	 * La función encargada de ejecutar las acciones necesarias para poder iniciar de manera normal la ejecución de la tarea. Permite inicializar las
	 * variables necesarias para la tarea.
	 */
	protected abstract void beforeExecute();

	/**
	 * La función encargada de definir las acciones dentro de este proceso.
	 */
	protected abstract void execute();

	/**
	 * La función encargada de ejecutar las acciones para terminar la ejecución de la tarea, como ser, cierre de conexiones o archivos.
	 */
	protected abstract void afterExecute();

	/**
	 * La función encargada de setear el nombre del proceso.
	 * 
	 * @param name
	 *            El nombre del proceso.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * La función encargada de retornar el nombre de la tarea de este elemento.
	 * 
	 * @return El nombre de la tarea.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * La función encargada de retornar el estado en el que se encuentra esta tarea.
	 * 
	 * @return El estado en el que se encuentra esta tarea.
	 */
	public TaskStatus getTaskState() {
		return this.taskState;
	}

	/**
	 * La función encargada de retornar el monitor que estamos ocupando dentro de este método.
	 * 
	 * @return El monitor que ocupamos.
	 */
	public GenericMonitor<M> getMonitor() {
		return this.taskMonitor;
	}
}
