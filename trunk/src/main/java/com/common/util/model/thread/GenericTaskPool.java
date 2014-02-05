package com.common.util.model.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * La clase que nos permite crear un pool de tareas para poder controlarlos a todos de manera conjunta dentro de un sistema en ejecución y que permite
 * reiniciarlos una vez que estos fueron finalizados.
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <T>
 *            La clase de los hilos de ejecución que vamos a manejar dentro de este pool de tareas.
 */
public class GenericTaskPool<T extends GenericTask<?>> {

	/**
	 * La enumeración que contiene los posibles estados en los que va a estar el pool de procesos.
	 * 
	 * @author Guillermo Mazzali
	 * @version 1.0
	 */
	public enum PoolStatus {
		INIT, RUNNING, PAUSE, STOP;
	}

	/**
	 * El Logger que vamos a ocupar dentro de la clase.
	 */
	private static final Logger log = Logger.getLogger(GenericTaskPool.class);

	/**
	 * El nombre del grupo de las tareas.
	 */
	protected String name;
	/**
	 * El listado de las tareas que vamos a manejar dentro de este pool.
	 */
	protected List<T> tasks;
	/**
	 * El estado del pool
	 */
	protected PoolStatus poolStatus;
	/**
	 * El elemento que nos permite bloquear el valor de lectura/escritura dentro del monitor.
	 */
	protected Object poolMutex = new Object();
	/**
	 * Los procesos que permiten accionar sobre las tareas.
	 */
	protected Thread tasksThread;
	/**
	 * El monitor que vamos a utilizar para monitorear el progreso de las acciones sobre las tareas, como arranque, pausa, parada o reinicio.
	 */
	protected GenericMonitor<Double> poolMonitor;

	/**
	 * El constructor de un pool de tareas que recibe el nombre del grupo.
	 * 
	 * @param name
	 *            El nombre del grupo de procesos.
	 */
	public GenericTaskPool(String name) {
		GenericTaskPool.log.trace("create GenericTaskPool");

		this.name = name;
		this.tasks = Collections.synchronizedList(new ArrayList<T>());
		this.poolStatus = PoolStatus.INIT;
		this.poolMonitor = new GenericMonitor<Double>();
		this.tasksThread = new Thread();
	}

	/**
	 * La función encargada de arrancar las tareas que tenemos dentro de este pool.
	 */
	public void start() {
		GenericTaskPool.log.trace("start GenericTaskPool");

		synchronized (this.poolMutex) {

			// Arrancamos solo si el pool de procesos esta iniciado.
			if (this.poolStatus == PoolStatus.INIT) {

				// Creamos un proceso para arrancar el pool de tareas.
				this.tasksThread = new Thread() {
					@Override
					public void run() {

						Double size = (double) GenericTaskPool.this.tasks.size();

						for (int index = 0; index < size; index++) {
							GenericTaskPool.this.tasks.get(index).start();
							GenericTaskPool.this.poolMonitor.setValue((index + 1) / size);
						}
						GenericTaskPool.this.poolStatus = PoolStatus.RUNNING;
					};
				};

				this.tasksThread.start();
			}
		}
	}

	/**
	 * La función encargada de pausar las tareas que se esten ejecutando en este momento dentro del pool.
	 */
	public void pause() {
		GenericTaskPool.log.trace("pause GenericTaskPool");

		synchronized (this.poolMutex) {

			// Pausamos solo si el pool de procesos esta arrancado.
			if (this.poolStatus == PoolStatus.RUNNING) {

				// Creamos un proceso para pausar el pool de tareas.
				this.tasksThread = new Thread() {
					@Override
					public void run() {

						Double size = (double) GenericTaskPool.this.tasks.size();

						for (int index = 0; index < size; index++) {
							GenericTaskPool.this.tasks.get(index).pause();
							GenericTaskPool.this.poolMonitor.setValue((index + 1) / size);
						}
						GenericTaskPool.this.poolStatus = PoolStatus.PAUSE;
					};
				};
				this.tasksThread.start();
			}
		}
	}

	/**
	 * La función encargada de resumir las tareas que se esten ejecutando en este momento dentro del pool.
	 */
	public void resume() {
		GenericTaskPool.log.trace("resume GenericTaskPool");

		synchronized (this.poolMutex) {

			// Arrancamos solo si el pool de procesos esta pausado.
			if (this.poolStatus == PoolStatus.PAUSE) {

				// Creamos un proceso para pausar el pool de tareas.
				this.tasksThread = new Thread() {
					@Override
					public void run() {

						Double size = (double) GenericTaskPool.this.tasks.size();

						for (int index = 0; index < size; index++) {
							GenericTaskPool.this.tasks.get(index).resume();
							GenericTaskPool.this.poolMonitor.setValue((index + 1) / size);
						}
						GenericTaskPool.this.poolStatus = PoolStatus.RUNNING;
					};
				};
				this.tasksThread.start();
			}
		}
	}

	/**
	 * La función encargada de detener por completo las tareas que actualmente se encuentren dentro de este pool.
	 */
	public void stop() {

		synchronized (this.poolMutex) {
			GenericTaskPool.log.trace("stop GenericTaskPool");

			// Creamos un proceso para detener el pool de tareas.
			this.tasksThread = new Thread() {
				@Override
				public void run() {

					Double size = (double) GenericTaskPool.this.tasks.size();

					for (int index = 0; index < size; index++) {
						GenericTaskPool.this.tasks.get(index).stop();
						GenericTaskPool.this.poolMonitor.setValue((index + 1) / size);
					}
					GenericTaskPool.this.poolStatus = PoolStatus.STOP;
				};
			};

			this.tasksThread.start();
		}
	}

	/**
	 * La función encargada de reinicar los procesos que hay dentro del pool de procesos, deteniendo su ejecución de manera controlada y volviendo a
	 * inicializar los mismos, para poder arrancarlos nuevamente.
	 */
	public void reboot() {

		synchronized (this.poolMutex) {
			GenericTaskPool.log.trace("reboot GenericTaskPool");

			// Creamos un proceso para reiniciar el pool de tareas.
			this.tasksThread = new Thread() {
				@Override
				public void run() {

					Double size = (double) GenericTaskPool.this.tasks.size();

					for (int index = 0; index < size; index++) {
						GenericTaskPool.this.tasks.get(index).reboot();
						GenericTaskPool.this.poolMonitor.setValue((index + 1) / size);
					}
					GenericTaskPool.this.poolStatus = PoolStatus.INIT;
				};
			};

			this.tasksThread.start();
		}
	}

	/**
	 * La función encargada de esperar a que todos los procesos internos finalizen.
	 * 
	 * @throws InterruptedException
	 *             En caso de que ocurra un problema al momento de esperar la finalización de los procesos.
	 */
	public void join() throws InterruptedException {

		synchronized (this.poolMutex) {
			GenericTaskPool.log.trace("join GenericTaskPool");

			if (this.tasksThread != null) {
				this.tasksThread.join();
			}
			for (T t : this.tasks) {
				t.join();
			}
		}
	}

	/**
	 * La función encargada de agregar una nueva tarea dentro del pool para su control y gestión.
	 * 
	 * @param tasksThread
	 *            La nueva tarea que va a colocarse dentro del pool.
	 */
	public void addTask(final T task) {
		GenericTaskPool.log.trace("addTask GenericTaskPool");

		if (task != null) {
			synchronized (this.poolMutex) {

				// Creamos un proceso para reiniciar el pool de tareas.
				this.tasksThread = new Thread() {
					@Override
					public void run() {
						GenericTaskPool.this.poolMonitor.setValue(0.0);

						GenericTaskPool.this.tasks.add(task);

						switch (GenericTaskPool.this.poolStatus) {

						case INIT:
							break;

						case RUNNING:
							task.start();
							break;

						case PAUSE:
							task.pause();
							break;

						case STOP:
							task.stop();
							break;
						}
						GenericTaskPool.this.poolMonitor.setValue(1.0);
					}
				};
			}
			this.tasksThread.start();
		}
	}

	/**
	 * La función encargada de setear el monitor que vamos a ocupar dentro de este pool de tareas para controlar el progreso de las acciones sobre los
	 * mismos.
	 * 
	 * @param taskMonitor
	 *            El monitor de las acciones sobre las tareas.
	 */
	public void setTaskMonitor(final GenericMonitor<Double> taskMonitor) {
		if (taskMonitor != null) {
			synchronized (this.poolMonitor) {
				this.poolMonitor = taskMonitor;
			}
		}
	}

	/**
	 * La función encargada de cargar el listado completo de tareas.
	 * 
	 * @param tasks
	 *            El listado completo de las tareas a controlar de manera conjunta.
	 */
	public void setTasks(final List<T> tasks) {
		if (tasks != null) {
			synchronized (this.poolMutex) {
				this.tasks = tasks;
			}
		}
	}

	/**
	 * La función encargada de retornar el nombre del grupo de las tareas.
	 * 
	 * @return El nombre del grupo de las tareas.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * La función que retorna el estado en el que se encuentra el pool de procesos.
	 * 
	 * @return El estado en el que se encuentra el pool de procesos.
	 */
	public PoolStatus getPoolStatus() {
		return this.poolStatus;
	}

	/**
	 * La función encargada de retornar el monitor de los avances de las acciones sobre las tareas del pool.
	 * 
	 * @return El monitor de las acciones de las tareas del pool.
	 */
	public GenericMonitor<Double> getTaskMonitor() {
		return this.poolMonitor;
	}

	/**
	 * La función encargada de retornar el listado de las tareas dentro del pool.
	 * 
	 * @return El listado de las tareas dentro del pool.
	 */
	public List<T> getTasks() {
		return this.tasks;
	}
}
