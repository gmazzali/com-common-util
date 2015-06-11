package com.common.util.business.service;

import java.io.Serializable;
import java.util.Date;

/**
 * Define la interfaz donde se van a poder obtener la fecha actual.
 * 
 * @since 11/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface DateService extends Serializable {

	/**
	 * Permite recuperar la fecha actual del sistema.
	 * 
	 * @return La fecha actual del sistema.
	 */
	public Date getCurrentDate();
}