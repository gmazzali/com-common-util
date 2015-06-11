package com.common.util.business.service.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.common.util.business.service.EntityFactory;

/**
 * Define la clase donde se van a poder crear nuevas entidades.
 * 
 * @since 11/11/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class EntityFactoryImpl implements EntityFactory {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(EntityFactoryImpl.class);

	@Override
	public <E extends Serializable> E newInstance(Class<E> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			LOGGER.warn("Cannot create the instance for class: " + clazz, e);
		}
		return null;
	}
}