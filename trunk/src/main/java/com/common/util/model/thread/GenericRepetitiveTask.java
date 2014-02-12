package com.common.util.model.thread;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.common.util.exception.UncheckedException;

/**
 * La clase que nos permite crear una tarea que va a ejecutarse de manera repetitiva para poder controlarla de acuerdo al estado en el que se
 * establece esta tarea.
 * 
 * @see GenericTask
 * 
 * @since 12/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <M>
 *            La clase que vamos a utilizar dentro del monitor de esta tarea.
 */
public abstract class GenericRepetitiveTask<M extends Serializable> extends GenericTask<M> {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GenericRepetitiveTask.class);

	@Override
	protected void execute() {
		GenericRepetitiveTask.log.trace("execute repetitive task");

		// Mientras el proceso no este detenido, lo ejecutamos.
		while (this.taskState != TaskStatus.STOP && this.dontStopCondition()) {
			try {
				
				// Si se pausa el proceso, lo bloqueamos.
				synchronized (this.stateMutex) {
					if (this.taskState == TaskStatus.PAUSE) {
						this.stateMutex.wait();
					}
				}

				// Realizamos una ejecución simple dentro del proceso.
				this.singleExecution();

				// Esperamos un tiempo antes de volver a correr la ejecución.
				Thread.sleep(this.getDelayExecution());
			} catch (Exception e) {
				GenericRepetitiveTask.log.error("single execution failed", e);
				
				if (e instanceof UncheckedException) {
					this.monitor.addError(((UncheckedException) e).getErrors());
				}
			}
		}
	}

	/**
	 * La condición que nos dice si va a terminarse la ejecución de la tarea repetitiva o va a continuarse.
	 * 
	 * @return <i>true</i> en caso de que se quiera continuar con la ejecución de la tarea, en caso de que quiera finalizarse el proceso se retorna
	 *         <i>false</i>.
	 */
	protected abstract Boolean dontStopCondition();

	/**
	 * La función encargada de realizar una ejecución simple de una tarea que va a ejecutarse de manera ininterrumpida.
	 * 
	 * @throws UncheckedException
	 *             En caso de que ocurra algún problema y que deba cortarse la ejecución en esta pasada.
	 */
	protected abstract void singleExecution() throws UncheckedException;

	/**
	 * La función encargada de retornar el tiempo que va a esperarse entre 2 ejecuciones consecutivas de la tarea, para no sobrecargar los recursos
	 * que se estan utilizando.
	 * 
	 * @return El tiempo de espera entre 2 ejecuciones consecutivas (en milisengundos).
	 */
	protected abstract Long getDelayExecution();
}