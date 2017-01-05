package com.common.util.business.service;

import java.io.Serializable;

/**
 * Define the interface of the factory of entities.
 * 
 * @since 11/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface EntityFactory extends Serializable {

	/**
	 * Allow create a new instance of the class received.
	 * 
	 * @param clazz
	 *            The class received to create a new instance.
	 * @param parameters
	 *            The parameters for the constructors.
	 * @param <E>
	 *            The type of the class received.
	 * @return The new instance of the class received.
	 */
	public <E extends Serializable> E newInstance(Class<E> clazz, Object... parameters);
}