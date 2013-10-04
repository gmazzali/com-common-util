package com.common.util.model.thread;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.common.util.model.Entity;

/**
 * La clase que nos permite definir un proceso gen�rico para poder ejecutar tareas en segundo plano y poder controlar la misma de manera completa.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <M>
 *            La clase que vamos a utilizar dentro del monitor de esta tarea.
 */
public abstract class GenericTask<M extends Serializable> extends Entity<Long> {

	private static final long serialVersionUID = 72323549692226354L;

	/**
	 * El Logger que vamos a ocupar dentro de la clase.
	 */
	private static final Logger log = Logger.getLogger(GenericTask.class);

	/**
	 * La enumeraci�n que contiene los posibles estados de un proceso. Esta enumeraci�n se va a usar internamente dentro del proceso para controlar la
	 * ejecuci�n del mismo.
	 * 
	 * @author Guillermo Mazzali
	 * @version 1.0
	 */
	protected enum TaskStatus {
		/**
		 * El estado que indica que un proceso se inici� con �xito.
		 */
		INIT,
		/**
		 * El estado que indica que un proceso se encuentra en ejecuci�n.
		 */
		RUNNING,
		/**
		 * | El estado que indica que un proceso fue pausado de su ejecuci�n.
		 */
		PAUSE,
		/**
		 * El estado que indica que un proceso fue detenido de manera imprevista.
		 */
		STOP,
		/**
		 * El estado que indica que un proceso finaliz� con �xito.
		 */
		FINISH;
	}

	/**
	 * El nombre del proceso.
	 */
	protected final String name;
	/**
	 * El proceso que va a contener la tarea para su ejecuci�n en segundo plano.
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
	 * El constructor de una tarea para su ejecuci�n en segundo plano.
	 */
	public GenericTask() {
		this("Generic Task");
	}

	/**
	 * El constructor de una tarea para su ejecuci�n en segundo plano que recibe el nombre de la misma.
	 * 
	 * @param name
	 *            El nombre de la tarea que vamos a ejecutar.
	 */
	public GenericTask(String name) {
		GenericTask.log.trace("create a generic task = " + name);

		this.name = name;
		this.taskMonitor = new GenericMonitor<M>();

		this.initTask();
	}

	/**
	 * La funci�n encargada de inicializar el proceso que va a contener la tarea que vamos a realizar y de inicializar el estado de la misma.
	 */
	private void initTask() {
		GenericTask.log.trace("initializing the generic task");

		this.thread = new Thread(this.name) {
			@Override
			public void run() {
				GenericTask.this.beforeExecute();
				GenericTask.this.execute();
				GenericTask.this.afterExecute();
				GenericTask.this.taskState = TaskStatus.FINISH;
			};
		};
		this.thread.setDaemon(this.daemon);

		this.taskState = TaskStatus.INIT;
	}

	/**
	 * La funci�n encargada de ejecutar las acciones previas a la ejecuci�n de la tarea propiamente dicha del proceso. Generalmente va a encargarse de
	 * inicializar un conjunto de elementos para poder ejecutar el proceso principal.
	 */
	protected abstract void beforeExecute();

	/**
	 * La funci�n encargada de definir las acciones dentro de este proceso pero que todavia no va a ejecutarse hasta que se ejecute el metodo
	 * {@link GenericTask#start()}.
	 */
	protected abstract void execute();

	/**
	 * La funci�n encargada de ejecutar las acciones para terminar la ejecuci�n de la tarea, como ser, cierre de conexiones o archivos.
	 */
	protected abstract void afterExecute();

	/**
	 * La funci�n encargada de arrancar la tarea en caso de que este en estado inicial, o de resumir la ejecuci�n en caso de que el proceso este
	 * pausado.
	 */
	public void start() {
		synchronized (this.mutex) {
			GenericTask.log.trace("start generic task");

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
	 * La funci�n encargada de resumir la ejecuci�n de un proceso en caso de que este se haya pausado.
	 */
	public void resume() {
		synchronized (this.mutex) {
			GenericTask.log.trace("resume generic task");

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
	 * La funci�n encargada de pausar de manera temporal la ejecuci�n de la tarea para realizar otras acciones.
	 */
	public void pause() {
		synchronized (this.mutex) {
			GenericTask.log.trace("pause generic task");

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
	 * Funci�n encargada de detener de la ejecuci�n de la tarea de manera definida.
	 */
	public void stop() {
		synchronized (this.mutex) {
			GenericTask.log.trace("stop generic task");

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
	 * La funci�n encargada de reiniciar el proceso que estamos utilizando dentro de esta tarea.
	 */
	public synchronized void reboot() {
		synchronized (this.mutex) {
			GenericTask.log.trace("reboot generic task");
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
	 * La funci�n que nos permite esperar hasta que el proceso de la tarea finalice.
	 * 
	 * @throws InterruptedException
	 *             En caso de que falle la ejecuci�n del proceso durante la espera de su finalizaci�n.
	 */
	public void join() throws InterruptedException {
		GenericTask.log.trace("join generic task");
		this.thread.join();
	}

	/**
	 * La funci�n encargada de retornar el nombre de la tarea de este elemento.
	 * 
	 * @return El nombre de la tarea.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * La funci�n encargada de retornar el estado en el que se encuentra esta tarea.
	 * 
	 * @return El estado en el que se encuentra esta tarea.
	 */
	public TaskStatus getTaskState() {
		return this.taskState;
	}

	/**
	 * La funci�n encargada de retornar el monitor que estamos ocupando dentro de este m�todo.
	 * 
	 * @return El monitor que ocupamos.
	 */
	public GenericMonitor<M> getMonitor() {
		return this.taskMonitor;
	}

	/**
	 * La funci�n encargada de cargar el monitor que vamos a ocupar dentro de este m�todo.
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
	 * La funci�n encargada de cargar al proceso que estamos ejecutando como de usuario (FALSE) o como un demonio (TRUE).
	 * 
	 * @param daemon
	 *            El valor booleano que nos va a definir si el proceso va a ser de usuario (FALSE) o como un demonio (TRUE).
	 */
	public void setDemon(Boolean daemon) {
		GenericTask.log.trace("set daemon = " + daemon);

		if (daemon != null) {
			this.daemon = daemon;
			this.thread.setDaemon(this.daemon);
		}
	}
}