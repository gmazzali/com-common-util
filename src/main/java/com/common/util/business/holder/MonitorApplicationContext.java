package com.common.util.business.holder;

import java.io.Serializable;

/**
 * Permite monitorear el progreso de carga del contexto de la aplicación actualmente.
 * 
 * @see LoaderApplicationContext
 * 
 * @since 17/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class MonitorApplicationContext implements Serializable {
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

	public void setBeanCount(Integer beanCount) {
		synchronized (this.beanCount) {
			this.beanCount = beanCount;
		}
	}

	public Integer getCurrentBeanCount() {
		synchronized (this.currentBeanCount) {
			return currentBeanCount;
		}
	}

	public void setCurrentBeanCount(Integer currentBeanCount) {
		synchronized (this.currentBeanCount) {
			this.currentBeanCount = currentBeanCount;
		}
	}

	public void incrementBeanCount() {
		synchronized (this.currentBeanCount) {
			this.currentBeanCount++;
		}
	}

	public String getCurrentBeanName() {
		synchronized (this.currentBeanName) {
			return currentBeanName;
		}
	}

	public void setCurrentBeanName(String currentBeanName) {
		synchronized (this.currentBeanName) {
			this.currentBeanName = currentBeanName;
		}
	}

	public boolean isContextComplete() {
		return contextComplete;
	}

	public void setContextComplete(boolean contextComplete) {
		this.contextComplete = contextComplete;
	}
}