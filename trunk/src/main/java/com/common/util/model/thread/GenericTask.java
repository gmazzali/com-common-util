package com.common.util.model.thread;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.common.util.exception.UncheckedException;
import com.common.util.model.Entity;
import com.common.util.model.thread.monitor.GenericErrorMonitor;

/**
 * Nos permite un proceso genérico para poder ejecutar tareas en segundo plano y poder controlar la misma de manera completa.
 * 
 * @see GenericErrorMonitor
 * 
 * @since 11/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <M>
 *            La clase que vamos a utilizar dentro del monitor de esta tarea.
 */
public abstract class GenericTask<M extends Serializable> extends Entity<Long> {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GenericTask.class);

	/**
	 * El nombre del proceso.
	 */
	protected final String name;
	/**
	 * El proceso que va a contener la tarea para su ejecución en segundo plano.
	 */
	protected Thread thread;
	/**
	 * El monitor que vamos a manejar dentro de esta tarea.
	 */
	protected GenericErrorMonitor<M> monitor;
	/**
	 * El estado en el que tenemos la tarea.
	 */
	protected TaskStatus taskState;
	/**
	 * El valor booleano que nos dice si el proceso va a ser de usuario o un demonio.
	 */
	protected Boolean daemon = false;

	/**
	 * El elemento que nos permite bloquear el valor de lectura/escritura dentro del monitor.
	 */
	protected Object mutex = new Object();

	/**
	 * El constructor de una tarea para su ejecución en segundo plano.
	 */
	public GenericTask() {
		this("generic task");
	}

	/**
	 * El constructor de una tarea para su ejecución en segundo plano que recibe el nombre de la misma.
	 * 
	 * @param name
	 *            El nombre de la tarea que vamos a ejecutar.
	 */
	public GenericTask(String name) {
		GenericTask.log.trace("create a generic task = " + name);

		this.name = name;
		this.monitor = new GenericErrorMonitor<M>();

		this.initTask();
	}

	/**
	 * Se encarga de inicializar el proceso que va a contener la tarea que vamos a realizar y de inicializar el estado de la misma.
	 */
	private void initTask() {
		GenericTask.log.trace("initializing the generic task");

		this.thread = new Thread(this.name) {
			@Override
			public void run() {
				try {
					// Si el proceso esta corriendo, ejecutamos la pre-tarea.
					if (TaskStatus.RUNNING.equals(GenericTask.this.taskState)) {
						GenericTask.this.beforeExecute();
					}

					// Si el proceso esta corriendo, ejecutamos la tarea.
					if (TaskStatus.RUNNING.equals(GenericTask.this.taskState)) {
						GenericTask.this.execute();
					}

					// Si el proceso esta corriendo, ejecutamos la post-tarea.
					if (TaskStatus.RUNNING.equals(GenericTask.this.taskState)) {
						GenericTask.this.afterExecute();
					}

					// Marcamos el proceso como finalizado.
					GenericTask.this.taskState = TaskStatus.FINISH;
				} catch (UncheckedException ex) {
					GenericTask.this.monitor.addError(ex.getErrors());
				}
			};
		};

		// Seteamos el tipo de proceso.
		this.thread.setDaemon(this.daemon);

		// Marcamos como inicializado el proceso.
		this.taskState = TaskStatus.INIT;
	}

	/**
	 * La función encargada de arrancar la tarea en caso de que este en estado inicial, o de resumir la ejecución en caso de que el proceso este
	 * pausado.
	 */
	public void start() {
		GenericTask.log.trace("start generic task");

		synchronized (this.mutex) {
			switch (this.taskState) {
				case INIT:
					this.taskState = TaskStatus.RUNNING;
					this.thread.start();
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
		GenericTask.log.trace("resume generic task");

		synchronized (this.mutex) {
			switch (this.taskState) {
				case PAUSE:
					this.taskState = TaskStatus.RUNNING;
					this.mutex.notify();
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
		GenericTask.log.trace("pause generic task");

		synchronized (this.mutex) {
			switch (this.taskState) {
				case RUNNING:
					this.taskState = TaskStatus.PAUSE;
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
		GenericTask.log.trace("stop generic task");

		synchronized (this.mutex) {
			switch (this.taskState) {
				case RUNNING:
					this.taskState = TaskStatus.STOP;
					break;

				case PAUSE:
					this.taskState = TaskStatus.STOP;
					this.mutex.notify();
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
		GenericTask.log.trace("reboot generic task");

		synchronized (this.mutex) {
			try {
				this.stop();
				this.thread.join();
				this.initTask();
			} catch (InterruptedException e) {
				GenericTask.log.error("reboot generic task failed", e);
			}
		}
	}

	/**
	 * La función que nos permite esperar hasta que el proceso de la tarea finalice.
	 * 
	 * @throws InterruptedException
	 *             En caso de que falle la ejecución del proceso durante la espera de su finalización.
	 */
	public void join() throws InterruptedException {
		GenericTask.log.trace("join generic task");

		this.thread.join();
	}

	/**
	 * Permite definir el modo en el que se va a correr el proceso de la tarea.
	 * 
	 * @see Thread#setDaemon(boolean)
	 * 
	 * @param daemon
	 *            El valor booleano que nos va a definir el tipo de proceso a ejecutar. Si es <i>true</i>, el proceso va a ejecutarse como un demonio,
	 *            en caso de que sea <i>false</i> el proceso va a ejecutarse como de usuario.
	 */
	public void setDaemon(Boolean daemon) {
		GenericTask.log.trace("set daemon = " + daemon);

		if (daemon != null) {
			this.daemon = daemon;
			this.thread.setDaemon(this.daemon);
		}
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
	 * @return El monitor que ocupamos para visualizar el avance de la tarea.
	 */
	public GenericErrorMonitor<M> getMonitor() {
		return this.monitor;
	}

	/**
	 * La función encargada de ejecutar las acciones previas a la ejecución de la tarea propiamente dicha del proceso. Permite inicializar un conjunto
	 * de atributos para el proceso. Esta función se va a ejecutar de manera integra en caso de que se detenga el proceso.
	 * 
	 * @see GenericTask#execute()
	 * @see GenericTask#beforeExecute()
	 * 
	 * @throws UncheckedException
	 *             En caso de que ocurra una falla al momento de preparar el proceso.
	 */
	protected abstract void beforeExecute() throws UncheckedException;

	/**
	 * La función encargada de definir la ejecución en si misma del proceso.
	 * 
	 * @see GenericTask#afterExecute()
	 * @see GenericTask#beforeExecute()
	 * 
	 * @throws UncheckedException
	 *             En caso de que ocurra una falla al momento de ejecutar el proceso.
	 */
	protected abstract void execute() throws UncheckedException;

	/**
	 * La función encargada de ejecutar las acciones para terminar la ejecución de la tarea, como cierres de conexiones o archivos.
	 * 
	 * @see GenericTask#afterExecute()
	 * @see GenericTask#execute()
	 * 
	 * @throws UncheckedException
	 *             En caso de que ocurra una falla al momento de finalziar el proceso.
	 */
	protected abstract void afterExecute() throws UncheckedException;
}