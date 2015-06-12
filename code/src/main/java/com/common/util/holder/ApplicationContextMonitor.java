package com.common.util.holder;

import java.io.Serializable;

/**
 * Permite monitorear el progreso de carga del contexto de la aplicación actualmente.
 * 
 * @see ApplicationContextLoader
 * 
 * @since 17/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ApplicationContextMonitor implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * La cantidad de beans dentro del contexto.
	 */
	private Integer beanCount = 0;
	/**
	 * El bean actualmente en carga.
	 */
	private Integer currentBeanCount = 0;
	/**
	 * El nombre del bean actualmente en carga.
	 */
	private String currentBeanName = "";
	/**
	 * El valor booleano que nos indica que se termino la carga del contexto.
	 */
	private boolean contextComplete = false;

	/**
	 * Permite recuperar la cantidad de beans dentro del contexto.
	 * 
	 * @return La cantidad de beans dentro del contexto.
	 */
	public Integer getBeanCount() {
		synchronized (this.beanCount) {
			return beanCount;
		}
	}

	/**
	 * Permite cargar la cantidad de beans que vamos a cargar dentro del contexto.
	 * 
	 * @param beanCount
	 *            La cantidad de beans que vamos a cargar dentro del contexto.
	 */
	public void setBeanCount(Integer beanCount) {
		synchronized (this.beanCount) {
			this.beanCount = beanCount;
		}
	}

	/**
	 * Permite saber cuantos beans se cargaron hasta este momento.
	 * 
	 * @return El numero de beans que cargamos hasta este momento.
	 */
	public Integer getCurrentBeanCount() {
		synchronized (this.currentBeanCount) {
			return currentBeanCount;
		}
	}

	/**
	 * Permite incrementar en 1 la cantidad de beans cargados en el contexto.
	 */
	public void incrementBeanCount() {
		synchronized (this.currentBeanCount) {
			this.currentBeanCount++;
		}
	}

	/**
	 * El nombre del bean que actualmente se esta cargando dentro del contexto.
	 * 
	 * @return El nombre del bean que actualmente se esta cargado dentro del contexto.
	 */
	public String getCurrentBeanName() {
		synchronized (this.currentBeanName) {
			return currentBeanName;
		}
	}

	/**
	 * Permite cargar el nombre del bean que actualmente se esta cargando dentro del contexto.
	 * 
	 * @param currentBeanName
	 *            El nombre del bean que actualmente se esta cargando dentro del contexto.
	 */
	public void setCurrentBeanName(String currentBeanName) {
		synchronized (this.currentBeanName) {
			this.currentBeanName = currentBeanName;
		}
	}

	/**
	 * Permite saber cuando el contexto se encuentra completamente cargado.
	 * 
	 * @return <code>true</code> cuando el contexto se encuentra completamente cargado, en caso contrario, retorna <code>false</code>.
	 */
	public boolean isContextComplete() {
		return contextComplete;
	}

	/**
	 * Permite cargar el estado de contexto.
	 * 
	 * @param contextComplete
	 *            el valor booleano que nos indica si el contexto esta completo o no.
	 */
	public void setContextComplete(boolean contextComplete) {
		this.contextComplete = contextComplete;
	}
}