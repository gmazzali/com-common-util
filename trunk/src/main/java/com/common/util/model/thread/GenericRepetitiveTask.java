package com.common.util.model.thread;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.common.util.exception.CheckedException;

/**
 * La clase que nos permite crear una tarea que va a ejecutarse de manera repetitiva para poder controlarla de acuerdo al estado en el que se
 * establece esta tarea.
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <M>
 *            La clase que vamos a utilizar dentro del monitor de esta tarea.
 */
public abstract class GenericRepetitiveTask<M extends Serializable> extends GenericTask<M> {

	private static final long serialVersionUID = 382679944625887313L;

	/**
	 * El Logger que vamos a ocupar dentro de la clase.
	 */
	private static final Logger log = Logger.getLogger(GenericRepetitiveTask.class);

	/**
	 * @see com.common.util.model.thread.GenericTask#execute()
	 */
	@Override
	protected void execute() {
		GenericRepetitiveTask.log.trace("execute GenericRepetitiveTask");

		// Mientras el proceso no este detenido, lo ejecutamos.
		while (this.taskState != TaskStatus.STOP && this.dontStopCondition()) {

			try {
				// Realizamos una ejecución simple dentro del proceso.
				this.singleExecution();
			} catch (CheckedException e) {
				GenericRepetitiveTask.log.error("singleExecution GenericRepetitiveTask failed", e);
			}

			try {
				// Esperamos un tiempo antes de volver a correr la ejecución.
				Thread.sleep(this.getDelayExecution());

				// Si se pausa el proceso, lo bloqueamos.
				synchronized (this.mutex) {
					if (this.taskState == TaskStatus.PAUSE) {
						this.mutex.wait();
					}
				}

			} catch (InterruptedException e) {
				GenericRepetitiveTask.log.error("sleep or wait GenericRepetitiveTask failed", e);
			}
		}
	}

	/**
	 * La condición que nos dice si va a terminarse la ejecución de la tarea repetitiva o va a continuarse.
	 * 
	 * @return TRUE en caso de que se quiera continuar con la ejecución de la tarea, en caso de que quiera finalizarse el proceso se retorna FALSE.
	 */
	protected abstract Boolean dontStopCondition();

	/**
	 * La función encargada de realizar una única pasada de la ejecución que va a ejecutarse de manera ininterrumpida.
	 * 
	 * @throws CheckedException
	 *             En caso de que ocurra algún problema y que deba cortarse la ejecución en esta pasada.
	 */
	protected abstract void singleExecution() throws CheckedException;

	/**
	 * La función encargada de retornar el tiempo que va a esperarse entre 2 ejecuciones consecutivas de la tarea, para no sobrecargar los recursos
	 * que se estan utilizando.
	 * 
	 * @return El tiempo de espera entre 2 ejecuciones consecutivas (en milisengundos).
	 */
	protected abstract Long getDelayExecution();
}
