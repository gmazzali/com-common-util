package com.common.util.business.service;

import java.io.Serializable;

/**
 * Define la interfaz donde se van a poder crear nuevas entidades.
 * 
 * @since 11/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface EntityFactory extends Serializable {

	/**
	 * Permite crear una nueva instancia de la clase recibida.
	 * 
	 * @param clazz
	 *            La clase de las entidades que vamos a crear una nueva entidad.
	 * @return La nueva instancia de la clase creada.
	 */
	public <E extends Serializable> E newInstance(Class<E> clazz);
}