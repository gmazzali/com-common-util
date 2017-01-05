package com.common.util.business.service.impl;

import java.io.Serializable;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import com.common.util.business.service.EntityFactory;

/**
 * Define the implementation of the interface of the factory of entities.
 * 
 * @since 11/11/2014
 * @author Guillermo S. Mazzali
 * @version 1.0
 */
public class EntityFactoryImpl implements EntityFactory {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(EntityFactoryImpl.class);

	@Override
	public <E extends Serializable> E newInstance(Class<E> clazz, Object... parameters) {
		try {
			if (ArrayUtils.isNotEmpty(parameters)) {
				Class<?>[] parameterTypes = new Class<?>[parameters.length];
				for (int i = 0; i < parameters.length; i++) {
					parameterTypes[i] = parameters[i].getClass();
				}
				return clazz.getConstructor(parameterTypes).newInstance(parameters);
			} else {
				return clazz.newInstance();
			}
		} catch (Exception e) {
			LOGGER.warn("Cannot create the instance for class: " + clazz + " with parameters [" + parameters + "]", e);
		}
		return null;
	}
}